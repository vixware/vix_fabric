<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 会员营销 <span>>优惠券管理 </span> <span>>优惠券发放</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="prepareStoreItemByChoose();">
					<i class="fa fa-plus"></i>&nbsp;批量发放
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>门店</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" /> <input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${channelDistributorId }" />
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
							itemsTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>会员列表</h2>
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
									<button onclick="itemsTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#iCode').val('');$('#iName').val('');itemsTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="itemTable" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="10%">
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default">
													选择 <i class="fa fa-caret-down"></i>
												</button>
												<ul class="dropdown-menu js-status-update pull-left">
													<li><a id="all" onclick="changeDataTableSelect('itemTable','all');" href="javascript:void(0);">全选</a></li>
													<li><a id="reverse" onclick="changeDataTableSelect('itemTable','reverse');" href="javascript:void(0);">反选</a></li>
													<li><a id="cancle" onclick="changeDataTableSelect('itemTable','cancle');" href="javascript:void(0);">取消</a></li>
												</ul>
											</div>
										</th>
										<th>编码</th>
										<th>名称</th>
										<th>规格</th>
										<th>价格</th>
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
	var itemsColumns = [ {
	"orderable" : false,
	"width" : "5%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"width" : "20%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"width" : "20%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"width" : "15%",
	"data" : function(data) {
		return data.specification == null ? '' : data.specification;
	}
	}, {
	"width" : "15%",
	"data" : function(data) {
		return data.price == null || data.price == '' ? '' : data.price;
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"updateStatus('" + data.id + "');\" title='分配'><span class='txt-color-blue pull-right'><i class='fa fa-tags'></i></span></a>";
			return update;
		}
		return '';
	}
	} ];

	var itemsTable = initDataTable("itemTable", "${nvix}/nvixnt/nvixCustomerAccountAction!goSingleList.action", itemsColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		};
	}, 10, "2", "1");

	function saveOrUpdate(id) {
		var categoryId = $("#selectId").val();
		goSaveOrUpdateEntity('${nvix}/nvixnt/mdm/nvixntItemAction!goSaveOrUpdate.action?id=' + id + '&categoryId=' + categoryId);
	}
	function updateStatus(id) {
		var channelDistributorId = $("#channelDistributorId").val();
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntAllocationItemAction!goSaveOrUpdatePrice.action?itemids=' + id + '&channelDistributorId=' + channelDistributorId, "itemForm", "发放优惠券", 750, 235, itemsTable);
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixntItemAction!deleteById.action?id=' + id, '是否删除该商品?', itemsTable);
	};
	function prepareStoreItemByChoose() {
		var channelDistributorId = $("#channelDistributorId").val();
		var emp = chooseMap.valueSetWithClear().split(":");
		if (emp != '' && emp.length == 2) {
			openSaveOrUpdateWindow('${nvix}/nvixnt/vixntDispenseCouponAction!goSaveOrUpdateBathCoupon.action?itemids=' + emp[0] + '&channelDistributorId=' + channelDistributorId, "itemForm", "发放优惠券", 750, 435, itemsTable);
		} else {
			layer.alert("请选择优惠券!");
		}
	};
</script>