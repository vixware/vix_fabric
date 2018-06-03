<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该文件么?','<s:text name='公共文件'/>',function(action){
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
loadOrderByImage("${vix}","publicCabinet");
</script>
<input type="hidden" id="publicCabinetTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="publicCabinetPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="publicCabinetTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="publicCabinetOrderField" value="${pager.orderField}" />
<input type="hidden" id="publicCabinetOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="publicCabinetInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th style="cursor: pointer;" onclick="orderBy('code');"><s:text name="oa_sort_no" />&nbsp;<img id="publicCabinetImg_code" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('keyword');"><s:text name="oa_name" />&nbsp;<img id="publicCabinetImg_keyword" src="${vix}/common/img/arrow.gif"></th>
			<th style="cursor: pointer;" onclick="orderBy('fileName');"><s:text name="oa_file_name" />&nbsp;<img id="publicCabinetImg_fileName" src="${vix}/common/img/arrow.gif"></th>
			<th><s:text name="drp_type" /></th>
			<th><s:text name="日期" /></th>
			<th><s:text name="cmn_operate" /></th>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.code}</a></td>
					<td><a href="#" style="color: gray;">${entity.keyword}</a></td>
					<td><a href="#" style="color: gray;">${entity.fileName}</a></td>
					<td><s:if test="%{#entity.type == 1}">技术类</s:if> <s:elseif test="%{#entity.type == 2}">资料类</s:elseif></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></a></td>
					<td>
						<div class="untitled" style="position: static; display: inline;">
							<span><img alt="" src="img/icon_untitled.png"> </span>
							<div class="popup" style="display: none; top: -7px;">
								<strong> <i class="close"><a href="#"></a> ${entity.fileName}</i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.name}</b>
								</strong>
								<p>
									<a href="${vix}/oa/publicCabinetAction!downloadAttachment.action?id=${entity.id}">下载</a>
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
				</tr>
			</c:forEach>
	</tbody>
</table>