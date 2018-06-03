<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link rel='stylesheet' type='text/css' href='${vix}/common/css/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='${vix}/common/css/fullcalendar.print.css' media='print' />
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script type='text/javascript' src='${vix}/common/js/jquery-ui-1.8.17.custom.min.js'></script>
<script type='text/javascript' src='${vix}/common/js/fullcalendar.min.js'></script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />
			<s:text name="print" /></a> <a href="#"><img src="img/icon_15.gif" alt="" />
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/schedule.png" class="png" alt="" width="26" height="26" />&nbsp;人力资源</a></li>
				<li><a href="#">基础设置</a></li>
				<li><a href="#">公共日历</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="right_content" style="margin-top: 10px;">
	<div id='calendar'></div>
</div>
<script type="text/javascript">
<!--
	//面包屑
	if($('.sub_menu').length)
	{
		$("#breadCrumb").jBreadCrumb();
	}
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
	var calendar = $('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		selectable: true,
		selectHelper: true,
		select: function(start, end, allDay) {
			var title = 'test';
			$.ajax({
				  url:'${vix}/template/simpleGridAction!goSaveOrUpdate.action?id='+1,
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 880,
							height : 360,
							title:"品牌",
							html:html,
							callback : function(action){
								if(action == 'ok'){
									if (title) {
										calendar.fullCalendar('renderEvent',
											{
												title: title,
												start: start,
												end: end,
												allDay: allDay
											},
											true // make the event "stick"
										);
									}
									calendar.fullCalendar('unselect');
								}
							},
							btnsbar : $.btn.OKCANCEL
						});
				  }
			});
		},
		editable: true,
		events: [
			{
				title: 'All Day Event',
				start: new Date(y, m, 1)
			},{
				title: 'Long Event',
				start: new Date(y, m, d-5),
				end: new Date(y, m, d-2)
			},{
				id: 999,
				title: 'Repeating Event',
				start: new Date(y, m, d-3, 16, 0),
				allDay: false
			},{
				id: 999,
				title: 'Repeating Event',
				start: new Date(y, m, d+4, 16, 0),
				allDay: false
			},{
				title: 'Meeting',
				start: new Date(y, m, d, 10, 30),
				allDay: false
			},{
				title: 'Lunch',
				start: new Date(y, m, d, 12, 0),
				end: new Date(y, m, d, 14, 0),
				allDay: false
			},{
				title: 'Birthday Party',
				start: new Date(y, m, d+1, 19, 0),
				end: new Date(y, m, d+1, 22, 30),
				allDay: false
			},{
				title: 'Click for Google',
				start: new Date(y, m, 28),
				end: new Date(y, m, 29),
				url: 'http://google.com/'
			}
		]
	});
//-->
</script>