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
				<li><a href="#" onclick="loadContent('${vix}/crm/agenda/dailyAction!goDailyTotal.action');">日报汇总</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div id="newtab2">
				<div class="addleft">
					<div class="addbtn" style="margin-bottom: 0;">
						<p style="padding: 5px 0;">
							<img src="${vix}/common/img/crm/calendar.png" />日期选择
						</p>
					</div>
					<div id="date2" class="date_box"></div>
					<script language="javascript" type="text/javascript">
						WdatePicker({eCont:'date2',dateFmt:'yyyyMMdd',skin:'blue',onpicked:function(dp){loadDailyContent(dp.cal.getDateStr());}})
					</script>
					<s:if test=" staffEmployeeList != null ">
						<div class="addbox">
							<div>人员选择：</div>
							<p>
								<s:iterator value="staffEmployeeList" var="emp">
									<a href="#" onclick="loadDailyContentByEmployee(${emp.id});">${emp.name}</a>
								</s:iterator>
								<script type="text/javascript">
									function loadDailyContentByEmployee(id){
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
				<div id="dailyContent" class="addright">
					<div class="ct_title">
						<strong>2011-12-26 周一 <span class="blue">王天弘</span></strong> &nbsp;&nbsp;过去5天：22号(四)<a href="#">写</a>&nbsp;&nbsp;23号(五)<a href="#">写</a>&nbsp;&nbsp;24号(六)<a href="#">写</a>&nbsp;&nbsp;25号(日)<a href="#">写</a>&nbsp;&nbsp;26号(一)<a href="#">写</a>
					</div>
					<p style="padding: 10px;" class="gray">
						<b>当日数据参考</b> 注意：当日数据会随编辑而变化，仅作为撰写总结时的参考
					</p>
					<div class="ct_topbar clearfix">
						<span class="r"><a href="#">今日创建</a> <a href="#">需今日结束</a> <a href="#">完成或推进</a> <a href="#">结束</a> <a href="#">传递</a></span><b>任务主题</b>
					</div>
					<div class="table margin-bottom">
						<table class="list borderlist pd-lr">
							<caption>
								<img align="absmiddle" src="img/ico-right.gif">代办任务
							</caption>
							<tbody>
								<tr class="alt">
									<td>·<a href="#">上门拜访客户</a> 今天
									</td>
								</tr>
							</tbody>
						</table>
						<table class="list borderlist pd-lr">
							<caption>
								<img align="absmiddle" src="img/ico-right.gif">日程
							</caption>
							<tbody>
								<tr class="alt">
									<td>·<a href="#">合肥测试客户</a> 潜在
									</td>
								</tr>
								<tr class="">
									<td>·<a href="#">合肥测试客户</a> 潜在
									</td>
								</tr>
								<tr class="alt">
									<td>·<a href="#">合肥测试客户</a> 潜在
									</td>
								</tr>
								<tr class="">
									<td>·<a href="#">合肥测试客户</a> 潜在
									</td>
								</tr>
								<tr class="alt">
									<td>·<a href="#">合肥测试客户</a> 潜在
									</td>
								</tr>
							</tbody>
						</table>
						<table class="list borderlist pd-lr">
							<caption>
								<img align="absmiddle" src="img/ico-right.gif">行动历史
							</caption>
							<tbody>
								<tr>
									<td>·<a href="#">86786</a> 今天 RMB 0.00/RMB 35,400.00
									</td>
								</tr>
								<tr class="alt">
									<td>·<a href="#">86786</a> 今天 RMB 0.00/RMB 35,400.00
									</td>
								</tr>
								<tr>
									<td>·<a href="#">86786</a> 今天 RMB 0.00/RMB 35,400.00
									</td>
								</tr>
								<tr class="alt">
									<td>·<a href="#">86786</a> 今天 RMB 0.00/RMB 35,400.00
									</td>
								</tr>
							</tbody>
						</table>
						<table class="list borderlist pd-lr">
							<caption>
								<img align="absmiddle" src="img/ico-right.gif"> 客服记录
							</caption>
							<tbody>
								<tr class="">
									<td>&nbsp;</td>
								</tr>
							</tbody>
						</table>
						<table class="list borderlist pd-lr">
							<caption>
								<img align="absmiddle" src="img/ico-right.gif">日报${today}
							</caption>
							<tbody>
								<tr class="">
									<td><p class="gray">
											<b>今日计划(由昨日拟定)</b>
										</p></td>
								</tr>
								<tr class="">
									<td><p class="gray">${yesterdayDaily.tomorrowPlan}</p></td>
								</tr>
								<tr class="">
									<td><p class="gray">
											<b>今日总结</b>
										</p></td>
								</tr>
								<tr class="">
									<td><p class="gray">${daily.todaySummary}</p></td>
								</tr>
								<tr class="">
									<td><p class="gray">
											<b>明日计划</b>
										</p></td>
								</tr>
								<tr class="">
									<td><p class="gray">${daily.tomorrowPlan}</p></td>
								</tr>
								<tr class="">
									<td><s:if test="daily.repeat != null">
											<p class="gray">
												<b>批示和回复</b>
											</p>
											<p class="gray">${daily.repeat}</p>
										</s:if></td>
								</tr>
							</tbody>
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