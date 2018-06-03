<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function chooseParentCategory(){
	$.ajax({
		  url:'${vix}/oa/wabAction!goChooseCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择群组分类",
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


/* 手机号码 */
function showHiddenObj(className){
	if($('.'+className+':visible').length>=4){
		alert('最多可以添加6个手机号码')
		return;
	}
	$('.'+className+':hidden:first').show();
}

$(function(){
	$('.click_hide').bind('click',function(){
		var jobj = $(this);
		var parentObj = jobj.parent();
		parentObj.hide();
		parentObj.find('input').val('');
	});
	$("#brandForm").validationEngine("attach",{promptPosition:"topLeft"});
});


/* 公司电话号码 */
function showHiddenCalls(className){
	if($('.'+className+':visible').length>=4){
		alert('最多可以添加6个公司号码')
		return;
	}
	$('.'+className+':hidden:first').show();
}

$(function(){
	$('.click_calls').bind('click',function(){
		var jobj = $(this);
		var parentObj = jobj.parent();
		parentObj.hide();
		parentObj.find('input').val('');
	});
});


/*个人邮箱 */
function showHiddenEmail(className){
	if($('.'+className+':visible').length>=4){
		alert('最多可以添加6个邮箱')
		return;
	}
	$('.'+className+':hidden:first').show();
}

$(function(){
	$('.click_email').bind('click',function(){
		var jobj = $(this);
		var parentObj = jobj.parent();
		parentObj.hide();
		parentObj.find('input').val('');
	});
});

/*qq */
function showHiddenqq(className){
	if($('.'+className+':visible').length>=4){
		alert('最多可以添加6个QQ')
		return;
	}
	$('.'+className+':hidden:first').show();
}

$(function(){
	$('.click_qq').bind('click',function(){
		var jobj = $(this);
		var parentObj = jobj.parent();
		parentObj.hide();
		parentObj.find('input').val('');
	});
});

/*自定义*/
function showHiddencustom(className){
	if($('.'+className+':visible').length>=4){
		alert('最多可以添加10个QQ')
		return;
	}
	$('.'+className+':hidden:first').show();
}

$(function(){
	$('.click_custom').bind('click',function(){
		var jobj = $(this);
		var parentObj = jobj.parent();
		parentObj.hide();
		parentObj.find('input').val('');
	});
});

</script>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<div class="tcbox">
	<form id="brandForm">
		<input type="hidden" id="id" name="id" value="${wab.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table style="margin: 0 auto;">
				<tr style="display: none;">
					<td><s:radio list="#{'1':'公共','0':'私有'}" id="wabtype" name="wabtype" value="%{wab.wabtype==1}" theme="simple"></s:radio></td>
				</tr>
				<tr>
					<td align="right">群组名:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="parentId" name="parentId" value="${wab.parentCategory.id}" /> <span id="categoryName"><s:property value="wab.parentCategory.name" /></span> <span class="btn"><a href="#" onclick="chooseParentCategory();">选择</a></span></td>
				</tr>
				<tr>
					<td width="49" align="right">姓名：</td>
					<td><div class="tc_copay">
							<input id="name" name="wab.name" value="${wab.name}" type="text" class="ipt tcipt_1 validate[required] text-input" /> <span style="color: red;">*</span>
						</div></td>
				</tr>
				<tr>
					<td align="right">公司：</td>
					<td><div class="tc_copay">
							<input id="company" name="wab.company" value="${wab.company}" type="text" class="ipt tcipt_1" />
						</div></td>
				</tr>
				<tr>
					<td align="right">地址：</td>
					<td><div class="tc_copay">
							<input id="langCode" name="wab.langCode" value="${wab.langCode}" type="text" class="ipt tcipt_1" />
						</div></td>
				</tr>
				<tr>
					<td align="right">职位：</td>
					<td><div class="tc_copay">
							<input id="position" name="wab.position" value="${wab.position}" type="text" class="ipt tcipt_1" />
						</div></td>
				</tr>
				<tr>
					<td align="right">手机号：</td>
					<td>
						<div class="tc_copay">
							<input type="text" value="${wab.mobileno}" name="mobileno" id="mobileno"> <a class="tc_texticon" href="javascript:showHiddenObj('sub_mobile');"><span>+</span></a>
						</div>
						<div class="tc_copay sub_mobile" style="<s:if test='wab==null||wab.mobileno1==null||wab.mobileno1==""'>display:none;</s:if>">
							<input type="text" value="${wab.mobileno1}" name="mobileno1" id="mobileno1"> <a class="tc_texticon click_hide" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_mobile" style="<s:if test='wab==null||wab.mobileno2==null||wab.mobileno2==""'>display:none;</s:if>">
							<input type="text" value="${wab.mobileno2}" name="mobileno2" id="mobileno2"> <a class="tc_texticon click_hide" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_mobile" style="<s:if test='wab==null||wab.mobileno3==null||wab.mobileno3==""'>display:none;</s:if>">
							<input type="text" value="${wab.mobileno3}" name="mobileno3" id="mobileno3"> <a class="tc_texticon click_hide" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_mobile" style="<s:if test='wab==null||wab.mobileno4==null||wab.mobileno4==""'>display:none;</s:if>">
							<input type="text" value="${wab.mobileno4}" name="mobileno4" id="mobileno4"> <a class="tc_texticon click_hide" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_mobile" style="<s:if test='wab==null||wab.mobileno5==null||wab.mobileno5==""'>display:none;</s:if>">
							<input type="text" value="${wab.mobileno5}" name="mobileno5" id="mobileno5"> <a class="tc_texticon click_hide" href="javascript:;"><span>-</span></a>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">公司号：</td>
					<td>
						<div class="tc_copay">
							<input type="text" class="ipt tcipt_1 validate[required]" value="${wab.calls}" name="calls" id="calls"> <a class="tc_texticon" href="javascript:showHiddenCalls('sub_calls');"><span style="color: red;">+</span></a>
						</div>
						<div class="tc_copay sub_calls" style="<s:if test='wab==null||wab.calls1==null||wab.calls1==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1 validate[required]" value="${wab.calls1}" name="calls1" id="calls1"> <a class="tc_texticon click_calls" href="javascript:;"><span style="color: red;">-</span></a>
						</div>
						<div class="tc_copay sub_calls" style="<s:if test='wab==null||wab.calls2==null||wab.calls2==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1 validate[required]" value="${wab.calls2}" name="calls2" id="calls2"> <a class="tc_texticon click_calls" href="javascript:;"><span style="color: red;">-</span></a>
						</div>
						<div class="tc_copay sub_calls" style="<s:if test='wab==null||wab.calls3==null||wab.calls3==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1 validate[required]" value="${wab.calls3}" name="calls3" id="calls3"> <a class="tc_texticon click_calls" href="javascript:;"><span style="color: red;">-</span></a>
						</div>
						<div class="tc_copay sub_calls" style="<s:if test='wab==null||wab.calls4==null||wab.calls4==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1 validate[required]" value="${wab.calls4}" name="calls4" id="calls4"> <a class="tc_texticon click_calls" href="javascript:;"><span style="color: red;">-</span></a>
						</div>
						<div class="tc_copay sub_calls" style="<s:if test='wab==null||wab.calls5==null||wab.calls5==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1 validate[required]" value="${wab.calls5}" name="calls5" id="calls5"> <a class="tc_texticon click_calls" href="javascript:;"><span style="color: red;">-</span></a>
						</div>
					</td>
				</tr>
				</tr>
				<tr>
					<td align="right">邮箱：</td>
					<td>
						<div class="tc_copay">
							<input type="text" text-input" value="${wab.email}" name="email" id="email"> <a class="tc_texticon" href="javascript:showHiddenEmail('sub_email');"><span>+</span></a>
						</div>
						<div class="tc_copay sub_email" style="<s:if test='wab==null||wab.email1==null||wab.email1==""'>display:none;</s:if>">
							<input type="text" value="${wab.email1}" name="email1" id="email1"> <a class="tc_texticon click_email" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_email" style="<s:if test='wab==null||wab.email2==null||wab.email2==""'>display:none;</s:if>">
							<input type="text" value="${wab.email2}" name="email2" id="email2"> <a class="tc_texticon click_email" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_email" style="<s:if test='wab==null||wab.email3==null||wab.email3==""'>display:none;</s:if>">
							<input type="text" value="${wab.email3}" name="email3" id="email3"> <a class="tc_texticon click_email" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_email" style="<s:if test='wab==null||wab.email4==null||wab.email4==""'>display:none;</s:if>">
							<input type="text" value="${wab.email4}" name="email4" id="email4"> <a class="tc_texticon click_email" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_email" style="<s:if test='wab==null||wab.email5==null||wab.email5==""'>display:none;</s:if>">
							<input type="text" value="${wab.email5}" name="email5" id="email5"> <a class="tc_texticon click_email" href="javascript:;"><span>-</span></a>
						</div>
					</td>
				</tr>
				<tr>
					<th>生日:&nbsp;</th>
					<td><input id="startTime" class="ipt tcipt_1" data-text-tooltip="时间格式 yyyy-MM-dd" name="startTime" onclick="showTime('startTime','yyyy-MM-dd')" value="<fmt:formatDate value='${wab.startTime}' type='both' pattern='yyyy-MM-dd' />" type="text" /> <img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
						width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr>
					<td align="right">QQ：</td>
					<td>
						<div class="tc_copay">
							<input type="text" value="${wab.qq}" name="qq" id="qq"> <a class="tc_texticon" href="javascript:showHiddenqq('sub_qq');"><span>+</span></a>
						</div>
						<div class="tc_copay sub_qq" style="<s:if test='wab==null||wab.qq1==null||wab.qq1==""'>display:none;</s:if>">
							<input type="text" value="${wab.qq1}" name="qq1" id="qq1"> <a class="tc_texticon click_qq" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_qq" style="<s:if test='wab==null||wab.qq2==null||wab.qq2==""'>display:none;</s:if>">
							<input type="text" value="${wab.qq2}" name="qq2" id="qq2"> <a class="tc_texticon click_qq" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_qq" style="<s:if test='wab==null||wab.qq3==null||wab.qq3==""'>display:none;</s:if>">
							<input type="text" value="${wab.qq3}" name="qq3" id="qq3"> <a class="tc_texticon click_qq" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_qq" style="<s:if test='wab==null||wab.qq4==null||wab.qq4==""'>display:none;</s:if>">
							<input type="text" value="${wab.qq4}" name="qq4" id="qq4"> <a class="tc_texticon click_qq" href="javascript:;"><span>-</span></a>
						</div>
						<div class="tc_copay sub_qq" style="<s:if test='wab==null||wab.qq5==null||wab.qq5==""'>display:none;</s:if>">
							<input type="text" value="${wab.qq5}" name="qq5" id="qq5"> <a class="tc_texticon click_qq" href="javascript:;"><span>-</span></a>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">自定义：</td>
					<td>
						<div class="tc_copay">
							<input type="text" class="ipt tcipt_1" value="${wab.custom}" name="custom" id="custom"> <a class="tc_texticon" href="javascript:showHiddencustom('sub_custom');">+</a>
						</div>
						<div class="tc_copay sub_custom" style="<s:if test='wab==null||wab.custom1==null||wab.custom1==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1" value="${wab.custom1}" name="custom1" id="custom1"> <a class="tc_texticon click_custom" href="javascript:;">-</a>
						</div>
						<div class="tc_copay sub_custom" style="<s:if test='wab==null||wab.custom2==null||wab.custom2==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1" value="${wab.custom2}" name="custom2" id="custom2"> <a class="tc_texticon click_custom" href="javascript:;">-</a>
						</div>
						<div class="tc_copay sub_custom" style="<s:if test='wab==null||wab.custom3==null||wab.custom3==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1" value="${wab.custom3}" name="custom3" id="custom3"> <a class="tc_texticon click_custom" href="javascript:;">-</a>
						</div>
						<div class="tc_copay sub_custom" style="<s:if test='wab==null||wab.custom4==null||wab.custom4==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1" value="${wab.custom4}" name="custom4" id="custom4"> <a class="tc_texticon click_custom" href="javascript:;">-</a>
						</div>
						<div class="tc_copay sub_custom" style="<s:if test='wab==null||wab.custom5==null||wab.custom5==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1" value="${wab.custom5}" name="custom5" id="custom5"> <a class="tc_texticon click_custom" href="javascript:;">-</a>
						</div>
						<div class="tc_copay sub_custom" style="<s:if test='wab==null||wab.custom6==null||wab.custom6==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1" value="${wab.custom6}" name="custom6" id="custom6"> <a class="tc_texticon click_custom" href="javascript:;">-</a>
						</div>
						<div class="tc_copay sub_custom" style="<s:if test='wab==null||wab.custom7==null||wab.custom7==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1" value="${wab.custom7}" name="custom7" id="custom7"> <a class="tc_texticon click_custom" href="javascript:;">-</a>
						</div>
						<div class="tc_copay sub_custom" style="<s:if test='wab==null||wab.custom8==null||wab.custom8==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1" value="${wab.custom8}" name="custom8" id="custom8"> <a class="tc_texticon click_custom" href="javascript:;">-</a>
						</div>
						<div class="tc_copay sub_custom" style="<s:if test='wab==null||wab.custom9==null||wab.custom9==""'>display:none;</s:if>">
							<input type="text" class="ipt tcipt_1" value="${wab.custom9}" name="custom9" id="custom9"> <a class="tc_texticon click_custom" href="javascript:;">-</a>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">个人简介：</td>
					<td colspan="3"><textarea id="memo" name="memo" class="example" rows="2" style="width: 357px; height: 74px;">${wab.memo }</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>