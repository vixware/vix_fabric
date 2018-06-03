<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该单位么?','<s:text name='vix_message'/>',function(action){
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
<input type="hidden" id="measureUnitTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="measureUnitPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="measureUnitTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="measureUnitOrderField" value="${pager.orderField}" />
<input type="hidden" id="measureUnitOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="measureUnitInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="all" /> </a></li>
							<li><a href="#"><s:text name="other" /> </a></li>
							<li><a href="#"><s:text name="close" /> </a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('code');">编码&nbsp; <s:if test="%{pager.orderField == 'code' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'code' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else></th>
			<th onclick="orderBy('name');">名称&nbsp; <s:if test="%{pager.orderField == 'name' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'name' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else></th>
			<th onclick="orderBy('type');">类型&nbsp; <s:if test="%{pager.orderField == 'type' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'type' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else></th>
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
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td>${entity.measureUnitType.name}</td>
				<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
				</a></td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>