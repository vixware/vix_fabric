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
				<li><a href="#"><img src="img/mdm_meetingRequest.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/meetingRequestAction!goList.action?pageNo=${pageNo}');">会议室管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/meetingRequestAction!goList.action?pageNo=${pageNo}');">会议室申请</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="roomId" value="${id}" />
<div class="content" style="margin-top: 10px;">
	<div id='meetingRoom'></div>
</div>
<style>
.fc-event, .fc-agenda .fc-event-time, .fc-event-skin, .fc-event-title,
	.fc-event-inner   a {
	background-color: green;
	border-color: green;
	color: yellow;
}
</style>
<script type="text/javascript">
	var meetingRoom = $('#meetingRoom')
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
	                    url : '${vix}/oa/meetingRoomAction!goSaveOrUpdate.action?start=' + $.fullCalendar.formatDate(start, 'yyyy-MM-dd HH:mm:ss') + '&end=' + $.fullCalendar
	                            .formatDate(end, 'yyyy-MM-dd HH:mm:ss'),
	                    cache : false,
	                    success : function(html) {
		                    asyncbox.open({
		                    modal : true,
		                    width : 650,
		                    height : 475,
		                    title : "会议申请",
		                    html : html,
		                    btnsbar : $.btn.OKCANCEL,
		                    callback : function(action) {
			                    if (action == 'ok') {
				                    if ($('#meetingRoomform').validationEngine('validate')) {
					                    $.post('${vix}/oa/meetingRoomAction!saveOrUpdate.action', {
					                	'meetingRoom.meetingRequest.id':$('#roomId').val(), 
					                    'meetingRoom.meetingName' : $("#meetingName").val(),
					                    'meetingRoom.content' : $("#schedulecontent").val(),
					                    'meetingRoom.bookingSituation':$('input:radio[name=bookingSituation]').val(), 
					                    'meetingRoom.startTime' : $("#startTime").val(),
					                    'meetingRoom.endTime' : $("#endTime").val(),
					                    'meetingRoom.isRemind' : $("#isRemind").val(),
					                    'meetingRoom.remindTime' : $("#remindTime").val(),
					                    'meetingRoom.isRepeat' : $("#isRepeat").val(),
					                    'meetingRoom.allDay' : $(":checkbox[name=allDay][checked]").val()
					                    }, function(result) {
						                    showMessage(result);
						                    loadContent('${vix}/oa/meetingRoomAction!goMeetingRoom.action');
						                    meetingRoom.fullCalendar('unselect');
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
	            url : "${vix}/oa/meetingRoomAction!calendarEvents.action",
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
				            title : res.rows[i].meetingName,
				            start : startdate,
				            end : enddate,
				            allDay : false
				            });
			            } else {
				            events.push({
				            id : res.rows[i].id,
				            title : res.rows[i].meetingName,
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
	            url : "${vix}/oa/meetingRoomAction!updateCanlendar.action?id=" + calEvent.id + '&startTime=' + $.fullCalendar
	                    .formatDate(startDate, 'yyyy-MM-dd HH:mm:ss') + "&endTime=" + $.fullCalendar.formatDate(endDate, 'yyyy-MM-dd HH:mm:ss'),
	            success : function() {
		            meetingRoom.fullCalendar('unselect');
	            }
	            });
            },
            eventClick : function(calEvent,jsEvent,view) {
	            $.ajax({
	            url : '${vix}/oa/meetingRoomAction!goSaveOrUpdate.action?id=' + calEvent.id,
	            cache : false,
	            success : function(html) {
		            asyncbox.open({
		            modal : true,
		            width : 650,
		            height : 450,
		            title : "会议申请",
		            html : html,
		            callback : function(action) {
			            if (action == 'ok') {
				            if ($('#meetingRoomform').validationEngine('validate')) {
					            $.post('${vix}/oa/meetingRoomAction!saveOrUpdate.action', {
					            'meetingRoom.meetingRequest.id':$('#roomId').val(), 
					            'meetingRoom.meetingName' : $("#meetingName").val(),
					            'meetingRoom.content' : $("#schedulecontent").val(),
					            'meetingRoom.startTime' : $("#startTime").val(),
					            'meetingRoom.bookingSituation':$('input:radio[name=bookingSituation]').val(), 
					            'meetingRoom.endTime' : $("#endTime").val(),
					            'meetingRoom.isRemind' : $("#isRemind").val(),
					            'meetingRoom.remindTime' : $("#remindTime").val(),
					            'meetingRoom.isRepeat' : $("#isRepeat").val(),
					            'meetingRoom.allDay' : $(":checkbox[name=allDay][checked]").val()
					            }, function(result) {
						            showMessage(result);
						            loadContent('${vix}/oa/meetingRoomAction!goMeetingRoom.action');
						            meetingRoom.fullCalendar('unselect');
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
            eventResize : function(event,dayDelta,minuteDelta,revertFunc,jsEvent,ui,view) {
	            var startDate = event.start;
	            var endDate = event.start;
	            if (event.end != null) {
		            endDate = event.end;
	            }
	            $.ajax({
	            type : "POST",
	            url : "${vix}/oa/meetingRoomAction!updateCanlendar.action?id=" + event.id + '&startTime=' + $.fullCalendar
	                    .formatDate(startDate, 'yyyy-MM-dd HH:mm:ss') + "&endTime=" + $.fullCalendar.formatDate(endDate, 'yyyy-MM-dd HH:mm:ss'),
	            success : function() {
		            meetingRoom.fullCalendar('unselect');
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
	    asyncbox.confirm('确定要删除该会议申请吗?', '提示信息', function(action) {
		    if (action == 'ok') {
			    $.ajax({
			    url : '${vix}/oa/meetingRoomAction!deleteCanlendarById.action?id=' + id,
			    cache : false,
			    success : function(html) {
				    showMessage(html);
				    loadContent('${vix}/oa/meetingRoomAction!goMeetingRoom.action');
			    }
			    });
		    }
	    });
    }
</script>