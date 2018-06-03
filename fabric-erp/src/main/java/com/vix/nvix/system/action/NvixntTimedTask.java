package com.vix.nvix.system.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vix.common.job.entity.JobTodo;
import com.vix.common.share.entity.AlertNotice;
import com.vix.contract.mamanger.entity.Contract;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Component
public class NvixntTimedTask{

	@Autowired
	IVixntBaseService vixntBaseService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 合同到期预警
	 */
//	@Scheduled(cron = "0 23 0 * * ?")
	@Scheduled(cron = "0/10 * * * * ?")
	public void changeContractType(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<Contract> list = vixntBaseService.findAllDataByConditions(Contract.class, params);
			if(null != list && list.size() > 0 ){
				for (Contract contract : list) {
					int day = 0;
					if(null != contract){
						if(contract.getContractEndTime() != null){
							Date d1 = contract.getContractEndTime();
							Date d2 = new Date();
							day = daysBetween(d1,d2);
							if (0 < day && day < 10) {
								AlertNotice alertNotice = null;
								alertNotice = vixntBaseService.findEntityByAttributeNoTenantId(AlertNotice.class, "boCode", contract.getCode()+contract.getContractEndTime());
								if(alertNotice != null){
									alertNotice.setContent(contract.getContractName()+contract.getContractCode()+simpleDateFormat.format(contract.getContractEndTime())+"还剩" + day + "天就到期了.");
								}else{
									alertNotice = new AlertNotice();
									alertNotice.setSubject("合同到期提醒");
									alertNotice.setBoCode(contract.getContractCode() + contract.getContractEndTime());
									alertNotice.setContent(contract.getContractName()+contract.getContractCode()+simpleDateFormat.format(contract.getContractEndTime())+"还剩" + day + "天就到期了.");
									alertNotice.setRemindTime(new Date());
									alertNotice.setTime(new Date());
									alertNotice.setType("contact");
									alertNotice.setTenantId(contract.getTenantId());
									alertNotice.setCompanyCode(contract.getCompanyCode());
									alertNotice.setCompanyInnerCode(contract.getCompanyInnerCode());
								}
								vixntBaseService.mergeOriginal(alertNotice);
							}else if (0 > day){
								AlertNotice alertNotice = null;
								alertNotice = vixntBaseService.findEntityByAttributeNoTenantId(AlertNotice.class, "boCode", contract.getCode()+contract.getContractEndTime());
								if(alertNotice != null){
									alertNotice.setContent(contract.getContractName()+contract.getContractCode()+simpleDateFormat.format(contract.getContractEndTime())+"已到期" + Math.abs(day) + "天了.");
								}else{
									alertNotice = new AlertNotice();
									alertNotice.setSubject("合同到期提醒");
									alertNotice.setBoCode(contract.getContractCode() + contract.getContractEndTime());
									alertNotice.setContent(contract.getContractName()+contract.getContractCode()+simpleDateFormat.format(contract.getContractEndTime())+"已到期" + Math.abs(day) + "天了.");
									alertNotice.setRemindTime(new Date());
									alertNotice.setTime(new Date());
									alertNotice.setType("contact");
									alertNotice.setTenantId(contract.getTenantId());
									alertNotice.setCompanyCode(contract.getCompanyCode());
									alertNotice.setCompanyInnerCode(contract.getCompanyInnerCode());
								}
								vixntBaseService.mergeOriginal(alertNotice);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 表单审批提醒
	 */
	@Scheduled(cron = "0 23 0 * * ?")//每天晚上12点执行
//	@Scheduled(cron = "0/10 * * * * ?")
	public void changeJobTodo(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<JobTodo> jobTodoList = vixntBaseService.findAllDataByConditions(JobTodo.class, params);
			if(jobTodoList != null && jobTodoList.size() > 0){
				for (JobTodo jobTodo : jobTodoList) {
					int day = 0;
					if(jobTodo != null){
						if(jobTodo.getEndTime() != null){
							day = daysBetween(jobTodo.getEndTime(), new Date());
							if (0 < day && day < 10) {
								AlertNotice alertNotice = null;
								alertNotice = vixntBaseService.findEntityByAttributeNoTenantId(AlertNotice.class, "boCode", jobTodo.getCode()+jobTodo.getEndTime());
								if(alertNotice != null){
									alertNotice.setContent(jobTodo.getJobName()+jobTodo.getJobContent()+simpleDateFormat.format(jobTodo.getEndTime())+"还剩" + day + "天就到期了.");
								}else{
									alertNotice = new AlertNotice();
									alertNotice.setSubject("表单审批提醒");
									alertNotice.setBoCode(jobTodo.getCode() + jobTodo.getEndTime());
									alertNotice.setContent(jobTodo.getJobName()+jobTodo.getJobContent()+simpleDateFormat.format(jobTodo.getEndTime())+"还剩" + day + "天就到期了.");
									alertNotice.setRemindTime(new Date());
									alertNotice.setTime(new Date());
									alertNotice.setType("approve");
									alertNotice.setTenantId(jobTodo.getTenantId());
									alertNotice.setCompanyCode(jobTodo.getCompanyCode());
									alertNotice.setCompanyInnerCode(jobTodo.getCompanyInnerCode());
								}
								vixntBaseService.mergeOriginal(alertNotice);
							}else if (0 > day){
								AlertNotice alertNotice = null;
								alertNotice = vixntBaseService.findEntityByAttributeNoTenantId(AlertNotice.class, "boCode", jobTodo.getCode() + jobTodo.getEndTime());
								if(alertNotice != null){
									alertNotice.setContent(jobTodo.getJobName()+jobTodo.getJobContent()+simpleDateFormat.format(jobTodo.getEndTime())+"已到期" + Math.abs(day) + "天了.");
								}else{
									alertNotice = new AlertNotice();
									alertNotice.setSubject("表单审批提醒");
									alertNotice.setBoCode(jobTodo.getCode() + jobTodo.getEndTime());
									alertNotice.setContent(jobTodo.getJobName()+jobTodo.getJobContent()+simpleDateFormat.format(jobTodo.getEndTime())+"已到期" + Math.abs(day) + "天了.");
									alertNotice.setRemindTime(new Date());
									alertNotice.setTime(new Date());
									alertNotice.setType("approve");
									alertNotice.setTenantId(jobTodo.getTenantId());
									alertNotice.setCompanyCode(jobTodo.getCompanyCode());
									alertNotice.setCompanyInnerCode(jobTodo.getCompanyInnerCode());
								}
								vixntBaseService.mergeOriginal(alertNotice);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 库存商品有效期/库存量预警
	 */
	/*@Scheduled(cron = "0 23 0 * * ?")//每天晚上12点执行
	//@Scheduled(cron = "0/10 * * * * ?")
	public void checkStandingBook(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<InventoryCurrentStock> list = vixntBaseService.findAllDataByConditions(InventoryCurrentStock.class,params);
			if(null != list && list.size() > 0 ){
				for (InventoryCurrentStock inventoryCurrentStock : list) {
					if(null != inventoryCurrentStock){
						Item item = null;
						Map<String, Object> ItemParams = new HashMap<String, Object>();
						ItemParams.put("isTemp," + SearchCondition.NOEQUAL, "1");
						ItemParams.put("isDeleted," + SearchCondition.NOEQUAL, "1");
						List<Item> itemList = vixntBaseService.findAllDataByConditions(Item.class, ItemParams);
						if(null != itemList && itemList.size() > 0){
							for (Item item2 : itemList) {
								if(item2 != null && item2.getCode().equals(inventoryCurrentStock.getItemcode())){
									item = item2;
								}
							}
						}
						if(null != item && null != item.getItemInventoryProperties()){
							ItemInventoryProperties itemInventoryProperties = vixntBaseService.findEntityById(ItemInventoryProperties.class, item.getItemInventoryProperties().getId());
							if(null != itemInventoryProperties){
								if(inventoryCurrentStock.getQuantity() > itemInventoryProperties.getMaxStockAmount()){
									inventoryCurrentStock.setStatus("4");//库存超限
									inventoryCurrentStock = vixntBaseService.mergeOriginal(inventoryCurrentStock);
									continue;
								}
								if(inventoryCurrentStock.getQuantity() < itemInventoryProperties.getMinStockAmount()){
									inventoryCurrentStock.setStatus("3");//库存不足
									inventoryCurrentStock = vixntBaseService.mergeOriginal(inventoryCurrentStock);
									continue;
								}
							}
						}
						if(inventoryCurrentStock.getMassunitEndTime() != null){
							Date nowDay = dateFormat.parse(DateUtil.format(new Date()));
							Date endDay = dateFormat.parse(inventoryCurrentStock.getMassunitEndTimeStr());
							long l = (endDay.getTime() - nowDay.getTime())/24/60/60/1000;
							if(l > 0){
								if(l <= 30){
									inventoryCurrentStock.setStatus("1");//即将到期
									inventoryCurrentStock.setDayTime(l);
									inventoryCurrentStock = vixntBaseService.mergeOriginal(inventoryCurrentStock);
								}
							}else{
								inventoryCurrentStock.setStatus("2");//已经到期
								inventoryCurrentStock = vixntBaseService.mergeOriginal(inventoryCurrentStock);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public int daysBetween(Date smdate, Date bdate) throws ParseException {
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time1 - time2) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
}
