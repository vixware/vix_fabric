<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}" />
<input type="hidden" id="itemId" value="${item.id}" />
<input type="hidden" id="count" value="${count}" />
<input type="hidden" id="customerId" value="${customerId}" />
<div class="jarviswidget" style="padding: 6px;">
	<header style="height: 1px; border-color: #ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<table id="chooseFixedPriceId" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="10%">选择</th>
						<th width="60%">商品名称</th>
						<th width="10%">优惠类型</th>
						<th width="10%">原始价格</th>
						<th width="10%">优惠价格</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseType = $("#chooseType").val();
	var chooseFixedPriceColumns = [ {
		"data" : function(data) {
			return "<input id='entityId' name='entityId' value='" + data.disCountPrice + "' type='radio' onchange=\"selectPrice('" + data.disCountPrice + "');\"/>";
		}
	}, {
		"data" : function(data) {
			return data.name;
		}
	}, {
		"data" : function(data) {
			return data.discountType == null ? "<span class='label label-info'>无优惠</span>" : "<span class='label label-info'>"+data.discountType+"</span>";
		}
	}, {
		"data" : function(data) {
			return data.price;
		}
	}, {
		"data" : function(data) {
			return data.disCountPrice;
		}
	}];
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var chooseSupplierTable = initDataTableNoPaging("chooseFixedPriceId", "${nvix}/nvixnt/nvixntSalesItemPriceAction!fixedPrice.action", chooseFixedPriceColumns, function() {
		var itemId = $("#itemId").val();
		var count = Number($("#count").val());
		var customerId = $("#customerId").val();
		return {
			"id" : itemId,
			"count" : count,
			"customerId" : customerId
		};
	}, selectType, isGenerateIndex);
	
	function selectPrice(price){
		$("#price").val(price);
	}
</script>