package com.vix.crm.workbench.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.workbench.entity.InnerBulletin;

@Controller
@Scope("prototype")
public class InnerBulletinAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private InnerBulletin innerBulletin;
	private String pageNo;
	private String filePath;
	
	private List<InnerBulletin> ibList;

	@Override
	@SuppressWarnings("unchecked")
	public String goList(){
		try{
			Map<String,Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			baseHibernateService.findPagerByHqlConditions(getPager(), InnerBulletin.class, params);
			ibList = getPager().getResultList();
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
			String title = getRequestParameter("title");
			if(null != title && !"".equals(title)){
				title = decode(title, "UTF-8");
				params.put("title,"+SearchCondition.ANYLIKE, title);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), InnerBulletin.class, params);
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
				innerBulletin = baseHibernateService.findEntityById(InnerBulletin.class,id);
				if(null != innerBulletin.getFileName() && !"".equals(innerBulletin.getFileName())){
					filePath = PropertyConfigLoader.crm_innerBulletinPath + System.getProperty("file.separator") + innerBulletin.getFileName();
					File f = new File(getBasePath(PropertyConfigLoader.crm_innerBulletinPath)+System.getProperty("file.separator") + innerBulletin.getFileName());
					if(!f.exists()){
						filePath = "";
					}
				}
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
			if(null != innerBulletin.getId()){
				isSave = false;
			}else{
				innerBulletin.setCreateDate(new Date());
				loadCommonData(innerBulletin);
			}
			if(null != PropertyConfigLoader.crm_innerBulletinPath && !"".equals(PropertyConfigLoader.crm_innerBulletinPath)){
				String basePath = getBasePath(PropertyConfigLoader.crm_innerBulletinPath);
				String bulletinFilePath = basePath+System.getProperty("file.separator");
				if(null != innerBulletin.getFileName() && !"".equals(innerBulletin.getFileName())){
					String fPath = bulletinFilePath + innerBulletin.getFileName();
					File f = new File(fPath);
					if(f.exists()){
						this.saveFile(innerBulletin.getContent(), bulletinFilePath+innerBulletin.getFileName());
					}else{
						String fileName = "bulletin_"+System.currentTimeMillis()+".html";
						this.saveFile(innerBulletin.getContent(), bulletinFilePath+fileName);
						innerBulletin.setFileName(fileName);
					}
				}else{
					String fileName = "bulletin_"+System.currentTimeMillis()+".html";
					this.saveFile(innerBulletin.getContent(), bulletinFilePath+fileName);
					innerBulletin.setFileName(fileName);
				}
			}
			if(null == innerBulletin.getInnerBulletinCategory() || null == innerBulletin.getInnerBulletinCategory().getId() || !innerBulletin.getInnerBulletinCategory().getId().equals("") || !innerBulletin.getInnerBulletinCategory().getId().equals("0")){
				innerBulletin.setInnerBulletinCategory(null);
			}
			String name = innerBulletin.getTitle();
			String py = ChnToPinYin.getPYString(name);
			innerBulletin.setChineseFirstLetter(py.toUpperCase());
			innerBulletin = baseHibernateService.merge(innerBulletin);
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
			InnerBulletin pb = baseHibernateService.findEntityById(InnerBulletin.class,id);
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
				baseHibernateService.batchDelete(InnerBulletin.class,delIds);
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

	public InnerBulletin getInnerBulletin() {
		return innerBulletin;
	}

	public void setInnerBulletin(InnerBulletin innerBulletin) {
		this.innerBulletin = innerBulletin;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<InnerBulletin> getIbList() {
		return ibList;
	}

	public void setIbList(List<InnerBulletin> ibList) {
		this.ibList = ibList;
	}
}
