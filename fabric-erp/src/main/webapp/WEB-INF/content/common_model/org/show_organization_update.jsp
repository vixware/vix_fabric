<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript">
<!--
$(".addtable .addtable_t").click(function(){
	$(this).toggleClass("addtable_td");
	$(this).next(".addtable_c").toggle();
});
$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
});
$(".order_table input[type='text']").focusin( function() {
   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
});
$(".order_table  input[type='text']").focusout( function() {
   $(this).css({'border':'1px solid #ccc','background':'#fff'});
});

function chooseParentOrganization(){
	$.ajax({
		  url:'${vix}/common/org/organizationAction!goChooseOrganization.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择父公司",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									showErrorMessage("不允许引用本公司为父公司!");
									setTimeout("", 1000);
									return false;
								}else{
									$("#parentId").val(result[0]);
									$("#organizationName").val(result[1]);
								}
							}else{
								$("#parentId").val("");
								$("#organizationName").val("");
								showErrorMessage("请选择上级公司信息!");
								setTimeout("", 1000);
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

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
		
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop })
							.animate({'opacity':'1', 'marginTop':'0'}, 500);
		
	}).focusout(function() {
		
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
				
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
	JT_init();
}
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");


function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}
$("#organizationForm").validationEngine();
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<form action="" id="organizationForm" name="organizationForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<tr>
					<td align="right">上级公司机构:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="parentId" name="parentId" value="${entity.parentOrganization.id}" /> <%-- <span id="organizationName"><s:property value="entity.parentOrganization.orgName"/></span> --%> <input type="text" id="organizationName" name="organizationName" readonly="readonly" value="${entity.parentOrganization.orgName}" />
						<input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /></td>
				</tr>
				<tr height="40">
					<td align="right">机构类型:&nbsp;</td>
					<td colspan="3"><s:select list="#{'jtgs':'集团公司','gs':'公司'}" name="entityForm.orgType" value="%{entity.orgType}" theme="simple"></s:select></td>
				</tr>
				<tr height="40">
					<td align="right">名称:&nbsp;</td>
					<td><input id="orgName" name="entityForm.orgName" readonly="readonly" value="${entity.orgName}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">简称:&nbsp;</td>
					<td><input id="briefName" name="entityForm.briefName" readonly="readonly" value="${entity.briefName}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">联系电话:&nbsp;</td>
					<td><input id="telephone" name="entityForm.telephone" readonly="readonly" value="${entity.telephone}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">传真:&nbsp;</td>
					<td><input id="fax" name="entityForm.fax" readonly="readonly" value="${entity.fax}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">所属行业:&nbsp;</td>
					<td><input id="industry" name="entityForm.industry" type="text" readonly="readonly" value="${entity.industry}" /></td>
					<td align="right">法人代表:&nbsp;</td>
					<td><input id="corporateRepresentative" name="entityForm.corporateRepresentative" type="text" readonly="readonly" value="${entity.corporateRepresentative}" /></td>
				</tr>
				<tr height="40">
					<td align="right">主页:&nbsp;</td>
					<td><input id="homePage" name="entityForm.homePage" type="text" readonly="readonly" value="${entity.homePage}" /></td>
					<td align="right">组织简介:&nbsp;</td>
					<td><input id="orgSummary" name="entityForm.orgSummary" type="text" readonly="readonly" value="${entity.orgSummary}" /></td>
				</tr>
				<tr height="40">
					<td align="right">拼音缩写:&nbsp;</td>
					<td><input id="shortName" name="entityForm.shortName" type="text" readonly="readonly" value="${entity.shortName}" /></td>
					<td align="right">英文名称:&nbsp;</td>
					<td><input id="enName" name="entityForm.enName" type="text" readonly="readonly" value="${entity.enName}" /></td>
				</tr>
				<tr height="40">
					<td align="right">地址:&nbsp;</td>
					<td><input id="address" name="entityForm.address" type="text" readonly="readonly" value="${entity.address}" /></td>
					<td align="right">所属国家:&nbsp;</td>
					<td><input id="country" name="entityForm.country" type="text" readonly="readonly" value="${entity.country}" /></td>
				</tr>
				<tr height="40">
					<td align="right">地区:&nbsp;</td>
					<td><input id="area" name="entityForm.area" type="text" readonly="readonly" value="${entity.area}" /></td>
					<td align="right">使用语言:&nbsp;</td>
					<td><input id="countryLanguage" name="entityForm.countryLanguage" type="text" readonly="readonly" value="${entity.countryLanguage}" /></td>
				</tr>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input id="companyCode" name="entityForm.companyCode" type="text" readonly="readonly" value="${entity.companyCode}" /></td>
					<td align="right">成立时间:&nbsp;</td>
					<td><input type="text" id="companyCreateDate" name="entityForm.companyCreateDate" readonly="readonly" value="<s:date name='%{entity.companyCreateDate}' format='yyyy-MM-dd'/>" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img onclick="showTime('companyCreateDate','yyyy-MM-dd')"
						src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td align="right">启用时间:&nbsp;</td>
					<td><input type="text" id="startUsingDate" name="entityForm.startUsingDate" readonly="readonly" value="<s:date name='%{entity.startUsingDate}' format='yyyy-MM-dd'/>" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img onclick="showTime('startUsingDate','yyyy-MM-dd')"
						src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">停用时间:&nbsp;</td>
					<td><input type="text" id="stopUsingDate" name="entityForm.stopUsingDate" readonly="readonly" value="<s:date name='%{entity.stopUsingDate}' format='yyyy-MM-dd'/>" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img onclick="showTime('stopUsingDate','yyyy-MM-dd')"
						src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td><input id="memo" name="entityForm.memo" type="text" readonly="readonly" value="${entity.memo}" /></td>
				</tr>
			</table>
			<!-- <hr/> -->
			<input type="hidden" id="addCategoryBrandIds" name="addCategoryBrandIds" value="" /> <input type="hidden" id="deleteCategoryBrandIds" name="deleteCategoryBrandIds" value="" />
		</form>
	</div>
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