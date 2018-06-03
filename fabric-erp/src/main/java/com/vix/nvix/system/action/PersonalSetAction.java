package com.vix.nvix.system.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.encode.Md5Encoder;
import com.vix.core.encode.Md5EncoderImpl;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class PersonalSetAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private UserAccount userAccount;
	private UserAccount account;
	private String id;
	private Employee emp;
	private Employee e;

	public String goSaveOrUpdate() {
		try {
			emp = getEmployee();
			userAccount = SecurityUtil.getCurrentUserAccount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		if (StringUtils.isNotEmpty(emp.getId())) {
			try {
				e = vixntBaseService.findEntityById(Employee.class, emp.getId());
				if (e != null) {
					e.setGender(emp.getGender());
					e.setWeixinid(emp.getWeixinid());
					e.setMobile(emp.getMobile());
					e.setEmail(emp.getEmail());
					e = vixntBaseService.merge(e);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderText(SAVE_FAIL);
			}
		}
		if (StringUtils.isNotEmpty(userAccount.getId())) {
			try {
				account = vixntBaseService.findEntityById(UserAccount.class, userAccount.getId());
				if (account != null) {
					//加密
					Md5Encoder md = new Md5EncoderImpl();
					account.setInitpassword(userAccount.getPassword());
					account.setPassword(md.encodeString(userAccount.getPassword()));
					account = vixntBaseService.merge(account);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderText(SAVE_FAIL);
			}
		}
		renderText(SAVE_SUCCESS);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
