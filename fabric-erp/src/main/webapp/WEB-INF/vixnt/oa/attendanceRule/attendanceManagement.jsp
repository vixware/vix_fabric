<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Date"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-home"></i> 我的考勤
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button id="doAtten" class="btn btn-primary" type="button" onclick="doAttendance('');" <s:if test="#session.PCR_IS_DOATTENDANCE == 1"> disabled="disabled"</s:if>>
						<i class="fa fa-share"></i>&nbsp;
						<s:text name="签到" />
					</button>
				</a> <a data-toggle="modal" href="#">
					<button id="outAtten" class="btn btn-primary" type="button" onclick="outAttendance('');" <s:if test="#session.PCR_IS_OUTATTENDANCE == 1"> disabled="disabled"</s:if>>
						<i class="fa fa-reply"></i>&nbsp;
						<s:text name="签退" />
					</button>
				</a>
			</div>
		</div>
	</div>
	<section class="">
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false">
					<header>
						<h2>考勤管理</h2>
						<div class="pull-right">
							<ul class="nav nav-tabs in" id="myTab">
								<li class="active"><a data-toggle="tab" href="#s1"><span class="hidden-mobile hidden-tablet">考勤记录</span></a></li>
							</ul>
						</div>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade active in clearfix" id="s1">
									<div class="col-sm-12 col-md-12 col-lg-8">
										<br>
										<div class="row">
											<div class="col-sm-6 col-md-4 col-lg-3 text-center">完成考勤：${times }次</div>
											<div class="col-sm-6 col-md-4 col-lg-2 text-center">迟到：${lates }次</div>
											<div class="col-sm-6 col-md-4 col-lg-2 text-center">早退：${earlys }次</div>
											<div class="col-sm-6 col-md-4 col-lg-2 text-center">缺卡：${lacks }次</div>
											<div class="col-sm-6 col-md-4 col-lg-3 text-center">旷工：${absenteeisms }次</div>
										</div>
										<div>
											<br>

											<div class="jarviswidget">
												<header>
													<div class="widget-toolbar">
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
										<br>
									</div>
									<div class="col-sm-12 col-md-12 col-lg-4">
										<br>
										<!-- <div class="alert alert-block alert-warning">
													<h4 class="alert-heading">工作时间：09：00-18：00</h4>
												</div> -->
										<h1 id="times">
											<small id="weekday" class="pull-right text-primary"><div id="weekday"></div></small>
										</h1>
										<div class="row">
											<div class="col-sm-4 col-md-4 col-lg-4">
												<div id="hours" class="time">
													<span class="time-mask"></span>
												</div>
											</div>
											<div class="col-sm-4 col-md-4 col-lg-4">
												<div id="minus" class="time">
													<span class="time-mask"></span>
												</div>
											</div>
											<div class="col-sm-4 col-md-4 col-lg-4">
												<div id="secs" class="time">
													<span class="time-mask"></span>
												</div>
											</div>
										</div>
										<div class="row" id="attendanceTime">
											<div class="col-sm-12 col-md-12 col-lg-12">
												<h5 class="pull-left" id="doAtt">
													签到时间：
													<s:if test="#session.PCR_IS_DOATTENDANCE == 1">
														<span class="label label-success">${PCR_DOATTENDANCE_TIME}</span>
													</s:if>
													<s:else>未签到</s:else>
												</h5>
												<h5 class="pull-right" id="outAtt">
													签退时间：
													<s:if test="#session.PCR_IS_OUTATTENDANCE == 1">
														<span class="label label-success">${PCR_OUTATTENDANCE_TIME}</span>
													</s:if>
													<s:else>未签退</s:else>
												</h5>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12 col-md-12 col-lg-12">
												<ul class="timelist">
													<s:iterator value="pcrList" var="key" status="s">
														<li>
															<div class="timelist_l pull-left">
																<div class="timelist_t" style="line-height: 79px;">${key}</div>
															</div>
															<div class="timelist_body">
																<s:iterator value="pMap[#key]" var="entity">
																	<div>
																		<span class="text-muted">打卡时间：${fn:substring(entity.punchCardDate, 11,19)}</span> <span class="text-muted">IP：${entity.ipAddress}</span>
																	</div>
																</s:iterator>
															</div>
														</li>
													</s:iterator>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var times = document.getElementById("times");
	var weekday = document.getElementById("weekday");
	var hours = document.getElementById("hours");
	var minus = document.getElementById("minus");
	var secs = document.getElementById("secs");

	function realSysTime(times, weekday, hours, minus, secs) {
		var now = new Date(); //创建Date对象
		var year = now.getFullYear(); //获取年份
		var month = now.getMonth(); //获取月份
		var date = now.getDate(); //获取日期
		var day = now.getDay(); //获取星期
		var hour = now.getHours(); //获取小时
		var minu = now.getMinutes(); //获取分钟
		var sec = now.getSeconds(); //获取秒钟
		month = month + 1;
		if (hour <= 9) {
			hour = "0" + hour;
		}
		if (minu <= 9) {
			minu = "0" + minu;
		}
		if (sec <= 9) {
			sec = "0" + sec;
		}
		var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
		var week = arr_week[day]; //获取中文的星期
		var time = year + "&nbsp;年&nbsp;" + month + "&nbsp;月&nbsp;" + date + "&nbsp;日&nbsp;" + "-&nbsp;&nbsp;" + week;
		times.innerHTML = time; //日期
		weekday.innerHTML = week; //星期
		hours.innerHTML = hour; //小时
		minus.innerHTML = minu; //分
		secs.innerHTML = sec; //秒
	};

	$(document).ready(function() {
		window.setInterval("realSysTime(times,weekday,hours,minus,secs)", 1000); //实时获取并显示系统时间

		var addEvent = function(title, priority, description, icon) {
			title = title.length === 0 ? "Untitled Event" : title;
			description = description.length === 0 ? "No Description" : description;
			icon = icon.length === 0 ? " " : icon;
			priority = priority.length === 0 ? "label label-default" : priority;

			var html = $('<li><span class="' + priority + '" data-description="' + description + '" data-icon="' +
                 icon + '">' + title + '</span></li>').prependTo('ul#external-events').hide().fadeIn();

			$("#event-container").effect("highlight", 800);

			initDrag(html);
		};
		var initDrag = function(e) {

			var eventObject = {
			title : $.trim(e.children().text()),
			description : $.trim(e.children('span').attr('data-description')),
			icon : $.trim(e.children('span').attr('data-icon')),
			className : $.trim(e.children('span').attr('class')),
			id : $.trim(e.children('span').attr('value'))
			};
			e.data('eventObject', eventObject);

			e.draggable({
			zIndex : 999,
			revert : true,
			revertDuration : 0
			});
		};
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		var hdr = {
		left : 'title',
		center : 'month,agendaWeek,agendaDay',
		right : 'prev,today,next'
		};

		$('#external-events > li').each(function() {
			initDrag($(this));
		});

		var calendar = $('#calendar').fullCalendar({

		header : hdr,
		buttonText : {
		prev : '<i class="fa fa-chevron-left"></i>',
		next : '<i class="fa fa-chevron-right"></i>'
		},

		editable : true,
		droppable : true,
		selectable : true,
		selectHelper : true,
		drop : function(date, allDay) {

			var originalEventObject = $(this).data('eventObject');
			var startDate = date;
			var endDate = date;

			var copiedEventObject = $.extend({}, originalEventObject);

			copiedEventObject.start = date;
			copiedEventObject.allDay = allDay;

			$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

			if ($('#drop-remove').is(':checked')) {
				var eventObject = $(this).data('eventObject');
				$(this).remove();
			}

		},

		events : function(start, end, timezone, callback) {
			$.ajax({
			url : "${nvix}/nvixnt/attendanceManagementAction!calendarEvents.action",
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
				element.find('.fc-event-title').append("<i class='air air-top-right fa " + event.icon +
                         " '></i>");
			}
		},

		windowResize : function(event, ui) {
			$('#calendar').fullCalendar('render');
		}
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

	function getDate(val) {
		if (val != null && val != "") {
			var d = new Date(val);
			return d;
		} else
			return "";
	};

	function doAttendance() {
		$.ajax({
		type : "POST",
		url : "${nvix}/nvixnt/attendanceManagementAction!doAttendance.action",
		success : function(result) {
			var message = result.split(",");
			if ('0' != message[0]) {
				$("#doAtten").attr("disabled", true);
			}
			layer.alert(message[2], function(index) {
				layer.close(index);
				$("#doAtt").html("<h5 class='pull-left' id='doAtt'>签到时间：<span class='label label-success'>" + message[0] + "</span></h5>");
			});
		}
		});
	};
	function outAttendance() {
		$.ajax({
		type : "POST",
		url : "${nvix}/nvixnt/attendanceManagementAction!outAttendance.action",
		success : function(result) {
			var message = result.split(",");
			if ('0' != message[0]) {
				$("#outAtten").attr("disabled", true);
			}
			layer.alert(message[2], function(index) {
				layer.close(index);
				$("#outAtt").html("<h5 class='pull-right' id='outAtt'>签退时间：<span class='label label-success'>" + message[0] + "</span></h5>");
			});
		}
		});
	};
</script>