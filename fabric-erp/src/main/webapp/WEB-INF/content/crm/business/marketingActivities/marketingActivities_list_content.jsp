<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该品牌么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
function chooseAll(chk){
	if(null == chk){
		$("input[name='chkId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkId']").attr("checked", true);
		}else{
			$("input[name='chkId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCount1").html(selectCount);
	$("#selectCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkIds']").attr("checked", false);
	}else{
		$("input[name='chkIds']").attr("checked", true);
	}
}
loadOrderByImage("${vix}","marketingAutomationProcess");
</script>
<input type="hidden" id="marketingAutomationProcessTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="marketingAutomationProcessPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="marketingAutomationProcessTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="marketingAutomationProcessOrderField" value="${pager.orderField}" />
<input type="hidden" id="marketingAutomationProcessOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="marketingAutomationProcessInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th><span style="cursor: pointer;" onclick="orderBy('name');">活动主题&nbsp;<img id="marketingAutomationProcessImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('startTime');">开始日期&nbsp;<img id="marketingAutomationProcessImg_startTime" src="${vix}/common/img/arrow.gif"></span></th>
			<th><span style="cursor: pointer;" onclick="orderBy('endTime');">结束日期&nbsp;<img id="marketingAutomationProcessImg_endTime" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('status');">状态&nbsp;<img id="marketingAutomationProcessImg_status" src="${vix}/common/img/arrow.gif"></span></th>
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
					<td><a href="#" style="color: gray;">${entity.startTime }</a></td>
					<td><a href="#" style="color: gray;">${entity.endTime }</a></td>
					<td><a href="#" style="color: gray;">${entity.status }</a></td>
					<td><a href="#" onclick="goStart('${entity.id}');" title="<s:text name='启动'/>"><img src="${vix}/common/img/wfstart.png" /></a> <a href="#" onclick="goStop('${entity.id}');" title="<s:text name='停止'/>"><img src="${vix}/common/img/wfstop.png" /></a> <a href="#" onclick="saveOrUpdate('${entity.id}',${pager.pageNo});" title="查看"><img
							src="${vix}/common/img/icon_edit.png" /></a></td>
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