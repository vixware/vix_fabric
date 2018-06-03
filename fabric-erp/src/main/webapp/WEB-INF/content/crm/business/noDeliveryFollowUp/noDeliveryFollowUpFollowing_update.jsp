<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>


<style>
.message_box {
	padding: 15px;
}

.message_title {
	margin-bottom: 15px;
}

.message_title input {
	vertical-align: middle;
}

.message_text {
	width: 90%;
	display: block;
	margin: 0 auto;
	height: 100px;
}

.message_box p {
	margin: 15px 0;
	color: #666;
}

.message_test {
	overflow: hidden;
	overflow: hidden;
}

.message_test textarea {
	width: 98%;
	height: 50px;
}
</style>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="myAffairsForm">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="myAffairsId" name="myAffairsId" value="%{myAffairs.id}" theme="simple" />
			<s:hidden id="salesOrderId" name="salesOrderId" value="%{salesOrderId}" theme="simple" />
			<table>
				<tr height="40">
					<td align="right">事务标题:&nbsp;</td>
					<td><input type="text" id="name" name="name" class="validate[required] text-input" value="${myAffairs.name}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">类型：</td>
					<td><select id="affairsType" name="affairsType" class="validate[required] text-input">
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
					</select><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">重要性：</td>
					<td><select id="importance" name="importance" class="validate[required] text-input">
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
					</select><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">处理人:&nbsp;</td>
					<td><input type="text" id="processor" name="processor" value="${myAffairs.processor}" /></td>
				</tr>
				<tr height="40">
					<th>延迟时间：</th>
					<td><input id="delayTime" name="delayTime" value="<s:date name="%{myAffairs.delayTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" /><img onclick="showTime('delayTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
						style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">事务备注:&nbsp;</td>
					<td><input type="text" id="memo" name="memo" value="${myAffairs.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>