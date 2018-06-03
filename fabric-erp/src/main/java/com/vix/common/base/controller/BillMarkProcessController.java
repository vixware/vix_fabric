package com.vix.common.base.controller;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CMNObjectModifyRecord;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.inventory.inbound.entity.StockRecords;

/**
 * 处理修改留痕
 * 
 * com.vix.common.base.controller.BillMarkProcessController
 *
 * @author bjitzhang
 *
 * @date 2015年11月19日
 */
@Controller("billMarkProcessController")
@Scope("prototype")
public class BillMarkProcessController {

	@Autowired
	private IBaseHibernateService baseHibernateService;

	/**
	 * 处理修改留痕
	 * 
	 * @param newBaseEntity
	 * @param updateField
	 * @throws Exception
	 */
	public void processMark(BaseEntity newBaseEntity, String updateField) throws Exception {
		if (newBaseEntity == null || StringUtils.isEmpty(newBaseEntity.getId())) {
			return;
		}
		BaseEntity oldBaseEntity = baseHibernateService.findEntityById(newBaseEntity.getClass(), newBaseEntity.getId());
		if (oldBaseEntity == null) {
			return;
		}
		String[] ufArray = updateField.split(",");
		List<String> list = new ArrayList<String>();
		if (ufArray != null && ufArray.length > 0) {
			for (String uf : ufArray) {
				if (StringUtils.isNotEmpty(uf)) {
					list.add(uf);
				}
			}
		}
		//Field[] fields = newBaseEntity.getClass().getDeclaredFields();
		PropertyDescriptor[] fields = PropertyUtils.getPropertyDescriptors(newBaseEntity);
		if (fields != null && fields.length > 0) {
			for (int i = 0; i < fields.length; i++) {
				if (list.contains(fields[i].getName())) {
					//fields[i].setAccessible(true);
					//Object oldObj = fields[i].get(oldBaseEntity);
					//Object newObj = fields[i].get(newBaseEntity);
					Object oldObj = getFieldValueByName(fields[i].getName(), oldBaseEntity);
					Object newObj = getFieldValueByName(fields[i].getName(), newBaseEntity);
					CMNObjectModifyRecord objectModifyRecord = new CMNObjectModifyRecord();
					objectModifyRecord.setBoCode(newBaseEntity.getClass().getName());
					objectModifyRecord.setKey(fields[i].getName());
					if (null != oldObj) {
						objectModifyRecord.setOldValue(String.valueOf(oldObj));
					}
					if (null != newObj) {
						objectModifyRecord.setNewValue(String.valueOf(newObj));
					}
					objectModifyRecord.setCreateTime(new Date());
					baseHibernateService.merge(objectModifyRecord);
				}
			}
		}
	}

	/**
	 * 使用反射根据属性名称获取属性值
	 * 
	 * @param fieldName
	 *            属性名称
	 * @param object
	 *            操作对象
	 * @return Object 属性值
	 */

	private Object getFieldValueByName(String fieldName, Object object) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = object.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(object, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("属性不存在");
			return null;
		}
	}

	public Pager getCMNObjectModifyRecordPager() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		//倒序排序
		Pager pager = new Pager();
		//排序
		if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
			pager.setOrderBy("desc");
			pager.setOrderField("id");
		}
		params.put("boCode," + SearchCondition.EQUAL, StockRecords.class.getName());
		return baseHibernateService.findPagerByHqlConditions(pager, CMNObjectModifyRecord.class, params);
	}
}
