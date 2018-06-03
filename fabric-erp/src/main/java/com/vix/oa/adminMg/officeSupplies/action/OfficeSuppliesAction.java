package com.vix.oa.adminMg.officeSupplies.action;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.officeSupplies.controller.OfficeSuppliesController;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeCategory;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSupplies;
import com.vix.oa.personaloffice.wab.controller.WabController;

/**
 * 
 * @ClassName: OfficeSuppliesAction
 * @Description: 办公用品信息管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-12 下午2:13:33
 */
@Controller
@Scope("prototype")
public class OfficeSuppliesAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(WabController.class);
	@Autowired
	private OfficeSuppliesController officeSuppliesController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private OfficeSupplies officeSupplies;
	private List<OfficeSupplies> officeSuppliesList;
	/** 办公用品分类*/
	private OfficeCategory officeCategory;
	private String id;
	private String parentId;
	private String pageNo;
	private Date updateTime;
	private String officeSuppliesName;
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			officeSuppliesList = officeSuppliesController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}			
			if (parentId != null) {
				params.put("officeCategory.id," + SearchCondition.EQUAL,parentId);
			}
			// 按最近使用
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}
			Pager pager = officeSuppliesController.doSubSingleList(params,getPager());
			logger.info("获取办公用品列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}	
			if(null != officeSuppliesName && !"".equals(officeSuppliesName)){
				officeSuppliesName = decode(officeSuppliesName, "UTF-8");
				params.put("officeSuppliesName,"+SearchCondition.ANYLIKE, officeSuppliesName);
			}
			if (parentId != null) {
				params.put("officeCategory.id," + SearchCondition.EQUAL,parentId);
			}else{
				baseHibernateService.findPagerByHqlConditions(getPager(),OfficeSupplies.class,params);
			}
			// 按最近使用
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}
			Pager pager = officeSuppliesController.doSubSingleList(params,getPager());
			logger.info("获取办公用品列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}
	
	public String goSearch() {
		return "goSearch";
	}
	
	/** 获取办公用品搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 型号
			String model = getRequestParameter("model");
			if (null != model && !"".equals(model)) {
				model = URLDecoder.decode(model, "utf-8");
			}
			// 名称
			String officeSuppliesName = getRequestParameter("officeSuppliesName");
			if (null != officeSuppliesName && !"".equals(officeSuppliesName)) {
				officeSuppliesName = URLDecoder.decode(officeSuppliesName, "utf-8");
			}
			// 编码
			String officeSuppliesCode = getRequestParameter("officeSuppliesCode");
			if (null != officeSuppliesCode && !"".equals(officeSuppliesCode)) {
				officeSuppliesCode = URLDecoder.decode(officeSuppliesCode, "utf-8");
			}
			// 库存
			String currentInventory = getRequestParameter("currentInventory");
			if (null != currentInventory && !"".equals(currentInventory)) {
				currentInventory = URLDecoder.decode(currentInventory, "utf-8");
			}
			// 最低库存
			String lowestVigilance = getRequestParameter("lowestVigilance");
			if (null != lowestVigilance && !"".equals(lowestVigilance)) {
				lowestVigilance = URLDecoder.decode(lowestVigilance, "utf-8");
			}
			// 最高库存
			String maximumVigilance = getRequestParameter("maximumVigilance");
			if (null != maximumVigilance && !"".equals(maximumVigilance)) {
				maximumVigilance = URLDecoder.decode(maximumVigilance, "utf-8");
			}
			// 供应商
			String supplier = getRequestParameter("supplier");
			if (null != supplier && !"".equals(supplier)) {
				supplier = URLDecoder.decode(supplier, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("model," + SearchCondition.ANYLIKE, model);
				pager = officeSuppliesController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != model && !"".equals(model)) {
					params.put("model," + SearchCondition.ANYLIKE, model);
				}
				if (null != officeSuppliesName && !"".equals(officeSuppliesName)) {
					params.put("officeSuppliesName," + SearchCondition.ANYLIKE, officeSuppliesName);
				}
				if (null != officeSuppliesCode && !"".equals(officeSuppliesCode)) {
					params.put("officeSuppliesCode," + SearchCondition.ANYLIKE, officeSuppliesCode);
				}
				if (null != currentInventory && !"".equals(currentInventory)) {
					params.put("currentInventory," + SearchCondition.ANYLIKE, currentInventory);
				}
				if (null != lowestVigilance && !"".equals(lowestVigilance)) {
					params.put("lowestVigilance," + SearchCondition.ANYLIKE, lowestVigilance);
				}
				if (null != maximumVigilance && !"".equals(maximumVigilance)) {
					params.put("maximumVigilance," + SearchCondition.ANYLIKE, maximumVigilance);
				}
				if (null != supplier && !"".equals(supplier)) {
					params.put("supplier," + SearchCondition.ANYLIKE, supplier);
				}
				pager = officeSuppliesController.goSingleList(params, getPager());
			}
			logger.info("获取办公用品搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	

	
	/**
	 * 新增办公用品分类
	 * 
	 * @return
	 */
	public String goSaveOrUpdateCategory() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				officeCategory = baseHibernateService.findEntityById(OfficeCategory.class, id);
			} else {
				officeCategory = new OfficeCategory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCategory";
	}
	
	/**
	 * 保存办公用品分类
	 * 
	 * @return
	 */
	public String saveOrUpdateCategory() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(officeCategory.getId()) && !"".equals(officeCategory.getId())) {
				isSave = false;
			}
			if (officeCategory.getParentOfficeCategory().getId() == null||"".equals(officeCategory.getParentOfficeCategory().getId() )) {
				officeCategory.setParentOfficeCategory(null);
			}
			baseHibernateService.merge(officeCategory);
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
	
	/**
	 * 获取办公用品分类的树形结构
	 */
	public void findBookCategoryTree() {
		try {
			List<OfficeCategory> listCategory = new ArrayList<OfficeCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)&&!"0".equals(id)) {
				listCategory = baseHibernateService.findAllSubEntity(OfficeCategory.class, "parentOfficeCategory.id", id, params);
			} else {
				listCategory = baseHibernateService.findAllSubEntity(OfficeCategory.class, "parentOfficeCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				OfficeCategory oc = listCategory.get(i);
				if (oc.getSubOfficeCategory().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(oc.getId());
					strSb.append("\",name:\"");
					strSb.append(oc.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(oc.getId());
					strSb.append("\",name:\"");
					strSb.append(oc.getName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < listCategory.size() - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 选择分类
	 * @return
	 */
	public String goBookCategory() {
		return "goBookCategory";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				officeSupplies = baseHibernateService.findEntityById(OfficeSupplies.class, id);
			} 
			//如果带出parentId,就无法修改
			/*else {
				officeSupplies = new OfficeSupplies();
				if (null != parentId && parentId.longValue() > 0) {
					OfficeCategory officeCategory = baseHibernateService.findEntityById(OfficeCategory.class,parentId);
					if (officeCategory != null) {
						officeSupplies.setOfficeCategory(officeCategory);
					}
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}
	
	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(officeSupplies.getId()) && !"".equals(officeSupplies.getId())) {
				isSave = false;
			}
			/**索引 */
			String officeSuppliesName = officeSupplies.getOfficeSuppliesName();
			String py = ChnToPinYin.getPYString(officeSuppliesName);
			officeSupplies.setChineseFirstLetter(py.toUpperCase());;
			if (officeSupplies.getOfficeCategory() == null ||"".equals(officeSupplies.getOfficeCategory().getId())) {
				officeSupplies.setOfficeCategory(null);
			}
			initEntityBaseController.initEntityBaseAttribute(officeSupplies);
			officeSupplies = officeSuppliesController.doSaveSalesOrder(officeSupplies);
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
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			OfficeSupplies pb = officeSuppliesController.findEntityById(id);
			if (null != pb) {
				officeSuppliesController.doDeleteByEntity(pb);
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


	public OfficeSupplies getOfficeSupplies() {
		return officeSupplies;
	}

	public void setOfficeSupplies(OfficeSupplies officeSupplies) {
		this.officeSupplies = officeSupplies;
	}

	public List<OfficeSupplies> getOfficeSuppliesList() {
		return officeSuppliesList;
	}

	public void setOfficeSuppliesList(List<OfficeSupplies> officeSuppliesList) {
		this.officeSuppliesList = officeSuppliesList;
	}

	public OfficeCategory getOfficeCategory() {
		return officeCategory;
	}

	public void setOfficeCategory(OfficeCategory officeCategory) {
		this.officeCategory = officeCategory;
	}

	public String getOfficeSuppliesName() {
		return officeSuppliesName;
	}

	public void setOfficeSuppliesName(String officeSuppliesName) {
		this.officeSuppliesName = officeSuppliesName;
	}

}
