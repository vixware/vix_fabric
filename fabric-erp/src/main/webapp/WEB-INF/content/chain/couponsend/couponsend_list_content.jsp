<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该优惠券么?','提示信息',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/chain/couponManagementAction!deleteCouponById.action?id='+id,
				  cache: false,
				  success: function(result){
						showMessage(result);
						setTimeout("", 1000);
						loadContent('${vix}/chain/couponManagementAction!goList.action');
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

</script>
<input type="hidden" id="couponTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="couponPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="couponTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="couponOrderField" value="${pager.orderField}" />
<input type="hidden" id="couponOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="couponInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th>优惠券名称</th>
			<th>优惠券面额</th>
			<th>优惠券类别</th>
			<th>使用限制</th>
			<th>生效日期</th>
			<th>截止日期</th>
			<th>数量</th>
			<th width="10%">状态</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
			<%
				int count = 0;
			%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<%
					count++;
				%>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><a href="#" style="color: gray;">${entity.name }</a></td>
					<td><a href="#" style="color: gray;">${entity.amount }</a></td>
					<td><a href="#" style="color: gray;">${entity.type }</a></td>
					<td><a href="#" style="color: gray;">${entity.userule }</a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.effectiveDate }" pattern="yyyy-MM-dd" /></a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.cutOffDate }" pattern="yyyy-MM-dd" /></a></td>
					<td><a href="#" style="color: gray;">${entity.quantity }</a></td>
					<td><a href="#" style="color: gray;"><s:if test="%{#entity.status==1}">未发放</s:if> </span> <s:elseif test="%{#entity.status==2}">已发放</s:elseif></a></td>
					<td><a href="#" onclick="chooseCustomerAccount('${entity.id}');" title="发放到会员"><img src="${vix}/common/img/icon_edit.png" /></a></td>
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
				</tr>
			</c:forEach>
	</tbody>
</table>