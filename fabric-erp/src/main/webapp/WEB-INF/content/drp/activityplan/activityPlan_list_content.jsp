<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//单条删除
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/drp/activityPlanAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 1000);
					pager("start", "${vix}/drp/activityPlanAction!goSingleList.action", 'activityPlan');
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
	loadOrderByImage("${vix}", "activityPlan");
</script>
<input type="hidden" id="activityPlanTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="activityPlanPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="activityPlanTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="activityPlanOrderField" value="${pager.orderField}" />
<input type="hidden" id="activityPlanOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="activityPlanInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th><span style="cursor: pointer;" onclick="orderBy('name');">主题&nbsp;<img id="activityPlanImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('salesOrganization');">销售组织&nbsp;<img id="activityPlanImg_salesOrganization" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('salesDepartment');">销售部门&nbsp;<img id="activityPlanImg_salesDepartment" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('activityBudget');">活动预算&nbsp;<img id="activityPlanImg_activityBudget" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('startTime');">开始时间&nbsp;<img id="activityPlanImg_startTime" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td><a href="#" style="color: gray;">${entity.name }</a></td>
				<td><a href="#" style="color: gray;">${entity.salesOrganization }</a></td>
				<td><a href="#" style="color: gray;">${entity.salesDepartment }</a></td>
				<td><a href="#" style="color: gray;">${entity.activityBudget }</a></td>
				<td><a href="#" style="color: gray;"><s:date format="yyyy-MM-dd" name="%{#entity.startTime}" /></a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" /></a></i> <i><a href="#" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.name}</b>
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>