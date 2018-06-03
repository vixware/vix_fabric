<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--

function deleteDataRowOwnerRole(row,id){
	$(row).parent().parent().remove();
	$("#deleteDataRowMetaDataIds").val($("#deleteDataRowMetaDataIds").val()+","+id);
}

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="dataRowMethodForm" name="dataRowMethodForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td width="20%" align="right">hql类全称:&nbsp;</td>
					<td width="80%"><input type="text" id="hqlProvider" name="entityForm.hqlProvider" value="${entity.hqlProvider}" class="validate[required] text-input" data-text-tooltip="必须填写！" style="width: 80%; border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">hql类的方法集合:&nbsp;</td>
					<td><input type="text" id="methodList" name="entityForm.methodList" value="${entity.methodList}" data-text-tooltip="必须填写！" class="validate[required] text-input sweet-tooltip" style="width: 80%; border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
			</table>
			<!-- <hr/> -->
			<input type="hidden" id="addDataRowMetaDataIds" name="addDataRowMetaDataIds" value="" /> <input type="hidden" id="deleteDataRowMetaDataIds" name="deleteDataRowMetaDataIds" value="" />
			<div class="table" style="margin: 5px;">
				<%-- <p id="table_top" style="border: 1px solid #D6D6D6;color: #CDCDCD;padding: 5px;">
				<img src="${vix}/common/img/icon_22.gif"/><a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">元数据列表</a>
			</p> --%>
				<%-- <table id="brandSub" class="list">
				<tr>
					<th width="10%">编号</th>
					<th width="80%">名称</th>
					<th width="10%">操作</th>
				</tr>
				<s:iterator value="entity.roles" var="role" status="s">
					<tr>
						<td align="right">${s.count}</td>
						<td align="right">${role.name}</td>
						<td align="center">
							<a href="#" onclick="deleteDataRowOwnerRole(this,${role.id});" title="删除">
								<img src="${vix}/common/img/icon_12.png"/>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table> --%>
				<%-- <table id="dimensionSub" class="list" style="display:none;">
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
							<a href="#" onclick="deleteUserAccountRoleDimension(this,${entity.id});" title="删除">
								<img src="${vix}/common/img/icon_12.png"/>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table> --%>
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