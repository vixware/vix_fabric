<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> <a onclick="loadContent('mid_task','${nvix}/nvixnt/taskAndPlanAction!goList.action');">考勤管理</a> <span>>日常操作 </span><span>>排班管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="editSchedulByOrg('',$('#selectTreeType').val());">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;
						<s:text name="按部门分组排班" />
					</button>
				</a> <a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="editSchedulByEmp('',$('#selectTreeType').val());">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;
						<s:text name="按指定人员排班" />
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>项目</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
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
							schedulingTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>排班列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									员工编号:
									<div class="form-group">
										<input type="text" value="" class="form-control" id="empCode" size="10">
									</div>
									员工姓名:
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
									<button onclick="schedulingTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#empCode').val('');$('#empName').val('');$('#startTime').val('');$('#endTime').val('');schedulingTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="scheduling" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var schedulingColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "公司名称",
	"width" : "35%",
	"data" : function(data) {
		return data.orgName;
	}
	}, {
	"title" : "部门名称",
	"width" : "35%",
	"data" : function(data) {
		return data.unitName;
	}
	}, {
	"title" : "职员",
	"width" : "15%",
	"data" : function(data) {
		return data.emp == null ? '' : data.emp.name;
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

	var schedulingTable = initDataTable("scheduling", "${nvix}/nvixnt/schedulingAction!getSchedulingJson.action", schedulingColumns, function(page, pageSize, orderField, orderBy) {
		var empCode = $("#empCode").val();
		empCode = Url.encode(empCode);
		var empName = $("#empName").val();
		empName = Url.encode(empName);
		var treeId = $("#selectId").val();
		treeId = Url.encode(treeId);
		var treeType = $("#selectTreeType").val();
		treeType = Url.encode(treeType);
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
		"empCode" : empCode,
		"startTime" : startTime,
		"endTime" : endTime,
		"treeId" : treeId,
		"treeType" : treeType
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

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/taskAndPlanAction!deleteById.action?id=' + id, '是否删除任务?', vixTaskTable);
	};
</script>