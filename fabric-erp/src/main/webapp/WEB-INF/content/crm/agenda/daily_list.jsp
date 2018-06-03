<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function writeDay(today){
	$.ajax({
		  url:'${vix}/crm/agenda/dailyAction!goSaveOrUpdate.action',
		  data :{
			  today : today  
		  },
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 850,
					height : 580,
					title:"日报信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($("#dailyId").val() == 'show'){
								return;
							}
							if($('#dailyForm').validationEngine('validate')){
								$.post('${vix}/crm/agenda/dailyAction!saveOrUpdate.action',
										{'daily.id': $("#dailyId").val(),
										  'daily.name':$("#name").val(),
										  'daily.dailyCode':$("#dailyCode").val(),
										  'daily.startTime':$("#startTime").val(),
										  'daily.endTime':$("#endTime").val(),
									      'daily.todaySummary':todaySummary.html(),
										  'daily.tomorrowPlan':tomorrowPlan.html(),
										  today : today
										},
										function(result){
											showMessage(result);
											loadContent('${vix}/crm/agenda/dailyAction!goList.action');
										}
									);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function writeWeek(today){
	$.ajax({
		  url:'${vix}/crm/agenda/weeklyAction!goSaveOrUpdate.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 850,
					height : 580,
					title:"周报信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#weeklyForm').validationEngine('validate')){
								$.post('${vix}/crm/agenda/weeklyAction!saveOrUpdate.action',
										{'weekly.id': $("#weeklyId").val(),
										  'weekly.name':$("#name").val(),
										  'weekly.weeklyCode':$("#weeklyCode").val(),
										  'weekly.startTime':$("#startTime").val(),
										  'weekly.endTime':$("#endTime").val(),
									      'weekly.weekSummary':weekSummary.html(),
										  'weekly.nextWeekPlan':nextWeekPlan.html()
										},
										function(result){
											showMessage(result);
										}
									);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function writeMonth(today){
	$.ajax({
		  url:'${vix}/crm/agenda/monthlyAction!goSaveOrUpdate.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 850,
					height : 580,
					title:"月报信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#monthlyForm').validationEngine('validate')){
								$.post('${vix}/crm/agenda/monthlyAction!saveOrUpdate.action',
										{'monthly.id': $("#monthlyId").val(),
										  'monthly.name':$("#name").val(),
										  'monthly.monthlyCode':$("#monthlyCode").val(),
										  'monthly.startTime':$("#startTime").val(),
										  'monthly.endTime':$("#endTime").val(),
									      'monthly.monthSummary':monthSummary.html(),
										  'monthly.nextMonthPlan':nextMonthPlan.html()
										},
										function(result){
											showMessage(result);
										}
									);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
function loadDailyContent(date){
	$.ajax({
		  url:'${vix}/crm/agenda/dailyAction!goListContent.action?date='+date+"&employeeId="+$("#empId").val(),
		  cache: false,
		  success: function(html){
			  $("#dailyContent").html(html);
		  }
	});
}
loadDailyContent('');
loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/daily.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/agenda/dailyAction!goList.action');">工作日报</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/agenda/dailyAction!goList.action');">撰写日报</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div id="newtab2">
				<div class="addleft">
					<div class="task_table" style="margin-top: 5px;">
						<table width="200" border="1">
							<tr>
								<td class="tr">日/周/月报</td>
								<td><a href="###" onclick="loadContent('${vix}/crm/agenda/dailyAction!goList.action');">日</a></td>
								<td><a href="###" onclick="loadContent('${vix}/crm/agenda/weeklyAction!goList.action');">周</a></td>
								<td><a href="###" onclick="loadContent('${vix}/crm/agenda/monthlyAction!goList.action');">月</a></td>
							</tr>
							<tr>
								<td class="tr">写</td>
								<td><a href="#" onclick="writeDay($('#wpDate').val());"><img src="img/list_icon_01.png" width="16" height="16" /></a></td>
								<td><a href="#" onclick="writeWeek($('#wpDate').val());"><img src="img/list_icon_01.png" width="16" height="16" /></a></td>
								<td><a href="#" onclick="writeMonth($('#wpDate').val());"><img src="img/list_icon_01.png" width="16" height="16" /></a></td>
							</tr>
							<tr>
								<td class="tr">看下级</td>
								<td><a href="#" onclick="loadContent('${vix}/crm/agenda/dailyAction!goDailyTotal.action');"><img src="img/testImg.png" width="16" height="16" /></a></td>
								<td><a href="#" onclick="loadContent('${vix}/crm/agenda/weeklyAction!goWeeklyTotal.action');"><img src="img/testImg.png" width="16" height="16" /></a></td>
								<td><a href="#" onclick="loadContent('${vix}/crm/agenda/monthlyAction!goMonthlyTotal.action');"><img src="img/testImg.png" width="16" height="16" /></a></td>
							</tr>
							<tr>
								<td class="tr">汇总</td>
								<td><a href="#"><img src="img/testImg.jpg" width="16" height="16" /></a></td>
								<td colspan="2"><a href="#"><img src="img/testImg.jpg" width="16" height="16" /></a></td>
							</tr>
							<tr>
								<td class="tr">提交情况</td>
								<td><a href="#"><img src="img/testImg.gif" width="16" height="16" /></a></td>
								<td><a href="#"><img src="img/testImg.gif" width="16" height="16" /></a></td>
								<td><a href="#"><img src="img/testImg.gif" width="16" height="16" /></a></td>
							</tr>
						</table>
					</div>
					<div class="addbtn" style="margin-bottom: 0;">
						<p style="padding: 5px 0;">
							<img src="${vix}/common/img/crm/calendar.png" />日期选择
						</p>
					</div>
					<div id="date2" class="date_box"></div>
					<input type="hidden" id="wpDate" name="wpDate" value="${today}" /> <input type="hidden" id="empId" name="empId" />
					<script language="javascript" type="text/javascript">
						WdatePicker({
							eCont:'date2',
							dateFmt:'yyyy-MM-dd',
							skin:'blue',
							onpicked:function(dp){
								loadDailyContent(dp.cal.getDateStr());
							}
						});
						function loadDate(){
							var myDate = new Date();
							$("#wpDate").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
						}
						loadDate();
					</script>
					<s:if test=" staffEmployeeList != null ">
						<div class="addbox">
							<p>
								<s:iterator value="staffEmployeeList" var="emp">
									<a href="###" onclick="loadDailyContentByEmployee('${emp.id}')">${emp.name}</a>
								</s:iterator>
								<script type="text/javascript">
									function loadDailyContentByEmployee(id){
										$("#empId").val(id);
										$.ajax({
											  url:'${vix}/crm/agenda/dailyAction!goListContent.action?employeeId='+id,
											  cache: false,
											  success: function(html){
												  $("#dailyContent").html(html);
											  }
										});
									}
								</script>
							</p>
						</div>
					</s:if>
				</div>
				<div id="dailyContent" class="addright"></div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>