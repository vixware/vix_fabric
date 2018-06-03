package com.vix.nvix.fabric.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.fabric.entity.MockupUsers;

/**
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.fabric.action.NvixntMockupUsersAction
 *
 * @date 2018年3月11日
 */
@Controller
@Scope("prototype")
public class NvixntMockupUsersAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private MockupUsers mockupUsers;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, MockupUsers.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				mockupUsers = vixntBaseService.findEntityById(MockupUsers.class, id);
			} else {
				mockupUsers = new MockupUsers();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (mockupUsers != null && StringUtils.isNotEmpty(mockupUsers.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(mockupUsers);
			mockupUsers = vixntBaseService.merge(mockupUsers);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText("更新失败");
			}
		}
	}
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				mockupUsers = vixntBaseService.findEntityById(MockupUsers.class, id);
				if (null != mockupUsers) {
					vixntBaseService.deleteByEntity(mockupUsers);
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

	/**
	 * @return the mockupUsers
	 */
	public MockupUsers getMockupUsers() {
		return mockupUsers;
	}

	/**
	 * @param mockupUsers
	 *            the mockupUsers to set
	 */
	public void setMockupUsers(MockupUsers mockupUsers) {
		this.mockupUsers = mockupUsers;
	}

}
