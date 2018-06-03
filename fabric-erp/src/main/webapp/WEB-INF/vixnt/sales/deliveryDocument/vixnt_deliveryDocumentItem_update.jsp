<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}"/>
<fieldset>
	<div class="col-sm-8" style="text-align: left; padding: 5px 5px;">
		<div id="deliveryDocumentItemDiv" class="jarviswidget jarviswidget-color-white">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i> </span>
				<h2>列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin:5px;">
						<div class="form-group" style="margin: 0 0px;">
							<div class="col-md-4">
								<div id="treeMenu" class="input-group col-md-12">
									<input type="hidden" id="chooseItemCategoryId" name="itemCategoryId" value="${itemCategoryId}"/>
									<input id="chooseItemCategoryName" type="text" onfocus="showMenu(); return false;" value="${itemCategoryName}" class="form-control" placeholder="分类" />
									<div class="input-group-btn">
										<button type="button" class="btn btn-default" onclick="$('#chooseItemCategoryId').val('');$('#chooseItemCategoryName').val('');chooseItemTable.ajax.reload();">
											清空
										</button>
									</div>
									<div id="menuContent" class="menuContent" style="display:none; position: absolute;border:1px solid gray;background-color: #dfdfdf;z-index: 9999;">
										<ul id="chooseItemCategoryZtree" class="ztree"></ul>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<input type="text" value="" placeholder="商品名称" class="form-control" id="searchItemName">
							</div>
							<button onclick="chooseItemTable.ajax.reload();" type="button" class="btn btn-info" >
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchItemName').val('');$('#chooseItemCategoryId').val('');$('#chooseItemCategoryName').val('');chooseItemTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</div>
					</div>
				    <table id="chooseItem" class="table table-striped table-bordered table-hover" width="100%">
				   		<thead>
							<tr>
								<th width="25%">编码</th>
								<th width="25%">名称</th>
								<th width="20%">价格</th>
								<th width="15%">规格型号</th>
								<th width="15%">计量单位</th>
							</tr>
						</thead>
				    </table>
			 	</div>
			</div>
		</div>
	</div>
	<div class="col-sm-4" style="text-align: left; padding: 0px 0px;">
		<form id="deliveryDocumentItemForm" class="form-horizontal" style="padding:5px 5px;" action="">
			<div class="jarviswidget">
				<header>
					<h2>明细</h2>
				</header>
				<div>
					<div class="widget-body">
						<fieldset>
							<input type="hidden" id="oiId" name="deliveryDocumentItem.id" value="${deliveryDocumentItem.id}"/>
							<input type="hidden" id="id" name="deliveryDocumentItem.deliveryDocument.id" value="${deliveryDocumentItem.deliveryDocument.id}"/>
							<input type="hidden" id="itemId" name="deliveryDocumentItem.item.id" value="${deliveryDocumentItem.item.id}"/>
							<input type="hidden" id="tax" name="deliveryDocumentItem.tax" value="${deliveryDocumentItem.tax}"/>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>编码:</label>
								<div class="col-md-8">
									<input id="itemCode" name="deliveryDocumentItem.item.code" value="${deliveryDocumentItem.item.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">名称:</label>
								<div class="col-md-8">
									<input id="itemName" name="deliveryDocumentItem.item.name" value="${deliveryDocumentItem.item.name}" class="form-control" type="text"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>数量:</label>
								<div class="col-md-8">
									<s:if test="deliveryDocumentItem != null && deliveryDocumentItem.count != null">
										<input id="soiAmount" name="deliveryDocumentItem.count" value="${deliveryDocumentItem.count}" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
									</s:if>
									<s:else>
										<input id="soiAmount" name="deliveryDocumentItem.count" value="1" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
									</s:else>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">规格:</label>
								<div class="col-md-8">
									<input id="specification" name="deliveryDocumentItem.specification" value="${deliveryDocumentItem.specification}" class="form-control" readonly="readonly" type="text"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>价格:</label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon">￥</span>
										<input id="price" name="deliveryDocumentItem.price" value="${deliveryDocumentItem.price}"  data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text"/>
									</div>
								</div>
							</div>
							<div style="text-align: right;">
								<button type="button" class="btn btn-success" onclick="mergeSaleOrderItem();">
									<s:if test="deliveryDocumentItem.id == ''||deliveryDocumentItem.id == null">
										<i class="fa fa-save"></i> &nbsp; 保存并新增
									</s:if>
									<s:else>
										<i class="fa fa-edit"></i> &nbsp; 修改并新增
									</s:else>
								</button>
							</div>
						</fieldset>
					</div>
				</div>
			</div>	
		</form>
	</div>
