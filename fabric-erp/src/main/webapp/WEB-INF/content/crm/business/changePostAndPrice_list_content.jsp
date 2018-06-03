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
	loadOrderByImage("${vix}", "salesOrder");
</script>
<input type="hidden" id="salesOrderTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="salesOrderPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="salesOrderTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="salesOrderOrderField" value="${pager.orderField}" />
<input type="hidden" id="salesOrderOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="salesOrderInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('outerId');">订单编码&nbsp;<img id="salesOrderImg_outerId" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('buyerNick');">买家昵称&nbsp;<img id="salesOrderImg_buyerNick" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="35%"><span style="cursor: pointer;" onclick="orderBy('receiverAddress');">收货地址&nbsp;<img id="salesOrderImg_receiverAddress" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('receiverMobile');">联系电话&nbsp;<img id="salesOrderImg_receiverMobile" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('totalFee');">应付金额&nbsp;<img id="salesOrderImg_totalFee" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><span style="cursor: pointer;" onclick="orderBy('payTypeCn');">支付类型&nbsp;<img id="salesOrderImg_payTypeCn" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><span style="cursor: pointer;" onclick="orderBy('status');">订单状态&nbsp;<img id="salesOrderImg_status" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><span style="cursor: pointer;" onclick="orderBy('createTime');">下单日期&nbsp;<img id="salesOrderImg_createTime" src="${vix}/common/img/arrow.gif"></span></th>
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
					<td><a href="#" onclick="goUpdatePrice('${entity.id}');" style="color: gray;">${entity.outerId }</a></td>
					<td><a href="#" onclick="goUpdatePrice('${entity.id}');" style="color: gray;">${entity.buyerNick }</a></td>
					<td><a href="#" onclick="goUpdatePrice('${entity.id}');" style="color: gray;">${entity.receiverAddress }</a></td>
					<td><a href="#" onclick="goUpdatePrice('${entity.id}');" style="color: gray;">${entity.receiverMobile }</a></td>
					<td><a href="#" onclick="goUpdatePrice('${entity.id}');" style="color: gray;">${entity.totalFee }</a></td>
					<td><a href="#" onclick="goUpdatePrice('${entity.id}');" style="color: gray;">${entity.payTypeCn }</a></td>
					<td><a href="#" onclick="goUpdatePrice('${entity.id}');" style="color: gray;"><span style="color: red;"><s:if test="%{#entity.status==5}">待出库</s:if> </span> <s:elseif test="%{#entity.status==6}">已发货</s:elseif> <s:elseif test="%{#entity.status==7}">完成</s:elseif></a></td>
					<td><a href="#" onclick="goUpdatePrice('${entity.id}');" style="color: gray;"><fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd" /></a></td>
					<td><a href="#" onclick="goUpdatePrice('${entity.id}');" title="改邮费价"><img src="${vix}/common/img/system/sec_base.png" /></a></td>
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