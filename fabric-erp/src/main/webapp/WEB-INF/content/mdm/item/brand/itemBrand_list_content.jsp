<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
 
function deleteEntity(id){
	asyncbox.confirm('确定要删除该${vv:varView('vix_mdm_item')}品牌么?','<s:text name='vix_message'/>',function(action){
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
loadOrderByImage("${vix}","itemBrand");
</script>
<input type="hidden" id="itemBrandTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="itemBrandPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="itemBrandTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="itemBrandOrderField" value="${pager.orderField}" />
<input type="hidden" id="itemBrandOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="itemBrandInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th onclick="orderBy('id');" width="8%"><s:text name="cmn_id" />&nbsp;<img id="itemBrandImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('code');" width="8%">编码&nbsp;<img id="itemBrandImg_code" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('name');" width="12%">名称&nbsp;<img id="itemBrandImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('brandCompany');" width="10%">公司名称&nbsp;<img id="itemBrandImg_brandCompany" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('companyAddress');" width="8%">公司地址&nbsp;<img id="itemBrandImg_companyAddress" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('orderBy');" width="10%">顺序&nbsp;<img id="itemBrandImg_orderBy" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('memo');" width="8%">备注&nbsp;<img id="itemBrandImg_memo" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%"><s:text name="cmn_operate" /></th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.code}</td>
				<td>${entity.name}</td>
				<td>${entity.brandCompany}</td>
				<td>${entity.companyAddress}</td>
				<td>${entity.orderBy}</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>