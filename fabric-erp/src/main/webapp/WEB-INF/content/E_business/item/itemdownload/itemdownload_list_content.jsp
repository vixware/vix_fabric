<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该数据么?', '<s:text name='vix_message'/>', function(action) {
			if (action == 'ok') {
				deleteById(id);
			}
		});
	}
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
	loadOrderByImage("${vix}", "inventoryCurrentStock");
</script>
<input type="hidden" id="inventoryCurrentStockTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="inventoryCurrentStockPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="inventoryCurrentStockTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="inventoryCurrentStockOrderField" value="${pager.orderField}" />
<input type="hidden" id="inventoryCurrentStockOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="inventoryCurrentStockInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('goodsCode');">编码&nbsp;<img id="inventoryCurrentStockImg_goodsCode" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="40%"><span style="cursor: pointer;" onclick="orderBy('goodName');">名称&nbsp;<img id="inventoryCurrentStockImg_goodName" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('storenum');">库存&nbsp;<img id="inventoryCurrentStockImg_storenum" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('price');">价格&nbsp;<img id="inventoryCurrentStockImg_price" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('channelDistributor.name');">网店名称&nbsp;<img id="inventoryCurrentStockImg_channelDistributor" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td><a href="#" style="color: gray;" onclick="pophtml('${entity.id}');">${entity.goodsCode }</a></td>
				<td><a href="#" style="color: gray;" onclick="pophtml('${entity.id}');">${entity.goodName }</a></td>
				<td><a href="#" style="color: gray;" onclick="pophtml('${entity.id}');"><span style="color: green;">${entity.storenum }</span></a></td>
				<td><a href="#" style="color: gray;" onclick="pophtml('${entity.id}');">${entity.price }</a></td>
				<td><a href="#" style="color: gray;" onclick="pophtml('${entity.id}');">${entity.channelDistributor.name }</a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.goodsCode}</b>
							</strong>
							<p>${entity.goodName}</p>
						</div>
					</div>
				</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>