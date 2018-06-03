<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="memberTagDetailForm">
		<s:hidden id="memberTagDetailId" name="memberTagDetailId" value="%{memberTagDetail.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<th>条件：</th>
					<td><select id="standardCategory" name="standardCategory">
							<option value="CTM_BUY_NUM">购买成功订单数</option>
							<option value="CTM_BUY_AMOUNT">购买金额</option>
							<option value="CTM_ITEM_NUM">购买件数</option>
							<option value="CTM_CLOSE_TRADE_COUNT">退款订单数</option>
							<option value="CTM_CLOSE_TRADE_AMOUNT">退款金额</option>
							<option value="CTM_LAST_TRADE_TIME">最后一次购买时间</option>
							<option value="CTM_FIRST_PURCHASE_TIME">第一次够买时间</option>
							<option value="CTM_CUSTOMER_LEVEL">会员等级</option>
							<option value="CTM_AVG_PRICE">平均客单价</option>
					</select></td>
					<th>运算符：</th>
					<td><select id="operationalCharacter" name="operationalCharacter">
							<option value="equal">等于</option>
							<option value="noequal">不等于</option>
							<option value="morethan">大于</option>
							<option value="morethanandequal">大于等于</option>
							<option value="lessthan">小于</option>
							<option value="lessthanandequal">小于等于</option>
					</select></td>
					<th>值：</th>
					<td><input id="categoryValue" name="categoryValue" value="${memberTagDetail.categoryValue }" type="text" class="validate[required] text-input"><img onclick="showTime('categoryValue','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
