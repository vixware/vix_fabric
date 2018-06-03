<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>招聘管理<span>> 用人申请</span>
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
			<h2>招聘列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						编号: <input type="text" value="" class="form-control" id="applicationNumber" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#applicationNumber').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
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
	"title" : "申请编号",
	"width" : "10%",
	"data" : function(data) {
		return data.applicationNumber;
	}
	}, {
	"title" : "申请名称",
	"width" : "10%",
	"data" : function(data) {
		return data.applicationName;
	}
	}, {
		"title" : "申请职位",
		"width" : "8%",
		"data" : function(data) {
			return data.jobApplication;
		}
	}, {
	"title" : "申请人",
	"width" : "7%",
	"data" : function(data) {
		return data.uploadPersonName;
	}
	}, {
	"title" : "部门",
	"width" : "10%",
	"data" : function(data) {
		return data.applicationDepartment;
	}
	}, {
		"title" : "申请日期",
		"width" : "10%",
		"data" : function(data) {
			return data.applicationDateStr;
		}
	}, {
		"title" : "申请理由",
		"width" : "20%",
		"data" : function(data) {
			return data.reasonOfTheApplication;
		}
	}, {
		"title" : "审批状态",
		"width" : "10%",
		"data" : function(data) {
			if (data.approvalStatus == '1') {
				return "<span class='label label-info'>通过</span>";
			} else if (data.approvalStatus == '2') {
				return "<span class='label label-primary'>未通过</span>";
			}else if (data.approvalStatus == '3') {
				return "<span class='label label-primary'>待议</span>";
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

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/hr/nvixRecruitStaffAction!applyList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var applicationNumber = $("#applicationNumber").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"applicationNumber" : applicationNumber
		};
	}, 10);
    
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixRecruitStaffAction!deleteApplyById.action?id=' + id, '是否删除该申请?', vehicleTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixRecruitStaffAction!goSaveOrUpdateApply.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
</script>