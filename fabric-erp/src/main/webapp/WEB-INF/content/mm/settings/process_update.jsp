<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- <script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script> --%>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>

<script type="text/javascript">
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
	/** 选择工作中心  */
	function chooseCourse(){
		$.ajax({
			  url:'${vix}/mm/processAction!goChooseCourse.action',
			  cache: false,
			  success: function(html){
				  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
				  $(".ab_c .content").css("margin-bottom","0");
				  $('.ab_c .content').parent().css('position','relative');
				  asyncbox.open({
					 	modal:true,
						width : 1000,
						height : 500,
						title:"选择工作中心",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var rv = returnValue.split(",");
									$("#orgid").val(rv[0]);
									$("#orgName").val(rv[1]);
								}else{
									asyncbox.success("请选择工作中心!","提示信息");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
//弹出组织树
function chooseParentCategory(tag){
	$.ajax({
		  url:'${vix}/mm/processAction!goChooseCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择组织",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
									$("#parentId").val(result[0]);
									
									if(tag==0){
									$("#categoryName").val(result[1]);
									}
									else if (tag==1){
										$("#categoryName1").val(result[1]);
									}
									else{
									$("#theJob").val(result[1]);
									}
							}else{
								$("#parentId").val("");
								$("#categoryName").html("");
								asyncbox.success("请选择分类信息!","提示信息");
							}
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
<%-- 处理保存按钮功能--%>
function saveOrUpdateOrder(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/mm/processAction!saveOrUpdate.action',
			{
				'processManagement.id':$("#id").val(),
				'processManagement.processCode':$("#processCode").val(),
				'processManagement.processDescription':$("#processDescription").val(),
				'processManagement.types':$("#types").val(),
				'processManagement.creDate':$("#creDate").val(),
				'processManagement.repPoint':$("#repPoint").val(),
				'processManagement.orgName':$("#orgName").val(),
				'processManagement.eeverseBlankingProcess':$("#eeverseBlankingProcess").val(),
				'processManagement.outsourcingProcess':$("#outsourcingProcess").val(),
				'processManagement.manufacturerCode':$("#manufacturerCode").val(),
				'processManagement.manufacturerName':$("#manufacturerName").val(),
				'processManagement.planOutsourcingProcess':$("#planOutsourcingProcess").val(),
				'processManagement.deliveryPeriod':$("#deliveryPeriod").val(),
				'processManagement.inspectionMethods':$("#inspectionMethods").val(),
				'processManagement.samplingRule':$("#samplingRule").val(),
				'processManagement.samplingRate':$("#samplingRate").val(),
				'processManagement.samplingNumber':$("#samplingNumber").val(),
				'processManagement.inspectionStrictDegree':$("#inspectionStrictDegree").val(),
				'processManagement.qualityInspectionPlan':$("#qualityInspectionPlan").val(),
				'processManagement.customInspectionRules':$("#customInspectionRules").val(),
				'processManagement.inspectionRules':$("#inspectionRules").val(),
				'processManagement.testLevel':$("#testLevel").val(),
				'processManagement.aqlCode':$("#aqlCode").val(),
				'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/mm/processAction!goList.action?menuId=menuOrder');
			}
		 );
	}
}

pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
function currentPager(tag){
	pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
}
$("#replanForm").validationEngine();
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/mm_gxgl.png" alt="" />生产管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mm/processAction!goList.action');">工序管理</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<form id="replanForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" title="保存" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" title="取消" alt="取消"
							src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" title="禁用" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" title="删除" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" title="审批通过" alt="审批通过"
							src="${vix}/common/img/dt_aprroval.png" /></a> <a href="#"><img width="22" height="22" title="驳回" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" title="上一条" alt="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" title="下一条" alt="下一条"
							src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" title="打印" alt="打印" src="${vix}/common/img/dt_print.png"></a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" title="报表" alt="报表" src="${vix}/common/img/dt_report.png"></a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" title="导出" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/mm/processAction!goList.action');"><img width="22" height="22" title="返回" alt="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增工序管理</b> <i></i>
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
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<%--检查ID，判断修改--%>
											<input type="hidden" id="id" name="id" value="${processManagement.id}" />
											<td align="right">工序编码：</td>
											<td><input id="processCode" name="processCode" value="${processManagement.processCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">工序名称：</td>
											<td><input name="processDescription" id="processDescription" type="text" size="30" value="${processManagement.processDescription}" /></td>
										</tr>
										<tr>
											<td align="right">报告点：</td>
											<td><input name="repPoint" id="repPoint" type="text" size="30" value="${processManagement.repPoint}" /></td>
											<td align="right">工作中心名称：</td>
											<td><input id="orgName" name="orgName" value="${processManagement.orgName }" type="text" size="30" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="orgid" name="orgid" value="${processManagement.orgid }" /> <input class="btn" type="button" value="选择" onclick="chooseCourse();" /></td>
										</tr>
										<tr>
											<td align="right">厂商编码：</td>
											<td><input name="manufacturerCode" id="manufacturerCode" type="text" size="30" value="${processManagement.manufacturerCode}" /></td>
											<td align="right">厂商名称：</td>
											<td><input name="manufacturerName" id="manufacturerName" type="text" size="30" value="${processManagement.manufacturerName}" /></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><input name="types" id="types" type="text" size="30" value="${processManagement.types}" /></td>
											<td align="right">检验水平：</td>
											<td><input name="testLevel" id="testLevel" type="text" size="30" value="${processManagement.testLevel}" /></td>
										</tr>
										<tr>
											<td align="right">倒冲工序：</td>
											<td><input name="eeverseBlankingProcess" id="eeverseBlankingProcess" type="text" size="30" value="${processManagement.eeverseBlankingProcess}" /></td>
											<td align="right">委外工序：</td>
											<td><input name="outsourcingProcess" id="outsourcingProcess" type="text" size="30" value="${processManagement.outsourcingProcess}" /></td>
										</tr>

										<tr>
											<td align="right">计划委外工序：</td>
											<td><input name="planOutsourcingProcess" id="planOutsourcingProcess" type="text" size="30" value="${processManagement.planOutsourcingProcess}" /></td>
											<td align="right">交货天数：</td>
											<td><input name="deliveryPeriod" id="deliveryPeriod" type="text" size="30" value="${processManagement.deliveryPeriod}" /></td>
										</tr>
										<tr>
											<td align="right">检验方式：</td>
											<td><input name="inspectionMethods" id="inspectionMethods" type="text" size="30" value="${processManagement.inspectionMethods}" /></td>
											<td align="right">抽检规则：</td>
											<td><input name="samplingRule" id="samplingRule" type="text" size="30" value="${processManagement.samplingRule}" /></td>
										</tr>
										<tr>
											<td align="right">抽检率%：</td>
											<td><input name="samplingRate" id="samplingRate" type="text" size="30" value="${processManagement.samplingRate}" /></td>
											<td align="right">抽检数量：</td>
											<td><input name="samplingNumber" id="samplingNumber" type="text" size="30" value="${processManagement.samplingNumber}" /></td>
										</tr>
										<tr>
											<td align="right">检验严格度：</td>
											<td><input name="inspectionStrictDegree" id="inspectionStrictDegree" type="text" size="30" value="${processManagement.inspectionStrictDegree}" /></td>
											<td align="right">质量检验方案：</td>
											<td><input name="qualityInspectionPlan" id="qualityInspectionPlan" type="text" size="30" value="${processManagement.qualityInspectionPlan}" /></td>
										</tr>
										<tr>
											<td align="right">自定义抽检规则：</td>
											<td><input name="customInspectionRules" id="customInspectionRules" type="text" size="30" value="${processManagement.customInspectionRules}" /></td>
											<td align="right">检验规则：</td>
											<td><input name="inspectionRules" id="inspectionRules" type="text" size="30" value="${processManagement.inspectionRules}" /></td>
										</tr>
										<tr>
											<td align="right">创建时间：</td>
											<td><input id="creDate" name="creDate" value="<fmt:formatDate value='${processManagement.creDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('creDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">AQL：</td>
											<td><input name="aqlCode" id="aqlCode" type="text" size="30" value="${processManagement.aqlCode}" /></td>
										</tr>
									</table>
									<div style="margin: 15px 70px; display: none;">
										<textarea id="content" name="content"></textarea>
										<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
										<script type="text/javascript">
								 var editor = KindEditor.create('textarea[name="content"]',
											{basePath:'${vix}/plugin/KindEditor/',
												width:1100,
												height:300,
												cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
												uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
												fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
												allowFileManager : true 
											}
								 );
								 </script>
									</div>
						</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />资源明细</a></li>
						</ul>
					</div>

					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/mm/processAction!getResourceiIformationJson.action?id=${processManagement.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120,editor:'text'">编号</th>
										<th data-options="field:'id',width:200,align:'center',editor:'text'">行号</th>
										<th data-options="field:'resourcesCode',width:120,align:'center',editor:'text'">资源编码</th>
										<th data-options="field:'resourcesName',width:120,align:'center',editor:'text'">资源名称</th>
										<th data-options="field:'resourcesType',width:120,align:'center',editor:'text'">资源类别</th>
										<th data-options="field:'courseFees',width:120,align:'center',editor:'text'">工时</th>
										<th data-options="field:'natureCourses',width:120,align:'center',editor:'text'">工时(分子)</th>
										<th data-options="field:'natureCoursed',width:120,align:'center',editor:'text'">工时(分母)</th>
										<th data-options="field:'natureCoursef',width:120,align:'center',editor:'text'">效率</th>
										<th data-options="field:'natureCourseg',width:120,align:'center',editor:'text'">是否计划</th>
										<th data-options="field:'chargingType',width:120,align:'center',editor:'text'">计费类型</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="baddItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="baddItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="bremoveDlLineItem()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlLineItem').datagrid();  
							function baddItem(){
								$.ajax({
									  url:'${vix}/mm/processAction!goAddResourceiIformation.action',
									  cache: false,
									  success: function(html){
										  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
										  $(".ab_c .content").css("margin-bottom","0");
										  $('.ab_c .content').parent().css('position','relative');
										  asyncbox.open({
											 	modal:true,
												width : 1000,
												height : 500,
												title:"选择资源",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														if(returnValue != undefined){
															var rv = returnValue.split(",");
															$.ajax({
																  url:'${vix}/mm/processAction!saveOrUpdateResourceiIformation.action?tdId='+rv[0]+"&id=${processManagement.id}",
																  cache: false,
																  success: function(html){
																	  showMessage(html);
																	  setTimeout("",1000);
																	  $('#dlLineItem').datagrid("reload");
																  }
															});
														}else{
															asyncbox.success("请选择资源!","提示信息");
														}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function beditItem(){
								var rows = $('#dlLineItem').datagrid('getSelected');
								if(null == rows){
									alert("请选择一条数据！");
									return;
								}
								$.ajax({
									  url:'${vix}/mm/processAction!goAddResourceiIformation.action?lineItemId='+rows.id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加资源",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#orderItemForm').validationEngine('validate')){
															$.post('${vix}/mm/processAction!saveOrUpdateResourceiIformation.action', {
																	'id' : $("#id").val(),
																	'resourceiIformation.id' : $("#tcId").val(),
																	'resourceiIformation.resourcesCode' : $("#resourcesCode").val(),
																	'resourceiIformation.resourcesName' : $("#resourcesName").val(),
																	'resourceiIformation.resourcesType' : $("#resourcesType").val(),
																	'resourceiIformation.chargingType' : $("#chargingType").val(),
																	}, function(result){
																		showMessage(result);
																		
																		setTimeout("",1000);
																		$('#dlLineItem').datagrid("reload");
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
							function bremoveDlLineItem(){
								var row = $('#dlLineItem').datagrid('getSelected');
								if(row){
									var index = $('#dlLineItem').datagrid('getRowIndex',row);
									$('#dlLineItem').datagrid('deleteRow', index);
								}
							}
					</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->