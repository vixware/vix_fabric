package com.vix.drp.mediaDeliveryPlan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.mediaDeliveryPlan.controller.MediaDeliveryPlanController;
import com.vix.drp.mediaDeliveryPlan.entity.MediaPlan;
import com.vix.drp.mediaDeliveryPlan.entity.NewsPaperMedia;
import com.vix.drp.mediaDeliveryPlan.entity.RadioMedia;
import com.vix.drp.mediaDeliveryPlan.entity.TelevisionMedia;

/**
 * 媒体组合投放计划
 * 
 * @author zhanghaibing
 * 
 * @date 2014-2-20
 */
@Controller
@Scope("prototype")
public class MediaDeliveryPlanAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MediaDeliveryPlanController mediaDeliveryPlanController;
	private String id;
	/**
	 * 媒体计划
	 */
	private MediaPlan mediaPlan;
	private List<MediaPlan> mediaPlanList;
	/**
	 * 电视
	 */
	private TelevisionMedia televisionMedia;
	/**
	 * 报纸
	 */
	private NewsPaperMedia newsPaperMedia;
	/**
	 * 电台
	 */
	private RadioMedia radioMedia;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = mediaDeliveryPlanController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				mediaPlan = mediaDeliveryPlanController.doListMediaPlanById(id);
			} else {
				mediaPlan = new MediaPlan();
				mediaPlan.setIsTemp("1");
				mediaPlan = mediaDeliveryPlanController.doSaveMediaPlan(mediaPlan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 电视
	 * 
	 * @return
	 */
	public String goSaveOrUpdateTelevisionMedia() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				televisionMedia = mediaDeliveryPlanController.doListTelevisionMediaById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTelevisionMedia";
	}

	/**
	 * 报纸
	 * 
	 * @return
	 */
	public String goSaveOrUpdateNewsPaperMedia() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				newsPaperMedia = mediaDeliveryPlanController.doListNewsPaperMediaById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateNewsPaperMedia";
	}

	/**
	 * 电台
	 * 
	 * @return
	 */
	public String goSaveOrUpdateRadioMedia() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				radioMedia = mediaDeliveryPlanController.doListRadioMediaById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateRadioMedia";
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != mediaPlan.getId() && !"".equals(mediaPlan.getId())) {
				isSave = false;
			}
			mediaPlan.setIsTemp("");
			mediaPlan = mediaDeliveryPlanController.doSaveMediaPlan(mediaPlan);
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
	 * 保存电视
	 * 
	 * @return
	 */
	public String saveOrUpdateTelevisionMedia() {
		boolean isSave = true;
		try {
			if (null != televisionMedia.getId() && !"".equals(televisionMedia.getId())) {
				isSave = false;
			}
			mediaPlan = mediaDeliveryPlanController.doListMediaPlanById(id);
			televisionMedia.setMediaPlan(mediaPlan);
			televisionMedia = mediaDeliveryPlanController.doSaveTelevisionMedia(televisionMedia);
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
	 * 保存报纸
	 * 
	 * @return
	 */
	public String saveOrUpdateNewsPaperMedia() {
		boolean isSave = true;
		try {
			if (null != newsPaperMedia.getId() && !"".equals(newsPaperMedia.getId())) {
				isSave = false;
			}
			mediaPlan = mediaDeliveryPlanController.doListMediaPlanById(id);
			newsPaperMedia.setMediaPlan(mediaPlan);
			newsPaperMedia = mediaDeliveryPlanController.doSaveNewsPaperMedia(newsPaperMedia);
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
	 * 保存电台
	 * 
	 * @return
	 */
	public String saveOrUpdateRadioMedia() {
		boolean isSave = true;
		try {
			if (null != radioMedia.getId() && !"".equals(radioMedia.getId())) {
				isSave = false;
			}
			mediaPlan = mediaDeliveryPlanController.doListMediaPlanById(id);
			radioMedia.setMediaPlan(mediaPlan);
			radioMedia = mediaDeliveryPlanController.doSaveRadioMedia(radioMedia);
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
	 * 获取电视
	 */
	public void getTelevisionMediaJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				MediaPlan mediaPlan = mediaDeliveryPlanController.doListMediaPlanById(id);
				if (mediaPlan != null) {
					json = convertListToJson(new ArrayList<TelevisionMedia>(mediaPlan.getTelevisionMedias()), mediaPlan.getTelevisionMedias().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取报纸
	 */
	public void getNewsPaperMediaJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				MediaPlan mediaPlan = mediaDeliveryPlanController.doListMediaPlanById(id);
				if (mediaPlan != null) {
					json = convertListToJson(new ArrayList<NewsPaperMedia>(mediaPlan.getNewsPaperMedias()), mediaPlan.getNewsPaperMedias().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取电台
	 */
	public void getRadioMediaJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				MediaPlan mediaPlan = mediaDeliveryPlanController.doListMediaPlanById(id);
				if (mediaPlan != null) {
					json = convertListToJson(new ArrayList<RadioMedia>(mediaPlan.getRadioMedias()), mediaPlan.getRadioMedias().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
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

	public MediaPlan getMediaPlan() {
		return mediaPlan;
	}

	public void setMediaPlan(MediaPlan mediaPlan) {
		this.mediaPlan = mediaPlan;
	}

	public List<MediaPlan> getMediaPlanList() {
		return mediaPlanList;
	}

	public void setMediaPlanList(List<MediaPlan> mediaPlanList) {
		this.mediaPlanList = mediaPlanList;
	}

	public TelevisionMedia getTelevisionMedia() {
		return televisionMedia;
	}

	public void setTelevisionMedia(TelevisionMedia televisionMedia) {
		this.televisionMedia = televisionMedia;
	}

	public NewsPaperMedia getNewsPaperMedia() {
		return newsPaperMedia;
	}

	public void setNewsPaperMedia(NewsPaperMedia newsPaperMedia) {
		this.newsPaperMedia = newsPaperMedia;
	}

	public RadioMedia getRadioMedia() {
		return radioMedia;
	}

	public void setRadioMedia(RadioMedia radioMedia) {
		this.radioMedia = radioMedia;
	}

}
