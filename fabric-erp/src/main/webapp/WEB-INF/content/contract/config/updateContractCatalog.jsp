<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#treeSingleGridForm").validationEngine();
function chooseParentCategory(){
	$.ajax({
		  url:'${vix}/contract/contractCatalogAction!goChooseCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择父分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									asyncbox.success("不允许引用自身为父分类!","提示信息");
								}else{
									$("#parentId").val(result[0]);
									$("#categoryName").html(result[1]);
								}
							}else{
								$("#parentId").val("");
								$("#categoryName").html("");
								asyncbox.success("请选择分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="treeSingleGridForm">
		<s:hidden id="id" name="category.id" value="%{category.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr>
					<td align="right">父分类:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="parentId" name="parentId" value="${contractCatalog.parentCategory.id}" /> <span id="categoryName"><s:property value="contractCatalog.parentCategory.name" /></span> <span class="btn"><a href="#" onclick="chooseParentCategory();">选择</a></span></td>
				</tr>
				<tr height="40">
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="category.name" value="${contractCatalog.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">是否禁用:&nbsp;</td>
					<td><s:if test="category.status == 0">
							<input type="radio" id="status1" name="status" value="1" />是
							<input type="radio" id="status2" name="status" value="0" checked="checked" />否
						</s:if> <s:elseif test="category.status == 1">
							<input type="radio" id="status1" name="status" value="1" checked="checked" />是
							<input type="radio" id="status2" name="status" value="0" />否
						</s:elseif> <s:else>
							<input type="radio" id="status1" name="status" value="1" />是
							<input type="radio" id="status2" name="status" value="0" checked="checked" />否
						</s:else></td>
					<td align="right">备注:&nbsp;</td>
					<td><s:textfield id="memo" name="contractCatalog.memo" value="%{contractCatalog.memo}" theme="simple" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script src="${vix}/common/js/jtip.js" type="text/javascript"></script>
<script type="text/javascript">
<!--
//提示
if($('input.sweet-tooltip').length){
	$('input.sweet-tooltip').focus(function() {
		tooltip				= $(this);
		tooltipText 		= tooltip.attr('data-text-tooltip');
		tooltipClassName	= 'tooltip';
		tooltipClass		= '.' + tooltipClassName;
		
		if(tooltip.hasClass('showed-tooltip')) return false;
		
		tooltip.addClass('showed-tooltip')
				 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');
		tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
		tooltipPosLeft = tooltip.position().left;
		tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop }).animate({'opacity':'1', 'marginTop':'0'}, 500);
	}).focusout(function() {
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
}
//-->
</script>