<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function deleteEntity(id){
	asyncbox.confirm('确定要删除该价格条件么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}
$(".untitled").hover(
		function () {
		$(this).css({ "position": "relative"});
		$(this).children('.popup').css({ "display": "block"});
		var bh = $("body").height(); 
		var pt = $(this).offset();
		if(( bh - pt.top) < 165){$(this).children('.popup').css({ "bottom": "0"});}else{$(this).children('.popup').css({ "top": "-7px"});};
	  },
		function () {
		$(this).css({ "position": "static"});
		$(this).children('.popup').css({ "display": "none"});
	  }
	);
	$(".close").click(
		function () {
			$(this).parent().parent().css({ "display": "none"});
		}
	);
	/** 载入数据列排序图标 */
	loadOrderByImage("${vix}","priceCondition");
</script>
<input type="hidden" id="priceConditionTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="priceConditionPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="priceConditionTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="priceConditionOrderField" value="${pager.orderField}" />
<input type="hidden" id="priceConditionOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="priceConditionInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="table" class="list">
	<tr>
		<th width="80px"><span style="cursor: pointer;" onclick="orderBy('code');"> 编码&nbsp;<img id="priceConditionImg_id" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('name');"> 主题&nbsp;<img id="priceConditionImg_name" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="160px"><span style="cursor: pointer;" onclick="orderBy('saleOrg.id');"> 销售组织&nbsp;<img id="priceConditionImg_saleOrg" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="80px"><span style="cursor: pointer;" onclick="orderBy('defaultTax');"> 税率&nbsp;<img id="priceConditionImg_defaultTax" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="140px"><span style="cursor: pointer;" onclick="orderBy('startEffectiveTime');"> 开始有效时间&nbsp;<img id="priceConditionImg_startEffectiveTime" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="140px"><span style="cursor: pointer;" onclick="orderBy('endEffectiveTime');"> 结束有效时间&nbsp;<img id="priceConditionImg_endEffectiveTime" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="10%">操作</th>
	</tr>
	<% int count = 0; %>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<% count++; %>
		<tr>
			<td>${entity.code}</td>
			<td><span style="cursor: pointer; font-weight: bold;" onclick="saveOrUpdate('${entity.id}');">${entity.name}</span></td>
			<td>${entity.saleOrg.fullName}</td>
			<td>${entity.defaultTax}</td>
			<td><s:property value="formatDateToString(#entity.startEffectiveTime)" /></td>
			<td><s:property value="formatDateToString(#entity.endEffectiveTime)" /></td>
			<td align="center"><a href="#" onclick="saveOrUpdate('${entity.id}');"> <img src="${vix}/common/img/icon_edit.png" alt="修改" />
			</a> <a href="#" onclick="deleteEntity('${entity.id}');"> <img src="${vix}/common/img/icon_12.png" alt="删除" />
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
		</tr>
	</c:forEach>
</table>