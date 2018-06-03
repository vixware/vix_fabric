<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 供应商管理<span>> 商品管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
					<i class="fa fa-plus"></i>&nbsp;新增
				</button>
				<button class="btn btn-primary" type="button" onclick="goChooseItem();">
					<i class="fa fa-search"></i>&nbsp;选择商品
				</button>
			</div>
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
							storeItemTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>${vv:varView("vix_mdm_item")}列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									编码:
									<div class="form-group">
										<input type="text" value="" class="form-control" id="iCode">
									</div>
									名称:
									<div class="form-group">
										<input type="text" value="" class="form-control" id="iName">
									</div>
									<button onclick="storeItemTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#iCode').val('');$('#iName').val('');storeItemTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="storeItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var storeItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "编码",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "商品主图",
	"name" : "name",
	"data" : function(data) {
		var result = "&nbsp;&nbsp;<span style=\"cursor: pointer;font-weight: bold;\" onmouseover=\"$('#epImageShow_" + data.id + "').attr('style','position: absolute;padding:-50px;');\" onmouseout=\"$('#epImageShow_" + data.id + "').attr('style','display:none;');\">";
		result += "<img src=\"" + data.imageFilePath + "\"  width='20' height='20'/>";
		result += "</span><img id=\"epImageShow_" + data.id + "\" src=\"" + data.imageFilePath + "\"  width='100' height='100' style='display:none;position: absolute;'/>"
		return result;
	}
	}, {
	"title" : "规格",
	"name" : "specification",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"title" : "sku编码",
	"name" : "skuCode",
	"data" : function(data) {
		return data.skuCode;
	}
	}, {
	"title" : "销售价格",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"title" : "会员价格",
	"name" : "vipPrice",
	"data" : function(data) {
		return data.vipPrice;
	}
	}, {
	"title" : "采购价格",
	"name" : "purchasePrice",
	"data" : function(data) {
		return data.purchasePrice;
	}
	}, {
	"title" : "供应商",
	"data" : function(data) {
		return data.supplierName;
	}
	}, {
	"title" : "操作",
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var updatePrice = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goUpdatePrice('" + data.id + "');\" title='改价'><span class='txt-color-blue pull-right'><i class='fa fa-edit'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + updatePrice + "&nbsp;&nbsp;" + del;
	}
	} ];

	var storeItemTable = initDataTable("storeItemTableId", "${nvix}/nvixnt/vixntSupplierItemAction!goSingleList.action", storeItemColumns, function(page, pageSize, orderField, orderBy) {
		var itemcode = $("#iCode").val();
		var searchName = $("#iName").val();
		searchName = Url.encode(searchName);
		var categoryId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"categoryId" : categoryId,
		"treeType" : treeType,
		"itemcode" : itemcode,
		"searchName" : searchName
		};
	}, 10, '0');
	//删除商品
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierItemAction!deleteById.action?id=' + id, '是否删除商品?', storeItemTable);
	};
	//修改采购价格
	function goUpdatePrice(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntSupplierItemAction!goUpdatePrice.action?id=' + id, "priceForm", "修改价格", 750, 245, storeItemTable);
	};
	//新增商品
	function goSaveOrUpdate(id) {
		if (id != null && id != '') {
			saveOrUpdate('${nvix}/nvixnt/vixntSupplierItemAction!goSaveOrUpdate.action?id=' + id);
		} else {
			if ($("#selectId").val() != '' && $("#selectId").val() != null) {
				saveOrUpdate('${nvix}/nvixnt/vixntSupplierItemAction!goSaveOrUpdate.action?id=' + id + "&categoryId=" + $("#selectId").val());
			} else {
				layer.alert("请选择分类!");
			}
		}
	};
	function saveOrUpdate(url) {
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}
	//选择总部商品
	function goChooseItem() {
		if ($("#selectId").val() != '' && $("#selectId").val() != null) {
			chooseListData('${nvix}/nvixnt/vixntSupplierItemAction!goChooseItem.action?categoryId=' + $("#selectId").val(), 'multi', '选择商品', null, function() {
				var emp = chooseMap.valueSetWithClear().split(":");
				if (emp != '' && emp.length == 2) {
					$.ajax({
					url : '${nvix}/nvixnt/vixntSupplierItemAction!itemToSupplier.action?itemIds=' + emp[0],
					cache : false,
					success : function() {
						loadContent('', '${nvix}/nvixnt/vixntSupplierItemAction!goList.action');
					}
					});
				} else {
					layer.alert("请选择商品!");
				}
			}, 750, 550);
		} else {
			layer.alert("请选择分类!");
		}
	};
	pageSetUp();
</script>