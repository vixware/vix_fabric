<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#wxpQyContactsForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="wxpQyContactsForm">
		<s:hidden id="wxpQyContactsId" name="wxpQyContactsId" value="%{wxpQyContacts.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">成员名称:&nbsp;</td>
					<td><input type="text" id="wxpQyContactsName" name="wxpQyContactsName" class="validate[required] text-input" value="${wxpQyContacts.name}" /><span style="color: red;">*</span></td>
					<td align="right">职位信息:&nbsp;</td>
					<td><input type="text" id="position" name="position" value="${wxpQyContacts.position}" /></td>
				</tr>
				<tr height="40">
					<td align="right">手机号码:&nbsp;</td>
					<td><input type="text" id="mobile" name="mobile" value="${wxpQyContacts.mobile}" /></td>
					<td align="right">邮箱:&nbsp;</td>
					<td><input type="text" id="email" name="email" value="${wxpQyContacts.email}" /></td>
				</tr>
				<tr height="40">
					<td align="right">微信号:&nbsp;</td>
					<td><input type="text" id="weixinid" name="weixinid" value="${wxpQyContacts.weixinid}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>