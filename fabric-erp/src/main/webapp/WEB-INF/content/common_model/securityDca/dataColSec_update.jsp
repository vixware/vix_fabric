<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
function chooseCompany(){
	$.ajax({
		  url:'${vix}/common/org/organizationAction!goChooseOrganization.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择公司",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#parentId").val(result[0]);
								$("#parentTreeName").html(result[1]);
							}else{
								$("#parentId").val("");
								$("#parentTreeName").html("");
								showErrorMessage("请选择公司信息!");
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



/**   easy ui
$(function(){
	$('#dataColGrid').datagrid({
		title:'列级权限配置',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'json_dataColGrids/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{title:'Code',field:'code',width:155,sortable:true}
		]],
		columns:[[
			{title:'Base Information',colspan:3},
			{field:'opt',title:'Operation',width:100,align:'center', rowspan:2,
				formatter:function(value,rec){
					return '<span style="color:red">Edit Delete</span>';
				}
			}
		],[
			{field:'name',title:'Name',width:120},
			{field:'addr',title:'Address',width:220,rowspan:2,sortable:true,
				sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'col4',title:'Col41',width:150,rowspan:2}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'Add',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('add')
			}
		},{
			id:'btncut',
			text:'Cut',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},'-',{
			id:'btnsave',
			text:'Save',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	var p = $('#dataColGrid').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			alert('before refresh');
		}
	});
});
function resize(){
	$('#dataColGrid').datagrid('resize', {
		width:700,
		height:400
	});
}
function getSelected(){
	var selected = $('#dataColGrid').datagrid('getSelected');
	if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	}
}
function getSelections(){
	var ids = [];
	var rows = $('#dataColGrid').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].code);
	}
	alert(ids.join(':'));
}
function clearSelections(){
	$('#dataColGrid').datagrid('clearSelections');
}
function selectRow(){
	$('#dataColGrid').datagrid('selectRow',2);
}
function selectRecord(){
	$('#dataColGrid').datagrid('selectRecord','002');
}
function unselectRow(){
	$('#dataColGrid').datagrid('unselectRow',2);
}
function mergeCells(){
	$('#dataColGrid').datagrid('mergeCells',{
		index:2,
		field:'addr',
		rowspan:2,
		colspan:2
	});
} */
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">

	<div class="box order_table" style="line-height: normal;">
		<form action="" id="dataColForm" name="dataColForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<%--  <tr>
				<td align="right">公司:&nbsp;</td>
				<td colspan="3">
					<input type="hidden" id="parentId" name="parentId" value="${parentId}"/>
					<span id="parentTreeName"><s:property value="parentTreeName"/></span>
					<span class="btn"><a href="#" onclick="chooseCompany();">选择</a></span>	
				</td>
			</tr> --%>
				<tr height="40">
					<td align="right">分类名称:&nbsp;</td>
					<td><input type="text" id="configName" name="entityForm.configName" value="${entity.configName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">是否开启:&nbsp;</td>
					<td><s:radio list="#{1:'激活',0:'禁用'}" name="entityForm.flag" value="%{entity.flag}" theme="simple" /></td>
				</tr>
				<!-- <tr height="30">
				<td align="right">新增品牌:</td>
				<td><a id="addCategoryBrand" href="#" onclick="addCategoryBrand();" style="color: #000000; text-decoration: none;">添加</a></td>
				<td align="right">品牌:</td>
				<td colspan="3"><span id="addCategoryBrandNames"></span></td>
			</tr> -->
				<%-- <tr height="40">
				<td align="right">组织单元类型:&nbsp;</td>
				<td><input type="text" id="orgType" name="entityForm.orgType" value="${entity.orgType}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);"/></td>
				<td align="right">业务类型:&nbsp;</td>
				<td><input type="text" id="bizUnitType" name="entityForm.bizUnitType" value="${entity.bizUnitType}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);"/></td>
			</tr>
			<tr height="40">
				<td align="right">全称:&nbsp;</td>
				<td><input type="text" id="fullName" name="entityForm.fullName" value="${entity.fullName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);"/></td>
				<td align="right">描述:&nbsp;</td>
				<td><input type="text" id="description" name="entityForm.description" value="${entity.description}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);"/></td>
			</tr>
			<tr height="40">
				<td align="right">启用时间:&nbsp;</td>
				<td><input type="text" id="startUsingDate" name="entityForm.startUsingDate" value="<s:date name='%{entity.startUsingDate}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);"/><img onclick="showTime('startUsingDate','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				<td align="right">停用时间:&nbsp;</td>
				<td><input type="text" id="stopUsingDate" name="entityForm.stopUsingDate" value="<s:date name='%{entity.stopUsingDate}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);"/><img onclick="showTime('stopUsingDate','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr> --%>
			</table>
			<!-- <hr/> -->
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