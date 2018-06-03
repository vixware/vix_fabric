package com.vix.wechat.base.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import com.vix.common.share.entity.BaseEntity;
import com.vix.oa.personaloffice.entity.TripRecord;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * 成员 com.vix.wechat.base.entity.WxpQyContacts
 *
 * @author bjitzhang
 *
 * @date 2015年12月29日
 */
public class WxpQyContacts extends BaseEntity {

	private static final long serialVersionUID = 1L;
	String userId; //成员UserID,非wxp_user表id
	String position; //职位信息'
	String mobile; //手机号码
	String email; //邮箱
	String weixinid; //微信号。企业内必须唯一。（注意：是微信号，不是微信的名字）'
	String openId; //相对于公众号的标示，关联wxp_user粉丝表
	String headImgUrl; //成员头像

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

	Set<WxpQyContactDeptRel> deptRelSet;
	private Set<VixTask> subVixTasks = new HashSet<VixTask>();
	private Set<TripRecord> subTripRecords = new HashSet<TripRecord>();
	/**
	 * 生成json使用指定的部门id列表，可以为null则使用关联set对象生成
	 */
	List<Long> specifyDeptIds;

	public String toJsonQiye() {
		/*
		{
		   "extattr": {"attrs":[{"name":"爱好","value":"旅游"},{"name":"卡号","value":"1234567234"}]}
		}
		 */
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("userid", this.getUserId());
		dataMap.put("name", this.getName());
		dataMap.put("position", this.getPosition());
		dataMap.put("mobile", this.getMobile());
		dataMap.put("email", this.getEmail());
		dataMap.put("weixinid", this.getWeixinid());

		List<Long> deptIdList = null;
		if (specifyDeptIds != null && specifyDeptIds.size() > 0) {
			deptIdList = specifyDeptIds;
		} else {
			if (this.getDeptRelSet() != null && this.getDeptRelSet().size() > 0) {
				deptIdList = new ArrayList<Long>();
				for (WxpQyContactDeptRel deptRel : this.getDeptRelSet()) {
					String deptId = deptRel.getDepartmentId();
					int idx = deptId.indexOf("-");
					deptIdList.add(Long.parseLong(deptId.substring(idx + 1)));
				}
			}
		}

		if (deptIdList != null && deptIdList.size() > 0)
			dataMap.put("department", deptIdList);

		return JSONObject.toJSONString(dataMap);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the headImgUrl
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * @param headImgUrl
	 *            the headImgUrl to set
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	/**
	 * @return the subTripRecords
	 */
	public Set<TripRecord> getSubTripRecords() {
		return subTripRecords;
	}

	/**
	 * @param subTripRecords
	 *            the subTripRecords to set
	 */
	public void setSubTripRecords(Set<TripRecord> subTripRecords) {
		this.subTripRecords = subTripRecords;
	}

	/**
	 * @return the subVixTasks
	 */
	public Set<VixTask> getSubVixTasks() {
		return subVixTasks;
	}

	/**
	 * @param subVixTasks
	 *            the subVixTasks to set
	 */
	public void setSubVixTasks(Set<VixTask> subVixTasks) {
		this.subVixTasks = subVixTasks;
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

	public Set<WxpQyContactDeptRel> getDeptRelSet() {
		return deptRelSet;
	}

	public void setDeptRelSet(Set<WxpQyContactDeptRel> deptRelSet) {
		this.deptRelSet = deptRelSet;
	}

	public List<Long> getSpecifyDeptIds() {
		return specifyDeptIds;
	}

	public void setSpecifyDeptIds(List<Long> specifyDeptIds) {
		this.specifyDeptIds = specifyDeptIds;
	}
}
