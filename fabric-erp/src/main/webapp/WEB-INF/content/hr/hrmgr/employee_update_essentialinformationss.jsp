<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	//设置单据类型选中(修改)
	if(${null == entity.entityTime }){
		var myDate = new Date();
		$("#entityTime").val(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate());
	}
});

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
	 * 添加岗位
	 */
	function chooseEmpOrgPosition(){
		var employeeId = $("#employeeId").val();//
		if(employeeId=="" || employeeId=="0"){
			 asyncbox.alert("请先保存职员的基本信息！","提示");
			return;
		}
		var orgUnitIdVal = "${entity.organizationUnit.id}";
		$.ajax({
			  url:'${vix}/common/org/essOrgPositionAction!goChooseOrgPosition.action',
			  data:{orgUnitId:orgUnitIdVal},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择岗位",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var result = returnValue.split(",");
								
									var posId = result[0];
									
									var posIds = posId.split(/[P,O,C]/);
									
										var queryString = "empId="+employeeId+"&posId="+posIds[0];
										$.post('${vix}/common/org/essOrgPositionAction!saveOrUpdateEssOfOrgPosition.action',
												queryString,
											function(result){
												asyncbox.success(result,"<s:text name='选择岗位'/>",function(action){
													loadEssGrid();
												}); 
											}
										 );
									
								}else{
									
									asyncbox.success("请选择岗位!","提示信息");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	function loadEssGrid(){
		var empId = $("#employeeId").val();
		
		$('#dgdA1').datagrid({
			title:'岗位信息 ',
			iconCls:'icon-save',
			width:"auto",
			height:550,
			singleSelect: true,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			url:'${vix}/hr/employeessAction!findEssOfPos.action?employeeId='+empId,
			remoteSort: false,
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{field:'posName',title:'岗位名称 ',width:150}
			]],
			columns:[[
				{field:'personAmount',title:'编制人数 ',width:80},
				{field:'postPayScale',title:'岗位薪级 ',width:80},
				{field:'ranksOfCadres',title:'干部职级 ',width:80},
				{field:'isChargeDep',title:'负责岗位 ',width:80}
			]],
			pagination:false,
			rownumbers:true,
			toolbar:[{
				id:'btnaddA1',
				text:'增加',
				iconCls:'icon-add',
				handler:function(){
					chooseEmpOrgPosition(); 
				}
			},{
				id:'btncutA1',
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					var idVal = getDataGridId("dgdA1");
					if(idVal!=''){
						var queryString = "empId="+empId+"&posId="+idVal;
						$.post('${vix}/common/org/essOrgPositionAction!deleteById.action',
							queryString,
							function(result){
								asyncbox.success(result,"<s:text name='岗位信息'/>",function(action){
									loadEssGrid();
								});
							}
						 );
					}
					
				}
			}]
		});
		
		//帐号的列表信息加载
		$('#dgdA3').datagrid({
			title:'帐号信息明细 ',
			iconCls:'icon-save',
			width:"auto",
			height:550,
			singleSelect: true,
			nowrap: true,
			autoRowHeight: false,
			striped: true,
			collapsible:true,
			url:'${vix}/common/org/essUserAccountAction!findEssOfUserAccount.action?employeeId='+empId,
			remoteSort: false,
			idField:'id',
			frozenColumns:[[
				{field:'ck',checkbox:true},
				{field:'account',title:'用户帐号',width:110}   
			]],
			columns:[[
				{field:'enable',title:'是否停用 ',width:200,
					formatter:function(value,rec){
						if(value=='0'){
							return '<span>禁用 </span>';
						}else if(value=='1'){
							return '<span>激活 </span>';
						}
						return '';
					}
				}
			]],
			pagination:true,
			rownumbers:true,
			toolbar:[{
				id:'btnaddA3',
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					saveUpdateEssUserAccount('',empId);
				}
			},{
				id:'btncutA3',
				text:'修改',
				iconCls:'icon-cut',
				handler:function(){
					var idVal = getDataGridId("dgdA3");
					if(idVal!=''){
						saveUpdateEssUserAccount(idVal,empId);
					}
				}
			},{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					var idVal = getDataGridId("dgdA3");
					if(idVal!=''){
						$.ajax({
							  url:'${vix}/common/org/essUserAccountAction!deleteById.action?id='+idVal,
							  cache: false,
							  success: function(html){
								asyncbox.success(html,"提示信息",function(action){
									$('#dgdA3').datagrid("reload");
								});
							  }
						});
					}
				}
			}]
		});
	}
	
	/**
	 * 新建和修改账户信息
	 */
	function saveUpdateEssUserAccount(id,empId){
		$.ajax({
			  url:'${vix}/common/org/essUserAccountAction!toAddEssOfUserAccount.action?id='+id+'&employeeId='+empId,
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 660,
						height : 380,
						title:"账户",
						html:html,
						callback : function(action){
							if(action == 'ok'){
								$("#userAccountForm").validationEngine('validate');
								var queryString = $('#userAccountForm').formSerialize(); 
								$.post('${vix}/common/org/essUserAccountAction!saveOrUpdateEssOfUserAccount.action?employeeId='+empId,
									queryString,
									function(result){
										asyncbox.success(result,"<s:text name='账户'/>",function(action){
											$('#dgdA3').datagrid("reload");
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
	
	loadEssGrid();
	
	function addOrderItem(){
		$.ajax({
			  url:'${vix}/template/simpleGridAction!saveOrUpdateCustomer.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 760,
						height : 440,
						title:"客户信息",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								 
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}

	function addOrderMemo(){
		$.ajax({
			  url:'${vix}/template/simpleGridAction!addOrderMemo.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 1024,
						height : 540,
						title:"客户信息",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								 
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	function resize(){
		$('#test').datagrid('resize', {
			width:700,
			height:400
		});
	}
	function getSelected(){
		var selected = $('#test').datagrid('getSelected');
		if (selected){
			alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
		}
	}
	function getSelections(){
		var ids = [];
		var rows = $('#test').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			ids.push(rows[i].code);
		}
		alert(ids.join(':'));
	}
	function clearSelections(){
		$('#test').datagrid('clearSelections');
	}
	function selectRow(){
		$('#test').datagrid('selectRow',2);
	}
	function selectRecord(){
		$('#test').datagrid('selectRecord','002');
	}
	function unselectRow(){
		$('#test').datagrid('unselectRow',2);
	}
	function mergeCells(){
		$('#test').datagrid('mergeCells',{
			index:2,
			field:'addr',
			rowspan:2,
			colspan:2
		});
	}
	function chooseChkCustomer(){
		$.ajax({
			  url:'${vix}/template/simpleGridAction!goChooseChkSimpleGrid.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 760,
						height : 440,
						title:"选择客户",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									$("#customerChk").html(returnValue);
								}else{
									$("#customerChk").html("");
									asyncbox.success("请选择分类信息!","提示信息");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	function saveOrUpdateCustomer(){
		$.ajax({
			  url:'${vix}/template/simpleGridAction!saveOrUpdateCustomer.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 760,
						height : 440,
						title:"客户信息",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								 
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
	 * 保存职员基本信息
	 */
	function saveOrUpdateEss(){
		$("#employeeEssForm").validationEngine('validate');
		
		var queryString = $('#employeeEssForm').formSerialize(); 
		$.post('${vix}/hr/employeessAction!saveOrUpdate.action?oId=${entity.organizationUnit.id}',
			queryString,
			function(result){
				var msgObj = $.parseJSON(result);
				var msg = msgObj.msg;
				var objId = msgObj.objId;
				
				$("#employeeId").val(objId);
				$("#essEmployeeId").val(objId);
				asyncbox.success(msg,"<s:text name='职员基本信息'/>",function (action){
				});
			}
		 );
	}
	
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
	function subBirthday(){
		var idNumber = $("#idNumber").val();
		var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		var re = new RegExp(reg);
		if(re.test(idNumber)){
			$("#birthDate").val(idNumber.substring(6, 10) + "-" + idNumber.substring(10, 12) + "-" + idNumber.substring(12, 14));
		}
		else{
			return false;
		}
	}
</script>

<div class="order">
	<dl>
		<dt></dt>
		<dd class="clearfix">
			<div class="order_table">
				<div class="voucher newvoucher">
					<dl>
						<dt>
							<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
								<s:text name="calculator" /></a>
							</span> <strong>基本信息</strong>
						</dt>
						<dd style="display: block;">
							<form id="employeeEssForm">
								<table style="border: none;">
									<tr height="40">
										<td align="right">所属部门:&nbsp;</td>
										<td><span id="parentUnitName">${entity.organizationUnit.fs}</span> <input type="hidden" id="orgUnitId" name="entityForm.organizationUnit.id" value="${entity.organizationUnit.id}" /></td>
									</tr>
									<tr height="40">
										<td align="right">员工类型：</td>
										<td><s:select list="#{'1':'在职','0':'后备'}" id="employeeType" name="entityForm.employeeType" value="%{entity.employeeType}" theme="simple"></s:select></td>
									</tr>
									<tr>
										<td align="right"><input type="hidden" id="essEmployeeId" name="entityForm.id" value="${entity.id}" /> 员工编码：</td>
										<td><input name="entityForm.code" type="text" size="30" value="${entity.code}" /></td>
										<td align="right">姓名：</td>
										<td><input name="entityForm.name" type="text" size="30" value="${entity.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
									</tr>
									<tr>
										<td align="right">曾用名：</td>
										<td><input name="entityForm.oldName" type="text" size="30" value="${entity.oldName}" /></td>
										<td align="right">姓名缩写</td>
										<td><input name="entityForm.fsName" type="text" size="30" value="${entity.fsName}" /></td>
									</tr>
									<tr>
										<td align="right">身份证号：</td>
										<td><input id="idNumber" name="entityForm.idNumber" type="text" size="30" value="${entity.idNumber}" onblur="subBirthday();" /></td>
										<td align="right">出生日期：</td>
										<td><input id="birthDate" name="entityForm.birthday" value="<s:date name="%{entity.birthday}" format="yyyy-MM-dd"/>" type="text" /> <img onclick="showTime('birthDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
									</tr>
									<tr>
										<td align="right">血型：</td>
										<td><s:select list="#{'A':'A','B':'B','AB':'AB','O':'O'}" id="bloodType" name="entityForm.bloodType" value="%{entity.bloodType}" theme="simple"></s:select></td>
										<td align="right">性别：</td>
										<td><s:select list="#{'1':'男','0':'女'}" id="gender" name="entityForm.gender" value="%{entity.gender}" theme="simple"></s:select></td>
									</tr>
									<tr>
										<td align="right">学历：</td>
										<td><input name="entityForm.qualificationsCode" type="text" size="30" value="${entity.qualificationsCode}" /></td>
										<td align="right">科系：</td>
										<td><input name="entityForm.departmentCode" type="text" size="30" value="${entity.departmentCode}" /></td>
									</tr>
									<tr>
										<td align="right">员工职号：</td>
										<td><input name="entityForm.staffJobNumber" type="text" size="30" value="${entity.staffJobNumber}" /></td>
										<td align="right">户籍地址：</td>
										<td><input name="entityForm.residenceAddress" type="text" size="30" value="${entity.residenceAddress}" /></td>
									</tr>
									<tr>
										<td align="right">出生地：</td>
										<td><input name="entityForm.birthplace" type="text" size="30" value="${entity.birthplace}" /></td>
										<td align="right">是否离职:&nbsp;</td>
										<td><s:radio list="#{'1':'是','0':'否'}" name="entityForm.isDemission" value="0" theme="simple"></s:radio></td>
									</tr>
									<tr>
										<td align="right">入职日期：</td>
										<td><input id="entityTime" name="entityForm.entityTime" value="<s:date name="%{entity.entityTime}" format="yyyy-MM-dd"/>" type="text" /> <img onclick="showTime('entityTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										<td align="right">联系电话：</td>
										<td><input name="entityForm.telephone" type="text" size="30" value="${entity.telephone}" /></td>
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
					<li class="current"><a onclick="javascript:tab(3,1,'a',event);">岗位</a></li>
					<li><a onclick="javascript:$('#a2').attr('style','');tab(3,2,'a',event)">联系方式</a></li>
					<li><a onclick="javascript:$('#a3').attr('style','');tab(3,3,'a',event)">帐号</a></li>
				</ul>
			</div>
			<div id="a1" style="width: 100%;">
				<div class="right_btn nomargin"></div>
				<table id="dgdA1"></table>
			</div>
			<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div style="padding: 8px;">
					<table id="dlReceivedAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlReceivedAddressTb',url: '${vix}/hr/employeessAction!getLinkmanJson.action?employeeId=${entity.id}'">
						<thead>
							<tr>
								<th data-options="field:'employeeCode',align:'center',width:120,editor:'text'">序号</th>
								<th data-options="field:'contact',width:200,align:'center',editor:'text'">联系人</th>
								<th data-options="field:'contactType',width:200,align:'center',editor:'text'">类型</th>
								<th data-options="field:'contactContent',width:200,align:'center',editor:'text'">内容</th>
								<th data-options="field:'remarks',width:200,align:'center',editor:'text'">备注</th>
							</tr>
						</thead>
					</table>
					<div id="dlReceivedAddressTb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addcItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
							data-options="iconCls:'icon-edit',plain:true" onclick="editcItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removecItem()"><span class="l-btn-left"><span
								class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
					</div>
					<script type="text/javascript">
							$('#dlReceivedAddress').datagrid();
							function addcItem(){
								$.ajax({
									  url:'${vix}/hr/employeessAction!goAddLinkman.action?id=${entity.id}',
									  cache: false,
									  success: function(html){
									 	  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#caForm').validationEngine('validate')){
															$.post('${vix}/hr/employeessAction!saveOrUpdateLinkman.action?id=${entity.id}', {
																'linkman.id' : $("#daId").val(),
																'linkman.employeeCode' : $("#employeeCode").val(),
																'linkman.contact' : $("#contact").val(),
																'linkman.contactType' : $("#contactType").val(),
																'linkman.contactContent' : $("#contactContent").val(),
																'linkman.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlReceivedAddress').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function editcItem(){
								var rows = $('#dlReceivedAddress').datagrid('getSelected');
								if(null == rows){
									alert("请选择行！");
									return;
									
								}
								$.ajax({
									  url:'${vix}/hr/employeessAction!goAddLinkman.action?id=${entity.id}&linkid='+rows.id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#caForm').validationEngine('validate')){
															$.post('${vix}/hr/employeessAction!saveOrUpdateLinkman.action?id=${entity.id}', {
																'linkman.id' : $("#daId").val(),
																'linkman.employeeCode' : $("#employeeCode").val(),
																'linkman.contact' : $("#contact").val(),
																'linkman.contactType' : $("#contactType").val(),
																'linkman.contactContent' : $("#contactContent").val(),
																'linkman.remarks' : $("#remarks").val(),
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlReceivedAddress').datagrid("reload");
																	});
														}else{
										  					return false;
										  				}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function removecItem(){
								var row = $('#dlReceivedAddress').datagrid('getSelected');
								if(row){
									var index = $('#dlReceivedAddress').datagrid('getRowIndex',row);
									$('#dlReceivedAddress').datagrid('deleteRow', index);
									$.ajax({url:'${vix}/hr/employeessAction!deleteLinkmanById.action?linId='+row.id,cache: false});
								}
							}
					</script>
				</div>
			</div>

			<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
				<div class="right_btn nomargin"></div>
				<table id="dgdA3"></table>
			</div>
		</div>
	</dl>
</div>
<!--oder-->
<div class="sub_menu sub_menu_bot">
	<div class="drop">
		<p></p>
	</div>
</div>
<!--submenu-->
<!-- content -->