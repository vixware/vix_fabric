<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}" />
<div class="jarviswidget" style="padding: 6px;">
	<header style="height: 1px; border-color: #ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin: 5px;">
				<div class="form-group" style="margin: 0 5px;">
					<div class="col-md-4">
						<input type="text" value="" placeholder="名称" class="form-control" id="purchaseName">
					</div>
					<button onclick="choosePurchasePlanTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button type="button" class="btn btn-default" onclick="$('#purchaseName').val('');choosePurchasePlanTable.ajax.reload();">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</div>
			</div>
			<table id="choosePurchasePlanTableId" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="10%"><s:if test="chooseType == 'multi'">
								<div class="btn-group">
									<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default" aria-expanded="false">
										选择 <i class="fa fa-caret-down"></i>
									</button>
									<ul class="dropdown-menu js-status-update pull-left">
										<li><a id="all" onclick="changeDataTableSelect('choosePurchasePlanTableId','all');" href="javascript:void(0);">全选</a></li>
										<li><a id="reverse" onclick="changeDataTableSelect('choosePurchasePlanTableId','reverse');" href="javascript:void(0);">反选</a></li>
										<li><a id="cancle" onclick="changeDataTableSelect('choosePurchasePlanTableId','cancle');" href="javascript:void(0);">取消</a></li>
									</ul>
								</div>
							</s:if> <s:else>选择</s:else></th>
						<th width="35%">编码</th>
						<th width="45%">名称</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseType = $("#chooseType").val();
	chooseMap.setColumnName("name");

	var choosePurchasePlanColumns = [ {
		"data" : function(data) {
			if (chooseType == 'multi') {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='checkBox' onchange=\"checkBoxChange('${entityName}',this,'" + data.id + "','" + data.name + "');\"/>";
			}
			if (chooseType == 'single') {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "');\"/>";
			}
			return "";
		}
	}, {
		"data" : function(data) {
			return data.purchasePlanCode;
		}
	}, {
		"data" : function(data) {
			return data.purchasePlanName;
		}
	} ];
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var choosePurchasePlanTable = initDataTable("choosePurchasePlanTableId", "${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goPurchasePlanSingleList.action", choosePurchasePlanColumns, function(page, pageSize, orderField, orderBy) {
		var purchaseName = $("#purchaseName").val();
		purchaseName = Url.encode(purchaseName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"purchaseName" : purchaseName
		};
	}, 6, selectType, isGenerateIndex);
</script>