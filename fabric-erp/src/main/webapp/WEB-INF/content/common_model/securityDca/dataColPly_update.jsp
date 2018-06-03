<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>
<script type="text/javascript">

var dataStatus = [
    		    {viewId:'YES',viewName:'可视'},
    		    {viewId:'NO',viewName:'不可视'}
    		];
function dataStatusFormatter(value){
	if(value==null || value=='Y'){
		return "是";
	}
	return "否";
};

$(function(){
	$('#dataColGrid').datagrid({
		title:'列级权限编辑',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		singleSelect:true,
		url:'${vix}/common/security/dataColPolicyAction!goSaveOrUpdateData.action',
		onClickRow: onClickRow,
		//onClickCell: onClickCell,
		queryParams:{'id':'${id}',
					'dataConfigId':'${dataConfigId}'
			},
		remoteSort: false,
		idField:'mdpId',
		columns:[[
			{field:'propertyName',title:"属性名称",align:'center',width:200},
			{field:'type',title:"类型",align:'center',width:150,
				formatter:function(value){
					if(value=="D"){
						return "基础属性";
					}else{
						return "扩展属性";
					}
				}
			},
			{field:'viewListStatus',title:"列表页可视",align:'center',width:200,
				formatter:dataStatusFormatter,  
		        editor:{  
		            type:'checkbox',  
		            options:{on:'Y',off:'N'}  
		        }
			},
			{field:'viewDetailStatus',title:"详情页可视",align:'center',width:200,  
		        formatter:dataStatusFormatter,
		         editor:{  
		            type:'checkbox',  
		            options:{on:'Y',off:'N'}  
		        }
			}
		]]
		
	});
	
	
});




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
	 /* if (endEditing()){  
		 $('#dataColGrid').datagrid('selectRow', index).datagrid('editCell', {index:index,field:field});  
		 editIndex = index;
	 } */
	 //alert( editIndex+"--"+index);
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


function saveOrUpdate(){
	
	if(editIndex!=undefined){
		$('#dataColGrid').datagrid('endEdit', editIndex);
	}else{
		showMessage("没有修改信息!");
		setTimeout("", 1000);
		return;
	}
	var rows = $('#dataColGrid').datagrid('getChanges');
	if(rows.length==0){
		showMessage("没有修改信息!");
		setTimeout("", 1000);
		return;
	}
	var paramValue = new Array();
	//id='+id +"&name="+name+"&dataConfigId="+dataConfigId
	var paramStr = "id=${id}&dataConfigId=${dataConfigId}";
	for(var i=0;i<rows.length;i++){
		paramStr += "&jsonParam="+encodeURIComponent(JSON.stringify(rows[i]));
	}
	$.post('${vix}/common/security/dataColPolicyAction!saveOrUpdate.action',
		paramStr,
		function(result){
			showMessage(result);
			setTimeout("", 1000);
			//loadContent('${vix}/common/security/dataColPolicyAction!goList.action?name=${name}&dataConfigId=${dataConfigId}');
			accept();
		}
	 );
}
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
				<li><a href="#"><s:text name="system_control_org_sec_dataPly" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/common/security/dataColPolicyAction!goList.action?name=${name}&dataConfigId=${dataConfigId}');"><span>返回</span></a>
		</p>
	</div>
</div>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="table" style="margin: 5px;">
		<table id="dataColGrid" class="easyui-datagrid" title="列级权限编辑"></table>
	</div>
	<div class="sub_menu sub_menu_bot">
		<div class="drop">
			<p>
				<a href="#" onclick="saveOrUpdate()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/common/security/dataColPolicyAction!goList.action?name=${name}&dataConfigId=${dataConfigId}');"><span>返回</span></a>
			</p>
		</div>
	</div>
</div>
