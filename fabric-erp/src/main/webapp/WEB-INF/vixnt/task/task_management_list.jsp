<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> 任务与计划 <span>> 任务管理 </span>
			</h1>
		</div>
		<!-- <div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增任务');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div> -->
	</div>
	<div class="jarviswidget">
		<header role="heading">
			<ul class="nav nav-tabs pull-left in">
				<li class="active"><a data-toggle="tab" href="#hr1" aria-expanded="true"> 待办列表 </a></li>
				<li class=""><a data-toggle="tab" href="#hr2" aria-expanded="true"> 下达的任务 </a></li>
				<li class=""><a data-toggle="tab" href="#hr3" aria-expanded="true"> 团队任务 </a></li>
			</ul>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div class="tab-content">
					<div class="tab-pane active" id="hr1">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								任务名称: <input type="text" value="" class="form-control" id="vixTaskName">
							</div>
							<button onclick="vixTaskTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#vixTaskName').val('');vixTaskTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="vixTask" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane" id="hr2">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								任务名称: <input type="text" value="" class="form-control" id="myVixTaskName">
							</div>
							<button onclick="myVixTaskTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#myVixTaskName').val('');myVixTaskTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="myVixTask" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane" id="hr3">
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								任务名称: <input type="text" value="" class="form-control" id="myTeamVixTaskName">
							</div>
							<button onclick="myTeamVixTaskTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#myTeamVixTaskName').val('');myTeamVixTaskTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="myTeamVixTask" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var vixTaskColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "任务名称",
	"width" : "25%",
	"data" : function(data) {
		return "<a style='cursor: pointer;' onclick=\"goProjectTaskDetails('" + data.id + "');\">" + data.questName + "</a>";
	}
	}, {
	"title" : "执行人",
	"width" : "10%",
	"data" : function(data) {
		//return "<div class='project-members'>" + data.employeestr + "</div>";
		return data.employee.name;
	}
	}, {
	"title" : "进度",
	"width" : "15%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
	}
	}, {
	"title" : "截止日期",
	"width" : "15%",
	"data" : function(data) {
		return data.taskEndTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"data" : function(data) {
		if (data.complete == 0) {
			return "<span class='label label-info'>未开始</span>";
		} else if (data.complete == 1) {
			return "<span class='label label-primary'>进行中</span>";
		} else if (data.complete == 2) {
			return "<span class='label label-success'>已完成</span>";
		} else if (data.complete == 3) {
			return "<span class='label label-danger'>已超时</span>";
		}
		return "";
	}
	},{
	"title" : "创建时间",
	"width" : "10%",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','" + data.flag + "','修改任务');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "','" + data.flag + "',);\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		// return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
		return show;
	}
	} ];

	var vixTaskTable = initDataTable("vixTask", "${nvix}/nvixnt/taskAndPlanAction!goMyAllToDoTaskList.action", vixTaskColumns, function(page, pageSize, orderField, orderBy) {
		var vixTaskName = $("#vixTaskName").val();
		vixTaskName = Url.encode(vixTaskName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"vixTaskName" : vixTaskName
		};
	}, 10, "1");
	var myVixTaskColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "任务名称",
	"width" : "25%",
	"data" : function(data) {
		return "<a style='cursor: pointer;' onclick=\"goProjectTaskDetails('" + data.id + "');\">" + data.questName + "</a>";
	}
	}, {
	"title" : "执行人",
	"width" : "10%",
	"data" : function(data) {
		//return "<div class='project-members'>" + data.empliststr + "</div>";
		return data.employee.name;
	}
	}, {
	"title" : "进度",
	"width" : "15%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
	}
	}, {
	"title" : "截止日期",
	"width" : "15%",
	"data" : function(data) {
		return data.taskEndTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"data" : function(data) {
		if (data.complete == 0) {
			return "<span class='label label-info'>未开始</span>";
		} else if (data.complete == 1) {
			return "<span class='label label-primary'>进行中</span>";
		} else if (data.complete == 2) {
			return "<span class='label label-success'>已完成</span>";
		} else if (data.complete == 3) {
			return "<span class='label label-danger'>已超时</span>";
		}
		return "";
	}
	},{
	"title" : "创建时间",
	"width" : "10%",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','" + data.flag + "','修改任务');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "','" + data.flag + "',);\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		// return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
		return show;
	}
	} ];

	var myVixTaskTable = initDataTable("myVixTask", "${nvix}/nvixnt/taskAndPlanAction!goMyTaskAllList.action", myVixTaskColumns, function(page, pageSize, orderField, orderBy) {
		var vixTaskName = $("#myVixTaskName").val();
		vixTaskName = Url.encode(vixTaskName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"vixTaskName" : vixTaskName
		};
	}, 10, "1");
	var myTeamVixTaskColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "任务名称",
	"width" : "25%",
	"data" : function(data) {
		return "<a style='cursor: pointer;' onclick=\"goProjectTaskDetails('" + data.id + "');\">" + data.questName + "</a>";
	}
	}, {
	"title" : "执行人",
	"width" : "10%",
	"data" : function(data) {
		//return "<div class='project-members'>" + data.empliststr + "</div>";
		return data.employee.name;
	}
	}, {
	"title" : "进度",
	"width" : "15%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
	}
	}, {
	"title" : "截止日期",
	"width" : "15%",
	"data" : function(data) {
		return data.taskEndTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"data" : function(data) {
		if (data.complete == 0) {
			return "<span class='label label-info'>未开始</span>";
		} else if (data.complete == 1) {
			return "<span class='label label-primary'>进行中</span>";
		} else if (data.complete == 2) {
			return "<span class='label label-success'>已完成</span>";
		} else if (data.complete == 3) {
			return "<span class='label label-danger'>已超时</span>";
		}
		return "";
	}
	},{
	"title" : "创建时间",
	"width" : "10%",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','" + data.flag + "','修改任务');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var feedback = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateExecutionFeedback('" + data.id + "','" + data.startFeedbackTime + "','" + data.endFeedbackTime + "');\"><span class='txt-color-blue pull-right'><i class='fa fa-comment-o'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "','" + data.flag + "',);\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		//return update + "&nbsp;&nbsp;" + feedback + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
		return feedback + "&nbsp;&nbsp;" + show;
	}
	} ];

	var myTeamVixTaskTable = initDataTable("myTeamVixTask", "${nvix}/nvixnt/taskAndPlanAction!goAllTeamTaskList.action", myTeamVixTaskColumns, function(page, pageSize, orderField, orderBy) {
		var vixTaskName = $("#myTeamVixTaskName").val();
		vixTaskName = Url.encode(vixTaskName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"vixTaskName" : vixTaskName
		};
	}, 10, "1");
	//删除
	function deleteById(id,flag) {
		if(flag == 0){
			deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteById.action?id=' + id, '是否删除任务?', vixTaskTable);
		}else{
			deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteById.action?id=' + id, '是否删除任务?', myTeamVixTaskTable);
		}
	};
	function goProjectTaskDetails(id) {
		loadContent('mid_projectstask', '${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=' + id + "&source=task");
	};
	function goSaveOrUpdate(id,flag,title) {
		if(flag == 0){
			openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdate.action?id=' + id, "taskForm", title, 900, 650, vixTaskTable);
		}else{
			openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateTeamTask.action?id=' + id, "taskForm", title, 900, 650, myTeamVixTaskTable);
		}
	};
	function goSaveOrUpdateExecutionFeedback(id, startFeedbackTime, endFeedbackTime) {
		$.ajax({
		url : '${nvix}/nvixnt/taskAndPlanAction!isFeedback.action?id=' + id,
		cache : false,
		success : function(data) {
			if (data == 1) {
				openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateExecutionFeedback.action?id=' + id, "executionFeedbackForm", "新增反馈", 750, 250, myTeamVixTaskTable);
			} else {
				if (startFeedbackTime != null && startFeedbackTime != '' && endFeedbackTime != null && endFeedbackTime != '') {
					layer.alert('该任务已经过了反馈时间,不能反馈!' + '反馈时间:' + startFeedbackTime + '-' + endFeedbackTime, {
						icon : 5
					});
				} else {
					layer.alert('该任务已经过了反馈时间,不能反馈!', {
						icon : 5
					});
				}
			}
		}
		});
	};
</script>