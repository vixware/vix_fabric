package com.vix.mdm.crm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.crm.base.entity.ContactPersonType;
import com.vix.crm.base.entity.CredentialType;
import com.vix.crm.base.entity.CustomerSource;
import com.vix.crm.base.entity.CustomerStage;
import com.vix.crm.base.entity.CustomerType;
import com.vix.crm.base.entity.HotLevel;
import com.vix.crm.base.entity.Industry;
import com.vix.crm.base.entity.RelationshipClass;
import com.vix.crm.base.entity.StaffScale;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.crm.member.entity.MemberTag;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerShare;
import com.vix.mdm.crm.service.ICustomerAccountService;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class CustomerAccountAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICustomerAccountService customerAccountService;

	private String id;
	private String ids;
	private CustomerAccount customerAccount;
	private ContactPerson contactPerson;
	private String pageNo;
	private List<HotLevel> hotLevelList;
	private List<CustomerType> customerTypeList;
	private List<CustomerSource> customerSourceList;
	private List<CustomerStage> customerStageList;
	private List<Industry> industryList;
	private List<RelationshipClass> relationshipClassList;
	private List<StaffScale> staffScaleList;
	private List<CredentialType> credentialTypeList;
	private List<ContactPersonType> contactPersonTypeList;
	/** 会员等级 */
	private List<MemberLevel> memberLevelList;
	/** 会员标签 */
	private List<MemberTag> memberTagsList;
	private String customerAccountType;//瀹㈡埛绫诲瀷
	private String source;
	/** 消费金额 */
	private Double count = 0.0;
	/** 闄勪欢 */
	private File fileToUpload;
	/** 闄勪欢鐨勫悕绉� */
	private String fileToUploadFileName;
	private List<CustomerAccount> caList;

	@Override
	@SuppressWarnings("unchecked")
	public String goList() {
		try {
			caList = getPager().getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goList";
	}

	/** 鑾峰彇鍒楄〃鏁版嵁 */
	public String goListContent() {
		try {
			StringBuilder hqlBuilder = new StringBuilder("select ca from CustomerShare cs left join cs.customerAccount ca where cs.customerAccount.id = ca.id AND ca.isTemp = :isTemp and ca.isDeleted = :isDeleted ");
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			if (null != source && "member".equals(source)) {
				params.put("type," + SearchCondition.EQUAL, "member");
				hqlBuilder.append("and ca.type = :type ");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String name = getRequestParameter("name");
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
				hqlBuilder.append("and ca.name like :name ");
			}
			Employee emp = getEmployee();
			if (null != emp && StrUtils.objectIsNotNull(emp.getId())) {
				params.put("creator," + SearchCondition.EQUAL, emp.getId());
				hqlBuilder.append("and ca.creator = :creator ");
			}
			params.put("isHighSea," + SearchCondition.EQUAL, "1");
			hqlBuilder.append(" or ca.isHighSea = :isHighSea ");
			if (null != getPager().getOrderField()) {
				hqlBuilder.append(" order by ca.");
				hqlBuilder.append(getPager().getOrderField());
				if (null != getPager().getOrderBy()) {
					hqlBuilder.append(" ");
					hqlBuilder.append(getPager().getOrderBy());
				}
			}
			customerAccountService.findPagerByHql(getPager(), "ca", hqlBuilder.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSubSingleList() {
		try {
			StringBuilder hqlBuilder = new StringBuilder("select ca from CustomerShare cs left join cs.customerAccount ca where cs.customerAccount.id = ca.id AND ca.isTemp = :isTemp and ca.isDeleted = :isDeleted ");
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			getPager().setPageSize(6);
			String name = getRequestParameter("name");
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
				hqlBuilder.append("and ca.name like :name ");
			}
			Employee emp = getEmployee();
			if (null != emp && StrUtils.objectIsNotNull(emp.getId())) {
				params.put("creator," + SearchCondition.EQUAL, emp.getId());
				hqlBuilder.append("and ca.creator = :creator ");
			}
			params.put("isHighSea," + SearchCondition.EQUAL, "1");
			hqlBuilder.append(" or ca.isHighSea = :isHighSea ");
			if (null != getPager().getOrderField()) {
				hqlBuilder.append(" order by ca.");
				hqlBuilder.append(getPager().getOrderField());
				if (null != getPager().getOrderBy()) {
					hqlBuilder.append(" ");
					hqlBuilder.append(getPager().getOrderBy());
				}
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			customerAccountService.findPagerByHql(getPager(), "ca", hqlBuilder.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 璺宠浆鑷崇敤鎴蜂慨鏀归〉闈� */
	public String goSaveOrUpdate() {
		try {
			customerTypeList = customerAccountService.findAllByEntityClassAndAttribute(CustomerType.class, "enable", "1");
			relationshipClassList = customerAccountService.findAllByEntityClassAndAttribute(RelationshipClass.class, "enable", "1");
			customerSourceList = customerAccountService.findAllByEntityClassAndAttribute(CustomerSource.class, "enable", "1");
			customerStageList = customerAccountService.findAllByEntityClassAndAttribute(CustomerStage.class, "enable", "1");
			hotLevelList = customerAccountService.findAllByEntityClassAndAttribute(HotLevel.class, "enable", "1");
			if ("enterPrise".equals(customerAccountType) || "internal".equals(customerAccountType)) {
				industryList = customerAccountService.findAllByEntityClassAndAttribute(Industry.class, "enable", "1");
				staffScaleList = customerAccountService.findAllByEntityClassAndAttribute(StaffScale.class, "enable", "1");
			} else {
				credentialTypeList = customerAccountService.findAllByEntityClassAndAttribute(CredentialType.class, "enable", "1");
				contactPersonTypeList = customerAccountService.findAllByEntityClassAndAttribute(ContactPersonType.class, "enable", "1");
			}
			if(StringUtils.isNotEmpty(id) && !"0".equals(id)){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class, id);
				if (!"enterPrise".equals(customerAccountType) && !"internal".equals(customerAccountType)) {
					contactPerson = customerAccount.getContactPerson();
				}
			} else {
				customerAccount = new CustomerAccount();
				customerAccount.setIsTemp("1");
				customerAccount.setIsDeleted("0");
				customerAccount.setIsHighSea("0");
				customerAccount.setCreateTime(new Date());
				customerAccount.setType(customerAccountType);
				Employee emp = getEmployee();
				if (null != emp) {
					customerAccount.setCreatorCode(emp.getCode());
					customerAccount.setCreator(emp.getName());
				}
				loadCommonData(customerAccount);
				customerAccount = customerAccountService.merge(customerAccount);
				CustomerShare cs = new CustomerShare();
				cs.setCustomerAccount(customerAccount);
				if (null != emp) {
					cs.setEmployee(new Employee());
					cs.getEmployee().setId(emp.getId());
				}
				cs.setRead("1");
				cs.setWrite("1");
				loadCommonData(cs);
				customerAccountService.merge(cs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerAccountType;
	}

	/** 澶勭悊淇敼鎿嶄綔 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(customerAccount.getId())) {
				isSave = false;
				customerAccount.setUpdateTime(new Date());
			} else {
				customerAccount.setCreateTime(new Date());
				loadCommonData(customerAccount);
			}
			customerAccount.setIsTemp("0");
			customerAccount.setIsDeleted("0");
			
			String[] attrArray ={"hotLevel","customerAccountCategory","customerType","customerSource","customerStage","industry","relationshipClass","staffScale"};
			checkEntityNullValue(customerAccount,attrArray);

			if (null == customerAccount.getIsHighSea() || "".equals(customerAccount.getIsHighSea())) {
				customerAccount.setIsHighSea("0");
			}
			String name = customerAccount.getName();
			String py = ChnToPinYin.getPYString(name);
			customerAccount.setChineseFirstLetter(py.toUpperCase());
			customerAccount = customerAccountService.merge(customerAccount);
			if ("personal".equals(customerAccount.getType()) || "member".equals(customerAccount.getType())) {
				contactPerson.setNameAllSpelling(py.toUpperCase());
				contactPerson.setIsBlack("0");
				contactPerson.setCustomerAccount(customerAccount);
				
				String[] attrArrays ={"contactPersonType","credentialType"};
				checkEntityNullValue(contactPerson,attrArrays);
				
				if (null == contactPerson.getId() || "".equals(contactPerson.getId())) {
					if(null != customerAccount.getName() || !"".equals(customerAccount.getName())){
						contactPerson.setName(customerAccount.getName());
					}
				}
				contactPerson = customerAccountService.merge(contactPerson);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public void fastSaveOrUpdate() {
		try {
			loadCommonData(customerAccount);
			customerAccount.setIsTemp("0");
			customerAccount.setIsDeleted("0");
			customerAccount = customerAccountService.merge(customerAccount);
			renderText(customerAccount.getId() + "," + customerAccount.getName() + "," + SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 澶勭悊鍒犻櫎鎿嶄綔 */
	public String deleteById() {
		try {
			CustomerAccount pb = customerAccountService.findEntityById(CustomerAccount.class, id);
			if (null != pb) {
				pb.setIsDeleted("1");
				customerAccountService.merge(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goFastAddCustomerAccount() {
		return "goFastAddCustomerAccount";
	}

	public String goChooseCustomerAccount() {
		return "goChooseCustomerAccount";
	}

	public void getContactPersonJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				CustomerAccount ca = customerAccountService.findEntityById(CustomerAccount.class, id);
				if (null != ca) {
					json = convertListToJson(new ArrayList<ContactPerson>(ca.getContactPersons()), ca.getContactPersons().size(), "customerAccount");
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goCustomerView() {
		try {
			customerTypeList = customerAccountService.findAllByEntityClassAndAttribute(CustomerType.class, "enable", "1");
			relationshipClassList = customerAccountService.findAllByEntityClassAndAttribute(RelationshipClass.class, "enable", "1");
			customerSourceList = customerAccountService.findAllByEntityClassAndAttribute(CustomerSource.class, "enable", "1");
			customerStageList = customerAccountService.findAllByEntityClassAndAttribute(CustomerStage.class, "enable", "1");
			hotLevelList = customerAccountService.findAllByEntityClassAndAttribute(HotLevel.class, "enable", "1");
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (null != customerAccountType && ("enterPrise".equals(customerAccountType) || "internal".equals(customerAccountType))) {
			return "enterPriseView";
		} else {
			return "personalView";
		}
	}

	public String goMemberView() {
		Map<String,Object> params = getParams();
		Double amount = 0.0;
		try {
			customerTypeList = customerAccountService.findAllByEntityClassAndAttribute(CustomerType.class, "enable", "1");
			relationshipClassList = customerAccountService.findAllByEntityClassAndAttribute(RelationshipClass.class, "enable", "1");
			customerSourceList = customerAccountService.findAllByEntityClassAndAttribute(CustomerSource.class, "enable", "1");
			customerStageList = customerAccountService.findAllByEntityClassAndAttribute(CustomerStage.class, "enable", "1");
			hotLevelList = customerAccountService.findAllByEntityClassAndAttribute(HotLevel.class, "enable", "1");
			memberLevelList = customerAccountService.findAllByEntityClass(MemberLevel.class);
			memberTagsList = customerAccountService.findAllByEntityClass(MemberTag.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class, id);
				params.put("customerAccount.id," + SearchCondition.EQUAL, customerAccount.getId());
				List<SalesOrder> soList = customerAccountService.findAllByConditions(SalesOrder.class, params);
				if(null != soList && soList.size() > 0){
					for(SalesOrder so : soList){
						amount = so.getAmount();
						count += amount;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (null != customerAccountType && ("enterPrise".equals(customerAccountType) || "internal".equals(customerAccountType))) {
			return "goMemberView";
		} else {
			return "personalView";
		}
	}

	/** 閫夋嫨瀵煎叆瀹㈡埛鐨勬枃浠� */
	public String chooseCustomerAccountFile() {
		return "chooseCustomerAccountFile";
	}

	/** 瀹㈡埛瀵煎叆 */
	public String importCustomerAccountFile() {
		try {
			if (null != fileToUpload) {
				List<CustomerAccount> customerAccountList = customerAccountService.findAllByEntityClass(CustomerAccount.class);
				Set<String> codeSet = new HashSet<String>();
				for (CustomerAccount i : customerAccountList) {
					if (null != i && null != i.getCode() && !"".equals(i.getCode())) {
						codeSet.add(i.getName());
					}
				}
				InputStream is = new FileInputStream(fileToUpload);
				String fileExName = fileToUploadFileName.split("\\.")[1];
				if (null != fileExName && "xls".equals(fileExName.toLowerCase())) {
					//					Set<String> orderCodeSet = new HashSet<String>();
					//					HSSFWorkbook hssfWorkbook = new HSSFWorkbook( is);  
					//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					//				    // 寰幆宸ヤ綔琛⊿heet  
					//				    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){  
					//				    	HSSFSheet hssfSheet = hssfWorkbook.getSheetAt( numSheet);  
					//				    	if(hssfSheet == null){  
					//				    		continue;  
					//				    	}
					//				    	List<SaleOrderItem> soiList = new ArrayList<SaleOrderItem>();
					//				    	SalesOrder salesOrder = null;
					//				    	// 寰幆琛孯ow
					//				    	for(int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++){  
					//				    		HSSFRow hssfRow = hssfSheet.getRow( rowNum);  
					//				    		if(hssfRow == null || rowNum == 0){  
					//				    			continue;  
					//				    		}
					//				    		
					//				    		SaleOrderItem soi = new SaleOrderItem();
					//				    		String orderCode = "";
					//				    		String customerAccountName = "";
					//				    		String billDateStr = "";
					//					        // 寰幆鍒桟ell    
					//					        for(int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++){  
					//					        	HSSFCell hssfCell = hssfRow.getCell( cellNum);  
					//					        	if(hssfCell == null){  
					//					        		continue;  
					//					        	}
					//					        	String value = getXlsValue(hssfCell);
					//					        	if(cellNum == 0 && null != value && !"".equals(value)){
					//					        		orderCode = value;
					//					        		if(!orderCodeSet.contains(value)){
					//					        			salesOrder = new SalesOrder();
					//					        			orderCodeSet.add(value);
					//					        		}
					//					        	}
					//					        	if(cellNum == 1 && null != value && !"".equals(value)){
					//					        		customerAccountName = value;
					//					        	}
					//					        	if(cellNum == 2 && null != value && !"".equals(value)){
					//					        		billDateStr = value;
					//					        	}
					//					        	
					//					        	if(cellNum == 3 && null != value && !"".equals(value)){
					//					        		Item i = customerAccountService.findEntityByAttribute(Item.class, "barCode", value);
					//					        		if(null != i){
					//					        			soi.setItem(i);
					//					        			soi.setPrice(i.getPrice());
					//					        		}
					//					        	}
					//					        	if(cellNum == 4 && null != value && !"".equals(value)){
					//					        		soi.setAmount(Double.parseDouble(value));
					//					        		if(null == salesOrder.getId() || salesOrder.getId() <= 0){
					//					        			soiList.add(soi);
					//					        		}else{
					//					        			soi.setSalesOrder(salesOrder);
					//					        			loadCommonData(soi);
					//					        			customerAccountService.merge(soi);
					//					        		}
					//					        	}
					//					        }
					//					        if(null == salesOrder.getId() || salesOrder.getId() <= 0){
					//					        	loadCommonData(salesOrder);
					//					        	salesOrder.setCode(orderCode);
					//					        	salesOrder.setIsDeleted("0");
					//					        	salesOrder.setIsTemp("0");
					//					        	List<CustomerAccount> caList = customerAccountService.findAllByEntityClassAndAttribute(CustomerAccount.class, "name", customerAccountName);
					//					        	CustomerAccount ca =  caList.get(0);
					//					        	salesOrder.setCustomerAccount(ca);
					//					        	if(billDateStr.contains("-")){
					//					        		salesOrder.setBillDate(sdf.parse(billDateStr));
					//					        	}
					//					        	salesOrder = customerAccountService.merge(salesOrder);
					//					        	for(SaleOrderItem saleOrderItem : soiList){
					//					        		saleOrderItem.setSalesOrder(salesOrder);
					//					        		customerAccountService.merge(saleOrderItem);
					//					        	}
					//					        	soiList.clear();
					//					        }
					//				    	}  
					//				    }
					importXlsCustomerAccount(is, codeSet);
				}
				if (null != fileExName && "xlsx".equals(fileExName.toLowerCase())) {
					importXlsxCustomerAccount(is, codeSet);
				}
			}
			renderJson("瀹㈡埛瀵煎叆鎴愬姛!");
		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("瀹㈡埛瀵煎叆澶辫触!");
		}
		return UPDATE;
	}

	private void importXlsxCustomerAccount(InputStream is, Set<String> codeSet) throws Exception {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		// 寰幆宸ヤ綔琛⊿heet  
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// 寰幆琛孯ow   
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null || rowNum == 0) {
					continue;
				}
				CustomerAccount xlsCustomerAccount = new CustomerAccount();
				// 寰幆鍒桟ell     
				for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
					XSSFCell xssfCell = xssfRow.getCell(cellNum);
					if (xssfCell == null) {
						continue;
					}
					String value = getXlsxValue(xssfCell);
					if (cellNum == 0 && null != value && !"".equals(value)) {
						xlsCustomerAccount.setCode(value);
					}
					if (cellNum == 1 && null != value && !"".equals(value) && !codeSet.contains(value)) {
						xlsCustomerAccount.setName(value);
						codeSet.add(value);
					}
				}
				if (null != xlsCustomerAccount.getName() && !"".equals(xlsCustomerAccount.getName())) {
					loadCommonData(xlsCustomerAccount);
					xlsCustomerAccount.setIsDeleted("0");
					xlsCustomerAccount.setIsTemp("0");
					xlsCustomerAccount.setType("personal");
					customerAccountService.merge(xlsCustomerAccount);
					xlsCustomerAccount = null;
				}
			}
		}
	}

	private String getXlsxValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(xssfCell)) { //鍒ゆ柇鏄棩鏈熺被鍨�
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				Date dt = DateUtil.getJavaDate(xssfCell.getNumericCellValue());//鑾峰彇鎴怐ATE绫诲瀷   
				return dateformat.format(dt);
			}
			double d = xssfCell.getNumericCellValue();
			String s = NumberFormat.getInstance().format(d);
			return s.replaceAll(",", "");
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	private void importXlsCustomerAccount(InputStream is, Set<String> codeSet) throws Exception {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// 寰幆宸ヤ綔琛⊿heet  
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 寰幆琛孯ow   
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null || rowNum == 0) {
					continue;
				}
				CustomerAccount xlsCustomerAccount = new CustomerAccount();
				// 寰幆鍒桟ell    
				for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellNum);
					if (hssfCell == null) {
						continue;
					}
					String value = getXlsValue(hssfCell);
					if (cellNum == 0 && null != value && !"".equals(value)) {
						xlsCustomerAccount.setCode(value);
					}
					if (cellNum == 1 && null != value && !"".equals(value) && !codeSet.contains(value)) {
						xlsCustomerAccount.setName(value);
						codeSet.add(value);
					}
				}
				if (null != xlsCustomerAccount.getName() && !"".equals(xlsCustomerAccount.getName())) {
					loadCommonData(xlsCustomerAccount);
					xlsCustomerAccount.setIsDeleted("0");
					xlsCustomerAccount.setIsTemp("0");
					xlsCustomerAccount.setType("personal");
					customerAccountService.merge(xlsCustomerAccount);
					xlsCustomerAccount = null;
				}
			}
		}
	}

	private String getXlsValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(hssfCell)) { //鍒ゆ柇鏄棩鏈熺被鍨�
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				Date dt = DateUtil.getJavaDate(hssfCell.getNumericCellValue());//鑾峰彇鎴怐ATE绫诲瀷   
				return dateformat.format(dt);
			}
			double d = hssfCell.getNumericCellValue();
			String s = NumberFormat.getInstance().format(d);
			return s.replaceAll(",", "");
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public List<HotLevel> getHotLevelList() {
		return hotLevelList;
	}

	public void setHotLevelList(List<HotLevel> hotLevelList) {
		this.hotLevelList = hotLevelList;
	}

	public List<CustomerType> getCustomerTypeList() {
		return customerTypeList;
	}

	public void setCustomerTypeList(List<CustomerType> customerTypeList) {
		this.customerTypeList = customerTypeList;
	}

	public List<CustomerSource> getCustomerSourceList() {
		return customerSourceList;
	}

	public void setCustomerSourceList(List<CustomerSource> customerSourceList) {
		this.customerSourceList = customerSourceList;
	}

	public List<CustomerStage> getCustomerStageList() {
		return customerStageList;
	}

	public void setCustomerStageList(List<CustomerStage> customerStageList) {
		this.customerStageList = customerStageList;
	}

	public List<Industry> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<Industry> industryList) {
		this.industryList = industryList;
	}

	public List<RelationshipClass> getRelationshipClassList() {
		return relationshipClassList;
	}

	public void setRelationshipClassList(List<RelationshipClass> relationshipClassList) {
		this.relationshipClassList = relationshipClassList;
	}

	public List<StaffScale> getStaffScaleList() {
		return staffScaleList;
	}

	public void setStaffScaleList(List<StaffScale> staffScaleList) {
		this.staffScaleList = staffScaleList;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public List<CredentialType> getCredentialTypeList() {
		return credentialTypeList;
	}

	public void setCredentialTypeList(List<CredentialType> credentialTypeList) {
		this.credentialTypeList = credentialTypeList;
	}

	public List<ContactPersonType> getContactPersonTypeList() {
		return contactPersonTypeList;
	}

	public void setContactPersonTypeList(List<ContactPersonType> contactPersonTypeList) {
		this.contactPersonTypeList = contactPersonTypeList;
	}

	public String getCustomerAccountType() {
		return customerAccountType;
	}

	public void setCustomerAccountType(String customerAccountType) {
		this.customerAccountType = customerAccountType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<CustomerAccount> getCaList() {
		return caList;
	}

	public void setCaList(List<CustomerAccount> caList) {
		this.caList = caList;
	}

	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public List<MemberLevel> getMemberLevelList() {
		return memberLevelList;
	}

	public void setMemberLevelList(List<MemberLevel> memberLevelList) {
		this.memberLevelList = memberLevelList;
	}

	public List<MemberTag> getMemberTagsList() {
		return memberTagsList;
	}

	public void setMemberTagsList(List<MemberTag> memberTagsList) {
		this.memberTagsList = memberTagsList;
	}

}
