<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" /> <input type="hidden" id="itemIds" name="itemIds" /> <input type="hidden" id="itemNames" name="itemNames" />
	<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 销售管理<span>>佣金结算</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="var index = layer.open({title:'选择条件',type: 1, area: ['700px', '300px'], content: $('#advanceSearch')});">
					<i class="glyphicon glyphicon-plus"></i>&nbsp; 新增
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<div id="advanceSearch" style="padding:20px 15px;display:none;">
				<form id="advanceSearchForm" class="form-horizontal">
					<fieldset>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
							<div class="col-md-4">
								<div class="input-group">
									<input id="startTime" name="startTime" value="" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startTime'});"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
							<div class="col-md-4">
								<div class="input-group">
									<input id="endTime" name="endTime" value="" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endTime'});"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group" style="text-align:center;">
							<button onclick="advanceSearch();" type="button" class="btn btn-primary">
								<i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;确定
							</button>
							<button onclick="clearSearchCondition('searchCustomerName,searchTitle,code,salePerson,bizTypeId,orderTypeId,createTime',salesOrderTable);"
								type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> &nbsp;&nbsp;清空
							</button>
						</div>
					</fieldset>
				</form>
			</div>
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>组织架构</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/nvixntCommissionResultAction!findTreeToJson.action",
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
						<h2>佣金结算列表</h2>
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
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var itemsColumns = [ {
	"orderable" : false,
	"title":"编号",
	"width" : "5%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title":"编码",
	"width" : "15%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title":"主题",
	"width" : "15%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title":"销售人员",
	"width" : "10%",
	"data" : function(data) {
		return data.specifications;
	}
	}, {
	"title":"佣金金额",
	"width" : "10%",
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"title":"年度",
	"width" : "10%",
	"data" : function(data) {
		return data.salesManName;
	}
	}, {
		"title":"状态",
	"width" : "5%",
	"data" : function(data) {
		 if (data.status == '0') {
			return "<span class='label label-info'>已结算</span>";
		} else if (data.status == '1') {
			return "<span class='label label-primary'>未结算</span>";
		} 
		return "";
	}
	}, {
	"orderable" : false,
	"title":"操作",
	"width" : "10%",
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var itemsTable = initDataTable("itemTable", "${nvix}/nvixnt/nvixntCommissionResultAction!goSingleList.action", itemsColumns, function(page, pageSize, orderField, orderBy) {
		var itemCode = $("#iCode").val();
		itemCode = Url.encode(itemCode);
		var itemName = $("#iName").val();
		itemName = Url.encode(itemName);
		var treeId = $("#selectId").val();
		treeId = Url.encode(treeId);
		var treeType = $("#selectTreeType").val();
		treeType = Url.encode(treeType);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"itemName" : itemName,
		"itemCode" : itemCode,
		"parentId" : treeId,
		"treeType" : treeType
		};
	}, 10, "2", "1");
	function saveOrUpdate(id) {
			goSaveOrUpdateEntity('${nvix}/nvixnt/nvixntSalePlanAction!goSaveOrUpdate.action?id=' + id+"&parentId="+$("#selectId").val());
	}
	function show(id) {
			goSaveOrUpdateEntity('${nvix}/nvixnt/nvixntSalePlanAction!show.action?id=' + id);
	}
	function goSaveOrUpdateService(id) {
		var categoryId = $("#selectId").val();
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixntSalePlanAction!goSaveOrUpdateService.action?id=' + id + '&categoryId=' + categoryId);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntSalePlanAction!deleteById.action?id=' + id, '是否删除该商品?', itemsTable);
	};
	function advanceSearch(){
		$("#advanceSearchForm").validationEngine();
		chooseListData("${nvix}/nvixnt/nvixntCommissionResultAction!goChooseSalesOrderItem.action?selectId="+$("#selectId").val()+"&treeType="+$("#selectTreeType").val()+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val(),'multi',"选择订单",null,function(){
			var saleOrderItemIds = chooseMap.valueIdSet();
			if(saleOrderItemIds){
				$.ajax({
					url : '${nvix}/nvixnt/nvixntCommissionResultAction!calculateCommissionResult.action?ids=' + saleOrderItemIds+"&selectId="+$("#selectId").val()+"&treeType="+$("#selectTreeType").val(),
					cache : false,
					success : function(data) {
						var d  = data.split(":");
						if(d[0] == "1"){
							showMessage(d[1],'success');
							$.ajax({
								url : '${nvix}/nvixnt/nvixntCommissionResultAction!goSaveOrUpdate.action?id=' + d[2],
								cache : false,
								success : function(html) {
									$("#mainContent").html(html);
								}
								});
						}else{
							showMessage(d[1]);
						}
					}
					});
			}
		},860,700);
		layer.closeAll('page');
	}
</script>