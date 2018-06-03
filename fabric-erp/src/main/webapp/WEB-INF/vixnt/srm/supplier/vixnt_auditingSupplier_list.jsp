<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-truck"></i>供应商管理<span>> 供应商预选与评估 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<!-- <div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div> -->
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>供应商列表</h2>
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
					<button onclick="clearSearchCondition('supplierName,code,contacts,type,industry,supplierCategoryId,cellphone,supplierCategoryName',supplierTable);" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '300px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
						<i class="glyphicon glyphicon-search"></i> 高级检索
					</button>
				</form>
				<div id="advanceSearch" style="padding:20px 15px;display:none;">
						<form id="advanceSearchForm" class="form-horizontal">
							<fieldset>
								<div class="form-group">
									<lable class="col-md-2 control-label">编码:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="code" placeholder="编码" class="form-control"/>
									</div>
									<label class="col-md-2 control-label">联系人:</label>
									<div class="col-md-4"> 
										<input type="text" value="" id="contacts" placeholder="联系人" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">类型:</label>
									<div class="col-md-4">
										<select id="type"  class="form-control">
											<option value="GENERAL">普通供应商</option>
											<option value="AGREEMENT">协议供应商</option>
											<option value="OUTSOURCING">委外供应商</option>
										</select>
									</div>
									<label class="col-md-2 control-label">所属行业:</label>
									<div class="col-md-4">
										<select id="industry" class="form-control">
											<option value="1">零售</option>
											<option value="2">生产</option>
											<option value="3">服务</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">分类:</label>
								<div class="col-md-4">
									<div class="row">
										<div class="col-sm-12">
											<div id="treeMenu" class="input-group">
												<input id="supplierCategoryId"  type="hidden"> 
												<input id="supplierCategoryName" type="text" onfocus="showICMenu(); return false;"
													 type="text" readonly="readonly" class="form-control" />
												<div class="input-group-btn">
													<button type="button" class="btn btn-default" onclick="$('#supplierCategoryId').val('');$('#supplierCategoryName').val('');">清空</button>
												</div>
												<div id="treeMenuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
													<ul id="supplierCategoryTree" class="ztree"></ul>
												</div>
											</div>
										</div>
									</div>
								</div>
									<label class="col-md-2 control-label"> 手机: </label>
									<div class="col-md-4">
										<div class="input-group">
											<input id="cellphone"  data-prompt-position="topLeft" class="form-control validate[custom[phone]]" type="text" /> <span class="input-group-addon"><i class="fa fa-phone"></i></span>
										</div>
									</div>
								</div>
								<div class="form-group" style="text-align:center;">
									<button onclick="advanceSearch();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="clearSearchCondition('supplierName,code,contacts,type,industry,supplierCategoryId,cellphone,supplierCategoryName',supplierTable);"
										type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</div>
							</fieldset>
						</form>
					</div>
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
	"name":"code",
	"title" : "编码",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"name":"name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "联系人",
	"name":"contacts",
	"data" : function(data) {
		return data.contacts;
	}
	}, {
	"title" : "电话",
	"name":"cellphone",
	"data" : function(data) {
		return data.cellphone;
	}
	}, {
	"title" : "类型",
	"name":"type",
	"data" : function(data) {
		if (data.type == 'GENERAL') {
			return "<span class='label label-info'>普通供应商</span>";
		} else if (data.type == 'AGREEMENT') {
			return "<span class='label label-primary'>协议供应商</span>";
		} else if (data.type == 'OUTSOURCING') {
			return "<span class='label label-success'>委外供应商</span>";
		}
		return "";
	}
	}, {
	"title" : "状态",
	"name":"status",
	"data" : function(data) {
		if (data.status == '1') {
			return "<span class='label label-info'>待建档</span>";
		} else if (data.status == '2') {
			return "<span class='label label-primary'>待评估</span>";
		} else if (data.status == '3') {
			return "<span class='label label-success'>正式</span>";
		} else if (data.status == '4') {
			return "<span class='label label-success'>无效</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"auditing('" + data.id + "');\" title='评估通过'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='评估'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='评估不通过'><span class='txt-color-red pull-right'><i class='fa fa-pencil'></i></span></a>";
		return show;
	}
	} ];

	var supplierTable = initDataTable("supplierTableId", "${nvix}/nvixnt/vixntSupplierAction!goSingleAuditingList.action", supplierColumns, function(page, pageSize, orderField, orderBy) {
		var supplierName = $("#supplierName").val();
		var code = $("#code").val();
		var contacts = $("#contacts").val();
		var type = $("#type").val();
		var industry = $("#industry").val();
		var supplierCategoryId = $("#supplierCategoryId").val();
		var cellphone = $("#cellphone").val();
		supplierName = Url.encode(supplierName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"code" : code,
		"contacts" : contacts,
		"type" : type,
		"industry" : industry,
		"supplierCategoryId" : supplierCategoryId,
		"cellphone" : cellphone,
		"orderBy" : orderBy,
		"supplierName" : supplierName
		};
	}, 10, '0');

	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierAction!saveOrUpdateAuditingIsNo.action?id=' + id, '是否不通过评估?', supplierTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateAuditingSupplier.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function auditing(id){
		deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierAction!saveOrUpdateAuditing.action?id=' + id, '是否通过评估?', supplierTable);
	}
	function advanceSearch(){
		supplierTable.ajax.reload();
		layer.closeAll('page');
	}
	var showICMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/vixntSupplierCategoryAction!findTreeToJson.action", "supplierCategoryId", "supplierCategoryName", "supplierCategoryTree", "treeMenuContent");
</script>