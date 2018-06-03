<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<style type="text/css">
.item_detail_tr {
	display: none;
}

.item_detail_tr .item_table {
	border-style: none;
	width: 100%;
}

.item_detail_tr .item_table td {
	border-style: none;
}

.item_detail_tr .item_table .amount {
	width: 70px;
}
</style>
<script type="text/javascript">
	$(function() {
		$('.order_tr').click(function() {
			var orderId = $(this).attr('orderId');
			$('.item_detail_tr:visible').hide();
			$('#item_detail_' + orderId).slideDown();
		});
	});
</script>
<input type="hidden" id="purchaseArrivalTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="purchaseArrivalPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="purchaseArrivalTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="purchaseArrivalOrderField" value="${pager.orderField}" />
<input type="hidden" id="purchaseArrivalOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="purchaseArrivalInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<div class="right_content">
	<div class="table">
		<table class="list">
			<tr class="alt">
				<th width="50">选择</th>
				<th onclick="orderBy('code');">编号</th>
				<th onclick="orderBy('name');">名称</th>
			</tr>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr class="order_tr" orderId="${entity.id}">
					<td><input id="chkBrandId" name="chkBrandId" value="${entity.id}" type="radio" onclick="chkChange(this,'${entity.id}')" /></td>
					<td>${entity.code}</td>
					<td>${entity.name}</td>
				</tr>
				<tr id="item_detail_${entity.id}" class="item_detail_tr">
					<td></td>
					<td colspan="2">
						<table class="item_table" border="0" cellpadding="0" cellspacing="0">
							<s:iterator value="#entity.purchaseArrivalItems" var="item">
								<tr>
									<td width="15"><input type="checkbox" class="item_check" checked="checked" value="<s:property value="#item.id"/>" /></td>
									<td width="120">数量: <input type="text" class="amount" id="amount_<s:property value="#item.id"/>" value="<s:property value="#item.amount"/>" />
									</td>
									<td><s:property value="#item.itemName" /></td>
								</tr>
							</s:iterator>

						</table>
					</td>
				</tr>
			</s:iterator>
			<%
				/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
				com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request.getAttribute("pager");
				count = pager.getPageSize() - count;
				request.setAttribute("count", count);
			%>
			<c:forEach begin="1" end="${count}">
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagelist drop">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="purchaseArrivalInfo"></b>到 <b class="purchaseArrivalTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
		</div>
	</div>
</div>
