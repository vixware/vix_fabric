<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-cubes"></i> 库存管理 <span>&gt; 入库管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu js-status-update pull-right">
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdate('');">普通入库单</a></li>
						<li><a href="javascript:void(0);" onclick="goChoosePurchaseOrder();">采购入库单</a></li>
					</ul>
				</div>
				<button class="btn btn-primary" type="button" onclick="$('#fileToUpload').click()">
					<i class="glyphicon glyphicon-import"></i>&nbsp;导入
				</button>
				<button class="btn btn-primary" type="button" onclick="downloadTemplate()">
					<i class="glyphicon glyphicon-save"></i>&nbsp;模板下载
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>入库单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							主题: <input type="text" value="" class="form-control" id="mytitle" style="width: 250px;">
						</div>
						<button onclick="stockRecordsTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#mytitle').val('');stockRecordsTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
						<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '350px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 高级检索
						</button>
					</form>
				</div>
				<table id="stockRecordsTableId" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<!-- <tr>
							<th></th>
							<th class="hasinput"><input type="text" class="form-control" placeholder="单据编码" id="code"></th>
							<th class="hasinput"><input type="text" class="form-control" placeholder="主题" id="name" /></th>
							<th class="hasinput"><input type="text" class="form-control" placeholder="仓库名称" id="warehouseName" /></th>
							<th class="hasinput"><input type="text" class="form-control" placeholder="创建人" id="creator" /></th>
							<th class="hasinput"><input type="text" class="form-control" placeholder="入库时间" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></th>
							<th></th>
						</tr> -->
						<tr>
							<th>编号</th>
							<th>单据编码</th>
							<th>主题</th>
							<th>仓库名称</th>
							<th>创建人</th>
							<th>入库时间</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<div id="advanceSearch" style="padding: 20px 15px; display: none;">
	<form id="advanceSearchForm" class="form-horizontal">
		<fieldset>
			<div class="form-group">
				<lable class="col-md-2 control-label">单据编码:</lable>
				<div class="col-md-4">
					<input type="text" value="" id="code" placeholder="单据编码" class="form-control" />
				</div>
				<lable class="col-md-2 control-label">主题:</lable>
				<div class="col-md-4">
					<input type="text" value="" id="name" placeholder="主题" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<lable class="col-md-2 control-label">创建人:</lable>
				<div class="col-md-4">
					<input type="text" value="" id="purchasePerson" placeholder="创建人" class="form-control" />
				</div>
				<lable class="col-md-2 control-label">入库时间:</lable>
				<div class="col-md-4">
					<input type="text" class="form-control" placeholder="入库时间" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
				</div>
			</div>
			<div class="form-group" style="text-align: center;">
				<button onclick="advanceSearch();" type="button" class="btn btn-primary">
					<i class="glyphicon glyphicon-search"></i> 检索
				</button>
				<button onclick="clearSearchCondition('name,title,code,purchasePerson,createTime,supplierName,supplierId,businessTypeCode,orderTypeCode',purchaseOrderTable);" type="button" class="btn btn-default">
					<i class="glyphicon glyphicon-repeat"></i> 清空
				</button>
			</div>
		</fieldset>
	</form>
</div>
<script type="text/javascript">
	var stockRecordsColumns = [ {
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"width" : "15%",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"width" : "15%",
	"name" : "invWarehouse.name",
	"data" : function(data) {
		return data.warehouseName;
	}
	}, {
	"width" : "15%",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"width" : "15%",
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goShowInboundWarehouse('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;

	}
	} ];
	var stockRecordsTable = initDataTable("stockRecordsTableId", "${nvix}/nvixnt/vixntInboundWarehouseAction!goSingleList.action", stockRecordsColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#mytitle").val();
		title = Url.encode(title);
		/* var code = $("#code").val();
		var createTime = $("#createTime").val();
		var name = $("#name").val();
		name = Url.encode(name);
		var warehouseName = $("#warehouseName").val();
		warehouseName = Url.encode(warehouseName);
		var creator = $("#creator").val();
		creator = Url.encode(creator); */
		return {
		"page" : page,
		"pageSize" : pageSize,
		/* "warehouseName" : warehouseName,
		"creator" : creator, */
		"title" : title
		/* "name" : name,
		"createTime" : createTime,
		"code" : code */
		};
	});
	//高級檢索
	function advanceSearch() {
		stockRecordsTable.ajax.reload();
		layer.closeAll('page');
	}
	//新增
	function goSaveOrUpdate(id, purchaseOrderid) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntInboundWarehouseAction!goSaveOrUpdate.action?id=' + id + "&purchaseOrderid=" + purchaseOrderid,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//查看
	function goShowInboundWarehouse(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntInboundWarehouseAction!goShowInboundWarehouse.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntInboundWarehouseAction!deleteById.action?id=' + id, '是否删除入库单?', stockRecordsTable);
	};

	function goChoosePurchaseOrder() {
		chooseListData('${nvix}/nvixnt/vixntInboundWarehouseAction!goChoosePurchaseOrder.action', 'multi', '选择采购订单', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$.ajax({
				url : '${nvix}/nvixnt/vixntInboundWarehouseAction!converterPurchaseOrderToStockrecords.action?purchaseOrderid=' + emp[0],
				cache : false,
				success : function(data) {
					var result = data.split(":");
					if (result.length > 1) {
						goSaveOrUpdate(result[0], result[1]);
					}
				}
				});
			} else {
				layer.alert("请选择商品!");
			}
		}, 750, 550);
	};
	function importXlsFile() {
		$.ajaxFileUpload({
		url : '${nvix}/nvixnt/vixntInboundWarehouseAction!importFile.action',// 用于文件上传的服务器端请求地址
		secureuri : true,// 是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',
		dataType : 'text',// 返回值类型 ,可以使xml、text、json、html
		success : function(id, status) {// 服务器成功响应处理函数
			goSaveOrUpdate(id);
		},
		error : function(data, status, e) {// 服务器响应失败处理函数
			asyncbox.success("上传错误!", "提示");
		}
		});
	};
	function downloadTemplate() {
		var url = "${nvix}/nvixnt/vixntInboundWarehouseAction!downloadTemplate.action";
		window.open(url);
	};
</script>