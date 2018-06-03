<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<s:if test="specList.size > 0">
	<table style="width: 100%;">
		<s:iterator var="specification" value="specList">
			<tr height="30">
				<td align="right">${specification.name}:&nbsp;</td>
				<td colspan="7"><s:iterator var="specificationDetail" value="#specification.specificationDetails">
						<input type="radio" onchange="specChange();" id="specification_${item.id}_${specificationDetail.id}" name="specificationDetail_${specification.id}" value="${specificationDetail.id}" />${specificationDetail.name}&nbsp;
					</s:iterator></td>
			</tr>
		</s:iterator>
	</table>
</s:if>