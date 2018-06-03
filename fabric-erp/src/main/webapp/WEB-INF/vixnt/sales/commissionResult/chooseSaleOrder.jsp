<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="selectId" value="${selectId}" />
<input type="hidden" id="treeType" value="${treeType}" />
<input type="hidden" id="startTime" value="${startTime}" />
<input type="hidden" id="endTime" value="${endTime}" />
<input type="hidden" id="chooseType" value="${chooseType}" />
<div class="jarviswidget jarviswidget-color-darken" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" style="padding: 6px;">
	<header style="height: 1px; border-color: #ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin: 5px;">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							<input type="text" value="" placeholder="客户名称" class="form-control col-md-3" id="chooseCustomerName" />
						</div>
						<button onclick="chooseCustomerTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#chooseCustomerName').val('');chooseCustomerTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
			</div>
			<table id="chooseCustomer" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="5%">选择</th>
						<th width="8%">条形码</th>
						<th width="8%">SKU</th>
						<th width="27%">${vv:varView('vix_mdm_item')}名称</th>
						<th width="8%">${vv:varView('vix_mdm_item')}编码</th>
						<th width="8%">规格型号</th>
						<th width="8%">单位</th>
						<th width="8%">数量</th>
						<th width="8%">金额</th>
						<th width="8%">销售员</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseType = $("#chooseType").val();
	chooseMap.setColumnName("name");
	var chooseCustomerColumns = [
		{"orderable":false,"data" : function(data) {
			return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "');\"/>";
		}},
		{"name":"name","data" : function(data) {return data.item == null ? '' : data.item.barCode;}},
		{"name":"name","data" : function(data) {return data.item == null ? '' : data.item.skuCode;}},
		{"name":"name","data" : function(data) {return data.item == null ? '' : data.item.name;}},
		{"name":"name","data" : function(data) {return data.item == null ? '' : data.item.code;}},
		{"name":"name","data" : function(data) {return data.specification;}},
		{"name":"name","data" : function(data) {return data.unit == null ? '' : data.unit;}},
		{"name":"name","data" : function(data) {return data.amount;}},
		{"name":"name","data" : function(data) {return data.price == null ? "￥ 0" : ("￥" + data.price);}},
		{"name":"code","data" : function(data) {return data.salesOrder == null ? "" : data.salesOrder.salePerson.name;}}
	];
	
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var chooseCustomerTable = initDataTable("chooseCustomer","${nvix}/nvixnt/nvixntCommissionResultAction!chooseSaleOrderItem.action",chooseCustomerColumns,function(page,pageSize,orderField,orderBy){
	 	var treeType = $("#treeType").val();
	 	var selectId = $("#selectId").val();
	 	var name = $("#chooseCustomerName").val();
	 	var startTime = $("#startTime").val();
	 	var endTime = $("#endTime").val();
	 	name=Url.encode(name);
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"selectId":selectId,"treeType":treeType,"startTime":startTime,"endTime":endTime};
	}, 10, selectType, isGenerateIndex);
	
	/** 页面加载完调用 */
	pageSetUp();
</script>