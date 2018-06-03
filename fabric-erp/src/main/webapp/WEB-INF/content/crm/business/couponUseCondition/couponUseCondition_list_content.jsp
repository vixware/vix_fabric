<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该品牌么?', '<s:text name='vix_message'/>', function(action) {
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
				businussOrderId = businussOrderId + "," + $(n).val();
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
	loadOrderByImage("${vix}", "coupon");
</script>
<input type="hidden" id="couponTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="couponPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="couponTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="couponOrderField" value="${pager.orderField}" />
<input type="hidden" id="couponOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="couponInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('amount');">面额&nbsp;<img id="couponImg_amount" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('expenditure');">消费额&nbsp;<img id="couponImg_expenditure" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('userule');">使用限制&nbsp;<img id="couponImg_userule" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('quantity');">数量&nbsp;<img id="couponImg_quantity" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('hasGiveOut');">发放数量&nbsp;<img id="couponImg_hasGiveOut" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('useQuantity');">使用数量&nbsp;<img id="couponImg_useQuantity" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('cancellationQuantity');">作废数量&nbsp;<img id="couponImg_cancellationQuantity" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><s:text name="cmn_operate" /></th>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onclick="chkChange(this,'${entity.id}')" /></td>
					<td><a href="#" style="color: gray;">${entity.amount }</a></td>
					<td><a href="#" style="color: gray;">${entity.expenditure }</a></td>
					<td><a href="#" style="color: gray;">${entity.userule }</a></td>
					<td><a href="#" style="color: gray;">${entity.quantity }</a></td>
					<td><a href="#" style="color: gray;">${entity.hasGiveOut }</a></td>
					<td><a href="#" style="color: gray;">${entity.useQuantity }</a></td>
					<td><a href="#" style="color: gray;">${entity.cancellationQuantity }</a></td>
					<td><a href="#" title=""><img src="${vix}/common/img/icon_edit.png" /></a></td>
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
				</tr>
			</c:forEach>
	</tbody>
</table>