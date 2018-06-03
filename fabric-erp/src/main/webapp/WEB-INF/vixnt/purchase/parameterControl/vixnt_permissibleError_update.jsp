<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="purchasePermissibleErrorForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/purchase/vixntPurchasePermissibleErrorAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="purchasePermissibleError.id" value="${purchasePermissibleError.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 编码:</label>
			<div class="col-md-3">
				<input id="code" name="purchasePermissibleError.code" value="${purchasePermissibleError.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-3">
				<input id="name" name="purchasePermissibleError.name" value="${purchasePermissibleError.name}" class="form-control validate[required]" type="text" />
			</div>
			<%-- <div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>规则类型:</label>
			<div class="col-md-3">
				<select id="status" name="purchasePermissibleError.status" class="form-control">
					<option  value="number"<c:if test='${purchasePermissibleError.status eq "number"}'>selected="selected"</c:if>>数量</option>
					<option  value="money"<c:if test='${purchasePermissibleError.status eq "money"}'>selected="selected"</c:if>>金额</option>
					<option  value="beLate"<c:if test='${purchasePermissibleError.status eq "beLate"}'>selected="selected"</c:if>>迟到天数</option>
					<option  value="earlyArrival"<c:if test='${purchasePermissibleError.status eq "earlyArrival"}'>selected="selected"</c:if>>早到天数</option>
				</select>
			</div>
		</div>	 --%>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">单据性质:</label>
			<div class="col-md-3">
				<select id="entityType" name="purchasePermissibleError.entityType" class="form-control">
					<option  value="PUR_ORDER"<c:if test='${purchasePermissibleError.entityType eq "PUR_ORDER"}'>selected="selected"</c:if>>采购订单</option>
					<option  value="PUR_PLAN"<c:if test='${purchasePermissibleError.entityType eq "PUR_PLAN"}'>selected="selected"</c:if>>采购计划</option>
					<option  value="PUR_INQUERY"<c:if test='${purchasePermissibleError.entityType eq "PUR_INQUERY"}'>selected="selected"</c:if>>采购询价</option>
					<option  value="PUR_INBOUND"<c:if test='${purchasePermissibleError.entityType eq "PUR_INBOUND"}'>selected="selected"</c:if>>采购入库</option>
					<option  value="PUR_APPLY"<c:if test='${purchasePermissibleError.entityType eq "PUR_APPLY"}'>selected="selected"</c:if>>采购申请</option>
					<option  value="PUR_ARRIVAL"<c:if test='${purchasePermissibleError.entityType eq "PUR_ARRIVAL"}'>selected="selected"</c:if>>采购到货</option>
					<option  value="PUR_INVOICE"<c:if test='${purchasePermissibleError.entityType eq "PUR_INVOICE"}'>selected="selected"</c:if>>采购发票</option>
				</select>
			</div>
			<label class="col-md-3 control-label">业务类型:</label>
			<div class="col-md-3">
				<select id="bizTypeId" name="purchasePermissibleError.bizType.id" class="form-control">
					<c:forEach items="${bizTypeList}" var="entity">
						<option  value="${entity.id}"<c:if test='${purchasePermissibleError.bizType.id eq entity.id}'>selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group" id="numberDiv">
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 允许数量偏差百分比:</label>
			<div class="col-md-3">
				<input id="number" name="purchasePermissibleError.number" value="${purchasePermissibleError.number}" class="form-control validate[required,custom[number],min[1]]" type="text" />
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 允许金额偏差百分比:</label>
			<div class="col-md-3">
				<input id="money" name="purchasePermissibleError.money" value="${purchasePermissibleError.money}" class="form-control validate[required,custom[number],min[1]]" type="text" />
			</div>
		</div>
		<div class="form-group" >
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 允许迟到天数:</label>
			<div class="col-md-3">
				<input id="beLate" name="purchasePermissibleError.beLate" value="${purchasePermissibleError.beLate}" class="form-control validate[required,custom[number],min[1]]" type="text" />
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 允许早到天数:</label>
			<div class="col-md-3">
				<input id="earlyArrival" name="purchasePermissibleError.earlyArrival" value="${purchasePermissibleError.earlyArrival}" class="form-control validate[required,custom[number],min[1]]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">备注:</label>
			<div class="col-md-9">
				<textarea name="purchasePermissibleError.memo" class="form-control">${purchasePermissibleError.memo} </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#purchasePermissibleErrorForm").validationEngine();
</script>