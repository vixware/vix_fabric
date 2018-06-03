<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<table>
	<s:iterator var="specification" value="specificationList">
		<tr height="30">
			<td align="right" width="75">${specification.name}:&nbsp;</td>
			<td colspan="7"><s:iterator var="specificationDetail" value="#specification.specificationDetails">
					<input type="checkbox" id="specification_${specification.id}" name="specificationDetail_${specification.id}_${specificationDetail.id}" value="${specificationDetail.id}" />${specificationDetail.name}&nbsp;
				</s:iterator></td>
		</tr>
	</s:iterator>
</table>