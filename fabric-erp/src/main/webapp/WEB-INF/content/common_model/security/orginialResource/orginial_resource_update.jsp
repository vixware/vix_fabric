<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
/* function chooseParentOrganization(){
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
									showErrorMessage("不允许引用本资源为父资源!");
									setTimeout("", 1000);
									return false;
								}else{
									$("#parentId").val(result[0]);
									$("#organizationName").html(result[1]);
								}
							}else{
								$("#parentId").val("");
								$("#organizationName").html("");
								showErrorMessage("请选择父资源信息!");
								setTimeout("", 1000);
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
} */


function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<form action="" id="resourceForm" name="resourceForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<tr height="40">
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="name" name="entityForm.name" value="${entity.name}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">显示名称:&nbsp;</td>
					<td><input type="text" id="displayName" name="entityForm.displayName" value="${entity.displayName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<%-- <td align="right">是否禁用:&nbsp;</td>
				<td>
					<s:if test="category.status == 0">
						<input type="radio" id="status1" name="status" value="1"/>是
						<input type="radio" id="status2" name="status" value="0" checked="checked"/>否
					</s:if>
					<s:elseif test="category.status == 1">
						<input type="radio" id="status1" name="status" value="1" checked="checked"/>是
						<input type="radio" id="status2" name="status" value="0"/>否
					</s:elseif>
					<s:else>
						<input type="radio" id="status1" name="status" value="1"/>是
						<input type="radio" id="status2" name="status" value="0"/>否
					</s:else>
				</td>
				<td align="right">备注:&nbsp;</td>
				<td><s:textfield id="memo" name = "category.memo" value="%{category.memo}" theme="simple"/></td> --%>
				</tr>
				<!-- <tr height="30">
				<td align="right">新增品牌:</td>
				<td><a id="addCategoryBrand" href="#" onclick="addCategoryBrand();" style="color: #000000; text-decoration: none;">添加</a></td>
				<td align="right">品牌:</td>
				<td colspan="3"><span id="addCategoryBrandNames"></span></td>
			</tr> -->
				<tr height="40">
					<td align="right">优先级:&nbsp;</td>
					<td><input type="text" id="priority" name="entityForm.priority" value="${entity.priority}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">控制URL:&nbsp;</td>
					<td><input type="text" id="url" name="entityForm.url" value="${entity.url}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">描述:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="entityForm.memo" value="${entity.memo}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
			</table>
			<!-- <hr/> -->
		</form>
		<%-- 
		<div class="table" style="margin: 5px;">
			<p id="table_top" style="border: 1px solid #D6D6D6;color: #CDCDCD;padding: 5px;">
				<img src="${vix}/common/img/icon_22.gif"/><a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">品牌列表</a>
			</p>
			<table id="brandSub" class="list">
				<tr>
					<th width="10%">编号</th>
					<th width="80%">名称</th>
					<th width="10%">操作</th>
				</tr>
				<s:iterator value="category.brands" var="entity" status="s">
					<tr>
						<td>${s.count}</td>
						<td>${entity.name}</td>
						<td align="center">
							<a href="#" onclick="deleteCategoryBrand(this,${entity.id});" title="删除">
								<img src="${vix}/common/img/icon_12.png"/>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
			<table id="dimensionSub" class="list" style="display:none;">
				<tr>
					<th width="10%">编号</th>
					<th width="80%">名称</th>
					<th width="10%">操作</th>
				</tr>
				<s:iterator value="category.dimensions" var="entity" status="s">
					<tr>
						<td>${s.count}</td>
						<td>${entity.name}</td>
						<td align="center">
							<a href="#" onclick="deleteCategoryDimension(this,${entity.id});" title="删除">
								<img src="${vix}/common/img/icon_12.png"/>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div> --%>
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