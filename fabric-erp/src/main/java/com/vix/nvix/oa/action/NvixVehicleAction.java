package com.vix.nvix.oa.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.claimsmanagement.vehicle.entity.Transport;

@Controller
@Scope("prototype")
public class NvixVehicleAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private Transport transport;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Transport.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				transport = vixntBaseService.findEntityById(Transport.class, id);
			} else {
				transport = new Transport();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != transport.getId()) {
				isSave = false;
			}
			transport = vixntBaseService.merge(transport);
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
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Transport transport = vixntBaseService.findEntityById(Transport.class, id);
				if (null != transport) {
					vixntBaseService.deleteByEntity(transport);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}
}
