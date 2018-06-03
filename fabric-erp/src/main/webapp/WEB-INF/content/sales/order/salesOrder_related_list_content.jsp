<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#subSalesOrder tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#subSalesOrder tr:even").addClass("alt");
function showOrder(id){
	$.ajax({
		url:'${vix}/sales/order/salesOrderAction!showSalesOrder.action?tag=window&id='+id,
		cache: false,
		success: function(html){
			asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"销售订单",
					html:html,
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<input type="hidden" id="subSalesOrderTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="subSalesOrderPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="subSalesOrderTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="subSalesOrderOrderField" value="${pager.orderField}" />
<input type="hidden" id="subSalesOrderOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="subSalesOrderInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list" id="subSalesOrder">
	<tbody>
		<tr class="alt">
			<th onclick="orderBy('id');" width="8%"><s:text name="cmn_id" />&nbsp;<img id="subSalesOrderImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('title');">主题&nbsp;<img id="orderImg_title" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('customerAccount.name');">客户&nbsp;<img id="orderImg_customerAccount" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('amountTotal');">金额&nbsp;<img id="orderImg_amountTotal" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('status');">状态&nbsp;<img id="orderImg_status" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="9%"><span style="cursor: pointer;" onclick="orderBy('salePerson.name');">业务员&nbsp;<img id="orderImg_salePerson" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('billDate');">日期&nbsp;<img id="orderImg_billDate" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%">查看</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.title}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.amountTotal}</td>
				<td><s:if test="#entity.status == 0">
						禁用
					</s:if> <s:elseif test="#entity.status == 1">
						激活
					</s:elseif></td>
				<td>${entity.salePerson.name}</td>
				<td><s:property value="formatDateToString(#entity.billDate)" /></td>
				<td><a href="#" onclick="showOrder('${entity.id}');"><img src="${vix}/common/img/icon_19.gif" /></a></td>
			</tr>
		</s:iterator>
		<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			count = 8 - count;
			request.setAttribute("count",count);
		%>
		<c:forEach begin="1" end="${count}">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>