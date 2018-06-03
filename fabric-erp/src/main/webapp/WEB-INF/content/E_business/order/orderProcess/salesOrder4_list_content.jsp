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
<input type="hidden" id="salesOrder4TotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="salesOrder4PageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="salesOrder4TotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="salesOrder4OrderField" value="${pager.orderField}" />
<input type="hidden" id="salesOrder4OrderBy" value="${pager.orderBy}" />
<input type="hidden" id="salesOrder4InfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th onclick="orderBy('id');" width="10%">订单编码&nbsp; <s:if test="%{pager.orderField == 'id' && pager.orderBy == 'asc' }">
					<img src="${vix}/common/img/arrow_down.gif">
				</s:if> <s:elseif test="%{pager.orderField == 'id' && pager.orderBy == 'desc' }">
					<img src="${vix}/common/img/arrow_up.gif">
				</s:elseif> <s:else>
					<img src="${vix}/common/img/arrow.gif">
				</s:else></th>
			<th>买家昵称</th>
			<th>来源网店</th>
			<th>联系电话</th>
			<th>买家留言</th>
			<th>订单状态</th>
			<th>日期</th>
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
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onclick="chkChange(this,'${entity.id}')" /></td>
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;">${entity.outerId }</a></td>
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;">${entity.buyerNick }</a></td>
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;">${entity.channelDistributor.name }</a></td>
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;">${entity.receiverMobile }</a></td>
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;">${entity.buyerMessage }</a></td>
				<%-- <td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;"><span style="color: red;"><s:if test="%{#entity.dealState==1}">未处理</s:if></span> <span style="color: green;"><s:elseif
									test="%{#entity.dealState==2}">已分拣</s:elseif> <s:elseif test="%{#entity.dealState==3}">待发货</s:elseif> <s:elseif test="%{#entity.dealState==4}">已发货</s:elseif> <s:elseif
									test="%{#entity.dealState==5}">完成</s:elseif> <s:elseif test="%{#entity.dealState==7}">已分仓</s:elseif></span></a></td> --%>
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;"><span style="color: red;"><s:if test="%{#entity.status==5}">待出库</s:if> </span> <s:elseif test="%{#entity.status==6}">已发货</s:elseif> <s:elseif test="%{#entity.status==7}">已关闭</s:elseif></a></td>
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;"><fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd" /></a></td>
				<td>
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" onclick="pophtml('${entity.id}');" title="<s:text name='cmn_show'/>"></a> </i> <i><a href="#" title="<s:text name='cmn_update'/>"></a> </i> <b>${entity.name}</b>
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