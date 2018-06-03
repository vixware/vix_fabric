package com.vix.system.action.expand;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;

@Controller
@Scope("prototype")
public class SpecificationDetailAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private SpecificationDetail specificationDetail;
	private List<SpecificationDetail> specificationDetailList;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				specificationDetailList = baseHibernateService.findAllByEntityClassAndAttribute(SpecificationDetail.class, "specification.id", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至维度修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				specificationDetail = baseHibernateService.findEntityById(SpecificationDetail.class, id);
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
			if (null != specificationDetail.getId()) {
				isSave = false;
			}
			if (null == specificationDetail.getCode() || "".equals(specificationDetail.getCode())) {
				SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
				specificationDetail.setCode("s_v_" + sdf.format(new Date()));
			}
			specificationDetail = baseHibernateService.merge(specificationDetail);
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
			SpecificationDetail sd = baseHibernateService.findEntityById(SpecificationDetail.class, id);
			if (null != sd) {
				baseHibernateService.deleteByAttribute(SpecificationDetail.class, "specification.id", sd.getId());
				baseHibernateService.deleteByEntity(sd);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_specificationDetailNotExist"));
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

	public SpecificationDetail getSpecificationDetail() {
		return specificationDetail;
	}

	public void setSpecificationDetail(SpecificationDetail specificationDetail) {
		this.specificationDetail = specificationDetail;
	}

	public List<SpecificationDetail> getSpecificationDetailList() {
		return specificationDetailList;
	}

	public void setSpecificationDetailList(List<SpecificationDetail> specificationDetailList) {
		this.specificationDetailList = specificationDetailList;
	}
}
