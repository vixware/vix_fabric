<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="membershipSubdivisionDetailForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntMembershipSubdivisionAction!saveOrUpdateMembershipSubdivisionDetail.action">
	<input type="hidden" id="membershipSubdivisionDetailId" name="membershipSubdivisionDetail.id" value="${membershipSubdivisionDetail.id}" /> <input type="hidden" id="membershipSubdivisionId" name="membershipSubdivisionDetail.membershipSubdivision.id" value="${membershipSubdivisionDetail.membershipSubdivision.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 条件:</label>
			<div class="col-md-7">
				<select id="standardCategory" name="membershipSubdivisionDetail.standardCategory" class="form-control">
					<option value="CTM_BUY_NUM">购买成功订单数</option>
					<option value="CTM_BUY_AMOUNT">购买金额</option>
					<option value="CTM_ITEM_NUM">购买件数</option>
					<option value="CTM_CLOSE_TRADE_COUNT">退款订单数</option>
					<option value="CTM_CLOSE_TRADE_AMOUNT">退款金额</option>
					<option value="CTM_LAST_TRADE_TIME">最后一次购买时间</option>
					<option value="CTM_FIRST_PURCHASE_TIME">第一次够买时间</option>
					<option value="CTM_CUSTOMER_LEVEL">会员等级</option>
					<option value="CTM_AVG_PRICE">平均客单价</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 运算符:</label>
			<div class="col-md-7">
				<select id="operationalCharacter" name="membershipSubdivisionDetail.operationalCharacter" class="form-control">
					<option value="equal">等于</option>
					<option value="noequal">不等于</option>
					<option value="morethan">大于</option>
					<option value="morethanandequal">大于等于</option>
					<option value="lessthan">小于</option>
					<option value="lessthanandequal">小于等于</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 值:</label>
			<div class="col-md-7">
				<input id="categoryValue" name="membershipSubdivisionDetail.categoryValue" value="${membershipSubdivisionDetail.categoryValue }" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#membershipSubdivisionDetailForm").validationEngine();
</script>