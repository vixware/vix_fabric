<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训管理<span>> 活动计划</span>
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
						计划主题: <input type="text" value="" class="form-control" id="applicationName" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#applicationName').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
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
	"title" : "计划主题",
	"width" : "10%",
	"data" : function(data) {
		return data.applicationName;
	}
	}, {
	"title" : "负责部门",
	"width" : "10%",
	"data" : function(data) {
		return data.org;
	}
	}, {
	"title" : "计划级别",
	"width" : "10%",
	"data" : function(data) {
		if (data.planLevel == '1') {
			return "<span class='label label-info'>新</span>";
		}else if (data.planLevel == '2') {
			return "<span class='label label-primary'>低</span>";
		}else if (data.planLevel == '3') {
			return "<span class='label label-primary'>中</span>";
		}else if (data.planLevel == '4') {
			return "<span class='label label-primary'>高</span>";
		}
		return "";
	}
	}, {
	"title" : "计划性质",
	"width" : "10%",
	"data" : function(data) {
		if (data.planningNature == '1') {
			return "<span class='label label-info'>定向</span>";
		}else if (data.planningNature == '0') {
			return "<span class='label label-primary'>非定向</span>";
		}
		return "";
	}
	}, {
		"title" : "计划类型",
		"width" : "10%",
		"data" : function(data) {
			if (data.planType == '1') {
				return "<span class='label label-info'>短期计划</span>";
			}else if (data.planType == '2') {
				return "<span class='label label-primary'>中期计划</span>";
			}else if (data.planType == '3') {
				return "<span class='label label-primary'>长期计划</span>";
			}
			return "";
		}
	}, {
		"title" : "提出计划时间",
		"width" : "10%",
		"data" : function(data) {
			return data.proposedTimeStr;
		}
	}, {
		"title" : "计划状态",
		"width" : "10%",
		"data" : function(data) {
			if (data.planStatus == '1') {
				return "<span class='label label-info'>执行</span>";
			}else if (data.planStatus == '0') {
				return "<span class='label label-primary'>未执行</span>";
			}
			return "";
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

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/hr/nvixTrainAction!goActivityList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var applicationName = $("#applicationName").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"applicationName" : applicationName
		};
	}, 10);
    
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixRecruitPlanAction!deleteById.action?id=' + id, '是否删除该员工信息?', vehicleTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixTrainAction!goSaveOrUpdateActivity.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
</script>