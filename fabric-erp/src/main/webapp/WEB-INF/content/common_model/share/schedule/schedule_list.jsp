<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link rel='stylesheet' type='text/css' href='${vix}/common/css/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='${vix}/common/css/fullcalendar.print.css' media='print' />
<script type='text/javascript' src='${vix}/common/js/fullcalendar.min.js'></script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">工作台</a></li>
				<li><a href="#" onclick="loadContent('${vix}/common/scheduleAction!goList.action');">日程</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并创建</span></a> <a href="#"><span>关闭</span></a>
		</p>
		<ul>
			<li><a href="#"><span>帮助</span></a>
				<ul>
					<li><a href="#">帮助信息</a></li>
					<li><a href="#">帮助信息</a></li>
					<li><a href="#">帮助信息</a></li>
					<li><a href="#">帮助信息</a></li>
				</ul></li>
		</ul>
	</div>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="mail_left">
				<div class="task_table">
					<table width="200" border="1">
						<tr>
							<td class="tr">日/周/月报</td>
							<td>日</td>
							<td>周</td>
							<td>月</td>
						</tr>
						<tr>
							<td class="tr">写</td>
							<td><a href="#"><img src="img/list_icon_01.png" width="16" height="16" /></a></td>
							<td><a href="#"><img src="img/list_icon_01.png" width="16" height="16" /></a></td>
							<td><a href="#"><img src="img/list_icon_01.png" width="16" height="16" /></a></td>
						</tr>
						<tr>
							<td class="tr">看下级</td>
							<td><a href="#"><img src="img/testImg.png" width="16" height="16" /></a></td>
							<td><a href="#"><img src="img/testImg.png" width="16" height="16" /></a></td>
							<td><a href="#"><img src="img/testImg.png" width="16" height="16" /></a></td>
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
				<div class="addbox">
					<div class="addtitle">日报周报和月报</div>
					<ol>
						<li>日报</li>
						<li>周报</li>
						<li>月报</li>
					</ol>
				</div>
				<div class="addbox">
					<div class="addtitle">日程、任务、行动历史的差异</div>
					<ol>
						<li>日程：<br /> 日程日程日程日程日程日程日程日程日程日程日程
						</li>
						<li>任务：<br /> 任务任务任务任务任务任务任务任务任务任务任务
						</li>
						<li>行动历史：<br /> 行动历史行动历史行动历史行动历史行动历史
						</li>
					</ol>
				</div>

			</div>
		</div>
		<div id="right">
			<div class="right_content" style="border: 0;">
				<div class="addtitle">${userInfo.employee.name}</div>
				<div id='calendar'></div>
				<div class="addtitle">
					<a class="abtn r"><span>新建</span></a><span class="l">行动历史 共375条</span>
				</div>
				<div class="table">
					<table class="list">
						<tbody>
							<tr class="alt">
								<th width="50"><div class="list_check">
										<div class="drop">
											<label><input type="checkbox" value="" name="" style=""></label>
											<ul>
												<li><a href="#">所有</a></li>
												<li><a href="#">其他</a></li>
												<li><a href="#">式样</a></li>
												<li><a href="#">关闭</a></li>
											</ul>
										</div>
									</div></th>
								<th>ID</th>
								<th>类别</th>
								<th>主题</th>
								<th>状态</th>
								<th>优先</th>
								<th>客户</th>
								<th>开始日期</th>
								<th>执行人</th>
								<th width="110">操作</th>
							</tr>

							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>1</td>
								<td>代办任务</td>
								<td>致电客户</td>
								<td><img src="img/mail_4.png" /></td>
								<td>高</td>
								<td>【民营企业<img src="img/file.png" />】
								</td>
								<td>2013-05-01</td>
								<td>王天弘</td>
								<td><a href="#" class="abtn"><span>编辑</span></a><a href="#" class="abtn"><span>删除</span></a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="pagelist drop clearfix">
					<ul>
						<li class="ed"><a href="#">选项</a>
							<ul>
								<li><a href="#">删除</a></li>
								<li><a href="#">邮件</a></li>
								<li><a href="#">批量更新</a></li>
								<li><a href="#">合并</a></li>
								<li><a href="#">添加到目标列表</a></li>
								<li><a href="#">导出</a></li>
							</ul></li>
					</ul>
					<strong>选中:0</strong>
					<div>
						<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<script type="text/javascript"> 
	//面包屑
	if($('.sub_menu').length){$("#breadCrumb").jBreadCrumb();}
	
	$(document).ready(function() {
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			selectable: true,
			selectHelper: true,
			editable: true,
			select: function(start, end, allDay) {
				$.ajax({
					  url:'${vix}/common/scheduleAction!goSaveOrUpdate.action?id='+0,
					  cache: false,
					  success: function(html){
						  asyncbox.open({
							 	modal:true,
								width : 820,
								height : 540,
								title:"日报",
								html:html,
								callback : function(action){
									if(action == 'ok'){
										$.post('${vix}/common/scheduleAction!saveOrUpdate.action',
											 {'schedule.id':$("#id").val(),
											  'schedule.name':$("#name").val(),
											  'schedule.startTime':$("#startTime").val(),
											  'schedule.endTime':$("#endTime").val(),
											  'schedule.todaySummary': editorSummary.html(),
											  'schedule.tomorrowPlan': editorPlan.html(),
											  'schedule.employeeCode':'${userInfo.employee.code}'
											},
											function(result){
												showMessage(result);
												setTimeout("",1000);
												$('#calendar').fullCalendar('renderEvent',
														{
															title: $("#name").val(),
															start:$("#startTime").val(),
															end:$("#endTime").val(),
															allDay: allDay
														},
														true // make the event "stick"
												);
												$('#calendar').fullCalendar('unselect');
											}
										 );
									}
								},
								btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			},
			events:'${vix}/common/scheduleAction!findDailyJsonByEmployee.action?id=${userInfo.employee.id}'
		});
	});
</script>