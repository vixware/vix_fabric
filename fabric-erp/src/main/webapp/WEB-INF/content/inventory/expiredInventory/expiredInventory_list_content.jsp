<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="inventoryCurrentStockTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="inventoryCurrentStockPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="inventoryCurrentStockTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="inventoryCurrentStockOrderField" value="${pager.orderField}" />
<input type="hidden" id="inventoryCurrentStockOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="inventoryCurrentStockInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<script type="text/javascript">
	loadOrderByImage("${vix}", "inventoryCurrentStock");
</script>
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
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
			<th><span style="cursor: pointer;" onclick="orderBy('itemcode');">商品编码&nbsp;<img id="inventoryCurrentStockImg_itemcode" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('itemname');">商品名称&nbsp;<img id="inventoryCurrentStockImg_itemname" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('masterUnit');">主计量单位&nbsp;<img id="inventoryCurrentStockImg_masterUnit" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('specification');">规格型号&nbsp;<img id="inventoryCurrentStockImg_specification" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('warehouse');">仓库&nbsp;<img id="inventoryCurrentStockImg_warehouse" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('quantity');">库存数量&nbsp;<img id="inventoryCurrentStockImg_quantity" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('producedate');">生产日期&nbsp;<img id="inventoryCurrentStockImg_producedate" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('massunitEndTime');">有效期至&nbsp;<img id="inventoryCurrentStockImg_massunitEndTime" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><s:text name="cmn_operate" /></th>
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
				<td><a href="#" style="color: gray;">${entity.itemname }</a></td>
				<td><a href="#" style="color: gray;">${entity.masterUnit }</a></td>
				<td><a href="#" style="color: gray;">${entity.specification}</a></td>
				<td><a href="#" style="color: gray;">${entity.warehouse }</a></td>
				<td><a href="#" style="color: gray;">${entity.quantity }</a></td>
				<td><a href="#" style="color: gray;">${entity.producedate}</a></td>
				<td><a href="#" style="color: gray;">${entity.massunitEndTime}</a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.name}</b>
							</strong>
							<p>${entity.memo}</p>
						</div>
					</div>
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