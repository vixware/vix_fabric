<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%-- <script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script> --%>
<%-- <link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet"/>
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet"/>
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script> --%>

<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>

<script type="text/javascript">
<!--
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	
	
	$(function(){
		loadMetaDataGrid();
		loadMetaDataGridExt();
	});
	
	
	/**
	 * 添加编辑 基本属性
	 */
	function goSaveUpdateMetaDataDefine(metaDataDefineId){
		var metaDataId = $("#metaDataId").val();//metaDataId
		if(metaDataId=="" || metaDataId=="0"){
			showMessage("请先保存元数据信息！");
			setTimeout("", 1000);
			return;
		}

		$.ajax({
			  url:'${vix}/common/model/orginialBaseMetaDataDefineAction!goSaveOrUpdate.action',
			  data:{id:metaDataDefineId,metaDataId:metaDataId},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 860,
						height : 350,
						title:"元数据基本属性",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								var queryString = $('#baseMetaDataDefineForm').formSerialize(); 
								$.post('${vix}/common/model/orginialBaseMetaDataDefineAction!saveOrUpdate.action',
									queryString,
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadMetaDataGrid();
									}
								 );
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	function loadMetaDataGrid(){
		var metaDataId = $("#metaDataId").val();
		
		$('#dgdA1').datagrid({
			//title:'元数据属性信息 ',
			iconCls:'icon-save',
			width:"auto",
			height:550,
			singleSelect: true,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			url:'${vix}/common/model/orginialBaseMetaDataDefineAction!goSingleList.action?metaDataId='+metaDataId,
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
							return "是";
						}
						return "";
					}		
				
				},
				{field:'defaultValue',title:'默认值 ',width:120}
			]],
			pagination:false,
			rownumbers:true,
			toolbar:[{
				id:'btnaddA1',
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					goSaveUpdateMetaDataDefine('');
				}
			},{
				id:'btncutA3',
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					var idVal = getDataGridId("dgdA1");
					if(idVal!=''){
						goSaveUpdateMetaDataDefine(idVal);
					}
				}
			},{
				id:'btncutA1',
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					//$('#btnsave').linkbutton('enable');
					//alert('cut')
					var idVal = getDataGridId("dgdA1");
					if(idVal!=''){
						var queryString = "id="+idVal;
						$.post('${vix}/common/model/orginialBaseMetaDataDefineAction!deleteById.action',
							queryString,
							function(result){
								showMessage(result);
								setTimeout("", 1000);
								loadMetaDataGrid();
							}
						 );
					}
					
				}
			}]
		});
	}	


	function getDataGridId(dgId){
		var res = '';
		var rows = $('#'+dgId).datagrid('getSelected');
		if(rows){
			res = rows.id;
		}else{
			showErrorMessage("只能修改一条数据!");
			setTimeout("", 1000);
			return ;
		}
		return res;
		
	}
	
	
	function loadMetaDataGridExt(){
		var metaDataId = $("#metaDataId").val();
		
		$('#dgdA2').datagrid({
			//title:'元数据属性信息 ',
			iconCls:'icon-save',
			width:"auto",
			height:550,
			singleSelect: true,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			url:'${vix}/common/model/orginialBaseMetaDataExtAction!goSingleList.action?metaDataId='+metaDataId,
			remoteSort: false,
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{field:'propertyName',title:'元数据名称 ',width:200}
			]],
			columns:[[
				{field:'propertyCode',title:'编码 ',width:120},
				{field:'property',title:'属性 ',width:150},
				{field:'dataType',title:'数据类型 ',width:80,
					formatter:function(value){
						if(value==null || value=="null" || value==''){
							return "";
						}else if(value=='Integer'){
							return "整型";
						}else if(value=='Long'){
							return "长整型";
						}else if(value=='Double'){
							return "双精度";
						}else if(value=='Date'){
							return "日期";
						}else if(value=='String'){
							return "字符串";
						}
						return "";
					}	
				},
				//{field:'propertyType',title:'数据类型 ',width:320},
				/*{field:'isCollectionType',title:'属性类型 ',width:80,
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
						if(value==null || value=="null"){
							return "否";
						}else{
							return "是";
						}
					}		
				
				},*/
				{field:'defaultValue',title:'默认值 ',width:120}
			]],
			pagination:false,
			rownumbers:true,
			toolbar:[{
				id:'btnaddA1',
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					goSaveUpdateMetaDataExt('');
				}
			},{
				id:'btncutA3',
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					var idVal = getDataGridId("dgdA2");
					if(idVal!=''){
						goSaveUpdateMetaDataExt(idVal);
					}
				}
			},{
				id:'btncutA1',
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					//$('#btnsave').linkbutton('enable');
					//alert('cut')
					asyncbox.confirm('确定要删除么?','提示信息',function(action){
						if(action == 'ok'){
							var idVal = getDataGridId("dgdA2");
							if(idVal!=''){
								var queryString = "id="+idVal;
								$.post('${vix}/common/model/orginialBaseMetaDataExtAction!deleteById.action',
									queryString,
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadMetaDataGridExt();
									}
								 );
							}
						}
					});
					
				}
			}]
		});
	}	
	
	/**
	 * 添加编辑 扩展属性
	 */
	function goSaveUpdateMetaDataExt(metaDataExtId){
		var metaDataId = $("#metaDataId").val();//metaDataId
		if(metaDataId=="" || metaDataId=="0"){
			showErrorMessage("请先保存元数据信息！");
			setTimeout("", 1000);
			return;
		}

		$.ajax({
			  url:'${vix}/common/model/orginialBaseMetaDataExtAction!goSaveOrUpdate.action',
			  data:{id:metaDataExtId,metaDataId:metaDataId},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 860,
						height : 350,
						title:"元数据自定义属性",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								var queryString = $('#baseMetaDataExtForm').formSerialize(); 
								$.post('${vix}/common/model/orginialBaseMetaDataExtAction!saveOrUpdate.action',
									queryString,
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadMetaDataGridExt();
									}
								 );
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	
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
			
			$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop })
								.animate({'opacity':'1', 'marginTop':'0'}, 500);
			
		}).focusout(function() {
			
			$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
					
				$(this).remove();
				tooltip.removeClass('showed-tooltip');
					
			});
		});
		JT_init();
	}
	$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
	$("table tr:even").addClass("alt");
	
	/**
	 * 保存元数据信息
	 */
	function saveOrUpdateMetaData(){
		$("#metaDataForm").validationEngine('validate');
		/* 
		var boData = $('#dgdA1').datagrid('getData');
		var boDataRow = boData.rows;
		if(boDataRow.length<=0){
			asyncbox.alert("必须为职员添加业务组织！","提示");
			return;
		} */
		
		var queryString = $('#metaDataForm').formSerialize(); 
		$.post('${vix}/common/model/orginialBaseMetaDataAction!saveOrUpdate.action',
			queryString,
			function(result){
				//debugger;
				var msgObj = $.parseJSON(result);
				var msg = msgObj.msg;
				var objId = msgObj.objId;
				
				$("#metaDataId").val(objId);
				$("#metaDataCode").attr("readonly","readonly");
				
				showMessage(msg);
				setTimeout("", 1000);
				//asyncbox.success(msg,"<s:text name='message'/>",function (action){
				//});
			}
		 );
	}
	
	/* 
	pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
	
	function currentPager(tag){
		pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
	}
	
	$("#order").validationEngine(); */
	
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/system/cmn_metadata.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_basedata" /></a></li>
				<li><a href="#"><s:text name="system_control_basedata_meta" /></a></li>
				<li><a href="#">元数据对象编辑</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="order">
	<dl>
		<dt>
			<span class="no_line"> <a href="#" onclick="saveOrUpdateMetaData()"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消"
					src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a href="#"><img
					width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" alt="打印"
					src="${vix}/common/img/dt_print.png"></a>
				<div class="ms" style="float: none; display: inline;">
					<a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
					<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
					</ul>
				</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/common/model/orginialBaseMetaDataAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
			</span> <strong> <b>元数据编辑</b> <i></i>
			</strong>
		</dt>
		<dd class="clearfix">
			<div class="order_table">
				<div class="voucher newvoucher">
					<dl>
						<dt>
							<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a>
							</span> <strong>元数据信息</strong>
						</dt>
						<dd style="display: block;">
							<form id="metaDataForm">
								<table style="border: none;">
									<tr height="40">
										<td width="15%" align="right"><input type="hidden" id="metaDataId" name="entityForm.id" value="${entity.id}" /> 编码：</td>
										<td width="35%"><input id="metaDataCode" name="entityForm.code" type="text" size="30" value="${entity.code}" <s:if test="%{entity.id!=null}">readonly="readonly"</s:if> /></td>
										<td width="15%">元数据对象名称</td>
										<td width="35%"><input name="entityForm.metaDataName" type="text" size="30" value="${entity.metaDataName}" /></td>
									</tr>
									<tr height="40">
										<td align="right">元数据对象显示名称：</td>
										<td><input name="entityForm.metaDataDisplayName" type="text" size="30" value="${entity.metaDataDisplayName}" /></td>
										<td>类别：</td>
										<td><s:select name="entityForm.baseMetaDataCategory.id" list="baseMetaDataCategoryList" listKey="id" listValue="categoryName" value="%{entity.baseMetaDataCategory.id}" theme="simple"></s:select></td>
									</tr>
									<%-- <tr height="40">
										<td align="right">元数据对象名称：</td>
										<td><input name="entityForm.metaDataName" type="text" size="30"  value="${entity.metaDataName}"/></td>
										<td></td>
										<td></td>
									</tr>
									<tr height="40">
										<td align="right">类别：</td>
										<td>
											<s:select name="entityForm.baseMetaDataCategory.id" list="baseMetaDataCategoryList" listKey="id" listValue="categoryName" value="%{entity.baseMetaDataCategory.id}" theme="simple"></s:select>
										</td>
										<td></td>
										<td></td>
									</tr> --%>
								</table>
							</form>
						</dd>
					</dl>
				</div>
			</div>
		</dd>
		<div class="clearfix" style="background: #FFF; position: relative;">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="javascript:tab(8,1,'a',event);">基本属性</a></li>
					<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)">自定义属性</a></li>
					<!-- <li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)">帐号</a></li> -->
					<!--<li><a onclick="javascript:$('#a4').attr('style','');tab(8,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />审批</a></li>
						<li><a onclick="javascript:$('#a5').attr('style','');tab(8,5,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						
						<li><a onclick="javascript:$('#a7').attr('style','');tab(8,7,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />交货地点</a></li>
						<li><a onclick="javascript:$('#a6').attr('style','');tab(8,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />附件/文本</a></li>
						<li><a onclick="javascript:$('#a8').attr('style','');tab(8,8,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />审批项</a></li>-->
				</ul>
			</div>
			<div id="a1" style="width: 100%;">
				<%-- <div class="right_btn nomargin">
						<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span>
						<span><a href="#" ><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
					</div> --%>
				<table id="dgdA1"></table>
			</div>
			<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<%-- <div class="right_btn nomargin">
						<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span>
						<span><a href="#" ><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
					</div> --%>
				<table id="dgdA2"></table>
			</div>
			<%-- <div id="a3" style="width:100%; visibility:hidden; position:absolute; top:-5000px;">
					<div class="right_btn nomargin">
						<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span>
						<span><a href="#" ><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
					</div>
					<table id="dgdA3"></table>
				</div> --%>
		</div>
	</dl>
</div>
<!--oder-->
<div class="sub_menu sub_menu_bot">
	<div class="drop">
		<p>
			<%-- <a href="#" onclick="saveOrUpdateEss();"><span>保存</span></a>
				<a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goList.action');"><span>返回</span></a> --%>
			<!-- <a href="#"><span>编辑</span></a>
				<a href="#"><span>复制</span></a>
				<a href="#"><span>删除</span></a>
				<a href="#"><span>关闭并新建</span></a>
				<a href="#"><span>关闭</span></a> -->
		</p>
	</div>
</div>
<!--submenu-->
<!-- content -->