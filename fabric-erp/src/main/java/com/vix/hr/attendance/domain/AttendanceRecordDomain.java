package com.vix.hr.attendance.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.attendance.entity.AttendanceRecord;
import com.vix.hr.attendance.service.IAttendanceRecordService;

/**
 * @Description: 考勤记录
 * @author ivan
 * @date 2013-09-02
 */
@Component("attendanceRecordDomain")
@Transactional
public class AttendanceRecordDomain {

	@Autowired
	private IAttendanceRecordService attendanceRecordService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = attendanceRecordService.findPagerByHqlConditions(pager, AttendanceRecord.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findAttendanceRecordPagerByOrHqlConditions(String hql, Pager pager) throws Exception {
		Pager p = attendanceRecordService.findPagerJustByHql(pager, hql);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = attendanceRecordService.findPagerByOrHqlConditions(pager, AttendanceRecord.class, params);
		return p;
	}

	public AttendanceRecord findEntityById(String id) throws Exception {
		return attendanceRecordService.findEntityById(AttendanceRecord.class, id);
	}

	public AttendanceRecord merge(AttendanceRecord attendanceRecord) throws Exception {
		return attendanceRecordService.merge(attendanceRecord);
	}

	public void deleteByEntity(AttendanceRecord attendanceRecord) throws Exception {
		attendanceRecordService.deleteByEntity(attendanceRecord);
	}

	/** 索引对象列表 */
	public List<AttendanceRecord> findAttendanceRecordIndex() throws Exception {
		return attendanceRecordService.findAllByConditions(AttendanceRecord.class, null);
	}

}
