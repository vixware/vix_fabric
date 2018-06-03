<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>招聘计划<span>> 汇总</span>
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
			<h2>招聘计划汇总</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						招聘职务: <input type="text" value="" class="form-control" id="recruitment" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#recruitment').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
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
	"title" : "招聘职务",
	"width" : "10%",
	"data" : function(data) {
		return data.recruitment;
	}
	}, {
	"title" : "发布时间",
	"width" : "10%",
	"data" : function(data) {
		return data.releaseTimeStr;
	}
	}, {
	"title" : "招聘部门",
	"width" : "10%",
	"data" : function(data) {
		return data.recruitmentDepartment;
	}
	}, {
	"title" : "审核人",
	"width" : "10%",
	"data" : function(data) {
		return data.auditPerson;
	}
	}, {
		"title" : "发布方式",
		"width" : "10%",
		"data" : function(data) {
			if (data.releaseMode == '1') {
				return "<span class='label label-info'>内部</span>";
			} else if (data.releaseMode == '2') {
				return "<span class='label label-primary'>外部</span>";
			}else if (data.releaseMode == '3') {
				return "<span class='label label-primary'>内部和外部</span>";
			}
			return "";
		}
	}, {
		"title" : "发布状态",
		"width" : "10%",
		"data" : function(data) {
			if (data.releaseState == '1') {
				return "<span class='label label-info'>发布</span>";
			} else if (data.releaseState == '0') {
				return "<span class='label label-primary'>不发布</span>";
			}
			return "";
		}
	}, {
		"title" : "备注",
		"width" : "25%",
		"data" : function(data) {
			return data.remarks;
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

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/hr/nvixRecruitPlanAction!collectPlanList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var recruitment = $("#recruitment").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"recruitment" : recruitment
		};
	}, 10);
    
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixRecruitPlanAction!deleteById.action?id=' + id, '是否删除该员工信息?', vehicleTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixRecruitPlanAction!goSaveOrUpdateCollect.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
</script>