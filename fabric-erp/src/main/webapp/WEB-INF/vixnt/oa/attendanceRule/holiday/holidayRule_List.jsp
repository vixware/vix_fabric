<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> <a onclick="loadContent('mid_task','${nvix}/nvixnt/taskAndPlanAction!goList.action');">考勤管理</a> <span>>日常操作 </span><span>>假日管理 </span>
			</h1>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<!-- <article style="width: 260px; float: left; margin-right: 20px">
					<div class="tree-box">
						<h2>公司</h2>
						<ul id="tree" class="ztree" style="height: 100%; overflow-x: hidden; overflow-y: auto;"></ul>
						<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
						<script type="text/javascript">
							var zTree;
							var setting = {
								async : {
								enable : true,
								url : "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action",
								autoParam : [ "id", "treeType" ]
								},
								callback : {
									onClick : onClick
								}
							};
							function onClick(event, treeId, treeNode, clickFlag) {
								$("#selectId").val(treeNode.id);
								$("#selectTreeType").val(treeNode.treeType);
								holidayRuleTable.ajax.reload();
							}
							zTree = $.fn.zTree.init($("#tree"), setting);
						</script>
					</div>
				</article> -->
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>假日管理</h2>
						<div class="pull-right">
							<ul class="nav nav-tabs in" id="myTab">
								<li class="active"><a data-toggle="tab" href="#s1"><span class="hidden-mobile hidden-tablet">假日日历</span></a></li>
								<li><a data-toggle="tab" href="#s2"><span class="hidden-mobile hidden-tablet">假日列表</span></a></li>
							</ul>
						</div>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade active in clearfix" id="s1">
									<div>
										<br>
										<div class="widget-body">
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
									<br>
								</div>
								<div class="tab-pane" id="s2">
									<div>
										<form role="search" class="navbar-form navbar-left">
											假日名称:
											<div class="form-group">
												<input type="text" value="" class="form-control" id="empName" size="10">
											</div>
											开始日期:
											<div class="input-group">
												<input id="startTime" name="startTime" value="${startTime}" size="10" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startTime'});"><i class="fa fa-calendar"></i></span>
											</div>
											结束日期:
											<div class="input-group">
												<input id="endTime" name="endTime" value="${endTime}" size="10" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endTime'});"><i class="fa fa-calendar"></i></span>
											</div>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<button onclick="holidayRuleTable.ajax.reload();" type="button" class="btn btn-info">
												<i class="glyphicon glyphicon-search"></i> 检索
											</button>
											<button onclick="$('#empCode').val('');$('#empName').val('');$('#startTime').val('');$('#endTime').val('');holidayRuleTable.ajax.reload();" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i> 清空
											</button>
										</form>
									</div>
									<table id="holidayRule" class="table table-striped table-bordered table-hover" width="100%"></table>
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
	$(document).ready(function() {
		pageSetUp();
		$('#add-event').click(function() {
			var title = $('#title').val();
			var priority = $('input:radio[name=priority]:checked').val();
			var description = $('#description').val();
			var icon = $('input:radio[name=iconselect]:checked').val();
			addEvent(title, priority, description, icon);

			$.post('${nvix}/nvixnt/nvixScheduleAction!saveOrUpdate.action', {
			'calendars.id' : $("#calendarsId").val(),
			'calendars.scheduleName' : title,
			'calendars.calendarsContent' : description,
			'calendars.priority' : priority,
			'calendars.icon' : icon
			}, function(result) {
				$('#calendar').fullCalendar('unselect');
			});
		});
		var addEvent = function(title, priority, description, icon) {
			title = title.length === 0 ? "Untitled Event" : title;
			description = description.length === 0 ? "No Description" : description;
			icon = icon.length === 0 ? " " : icon;
			priority = priority.length === 0 ? "label label-default" : priority;

			var html = $('<li><span class="' + priority + '" data-description="' + description + '" data-icon="' +  icon + '">' + title + '</span></li>').prependTo('ul#external-events').hide().fadeIn();

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

		select : function(start) {
			var params = {
				start : start
			};
			openSaveOrUpdateWindow('${nvix}/nvixnt/attendance/holiDayRuleAction!goSaveOrUpdate.action?start=' + start, "holidayRuleForm", "添加假日", 720, 480, null, null, function() {
				$('#calendar').fullCalendar('refetchEvents');
			});
		},
		eventClick : function(calEvent, jsEvent, view) {
			if (calEvent.id != null) {
				goSaveOrUpdateHoliDayRule(calEvent.id);
			} else {
				openSaveOrUpdateWindow('${nvix}/nvixnt/attendance/holiDayRuleAction!goSaveOrUpdate.action?id=' + calEvent.id, "holidayRuleForm", "修改假日", 720, 480, null, null, function() {
					$('#calendar').fullCalendar('refetchEvents');
				});
			}
		},
		events : function(start, end, timezone, callback) {
			$.ajax({
			url : "${nvix}/nvixnt/attendance/holiDayRuleAction!holiDayRuleEvents.action",
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
					title : $(this).attr('title'),
					start : $(this).attr('start'),
					end : $(this).attr('end'),
					className : [ "event", "bg-color-red" ],
					icon : 'fa-calendar'
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
				element.find('.fc-event-title').append("<i class='air air-top-right fa " + event.icon +  " '></i>");
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

	function goSaveOrUpdateHoliDayRule(id) {
		var url = "${nvix}/nvixnt/attendance/holiDayRuleAction!goSaveOrUpdate.action?id=" + id;
		var mycars = new Array("确定", "取消", "删除")
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			layer.open({
			type : 1,
			title : "修改",
			area : [ 720 + 'px', 480 + 'px' ],
			closeBtn : 1,
			content : html,
			btn : mycars,
			yes : function(index, layero) {
				$("#holidayRuleForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/attendance/holiDayRuleAction!saveOrUpdate.action",
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
				deleteEntityByConfirm("${nvix}/nvixnt/attendance/holiDayRuleAction!deleteHoliDayRuleById.action?id=" + id, "是否删除该假日!", null, "删除假日", function() {
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
	pageSetUp();
	var holidayRuleColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "假日编码",
	"width" : "25%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "假日名称",
	"width" : "25%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "假日开始时间",
	"width" : "15%",
	"data" : function(data) {
		return data.holidayStartTime == null ? '' : data.holidayStartTimeStr;
	}
	}, {
	"title" : "假日结束时间",
	"width" : "15%",
	"data" : function(data) {
		return data.holidayEndTime == null ? '' : data.holidayEndTimeStr;
	}
	}, {
	"title" : "使用状态",
	"width" : "15%",
	"data" : function(data) {
		return data.isEnable == null ? '' : data.isEnable == '0' ? '<span style="color: red;">禁用</span>' : '<span style="color: blue;">启用</span>';
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"editSchedul('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletesById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var holidayRuleTable = initDataTable("holidayRule", "${nvix}/nvixnt/holiDayRuleAction!getHolidayRuleTableData.action", holidayRuleColumns, function(page, pageSize, orderField, orderBy) {
		var empName = $("#empName").val();
		empName = Url.encode(empName);
		var startTime = $("#startTime").val();
		startTime = Url.encode(startTime);
		var endTime = $("#endTime").val();
		endTime = Url.encode(endTime);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"empName" : empName,
		"startTime" : startTime,
		"endTime" : endTime
		};
	});

	function editSchedulByOrg(id, treeType) {
		if (treeType == '') {
			layer.alert("请选择要排班的公司或部门!", function(index) {
				layer.close(index);
			});
		} else {
			if (treeType != 'E') {
				openSaveOrUpdateWindow('${nvix}/nvixnt/schedulingAction!goSaveOrUpdate.action?treeId=' + $('#selectId').val() + "&id=" + id + "&treeType=" + $('#selectTreeType').val(), 'schedulingForm', '排班', 720, 420, schedulingTable, null, function() {
					schedulingTable.ajax.reload();
				});
			} else {
				layer.alert("请选择要排班的公司或部门,而不是职员!", function(index) {
					layer.close(index);
				});
			}
		}
	};
	function editSchedulByEmp(id, treeType) {
		if (treeType == '') {
			layer.alert("请选择要排班的职员!", function(index) {
				layer.close(index);
			});
		} else {
			if (treeType == 'E') {
				openSaveOrUpdateWindow('${nvix}/nvixnt/schedulingAction!goSaveOrUpdate.action?treeId=' + $('#selectId').val() + "&id=" + id + "&treeType=" + $('#selectTreeType').val(), 'schedulingForm', '排班', 720, 420, schedulingTable, null, function() {
					schedulingTable.ajax.reload();
				});
			} else {
				layer.alert("请选择要排班的职员,而不是公司或部门!", function(index) {
					layer.close(index);
				});
			}
		}
	};

	function editSchedul(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/schedulingAction!goSaveOrUpdate.action?id=' + id, 'schedulingForm', '排班', 720, 420, schedulingTable, null, function() {
			schedulingTable.ajax.reload();
		});
	}
</script>