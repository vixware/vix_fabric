package com.vix.sales.order.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.sales.order.entity.SalesTicket;
import com.vix.sales.order.entity.SalesTicketDetail;

@Controller
@Scope("prototype")
public class SalesTicketAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private SalesTicket salesTicket;
	private SalesTicketDetail salesTicketDetail;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String subject = getRequestParameter("subject");
			if (StrUtils.objectIsNotNull(subject)) {
				subject = decode(subject, "UTF-8");
				params.put("subject," + SearchCondition.ANYLIKE, subject);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), SalesTicket.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				salesTicket = baseHibernateService.findEntityById(
						SalesTicket.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdateDetail() {
		try {
			if (null != id && !"".equals(id)) {
				salesTicketDetail = baseHibernateService.findEntityById(
						SalesTicketDetail.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateDetail";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != salesTicket.getId()) {
				isSave = false;
			} else {
				salesTicket.setCreateTime(new Date());
				loadCommonData(salesTicket);
			}
			salesTicket = baseHibernateService.merge(salesTicket);
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

	/** 处理修改操作 */
	public String saveOrUpdateDetail() {
		boolean isSave = true;
		try {
			if (null != salesTicketDetail.getId()) {
				isSave = false;
			}
			salesTicketDetail = baseHibernateService.merge(salesTicketDetail);
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
			SalesTicket pb = baseHibernateService.findEntityById(
					SalesTicket.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
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

	/** 处理删除操作 */
	public String deleteDetailById() {
		try {
			SalesTicketDetail pb = baseHibernateService.findEntityById(
					SalesTicketDetail.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
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

	public void getSalesTicketDetailJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				SalesTicket sdp = baseHibernateService.findEntityById(
						SalesTicket.class, id);
				if (null != sdp) {
					json = convertListToJson(new ArrayList<SalesTicketDetail>(
							sdp.getSalesTicketDetails()), sdp
							.getSalesTicketDetails().size(), "salesTicket");
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

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
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

	public SalesTicket getSalesTicket() {
		return salesTicket;
	}

	public void setSalesTicket(SalesTicket salesTicket) {
		this.salesTicket = salesTicket;
	}

	public SalesTicketDetail getSalesTicketDetail() {
		return salesTicketDetail;
	}

	public void setSalesTicketDetail(SalesTicketDetail salesTicketDetail) {
		this.salesTicketDetail = salesTicketDetail;
	}
}
