<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function deleteEntity(id){
	asyncbox.confirm('确定要删除该分类么?','<s:text name='vix_message'/>',function(action){
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
	loadOrderByImage("${vix}","customerAccountCategory");
</script>
<input type="hidden" id="customerAccountCategoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="customerAccountCategoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="customerAccountCategoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="customerAccountCategoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="customerAccountCategoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="customerAccountCategoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table id="table" class="list">
	<tr>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('id');"> 编号&nbsp; <img id="customerAccountCategoryImg_id" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('code');"> 编码&nbsp;<img id="customerAccountCategoryImg_code" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="180px"><span style="cursor: pointer;" onclick="orderBy('name');"> <s:text name="cmn_name" />&nbsp;<img id="customerAccountCategoryImg_name" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="100px"><span style="cursor: pointer;" onclick="orderBy('memo');"> 备注&nbsp;<img id="customerAccountCategoryImg_memo" src="${vix}/common/img/arrow.gif">
		</span></th>
		<th width="10%">操作</th>
	</tr>
	<s:iterator value="pager.resultList" var="entity" status="s">
		<tr>
			<td><s:if test="#entity.id != null ">
					${s.count + (pager.pageSize * (pager.pageNo -1))}
				</s:if> <s:else>&nbsp;</s:else></td>
			<td>${entity.code}</td>
			<td><span style="cursor: pointer; font-weight: bold;" onclick="saveOrUpdate('${entity.id}');">${entity.name}</span></td>
			<td>${entity.memo}</td>
			<td align="center"><s:if test="#entity.id != null ">
					<a href="#" onclick="saveOrUpdate('${entity.id}');"> <img src="${vix}/common/img/icon_edit.png" alt="修改" />
					</a>
					<a href="#" onclick="deleteEntity('${entity.id}');"> <img src="${vix}/common/img/icon_12.png" alt="删除" />
					</a>
				</s:if></td>
		</tr>
	</s:iterator>
</table>