<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>人事管理<span>> 员工列表</span>
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
			<h2>员工列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						员工姓名: <input type="text" value="" class="form-control" id="name" style="width: 250px;">
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
			}else{
				return "";
			}
		}
	}, {
		"title" : "职务",
		"width" : "8%",
		"data" : function(data) {
			return data.subordinatePosition;
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

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/hr/nvixNameBookAction!goSingleList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#name").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name
		};
	}, 10);
    
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixNameBookAction!deleteById.action?id=' + id, '是否删除该员工信息?', vehicleTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixNameBookAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
</script>