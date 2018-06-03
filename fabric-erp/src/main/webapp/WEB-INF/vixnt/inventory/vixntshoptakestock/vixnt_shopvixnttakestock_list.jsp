<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 门店管理 <span>&gt; 门店业务管理</span> <span>&gt; 门店盘点管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a href="#" class="btn btn-primary" onclick="goChooseWarehouseAndShelf('');"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</a>
			</div>
		</div>
	</div>
	<!-- row -->
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>盘点单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						主题: <input type="text" value="" class="form-control" id="title">
					</div>
					<button onclick="vixntTakeStockTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#title').val('');vixntTakeStockTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="vixntTakeStockTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="stockTakingId" />
<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<script type="text/javascript">
	var vixntTakeStockColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "编码",
	"width" : "15%",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "主题",
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "仓库名称",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return data.warehouseName;
	}
	}, {
	"title" : "创建人",
	"width" : "10%",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"title" : "盘点时间",
	"width" : "15%",
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var export1 = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"exportExcel('" + data.id + "');\" title='导出盘点表模板'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-export'></i></span></a>";
		var import1 = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"setValue('" + data.id + "');$('#fileToUpload').click();\" title='导入盘点表'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-import'></i></span></a>";
		var export2 = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"exportExcel1('" + data.id + "');\" title='导出盘点差异表'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-export'></i></span></a>";
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='glyphicon glyphicon-edit'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='glyphicon glyphicon-remove'></i></span></a>";
		return export1 + "&nbsp;&nbsp;" + import1 + "&nbsp;&nbsp;" + export2 + "&nbsp;&nbsp;" + update + "&nbsp;&nbsp;" + del;
	}
	} ];
	var vixntTakeStockTable = initDataTable("vixntTakeStockTableId", "${nvix}/nvixnt/vixntShopTakeStockAction!goSingleList.action", vixntTakeStockColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#title").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"title" : title
		};
	});
	function goChooseWarehouseAndShelf() {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntShopTakeStockAction!goChooseWarehouseAndShelf.action', "warehouseform", "盘点", 650, 455, null, null, function(id) {
			goSaveOrUpdate(id);
		});
	};

	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntShopTakeStockAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntShopTakeStockAction!deleteById.action?id=' + id, '是否删除盘点单?', vixntTakeStockTable);
	};
	//导出盘点单
	function exportExcel(id) {
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/vixntShopTakeStockAction!exportTakeStockExcel2.action?id=" + id;
		form.submit();
	};
	//导出盘点对比表
	function exportExcel1(id) {
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/vixntShopTakeStockAction!exportTakeStockExcel1.action?id=" + id;
		form.submit();
	}
	function importXlsFile() {
		$.ajaxFileUpload({
		url : '${nvix}/nvixnt/vixntShopTakeStockAction!importFile.action?id=' + $('#stockTakingId').val(),// 用于文件上传的服务器端请求地址
		secureuri : true,// 是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',
		dataType : 'text',// 返回值类型 ,可以使xml、text、json、html
		success : function(data, status) {// 服务器成功响应处理函数
			var data = $.parseJSON(data);
			if (data.success == '1') {
				asyncbox.success(data.msg, "提示");
			} else {
				if (typeof (data.error) != 'undefined') {
					if (data.error != '') {
						asyncbox.alert(data.error, "上传错误");
					} else {
						alert(data.msg);
					}
				}
			}
		},
		error : function(data, status, e) {// 服务器响应失败处理函数
			asyncbox.success("上传错误!", "提示");
		}
		});
	};
	function setValue(id) {
		$('#stockTakingId').val(id);
	};
</script>