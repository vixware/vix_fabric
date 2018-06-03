<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#industryManagementform").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="industryManagementform">
		<s:hidden id="industryManagementId" name="industryManagementId" value="%{industryManagement.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<%-- <td align="right">编码:&nbsp;</td>
					<td><input type="text" id="vixCode" name="vixCode" class="validate[required] text-input"
						value="${industryManagement.vixCode}" /><span style="color: red;">*</span></td> --%>
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="name" name="name" class="validate[required] text-input" value="${industryManagement.name}" /> <span style="color: red;">*</span></td>
					<%-- 	<td align="right">行业内码:&nbsp;</td>
					<td><input type="text" id="innerCode" name="innerCode" class="validate[required] text-input"
						value="${industryManagement.innerCode}" /><span style="color: red;">*</span></td> --%>
				</tr>
				<tr height="40">
					<td align="right">英文名称:&nbsp;</td>
					<td><input type="text" id="englishName" name="englishName" value="${industryManagement.englishName}" /></td>
					<td align="right">所属行业:&nbsp;</td>
					<td><input type="text" id="belongsIndustry" name="belongsIndustry" value="${industryManagement.belongsIndustry}" /></td>
				</tr>
				<tr height="40">
					<td align="right">开始时间:&nbsp;</td>
					<td><input type="text" id="startTime" name="entityForm.startTime" value="<s:date name='%{industryManagement.startTime}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img
						onclick="showTime('startTime','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">结束时间:&nbsp;</td>
					<td><input type="text" id="endTime" name="entityForm.endTime" value="<s:date name='%{industryManagement.endTime}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img onclick="showTime('endTime','yyyy-MM-dd')"
						src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<%-- <tr height="40">
					<td align="right">行业内码:&nbsp;</td>
					<td><input type="text" id="innerCode" name="innerCode" class="validate[required] text-input"
						value="${industryManagement.innerCode}" /><span style="color: red;">*</span></td>
				</tr> --%>
			</table>
		</div>
	</form>
</div>