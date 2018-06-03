package com.vix.crm.market.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.crm.market.entity.CrmGift;
import com.vix.crm.market.entity.CrmGiftRequisition;

@Controller
@Scope("prototype")
public class CrmGiftRequisitionAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private CrmGiftRequisition crmGiftRequisition;
	private String pageNo;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CrmGiftRequisition.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				crmGiftRequisition = baseHibernateService.findEntityById(CrmGiftRequisition.class,id);
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
			if(null != crmGiftRequisition.getId()){
				isSave = false;
			}else{
				crmGiftRequisition.setRequisitionDate(new Date());
				loadCommonData(crmGiftRequisition);
			}
			if(null != crmGiftRequisition.getCrmGift() && null != crmGiftRequisition.getCrmGift().getId() && null != crmGiftRequisition.getCount()){
				CrmGift cg = baseHibernateService.findEntityById(CrmGift.class, crmGiftRequisition.getCrmGift().getId());
				if(null != crmGiftRequisition.getId() && !crmGiftRequisition.getId().equals("") && !crmGiftRequisition.getId().equals("0")){
					CrmGiftRequisition c = baseHibernateService.findEntityById(CrmGiftRequisition.class, crmGiftRequisition.getId());
					if(null != c.getCount() && c.getCount() > 0){
						if(crmGiftRequisition.getCount() > c.getCount()){
							cg.setStockNumber(cg.getStockNumber() - (crmGiftRequisition.getCount()-c.getCount()));
							baseHibernateService.merge(cg);
						}else{
							cg.setStockNumber(cg.getStockNumber() - (crmGiftRequisition.getCount()-c.getCount()));
							baseHibernateService.merge(cg);
						}
					}else{
						cg.setStockNumber(cg.getStockNumber() - crmGiftRequisition.getCount());
						baseHibernateService.merge(cg);
					}
				}else{
					cg.setStockNumber(cg.getStockNumber() - crmGiftRequisition.getCount());
					baseHibernateService.merge(cg);
				}
			}
			crmGiftRequisition = baseHibernateService.merge(crmGiftRequisition);
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
			CrmGiftRequisition pb = baseHibernateService.findEntityById(CrmGiftRequisition.class,id);
			if(null != pb){
				if(null != pb.getCrmGift() && null != pb.getCrmGift().getId() && null != pb.getCount()){
					CrmGift cg = baseHibernateService.findEntityById(CrmGift.class, pb.getCrmGift().getId());
					cg.setStockNumber(cg.getStockNumber() + pb.getCount());
					baseHibernateService.merge(cg);
				}
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
				baseHibernateService.batchDelete(CrmGiftRequisition.class,delIds);
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
	
	public String goChooseType(){
		return "goChooseType";
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

	public CrmGiftRequisition getCrmGiftRequisition() {
		return crmGiftRequisition;
	}

	public void setCrmGiftRequisition(CrmGiftRequisition crmGiftRequisition) {
		this.crmGiftRequisition = crmGiftRequisition;
	}
}
