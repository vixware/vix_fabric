package com.vix.oa.claimsmanagement.vehicle.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.claimsmanagement.vehicle.entity.Transport;

@Controller
@Scope("prototype")
public class VehicleAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private Transport transport;

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), Transport.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				transport = baseHibernateService.findEntityById(Transport.class, id);
			} else {
				transport = new Transport();
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
			if (null != transport.getId()) {
				isSave = false;
			}
			transport = baseHibernateService.merge(transport);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

}
