<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link rel='stylesheet' type='text/css' href='${vix}/common/css/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='${vix}/common/css/fullcalendar.print.css' media='print' />
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script type='text/javascript' src='${vix}/common/js/jquery-ui-1.8.17.custom.min.js'></script>
<script type='text/javascript' src='${vix}/common/js/fullcalendar.min.js'></script>
<div class="content" style="margin-top: 10px;">
	<div id='calendar'></div>
</div>
<script type="text/javascript">
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
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
			height : 600,
			title : "日程",
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
						'calendars.allDay' : $(":checkbox[name=allDay][checked]").val()
						}, function(result) {
							showMessage(result);
							$.ajax({
							url : '${vix}/common/vixIndexDataAction!goCalendar.action',
							cache : false,
							success : function(html) {
								$("#calendar").html(html);
							}
							});
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
						color : '#afb4db'
						});
					} else if (res.rows[i].taskType == 2) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : '#f391a9'
						});
					} else if (res.rows[i].taskType == 3) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : '#cde6c7'
						});
					} else if (res.rows[i].taskType == 4) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : '#00a6ac'
						});
					} else if (res.rows[i].taskType == 5) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : 'pink'
						});
					} else if (res.rows[i].taskType == 5) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : false,
						color : '#feeeed'
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
						color : '#afb4db'
						});
					} else if (res.rows[i].taskType == 2) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : '#f391a9'
						});
					} else if (res.rows[i].taskType == 3) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : '#cde6c7'
						});
					} else if (res.rows[i].taskType == 4) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : '#00a6ac'
						});
					} else if (res.rows[i].taskType == 5) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : 'pink'
						});
					} else if (res.rows[i].taskType == 5) {
						events.push({
						id : res.rows[i].id,
						title : res.rows[i].scheduleName,
						start : startdate,
						end : enddate,
						allDay : true,
						color : '#feeeed'
						/* textColor : 'black'// 文字颜色 */
						});
					}
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
		url : "${vix}/common/canlendarAction!updateCanlendar.action?id=" + calEvent.id + '&startTime=' + $.fullCalendar.formatDate(startDate, 'yyyy-MM-dd HH:mm:ss') + "&endTime=" + $.fullCalendar
				.formatDate(endDate, 'yyyy-MM-dd HH:mm:ss'),
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
							$.ajax({
							url : '${vix}/common/vixIndexDataAction!goCalendar.action',
							cache : false,
							success : function(html) {
								$("#calendar").html(html);
							}
							});
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
		url : "${vix}/common/canlendarAction!updateCanlendar.action?id=" + event.id + '&startTime=' + $.fullCalendar.formatDate(startDate, 'yyyy-MM-dd HH:mm:ss') + "&endTime=" + $.fullCalendar
				.formatDate(endDate, 'yyyy-MM-dd HH:mm:ss'),
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
					$.ajax({
					url : '${vix}/common/vixIndexDataAction!goCalendar.action',
					cache : false,
					success : function(html) {
						$("#calendar").html(html);
					}
					});
				}
				});
			}
		});
	}
</script>