<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训体系<span>> 培训课程</span>
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
			<h2>课程列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						课程名称: <input type="text" value="" class="form-control" id="courseName" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#courseName').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
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
	"title" : "课程名称",
	"width" : "10%",
	"data" : function(data) {
		return data.courseName;
	}
	}, {
	"title" : "课程类别",
	"width" : "10%",
	"data" : function(data) {
		if (data.courseType == '1') {
			return "<span class='label label-info'>入职培训</span>";
		}else if (data.courseType == '2') {
			return "<span class='label label-primary'>技术课程</span>";
		}else if (data.courseType == '3') {
			return "<span class='label label-primary'>业务课程</span>";
		}else if(data.courseType == '4'){
			return "<span class='label label-primary'>管理课程</span>";
		}
		return "";
	}
	}, {
		"title" : "课程性质",
		"width" : "10%",
		"data" : function(data) {
			return data.natureCourse;
		}
	}, {
		"title" : "需要课时",
		"width" : "10%",
		"data" : function(data) {
			return data.needs;
		}
	}, {
		"title" : "有效开始时间",
		"width" : "10%",
		"data" : function(data) {
			return data.effectiveStartDateStr;
		}
	}, {
		"title" : "有效结束时间",
		"width" : "10%",
		"data" : function(data) {
			return data.effectiveEndDateStr;
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

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/hr/nvixTrainSystemAction!goCourseList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var courseName = $("#courseName").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"courseName" : courseName
		};
	}, 10);
    
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixTrainSystemAction!deleteCourseById.action?id=' + id, '是否删除该课程信息?', vehicleTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixTrainSystemAction!goSaveOrUpdateCourse.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
</script>