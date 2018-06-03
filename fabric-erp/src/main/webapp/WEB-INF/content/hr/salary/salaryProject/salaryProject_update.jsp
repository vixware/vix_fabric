<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%-- 
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
--%>
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
	
	/**
	 * 添加编辑 基本属性
	 */
	function goCheckSalaryProjectItemMod(metaDataDefineId){
		var salaryProjectId = $("#salaryProjectId").val();//metaDataId
		if(salaryProjectId=="" || salaryProjectId=="0"){
			showMessage("请先保存工资类别信息！");
			setTimeout("", 1000);
			return;
		}

		$.ajax({
			  url:'${vix}/hr/salary/salaryProjectAction!goSelectSalaryProjectItemMod.action',
			  data:{},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 860,
						height : 550,
						title:"工资项选择",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								
								/* var queryString = $('#salaryProjectForm').formSerialize(); 
								$.post('${vix}/hr/salary/salaryProjectAction!addSalaryProjectItemMod.action',
									queryString,
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										loadMetaDataGrid();
									}
								 ); */
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";

									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											selectIds = v[0];
											selectNames = v[1];
										}
									}
									
									
									//$("#bizOrgIds").val(selectIds);
									//selectNames = selectNames.substring(1,selectNames.length);
									//$("#bizOrgNames").val(selectNames);
									//alert(selectIds+"--"+selectNames);
									if(selectIds!="" && selectNames!=""){
										var itemModParam = "salaryProjectId=" + salaryProjectId + "&itemModId="+selectIds; 
										$.post('${vix}/hr/salary/salaryProjectAction!addSalaryProjectItemMod.action',
											itemModParam,
											function(result){
												showMessage(result);
												setTimeout("", 1000);
												loadSalaryProjectItemGrid();
											}
										 );
									}
									
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	function loadSalaryProjectItemGrid(){
		var salaryProjectId = $("#salaryProjectId").val();
		
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
			url:'${vix}/hr/salary/salaryProjectAction!goSubSingleList.action?salaryProjectId='+salaryProjectId,
			remoteSort: false,
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{field:'name',title:'工资项名称 ',width:200}
			]],
			columns:[[
				{field:'salaryItemCode',title:'编码 ',width:120}
				/* {field:'propertyType',title:'数据类型 ',width:320,
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
						if(value==null || value=="null"){
							return "否";
						}else{
							return "是";
						}
					}		
				
				}, */
				//{field:'defaultValue',title:'默认值 ',width:120}
			]],
			pagination:false,
			rownumbers:true,
			toolbar:[{
				id:'btnaddA1',
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					goCheckSalaryProjectItemMod('');
				}
			},{
				id:'btncutA3',
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					var idVal = getDataGridId("dgdA1");
					if(idVal!=''){
						//goCheckSalaryProjectItemMod(idVal);
						alert("修改选择过来的工资项实例的公式、值");
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
						asyncbox.confirm('确定要保存么?','提示信息',function(action){
							if(action == 'ok'){
								var queryString = "itemId="+idVal;
								//alert("删除选择过来的工资项");
								$.post('${vix}/hr/salary/salaryProjectAction!deleteSalaryProjectItem.action',
									queryString,
									function(result){
										debugger;
										showMessage(result);
										setTimeout("", 1000);
										loadSalaryProjectItemGrid();
									}
								 );
							}
						});
					}
					
				}
			},{
				id:'btncutA1',
				text:'验证工资项',
				iconCls:'icon-edit',
				handler:function(){
					//$('#btnsave').linkbutton('enable');
					//alert('cut')
					alert("验证所有工资项的是否有值，公式所需的工资项（如是否实例化）或者辅助项是否有合理");
					var idVal = getDataGridId("dgdA1");
					if(idVal!=''){
						//var queryString = "id="+idVal;
						
						/* $.post('${vix}/hr/salary/salaryProjectAction!deleteById.action',
							queryString,
							function(result){
									showMessage(result);
									setTimeout("", 1000);
									loadMetaDataGrid();
							}
						 ); */
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
			showMessage("只能修改一条数据!");
			setTimeout("", 1000);
			return ;
		}
		return res;
		
	}
	
	
	$(function(){
		loadSalaryProjectItemGrid();
	});
	
	
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
	 * 
	 */
	function saveOrUpdateSalaryProjectItem(){
		$("#metaDataForm").validationEngine('validate');
		/* 
		var boData = $('#dgdA1').datagrid('getData');
		var boDataRow = boData.rows;
		if(boDataRow.length<=0){
			asyncbox.alert("必须为职员添加业务组织！","提示");
			return;
		} */
		
		var queryString = $('#metaDataForm').formSerialize(); 
		$.post('${vix}/common/model/baseMetaDataAction!saveOrUpdate.action',
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
				//asyncbox.success(msg,"<s:text name='cmn_message'/>",function (action){
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
				<li><a href="#"><img src="${vix}/common/img/hr/hr_salary.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_basedata" /></a></li>
				<li><a href="#">工资类别编辑</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="order">
	<dl>
		<dt>
			<span class="no_line"> <a href="#" onclick="saveOrUpdateSalaryProject()"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消"
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
				</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/hr/salary/salaryProjectAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
			</span> <strong> <b>工资类别编辑</b> <i></i>
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
							</span> <strong>工资类别信息</strong>
						</dt>
						<dd style="display: block;">
							<form id="salaryProjectForm">

								<%-- <s:hidden id="id" name="entityForm.id" value="%{entity.id}"/> --%>
								<s:hidden id="salaryProjectId" name="entityForm.id" value="%{entity.id}" theme="simple" />

								<table style="border: none;">
									<tr height="40">
										<td width="15%" align="right">名称:&nbsp;</td>
										<td width="35%"><input type="text" id="projectName" name="entityForm.projectName" value="${entity.projectName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
										<td width="15%" align="right">是否启用:&nbsp;</td>
										<td width="35%"><s:radio id="isActive" list="#{\"Y\":\"是\",\"N\":\"否\"}" name="entityForm.isActive" value="%{entity.isActive}" theme="simple"></s:radio>
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
										<td><input type="text" id="startTime" name="entityForm.startTime" value="<s:date name='%{entity.startTime}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img onclick="showTime('startTime','yyyy-MM-dd')"
											src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										<td align="right">停用时间:&nbsp;</td>
										<td><input type="text" id="endTime" name="entityForm.endTime" value="<s:date name='%{entity.endTime}' format='yyyy-MM-dd'/>" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /><img onclick="showTime('endTime','yyyy-MM-dd')"
											src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
									</tr>

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
					<li class="current"><a onclick="javascript:tab(2,1,'a',event);">工资项</a></li>
					<!-- <li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)">自定义属性</a></li>
						<li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)">帐号</a></li> -->
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
			<%--
				<div id="a2" style="width:100%; visibility:hidden; position:absolute; top:-5000px;">
					 <div class="right_btn nomargin">
						<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span>
						<span><a href="#" ><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
					</div> 
					<table id="dgdA2"></table>
				</div>--%>
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