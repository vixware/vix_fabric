<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 门店管理 <span>&gt; 门店业务管理</span> <span>&gt; 门店入库管理</span>
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
	<!-- row -->
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
					</form>
				</div>
				<table id="stockRecordsTableId" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
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
<script type="text/javascript">
	var stockRecordsColumns = [ {
	"width" : "7%",
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
	"width" : "26%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"width" : "15%",
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
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var stockRecordsTable = initDataTable("stockRecordsTableId", "${nvix}/nvixnt/vixntStoreInboundWarehouseAction!goSingleList.action", stockRecordsColumns, function(page, pageSize, orderField, orderBy) {
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
		"title" : title/* ,
		"warehouseName" : warehouseName,
		"creator" : creator,
		"name" : name,
		"createTime" : createTime,
		"code" : code */
		};
	});

	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntStoreInboundWarehouseAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntStoreInboundWarehouseAction!deleteById.action?id=' + id, '是否删除入库单?', stockRecordsTable);
	};

	function goChoosePurchaseOrder() {
		chooseListData('${nvix}/nvixnt/vixntStoreInboundWarehouseAction!goChoosePurchaseOrder.action', 'multi', '选择采购订单', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$.ajax({
				url : '${nvix}/nvixnt/vixntStoreInboundWarehouseAction!converterPurchaseOrderToStockrecords.action?purchaseOrderid=' + emp[0],
				cache : false,
				success : function(id) {
					goSaveOrUpdate(id);
				}
				});
			} else {
				layer.alert("请选择商品!");
			}
		}, 750, 550);
	};
	function importXlsFile() {
		$.ajaxFileUpload({
		url : '${nvix}/nvixnt/vixntStoreInboundWarehouseAction!importFile.action',// 用于文件上传的服务器端请求地址
		secureuri : true,// 是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',
		dataType : 'text',// 返回值类型 ,可以使xml、text、json、html
		success : function(result,status) {// 服务器成功响应处理函数
			var r = result.split(":");
			if(r[0]=="0"){
				layer.alert(r[1]);
			}else if(r[1] == "1"){
				goSaveOrUpdate(r[1]);
			}
		},
		error : function(data, status, e) {// 服务器响应失败处理函数
			asyncbox.success("上传错误!", "提示");
		}
		});
	};
	function downloadTemplate() {
		var url = "${nvix}/nvixnt/vixntStoreInboundWarehouseAction!downloadTemplate.action";
		window.open(url);
	};
</script>