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
		<i> <a href="#"><img src="img/icon_14.gif" alt="" /> <s:text name="print" /></a> <a href="#"><img src="img/icon_15.gif" alt="" /> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/schedule.png" class="png" alt="" width="26" height="26" />&nbsp;协同办公</a></li>
				<li><a href="#">个人办公</a></li>
				<li><a href="#">日程安排</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content" style="margin-top: 10px;">
	<div id='calendar'></div>
</div>

<script type="text/javascript">
	var calendar = $('#calendar').fullCalendar({
	header : {
	left : 'prev,next today',
	center : 'title',
	right : 'month,agendaWeek,agendaDay'
	},
	selectable : true,
	selectHelper : true,
	select : function(start, end, allDay) {
		var params = {
		start : start,
		end : end,
		allDay : allDay
		};
		$.ajax({
		url : '${vix}/common/canlendarAction!goSaveOrUpdate.action?start=' + $.fullCalendar.formatDate(start, 'yyyy-MM-dd HH:mm:ss') + '&end=' + $.fullCalendar.formatDate(end, 'yyyy-MM-dd HH:mm:ss'),
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 850,
			height : 525,
			title : "日程安排",
			html : html,
			btnsbar : $.btn.OKCANCEL,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#calendarsform').validationEngine('validate')) {
						$.post('${vix}/common/canlendarAction!saveOrUpdate.action', {
						'calendars.id' : $("#id").val(),
						'calendars.scheduleName' : $("#name").val(),
						'calendars.calendarsContent' : calendarsContent.html(),
						'calendars.startTime' : $("#startTime").val(),
						'calendars.endTime' : $("#endTime").val(),
						'calendars.isRemind' : $("#isRemind").val(),
						'calendars.remindTime' : $("#remindTime").val(),
						'calendars.isRepeat' : $("#isRepeat").val(),
						'calendars.allDay' : $(":radio[name=allDay][checked]").val()
						}, function(result) {
							showMessage(result);
							loadContent('${vix}/common/canlendarAction!goCanlendar.action');
							calendar.fullCalendar('unselect');
						});
					} else {
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	},
	editable : true,
	events : function(start, end, callback) {
		var params = {};
		$.ajax({
		url : "${vix}/common/canlendarAction!calendarEvents.action",
		cache : false,
		type : "post",
		data : params,
		dataType : 'json',
		success : function(res) {
			events = [];
			for (i in res.rows) {
				var startdate = getDate(res.rows[i].startTime);
				var enddate = getDate(res.rows[i].endTime);
				if (res.rows[i].allDay == false) {
					if (res.rows[i].taskType == 1) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : '#afb4db',// 背景色
						textColor : 'black'// 文字颜色
						});
					} else if (res.rows[i].taskType == 2) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : '#f391a9',// 背景色
						textColor : 'black'// 文字颜色
						});
					} else if (res.rows[i].taskType == 3) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : '#cde6c7',// 背景色
						textColor : 'black'// 文字颜色
						});
					} else if (res.rows[i].taskType == 4) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : '#00a6ac',// 背景色
						textColor : 'black'// 文字颜色
						});
					} else if (res.rows[i].taskType == 5) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : 'pink',// 背景色
						textColor : 'black'// 文字颜色
						});
					} else if (res.rows[i].taskType == 5) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : '#feeeed',// 背景色
						textColor : 'black'// 文字颜色
						});
					}
				} else {
					if (res.rows[i].taskType == 1) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : '#afb4db',// 背景色
						textColor : 'black'// 文字颜色
						});
					} else if (res.rows[i].taskType == 2) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : '#f391a9',// 背景色
						textColor : 'black'// 文字颜色
						});
					} else if (res.rows[i].taskType == 3) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : '#cde6c7',// 背景色
						textColor : 'black'// 文字颜色
						});
					} else if (res.rows[i].taskType == 4) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : '#00a6ac',// 背景色
						textColor : 'black'// 文字颜色
						});
					} else if (res.rows[i].taskType == 5) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : 'pink',// 背景色
						textColor : 'black'// 文字颜色
						});
					} else if (res.rows[i].taskType == 5) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : '#feeeed',// 背景色
						textColor : 'black'// 文字颜色
						});
					}
				}
			}
			callback(events);
		}
		});
	},
	eventDrop : function(calEvent, jsEvent, ui, view) {

		var startDate = calEvent.start;
		var endDate = calEvent.start;
		if (calEvent.end != null) {
			endDate = calEvent.end;
		}
		$.ajax({
		type : "POST",
		url : "${vix}/common/canlendarAction!updateCanlendar.action?id=" + calEvent.id + '&startTime=' + $.fullCalendar.formatDate(startDate, 'yyyy-MM-dd HH:mm:ss') + "&endTime=" + $.fullCalendar.formatDate(endDate, 'yyyy-MM-dd HH:mm:ss'),
		success : function() {
			calendar.fullCalendar('unselect');
		}
		});
	},
	eventClick : function(calEvent, jsEvent, view) {
		$.ajax({
		url : '${vix}/common/canlendarAction!goSaveOrUpdate.action?id=' + calEvent.id,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 850,
			height : 600,
			title : "日程",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#calendarsform').validationEngine('validate')) {
						$.post('${vix}/common/canlendarAction!saveOrUpdate.action', {
						'calendars.id' : $("#id").val(),
						'calendars.scheduleName' : $("#name").val(),
						'calendars.calendarsContent' : calendarsContent.html(),
						'calendars.startTime' : $("#startTime").val(),
						'calendars.endTime' : $("#endTime").val(),
						'calendars.isRemind' : $("#isRemind").val(),
						'calendars.remindTime' : $("#remindTime").val(),
						'calendars.isRepeat' : $("#isRepeat").val(),
						'calendars.allDay' : $(":checkbox[name=allDay][checked]").val()
						}, function(result) {
							showMessage(result);
							loadContent('${vix}/common/canlendarAction!goCanlendar.action');
							calendar.fullCalendar('unselect');
						});
					} else {
						return false;
					}
				}
				if (action == 'del') {
					var id = $("#id").val();
					if (id != null) {
						deleteCanlendar(id);
					}
				}
			},
			btnsbar : $.btn.OKCANCEL.concat([ {
			text : '删除',
			action : 'del'
			} ])
			});
		}
		});
	},
	eventResize : function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view) {
		var startDate = event.start;
		var endDate = event.start;
		if (event.end != null) {
			endDate = event.end;
		}
		$.ajax({
		type : "POST",
		url : "${vix}/common/canlendarAction!updateCanlendar.action?id=" + event.id + '&startTime=' + $.fullCalendar.formatDate(startDate, 'yyyy-MM-dd HH:mm:ss') + "&endTime=" + $.fullCalendar.formatDate(endDate, 'yyyy-MM-dd HH:mm:ss'),
		success : function() {
			calendar.fullCalendar('unselect');
		}
		});
	}
	});
	function getDate(val) {
		if (val != null && val != "") {
			var d = new Date(val);
			return d;
		} else
			return "";
	}
	function deleteCanlendar(id) {
		asyncbox.confirm('确定要删除该日程吗?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/common/canlendarAction!deleteCanlendarById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					loadContent('${vix}/common/canlendarAction!goCanlendar.action');
				}
				});
			}
		});
	}
</script>