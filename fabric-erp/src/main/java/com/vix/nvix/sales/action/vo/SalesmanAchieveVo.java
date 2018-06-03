/** 为导出excel表建立相应的类,携带各列数据 **/
package com.vix.nvix.sales.action.vo;

/** 销售智能分析 > 销售人员业绩排行 > 相关列表   **/
public class SalesmanAchieveVo {
	private String name;//姓名
	private String sex;//性别
	private String tel;//电话号码
	private String jobNum;//工号
	private String lineNumber;//列表的行编号
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	@Override
	public String toString() {
		return "SalesmanAchieveVo [name=" + name + ", sex=" + sex + ", tel=" + tel + ", jobNum=" + jobNum
				+ ", lineNumber=" + lineNumber + "]";
	}
}