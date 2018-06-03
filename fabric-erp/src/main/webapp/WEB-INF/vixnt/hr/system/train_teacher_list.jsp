<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训体系<span>> 培训讲师</span>
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
			<h2>讲师列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						姓名: <input type="text" value="" class="form-control" id="lecturerName" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#lecturerName').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
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
	"width" : "7%",
	"data" : function(data) {
		return data.lecturerName;
	}
	}, {
	"title" : "讲师性质",
	"width" : "10%",
	"data" : function(data) {
		if (data.lecturerNature == '1') {
			return "<span class='label label-info'>内部讲师</span>";
		}else if (data.lecturerNature == '2') {
			return "<span class='label label-primary'>外部讲师</span>";
		}
		return "";
	}
	}, {
		"title" : "职位",
		"width" : "10%",
		"data" : function(data) {
			return data.lecturerPosition;
		}
	}, {
		"title" : "部门",
		"width" : "10%",
		"data" : function(data) {
			return data.branches;
		}
	}, {
		"title" : "讲师级别",
		"width" : "10%",
		"data" : function(data) {
			if (data.lecturerLevel == '1') {
				return "<span class='label label-info'>助理讲师</span>";
			}else if (data.lecturerLevel == '2') {
				return "<span class='label label-primary'>讲师</span>";
			}else if (data.lecturerLevel == '2') {
				return "<span class='label label-primary'>高级讲师</span>";
			}
			return "";
		}
	}, {
		"title" : "讲师类别",
		"width" : "8%",
		"data" : function(data) {
			if (data.lecturerType == '1') {
				return "<span class='label label-info'>内聘讲师</span>";
			}else if (data.lecturerType == '2') {
				return "<span class='label label-primary'>外聘讲师</span>";
			}
			return "";
		}
	}, {
		"title" : "费用",
		"width" : "7%",
		"data" : function(data) {
			return data.lecturerCost;
		}
	}, {
		"title" : "简介",
		"width" : "18%",
		"data" : function(data) {
			return data.lecturerIntroduction;
		}
	}, {
	"title" : "操作",
	"width" : "8%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/hr/nvixTrainSystemAction!teacherList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var lecturerName = $("#lecturerName").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"lecturerName" : lecturerName
		};
	}, 10);
    
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixTrainSystemAction!deleteTeacherById.action?id=' + id, '是否删除该讲师信息?', vehicleTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixTrainSystemAction!goSaveOrUpdateTeacher.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
</script>