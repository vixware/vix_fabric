package com.vix.common.securityDra.vo.rule;

import java.io.Serializable;
import java.util.List;

/**
 * 数据权限序列化的json
 * 目前暂时就使用 单个group   也就是此类的op暂时不用
 * 
 * 数据样例
 * {"groups":[{"rules":[{"field":"{CurrentRoleID}","op":"in","value":"2,6","type":"int"}],"op":"and"},{"rules":[{"field":"{CurrentRoleID}","op":"equal","value":"7","type":"int"},{"field":"EmployeeID","op":"equal","value":"{CurrentEmployeeID}","type":"number"}],"op":"and"},{"rules":[{"field":"ShipName","op":"equal","value":"aa","type":"string"},{"field":"ShipAddress","op":"equal","value":"ss","type":"string"}],"op":"and"},{"groups":[{"rules":[{"field":"ShipRegion","op":"equal","value":"1111","type":"string"}],"op":"and"}],"rules":[{"field":"ShipPostalCode","op":"equal","value":"1111","type":"string"}],"op":"and"}],"op":"or"}
 * @author Administrator
 *
 */
public class RowData implements Serializable {

	/** 多个分组信息 */
	private List<RowDataGroup> groups;
	
	/** 分组信息间的操作符  */
	private String op;

	public List<RowDataGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<RowDataGroup> groups) {
		this.groups = groups;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
	
}
