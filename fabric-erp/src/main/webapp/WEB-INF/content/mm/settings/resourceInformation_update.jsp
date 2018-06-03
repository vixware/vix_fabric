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
	
function chooseEmployees(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 800,
						height : 600,
						title:"选择人员",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var selectIds = "";
									var selectNames = "";
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											
											selectIds += "," + v[0];
											selectNames = v[1];
										}
									}
									$("#schemer").val(selectNames);
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
		  url:'${vix}/mm/resourceInformationAction!goChooseCategory.action',
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
									$("#applicationDepartment").val(result[1]);
									}
									else if (tag==1){
										$("#applicationTrainingDepartment").val(result[1]);
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
	var parentId = $("#parentId").val().split(/[P,O,C]/);
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/mm/resourceInformationAction!saveOrUpdate.action',
			{
				'resourceiIformation.id':$("#id").val(),
				'resourceiIformation.organizationUnit.id':parentId[0],
				'resourceiIformation.applicationDepartment':$("#applicationDepartment").val(),
				'resourceiIformation.resourcesCode':$("#resourcesCode").val(),
				'resourceiIformation.resourcesName':$("#resourcesName").val(),
				'resourceiIformation.resourcesType':$("#resourcesType").val(),
				'resourceiIformation.chargingType':$("#chargingType").val(),
				'resourceiIformation.remarks':$("#remarks").val(),
				'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/mm/resourceInformationAction!goList.action?menuId=menuOrder');
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
				<li><a href="#"><img src="img/mm_zygl.png" alt="" />生产管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mm/resourceInformationAction!goList.action');">资源资料</a></li>
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
						</div> <a href="#"><img width="22" height="22" title="导出" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/mm/resourceInformationAction!goList.action');"><img width="22" height="22" title="返回" alt="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增资源资料 </b> <i></i>
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
											<td align="right">申请部门：</td>
											<td><input id="applicationDepartment" name="applicationDepartment" value="${resourceiIformation.applicationDepartment}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${resourceiIformation.organizationUnit.id}" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentCategory(0);" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<%--检查ID，判断修改--%>
											<input type="hidden" id="id" name="id" value="${resourceiIformation.id}" />
											<td align="right">资源编码：</td>
											<td><input id="resourcesCode" name="resourcesCode" value="${resourceiIformation.resourcesCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">资源名称：</td>
											<td><input name="resourcesName" id="resourcesName" type="text" size="30" value="${resourceiIformation.resourcesName}" /></td>
										</tr>
										<tr>
											<td align="right">资源类别：</td>
											<td><input name="resourcesType" id="resourcesType" type="text" size="30" value="${resourceiIformation.resourcesType}" /></td>
											<td align="right">计费类型：</td>
											<td><input name="chargingType" id="chargingType" type="text" size="30" value="${resourceiIformation.chargingType}" /></td>
										</tr>
										<tr>
											<th align="right">备注：</th>
											<td colspan="3"><textarea id="remarks" name="remarks">${resourceiIformation.remarks}</textarea></td>
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
							<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />资源资料明细</a></li>
						</ul>
					</div>

					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
						function saveDeliveryAddress(url){
							$.ajax({
								  url:url,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										  	title:'资源资料明细 ',
										 	modal:true,
											width : 724,
											height :450,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#daForm').validationEngine('validate')){
														$.post('${vix}/mm/resourceInformationAction!saveOrUpdateResourceiIformationDetail.action',
																{'id':$("#id").val(),
																  'detail.id':$("#daId").val(),
																  'detail.org':$("#org").val(),
																  'detail.orgName':$("#orgName").val(),
																  'detail.productivityCalculation':$("#productivityCalculation").val(),
																  'detail.quantityAvailable':$("#quantityAvailable").val(),
																  'detail.utilizationRate':$("#utilizationRate").val(),
																  'detail.efficiency':$("#efficiency").val(),
																  'detail.effectiveSchedulingRelated':$("#effectiveSchedulingRelated").val(),
																  'detail.criticalResources':$("#criticalResources").val(),
																  'detail.zikeRate':$("#zikeRate").val(),
																  'detail.overloadPercentage':$("#overloadPercentage").val(),
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#dlAddress2').datagrid("reload");
																}
														);
													}
												}
											},
											btnsbar : $.btn.OKCANCEL
										});
								  }
							});
						}
						$('#dlAddress2').datagrid({
							url: '${vix}/mm/resourceInformationAction!getResourceiIformationDetailJson.action?id=${resourceiIformation.id}',
							title: '资源资料明细',
							width: 'auto',
							height: '450',
							fitColumns: true,
							columns:[[
								{field:'id',title:'编号',width:100,align:'center'},
								{field:'org',title:'工作中心',width:200,align:'center'},
								{field:'orgName',title:'工作中心名称',width:200,align:'center'},
								{field:'productivityCalculation',title:'计算产能',width:200,align:'center'},
								{field:'quantityAvailable',title:'可用数量',width:200,align:'center'},
								{field:'utilizationRate',title:'利用率%',width:200,align:'center'},
								{field:'efficiency',title:'效率%',width:200,align:'center'},
								{field:'effectiveSchedulingRelated',title:'有效排程相关',width:200,align:'center'},
								{field:'criticalResources',title:'关键资源',width:200,align:'center'},
								{field:'zikeRate',title:'资课费率%',width:200,align:'center'},
								{field:'overloadPercentage',title:'超载百分比 %',width:200,align:'center'},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'新增',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									saveDeliveryAddress('${vix}/mm/resourceInformationAction!goAddResourceiIformationDetail.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#dlAddress2').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										saveDeliveryAddress('${vix}/mm/resourceInformationAction!goAddResourceiIformationDetail.action?id='+row.id)
									}
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#dlAddress2').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#dlAddress2').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#dlAddress2').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/mm/resourceInformationAction!deleteResourceiIformationDetailById.action?id='+rows[i].id,cache: false});
									}
								}
							}]
						});
						//为原始Date类型拓展format一个方法，用于日期显示的格式化
						Date.prototype.format = function (format) 
						 {
						     var o = {
						         "M+": this.getMonth() + 1, //month 
						         "d+": this.getDate(),    //day 
						         "h+": this.getHours(),   //hour 
						         "m+": this.getMinutes(), //minute 
						         "s+": this.getSeconds(), //second 
						         "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter 
						         "S": this.getMilliseconds() //millisecond 
						     }
						     if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
						     (this.getFullYear() + "").substr(4 - RegExp.$1.length));
						     for (var k in o) if (new RegExp("(" + k + ")").test(format))
						         format = format.replace(RegExp.$1,
						       RegExp.$1.length == 1 ? o[k] :
						         ("00" + o[k]).substr(("" + o[k]).length));
						     return format;
						 }
						
						//格式化日期
						function formatDatebox(value) {
					         if (value == null || value == '') {
					             return '';
					         }
					     var dt;
					     if (value instanceof Date) {
					         dt = value;
					     }
					     else {
					         dt = new Date(value);
					         if (isNaN(dt)) {
					             value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
					             dt = new Date();
					             dt.setTime(value);
					         }
					     }
					 
					    return dt.format("yyyy-MM-dd");  //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
					 }
				</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->