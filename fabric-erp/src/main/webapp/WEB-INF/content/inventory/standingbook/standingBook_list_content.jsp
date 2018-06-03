<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function chooseAll(chk) {
		if (null == chk) {
			$("input[name='chkId']").attr("checked", true);
		} else {
			if ($(chk).attr('checked')) {
				$("input[name='chkId']").attr("checked", true);
			} else {
				$("input[name='chkId']").attr("checked", false);
			}
		}
		selectCount();
	}
	function selectCount() {
		var selectCount = 0;
		$.each($("input[name='chkId']"), function(i, n) {
			if ($(n).attr('checked')) {
				selectCount++;
			}
		});
		$("#selectCount1").html(selectCount);
		$("#selectCount2").html(selectCount);
		if (selectCount == 0) {
			$("input[name='chkIds']").attr("checked", false);
		} else {
			$("input[name='chkIds']").attr("checked", true);
		}
	}

	loadOrderByImage("${vix}", "standingbook");
</script>
<input type="hidden" id="standingbookTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="standingbookPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="standingbookTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="standingbookOrderField" value="${pager.orderField}" />
<input type="hidden" id="standingbookOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="standingbookInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="4%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /> </a></li>
							<li><a href="#"><s:text name="cmn_other" /> </a></li>
							<li><a href="#"><s:text name="cmn_close" /> </a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('itemcode');">商品编码&nbsp;<img id="standingbookImg_itemcode" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('skuCode');">SKU编码&nbsp;<img id="standingbookImg_skuCode" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('itemname');">名称&nbsp;<img id="standingbookImg_itemname" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('quantity');">库存数量&nbsp;<img id="standingbookImg_quantity" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('avaquantity');">可用数量&nbsp;<img id="standingbookImg_avaquantity" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('warehouse');">价格&nbsp;<img id="standingbookImg_price" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('warehouse');">单位&nbsp;<img id="standingbookImg_warehouse" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('invShelfName');">货位&nbsp;<img id="standingbookImg_invShelfName" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('batchcode');">批次号&nbsp;<img id="standingbookImg_batchcode" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('massunitEndTime');">到期日&nbsp;<img id="standingbookImg_massunitEndTime" src="${vix}/common/img/arrow.gif"></span></th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><a href="#" style="color: gray;">${entity.itemcode }</a></td>
				<td><a href="#" style="color: gray;">${entity.skuCode }</a></td>
				<td><a href="#" style="color: gray;">${entity.itemname }</a></td>
				<td><a href="#" style="color: gray;"><span style="color: green;">${entity.quantity }</span></a></td>
				<td><a href="#" style="color: gray;"><span style="color: blue;">${entity.avaquantity }</span></a></td>
				<td><a href="#" style="color: gray;">${entity.price}</a></td>
				<td><a href="#" style="color: gray;">${entity.unit}</a></td>
				<td><a href="#" style="color: gray;">${entity.invShelf.name}</a></td>
				<td><a href="#" style="color: gray;">${entity.batchcode }</a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.massunitEndTime }" pattern="yyyy-MM-dd" /></a></td>
			</tr>
		</s:iterator>
		<%
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count", count);
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>