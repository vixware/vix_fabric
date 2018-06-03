package com.vix.nvix.system.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.HomeTemplateConstant;
import com.vix.core.constant.HomeTemplateDetailConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.entity.HomeTemplate;
import com.vix.nvix.common.base.entity.HomeTemplateDetail;

/**
 * 工作台模板管理
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.system.action.NvixntTemplateAction
 *
 * @date 2018年1月8日
 */
@Controller
@Scope("prototype")
public class NvixntTemplateAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private String homeTemplateId;
	private HomeTemplate homeTemplate;
	private HomeTemplateDetail homeTemplateDetail;
	private Map<String, String> homeTemplateMap = new HashMap<String, String>();
	private Map<String, String> homeTemplateDetailMap = new HashMap<String, String>();
	public void goSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			pager = vixntBaseService.findPagerByHqlConditions(pager, HomeTemplate.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate() {
		try {
			homeTemplateMap = HomeTemplateConstant.getHomeTemplateMap();
			if (StringUtils.isNotEmpty(id)) {
				homeTemplate = vixntBaseService.findEntityByAttribute(HomeTemplate.class, "id", id);
			} else {
				homeTemplate = new HomeTemplate();
				homeTemplate.setIsTemp("1");
				homeTemplate.setStatus("1");
				homeTemplate = vixntBaseService.merge(homeTemplate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != homeTemplate.getId()) {
				isSave = false;
			}
			homeTemplate.setIsTemp("");
			homeTemplate = vixntBaseService.merge(homeTemplate);
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
	}
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				HomeTemplate homeTemplate = vixntBaseService.findEntityByAttribute(HomeTemplate.class, "id", id);
				if (null != homeTemplate) {
					vixntBaseService.deleteByEntity(homeTemplate);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void deleteHomeTemplateDetailById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				HomeTemplateDetail homeTemplateDetail = vixntBaseService.findEntityByAttribute(HomeTemplateDetail.class, "id", id);
				if (null != homeTemplateDetail) {
					vixntBaseService.deleteByEntity(homeTemplateDetail);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void goHomeTemplateDetailList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id)) {
				params.put("homeTemplate.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, HomeTemplateDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到新增栏目明细
	 * 
	 * @return
	 */
	public String goSaveOrUpdateTemplateDetail() {
		try {
			homeTemplateDetailMap = HomeTemplateDetailConstant.getHomeTemplateDetailMap();
			if (StringUtils.isNotEmpty(id)) {
				homeTemplateDetail = vixntBaseService.findEntityByAttribute(HomeTemplateDetail.class, "id", id);
			} else {
				homeTemplateDetail = new HomeTemplateDetail();
				homeTemplateDetail.setStatus("1");
				if (StringUtils.isNotEmpty(homeTemplateId)) {
					homeTemplate = vixntBaseService.findEntityById(HomeTemplate.class, homeTemplateId);
					if (homeTemplate != null) {
						homeTemplateDetail.setHomeTemplate(homeTemplate);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTemplateDetail";
	}
	/**
	 * 保存栏目明细
	 */
	public void saveOrUpdateTemplateDetail() {
		boolean isSave = true;
		try {
			if (null != homeTemplateDetail.getId()) {
				isSave = false;
			}
			homeTemplateDetail = vixntBaseService.merge(homeTemplateDetail);
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
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the homeTemplate
	 */
	public HomeTemplate getHomeTemplate() {
		return homeTemplate;
	}
	/**
	 * @param homeTemplate
	 *            the homeTemplate to set
	 */
	public void setHomeTemplate(HomeTemplate homeTemplate) {
		this.homeTemplate = homeTemplate;
	}

	/**
	 * @return the homeTemplateId
	 */
	public String getHomeTemplateId() {
		return homeTemplateId;
	}
	/**
	 * @param homeTemplateId
	 *            the homeTemplateId to set
	 */
	public void setHomeTemplateId(String homeTemplateId) {
		this.homeTemplateId = homeTemplateId;
	}
	/**
	 * @return the homeTemplateDetail
	 */
	public HomeTemplateDetail getHomeTemplateDetail() {
		return homeTemplateDetail;
	}
	/**
	 * @param homeTemplateDetail
	 *            the homeTemplateDetail to set
	 */
	public void setHomeTemplateDetail(HomeTemplateDetail homeTemplateDetail) {
		this.homeTemplateDetail = homeTemplateDetail;
	}
	/**
	 * @return the homeTemplateMap
	 */
	public Map<String, String> getHomeTemplateMap() {
		return homeTemplateMap;
	}
	/**
	 * @param homeTemplateMap
	 *            the homeTemplateMap to set
	 */
	public void setHomeTemplateMap(Map<String, String> homeTemplateMap) {
		this.homeTemplateMap = homeTemplateMap;
	}
	/**
	 * @return the homeTemplateDetailMap
	 */
	public Map<String, String> getHomeTemplateDetailMap() {
		return homeTemplateDetailMap;
	}
	/**
	 * @param homeTemplateDetailMap
	 *            the homeTemplateDetailMap to set
	 */
	public void setHomeTemplateDetailMap(Map<String, String> homeTemplateDetailMap) {
		this.homeTemplateDetailMap = homeTemplateDetailMap;
	}
}