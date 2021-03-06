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
	loadOrderByImage("${vix}", "membershipSubdivision");
</script>
<input type="hidden" id="membershipSubdivisionTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="membershipSubdivisionPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="membershipSubdivisionTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="membershipSubdivisionOrderField" value="${pager.orderField}" />
<input type="hidden" id="membershipSubdivisionOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="membershipSubdivisionInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="30%"><span style="cursor: pointer;" onclick="orderBy('name');">主题&nbsp;<img id="membershipSubdivisionImg_code" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('creator');">业务人员&nbsp;<img id="membershipSubdivisionImg_creator" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('startTime');">开始时间&nbsp;<img id="membershipSubdivisionImg_startTime" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('endTime');">结束时间&nbsp;<img id="membershipSubdivisionImg_endTime" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><s:text name="cmn_operate" /></th>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.name }</a></td>
					<td><a href="#" style="color: gray;">${entity.creator }</a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.startTime}" pattern="yyyy-MM-dd" /></a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.endTime}" pattern="yyyy-MM-dd" /></a></td>
					<td><a href="#" onclick="goCustomerList('${entity.id}','${entity.name }');" title="会员列表"><img src="${vix}/common/img/customerService_18x18.png" /></a> <a href="#" onclick="saveOrUpdate('${entity.id}');" title="修改"><img src="${vix}/common/img/icon_edit.png" /></a></td>
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
				</tr>
			</c:forEach>
	</tbody>
</table>