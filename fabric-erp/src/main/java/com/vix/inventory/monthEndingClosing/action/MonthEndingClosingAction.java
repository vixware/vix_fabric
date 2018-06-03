package com.vix.inventory.monthEndingClosing.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.transfer.controller.TransferController;
import com.vix.inventory.transfer.entity.WimTransvouch;
import com.vix.inventory.transfer.entity.WimTransvouchitem;

/**
 * 月末结账
 * 
 * @author zhanghaibing
 * 
 * @date 2013-12-11
 */
@Controller
@Scope("prototype")
public class MonthEndingClosingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(MonthEndingClosingAction.class);
	@Autowired
	private TransferController transferController;
	private String id;
	private String ids;
	private String pageNo;
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

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			wimTransvouchList = transferController.doListWimTransvouchList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = transferController.doListTransvouch(params, getPager());
			setPager(pager);
			logger.info("获取服务订单列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	// 搜索
	public String goSearchTransferList() {
		try {
			Map<String, Object> params = getParams();
			String tag = getRequestParameter("tag");
			String content = getRequestParameter("content");
			Pager pager = null;
			if ("0".equals(tag)) {
				// 简单搜索
				params.put("tvcode," + SearchCondition.ANYLIKE, content);
				params.put("outwarehousecode," + SearchCondition.ANYLIKE, content);
				params.put("inwarehousecode," + SearchCondition.ANYLIKE, content);
				pager = transferController.doListTransvouchByCon(params, getPager());
			} else {
				// 高级搜索
				// 入库单号
				String code1 = getRequestParameter("code1");
				// 调出仓库编码
				String outwarehousecodes = getRequestParameter("outwarehousecodes");
				// 调入仓库编码
				String inwarehousecodes = getRequestParameter("inwarehousecodes");
				if (code1 != null && !"".equals(code1)) {
					params.put("tvcode," + SearchCondition.ANYLIKE, code1);
				}
				if (outwarehousecodes != null && !"".equals(outwarehousecodes)) {
					params.put("outwarehousecode," + SearchCondition.ANYLIKE, outwarehousecodes);
				}
				if (inwarehousecodes != null && !"".equals(inwarehousecodes)) {
					params.put("inwarehousecode," + SearchCondition.ANYLIKE, inwarehousecodes);
				}
				pager = transferController.doListTransvouch(params, getPager());
			}
			setPager(pager);
			logger.info("获取服务订单列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至新增修改调拨单 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimTransvouch = transferController.doListWimTransvouchById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至新增修改调拨申请单 */
	public String goSaveOrUpdateAllocateandTransfer() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimTransvouch = transferController.doListWimTransvouchById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateAllocateandTransfer";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(wimTransvouch.getId()) && !"0".equals(wimTransvouch.getId())) {
				isSave = false;
			}
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
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			WimTransvouch pb = transferController.doListWimTransvouchById(id);
			if (null != pb) {
				transferController.doDeleteByEntity(pb);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */

	public void getWimTransvouchItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				WimTransvouch so = transferController.doListWimTransvouchById(id);
				if (so != null) {
					json = convertListToJson(new ArrayList<WimTransvouchitem>(so.getWimTransvouchitem()), so.getWimTransvouchitem().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加入库单明细
	 * 
	 * @return
	 */
	public String saveOrUpdateWimTransvouchitem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimTransvouch = transferController.doListWimTransvouchById(id);
				wimTransvouchitem.setWimTransvouch(wimTransvouch);
				transferController.doSaveWimTransvouchitem(wimTransvouchitem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	/** 获取json数据 */
	public void getWimTransvouchitemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				WimTransvouch wimTransvouch = transferController.doListWimTransvouchById(id);
				if (null != wimTransvouch) {
					json = convertListToJson(new ArrayList<WimTransvouchitem>(wimTransvouch.getWimTransvouchitem()), wimTransvouch.getWimTransvouchitem().size(), "wimTransvouch");
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

	/** 处理删除操作 */
	public String deleteWimStockrecordlinesById() {
		try {
			WimTransvouchitem wimTransvouchitem = transferController.doListWimTransvouchitemById(id);
			if (null != wimTransvouchitem) {
				transferController.deleteWimTransvouchitemEntity(wimTransvouchitem);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

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

	public WimTransvouch getWimTransvouch() {
		return wimTransvouch;
	}

	public void setWimTransvouch(WimTransvouch wimTransvouch) {
		this.wimTransvouch = wimTransvouch;
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

}
