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
 
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}


function chooseMetaDataType(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrginialMetaDataAction!goChooseMetaData.action?tag=choose&chkStyle=radio',//dataResRowOwnerAction
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 440,
					title:"选择元数据",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							var roleIds = "";
							var roleNames = "";
							var result = returnValue.split(",");
							//debugger;
							for (var i=0; i<result.length; i++){
								if(result[i].length>1){
									var v = result[i].split(":");
									roleIds +=  v[0];
									roleNames += v[1];
									break;
								}
							}
							//$("#addDataRowMetaDataIds").val(roleIds);
							$("#propertyType").val(roleNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
	
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
	<form action="" id="baseMetaDataExtForm">

		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<s:hidden id="metaDataId" name="metaDataId" value="%{metaDataId}" theme="simple" />

		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td width="15%" align="right">属性描述:</td>
					<td width="35%"><input id="propertyName" name="entityForm.propertyName" value="${entity.propertyName}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td width="15%" align="right">编码:</td>
					<td width="35%"><input id="propertyCode" name="entityForm.propertyCode" value="${entity.propertyCode}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">属性:</td>
					<td><input id="property" name="entityForm.property" value="${entity.property}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">属性类别:</td>
					<td><s:select list="#{\"0\":\"简单属性\"}" name="entityForm.isCollectionType" value="%{entity.isCollectionType}" theme="simple"></s:select></td>
				</tr>
				<tr height="40">
					<td align="right">简单类型:&nbsp;</td>
					<td><s:select id="dataType" name="entityForm.dataType" list="#request.sjlx" listKey="key" listValue="value" value="%{entity.dataType}" theme="simple"></s:select></td>
					<td align="left"></td>
					<td align="left"></td>
				</tr>
				<%-- <tr height="40">
				<td align="right">数据类型:&nbsp;</td>
				<td>
					<input type="text" id="propertyType" name="entityForm.propertyType" readonly="readonly"  size="30" value="${entity.propertyType}"/>
				</td>
				<td align="left"><input class="btn" type="button" value="选择" onclick="chooseMetaDataType();"/></td>
				<td align="left"></td>
			</tr> --%>
				<tr height="40">
					<td align="right">是否显示:</td>
					<td><s:radio list="#{\"0\":\"否\",\"1\":\"是\"}" name="entityForm.isSelectView" value="%{entity.isSelectView}" theme="simple"></s:radio></td>
					<td align="right">默认值:</td>
					<td><input id="defaultValue" name="entityForm.defaultValue" value="${entity.defaultValue}" type="text" size="30" /></td>
				</tr>
				<%-- <tr>
				<td align="right">名称:</td>
				<td><input id="categoryName" name="entityForm.categoryName" value="${entity.categoryName}" type="text"  size="30" class="validate[required] text-input"/><span style="color: red;">*</span></td>
				<td align="right">编码:</td>
				<td><input id="code" name="entityForm.code" value="${entity.code}" type="text"  size="30" class="validate[required] text-input"/><span style="color: red;">*</span></td>
			</tr>
			<tr>
				<td align="right">名称:</td>
				<td><input id="categoryName" name="entityForm.categoryName" value="${entity.categoryName}" type="text"  size="30" class="validate[required] text-input"/><span style="color: red;">*</span></td>
				<td align="right">编码:</td>
				<td><input id="code" name="entityForm.code" value="${entity.code}" type="text"  size="30" class="validate[required] text-input"/><span style="color: red;">*</span></td>
			</tr> --%>
			</table>
		</div>
	</form>
</div>
<!-- content -->
