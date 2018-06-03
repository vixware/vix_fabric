<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//单条删除
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/drp/storesFeedbackAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 1000);
					pager("start", "${vix}/drp/storesFeedbackAction!goSingleList.action", 'policyInformation');
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
	loadOrderByImage("${vix}", "policyInformation");
</script>
<input type="hidden" id="policyInformationTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="policyInformationPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="policyInformationTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="policyInformationOrderField" value="${pager.orderField}" />
<input type="hidden" id="policyInformationOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="policyInformationInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th><span style="cursor: pointer;" onclick="orderBy('name');"><s:text name="drp_theme" />&nbsp;<img id="policyInformationImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('type');"><s:text name="drp_type" />&nbsp;<img id="policyInformationImg_type" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('priority');"><s:text name="drp_priority" />&nbsp;<img id="policyInformationImg_priority" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('createTime');"><s:text name="drp_release_time" />&nbsp;<img id="policyInformationImg_createTime" src="${vix}/common/img/arrow.gif"></span></th>
			<th><s:text name="cmn_operate" /></th>
		</tr>
		<%
			int count = 0;
		%>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<%
				count++;
			%>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><a href="#" style="color: gray;">${entity.name}</a></td>
				<td><a href="#" style="color: gray;"><s:if test="%{#entity.type==1}">惠民</s:if> <s:elseif test="%{#entity.type==2}">民生</s:elseif></a></td>
				<td><a href="#" style="color: gray;">${entity.priority}</a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd" /></a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" /></a></i><i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="反馈"><img src="${vix}/common/img/icon_edit.png" /></a> </i> <b>${entity.code}</b>
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>