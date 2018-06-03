package com.vix.oa.personaloffice.domain;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.entity.Attendance;
import com.vix.oa.personaloffice.entity.OvertimeRecords;
import com.vix.oa.personaloffice.entity.PersonalAttendance;
import com.vix.oa.personaloffice.entity.TLeaveRecords;
import com.vix.oa.personaloffice.entity.TripRecord;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;

/**
 * 
 * @ClassName: PersonalAttendanceDomain
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-16 下午1:29:58
 */
@Component("personalAttendanceDomain")
@Transactional
public class PersonalAttendanceDomain extends BaseDomain{

	@Autowired
	private IPersonalAttendanceService personalAttendanceService;
	
	/** 获取外出记录列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = personalAttendanceService.findPagerByHqlConditions(pager,PersonalAttendance.class, params);
		return p;
	}
	
	/** 获取外出搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = personalAttendanceService.findPagerByOrHqlConditions(pager, PersonalAttendance.class, params);
		return p;
	}
	
	/** 获取请假列表数据 */
	public Pager findPagerByHqlConditions1(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = personalAttendanceService.findPagerByHqlConditions(pager,TLeaveRecords.class, params);
		return p;
	}
	
	/** 获取请假搜索列表数据  */
	public Pager findPagerByOrHqlConditions1(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = personalAttendanceService.findPagerByOrHqlConditions(pager, TLeaveRecords.class, params);
		return p;
	}
	
	/** 获取出差列表数据 */
	public Pager findPagerByHqlConditions2(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = personalAttendanceService.findPagerByHqlConditions(pager,TripRecord.class, params);
		return p;
	}
	
	/** 获取出差搜索列表数据  */
	public Pager findPagerByOrHqlConditions2(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = personalAttendanceService.findPagerByOrHqlConditions(pager, TripRecord.class, params);
		return p;
	}
	
	/** 获取加班登记列表数据 */
	public Pager findPagerByHqlConditions3(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = personalAttendanceService.findPagerByHqlConditions(pager,OvertimeRecords.class, params);
		return p;
	}
	
	/** 获取出差搜索列表数据  */
	public Pager findPagerByOrHqlConditions3(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = personalAttendanceService.findPagerByOrHqlConditions(pager, OvertimeRecords.class, params);
		return p;
	}
	
	/** 获取考勤记录列表数据 */
	public Pager findPagerByHqlConditions4(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = personalAttendanceService.findPagerByHqlConditions(pager,Attendance.class, params);
		return p;
	}
	
	/** 获取考勤记录搜索列表数据  */
	public Pager findPagerByOrHqlConditions4(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = personalAttendanceService.findPagerByOrHqlConditions(pager, Attendance.class, params);
		return p;
	}
	
	
	/** 索引外出记录对象列表 */
	public List<PersonalAttendance> findSalesOrderIndex() throws Exception {
		return personalAttendanceService.findAllByConditions(PersonalAttendance.class, null);
	}
	
	/** 索引请假记录对象列表 */
	public List<TLeaveRecords> findSalesOrderIndex1() throws Exception {
		return personalAttendanceService.findAllByConditions(TLeaveRecords.class, null);
	}
	/** 索引出差记录对象列表 */
	public List<TripRecord> findSalesOrderIndex2() throws Exception {
		return personalAttendanceService.findAllByConditions(TripRecord.class, null);
	}
	/** 索引加班记录对象列表 */
	public List<OvertimeRecords> findSalesOrderIndex3() throws Exception {
		return personalAttendanceService.findAllByConditions(OvertimeRecords.class, null);
	}
	/** 索引考勤记录对象列表 */
	public List<Attendance> findAttendanceIndex() throws Exception {
		return personalAttendanceService.findAllByConditions(Attendance.class, null);
	}
	
	/**处理删除外出记录操作*/
	public PersonalAttendance findEntityById(String id) throws Exception {
		return personalAttendanceService.findEntityById(PersonalAttendance.class, id);
	}
	
	/**处理删除外出记录操作*/
	public void deleteByEntity(PersonalAttendance personalAttendance) throws Exception {
		personalAttendanceService.deleteByEntity(personalAttendance);
	}
	
	/**处理删除请假记录操作*/
	public TLeaveRecords findLeaveById(String id) throws Exception {
		return personalAttendanceService.findEntityById(TLeaveRecords.class, id);
	}
	/**处理删除出差记录操作*/
	public TripRecord findTripRecordById(String id) throws Exception {
		return personalAttendanceService.findEntityById(TripRecord.class, id);
	}
	/**处理删除加班登记操作*/
	public OvertimeRecords findOvertimeRecordsById(String id) throws Exception {
		return personalAttendanceService.findEntityById(OvertimeRecords.class, id);
	}
	/**处理删除考勤记录操作*/
	public Attendance findAttendanceById(String id) throws Exception {
		return personalAttendanceService.findEntityById(Attendance.class, id);
	}
	
	/**处理删除请假记录操作*/
	public void deleteByLeave(TLeaveRecords tLeaveRecords) throws Exception {
		personalAttendanceService.deleteByEntity(tLeaveRecords);
	}
	/**处理删除出差记录操作*/
	public void deleteByTripRecord(TripRecord tripRecord) throws Exception {
		personalAttendanceService.deleteByEntity(tripRecord);
	}
	/**处理删除加班登记操作*/
	public void deleteByOvertimeRecords(OvertimeRecords overtimeRecords) throws Exception {
		personalAttendanceService.deleteByEntity(overtimeRecords);
	}
	/**处理删除考勤记录操作*/
	public void deleteByAttendance(Attendance attendance) throws Exception {
		personalAttendanceService.deleteByEntity(attendance);
	}
	
	public TLeaveRecords findEntityById1(String id) throws Exception {
		return personalAttendanceService.findEntityById(TLeaveRecords.class, id);
	}
	
	public TripRecord findEntityById2(String id) throws Exception {
		return personalAttendanceService.findEntityById(TripRecord.class, id);
	}
	
	public OvertimeRecords findEntityById3(String id) throws Exception {
		return personalAttendanceService.findEntityById(OvertimeRecords.class, id);
	}
	
	public Attendance findEntityById4(String id) throws Exception {
		return personalAttendanceService.findEntityById(Attendance.class, id);
	}
	
	public PersonalAttendance merge(PersonalAttendance personalAttendance) throws Exception {
		return personalAttendanceService.merge(personalAttendance);
	}
	
	public TLeaveRecords merge1(TLeaveRecords tleaveRecords) throws Exception {
		return personalAttendanceService.merge(tleaveRecords);
	}
	
	public TripRecord merge2(TripRecord tripRecord) throws Exception {
		return personalAttendanceService.merge(tripRecord);
	}
	public OvertimeRecords merge3(OvertimeRecords overtimeRecords) throws Exception {
		return personalAttendanceService.merge(overtimeRecords);
	}
	public Attendance merge3(Attendance attendance) throws Exception {
		return personalAttendanceService.merge(attendance);
	}

}
