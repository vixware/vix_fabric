<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	//设置单据类型选中(修改)
	if(${null == entity.entityTime }){
		var myDate = new Date();
		$("#entityTime").val(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate());
	}
	
	$("#employeeEssForm").validationEngine();
});

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
	
	
	/** 选择职务*/
	function choosePosition(){
		$.ajax({
			  url:'${vix}/common/org/employeeOrgAction!goChoosehrPost.action',
			  cache: false,
			  success: function(html){
				  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
				  $(".ab_c .content").css("margin-bottom","0");
				  $('.ab_c .content').parent().css('position','relative');
				  asyncbox.open({
					 	modal:true,
						width : 1000,
						height : 500,
						title:"选择职务",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var rv = returnValue.split(",");
									$("#postsysCode").val(rv[0]);
									$("#subordinatePosition").val(rv[1]);
								}else{
									showMessage("请选择职务!");
									setTimeout("", 1000);
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	/** 选择职称*/
	function chooseTitle(){
		$.ajax({
			  url:'${vix}/common/org/employeeOrgAction!goChoosehrTitle.action',
			  cache: false,
			  success: function(html){
				  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
				  $(".ab_c .content").css("margin-bottom","0");
				  $('.ab_c .content').parent().css('position','relative');
				  asyncbox.open({
					 	modal:true,
						width : 1000,
						height : 500,
						title:"选择职称",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var rv = returnValue.split(",");
									$("#titlesysCode").val(rv[0]);
									$("#subordinateTitle").val(rv[1]);
								}else{
									showMessage("请选择职称!");
									setTimeout("", 1000);
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
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
	function subBirthday(){
		var idNumber = $("#idNumber").val();
		var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		var re = new RegExp(reg);
		if(re.test(idNumber)){
			$("#birthDate").val(idNumber.substring(6, 10) + "-" + idNumber.substring(10, 12) + "-" + idNumber.substring(12, 14));
		}
		else{
			return false;
		}
	}
</script>

<div class="order">
	<dl>
		<dt></dt>
		<dd class="clearfix">
			<div class="order_table">
				<div class="voucher newvoucher">
					<dl>
						<!-- <dt>
								<b class="downup"></b>
								<strong>基本信息</strong>
							</dt> -->
						<dd style="display: block;">
							<form id="employeeEssFastForm">
								<table style="border: none;">
									<tr height="40">
										<td align="right">所属部门:&nbsp;</td>
										<td><span id="parentUnitName">${entity.organizationUnit.fs}</span> <input type="hidden" id="orgUnitId" name="entityForm.organizationUnit.id" value="${entity.organizationUnit.id}" /></td>
									</tr>
									<tr height="40">
										<td align="right">员工类型：</td>
										<td><s:select list="#{'1':'在职','0':'后备'}" id="employeeType" name="entityForm.employeeType" value="%{entity.employeeType}" theme="simple"></s:select></td>
									</tr>
									<tr>
										<td align="right"><input type="hidden" id="essEmployeeId" name="entityForm.id" value="${entity.id}" /> 员工编码：</td>
										<td><input name="entityForm.code" type="text" size="30" value="${entity.code}" /></td>
										<td align="right">姓名：</td>
										<td><input name="entityForm.name" type="text" size="30" value="${entity.name}" class="validate[required] text-input" data-text-tooltip="必须填写！" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><span style="color: red;">*</span></td>
									</tr>
									<tr>
										<td align="right">曾用名：</td>
										<td><input name="entityForm.oldName" type="text" size="30" value="${entity.oldName}" /></td>
										<td align="right">姓名缩写</td>
										<td><input name="entityForm.fsName" type="text" size="30" value="${entity.fsName}" /></td>
									</tr>
									<tr>
										<td align="right">身份证号：</td>
										<td><input id="idNumber" name="entityForm.idNumber" type="text" size="30" value="${entity.idNumber}" onblur="subBirthday();" /></td>
										<td align="right">出生日期：</td>
										<td><input id="birthDate" name="entityForm.birthday" value="<s:date name="%{entity.birthday}" format="yyyy-MM-dd"/>" type="text" /> <img onclick="showTime('birthDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
									</tr>
									<tr>
										<td align="right">血型：</td>
										<td><s:select list="#{'A':'A','B':'B','AB':'AB','O':'O'}" id="bloodType" name="entityForm.bloodType" value="%{entity.bloodType}" theme="simple"></s:select></td>
										<td align="right">性别：</td>
										<td><s:select list="#{'1':'男','0':'女'}" id="gender" name="entityForm.gender" value="%{entity.gender}" theme="simple"></s:select></td>
									</tr>
									<tr>
										<td align="right">学历：</td>
										<td><input name="entityForm.qualificationsCode" type="text" size="30" value="${entity.qualificationsCode}" /></td>
										<td align="right">科系：</td>
										<td><input name="entityForm.departmentCode" type="text" size="30" value="${entity.departmentCode}" /></td>
									</tr>
									<tr>
										<td align="right">员工职号：</td>
										<td><input name="entityForm.staffJobNumber" type="text" size="30" value="${entity.staffJobNumber}" /></td>
										<td align="right">户籍地址：</td>
										<td><input name="entityForm.residenceAddress" type="text" size="30" value="${entity.residenceAddress}" /></td>
									</tr>
									<tr>
										<td align="right">出生地：</td>
										<td><input name="entityForm.birthplace" type="text" size="30" value="${entity.birthplace}" /></td>
										<td align="right">是否离职:&nbsp;</td>
										<td><s:radio list="%{#{'0':'否','1':'是'}}" name="entityForm.isDemission" value="0" theme="simple"></s:radio></td>
									</tr>
									<tr>
										<td align="right">入职日期：</td>
										<td><input id="entityTime" name="entityForm.entityTime" value="<s:date name="%{entity.entityTime}" format="yyyy-MM-dd"/>" type="text" /> <img onclick="showTime('entityTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										<td align="right">联系电话：</td>
										<td><input name="entityForm.telephone" type="text" size="30" value="${entity.telephone}" /></td>
									</tr>
									<tr>
										<td align="right">所属职务：</td>
										<td><input id="subordinatePosition" name="entityForm.subordinatePosition" value="${entity.subordinatePosition }" type="text" size="30" class="validate[required] text-input" data-text-tooltip="必须填写！" /> <span style="color: red;">*</span> <input type="hidden" id="positionid" name="positionid" value="${entity.positionid }" /> <input
											class="btn" type="button" value="选择" onclick="choosePosition();" /></td>
										<td align="right">所属职称：</td>
										<td><input id="subordinateTitle" name="entityForm.subordinateTitle" value="${entity.subordinateTitle }" type="text" size="30" class="validate[required] text-input" data-text-tooltip="必须填写！" /> <span style="color: red;">*</span> <input type="hidden" id="titleid" name="titleid" value="${entity.titleid }" /> <input class="btn"
											type="button" value="选择" onclick="chooseTitle();" /></td>
									</tr>
								</table>
							</form>
						</dd>
					</dl>
				</div>
			</div>
		</dd>
	</dl>
</div>
<!--oder-->
<div class="sub_menu sub_menu_bot">
	<div class="drop">
		<p></p>
	</div>
</div>
<!--submenu-->
<!-- content -->