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
			<th style="cursor: pointer;" onclick="orderBy('theme');">主题&nbsp;<img id="trendsImg_theme" src="${vix}/common/img/arrow.gif">
			</th>
			<th>车牌号</th>
			<th style="cursor: pointer;" onclick="orderBy('carName');">用车人&nbsp;<img id="trendsImg_carName" src="${vix}/common/img/arrow.gif">
			</th>
			<th style="cursor: pointer;" onclick="orderBy('pubNames');">随行人员&nbsp;<img id="trendsImg_pubNames" src="${vix}/common/img/arrow.gif">
			</th>
			<th width="14%" style="cursor: pointer;" onclick="orderBy('reasons');">事由&nbsp;<img id="trendsImg_reasons" src="${vix}/common/img/arrow.gif">
			</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>备注</th>
			<th><s:text name="状态" /></th>
			<th><s:text name="cmn_operate" /></th>
			<% int count = 0;%>
			<s:iterator value="pager.resultList" var="entity" status="s">
				<% count++; %>
				<tr>
					<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
					<td><span style="color: gray;">${entity.theme}</span></td>
					<td><span style="color: gray;">${entity.vehicleRequest.model}</span></td>
					<td><span style="color: gray;">${entity.carName}</span></td>
					<td><span style="color: gray;">${entity.pubNames}</span></td>
					<td><span style="color: gray;">${entity.reasons}</span></td>
					<td><span style="color: gray;"><fmt:formatDate value="${entity.startTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
					<td><span style="color: gray;"><fmt:formatDate value="${entity.endTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
					<td><span style="color: gray;">${entity.remarks}</span></td>
					<td id="bookingSituation_${entity.id}"><s:if test="%{#entity.bookingSituation == 1}">
							<span style="color: red;">已批准</span>
						</s:if> <s:elseif test="%{#entity.bookingSituation == 0}">
							<span style="color: green;">待批准</span>
						</s:elseif> <s:elseif test="%{#entity.bookingSituation == 2}">
							<span style="color: green;">被驳回</span>
						</s:elseif> <s:elseif test="%{#entity.bookingSituation == 3}">
							<span style="color: green;">完成</span>
						</s:elseif></td>
					<td>
						<div class="untitled" style="position: static; display: inline;">
							<s:if test="%{#entity.bookingSituation==1 }">
								<a href="#" onclick="updateBookingSituation('${entity.id}',${entity.bookingSituation})"> <span>收回</span>
								</a>
							</s:if>
							<s:elseif test="%{#entity.bookingSituation==0 }">
								<a href="#" onclick="updateBookingSituation('${entity.id}',1)"> <span style="color: green;">批准</span>
								</a>
								<a href="#" onclick="updateBookingSituation('${entity.id}',2)"> <span style="color: green;">不批准</span>
								</a>
							</s:elseif>
							<s:elseif test="%{#entity.bookingSituation == 2}">
								<span style="color: green;">申请失败</span>
							</s:elseif>
							<s:elseif test="%{#entity.bookingSituation == 3}">
								<span style="color: green;">完成</span>
							</s:elseif>
						</div>
					</td>
				</tr>
			</s:iterator>
			<%
				/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
				com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request.getAttribute("pager");
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
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
	</tbody>
</table>