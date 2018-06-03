<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-desktop"></i> 工作台
			</h1>
		</div>
		<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
			<ul id="u-sparks" class="clearfix">
				<li class="u-sparks-info"><a href="#" onclick="loadContent('mid_task','${nvix}/nvixnt/taskAndPlanAction!goList.action');"><img src="${nvix}/vixntcommon/base/images/clock.png">
						<div class="u-sparks-text">
							<s:if test="taskNum != null">${taskNum }</s:if>
							<s:else>0</s:else>
							<span>待完成任务</span>
						</div> </a></li>
				<li class="u-sparks-info" id="pcrAtt"><s:if test="#session.PCR_IS_DOATTENDANCE == 0">
						<a href="javascript:void(0);" id="sign" onclick="doAttendance('');"> <img src="${nvix}/vixntcommon/base/images/sign.png">
							<div class="u-sparks-text">
								<div id="nowTime"></div>
								<span id="nowDateTime"></span>
							</div>
						</a>
					</s:if> <s:elseif test="#session.PCR_IS_OUTATTENDANCE == 0">
						<a href="javascript:void(0);" id="signOut" onclick="outAttendance('');"> <img src="${nvix}/vixntcommon/base/images/signOut.png">
							<div class="u-sparks-text">
								<div id="nowTime"></div>
								<span id="nowDateTime"></span>
							</div>
						</a>
					</s:elseif> <s:else>
						<a href="javascript:void(0);">
							<div class="u-sparks-text">
								<div id="nowTime"></div>
								<span id="nowDateTime"></span>
							</div>
						</a>
					</s:else></li>
			</ul>
		</div>
	</div>
	<section id="" class="">
		<s:if test="homeTemplateDetailList.size > 0">
			<s:iterator value="homeTemplateDetailList" var="entity" status="s">
				<s:if test="#s.count%2 == 1 ">
					<div class="row">
				</s:if>
				<c:choose>
					<c:when test="${entity.code == 'NVIXNT_TASK' }">
						<article class="col-sm-12 col-md-12 col-lg-6">
							<div class="jarviswidget" id="">
								<header>
									<span class="widget-icon"> <i class="fa fa-tasks"></i></span>
									<h2>任务</h2>
									<ul class="nav nav-tabs pull-right in" id="myTab">
										<li class="active"><a data-toggle="tab" href="#s1"><span class="hidden-mobile hidden-tablet">进行中</span></a></li>
										<li><a data-toggle="tab" href="#s2"><span class="hidden-mobile hidden-tablet">未开始</span></a></li>
										<li><a data-toggle="tab" href="#s3"><span class="hidden-mobile hidden-tablet">已完成</span></a></li>
									</ul>
								</header>
								<div>
									<div class="jarviswidget-editbox"></div>
									<div class="widget-body no-padding">
										<div id="myTabContent" class="tab-content">
											<div class="tab-pane fade active in" id="s1">
												<table id="myOnvixTaskListTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
											<div class="tab-pane fade in" id="s2">
												<table id="vixTask" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
											<div class="tab-pane fade in" id="s3">
												<table id="myEndVixTaskTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</article>
					</c:when>
					<c:when test="${entity.code == 'NVIXNT_MESSAGE' }">
						<article class="col-sm-12 col-md-12 col-lg-6">
							<!-- new widget -->
							<div class="jarviswidget">
								<header>
									<span class="widget-icon"> <i class="fa fa-envelope-o"></i></span>
									<h2>提醒</h2>
									<ul class="nav nav-tabs pull-right in" id="myTab">
										<li class="active"><a data-toggle="tab" href="#d1"><span class="hidden-mobile hidden-tablet">提醒</span></a></li>
										<li><a data-toggle="tab" href="#d2"><span class="hidden-mobile hidden-tablet">通知</span></a></li>
										<!-- <li><a data-toggle="tab" href="#d3"><span class="hidden-mobile hidden-tablet">沟通会话</span></a></li> -->
									</ul>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div class="tab-content">
											<div class="tab-pane fade active in" id="d1">
												<table id="goMyToDoTaskTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
											<div class="tab-pane fade in" id="d2">
												<table id="trendsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</article>
					</c:when>
					<c:when test="${entity.code == 'NVIXNT_PROJECT' }">
						<article class="col-sm-12 col-md-12 col-lg-6">
							<div class="jarviswidget">
								<header>
									<span class="widget-icon"> <i class="fa fa-book"></i></span>
									<h2>项目</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<table id="vixProjectTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
						</article>
					</c:when>
					<c:when test="${entity.code == 'NVIXNT_DOCUMENT' }">
						<article class="col-sm-12 col-md-12 col-lg-6">
							<div class="jarviswidget">
								<header>
									<span class="widget-icon"> <i class="fa fa-file-o"></i></span>
									<h2>我的文档</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<table id="vixDocTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
						</article>
					</c:when>
					<c:when test="${entity.code == 'NVIXNT_CALENDAR' }">
						<article class="col-sm-12 col-md-12 col-lg-6">
							<div class="jarviswidget">
								<header>
									<span class="widget-icon"> <i class="fa fa-calendar"></i></span>
									<h2>日程安排</h2>
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
						</article>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<s:if test="#s.count%2 == 0 ">
				</s:if>
			</s:iterator>
		</s:if>
		<s:else>
			<!-- row -->
			<div class="row">
				<article class="col-sm-12 col-md-12 col-lg-6">
					<div class="jarviswidget" id="">
						<header>
							<span class="widget-icon"> <i class="fa fa-tasks"></i></span>
							<h2>任务</h2>
							<ul class="nav nav-tabs pull-right in" id="myTab">
								<li class="active"><a data-toggle="tab" href="#s1"><span class="hidden-mobile hidden-tablet">进行中</span></a></li>
								<li><a data-toggle="tab" href="#s2"><span class="hidden-mobile hidden-tablet">未开始</span></a></li>
								<li><a data-toggle="tab" href="#s3"><span class="hidden-mobile hidden-tablet">已完成</span></a></li>
							</ul>
						</header>
						<div>
							<div class="jarviswidget-editbox"></div>
							<div class="widget-body no-padding">
								<div id="myTabContent" class="tab-content">
									<div class="tab-pane fade active in" id="s1">
										<table id="myOnvixTaskListTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
									<div class="tab-pane fade in" id="s2">
										<table id="vixTask" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
									<div class="tab-pane fade in" id="s3">
										<table id="myEndVixTaskTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</article>
				<article class="col-sm-12 col-md-12 col-lg-6">
					<!-- new widget -->
					<div class="jarviswidget" id="">
						<header>
							<span class="widget-icon"> <i class="fa fa-envelope-o"></i></span>
							<h2>提醒</h2>
							<ul class="nav nav-tabs pull-right in" id="myTab">
								<li class="active"><a data-toggle="tab" href="#d1"><span class="hidden-mobile hidden-tablet">提醒</span></a></li>
								<li><a data-toggle="tab" href="#d2"><span class="hidden-mobile hidden-tablet">通知</span></a></li>
							</ul>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div class="tab-content">
									<div class="tab-pane fade active in" id="d1">
										<table id="goMyToDoTaskTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
									<div class="tab-pane fade in" id="d2">
										<table id="trendsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</article>
			</div>
			<div class="row">
				<article class="col-sm-12 col-md-12 col-lg-6">
					<div class="jarviswidget">
						<header>
							<span class="widget-icon"> <i class="fa fa-calendar"></i></span>
							<h2>日程安排</h2>
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
				</article>
				<article class="col-sm-12 col-md-12 col-lg-6">
					<div class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-file-text-o"></i></span>
							<h2>待办审批</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<table id="vixJobTodoTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</article>
				<article class="col-sm-12 col-md-12 col-lg-6">
					<div class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-book"></i></span>
							<h2>项目</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<table id="vixProjectTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</article>
			</div>
			<div class="row">
				<article class="col-sm-12 col-md-12 col-lg-6">
					<div class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-file-o"></i></span>
							<h2>我的文档</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<table id="vixDocTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</article>
				<article class="col-sm-12 col-md-12 col-lg-6">
					<div class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-bullhorn"></i></span>
							<h2>公告</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<table id="vixNoticeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</article>
			</div>
			<div class="row">
				<article class="col-sm-12 col-md-12 col-lg-6">
					<div class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-hacker-news"></i></span>
							<h2>新闻</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<table id="vixNewsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</article>
			</div>
			<div class="row">
				<article class="col-sm-12 col-md-12 col-lg-6">
					<div class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-hacker-news"></i></span>
							<h2>征集</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<table id="vixCollectTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</article>
			</div>
		</s:else>
	</section>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
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
		var calendar = $('#calendar').fullCalendar({
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
			openSaveOrUpdateWindow('${nvix}/nvixnt/nvixScheduleAction!goSaveOrUpdate.action?start=' + moment(start).format('YYYY-MM-DD HH:mm:ss') + "&end=" + moment(end).format('YYYY-MM-DD HH:mm:ss'), "calendarsForm", "添加", 600, 550, null, null, function() {
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
				openSaveOrUpdateWindow('${nvix}/nvixnt/nvixScheduleAction!goSaveOrUpdate.action?id=' + calEvent.id + "&start=" + moment(startDate).format('YYYY-MM-DD HH:mm:ss') + "&end=" + moment(endDate).format('YYYY-MM-DD HH:mm:ss'), "calendarsForm", "添加", 600, 550, null, null, function() {
					$('#calendar').fullCalendar('refetchEvents');
				});
			}
		},
		events : function(start, end, timezone, callback) {
			$.ajax({
			url : '${nvix}/nvixnt/nvixScheduleAction!calendarEvents.action',
			dataType : 'json',
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

	function goSaveOrUpdateCalendar(id) {
		var url = "${nvix}/nvixnt/nvixScheduleAction!goSaveOrUpdate.action?id=" + id;
		var mycars = new Array("确定", "取消", "删除")
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			layer.open({
			type : 1,
			title : "新增",
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
	}

	function getDate(val) {
		if (val != null && val != "") {
			var d = new Date(val);
			return d;
		} else {
			return "";
		}
	};

	var vixTaskColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"width" : "30%",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'>" + data.questName + "</a>";
	}
	}, {
	"title" : "结束时间",
	"width" : "25%",
	"data" : function(data) {
		return data.taskEndTimeTimeStr;
	}
	}, {
	"title" : "进度",
	"width" : "20%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		var feedback = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateExecutionFeedback('" + data.id + "');\" title='反馈'><span class='txt-color-blue pull-right'><i class='fa fa-comment-o'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		return show + "&nbsp;&nbsp;" + feedback;
	}
	} ];

	var vixTaskTable = initDataTable("vixTask", "${nvix}/nvixnt/vixNtIndexAction!goMyUnVixTaskList.action", vixTaskColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});

	var myOnvixTaskColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"width" : "30%",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'>" + data.questName + "</a>";
	}
	}, {
	"title" : "结束时间",
	"width" : "25%",
	"data" : function(data) {
		return data.taskEndTimeTimeStr;
	}
	}, {
	"title" : "进度",
	"width" : "20%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		var feedback = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateExecutionFeedback('" + data.id + "');\" title='反馈'><span class='txt-color-blue pull-right'><i class='fa fa-comment-o'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		return show + "&nbsp;&nbsp;" + feedback;
	}
	} ];

	var myOnvixTaskTable = initDataTable("myOnvixTaskListTableId", "${nvix}/nvixnt/vixNtIndexAction!goMyOnVixTaskList.action", myOnvixTaskColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	var goMyEndVixTaskColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"width" : "30%",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'>" + data.questName + "</a>";
	}
	}, {
	"title" : "结束时间",
	"width" : "25%",
	"data" : function(data) {
		return data.taskEndTimeTimeStr;
	}
	}, {
	"title" : "进度",
	"width" : "20%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		var feedback = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateExecutionFeedback('" + data.id + "');\" title='反馈'><span class='txt-color-blue pull-right'><i class='fa fa-comment-o'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		return show + "&nbsp;&nbsp;" + feedback;
	}
	} ];

	var myEndVixTaskTable = initDataTable("myEndVixTaskTableId", "${nvix}/nvixnt/vixNtIndexAction!goMyEndVixTaskList.action", goMyEndVixTaskColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	//我的代办
	var goMyToDoTaskColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"width" : "25%",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'>" + data.questName + "</a>";
	}
	}, {
	"title" : "结束时间",
	"width" : "25%",
	"data" : function(data) {
		return data.taskEndTimeTimeStr;
	}
	}, {
	"title" : "进度",
	"width" : "25%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		var feedback = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateExecutionFeedback('" + data.id + "');\" title='反馈'><span class='txt-color-blue pull-right'><i class='fa fa-comment-o'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		return show + "&nbsp;&nbsp;" + feedback;
	}
	} ];

	var myToDoTaskTable = initDataTable("goMyToDoTaskTableId", "${nvix}/nvixnt/vixNtIndexAction!goAlertNoticeList.action", goMyToDoTaskColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});

	var trendsColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"width" : "60%",
	"data" : function(data) {
		if (data.id) {
			return "<a href='javascript:void(0);' onclick=\"goNotice('" + data.id + "');\" title='查看'>" + data.title + "</a>";
		}
		return "&nbsp;";
	}
	}, {
	"title" : "时间",
	"width" : "25%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	} ];

	var trendsTable = initDataTable("trendsTableId", "${nvix}/nvixnt/vixNtIndexAction!goNoticeList.action", trendsColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});

	function goTrends(id) {
		$.ajax({
		url : '${nvix}/nvixnt/newsAdministrationAction!goNewsDetail.action?id=' + id + "&syncTag=home",
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};

	var noticeColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"width" : "60%",
	"data" : function(data) {
		if (data.id) {
			return "<a href='javascript:void(0);' onclick=\"goNotice('" + data.id + "');\" title='查看'>" + data.title + "</a>";
		}
		return "&nbsp;";
	}
	}, {
	"title" : "时间",
	"width" : "25%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	} ];

	var noticeTable = initDataTable("vixNoticeTableId", "${nvix}/nvixnt/vixNtIndexAction!goNoticeList.action", noticeColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
    /** 我的征集*/
	var noticeColumns = [ {
		"orderable" : false,
		"title" : "编号",
		"width" : "10%",
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "主题",
		"width" : "60%",
		"data" : function(data) {
			if (data.id) {
				return "<a href='javascript:void(0);' onclick=\"goCollectDetail('" + data.id + "');\" title='查看'>" + data.scheduleName + "</a>";
			}
			return "&nbsp;";
		}
		}, {
		"title" : "时间",
		"width" : "25%",
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
		} ];

		var noticeTable = initDataTable("vixCollectTableId", "${nvix}/nvixnt/vixNtIndexAction!goCollect.action", noticeColumns, function(page, pageSize, orderField, orderBy) {
			return {
			"page" : page,
			"pageSize" : pageSize,
			"orderField" : orderField,
			"orderBy" : orderBy
			};
	});
	function goCollectDetail(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixNtIndexAction!goCollectDetail.action?id=' + id + "&syncTag=home",
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
	function goNotice(id) {
		$.ajax({
		url : '${nvix}/nvixnt/noticeAction!goViewNotice.action?id=' + id + "&syncTag=home",
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	var newsColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"width" : "60%",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"goTrends('" + data.id + "');\" title='查看'>" + data.title + "</a>";
	}
	}, {
	"title" : "时间",
	"width" : "25%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	} ];

	var newsTable = initDataTable("vixNewsTableId", "${nvix}/nvixnt/vixNtIndexAction!goTrendsList.action", newsColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});

	var jobTodoColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "代办标题",
	"data" : function(data) {
		if (data.id) {
			return "<a href='javascript:void(0);' onclick=\"goAudit('" + data.id + "');\" title='审批'>" + data.jobName + "</a>";
		}
		return "&nbsp;";
	}
	}, {
	"title" : "单据主题",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "时间",
	"width" : "25%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		if (data.id) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goAudit('" + data.id + "');\" title='审批'><span class='txt-color-red pull-right'><i class='fa icon-iconfont-employee'></i></span></a>";
			return update;
		}
		return "&nbsp;";
	}
	} ];

	var jobTodoTable = initDataTable("vixJobTodoTableId", "${nvix}/nvixnt/vreport/nvixJobTodoAction!goHomeSingleList.action", jobTodoColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	});

	var projectColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "项目名称",
	"width" : "20%",
	"data" : function(data) {
		if(data != null && data.projectName != null){
			return "<a href='javascript:void(0);' onclick=\"goProjectDetail('" + data.id + "');\" title='查看'>" + data.projectName + "</a>";
		}else{
			return '';
		}
	}
	}, {
	"title" : "执行人",
	"width" : "25%",
	"data" : function(data) {
		if (data.id) {
			// return data == null ? '' : "<div class='project-members'>" + data.empliststr + "</div>";
			return data.employeeNames
		}
		return "&nbsp;";
	}
	}, {
	"title" : "进度",
	"width" : "20%",
	"data" : function(data) {
		if (data.id) {
			return data == null ? '' : "<div data-progressbar-value='"+data.projectSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
		}
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		if (data.id) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectDetail('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		}
		return "&nbsp;";
	}
	} ];

	var projectTable = initDataTable("vixProjectTableId", "${nvix}/nvixnt/nvixProjectAction!goSingleList.action", projectColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});

	var vixDocColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "文档名称",
	"width" : "35%",
	"data" : function(data) {
		if (data.id) {
			return "<a href='javascript:void(0);' onclick=\"goDoc('" + data.communication.id + "');\" title='查看'>" + data.fileName + "</a>";
		}
		return "&nbsp;";
	}
	}, {
	"title" : "发送人",
	"width" : "10%",
	"data" : function(data) {
		return data.employeeName;
	}
	}, {
	"title" : "发送时间",
	"width" : "20%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		if (data.id) {
			var download = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"downloadUploader('" + data.id + "');\" title='下载'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-download-alt'></i></span></a>";
			return download;
		}
		return "&nbsp;";
	}
	} ];

	var vixDocTable = initDataTable("vixDocTableId", "${nvix}/nvixnt/nvixFileAction!goSingleDocList.action", vixDocColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	});
	function goDoc(id) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixFileAction!goSaveOrUpdate.action?id=' + id + "&source=home",
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function getCurDate() {
		var d = new Date();
		var week;
		switch (d.getDay()) {
		case 1:
			week = "星期一";
			break;
		case 2:
			week = "星期二";
			break;
		case 3:
			week = "星期三";
			break;
		case 4:
			week = "星期四";
			break;
		case 5:
			week = "星期五";
			break;
		case 6:
			week = "星期六";
			break;
		default:
			week = "星期天";
		}
		var years = d.getFullYear();
		var month = add_zero(d.getMonth() + 1);
		var days = add_zero(d.getDate());
		var hours = add_zero(d.getHours());
		var minutes = add_zero(d.getMinutes());
		var seconds = add_zero(d.getSeconds());
		var ndate = years + "-" + month + "-" + days + " " + week;
		$("#nowDateTime").html(ndate)
	};

	function add_zero(temp) {
		if (temp < 10)
			return "0" + temp;
		else
			return temp;
	};
	function currentTime() {
		var d = new Date();
		var str = '';
		str += add_zero(d.getHours()) + ':';
		str += add_zero(d.getMinutes()) + ':';
		str += add_zero(d.getSeconds());
		return str;
	}
	getCurDate();
	setInterval(function() {
		$("#nowTime").html(currentTime)
	}, 1000);

	function goProjectTaskDetails(id) {
		loadContent('mid_projectstask', '${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=' + id + "&source=home");
	};
	function goProjectDetail(id) {
		loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectDetail.action?id=' + id + "&source=home");
	};
	//新增反馈
	function goSaveOrUpdateExecutionFeedback(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateExecutionFeedback.action?id=' + id, "executionFeedbackForm", "反馈", 750, 350, vixTaskTable, null, function() {
			vixTaskTable.ajax.reload();
			myOnvixTaskTable.ajax.reload();
			myEndVixTaskTable.ajax.reload();
		});
	};

	//签到
	function doAttendance() {
		$.ajax({
		type : "POST",
		url : "${nvix}/nvixnt/attendanceManagementAction!doAttendance.action",
		success : function(result) {
			var message = result.split(",");
			if ('0' != message[0]) {
				layer.alert(message[0] + " " + message[2], function(index) {
					$("#pcrAtt").html("<a href='javascript:void(0);' id='signOut' onclick='outAttendance();'>" + "<img src='${nvix}/vixntcommon/base/images/signOut.png'>" + "<div class='u-sparks-text'><div id='nowTime'></div><span id='nowDateTime'></span></div></a>");
					getCurDate();
					layer.close(index);
				});
			} else {
				layer.alert("签到失败,请联系管理员!", function(index) {
					layer.close(index);
				});
			}
		}
		});
	}
	//签退
	function outAttendance() {
		$.ajax({
		type : "POST",
		url : "${nvix}/nvixnt/attendanceManagementAction!outAttendance.action",
		success : function(result) {
			var message = result.split(",");
			if ('0' != message[0]) {
				layer.alert(message[0] + " " + message[2], function(index) {
					$("#pcrAtt").html("<a href='javascript:void(0);'><div class='u-sparks-text'><div id='nowTime'></div><span id='nowDateTime'></span></div></a>");
					getCurDate();
					layer.close(index);
				});
			} else {
				layer.alert("签到失败,请联系管理员!", function(index) {
					layer.close(index);
				});
			}
		}
		});
	};
	function downloadUploader(id) {
		var url = "${nvix}/nvixnt/nvixFileAction!downloadUploader.action?id=" + id;
		window.open(url);
	};

	function goAudit(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vreport/nvixJobTodoAction!goBillAudit.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>