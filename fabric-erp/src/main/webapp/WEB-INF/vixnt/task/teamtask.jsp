<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i>任务与计划 <span>> 团队任务管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>团队成员</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/taskAndPlanAction!findBusinessOrgViewTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							vixTaskTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>任务列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
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
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var vixTaskColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "任务名称",
	"width" : "20%",
	"data" : function(data) {
		return data.questName;
	}
	}, {
	"title" : "进度",
	"width" : "15%",
	"data" : function(data) {
		return "<div data-progressbar-value='"+data.taskSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
	}
	}, {
	"title" : "开始时间",
	"width" : "15%",
	"data" : function(data) {
		return data.taskStartTimeTimeStr;
	}
	}, {
	"title" : "结束时间",
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
	}, {
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
	}
	} ];

	var vixTaskTable = initDataTable("vixTask", "${nvix}/nvixnt/taskAndPlanAction!goTeamTaskList.action", vixTaskColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var vixTaskName = $("#vixTaskName").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"vixTaskName" : vixTaskName,
		"parentId" : parentId,
		"treeType" : treeType
		};
	});

	function goSaveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdateTeamTask.action?parentId=' + $('#selectId').val() + "&id=" + id + "&treeType=" + $('#selectTreeType').val(), "taskForm", "新增任务", 900, 650, vixTaskTable);
	};

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteById.action?id=' + id, '是否删除任务?', vixTaskTable);
	};
	function goProjectTaskDetails(id) {
		loadContent('mid_projectstask', '${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=' + id);
	};
</script>