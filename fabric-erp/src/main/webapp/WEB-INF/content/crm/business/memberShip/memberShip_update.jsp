<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="id" name="id" value="${customerCare.id}" />
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="customerCareForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">主题:</td>
					<td colspan="3"><input id="subject" name="customerCare.subject" value="${customerCare.subject}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">会员:</td>
					<td><input id="customerAccountName" name="customerCare.customerAccount.name" value="${customerCare.customerAccount.name}" type="text" /> <span><a class="abtn" href="###" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
				</tr>
				<tr>
					<td align="right">关怀内容:</td>
					<td><textarea id="careContent" rows="3" cols="40" style="font-size: 12px;">${customerCare.careContent}</textarea></td>
					<td align="right">客户反馈:</td>
					<td><textarea id="customerFeedback" rows="3" cols="40" style="font-size: 12px;">${customerCare.customerFeedback}</textarea></td>
				</tr>
				<tr>
					<td align="right">备注:</td>
					<td colspan="3"><textarea id="memo" rows="3" cols="60" style="font-size: 12px;">${customerCare.memo}</textarea></td>
				</tr>
			</table>
		</div>
		<!--oder-->
	</form>
</div>
