<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="memberTagForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixCustomerAccountClipAction!saveOrUpdateClipDetail.action">
	<input type="hidden" id="customerAccountClipId" name="customerAccountClipDetail.customerAccountClip.id" value="${customerAccountClipDetail.customerAccountClip.id}" />
	<input type="hidden" id="customerAccountId" name="customerAccountClipDetail.customerAccount.id" value="${customerAccountClipDetail.customerAccount.id}" />
	<input type="hidden" id="customerAccountClipDetailId" name="customerAccountClipDetail.id" value="${customerAccountClipDetail.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 服务项目:</label>
			<div class="col-md-3">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="itemId" type="hidden" name="customerAccountClipDetail.item.id" value="${customerAccountClipDetail.item.id}" />
							<input id="itemName" name="customerAccountClipDetail.item.name" value="${customerAccountClipDetail.item.name}" class="form-control validate[required]" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseitem();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#customerId').val('');$('#customerName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 服务次数:</label>
			<div class="col-md-3">
				<input id="number" name="customerAccountClipDetail.number" value="${customerAccountClipDetail.number}" class="form-control" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="customerAccountClipDetail.memo" class="form-control">${customerAccountClipDetail.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script>
	function goChooseitem() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAccountClipAction!goChooseItem.action', 'single', '选择服务项目', 'item');
	};
</script>