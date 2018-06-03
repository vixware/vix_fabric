<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//单条删除
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/inventory/warehouseAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 1000);
					pager("start", "${vix}/inventory/warehouseAction!goSingleList.action", 'invShelf');
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
	loadOrderByImage("${vix}", "invShelf");
</script>
<input type="hidden" id="invShelfTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="invShelfPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="invShelfTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="invShelfOrderField" value="${pager.orderField}" />
<input type="hidden" id="invShelfOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="invShelfInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="30%"><span style="cursor: pointer;" onclick="orderBy('invWarehouse.name');">仓库名称&nbsp;<img id="invShelfImg_invWarehouse" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="30%"><span style="cursor: pointer;" onclick="orderBy('code');">货位编码&nbsp;<img id="invShelfImg_code" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="30%"><span style="cursor: pointer;" onclick="orderBy('name');">货位名称&nbsp;<img id="invShelfImg_name" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td><a href="#" onclick="updateWarehouse('${entity.invWarehouse.id}');" title="修改仓库">${entity.invWarehouse.name}</a></td>
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" /></a></i> <i><a href="#" title="查看"></a> </i> <i><a href="#" onclick="goSaveOrUpdateCargoSpace('${entity.id}',$('#selectId').val(),0);" title="修改"></a></i> <i><a href="#"
									onclick="deleteEntity('${entity.id}');" title="删除"></i> <b>${entity.id}</b>
							</strong>
							<p>${entity.code}</p>
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
			</tr>
		</c:forEach>
	</tbody>
</table>