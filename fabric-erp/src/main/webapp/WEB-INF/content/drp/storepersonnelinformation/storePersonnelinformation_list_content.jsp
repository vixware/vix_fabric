<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
//单条删除
function deleteEntity(id) {
	asyncbox.confirm('确定要删除该数据么?', '提示信息', function(action) {
		if (action == 'ok') {
			$.ajax({
			url : '${vix}/drp/storePersonnelinformationAction!deleteById.action?id=' + id,
			cache : false,
			success : function(html) {
				showMessage(html);
				setTimeout("", 1000);
				pager("start", "${vix}/drp/storePersonnelinformationAction!goSingleList.action", 'distributorEmployee');
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
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","distributorEmployee");
</script>
<input type="hidden" id="distributorEmployeeTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="distributorEmployeePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="distributorEmployeeTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="distributorEmployeeOrderField" value="${pager.orderField}" />
<input type="hidden" id="distributorEmployeeOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="distributorEmployeeInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="distributorEmployeeImg_code" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('name');">姓名&nbsp;<img id="distributorEmployeeImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('telephone');">联系电话&nbsp;<img id="distributorEmployeeImg_telephone" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('entityTime');">入职时间&nbsp;<img id="distributorEmployeeImg_entityTime" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td><a href="#" style="color: gray;">${entity.code }</a></td>
				<td><a href="#" style="color: gray;">${entity.name }</a></td>
				<td><a href="#" style="color: gray;">${entity.telephone }</a></td>
				<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.entityTime }" pattern="yyyy-MM-dd" /></a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" /></a></i><i><a href="#" onclick="saveOrUpdateUserAccount(0,${entity.id});" title="创建账号"><img src="${vix}/common/img/link.png" /></a></i> <i><a href="#"
									onclick="saveOrUpdate('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
								</a></i> <b>${entity.name}</b>
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