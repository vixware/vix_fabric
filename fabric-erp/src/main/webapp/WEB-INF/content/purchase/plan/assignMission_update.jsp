<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="purchasePlanPackageId" name="purchasePlanPackageId" value="${purchasePlanPackage.id}" />
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="purchasePlanPackageForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">主题：</td>
					<td><input id="purchasePlanPackageName" name="purchasePlanPackageName" value="${purchasePlanPackage.name }" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">编制人：</td>
					<td><input id="creator" name="creator" value="${purchasePlanPackage.creator }" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>