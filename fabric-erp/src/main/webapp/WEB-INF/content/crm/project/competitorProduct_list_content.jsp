<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function deleteEntity(id){
	asyncbox.confirm('确定要删除该竞争产品么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
/** 载入数据列排序图标 */
loadOrderByImage("${vix}","competitorProduct");
</script>
<input type="hidden" id="competitorProductTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="competitorProductPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="competitorProductTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="competitorProductOrderField" value="${pager.orderField}" />
<input type="hidden" id="competitorProductOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="competitorProductInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
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
			<th onclick="orderBy('id');" width="10%" style="cursor: pointer;"><s:text name="cmn_id" />&nbsp;<img id="competitorProductImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('name');" width="20%" style="cursor: pointer;">产品名称&nbsp;<img id="competitorProductImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('specification');" width="10%" style="cursor: pointer;">产品规格&nbsp;<img id="competitorProductImg_specification" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('price');" width="10%" style="cursor: pointer;">价格&nbsp;<img id="competitorProductImg_price" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('competitor.name');" width="15%" style="cursor: pointer;">竞争对手&nbsp;<img id="competitorProductImg_competitor" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('saleArea');" width="10%" style="cursor: pointer;">销售区域&nbsp;<img id="competitorProductImg_saleArea" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.name}</td>
				<td>${entity.specification}</td>
				<td>${entity.price}</td>
				<td>${entity.competitor.companyName}</td>
				<td>${entity.saleArea}</td>
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