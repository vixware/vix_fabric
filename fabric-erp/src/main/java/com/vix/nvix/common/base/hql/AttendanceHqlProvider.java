package com.vix.nvix.common.base.hql;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.utils.DateUtil;

@Component
@Scope("prototype")
public class AttendanceHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	public StringBuilder findEmpCardRecordsList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select hentity.userCode,hentity.datetemp,min(hentity.punchCardDate),max(hentity.punchCardDate) from (select date_format(punchCardDate,'%Y-%m-%d') datetemp,punchCardDate,userCode from OA_ATTENDANCE_PUNCHCARDRECORD) ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("userCode")) {
				String userCode = (String) params.get("userCode");
				if (userCode == null) {
				} else {
					hql.append(" and ").append(ename).append(".userCode ='").append(userCode).append("' ");
				}
			}
		}
		hql.append(" GROUP BY ");
		hql.append(ename).append(".datetemp,");
		hql.append(ename).append(".userCode");
		return hql;
	}

	public StringBuilder findSchedulingHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Scheduling ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append(ename).append(".emp.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append(ename).append(".emp.id = :employeeId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findSchedulingByUnitHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Scheduling ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("unitId")) {
				Object unitId = params.get("unitId");
				if (unitId == null) {
					hql.append(" and ").append(ename).append(".unit.id is null");
					params.remove("unitId");
				} else {
					hql.append(" and ").append(ename).append(".unit.id = :unitId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findLeaveRecordHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from LeaveRecord ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("employeeId")) {
				Object employeeId = params.get("employeeId");
				if (employeeId == null) {
					hql.append(" and ").append(ename).append(".employee.id is null");
					params.remove("employeeId");
				} else {
					hql.append(" and ").append(ename).append(".employee.id = :employeeId ");
				}
			}
			if (params.containsKey("startTime")) {
				Object startTime = params.get("startTime");
				if (startTime == null) {
					hql.append(" and ").append(ename).append(".startTime is null");
					params.remove("startTime");
				} else {
					hql.append(" and ").append(ename).append(".startTime < :startTime ");
				}
			}
			if (params.containsKey("endTime")) {
				Object endTime = params.get("endTime");
				if (endTime == null) {
					hql.append(" and ").append(ename).append(".endTime is null");
					params.remove("endTime");
				} else {
					hql.append(" and ").append(ename).append(".endTime > :endTime ");
				}
			}
		}
		return hql;
	}

	/**
	 * 查询指定时间段员工的出勤状态
	 * 
	 * @param params
	 * @return
	 */
	public StringBuilder findEmpDayCardRecordsList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select hentity.userCode,min(hentity.punchCardDate) as 'min',CASE WHEN MIN(hentity.punchCardDate) = MAX(hentity.punchCardDate) THEN null ELSE MAX(hentity.punchCardDate) END  AS 'max',date_format(punchCardDate,'%Y-%m-%d') datetemp  from OA_ATTENDANCE_PUNCHCARDRECORD ");
		hql.append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("starttime") && params.containsKey("endtime")) {
				String starttime = (String) params.get("starttime");
				String endtime = (String) params.get("endtime");
				if (starttime == null || endtime == null) {
				} else {
					hql.append(" and ").append(ename).append(".punchCardDate between '").append(starttime).append("' ");
					hql.append(" and '").append(endtime).append("' ");
				}
			}
			if (params.containsKey("userCode")) {
				String userCode = (String) params.get("userCode");
				if (userCode == null || "".equals(userCode)) {
				} else {
					hql.append(" and ").append(ename).append(".userCode ='").append(userCode).append("' ");
				}
			}
		}
		hql.append(" GROUP BY datetemp,");
		hql.append(ename).append(".userCode");
		return hql;
	}

	/**
	 * 获取任务统计
	 * 
	 * @param params
	 * @return
	 */
	public StringBuilder findTaskStatisticsBoList(Map<String, Object> params, String syncTag) {
		StringBuilder hql = new StringBuilder();
		if("week".equals(syncTag)){
			String startTime = DateUtil.format(DateUtil.getFirstDayOfWeek(new Date())) + " 00:00:01";
			String endTime = DateUtil.format(DateUtil.getLastDayOfWeek(new Date())) + " 23:59:59";
			hql.append("SELECT p.Name, SUM(1),sum(CASE task.complete WHEN '0' THEN 1 ELSE 0 END ) AS 未开始,sum(CASE task.complete WHEN '1' THEN 1 ELSE 0 END ) AS 进行中, sum(CASE task.complete WHEN '2' THEN 1 ELSE 0 END ) AS 已完成, sum(CASE task.complete WHEN '3' THEN 1 ELSE 0 END ) AS 已超时 ,date_format(task.CREATETIME,'%Y') datetemp,c.Employee_ID FROM OA_VIXTASK task LEFT JOIN HR_ORG_EMPLOYEE_TASK c ON c.VixTask_ID =task.ID LEFT JOIN hr_org_employee p on p.ID=c.Employee_ID WHERE  task.isDeleted='0' and task.isTemp='1' and task.status='0' and p.Name is not null and task.CREATETIME BETWEEN '"+startTime+"' AND '"+endTime+"'  GROUP BY c.Employee_ID ");
		}else if("month".equals(syncTag)){
			hql.append("SELECT p.Name, SUM(1),sum(CASE task.complete WHEN '0' THEN 1 ELSE 0 END ) AS 未开始,sum(CASE task.complete WHEN '1' THEN 1 ELSE 0 END ) AS 进行中, sum(CASE task.complete WHEN '2' THEN 1 ELSE 0 END ) AS 已完成, sum(CASE task.complete WHEN '3' THEN 1 ELSE 0 END ) AS 已超时 ,date_format(task.CREATETIME,'%Y-%m') datetemp,c.Employee_ID FROM OA_VIXTASK task LEFT JOIN HR_ORG_EMPLOYEE_TASK c ON c.VixTask_ID =task.ID LEFT JOIN hr_org_employee p on p.ID=c.Employee_ID WHERE  task.isDeleted='0' and task.isTemp='1' and task.status='0' and p.Name is not null  GROUP BY c.Employee_ID,datetemp ");
		}else if("reason".equals(syncTag)){
			Calendar cal = Calendar.getInstance();
			Calendar cal1 = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if (month >= 1 && month <= 3){
				cal.set(Calendar.MONTH, 0);
				cal1.set(Calendar.MONTH, 2);
				cal1.set(Calendar.DATE, 31);
			} else if (month >= 4 && month <= 6){
				cal.set(Calendar.MONTH, 3);
				cal1.set(Calendar.MONTH, 5);
                cal1.set(Calendar.DATE, 30);
			} else if (month >= 7 && month <= 9){
				cal.set(Calendar.MONTH, 6);
				cal1.set(Calendar.MONTH, 8);
				cal1.set(Calendar.DATE, 30);
			} else if (month >= 10 && month <= 12){
				cal.set(Calendar.MONTH, 9);
				cal1.set(Calendar.MONTH, 11);
				cal1.set(Calendar.DATE, 31);
			}
			cal.set(Calendar.DATE, 1);
			String startTime = DateUtil.format(cal.getTime()) + " 00:00:01";
			String endTime = DateUtil.format(cal1.getTime()) + " 23:59:59";
			hql.append("SELECT p.Name, SUM(1),sum(CASE task.complete WHEN '0' THEN 1 ELSE 0 END ) AS 未开始,sum(CASE task.complete WHEN '1' THEN 1 ELSE 0 END ) AS 进行中, sum(CASE task.complete WHEN '2' THEN 1 ELSE 0 END ) AS 已完成, sum(CASE task.complete WHEN '3' THEN 1 ELSE 0 END ) AS 已超时 ,date_format(task.CREATETIME,'%Y') datetemp,c.Employee_ID FROM OA_VIXTASK task LEFT JOIN HR_ORG_EMPLOYEE_TASK c ON c.VixTask_ID =task.ID LEFT JOIN hr_org_employee p on p.ID=c.Employee_ID WHERE  task.isDeleted='0' and task.isTemp='1' and task.status='0' and p.Name is not null and task.CREATETIME BETWEEN '"+startTime+"' AND '"+endTime+"'  GROUP BY c.Employee_ID ");
		}else if("year".equals(syncTag)){
			hql.append("SELECT p.Name, SUM(1),sum(CASE task.complete WHEN '0' THEN 1 ELSE 0 END ) AS 未开始,sum(CASE task.complete WHEN '1' THEN 1 ELSE 0 END ) AS 进行中, sum(CASE task.complete WHEN '2' THEN 1 ELSE 0 END ) AS 已完成, sum(CASE task.complete WHEN '3' THEN 1 ELSE 0 END ) AS 已超时 ,date_format(task.CREATETIME,'%Y') datetemp,c.Employee_ID FROM OA_VIXTASK task LEFT JOIN HR_ORG_EMPLOYEE_TASK c ON c.VixTask_ID =task.ID LEFT JOIN hr_org_employee p on p.ID=c.Employee_ID WHERE  task.isDeleted='0' and task.isTemp='1' and task.status='0' and p.Name is not null  GROUP BY c.Employee_ID,datetemp ");
		}else if("projectTask".equals(syncTag)){
			hql.append("SELECT p.projectName, SUM(1),sum(CASE task.complete WHEN '0' THEN 1 ELSE 0 END ) AS 未开始,sum(CASE task.complete WHEN '1' THEN 1 ELSE 0 END ) AS 进行中, sum(CASE task.complete WHEN '2' THEN 1 ELSE 0 END ) AS 已完成, sum(CASE task.complete WHEN '3' THEN 1 ELSE 0 END ) AS 已超时 ,date_format(p.estimateStartTime,'%Y-%m-%d'),p.ID FROM OA_VIXTASK task LEFT JOIN PM_PROJECT p ON task.project_id=p.ID WHERE  task.isDeleted='0' and task.isTemp='1' and task.status='0' and p.projectName is not null  GROUP BY p.ID ");
		}else if("orgTask".equals(syncTag)){
			hql.append("SELECT o.fs, SUM(1),sum(CASE task.complete WHEN '0' THEN 1 ELSE 0 END ) AS 未开始,sum(CASE task.complete WHEN '1' THEN 1 ELSE 0 END ) AS 进行中, sum(CASE task.complete WHEN '2' THEN 1 ELSE 0 END ) AS 已完成, sum(CASE task.complete WHEN '3' THEN 1 ELSE 0 END ) AS 已超时 ,o.orgCode,o.ID FROM OA_VIXTASK task LEFT JOIN HR_ORG_EMPLOYEE_TASK c ON c.VixTask_ID =task.ID LEFT JOIN hr_org_employee p on p.ID=c.Employee_ID LEFT JOIN MDM_ORG_ORGANIZATIONUNIT o on o.ID=p.OrganizationUnit_ID WHERE  task.isDeleted='0' and task.isTemp='1' and task.status='0' and o.fs is not null  GROUP BY o.ID ");
		}
		return hql;
	}
	
	public StringBuilder findCompleteTaskStatisticsVoList() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT p.Name, sum(CASE task.complete WHEN '2' THEN 1 ELSE 0 END ) AS sum FROM OA_VIXTASK task LEFT JOIN HR_ORG_EMPLOYEE_TASK c ON c.VixTask_ID =task.ID LEFT JOIN hr_org_employee p on p.ID=c.Employee_ID WHERE  task.isDeleted='0' and task.isTemp='1' and task.status='0' and p.Name is not null  GROUP BY c.Employee_ID order by sum desc");
		return hql;
	}
	public StringBuilder findTimeOutTaskStatisticsVoList() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT p.Name, sum(CASE task.complete WHEN '3' THEN 1 ELSE 0 END ) AS sum FROM OA_VIXTASK task LEFT JOIN HR_ORG_EMPLOYEE_TASK c ON c.VixTask_ID =task.ID LEFT JOIN hr_org_employee p on p.ID=c.Employee_ID WHERE  task.isDeleted='0' and task.isTemp='1' and task.status='0' and p.Name is not null  GROUP BY c.Employee_ID order by sum desc");
		return hql;
	}

	/**
	 * 获取日志统计
	 * 
	 * @param params
	 * @return
	 */
	public StringBuilder findWorklogStatisticsBoList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT employee.NAME, date_format(worklog.CREATETIME, '%Y-%m-%d') datetemp, worklog.employee_id FROM OA_WORKLOG worklog LEFT JOIN hr_org_employee employee ON employee.ID = worklog.employee_id WHERE worklog.isTemp != '1' AND worklog.STATUS = '1' AND employee.NAME IS NOT NULL and worklog.TENANTID='" + params.get("TENANTID") + "' GROUP BY worklog.employee_id, datetemp ORDER BY datetemp DESC ");
		return hql;
	}

	public StringBuilder findAllWorkLogStatisticsBoList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT s.empName, SUM(s.isCreate!='1'),left(s.datetemp, 7),s.empcodeAndDate,s.empId from OA_WORKLOG_WORKLOGSTATISTICS s GROUP BY left(s.datetemp, 7),s.empName ");
		return hql;
	}

	public StringBuilder findStockRecordLinesVoList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT inv_stockrecordlines.itemcode, inv_stockrecordlines.itemname, SUM( inv_stockrecordlines.quantity ) quantity,round( SUM( inv_stockrecordlines.quantity * inv_stockrecordlines.unitcost ), 2 ) price, inv_stockrecordlines.CREATETIME FROM inv_stockrecordlines WHERE CREATETIME IS NOT NULL ");
		if (params != null) {
			if (params.containsKey("dateType")) {
				if ("week".equals(params.get("dateType"))) {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
				} else if ("month".equals(params.get("dateType"))) {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(CREATETIME) ");
				} else {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
				}
			} else {
				hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
			}
		} else {
			hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
		}
		hql.append(" AND inv_stockrecordlines.flag = '1' GROUP BY inv_stockrecordlines.itemcode ORDER BY quantity DESC LIMIT 10");
		return hql;
	}

	public StringBuilder findStockRecordLinesPriceVoList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT inv_stockrecordlines.itemcode, inv_stockrecordlines.itemname, SUM( inv_stockrecordlines.quantity ) quantity,round( SUM( inv_stockrecordlines.quantity * inv_stockrecordlines.unitcost ), 2 ) price, inv_stockrecordlines.CREATETIME FROM inv_stockrecordlines WHERE CREATETIME IS NOT NULL ");
		if (params != null) {
			if (params.containsKey("dateType")) {
				if ("week".equals(params.get("dateType"))) {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
				} else if ("month".equals(params.get("dateType"))) {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(CREATETIME) ");
				} else {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
				}
			} else {
				hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
			}
		} else {
			hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
		}
		hql.append(" AND inv_stockrecordlines.flag = '1' GROUP BY inv_stockrecordlines.itemcode ORDER BY price DESC LIMIT 10");
		return hql;
	}

	public StringBuilder findOutStockRecordLinesVoList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT inv_stockrecordlines.itemcode,inv_stockrecordlines.itemname,SUM(inv_stockrecordlines.quantity) quantity ,round(SUM(inv_stockrecordlines.price)* quantity,2) p ,inv_stockrecordlines.CREATETIME from inv_stockrecordlines where CREATETIME is not null and inv_stockrecordlines.flag ='2'  GROUP BY inv_stockrecordlines.itemcode ORDER BY p DESC LIMIT 10");
		return hql;
	}

	public StringBuilder findSalesOrderItemVoList(Map<String, Object> params, String tenantId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT E_ORDERDETAI.outerGoodsId,E_ORDERDETAI.title t,SUM(E_ORDERDETAI.num) num,  round(SUM(E_ORDERDETAI.payment),2) payment ,date_format(E_ORDERDETAI.CREATETIME,'%Y-%m-%d') datetemp from E_ORDERDETAI where CREATETIME is not null ");
		if (params != null) {
			if (params.containsKey("dateType")) {
				if ("week".equals(params.get("dateType"))) {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
				} else if ("month".equals(params.get("dateType"))) {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(CREATETIME) ");
				} else {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
				}
			} else {
				hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
			}
		} else {
			hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
		}
		hql.append(" and TENANTID ='" + tenantId);
		hql.append("' GROUP BY t ORDER BY payment desc LIMIT 10 ");
		return hql;
	}

	public StringBuilder findSalesOrderItemNumVoList(Map<String, Object> params, String tenantId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT E_ORDERDETAI.outerGoodsId,E_ORDERDETAI.title t,SUM(E_ORDERDETAI.num) num,  round(SUM(E_ORDERDETAI.payment),2) payment ,date_format(E_ORDERDETAI.CREATETIME,'%Y-%m-%d') datetemp from E_ORDERDETAI where CREATETIME is not null ");
		if (params != null) {
			if (params.containsKey("dateType")) {
				if ("week".equals(params.get("dateType"))) {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
				} else if ("month".equals(params.get("dateType"))) {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(CREATETIME) ");
				} else {
					hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
				}
			} else {
				hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
			}
		} else {
			hql.append(" and DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(CREATETIME) ");
		}
		hql.append(" and TENANTID ='" + tenantId);
		hql.append("' GROUP BY t ORDER BY num desc LIMIT 10 ");
		return hql;
	}

	public StringBuilder findSalesOrderItemDayList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT SUM(E_ORDERDETAI.num) num, round(SUM(E_ORDERDETAI.payment),2) payment ,date_format(E_ORDERDETAI.CREATETIME,'%Y-%m-%d') datetemp from E_ORDERDETAI where CREATETIME is not null and DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(E_ORDERDETAI.CREATETIME)   GROUP BY datetemp ");
		return hql;
	}

	public StringBuilder findCustomerSalesList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT  round(SUM(e.payment),2) payment ,c.customerName from e_customer c LEFT JOIN e_order o ON o.customer_id =c.ID LEFT JOIN e_orderdetai e on e.order_id=o.ID where c.customerName !='' GROUP BY c.customerName ORDER BY payment desc LIMIT 10");
		return hql;
	}

	public StringBuilder findOrderDetaiVoList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c.outerGoodsId,c.title,date_format(c.STARTTIME,'%Y-%m-%d') datetemp,round(SUM(c.payment),2),SUM(c.num),o.PURCHASEPRICE FROM  e_orderdetai c LEFT JOIN mdm_item o ON o.`CODE` =c.outerGoodsId GROUP BY datetemp,o.`CODE`");
		return hql;
	}

	public StringBuilder findSalesDataVoList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT ch.NAME, round(SUM(d.payment), 2) payment, date_format(d.CREATETIME, '%Y-%m') datetemp FROM E_ORDERDETAI d LEFT JOIN e_order e ON e.id = d.order_id LEFT JOIN drp_channeldistributor ch ON ch.id = e.channelDistributor_id WHERE d.CREATETIME IS NOT NULL GROUP BY datetemp ORDER BY payment DESC LIMIT 20");
		return hql;
	}

	public StringBuilder findInAndOutStatisticsBoList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT i.itemcode,i.itemname,SUM(i.quantity) q,date_format(i.CREATETIME, '%Y-%m-%d') datetemp FROM inv_stockrecordlines i LEFT JOIN inv_stockrecords e ON e.id = i.invstockrecords_id LEFT JOIN drp_channeldistributor ch ON ch.id = e.channelDistributor_id GROUP BY i.itemname,datetemp order by q desc ");
		return hql;
	}

	public StringBuilder findOrderDetailStatisticsBoList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT i.outerGoodsId,i.title,i.price,SUM(i.num),SUM(i.payment), date_format(i.CREATETIME, '%Y-%m-%d') datetemp FROM e_orderdetai i LEFT JOIN e_order e ON e.id = i.order_id LEFT JOIN drp_channeldistributor ch ON ch.id = e.channelDistributor_id GROUP BY i.title,datetemp");
		return hql;
	}

	public StringBuilder findEmpMonthCardRecordsList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select aa.recordNum,aa.datetemp,SUM(discountDay),SUM(accumulativeWorkHours),SUM(accumulativeLeaveHours),SUM(accumulativeOverTimeHours),SUM(accumulativeEvectionHours),SUM(lateTime),SUM(earlyTime),sum( CASE vacationType WHEN '2' THEN 1 ELSE 0 END ) AS 事假,sum( CASE vacationType WHEN '3' THEN 1 ELSE 0 END ) AS 病假,sum( CASE attendanceStatus WHEN '1' THEN 1 ELSE 0 END ) AS 旷工  from (select substring(OA_ATTENDANCE_DAILYSTATISTICS.dayAndMonth,1, 7) datetemp,recordNum,accumulativeWorkHours,accumulativeLeaveHours,accumulativeOverTimeHours,accumulativeEvectionHours,lateTime,earlyTime,attendanceStatus,discountDay,vacationType from OA_ATTENDANCE_DAILYSTATISTICS ) aa group by aa.recordNum,aa.datetemp ");
		if (params != null) {
			if (params.containsKey("starttime") && params.containsKey("endtime")) {
				String starttime = (String) params.get("starttime");
				String endtime = (String) params.get("endtime");
				if (starttime == null || endtime == null) {
				} else {
					hql.append(" and ").append(ename).append(".punchCardDate between '").append(starttime).append("' ");
					hql.append(" and '").append(endtime).append("' ");
				}
			}
			if (params.containsKey("userCode")) {
				String userCode = (String) params.get("userCode");
				if (userCode == null || "".equals(userCode)) {
				} else {
					hql.append(" and ").append(ename).append(".userCode ='").append(userCode).append("' ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findCustomerExpense(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT i.`NAME`, SUM(ri.NUM) amount FROM drp_requiregoodsorderitem ri LEFT JOIN drp_requiregoodsorder r ON r.ID = ri.REQUIREGOODSORDER_ID LEFT JOIN mdm_item i ON i.ID = ri.item_id WHERE 1 = 1 ");
		if(params != null){
			if (params.containsKey("customerAccountId")) {
				String customerAccountId = (String) params.get("customerAccountId");
				if(StringUtils.isNotEmpty(customerAccountId)){
					hql.append(" AND r.customerAccount_id = '" +customerAccountId +"' ");
				}
			}
		}
		hql.append("GROUP BY i.`NAME` ORDER BY amount DESC LIMIT 5");
		return hql;
	}

	public StringBuilder findCustomerAllExpense(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT i.`NAME`, SUM(ri.NUM) amount FROM drp_requiregoodsorderitem ri LEFT JOIN drp_requiregoodsorder r ON r.ID = ri.REQUIREGOODSORDER_ID LEFT JOIN mdm_item i ON i.ID = ri.item_id WHERE 1 = 1 ");
		if(params != null){
			if (params.containsKey("customerAccountId")) {
				String customerAccountId = (String) params.get("customerAccountId");
				if(StringUtils.isNotEmpty(customerAccountId)){
					hql.append(" AND r.customerAccount_id = '" +customerAccountId +"' ");
				}
			}
		}
		hql.append("GROUP BY i.`NAME` ORDER BY amount DESC");
		return hql;
	}
}