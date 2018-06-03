<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="parentId" name="parentId" value="${projectId }" />
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>项目管理<span>> 项目成员列表</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectDetail.action?id=${projectId }');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>项目成员列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						姓名: <input type="text" value="" class="form-control" id="name" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#name').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
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
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "工号",
	"width" : "7%",
	"data" : function(data) {
		return data.staffJobNumber;
	}
	}, {
	"title" : "姓名",
	"width" : "8%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "性别",
	"width" : "5%",
	"data" : function(data) {
		if (data.gender == '1') {
			return "<span class='label label-info'>男</span>";
		} else if (data.gender == '0') {
			return "<span class='label label-primary'>女</span>";
		}
		return "";
	}
	}, {
	"title" : "专业",
	"width" : "9%",
	"data" : function(data) {
		return data.professional;
	}
	}, {
	"title" : "联系方式",
	"width" : "15%",
	"data" : function(data) {
		return data.telephone;
	}
	}, {
	"title" : "家庭住址",
	"width" : "20%",
	"data" : function(data) {
		return data.currentResidence;
	}
	}, {
	"title" : "学历",
	"width" : "7%",
	"data" : function(data) {
		if (data.qualificationsCode == '1') {
			return "<span class='label label-info'>高中</span>";
		} else if (data.qualificationsCode == '2') {
			return "<span class='label label-primary'>中专</span>";
		} else if (data.qualificationsCode == '3') {
			return "<span class='label label-primary'>大专</span>";
		} else if (data.qualificationsCode == '4') {
			return "<span class='label label-primary'>本科</span>";
		} else if (data.qualificationsCode == '5') {
			return "<span class='label label-primary'>硕士</span>";
		} else if (data.qualificationsCode == '6') {
			return "<span class='label label-primary'>博士</span>";
		}
		return "";
	}
	}, {
	"title" : "部门",
	"width" : "6%",
	"data" : function(data) {
		if (data.organizationUnit != null) {
			return data.organizationUnit.fs;
		} else {
			return "";
		}
	}
	}, {
	"title" : "职务",
	"width" : "8%",
	"data" : function(data) {
		return data.subordinatePosition;
	}
	} ];

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/nvixProjectAction!goProjectEmployeeList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#parentId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId
		};
	});
</script>