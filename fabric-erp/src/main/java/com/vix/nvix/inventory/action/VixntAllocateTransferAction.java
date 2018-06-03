package com.vix.nvix.inventory.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.transfer.controller.TransferController;
import com.vix.inventory.transfer.entity.WimTransvouch;
import com.vix.inventory.transfer.entity.WimTransvouchitem;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 调拨管理
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.inventory.action.VixntAllocateTransferAction
 *
 * @date 2017年12月27日
 */
@Controller
@Scope("prototype")
public class VixntAllocateTransferAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private TransferController transferController;
	private String id;
	private String ids;
	private String parentId;
	private String wimTransvouchId;
	private String wimTransvouchitemNames;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");
	/**
	 * 调拨单
	 */
	private WimTransvouch wimTransvouch;
	private List<WimTransvouch> wimTransvouchList;

	/**
	 * 调拨单明细
	 */
	private WimTransvouchitem wimTransvouchitem;
	private List<WimTransvouchitem> wimTransvouchitemList;
	/**
	 * 计量单位
	 */
	private List<MeasureUnit> measureUnitList;
	/**
	 * 
	 */
	private InvWarehouse outInvWarehouse;
	private InvWarehouse inInvWarehouse;
	private InventoryCurrentStock inventoryCurrentStock;
	/**
	 * 跳转添加调拨明细
	 * 
	 * @return
	 */
	public String goSaveOrUpdateTransferItem() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				wimTransvouchitem = vixntBaseService.findEntityById(WimTransvouchitem.class, id);
			} else {
				wimTransvouchitem = new WimTransvouchitem();
				if (StringUtils.isNotEmpty(wimTransvouchId)) {
					wimTransvouch = vixntBaseService.findEntityById(WimTransvouch.class, wimTransvouchId);
					if (wimTransvouch != null) {
						wimTransvouchitem.setWimTransvouch(wimTransvouch);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTransferItem";
	}
	/**
	 * 添加调拨明细
	 * 
	 * @return
	 */
	public void saveOrUpdateWimTransvouchitem() {
		boolean isSave = true;
		try {
			if (wimTransvouchitem != null) {
				if (StringUtils.isNotEmpty(wimTransvouchitem.getId())) {
					isSave = false;
				}
				if (wimTransvouchitem.getInvShelf() != null && StringUtils.isNotEmpty(wimTransvouchitem.getInvShelf().getId())) {
				} else {
					wimTransvouchitem.setInvShelf(null);
				}
				if (wimTransvouchitem.getInvWarehouse() != null && StringUtils.isNotEmpty(wimTransvouchitem.getInvWarehouse().getId())) {
				} else {
					wimTransvouchitem.setInvWarehouse(null);
				}
				if (wimTransvouchitem.getWimTransvouch() != null && StringUtils.isNotEmpty(wimTransvouchitem.getWimTransvouch().getId())) {
				} else {
					wimTransvouchitem.setWimTransvouch(null);
				}
				if (wimTransvouchitem.getMeasureUnit() != null && StringUtils.isNotEmpty(wimTransvouchitem.getMeasureUnit().getId())) {
				} else {
					wimTransvouchitem.setMeasureUnit(null);
				}
				wimTransvouchitem.setIsUpdateStore("1");
				transferController.doSaveWimTransvouchitem(wimTransvouchitem);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	/**
	 * 获取调拨单列表
	 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			pager = vixntBaseService.findPagerByHqlConditions(pager, WimTransvouch.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				wimTransvouch = vixntBaseService.findEntityById(WimTransvouch.class, id);
			} else {
				wimTransvouch = new WimTransvouch();
				wimTransvouch.setIsTemp("1");
				wimTransvouch.setCode(VixUUID.createCode(15));
				wimTransvouch.setName("调拨单" + dateFormat.format(new Date()));
				wimTransvouch.setCreator(SecurityUtil.getCurrentUserName());
				initEntityBaseController.initEntityBaseAttribute(wimTransvouch);
			}
			wimTransvouch = vixntBaseService.merge(wimTransvouch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(wimTransvouch.getId())) {
				isSave = false;
			}
			wimTransvouch.setIsTemp("");
			// 处理中文索引
			String chineseCharacter = ChnToPinYin.getPYString(wimTransvouch.getName());
			wimTransvouch.setChineseCharacter(chineseCharacter);
			initEntityBaseController.initEntityBaseAttribute(wimTransvouch);
			wimTransvouch = transferController.doSaveWimTransvouch(wimTransvouch);
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

	/** 处理删除操作 */
	public void deleteWimTransvouchitemById() {
		try {
			wimTransvouchitem = vixntBaseService.findEntityById(WimTransvouchitem.class, id);
			if (null != wimTransvouchitem) {
				vixntBaseService.deleteByEntity(wimTransvouchitem);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void deleteById() {
		try {
			WimTransvouch pb = transferController.doListWimTransvouchById(id);
			if (null != pb) {
				transferController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/** 批量处理删除操作 */
	public void deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				transferController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 获取json数据 */
	public void getWimTransvouchItemJson() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(id)) {
				params.put("wimTransvouch.id," + SearchCondition.EQUAL, id);
				String searchName = getDecodeRequestParameter("searchName");
				if (StringUtils.isNotEmpty(searchName)) {
					params.put("itemname," + SearchCondition.ANYLIKE, searchName);
				}
				pager = vixntBaseService.findPagerByHqlConditions(pager, WimTransvouchitem.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteWimTransvouchItemById() {
		try {
			WimTransvouchitem wimTransvouchitem = transferController.doListWimTransvouchitemById(id);
			if (null != wimTransvouchitem) {
				transferController.deleteWimTransvouchitemEntity(wimTransvouchitem);
				renderText(DELETE_SUCCESS);
			} else {
				renderText(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/**
	 * 弹出库存信息
	 * 
	 * @return
	 */
	public String goInventoryList() {
		try {
			Map<String, Object> params = getParams();
			measureUnitList = transferController.doListMeasureUnitList(params);
			if (StringUtils.isNotEmpty(id)) {
				wimTransvouchitem = transferController.doListWimTransvouchitemById(id);
			} else {
				wimTransvouchitem = new WimTransvouchitem();
				if (StringUtils.isNotEmpty(parentId)) {
					WimTransvouch wimTransvouch = transferController.doListWimTransvouchById(parentId);
					wimTransvouchitem.setWimTransvouch(wimTransvouch);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goInventoryList";
	}

	public String goInventoryListContent() {
		try {
			Map<String, Object> params = getParams();
			// 倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("id");
				pager.setOrderBy("desc");
			}
			pager.setPageSize(6);
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isQualfied," + SearchCondition.EQUAL, 1);
			transferController.doListInventory(params, pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryListContent";
	}

	public String goPrintAllocateTransfer() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimTransvouch = transferController.doListWimTransvouchById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintAllocateTransfer";
	}

	/**
	 * 查看调拨单
	 * 
	 * @return
	 */
	public String goShowAllocateTransfer() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimTransvouch = transferController.doListWimTransvouchById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowAllocateTransfer";
	}

	public String goSearch() {
		return "goSearch";
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public WimTransvouch getWimTransvouch() {
		return wimTransvouch;
	}

	public void setWimTransvouch(WimTransvouch wimTransvouch) {
		this.wimTransvouch = wimTransvouch;
	}

	/**
	 * @return the measureUnitList
	 */
	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	/**
	 * @param measureUnitList
	 *            the measureUnitList to set
	 */
	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}

	public List<WimTransvouch> getWimTransvouchList() {
		return wimTransvouchList;
	}

	public void setWimTransvouchList(List<WimTransvouch> wimTransvouchList) {
		this.wimTransvouchList = wimTransvouchList;
	}

	public WimTransvouchitem getWimTransvouchitem() {
		return wimTransvouchitem;
	}

	public void setWimTransvouchitem(WimTransvouchitem wimTransvouchitem) {
		this.wimTransvouchitem = wimTransvouchitem;
	}

	public List<WimTransvouchitem> getWimTransvouchitemList() {
		return wimTransvouchitemList;
	}

	public void setWimTransvouchitemList(List<WimTransvouchitem> wimTransvouchitemList) {
		this.wimTransvouchitemList = wimTransvouchitemList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getWimTransvouchitemNames() {
		return wimTransvouchitemNames;
	}

	public void setWimTransvouchitemNames(String wimTransvouchitemNames) {
		this.wimTransvouchitemNames = wimTransvouchitemNames;
	}
	public String getWimTransvouchId() {
		return wimTransvouchId;
	}
	public void setWimTransvouchId(String wimTransvouchId) {
		this.wimTransvouchId = wimTransvouchId;
	}
	public InvWarehouse getOutInvWarehouse() {
		return outInvWarehouse;
	}
	public void setOutInvWarehouse(InvWarehouse outInvWarehouse) {
		this.outInvWarehouse = outInvWarehouse;
	}
	public InvWarehouse getInInvWarehouse() {
		return inInvWarehouse;
	}
	public void setInInvWarehouse(InvWarehouse inInvWarehouse) {
		this.inInvWarehouse = inInvWarehouse;
	}
	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	public InventoryCurrentStock getInventoryCurrentStock() {
		return inventoryCurrentStock;
	}
	public void setInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) {
		this.inventoryCurrentStock = inventoryCurrentStock;
	}
}
