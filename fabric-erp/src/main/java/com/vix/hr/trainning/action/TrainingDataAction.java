package com.vix.hr.trainning.action;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.controller.TrainingDataController;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.service.ITrainingDataService;

/**
 * @Description: 培训资源
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class TrainingDataAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(TrainingData.class);

	/** 注入service */
	@Autowired
	private TrainingDataController trainingDataController;
	private List<TrainingData> trainingDataList;
	private TrainingData trainingData;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private ITrainingDataService iTrainingDataService;
	private String id;

	private String pageNo;

	private String parentId;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<TrainingData> getTrainingDataList() {
		return trainingDataList;
	}

	public void setTrainingDataList(List<TrainingData> trainingDataList) {
		this.trainingDataList = trainingDataList;
	}

	public TrainingData getTrainingData() {
		return trainingData;
	}

	public void setTrainingData(TrainingData trainingData) {
		this.trainingData = trainingData;
	}

	public ITrainingDataService getiTrainingDataService() {
		return iTrainingDataService;
	}

	public void setiTrainingDataService(ITrainingDataService iTrainingDataService) {
		this.iTrainingDataService = iTrainingDataService;
	}

	@Override
	public String goList() {
		try {
			trainingDataList = trainingDataController.findTrainingDataIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 状态 */
			String datumType = getRequestParameter("datumType");
			if (null != datumType && !"".equals(datumType)) {
				params.put("datumType," + SearchCondition.ANYLIKE, datumType);
			}
			/* 按最近使用 */
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = trainingDataController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取培训资料搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 资料名称
			String datumName = getRequestParameter("datumName");
			if (null != datumName && !"".equals(datumName)) {
				datumName = URLDecoder.decode(datumName, "utf-8");
			}
			// 资料编码
			String datumCode = getRequestParameter("datumCode");
			if (null != datumCode && !"".equals(datumCode)) {
				datumCode = URLDecoder.decode(datumCode, "utf-8");
			}
			// 资料费用
			String datumCost = getRequestParameter("datumCost");
			if (null != datumCost && !"".equals(datumCost)) {
				datumCost = URLDecoder.decode(datumCost, "utf-8");
			}
			// 存放名称
			String storageLocation = getRequestParameter("storageLocation");
			if (null != storageLocation && !"".equals(storageLocation)) {
				storageLocation = URLDecoder.decode(storageLocation, "utf-8");
			}
			// 资料作者
			String datumauthor = getRequestParameter("datumauthor");
			if (null != datumauthor && !"".equals(datumauthor)) {
				datumauthor = URLDecoder.decode(datumauthor, "utf-8");
			}
			// 出版社
			String publishingUnit = getRequestParameter("publishingUnit");
			if (null != publishingUnit && !"".equals(publishingUnit)) {
				publishingUnit = URLDecoder.decode(publishingUnit, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("datumName," + SearchCondition.ANYLIKE, datumName);
				pager = trainingDataController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != datumName && !"".equals(datumName)) {
					params.put("datumName," + SearchCondition.ANYLIKE, datumName);
				}
				if (null != datumCode && !"".equals(datumCode)) {
					params.put("datumCode," + SearchCondition.ANYLIKE, datumCode);
				}
				if (null != datumCost && !"".equals(datumCost)) {
					params.put("datumCost," + SearchCondition.ANYLIKE, datumCost);
				}
				if (null != storageLocation && !"".equals(storageLocation)) {
					params.put("storageLocation," + SearchCondition.ANYLIKE, storageLocation);
				}
				if (null != datumauthor && !"".equals(datumauthor)) {
					params.put("datumauthor," + SearchCondition.ANYLIKE, datumauthor);
				}
				if (null != publishingUnit && !"".equals(publishingUnit)) {
					params.put("publishingUnit," + SearchCondition.ANYLIKE, publishingUnit);
				}
				pager = trainingDataController.goSingleList(params, getPager());
			}
			logger.info("获取培训资料搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				trainingData = trainingDataController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改/保存操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainingData.getId()) && !"0".equals(trainingData.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = trainingData.getDatumName();
			String py = ChnToPinYin.getPYString(title);
			trainingData.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(trainingData);
			trainingData = trainingDataController.doSaveTrainingData(trainingData);

			this.trainingData.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.trainingData.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			trainingData.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.trainingData);
			logger.info("对培训资料进行了修改！");
			if (isSave) {
				setMessage(SAVE_SUCCESS);
			} else {
				setMessage(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage(SAVE_FAIL);
			} else {
				this.setMessage(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			TrainingData trainingData = trainingDataController.doListEntityById(id);
			if (null != trainingData) {
				trainingDataController.doDeleteByEntity(trainingData);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除培训资料");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = iTrainingDataService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iTrainingDataService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
