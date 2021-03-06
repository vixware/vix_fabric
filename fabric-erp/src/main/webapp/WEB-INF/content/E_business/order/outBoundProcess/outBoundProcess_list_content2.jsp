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
			businussOrderId = businussOrderId + "," + $(n).val();
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
<input type="hidden" id="order2TotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="order2PageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="order2TotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="order2OrderField" value="${pager.orderField}" />
<input type="hidden" id="order2OrderBy" value="${pager.orderBy}" />
<input type="hidden" id="order2InfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="20%">批次号</th>
			<th width="25%">操作人</th>
			<th width="20%">操作日期</th>
			<th width="20%">状态</th>
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
				<td><a href="#" style="color: gray;" onclick="showOrders('${entity.id}');">${entity.code}</a></td>
				<td><a href="#" style="color: gray;" onclick="showOrders('${entity.id}');">${entity.creator}</a></td>
				<td><a href="#" style="color: gray;" onclick="showOrders('${entity.id}');"><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></a></td>
				<td><a href="#" style="color: gray;" onclick="showOrders('${entity.id}');"><s:if test="%{#entity.status==1}">待打单</s:if> <s:elseif test="%{#entity.status==2}">待配货</s:elseif> <s:elseif test="%{#entity.status==3}">待发货</s:elseif></a></td>
				<td><a href="#" title="打印拣货单" onclick="goPrintPickingList('${entity.id}');"><img src="${vix}/common/img/print_picking.png" /></a> <a href="#" onclick="goPrintDelivery('${entity.id}');" title="打印发货单"> <img src="${vix}/common/img/print_delivery.png" />
				</a> <a href="#" title="打印快递单" onclick="bahtPreviewPrintExpressList('${entity.id}');"><img src="${vix}/common/img/print_express.png" /></a> <a href="#" title="填写快递单号" onclick="updateLogisticsNumbers('${entity.id}');"><img src="${vix}/common/img/win_btn_01.png" /></a> <%-- <a href="#" title="打单完成" onclick="updateOrderBatchState('${entity.id}');"><img
						src="${vix}/common/img/finish.png" /></a> --%> <%-- <a href="#" title="发货" onclick="sendOut('${entity.id}');"><img src="${vix}/common/img/finish.png" /></a> --%> <a href="#" title="电商发货" onclick="sendOrderToBusiness('${entity.id}');"><img src="${vix}/common/img/finish.png" /></a></td>
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