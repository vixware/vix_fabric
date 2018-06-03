<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//单条删除
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/drp/marketingExecutiveAction!deleteById.action?id=' + id,
				cache : false,
				success : function(html) {
					showMessage(html);
					setTimeout("", 1000);
					pager("start", "${vix}/drp/marketingExecutiveAction!goSingleList.action", 'marketingCampaign');
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
	loadOrderByImage("${vix}", "marketingCampaign");
</script>
<input type="hidden" id="marketingCampaignTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="marketingCampaignPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="marketingCampaignTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="marketingCampaignOrderField" value="${pager.orderField}" />
<input type="hidden" id="marketingCampaignOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="marketingCampaignInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="30%"><span style="cursor: pointer;" onclick="orderBy('marketingCampaign.name');">活动主题&nbsp;<img id="marketingCampaignImg_marketingCampaign" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('feedbackContent');">反馈内容&nbsp;<img id="marketingCampaignImg_feedbackContent" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('creator');">反馈人&nbsp;<img id="marketingCampaignImg_creator" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('status');">状态&nbsp;<img id="marketingCampaignImg_status" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('createTime');">完成时间&nbsp;<img id="marketingCampaignImg_createTime" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="5%"><s:text name="cmn_operate" /></th>
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
				<td><a href="#" style="color: gray;">${entity.marketingCampaign.name }</a></td>
				<td><a href="#" style="color: gray;">${entity.feedbackContent }</a></td>
				<td><a href="#" style="color: gray;">${entity.creator }</a></td>
				<td><a href="#" style="color: gray;"><s:if test="%{#entity.status==1}">未反馈</s:if> <s:elseif test="%{#entity.status==2}">已反馈</s:elseif> </a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd" /></a></td>
				<td><i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" /></a></i><a href="#" title="反馈" onclick="marketingCampaignTask('${entity.id}');"><img src="${vix}/common/img/icon_show.png" /></a></td>
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