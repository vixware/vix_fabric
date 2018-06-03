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
									asyncbox.success("不允许引用本部门为父部门!","提示信息");
								}else{
									$("#parentId").val(result[0]);
									$("#parentTreeName").val(result[1]);
									$("#parentTreeType").val(result[2]);//parentTreeType
								}
							}else{
								$("#parentId").val("");
								$("#parentTreeName").val("");
								$("#parentTreeType").val("");
								asyncbox.success("请选择部门信息!","提示信息");
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
 * 业务组织选择
 */
function chooseBusinessOrg(){
	
	var entityBusinessOrgName = $("#entityBusinessOrgName");
	var entityBusinessOrgNameOffset = $("#entityBusinessOrgName").offset();
	
	var left1 = entityBusinessOrgNameOffset.left-330;
	var top1 = entityBusinessOrgNameOffset.top  ;
	alert(entityBusinessOrgName.outerHeight()+"--"+top1);
	$("#menuContent").css({left:left1 + "px", top:top1 + entityBusinessOrgName.outerHeight()-120 + "px"}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "entityBusinessOrgName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}




$(document).ready(function(){
	
		
	var chooseBusinessOrgTree;			
	var setting = {
		check: {
			enable: true,
			chkboxType: {"Y":"", "N":""}
		},
		view: {
			dblClickExpand: false
		},
		callback: {
			beforeClick: beforeClick,
			onCheck: onCheck
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		}
	};
	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgUnitBoTree");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgUnitBoTree");
		var nodes = zTree.getCheckedNodes(true);
		var objNames = "";
		var objIds ="";
		for (var i=0, l=nodes.length; i<l; i++) {
			objNames += nodes[i].name + ",";
			objIds += nodes[i].id + ",";
		}
		if (objNames.length > 0 ) objNames = objNames.substring(0, objNames.length-1);
		if (objIds.length > 0 ) objIds = objIds.substring(0, objIds.length-1);
		
		$("#entityBusinessOrgId").attr("value", objIds);
		$("#entityBusinessOrgName").attr("value", objNames);
	}
	
	
	vixAjaxSendHtml("${vix}/common/org/organizationUnitAction!goSaveOrUpdateJson.action","id=${id}",function(data){
		//debugger;
		var treeData = $.parseJSON(data);
		chooseBusinessOrgTree = $.fn.zTree.init($("#orgUnitBoTree"), setting,treeData);
	});
	//
	//alert($("#entityBusinessOrgJsonStr").val());
	//var zNodes = $.parseJSON($("#entityBusinessOrgJsonStr").val());
	//$.fn.zTree.init($("#orgUnitBoTree"), setting, zNodes);
}); 
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">

	<div class="box order_table" style="line-height: normal;">
		<form action="" id="organizationUnitForm" name="organizationUnitForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<tr>
					<td align="right">上级机构:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="parentId" name="parentId" value="${parentId}" /> <input type="hidden" id="parentTreeType" name="parentTreeType" value="${parentTreeType}" /> <%-- <span id="parentTreeName"><s:property value="parentTreeName"/></span> --%> <input type="text" id="parentTreeName" name="parentTreeName"
						readonly="readonly" value="${parentTreeName}" /> <input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /></td>
				</tr>
				<tr>
					<td align="right">业务组织:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="entityBusinessOrgJsonStr" name="entityBusinessOrgJsonStr" value="${entityBusinessOrgJsonStr}" /> <input type="hidden" id="entityBusinessOrgId" name="entityBusinessOrgId" value="${entityBusinessOrgId}" /> <%-- <span id="parentTreeName"><s:property value="parentTreeName"/></span> --%> <input
						type="text" id="entityBusinessOrgName" name="entityBusinessOrgName" readonly="readonly" value="${entityBusinessOrgName}" /> <input id="menuBtn" class="btn" type="button" value="选择" onclick="chooseBusinessOrg();return false;" /></td>
				</tr>
				<tr height="40">
					<td align="right">编码:&nbsp;</td>
					<td><input type="text" id="orgCode" name="entityForm.orgCode" value="${entity.orgCode}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">简称:&nbsp;</td>
					<td><input type="text" id="fs" name="entityForm.fs" value="${entity.fs}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
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
					<td align="right">组织单元类型:&nbsp;</td>
					<td><s:select list="#{'JZBM':'基准部门','XSBGS':'销售办公室','XSZ':'销售组'}" name="entityForm.unitType" value="%{entity.unitType}" theme="simple"></s:select></td>
					<td align="right">业务类型:&nbsp;</td>
					<td><input type="text" id="bizUnitType" name="entityForm.bizUnitType" value="${entity.bizUnitType}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">全称:&nbsp;</td>
					<td><input type="text" id="fullName" name="entityForm.fullName" value="${entity.fullName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">描述:&nbsp;</td>
					<td><input type="text" id="description" name="entityForm.description" value="${entity.description}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
				</tr>
				<tr height="40">
					<td align="right">启用时间:&nbsp;</td>
					<td><input type="text" id="startUsingDate" name="entityForm.startUsingDate" value="<s:date name='%{entity.startUsingDate}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img
						onclick="showTime('startUsingDate','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">停用时间:&nbsp;</td>
					<td><input type="text" id="stopUsingDate" name="entityForm.stopUsingDate" value="<s:date name='%{entity.stopUsingDate}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img
						onclick="showTime('stopUsingDate','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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

<div id="menuContent" class="menuContent" style="position: absolute;">
	<ul id="orgUnitBoTree" class="ztree" style="margin-top: 0; width: 180px; height: 300px;"></ul>
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