<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训计划<span>> 培训活动</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>活动列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						活动名称: <input type="text" value="" class="form-control" id="activityName" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#projectName').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="employeeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var vehicleColumns = [ {
	"title" : "序号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "活动编码",
	"width" : "7%",
	"data" : function(data) {
		return data.activityCode;
	}
	}, {
	"title" : "活动部门",
	"width" : "10%",
	"data" : function(data) {
		return data.activityDepartmentOrLeadings;
	}
	}, {
		"title" : "拟培训日期 ",
		"width" : "10%",
		"data" : function(data) {
			return data.trainingTimeStr;
		}
	
	}, {
		"title" : "预算费用",
		"width" : "8%",
		"data" : function(data) {
			return data.budgetExpense;
		}
	}, {
		"title" : "开始时间",
		"width" : "7%",
		"data" : function(data) {
			return data.activityStartDateStr;
		}
	}, {
		"title" : "结束时间",
		"width" : "8%",
		"data" : function(data) {
			return data.activityEndDateStr;
		}
	}, {
		"title" : "内容",
		"width" : "12%",
		"data" : function(data) {
			return data.trainingContent;
		}
	}, {
		"title" : "培训方式",
		"width" : "6%",
		"data" : function(data) {
			if (data.trainingMethod == '1') {
				return "<span class='label label-info'>学习</span>";
			} else if (data.trainingMethod == '2') {
				return "<span class='label label-primary'>授课</span>";
			}
			return "";
		}
	}, {
		"title" : "考核方式",
		"width" : "6%",
		"data" : function(data) {
			if (data.examinationMethod == '1') {
				return "<span class='label label-info'>笔记</span>";
			} else if (data.examinationMethod == '2') {
				return "<span class='label label-primary'>评价</span>";
			}
			return "";
		}
	}, {
	"title" : "操作",
	"width" : "6%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/hr/nvixTrainProjectAction!activityList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var activityName = $("#activityName").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"activityName" : activityName
		};
	}, 10);
    
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixTrainProjectAction!deleteTeacherById.action?id=' + id, '是否删除该讲师信息?', vehicleTable);
	};
	
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixTrainProjectAction!goSaveOrUpdateActivity.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
</script>