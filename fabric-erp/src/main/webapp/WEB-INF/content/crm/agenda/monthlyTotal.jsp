<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
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
				<li><a href="#" onclick="loadContent('${vix}/crm/agenda/monthlyAction!goMonthlyTotal.action');">月报汇总</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div id="newtab1" class="npcontent clearfix">
				<div class="addleft">
					<div class="addbtn" style="margin-bottom: 0;">
						<p style="padding: 5px 0;">
							<img src="${vix}/common/img/crm/calendar.png" />日期选择
						</p>
					</div>
					<div id="date2" class="date_box"></div>
					<script language="javascript" type="text/javascript">
						WdatePicker({eCont:'date2',dateFmt:'yyyyMMdd',skin:'blue',onpicked:function(dp){loadMonthlyContent('newtab1',dp.cal.getDateStr());}})
					</script>
					<s:if test=" staffEmployeeList != null ">
						<div class="addbox">
							<div>人员选择：</div>
							<p>
								<s:iterator value="staffEmployeeList" var="emp">
									<a href="#" onclick="loadMonthlyContentByEmployee(${emp.id});">${emp.name}</a>
								</s:iterator>
								<script type="text/javascript">
									function loadMonthlyContentByEmployee(id){
										$.ajax({
											  url:'${vix}/crm/agenda/monthlyAction!goListContent.action?employeeId='+id,
											  cache: false,
											  success: function(html){
												  $("#monthlyContent").html(html);
											  }
										});
									}
								</script>
							</p>
						</div>
					</s:if>
				</div>
				<div id="monthlyContent" class="addright">
					<div class="list borderlist lineh30" style="border: 0; margin-bottom: 20px;">
						<input type="hidden" id="nextMonthNumber" name="nextMonthNumber" value="${nextMonthNumber}" /> <input type="hidden" id="previousMonthNumber" name="previousMonthNumber" value="${previousMonthNumber}" />
						<table width="100%">
							<tr>
								<th style="text-align: center;" colspan="2"><a href="#" onclick="loadMonthlyContent('newtab2',$('#previousMonthNumber').val());">&lt;&lt;</a> ${currentMonth} <a href="#" onclick="loadMonthlyContent('newtab2',$('#nextMonthNumber').val());">&gt;&gt;</a></th>
							</tr>
							<tr>
								<td colspan="2"><b>本月计划：（由上月拟定）</b><br /></td>
							</tr>
							<tr>
								<td><b>本月总结</b><br />
									<div>${monthly.monthSummary}</div></td>
								<td><b>下月计划</b><br />
									<div>${monthly.nextMonthPlan}</div></td>
							</tr>
							<tr>
								<td colspan="2"><b>补充回复：</b><br />
								<br /> <b>补充回复信息：</b><br /> <textarea name="" style="width: 80%; height: 100px; margin: 0 0 10px;" cols="" rows=""></textarea><br /> <input name="" type="button" class="btn" value="保存" /><br />&nbsp;</td>
							</tr>
						</table>
					</div>
					<div class="np_left_title">
						<h2>当月行动和日报参考：</h2>
					</div>
					<div class="task_list borderlist lineh30 hovertd" style="border: 0px none; padding: 0;">
						<table width="100%">

							<tr>
								<th width="14%" style="text-align: center;">周一</th>
								<th width="14%" style="text-align: center;">周二</th>
								<th width="14%" style="text-align: center;">周三</th>
								<th width="14%" style="text-align: center;">周四</th>
								<th width="14%" style="text-align: center;">周五</th>
								<th width="14%" style="text-align: center;">周六</th>
								<th width="14%" style="text-align: center;">周日</th>
							</tr>
							<tr>
								<td class="ar bggray" style="text-align: center;">${firstDailyDate}</td>
								<td class="ar bggray" style="text-align: center;">${secondDailyDate}</td>
								<td class="ar bggray" style="text-align: center;">${thirdDailyDate}</td>
								<td class="ar bggray" style="text-align: center;">${fourDailyDate}</td>
								<td class="ar bggray" style="text-align: center;">${fiveDailyDate}</td>
								<td class="ar bggray" style="text-align: center;">${sixDailyDate}</td>
								<td class="ar bggray" style="text-align: center;">${sevenDailyDate}</td>
							</tr>
							<tr>
								<s:iterator value="dailyList" var="daily">
									<td>
										<div class="table_sc">${daily.tomorrowPlan}</div>
									</td>
								</s:iterator>
							</tr>
							<tr>
								<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
								<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
								<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
								<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
								<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
								<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
								<td class="tc bggray"><span class="gray">日报-工作总结：</span></td>
							</tr>
							<tr>
								<s:iterator value="dailyList" var="daily">
									<td>
										<div class="table_sc">${daily.todaySummary}</div>
									</td>
								</s:iterator>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>