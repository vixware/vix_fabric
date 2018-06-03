<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

/** 载入数据列排序图标 */
loadOrderByImage("${vix}","incoming");
</script>
<input type="hidden" id="incomingTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="incomingPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="incomingTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="incomingOrderField" value="${pager.orderField}" />
<input type="hidden" id="incomingOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="incomingInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
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
			<th style="cursor: pointer;" onclick="orderBy('receiptNumber');">单据编号&nbsp;<img id="incomingImg_receiptNumber" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('itemName');">${vv:varView("vix_mdm_item")}名称&nbsp;<img id="incomingImg_itemName" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('itemCode');">${vv:varView("vix_mdm_item")}编码&nbsp;<img id="incomingImg_itemCode" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="抽样标准" /></th>
			<th style="cursor: pointer;" onclick="orderBy('inspectionDate');">报检日期&nbsp;<img id="incomingImg_inspectionDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="报检人" /></th>
			<th style="cursor: pointer;" onclick="orderBy('participant');">检验方法&nbsp;<img id="incomingImg_participant" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="样品等级" /></th>
			<th><s:text name="cmn_operate" /></th>
			<% int count = 0; %>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.receiptNumber}</a></td>
					<td><a href="#" style="color: gray;">${entity.itemName}</a></td>
					<td><a href="#" style="color: gray;">${entity.itemCode}</a></td>
					<td><a href="#" style="color: gray;">${entity.testMethods.criterion}</a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.inspectionDate}" type="both" pattern="yyyy-MM-dd" /></a></td>
					<td><a href="#" style="color: gray;">${entity.participant}</a></td>
					<td><a href="#" style="color: gray;">${entity.testMethods.name}</a></td>
					<td><a href="#" style="color: gray;">${entity.inspectionCharacteristics.name}</a></td>
					<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='修改'/>"> <img src="${vix}/common/img/icon_edit.png" />
					</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='删除'/>"> <img src="${vix}/common/img/icon_12.png" />
					</a></td>
				</tr>
			</s:iterator>
			<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
			com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
			count = pager.getPageSize() - count;
			request.setAttribute("count",count);
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
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
	</tbody>
</table>