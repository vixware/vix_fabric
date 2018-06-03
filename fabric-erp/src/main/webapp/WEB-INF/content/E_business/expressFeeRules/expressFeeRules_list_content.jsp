<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该规则么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/business/expressFeeRulesAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 1000);
					pager("start", "${vix}/business/expressFeeRulesAction!goSingleList.action", 'expressFeeRules');
				}
				});
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
	loadOrderByImage("${vix}", "expressFeeRules");
</script>
<input type="hidden" id="expressFeeRulesTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="expressFeeRulesPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="expressFeeRulesTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="expressFeeRulesOrderField" value="${pager.orderField}" />
<input type="hidden" id="expressFeeRulesOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="expressFeeRulesInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkCategoryIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name='all' /> </a></li>
							<li><a href="#"><s:text name='other' /> </a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('logistics.name');">快递公司&nbsp;<img id="expressFeeRulesImg_logistics" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('deliveryArea.name');">区域&nbsp;<img id="expressFeeRulesImg_deliveryArea" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('firstWeight');">首重(费用)&nbsp;<img id="expressFeeRulesImg_firstWeight" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('perWeight');">续重单位量(费用)&nbsp;<img id="expressFeeRulesImg_perWeight" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%">操作</th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td><input id="chkCategoryId" name="chkCategoryId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${entity.logistics.name}</td>
				<td>${entity.deliveryArea.name}</td>
				<td>${entity.firstWeight}<span style="color: red;">(${entity.firstCost})</span></td>
				<td>${entity.perWeight}<span style="color: red;">(${entity.perCost})</span></td>
				<td><a href="#" onclick="goSaveOrUpdate('${entity.id}');" title="修改"><img src="${vix}/common/img/icon_edit.png" /></a> <a href="#" onclick="deleteEntity('${entity.id}');" title="删除"><img src="${vix}/common/img/icon_delete.png" /></a></td>
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