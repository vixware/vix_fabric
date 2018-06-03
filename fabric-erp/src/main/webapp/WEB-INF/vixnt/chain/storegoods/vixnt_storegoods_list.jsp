<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 门店管理<span>> 业务管理 </span><span>> 门店库存台账管理 </span>
			</h1>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>商品分类</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							invWarehouseTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>库存列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="" style="padding-bottom: 60px;">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										商品名称: <input type="text" value="" class="form-control" id="itemname">
									</div>
									<button onclick="invWarehouseTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#itemname').val('');invWarehouseTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="invWarehouseTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>

</div>
<script type="text/javascript">
	var invWarehouseColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"class" : 'details-control',
	"orderable" : false,
	"data" : null,
	"defaultContent" : ''
	}, {
	"title" : "商品编码",
	"data" : function(data) {
		return data.itemcode;
	}
	}, {
	"title" : "SKU编码",
	"data" : function(data) {
		return data.skuCode;
	}
	}, {
	"title" : "商品名称",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"title" : "库存数量",
	"data" : function(data) {
		return "<span class='badge bg-color-greenLight'>" + data.avaquantity + "</span>";
	}
	}, {
	"title" : "价格",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"title" : "库存成本",
	"data" : function(data) {
		return data.costPrice;
	}
	}, {
	"title" : "仓库",
	"data" : function(data) {
		return data.warehouseName;
	}
	}, {
	"title" : "货位",
	"data" : function(data) {
		return data.invShelfName;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改库存数量'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		return update;
	}
	} ];

	var invWarehouseTable = initDataTable("invWarehouseTableId", "${nvix}/nvixnt/vixntStoreGoodsAction!goSingleList.action", invWarehouseColumns, function(page, pageSize, orderField, orderBy) {
		var itemname = $("#itemname").val();
		var selectId = $("#selectId").val();
		itemname = Url.encode(itemname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"itemname" : itemname,
		"selectId" : selectId
		};
	}, 10, '0', '0');

	function format(d) {
		return '<table cellpadding="5" cellspacing="0" border="0" class="table table-hover table-condensed">' + '<tr>' + '<td style="width:100px">供应商:</td>' + '<td>' + d.supplierName + '</td>' + '</tr>' + '<tr>' + '<td style="width:100px">到期日:</td>' + '<td>' + d.massunitEndTimeStr + '</td>' + '</tr>' + '<tr>' + '<td>单位:</td>' + '<td>' + d.unit + '</td>' + '</tr>' + '</table>';
	};
	$('#invWarehouseTableId tbody').on('click', 'td.details-control', function() {
		var tr = $(this).closest('tr');
		var row = invWarehouseTable.row(tr);
		if (row.child.isShown()) {
			row.child.hide();
			tr.removeClass('shown');
		} else {
			row.child(format(row.data())).show();
			tr.addClass('shown');
		}
	});

	function importXlsFile() {
		$.ajaxFileUpload({
		url : '${nvix}/nvixnt/vixntStoreGoodsAction!importFile.action',//用于文件上传的服务器端请求地址
		secureuri : true,//是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',//文件上传空间的id属性  <input type="file" id="file" name="file" />
		dataType : 'text',//返回值类型 ,可以使xml、text、json、html
		success : function(data, status) { //服务器成功响应处理函数
			var data = $.parseJSON(data);
			if (data.success == '1') {
				loadContent('', '${nvix}/nvixnt/vixntStoreGoodsAction!goList.action');
			} else {
				if (typeof (data.error) != 'undefined') {
					if (data.error != '') {
						showMessage(data.error, 'error');
					} else {
						showMessage(data.msg, 'success');
					}
				}
			}
		},
		error : function(data, status, e) {
			showMessage("上传错误!", 'error');
		}
		});
	};

	function goSaveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntStoreGoodsAction!goSaveOrUpdate.action?id=' + id, "invWarehouseForm", "修改数量", 750, 275, invWarehouseTable);
	};
	pageSetUp();
</script>