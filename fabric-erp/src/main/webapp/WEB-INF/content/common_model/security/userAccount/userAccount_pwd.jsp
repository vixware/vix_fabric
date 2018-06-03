<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<s:hidden id="accountId" name="entityForm.id" value="%{entity.id}" theme="simple" />
	<div class="box order_table" style="line-height: normal;">
		<table>
			<tr height="40">
				<td align="right">密码:&nbsp;</td>
				<td><input type="password" id="password" name="entityForm.password" value="" class="validate[required] text-input" /></td>
				<td align="right">确认密码:&nbsp;</td>
				<td><input type="password" id="passwordConfirm" name="entityForm.passwordConfirm" value="" class="validate[required] text-input" /></td>
			</tr>
		</table>
	</div>
	</form>
</div>
<script src="${vix}/common/js/jtip.js" type="text/javascript"></script>

