package com.vix.hr.attendance.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.attendance.entity.AttendanceCategory;
import com.vix.hr.attendance.service.IAttendanceCategoryService;

@Component("attendancecategorydomain")
@Transactional
public class AttendanceCategoryDomain {

	@Autowired
	private IAttendanceCategoryService iAttendanceCategoryService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iAttendanceCategoryService.findPagerByHqlConditions(pager, AttendanceCategory.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = iAttendanceCategoryService.findPagerByOrHqlConditions(pager, AttendanceCategory.class, params);
		return p;
	}

	public AttendanceCategory findAttendanceCategoryById(String id) throws Exception {
		return iAttendanceCategoryService.findEntityById(AttendanceCategory.class, id);
	}

	public AttendanceCategory merge(AttendanceCategory attendanceCategory) throws Exception {
		return iAttendanceCategoryService.merge(attendanceCategory);
	}

	public void deleteByEntity(AttendanceCategory attendanceCategory) throws Exception {
		iAttendanceCategoryService.deleteByEntity(attendanceCategory);
	}

	/** 索引对象列表 */
	public List<AttendanceCategory> findAttendanceCategoryIndex() throws Exception {
		return iAttendanceCategoryService.findAllByConditions(AttendanceCategory.class, null);
	}

	public List<AttendanceCategory> findAllSubEntity(String attributeName, String parentId, Map<String, Object> params) throws Exception {
		return iAttendanceCategoryService.findAllSubEntity(AttendanceCategory.class, attributeName, parentId, params);
	}
}
