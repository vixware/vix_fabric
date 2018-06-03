<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i> <a style="cursor: pointer;" onclick="loadContent('mid_project','${nvix}/nvixnt/nvixProjectAction!goList.action');">项目管理</a><span>> 项目跟踪管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增任务');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						项目视图 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goList.action');">列表视图</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>项目</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /><input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/nvixProjectAction!findProjectTreeToJson.action",
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
				<div class="jarviswidget" id="">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
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
	var vixTaskColumns = [ {
	"title" : "编号",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "任务名称",
	"width" : "20%",
	"data" : function(data) {
		return data.questName;
	}
	}, {
	"title" : "进度",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		if(data.projectSchedule != null){
			return "<div data-progressbar-value='"+data.projectSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
		}else{
			return "";
		}
	}
	}, {
	"title" : "负责人",
	"width" : "10%",
	"data" : function(data) {
		//return "<div class='project-members'>" + data.empliststr + "</div>";
		return data.employee == null ? '' : data.employee.name;
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
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var open = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goopentask('" + data.id + "');\" title='重启任务'><span class='txt-color-blue pull-right'><i class='fa fa-check-circle-o'></i></span></a>";
		var close = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goclosetask('" + data.id + "');\" title='关闭任务'><span class='txt-color-blue pull-right'><i class='fa fa-times-circle-o'></i></span></a>";
		var detail = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskDetails('" + data.id + "');\" title='任务详情'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var deltete =  "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		if (data.status == 0) {
			return update + "&nbsp;&nbsp;" + detail + "&nbsp;&nbsp;" + close + "&nbsp;&nbsp;" + deltete;
		} else if (data.status == 1) {
			return update + "&nbsp;&nbsp;" + detail + "&nbsp;&nbsp;" + open + "&nbsp;&nbsp;" + deltete;
		}
		return "";
	}
	} ];

	var vixTaskTable = initDataTable("vixTask", "${nvix}/nvixnt/nvixProjectAction!goVixTaskList.action", vixTaskColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var vixTaskName = $("#vixTaskName").val();
		vixTaskName = Url.encode(vixTaskName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"vixTaskName" : vixTaskName,
		"treeType" : treeType
		};
	}, 10);

	function format(d) {
		return '<table cellpadding="5" cellspacing="0" border="0" class="table table-hover table-condensed">' + '<tr>' + '<td style="width:100px">任务名称:</td>' + '<td>' + d.questName + '</td>' + '</tr>' + '<tr>' + '<td style="width:100px">任务详情:</td>' + '<td>' + d.questName + '</td>' + '</tr>' + '<tr>' + '<td>期限:</td>' + '<td>' + moment(d.taskEndTime)
				.format('YYYY-MM-DD') + '</td>' + '</tr>' + '<tr>' + '<td>操作:</td>' + '<td><button class="btn btn-xs" onclick="goProjectTaskDetails(\'' + d.id + '\');">详情查看</button> <button class="btn btn-xs btn-danger pull-right" style="margin-left:5px" onclick="deleteById(\'' + d.id + '\');">删除</button> <button class="btn btn-xs btn-success pull-right" onclick="goSaveOrUpdate(\'' + d.id + '\',\'修改任务\');">修改</button> </td>' + '</tr>' + '</table>';
	}

	$('#vixTask tbody').on('click', 'td.details-control', function() {
		var tr = $(this).closest('tr');
		var row = vixTaskTable.row(tr);
		if (row.child.isShown()) {
			row.child.hide();
			tr.removeClass('shown');
		} else {
			if(row.data().id != null){
				row.child(format(row.data())).show();
				tr.addClass('shown');
			}
		}
	});
	function goSaveOrUpdate(id, title) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		if(!id && !treeType){
			layer.alert("请选择项目或者虚拟任务节点");
			return ;
		}
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateTask.action?id=' + id + '&parentId=' + parentId + '&treeType=' + treeType + '&source=project');
	};

	//关闭任务
	function goclosetask(id) {
		updateEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!closeTask.action?id=' + id, '确定关闭任务吗?', vixTaskTable);
	};
	//重启任务
	function goopentask(id) {
		updateEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!openTask.action?id=' + id, '确定启动任务吗?', vixTaskTable);
	};
	//任务详情
	function goProjectTaskDetails(id) {
		//loadContent('mid_teamtask', '${nvix}/nvixnt/nvixProjectAction!goProjectTaskDetails.action?id=' + id + '&source=project');
		loadContent('mid_projectstask', '${nvix}/nvixnt/taskAndPlanAction!goProjectTaskDetails.action?id=' + id + '&source=project');
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteTaskById.action?id=' + id, '是否删除任务?', vixTaskTable, '删除任务', function(){
			var treeNode = zTree.getNodeByTId($("#selectId").val());
			if (null != treeNode) {
				treeNode.isParent = true;
			}
			zTree.reAsyncChildNodes(treeNode, "refresh");
		});
	};
</script>