<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="nt">
	<div class="nt_title">
		<label>规格列表</label>
	</div>
	<div class="nt_line">
		<table id="skutable">
			<tr height="30">
				<s:if test="titlespecUtil.filds1 != null">
					<td align="center">${titlespecUtil.filds1 }</td>
				</s:if>
				<s:if test="titlespecUtil.filds2 != null">
					<td align="center">${titlespecUtil.filds2 }</td>
				</s:if>
				<td align="center">价格</td>
				<td align="center">数量</td>
				<td align="center">SKU编码</td>
			</tr>
			<s:iterator var="ef" value="specUtilList" status="s">
				<tr height="30">
					<s:if test="#ef.filds1 != null">
						<td align="center"><input name="filds1" type="text" value="${ef.filds1 }" readonly="readonly" /></td>
					</s:if>
					<s:if test="#ef.filds2 != null">
						<td align="center"><input name="filds2" type="text" value="${ef.filds2 }" readonly="readonly" /></td>
					</s:if>
					<td align="center"><input name="skuprice" type="text" /></td>
					<td align="center"><input name="skuamount" type="text" /></td>
					<td align="center"><input name="skucode" type="text" /></td>
				</tr>
			</s:iterator>
		</table>
	</div>
</div>
