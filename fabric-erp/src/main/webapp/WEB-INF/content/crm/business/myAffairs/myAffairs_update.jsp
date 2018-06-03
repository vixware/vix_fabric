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
	<form id="orderExpeditingForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">事务标题:&nbsp;</td>
					<td><input type="text" id="code" name="code" class="validate[required] text-input" value="${invWarehouse.code}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">类型：</td>
					<td><select id="properties" name="properties" class="validate[required] text-input">
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
					</select><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">重要性：</td>
					<td><select id="properties" name="properties" class="validate[required] text-input">
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
					</select><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">创建人:&nbsp;</td>
					<td><input type="text" id="telephone" name="telephone" value="${invWarehouse.telephone}" /></td>
				</tr>
				<tr height="40">
					<td align="right">处理人:&nbsp;</td>
					<td><select id="properties" name="properties" class="validate[required] text-input">
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
					</select><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">事务备注:&nbsp;</td>
					<td><input type="text" id="memo" name="memo" value="${invWarehouse.memo}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>