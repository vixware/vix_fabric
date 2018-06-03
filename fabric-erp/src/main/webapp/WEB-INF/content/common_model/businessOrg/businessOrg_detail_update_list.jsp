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
		loadBOUnitGrid();
		loadBOEmpGrid();
		//loadBORoleGrid();
	});
	
	
	/**
	 * 部门列表
	 */
	function loadBOUnitGrid(){
		$('#dgdB1').datagrid({
			//title:'',
			iconCls:'icon-save',
			width:800,
			height:350,
			singleSelect: true,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			url:'${vix}/common/org/businessOrgAction!goBoOrgUnitList.action?id=${id}',
			remoteSort: false,
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{field:'fs',title:'简称',width:200}
			]],
			columns:[[
				{field:'fullName',title:'全称',width:120},
				{field:'orgCode',title:'编码 ',width:150},
				{field:'unitType',title:'部门类型 ',width:80,
					formatter:function(value){
						if(value=='JZBM'){
							return "基准部门";
						}else if(value=='XSBGS'){
							return "销售办公室";
						}else if(value=='XSZ'){
							return "销售组";
						}else{
							return '';
						}
					}	
				}
			]],
			pagination:false,
			rownumbers:true,
			toolbar:[{
				id:'btnaddA1',
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					addBoOrganizationUnit();
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
						deleteBoDetail('O',idVal);
					}
					
				}
			}]
		});
	}	


	/**
	 * 职员列表
	 */
	function loadBOEmpGrid(){
		
		//$('#dgdB2').datagrid({
		$('#dgdB2').datagrid({
			//title:'',
			iconCls:'icon-save',
			width:800,
			height:250,
			singleSelect: true,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			url:'${vix}/common/org/businessOrgAction!goBoEmpList.action?id=${id}',
			remoteSort: false,
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{field:'name',title:'姓名',width:200}
			]],
			columns:[[
				{field:'staffJobNumber',title:'员工职号',width:120},
				{field:'gender',title:'性别',width:80,
					formatter:function(value){
						if(value=='1'){
							return "男";
						}else if(value=='0'){
							return "女";
						}else{
							return '';
						}
					}	
				},
				{field:'isDemission',title:'是否离职',width:80,
					formatter:function(value){
						if(value=='1'){
							return "是";
						}else if(value=='0'){
							return "否";
						}else{
							return '';
						}
					}	
				}
			]],
			pagination:false,
			rownumbers:true,
			toolbar:[{
				id:'btnaddA1',
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					addBoEmp();
				}
			},{
				id:'btncutA1',
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					//$('#btnsave').linkbutton('enable');
					//alert('cut')
					var idVal = getDataGridId("dgdB2");
					if(idVal!=''){
						deleteBoDetail('E',idVal);
					}
					
				}
			}]
		});
	}	

		
	/**
	 * 角色列表
	 */
	function loadBORoleGrid(){
		
		//$('#dgdB2').datagrid({
		$('#dgdB3').datagrid({
			//title:'',
			iconCls:'icon-save',
			width:800,
			height:250,
			singleSelect: true,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			url:'${vix}/common/org/businessOrgAction!goBoRoleList.action?id=${id}',
			remoteSort: false,
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{field:'name',title:'角色名',width:200}
			]],
			columns:[[
				{field:'roleCode',title:'编码 ',width:150}
			]],
			pagination:false,
			rownumbers:true,
			toolbar:[{
				id:'btnaddA1',
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					addBoRole();
				}
			},{
				id:'btncutA1',
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					//$('#btnsave').linkbutton('enable');
					//alert('cut')
					var idVal = getDataGridId("dgdB3");
					if(idVal!=''){
						deleteBoDetail('R',idVal);
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
			//asyncbox.alert("只能操作一条数据!","提示:");
			showMessage("只能操作一条数据!");
			setTimeout("", 1000);
			return ;
		}
		return res;
		
	}
	
	/**
	 * //unitType : 'XS',
	 * 添加部门
	 */
	function addBoOrganizationUnit(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
			  data:{
				  chkStyle:"checkbox",
				  canCheckComp:0
			  },
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择部门",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var resObj = $.parseJSON(returnValue);
									
									for(var i=0;i<resObj.length;i++){
										selectIds += "," + resObj[i].treeId;
										selectNames += "," + resObj[i].name;
									}
									
									selectIds = selectIds.substring(1,selectNames.length);
									
									asyncbox.confirm('确定要添加部门么?','提示信息',function(action){
										if(action == 'ok'){
											//alert(selectIds+"#        #"+selectNames);
											//saveChargeOfEmp('O','',selectIds);
											saveBoDetail('O',selectIds);
										}else{
											return false;
										}
									});
									//$("#pubIds").val(selectIds);
									//selectNames = selectNames.substring(1,selectNames.length);
									//$("#pubNames").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	/**
	 * 添加职员
	 */
	function addBoEmp(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 760,
						height : 440,
						title:"选择人员",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											selectIds +=  "," + v[0];
											selectNames +=  "," + v[1];
										}
									}
									
									
									selectIds = selectIds.substring(1,selectIds.length);
									//alert(selectIds+"          "+selectNames);
									
									asyncbox.confirm('确定要添加员工么?','提示信息',function(action){
										if(action == 'ok'){
											//alert(selectIds+"#        #"+selectNames);
											//saveChargeOfEmp('O','',selectIds);
											saveBoDetail('E',selectIds);
										}else{
											return false;
										}
									});
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	
	/**
	 * 添加角色
	 */
	function addBoRole(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectRoleAction!goList.action',
			  data:{chkStyle:"radio"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
					 	width : 760,
						height : 520,
						title:"选择角色",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";

									/* if(resObj.length == 0 ){
										return;
									} */
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											selectIds += "," +  v[0];
											selectNames +=  "," + v[1];
										}
									}
									
									
									//$("#bizOrgIds").val(selectIds);
									//selectNames = selectNames.substring(1,selectNames.length);
									//$("#bizOrgNames").val(selectNames);
									selectIds = selectIds.substring(1,selectNames.length);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	
	
	/**
	 * 保存业务组织详情
	 */
	function saveBoDetail(bizOrgType,bizOrgIds){
		var queryString = "id=${id}";
		queryString += "&bizOrgType=" + bizOrgType + "&bizOrgIds=" + bizOrgIds;
		$.post('${vix}/common/org/businessOrgAction!saveOrUpdateDetail.action',
			queryString,
			function(result){
				//asyncbox.success(result,"<s:text name='message'/>",function(action){
				showMessage(result);
				setTimeout("", 1000);	
				if(bizOrgType=='O'){
					loadBOUnitGrid();
				}else if(bizOrgType=='E'){
					loadBOEmpGrid();
				}
				//});
			}
		 );
	
	}
	
	/**
	 * 删除
	 */
	function deleteBoDetail(bizOrgType,bizOrgId){
		asyncbox.confirm('确定要删除么?','提示信息',function(action){
			if(action == 'ok'){
				var queryString = "id=${id}&bizOrgType=" +bizOrgType+ "&detailBizOrgId="+bizOrgId;//detailBizOrgId
				$.post('${vix}/common/org/businessOrgAction!deleteDetailById.action',
					queryString,
					function(result){
						showMessage(result);
						setTimeout("", 1000);					
						if(bizOrgType=='O'){
							loadBOUnitGrid();
						}else if(bizOrgType=='E'){
							loadBOEmpGrid();
						}
					}
				 );
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
				<li><a href="#"><s:text name="system_control_org_sys" /></a></li>
				<li><a href="#">业务组织设置</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="order">
	<dl>
		<dt>
			<span class="no_line"> <%-- <a href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a>
					<a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png"/></a> --%> <a href="#"><img width="22" height="22" alt="取消" src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22"
					alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a href="#"><img width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a>
				<a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" alt="打印" src="${vix}/common/img/dt_print.png"></a>
				<div class="ms" style="float: none; display: inline;">
					<a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
					<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
						<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
					</ul>
				</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/common/org/businessOrgAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
			</span> <strong> <b>业务组织设置</b> <i></i>
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
							</span> <strong>业务组织信息</strong>
						</dt>
						<dd style="display: none;"></dd>
					</dl>
				</div>
			</div>
		</dd>

		<div class="clearfix" style="background: #FFF; position: relative;">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="javascript:tab(3,1,'b',event);">部门</a></li>
					<li><a onclick="javascript:$('#b2').attr('style','');tab(3,2,'b',event)">员工</a></li>
					<!-- <li><a onclick="javascript:$('#b3').attr('style','');tab(3,3,'b',event)">角色</a></li> -->
				</ul>
			</div>
			<div id="b1" style="width: 100%;">
				<table id="dgdB1"></table>
			</div>
			<div id="b2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<table id="dgdB2"></table>
			</div>
			<!-- <div id="b3" style="width:100%; visibility:hidden; position:absolute; top:-5000px;">
					<table id="dgdB3"></table>
				</div> -->
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