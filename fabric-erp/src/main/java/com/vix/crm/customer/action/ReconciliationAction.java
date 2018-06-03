package com.vix.crm.customer.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.StrUtils;
import com.vix.crm.salechance.entity.BackSectionRecord;
import com.vix.crm.salechance.entity.BackSectionRecordTotal;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.service.ICustomerAccountService;

@Controller
@Scope("prototype")
public class ReconciliationAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICustomerAccountService customerAccountService;
	
	private CustomerAccount customerAccount;
	private Long year;
	private Long preYear;
	private Long nextYear;
	private List<BackSectionRecordTotal> bsrtList;
	@Override
	public String goList(){
		try{
			int month = 12;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			if(!StrUtils.objectIsNotNull(year)){
				String y = sdf.format(new Date());
				year = Long.parseLong(y);
				preYear = year - 1;
				nextYear = 0l;
				Calendar cal = new GregorianCalendar();
				month = cal.get(Calendar.MONTH)+1;
				if(month == 13){
					month = 12;
				}
			}else{
				String y = sdf.format(new Date());
				Long currentYear = Long.parseLong(y);
				preYear = year - 1;
				if(currentYear.longValue() > year.longValue()){
					nextYear = year +1;
				}else{
					Calendar cal = new GregorianCalendar();
					month = cal.get(Calendar.MONTH)+1;
					if(month == 13){
						month = 12;
					}
				}
			}
			bsrtList = new ArrayList<BackSectionRecordTotal>();
			for(int i=1;i<=month;i++){
				bsrtList.add(backSectionRecordTotalByMonth(i));
			}
			for(int i=0;i<month;i++){
				if(i > 0){
					BackSectionRecordTotal bsrt1 = bsrtList.get(i-1);
					BackSectionRecordTotal bsrt2 = bsrtList.get(i);
					bsrt2.setPreviousMonthAmount(bsrt1.getCurrentMonthAmount()+bsrt1.getPreviousMonthAmount());
				}
			}
			year--;
			BackSectionRecordTotal preYearLastMonth = backSectionRecordTotalByMonth(12);
			bsrtList.get(0).setPreviousMonthAmount(preYearLastMonth.getCurrentMonthAmount());
			year++;
			String caId = getRequestParameter("customerAccountId");
			if(null != caId && !"".equals(caId)){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class, caId);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	public BackSectionRecordTotal backSectionRecordTotalByMonth(long month) throws Exception {
		BackSectionRecordTotal bsrt = new BackSectionRecordTotal();
		bsrt.setMonth(month);
		Map<String,Object> params = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sDateStr = year + "-" + month + "-01";
		String eDateStr = year + "-" + month + "-01";
		String sDate = DateUtil.format(DateUtil.getFirstDayOfMonth(sdf.parse(sDateStr)), "yyyy-MM-dd");
		String eDate = DateUtil.format(DateUtil.getLastDayOfMonth(sdf.parse(eDateStr)), "yyyy-MM-dd");
		params.put("backSectionDate,"+SearchCondition.BETWEENAND, sDate + " 00:00:01!" + eDate+" 23:59:59");
		String caId = getRequestParameter("customerAccountId");
		if(null != caId && !"".equals(caId)){
			params.put("customerAccount.id,"+SearchCondition.EQUAL,Long.parseLong(caId));
		}
		List<BackSectionRecord> list = customerAccountService.findAllByConditions(BackSectionRecord.class, params);
		if(null != list){
			double amountTotal = 0l;
			for(BackSectionRecord bsr : list){
				if(null != bsr){
					amountTotal += bsr.getAmount();
				}
			}
			bsrt.setCurrentMonthAmount(amountTotal);
		}
		return bsrt;
	}
	
	public String showReconciliationDetail(){
		try{
			Map<String,Object> params = getParams();
			String caId = getRequestParameter("customerAccountId");
			if(null != caId && !"".equals(caId)){
				params.put("customerAccount.id,"+SearchCondition.EQUAL,Long.parseLong(caId));
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class, caId);
			}
			String y = getRequestParameter("year");
			String m = getRequestParameter("month");
			if(null != y && !"".equals(y) && null != m && !"".equals(m)){
				if(m.length() == 1){
					m = "0" + m;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sDateStr = y + "-" + m + "-01";
				String eDateStr = y + "-" + m + "-01";
				String sDate = DateUtil.format(DateUtil.getFirstDayOfMonth(sdf.parse(sDateStr)), "yyyy-MM-dd");
				String eDate = DateUtil.format(DateUtil.getLastDayOfMonth(sdf.parse(eDateStr)), "yyyy-MM-dd");
				params.put("backSectionDate,"+SearchCondition.BETWEENAND, sDate + " 00:00:01!" + eDate+" 23:59:59");
				customerAccountService.findPagerByHqlConditions(getPager(), BackSectionRecord.class, params);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "showReconciliationDetail";
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getPreYear() {
		return preYear;
	}

	public void setPreYear(Long preYear) {
		this.preYear = preYear;
	}

	public Long getNextYear() {
		return nextYear;
	}

	public void setNextYear(Long nextYear) {
		this.nextYear = nextYear;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public List<BackSectionRecordTotal> getBsrtList() {
		return bsrtList;
	}

	public void setBsrtList(List<BackSectionRecordTotal> bsrtList) {
		this.bsrtList = bsrtList;
	}
}
