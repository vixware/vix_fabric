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
				<li><a href="#"><img src="${vix}/common/img/schedule.png" class="png" alt="" width="26" height="26" />&nbsp;工作台</a></li>
				<li><a href="#">日程安排</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content" style="margin-top: 10px;">
	<div id='calendar'></div>
</div>
<script type="text/javascript">
	//面包屑
    if ($('.sub_menu').length) {
	    $("#breadCrumb").jBreadCrumb();
    }
    var calendar = $('#calendar')
            .fullCalendar({
            header : {
            left : 'prev,next today',
            center : 'title',
            right : 'month,agendaWeek,agendaDay'
            },
            selectable : true,
            selectHelper : true,
            select : function(start,end,allDay) {
	            var params = {
	            start : start,
	            end : end,
	            allDay : allDay
	            };
	            $.ajax({
	                    url : '${vix}/crm/agenda/calendarsAction!goSaveOrUpdate.action?start=' + $.fullCalendar.formatDate(start, 'yyyy-MM-dd HH:mm:ss') + '&end=' + $.fullCalendar.formatDate(end, 'yyyy-MM-dd HH:mm:ss'),
	                    cache : false,
	                    success : function(html) {
		                    asyncbox.open({
		                    modal : true,
		                    width : 650,
		                    height : 450,
		                    title : "日程",
		                    html : html,
		                    callback : function(action) {
			                    if (action == 'ok') {
				                    if ($('#calendarsform').validationEngine('validate')) {
					                    $.post('${vix}/crm/agenda/calendarsAction!saveOrUpdate.action', {
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
						                    loadContent('${vix}/crm/agenda/calendarsAction!goCanlendar.action');
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
            events : function(start,end,callback) {
	            var params = {};
	            $.ajax({
	            url : "${vix}/crm/agenda/calendarsAction!calendarEvents.action",
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
            eventDragStart : function(calEvent,jsEvent,ui,view) {
	            //日程事件被拖动之前和之后触发.  这里的拖动不一定是一个有效的拖动 
            },
            eventDragStop : function(calEvent,jsEvent,ui,view) {
	            //日程事件被拖动之前和之后触发.  这里的拖动不一定是一个有效的拖动 
            },
            eventDrop : function(calEvent,jsEvent,ui,view) {

	            var startDate = calEvent.start;
	            var endDate = calEvent.start;
	            if (calEvent.end != null) {
		            endDate = calEvent.end;
	            }

	            $.ajax({
	            type : "POST",
	            url : "${vix}/crm/agenda/calendarsAction!updateCanlendar.action?id=" + calEvent.id + '&startTime=' + $.fullCalendar
	                    .formatDate(startDate, 'yyyy-MM-dd HH:mm:ss') + "&endTime=" + $.fullCalendar.formatDate(endDate, 'yyyy-MM-dd HH:mm:ss'),
	            success : function() {
		            calendar.fullCalendar('unselect');
	            }
	            });
            },
            eventResize : function(event,dayDelta,minuteDelta,revertFunc,jsEvent,ui,view) {
	            var startDate = event.start;
	            var endDate = event.start;
	            if (event.end != null) {
		            endDate = event.end;
	            }

	            $.ajax({
	            type : "POST",
	            url : "${vix}/crm/agenda/calendarsAction!updateCanlendar.action?id=" + event.id + '&startTime=' + $.fullCalendar
	                    .formatDate(startDate, 'yyyy-MM-dd HH:mm:ss') + "&endTime=" + $.fullCalendar.formatDate(endDate, 'yyyy-MM-dd HH:mm:ss'),
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
</script>