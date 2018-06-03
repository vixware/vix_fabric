package com.vix.crm.project.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.Competitor;
import com.vix.crm.project.service.ICompetitorService;

@Controller
@Scope("prototype")
public class CompetitorAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICompetitorService competitorService;
	
	private String id;
	private String ids;
	private Competitor competitor;
	private String pageNo;
	private List<Competitor> indexList;
	
	@Override
	@SuppressWarnings("unchecked")
	public String goList(){
		try{
			Map<String,Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			competitorService.findPagerByHqlConditions(getPager(), Competitor.class, params);
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
			String companyName = getRequestParameter("companyName");
			if(StrUtils.objectIsNotNull(companyName)){
				companyName = decode(companyName, "UTF-8");
				params.put("companyName,"+SearchCondition.ANYLIKE, companyName);
			}
			String product = getRequestParameter("product");
			if(StrUtils.objectIsNotNull(product)){
				product = decode(product, "UTF-8");
				params.put("competeProduct,"+SearchCondition.ANYLIKE, product);
			}
			Pager pager = competitorService.findPagerByHqlConditions(getPager(), Competitor.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 获取列表数据  */
	public String goListContentForCrmProject(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = competitorService.findPagerByHqlConditions(getPager(), Competitor.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSubListContent(){
		try {
			Map<String,Object> params = getParams();
			getPager().setPageSize(6);
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = competitorService.findPagerByHqlConditions(getPager(), Competitor.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
	
	private List<CurrencyType> currencyTypeList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			currencyTypeList = competitorService.findAllByEntityClass(CurrencyType.class);
			if(null != id && !"".equals(id)){
				competitor = competitorService.findEntityById(Competitor.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
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
			if(null != competitor.getId()){
				isSave = false;
			}else{
				competitor.setCreateTime(new Date());
				loadCommonData(competitor);
			}
			if(null == competitor.getCustomerAccount() || null == competitor.getCustomerAccount().getId() || !competitor.getCustomerAccount().getId().equals("") || !competitor.getCustomerAccount().getId().equals("0")){
				competitor.setCustomerAccount(null);
			}
			String name = competitor.getCompanyName();
			String py = ChnToPinYin.getPYString(name);
			competitor.setChineseFirstLetter(py.toUpperCase());
			competitor = competitorService.merge(competitor);
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
			Competitor pb = competitorService.findEntityById(Competitor.class,id);
			if(null != pb){
				competitorService.deleteByEntity(pb);
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
				competitorService.batchDelete(Competitor.class,delIds);
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
	
	/**
	 * 获取上一条数据
	 * 
	 * @return
	 */
	
	
	
	/**
	 * 获取下一条数据
	 * 
	 * @return
	 */

	
	public String goChooseCompetitor(){
		return "goChooseCompetitor";
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

	public Competitor getCompetitor() {
		return competitor;
	}

	public void setCompetitor(Competitor competitor) {
		this.competitor = competitor;
	}

	public List<Competitor> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<Competitor> indexList) {
		this.indexList = indexList;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

}
