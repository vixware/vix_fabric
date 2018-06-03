package com.vix.crm.workbench.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.workbench.entity.Knowledge;

@Controller
@Scope("prototype")
public class KnowledgeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id; 
	private String ids;
	private Knowledge knowledge;
	private String subject;
	private String kmQuestion;
	private String knowledgeCategory;
	private String pageNo;
	private String filePath;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if (StrUtils.objectIsNotNull(subject)) {
				subject = decode(subject, "UTF-8");
				params.put("subject," + SearchCondition.ANYLIKE,subject);
			}
			if (StrUtils.objectIsNotNull(kmQuestion)) {
				kmQuestion = decode(kmQuestion, "UTF-8");
				params.put("kmQuestion," + SearchCondition.ANYLIKE,kmQuestion);
			}
			if (StrUtils.objectIsNotNull(knowledgeCategory)) {
				knowledgeCategory = decode(knowledgeCategory, "UTF-8");
				params.put("knowledge.knowledgeCategory," + SearchCondition.ANYLIKE,knowledgeCategory);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), Knowledge.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSubSingleList(){
		try {
			Map<String,Object> params = getParams();
			getPager().setPageSize(6);
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), Knowledge.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				knowledge = baseHibernateService.findEntityById(Knowledge.class,id);
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
			if(StrUtils.objectIsNotNull(knowledge.getId())){
				isSave = false;
			}else{
				knowledge.setCreateTime(new Date());
				loadCommonData(knowledge);
			}
			
			if(null == knowledge.getKnowledgeCategory() || StrUtils.objectIsNotNull(knowledge.getKnowledgeCategory().getId()) || !knowledge.getKnowledgeCategory().getId().equals("0")){
				knowledge.setKnowledgeCategory(null);
			}
			knowledge = baseHibernateService.merge(knowledge);
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
			Knowledge pb = baseHibernateService.findEntityById(Knowledge.class,id);
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
				baseHibernateService.batchDelete(Knowledge.class,delIds);
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
	
	//高级查询
	public String goSearch() {
		return "goSearch";
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

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getKmQuestion() {
		return kmQuestion;
	}

	public void setKmQuestion(String kmQuestion) {
		this.kmQuestion = kmQuestion;
	}

	public String getKnowledgeCategory() {
		return knowledgeCategory;
	}

	public void setKnowledgeCategory(String knowledgeCategory) {
		this.knowledgeCategory = knowledgeCategory;
	}

}
