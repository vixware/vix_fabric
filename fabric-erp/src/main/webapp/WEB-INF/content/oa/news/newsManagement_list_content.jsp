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
loadOrderByImage("${vix}","trends");
</script>
<input type="hidden" id="trendsTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="trendsPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="trendsTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="trendsOrderField" value="${pager.orderField}" />
<input type="hidden" id="trendsOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="trendsInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /></a></li>
							<li><a href="#"><s:text name="cmn_other" /></a></li>
							<li><a href="#"><s:text name="cmn_close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="8%" style="cursor: pointer;" onclick="orderBy('uploadPersonName');">发布人&nbsp;<img id="trendsImg_uploadPersonName" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="36%" style="cursor: pointer;" onclick="orderBy('title');">标题&nbsp;<img id="trendsImg_title" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="7%"><s:text name="类型" /></th>
			<th width="8%" style="cursor: pointer;" onclick="orderBy('createTime');">发布时间&nbsp;<img id="trendsImg_createTime" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%" style="cursor: pointer;" onclick="orderBy('readTimes');">点击次数&nbsp;<img id="trendsImg_readTimes" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="8%"><s:text name="评论（条）" /></th>
			<th width="8%"><s:text name="新闻评论" /></th>
			<% int count = 0;%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><span style="color: gray;">${entity.uploadPersonName }</span></td>
					<td><a href="javascript:void(0);" style="color: gray;" onclick="popNews('${entity.id}');">${entity.title}</a>
					<td><a href="#" style="color: gray;"> <s:if test="%{#entity.newsType == 0}">
								<span style="color: red;">文本</span>
							</s:if> <s:elseif test="%{#entity.newsType == 1}">
								<span style="color: green;">图片</span>
							</s:elseif>
					</a></td>
					<td><a href="#" style="color: gray;"><fmt:formatDate value="${entity.createTime}" type="both" pattern="yyyy-MM-dd" /></a></td>
					<td style="text-align: center"><span style="color: gray;">${entity.readTimes }</span></td>
					<td style="text-align: center"><span style="color: gray;">${entity.comments.size() }</span></td>
					<td>
						<div class="untitled" style="position: static; display: inline;">
							<s:if test="tag != 'choose'">
								<a href="javascript:void(0);" onclick="popNews('${entity.id}');"><span style="color: blue;">查看</span></a>
								<a href="javascript:void(0);" onclick="saveOrUpdate('${entity.id}');">评论</a>
							</s:if>
						</div>
					</td>
				</tr>
			</s:iterator>
			<%
				/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
				com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request
						.getAttribute("pager");
				count = pager.getPageSize() - count;
				request.setAttribute("count", count);
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