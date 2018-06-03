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
						<input type="text" value="" placeholder="名称" class="form-control" id="customerName">
					</div>
					<button onclick="chooseCustomerAccountClipTypeTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button type="button" class="btn btn-default" onclick="$('#customerName').val('');chooseCustomerAccountClipTypeTable.ajax.reload();">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</div>
			</div>
			<table id="chooseCustomerAccountClipTypeTableId" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="10%"><s:if test="chooseType == 'multi'">
								<div class="btn-group">
									<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default" aria-expanded="false">
										选择 <i class="fa fa-caret-down"></i>
									</button>
									<ul class="dropdown-menu js-status-update pull-left">
										<li><a id="all" onclick="changeDataTableSelect('chooseCustomerAccountClipTypeTableId','all');" href="javascript:void(0);">全选</a></li>
										<li><a id="reverse" onclick="changeDataTableSelect('chooseCustomerAccountClipTypeTableId','reverse');" href="javascript:void(0);">反选</a></li>
										<li><a id="cancle" onclick="changeDataTableSelect('chooseCustomerAccountClipTypeTableId','cancle');" href="javascript:void(0);">取消</a></li>
									</ul>
								</div>
							</s:if> <s:else>选择</s:else></th>
						<th width="30%">名称</th>
						<th width="30%">金额</th>
						<th width="25%">类型</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseType = $("#chooseType").val();
	chooseMap.setColumnName("name");

	var chooseCustomerAccountClipTypeTableColumns = [ {
		"data" : function(data) {
			if (chooseType == 'multi') {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='checkBox' onchange=\"checkBoxChange('${entityName}',this,'" + data.id + "','" + data.name + "','" + data.payMoney + "','" + data.type + "');\"/>";
			}
			if (chooseType == 'single') {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "','" + data.payMoney + "','" + data.type + "');\"/>";
			}
			return "";
		}
	}, {
		"data" : function(data) {
			return data.name;
		}
	}, {
		"data" : function(data) {
			return data.payMoney;
		}
	}, {
		"data" : function(data) {
			if (data.type == 1) {
				return "<span class='label label-warning'>余额卡</span>";
			} else if (data.type == 2) {
				return "<span class='label label-info'>次卡</span>";
			} else if (data.type == 3) {
				return "<span class='label label-primary'>通用卡</span>";
			}
			return "";
		}
	} ];
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var chooseCustomerAccountClipTypeTable = initDataTable("chooseCustomerAccountClipTypeTableId", "${nvix}/nvixnt/nvixCustomerAccountClipAction!getCustomerAccountClipTyoeList.action", chooseCustomerAccountClipTypeTableColumns, function(page, pageSize, orderField, orderBy) {
		var customerName = $("#customerName").val();
		customerName = Url.encode(customerName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"selectName" : customerName
		};
	}, 6, selectType, isGenerateIndex);
	function radioChange(entityName,id,name,payMoney,type){
		parent.$('#' + entityName + 'Id').val(id);
		parent.$('#' + entityName + 'Name').val(name);
		parent.$('#' + entityName + 'PayMoney').val(payMoney);
		parent.$('#' + entityName + 'Type').val(type);
	}
</script>