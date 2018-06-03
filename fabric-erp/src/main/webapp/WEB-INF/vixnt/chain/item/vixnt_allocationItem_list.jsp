<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 连锁管理<span> > 商品库管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-search"></i>&nbsp;选择分配&nbsp;<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);" onclick="goChooseItem();"> 分配商品 </a></li>
						<li><a href="javascript:void(0);" onclick="bathBindItemToSupplierByItem();"> 分配供应商 </a></li>
					</ul>
				</div>
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-search"></i>&nbsp;全部分配&nbsp;<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);" onclick="uploadItemCatalogToStore();"> 分配分类 </a></li>
						<li><a href="javascript:void(0);" onclick="bathItemToChannelDistributor();"> 分配商品 </a></li>
						<li><a href="javascript:void(0);" onclick="bathBindItemToSupplier();"> 分配供应商 </a></li>
					</ul>
				</div>
				<button class="btn btn-primary" type="button" onclick="goSupplierItemList();">
					<i class="fa fa-list-alt"></i>&nbsp;供应商商品
				</button>
			</div>
		</div>
	</div>
	<section>
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>门店</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vixntDistributionSystemAction!findOrgAndUnitTreeToJson.action",
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
									<input type="hidden" id="supplierId" name="" value="" />
									<div class="form-group">
										商品名称: <input type="text" value="" class="form-control" id="searchName">
									</div>
									<button onclick="storeItemTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchName').val('');storeItemTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="storeItemTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="10%">
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default">
													选择 <i class="fa fa-caret-down"></i>
												</button>
												<ul class="dropdown-menu js-status-update pull-left">
													<li><a id="all" onclick="changeDataTableSelect('storeItemTableId','all');" href="javascript:void(0);">全选</a></li>
													<li><a id="reverse" onclick="changeDataTableSelect('storeItemTableId','reverse');" href="javascript:void(0);">反选</a></li>
													<li><a id="cancle" onclick="changeDataTableSelect('storeItemTableId','cancle');" href="javascript:void(0);">取消</a></li>
												</ul>
											</div>
										</th>
										<th>编码</th>
										<th>名称</th>
										<th>规格</th>
										<th>价格</th>
										<th>图片</th>
										<th>供应商</th>
										<th>操作</th>
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
	chooseMap.setColumnName("name");
	var storeItemColumns = [ {
	"orderable" : false,
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
		"data" : function(data) {
			return data.code;
		}
	}, {
		"data" : function(data) {
			return data.name;
		}
	}, {
		"data" : function(data) {
			return data.specification;
		}
	}, {
		"data" : function(data) {
			return data.price;
		}
	}, {
		"data" : function(data) {
			if(data.itemImageFilePath != null){
				return "<div class='project-members'>" + data.itemImageFilePath + "</div>";	
			}
			return '';
		}
	}, {
		"data" : function(data) {
			return data.supplierName;
		}
	}, {
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goChooseSupplier('" + data.id + "');\" title='绑定供应商'><span class='txt-color-blue pull-right'><i class='fa fa-share-alt'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];
	var storeItemTable = initDataTable("storeItemTableId", "${nvix}/nvixnt/vixntAllocationItemAction!goSingleList.action", storeItemColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"treeType" : treeType,
		"searchName" : searchName
		};
	}, 10, "2", "1");

	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntAllocationItemAction!deleteById.action?id=' + id, '是否删除商品?', storeItemTable);
	};
	function bathItemToChannelDistributor() {
		if ($("#selectTreeType").val() != '' && $("#selectTreeType").val() != null && $("#selectTreeType").val() == 'CH') {
			updateEntityByConfirm('${nvix}/nvixnt/vixntAllocationItemAction!bathItemToChannelDistributor.action?parentId=' + $("#selectId").val(), "确定将所有商品都授权到门店吗?", storeItemTable);
		} else {
			layer.alert("请选择门店!");
		}
	};
	function uploadItemCatalogToStore() {
		if ($("#selectTreeType").val() != '' && $("#selectTreeType").val() != null && $("#selectTreeType").val() == 'CH') {
			updateEntityByConfirm('${nvix}/nvixnt/vixntAllocationItemAction!uploadItemCatalogToStore.action?parentId=' + $("#selectId").val(), "确定将所有分类都授权到门店吗?", storeItemTable);
		} else {
			layer.alert("请选择门店!");
		}
	};
	function goChooseItem() {
		if ($("#selectTreeType").val() != '' && $("#selectTreeType").val() != null && $("#selectTreeType").val() == 'CH') {
			chooseListData('${nvix}/nvixnt/vixntAllocationItemAction!goChooseItem.action?parentId=' + $("#selectId").val(), 'multi', '选择商品', null, function() {
				var emp = chooseMap.valueSetWithClear().split(":");
				if (emp != '' && emp.length == 2) {
					$.ajax({
					url : '${nvix}/nvixnt/vixntAllocationItemAction!itemToChannelDistributor.action?itemids=' + emp[0] + "&parentId=" + $("#selectId").val(),
					cache : false,
					success : function() {
						loadContent('drp_allocationitem', '${nvix}/nvixnt/vixntAllocationItemAction!goList.action');
					}
					});
				} else {
					layer.alert("请选择商品!");
				}
			}, 750, 550);
		} else {
			layer.alert("请选择门店!");
		}
	};
	function goChooseSupplier(id) {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier', function() {
			$.ajax({
			url : '${nvix}/nvixnt/vixntAllocationItemAction!bindSupplierToStoreItem.action?storeItemId=' + id + "&supplierId=" + $('#supplierId').val(),
			cache : false,
			success : function() {
				storeItemTable.ajax.reload();
			}
			});
		});
	};
	function bathBindItemToSupplier() {
		if ($("#selectTreeType").val() != '' && $("#selectTreeType").val() != null && $("#selectTreeType").val() == 'CH') {
			chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier', function() {
				$.ajax({
				url : '${nvix}/nvixnt/vixntAllocationItemAction!bathBindSupplierToStoreItem.action?channelDistributorId=' + $("#selectId").val() + "&supplierId=" + $('#supplierId').val(),
				cache : false,
				success : function() {
					storeItemTable.ajax.reload();
				}
				});
			});
		} else {
			layer.alert("请选择门店!");
		}
	};
	function bathBindItemToSupplierByItem() {
		if ($("#selectTreeType").val() != '' && $("#selectTreeType").val() != null && $("#selectTreeType").val() == 'CH') {
			chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier', function() {
				var emp = chooseMap.valueSetWithClear().split(":");
				if (emp != '' && emp.length == 2) {
					$.ajax({
					url : '${nvix}/nvixnt/vixntAllocationItemAction!bathBindSupplierToStoreItem.action?channelDistributorId=' + $("#selectId").val() + "&supplierId=" + $('#supplierId').val() + "&itemIds=" + emp[0],
					cache : false,
					success : function() {
						storeItemTable.ajax.reload();
					}
					});
				} else {
					layer.alert("请选择商品!");
				}
			});
		} else {
			layer.alert("请选择门店!");
		}
	};
	function goSupplierItemList() {
		$.ajax({
		url : '${nvix}/nvixnt/vixntAllocationItemAction!goSupplierItemList.action',
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function prepareStoreItem(id) {
		updateEntityByConfirm('${nvix}/nvixnt/vixntAllocationItemAction!prepareStoreItem.action?id=' + id, "确认同步所有商品到店铺吗!", storeItemTable);
	};
</script>