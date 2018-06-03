<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该区域么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/business/deliveryAreaAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 1000);
					pager("start", "${vix}/business/deliveryAreaAction!goSingleList.action", 'deliveryArea');
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
	loadOrderByImage("${vix}", "deliveryArea");
</script>
<input type="hidden" id="deliveryAreaTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="deliveryAreaPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="deliveryAreaTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="deliveryAreaOrderField" value="${pager.orderField}" />
<input type="hidden" id="deliveryAreaOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="deliveryAreaInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkCategoryIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();">所有 </a></li>
							<li><a href="#">其他 </a></li>
							<li><a href="#">式样</a></li>
							<li><a href="#">关闭</a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('code');">区域编码&nbsp;<img id="deliveryAreaImg_code" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="75%"><span style="cursor: pointer;" onclick="orderBy('name');">区域名称&nbsp;<img id="deliveryAreaImg_name" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td>${entity.code}</td>
				<td>${entity.name}</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>