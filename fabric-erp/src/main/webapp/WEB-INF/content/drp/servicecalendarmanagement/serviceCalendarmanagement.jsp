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
		<i> <a href="#"><img src="img/icon_14.gif" alt="" /> <s:text name="print" /> </a> <a href="#"><img src="img/icon_15.gif" alt="" /> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/drpAction!goList.action','bg_02');"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_customer_serviceandcare" /> </a></li>
				<li><a href="#"><s:text name="drp_service_calendar_management" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content" style="margin-top: 10px;">
	<div id='calendar'></div>
</div>
<script type="text/javascript">
	loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
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
			width : 650,
			height : 450,
			title : "服务项目",
			html : html,
			btnsbar : $.btn.OKCANCEL,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#calendarsform').validationEngine('validate')) {
						$.post('${vix}/common/canlendarAction!saveOrUpdate.action', {
						'calendars.id' : $("#id").val(),
						'calendars.name' : $("#name").val(),
						'calendars.content' : $("#schedulecontent").val(),
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
				if (1 == 1) {
					$(this).addClass('other')
				}
				if (res.rows[i].allDay == false) {
					events.push({
					id : res.rows[i].id,
					title : res.rows[i].name,
					start : startdate,
					end : enddate,
					allDay : false
					});
				} else {
					events.push({
					id : res.rows[i].id,
					title : res.rows[i].name,
					start : startdate,
					end : enddate,
					allDay : true
					});
				}
			}
			callback(events);
		}
		});
	},
	eventDragStart : function(calEvent, jsEvent, ui, view) {
		//日程事件被拖动之前和之后触发.  这里的拖动不一定是一个有效的拖动 
	},
	eventDragStop : function(calEvent, jsEvent, ui, view) {
		//日程事件被拖动之前和之后触发.  这里的拖动不一定是一个有效的拖动 
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
			width : 650,
			height : 450,
			title : "服务项目",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#calendarsform').validationEngine('validate')) {
						$.post('${vix}/common/canlendarAction!saveOrUpdate.action', {
						'calendars.id' : $("#id").val(),
						'calendars.name' : $("#name").val(),
						'calendars.content' : $("#schedulecontent").val(),
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
					asyncbox.success(html, "提示信息", function(action) {
						loadContent('${vix}/common/canlendarAction!goCanlendar.action');
						calendar.fullCalendar('unselect');
					});
				}
				});
			}
		});
	}
</script>