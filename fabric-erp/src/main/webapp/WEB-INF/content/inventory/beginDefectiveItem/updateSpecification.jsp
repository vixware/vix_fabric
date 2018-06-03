<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<table class="commodity_border" id="skutable">
	<tr>
		<s:if test="titlespecUtil.filds1 != null">
			<th>${titlespecUtil.filds1 }</th>
		</s:if>
		<s:if test="titlespecUtil.filds2 != null">
			<th>${titlespecUtil.filds2 }</th>
		</s:if>
		<th>价格</th>
		<th>数量</th>
		<th>SKU编码</th>
	</tr>
	<s:iterator var="ef" value="specUtilList" status="s">
		<tr>
			<s:if test="#ef.filds1 != null">
				<td><input name="filds1" type="text" class="ipt" value="${ef.filds1 }" readonly="readonly" /></td>
			</s:if>
			<s:if test="#ef.filds2 != null">
				<td><input name="filds2" type="text" class="ipt" value="${ef.filds2 }" readonly="readonly" /></td>
			</s:if>
			<td><input name="skuprice" type="text" class="ipt" /></td>
			<td><input name="skuamount" type="text" class="ipt" /></td>
			<td><input name="skucode" type="text" class="ipt" /></td>
		</tr>
	</s:iterator>
</table>