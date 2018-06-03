<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该${vv:varView('vix_mdm_item')}组么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
$(".close").click(
	function () {
		$(this).parent().parent().css({ "display": "none"});
	}
);
</script>
<input type="hidden" id="itemGroupTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="itemGroupPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="itemGroupTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="itemGroupOrderField" value="${pager.orderField}" />
<input type="hidden" id="itemGroupOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="itemGroupInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="10%"><span style="cursor: pointer;" onclick="orderBy('id');">编号&nbsp;<img id="itemGroupImg_id" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="15%"><span style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="itemGroupImg_code" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="25%"><span style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img id="itemGroupImg_name" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="40%"><span style="cursor: pointer;" onclick="orderBy('memo');">备注&nbsp;<img id="itemGroupImg_memo" src="${vix}/common/img/arrow.gif"></span></th>
			<th width="10%">操作</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.code}</td>
				<td>${entity.name}</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>