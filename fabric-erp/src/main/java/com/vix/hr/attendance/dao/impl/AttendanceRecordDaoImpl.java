/**   
* @Title: ApplicationFormDaoImpl.java 
* @Package com.vix.contract.dao.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-24 下午1:43:44  
*/
package com.vix.hr.attendance.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.hr.attendance.dao.IAttendanceRecordDao;

/**
 * @Description: 考勤记录
 * @author ivan
 * @date 2013-09-02
 */
@Repository("attendanceRecordDao")
public class AttendanceRecordDaoImpl extends BaseHibernateDaoImpl implements IAttendanceRecordDao {

	private static final long serialVersionUID = 1L;

}
