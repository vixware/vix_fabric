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

//选择元数据的id
function loadChoosMetaDataId(){
	var queryString = "metaDataName=" + $("#propertyType").val();
	/* $.post('${vix}/common/model/baseMetaDataAction!findMetaDataByName.action',
		queryString,
		function(result){
			//debugger;
			var msgObj = $.parseJSON(result);
			//var msg = msgObj.msg;
			var objId = msgObj.objId;
			$("#propertyTypeId").val(objId);
		}
	 ); */
	
	vixAjaxSend(
			'${vix}/common/model/baseMetaDataAction!findMetaDataByName.action',
			queryString,
			function(result){
				//var msgObj = $.parseJSON(result);
				//var msg = msgObj.msg;
				var objId = result.objId;
				$("#propertyTypeId").val(objId);
			}
	);
}

function chooseMetaDataType(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectMetaDataAction!goChooseMetaData.action?tag=choose&chkStyle=radio',//dataResRowOwnerAction
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
							//$("#propertyTypeId").val(roleIds);
							$("#propertyType").val(roleNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
	
}


/**
 * 元数据属性的对象可编辑列表
 */
function loadPropertyMetaDataGrid(){
	var metaDataName = $("#propertyType").val();
	
	$('#dgdB1').datagrid({
		//title:'元数据属性信息 ',
		iconCls:'icon-save',
		width:800,
		height:350,
		singleSelect: true,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/model/baseMetaDataDefineAction!goPropertyObjList.action?metaDataName='+metaDataName,
		remoteSort: false,
		idField:'id',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{field:'propertyName',title:'元数据名称 ',width:200}
		]],
		columns:[[
			{field:'propertyCode',title:'编码 ',width:120},
			{field:'property',title:'属性 ',width:150},
			{field:'propertyType',title:'数据类型 ',width:320,
				formatter:function(value,rowData, rowIndex){
					var isCollectionTypeTmp = rowData.isCollectionType;
					
					if(isCollectionTypeTmp==null || isCollectionTypeTmp=="null" || isCollectionTypeTmp=='0'){
						//return "基本属性";
						var dataType = rowData.dataType;
						if( dataType== 'Integer'){
							return "整型";
						}else if(dataType== 'Long'){
							return "长整型";
						}else if(dataType== 'Double'){
							return "双精度";
						}else if(dataType== 'Date'){
							return "日期";
						}else if(dataType== 'String'){
							return "字符串";
						}
						return "";
					}else if(isCollectionTypeTmp=='1' || isCollectionTypeTmp=='2'){
						return value;
					}
					
					return "";
					
				}	
			},
			{field:'isCollectionType',title:'属性类型 ',width:80,
				formatter:function(value){
					if(value==null || value=="null" || value=='0'){
						return "基本属性";
					}else if(value=='1'){
						return "业务对象属性";
					}else if(value=='2'){
						return "集合属性";
					}
				}	
			},
			{field:'isSelectView',title:'是否显示 ',width:80,
				formatter:function(value){
					
					if(value==null || value=="null" ||  value=="0" ){
						return "否";
					}else if(value=="1"){
						return "是A";
					}
					return "";
				}		
			
			},
			{field:'defaultValue',title:'默认值 ',width:120}
		]],
		pagination:false,
		rownumbers:true
	});
}	


var dataTypeArray = [
    		    {dataTypeId:'Integer',dataTypeValue:'整型'},
    		    {dataTypeId:'Long',dataTypeValue:'长整型'},
    		    {dataTypeId:'Double',dataTypeValue:'双精度'},
    		    {dataTypeId:'Date',dataTypeValue:'日期'},
    		    {dataTypeId:'String',dataTypeValue:'字符串'}
    		];



/**
 * 元数据属性的对象可编辑列表
 */
