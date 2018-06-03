<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该费用信息么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","projectCost");
</script>
<input type="hidden" id="projectCostTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="projectCostPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="projectCostTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="projectCostOrderField" value="${pager.orderField}" />
<input type="hidden" id="projectCostOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="projectCostInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th onclick="orderBy('id');" width="10%"><s:text name="cmn_id" />&nbsp;<img id="projectCostImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('happenDate');" width="15%">发生时间&nbsp;<img id="projectCostImg_happenDate" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('totalAmount');" width="15%">金额&nbsp;<img id="projectCostImg_totalAmount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('billCount');" width="15%">票据张数&nbsp;<img id="projectCostImg_billCount" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('use');" width="20%">用途&nbsp;<img id="projectCostImg_use" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('isApproved');" width="10%">审核&nbsp;<img id="projectCostImg_isApproved" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td><s:property value='formatDateToString(#entity.happenDate)' /></td>
				<td>${entity.totalAmount}</td>
				<td>${entity.billCount}</td>
				<td>${entity.use}</td>
				<td><s:if test="#entity.isApproved ==0">
						未审核
					</s:if> <s:if test="#entity.isApproved ==1">
						已审核
					</s:if></td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>