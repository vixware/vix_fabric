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
						<input type="text" value="" placeholder="名称" class="form-control" id="searchName">
					</div>
					<button onclick="chooseSupplierTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button type="button" class="btn btn-default" onclick="$('#searchName').val('');chooseSupplierTable.ajax.reload();">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</div>
			</div>
			<table id="chooseSupplierTableId" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="10%">
							<s:if test="chooseType == 'multi'">
								<div class="btn-group">
									<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default" aria-expanded="false">
										选择 <i class="fa fa-caret-down"></i>
									</button>
									<ul class="dropdown-menu js-status-update pull-left">
										<li><a id="all" onclick="changeDataTableSelect('chooseCustomerAccountClipTableId','all');" href="javascript:void(0);">全选</a></li>
										<li><a id="reverse" onclick="changeDataTableSelect('chooseCustomerAccountClipTableId','reverse');" href="javascript:void(0);">反选</a></li>
										<li><a id="cancle" onclick="changeDataTableSelect('chooseCustomerAccountClipTableId','cancle');" href="javascript:void(0);">取消</a></li>
									</ul>
								</div>
							</s:if> 
							<s:else>选择</s:else></th>
						<th width="15%">名称</th>
						<th width="15%">类型</th>
						<th width="15%">所属行业</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseType = $("#chooseType").val();
	var chooseSupplierTableColumns = [ {
		"data" : function(data) {
			if (chooseType == 'single') {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "');\"/>";
			}
			return "";
		}
	}, {
		"data" : function(data) {
			return data.name;
		}
	} , {
		"data" : function(data) {
			if (data.type == 'GENERAL') {
				return "<span class='label label-info'>普通供应商</span>";
			} else if (data.type == 'AGREEMENT') {
				return "<span class='label label-primary'>协议供应商</span>";
			} else if (data.type == 'OUTSOURCING') {
				return "<span class='label label-success'>委外供应商</span>";
			}
			return "";
		}
	}, {
		"data" : function(data) {
			if (data.industry == '1') {
				return "<span class='label label-info'>零售</span>";
			} else if (data.industry == '2') {
				return "<span class='label label-primary'>生产</span>";
			} else if (data.industry == '3') {
				return "<span class='label label-success'>服务</span>";
			}
			return "";
		}
	}];
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var chooseSupplierTable = initDataTable("chooseSupplierTableId", "${nvix}/nvixnt/mdm/nvixntPurchasePriceAction!getSupplierList.action", chooseSupplierTableColumns, function(page, pageSize, orderField, orderBy) {
		var selectName = $("#searchName").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"selectName" : selectName
		};
	}, 5, selectType, isGenerateIndex);
</script>