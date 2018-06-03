package com.vix.inventory.transfer.action;

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
 * 调拨
 * @author 
 *
 */
@Controller
@Scope("prototype")
public class AllocateTransferAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private TransferController transferController;
	private String id;
	private String ids;
	private String parentId;
	private String wimTransvouchitemIds;
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
	 * @return
	 */
	public String goSaveOrUpdateWimTransvouchitem(){
		try {
			if(StringUtils.isNotEmpty(id)){
				wimTransvouchitem = vixntBaseService.findEntityById(WimTransvouchitem.class,id);
			}else{
				wimTransvouchitem = new WimTransvouchitem();
				if(StringUtils.isNotEmpty(wimTransvouchId)){
					wimTransvouch = vixntBaseService.findEntityById(WimTransvouch.class,wimTransvouchId);
					if(wimTransvouch!=null ){
						wimTransvouchitem.setWimTransvouch(wimTransvouch);	
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateWimTransvouchitem";
	}
	/**
	 * 添加调拨明细
	 * @return
	 */
	public String saveOrUpdateWimTransvouchitem(){
//		boolean isSave = true;
//		try {
//			if (StringUtils.isNotEmpty(wimTransvouchitem.getId())) {
//				isSave = false;
//			}
//			if (StringUtils.isNotEmpty(id)) {
//				wimTransvouch = vixntBaseService.findEntityById(WimTransvouch.class, id);
//				if (wimTransvouch != null) {
//					wimTransvouchitem.setWimTransvouch(wimTransvouch);
//					if (wimTransvouchitem.getMeasureUnit() == null || StringUtils.isEmpty(wimTransvouchitem.getMeasureUnit().getId())) {
//						wimTransvouchitem.setMeasureUnit(null);
//					}
//				}
//			}
//			initEntityBaseController.initEntityBaseAttribute(wimTransvouchitem);
//			wimTransvouchitem = vixntBaseService.merge(wimTransvouchitem);
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
				transferController.doSaveWimTransvouchitem(wimTransvouchitem);
			}
			if (isSave) {
				setMessage(SAVE_SUCCESS);
			} else {
				setMessage(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				this.setMessage(SAVE_FAIL);
			} else {
				this.setMessage(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}
	/**
	 * 获取调拨单列表
	 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("name,"+SearchCondition.ANYLIKE, searchName);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if(StringUtils.isNotEmpty(id)){
				params.put("wimTransvouch.id," + SearchCondition.EQUAL, id);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, WimTransvouch.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimTransvouch = vixntBaseService.findEntityById(WimTransvouch.class, id);
				if(wimTransvouch != null && wimTransvouch.getWimTransvouchitem() != null){
					wimTransvouchitemIds = "";
					wimTransvouchitemNames = "";
					for (WimTransvouchitem w : wimTransvouch.getWimTransvouchitem()) {
						wimTransvouchitemIds += ","+w.getId();
						wimTransvouchitemNames += ","+w.getName();
					}
				}
			} else {
				wimTransvouch = new WimTransvouch();
				wimTransvouch.setIsTemp("1");
				wimTransvouch.setCode(VixUUID.createCode(15));
				wimTransvouch.setName("调拨单"+dateFormat.format(new Date()));
				wimTransvouch.setCreator(SecurityUtil.getCurrentUserName());
			}
			initEntityBaseController.initEntityBaseAttribute(wimTransvouch);
			wimTransvouch = vixntBaseService.merge(wimTransvouch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public String goShow(){
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimTransvouch = vixntBaseService.findEntityById(WimTransvouch.class, id);
				if(wimTransvouch != null && wimTransvouch.getWimTransvouchitem() != null){
					wimTransvouchitemIds = "";
					wimTransvouchitemNames = "";
					for (WimTransvouchitem w : wimTransvouch.getWimTransvouchitem()) {
						wimTransvouchitemIds += ","+w.getId();
						wimTransvouchitemNames += ","+w.getName();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShow";
	}
	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(wimTransvouch.getId()) && !"0".equals(wimTransvouch.getId())) {
				if (wimTransvouch != null && wimTransvouch.getIsTemp() != null && !"1".equals(wimTransvouch.getIsTemp())) {
					isSave = false;
				}
			}
			wimTransvouch.setIsTemp("");
			//处理中文索引
			String chineseCharacter = ChnToPinYin.getPYString(wimTransvouch.getName());
			wimTransvouch.setChineseCharacter(chineseCharacter);
			initEntityBaseController.initEntityBaseAttribute(wimTransvouch);
			
			//ACTION保存前执行
			//billMarkProcessController.processMark(wimTransvouch, updateField);
			wimTransvouch = transferController.doSaveWimTransvouch(wimTransvouch);
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
	public String deleteWimTransvouchitemById() {
		try {
			wimTransvouchitem = vixntBaseService.findEntityById(WimTransvouchitem.class,id);
			if (null != wimTransvouchitem) {
				vixntBaseService.deleteByEntity(wimTransvouchitem);
				setMessage(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}
	public String deleteById() {
		try {
			WimTransvouch pb = transferController.doListWimTransvouchById(id);
			if (null != pb) {
				transferController.doDeleteByEntity(pb);
				setMessage(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
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
				transferController.doDeleteByIds(delIds);
				setMessage(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
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
	public String deleteWimTransvouchItemById() {
		try {
			WimTransvouchitem wimTransvouchitem = transferController.doListWimTransvouchitemById(id);
			if (null != wimTransvouchitem) {
				transferController.deleteWimTransvouchitemEntity(wimTransvouchitem);
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
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
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
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
			//倒序排序
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

	/**
	 * 获取上一条数据
	 * 
	 * @return
	 */
	public String goBeforeWimTransvouch() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				wimTransvouch = transferController.doListWimTransvouchById(id);
				if (wimTransvouch != null && wimTransvouch.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(wimTransvouch.getCreateTime()));
					// 过滤临时数据
					params.put("isTemp", "0");
					params.put("tenantId", wimTransvouch.getTenantId());
					params.put("companyInnerCode", wimTransvouch.getCompanyInnerCode());
					params.put("creator", wimTransvouch.getCreator());
					if (wimTransvouch == null || StringUtils.isEmpty(wimTransvouch.getId())) {
						wimTransvouch = transferController.doListWimTransvouchById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowAllocateTransfer";
	}

	/**
	 * 
	 * @return
	 */
	public String goAfterWimTransvouch() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				wimTransvouch = transferController.doListWimTransvouchById(id);
				if (wimTransvouch != null && wimTransvouch.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(wimTransvouch.getCreateTime()));
					params.put("isTemp", "0");
					params.put("tenantId", wimTransvouch.getTenantId());
					params.put("companyInnerCode", wimTransvouch.getCompanyInnerCode());
					params.put("creator", wimTransvouch.getCreator());
					if (wimTransvouch == null || StringUtils.isEmpty(wimTransvouch.getId())) {
						wimTransvouch = transferController.doListWimTransvouchById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowAllocateTransfer";
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

	public String getWimTransvouchitemIds() {
		return wimTransvouchitemIds;
	}

	public void setWimTransvouchitemIds(String wimTransvouchitemIds) {
		this.wimTransvouchitemIds = wimTransvouchitemIds;
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
