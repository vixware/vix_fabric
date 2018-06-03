<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<s:iterator var="sdList" value="specificationDetailList" status="s">
	<tr height="30">
		<td><input type="text" id="sku_${s.count}" name="sku" onblur="checkSkuIsExist(this);" class="validate[required] text-input"></td>
		<s:iterator var="sd" value="sdList" status="status">
			<td><input type="hidden" id="specDetailCode_${s.count}_${status.count}" name="code" value="${sd.id}" /> <span id="specDetailName_${s.count}_${status.count}">${sd.name}</span></td>
		</s:iterator>
		<td><a href="###"><img onclick="removeThisSpecification(this,0);" src="${vix}/common/img/icon_12.png" alt="删除" /></a></td>
	</tr>
</s:iterator>
