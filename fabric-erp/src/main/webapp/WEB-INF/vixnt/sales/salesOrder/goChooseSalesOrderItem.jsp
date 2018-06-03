<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}"/>
<fieldset>
	<div class="col-sm-12" style="text-align: left; padding:10px 5px 0px 15px;">
		<div id="saleOrderItemDiv" class="jarviswidget jarviswidget-color-white">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i> </span>
				<h2>列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin:5px;">
						<div class="form-group" style="margin: 0 0px;">
							<div class="col-md-5">
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
								<th width="10%">选择</th>
								<th width="20%">编码</th>
								<th width="20%">名称</th>
								<th width="20%">价格</th>
								<th width="15%">规格型号</th>
								<th width="15%">数量</th>
							</tr>
						</thead>
				    </table>
			 	</div>
			</div>
		</div>
	</div>
</fieldset>
<script type="text/javascript" >
	var chooseItemColumns = [
			{"orderable":false,"data" : function(data) {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.item.name + "');\"/>";
			}},
	   		{"name":"item","data" : function(data) {return data.item == null ? '' : data.item.code;}},
	   		{"name":"item","data" : function(data) {return data.item == null ? '' : data.item.name;}},
	   		{"name":"item","data" : function(data) {return data.item == null ? '' : '￥ '+ data.item.price;}},
	   		{"name":"specification","data" : function(data) {return data.specification;}},
	   		{"name":"amount","data" : function(data) {return data.amount;}}
		];

	var chooseItemTable = initDataTable("chooseItem","${nvix}/nvixnt/nvixSalesOrderAction!getSaleOrderItemJson.action",chooseItemColumns,function(page,pageSize,orderField,orderBy){
 		var name = $("#searchItemName").val();
 	 	name=Url.encode(name);
 	 	var categoryId = $("#chooseItemCategoryId").val();
 	 	var id = $("#id").val();
 		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"name":name,"categoryId":categoryId,"id":id};
 	},10,'1','0');
           
	$('#chooseItem tbody').on( 'click', 'tr', function () {
		var data = chooseItemTable.row(".selected").data();
		if(data != undefined){
			$("#saleOrderItemId").val(data.id);
      		$("#saleOrderItemName").val(data.item.name);
      		$("#saleOrderItemCount").val(data.amount);
		}
	});
           
    /** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu","${nvix}/mdm/item/itemCatalogAction!findTreeToJson.action","chooseItemCategoryId","chooseItemCategoryName","chooseItemCategoryZtree","menuContent",null,function(){
		chooseItemTable.ajax.reload();
	});

</script>