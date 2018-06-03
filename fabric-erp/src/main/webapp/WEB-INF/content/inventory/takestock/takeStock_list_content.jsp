<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该数据么?','<s:text name='vix_message'/>',function(action){
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

loadOrderByImage("${vix}","takeStock");
</script>
<input type="hidden" id="takeStockTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="takeStockPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="takeStockTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="takeStockOrderField" value="${pager.orderField}" />
<input type="hidden" id="takeStockOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="takeStockInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="takeStockImg_code" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('invWarehouse.name');">仓库&nbsp;<img id="takeStockImg_invWarehouse" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('personcode');">盘点人&nbsp;<img id="takeStockImg_personcode" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('createTime');">时间&nbsp;<img id="takeStockImg_createTime" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('status');">状态&nbsp;<img id="takeStockImg_status" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td><a href="#" style="color: gray;">${entity.code}</a></td>
				<td><a href="#" style="color: gray;">${entity.invWarehouse.name }</a></td>
				<td><a href="#" style="color: gray;">${entity.personcode }</a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd" /></a></td>
				<td><a href="#" style="color: gray;">${entity.status }</a></td>
				<td><a href="#" onclick="exportExcel('${entity.id}');" title="导出"><img src="${vix}/common/img/page_excel.png" /></a> <a href="#" onclick="setValue('${entity.id}');$('#fileToUpload').click();" title="初盘导入"><img src="${vix}/common/img/page_excel.png" /></a> <a href="#" onclick="exportExcel1('${entity.id}');" title="导出盘点对比表"><img
						src="${vix}/common/img/page_excel.png" /></a>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" /></a></i> <i><a href="#" title="查看" onclick="goShowTakeStock('${entity.id}',${pager.pageNo});"><img src="${vix}/common/img/icon_show.gif" /></a> </i> <i><a href="#"
									onclick="saveOrUpdate('${entity.id}',${pager.pageNo});" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.name}</b>
							</strong>
							<p>${entity.memo}</p>
						</div>
					</div></td>
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