<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
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
	

//弹出组织树
function chooseParentCategory(tag){
	$.ajax({
		  url:'${vix}/hr/trainReAction!goChooseCategory.action',
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
									else if (tag==3){
										$("#orgDepartment").val(result[1]);
									}
									else{
									$("#job").val(result[1]);
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

/** 保存培训资源 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	if($('#reactForm').validationEngine('validate')){
		$.post('${vix}/hr/trainReAction!saveOrUpdate.action',
			{
			'trainingResources.id':$("#id").val(),
			'trainingResources.resourcesCode':$("#resourcesCode").val(),
			'trainingResources.resourcesName':$("#resourcesName").val(),
			'trainingResources.whetherEffective':$("#whetherEffective").val(),
			'trainingResources.whetherTheGeneral':$("#whetherTheGeneral").val(),
			'trainingResources.applicationPost':$("#applicationPost").val(),
			'trainingResources.remarks':$("#remarks").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainReAction!goList.action?menuId=menuOrder');
			}
		 );
	}
}

/** 保存并新增培训资源 */
function saveOrAdd(){
	var orderItemStr = "";
	if($('#reactForm').validationEngine('validate')){
		$.post('${vix}/hr/trainReAction!saveOrUpdate.action',
			{
			'trainingResources.id':$("#id").val(),
			'trainingResources.resourcesCode':$("#resourcesCode").val(),
			'trainingResources.resourcesName':$("#resourcesName").val(),
			'trainingResources.whetherEffective':$("#whetherEffective").val(),
			'trainingResources.whetherTheGeneral':$("#whetherTheGeneral").val(),
			'trainingResources.applicationPost':$("#applicationPost").val(),
			'trainingResources.remarks':$("#remarks").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainReAction!goList.action');
			}
		 );
	}
}


$("#reactForm").validationEngine();
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
				<li><a href="#"><img src="img/hr_re.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainReAction!goList.action');">教育培训</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainReAction!goList.action');">培训资源</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<form id="reactForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/hr/trainReAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增培训资源" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${trainingResources.id}" />
											<td align="right">资源编码：</td>
											<td><input name="resourcesCode" id="resourcesCode" type="text" size="30" value="${trainingResources.resourcesCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">资源名称：</td>
											<td><input name="resourcesName" id="resourcesName" type="text" size="30" value="${trainingResources.resourcesName }" /></td>
										</tr>
										<tr>
											<td align="right">是否有效：</td>
											<td><input name="whetherEffective" id="whetherEffective" type="text" size="30" value="${trainingResources.whetherEffective }" /></td>
											<td align="right">是否通用：</td>
											<td><input name="whetherTheGeneral" id="whetherTheGeneral" type="text" size="30" value="${trainingResources.whetherTheGeneral }" /></td>
										</tr>
										<tr>
											<td align="right">适用岗位：</td>
											<td><input name="applicationPost" id="applicationPost" type="text" size="30" value="${trainingResources.applicationPost }" /></td>
											<td align="right">备注：</td>
											<td><input name="remarks" id="remarks" type="text" size="30" value="${trainingResources.remarks }" /></td>
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
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />培训课程</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />培训资料</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />培训设施</a></li>
							<li><a onclick="javascript:$('#a4').attr('style','');tab(8,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />培训教师</a></li>
							<!--<li><a onclick="javascript:$('#a5').attr('style','');tab(8,5,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						<li><a onclick="javascript:$('#a6').attr('style','');tab(8,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />附件/文本</a></li>
						<li><a onclick="javascript:$('#a7').attr('style','');tab(8,7,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />交货地点</a></li>
						<li><a onclick="javascript:$('#a8').attr('style','');tab(8,8,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />审批项</a></li>-->
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
										    title:'培训课程明细 ',
										 	modal:true,
											width : 724,
											height :450,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#daForm').validationEngine('validate')){
														$.post('${vix}/hr/trainReAction!saveOrUpdateTrainingCourse.action',
																{'id':$("#id").val(),
																  'trainingCourse.id':$("#daId").val(),
																  'trainingCourse.courseCode':$("#courseCode").val(),
																  'trainingCourse.courseType':$("#courseType").val(),
																  'trainingCourse.courseName':$("#courseName").val(),
																  'trainingCourse.courseeducation':$("#courseeducation").val(),
																  'trainingCourse.remarkss':$("#remarkss").val(),
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
							url: '${vix}/hr/trainReAction!getTrainingCourseJson.action?id=${trainingResources.id}',
							title: '培训课程明细',
							width: 'auto',
							height: '450',
							fitColumns: true,
							columns:[[
								{field:'id',title:'编号',width:100,align:'center'},
								{field:'courseCode',title:'课程编码',width:200,align:'center'},
								{field:'courseType',title:'课程类别',width:200,align:'center'},
								{field:'courseName',title:'课程名称',width:200,align:'center'},
								{field:'courseeducation',title:'课程简介',width:200,align:'center'},
								{field:'remarkss',title:'备注',width:200,align:'center'},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									saveDeliveryAddress('${vix}/hr/trainReAction!goAddTrainingCourse.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#dlAddress2').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										saveDeliveryAddress('${vix}/hr/trainReAction!goAddTrainingCourse.action?id='+row.id)
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
										$.ajax({url:'${vix}/hr/trainReAction!deleteTrainingCourseById.action?id='+rows[i].id,cache: false});
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

					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
						function saveTrainingData(url){
							$.ajax({
								  url:url,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										    title:'培训资料明细 ',
										 	modal:true,
											width : 724,
											height :450,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#tdForm').validationEngine('validate')){
														$.post('${vix}/hr/trainReAction!saveOrUpdateTrainingData.action',
																{'id':$("#id").val(),
																  'trainingData.id':$("#daId").val(),
																  'trainingData.datumCode':$("#datumCode").val(),
																  'trainingData.datumType':$("#datumType").val(),
																  'trainingData.datumName':$("#datumName").val(),
																  'trainingData.profile':$("#profile").val(),
																  'trainingData.datumauthor':$("#datumauthor").val(),
																  'trainingData.datumCost':$("#datumCost").val(),
																  'trainingData.publishingUnit':$("#publishingUnit").val(),
																  'trainingData.storageLocation':$("#storageLocation").val(),
																  'trainingData.remarksss':$("#remarksss").val(),
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#soAttach').datagrid("reload");
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
						$('#soAttach').datagrid({
							url: '${vix}/hr/trainReAction!getTrainingDataJson.action?id=${trainingResources.id}',
							title: '培训资料明细',
							width: 'auto',
							height: '450',
							fitColumns: true,
							columns:[[
								{field:'id',title:'编号',width:100,align:'center'},
								{field:'datumCode',title:'资料编码',width:200,align:'center'},
								{field:'datumType',title:'资料类别',width:200,align:'center'},
								{field:'datumName',title:'资料名称',width:200,align:'center'},
								{field:'profile',title:'资料简介',width:200,align:'center'},
								{field:'datumauthor',title:'资料作者',width:200,align:'center'},
								{field:'datumCost',title:'资料费用',width:200,align:'center'},
								{field:'publishingUnit',title:'出版单位',width:200,align:'center'},
								{field:'storageLocation',title:'存放位置',width:200,align:'center'},
								{field:'remarksss',title:'备注',width:200,align:'center'},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									saveTrainingData('${vix}/hr/trainReAction!goAddTrainingData.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#soAttach').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										saveTrainingData('${vix}/hr/trainReAction!goAddTrainingData.action?id='+row.id)
									}
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#soAttach').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#soAttach').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#soAttach').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/hr/trainReAction!deleteTrainingDataById.action?id='+rows[i].id,cache: false});
									}
								}
							}]
						});
				</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
						function saveTrainingFacilities(url){
							$.ajax({
								  url:url,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										    title:'培训设施明细 ',
										 	modal:true,
											width : 724,
											height :450,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#tfForm').validationEngine('validate')){
														$.post('${vix}/hr/trainReAction!saveOrUpdateTrainingFacilities.action',
																{'id':$("#id").val(),
																  'trainingFacilities.id':$("#daId").val(),
																  'trainingFacilities.facilitiesCode':$("#facilitiesCode").val(),
																  'trainingFacilities.facilitiesType':$("#facilitiesType").val(),
																  'trainingFacilities.facilitiesName':$("#facilitiesName").val(),
																  'trainingFacilities.classroomTraining':$("#classroomTraining").val(),
																  'trainingFacilities.facilitiesNumber':$("#facilitiesNumber").val(),
																  'trainingFacilities.remarsks':$("#remarsks").val(),
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#tfAttach').datagrid("reload");
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
						$('#tfAttach').datagrid({
							url: '${vix}/hr/trainReAction!getTrainingFacilitiesJson.action?id=${trainingResources.id}',
							title: '培训设施明细',
							width: 'auto',
							height: '450',
							fitColumns: true,
							columns:[[
								{field:'id',title:'编号',width:100,align:'center'},
								{field:'facilitiesCode',title:'设施编码',width:200,align:'center'},
								{field:'facilitiesType',title:'设施类别',width:200,align:'center'},
								{field:'facilitiesName',title:'设施名称',width:200,align:'center'},
								{field:'classroomTraining',title:'培训教室',width:200,align:'center'},
								{field:'facilitiesNumber',title:'数量',width:200,align:'center'},
								{field:'remarsks',title:'备注',width:200,align:'center'},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									saveTrainingFacilities('${vix}/hr/trainReAction!goAddTrainingFacilities.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#tfAttach').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										saveTrainingFacilities('${vix}/hr/trainReAction!goAddTrainingFacilities.action?id='+row.id)
									}
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#tfAttach').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#tfAttach').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#tfAttach').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/hr/trainReAction!deleteTrainingFacilitiesById.action?id='+rows[i].id,cache: false});
									}
								}
							}]
						});
				</script>
						<div style="padding: 8px;">
							<table id="tfAttach"></table>
						</div>
					</div>
					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
						function saveTrainingTeacher(url){
							$.ajax({
								  url:url,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										    title:'培训教师明细 ',
										 	modal:true,
											width : 724,
											height :450,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#ttForm').validationEngine('validate')){
														$.post('${vix}/hr/trainReAction!saveOrUpdateTrainingTeacher.action',
																{'id':$("#id").val(),
																  'trainingTeacher.id':$("#daId").val(),
																  'trainingTeacher.teacherCode':$("#teacherCode").val(),
																  'trainingTeacher.teacherType':$("#teacherType").val(),
																  'trainingTeacher.teacherName':$("#teacherName").val(),
																  'trainingTeacher.education':$("#education").val(),
																  'trainingTeacher.professional':$("#professional").val(),
																  'trainingTeacher.telephone':$("#telephone").val(),
																  'trainingTeacher.teacherIntroduction':$("#teacherIntroduction").val(),
																  'trainingTeacher.remark':$("#remark").val(),
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#ttAttach').datagrid("reload");
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
						$('#ttAttach').datagrid({
							url: '${vix}/hr/trainReAction!getTrainingTeacherJson.action?id=${trainingResources.id}',
							title: '培训教师明细 ',
							width: 'auto',
							height: '450',
							fitColumns: true,
							columns:[[
								{field:'id',title:'编号',width:100,align:'center'},
								{field:'teacherCode',title:'教师编码',width:200,align:'center'},
								{field:'teacherType',title:'教师类别',width:200,align:'center'},
								{field:'teacherName',title:'教师姓名',width:200,align:'center'},
								{field:'education',title:'学历',width:200,align:'center'},
								{field:'professional',title:'专业',width:200,align:'center'},
								{field:'telephone',title:'联系电话',width:200,align:'center'},
								{field:'teacherIntroduction',title:'教师简介',width:200,align:'center'},
								{field:'remark',title:'备注',width:200,align:'center'},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									saveTrainingTeacher('${vix}/hr/trainReAction!goAddTrainingTeacher.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#ttAttach').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										saveTrainingTeacher('${vix}/hr/trainReAction!goAddTrainingTeacher.action?id='+row.id)
									}
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#ttAttach').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#ttAttach').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#ttAttach').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/hr/trainReAction!deleteTrainingTeacherById.action?id='+rows[i].id,cache: false});
									}
								}
							}]
						});
				</script>
						<div style="padding: 8px;">
							<table id="ttAttach"></table>
						</div>
					</div>
					<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA5"></table>
					</div>
					<div id="a6" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA6"></table>
					</div>
					<div id="a7" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA7"></table>
					</div>
					<div id="a8" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA8"></table>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->