<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="clipId" value="${customerAccountClip.id}" />
<input type="hidden" id="chooseType" value="${chooseType}" />
<div class="jarviswidget" style="padding: 6px;">
	<header style="height: 1px; border-color: #ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin: 5px;">
				<div class="form-group" style="margin: 0 5px;">
					<div class="col-md-4">
						<input type="text" value="" placeholder="服务项目" class="form-control" id="selectName">
					</div>
					<button onclick="chooseWarehouseTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button type="button" class="btn btn-default" onclick="$('#customerName').val('');chooseWarehouseTable.ajax.reload();">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</div>
			</div>
			<table id="chooseCustomerTableId" class="table table-striped table-bordered table-hover" width="100%">
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseCustomerColumns = [{
		"title" : "编号",
		"width" : "8%",
		"defaultContent" : ''
		},{
		"title" : "服务项目",
		"width" : "30%",
		"data" : function(data) {
			return data.itemName;
		}
	}, {
		"title" : "服务内容",
		"width" : "45%",
		"data" : function(data) {
			return data.itemServiceContent;
		}
	} , {
		"title" : "服务次数",
		"width" : "15%",
		"data" : function(data) {
			return data.number;
		}
	}];
	var chooseWarehouseTable = initDataTable("chooseCustomerTableId", "${nvix}/nvixnt/nvixCustomerAccountClipAction!getCustomerAccountClipDetailById.action", chooseCustomerColumns, function(page, pageSize, orderField, orderBy) {
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		var clipId = $("#clipId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectName" : selectName,
		"customerAccountClipId":clipId
		};
	}, 6, "1");
</script>