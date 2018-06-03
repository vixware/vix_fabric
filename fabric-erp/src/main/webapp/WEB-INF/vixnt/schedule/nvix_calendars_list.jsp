<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-calendar fa-fw "></i> 日程安排
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate();">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>日程安排</h2>
			<div class="widget-toolbar">
				<div class="btn-group">
					<button class="btn dropdown-toggle btn-xs btn-default" data-toggle="dropdown">
						日程类型 <i class="fa fa-caret-down"></i>
					</button>
					<input type="hidden" id="taskType" name="taskType" />
					<ul class="dropdown-menu js-status-update pull-right">
						<li><a href="javascript:void(0);" onclick="changecalendar('');">全部</a></li>
						<li><a href="javascript:void(0);" onclick="changecalendar('1');">任务</a></li>
						<li><a href="javascript:void(0);" onclick="changecalendar('2');">代办</a></li>
						<li><a href="javascript:void(0);" onclick="changecalendar('3');">消息</a></li>
						<li><a href="javascript:void(0);" onclick="changecalendar('4');">提醒</a></li>
						<li><a href="javascript:void(0);" onclick="changecalendar('8');">通知</a></li>
					</ul>
				</div>
				<div class="btn-group">
					<button class="btn dropdown-toggle btn-xs btn-default" data-toggle="dropdown">
						显示 <i class="fa fa-caret-down"></i>
					</button>
					<ul class="dropdown-menu js-status-update pull-right">
						<li><a href="javascript:void(0);" id="mt">月</a></li>
						<li><a href="javascript:void(0);" id="ag">周</a></li>
						<li><a href="javascript:void(0);" id="td">天</a></li>
					</ul>
				</div>
			</div>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div class="widget-body-toolbar">
					<div id="calendar-buttons">
						<div class="btn-group">
							<a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-prev"><i class="fa fa-chevron-left"></i></a> <a href="javascript:void(0)" class="btn btn-default btn-xs" id="btn-next"><i class="fa fa-chevron-right"></i></a>
						</div>
					</div>
				</div>
				<div id="calendar"></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		$('#calendar').fullCalendar({
		lang : 'zh-cn',
		header : {
		left : 'title',
		center : 'month,agendaWeek,agendaDay',
		right : 'prev,today,next'
		},
		firstDay : 1,
		editable : true,
		droppable : true,
		selectable : true,
		selectHelper : true,
		drop : function(date, allDay) {
			var originalEventObject = $(this).data('eventObject');
			$.ajax({
			type : "POST",
			url : "${nvix}/nvixnt/nvixScheduleAction!updateCanlendar.action?id=" + originalEventObject.id + '&start=' + moment(date).format('YYYY-MM-DD HH:mm:ss') + "&end=" + moment(date).format('YYYY-MM-DD HH:mm:ss'),
			success : function() {
				$('#calendar').fullCalendar('refetchEvents');
			}
			});

			var copiedEventObject = $.extend({}, originalEventObject);

			copiedEventObject.start = date;
			copiedEventObject.allDay = allDay;

			$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
		},
		eventDrop : function(calEvent, jsEvent, ui, view) {

			var startDate = calEvent.start;
			var endDate = calEvent.start;
			if (calEvent.end != null) {
				endDate = calEvent.end;
			}
			$.ajax({
			type : "POST",
			url : "${nvix}/nvixnt/nvixScheduleAction!eventDropCanlendar.action?id=" + calEvent.id + '&start=' + moment(startDate).format('YYYY-MM-DD HH:mm:ss') + "&end=" + moment(endDate).format('YYYY-MM-DD HH:mm:ss'),
			success : function() {
				$('#calendar').fullCalendar('refetchEvents');
			}
			});
		},
		select : function(start, end, allDay) {
			var params = {
			start : start,
			end : end,
			allDay : allDay
			};
			openSaveOrUpdateWindow('${nvix}/nvixnt/nvixScheduleAction!goSaveOrUpdate.action?start=' + moment(start).format('YYYY-MM-DD HH:mm:ss') + "&end=" + moment(end).format('YYYY-MM-DD HH:mm:ss'), "calendarsForm", "新增", 600, 550, null, null, function() {
				$('#calendar').fullCalendar('refetchEvents');
			});
		},
		eventClick : function(calEvent, jsEvent, view) {
			var startDate = calEvent.start;
			var endDate = calEvent.start;
			if (calEvent.end != null) {
				endDate = calEvent.end;
			}
			if (calEvent.id != null) {
				goSaveOrUpdateCalendar(calEvent.id);
			} else {
				openSaveOrUpdateWindow('${nvix}/nvixnt/nvixScheduleAction!goSaveOrUpdate.action?id=' + calEvent.id + '&start=' + moment(startDate).format('YYYY-MM-DD HH:mm:ss') + "&end=" + moment(endDate).format('YYYY-MM-DD HH:mm:ss'), "calendarsForm", "新增", 600, 550, null, null, function() {
					$('#calendar').fullCalendar('refetchEvents');
				});
			}
		},
		events : function(start, end, timezone, callback) {
			$.ajax({
			url : "${nvix}/nvixnt/nvixScheduleAction!calendarEvents.action?taskType=" + $('#taskType').val(),
			dataType : 'json',
			data : {
			start : start.format(),
			end : end.format()
			},
			success : function(doc) {
				var event = [];
				$(doc).each(function() {
					event.push({
					id : $(this).attr('id'),
					allDay : $(this).attr('allDay'),
					icon : $(this).attr('icon'),
					title : $(this).attr('title'),
					start : $(this).attr('start'),
					end : $(this).attr('end'),
					className : eval($(this).attr('className'))
					});
				});
				callback(event);
			}
			});
		},

		eventRender : function(event, element, icon) {
			if (!event.description == "") {
				element.find('.fc-event-title').append("<br/><span class='ultra-light'>" + event.description + "</span>");
			}
			if (!event.icon == "") {
				element.find('.fc-event-title').append("<i class='air air-top-right fa " + event.icon + " '></i>");
			}
		},
		eventResize : function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view) {
			var startDate = event.start;
			var endDate = event.start;
			if (event.end != null) {
				endDate = event.end;
			}
			$.ajax({
			type : "POST",
			url : "${vix}/common/canlendarAction!updateCanlendar.action?id=" + event.id + '&start=' + $.fullCalendar.formatDate(startDate, 'yyyy-MM-dd HH:mm:ss') + "&end=" + $.fullCalendar.formatDate(endDate, 'yyyy-MM-dd HH:mm:ss'),
			success : function() {
				$('#calendar').fullCalendar('refetchEvents');
			}
			});
		},
		windowResize : function(event, ui) {
			$('#calendar').fullCalendar('render');
		}
		});

		var initDrag = function(e) {
			var eventObject = {
			title : $.trim(e.children().text()), // use the element's text as the event title
			description : $.trim(e.children('span').attr('data-description')),
			icon : $.trim(e.children('span').attr('data-icon')),
			className : $.trim(e.children('span').attr('class'))
			};
			e.data('eventObject', eventObject);
			e.draggable({
			zIndex : 999,
			revert : true,
			revertDuration : 0
			});
		};
		$('#external-events > li').each(function() {
			initDrag($(this));
		});

		$('.fc-header-right, .fc-header-center').hide();

		$('#calendar-buttons #btn-prev').click(function() {
			$('.fc-button-prev').click();
			return false;
		});

		$('#calendar-buttons #btn-next').click(function() {
			$('.fc-button-next').click();
			return false;
		});

		$('#calendar-buttons #btn-today').click(function() {
			$('.fc-button-today').click();
			return false;
		});

		$('#taskid').click(function() {
			$('#calendar').fullCalendar('refetchResources');
		});

		$('#mt').click(function() {
			$('#calendar').fullCalendar('changeView', 'month');
		});
		$('#ag').click(function() {
			$('#calendar').fullCalendar('changeView', 'agendaWeek');
		});
		$('#td').click(function() {
			$('#calendar').fullCalendar('changeView', 'agendaDay');
		});
	});
	function goSaveOrUpdate() {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixScheduleAction!goSaveOrUpdate.action', "calendarsForm", "新增", 600, 550, null, null, function() {
			$('#calendar').fullCalendar('refetchEvents');
		});
	}
	function goSaveOrUpdateCalendar(id) {
		var url = "${nvix}/nvixnt/nvixScheduleAction!goSaveOrUpdate.action?id=" + id;
		var mycars = new Array("确定", "取消", "删除")
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			layer.open({
			type : 1,
			title : "修改",
			area : [ 600 + 'px', 550 + 'px' ],
			closeBtn : 1,
			content : html,
			btn : mycars,
			yes : function(index, layero) {
				$("#calendarsForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixScheduleAction!saveOrUpdate.action",
				dataType : "text",
				success : function(json) {
					layer.close(index);
					$('#calendar').fullCalendar('refetchEvents');
				}
				});
			},
			btn2 : function(index, layero) { //或者使用btn2
				layer.close(index);
			},
			btn3 : function(index, layero) {
				deleteEntityByConfirm("${nvix}/nvixnt/nvixScheduleAction!deleteCanlendarById.action?id=" + id, "是否删除该日程!", null, "删除日程", function() {
					layer.close(index);
					$('#calendar').fullCalendar('refetchEvents');
				});
			}
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("系统错误，请联系管理员");
		}
		});
	};
	function getDate(val) {
		if (val != null && val != "") {
			var d = new Date(val);
			return d;
		} else
			return "";
	};

	function changecalendar(taskType) {
		$("#taskType").val(taskType);
		$('#calendar').fullCalendar('refetchEvents');
	};
	pageSetUp();
</script>