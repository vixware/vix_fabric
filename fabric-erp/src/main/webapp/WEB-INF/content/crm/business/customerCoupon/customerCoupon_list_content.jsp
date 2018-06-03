<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该优惠券么?', '提示信息', function(action) {
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

	loadOrderByImage("${vix}", "customerAccount");
</script>
<input type="hidden" id="customerAccountTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerAccountPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerAccountTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerAccountOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerAccountOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerAccountInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<div class="table"></div>
	<tbody>
		<th width="5%">
			<div class="list_check">
				<div class="drop">
					<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
					<ul>
						<li><a href="#" onclick="chooseAll();"><s:text name="all" /></a></li>
						<li><a href="#"><s:text name="other" /></a></li>
						<li><a href="#"><s:text name="close" /></a></li>
					</ul>
				</div>
			</div>
		</th>
		<th width="15%"><span style="cursor: pointer;" onclick="orderBy('amount');">面值&nbsp;<img id="customerAccountImg_amount" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="15%"><span style="cursor: pointer;" onclick="orderBy('expenditure');">所需消费金额&nbsp;<img id="customerAccountImg_expenditure" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="15%"><span style="cursor: pointer;" onclick="orderBy('type');">优惠券类别&nbsp;<img id="customerAccountImg_type" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="15%"><span style="cursor: pointer;" onclick="orderBy('userule');">使用限制&nbsp;<img id="customerAccountImg_userule" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="15%"><span style="cursor: pointer;" onclick="orderBy('effectiveDate');">有效期&nbsp;<img id="customerAccountImg_effectiveDate" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="15%"><span style="cursor: pointer;" onclick="orderBy('status');">状态&nbsp;<img id="customerAccountImg_status" src="${vix}/common/img/arrow.gif"></span></th>
		<th width="5%"><s:text name="cmn_operate" /></th>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s" id="customerAccountId">
			<%
				count++;
			%>
			<tr>
				<th colspan="8"><span class="pull-right">手机号码：${customerAccountId.mobilePhone}</span> <label> 会员姓名：${customerAccountId.name}</label></th>
			</tr>
			<s:iterator value="#customerAccountId.subCouponDetails" var="couponDetail" status="s">
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td>￥ ${couponDetail.amount}</td>
					<td>￥${couponDetail.expenditure}</td>
					<td>${couponDetail.type}</td>
					<td>${couponDetail.userule}</td>
					<td><fmt:formatDate value="${couponDetail.effectiveDate}" pattern="yyyy-MM-dd" />~<fmt:formatDate value="${couponDetail.cutOffDate}" pattern="yyyy-MM-dd" /></td>
					<td><s:if test="%{#couponDetail.status==2}">未使用</s:if> <s:elseif test="%{#couponDetail.status==3}">已使用</s:elseif> <s:elseif test="%{#couponDetail.status==4}">已过期</s:elseif></td>
					<td><a href="#" onclick="" title="删除"><img src="${vix}/common/img/icon_delete.png" /></a></td>
				</tr>
			</s:iterator>
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
			</tr>
		</c:forEach>
	</tbody>
</table>