<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-tasks"></i> 任务与计划
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增任务');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="row">
		<article class="col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget">
				<header>
					<div class="jarviswidget-ctrls" role="menu">
						<a href="javascript:void(0);" class="button-icon jarviswidget-fullscreen-btn" rel="tooltip" title="" data-placement="bottom" data-original-title="Fullscreen"><i class="fa fa-expand"></i></a>
					</div>
					<span class="widget-icon"> <i class="fa fa-table"></i></span>
					<div class="widget-toolbar">
						<div class="btn-group">
							<button class="btn dropdown-toggle btn-xs btn-success" data-toggle="dropdown">
								我的任务 <i class="fa fa-caret-down"></i>
							</button>
							<input type="hidden" id="complete" name="complete" value="" />
							<ul class="dropdown-menu pull-right js-status-update">
								<li><a href="javascript:void(0);" onclick="$('#complete').val(0);vixTaskTable.ajax.reload();"><i class="fa fa-circle txt-color-green"></i> 未开始</a></li>
								<li><a href="javascript:void(0);" onclick="$('#complete').val(1);vixTaskTable.ajax.reload();"><i class="fa fa-circle txt-color-red"></i> 进行中</a></li>
								<li><a href="javascript:void(0);" onclick="$('#complete').val(2);vixTaskTable.ajax.reload();"><i class="fa fa-circle txt-color-orange"></i> 已完成</a></li>
							</ul>
						</div>
					</div>
					<h2>我的任务</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding">
						<div class="well well-sm">
							<div class="input-group">
								<input class="form-control" type="text" placeholder="填写任务标题..." id="searchtaskname">
								<div class="input-group-btn">
									<button onclick="vixTaskTable.ajax.reload();myVixTaskTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i>
									</button>
									<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增任务');">
										<i class="glyphicon glyphicon-plus"></i>
									</button>
									<button onclick="$('#searchtaskname').val('');myVixTaskTable.ajax.reload();vixTaskTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i>
									</button>
								</div>
							</div>
						</div>
						<table id="vixTask" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
				</div>
			</div>
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i></span>
					<h2>下达的任务</h2>
				</header>
				<div>
					<div class="widget-body no-padding">
						<table id="myVixTask" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
				</div>
			</div>
			<div class="jarviswidget">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i></span>
					<h2>团队任务</h2>
				</header>
				<div>
					<div class="widget-body no-padding">
						<table id="teamtasktable" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
				</div>
			</div>
		</article>
		<%-- <article class="col-sm-12 col-md-12 col-lg-5">
			<div class="jarviswidget" id="showtaskid">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i></span>
					<h2>任务主题:&nbsp;&nbsp;${vixTask.questName }</h2>
				</header>
				<div>
					<div class="widget-body">
						<div class="input-group">
							<span class="input-group-addon"> <i class="icon-prepend fa fa-user" id="alertname"></i>
							</span> <input type="text" class="form-control" name="fname" placeholder="">
						</div>
						<br>
						<div class="well well-sm" role="alert" style="height: 250px;">
							<p>${vixTask.taskDescription }</p>
						</div>
						<br>
						<div class="input-group">
							<span class="input-group-addon"> <i class="icon-prepend fa fa-plus" id="alertvalue"></i>
							</span> <input type="text" class="form-control" name="fname" placeholder="">
						</div>
					</div>
				</div>
			</div>
		</article> --%>
	</div>
