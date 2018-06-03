<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-book"></i> <a style="cursor: pointer;">合同管理</a><span>> 合同列表 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						</i>&nbsp;新增&nbsp;<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdate('1','');">采购合同</a></li>
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdate('2','')">采购框架协议</a></li>
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdate('3','')">销售合同</a></li>
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdate('4','')">销售框架协议</a></li>
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdate('5','')">项目合同</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>合同列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						名称: <input type="text" value="" class="form-control" id="supplierName" style="width: 250px;">
					</div>
					<button onclick="supplierTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#supplierName').val('');supplierTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="supplierTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var supplierColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "编码",
	"data" : function(data) {
		return data.contractCode;
	}
	}, {
	"title" : "合同名称",
	"data" : function(data) {
		return data.contractName;
	}
	}, {
		"title" : "合同类型",
		"data" : function(data) {
			if(data.contractType == '1'){
				return "采购合同";
			}else if(data.contractType == '2'){
				return "采购框架协议";
			}else if(data.contractType == '3'){
				return "销售合同";
			}else if(data.contractType == '4'){
				return "销售框架协议";
			}else if(data.contractType == '5'){
				return "项目合同";
			}else if(data.contractType == '6'){
				return "劳动合同";
			}
			return "";
		}
		}, {
		"title" : "项目名称",
		"data" : function(data) {
			return data.projectName;
		}
		}, {
		"title" : "金额",
		"data" : function(data) {
			return data.totalAmount;
		}
		}, {
		"title" : "经办人",
		"data" : function(data) {
			return data.operator;
		}
		}, {
		"title" : "批准人",
		"data" : function(data) {
			return data.approval;
		}
		}, {
		"title" : "签订日期",
		"data" : function(data) {
			return data.signDateStr;
		}
		}, {
		"title" : "合同状态",
		"data" : function(data) {
			if(data.status == "0"){
				return "<span class='label label-success'>正常</span>"
			}else if(data.status == "1"){
				return "<span class='label label-primary'>还有"+data.dayTime+"天到期</span>"
			}else if(data.status == "2"){
				return "<span class='label label-warning'>已到期</span>";
			}
			return "";
		}
		}, {
		"title" : "操作",
		"width" : "10%",
		"orderable" : false,
		"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.contractType +"','"+ data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var supplierTable = initDataTable("supplierTableId", "${nvix}/nvixnt/contract/vixntContractAction!goSingleList.action", supplierColumns, function(page, pageSize, orderField, orderBy) {
		var supplierName = $("#supplierName").val();
		supplierName = Url.encode(supplierName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"supplierName" : supplierName
		};
	}, 10, '0');

	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntContractAction!deleteById.action?id=' + id, '是否删除协议?', supplierTable);
	};
	function goSaveOrUpdate(type,id) {
		$.ajax({
		url : '${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdate.action?id=' + id+'&type='+type,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>