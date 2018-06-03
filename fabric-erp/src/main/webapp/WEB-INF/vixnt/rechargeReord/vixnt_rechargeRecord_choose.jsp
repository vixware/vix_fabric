<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}" />
<input type="hidden" id="cardType" name="customerAccountClip.card.type" value="${customerAccountClip.card.type}" />
<div class="jarviswidget" style="padding: 6px;">
	<header style="height: 1px; border-color: #ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin: 5px;">
				<div class="form-group" style="margin: 0 5px;">
					<div class="col-md-4">
						<input type="text" value="" placeholder="名称" class="form-control" id="selectName">
					</div>
					<button onclick="chooseWarehouseTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button type="button" class="btn btn-default" onclick="$('#"selectName"').val('');chooseWarehouseTable.ajax.reload();">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</div>
			</div>
			<table id="chooseCustomerTableId" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="10%"><s:if test="chooseType == 'multi'">
								<div class="btn-group">
									<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default" aria-expanded="false">
										选择 <i class="fa fa-caret-down"></i>
									</button>
									<ul class="dropdown-menu js-status-update pull-left">
										<li><a id="all" onclick="changeDataTableSelect('chooseCustomerTableId','all');" href="javascript:void(0);">全选</a></li>
										<li><a id="reverse" onclick="changeDataTableSelect('chooseCustomerTableId','reverse');" href="javascript:void(0);">反选</a></li>
										<li><a id="cancle" onclick="changeDataTableSelect('chooseCustomerTableId','cancle');" href="javascript:void(0);">取消</a></li>
									</ul>
								</div>
							</s:if> <s:else>选择</s:else></th>
						<th width="15%">名称</th>
						<th width="15%">充值金额</th>
						<th width="15%">赠送金额</th>
						<th width="15%">赠送积分</th>
						<th width="15%">赠送次数</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseType = $("#chooseType").val();
	chooseMap.setColumnName("name");

	var chooseCustomerColumns = [ {
		"data" : function(data) {
			if (chooseType == 'multi') {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='checkBox' onchange=\"checkBoxChange('${entityName}',this,'" + data.id + "','" + data.name + "','" + data.amount + "','" + data.ifAuthorize + "');\"/>";
			}
			if (chooseType == 'single') {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "','" + data.amount + "','" + data.ifAuthorize + "');\"/>";
			}
			return "";
		}
	}, {
		"data" : function(data) {
			return data.name;
		}
	} , {
		"data" : function(data) {
			return data.amount;
		}
	}, {
		"data" : function(data) {
			return data.giftAmount;
		}
	}  , {
		"data" : function(data) {
			return data.giftPoints;
		}
	}, {
		"data" : function(data) {
			return data.giftNumber;
		}
	}   ];
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var chooseWarehouseTable = initDataTable("chooseCustomerTableId", "${nvix}/nvixnt/storedValueRuleSetAction!goSingleList.action", chooseCustomerColumns, function(page, pageSize, orderField, orderBy) {
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		var cardType = $("#cardType").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"cardType" : cardType,
		"selectName" : selectName
		};
	}, 6, selectType, isGenerateIndex);
	function radioChange(entityName,id,name,amount,ifAuthorize){
		parent.$('#' + entityName + 'Id').val(id);
		parent.$('#' + entityName + 'Name').val(name);
		parent.$('#' + entityName + 'Amount').val(amount);
		parent.$('#' + entityName + 'ifAuthorize').val(ifAuthorize);
	}
</script>