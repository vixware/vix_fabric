<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-desktop"></i> 办公 <span>> 日报统计</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="dealAttendance();">
					<i class="glyphicon glyphicon-list"></i>&nbsp;统计
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/dailyJournalAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article class="col-sm-12 col-md-12 col-lg-6">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>日报统计</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										姓名: <input type="text" value="" class="form-control" id="empName">
									</div>
									<button onclick="taskStatisticsTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#empName').val('');taskStatisticsTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="taskStatisticsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
			<article class="col-sm-12 col-md-12 col-lg-6">
				<div class="jarviswidget">
					<header>
						<h2>日报统计</h2>
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
		</div>
	</section>
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
		defaultView : 'basicWeek',
		//defaultView : 'basicDay',
		//defaultView : 'agendaDay',
		firstDay : 1,
		editable : true,
		selectable : true,
		selectHelper : true,
		eventClick : function(calEvent, jsEvent, view) {
			if (calEvent.id != null) {
				goSaveOrUpdateCalendar(calEvent.id);
			}
		},
		events : function(start, end, timezone, callback) {
			$.ajax({
			url : "${nvix}/nvixnt/dailyJournalAction!workLogEvents.action?empId=" + $('#selectId').val() + "&treeType=" + $('#selectTreeType').val(),
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
			$('#calendar').fullCalendar('changeView', 'basicWeek');
		});
		$('#td').click(function() {
			$('#calendar').fullCalendar('changeView', 'basicDay');
		});
	});
	function getDate(val) {
		if (val != null && val != "") {
			var d = new Date(val);
			return d;
		} else
			return "";
	};
	function dealAttendance() {
		$.ajax({
		url : '${nvix}/nvixnt/wxpWorkLogStatisticsAction!dealWorkLogStatistics.action',
		cache : false,
		success : function() {
			taskStatisticsTable.ajax.reload();
		}
		});
	};
	var taskStatisticsColumns = [ {
	"width" : "10%",
	"title" : "编号",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "姓名",
	"data" : function(data) {
		return data.empName;
	}
	}, {
	"title" : "月份",
	"data" : function(data) {
		return data.datetemp;
	}
	}, {
	"title" : "未编写日报天数",
	"data" : function(data) {
		return data.num;
	}
	} ];

	var taskStatisticsTable = initDataTable("taskStatisticsTableId", "${nvix}/nvixnt/wxpWorkLogStatisticsAction!goWorkLogStatisticsList.action", taskStatisticsColumns, function(page, pageSize, orderField, orderBy) {
		var empName = $("#empName").val();
		empName = Url.encode(empName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"empName" : empName
		};
	}, 10, "1");

	function dealAttendance() {
		$.ajax({
		url : '${nvix}/nvixnt/wxpWorkLogStatisticsAction!dealWorkLogStatistics.action',
		cache : false,
		success : function() {
			taskStatisticsTable.ajax.reload();
		}
		});
	};
</script>