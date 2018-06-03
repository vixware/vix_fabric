<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-calendar fa-desktop"></i> 办公 <span>&gt;工作计划 </span>
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
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>计划列表</h2>
			<ul class="nav nav-tabs pull-right in" id="myTab">
				<li class="<c:if test='${source != "other"}'>active</c:if>"><a href="#s1" data-toggle="tab">我的计划</a></li>
				<li class="<c:if test='${source == "other"}'>active</c:if>"><a href="#s2" data-toggle="tab">下属计划</a></li>
			</ul>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="" class="tab-content">
					<div class="tab-pane fade in <c:if test='${source != "other"}'>active</c:if>" id="s1">
						<div class="jarviswidget">
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
					<div class="tab-pane fade in <c:if test='${source == "other"}'>active</c:if>" id="s2">
						<form class="navbar-form navbar-left" role="search">
							计划标题：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="工作计划标题" id="title">
							</div>
							编写人：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="编写人" id="uploadPersonName">
							</div>
							<button onclick="workPlansTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#title').val('');$('#uploadPersonName').val('');workPlansTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="workPlans" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>计划标题</th>
									<th>编写人</th>
									<th>开始时间</th>
									<th>截止时间</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
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
		defaultView : 'basicDay',
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
			url : "${nvix}/nvixnt/workPlansAction!projectManagementEvents.action",
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

	function goSaveOrUpdate() {
		openSaveOrUpdateWindow('${nvix}/nvixnt/workPlansAction!goSaveOrUpdate.action', "workPlansForm", "新增", 750, 475, null, null, function() {
			$('#calendar').fullCalendar('refetchEvents');
		});
	};
	function goSaveOrUpdateCalendar(id) {
		var url = "${nvix}/nvixnt/workPlansAction!goSaveOrUpdate.action?id=" + id;
		var mycars = new Array("确定", "取消", "删除")
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			layer.open({
			type : 1,
			title : "修改",
			area : [ 750 + 'px', 475 + 'px' ],
			closeBtn : 1,
			content : html,
			btn : mycars,
			yes : function(index, layero) {
				$("#workPlansForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/workPlansAction!saveOrUpdate.action",
				dataType : "text",
				success : function(json) {
					layer.close(index);
					$('#calendar').fullCalendar('refetchEvents');
				}
				});
			},
			btn2 : function(index, layero) {
				layer.close(index);
			},
			btn3 : function(index, layero) {
				deleteEntityByConfirm("${nvix}/nvixnt/workPlansAction!deleteById.action?id=" + id, "是否删除该计划!", null, "删除计划", function() {
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
	var workPlansColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "proposalTitle",
	"data" : function(data) {
		return data.proposalTitle;
	}
	}, {
	"name" : "uploadPersonName",
	"data" : function(data) {
		return data.uploadPersonName;
	}
	}, {
	"name" : "initiateTime",
	"data" : function(data) {
		return data.initiateTimeStr;
	}
	}, {
	"name" : "overTime",
	"data" : function(data) {
		return data.overTimeStr;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return "<a class='btn btn-xs btn-default' onclick=\"viewPlan('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
	}
	} ];

	var workPlansTable = initDataTable("workPlans", "${nvix}/nvixnt/workPlansAction!goWorkPlans.action", workPlansColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#title").val();
		title = Url.encode(title);
		var uploadPersonName = $("#uploadPersonName").val();
		uploadPersonName = Url.encode(uploadPersonName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"title" : title,
		"uploadPersonName" : uploadPersonName
		};
	});

	//根据ID查看工作计划
	function viewPlan(id) {
		$.ajax({
		url : '${nvix}/nvixnt/workPlansAction!goViewPlan.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	pageSetUp();
</script>