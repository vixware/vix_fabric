<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	 
$("#contactPersonForm").validationEngine();
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
<input type="hidden" id="id" name="id" value="${contactPerson.id}" />
<div style="padding-top: 15px;" class="content">
	<form id="contactPersonForm">
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<th align="right">姓:&nbsp;</th>
					<td><input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" type="text" /></td>
					<th align="right">名:&nbsp;</th>
					<td><input id="firstName" name="contactPerson.firstName" value="${contactPerson.firstName}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">性别:&nbsp;</th>
					<td><s:if test="contactPerson.sex == 0">
							<input type="radio" id="sex1" name="sex" value="1" />男
							<input type="radio" id="sex2" name="sex" value="0" checked="checked" />女
						</s:if> <s:elseif test="contactPerson.sex == 1">
							<input type="radio" id="sex1" name="sex" value="1" checked="checked" />男
							<input type="radio" id="sex2" name="sex" value="0" />女
						</s:elseif> <s:else>
							<input type="radio" id="sex1" name="sex" value="1" />男
							<input type="radio" id="sex2" name="sex" value="0" />女
						</s:else></td>
					<th align="right">电话:&nbsp;</th>
					<td><input id="mobile" name="contactPerson.mobile" value="${contactPerson.mobile}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">称谓:&nbsp;</th>
					<td><input id="callTitle" name="contactPerson.callTitle" value="${contactPerson.callTitle}" type="text" /></td>
					<th align="right">生日:&nbsp;</th>
					<td><input id="birthday" name="contactPerson.birthday" value="<s:property value='formatDateToString(contactPerson.birthday)'/>" type="text" /> <img onclick="showTime('birthday','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="30">
					<th align="right">工作电话:&nbsp;</th>
					<td><input id="directPhone" name="directPhone" value="${contactPerson.directPhone}" type="text" /></td>
					<th align="right">邮件:&nbsp;</th>
					<td><input id="email" name="contactPerson.email" value="${contactPerson.email}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">家庭电话:&nbsp;</th>
					<td><input id="phoneHome" name="contactPerson.phoneHome" value="${contactPerson.phoneHome}" type="text" /></td>
					<th align="right">MSN:&nbsp;</th>
					<td><input id="msnAccount" name="contactPerson.msnAccount" value="${contactPerson.msnAccount}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">QQ:&nbsp;</th>
					<td><input id="qqAccount" name="contactPerson.qqAccount" value="${contactPerson.qqAccount}" type="text" /></td>
					<th align="right">SKYPE:&nbsp;</th>
					<td><input id="skypeAccount" name="contactPerson.skypeAccount" value="${contactPerson.skypeAccount}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">旺旺:&nbsp;</th>
					<td><input id="wangAccount" name="contactPerson.wangAccount" value="${contactPerson.wangAccount}" type="text" /></td>
					<th align="right">传真:&nbsp;</th>
					<td><input id="fax" name="contactPerson.fax" value="${contactPerson.fax}" type="text" /></td>
				</tr>
				<tr height="30">
					<th align="right">公司:&nbsp;</th>
					<td><input id="company" name="contactPerson.company" value="${contactPerson.company}" type="text" /></td>
					<th align="right">负责业务:&nbsp;</th>
					<td><input id="presideBusiness" name="contactPerson.presideBusiness" value="${contactPerson.presideBusiness}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
