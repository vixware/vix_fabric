<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该分类么?', '提示信息', function(action) {
			if (action == 'ok') {
				deleteById(id);
			}
		});
	}

	function chooseAll(chk) {
		if (null == chk) {
			$("input[name='chkCategoryId']").attr("checked", true);
		} else {
			if ($(chk).attr('checked')) {
				$("input[name='chkCategoryId']").attr("checked", true);
			} else {
				$("input[name='chkCategoryId']").attr("checked", false);
			}
		}
		selectCount();
	}

	function selectCount() {
		var selectCount = 0;
		$.each($("input[name='chkCategoryId']"), function(i, n) {
			if ($(n).attr('checked')) {
				selectCount++;
			}
		});
		$("#selectCategoryCount1").html(selectCount);
		$("#selectCategoryCount2").html(selectCount);
		if (selectCount == 0) {
			$("input[name='chkCategoryIds']").attr("checked", false);
		} else {
			$("input[name='chkCategoryIds']").attr("checked", true);
		}
	}
</script>
<input type="hidden" id="purchasePlanTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="purchasePlanPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="purchasePlanTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="purchasePlanOrderField" value="${pager.orderField}" />
<input type="hidden" id="purchasePlanOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="purchasePlanInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkCategoryIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();">所有</a></li>
							<li><a href="#">其他</a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="20%">上报日期</th>
			<th width="15%">上报人</th>
			<th width="10%">上报类型</th>
			<th width="10%">审批状态</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="chkChange(this,'${entity.id}')" /></td>
				<td><a href="#" style="color: gray;" onclick="showPurchasePlanDetail('${entity.id}');">${entity.packageDate }</a></td>
				<td><a href="#" style="color: gray;" onclick="showPurchasePlanDetail('${entity.id}');">${entity.planMaker }</a></td>
				<td><a href="#" style="color: gray;" onclick="showPurchasePlanDetail('${entity.id}');"><c:if test="${null == entity.packageType }">
						</c:if> <c:if test="${entity.packageType == '1' }">
							类型1
						</c:if> <c:if test="${entity.packageType == '2' }">
							类型2
						</c:if></a></td>
				<td><a href="#" style="color: gray;" onclick="showPurchasePlanDetail('${entity.id}');">${entity.approvalStatus }</a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close" onclick="deleteEntity('${entity.id}');"><a href="#"></a></i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_show'/>"></a></i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"></a></i> <b>${entity.purchasePlanName}</b>
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
			</tr>
		</c:forEach>
	</tbody>
</table>