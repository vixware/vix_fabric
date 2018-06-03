package com.vix.hr.attendance.service;

import java.util.List;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * @Description: 考勤记录
 * @author ivan
 * @date 2013-09-02
 */
public interface IAttendanceRecordService extends IBaseHibernateService {
	List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception;

}