</div>
<script type="text/javascript">
	var vixTaskColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"width" : "25%",
	"data" : function(data) {
		return "<a style='cursor: pointer;' onclick=\"goShowTaskDetails('" + data.id + "');\">" + data.questName + "</a>";
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
	"width" : "20%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
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
	}, {
	"title" : "创建时间",
	"width" : "10%",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default'  onclick=\"goShowTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var feedback = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateExecutionFeedback('" + data.id + "','" + data.startFeedbackTime + "','" + data.endFeedbackTime + "');\"><span class='txt-color-blue pull-right'><i class='fa fa-comment-o'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById1('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		if (data.complete == 3) {
			return show + "&nbsp;&nbsp;" + del;
		} else {
			return show + "&nbsp;&nbsp;" + feedback + "&nbsp;&nbsp;" + del;
		}
	}
	} ];

	var vixTaskTable = initDataTable("vixTask", "${nvix}/nvixnt/taskAndPlanAction!goMyToDoTaskList.action", vixTaskColumns, function(page, pageSize, orderField, orderBy) {
		var complete = $("#complete").val();
		var searchtaskname = $("#searchtaskname").val();
		searchtaskname = Url.encode(searchtaskname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"complete" : complete,
		"searchtaskname" : searchtaskname
		};
	});
	var myVixTaskColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"width" : "25%",
	"data" : function(data) {
		return "<a style='cursor: pointer;' onclick=\"goShowTaskDetails('" + data.id + "');\">" + data.questName + "</a>";
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
	"width" : "20%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
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
	}, {
	"title" : "创建时间",
	"width" : "10%",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改任务');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goShowTaskDetails('" + data.id + "');\" title='详情'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var feedback = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateExecutionFeedback('" + data.id + "');\" title='反馈'><span class='txt-color-blue pull-right'><i class='fa fa-comment-o'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById2('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
	}
	} ];

	var myVixTaskTable = initDataTable("myVixTask", "${nvix}/nvixnt/taskAndPlanAction!goMyTaskList.action", myVixTaskColumns, function(page, pageSize, orderField, orderBy) {
		var searchtaskname = $("#searchtaskname").val();
		searchtaskname = Url.encode(searchtaskname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchtaskname" : searchtaskname
		};
	});
	var teamtasktableColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"width" : "25%",
	"data" : function(data) {
		return "<a style='cursor: pointer;' onclick=\"goShowTaskDetails('" + data.id + "');\">" + data.questName + "</a>";
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
	"width" : "20%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
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
	"orderable" : false,
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goShowTaskDetails('" + data.id + "');\" title='详情'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var feedback = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateExecutionFeedback('" + data.id + "');\" title='反馈'><span class='txt-color-blue pull-right'><i class='fa fa-comment-o'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update;
	}
	} ];

	var teamtasktable = initDataTable("teamtasktable", "${nvix}/nvixnt/taskAndPlanAction!goAllTeamTaskList.action", teamtasktableColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	//新增任务
	function goSaveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdate.action?id=' + id, "taskForm", title, 900, 650, vixTaskTable, null, function() {
			vixTaskTable.ajax.reload();
			myVixTaskTable.ajax.reload();
			teamtasktable.ajax.reload();
		});
	};
	//新增反馈
	function goSaveOrUpdateExecutionFeedback(id, startFeedbackTime, endFeedbackTime) {
		$.ajax({
		url : '${nvix}/nvixnt/taskAndPlanAction!isFeedback.action?id=' + id,
		cache : false,
		success : function(data) {
			if (data == 1) {
				openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateExecutionFeedback.action?id=' + id, "executionFeedbackForm", "反馈", 750, 350, vixTaskTable);
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
	function goShowTask(id) {
		$.ajax({
		url : '${nvix}/nvixnt/taskAndPlanAction!goShowTask.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#showtaskid").html(html);
		}
		});
	};
	//跳转到任务详情
	function goShowTaskDetails(id) {
		loadContent('mid_projectstask', '${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=' + id + '&source=taskhome');
	};
	//完成任务
	function updateFinishTask(id) {
		updateEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!updateFinishTask.action?id=' + id, '确定结束任务吗?', vixTaskTable);
	};
	//删除
	function deleteById1(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteById.action?id=' + id, '是否删除任务?', vixTaskTable);
	};
	function deleteById2(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteById.action?id=' + id, '是否删除任务?', myVixTaskTable);
	};
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteById.action?id=' + id, '是否删除任务?', teamtasktable);
	};
	$('#alertvalue,#alertname').hover(function() {
		$(this).css('color', '#3276b1');
	}, function() {
		$(this).css('color', '#a2a2a2');
	});
</script>