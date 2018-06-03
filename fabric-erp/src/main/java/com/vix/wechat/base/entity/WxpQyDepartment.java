package com.vix.wechat.base.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import com.vix.common.share.entity.BaseEntity;
/**
 * 
 * com.vix.wechat.base.entity.WxpQyDepartment
 *
 * @author bjitzhang
 *
 * @date 2015年12月29日
 */
public class WxpQyDepartment extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	int isRoot; //本site内是否为根节点
	String id; //部门id，siteId+企业号部门id
	String parentId; //父部门id
	Long syncId; //与腾讯同步后的id，并以此判断是否同步到腾讯
	String name; //名称
	int sortOrder; //排序，从小到大

	String column1; //扩展字段1
	String column2; //扩展字段2
	String column3; //扩展字段3
	String column4; //扩展字段4
	String column5; //扩展字段5
	String column6; //扩展字段6
	String column7; //扩展字段7
	String column8; //扩展字段8
	String column9; //扩展字段9
	String column10; //扩展字段10
	String column11; //扩展字段11
	String column12; //扩展字段12
	String column13; //扩展字段13
	String column14; //扩展字段14
	String column15; //扩展字段15

	Set<WxpQyContactDeptRel> contactRelSet;

	Set<WxpQyDepartment> subDepartmentSet;

	public void makeId(Long siteId, Object syncId) {
		this.id = siteId + "-" + syncId;
	}

	public void makeParentId(Long siteId, Object parentId) {
		this.parentId = siteId + "-" + parentId;
	}

	public String toJsonQiye() {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		if (this.syncId != null && this.syncId > 0)
			dataMap.put("id", this.syncId);

		dataMap.put("name", this.getName());
		int idx = this.getParentId().indexOf("-");
		String txParentId = this.getParentId().substring(idx + 1);
		dataMap.put("parentid", txParentId);
		dataMap.put("order", this.getSortOrder());
		return JSONObject.toJSONString(dataMap);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}

	public String getColumn6() {
		return column6;
	}

	public void setColumn6(String column6) {
		this.column6 = column6;
	}

	public String getColumn7() {
		return column7;
	}

	public void setColumn7(String column7) {
		this.column7 = column7;
	}

	public String getColumn8() {
		return column8;
	}

	public void setColumn8(String column8) {
		this.column8 = column8;
	}

	public String getColumn9() {
		return column9;
	}

	public void setColumn9(String column9) {
		this.column9 = column9;
	}

	public String getColumn10() {
		return column10;
	}

	public void setColumn10(String column10) {
		this.column10 = column10;
	}

	public String getColumn11() {
		return column11;
	}

	public void setColumn11(String column11) {
		this.column11 = column11;
	}

	public String getColumn12() {
		return column12;
	}

	public void setColumn12(String column12) {
		this.column12 = column12;
	}

	public String getColumn13() {
		return column13;
	}

	public void setColumn13(String column13) {
		this.column13 = column13;
	}

	public String getColumn14() {
		return column14;
	}

	public void setColumn14(String column14) {
		this.column14 = column14;
	}

	public String getColumn15() {
		return column15;
	}

	public void setColumn15(String column15) {
		this.column15 = column15;
	}

	public Set<WxpQyContactDeptRel> getContactRelSet() {
		return contactRelSet;
	}

	public void setContactRelSet(Set<WxpQyContactDeptRel> contactRelSet) {
		this.contactRelSet = contactRelSet;
	}

	public Set<WxpQyDepartment> getSubDepartmentSet() {
		return subDepartmentSet;
	}

	public void setSubDepartmentSet(Set<WxpQyDepartment> subDepartmentSet) {
		this.subDepartmentSet = subDepartmentSet;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getSyncId() {
		return syncId;
	}

	public void setSyncId(Long syncId) {
		this.syncId = syncId;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return parentId;
	}

	public int getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(int isRoot) {
		this.isRoot = isRoot;
	}

}
