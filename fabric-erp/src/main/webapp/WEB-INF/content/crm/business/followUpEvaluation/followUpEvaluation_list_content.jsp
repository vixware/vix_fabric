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
	loadOrderByImage("${vix}","order");
</script>
<input type="hidden" id="orderTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="orderPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="orderTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="orderOrderField" value="${pager.orderField}" />
<input type="hidden" id="orderOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="orderInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tr>
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
		<th><span style="cursor: pointer;" onclick="orderBy('code');">评价内容&nbsp;<img id="orderImg_code" src="${vix}/common/img/arrow.gif"></span></th>
		<th><span style="cursor: pointer;" onclick="orderBy('code');">回复内容&nbsp;<img id="orderImg_code" src="${vix}/common/img/arrow.gif"></span></th>
		<th><span style="cursor: pointer;" onclick="orderBy('code');">订单编码&nbsp;<img id="orderImg_code" src="${vix}/common/img/arrow.gif"></span></th>
	</tr>
	<%
		int count = 0;
	%>
	<s:iterator value="pager.resultList" var="entity" status="s" id="invoiceId">
		<%
			count++;
		%>
		<tr>
			<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
			<td>${entity.id }</td>
			<td>${entity.id }</td>
			<td>${entity.id }</td>
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
		</tr>
	</c:forEach>
</table>