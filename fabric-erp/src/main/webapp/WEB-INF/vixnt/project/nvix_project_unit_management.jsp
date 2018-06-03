<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i> 项目管理 <span>> 项目组织管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdateProjectRole('','新增组织');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
				<button class="btn btn-primary" type="button" onclick="chooseEmployee();">
					<i class="glyphicon glyphicon-user"></i>&nbsp;添加成员
				</button>
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
						url : "${nvix}/nvixnt/nvixProjectAction!findProjectRoleToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							employeeTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>人员列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										姓名: <input type="text" value="" class="form-control" id="employeeName">
									</div>
									<button onclick="employeeTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#employeeName').val('');employeeTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
								<table id="employeeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
	var employeeColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "姓名",
	"data" : function(data) {
		return data.name;
		// return "<div class='project-members'><a href='javascript:void(0)'><img src='"+data.headImgUrl +"'> " + data.name + "</a></div> ";
	}
	}, {
	"title" : "微信",
	"data" : function(data) {
		return data.weixinid;
	}
	}, {
	"title" : "电话",
	"data" : function(data) {
		return data.mobile;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var parentId = $("#selectId").val();
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateTask('" + data.id + "','" + data.name + "');\" title='分配任务'><span class='txt-color-blue pull-right'><i class='fa fa-share-alt'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteProjectEmployee('" + data.id + "','" + parentId + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		if(data != null && data.id != null){
			return update + "&nbsp;&nbsp;" + del;
		}else{
			return "&nbsp;";
		}
	}
	} ];

	var employeeTable = initDataTable("employeeTableId", "${nvix}/nvixnt/nvixProjectAction!goProjectUnitManagementList.action", employeeColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var employeeName = $("#employeeName").val();
		employeeName = Url.encode(employeeName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"employeeName" : employeeName,
		"treeType" : treeType
		};
	}, 10, '0');
	//删除执行人
	function deleteProjectEmployee(id, projectId) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectAction!deleteProjectEmployeeById.action?id=' + id + "&projectId=" + projectId, '是否删除执行人?', employeeTable);
	};
	function goSaveOrUpdateProjectRole(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateProjectRole.action?parentId=' + $('#selectId').val() + "&id=" + id + "&treeType=" + $('#selectTreeType').val(), "projectRoleForm", title, 750, 200, employeeTable);
	};
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$.ajax({
				url : '${nvix}/nvixnt/nvixProjectAction!addEmployee.action?parentId=' + $('#selectId').val() + "&employeeIds=" + emp[0] + "&treeType=" + $('#selectTreeType').val(),
				cache : false,
				success : function() {
					employeeTable.ajax.reload();
				}
				});
			} else {
				layer.alert("请选择人员!");
			}
		}, 750, 550);
	};
	//新增任务
	function goSaveOrUpdateTask(id, name) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateTask.action?employeeIds=' + id + '&parentId=' + $("#selectId").val() + '&treeType=P&source=project');
	};
</script>