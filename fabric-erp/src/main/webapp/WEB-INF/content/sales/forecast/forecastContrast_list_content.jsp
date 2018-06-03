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
</script>
<input type="hidden" id="forecastContrastTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="forecastContrastPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="forecastContrastTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="forecastContrastOrderField" value="${pager.orderField}" />
<input type="hidden" id="forecastContrastOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="forecastContrastInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="8%">年份</th>
			<th width="8%">1月</th>
			<th width="8%">2月</th>
			<th width="8%">3月</th>
			<th width="8%">4月</th>
			<th width="8%">5月</th>
			<th width="8%">6月</th>
			<th width="8%">7月</th>
			<th width="7%">8月</th>
			<th width="7%">9月</th>
			<th width="7%">10月</th>
			<th width="7%">11月</th>
			<th width="7%">12月</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td>${entity.year}</td>
				<td>${entity.one}</td>
				<td>${entity.two}</td>
				<td>${entity.three}</td>
				<td>${entity.four}</td>
				<td>${entity.five}</td>
				<td>${entity.six}</td>
				<td>${entity.seven}</td>
				<td>${entity.eight}</td>
				<td>${entity.nine}</td>
				<td>${entity.ten}</td>
				<td>${entity.eleven}</td>
				<td>${entity.twelve}</td>
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
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</c:forEach>
	</tbody>
</table>