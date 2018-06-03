<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}"/>
<fieldset>
	<div class="col-sm-6" style="text-align: left; padding: 5px 5px;">
		<div id="salePlanDetailsDiv" class="jarviswidget jarviswidget-color-white">
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
							<div class="col-md-3">
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
								<th width="50%">名称</th>
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
	<div class="col-sm-6" style="text-align: left; padding: 0px 0px; height:700">
		<form id="salePlanDetailsForm" class="form-horizontal" style="padding:5px 5px;" action="${snow}/inventory/bill/stockInAction!saveOrUpdate.action">
			<div class="jarviswidget">
				<header>
					<h2>销售计划明细</h2>
				</header>
				<div>
					<div class="widget-body">
						<fieldset>
							<input type="hidden" id="salePlanDetailsId" name="salePlanDetails.id" value="${salePlanDetails.id}"/>
							<input type="hidden" id="salePlanDetailsSalePlanId" name="salePlanDetails.salePlan.id" value="${salePlanDetails.salePlan.id}"/>
							<input type="hidden" id="itemId" name="salePlanDetails.item.id" value="${salePlanDetails.item.id}"/>
							<div class="form-group">
								<label class="col-md-2 control-label">名称:</label>
								<div class="col-md-4">
									<input id="itemName" name="salePlanDetails.item.name" value="${salePlanDetails.item.name}" class="form-control" type="text"/>
								</div>
								<label class="col-md-2 control-label"><span class="text-danger">*</span>价格:</label>
								<div class="col-md-4">
									<div class="input-group">
										<span class="input-group-addon">￥</span>
										<input id="price" name="salePlanDetails.item.price" value="${salePlanDetails.item.price}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">01月:</label>
								<div class="col-md-4">
									<input id="jan" type="text" name="salePlanDetails.jan" value="${salePlanDetails.jan}"  class="form-control validate[required,custom[integer]]">
								</div>
								<label class="col-md-2 control-label">02月:</label>
								<div class="col-md-4">
									<input id="feb" type="text" name="salePlanDetails.feb" value="${salePlanDetails.feb}"  class="form-control validate[required,custom[integer]]">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">03月:</label>
								<div class="col-md-4">
									<input id="mar" type="text" name="salePlanDetails.mar" value="${salePlanDetails.mar}"  class="form-control validate[required,custom[integer]]">
								</div>
								<label class="col-md-2 control-label">04月:</label>
								<div class="col-md-4">
									<input id="apr" type="text" name="salePlanDetails.apr" value="${salePlanDetails.apr}"  class="form-control validate[required,custom[integer]]">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">05月:</label>
								<div class="col-md-4">
									<input id="may" type="text" name="salePlanDetails.may" value="${salePlanDetails.may}"  class="form-control validate[required,custom[integer]]">
								</div>
								<label class="col-md-2 control-label">06月:</label>
								<div class="col-md-4">
									<input id="jun" type="text" name="salePlanDetails.jun" value="${salePlanDetails.jun}"  class="form-control validate[required,custom[integer]]">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">07月:</label>
								<div class="col-md-4">
									<input id="jul" type="text" name="salePlanDetails.jul" value="${salePlanDetails.jul}"  class="form-control validate[required,custom[integer]]">
								</div>
								<label class="col-md-2 control-label">08月:</label>
								<div class="col-md-4">
									<input id="aug" type="text" name="salePlanDetails.aug" value="${salePlanDetails.aug}"  class="form-control validate[required,custom[integer]]">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">09月:</label>
								<div class="col-md-4">
									<input id="sep" type="text" name="salePlanDetails.sep" value="${salePlanDetails.sep}"  class="form-control validate[required,custom[integer]]">
								</div>
								<label class="col-md-2 control-label">10月:</label>
								<div class="col-md-4">
									<input id="oct" type="text" name="salePlanDetails.oct" value="${salePlanDetails.oct}"  class="form-control validate[required,custom[integer]]">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">11月:</label>
								<div class="col-md-4">
									<input id="nov" type="text" name="salePlanDetails.nov" value="${salePlanDetails.nov}"  class="form-control validate[required,custom[integer]]">
								</div>
								<label class="col-md-2 control-label">12月:</label>
								<div class="col-md-4">
									<input id="december" type="text" name="salePlanDetails.december" value="${salePlanDetails.december}"  class="form-control validate[required,custom[integer]]">
								</div>
							</div>
							<div class="form-group">
							</div>
							<div style="text-align: right;">
								<button type="button" class="btn btn-success" onclick="mergeSaleOrderItem();">
									<s:if test="salePlanDetails.id == '' || salePlanDetails.id == null">
										<i class="fa fa-edit"></i> &nbsp; 保存并新增
									</s:if>
									<s:else>
										<i class="fa fa-save"></i> &nbsp; 修改并新增
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
 	},8,'1','0');
           
	$('#chooseItem tbody').on( 'click', 'tr', function () {
		var data = chooseItemTable.row(".selected").data();
		if(data != undefined){
			$("#itemId").val(data.id);
      		$("#itemName").val(data.name);
      		$("#itemCode").val(data.code);
      		$("#specification").val(data.specification);
      		$("#price").val(data.price);
      		$("#tax").val(data.saleTax);
      		$("#unit").val(data.measureUnit.name);
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
		if($('#salePlanDetailsForm').validationEngine('validate')){
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			//入库单主体ID
			$("#salePlanDetailsSalePlanId").val($("#salePlanId").val());
			$("#salePlanDetailsForm").ajaxSubmit({
				type: "post",
				url:"${nvix}/nvixnt/nvixntSalePlanDetailsAction!saveOrUpdate.action",
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success : function(result) {
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					showMessage(result);
					salePlanDetailsTable.ajax.reload();
					clearSaleOrderItemForm();
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
<%-- <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="salePlanDetailsForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixntSalePlanDetailsAction!saveOrUpdate.action">
	<input id="salePlanDetailsReturnRuleId" name="salePlanDetails.salePlan.id" value="${salePlanDetails.salePlan.id}" type="hidden" />
	<input id="salePlanDetailsId" name="salePlanDetails.id" value="${salePlanDetails.id}" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">商品:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input type="hidden" id="itemId" name="salePlanDetails.item.id" value="${salePlanDetails.item.id}"/>
					<input id="itemName" value="${salePlanDetails.item.name}" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
					<span class="input-group-addon" style="cursor:pointer;" onclick="chooseItem();">选择</span>
				</div>
			</div>
			<label class="col-md-2 control-label">单价:</label>
			<div class="col-md-3">
				<input id="itemPrice" value="${salePlanDetails.item.price}" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">01月计划数量:</label>
			<div class="col-md-3">
				<input id="jan" type="text" name="salePlanDetails.jan" value="${salePlanDetails.jan}"  class="form-control validate[required,custom[integer]]">
			</div>
			<label class="col-md-2 control-label">02月计划数量:</label>
			<div class="col-md-3">
				<input id="feb" type="text" name="salePlanDetails.feb" value="${salePlanDetails.feb}"  class="form-control validate[required,custom[integer]]">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">03月计划数量:</label>
			<div class="col-md-3">
				<input id="mar" type="text" name="salePlanDetails.mar" value="${salePlanDetails.mar}"  class="form-control validate[required,custom[integer]]">
			</div>
			<label class="col-md-2 control-label">04月计划数量:</label>
			<div class="col-md-3">
				<input id="apr" type="text" name="salePlanDetails.apr" value="${salePlanDetails.apr}"  class="form-control validate[required,custom[integer]]">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">05月计划数量:</label>
			<div class="col-md-3">
				<input id="may" type="text" name="salePlanDetails.may" value="${salePlanDetails.may}"  class="form-control validate[required,custom[integer]]">
			</div>
			<label class="col-md-2 control-label">06月计划数量:</label>
			<div class="col-md-3">
				<input id="jun" type="text" name="salePlanDetails.jun" value="${salePlanDetails.jun}"  class="form-control validate[required,custom[integer]]">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">07月计划数量:</label>
			<div class="col-md-3">
				<input id="jul" type="text" name="salePlanDetails.jul" value="${salePlanDetails.jul}"  class="form-control validate[required,custom[integer]]">
			</div>
			<label class="col-md-2 control-label">08月计划数量:</label>
			<div class="col-md-3">
				<input id="aug" type="text" name="salePlanDetails.aug" value="${salePlanDetails.aug}"  class="form-control validate[required,custom[integer]]">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">09月计划数量:</label>
			<div class="col-md-3">
				<input id="sep" type="text" name="salePlanDetails.sep" value="${salePlanDetails.sep}"  class="form-control validate[required,custom[integer]]">
			</div>
			<label class="col-md-2 control-label">10月计划数量:</label>
			<div class="col-md-3">
				<input id="oct" type="text" name="salePlanDetails.oct" value="${salePlanDetails.oct}"  class="form-control validate[required,custom[integer]]">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">11月计划数量:</label>
			<div class="col-md-3">
				<input id="nov" type="text" name="salePlanDetails.nov" value="${salePlanDetails.nov}"  class="form-control validate[required,custom[integer]]">
			</div>
			<label class="col-md-2 control-label">12月计划数量:</label>
			<div class="col-md-3">
				<input id="december" type="text" name="salePlanDetails.december" value="${salePlanDetails.december}"  class="form-control validate[required,custom[integer]]">
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$(function(){
		if(!$("#salePlanDetailsReturnRuleId").val()){
			$("#salePlanDetailsReturnRuleId").val($("#salePlanId").val());
		}
	})
	function chooseItem(){
		chooseListData('${nvix}/nvixnt/vixntProductAssemblyAction!goChooseItem.action', 'single', '选择商品','item');
	}
	$("#salePlanDetailsForm").validationEngine();
</script> --%>