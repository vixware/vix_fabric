package com.vix.crm.salechance.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.SaleChanceStatus;
import com.vix.crm.base.entity.SaleStage;
import com.vix.crm.base.entity.SaleType;
import com.vix.crm.salechance.entity.SaleChance;

@Controller
@Scope("prototype")
public class SaleChanceAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String categoryId;
	private String ids;
	private SaleChance saleChance;
	private String companyCode;
	private String pageNo;
	
	private List<SaleChance> indexList;

	@Override
	@SuppressWarnings("unchecked")
	public String goList(){
		try{
			Map<String,Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			baseHibernateService.findPagerByHqlConditions(getPager(), SaleChance.class, params);
			indexList = getPager().getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "goList";
	}
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String subject = getRequestParameter("subject");
			if(StrUtils.objectIsNotNull(subject)){
				subject = decode(subject, "UTF-8");
				params.put("subject,"+SearchCondition.ANYLIKE, subject);
			}
			
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			
			String charger = getRequestParameter("charger");
			if(StrUtils.objectIsNotNull(charger)){
				charger = decode(charger, "UTF-8");
				params.put("charger,"+SearchCondition.ANYLIKE, charger);
			}
			
			if(null != companyCode && !"".equals(companyCode)){
				params.put("companyCode,"+SearchCondition.EQUAL, companyCode);
			}
			params.put("isDeleted,"+SearchCondition.NOEQUAL, "1");
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), SaleChance.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	// 高级搜索
	public String goSearch() {
		return "goSearch";
	}
	
	private List<CurrencyType> currencyTypeList;
	private List<SaleStage> saleStageList;
	private List<SaleChanceStatus> saleChanceStatusList;
	private List<SaleType> saleTypeList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			saleTypeList = baseHibernateService.findAllByEntityClass(SaleType.class);
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			saleStageList =  baseHibernateService.findAllByEntityClass(SaleStage.class);
			saleChanceStatusList = baseHibernateService.findAllByEntityClass(SaleChanceStatus.class);
			if(null != id && !"".equals(id)){
				saleChance = baseHibernateService.findEntityById(SaleChance.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}else{
				saleChance = new SaleChance();
				saleChance.setFindDate(new Date());
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
			if(StrUtils.objectIsNotNull(saleChance.getId())){
				isSave = false;
			}else{
				saleChance.setCreateTime(new Date());
				saleChance.setIsDeleted("0");
				loadCommonData(saleChance);
			}
			
			String[] attrArray ={"saleChanceStatus","currencyType","customerAccount","contactPerson","saleStage","crmProject"};
			checkEntityNullValue(saleChance,attrArray);
			
			String name = saleChance.getSubject();
			String py = ChnToPinYin.getPYString(name);
			saleChance.setChineseFirstLetter(py.toUpperCase());
			saleChance.setDateModified(new Date());
			saleChance = baseHibernateService.merge(saleChance);
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
			SaleChance pb = baseHibernateService.findEntityById(SaleChance.class,id);
			if(null != pb){
				pb.setIsDeleted("1");
				baseHibernateService.merge(pb);
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
	
	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if(null != ids && !"".equals(ids)){
				List<String> delIds = new ArrayList<String>();
				for(String idStr : ids.split(",")){
					if(null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)){
						delIds.add(idStr);
					}
				}
				baseHibernateService.batchDelete(SaleChance.class,delIds);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public String goSaveOrUpdateForCustomerAccount(){
		goSaveOrUpdate();
		return "goSaveOrUpdateForCustomerAccount";
	}
	
	public String saveOrUpdateForCustomerAccount(){
		saveOrUpdate();
		return UPDATE;
	}

	public String goListContentForCustomerAccount(){
		Map<String,Object> params = getParams();
		if(null != id && !"".equals(id)){
			params.put("customerAccount.id,"+SearchCondition.EQUAL, id);
		}
		goListContent();
		return "goSingleListForCustomerAccount";
	}
	
	public String goChooseType(){
		return "goChooseType";
	}
	
	public void loadFunnel(){
		StringBuilder jsonBuilder = new StringBuilder();
		try{
			jsonBuilder.append("[{name: '数量',data: [");
			List<SaleStage> scsList = baseHibernateService.findAllByEntityClassAndAttribute(SaleStage.class, "enable", "1");
			Map<String,Object> params = getParams();
			if(null != id && !"".equals(id)){
				params.put("customerAccount.id,"+SearchCondition.EQUAL, id);
			}
			if(null != categoryId && !"".equals(categoryId)){
				params.put("customerAccount.customerAccountCategory.id,"+SearchCondition.EQUAL, categoryId);
			}
			if(null != scsList && scsList.size() > 0){
				List<String> list = new ArrayList<String>();
				for(int i=0;i<scsList.size();i++){
					SaleStage scs = scsList.get(i);
					params.put("saleStage.id,"+SearchCondition.EQUAL, scs.getId());
					List<SaleChance> scList = baseHibernateService.findAllByConditions(SaleChance.class, params);
					if(null != scList && scList.size() > 0){
						list.add("['" + scs.getName() + "', " + scList.size() + " ]");
					}
				}
				for(int i=0;i<list.size();i++){
					if(i>0){
						jsonBuilder.append(",");
					}
					jsonBuilder.append(list.get(i));
				}
			}
			jsonBuilder.append("]}]");
			renderHtml(jsonBuilder.toString());
		}catch(Exception ex){
			ex.printStackTrace();
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

	public SaleChance getSaleChance() {
		return saleChance;
	}

	public void setSaleChance(SaleChance saleChance) {
		this.saleChance = saleChance;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<SaleStage> getSaleStageList() {
		return saleStageList;
	}

	public void setSaleStageList(List<SaleStage> saleStageList) {
		this.saleStageList = saleStageList;
	}

	public List<SaleChanceStatus> getSaleChanceStatusList() {
		return saleChanceStatusList;
	}

	public void setSaleChanceStatusList(List<SaleChanceStatus> saleChanceStatusList) {
		this.saleChanceStatusList = saleChanceStatusList;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public List<SaleChance> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<SaleChance> indexList) {
		this.indexList = indexList;
	}

	public List<SaleType> getSaleTypeList() {
		return saleTypeList;
	}

	public void setSaleTypeList(List<SaleType> saleTypeList) {
		this.saleTypeList = saleTypeList;
	}
	
}
