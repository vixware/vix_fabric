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

</script>
<input type="hidden" id="brandTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="brandPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="brandTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="brandOrderField" value="${pager.orderField}" />
<input type="hidden" id="brandOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="brandInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th>车辆编号</th>
			<th><s:text name="ldm_name" /></th>
			<th><s:text name="ldm_type" /></th>
			<th><s:text name="ldm_license_plate_number" /></th>
			<th><s:text name="ldm_operation_of" /></th>
			<th><s:text name="ldm_state" /></th>
			<th><s:text name="ldm_description" /></th>
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
				<td><a href="#" style="color: gray;">${entity.vehicleCode }</a></td>
				<td><a href="#" style="color: gray;">${entity.name }</a></td>
				<td><a href="#" style="color: gray;">${entity.type }</a></td>
				<td><a href="#" style="color: gray;">${entity.vehicleNO }</a></td>
				<td><a href="#" style="color: gray;">${entity.useYear }</a></td>
				<td><a href="#" style="color: gray;">${entity.vehicleStauts }</a></td>
				<td><a href="#" style="color: gray;">${entity.description }</a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.name}</b>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>