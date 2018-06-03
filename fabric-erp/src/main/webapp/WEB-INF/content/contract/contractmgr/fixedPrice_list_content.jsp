<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#caTable table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#caTable table tr:even").addClass("alt");
function radioCheck(price){
	$.returnValue = price;
}
</script>
<table id="caTable" class="list">
	<tbody>
		<tr class="alt">
			<th>选择</th>
			<th>名称</th>
			<th>类型</th>
			<th>价格</th>
		</tr>
		<% int count =0; %>
		<s:iterator value="priceFixEntityList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkPriceId" name="chkPriceId" value="${entity.price}" type="radio" onclick="radioCheck(${entity.price});" /></td>
				<td><span style="color: gray;">${entity.name}</span></td>
				<td><s:if test="#entity.type == 'count'">
						数量
					</s:if> <s:elseif test="#entity.type == 'agency'">
						经销商
					</s:elseif> <s:elseif test="#entity.type == 'customerAccount'">
						客户
					</s:elseif> <s:elseif test="#entity.type == 'time'">
						时间
					</s:elseif> <s:elseif test="#entity.type == 'supplier'">
						供应商
					</s:elseif></td>
				<td><span style="color: gray;">${entity.price}</span></td>
			</tr>
		</s:iterator>
		<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			java.util.List<com.vix.mdm.item.action.transport.PriceFixEntity> list = (java.util.List<com.vix.mdm.item.action.transport.PriceFixEntity>)request.getAttribute("priceFixEntityList");
			count = 10 -list.size();
			if(count < 0){
				count = 0;
			}
			request.setAttribute("count",count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>