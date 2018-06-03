<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
function chooseParentOrganization(){
	$.ajax({
		  url:'${vix}/common/org/organizationUnitAction!goChooseOrganization.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择上级",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									showErrorMessage("不允许引用本部门为父部门!");
									setTimeout("", 1000);
									return false;
								}else{
									$("#parentId").val(result[0]);
									$("#parentTreeName").val(result[1]);
									$("#parentTreeType").val(result[2]);
								}
							}else{
								$("#parentId").val("");
								$("#parentTreeName").val("");
								$("#parentTreeType").val("");
								showErrorMessage("请选择部门信息!");
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


function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}

/**
 * 负责人选择
 */
function chooseManager(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"radio"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 340,
					title:"选择负责人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								var resultLength = result.length;
								if(result[resultLength-1].length>1){
									var v = result[resultLength-1].split(":");
									selectIds += "," + v[0];
									selectNames += "," + v[1];
								}
								$("#managerEmpId").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#managerEmpName").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">

	<div class="box order_table" style="line-height: normal;">
		<form action="" id="organizationUnitShowForm" name="organizationUnitShowForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<tr>
					<td align="right">上级机构:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="parentId" name="parentId" value="${parentId}" /> <input type="hidden" id="parentTreeType" name="parentTreeType" value="${parentTreeType}" /> <input type="text" id="parentTreeName" name="parentTreeName" readonly="readonly" value="${parentTreeName}" /> <input class="btn" type="button" value="选择"
						onclick="chooseParentOrganization();" /></td>
				</tr>
				<tr height="40">
					<td align="right">负责人:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="managerEmpId" name="entityForm.manager.id" value="${entity.manager.id}" /> <input type="text" id="managerEmpName" name="managerEmpName" readonly="readonly" value="${entity.manager.name}" /> <input class="btn" type="button" value="选择" onclick="chooseManager();" /></td>
				</tr>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="orgCode" name="entityForm.orgCode" readonly="readonly" value="${entity.orgCode}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">简称:&nbsp;</td>
					<td><input type="text" id="fs" name="entityForm.fs" readonly="readonly" value="${entity.fs}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				<tr height="40">
					<td align="right">组织单元类型:&nbsp;</td>
					<td><s:select list="#{'JZBM':'基准部门','XSBGS':'销售办公室','XSZ':'销售组'}" name="entityForm.unitType" readonly="readonly" value="%{entity.unitType}" theme="simple"></s:select></td>
					<td align="right">业务类型:&nbsp;</td>
					<td><input type="text" id="bizUnitType" name="entityForm.bizUnitType" readonly="readonly" value="${entity.bizUnitType}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">全称:&nbsp;</td>
					<td><input type="text" id="fullName" name="entityForm.fullName" readonly="readonly" value="${entity.fullName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">描述:&nbsp;</td>
					<td><input type="text" id="description" name="entityForm.description" readonly="readonly" value="${entity.description}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">启用时间:&nbsp;</td>
					<td><input type="text" id="startUsingDate" name="entityForm.startUsingDate" readonly="readonly" value="<s:date name='%{entity.startUsingDate}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img
						onclick="showTime('startUsingDate','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">停用时间:&nbsp;</td>
					<td><input type="text" id="stopUsingDate" name="entityForm.stopUsingDate" readonly="readonly" value="<s:date name='%{entity.stopUsingDate}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img
						onclick="showTime('stopUsingDate','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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