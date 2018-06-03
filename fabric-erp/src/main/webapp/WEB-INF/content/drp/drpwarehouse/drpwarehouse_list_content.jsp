<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
 
//单条删除
function deleteEntity(id) {
	asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
		if (action == 'ok') {
			$.ajax({
			url : '${vix}/drp/drpWarehouseAction!deleteById.action?id=' + id,
			cache : false,
			success : function(html) {
				showMessage(html);
				setTimeout("", 1000);
				pager("start", "${vix}/drp/drpWarehouseAction!goSingleList.action", 'invWarehouse');
			}
			});
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
loadOrderByImage("${vix}","invWarehouse");
</script>
<input type="hidden" id="invWarehouseTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="invWarehousePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="invWarehouseTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="invWarehouseOrderField" value="${pager.orderField}" />
<input type="hidden" id="invWarehouseOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="invWarehouseInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('code');">仓库编码&nbsp;<img id="invWarehouseImg_code" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('name');">仓库名称&nbsp;<img id="invWarehouseImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('postion');">仓库地址&nbsp;<img id="invWarehouseImg_postion" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('createTime');">创建时间&nbsp;<img id="invWarehouseImg_createTime" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td><a href="#" style="color: gray;">${entity.code }</a></td>
				<td><a href="#" style="color: gray;">${entity.name }</a></td>
				<td><a href="#" style="color: gray;">${entity.postion }</a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd HH:MM:SS" /></a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" /></a></i> <i><a href="#" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}',${pager.pageNo});" title="<s:text name='cmn_update'/>"></a> </i>
								<b>${entity.name}</b>
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
			</tr>
		</c:forEach>
	</tbody>
</table>