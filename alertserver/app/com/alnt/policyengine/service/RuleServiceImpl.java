package com.alnt.policyengine.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;

import com.alnt.platform.base.exception.BaseBusinessException;
import com.alnt.platform.base.exception.type.ErrorType;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.response.ApiMessage;
import com.alnt.platform.base.response.ApiMessageType;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.binaryresource.service.BinaryResourceService;
import com.alnt.platform.core.classdef.domain.dto.ClassDefDTO;
import com.alnt.platform.core.classdef.domain.dto.FieldDefDTO;
import com.alnt.platform.core.classdef.service.ClassDefService;
import com.alnt.platform.core.lists.domain.dto.ListEntriesDTO;
import com.alnt.platform.core.lists.service.ListsService;
import com.alnt.policyengine.domain.Rule;
import com.alnt.policyengine.domain.dto.RuleConditionDTO;
import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.policyengine.domain.dto.RuleExpressionDTO;
import com.alnt.policyengine.domain.dto.UploadRuleDTO;
import com.alnt.ruleengine.mapper.RuleMapper;
import com.alnt.ruleengine.repository.RulesRepository;
import com.monitorjbl.xlsx.StreamingReader;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class RuleServiceImpl extends BaseServiceImpl<Rule, RuleDTO> implements RuleService {
    
	private ClassDefService classDefService;

	private BinaryResourceService binaryResourceService;
	
	private ListsService listsService;
	
	@Inject
	public RuleServiceImpl(HttpExecutionContext ec, RulesRepository repository,BinaryResourceService binaryResourceService,ListsService listsService,
			 ClassDefService classDefService) {
		super( ec, repository, RuleMapper.INSTANCE);
		this.binaryResourceService=binaryResourceService;
		this.listsService=listsService;
		this.classDefService=classDefService;
	}

	@Override
	public CompletionStage<ApiResponse> uploadExcel(RequestDetails requestDetails,UploadRuleDTO uploadRuleDTO) {
		return binaryResourceService.get(requestDetails, uploadRuleDTO.getFileId()).thenComposeAsync(resource->{
			return listsService.getBy(requestDetails, "listCode", "RULEOPERATOR").thenComposeAsync(list->{
				 Map<String,String> ruleOptMap=
				 list.map(hh->hh.getListEntries()).findAny().get().stream().collect(Collectors.toMap(ListEntriesDTO::getEntryName, ListEntriesDTO::getEntryCode));
				return listsService.getBy(requestDetails, "listCode", "RULELOGICALOPERATOR").thenComposeAsync(list1->{
					List<String> entities=new ArrayList<String>();
				Map<String,String>	attributeMap=buildAttributeMap(entities,requestDetails);
						//String entityPrefix=uploadRuleDTO.getEntityId();
					Map<String,String> logicalOptMap=
							 list1.map(hh->hh.getListEntries()).findAny().get().stream().collect(Collectors.toMap(ListEntriesDTO::getEntryName, ListEntriesDTO::getEntryCode));
					 File file=new File(resource.get().getPath());
					 Map<String, String> errors = new HashMap<String, String>();
					 int totalCnt=0;
					 Map<String,RuleExpressionDTO> expressionMap = null;
					 try (FileInputStream fileInputStream = new FileInputStream(file);) {
							StreamingReader reader = StreamingReader.builder()
							        .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
							        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
							        .sheetIndex(0)        // index of sheet to use (defaults to 0)
							        .read(fileInputStream);   
							expressionMap=new LinkedHashMap<String,RuleExpressionDTO>() ;
							int ruleNameIdx=0,ruleIdxIdx=0,conditionIdx=0,attrIdx=0,operatorIdx=0,valueIdx=0,rangeIdx=0,enforcementIdx=0,entityIdx=0;
							final String RULENAME="RuleName",RULEINDEX="RuleIndex",CONDITION="Condition",ATTRIBUTE="Attribute",OPERATOR="Operator",VALUE="Value",
									RANGE="Range",ENFORCEMENT="Enforcement",ENTITY="Entity";
							for (Row row : reader) {
								if(row.getRowNum()==0) 
								{
									for(int i=1;i<10;i++)
									{
										Cell cell=row.getCell(i);
										if(cell!=null)
										{
											String cellvalue=cell.getStringCellValue();
											int cellIndex=cell.getColumnIndex();
											switch (cellvalue) {
											
                                            case RULENAME:
                                            	ruleNameIdx=cellIndex;
												break;
                                            case RULEINDEX:
                                            	ruleIdxIdx=cellIndex;
												break;
                                            case CONDITION:
                                            	conditionIdx=cellIndex;
												break;
                                            case ATTRIBUTE:
                                            	attrIdx=cellIndex;
												break;
                                            case OPERATOR:
                                            	operatorIdx=cellIndex;
												break;
                                            case VALUE:
												valueIdx=cellIndex;
												break;	
                                            case RANGE:
                                            	rangeIdx=cellIndex;
												break;
                                            case ENFORCEMENT:
                                            	enforcementIdx=cellIndex;
												break;
                                            case ENTITY:
                                            	entityIdx=cellIndex;
												break;
											default:
												break;
											}
										}
									}
								continue;	
								}
								List<RuleConditionDTO> ruleConditionDTOs=null;
								RuleConditionDTO ruleConditionDTO=null;
								
								Cell ruleNameCell=row.getCell(ruleNameIdx); 
								Cell ruleIdxCell=row.getCell(ruleIdxIdx);
								Cell conditionCell=row.getCell(conditionIdx);
								Cell attrCell=row.getCell(attrIdx);
								Cell operatorCell=row.getCell(operatorIdx);
								Cell valueCell=row.getCell(valueIdx);
								Cell rangeCell=row.getCell(rangeIdx);
								Cell enforcementCell=row.getCell(enforcementIdx);
								Cell entityCell=row.getCell(entityIdx);
								String ruleName=ruleNameCell!=null ?ruleNameCell.getStringCellValue():StringUtils.EMPTY;
								String ruleIdx=ruleIdxCell!=null ?ruleIdxCell.getStringCellValue():null;
								String condition=conditionCell!=null ?conditionCell.getStringCellValue():null;
								String attribute=attrCell!=null ?attrCell.getStringCellValue():null;
								String operator=operatorCell!=null ?operatorCell.getStringCellValue():null;
								String value=valueCell!=null ?valueCell.getStringCellValue():null;
								Integer range=rangeCell!=null && Cell.CELL_TYPE_NUMERIC==rangeCell.getCellType()?(int)rangeCell.getNumericCellValue(): null;
								String enforcement=enforcementCell!=null ?enforcementCell.getStringCellValue():null;
								String entityPrefix=entityCell!=null?entityCell.getStringCellValue():null;
								if(StringUtils.isBlank(ruleName) && StringUtils.isBlank(ruleIdx) && StringUtils.isBlank(value)  && StringUtils.isBlank(condition) && StringUtils.isBlank(operator) && StringUtils.isBlank(attribute) && StringUtils.isBlank(entityPrefix) )
									continue;
								StringBuilder errorMsg=new StringBuilder();
								if(StringUtils.isBlank(ruleName) )
									errorMsg.append(RULENAME).append(" is blank");
								if( StringUtils.isBlank(ruleIdx) || !NumberUtils.isParsable(ruleIdx))
								{
									if(errorMsg.length()>0) errorMsg.append("|");
									 errorMsg.append(RULEINDEX).append(" is blank or not a number");
								}
								if(StringUtils.isBlank(value))
								{
									if(errorMsg.length()>0) errorMsg.append("|");
									 errorMsg.append(VALUE).append(" is blank");
								}
								if((expressionMap.containsKey(ruleName) && StringUtils.isBlank(condition)) || ( StringUtils.isNotBlank(condition) && !logicalOptMap.containsKey(condition)) )
								{
									if(errorMsg.length()>0) errorMsg.append("|");
									 errorMsg.append(CONDITION).append(" is blank or invalid");
								}
								if( StringUtils.isBlank(operator) || ( StringUtils.isNotBlank(operator) && !ruleOptMap.containsKey(operator) ) )
								{
									if(errorMsg.length()>0) errorMsg.append("|");
									 errorMsg.append(OPERATOR).append(" is blank or invalid");
								}
								if(StringUtils.isBlank(attribute) || ( StringUtils.isNotBlank(attribute) && !attributeMap.containsKey(attribute) ))
								{
									if(errorMsg.length()>0) errorMsg.append("|");
									 errorMsg.append(ATTRIBUTE).append(" is blank or invalid");
								}
								if(StringUtils.isBlank(entityPrefix) ||( StringUtils.isNotBlank(entityPrefix) && !entities.contains(entityPrefix) ))
								{
									if(errorMsg.length()>0) errorMsg.append("|");
									 errorMsg.append(ENTITY).append(" is blank or invalid");
								}
								if(errorMsg.length()>0)
								    {
									if(errors.containsKey(ruleName))
									{
										String errorValue=String.join(",",errors.get(ruleName), new StringBuilder(String.valueOf(row.getRowNum())).append("=").append(errorMsg).toString());
										errors.put(ruleName,errorValue);
									}
									else {
										System.out.println(row.getRowNum());
										errors.put(ruleName, new StringBuilder(String.valueOf(row.getRowNum())).append("=").append(errorMsg).toString()); 
									totalCnt++;
									}
									continue;
								    }
								
								if(errors.containsKey(ruleName)) continue;
						        boolean isFirst=false;
									if(expressionMap.containsKey(ruleName)) 
										{
										ruleConditionDTOs=expressionMap.get(ruleName).getRuleConditionDTOs();
										}
									else 
										{
										isFirst=true;
										totalCnt++;
										RuleExpressionDTO ruleExpressionDTO=new RuleExpressionDTO();
										ruleExpressionDTO.setText(ruleName);
										ruleExpressionDTO.setDescription(ruleName);
										ruleExpressionDTO.setEnforcement(enforcement);
										ruleExpressionDTO.setEntityPrefix(entityPrefix);
										ruleConditionDTOs=new ArrayList<RuleConditionDTO>();
										ruleExpressionDTO.setRuleConditionDTOs(ruleConditionDTOs);
										expressionMap.put(ruleName, ruleExpressionDTO);
										}
								
									ruleConditionDTO=getOjectBySequenceNo(ruleConditionDTOs, ruleIdx);
									ruleConditionDTO.setAttribute(attribute);
									if(!isFirst)
									ruleConditionDTO.setLogicalOperator(logicalOptMap.get(condition));
									ruleConditionDTO.setOperator(ruleOptMap.get(operator));
									ruleConditionDTO.setValue(Arrays.asList(value.split("\\s*,\\s*")));
									ruleConditionDTO.setRange(range);
							
							
							}
						}catch (Exception e) {
							throw new CompletionException(e);
						}
					 
					 List<ApiMessage> successMessages = new ArrayList<ApiMessage>();
					 ApiMessage message = new ApiMessage();
					 if(errors.isEmpty()) {
						 message.setMessageText("Rules uploaded successfully");
						 message.setMessageDisplayText("Rules uploaded successfully");
						 message.setMessageType(ApiMessageType.INFO);
					 }else {
						 if(totalCnt==errors.size())
						 {
						 message.setMessageText("Rules are failed");
						 message.setMessageDisplayText("Rules are failed");
						 message.setMessageType(ApiMessageType.ERROR);
						 }else
						 {
						 message.setMessageText("Out of "+totalCnt+" ,"+errors.size()+" Rule(s)  failed");
						 message.setMessageDisplayText("Out of "+totalCnt+" ,"+errors.size()+" Rule(s)  failed");
						 message.setMessageType(ApiMessageType.WARNING);
						 }
					 }
					 
					 successMessages.add(message);
						
					 return ruleExcelSave(requestDetails, attributeMap, expressionMap,errors).thenApplyAsync(ss->{
							return new ApiResponse(Boolean.TRUE, errors, successMessages);
						}, ec.current());
						
				
				},ec.current());
			 },ec.current());
			
		
				
		}, ec.current());
				
	}
	
	
	private Map<String, String> buildAttributeMap(List<String> entities ,RequestDetails requestDetails )
	{
		
		  Map<String, String> attributeMap=new HashMap<String, String>();
			class FieldData
			{
				String  extId;
				String  fieldType;
				String fieldPrefix;
				public FieldData(String extId, String fieldType, String fieldPrefix) {
					super();
					this.extId = extId;
					this.fieldType = fieldType;
					this.fieldPrefix = fieldPrefix;
				}
				
			}
			Stack<FieldData> stack=new Stack<FieldData>();
			 try {
				classDefService.getBy(requestDetails, "isRuleParent", true).toCompletableFuture().get().forEach(data->{
					stack.add(new FieldData(data.getExtId(), null,null));
					entities.add(data.getExtId());
				});
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new CompletionException(e1);
			}
			
		     while(!stack.isEmpty())
		     {
		    	 FieldData fieldData=stack.pop();
		    	// TODO: Sequential database call , needs to find alternative
		    	
		    		 ClassDefDTO classDefDTO;
					try {
						classDefDTO = classDefService.getBy(requestDetails, "extId", fieldData.extId).toCompletableFuture().get().findAny().get();
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new CompletionException(e);
					}
		    		 for(FieldDefDTO fieldDefDTO:classDefDTO.getFieldDefs())
		 			{
		 				if(fieldDefDTO.getIsList())
		 				{
		 					
		 					StringBuilder fieldPrfxBldr=new StringBuilder();
		 					if(StringUtils.isNotBlank(fieldData.fieldPrefix))fieldPrfxBldr.append(fieldData.fieldPrefix).append(".");
		 					fieldPrfxBldr.append(fieldDefDTO.getFieldname());
		 					String fieldPrfxText=fieldPrfxBldr.toString();
		 					FieldData childData=new FieldData(fieldDefDTO.getType(), "List", fieldPrfxText);
		 					stack.add(childData);
		 				}else
		 				{
		 					StringBuilder fieldKey=StringUtils.isNotBlank(fieldData.fieldPrefix)? new StringBuilder(fieldData.fieldPrefix).append(".").append(fieldDefDTO.getFieldname()):new StringBuilder(fieldDefDTO.getFieldname());
		 					attributeMap.put(fieldKey.toString(), StringUtils.isNotBlank(fieldData.fieldType)?fieldData.fieldType:"text");
		 					
		 				}
		 				
		 			}
		
		     }
		     
		  return attributeMap;
		  
		
	}	
	
	private CompletionStage<List<Optional<RuleDTO>>> ruleExcelSave(RequestDetails requestDetails,Map<String,String> attribuMap,Map<String,RuleExpressionDTO> expressionMap,Map<String, String> errors)
	{
		List<RuleDTO> ruleDTOs=new ArrayList<RuleDTO>();
		for(String ruleName:expressionMap.keySet())
		{
			RuleExpressionDTO ruleExpressionDTO= expressionMap.get(ruleName);
			String conditionJson=	Json.toJson(ruleExpressionDTO.getRuleConditionDTOs()).toString();
			
			try
			{
			String condition=prepareJsonToExpression(attribuMap, ruleExpressionDTO.getRuleConditionDTOs(), new StringBuilder(),ruleExpressionDTO.getEntityPrefix()).toString();
			ParserContext context = new ParserContext();
			Serializable compiledExpression = MVEL.compileExpression(condition, context);
			if(!errors.containsKey(ruleName))
			{
			RuleDTO ruleDTO=new RuleDTO();
			ruleDTO.setText(ruleExpressionDTO.getText());
			ruleDTO.setDescription(ruleExpressionDTO.getDescription());
			ruleDTO.setEnforcement(ruleExpressionDTO.getEnforcement());
			ruleDTO.setCondition(condition);
			ruleDTO.setConditionJson(conditionJson);
			ruleDTO.setEntityId(ruleExpressionDTO.getEntityPrefix());
			ruleDTOs.add(ruleDTO);
			}
			}catch (Exception e) {
				if(errors.containsKey(ruleName))
					errors.put(ruleName,String.join("|",errors.get(ruleName), "MVEL expression is invalid"));
				else errors.put(ruleName, "MVEL expression is invalid");
			}
		}
		
		return saveAll(requestDetails, ruleDTOs);
		
	}
	
	private RuleConditionDTO getOjectBySequenceNo(List<RuleConditionDTO> ruleExpressionDTOs, String srNo) 
	{
		srNo=srNo.replace(".0", ".");
		String[] indexList=srNo.split("\\.");
		if(indexList.length>1)
		{
			indexList=Stream.of(new String[] {indexList[0]}, indexList[1].split(""))
					.flatMap(Stream::of)		
					.toArray(String[]::new);
		}
		
		int[] indexArr=Arrays.stream(indexList).mapToInt(Integer::parseInt).toArray();
		
		
		int length=indexArr.length;
		int index=Integer.parseInt(indexList[0]);
		RuleConditionDTO ruleExpressionDTO=null;
		if(index-ruleExpressionDTOs.size()>1) throw new BaseBusinessException(ErrorType.EXCEL_INVALID_INDEX);
		if(ruleExpressionDTOs.isEmpty() || index > ruleExpressionDTOs.size())
			{
			ruleExpressionDTO=new RuleConditionDTO();
			ruleExpressionDTOs.add(ruleExpressionDTO);
			ruleExpressionDTO.setLeaf(true);
			return ruleExpressionDTO;
			}else ruleExpressionDTO=ruleExpressionDTOs.get(index-1);
		
		for(int i=1;i<length;i++)
		{    index=indexArr[i];
		
		List<RuleConditionDTO> childrens= ruleExpressionDTO.getChildren();
		if(childrens==null)
			{
			childrens=new ArrayList<RuleConditionDTO>();
			ruleExpressionDTO.setLeaf(null);
			ruleExpressionDTO.setChildren(childrens);
			}
		if( index-childrens.size()>1) throw new BaseBusinessException(ErrorType.EXCEL_INVALID_INDEX);
		if( childrens.isEmpty() || index > childrens.size())
			{
			ruleExpressionDTO=new RuleConditionDTO();
			ruleExpressionDTO.setLeaf(true);
			childrens.add(ruleExpressionDTO);
			return ruleExpressionDTO;
			}else 
				{
				ruleExpressionDTO.setLeaf(null);
				ruleExpressionDTO.setPrependLogicalOperator(true);
				ruleExpressionDTO=childrens.get(index-1);
				
				}
		
		}
		
		return ruleExpressionDTO;
	}
	
	  private StringBuilder  prepareJsonToExpression (Map<String,String> attribuMap,List<RuleConditionDTO> tree,  StringBuilder expBuilder,String entityPrefix) {
      
		  tree.forEach(condition->{
			if(condition.getChildren()!=null && !condition.getChildren().isEmpty())  
			{
				 condition.setPrependLogicalOperator(true);
	                 addTextToQueryExpression(expBuilder, condition.getLogicalOperator());
	                addTextToQueryExpression(expBuilder, "(");
			}
			 if (attribuMap.containsKey(condition.getAttribute()) && attribuMap.get(condition.getAttribute()).equals("List")) {
				 
				String[] conditionArr= condition.getAttribute().split("\\.");
				String collectionName=conditionArr[0];
				String attributeName=conditionArr[1];
				 addTextToQueryExpression(expBuilder, (condition.getPrependLogicalOperator()!=null && condition.getPrependLogicalOperator() ? "" : condition.getLogicalOperator()));
				 if (Arrays.asList("ANYTWO","ANYTHREE","ANYM").contains( condition.getOperator())) {
					StringBuilder valueBuilder=new StringBuilder("[");
					condition.getValue().stream().forEach(value->{
						if(valueBuilder.length()>1)valueBuilder.append(",");
						valueBuilder.append('"').append(value).append('"');
					});
					valueBuilder.append("]");
					
					 if ("ANYM".equals(condition.getOperator())) {
	                     valueBuilder.append(",").append(condition.getRange());
	                 }
					 
					 addTextToQueryExpression(expBuilder, new StringBuilder("dsl.").append(condition.getOperator()).append("(").toString());
	                 addTextToQueryExpression(expBuilder, new StringBuilder(entityPrefix).append(".").append(collectionName).append(",").toString());
	                 addTextToQueryExpression(expBuilder, new StringBuilder('"').append(attributeName).append(",").append(valueBuilder).toString());
	                 addTextToQueryExpression(expBuilder, ")");
						
				 }
				 else {
	                 addTextToQueryExpression(expBuilder, new StringBuilder("((").append(attributeName).append(" in ").append(entityPrefix).append(".").append(collectionName).append(")").toString());
	                 addTextToQueryExpression(expBuilder, (condition.getOperator()));
	                 addTextToQueryExpression(expBuilder, (new StringBuilder("'").append(condition.getValue().get(0)).append("'").toString()));
	                 addTextToQueryExpression(expBuilder, (")"));
	             }
			 }else {
	             addTextToQueryExpression(expBuilder, (condition.getPrependLogicalOperator()!=null && condition.getPrependLogicalOperator() ? "" : condition.getLogicalOperator()));
	             addTextToQueryExpression(expBuilder, (new StringBuilder(entityPrefix).append(".").append(condition.getAttribute()).toString()));
	             addTextToQueryExpression(expBuilder, (condition.getOperator()));
	             addTextToQueryExpression(expBuilder, (new StringBuilder("'").append(condition.getValue().get(0)).append("'").toString()));
	         }
	         if (condition.getChildren()!=null && !condition.getChildren().isEmpty()) {
	             prepareJsonToExpression(attribuMap,condition.getChildren(), expBuilder,entityPrefix);
	             addTextToQueryExpression(expBuilder, ")");
	         }
			
		  });
		  
        return expBuilder;
    }

   private StringBuilder addTextToQueryExpression(StringBuilder expBuilder, String text) {
        if (StringUtils.isBlank(text)) return expBuilder;
        return expBuilder.append(" ").append(text);
    }
   
}
