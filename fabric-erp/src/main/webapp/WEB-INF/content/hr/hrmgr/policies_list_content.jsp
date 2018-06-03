<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该政策制度管理么?','<s:text name='政策制度管理'/>',function(action){
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

/** 载入数据列排序图标 */
loadOrderByImage("${vix}","policies");
</script>
<input type="hidden" id="policiesTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="policiesPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="policiesTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="policiesOrderField" value="${pager.orderField}" />
<input type="hidden" id="policiesOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="policiesInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="6%">
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
			<th style="cursor: pointer;" onclick="orderBy('code');"><s:text name="编码" />&nbsp;<img id="policiesImg_code" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('polName');"><s:text name="姓名" />&nbsp;<img id="policiesImg_polName" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('fileName');"><s:text name="文件名" />&nbsp;<img id="policiesImg_fileName" src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="drp_type" /></th>
			<th style="cursor: pointer;" onclick="orderBy('createTime');"><s:text name="日期" />&nbsp;<img id="policiesImg_createTime" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('uploadPersonName');"><s:text name="创建人" />&nbsp;<img id="policiesImg_uploadPersonName" src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="cmn_operate" /></th>
			<% int count = 0; %>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.code}</a></td>
					<td><a href="#" style="color: gray;">${entity.polName}</a></td>
					<td><a href="#" style="color: gray;">${entity.fileName}</a></td>
					<td><s:if test="%{#entity.states == 1}">办公用品管理制度</s:if> <s:elseif test="%{#entity.states == 2}">会议管理制度</s:elseif> <s:elseif test="%{#entity.states == 3}">档案管理制度</s:elseif> <s:elseif test="%{#entity.states == 4}">保密制度</s:elseif> <s:elseif test="%{#entity.states == 5}">电脑使用管理规定</s:elseif></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.createTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
					<td><a href="#" style="color: gray;">${entity.uploadPersonName}</a></td>
					<td>
						<div class="untitled" style="position: static; display: inline;">
							<span><img alt="" src="img/icon_untitled.png"> </span>
							<div class="popup" style="display: none; top: -7px;">
								<strong> <i class="close"><a href="#"></a>${entity.fileName}</i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.name}</b>
								</strong>
								<p>
									<a href="${vix}/oa/policiesAction!downloadAttachment.action?id=${entity.id}">下载</a>
								</p>
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
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
	</tbody>
</table>