package com.vix.eam.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.eam.common.action.EamBaseAction;
import com.vix.eam.entity.Equipment;

@Controller
@Scope("prototype")
public class EamEquipmentAction extends EamBaseAction {

	public static final String GO_LIST_CONTENT = "goListContent";
	/** 列表数据页 */
	private static final long serialVersionUID = 1L;

	/** 实体对象 */
	private Equipment equipment;
	/** 设备名称 */
	private String eqname;
	/** 设备列表 */
	private List<Equipment> equipmentList = new ArrayList<Equipment>();


	/**
	 * @Title goList
	 * @Description 设备清单展示
	 * @param
	 * @exception
	 */
	@Override
	public String goList() {
		try {
			equipmentList = baseHibernateService
					.findAllByEntityClass(Equipment.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * @throws Exception
	 * @Description 获取列表清单
	 */
	public String goListContent() throws Exception {
		Map<String, Object> params = getParams();
		if (categoryId != null && !"".equals(categoryId)) {
			params.put("categoryId", categoryId);
		}
		// Pager pager=baseHibernateService.findEquipmentPagerByHql(getPager(),
		// params);
		// setPager(pager);
		return GO_LIST_CONTENT;
	}

	/**
	 * @throws Exception
	 * @Title goSaveOrUpdate
	 * @Description 进入新增或修改页面
	 * @param
	 */
	public String goSaveOrUpdate() throws Exception {
		if (id != null && !"".equals(id)) {
			equipment = baseHibernateService.findEntityById(Equipment.class, id);
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * @throws Exception
	 * @Description 删除记录
	 */
	public String deleteById() throws Exception {
		if (id != null && !"".equals(id)) {
			baseHibernateService.deleteById(Equipment.class, id);
		}

		return UPDATE;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public String getEqname() {
		return eqname;
	}

	public void setEqname(String eqname) {
		this.eqname = eqname;
	}

}
