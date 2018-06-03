<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function deleteEntity(id){
	asyncbox.confirm('确定要删除该规格么?','<s:text name='vix_message'/>',function(action){
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
	loadOrderByImage("${vix}","specification");
</script>
<input type="hidden" id="specificationTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="specificationPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="specificationTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="specificationOrderField" value="${pager.orderField}" />
<input type="hidden" id="specificationOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="specificationInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="table" class="list">
	<tr>
		<th width="80px" style="cursor: pointer;" onclick="orderBy('id');">编号&nbsp;<img id="specificationImg_id" src="${vix}/common/img/arrow.gif">
		</th>
		<th width="180px" style="cursor: pointer;" onclick="orderBy('code');">编码&nbsp;<img id="specificationImg_code" src="${vix}/common/img/arrow.gif">
		</th>
		<th width="180px" style="cursor: pointer;" onclick="orderBy('name');">名称&nbsp;<img id="specificationImg_name" src="${vix}/common/img/arrow.gif">
		</th>
		<th width="140px" style="cursor: pointer;" onclick="orderBy('productCategory.name');">分类&nbsp;<img id="specificationImg_productCategory" src="${vix}/common/img/arrow.gif">
		</th>
		<th width="180px" style="cursor: pointer;" onclick="orderBy('specificationTableName');">规格表&nbsp;<img id="specificationImg_specificationTableName" src="${vix}/common/img/arrow.gif">
		</th>
		<th width="85px" style="cursor: pointer;" onclick="orderBy('orderBy');">顺序&nbsp;<img id="specificationImg_orderBy" src="${vix}/common/img/arrow.gif">
		</th>
		<th style="cursor: pointer;" onclick="orderBy('memo');">备注&nbsp;<img id="specificationImg_memo" src="${vix}/common/img/arrow.gif">
		</th>
		<th width="10%">操作</th>
	</tr>
	<% int count = 0; %>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<% count++; %>
		<tr>
			<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
			<td>${entity.code}</td>
			<td><span style="cursor: pointer; font-weight: bold;" onclick="saveOrUpdate('${entity.id}');">${entity.name}</span></td>
			<td>${entity.objectExpand.name}</td>
			<td>${entity.specificationTableName}</td>
			<td>${entity.orderBy}</td>
			<td>${entity.memo}</td>
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
			<td>&nbsp;</td>
		</tr>
	</c:forEach>
</table>