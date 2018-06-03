<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function deleteEntity(id){
	asyncbox.confirm('确定要删除该纪念日么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","memorialDay");
</script>
<input type="hidden" id="memorialDayTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="memorialDayPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="memorialDayTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="memorialDayOrderField" value="${pager.orderField}" />
<input type="hidden" id="memorialDayOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="memorialDayInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="all" /></a></li>
							<li><a href="#"><s:text name="other" /></a></li>
							<li><a href="#"><s:text name="close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" width="10%"><s:text name="cmn_id" />&nbsp;<img id="memorialDayImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('customerAccount.name');" width="15%">客户&nbsp;<img id="memorialDayImg_customerAccount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('contactPerson.name');" width="10%">联系人&nbsp;<img id="memorialDayImg_contactPerson" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('date');" width="15%">纪念日&nbsp;<img id="memorialDayImg_date" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('memorialDayType.name');" width="10%">纪念日类型&nbsp;<img id="memorialDayImg_memorialDayType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('dateType');" width="10%">日期类型&nbsp;<img id="memorialDayImg_dateType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('memo');" width="15%">备注&nbsp;<img id="memorialDayImg_memo" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.customerAccount.name}</td>
				<td>${entity.contactPerson.name}</td>
				<td><s:property value="formatDateToString(#entity.date)" /></td>
				<td>${entity.memorialDayType.name}</td>
				<td><s:if test="#entity.dateType == 0">
						公历
					</s:if> <s:elseif test="#entity.dateType == 1">
						农历
					</s:elseif> <s:else>
						无类型
					</s:else></td>
				<td>${entity.memo}</td>
				<td><a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='update'/>"> <img src="${vix}/common/img/icon_edit.png" />
				</a> <a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='delete'/>"> <img src="${vix}/common/img/icon_12.png" />
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
			</tr>
		</c:forEach>
	</tbody>
</table>