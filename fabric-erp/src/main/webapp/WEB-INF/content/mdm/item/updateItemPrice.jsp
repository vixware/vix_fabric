<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#itemPriceForm").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="itemPriceForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="ipId" name="itemPrice.id" value="%{itemPrice.id}" theme="simple" />
				<tr height="30">
					<td align="right">开始时间:&nbsp;</td>
					<td><input id="startDate" name="itemPrice.startDate" value="<s:property value='formatDateToTimeString(itemPrice.startDate)'/>" onfocus="showTime('startDate','yyyy-MM-dd HH:mm')" class="validate[required] text-input" /> <span style="color: red;">*</span><img onclick="showTime('startDate','yyyy-MM-dd HH:mm')"
						src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					</td>
					<td align="right">结束时间:&nbsp;</td>
					<td><input id="endDate" name="itemPrice.endDate" value="<s:property value='formatDateToTimeString(itemPrice.endDate)'/>" onfocus="showTime('endDate','yyyy-MM-dd HH:mm')" class="validate[required] text-input" /> <span style="color: red;">*</span><img onclick="showTime('endDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif"
						width="16" height="22" align="absmiddle"></td>
					</td>
				</tr>
				<tr height="30">
					<td align="right">最低价格:&nbsp;</td>
					<td><input id="minPrice" name="itemPrice.minPrice" value="${itemPrice.minPrice}" /></td>
					<td align="right">实际价格:&nbsp;</td>
					<td><input id="price" name="itemPrice.price" value="${itemPrice.price}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">最高价格:&nbsp;</td>
					<td><input id="maxPrice" name="itemPrice.maxPrice" value="${itemPrice.maxPrice}" /></td>
					<td align="right">备注:&nbsp;</td>
					<td><input id="memo" name="itemPrice.memo" value="${itemPrice.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>