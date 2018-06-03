<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> <a onclick="loadContent('mid_task','${nvix}/nvixnt/taskAndPlanAction!goList.action');">考勤管理</a> <span>> 考勤原始记录 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goAddUploader('');">
						<i class="fa fa-share"></i>&nbsp;
						<s:text name="导入原始记录" />
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>公司</h2>
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
							vixTaskTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>打卡列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										员工编号: <input type="text" value="" class="form-control" id="empCode" size="10">
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
									<button onclick="vixTaskTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#empName').val('');$('#empCode').val('');$('#startTime').val('');$('#endTime').val('');attDetailTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="attDetail" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var attDetailColumns = [ {
	"title" : "编号",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "员工编号",
	"data" : function(data) {
		return data.userCode;
	}
	}, {
	"title" : "打卡时间",
	"data" : function(data) {
		return data.punchCardDate;
	}
	} ];

	var attDetailTable = initDataTable("attDetail", "${nvix}/nvixnt/attendanceDetailAction!getDetailJson.action", attDetailColumns, function(page, pageSize, orderField, orderBy) {
		var empCode = $("#empCode").val();
		empCode = Url.encode(empCode);
		var startTime = $("#startTime").val();
		startTime = Url.encode(startTime);
		var endTime = $("#endTime").val();
		endTime = Url.encode(endTime);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"startTime" : startTime,
		"endTime" : endTime,
		"empCode" : empCode
		};
	});

	function goSaveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/taskAndPlanAction!goSaveOrUpdate.action?parentId=' + $('#selectId').val() + "&id=" + id + "&treeType=" + $('#selectTreeType').val(), "taskForm", "新增任务", 900, 650, attDetailTable);
	};

	//添加附件
	function goAddUploader() {
		openSaveOrUpdateWindow('${nvix}/nvixnt/attendanceDetailAction!goAddUploader.action', 'uploaderAttLogsForm', '添加考勤原始记录', 720, 200, null, null, function() {
			loadContent('oa_attendanceDetail', '${nvix}/nvixnt/attendanceDetailAction!goList.action');
		});
	};

	function readTxtFile() {
		$.ajax({
		url : '${nvix}/nvixnt/attendanceDetailAction!readTxtFile.action',
		cache : false,
		success : function() {
			alert("导入完成！");
		}
		});
	}
</script>