function loadExtPropertyMetaDataGrid(){
	var metaDataName = $("#propertyType").val();
	
	//$('#dgdB2').datagrid({
	$('#dgdB1').datagrid({
		//title:'元数据属性信息 ',
		iconCls:'icon-save',
		width:800,
		height:250,
		singleSelect: true,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/model/baseMetaDataDefineAction!goExtPropertyObjList.action?metaDataName='+metaDataName,
		remoteSort: false,
		idField:'id',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{field:'propertyName',title:'元数据名称 ',width:200,editor:'text'}
		]],
		columns:[[
			{field:'propertyCode',title:'编码 ',width:120,editor:'text'},
			{field:'property',title:'属性 ',width:150,editor:'text'},
			{field:'dataType',title:'数据类型',width:120,
				formatter:function(value,row){
					for(var i=0; i<dataTypeArray.length; i++){
						if (dataTypeArray[i].dataTypeId == value) return dataTypeArray[i].dataTypeValue;
					}
					return value;
				},
				editor:{
					type:'combobox',
					options:{
						valueField: 'dataTypeId',
						textField: 'dataTypeValue',
						data: dataTypeArray,
						required:true
					}
				}
			
			},
			{field:'isCollectionType',title:'属性类型 ',width:80,
				formatter:function(value){
					if(value==null || value=="null" || value=='0'){
						return "基本属性";
					}else if(value=='1'){
						return "业务对象属性";
					}else if(value=='2'){
						return "集合属性";
					}
				}	
			},
			/* {field:'isSelectView',title:'是否显示 ',width:80,
				formatter:function(value){
					if(value==null || value=="null"){
						return "否";
					}else{
						return "是";
					}
				},
				editor:{
					type:'combobox',
					options:{
						valueField: 'label',
						textField: 'value',
						data: [{
							label: 'null',
							value: '否'
						},{
							label: '1',
							value: '是'
						}],
						required:true
					}
				}
			
			}, */
			{field:'defaultValue',title:'默认值 ',width:120,editor:'text'}
		]],
		onClickRow: onClickRow,
		pagination:false,
		rownumbers:true,
		toolbar:[{
			id:'btnaddA1',
			text:'增加',
			iconCls:'icon-add',
			handler:function(){
				addExtPropertyGrid();
			}
		},{
			id:'btncutA1',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				//$('#btnsave').linkbutton('enable');
				//alert('cut')
				var idVal = getDataGridId("dgdB1");
				if(idVal!=''){
					asyncbox.confirm('确定要删除么?','提示信息',function(action){
						if(action == 'ok'){
							var queryString = "id="+idVal;
							$.post('${vix}/common/model/baseMetaDataDefineAction!deleteExtById.action',
								queryString,
								function(result){
									showMessage(result);
									setTimeout("", 1000);
									loadExtPropertyMetaDataGrid();
								}
							 );
						}
					});
				}
				
			}
		},{
			id:'btnsaveA1',
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				//$('#btnsave').linkbutton('enable');
				saveExtPropertyGrid();
			}
		}]
	});
}	

var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if ($('#dgdB1').datagrid('validateRow', editIndex)){
		//var ed = $('#dgdB1').datagrid('getEditor', {index:editIndex,field:'dataType'});
		//var dataTypeValueTmp = $(ed.target).combobox('getText');
		//$('#dgdB1').datagrid('getRows')[editIndex]['dataTypeValue'] = dataTypeValueTmp;
		//$('#dgdB1').datagrid('endEdit', editIndex);
		//editIndex = undefined;
		//return true;
		$('#dgdB1').datagrid('endEdit', editIndex);  
		 editIndex = undefined; 
		 return true;  
	} else {
		return false;
	}
}
function onClickRow(index,field){  
	 if (editIndex != index){
		 if (endEditing()){
			 $('#dgdB1').datagrid('selectRow', index).datagrid('beginEdit', index);
			 editIndex = index; 
		 }else{
			 $('#dgdB1').datagrid('selectRow', editIndex); 
		 }
	 }
} 
//增加
function addExtPropertyGrid(){
	if (endEditing()){
		$('#dgdB1').datagrid('appendRow',{});//,{status:'P'}
		editIndex = $('#dgdB1').datagrid('getRows').length-1;
		$('#dgdB1').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	}
}
//保存扩展属性
function saveExtPropertyGrid(){
	if(editIndex!=undefined){
		$('#dgdB1').datagrid('endEdit', editIndex);
	}else{
		showErrorMessage("没有修改信息!");
		setTimeout("", 1000);
		return;
	}
	
	var rows = $('#dgdB1').datagrid('getChanges');
	if(rows.length==0){
		epmAlertInfo("提示","没有操作数据！");
		return;
	}
	
	loadChoosMetaDataId();
	
	var paramValue = new Array();
	var paramStr = "metaDataId="+$("#propertyTypeId").val();
	for(var i=0;i<rows.length;i++){
		//alert(json2str(rows[i]));
		//paramValue.push(json2str(rows[i]));
		//alert(replaceAllEpm(json2str(rows[i]),"\"",""));
		
		paramStr += "&jsonParam="+encodeURIComponent(JSON.stringify(rows[i]));
		//paramStr += "&jsonParam="+encodeURIComponent(replaceAllEpm(json2str(rows[i]),"\"",""));
	}
	
	$.post('${vix}/common/model/baseMetaDataDefineAction!saveOrUpdateExt.action',
		paramStr,
		function(result){
			showMessage(result);
			setTimeout("", 1000);
			loadExtPropertyMetaDataGrid();
			//asyncbox.success(result,"提示信息",function(action){
			   /*  if (endEditing()){  
			        $('#dgdB1').datagrid('acceptChanges');  
			    } */
			    //alert(action);
			//});
		}
	 );
	
}



