package com.vix.drp.newProductLaunchInformation.action;

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
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.newProductLaunchInformation.controller.ProductInformationController;
import com.vix.drp.newProductLaunchInformation.entity.ProductInformation;

@Controller
@Scope("prototype")
public class ProductInformationAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductInformationController productInformationController;
	private String id;
	private ChannelDistributor channelDistributor;
	private List<ChannelDistributor> channelDistributorList;

	private ProductInformation productInformation;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = productInformationController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelDistributor = productInformationController.doListChannelDistributorById(id);
			} else {
				channelDistributor = new ChannelDistributor();
				channelDistributor.setIsTemp("1");
				channelDistributor = productInformationController.doSaveChannelDistributor(channelDistributor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateProductInformation() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				productInformation = productInformationController.doListProductInformationById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateProductInformation";
	}

	/**
	 * 保存渠道
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != channelDistributor.getId() && !"".equals(channelDistributor.getId())) {
				isSave = false;
			}
			channelDistributor.setIsTemp("");
			channelDistributor = productInformationController.doSaveChannelDistributor(channelDistributor);
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
	 * 保存产品投放信息
	 * 
	 * @return
	 */
	public String saveOrUpdateProductInformation() {
		boolean isSave = true;
		try {
			if (null != productInformation.getId() && !"".equals(productInformation.getId())) {
				isSave = false;
			}
			channelDistributor = productInformationController.doListChannelDistributorById(id);
			productInformation.setChannelDistributor(channelDistributor);
			productInformation = productInformationController.doSaveChannelPrice(productInformation);
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
			ChannelDistributor channelDistributor = productInformationController.doListChannelDistributorById(id);
			if (null != channelDistributor) {
				productInformationController.doDeleteByEntity(channelDistributor);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteProductInformationById() {
		try {
			ProductInformation productInformation = productInformationController.doListProductInformationById(id);
			if (null != productInformation) {
				productInformationController.doDeleteProductInformationByEntity(productInformation);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getProductInformationJson() {
		try {

			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ChannelDistributor channelDistributor = productInformationController.doListChannelDistributorById(id);
				if (channelDistributor != null) {
					json = convertListToJson(new ArrayList<ProductInformation>(channelDistributor.getProductInformations()), channelDistributor.getProductInformations().size());
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

	public ProductInformation getProductInformation() {
		return productInformation;
	}

	public void setProductInformation(ProductInformation productInformation) {
		this.productInformation = productInformation;
	}

}
