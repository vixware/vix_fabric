<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该短信模板么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/crm/business/businessSetAction!deleteMessageTemplateById.action?id=' + id,
				cache : false,
				success : function(result) {
					showMessage(result);
					setTimeout("", 1000);
					loadContent('${vix}/crm/business/businessSetAction!goMessageList.action');
				}
				});
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
	loadOrderByImage("${vix}", "messageTemplate");
</script>
<input type="hidden" id="messageTemplateTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="messageTemplatePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="messageTemplateTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="messageTemplateOrderField" value="${pager.orderField}" />
<input type="hidden" id="messageTemplateOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="messageTemplateInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('name');">短信主题&nbsp;<img id="messageTemplateImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="65%"><span style="cursor: pointer;" onclick="orderBy('messageContent');">内容&nbsp;<img id="messageTemplateImg_messageContent" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><s:text name="cmn_operate" /></th>
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
					<td><a href="#" style="color: gray;">${entity.messageContent }</a></td>
					<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="更新"><img src="${vix}/common/img/icon_edit.png" /></a> <a href="#" onclick="deleteEntity('${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_12.png" />
					</a></td>
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