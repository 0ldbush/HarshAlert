package com.alnt.platform.core.docnumberrange.service;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import com.alnt.platform.base.domain.BaseEntity.INT_STATUS;
import com.alnt.platform.base.domain.BaseMasterEntity;
import com.alnt.platform.base.domain.BaseSettingEntity;
import com.alnt.platform.base.exception.BaseBusinessException;
import com.alnt.platform.base.exception.type.ErrorType;
import com.alnt.platform.base.request.Criteria;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.base.util.BusObjTypeMapping;
import com.alnt.platform.base.util.DateUtil;
import com.alnt.platform.core.docnumberrange.domain.DocNumber;
import com.alnt.platform.core.docnumberrange.domain.DocNumberRange;
import com.alnt.platform.core.docnumberrange.domain.dto.DocNumberRangeDTO;
import com.alnt.platform.core.docnumberrange.domain.dto.DocNumberRequestDTO;
import com.alnt.platform.core.docnumberrange.mapper.DocNumberRangeMapper;
import com.alnt.platform.core.docnumberrange.repository.DocNumberRangeRepository;
import com.alnt.platform.core.docnumberrange.repository.DocNumberRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class DocNumberRangeServiceImpl extends BaseServiceImpl<DocNumberRange, DocNumberRangeDTO>
		implements DocNumberRangeService {
	
	@Inject
	protected DocNumberRepository docNumberRepository;

	@Inject
	public DocNumberRangeServiceImpl(HttpExecutionContext ec, DocNumberRangeRepository repository) {
		super(ec, repository, DocNumberRangeMapper.INSTANCE);
	}
	
	public CompletionStage<Optional<Object>> getDocNumber(RequestDetails requestDetails, Object entity) {
		return supplyAsync(() -> docNumber(requestDetails, entity), ec.current());
	}
	
	
	public CompletionStage<Optional<String>> getDocNumber(RequestDetails requestDetails, DocNumberRequestDTO docNumberRequestDTO) {
		return supplyAsync(() -> docNumber(requestDetails, docNumberRequestDTO), ec.current());
	}
	
	private Optional<Object> docNumber(RequestDetails requestDetails, Object entity) {
		try {
			DocNumberRequestDTO docNumberRequestDTO = new DocNumberRequestDTO();
			String busObjTypeId = null;
			if(entity instanceof BaseMasterEntity) {
				busObjTypeId = ((BaseMasterEntity) entity).getType();
			} 
			docNumberRequestDTO.setBusObjCat(getBusObjCat(entity));
			docNumberRequestDTO.setBusObjCatClazz(getBusObjCatClazz(entity));
			docNumberRequestDTO.setBusObjTypeId(busObjTypeId);
			if(entity instanceof BaseMasterEntity || entity instanceof BaseSettingEntity) {
				Optional<String> docNumber = docNumber(requestDetails, docNumberRequestDTO);
				if(docNumber!= null && docNumber.isPresent()) {
					if(entity instanceof BaseMasterEntity) {
						((BaseMasterEntity) entity).setExtId(docNumber.get());
					}else {
						((BaseSettingEntity) entity).setExtId(docNumber.get());
					}
				}
			}
		}catch(Exception exception) {
			if(exception instanceof BaseBusinessException) {
				ErrorType error = ((BaseBusinessException)exception).getErrorType();
				return Optional.of(error);
			}
		}
		return Optional.of(entity);
	}
	
	private String getBusObjCat(Object dto) {
		String classSimpleName  = dto.getClass().getSimpleName();
		return classSimpleName;
		//return className.substring(0, className.indexOf("DTO"));
	}
	
	private String getBusObjCatClazz(Object dto) {
		/*String simpleClassName = getBusObjCat(dto);
		String packageName  = dto.getClass().getPackage().getName();
		return packageName.substring(0, packageName.indexOf(".dto"))+"."+simpleClassName;*/
		String className  = dto.getClass().getName();
		return className;
	}
	

	private Optional<String> docNumber(RequestDetails requestDetails, DocNumberRequestDTO docNumberRequestDTO) {
		String finalResult = "";
		String extId = docNumberRequestDTO.getDocNumberRangeId();
		int fiscalYear = docNumberRequestDTO.getFiscalYear();
		String userDocNum = docNumberRequestDTO.getUserDocNum();

		/*if (StringUtils.isBlank(docNumberRequestDTO.getDocNumberRangeId())) {
			BaseSettingEntity settings = loadType(requestDetails, docNumberRequestDTO);
			if (settings != null) {
				extId = settings.getDocNumberRange();
			}
		}*/

		//int latestDocNumber = 0;
		//DocNumberRange doc = loadDocNumberRange(requestDetails, docNumberRequestDTO.getBusObjCat(), extId);
		
		DocNumberRange doc = loadDocNumberRangeForType(requestDetails, docNumberRequestDTO.getBusObjCat(), docNumberRequestDTO.getBusObjTypeId());
		
		if(doc == null) {
			return Optional.empty();
		}else {
			extId = doc.getExtId();
		}

		
		//TODO : If doc is null

		if (doc.getFiscalYearRange() == true && fiscalYear == 0) {
			throw new RuntimeException(
					"The number range is Fiscal year dependent but Fiscal year is not available.");
		} else if (doc.getFiscalYearRange() == false && fiscalYear > 0) {
			fiscalYear = 0;
		}

		if (StringUtils.isNotBlank(extId)) {
			if (doc.getUserInputOfExtId().equalsIgnoreCase("Always")) {
				throw new RuntimeException("User did not enter docNumber and having always userInput");
			} else {
				finalResult = getNumber(requestDetails, doc, fiscalYear, extId);
				if (finalResult != null && finalResult.length() > 0 && doc.getRemoveLeadingZeros() != null
						&& doc.getRemoveLeadingZeros() == true) {
					while (finalResult.length() > 0 && finalResult.charAt(0) == '0') {
						finalResult = finalResult.substring(1, finalResult.length());
					}
				}
			}
		} else {
			if (doc.getUserInputOfExtId().equalsIgnoreCase("Never"))
				throw new RuntimeException("User entered docNumber and having Never userInput");
			else
				finalResult = checkNumber(requestDetails, doc, userDocNum);

		}
		return Optional.of(finalResult);
	}

	public String getNumber(RequestDetails requestDetails, DocNumberRange doc, int fiscalYear, String extId) {

		// System.out.println("Synchronised Gen :" + Thread.currentThread().getName());
		String currentDocNum = "Error!";
		try {

			// TODO:- if fiscalYear is null or 0, we need to calculate it as currentYear
			// like 2020

			if (fiscalYear <= 0)
				fiscalYear = DateUtil.getCurrentYear();
			
			DocNumber currentDocNumber = loadCurrentDocNumber(requestDetails, doc, fiscalYear, extId);
			//int tempDocNumber = 0;
			if (currentDocNumber != null) {
				currentDocNum = currentDocNumber.getDocNumber();
				currentDocNum = getNumberNext(doc.getInternalRangeStart(), doc.getInternalRangeEnd(),
						doc.getInternalRangeFormat(), currentDocNumber.getDocNumber());
				currentDocNumber.setDocNumber(String.valueOf(currentDocNum));
				/// TODO Save
				// updUtils.addRowToCollection("CurrentDoc", currentDoc, objStore);
				docNumberRepository.save(requestDetails, currentDocNumber);

			} else {
				currentDocNum = doc.getInternalRangeStart();
				if (isInteger(currentDocNum)) {
					int digits = 0;
					if (doc.getInternalRangeFormat() != null) {
						digits = doc.getInternalRangeFormat().length();
						currentDocNum = String.format("%0" + Integer.toString(digits) + "d",
								Integer.parseInt(currentDocNum));
					}
				}
				//fiscalYear = 0;
				createNewCurrentDoc(requestDetails, fiscalYear, doc.getExtId(), currentDocNum);
			}
			if (currentDocNum == "Error!")
				currentDocNum = doc.getInternalRangeStart();

		} catch (Exception e) {
			// logger.error("Error came in numberRangeGeneration:" + e.getMessage(), e);
			// TODO
		}
		return currentDocNum;
	}

	private void createNewCurrentDoc(RequestDetails requestDetails, int fiscalYear, String numRangeId, String docNumber) {
		DocNumber cd = new DocNumber();
		cd.setDocNumber(docNumber);
		if (fiscalYear > 0)
			cd.setFiscalYear(fiscalYear);

		cd.setNumRangeId(numRangeId);
		cd.setNumRangeResetDate(new Date());
		docNumberRepository.save(requestDetails, cd);
		// updUtils.addRowToCollection("CurrentDoc", cd, objStore);
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	private BaseSettingEntity loadType(RequestDetails requestDetails, DocNumberRequestDTO docNumberRequestDTO) {
		String busObjTypeClazz = null;
		Class typeClazz = null;
		String busObjCatClazz = docNumberRequestDTO.getBusObjCatClazz();
		boolean loadTypeFromMapping = false;
		if(StringUtils.isNotBlank(busObjCatClazz)){
			busObjTypeClazz = busObjCatClazz+"Type";
			try {
				typeClazz = Class.forName(busObjTypeClazz);
			} catch (ClassNotFoundException e) {
				loadTypeFromMapping = true;
			}
		}else {
			loadTypeFromMapping = true;
		}
		
		if(loadTypeFromMapping) {
			BusObjTypeMapping mapping = BusObjTypeMapping.getInstance(docNumberRequestDTO.getBusObjCat());
			busObjTypeClazz = mapping.getTypeClazz();
		}
		
		if(StringUtils.isNotBlank(busObjTypeClazz) && typeClazz == null){
			try {
				typeClazz = Class.forName(busObjTypeClazz);
			} catch (ClassNotFoundException e) {
				return null;
			}
		}
				
		try {
			List objList = repository.getByGeneric(requestDetails, typeClazz, "extId", docNumberRequestDTO.getBusObjTypeId()).toCompletableFuture().get();
			if(objList != null && objList.size() > 0) {
				return (BaseSettingEntity)objList.get(0);
			}
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unused")
	private DocNumberRange loadDocNumberRange(RequestDetails requestDetails, String busObjCat, String extId) {
		List<DocNumberRange> objList = null;
		try {
			if(StringUtils.isNotBlank(extId))
				objList = repository.getBy(requestDetails, "extId", extId).toCompletableFuture().get();
			else
				objList = loadDefaultDocNumberRange(requestDetails, busObjCat);
			
			if(objList != null && objList.size() > 0) {
				return objList.get(0);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	private DocNumberRange loadDocNumberRangeForType(RequestDetails requestDetails, String busObjCat, String busObjTypeId) {
		List<DocNumberRange> objList = null;			
		objList = loadDocNumberRangeForBusObjCat(requestDetails, busObjCat);
		boolean selectDefault = false;
		DocNumberRange defaultRange = null;
		if(objList != null && objList.size() > 0) {
			List<DocNumberRange> defaultList = objList.stream()
					.filter(docRange -> docRange.getBusObjTypes()==null || docRange.getBusObjTypes().isEmpty())
					.collect(Collectors.toList());
			if(defaultList != null && defaultList.size() > 0) {
				defaultRange = defaultList.get(0);
			}
			
			if(StringUtils.isNotBlank(busObjTypeId)) {
				List<DocNumberRange> filList = objList.stream()
						.filter(docRange -> docRange.getBusObjTypes()!=null && !docRange.getBusObjTypes().isEmpty() && docRange.getBusObjTypes().contains(busObjTypeId))
						.collect(Collectors.toList());
				
				if(filList != null && filList.size() > 0) {
					return filList.get(0);
				} else {
					selectDefault = true;
				}
			}else {
				selectDefault = true;
			}
		} else {
			//return null;
			throw new BaseBusinessException(ErrorType.DOC_NUMBER_RANGE_NOT_FOUND);
		}
		if(selectDefault) {
			if(defaultRange != null) {
				return defaultRange;
			}else {
				//return null;
				throw new BaseBusinessException(ErrorType.DOC_NUMBER_RANGE_NOT_FOUND);
			}
		}else {
			//return null;
			throw new BaseBusinessException(ErrorType.DOC_NUMBER_RANGE_NOT_FOUND);
		}
	}

	private DocNumber loadCurrentDocNumber(RequestDetails requestDetails, DocNumberRange doc, int fiscalYear, String extId) {
		// TODO
		//return null;
		SearchCriteria searchParams = new SearchCriteria();
		List<Criteria> filterCriteria= new ArrayList<Criteria>();
		Criteria criteria1 = new Criteria();
		criteria1.setFieldName("numRangeId");
		criteria1.setOperator("=");
		criteria1.setValue(extId);
		Criteria criteria2 = new Criteria();
		criteria2.setFieldName("fiscalYear");
		criteria2.setOperator("=");
		criteria2.setValue(fiscalYear+"");
		
		filterCriteria.add(criteria1);
		filterCriteria.add(criteria2);
		
		searchParams.setFilterCriteria(filterCriteria);
		DocNumber docNumber  = null;
		try {
			Page<DocNumber> docNumberPage =  docNumberRepository.findAll(requestDetails, searchParams).toCompletableFuture().get();
			List<DocNumber> docNumberList = docNumberPage.toList();
			if(docNumberList != null && docNumberList.size() > 0) {
				docNumber = docNumberList.get(0);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return docNumber;
	}
	
	private List<DocNumberRange> loadDocNumberRangeForBusObjCat(RequestDetails requestDetails, String busObjCat) {
		// TODO
		//return null;
		SearchCriteria searchParams = new SearchCriteria();
		List<Criteria> filterCriteria= new ArrayList<Criteria>();
		Criteria criteria1 = new Criteria();
		criteria1.setFieldName("busObjCat");
		criteria1.setOperator("=");
		criteria1.setValue(busObjCat);
		
		filterCriteria.add(criteria1);
				
		searchParams.setFilterCriteria(filterCriteria);
		//searchParams.setIntStatus(intStatusList);
		List<DocNumberRange> docNumberRangeList = null;
		try {
			Page<DocNumberRange> docNumberRangePage =  repository.findAll(requestDetails, searchParams).toCompletableFuture().get();
			docNumberRangeList = docNumberRangePage.toList();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return docNumberRangeList;
	}
	
	private List<DocNumberRange> loadDefaultDocNumberRange(RequestDetails requestDetails, String busObjCat) {
		// TODO
		//return null;
		SearchCriteria searchParams = new SearchCriteria();
		List<Criteria> filterCriteria= new ArrayList<Criteria>();
		Criteria criteria1 = new Criteria();
		criteria1.setFieldName("busObjCat");
		criteria1.setOperator("=");
		criteria1.setValue(busObjCat);
		
		filterCriteria.add(criteria1);
		
		List<Integer> intStatusList= new ArrayList<Integer>();
		intStatusList.add(INT_STATUS.TEMPLATE.getValue());
		
		searchParams.setFilterCriteria(filterCriteria);
		searchParams.setIntStatus(intStatusList);
		List<DocNumberRange> docNumberRangeList = null;
		try {
			Page<DocNumberRange> docNumberRangePage =  repository.findAll(requestDetails, searchParams).toCompletableFuture().get();
			docNumberRangeList = docNumberRangePage.toList();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return docNumberRangeList;
	}

	public String getNum(String rangeStart, String rangeEnd, String format, String currnentDocNum) {

		try {
			long num = Long.parseLong((currnentDocNum));
			if (currnentDocNum.equalsIgnoreCase(rangeEnd))
				return "rangeEnd";
			num++;
			currnentDocNum = String.valueOf(num);
			// System.out.println(currnentDocNum);
			return currnentDocNum;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
			// TODO: handle exception
		}
	}

	// API to check the uniqueness of the docNumber(i.e checking External
	// numbering allowed or not, ranges and format as well)

	public String checkNumber(RequestDetails requestDetails, DocNumberRange doc, String docNumber) {

		String msg = "valid number";
		try {
			int tempDocNumber = 0;
			if (doc.getUserInputOfExtId().equalsIgnoreCase("Never"))
				msg = "External numbering not allowed"; // if
														// getUserInputOfExtID
														// = "Always"
														// then
														// msg="External numbering required"
			else if (doc.getUserInputOfExtId().equalsIgnoreCase("Always"))
				msg = "External number is required";
			// stop here if docNumber is null
			if (docNumber != null || docNumber != "") {
				if (containsOnlyNumbers(docNumber)) {
					if (Integer.parseInt(docNumber) < Integer.parseInt(doc.getExternalRangeStart())
							|| Integer.parseInt(docNumber) > Integer.parseInt(doc.getExternalRangeEnd()))
						msg = "External number outside range";
				} else
					msg = "External number is not in proper format ";

				if (doc.getDuplicatesAllowed() == false) {
					DocNumber currentDocNumber = loadCurrentDocNumber(requestDetails, doc, 0, null);
					tempDocNumber = Integer.parseInt(currentDocNumber.getDocNumber());
					if (Integer.parseInt(docNumber) <= tempDocNumber) {
						msg = "This extID already assigned to some other document. ";
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
		return msg;
	}

	// This method checks if a String contains only numbers
	private static boolean containsOnlyNumbers(String str) {

		// It can't contain only numbers if it's null or empty...
		if (str == null || str.length() == 0)
			return false;

		for (int i = 0; i < str.length(); i++) {
			// If we find a non-digit character we return false.
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}

	@SuppressWarnings("static-access")
	public long getExpiryDateWithDate(Date myDate, String internalRangeStart, String internalRangeEnd,
			String currentDocNumber) {
		try {
			Calendar resetDate = Calendar.getInstance();
			Calendar todayDate = Calendar.getInstance();
			resetDate.setTime(myDate);
			todayDate.setTime(new Date());
			long resetDateMilis = resetDate.getTimeInMillis();
			long todayDateMilis = todayDate.getTimeInMillis();
			long diff = todayDateMilis - resetDateMilis;
			long diffInhrs = diff / (60 * 60 * 1000);
			long diffInRange = Long.parseLong(internalRangeEnd) - Long.parseLong(internalRangeStart);
			long diffInDocNum = Long.parseLong(currentDocNumber) - Long.parseLong(internalRangeStart);
			long finalValue = 0;
			if (diffInDocNum <= 0) {
				diffInDocNum = 1;
			}

			if (diffInhrs <= 0) {
				diffInhrs = 1;
			}

			finalValue = (diffInhrs * diffInRange) / diffInDocNum;
			int finalValue2 = Integer.parseInt(String.valueOf(finalValue));
			resetDate.add(resetDate.HOUR, finalValue2);
			// Date finalDate = resetDate.getTime();
			return resetDate.getTimeInMillis();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;// TODO: handle exception
		}
	}

	// API for caclutaing expiry date
	public long getExpiryDate(String numRangeResetDate, String internalRangeStart, String internalRangeEnd,
			String currentDocNumber) {
		SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		Date myDate = null;
		try {
			myDate = sdfSource.parse(numRangeResetDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getExpiryDateWithDate(myDate, internalRangeStart, internalRangeEnd, currentDocNumber);
	}

	public boolean isNotNull(String strCheck) {
		if (strCheck != null && strCheck.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public String replaceCharAt(String s, int pos, char c) {
		return s.substring(0, pos) + c + s.substring(pos + 1);
	}

	public String getNumberNext(String startRange, String finishRange, String format, String currentNumber) {
		String nextNumber;
		@SuppressWarnings("unused")
		int length, nextNumValue, charValue;
		char format_typ, currentChar, nextCharValue;
		String formatToSeparateIntAndChars = "";
		String currentNumFormat = "";
		int count = 0;

		length = format.length();
		nextNumber = currentNumber;

		if (isInteger(currentNumber)) {
			int currentNumberInt = Integer.parseInt(currentNumber);
			int finishRangeInt = Integer.parseInt(finishRange);
			int startRangeInt = Integer.parseInt(startRange);

			if (currentNumberInt < finishRangeInt && currentNumberInt >= startRangeInt) {
				// nextNumber =
				// String.valueOf(IbeCacheFactory.getCacheImplementation().increment("NumberRange"));
				nextNumber = Integer.toString(currentNumberInt + 1);
			}
			// sometimes current number might be less than start number..so in that case
			// assign to start number.
			if (currentNumberInt < startRangeInt) {
				nextNumber = String.valueOf(startRangeInt);
			}
			int digits = 0;
			if (format != null) {
				digits = format.length();
				nextNumber = String.format("%0" + Integer.toString(digits) + "d", Integer.parseInt(nextNumber));
			}
		} else {
			if (currentNumber.length() != format.length()) {
				throw new RuntimeException(
						"Format mismatched for Current number and Format assigned in DocNumber config");
			}

			int currentNumberLength = currentNumber.length();
			for (int i = 0; i < currentNumberLength; i++) {
				if (Character.isLetter((currentNumber.charAt(i)))) {
					currentNumFormat = currentNumFormat + 'A';
				} else if (Character.isDigit((currentNumber.charAt(i)))) {
					currentNumFormat = currentNumFormat + 'N';
				} else {
					currentNumFormat = currentNumFormat + currentNumber.charAt(i);
				}
			}

			for (int i = 0; i < currentNumFormat.length(); i++) {
				if (i > 0 && (currentNumFormat.charAt(i) != currentNumFormat.charAt(i - 1)
						|| currentNumFormat.charAt(i - 1) == 'A')) {
					formatToSeparateIntAndChars = formatToSeparateIntAndChars + count + currentNumFormat.charAt(i - 1);
					count = 0;
				}
				count = count + 1;
				if (i == (currentNumFormat.length() - 1)) {
					formatToSeparateIntAndChars = formatToSeparateIntAndChars + count + currentNumFormat.charAt(i);
				}
			}

			int startIndex = currentNumber.length();
			int endIndex = currentNumber.length();
			for (int i = formatToSeparateIntAndChars.length() - 1; i >= 0; i--) {
				format_typ = formatToSeparateIntAndChars.charAt(i);
				if (format_typ == 'A') {
					char subStr = formatToSeparateIntAndChars.charAt(i - 1);
					int subStrLength = Character.getNumericValue(subStr);
					endIndex = startIndex;
					startIndex = endIndex - subStrLength;
					String startRangeSubstr = startRange.substring(startIndex, endIndex).toUpperCase();
					String finishRangeSubstr = finishRange.substring(startIndex, endIndex).toUpperCase();
					String currentNumberSubstr = currentNumber.substring(startIndex, endIndex).toUpperCase();
					if (currentNumberSubstr.equalsIgnoreCase(finishRangeSubstr) == false) {
						currentChar = currentNumberSubstr.charAt(0);
						charValue = currentChar;
						nextCharValue = (char) (charValue + 1);
						currentNumber = replaceCharAt(currentNumber, startIndex, nextCharValue);
						break;
					} else {
						currentChar = startRangeSubstr.charAt(0);
						currentNumber = replaceCharAt(currentNumber, startIndex, currentChar);

					}

				} else if (format_typ == 'N') {
					char subStr = formatToSeparateIntAndChars.charAt(i - 1);
					int subStrLength = Character.getNumericValue(subStr);
					endIndex = startIndex;
					startIndex = endIndex - subStrLength;
					String startRangeSubstr = startRange.substring(startIndex, endIndex).toUpperCase();
					String finishRangeSubstr = finishRange.substring(startIndex, endIndex).toUpperCase();
					String currentNumberSubstr = currentNumber.substring(startIndex, endIndex).toUpperCase();

					if (currentNumberSubstr.equalsIgnoreCase(finishRangeSubstr) == false) {
						nextNumValue = Integer.parseInt(currentNumberSubstr) + 1;
						;
						String i1 = Integer.toString(nextNumValue);
						while (i1.length() < subStrLength) {
							i1 = '0' + i1;
						}
						for (int j = 0; j < i1.length(); j++) {
							currentNumber = replaceCharAt(currentNumber, startIndex + j, i1.charAt(j));
						}
						break;
					} else {
						nextNumValue = Integer.parseInt(startRangeSubstr);
						String i1 = Integer.toString(nextNumValue);
						while (i1.length() < subStrLength) {
							i1 = '0' + i1;
						}
						for (int j = 0; j < i1.length(); j++) {
							currentNumber = replaceCharAt(currentNumber, startIndex + j, i1.charAt(j));
						}

					}
				} else if (!Character.isDigit(format_typ)) {
					char subStr = formatToSeparateIntAndChars.charAt(i - 1);
					int subStrLength = Character.getNumericValue(subStr);
					endIndex = startIndex;
					startIndex = endIndex - subStrLength;
				}
			}
			nextNumber = currentNumber;

		}

		return nextNumber;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getSeries(String startRange, String finishRange, String format, String currentNumber, int occurances) {

		Map map = null;
		List listForResults = new ArrayList();
		String currentDocNum = currentNumber;
		for (int i = 0; i <= occurances; i++) {
			currentDocNum = getNumberNext(startRange, finishRange, format, currentDocNum);
			map = new HashMap();
			map.put((i + 1) + " :", currentDocNum);
			listForResults.add(map);
			if (currentDocNum.equalsIgnoreCase(finishRange)) {
				break;
			}
		}
		return listForResults;
	}

}
