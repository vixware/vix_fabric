package com.vix.hr.attendance.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.web.Pager;
import com.vix.hr.attendance.entity.PunchCard;
import com.vix.hr.attendance.service.IAttendanceRecordService;

@Component("PunchCardDomain")
@Transactional
public class PunchCardDomain {

	@Autowired
	private IAttendanceRecordService attendanceRecordService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = attendanceRecordService.findPagerByHqlConditions(pager, PunchCard.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findAPunchCardPagerByOrHqlConditions(String hql, Pager pager) throws Exception {
		Pager p = attendanceRecordService.findPagerJustByHql(pager, hql);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = attendanceRecordService.findPagerByOrHqlConditions(pager, PunchCard.class, params);
		return p;
	}

	public PunchCard findEntityById(String id) throws Exception {
		return attendanceRecordService.findEntityById(PunchCard.class, id);
	}

	public PunchCard merge(PunchCard punchCard) throws Exception {
		return attendanceRecordService.merge(punchCard);
	}

	public void deleteByEntity(PunchCard punchCard) throws Exception {
		attendanceRecordService.deleteByEntity(punchCard);
	}

	/** 索引对象列表 */
	public List<PunchCard> findPunchCardIndex() throws Exception {
		return attendanceRecordService.findAllByConditions(PunchCard.class, null);
	}

}
