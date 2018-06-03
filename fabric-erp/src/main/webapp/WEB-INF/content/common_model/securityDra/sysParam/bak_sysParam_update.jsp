<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">
<!--
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
/** 保存订单 */
function saveOrUpdateParams(){
	var orderItemStr = "";
	/** 送货地址 */
	var dlData = $("#dlAddress").datagrid("getRows");
	var dlJson = JSON.stringify(dlData);
	/** 发运计划 */
	var dpData = $("#deliveryPlan").datagrid("getRows");
	var dpJson = JSON.stringify(dpData);
	if($('#order').validationEngine('validate')){
		$.post('${vix}/common/security/dataResRowSysParamAction!saveOrUpdate.action',
			{'id':'1'
			},
			function(result){
				showMessage(result);
				//setTimeout("",1000);
				//loadContent('${vix}/sales/order/salesOrderAction!goList.action?menuId=menuOrder');
			}
		);
	}
}
$("#order").validationEngine();
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


$(function(){
	
	loadSysParamSubGrid();
});



var updateField = "";
function salesOrderFieldChanged(input){
	updateField+= $(input).attr("id")+",";
}

function chooseMetadata(){
	$.ajax({
		  url:'${vix}/common/security/dataResRowMethodConfigAction!goChooseMetaData.action?tag=choose&chkStyle=radio',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择元数据",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							var metadataIds = "";
							var metadataNames = "";
							var result = returnValue.split(",");
							for (var i=0; i<result.length; i++){
								if(result[i].length>1){
									var v = result[i].split(":");
									metadataIds += "," + v[0];
									metadataNames += "," + v[1];
								}
							}
							//alert(metadataNames.substring(1,metadataNames.length));
							metadataNames = metadataNames.substring(1,metadataNames.length);
							$("#metadataId").val(metadataIds);
							$("#metadataName").html(metadataNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function loadSysParamSubGrid(){
	var paramId = $("#id").val();
	
	$('#d1').datagrid({
		url: '${vix}/common/security/dataResRowSysParamAction!findDataParamRoleList.action?id='+paramId,
		//title: '角色',
		width: 700,
		height: 'auto',
		singleSelect: true,
		fitColumns: true,
		columns:[[
			{field:'id',title:'编号',width:80},
			{field:'name',title:'名称',width:120},
			{field:'roleCode',title:'角色编码',width:120}
		]],
		toolbar:[{
			id:'da1Btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				//$('#btnsave').linkbutton('enable');
				var chkDataId = checkParamId();
				if(chkDataId != null){
					$.ajax({
						  url:'${vix}/common/security/userAccountAction!goChooseRole.action?tag=choose&chkStyle=checkbox',
						  cache: false,
						  success: function(html){
							  asyncbox.open({
								 	modal:true,
									width : 560,
									height : 340,
									title:"选择角色",
									html:html,
									callback : function(action,returnValue){
										if(action == 'ok'){
											if(returnValue != undefined){
												debugger;
												var result = returnValue.split(",");
												var roleId = "";
												var result = returnValue.split(",");
												for (var i=0; i<result.length; i++){
													if(result[i].length>1){
														var v = result[i].split(":");
														roleId += ","+ v[0];
														//break;
													}
												}
												if(roleId!=""){
													var queryString = "roleId="+roleId+"&sysParamId="+$("#id").val();
													$.post('${vix}/common/security/dataResRowSysParamAction!saveRoleForParam.action',
														queryString,
														function(result){
															asyncbox.success(result,"<s:text name='message'/>",function(action){
																$('#d1').datagrid("reload");
															});
														}
													 );
												}
												
												//}
											}/* else{
												asyncbox.success("请选择角色!","提示信息");
											} */
										}
									},
									btnsbar : $.btn.OKCANCEL
								});
						  }
					});
					
				}
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				/* var rows = $('#d1').datagrid("getSelections");	//获取你选择的所有行	
				//循环所选的行
				for(var i =0;i<rows.length;i++){
					var index = $('#d1').datagrid('getRowIndex',rows[i]);//获取某行的行号
					$('#dlAddress2').datagrid('deleteRow',index);	//通过行号移除该行
					$.ajax({url:'${vix}/sales/order/salesOrderAction!deleteDeliveryAddressById.action?id='+rows[i].id,cache: false});
				} */
				
				var idVal = getDataGridId("d1");
				if(idVal!=''){
					//if( asyncbox.confirm("确定要删除该角色吗？") ){
					if(confirm("确定要删除该角色吗？")){
						var queryString = "roleId="+idVal+"&sysParamId="+$("#id").val();
						$.ajax({
							  url:'${vix}/common/security/dataResRowSysParamAction!deleteRoleForParam.action',
							  data:queryString,
							  cache: false,
							  success: function(html){
								asyncbox.success(html,"提示信息",function(action){
									$('#d1').datagrid("reload");
								});
							  }
						});
					}
				}
			}
		}]
	});
	
	
	
	$('#d2').datagrid({
		url: '${vix}/common/security/dataResRowSysParamAction!findDataParamPreDataList.action?id='+paramId,
		//title: '预处理数据',
		width: 700,
		height: 'auto',
		singleSelect: true,
		fitColumns: true,
		columns:[[
			{field:'id',title:'编号',width:80},
			{field:'priority',title:'优先级',width:80},
			{field:'preData',title:'预处理数据',width:420}
		]],
		toolbar:[{
			id:'da2Btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				var chkDataId = checkParamId();
				if(chkDataId != null){
					//$('#da2Btnadd').linkbutton('enable');
					$.ajax({
						  url:'${vix}/common/security/dataResRowSysParamAction!goSaveUpdatePredata.action?sysParamId='+$("#id").val(),
						  cache: false,
						  success: function(html){
							  asyncbox.open({
								 	modal:true,
									width : 660,
									height : 280,
									title:"预处理数据",
									html:html,
									callback : function(action){
										if(action == 'ok'){
											$("#predataForm").validationEngine('validate');
											var queryString = $('#predataForm').formSerialize(); 
											$.post('${vix}/common/security/dataResRowSysParamAction!savePredataForParam.action',
												queryString,
												function(result){
													asyncbox.success(result,"<s:text name='message'/>",function(action){
														//pager("start","${vix}/common/security/roleAction!goSingleList.action?name="+name,'role');
														$('#d2').datagrid("reload");
													});
												}
											 );
										}
									},
									btnsbar : $.btn.OKCANCEL
								});
						  }
					});
				}	
				
			}
		},'-',{
			id:'btnedit2',
			text:'修改',
			iconCls: 'icon-edit',
			handler: function(){
				var chkDataId = getDataGridId("d2");
				if(chkDataId != null){
					//$('#da2Btnadd').linkbutton('enable');
					$.ajax({
						  url:'${vix}/common/security/dataResRowSysParamAction!goSaveUpdatePredata.action?sysParamId='+$("#id").val()+"&predataId="+chkDataId,
						  cache: false,
						  success: function(html){
							  asyncbox.open({
								 	modal:true,
									width : 660,
									height : 280,
									title:"预处理数据",
									html:html,
									callback : function(action){
										if(action == 'ok'){
											$("#predataForm").validationEngine('validate');
											var queryString = $('#predataForm').formSerialize(); 
											$.post('${vix}/common/security/dataResRowSysParamAction!savePredataForParam.action',
												queryString,
												function(result){
													asyncbox.success(result,"<s:text name='message'/>",function(action){
														//pager("start","${vix}/common/security/roleAction!goSingleList.action?name="+name,'role');
														$('#d2').datagrid("reload");
													});
												}
											 );
										}
									},
									btnsbar : $.btn.OKCANCEL
								});
						  }
					});
				}
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				/* var rows = $('#dlAddress2').datagrid("getSelections");	//获取你选择的所有行	
				//循环所选的行
				for(var i =0;i<rows.length;i++){
					var index = $('#dlAddress2').datagrid('getRowIndex',rows[i]);//获取某行的行号
					$('#dlAddress2').datagrid('deleteRow',index);	//通过行号移除该行
					$.ajax({url:'${vix}/sales/order/salesOrderAction!deleteDeliveryAddressById.action?id='+rows[i].id,cache: false});
				} */
				var idVal = getDataGridId("d2");
				if(idVal!=''){
					if(confirm("确定要删除该角色吗？")){
						 var queryString = "predataId="+idVal;

						 $.ajax({
						 	  url:'${vix}/common/security/dataResRowSysParamAction!deletePredataForParam.action',
						 	  data:queryString,
						 	  cache: false,
						 	  success: function(html){
						 		asyncbox.success(html,"提示信息",function(action){
						 			$('#d2').datagrid("reload");
						 		});
						 	  }
						 });
					}
				}
			}
		}]
	});
}

/**
 * 验证是否是否保存了 paramid
 */
function checkParamId(){
	var paramId = $("#id").val();//employeeId
	if(paramId=="" || paramId=="0"){
		asyncbox.alert("请先保存参数配置信息！","提示");
		return null;
	}
	return paramId;
}


function getDataGridId(dgId){
	var res = '';
	var rows = $('#'+dgId).datagrid('getSelected');
	if(rows){
		res = rows.id;
	}else{
		asyncbox.alert("只能修改一条数据!","提示:");
		return ;
	}
	return res;
	
}

/**
 * 保存主对象
 */
function saveOrUpdateSysParam(){
	$("#sysParamForm").validationEngine('validate');
	var queryString = $('#sysParamForm').formSerialize(); 
	$.post('${vix}/common/security/dataResRowSysParamAction!saveOrUpdate.action',
		queryString,
		function(result){
			var msgObj = $.parseJSON(result);
			var msg = msgObj.msg;
			var objId = msgObj.objId;
			$("#id").val(objId);
			debugger;
			asyncbox.success(msg,"<s:text name='message'/>",function (action){
			});
		}
	 );
}

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
				<li><a href="#"><s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec" /></a></li>
				<li><a href="#">参数配置</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" title="取消"
						src="${vix}/common/img/dt_cancleback.png" /></a> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a
					href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" title="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22"
						title="打印" src="${vix}/common/img/dt_print.png"></a> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
				</span>
			</dt>

			<dd class="clearfix">
				<form id="sysParamForm">
					<input type="hidden" id="id" name="entityForm.id" value="${id}" />
					<div class="order_table">
						<table>
							<tr>
								<th>变量</th>
								<td><input id="keyProperty" name="entityForm.keyProperty" value="${entity.keyProperty}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<th>变量显示名</th>
								<td><input id=keyPropertyName name="entityForm.keyPropertyName" value="${entity.keyPropertyName}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<th>类型:</th>
								<td><s:radio list="#{\"S\":\"系统常量 \"}" name="entityForm.paramType" value="%{entity.paramType}" theme="simple"></s:radio> <%-- 
								<s:radio list="#{\"S\":\"系统常量 \",\"P\":\"预处理常量\"}" name="entityForm.paramType" value="%{entity.paramType}" theme="simple"></s:radio>
								 --%></td>
								<th>是否激活:</th>
								<td><s:radio list="#{\"0\":\"是\",\"1\":\"否\"}" name="entityForm.isActive" value="%{entity.isActive}" theme="simple"></s:radio></td>
							</tr>
							<tr>
								<th>值类型:</th>
								<td><s:radio list="#{\"select\":\"选择数据 \",\"text\":\"文本\",\"sql\":\"SQL\"}" name="entityForm.valueType" value="%{entity.valueType}" theme="simple"></s:radio></td>

								<th>是否读取预处理数据:</th>
								<td><s:radio list="#{\"0\":\"否 \",\"1\":\"是\"}" name="entityForm.readFromPreData" value="%{entity.readFromPreData}" theme="simple"></s:radio></td>

							</tr>
							<tr>

								<th>选择链接（暂用）:</th>
								<td colspan="3"><input id=paramUrl name="entityForm.paramUrl" value="${entity.paramUrl}" size="150" type="text" /></td>

							</tr>

							<tr>
								<th>元数据:</th>
								<td colspan="3"><input type="hidden" id="metadataId" name="entityForm.baseMetaData.id" value="${entity.baseMetaData.id}" /> <span id="metadataName">${entity.baseMetaData.metaDataDisplayName}</span> <a href="#" onclick="chooseMetadata();">选择</a></td>
							</tr>
						</table>
					</div>
				</form>
			</dd>

			<div class="clearfix" style="background: #FFF; position: relative; display: none;">
				<div class="right_menu">
					<ul>
						<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(2,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />角色</a></li>
						<li><a onclick="javascript:$('#a2').attr('style','');tab(2,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />预处理值</a></li>
					</ul>
				</div>

				<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
					<table id="d1"></table>
				</div>

				<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
					<table id="d2"></table>
				</div>

			</div>
		</dl>
	</div>
	<!--oder-->
	<div class="sub_menu sub_menu_bot">
		<div class="drop">
			<p>
				<a href="#" onclick="saveOrUpdateSysParam();"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/common/security/dataResRowSysParamAction!goList.action?pageNo=${pageNo}');"><span>返回</span></a>
			</p>
		</div>
	</div>
	<!--submenu-->

</div>
<!-- content -->
<script type="text/javascript">
	$(function(){
		$.fn.fix = function(options){
			var defaults = {
				'advance' : 0,
				'top' : 0
			}
			options = $.extend(defaults, options);
			return this.each(function(){
				var $_this = $(this);
				$_this.wrap('<div></div>');
				var wp = $_this.parent('div');
				wp.height(wp.height());
				var ofst = wp.offset();
				
				if(!$_this.is(':visible') && $(window).scrollTop() + options.advance > $_this.offset().top){
					$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
				}
				$(window).scroll(function(){
					if(!$_this.is(':visible')){
						return ;
					}
					
					if($(window).scrollTop() + options.advance > wp.offset().top){
						$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
					}else{
						$_this.css({'position':'static','z-index':'auto','top': 'auto','width':'auto'});
					}
				});
			});
		}
		$('#a1 .right_btn,#a3 .right_btn').fix({'advance':38,'top':38});
	});
	function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
 
	$(window).scroll(function(){
		if($('#orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()){
			if(!$('#orderTitleFixd').hasClass('fixed')){
				$('#orderTitleFixd').addClass('fixed');
				$('#orderTitleFixd').parent('dl').css('padding-top',30);
			}
		}else{
			$('#orderTitleFixd').removeClass('fixed');
			$('#orderTitleFixd').parent('dl').css('padding-top',0);
		}
	});
 
</script>