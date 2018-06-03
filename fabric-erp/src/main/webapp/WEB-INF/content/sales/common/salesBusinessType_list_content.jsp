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
<input type="hidden" id="salesBusinessTypeTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="salesBusinessTypePageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="salesBusinessTypeTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="salesBusinessTypeOrderField" value="${pager.orderField}" />
<input type="hidden" id="salesBusinessTypeOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="salesBusinessTypeInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<table class="list">
	<tbody>
		<tr class="alt">
			<th width="5%"><s:text name="cmn_id" />&nbsp;</th>
			<th width="10%">缺省&nbsp;</th>
			<th width="10%">启用&nbsp;</th>
			<th width="50%">内容</th>
			<th width="25%">备注</th>
		</tr>
		<s:iterator value="salesBusinessTypeList" var="entity" status="s">
			<tr>
				<td><input id="salesBusinessTypeId_${s.count}" value="${entity.id}" type="hidden" />${s.count}</td>
				<td><s:if test="#entity.isDefault == 1">
						<input id="salesBusinessTypeRadio_${s.count}" type="radio" value="1" name="salesBusinessTypeRadio" checked="checked" />
					</s:if> <s:else>
						<input id="salesBusinessTypeRadio_${s.count}" type="radio" value="0" name="salesBusinessTypeRadio" />
					</s:else></td>
				<td><s:if test="#entity.enable == 1">
						<input id="salesBusinessTypeCheckbox_${s.count}" type="checkbox" value="1" checked="checked" />
					</s:if> <s:else>
						<input id="salesBusinessTypeCheckbox_${s.count}" type="checkbox" value="0" />
					</s:else></td>
				<td><input id="salesBusinessTypeName_${s.count}" type="text" style="height: 24px; width: 100%;" value="${entity.name }" onblur="directoryBlur('salesBusinessType',${s.count});" /></td>
				<td><input id="salesBusinessTypeMemo_${s.count}" type="text" style="height: 24px; width: 100%;" value="${entity.memo }" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>