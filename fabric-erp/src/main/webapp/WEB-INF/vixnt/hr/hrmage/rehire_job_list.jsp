<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>人事事务管理<span>> 返聘 </span>
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
			<h2>事务列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						当事人: <input type="text" value="" class="form-control" id="employeeName" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#employeeName').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="regularTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var vehicleColumns = [ {
	"title" : "序号",
	"width" : "8%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "主题",
	"width" : "8%",
	"data" : function(data) {
		return data.theme;
	}
	}, {
	"title" : "类型",
	"width" : "10%",
	"data" : function(data) {
		if (data.types == 'regular') {
			return "<span class='label label-info'>转正</span>";
		} else if (data.types == 'unusual') {
			return "<span class='label label-primary'>异动</span>";
		}else if (data.types == 'borrow') {
			return "<span class='label label-primary'>借调</span>";
		}else if (data.types == 'leave') {
			return "<span class='label label-primary'>请假</span>";
		}else if (data.types == 'dimission') {
			return "<span class='label label-primary'>离职</span>";
		}else if (data.types == 'refuse') {
			return "<span class='label label-primary'>辞退</span>";
		}else if (data.types == 'retire') {
			return "<span class='label label-primary'>离退休</span>";
		}else if (data.types == 'rehire') {
			return "<span class='label label-primary'>返聘</span>";
		}
		return "";
	}
	}, {
	"title" : "当事人",
	"width" : "15%",
	"data" : function(data) {
		return data.litigant;
	}
	}, {
		"title" : "经办人",
		"width" : "15%",
		"data" : function(data) {
			return data.gestores;
		}
	}, {
		"title" : "审批人",
		"width" : "15%",
		"data" : function(data) {
			if (data.employee != null && data.employee.name != null) {
				return data.employee.name;
			}
			return "";
		}
	}, {
		"title" : "返聘时间",
		"width" : "11%",
		"data" : function(data) {
			return data.licenseTimeStr;
		}
	}, {
		"title" : "事务状态",
		"width" : "9%",
		"data" : function(data) {
			if (data.transactionState == '1') {
				return "<span class='label label-info'>通过</span>";
			} else if (data.transactionState == '0') {
				return "<span class='label label-primary'>待议</span>";
			}else{
				return "<span class='label label-primary'>未通过</span>";
			}
			return "";
		}
	}, {
	"title" : "操作",
	"width" : "9%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var vehicleTable = initDataTable("regularTableId", "${nvix}/nvixnt/hr/nvixHrManageAction!goRehireList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var employeeName = $("#employeeName").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"employeeName" : employeeName
		};
	}, 10);

	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixHrManageAction!deleteById.action?id=' + id, '是否删除该事务信息?', vehicleTable);
	};
	
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixHrManageAction!goSaveOrUpdateRehire.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>