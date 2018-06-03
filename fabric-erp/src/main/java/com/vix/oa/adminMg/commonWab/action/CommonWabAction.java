
package com.vix.oa.adminMg.commonWab.action;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.wab.controller.WabController;
import com.vix.oa.personaloffice.wab.entity.Wab;

/**
 * 
 * @ClassName: CommonWabAction
 * @Description: 公共通讯簿 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-26 上午10:39:41
 */
@Controller
@Scope("prototype")
public class CommonWabAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(WabController.class);
	
	@Autowired
	private WabController wabController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	private Wab wab;
	
	private List<Wab> wabList;
	
	private String id;
	
	private String parentId;
	
	private String pageNo;
	
	private Date updateTime;
	
	
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			wabList = wabController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取公共通讯簿列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			params.put("wabtype," + SearchCondition.EQUAL,"1");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}			
			if(StringUtils.isNotEmpty(parentId) && !parentId.equals("0")){
				params.put("parentCategory.id,"+SearchCondition.EQUAL,parentId);
			}
			Pager pager = wabController.doSubSingleList(params,getPager());
			logger.info("获取电话薄列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSearch() {
		return "goSearch";
	}
	
	/** 获取电话薄搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			params.put("wabtype," + SearchCondition.EQUAL,"1");
			String i = getRequestParameter("i");
			// 姓名
			String name = getRequestParameter("name");
			if (null != name && !"".equals(name)) {
				name = URLDecoder.decode(name, "utf-8");
			}
			// 公司
			String company = getRequestParameter("company");
			if (null != company && !"".equals(company)) {
				company = URLDecoder.decode(company, "utf-8");
			}
			//手机号码
			String mobileno = getRequestParameter("mobileno");
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
				pager = wabController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != company && !"".equals(company)) {
					params.put("company," + SearchCondition.ANYLIKE, company);
				}
				if (null != name && !"".equals(name)) {
					params.put("name," + SearchCondition.ANYLIKE, name);
				}
				if (null != mobileno && !"".equals(mobileno)) {
					params.put("mobileno," + SearchCondition.ANYLIKE, mobileno);
				}
				pager = wabController.goSingleList(params, getPager());
			}
			logger.info("获取电话薄搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	

	
	/** 跳转至公共通讯簿用户修改页面 */
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				wab = wabController.doListEntityById(id);
				logger.info("");
			}else{
				if(StringUtils.isNotEmpty(parentId) && !parentId.equals("0")){
					Wab c = baseHibernateService.findEntityById(Wab.class,parentId);
					if(null != c){
						wab = new Wab();
						wab.setParentCategory(c);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理公共通讯簿新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (wab.getParentCategory() == null||StringUtils.isNotEmpty(wab.getParentCategory().getId() )){
				wab.setParentCategory(null);
			}
			if (StringUtils.isNotEmpty(wab.getId()) && !"".equals(wab.getId())) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(wab);
			wab = wabController.doSaveSalesOrder(wab);
			/**拿到当前用户*/
			this.wab.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.wab.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			wab.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.wab);
			logger.info("新增！");
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
	
	/** 处理公共通讯簿删除操作 */
	public String deleteById() {
		try {
			Wab pb = wabController.findEntityById(id);
			if (null != pb) {
				wabController.doDeleteByEntity(pb);
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
	
	/** 树形结构JSON */
	public void findTreeToJson(){
		try{
			List<Wab> listCategory = new ArrayList<Wab>();
			/** 获取查询参数 */
			Map<String,Object> params = getParams();
			if(null!=id && !"".equals(id)){
				listCategory = baseHibernateService.findAllSubEntity(Wab.class, "parentCategory.id", id, params);
			}else{
				listCategory = baseHibernateService.findAllSubEntity(Wab.class, "parentCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for(int i =0;i<listCategory.size();i++){
				Wab cc = listCategory.get(i);
				if(cc.getSubCategorys().size() > 0){
					strSb.append("{id:\"");
					strSb.append(cc.getId());
					strSb.append("\",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:true}");
				}else{
					strSb.append("{id:\"");
					strSb.append(cc.getId());
					strSb.append("\",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:false}");
				}
				if(i < listCategory.size() -1){
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String goChooseCategory(){
		return "goChooseCategory";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public Wab getWab() {
		return wab;
	}

	public void setWab(Wab wab) {
		this.wab = wab;
	}

	public List<Wab> getWabList() {
		return wabList;
	}

	public void setWabList(List<Wab> wabList) {
		this.wabList = wabList;
	}

}
