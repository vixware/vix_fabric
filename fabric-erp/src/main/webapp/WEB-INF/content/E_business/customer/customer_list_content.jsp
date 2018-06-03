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
loadOrderByImage("${vix}","customer");
</script>
<input type="hidden" id="customerTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('customerName');">会员名称&nbsp;<img id="customerImg_customerName" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('mobile');">联系电话&nbsp;<img id="customerImg_mobile" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="45%"><span style="cursor: pointer;" onclick="orderBy('addr');">联系地址&nbsp;<img id="customerImg_addr" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('channelDistributor.name');">来源网店&nbsp;<img id="customerImg_channelDistributor" src="${vix}/common/img/arrow.gif"></span></th>
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
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;">${entity.customerName }</a></td>
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;">${entity.mobile }</a></td>
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;">${entity.addr }</a></td>
				<td><a href="#" onclick="pophtml('${entity.id}');" style="color: gray;">${entity.channelDistributor.name }</a></td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>