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
$.returnValue = "";
function chooseAll(chk){
	if(null == chk){
		$("input[name='chkcpqtId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkcpqtId']").attr("checked", true);
		}else{
			$("input[name='chkcpqtId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkcpqtId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
			$.returnValue = $.returnValue + "," + $(n).val();
		}else{
			 $.returnValue = $.returnValue.replace(","+$(n).val(),"");
		}
	});
	$("#selectpqtCount1").html(selectCount);
	$("#selectpqtCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkcpqtIds']").attr("checked", false);
	}else{
		$("input[name='chkcpqtIds']").attr("checked", true);
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
loadOrderByImage("${vix}","chooseProjectQuotationTemplate");
</script>
<input type="hidden" id="chooseProjectQuotationTemplateTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="chooseProjectQuotationTemplatePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="chooseProjectQuotationTemplateTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="chooseProjectQuotationTemplateOrderField" value="${pager.orderField}" />
<input type="hidden" id="chooseProjectQuotationTemplateOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="chooseProjectQuotationTemplateInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkcpqtIds" onchange="chooseAll(this)"></label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="all" /></a></li>
							<li><a href="#"><s:text name="other" /></a></li>
							<li><a href="#"><s:text name="close" /></a></li>
						</ul>
					</div>
				</div>
			</th>
			<th onclick="orderBy('id');" width="10%"><s:text name="cmn_id" />&nbsp;<img id="chooseProjectQuotationTemplateImg_id" src="${vix}/common/img/arrow.gif"></th>
			<th onclick="orderBy('name');" width="20%">报价主题&nbsp;<img id="chooseProjectQuotationTemplateImg_name" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('bizType');" width="15%">业务类型&nbsp;<img id="chooseProjectQuotationTemplateImg_bizType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('formType');" width="15%">单据类型&nbsp;<img id="chooseProjectQuotationTemplateImg_formType" src="${vix}/common/img/arrow.gif">
			</th>
			<th onclick="orderBy('amount');" width="15%">金额&nbsp;<img id="chooseProjectQuotationTemplateImg_amount" src="${vix}/common/img/arrow.gif">
			</th>
		</tr>
		<% int count = 0; %>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<% count++; %>
			<tr>
				<td><input id="chkcpqtId" name="chkcpqtId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
				<td>${entity.name}</td>
				<td>${entity.bizType}</td>
				<td>${entity.formType}</td>
				<td>${entity.amount}</td>
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
			</tr>
		</c:forEach>
	</tbody>
</table>