<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理 <span>&gt; 采购询价</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a href="#" class="btn btn-primary" onclick="goSaveOrUpdate('');"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</a>
				<div class="btn-group">
				<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
					<i class="fa fa-gear"></i>&nbsp;其他&nbsp;<span class="caret"></span>
				</button>
				<ul class="dropdown-menu pull-right">
					<li><a href="javascript:void(0);" onclick="exportExcel()">导出询价单</a></li>
				</ul>
			</div>
			</div>
		</div>
	</div>
	<!-- row -->
	<div class="jarviswidget" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>询价单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							主题: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="purchaseInquireTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '230px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 高级检索
						</button>
						<button onclick="clearSearchCondition('name,searchName,code,purchasePerson,createTime',purchaseInquireTable);" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<div id="advanceSearch" style="padding:20px 15px;display:none;">
						<form id="advanceSearchForm" class="form-horizontal">
							<fieldset>
								<div class="form-group">
									<lable class="col-md-2 control-label">单据编码:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="code" placeholder="单据编码" class="form-control"/>
									</div>
									<lable class="col-md-2 control-label">主题:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="name" placeholder="主题" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<lable class="col-md-2 control-label">询价人:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="purchasePerson" placeholder="询价人" class="form-control"/>
									</div>
									<lable class="col-md-2 control-label">询价日期:</lable>
									<div class="col-md-4"> 
										<input type="text" class="form-control" placeholder="询价日期" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
									</div>
								</div>
								<div class="form-group" style="text-align:center;">
									<button onclick="advanceSearch();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="clearSearchCondition('name,searchName,code,purchasePerson,createTime',purchaseInquireTable);"
										type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</div>
							</fieldset>
						</form>
					</div>
				<table id="purchaseInquireTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//我的报销单
	var purchaseInquireColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "编码",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "主题",
	"orderable" : false,
	"data" : function(data) {
		return data.name;
	}
	},{
	"title" : "供应商",
	"orderable" : false,
	"data" : function(data) {
		return data.supplierName;
	}
	}, {
	"title" : "询价人",
	"orderable" : false,
	"data" : function(data) {
		return data.purchasePerson;
	}
	}, {
	"title" : "询价时间",
	"orderable" : false,
	"data" : function(data) {
		return data.appDateStr;
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var create = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"createPurchaseOrder('" + data.id + "');\" title='生成订单'><span class='txt-color-blue pull-right'><i class='fa fa-exchange'></i></span></a>";
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		if(data.status == "2"){
			return update+"&nbsp;&nbsp;" +show +"&nbsp;&nbsp;"+del;
		}
		return update+"&nbsp;&nbsp;"+ show+"&nbsp;&nbsp;"+create + "&nbsp;&nbsp;" + del;
	}
	} ];
	var purchaseInquireTable = initDataTable("purchaseInquireTableId", "${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!goSingleList.action", purchaseInquireColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		var code = $("#code").val();
		var purchasePerson = $("#purchasePerson").val();
		var createTime = $("#createTime").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"code" : code,
		"purchasePerson" : purchasePerson,
		"createTime" : createTime,
		"orderBy" : orderBy,
		"name" : searchName
		};
	});

	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!deleteById.action?id=' + id, '是否删除询价单?', purchaseInquireTable);
	};
	function show(id){
		goSaveOrUpdateEntity('${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!show.action?id=' + id);
	}
	//通过采购询价生成采购订单
	function createPurchaseOrder(id){
		$.ajax({
			url : '${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!createPurchaseOrder.action?id=' + id,
			cache : false,
			success : function(result) {
				var r = result.split(":");
				if(r[0] == "0"){
					showMessage(r[1]);
					loadContent('purchase_purchaseInquire','${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!goList.action');
				}else{
					$.ajax({
						url : '${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goSaveOrUpdate.action?id=' + r[2],
						cache : false,
						success : function(html) {
							$("#mainContent").html(html);
						}
						});
				}
			}
			}); 
	}
	function advanceSearch(){
		purchaseInquireTable.ajax.reload();
		layer.closeAll('page');
	}
	function exportExcel() {
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/purchase/vixntPurchaseInquireAction!exportPurchaseInquireExcel.action";
		form.submit();
	};
</script>