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
<input type="hidden" id="salesOrderTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="salesOrderPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="salesOrderTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="salesOrderOrderField" value="${pager.orderField}" />
<input type="hidden" id="salesOrderOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="salesOrderInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /> </a></li>
							<li><a href="#" onclick="chooseOther();"><s:text name="cmn_other" /> </a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="orderImg_code" src="${vix}/common/img/arrow.gif">
			</span></th>
			<th width="20%"><span style="cursor: pointer;" onclick="orderBy('title');">主题&nbsp;<img id="orderImg_title" src="${vix}/common/img/arrow.gif">
			</span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('customerAccount.name');">客户&nbsp;<img id="orderImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('status');">状态&nbsp;<img id="orderImg_status" src="${vix}/common/img/arrow.gif">
			</span></th>
			<th width="8%"><span style="cursor: pointer;" onclick="orderBy('amountTotal');">金额&nbsp;<img id="orderImg_amountTotal" src="${vix}/common/img/arrow.gif">
			</span></th>
			<th width="9%"><span style="cursor: pointer;" onclick="orderBy('salePerson.name');">业务员&nbsp;<img id="orderImg_salePerson" src="${vix}/common/img/arrow.gif">
			</span></th>
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('billDate');">日期&nbsp;<img id="orderImg_billDate" src="${vix}/common/img/arrow.gif">
			</span></th>
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
				<td>${entity.title}</td>
				<td>${entity.customerAccount.name}</td>
				<td><s:if test="#entity.status == 0">
						禁用
					</s:if> <s:elseif test="#entity.status == 1">
						激活
					</s:elseif></td>
				<td>${entity.amountTotal}</td>
				<td>${entity.salePerson.name}</td>
				<td><s:property value="formatDateToString(#entity.billDate)" /></td>
				<td><s:if test="tag != 'choose'">
						<div class="untitled" style="position: static; display: inline;">
							<span><img alt="" src="img/icon_untitled.png"> </span>
							<div class="popup" style="display: none; top: -7px;">
								<strong> <i class="close"><a href="#"></a> </i> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"><img src="${vix}/common/img/icon_12.png" /> </a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"><img src="${vix}/common/img/icon_edit.png" /> </a>
								</i> <b>${entity.code}</b>
								</strong>
								<p>${entity.customerAccount.name}</p>
							</div>
						</div>
					</s:if></td>
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