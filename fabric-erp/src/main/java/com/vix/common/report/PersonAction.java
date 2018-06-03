package com.vix.common.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecords;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@Scope("prototype")
public class PersonAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5615536211473184586L;
	private List<Employee> employees;
	
	@Autowired
	public IBaseHibernateService baseHibernateService;
	
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String findJasper() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤入库单
		//params.put("flag," + SearchCondition.ANYLIKE, "1");
		///params.put("isTemp," + SearchCondition.NOEQUAL, "1");
		//params.put("code," + SearchCondition.NOEQUAL, null);
		List<StockRecords> ds = baseHibernateService.findAllByConditions(StockRecords.class, params);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ds, false);
		JasperReport jasperReport = JasperCompileManager.compileReport(PersonAction.class.getResource("/").toString().substring(6) + "stockRecords.jrxml");
		Map<String, Object> parameters = new HashMap<String, Object>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		str = ExportReport.exportToHTML(jasperPrint);
		return "findJasper";
	}

	public String exportToPDF() throws Exception {
		employees = baseHibernateService.findAllByEntityClass(Employee.class);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
		JasperReport jasperReport = JasperCompileManager.compileReport(PersonAction.class.getResource("/").toString().substring(6) + "person.jrxml");
		Map parameters = new HashMap();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		ExportReport.exportToPDF(jasperPrint);
		return "findJasper";
	}

	public String exportToRTF() throws Exception {
		employees = baseHibernateService.findAllByEntityClass(Employee.class);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
		JasperReport jasperReport = JasperCompileManager.compileReport(PersonAction.class.getResource("/").toString().substring(6) + "person.jrxml");
		Map parameters = new HashMap();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		ExportReport.exportToRTF(jasperPrint);
		return "findJasper";
	}

	public String exportToXML() throws Exception {
		employees = baseHibernateService.findAllByEntityClass(Employee.class);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
		JasperReport jasperReport = JasperCompileManager.compileReport(PersonAction.class.getResource("/").toString().substring(6) + "person.jrxml");
		Map parameters = new HashMap();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		ExportReport.exportToXML(jasperPrint);
		return "findJasper";
	}

	public String exportToExcel() throws Exception {
		employees = baseHibernateService.findAllByEntityClass(Employee.class);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
		JasperReport jasperReport = JasperCompileManager.compileReport(PersonAction.class.getResource("/").toString().substring(6) + "person.jrxml");
		Map parameters = new HashMap();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		ExportReport.exportToExcel(jasperPrint);
		return "findJasper";
	}
}
