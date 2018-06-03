package com.vix.common.base.controller;

import java.lang.reflect.Field;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * com.vix.common.base.controller.InitEntityBaseController
 * 
 * @author zhanghaibing
 * 
 * @date 2014-7-2
 */
@Controller("initEntityBaseController")
@Scope("prototype")
public class InitEntityBaseController {
	
	@Autowired
	public IBaseHibernateService baseHibernateService;

	/** 获取职员姓名 */
	public Employee getEmployee() {
		Employee emp = null;
		try {
			String empId = SecurityUtil.getCurrentEmpId();
			if (empId != null) {
				emp = baseHibernateService.findEntityById(Employee.class, empId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return emp;
	}

	/**
	 * 初始化基础数据
	 * 
	 * @param baseEntity
	 * @throws Exception
	 */
	public void initEntityBaseAttribute(BaseEntity baseEntity) throws Exception {

		String objCc = SecurityUtil.getCurrentUserAccountCompanyCode();
		String ti = SecurityUtil.getCurrentUserTenantId();
		String companyInnerCode = SecurityUtil.getCurrentUserAccountCompanyInnerCode();
		if (null != baseEntity) {
			Field[] fs = baseEntity.getClass().getSuperclass().getDeclaredFields();
			for (Field f : fs) {
				if (f.getName().equals("tenantId")) {
					f.setAccessible(true);
					if (null != ti && !"".equals(ti)) {
						f.set(baseEntity, ti);
					} else {
						String tenantId = PropertyConfigLoader.system_tenantId;
						if (null != tenantId && !"".equals(tenantId)) {
							f.set(baseEntity, tenantId);
						} else {
							f.set(baseEntity, "1001");
						}
					}
				}
				/** 载入公司编码 */
				if (f.getName().equals("companyCode")) {
					f.setAccessible(true);
					if (null != objCc && !"".equals(objCc)) {
						f.set(baseEntity, objCc);
					} else {
						String companyCode = PropertyConfigLoader.system_companyCode;
						if (null != companyCode && !"".equals(companyCode)) {
							f.set(baseEntity, companyCode);
						} else {
							f.set(baseEntity, "1001");
						}
					}
				}
				/** 载入数据创建人 */
				if (f.getName().equals("creator")) {
					Employee emp = getEmployee();
					if (null != emp) {
						f.setAccessible(true);
						f.set(baseEntity, emp.getName().toString());
					}
				}
				/** 载入创建时间 */
				if (f.getName().equals("createTime")) {
					f.setAccessible(true);
					f.set(baseEntity, new Date());
				}
				/** 载入更新时间 */
				if (f.getName().equals("updateTime")) {
					f.setAccessible(true);
					f.set(baseEntity, new Date());
				}
				/** 载入公司内部编码 */
				if (f.getName().equals("companyInnerCode")) {
					f.setAccessible(true);
					if (null != companyInnerCode && !"".equals(companyInnerCode)) {
						f.set(baseEntity, companyInnerCode);
					} else {
						f.set(baseEntity, "1001");
					}
				}
			}
		}
	}
}
