<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该回款计划么?', '<s:text name='vix_message'/>', function(action) {
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
</script>
<input type="hidden" id="salesOrderTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="salesOrderPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="salesOrderTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="salesOrderOrderField" value="${pager.orderField}" />
<input type="hidden" id="salesOrderOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="salesOrderInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<div class="table"></div>
	<tbody>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s" id="invoiceId">
			<%
				count++;
			%>
			<tr>
				<th colspan="5"><span class="pull-right">下单时间：<fmt:formatDate value="${invoiceId.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span> <label><input type="checkbox" /> 订单编号：${invoiceId.outerId}</label></th>
			</tr>
			<tr>
				<td width="50%"><s:iterator value="#invoiceId.saleOrderItems" var="orderDetail" status="s">
						<dl class="goods_info">
							<dt>
								<img src="http://qiniuphotos.qiniudn.com/gogopher.jpg?imageView2/1/w/50/h/50">
							</dt>
							<dd>
								<span class="gray">${orderDetail.skuPropertiesName}</span><br /> <span class="pull-right">&yen;${orderDetail.price}&nbsp;&nbsp;x&nbsp;&nbsp; ${orderDetail.num}</span>
							</dd>
						</dl>
					</s:iterator></td>
				<td width="10%">${invoiceId.receiverName}</td>
				<td width="10%"><span><s:if test="%{#invoiceId.payment>0}">&yen;${invoiceId.payment}</s:if> <s:if test="%{#invoiceId.postFee>0}">(含运费:&yen;${invoiceId.postFee})</s:if></span><br /> <span class="gray">${invoiceId.payTypeCn}</span></td>
				<td width="10%"><s:if test="%{#invoiceId.status==5}">待出库</s:if> </span> <s:elseif test="%{#invoiceId.status==6}">已发货</s:elseif> <s:elseif test="%{#invoiceId.status==7}">已关闭</s:elseif></td>
				<td width="20%"><input type="button" class="btn" value="催付" onclick="saveOrUpdateExpediting(${invoiceId.id});" /> <input type="button" class="btn" value="跟进" onclick="saveOrUpdate(${invoiceId.id});" /></td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>