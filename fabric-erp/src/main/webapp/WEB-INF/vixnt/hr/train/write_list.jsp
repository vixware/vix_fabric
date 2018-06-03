<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训管理<span>> 填报计划</span>
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
			<h2>活动计划列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						计划名称: <input type="text" value="" class="form-control" id="planName" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#planName').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
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
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "计划名称",
	"width" : "10%",
	"data" : function(data) {
		return data.planName;
	}
	}, {
	"title" : "填报部门或人员",
	"width" : "10%",
	"data" : function(data) {
		if (data.pubType == 'O') {
			return "<span class='label label-info'>部门</span>";
		}else if (data.pubType == 'E') {
			return "<span class='label label-primary'>人员</span>";
		}
		return "";
	}
	}, {
	"title" : "拟培训时间",
	"width" : "10%",
	"data" : function(data) {
		return data.quasiTrainingTimeStr;
	}
	}, {
	"title" : "开始日期",
	"width" : "10%",
	"data" : function(data) {
		return data.trainingStartDateStr;
	}
	}, {
		"title" : "结束日期",
		"width" : "10%",
		"data" : function(data) {
			return data.trainingEndDateStr;
		}
	}, {
		"title" : "计划总费用",
		"width" : "10%",
		"data" : function(data) {
			return data.totalCost;
		}
	}, {
		"title" : "课程成本权重",
		"width" : "10%",
		"data" : function(data) {
			return data.courseCostWeight;
		}
	}, {
		"title" : "课程总费用",
		"width" : "10%",
		"data" : function(data) {
			return  data.courseTotalCost;
		}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/hr/nvixTrainAction!writeList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var planName = $("#planName").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"planName" : planName
		};
	}, 10);
    
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixRecruitPlanAction!deleteById.action?id=' + id, '是否删除该员工信息?', vehicleTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixTrainAction!goSaveOrUpdateWrite.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
</script>