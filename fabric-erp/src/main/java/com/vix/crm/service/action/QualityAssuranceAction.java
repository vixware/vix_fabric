package com.vix.crm.service.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.service.entity.QualityAssurance;

@Controller
@Scope("prototype")
public class QualityAssuranceAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private QualityAssurance qualityAssurance;
	private String pageNo;
	
	private List<QualityAssurance> indexList;

	@Override
	@SuppressWarnings("unchecked")
	public String goList(){
		try{
			Map<String,Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			baseHibernateService.findPagerByHqlConditions(getPager(), QualityAssurance.class, params);
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
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), QualityAssurance.class, params);
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
				qualityAssurance = baseHibernateService.findEntityById(QualityAssurance.class,id);
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
			if(StrUtils.objectIsNotNull(qualityAssurance.getId())){
				isSave = false;
			}else{
				qualityAssurance.setCreateDate(new Date());
				loadCommonData(qualityAssurance);
			}
			String name = qualityAssurance.getTitle();
			String py = ChnToPinYin.getPYString(name);
			qualityAssurance.setChineseFirstLetter(py.toUpperCase());
			qualityAssurance = baseHibernateService.merge(qualityAssurance);
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
			QualityAssurance pb = baseHibernateService.findEntityById(QualityAssurance.class,id);
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
				baseHibernateService.batchDelete(QualityAssurance.class,delIds);
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

	public QualityAssurance getQualityAssurance() {
		return qualityAssurance;
	}

	public void setQualityAssurance(QualityAssurance qualityAssurance) {
		this.qualityAssurance = qualityAssurance;
	}

	public List<QualityAssurance> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<QualityAssurance> indexList) {
		this.indexList = indexList;
	}
}
