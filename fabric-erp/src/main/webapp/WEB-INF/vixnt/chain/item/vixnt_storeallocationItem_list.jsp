<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 门店管理<span>> 业务管理 </span><span>> 商品管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('')">
					<i class="fa fa-plus"></i>&nbsp;新增商品
				</button>
				<button class="btn btn-primary" type="button" onclick="goChooseItem();">
					<i class="fa fa-search"></i>&nbsp;选取商品
				</button>
				<button class="btn btn-primary" type="button" onclick="bathItemToChannelDistributor();">
					<i class="fa fa-search"></i>&nbsp;批量选取商品
				</button>
				<button class="btn btn-primary" type="button" onclick="deleteByIds();">
					<i class="fa fa-trash-o"></i>&nbsp;批量删除
				</button>
				<button class="btn btn-primary" type="button" onclick="prepareStoreItemByChoose();">
					<i class="fa fa-copy"></i>&nbsp;同步商品
				</button>
				<button class="btn btn-primary" type="button" onclick="$('#fileToUpload').click()">
					<i class="glyphicon glyphicon-import"></i>&nbsp;导入
				</button>
			</div>
		</div>
	</div>
	<section>
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>${vv:varView("vix_mdm_item")}分类</h2>
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
							itemsTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>商品列表</h2>
						<ul class="nav nav-tabs pull-right in">
							<li class="active"><a data-toggle="tab" href="#hr1"><span class="hidden-mobile hidden-tablet">已同步</span></a></li>
							<li><a data-toggle="tab" href="#hr2"><span class="hidden-mobile hidden-tablet">未同步</span></a></li>
						</ul>
					</header>
					<div class="tab-content">
						<div class="tab-pane active" id="hr1">
							<div class="widget-body no-padding">
								<div>
									<form role="search" class="navbar-form navbar-left">
										<div class="form-group">
											商品名称: <input type="text" class="form-control" id="selectname">
										</div>
										<button onclick="storeItemTable.ajax.reload();" type="button" class="btn btn-info">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#selectname').val('');storeItemTable.ajax.reload();" type="button" class="btn btn-default">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
								<table id="storeItemTableId" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th>编号</th>
											<th>编码</th>
											<th>名称</th>
											<th>规格</th>
											<th>sku编码</th>
											<th>销售价格</th>
											<th>会员价格</th>
											<th>操作</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<div class="tab-pane" id="hr2">
							<div class="widget-body no-padding">
								<div>
									<form role="search" class="navbar-form navbar-left">
										<div class="form-group">
											商品名称: <input type="text" value="" class="form-control" id="searchName">
										</div>
										<button onclick="noStoreItemTable.ajax.reload();" type="button" class="btn btn-info">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchName').val('');noStoreItemTable.ajax.reload();" type="button" class="btn btn-default">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
								<table id="noStoreItemTableId" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th>
												<div class="btn-group">
													<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default">
														选择 <i class="fa fa-caret-down"></i>
													</button>
													<ul class="dropdown-menu js-status-update pull-left">
														<li><a id="all" onclick="changeDataTableSelect('noStoreItemTableId','all');" href="javascript:void(0);">全选</a></li>
														<li><a id="reverse" onclick="changeDataTableSelect('noStoreItemTableId','reverse');" href="javascript:void(0);">反选</a></li>
														<li><a id="cancle" onclick="changeDataTableSelect('noStoreItemTableId','cancle');" href="javascript:void(0);">取消</a></li>
													</ul>
												</div>
											</th>
											<th>编码</th>
											<th>名称</th>
											<th>规格</th>
											<th>sku编码</th>
											<th>销售价格</th>
											<th>会员价格</th>
											<th>操作</th>
										</tr>
									</thead>
								</table>
							</div>
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
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "";
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
	"width" : "10%",
	"name" : "specification",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"width" : "10%",
	"name" : "skuCode",
	"data" : function(data) {
		return data.skuCode;
	}
	}, {
	"width" : "10%",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"width" : "10%",
	"name" : "vipPrice",
	"data" : function(data) {
		return data.vipPrice;
	}
	}, {
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var updatePrice = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goUpdatePrice('" + data.id + "');\" title='改价'><span class='txt-color-blue pull-right'><i class='fa fa-edit'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + updatePrice + "&nbsp;&nbsp;" + del;
	}
	} ];

	var storeItemTable = initDataTable("storeItemTableId", "${nvix}/nvixnt/vixntAllocationItemAction!goStoreItemSingleList.action", storeItemColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#selectname").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchName
		};
	}, 10, '0');

	var noStoreItemColumns = [ {
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "";
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
	"width" : "10%",
	"name" : "specification",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"width" : "10%",
	"name" : "skuCode",
	"data" : function(data) {
		return data.skuCode;
	}
	}, {
	"width" : "10%",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"width" : "10%",
	"name" : "vipPrice",
	"data" : function(data) {
		return data.vipPrice;
	}
	}, {
	"width" : "15%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var updatePrice = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goUpdatePrice('" + data.id + "');\" title='改价'><span class='txt-color-blue pull-right'><i class='fa fa-edit'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + updatePrice + "&nbsp;&nbsp;" + del;
	}
	} ];

	var noStoreItemTable = initDataTable("noStoreItemTableId", "${nvix}/nvixnt/vixntAllocationItemAction!goNoStoreItemSingleList.action", noStoreItemColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchName
		};
	}, 10, '0');
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntAllocationItemAction!deleteById.action?id=' + id, '是否删除商品?', storeItemTable);
	};
	function goUpdatePrice(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntAllocationItemAction!goUpdatePrice.action?id=' + id, "priceForm", "修改价格", 750, 245, storeItemTable);
	};
	function bathItemToChannelDistributor() {
		updateEntityByConfirm('${nvix}/nvixnt/vixntAllocationItemAction!bathShopItemToChannelDistributor.action', "确定将所有商品都授权到门店吗?", storeItemTable);
	};
	function deleteByIds() {
		updateEntityByConfirm('${nvix}/nvixnt/vixntAllocationItemAction!deleteByIds.action', "确定将所有商品都删除吗?", storeItemTable);
	};
	function goChooseItem() {
		chooseListData('${nvix}/nvixnt/vixntAllocationItemAction!goChooseItem.action', 'multi', '选择商品', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$.ajax({
				url : '${nvix}/nvixnt/vixntAllocationItemAction!shopItemToChannelDistributor.action?itemids=' + emp[0],
				cache : false,
				success : function() {
					loadContent('drp_allocationitem', '${nvix}/nvixnt/vixntAllocationItemAction!goStoreItemList.action');
				}
				});
			} else {
				layer.alert("请选择商品!");
			}
		}, 750, 550);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntAllocationItemAction!goSaveOrUpdate.action?id=' + id + "&parentId=" + $('#selectId').val(),
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function prepareStoreItem() {
		updateEntityByConfirm('${nvix}/nvixnt/vixntAllocationItemAction!prepareStoreItem.action', "确认同步所有商品到店铺吗!", storeItemTable);
	};
	function importXlsFile() {
		$.ajaxFileUpload({
		url : '${nvix}/nvixnt/vixntAllocationItemAction!importFile.action',// 用于文件上传的服务器端请求地址
		secureuri : true,// 是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',
		dataType : 'text',// 返回值类型 ,可以使xml、text、json、html
		success : function(data, status) {// 服务器成功响应处理函数
			storeItemTable.ajax.reload();
		},
		error : function(data, status, e) {// 服务器响应失败处理函数
			asyncbox.success("上传错误!", "提示");
		}
		});
	};
	function prepareStoreItemByChoose() {
		var emp = chooseMap.valueSetWithClear().split(":");
		if (emp != '' && emp.length == 2) {
			$.ajax({
			url : '${nvix}/nvixnt/vixntAllocationItemAction!prepareStoreItem.action?itemids=' + emp[0],
			cache : false,
			success : function() {
				loadContent('drp_allocationitem', '${nvix}/nvixnt/vixntAllocationItemAction!goStoreItemList.action');
			}
			});
		} else {
			layer.alert("请选择商品!");
		}
	};
	pageSetUp();
</script>