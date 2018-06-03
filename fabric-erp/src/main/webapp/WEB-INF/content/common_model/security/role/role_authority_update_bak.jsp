<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>
<script type="text/javascript">
<!--

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}

function dataStatusFormatter(value){
	if(value==null || value=='Y'){
		return "是";
	}
	return "否";
}

$(function(){
	
	
});

function initAuthorityData(){
	var bizType = $("#authorityBizType").val();
	$('#dataColGrid').treegrid({
		title:'权限列表',
		/* width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true, */
		singleSelect:true,
		url:'${vix}/common/security/roleAction!findRoleAuthority.action?roleId=${id}&bizType='+bizType,
		//queryParams:{'roleId':'${id}'},
		//remoteSort: false,
		idField:'id',
		treeField:'displayName',
		frozenColumns:[[
		    			{field:'checkId',title:"授权",align:'center',width:100,
							formatter: function(value,row,index){
								var resVal = "<input type='checkbox' name='checkAuthority' id='selectCheck|"+value+"' value='"+value+"' ";
								if(row.isCheck=="Y"){
									resVal += " checked />";
								}
								return resVal;
							}
						}
		    		]], 
		onBeforeLoad: function(row,param){  
           if (!row) { 
        	   //$(this).treegrid('options').url="${vix}/common/security/roleAction!findRoleAuthority.action?id=${id}&treeId="+row.treeId;
                param.id = -1;    
            }
        } ,  
		columns:[[
			{field:'displayName',title:"名称",align:'left',width:400},
			{field:'authType',title:"类型",align:'center',width:150,
				formatter:function(value){
					if(value=="M"){
						return "菜单";
					}else if(value=="F"){
						return "功能";
					}else {
						return "其他";
					}
				}
			},
			{field:'memo',title:"备注",align:'center',width:200}
		]]
			
	});
}

/* var editIndex = undefined;  
function endEditing(){  
    if (editIndex == undefined){return true}  
    if ($('#dataColGrid').datagrid('validateRow', editIndex)){  
    	$('#dataColGrid').datagrid('endEdit', editIndex);  
        editIndex = undefined;  
        return true;  
    } else {  
        return false;  
    }  
}   
*/


var editIndex = undefined; 
function endEditing(){  
	if (editIndex == undefined){return true;}  
	if ($('#dataColGrid').datagrid('validateRow', editIndex)){  
		
		/* var ed = $('#dataColGrid').datagrid('getEditor', {index:editIndex,field:'viewId'});
		var viewName = $(ed.target).combobox('getText');  
		$('#dataColGrid').datagrid('getRows')[editIndex]['viewName'] = viewName;   */
		$('#dataColGrid').datagrid('endEdit', editIndex);  
		 editIndex = undefined; 
		 return true;  
	}else{
		return false; 
	}
	
}

function onClickRow(index,field){  
	 if (editIndex != index){
		 if (endEditing()){
			 $('#dataColGrid').datagrid('selectRow', index).datagrid('beginEdit', index);
			 editIndex = index; 
		 }else{
			 $('#dataColGrid').datagrid('selectRow', editIndex); 
		 }
	 }
} 

function append(){  
    if (endEditing()){  
        editIndex = $('#dataColGrid').datagrid('getRows').length-1;  
        $('#dataColGrid').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);  
    }  
}  
function removeit(){  
    if (editIndex == undefined){return}  
    $('#dataColGrid').datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);  
    editIndex = undefined;  
}  
function accept(){  
    if (endEditing()){  
        $('#dataColGrid').datagrid('acceptChanges');  
    }  
}  
function reject(){  
    $('#dataColGrid').datagrid('rejectChanges');  
    editIndex = undefined;  
}  
function getChanges(){  
    var rows = $('#dataColGrid').datagrid('getChanges');  
   // alert(rows.length+' rows are changed!');  
}  


function saveForAuthority(){
	var checkValArray = "a=1";
	 $('input[name="checkAuthority"]:checked').each(function(){
		 checkValArray+="&checkAuthority="+$(this).val();
	 });
	 //alert(checkValArray);
	 $.post('${vix}/common/security/roleAction!saveForAuthority.action?roleId=${id}',
		checkValArray,
		function(result){
			asyncbox.success(result,"提示信息",function(action){
				//loadContent('${vix}/common/security/dataColPolicyAction!goList.action?name=${name}&dataConfigId=${dataConfigId}');
				//accept();
			});
		}
	 );
	/* if(editIndex!=undefined){
		$('#dataColGrid').datagrid('endEdit', editIndex);
	}else{
		asyncbox.alert("没有修改信息!","提示信息");
	}
	var rows = $('#dataColGrid').datagrid('getChanges');
	if(rows.length==0){
		asyncbox.alert("没有修改信息!","提示信息");
		return;
	}
	var paramValue = new Array();
	//id='+id +"&name="+name+"&dataConfigId="+dataConfigId
	var paramStr = "id=${id}&dataConfigId=${dataConfigId}";
	for(var i=0;i<rows.length;i++){
		paramStr += "&jsonParam="+encodeURIComponent(JSON.stringify(rows[i]));
	}
	$.post('${vix}/common/security/roleAction!saveOrUpdate.action',
		paramStr,
		function(result){
			asyncbox.success(result,"提示信息",function(action){
				//loadContent('${vix}/common/security/dataColPolicyAction!goList.action?name=${name}&dataConfigId=${dataConfigId}');
				accept();
			});
		}
	 ); */
}
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec_role" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec_role_authority" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveForAuthority()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/common/security/roleAction!goList.action');"><span>返回</span></a>
		</p>
	</div>
</div>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="c_head" class=" drop">
		<div>
			<label>权限分类:<s:select list="bizTypeMap" id="authorityBizType" listKey="key" listValue="value" onchange="initAuthorityData();" theme="simple"></s:select></label>
			<%-- <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();"/></label> --%>
		</div>
	</div>
	<div class="table" style="margin: 5px;">
		<table id="dataColGrid" class="easyui-datagrid" title="权限分配"></table>

		<!-- <table id="test1" title="Folder Browser" class="easyui-treegrid" style="width:700px;height:250px">
			<thead>
				<tr>
					<th data-options="field:'name'" width="220">Name</th>
					<th data-options="field:'size'" width="100" align="right">Size</th>
					<th data-options="field:'date'" width="150">Modified Date</th>
				</tr>
			</thead>
		</table>  -->
	</div>
	<div class="sub_menu sub_menu_bot">
		<div class="drop">
			<p>
				<a href="#" onclick="saveForAuthority()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/common/security/roleAction!goList.action?roleName=${roleName}');"><span>返回</span></a>
			</p>
		</div>
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