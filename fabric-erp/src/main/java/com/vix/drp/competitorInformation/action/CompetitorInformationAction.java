package com.vix.drp.competitorInformation.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.drp.competitorInformation.controller.CompetitorInformationController;
import com.vix.drp.competitorInformation.entity.CompetingChannelInfo;

@Controller
@Scope("prototype")
public class CompetitorInformationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String ids;
	private String pageNo;
	@Autowired
	private CompetitorInformationController competitorInformationController;
	/**
	 * 竞争者/竞争渠道信息
	 */
	private CompetingChannelInfo competingChannelInfo;

	private List<CompetingChannelInfo> competingChannelInfoList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			competingChannelInfoList = competitorInformationController.doListCompetingChannelInfoList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager = competitorInformationController.doListStoreInfomation(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				competingChannelInfo = competitorInformationController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != competingChannelInfo.getId() && !"".equals(competingChannelInfo.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(competingChannelInfo);
			//处理修改留痕
			billMarkProcessController.processMark(competingChannelInfo, updateField);
			competitorInformationController.doSaveStoreInfomation(competingChannelInfo);
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
			CompetingChannelInfo cd = competitorInformationController.doListEntityById(id);
			if (null != cd) {
				competitorInformationController.doDeleteByEntity(cd);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				competitorInformationController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public CompetingChannelInfo getCompetingChannelInfo() {
		return competingChannelInfo;
	}

	public void setCompetingChannelInfo(CompetingChannelInfo competingChannelInfo) {
		this.competingChannelInfo = competingChannelInfo;
	}

	public List<CompetingChannelInfo> getCompetingChannelInfoList() {
		return competingChannelInfoList;
	}

	public void setCompetingChannelInfoList(List<CompetingChannelInfo> competingChannelInfoList) {
		this.competingChannelInfoList = competingChannelInfoList;
	}

}
