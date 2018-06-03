<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--//-->

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}

/**
 * 选择人员
 */
function chooseUserAccountEmp(){
	$.ajax({
		 url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		 data:{chkStyle:"radio"},
		 //url:'${vix}/common/select/commonSelectEmpByBizOrgAction!goChooseEmp.action',
		 //data:{chkStyle:"radio","bizViewCode":"pvcode1"},
	 	 cache: false,
	  	 success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 540,
					title:"选择人员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
							
								var result = returnValue.split(",");
								//for (var i=0; i<result.length; i++){
								var resObj = result[result.length-1];
								if(resObj.length>1){
									var v = resObj.split(":");
									selectIds = v[0];
									selectNames = v[1];
								}
								//selectIds = $("#empId").val()+selectIds;
								//selectNames = $("#empName").val()+selectNames;
								$("#empId").val(selectIds);
								//selectNames = selectNames.substring(1,selectNames.length);
								$("#empName").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

$(function(){
	$("#userAccountProxyApplyForm").validationEngine();
});


</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="userAccountProxyApplyForm" name="userAccountProxyApplyForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">职员:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="empId" name="entityForm.empId" value="${entity.empId}" /> <input type="text" id="empName" name="entityForm.empName" readonly="readonly" class='validate[required] text-input' value="${entity.empName}" /> <input class="btn" type="button" value="选择" onclick="chooseUserAccountEmp();" /></td>
				</tr>
				<tr height="40">
					<td align="right">是否启用:&nbsp;</td>
					<td><s:radio list="#{\"Y\":\"是\",\"N\":\"否\"}" name="entityForm.isEnable" value="%{entity.isEnable}" theme="simple"></s:radio></td>
					<td align="right"></td>
					<td></td>
				</tr>

				<tr height="40">
					<td align="right">申请说明:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="entityForm.memo" value="${entity.memo}" class="text-input" /></td>
					<!-- <td align="right"></td>
				<td></td> -->
				</tr>
			</table>
		</div>
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