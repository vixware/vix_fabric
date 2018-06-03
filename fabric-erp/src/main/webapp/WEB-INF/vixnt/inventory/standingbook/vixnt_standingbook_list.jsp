<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 库存管理<span>> 台账管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntInitInventoryAction!goList.action');">
					<i class="fa fa-cubes"></i>&nbsp;期初入库
				</button>
				<button class="btn btn-primary" type="button" onclick="$('#fileToUpload').click()">
					<i class="glyphicon glyphicon-import"></i>&nbsp;导入修改价格
				</button>
				<button class="btn btn-primary" type="button" onclick="exportExcel();">
					<i class="glyphicon glyphicon-import"></i>&nbsp;导出库存
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>仓库</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /><input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vixntWarehouseAction!findInvWarehouseTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							inventoryCurrentStockTable.ajax.reload();
							$("#invShelf").load("${nvix}/nvixnt/vixntStandingBookAction!loadInvShelfList.action?parentId="+$("#selectId").val());
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
						<h2>商品列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										商品名称: <input type="text" value="" class="form-control" id="itemname">
										<input type="hidden" class="form-control" id="invShelfId">
										<input type="hidden" class="form-control" id="invShelfName">
										货位:<div class="form-group" id="invShelf">
											<div class="input-group" >
				                                <input type="text" class="form-control" id="invShelfJson" value="">
				                                <div class="input-group-btn">
				                                    <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
				                                        <span class="caret"></span>
				                                    </button>
				                                    <ul class="dropdown-menu dropdown-menu-right" role="menu">
				                                    </ul>
				                                </div>
				                            </div>
										</div>
										<script type="text/javascript">
										 var testdataBsSuggest = $("#invShelfJson").bsSuggest({
										     indexId: 1,
										     indexKey: 1,
										     idField: "id",                 
										     keyField: "",              
										     effectiveFields: ["word"], 
										     listStyle: {
										         "padding-top":0, "max-height": "123px", "max-width": "300px",
										         "overflow": "auto", "width": "auto",
										         "transition": "0.3s", "-webkit-transition": "0.3s", "-moz-transition": "0.3s", "-o-transition": "0.3s"       
										     },                            
										     data:${invShelfJson}
										 });
										 $("input#invShelfJson").on("onSetSelectValue", function (event, result) {
											    var sID = result.id;
											    var name = result.key;
											    if(sID=='all'){
											    	$("#invShelfId").val('');
											    	$("#invShelfName").val('');
											    }else{
											    	$("#invShelfId").val(sID);
												    $("#invShelfName").val(name);
											    }
											});
										</script>
									</div>
									<button onclick="inventoryCurrentStockTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#itemname').val('');$('#invShelfJson').val('');$('#invShelfName').val('');$('#invShelfId').val('');inventoryCurrentStockTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="inventoryCurrentStockTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var inventoryCurrentStockColumns = [ {
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
	"title" : "商品名称",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"title" : "SKU编码",
	"data" : function(data) {
		return data.skuCode;
	}
	}, {
	"title" : "规格",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"title" : "库存数量",
	"data" : function(data) {
		return "<span class='badge bg-color-blueLight'>" + data.quantity + " " + data.measureUnitName + "</span>";
	}
	}, {
	"title" : "可用数量",
	"data" : function(data) {
		return "<span class='badge bg-color-greenLight'>" + data.avaquantity + " " + data.measureUnitName + "</span>";
	}
	}, {
	"title" : "价格",
	"data" : function(data) {
		return data.price + " 元";
	}
	}, {
	"title" : "库存成本",
	"data" : function(data) {
		return data.price * data.avaquantity + " 元";
	}
	}, {
	"title" : "所在仓库",
	"data" : function(data) {
		return data.warehouse;
	}
	}, {
	"title" : "货位",
	"data" : function(data) {
		return data.invShelfName;
	}
	} ];

	var inventoryCurrentStockTable = initDataTable("inventoryCurrentStockTableId", "${nvix}/nvixnt/vixntStandingBookAction!goSingleList.action", inventoryCurrentStockColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var itemname = $("#itemname").val();
		itemname = Url.encode(itemname);
		var invShelfName = $("#invShelfName").val();
		invShelfName = Url.encode(invShelfName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"parentId" : parentId,
		"itemname" : itemname,
		"invShelfName" : invShelfName,
		"treeType" : treeType
		};
	}, 10, '0', '0');

	function format(d) {
		return '<table cellpadding="5" cellspacing="0" border="0" class="table table-hover table-condensed">' + '<tr>' + '<td style="width:100px">批次号:</td>' + '<td>' + d.batchcode + '</td>' + '</tr>' + '<tr>' + '<td style="width:100px">供应商:</td>' + '<td>' + d.supplierName + '</td>' + '</tr>' + '<tr>' + '<td style="width:100px">到期日:</td>' + '<td>' + d.massunitEndTimeStr + '</td>' + '</tr>' + '<tr>' + '<td>单位:</td>' + '<td>' + d.measureUnitName + '</td>' + '</tr>' + '<tr>' + '<td>操作:</td>' + '<td><button class="btn btn-xs btn-danger pull-right" style="margin-left:5px" onclick="deleteItemById(\'' + d.id + '\');">删除</button> </td>' + '</tr>' + '</table>';
	};
	$('#inventoryCurrentStockTableId tbody').on('click', 'td.details-control', function() {
		var tr = $(this).closest('tr');
		var row = inventoryCurrentStockTable.row(tr);
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
		url : '${nvix}/nvixnt/vixntStandingBookAction!importFile.action',//用于文件上传的服务器端请求地址
		secureuri : true,//是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',//文件上传空间的id属性  <input type="file" id="file" name="file" />
		dataType : 'text',//返回值类型 ,可以使xml、text、json、html
		success : function(data, status) { //服务器成功响应处理函数
			var data = $.parseJSON(data);
			if (data.success == '1') {
				loadContent('inv_vixntstandingbook', '${nvix}/nvixnt/vixntStandingBookAction!goList.action');
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
			//服务器响应失败处理函数
			showMessage("上传错误!", 'error');
		}
		});
	};
	//删除
	function deleteItemById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntStandingBookAction!deleteById.action?id=' + id, '是否删除库存商品?', inventoryCurrentStockTable);
	};
	function exportExcel() {
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/vixntStandingBookAction!exportInventoryExcel.action";
		form.submit();
	};
</script>