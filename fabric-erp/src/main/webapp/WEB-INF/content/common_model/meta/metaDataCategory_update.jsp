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
 
$("#mountPointForm").validationEngine();
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
/*
function saveOrUpdateMountPoint(){
	if($('#mountPointForm').validationEngine('validate')){
		$.post('${vix}/sales/config/mountPointAction!saveOrUpdate.action',
			{'mountPoint.id':$("#id").val(),
			  'mountPoint.name':$("#name").val(),
			  'mountPoint.code':$("#code").val(),
			  'mountPoint.workTime':$("#workTime").val(),
			  'mountPoint.address':$("#address").val(),
			  'mountPoint.phone':$("#phone").val(),
			  'mountPoint.mobie':$("#mobie").val(),
			  'mountPoint.email':$("#email").val(),
			  'mountPoint.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/config/mountPointAction!goList.action');
			}
		);
	}
} */
 
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
//-->
</script>
<%-- <div class="sub_menu">
	<h2>
		<i>
			<a href="#"><img alt="" src="img/icon_14.gif"><s:text name="print"/></a>
			<a href="#"><img alt="" src="img/icon_15.gif"><s:text name="help"/></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li>
					<a href="#"><s:text name="system_control"/></a>
				</li>
				<li>
					<a href="#"><s:text name="system_control_basedata"/></a>
				</li>
				<li>
					<a href="#"><s:text name="system_control_basedata_meta"/></a>
				</li>
				<li>
					<a href="#"><s:text name="system_control_basedata_metadatacategory"/></a>
				</li>
			</ul>
		</div>
	</h2>
</div> --%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="baseMetaDataCategoryForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td width="20%" align="right">名称:</td>
					<td width="80%"><input id="categoryName" name="entityForm.categoryName" value="${entity.categoryName}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">编码:</td>
					<td width="80%"><input id="code" name="entityForm.code" value="${entity.code}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
