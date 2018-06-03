package com.vix.hr.trainning.action;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrCategory;
import com.vix.hr.trainning.controller.TrainingFacilitiesController;
import com.vix.hr.trainning.entity.TrainingFacilities;
import com.vix.hr.trainning.service.ITrainingFacilitiesService;

/**
 * @Description: 培训设施
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class TrainingFacilitiesAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(TrainingFacilities.class);

	/** 注入service */
	@Autowired
	private TrainingFacilitiesController trainingFacilitiesController;
	private List<TrainingFacilities> trainingFacilitieList;
	private TrainingFacilities trainingFacilities;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private ITrainingFacilitiesService iTrainingFacilitiesService;
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

	public List<TrainingFacilities> getTrainingFacilitieList() {
		return trainingFacilitieList;
	}

	public void setTrainingFacilitieList(List<TrainingFacilities> trainingFacilitieList) {
		this.trainingFacilitieList = trainingFacilitieList;
	}

	public TrainingFacilities getTrainingFacilities() {
		return trainingFacilities;
	}

	public void setTrainingFacilities(TrainingFacilities trainingFacilities) {
		this.trainingFacilities = trainingFacilities;
	}

	public ITrainingFacilitiesService getiTrainingFacilitiesService() {
		return iTrainingFacilitiesService;
	}

	public void setiTrainingFacilitiesService(ITrainingFacilitiesService iTrainingFacilitiesService) {
		this.iTrainingFacilitiesService = iTrainingFacilitiesService;
	}

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	@Override
	public String goList() {
		try {
			trainingFacilitieList = trainingFacilitiesController.findTrainingFacilitiesIndex();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = trainingFacilitiesController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取培训设施搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 设施编码
			String facilitiesCode = getRequestParameter("facilitiesCode");
			if (null != facilitiesCode && !"".equals(facilitiesCode)) {
				facilitiesCode = URLDecoder.decode(facilitiesCode, "utf-8");
			}
			// 设施名称
			String facilitiesName = getRequestParameter("facilitiesName");
			if (null != facilitiesName && !"".equals(facilitiesName)) {
				facilitiesName = URLDecoder.decode(facilitiesName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("facilitiesCode," + SearchCondition.ANYLIKE, facilitiesCode);
				pager = trainingFacilitiesController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != facilitiesCode && !"".equals(facilitiesCode)) {
					params.put("facilitiesCode," + SearchCondition.ANYLIKE, facilitiesCode);
				}
				if (null != facilitiesName && !"".equals(facilitiesName)) {
					params.put("telephone," + SearchCondition.ANYLIKE, facilitiesName);
				}
				pager = trainingFacilitiesController.goSingleList(params, getPager());
			}
			logger.info("获取培训设施搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id) {
				trainingFacilities = trainingFacilitiesController.doListEntityById(id);
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
			if (null != trainingFacilities.getId()) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(trainingFacilities);
			trainingFacilities = trainingFacilitiesController.doSaveTrainingFacilities(trainingFacilities);
			logger.info("对订单进行了修改！");
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
			TrainingFacilities trainingFacilities = trainingFacilitiesController.doListEntityById(id);
			if (null != trainingFacilities) {
				trainingFacilitiesController.doDeleteByEntity(trainingFacilities);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除培训设施");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<HrCategory> listCategory = new ArrayList<HrCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iTrainingFacilitiesService.findAllSubEntity(HrCategory.class, "parentCategory.id", id, params);
			} else {
				listCategory = iTrainingFacilitiesService.findAllSubEntity(HrCategory.class, "parentCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				HrCategory cc = listCategory.get(i);
				if (cc.getSubCategorys().size() > 0) {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getName());
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
}
