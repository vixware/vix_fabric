<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>招聘管理<span>> 高级人才招聘</span>
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
			<h2>高级人才招聘</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						姓名: <input type="text" value="" class="form-control" id="applicantsName" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#applicantsName').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
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
	"title" : "姓名",
	"width" : "10%",
	"data" : function(data) {
		return data.applicantsName;
	}
	}, {
	"title" : "学历",
	"width" : "10%",
	"data" : function(data) {
		if (data.highestDegree == '1') {
			return "<span class='label label-info'>本科</span>";
		} else if (data.highestDegree == '2') {
			return "<span class='label label-primary'>硕士</span>";
		}else if (data.highestDegree == '3') {
			return "<span class='label label-primary'>博士</span>";
		}
		return "";
	}
	}, {
		"title" : "职位名称",
		"width" : "10%",
		"data" : function(data) {
			return data.employmentObjective;
		}
	}, {
	"title" : "应聘部门",
	"width" : "10%",
	"data" : function(data) {
		return data.applicantsDepartment;
	}
	}, {
	"title" : "所属招聘阶段",
	"width" : "10%",
	"data" : function(data) {
		if (data.recruitmentStage == '1') {
			return "<span class='label label-info'>日常寻访联系阶段</span>";
		} else if (data.recruitmentStage == '2') {
			return "<span class='label label-primary'>准备入职联系阶段</span>";
		}
		return "";
	}
	}, {
		"title" : "结论",
		"width" : "10%",
		"data" : function(data) {
			if (data.conclusion == '1') {
				return "<span class='label label-info'>通过</span>";
			}else if (data.conclusion == '2') {
				return "<span class='label label-primary'>未通过</span>";
			}else if (data.conclusion == '2') {
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

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/hr/nvixRecruitStaffAction!superTalentList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var applicantsName = $("#applicantsName").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"applicantsName" : applicantsName
		};
	}, 10);
    
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixRecruitStaffAction!deleteApplyById.action?id=' + id, '是否删除该申请?', vehicleTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixRecruitStaffAction!goSaveOrUpdateSuperTalent.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
</script>