$(function(){
	loadPropertyMetaDataGrid();
	//loadExtPropertyMetaDataGrid();
});





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
	<form action="" id="baseMetaDataDefineForm">

		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<s:hidden id="metaDataId" name="metaDataId" value="%{metaDataId}" theme="simple" />

		<input type="hidden" id="propertyTypeId" name="propertyTypeId" value="" />

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
					<td><s:select list="#{\"0\":\"简单属性\",\"1\":\"业务对象属性\",\"2\":\"业务对象集合属性\"}" name="entityForm.isCollectionType" value="%{entity.isCollectionType}" theme="simple"></s:select></td>
				</tr>
				<tr height="40">
					<td align="right">简单类型:&nbsp;</td>
					<td><s:select id="dataType" name="entityForm.dataType" list="#request.sjlx" listKey="key" listValue="value" value="%{entity.dataType}" theme="simple"></s:select></td>
					<td align="left"></td>
					<td align="left"></td>
				</tr>
				<tr height="40">
					<td align="right">数据类型:&nbsp;</td>
					<td><input type="text" id="propertyType" name="entityForm.propertyType" readonly="readonly" size="30" value="${entity.propertyType}" onchange="loadPropertyMetaDataGrid();" /></td>
					<td align="left"><input class="btn" type="button" value="选择" onclick="chooseMetaDataType();" /></td>
					<td align="left"></td>
				</tr>
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
	<div class="clearfix" style="background: #FFF; position: relative;">
		<div class="right_menu">
			<ul>
				<li class="current"><a onclick="javascript:tab(2,1,'b',event);">基本属性</a></li>
				<li><a onclick="javascript:$('#a2_').attr('style','');tab(2,2,'b',event)">自定义属性</a></li>
				<!-- <li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)">帐号</a></li> -->
				<!--<li><a onclick="javascript:$('#a4').attr('style','');tab(8,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />审批</a></li>
						<li><a onclick="javascript:$('#a5').attr('style','');tab(8,5,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						
						<li><a onclick="javascript:$('#a7').attr('style','');tab(8,7,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />交货地点</a></li>
						<li><a onclick="javascript:$('#a6').attr('style','');tab(8,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />附件/文本</a></li>
						<li><a onclick="javascript:$('#a8').attr('style','');tab(8,8,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />审批项</a></li>-->
			</ul>
		</div>
		<div id="b1" style="width: 100%;">
			<%-- <div class="right_btn nomargin">
						<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span>
						<span><a href="#" ><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
					</div> --%>
			<table id="dgdB1"></table>
		</div>
		<div id="b2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
			<%-- <div class="right_btn nomargin">
				<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span>
				<span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span>
				<span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span>
				<span><a href="#"><img src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
			</div>--%>
			<table id="dgdB2"></table>
		</div>
	</div>
</div>
<!-- content -->
