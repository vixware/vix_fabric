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
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="vixCode" name="vixCode" class="validate[required] text-input" value="${industryManagement.vixCode}" /><span style="color: red;">*</span></td>
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="name" name="name" class="validate[required] text-input" value="${industryManagement.name}" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">英文名称:&nbsp;</td>
					<td><input type="text" id="englishName" name="englishName" value="${industryManagement.englishName}" /></td>
					<td align="right">所属行业:&nbsp;</td>
					<td><input type="text" id="belongsIndustry" name="belongsIndustry" value="${industryManagement.belongsIndustry}" /></td>
				</tr>
				<tr height="40">
					<td align="right">行业内码:&nbsp;</td>
					<td><input type="text" id="innerCode" name="innerCode" class="validate[required] text-input" value="${industryManagement.innerCode}" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>