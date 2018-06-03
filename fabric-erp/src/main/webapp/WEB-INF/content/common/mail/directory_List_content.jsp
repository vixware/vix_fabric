<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<input type="hidden" id="directoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="directoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="directoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="directoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="directoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="directoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tr>
		<th width="5%">
			<div class="list_check">
				<div class="drop">
					<label><input name="" type="checkbox" value="" /></label>
					<ul>
						<li><a href="#">所有</a></li>
						<li><a href="#">其他</a></li>
						<li><a href="#">式样</a></li>
						<li><a href="#">关闭</a></li>
					</ul>
				</div>
			</div>
		</th>
		<th width="10%">姓名<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
		<th width="60%">邮箱<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
		<th width="10%">电话<a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
	</tr>
	<%
		int count = 0;
	%>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<%
			count++;
		%>
		<tr>
			<td><input name="" type="checkbox" value="" /></td>
			<td>${entity.name }</td>
			<td>${entity.email }</td>
			<td>${entity.mobileno }</td>
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