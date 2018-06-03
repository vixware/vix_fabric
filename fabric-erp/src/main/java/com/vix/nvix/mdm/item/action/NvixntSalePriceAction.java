package com.vix.nvix.mdm.item.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.TaxRate;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.constant.ItemConstant;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.PriceCondition;
import com.vix.mdm.item.entity.PriceConditionCountArea;
import com.vix.mdm.item.entity.PriceConditionPriceArea;
import com.vix.mdm.item.service.IPriceConditionService;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 物料定价条件
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class NvixntSalePriceAction extends VixntBaseAction implements ItemConstant {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IPriceConditionService priceConditionService;

	private String id;
	private String name;
	private PriceCondition priceCondition;
	private Item item;
	private String type;
	private String supplierId;
	private String channelId;
	private String channelDistributorId;
	private String customerAccountId;
	private String salesAreaId;
	private Long count;

	private String priceConditionId;
	private PriceConditionCountArea priceConditionCountArea;
	private PriceConditionPriceArea priceConditionPriceArea;
	private List<TaxRate> taxRateList;
	/** 获取列表数据 */
	public void getSalePriceJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			params.put("priceConditionType," + SearchCondition.EQUAL, "sale");

			String itemId = getRequestParameter("itemId");
			String subject = getRequestParameter("subject");
			if (null != subject && !"".equals(subject)) {
				subject = decode(subject, "UTF-8");
				params.put("subject," + SearchCondition.ANYLIKE, subject);
			}
			String companyCode = getRequestParameter("companyCode");
			if (null != companyCode && !"".equals(companyCode)) {
				companyCode = decode(companyCode, "UTF-8");
				params.put("companyCode," + SearchCondition.EQUAL, companyCode);
			}
			if (null != itemId && !"".equals(itemId) && !"undefined".equals(itemId)) {
				String[] idStr = itemId.split(",");
				if (idStr.length == 2 && "item".equals(idStr[1])) {
					params.put("item.id," + SearchCondition.EQUAL, idStr[0]);
				}
			}
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}

			pager = priceConditionService.findPagerByHqlConditions(getPager(), PriceCondition.class, params);
			String[] excludes = { "" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取列表数据 */
	public String goChooseListContent() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(10);
			Pager pager = priceConditionService.findPagerByHqlConditions(getPager(), PriceCondition.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseListContent";
	}

	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;

	public String goSaveOrUpdate() {
		try {
			taxRateList = vixntBaseService.findAllByEntityClass(TaxRate.class);
			currencyTypeList = priceConditionService.findAllByEntityClass(CurrencyType.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				priceCondition = priceConditionService.findEntityById(PriceCondition.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				priceCondition = new PriceCondition();
				Employee baseEmp = getEmployee();
				if (null != baseEmp) {
					Employee emp = priceConditionService.findEntityById(Employee.class, baseEmp.getId());
					if (null != emp) {
						priceCondition.setSaleOrg(emp.getOrganizationUnit());
					}
				}
				priceCondition.setCode(VixUUID.createCode(10));
				priceCondition.setIsTemp("1");
				priceCondition.setIsDeleted("0");
				priceCondition.setStartEffectiveTime(new Date());
				priceCondition.setEndEffectiveTime(sdf.parse("9999-12-31 23:59:59"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != priceCondition.getId()) {
				isSave = false;
			}
			priceCondition.setIsTemp("0");
			priceCondition.setIsDeleted("0");
			priceCondition.setPriceConditionType("sale");
			loadCommonData(priceCondition);
			if("1".equals(priceCondition.getEnable())){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("enable," + SearchCondition.EQUAL, "1");
				params.put("priceConditionType," + SearchCondition.EQUAL, "sale");
				params.put("id," + SearchCondition.NOEQUAL, priceCondition.getId());
				List<PriceCondition> priceConditions = priceConditionService.findAllByConditions(PriceCondition.class, params);
				if(priceConditions != null && priceConditions.size() > 0){
					for (PriceCondition pc : priceConditions) {
						pc.setEnable("0");
						pc = priceConditionService.merge(pc);
					}
				}
			}
			priceCondition = priceConditionService.merge(priceCondition);
			if (isSave) {
				renderText(SAVE_SUCCESS+":"+priceCondition.getId());
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
	}
	
	public void saveOrUpdateInner() {
		try {
			priceCondition.setIsTemp("0");
			priceCondition.setIsDeleted("0");
			priceCondition.setPriceConditionType("sale");
			loadCommonData(priceCondition);
			priceCondition = priceConditionService.merge(priceCondition);
			renderText(SAVE_SUCCESS+":"+priceCondition.getId());
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			PriceCondition pc = priceConditionService.findEntityById(PriceCondition.class, id);
			if (null != pc) {
				List<PriceConditionPriceArea> priceConditionPriceAreas = vixntBaseService.findAllByEntityClassAndAttribute(PriceConditionPriceArea.class, "priceCondition.id", pc.getId());
				for (PriceConditionPriceArea priceConditionPriceArea : priceConditionPriceAreas) {
					vixntBaseService.deleteByEntity(priceConditionPriceArea);
				}
				List<PriceConditionCountArea> priceConditionCountAreas = vixntBaseService.findAllByEntityClassAndAttribute(PriceConditionCountArea.class, "priceCondition.id", pc.getId());
				for (PriceConditionCountArea priceConditionCountArea : priceConditionCountAreas) {
					vixntBaseService.deleteByEntity(priceConditionCountArea);
				}
				vixntBaseService.deleteByEntity(pc);
				renderText(DELETE_SUCCESS);
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	public void getPriceJson(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StrUtils.isNotEmpty(name)){
				name = getDecodeRequestParameter("name");
				params.put("conditionType," + SearchCondition.ANYLIKE, name);
			}
			if(StrUtils.isNotEmpty(id)){
				priceCondition = vixntBaseService.findEntityById(PriceCondition.class, id);
				if(priceCondition != null){
					params.put("priceCondition.id," + SearchCondition.EQUAL, priceCondition.getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, PriceConditionPriceArea.class, params);
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdatePriceConditionPriceArea(){
		try {
			if(StringUtils.isNotEmpty(id)){
				priceConditionPriceArea = vixntBaseService.findEntityById(PriceConditionPriceArea.class, id);
			}else{
				priceConditionPriceArea = new PriceConditionPriceArea();
				if(StringUtils.isNotEmpty(priceConditionId)){
					priceCondition = vixntBaseService.findEntityById(PriceCondition.class, priceConditionId);
					if(priceCondition != null){
						priceConditionPriceArea.setPriceCondition(priceCondition);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePriceConditionPriceArea";
	}
	
	public void saveOrUpdatePriceConditionPriceArea(){
		boolean isSave = true;
		try {
			if (priceConditionPriceArea != null) {
				if(StrUtils.isNotEmpty(priceConditionPriceArea.getId())){
					isSave = false;
				}
				if(null == priceConditionPriceArea.getRegional() || StrUtils.isEmpty(priceConditionPriceArea.getRegional().getId())){
					priceConditionPriceArea.setRegional(null);
				}
				if(null == priceConditionPriceArea.getCustomerAccount() || StrUtils.isEmpty(priceConditionPriceArea.getCustomerAccount().getId())){
					priceConditionPriceArea.setCustomerAccount(null);
				}
				if(null == priceConditionPriceArea.getChannelDistributor() || StrUtils.isEmpty(priceConditionPriceArea.getChannelDistributor().getId())){
					priceConditionPriceArea.setChannelDistributor(null);
				}
				if(null == priceConditionPriceArea.getItem() || StrUtils.isEmpty(priceConditionPriceArea.getItem().getId())){
					priceConditionPriceArea.setItem(null);
				}
				if(null == priceConditionPriceArea.getStoreItem() || StrUtils.isEmpty(priceConditionPriceArea.getStoreItem().getId())){
					priceConditionPriceArea.setStoreItem(null);
				}
				initEntityBaseController.initEntityBaseAttribute(priceConditionPriceArea);
				loadCommonData(priceConditionPriceArea);
				priceConditionPriceArea = vixntBaseService.merge(priceConditionPriceArea);
				if (isSave) {
					renderText(SAVE_SUCCESS);
				} else {
					renderText(UPDATE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}
	
	public void deletePriceConditionPriceArea(){
		try {
			if(StringUtils.isNotEmpty(id)){
				priceConditionPriceArea = vixntBaseService.findEntityById(PriceConditionPriceArea.class, id);
				if(priceConditionPriceArea != null){
					vixntBaseService.deleteByEntity(priceConditionPriceArea);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	public void getCountPriceJson(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StrUtils.isNotEmpty(name)){
				name = getDecodeRequestParameter("name");
				params.put("conditionType," + SearchCondition.ANYLIKE, name);
			}
			if(StrUtils.isNotEmpty(id)){
				priceCondition = vixntBaseService.findEntityById(PriceCondition.class, id);
				if(priceCondition != null){
					params.put("priceCondition.id," + SearchCondition.EQUAL, priceCondition.getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, PriceConditionCountArea.class, params);
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增数量区间
	 * @return
	 */
	public String goSaveOrUpdatePriceConditionCountArea(){
		try {
			if(StringUtils.isNotEmpty(id)){
				priceConditionCountArea = vixntBaseService.findEntityById(PriceConditionCountArea.class, id);
			}else{
				priceConditionCountArea = new PriceConditionCountArea();
				if(StringUtils.isNotEmpty(priceConditionId)){
					priceCondition = vixntBaseService.findEntityById(PriceCondition.class, priceConditionId);
					if(priceCondition != null){
						priceConditionCountArea.setPriceCondition(priceCondition);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePriceConditionCountArea";
	}
	
	public void saveOrUpdatePriceConditionCountArea(){
		boolean isSave = true;
		try {
			if (priceConditionCountArea != null) {
				if(StrUtils.isNotEmpty(priceConditionCountArea.getId())){
					isSave = false;
				}
				if(null == priceConditionCountArea.getRegional() || StrUtils.isEmpty(priceConditionCountArea.getRegional().getId())){
					priceConditionCountArea.setRegional(null);
				}
				if(null == priceConditionCountArea.getCustomerAccount() || StrUtils.isEmpty(priceConditionCountArea.getCustomerAccount().getId())){
					priceConditionCountArea.setCustomerAccount(null);
				}
				if(null == priceConditionCountArea.getChannelDistributor() || StrUtils.isEmpty(priceConditionCountArea.getChannelDistributor().getId())){
					priceConditionCountArea.setChannelDistributor(null);
				}
				if(null == priceConditionCountArea.getItem() || StrUtils.isEmpty(priceConditionCountArea.getItem().getId())){
					priceConditionCountArea.setItem(null);
				}
				if(null == priceConditionCountArea.getStoreItem() || StrUtils.isEmpty(priceConditionCountArea.getStoreItem().getId())){
					priceConditionCountArea.setStoreItem(null);
				}
				initEntityBaseController.initEntityBaseAttribute(priceConditionCountArea);
				loadCommonData(priceConditionCountArea);
				priceConditionCountArea = vixntBaseService.merge(priceConditionCountArea);
				if (isSave) {
					renderText(SAVE_SUCCESS);
				} else {
					renderText(UPDATE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}
	
	public void deletePriceConditionCountArea(){
		try {
			if(StringUtils.isNotEmpty(id)){
				priceConditionCountArea = vixntBaseService.findEntityById(PriceConditionCountArea.class, id);
				if(priceConditionCountArea != null){
					vixntBaseService.deleteByEntity(priceConditionCountArea);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	public String goChooseItem() {
		return "goChooseItem";
	}
	
	public String goChooseChannelDistributor() {
		return "goChooseChannelDistributor";
	}
	
	public String goChooseChannelItem() {
		return "goChooseChannelItem";
	}
	
	public void goChannelDistributorList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			// 处理搜索条件
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("shopType," + SearchCondition.EQUAL, "1");
			params.put("status," + SearchCondition.EQUAL, "0");
			pager = vixntBaseService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goChannelItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name", "%" + searchName + "%");
			}
			String channelId = getRequestParameter("channelId");
			if (StringUtils.isNotEmpty(channelId)) {
				params.put("channelDistributorsId", channelId);
				pager = vixntBaseService.findStoreItemPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getItemList(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("itemClass,"+SearchCondition.IN, "goods,finishedgoods");
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("name,"+SearchCondition.ANYLIKE, selectName);
			}
			String code = getRequestParameter("code");
			if(StringUtils.isNotEmpty(code)){
				params.put("code,"+SearchCondition.ANYLIKE, code);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Item.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 启用
	public void enable(){
		try{
			if(StrUtils.isNotEmpty(id)){
				priceCondition = vixntBaseService.findEntityById(PriceCondition.class, id);
				priceCondition.setEnable("1");
				priceCondition.setUpdateTime(new Date());
				priceCondition = vixntBaseService.merge(priceCondition);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("enable," + SearchCondition.EQUAL, "1");
				params.put("priceConditionType," + SearchCondition.EQUAL, "sale");
				params.put("id," + SearchCondition.NOEQUAL, priceCondition.getId());
				List<PriceCondition> priceConditions = priceConditionService.findAllByConditions(PriceCondition.class, params);
				if(priceConditions != null && priceConditions.size() > 0){
					for (PriceCondition pc : priceConditions) {
						pc.setEnable("0");
						pc = priceConditionService.merge(pc);
					}
				}
				renderText(UPDATE_SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			renderText(UPDATE_FAIL);
		}
	}
	// 禁用
	public void noable(){
		try{
			if(StrUtils.isNotEmpty(id)){
				priceCondition = vixntBaseService.findEntityById(PriceCondition.class, id);
				priceCondition.setEnable("0");
				priceCondition.setUpdateTime(new Date());
				priceCondition = vixntBaseService.merge(priceCondition);
				renderText(UPDATE_SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			renderText(UPDATE_FAIL);
		}
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriceConditionId() {
		return priceConditionId;
	}

	public void setPriceConditionId(String priceConditionId) {
		this.priceConditionId = priceConditionId;
	}

	public PriceConditionCountArea getPriceConditionCountArea() {
		return priceConditionCountArea;
	}

	public void setPriceConditionCountArea(PriceConditionCountArea priceConditionCountArea) {
		this.priceConditionCountArea = priceConditionCountArea;
	}

	public PriceConditionPriceArea getPriceConditionPriceArea() {
		return priceConditionPriceArea;
	}

	public void setPriceConditionPriceArea(PriceConditionPriceArea priceConditionPriceArea) {
		this.priceConditionPriceArea = priceConditionPriceArea;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public List<TaxRate> getTaxRateList() {
		return taxRateList;
	}

	public void setTaxRateList(List<TaxRate> taxRateList) {
		this.taxRateList = taxRateList;
	}
}