<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<fieldset>
	<div class="col-sm-7" style="text-align: left; padding: 5px 5px;">
		<div id="stockInDetailDiv" class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i>
				</span>
				<h2>服务列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin: 5px;">
						<div class="form-group" style="margin: 0 0px;">
							<div class="col-md-4">
								<input type="text" value="" placeholder="服务名称" class="form-control" id="searchProductName">
							</div>
							<button onclick="chooseEcProductTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
						</div>
					</div>
					<table id="chooseEcProduct" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="15%">编码</th>
								<th width="60%">名称</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-5" style="text-align: left; padding: 0px 0px;">
		<form id="storedValueRuleSetDetailForm" class="form-horizontal" style="padding: 5px 5px;">
			<div class="jarviswidget">
				<header>
					<h2>规则明细</h2>
				</header>
				<div>
					<div class="widget-body">
						<fieldset>
							<input type="hidden" id="storedValueRuleSetDetailId" name="storedValueRuleSetDetail.id" value="${storedValueRuleSetDetail.id }" /> 
							<input type="hidden" id="storedValueRuleSetId" name="storedValueRuleSetDetail.storedValueRuleSet.id" value="${storedValueRuleSetDetail.storedValueRuleSet.id }" />
							<input type="hidden" id="itemId" name="storedValueRuleSetDetail.item.id" value="${storedValueRuleSetDetail.item.id }" />
							<legend></legend>
							<div class="form-group">
								<label class="col-md-3 control-label"><span class="text-danger">*</span>编码:</label>
								<div class="col-md-9">
									<input id="itemcode" name="storedValueRuleSetDetail.item.code" value="${storedValueRuleSetDetail.item.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label"><span class="text-danger">*</span>名称:</label>
								<div class="col-md-9">
									<input id="itemname" name="storedValueRuleSetDetail.item.name" value="${storedValueRuleSetDetail.item.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label"><span class="text-danger">*</span>次数:</label>
								<div class="col-md-6">
									<s:if test="null == storedValueRuleSetDetail">
										<input id="num" name="storedValueRuleSetDetail.num" value="1" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:if>
									<s:else>
										<input id="num" name="storedValueRuleSetDetail.num" value="${storedValueRuleSetDetail.num}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:else>
								</div>
							</div>
							<legend></legend>
						</fieldset>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-12">
									<button type="button" class="btn btn-success" onclick="updateStoredValueRuleSetDetail();">
										<i class="fa fa-save"></i> &nbsp; 保存
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</fieldset>
<script type="text/javascript">
	var chooseEcProductColumns = [ {
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	} ];

	var chooseEcProductTable = initDataTable("chooseEcProduct", "${nvix}/nvixnt/storedValueRuleSetAction!goItemContent.action", chooseEcProductColumns, function(page, pageSize, orderField, orderBy) {
		var searchItemName = $("#searchProductName").val();
		searchItemName = Url.encode(searchItemName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"selectName" : searchItemName
		};
	}, 6, '1', '0');
	$('#chooseEcProduct tbody').on('click', 'tr', function() {
		var data = chooseEcProductTable.row(".selected").data();
		if (data != undefined) {
			$("#itemname").val(data.name);
			$("#itemcode").val(data.code);
			$("#num").val(1);
			$("#itemId").val(data.id);
		} else {
			clearStockInDetailForm();
		}
	});
	function updateStoredValueRuleSetDetail() {
		if ($('#storedValueRuleSetDetailForm').validationEngine('validate')) {
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			$("#storedValueRuleSetDetailForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/storedValueRuleSetAction!updateStoredValueRuleSetDetail.action?id="+$("#storedValueRuleSetId").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				itemTable.ajax.reload();
				clearStockInDetailForm();
			}
			});
		}
	};
	function clearStockInDetailForm() {
		$("#itemname").val("");
		$("#itemcode").val("");
		$("#num").val("");
		$("#itemId").val("");
	};
</script>