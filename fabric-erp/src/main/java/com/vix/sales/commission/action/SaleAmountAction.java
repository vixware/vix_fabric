package com.vix.sales.commission.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.sales.commission.entity.SaleAmount;
import com.vix.sales.commission.entity.SaleAmountItem;

@Controller
@Scope("prototype")
public class SaleAmountAction extends BaseAction{
	private static final long serialVersionUID = 1L;


	
	private String id;
	private SaleAmount saleAmount;
	private String pageNo;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			String pcId = getRequestParameter("personnelCategoryId");
			if(null != pcId && !"".equals(pcId)){
				params.put("personnelCategory.id,"+SearchCondition.EQUAL, Long.parseLong(pcId));
			}
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), SaleAmount.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id) && !"0".equals(id)){
				saleAmount = baseHibernateService.findEntityById(SaleAmount.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}else{
				saleAmount = new SaleAmount();
				loadCommonData(saleAmount);
				saleAmount.setIsTemp("1");
				saleAmount = baseHibernateService.merge(saleAmount);
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
			if(StrUtils.objectIsNotNull(saleAmount.getId())){
				isSave = false;
			}else{
				saleAmount.setCreateTime(new Date());
				loadCommonData(saleAmount);
			}
			saleAmount.setIsTemp("1");
			
			String[] attrArray = {"saleOrg","employee","personnelCategory"}; 
			checkEntityNullValue(saleAmount, attrArray);
		
			saleAmount = baseHibernateService.merge(saleAmount);
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
			SaleAmount pb = baseHibernateService.findEntityById(SaleAmount.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
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

	public void getSaleAmountItemJson(){
		try {
			String json = "";
			if(null != id && !"".equals(id)){
				SaleAmount sa = baseHibernateService.findEntityById(SaleAmount.class, id);
				if(null != sa){
					json = convertListToJson(new ArrayList<SaleAmountItem>(sa.getSaleAmountItems()),sa.getSaleAmountItems().size(),"saleAmount");
				}
			}
			if(!"".equals(json)){
				renderJson(json);
			}else{
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public SaleAmount getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(SaleAmount saleAmount) {
		this.saleAmount = saleAmount;
	}
}