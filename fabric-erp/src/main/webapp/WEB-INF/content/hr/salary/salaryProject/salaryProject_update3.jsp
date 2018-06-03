<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">

	<div class="box order_table" style="line-height: normal;">
		<form action="" id="organizationUnitForm" name="organizationUnitForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<tr height="40">
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="projectName" name="entityForm.projectName" value="${entity.projectName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">是否启用:&nbsp;</td>
					<td><s:radio id="isActive" list="#{\"Y\":\"是\",\"N\":\"否\"}" name="entityForm.isActive" value="%{entity.isActive}" theme="simple"></s:radio>
						<%--onchange="changePubType(this.value);"  --%></td>
				</tr>
				<tr height="40">
					<td align="right">位数处理:&nbsp;</td>
					<td><input type="text" id="dotHandler" name="entityForm.dotHandler" value="${entity.dotHandler}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">输出格式:&nbsp;</td>
					<td><input type="text" id="exportFormat" name="entityForm.exportFormat" value="${entity.exportFormat}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">输出长度:&nbsp;</td>
					<td><input type="text" id="printLenth" name="entityForm.printLenth" value="${entity.printLenth}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">描述:&nbsp;</td>
					<td><input type="text" id="projectNote" name="entityForm.projectNote" value="${entity.projectNote}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">启用时间:&nbsp;</td>
					<td><input type="text" id="startTime" name="entityForm.startTime" value="<s:date name='%{entity.startTime}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img onclick="showTime('startUsingDate','yyyy-MM-dd')"
						src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">停用时间:&nbsp;</td>
					<td><input type="text" id="endTime" name="entityForm.endTime" value="<s:date name='%{entity.endTime}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img onclick="showTime('stopUsingDate','yyyy-MM-dd')"
						src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
			<!-- <hr/> -->
			<input type="hidden" id="addCategoryBrandIds" name="addCategoryBrandIds" value="" /> <input type="hidden" id="deleteCategoryBrandIds" name="deleteCategoryBrandIds" value="" />

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