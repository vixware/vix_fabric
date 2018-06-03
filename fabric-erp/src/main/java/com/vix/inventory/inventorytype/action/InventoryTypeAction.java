package com.vix.inventory.inventorytype.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.inventory.inventorytype.entity.InventoryType;

@Controller
@Scope("prototype")
public class InventoryTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private Long id;
	private List<InventoryType> inventoryTypeList;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			inventoryTypeList = baseHibernateService.findAllByEntityClass(InventoryType.class);
			if (null != inventoryTypeList && inventoryTypeList.size() < 8) {
				int stepCount = 8 - inventoryTypeList.size();
				for (int i = 0; i < stepCount; i++) {
					inventoryTypeList.add(new InventoryType());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			String data = getRequestParameter("data");
			if (null != data && !"".equals(data)) {
				String[] cs = data.split(",");
				for (String s : cs) {
					String[] csItem = s.split(":");
					InventoryType inventoryType = new InventoryType();
					if (!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])) {
						inventoryType.setId(csItem[0]);
						isSave = false;
					}
					inventoryType.setIsDefault(csItem[1]);
					inventoryType.setEnable(csItem[2]);
					if (!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])) {
						inventoryType.setName(csItem[3]);
					}
					if (!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])) {
						inventoryType.setMemo(csItem[4]);
					}
					baseHibernateService.merge(inventoryType);
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
		return UPDATE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<InventoryType> getInventoryTypeList() {
		return inventoryTypeList;
	}

	public void setInventoryTypeList(List<InventoryType> inventoryTypeList) {
		this.inventoryTypeList = inventoryTypeList;
	}

}
