package com.vix.mdm.item.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.PriceCondition;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.item.service.IPriceConditionService;
/**
 * 物料定价条件
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class SalePriceConditionAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IPriceConditionService priceConditionService;
	@Autowired
	private IItemService itemService;
	
	private String id;
	private PriceCondition priceCondition;
	private Item item;
	private String type;
	private String supplierId;
	private String channelDistributorId;
	private String customerAccountId;
	private String salesAreaId;
	private Long count;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			String itemId = getRequestParameter("itemId");
			String subject = getRequestParameter("subject");
			if(null != subject && !"".equals(subject)){
				subject = decode(subject, "UTF-8");
				params.put("subject,"+SearchCondition.ANYLIKE, subject);
			}
			String companyCode = getRequestParameter("companyCode");
			if(null != companyCode && !"".equals(companyCode)){
				companyCode = decode(companyCode, "UTF-8");
				params.put("companyCode,"+SearchCondition.EQUAL, companyCode);
			}
			if(null != itemId && !"".equals(itemId) && !"undefined".equals(itemId)){
				String[] idStr = itemId.split(",");
				if(idStr.length == 2 && "item".equals(idStr[1])){
					params.put("item.id,"+SearchCondition.EQUAL, idStr[0]);
				}
			}
			params.put("isTemp,"+SearchCondition.NOEQUAL,"1");
			params.put("isDeleted,"+SearchCondition.NOEQUAL,"1");
			params.put("priceConditionType,"+SearchCondition.EQUAL,"sale");
			if(null == getPager().getOrderField() || "".equals(getPager().getOrderField())){
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			Pager pager = priceConditionService.findPagerByHqlConditions(getPager(),PriceCondition.class,params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goListContent";
	}
	
	/** 获取列表数据  */
	public String goChooseListContent(){
		try {
			Map<String,Object> params = getParams();
			getPager().setPageSize(10);
			Pager pager = priceConditionService.findPagerByHqlConditions(getPager(),PriceCondition.class,params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goChooseListContent";
	}
	
	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;
	public String goSaveOrUpdate() {
		try {
			currencyTypeList = priceConditionService.findAllByEntityClass(CurrencyType.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				priceCondition = priceConditionService.findEntityById(PriceCondition.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				priceCondition = new PriceCondition();
				Employee baseEmp = getEmployee();
				if(null != baseEmp){
					Employee emp = priceConditionService.findEntityById(Employee.class, baseEmp.getId());
					if(null != emp){
						priceCondition.setSaleOrg(emp.getOrganizationUnit());
					}
				}
				priceCondition.setIsTemp("1");
				priceCondition.setIsDeleted("0");
				priceCondition.setStartEffectiveTime(new Date());
				priceCondition.setEndEffectiveTime(sdf.parse("9999-12-31 23:59:59"));
				loadCommonData(priceCondition);
				priceCondition = priceConditionService.merge(priceCondition);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != priceCondition.getId()){
				isSave = false;
			}
			priceCondition.setIsTemp("0");
			priceCondition.setIsDeleted("0");
			priceCondition.setPriceConditionType("sale");
			
			String[] attrArray = {"currencyType","saleOrg"};
			checkEntityNullValue(priceCondition, attrArray);
			
			/*if(null != priceCondition.getCurrencyType() && null != priceCondition.getCurrencyType().getId() && !priceCondition.getCurrencyType().getId().equals("") && !priceCondition.getCurrencyType().getId().equals("0")){
				priceCondition.setCurrencyType(null);
			}*/
			priceCondition = priceConditionService.merge(priceCondition);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			PriceCondition pc = priceConditionService.findEntityById(PriceCondition.class,id);
			if(null != pc){
				pc.setIsTemp("1");
				priceConditionService.merge(pc);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PriceCondition getPriceCondition() {
		return priceCondition;
	}

	public void setPriceCondition(PriceCondition priceCondition) {
		this.priceCondition = priceCondition;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getSalesAreaId() {
		return salesAreaId;
	}

	public void setSalesAreaId(String salesAreaId) {
		this.salesAreaId = salesAreaId;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}
}