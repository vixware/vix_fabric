<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	asyncbox.confirm('确定要删除该办公用品么?','提示信息',function(action){
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
loadOrderByImage("${vix}","bookEntry");
</script>
<input type="hidden" id="bookEntryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="bookEntryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="bookEntryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="bookEntryOrderField" value="${pager.orderField}" />
<input type="hidden" id="bookEntryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="bookEntryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="10%" style="cursor: pointer;" onclick="orderBy('bookCode');">编号&nbsp;<img id="bookEntryImg_bookCode" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="10%" style="cursor: pointer;" onclick="orderBy('bookName');">书名&nbsp;<img id="bookEntryImg_bookName" src="${vix}/common/img/arrow.gif">
			</th>
			<th><s:text name="ISBN" /></th>
			<th><s:text name="类型" /></th>
			<th><s:text name="作者" /></th>
			<th><s:text name="出版社" /></th>
			<th><s:text name="数量" /></th>
			<th><s:text name="存放地点" /></th>
			<% int count = 0; %>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td style="cursor: pointer" onclick="chooseBook('${entity.id}','${entity.bookCode}','${entity.bookName}','${entity.ISBN}','${entity.bookType}','${entity.author}','${entity.press}','${entity.address}','${entity.amount}')">${entity.bookCode}</td>
					<td style="cursor: pointer" onclick="chooseBook('${entity.id}','${entity.bookCode}','${entity.bookName}','${entity.ISBN}','${entity.bookType}','${entity.author}','${entity.press}','${entity.address}','${entity.amount}')">${entity.bookName}</td>
					<td style="cursor: pointer" onclick="chooseBook('${entity.id}','${entity.bookCode}','${entity.bookName}','${entity.ISBN}','${entity.bookType}','${entity.author}','${entity.press}','${entity.address}','${entity.amount}')">${entity.ISBN}</td>
					<td style="cursor: pointer" onclick="chooseBook('${entity.id}','${entity.bookCode}','${entity.bookName}','${entity.ISBN}','${entity.bookType}','${entity.author}','${entity.press}','${entity.address}','${entity.amount}')">${entity.bookType}</td>
					<td style="cursor: pointer" onclick="chooseBook('${entity.id}','${entity.bookCode}','${entity.bookName}','${entity.ISBN}','${entity.bookType}','${entity.author}','${entity.press}','${entity.address}','${entity.amount}')">${entity.author}</td>
					<td style="cursor: pointer" onclick="chooseBook('${entity.id}','${entity.bookCode}','${entity.bookName}','${entity.ISBN}','${entity.bookType}','${entity.author}','${entity.press}','${entity.address}','${entity.amount}')">${entity.press}</td>
					<td style="cursor: pointer" onclick="chooseBook('${entity.id}','${entity.bookCode}','${entity.bookName}','${entity.ISBN}','${entity.bookType}','${entity.author}','${entity.press}','${entity.address}','${entity.amount}')">${entity.address}</td>
					<td style="cursor: pointer" onclick="chooseBook('${entity.id}','${entity.bookCode}','${entity.bookName}','${entity.ISBN}','${entity.bookType}','${entity.author}','${entity.press}','${entity.address}','${entity.amount}')">${entity.amount}</td>
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