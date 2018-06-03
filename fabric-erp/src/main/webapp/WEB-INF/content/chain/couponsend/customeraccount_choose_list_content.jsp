<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function chooseAll(chk) {
		if (null == chk) {
			$("input[name='chkId1']").attr("checked", true);
		} else {
			if ($(chk).attr('checked')) {
				$("input[name='chkId1']").attr("checked", true);
			} else {
				$("input[name='chkId1']").attr("checked", false);
			}
		}
		selectCount();
	}

	function selectCount() {
		var selectCount = 0;
		$.each($("input[name='chkId1']"), function(i, n) {
			if ($(n).attr('checked')) {
				chkChange(n, n.value);
				selectCount++;
			}
		});
		$("#selectCount").html(selectCount);
		if (selectCount == 0) {
			$("input[name='chkIds1']").attr("checked", false);
		} else {
			$("input[name='chkIds1']").attr("checked", true);
		}
	}
</script>
<input type="hidden" id="customerAccountTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerAccountPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerAccountTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerAccountOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerAccountOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerAccountInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="15%"><label><input type="checkbox" name="chkIds1" onchange="chooseAll(this)"> </label></th>
			<th width="40%" onclick="orderBy('name');">姓名</th>
			<th width="45%" onclick="orderBy('code');">等级</th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td><input id="chkId1" name="chkId1" value="${entity.id}" type="checkbox" onchange="chkChange(this,'${entity.id}')" /></td>
				<td>${entity.name}</td>
				<td>${entity.memberLevel.name}</td>
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
	</tbody>
</table>
