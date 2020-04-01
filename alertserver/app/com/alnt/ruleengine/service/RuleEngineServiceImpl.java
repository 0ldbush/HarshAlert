package com.alnt.ruleengine.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.classdef.domain.dto.ClassDefDTO;
import com.alnt.platform.core.classdef.domain.dto.FieldDefDTO;
import com.alnt.platform.core.classdef.service.ClassDefService;
import com.alnt.policyengine.domain.Rule;
import com.alnt.policyengine.domain.dto.PolicyDTO;
import com.alnt.policyengine.domain.dto.PolicyGroupDTO;
import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.policyengine.domain.dto.RuleSetDTO;
import com.alnt.policyengine.service.PolicyGroupService;
import com.alnt.policyengine.service.PolicyService;
import com.alnt.policyengine.service.PolicyServiceImpl;
import com.alnt.policyengine.service.RuleService;
import com.alnt.ruleengine.business.DefaultInferenceEngine;
import com.alnt.ruleengine.business.RuleLibrary;
import com.alnt.ruleengine.domain.dto.DefaultOutput;
import com.alnt.ruleengine.mapper.RuleMapper;
import com.alnt.ruleengine.repository.RulesRepository;
import com.google.inject.name.Named;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class RuleEngineServiceImpl extends BaseServiceImpl<Rule, RuleDTO> implements RuleEngineService {
    
	
	@Inject
	DefaultInferenceEngine inferenceEngine;
	
	@Inject
	PolicyService policyService;

	@Inject
	PolicyGroupService pgService;
	
	
	@Inject
	RuleService ruleService;

	@Inject
	RuleLibrary rbl;
	
	private ClassDefService classDefService;
	
   // private final static RuleLibrary rbl = new RuleLibrary(); //.put("dsl", myObj);

	
	@Inject
	public RuleEngineServiceImpl(HttpExecutionContext ec, RulesRepository repository,@Named("base") ClassDefService classDefService) {
		
		super( ec, repository, RuleMapper.INSTANCE);
		this.classDefService=classDefService;
		
		
	}

	@Override
	public CompletableFuture<DefaultOutput> evaluateRule(RequestDetails requestDetails,  Map<String,Object> map,Long ruleId) {

		CompletionStage<Optional<RuleDTO>> ruleStage = ruleService.get(requestDetails, ruleId);
		RuleDTO ruleDTO = null;
		try {
			ruleDTO = ruleStage.toCompletableFuture().get().get();
		} catch (Exception e) {
			e.printStackTrace();
			 
		}
		
		  System.err.println(" ruleDTO.get() " +ruleDTO.getId());

		  map.put("dsl", rbl);
		  DefaultOutput defaultOutput = this.evaluateRuleInternal(ruleDTO,map);
		  
		  System.err.println("@@@@@@ " + defaultOutput);
		  return CompletableFuture.supplyAsync(() ->  { return defaultOutput; } );
		 
		
	}
	
	private DefaultOutput evaluateRuleInternal(RuleDTO rule, Map<String,Object> map) {
		

		  DefaultOutput defaultOutput = inferenceEngine.runSequential(rule, map );

		  System.err.print(" rule  " +rule + " do " +   defaultOutput);
		 return   defaultOutput;

		 
	}
	private List<String> buildAttributeList(List<String> entities,RequestDetails requestDetails )
	{

		List<String> attributeList = new ArrayList<String>();
		class FieldData {
			String extId;
			String fieldPrefix;

			public FieldData(String extId, String fieldPrefix) {
				super();
				this.extId = extId;
				this.fieldPrefix = fieldPrefix;
			}

		}
		Stack<FieldData> stack = new Stack<FieldData>();
		try {
			classDefService.getBy(requestDetails, "isRuleParent", true).toCompletableFuture().get().forEach(data -> {
				stack.add(new FieldData(data.getExtId(), null));
				entities.add(data.getExtId());
			});
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new CompletionException(e1);
		}

		while (!stack.isEmpty()) {
			FieldData fieldData = stack.pop();

			ClassDefDTO classDefDTO;
			try {
				classDefDTO = classDefService.getBy(requestDetails, "extId", fieldData.extId).toCompletableFuture()
						.get().findAny().get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				throw new CompletionException(e);
			}
			for (FieldDefDTO fieldDefDTO : classDefDTO.getFieldDefs()) {
				if (fieldDefDTO.getIsList()) {

					StringBuilder fieldPrfxBldr = new StringBuilder();
					if (StringUtils.isNotBlank(fieldData.fieldPrefix))
						fieldPrfxBldr.append(fieldData.fieldPrefix).append(".");
					fieldPrfxBldr.append(fieldDefDTO.getFieldname());
					String fieldPrfxText = fieldPrfxBldr.toString();
					FieldData childData = new FieldData(fieldDefDTO.getType(), fieldPrfxText);
					stack.add(childData);
				} else {
					StringBuilder fieldKey = StringUtils.isNotBlank(fieldData.fieldPrefix)
							? new StringBuilder(fieldData.fieldPrefix).append(".").append(fieldDefDTO.getFieldname())
							: new StringBuilder(fieldDefDTO.getFieldname());
//		 					attributeMap.put(fieldKey.toString(), StringUtils.isNotBlank(fieldData.fieldType)?fieldData.fieldType:"text");
					attributeList.add(fieldKey.toString());

				}

			}

		}

		return attributeList;

	}
	private Map<String, List<String>> buildAttributeMap(Map<String,Object> map,Map<String, List<String>> attributeMap,List<String> entities )
	{
		class FieldData {
			Object object;
			String prefix;

			public FieldData(Object object, String prefix) {
				super();
				this.object = object;
				this.prefix = prefix;
			}

		}

		Stack<FieldData> stack = new Stack<FieldData>();
		FieldData data = new FieldData(map, null);
		stack.add(data);
		while (!stack.isEmpty()) {
			FieldData fieldData = stack.pop();
			Object object = fieldData.object;
			if (object instanceof Map) {
				Map<String, Object> mapObject = ((Map) object);

				for (String key : mapObject.keySet()) {
					Object objValue = mapObject.get(key);

					if (objValue instanceof String) {
						String value = objValue.toString();
						StringBuilder fieldPrfxBldr = new StringBuilder();
						if (StringUtils.isNotBlank(fieldData.prefix))
							fieldPrfxBldr.append(fieldData.prefix).append(".");
						fieldPrfxBldr.append(key);
						String mapKey = fieldPrfxBldr.toString();
						if (attributeMap.containsKey(mapKey)) {
							if (!attributeMap.get(mapKey).contains(value))
								attributeMap.get(mapKey).add(value);
						} else {
							List<String> valueList = new ArrayList<String>();
							valueList.add(value.toString());
							attributeMap.put(mapKey, valueList);
						}

					} else if (objValue instanceof List || objValue instanceof Map) {
						if (entities.contains(key)) {
							FieldData fieldChild = new FieldData(objValue, null);
							stack.add(fieldChild);
						} else {
							StringBuilder fieldPrfxBldr = new StringBuilder();
							if (StringUtils.isNotBlank(fieldData.prefix))
								fieldPrfxBldr.append(fieldData.prefix).append(".");
							fieldPrfxBldr.append(key);
							String fieldPrfxText = fieldPrfxBldr.toString();
							FieldData fieldChild = new FieldData(objValue, fieldPrfxText);
							stack.add(fieldChild);

						}

					}

				}

			} else if (object instanceof List) {
				List<Object> listObject = (List) object;

				listObject.stream().forEach(obj ->

				{
					if (obj instanceof Map) {
						FieldData fieldChild = new FieldData(obj, fieldData.prefix);
						stack.add(fieldChild);
					} else if (obj instanceof String) {
						String value = obj.toString();
						String mapKey = fieldData.prefix;
						if (attributeMap.containsKey(mapKey)) {
							if (!attributeMap.get(mapKey).contains(value))
								attributeMap.get(mapKey).add(value);
						} else {
							List<String> valueList = new ArrayList<String>();
							valueList.add(obj.toString());
							attributeMap.put(mapKey, valueList);
						}

					}
				});

			}

		}

		return attributeMap;

	}
	

	
	public CompletableFuture<List<DefaultOutput>> applyRules_16(RequestDetails requestDetails, Map<String,Object> map, List<String> policyGroup)  {
		 map.put("dsl", rbl);

		 List<DefaultOutput> allOp = new ArrayList<>();
		 
		 ExecutorService WORKER_THREAD_POOL 
	        = Executors.newFixedThreadPool(40);
		 ExecutorService WORKER_THREAD_POOL_TASK 
	        = Executors.newFixedThreadPool(10);
		 try {
			PolicyGroupDTO pgdto = pgService.getBy(requestDetails, "extId",policyGroup).toCompletableFuture().join().findFirst().get();
			
			List<PolicyDTO> policies = policyService.getBy(requestDetails, "policyGroup", pgdto.getExtId()).toCompletableFuture().get().collect(Collectors.toList());
			
		
			
			 List<Callable<List<DefaultOutput>>> tasksList = new ArrayList<>();
			 
			 long l = System.currentTimeMillis();
			 for(PolicyDTO p : policies) {
				 
				 if(p.getRuleSets().isEmpty() || !p.isActive()) continue;
				 
				 Callable<List<DefaultOutput>> task = new Callable<List<DefaultOutput>>() {
						
						@Override
						public  List<DefaultOutput> call() throws Exception {
							
							List<DefaultOutput> ops = new ArrayList<>();
							for(RuleSetDTO rs : p.getRuleSets()) {
								if(rs.getRules().isEmpty() || !rs.isActive()) continue;
								
								for(RuleDTO rule : rs.getRules()) {
									if(!rule.isActive()) continue;
									DefaultOutput dop = inferenceEngine.runSequential(rule, map );
									if(dop !=null) {
										ops.add(dop);
									}
									
								}
								
							}
							
						return ops;	
						}
					};
					tasksList.add(task);
			 }
			 long sec = (System.currentTimeMillis() - l) / 1000;
			System.err.print("tasls " + sec);
			 List<Future<List<DefaultOutput>>> results = WORKER_THREAD_POOL.invokeAll(tasksList);
			 WORKER_THREAD_POOL.shutdown();
		        long count = 0;
		        for(Future<List<DefaultOutput>> result : results) {
		            List<DefaultOutput> list = result.get();
		            allOp.addAll(list);
		          // count++;
		        }
			
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			
			//WORKER_THREAD_POOL.shutdown();
			
		}
		
		
		
		CompletableFuture<List<DefaultOutput>> listCompletableFuture = CompletableFuture.supplyAsync(() ->  { return allOp; } );

		return listCompletableFuture;

		
	}

	
	@Override
	public CompletableFuture<List<DefaultOutput>> applyRules(RequestDetails requestDetails, Map<String,Object> map, List<String> policyGroup)  {
		 //String ruleNamespace = inferenceEngine.getRuleNamespace().toString();
	        //TODO: Here for each call, we are fetching all rules from db. It should be cache.
		List<String> entities=new ArrayList<String>();
		List<String> attributeList= buildAttributeList(entities,requestDetails);
		 map.put("requestMap", buildAttributeMap(map, new HashMap<String, List<String>>(), entities));
		 map.put("attributeList", attributeList);
		 map.put("dsl", rbl);
		
		 

//		PolicyGroupDTO pgDTO = null;
//		try {
//			CompletionStage<Optional<PolicyGroupDTO>> policyGroupStage = 
//					pgService.getBy(requestDetails, "extId",policyGroup).thenApply((e) ->  {return e.findFirst();});
//			pgDTO  = policyGroupStage.toCompletableFuture().get().get();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//			throw new RuntimeException("Error while retrieving Policy Group " + policyGroup);
//			
//		}
		
//		List<String> pgs  = new ArrayList<String>();		
		
		CompletionStage<Stream<PolicyDTO>> allPolicyForGroups = policyService.getAllPolicyForGroups(requestDetails, policyGroup);
		CompletionStage<Stream<CompletableFuture<List<DefaultOutput>>>> thenApplyAsync = allPolicyForGroups.thenApplyAsync(policies -> {
			return policies.parallel().map(policy -> {
				return this.applyRuleInternal(policy,map);
			});
		});
		CompletionStage<List<DefaultOutput>> thenApplyAsync2 = thenApplyAsync.thenApplyAsync(stream -> {
			List<DefaultOutput> allListDO = new ArrayList<>();
			stream.parallel().forEach(future -> {
				future.thenAcceptAsync(listDO -> {
					allListDO.addAll(listDO);
				});
			});
			return allListDO;
		});
		return thenApplyAsync2.toCompletableFuture();
		//Punet
//		Stream<CompletionStage<List<DefaultOutput>>> allPgStream = policyGroup.stream().unordered()
//		.map(pg -> {
//			
//			CompletionStage<List<DefaultOutput>> pgResult = pgService.getBy(requestDetails, "extId",policyGroup)
//			.thenApply((e) ->  {return e.findFirst();})
//			.thenApply( pgdto -> {
//				
//				CompletionStage<List<PolicyDTO>> policyStreamStage = ((PolicyServiceImpl)policyService).getByCached(requestDetails, "policyGroup", pgdto.get().getExtId());
//				
//				List<DefaultOutput> f_LD = policyStreamStage.thenApplyAsync((s) -> {
//						
//						List<DefaultOutput> collect = s.parallelStream().map(policy ->   {
//							
//							List<DefaultOutput> o = new ArrayList<DefaultOutput>();
//								try {
//									o =  this.applyRuleInternal(policy,map).get();
//								} catch (InterruptedException e1) {
//									e1.printStackTrace();
//								} catch (ExecutionException e1) {
//									e1.printStackTrace();
//								}
//								return o;
//							})
//							//.collect(Collectors.toList())
//							//.stream()
//							.flatMap(List::stream)
//							.collect(Collectors.toList());
//						
//						return collect;
//						
//					})
//					.toCompletableFuture()
//					.join();
//				
//				System.err.print("join");
//				return f_LD;
//			});
//			
//			
//			return pgResult;
//		})
//		.collect(Collectors.toList())
//		.parallelStream();
//		
//		List<DefaultOutput> allOp = new ArrayList<>();
//		
//		allPgStream.forEach(l -> {
//			
//			List<DefaultOutput> join = l.toCompletableFuture().join();
//			allOp.addAll(join);
//		});
//		
//		
//		CompletableFuture<List<DefaultOutput>> listCompletableFuture = CompletableFuture.supplyAsync(() ->  { return allOp; } );
//
//		System.err.print("returned...");
//		return listCompletableFuture;
		//Punet
		
		
		
		//commented for loop at policy_groups
//		CompletionStage<Stream<PolicyDTO>> policyStreamStage = policyService.getBy(requestDetails, "policyGroup", pgDTO.getExtId());
//		
//		CompletionStage<List<DefaultOutput>> thenApplyAsync = policyStreamStage.thenApplyAsync((s) -> {
//			
//			List<DefaultOutput> collect = s.unordered().map(policy ->   {
//				
//				List<DefaultOutput> o = new ArrayList<DefaultOutput>();
//					try {
//						o =  this.applyRuleInternal(policy,map).get();
//					} catch (InterruptedException e1) {
//						e1.printStackTrace();
//					} catch (ExecutionException e1) {
//						e1.printStackTrace();
//					}
//					return o;
//				})
//				.collect(Collectors.toList())
//				.stream()
//				.flatMap(List::stream)
//				.collect(Collectors.toList());
//			
//			return collect;
//			
//		});
//		
//		return thenApplyAsync.toCompletableFuture();
		//END ********* commented for loop at policy_groups
		
//		CompletableFuture<List<DefaultOutput>> listCompletableFuture = CompletableFuture.supplyAsync(() ->  { return thenApplyAsync; } );
//
//		
//		CompletionStage<Optional<PolicyDTO>> policyStage = policyService.getBy(requestDetails, "extId",policyGroup).thenApply((e) ->  {return e.findFirst();});
//		Optional<PolicyDTO> policy = null;
//		
//			try {
//				policy = policyStage.toCompletableFuture().get();
//			} catch (Exception e) {
//				
//				e.printStackTrace();
//				
//			}
//		
//		return this.applyRuleInternal(policy,map);
		
	}

	private CompletableFuture<List<DefaultOutput>> applyRuleInternal (PolicyDTO policyDTO,  Map<String,Object> map ) {
		
//		if(!policy.isPresent()) return null;
//		PolicyDTO policyDTO = policy.get();
		List<DefaultOutput> allOp = new ArrayList<>();
		
		if(policyDTO.getRuleSets() != null && !policyDTO.getRuleSets().isEmpty()) {
			
			Stream<RuleSetDTO> ruleSetStream = policyDTO.getRuleSets().parallelStream();
			
//			Stream<RuleSetDTO> filtered = ruleSetStream.filter(ruleSet -> {
//				if(ruleSet.getRules() != null) {
//					
//					return true;
//				}
//				return false;
//			});
			
			ruleSetStream.filter(ruleSet -> {
					if(ruleSet.getRules() != null && ruleSet.isActive()) {
						return true;
					}
					return false;
				})	
			.parallel()
			 .map(ruleSet -> {
						 
						 System.err.print("Rules count " + ruleSet.getRules().size());
						 List<DefaultOutput> ops = ruleSet.getRules()
								 							.parallelStream()
								 							.map(rule ->   {
								 								
								 								
								 								return inferenceEngine.runSequential(rule, map );
								 							})
								 							.filter(d ->  {return d != null;})
								 							.collect(Collectors.toList());
						 
						 return ops;
					 })
					 .forEach(l -> allOp.addAll(l));
			 
			// map2.forEach(l -> allOp.addAll(l));
		}
		
	
		 
		 CompletableFuture<List<DefaultOutput>> listCompletableFuture = CompletableFuture.supplyAsync(() ->  { return allOp; } );
			
		return listCompletableFuture;
	}

	@Override
	public CompletableFuture<String> evaluateExpr(String expr) {
		

		 CompletableFuture<String> listCompletableFuture = CompletableFuture.supplyAsync(() ->  { 
			 
			 ParserContext context = new ParserContext();
				Serializable compiledExpression = MVEL.compileExpression(expr, context);
			 return "valid"; 
			 } );
//			.exceptionally(e ->  {
//				return e.getMessage(); 
//				})	 ;
		 
		 return listCompletableFuture;

	}



	

}
