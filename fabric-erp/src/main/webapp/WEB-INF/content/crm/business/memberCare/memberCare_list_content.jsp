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
loadOrderByImage("${vix}","customerAccount");
</script>
<input type="hidden" id="customerAccountTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerAccountPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerAccountTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerAccountOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerAccountOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerAccountInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('memberSource');">来源网店&nbsp;<img id="customerAccountImg_memberSource" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('name');">姓名&nbsp;<img id="customerAccountImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('mobilePhone');">手机&nbsp;<img id="customerAccountImg_mobilePhone" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="40%"><span style="cursor: pointer;" onclick="orderBy('address');">联系地址&nbsp;<img id="customerAccountImg_address" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%"><s:text name="cmn_operate" /></th>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onclick="chkChange(this,'${entity.id}')" /></td>
					<td><a href="#" style="color: gray;">${entity.memberSource }</a></td>
					<td><a href="#" style="color: gray;">${entity.name }</a></td>
					<td><a href="#" style="color: gray;">${entity.mobilePhone }</a></td>
					<td><a href="#" style="color: gray;">${entity.address }</a></td>
					<td><a href="#" title="发货提醒"><img src="${vix}/common/img/mail_4.png" /></a> <a href="#" title="催付"> <img src="${vix}/common/img/mail_4.png" />
					</a> <a href="#" title="优惠券到期提醒"><img src="${vix}/common/img/mail_4.png" /></a> <a href="#" title="生日关怀"><img src="${vix}/common/img/mail_4.png" /></a> <a href="#" title="日常关怀"><img src="${vix}/common/img/mail_4.png" /></a> <a href="#" title="物流延迟提醒"><img src="${vix}/common/img/mail_4.png" /></a></td>
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