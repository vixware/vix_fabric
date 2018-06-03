<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="memberTagForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixCustomerAccountClipAction!swivelCard.action">
	<input type="hidden" id="id" name="customerAccountClip.id" value="${customerAccountClip.id}" />
	<input type="hidden" id="customerId" name="customerAccountClip.customerAccount.id" value="${customerAccountClip.customerAccount.id}" />
	<input type="hidden" id="cardId" name="customerAccountClip.card.id" value="${customerAccountClip.card.id}" />
	<fieldset>
		
		<div class="form-group">
			<label class="col-md-2 control-label"> 编码:</label>
			<div class="col-md-3">
				<input id="code" name="customerAccountClip.code" value="${customerAccountClip.code}" class="form-control validate[required]" type="text" readonly="readonly"/>
			</div>
			<label class="col-md-2 control-label">原持卡人:</label>
			<div class="col-md-3">
				<input id="name" name="" value="${customerAccountClip.customerAccount.name}" class="form-control validate[required]" type="text" readonly="readonly"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 会员卡号:</label>
			<div class="col-md-3">
				<input id="name" name="customerAccountClip.name" value="${customerAccountClip.name}" class="form-control validate[required]" type="text" readonly="readonly"/>
			</div>
			<label class="col-md-2 control-label">会员卡类型:</label>
			<div class="col-md-3">
				<select id="clipType" name="customerAccountClip.card.type" class="form-control" disabled="disabled">
					<option value="">------请选择------</option>
					<option value="1" <c:if test='${customerAccountClip.card.type == "1"}'>selected="selected"</c:if>>余额卡</option>
					<option value="2" <c:if test='${customerAccountClip.card.type == "2"}'>selected="selected"</c:if>>次卡</option>
					<%-- <c:forEach items="${customerAccountClipTypeList}" var="entity">
						<option value="${entity.id}" <c:if test="${customerAccountClip.customerAccountClipType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach> --%>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 会员卡余额:</label>
			<div class="col-md-3">
				<input id="money" name="customerAccountClip.money" value="${customerAccountClip.money}" class="form-control" type="text" readonly="readonly"/>
			</div>
			<label class="col-md-2 control-label">会员卡积分:</label>
			<div class="col-md-3">
				<input id="pointValue" name="customerAccountClip.pointValue" value="${customerAccountClip.pointValue}" class="form-control validate[required]" type="text" readonly="readonly"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 转赠人:</label>
			<div class="col-md-3">
			<div class="row">
				<div class="col-sm-12">
					<div class="input-group">
						<input id="customerAccountId" type="hidden" name="customerAccount.id" value="${customerAccount.id}" />
						<input id="customerAccountName" name="customerAccount.name" value="${customerAccount.name}" class="form-control validate[required]" type="text" readonly="readonly" />
						<div class="input-group-btn">
							<button onclick="goChooseCustomer();" type="button" class="btn btn-info">
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
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="customerAccountClip.memo" class="form-control">${customerAccountClip.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#memberTagForm").validationEngine();
	function goChooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAccountClipAction!goChooseCustomer.action', 'single', '选择会员', 'customerAccount');
	};
</script>