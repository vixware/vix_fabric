package com.vix.drp.competitionchannel.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.competitionchannel.controller.CompetitionChannelController;

@Controller
@Scope("prototype")
public class CompetitionChannelAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private CompetitionChannelController competitionChannelController;

	private String id;
	private String ids;
	private String pageNo;
	private ChannelDistributor channelDistributor;

	private List<ChannelDistributor> channelDistributorList;
	private List<CurrencyType> currencyTypeList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤渠道
			params.put("type," + SearchCondition.ANYLIKE, "5");
			channelDistributorList = competitionChannelController.doListChannelDistributorList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type," + SearchCondition.ANYLIKE, "5");
			/* 高级查询 */
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String nameOr = getRequestParameter("nameOr");
			String person = getRequestParameter("person");
			if (null != nameOr && !"".equals(nameOr)) {
				params.put("name," + SearchCondition.ANYLIKE, nameOr);
			}
			if (null != person && !"".equals(person)) {
				params.put("artificialPerson," + SearchCondition.ANYLIKE, person);
			}
			pager = competitionChannelController.doListChannelDistributor(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			currencyTypeList = competitionChannelController.doListCurrencyTypeList(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelDistributor = competitionChannelController.doListEntityById(id);
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
			if (null != channelDistributor.getId()) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(channelDistributor);
			//处理修改留痕
			billMarkProcessController.processMark(channelDistributor, updateField);
			channelDistributor = competitionChannelController.doSaveOrUpdateChannelDistributor(channelDistributor);
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
			ChannelDistributor cd = competitionChannelController.doListEntityById(id);
			if (null != cd) {
				competitionChannelController.doDeleteByEntity(cd);
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
				competitionChannelController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
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

	/**
	 * @return the currencyTypeList
	 */
	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	/**
	 * @param currencyTypeList
	 *            the currencyTypeList to set
	 */
	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

}