</fieldset>
<script type="text/javascript" >
	var chooseItemColumns = [
	   		{"name":"code","data" : function(data) {return data.code;}},
	   		{"name":"name","data" : function(data) {return data.name;}},
	   		{"name":"price","data" : function(data) {return '￥' + data.price;}},
	   		{"name":"specification","data" : function(data) {return data.specification;}},
	   		{"name":"measureUnit","data" : function(data) {return data.measureUnit == null ? '' : data.measureUnit.name;}}
		];

	var chooseItemTable = initDataTable("chooseItem","${nvix}/mdm/item/saleItemPriceAction!getListJson.action",chooseItemColumns,function(page,pageSize,orderField,orderBy){
 		var name = $("#searchItemName").val();
 	 	name=Url.encode(name);
 	 	var categoryId = $("#chooseItemCategoryId").val();
 		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"name":name,"categoryId":categoryId};
 	},10,'1','0');
           
	$('#chooseItem tbody').on( 'click', 'tr', function () {
		var data = chooseItemTable.row(".selected").data();
		if(data != undefined){
			$("#itemId").val(data.id);
      		$("#itemName").val(data.name);
      		$("#itemCode").val(data.code);
      		$("#specification").val(data.specification);
      		$("#price").val(data.price);
      		$("#tax").val(data.saleTax);
		}else{
			clearSaleOrderItemForm();
     	}
	});
           
    /** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu","${nvix}/mdm/item/itemCatalogAction!findTreeToJson.action","chooseItemCategoryId","chooseItemCategoryName","chooseItemCategoryZtree","menuContent",null,function(){
		chooseItemTable.ajax.reload();
	});
	
	/* 保存订单明细*/
	function mergeSaleOrderItem(){
		if($('#deliveryDocumentItemForm').validationEngine('validate')){
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			//入库单主体ID
			$("#id").val($("#deliveryDocumentId").val());
			$("#deliveryDocumentItemForm").ajaxSubmit({
				type: "post",
				url:"${nvix}/nvixnt/nvixntDeliveryDocumentAction!saveOrUpdateDeliveryDocumentItem.action",
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success : function(result) {
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					var r = result.split(":");
					showMessage(r[1]);
					if($.trim(r[0]) == '0'){
						//清空输入项
						clearSaleOrderItemForm();
						deliveryDocumentItemTable.ajax.reload();
						$.ajax({
							url : '${nvix}/nvixnt/nvixntDeliveryDocumentAction!calculateAmount.action?id=' + $("#deliveryDocumentId").val(),
							cache : false,
							success : function(json) {
								$("#amount").val(json);
							}
						});
					}
				}
			});
		}
	}

	/** 清空输入项 */
	function clearSaleOrderItemForm(){
		$("#oiId").val("");
		$("#itemCode").val("");
		$("#itemId").val("");
		$("#itemName").val("");
		$("#soiAmount").val("");
		$("#specification").val("");
		$("#price").val("");
		$("#btnHtml").html("保存");
	}
	// 定价
	function goFixedPrice() {
		var itemId = $("#itemId").val();
		var amount = $("#soiAmount").val();
		var customerId = $("#customerId").val();
		if(!itemId){
			layer.alert("请选择商品");
			return;
		}else if(!amount){
			layer.alert("请填写商品数量");
			return;
		}else{
			chooseListData('${nvix}/nvixnt/nvixntSalesItemPriceAction!goFixedPrice.action?id='+itemId+'&count='+amount+"&customerId="+customerId, 'single', '选择定价', 'fixedPrice');
		}
	};
</script>