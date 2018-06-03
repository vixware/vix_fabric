<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
    
function deleteEntity(id){
	asyncbox.confirm('确定要删除数据吗?','提示信息',function(action){
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
</script>
<input type="hidden" id="purchaseTenderTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="purchaseTenderPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="purchaseTenderTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="purchaseTenderOrderField" value="${pager.orderField}" />
<input type="hidden" id="purchaseTenderOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="purchaseTenderInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="50">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#"><s:text name="cmn_other" /></a></li>
							<li><a href="#"><s:text name="cmn_close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="15%">编码</th>
			<th width="20%"><s:text name="pur_theme" /></th>
			<th width="10%"><s:text name="srm_type" /></th>
			<th width="10%">联系人</th>
			<th width="10%">联系电话</th>
			<th width="10%"><s:text name="srm_starttimes" /></th>
			<th width="10%"><s:text name="srm_endtimes" /></th>
			<th width="10%"><s:text name="cmn_operate" /></th>
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
				<td><span style="color: gray;">${entity.tenderCode }</span></td>
				<td><span style="color: gray;">${entity.title }</span></td>
				<td><span style="color: gray;"> <c:if test="${null == entity.type }">
						</c:if> <c:if test="${entity.type == '1' }">
							类型1
						</c:if> <c:if test="${entity.type == '2' }">
							类型2
						</c:if>
				</span></td>
				<td><span style="color: gray;">${entity.contact }</span></td>
				<td><span style="color: gray;">${entity.telephone }</span></td>
				<td><span style="color: gray;"><fmt:formatDate value="${entity.startTime }" type="both" pattern="yyyy-MM-dd" /></span></td>
				<td><span style="color: gray;"><fmt:formatDate value="${entity.endTime }" type="both" pattern="yyyy-MM-dd" /></span></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"></span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#" onclick="deleteEntity('${entity.id}');" title="删除"></a></i> <i><a href="#" title="<s:text name='cmn_show'/>"></a></i> <i> <c:if test="${entity.isParent == '0' }">
										<a href="#" onclick="saveOrUpdate('${entity.id}',$('#parentId').val());" title="<s:text name='cmn_update'/>"></a>
									</c:if> <c:if test="${entity.isParent == '1' }">
										<a href="#" onclick="saveOrUpdate2('${entity.id}',$('#parentId').val());" title="<s:text name='cmn_update'/>"></a>
									</c:if>
							</i> <b>${entity.title}</b>
							</strong>
							<p>${entity.title}</p>
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
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>