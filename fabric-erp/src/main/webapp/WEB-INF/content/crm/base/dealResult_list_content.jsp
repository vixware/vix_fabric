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
<input type="hidden" id="dealResultTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="dealResultPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="dealResultTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="dealResultOrderField" value="${pager.orderField}" />
<input type="hidden" id="dealResultOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="dealResultInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%"><s:text name="cmn_id" />&nbsp;</th>
			<th width="10%">缺省&nbsp;</th>
			<th width="10%">启用&nbsp;</th>
			<th width="50%">内容</th>
			<th width="25%">备注</th>
		</tr>
		<s:iterator value="dealResultList" var="entity" status="s">
			<tr>
				<td><input id="dealResultId_${s.count}" value="${entity.id}" type="hidden" />${s.count}</td>
				<td><s:if test="#entity.isDefault == 1">
						<input id="dealResultRadio_${s.count}" type="radio" value="1" name="dealResultRadio" checked="checked" />
					</s:if> <s:else>
						<input id="dealResultRadio_${s.count}" type="radio" value="0" name="dealResultRadio" />
					</s:else></td>
				<td><s:if test="#entity.enable == 1">
						<input id="dealResultCheckbox_${s.count}" type="checkbox" value="1" checked="checked" />
					</s:if> <s:else>
						<input id="dealResultCheckbox_${s.count}" type="checkbox" value="0" />
					</s:else></td>
				<td><input id="dealResultName_${s.count}" type="text" style="height: 24px; width: 100%;" value="${entity.name }" onblur="directoryBlur('dealResult',${s.count});" /></td>
				<td><input id="dealResultMemo_${s.count}" type="text" style="height: 24px; width: 100%;" value="${entity.memo }" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>