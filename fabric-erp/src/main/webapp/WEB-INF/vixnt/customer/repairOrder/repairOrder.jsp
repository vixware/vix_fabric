<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售后管理 </span><span>> 维修工单 </span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget jarviswidget-color-darken" id="chanceAndTrackingHead" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>维修工单列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										主题:<input type="text" value="" placeholder="主题" class="form-control" id="searchSubject">
										客户:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName">
										<!-- 项目:<input type="text" value="" placeholder="项目主题" class="form-control" id="searchProjectName"> -->
									</div>
									<button onclick="repairOrderTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchSubject').val('');$('#searchCustomerName').val('');repairOrderTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="repairOrderTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>主题</th>
										<th>客户</th>
										<th>接单日期</th>
										<th>约定交付</th>
										<th>费用</th>
										<th>已收款</th>
										<th>状态</th>
										<th>承接部门</th>
										<th>接单人</th>
										<th width="8%">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var repairOrderColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "subject",
	"data" : function(data) {
		return data.subject;
	}
	}, {
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccount == null ? '' : data.customerAccount.name;
	}
	}, {
	"name" : "receiveDate",
	"data" : function(data) {
		return data.receiveDateStr;
	}
	}, {
	"name" : "deliveryDate",
	"data" : function(data) {
		return data.deliveryDateStr;
	}
	}, {
	"name" : "money",
	"data" : function(data) {
		return "￥"+ data.money;
	}
	}, {
	"name" : "backSection",
	"data" : function(data) {
		return "￥"+ data.backSection;
	}
	},{
	"name" : "status",
	"data" : function(data) {
		if(data.status == 0){
			return "<span class='label label-primary'>执行中</span>";
		}else if(data.status == 1){
			return "<span class='label label-success'>结束</span>";
		}else if(data.status == 2){
			return "<span class='label label-danger'>意外中止</span>";
		}else{
			return "";
		}
	}
	}, {
	"name" : "organizationUnit",
	"data" : function(data) {
		return data.organizationUnit == null ? '' : data.organizationUnit.fullName;
	}
	},{
	"name" : "employee",
	"data" : function(data) {
		return data.employee == null ? '' : data.employee.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (null != data.id) {
			var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var repairOrderTable = initDataTable("repairOrderTableId", "${nvix}/nvixnt/nvixRepairOrderAction!goListContent.action", repairOrderColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchSubject").val();
		var cName = $("#searchCustomerName").val();
		var project = $("#searchProjectName").val();
		name = Url.encode(name);
		cName = Url.encode(cName);
		//project = Url.encode(project);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"subject" : name,
		"customerName" : cName
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixRepairOrderAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixRepairOrderAction!deleteById.action?id=' + id, '是否删除维修工单?', repairOrderTable);
	}

	pageSetUp();
</SCRIPT>