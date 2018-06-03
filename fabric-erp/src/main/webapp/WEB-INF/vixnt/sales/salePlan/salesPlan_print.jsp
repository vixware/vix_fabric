<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="div_print">
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7">
			<tbody>
				<tr>
					<td align="center" style="font-size: 15px">销售计划</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7" class="pickinglisttable">
			<tbody>
				<s:hidden id="id" name="salePlan.id" value="%{salePlan.id}" theme="simple" />
				<tr>
					<td align="right" height="25">计划名称:&nbsp;</td>
					<td height="25">${salePlan.name}</td>
				</tr>
				<tr>
					<td align="right" height="25">上级计划:&nbsp;</td>
					<td height="25">${salePlan.parentSalePlan.name}<s:hidden id="parentSalePlanId" name="parentSalePlanId" value="%{salePlan.parentSalePlan.id}" theme="simple" />
					</td>
					<td align="right" height="25">销售人员:&nbsp;</td>
					<td height="25">${salePlan.salesMan.name}<s:hidden id="salesManId" name="salesManId" value="%{salePlan.salesMan.id}" theme="simple" />
					</td>
				</tr>
				<tr>
					<td align="right" height="25">销售组织:&nbsp;</td>
					<td height="25">${salePlan.saleOrg.fs}<s:hidden id="saleOrgId" name="saleOrgId" value="%{salePlan.saleOrg.id}" theme="simple" />
					</td>
					<td align="right">销售部门:</td>
					<td>${salePlan.departmet.fullName}<s:hidden id="departmentId" name="departmetId" value="%{salePlan.departmet.id}" theme="simple" />
					</td>
				</tr>
				<tr>
					<td align="right" height="25">${vv:varView('vix_mdm_item')}名称:&nbsp;</td>
					<td height="25">${salePlan.item.name}<s:hidden id="itemId" name="itemId" value="%{salePlan.item.id}" theme="simple" />
					</td>
					<td align="right">${vv:varView('vix_mdm_item')}规格:</td>
					<td>${salePlan.specifications}</td>
				</tr>
				<tr>
					<td align="right" height="25">数量:&nbsp;</td>
					<td height="25">${salePlan.amount}</td>
					<td align="right" height="25">计量单位:&nbsp;</td>
					<td height="25">${salePlan.measurementUnit}</td>
				</tr>
				<tr>
					<td align="right" height="25">辅助计量单位:&nbsp;</td>
					<td height="25">${salePlan.auxiliaryUnit}</td>
					<td align="right" height="25">生产组织:&nbsp;</td>
					<td height="25">${salePlan.produceOrg}</td>
				</tr>
				<tr>
					<td align="right" height="25">时间:&nbsp;</td>
					<td height="25">${salePlan.time}</td>
					<td align="right" height="25">周期:&nbsp;</td>
					<td height="25">${salePlan.cycle}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<style>
.pickinglisttable tr {
	font-size: 10px;
}
</style>