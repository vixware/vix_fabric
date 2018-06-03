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
<input type="hidden" id="orderBatchTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="orderBatchPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="orderBatchTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="orderBatchOrderField" value="${pager.orderField}" />
<input type="hidden" id="orderBatchOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="orderBatchInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="5%">买家昵称</th>
			<th width="10%">网店</th>
			<th width="25%">地址</th>
			<th width="5%">联系电话</th>
			<th width="20%">买家留言</th>
			<th width="5%">付款方式</th>
			<th width="5%">订单状态</th>
			<th width="5%">称重状态</th>
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
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onclick="chkChange(this,${entity.id})" /></td>
				<td><a href="#" onclick="goWeighing('${entity.id}');" style="color: gray;">${entity.outerId }</a></td>
				<td><a href="#" onclick="goWeighing('${entity.id}');" style="color: gray;">${entity.buyerNick }</a></td>
				<td><a href="#" onclick="goWeighing('${entity.id}');" style="color: gray;">${entity.channelDistributor.name }</a></td>
				<td><a href="#" onclick="goWeighing('${entity.id}');" style="color: gray;">${entity.receiverAddress }</a></td>
				<td><a href="#" onclick="goWeighing('${entity.id}');" style="color: gray;">${entity.receiverMobile }</a></td>
				<td><a href="#" onclick="goWeighing('${entity.id}');" style="color: gray;">${entity.buyerMessage }</a></td>
				<td><a href="#" onclick="goWeighing('${entity.id}');" style="color: gray;">${entity.payTypeCn }</a></td>
				<td><a href="#" onclick="goWeighing('${entity.id}');" style="color: gray;"><span style="color: red;"><s:if test="%{#entity.status==5}">待出库</s:if> </span> <span style="color: green;"><s:elseif test="%{#entity.status==6}">已发货</s:elseif></span> <span style="color: yellow;"><s:elseif test="%{#entity.status==7}">完成</s:elseif></span></a></td>
				<td><a href="#" onclick="goWeighing('${entity.id}');" style="color: gray;"><span style="color: red;"><s:if test="%{#entity.isWeight==0}">未称重</s:if> </span> <span style="color: green;"><s:elseif test="%{#entity.isWeight==1}">已称重</s:elseif></span> </a></td>
				<td><a href="#" onclick="goWeighing('${entity.id}');" title="称重"><img src="${vix}/common/img/weight_18x18.png" /></a></td>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>