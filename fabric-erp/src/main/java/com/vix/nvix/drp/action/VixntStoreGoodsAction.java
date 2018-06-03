package com.vix.nvix.drp.action;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.JSonUtils;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.controller.StandingBookController;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntStoreGoodsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 可用数量
	 */
	private Double avaquantity;
	@Autowired
	private StandingBookController standingBookController;

	/**
	 * 根据条件查询入库单信息
	 */
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String itemname = getDecodeRequestParameter("itemname");
			if (itemname != null && !"".equals(itemname)) {
				params.put("itemname," + SearchCondition.ANYLIKE, itemname.trim());
			}
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}

			if (StringUtils.isNotEmpty(selectId)) {
				params.put("itemCatalog.id," + SearchCondition.EQUAL, selectId);
			}
			params.put("isQualfied," + SearchCondition.EQUAL, 1);
			Employee employee = super.getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void importFile() {
		Map<String, String> msgMap = new HashMap<String, String>();
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
				msgMap.put("success", "0");
				msgMap.put("error", "没有选择文件!");
			} else {
				standingBookController.importShopEcProductPrice(fileToUpload, fileToUploadFileName);
				msgMap.put("success", "1");
				msgMap.put("msg", "导入成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msgMap.put("success", "0");
			msgMap.put("error", e.getMessage());
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
		String reMsg = JSonUtils.makeMapToJson(msgMap);
		renderHtml(reMsg);
	}

	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(id)) {
				InventoryCurrentStock inventoryCurrentStock = vixntBaseService.findEntityById(InventoryCurrentStock.class, id);
				if (inventoryCurrentStock != null) {
					if (avaquantity != null) {
						inventoryCurrentStock.setAvaquantity(avaquantity);
						vixntBaseService.merge(inventoryCurrentStock);
					}
				}
			}
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

	public Double getAvaquantity() {
		return avaquantity;
	}

	public void setAvaquantity(Double avaquantity) {
		this.avaquantity = avaquantity;
	}

}