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
/** 保存培训需求*/
function saveOrUpdateOrder(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/hr/trainingChannelAction!saveOrUpdate.action',
			{
			'trainingChannel.id':$("#id").val(),
			'trainingChannel.channelName':$("#channelName").val(),
			'trainingChannel.channelCost':$("#channelCost").val(),
			'trainingChannel.province':$("#province").val(),
			'trainingChannel.city':$("#city").val(),
			'trainingChannel.contactInformation':$("#contactInformation").val(),
			'trainingChannel.channelType':$("#channelType").val(),
			'trainingChannel.channelDescription':$("#channelDescription").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainingChannelAction!goList.action?menuId=menuOrder');
			}
		 );
	}
}

/** 保存并新增培训需求*/
function saveOrAdd(){
	var orderItemStr = "";
	 var parentId = $("#parentId").val().split(/[P,O,C]/);
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/hr/trainingChannelAction!saveOrUpdate.action',
			{
			'trainingChannel.id':$("#id").val(),
			'trainingChannel.channelName':$("#channelName").val(),
			'trainingChannel.channelCost':$("#channelCost").val(),
			'trainingChannel.province':$("#province").val(),
			'trainingChannel.city':$("#city").val(),
			'trainingChannel.contactInformation':$("#contactInformation").val(),
			'trainingChannel.channelType':$("#channelType").val(),
			'trainingChannel.channelDescription':$("#channelDescription").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainingChannelAction!goSaveOrUpdate.action');
			}
		 );
	}
}

//页面首次加载
$(function(){
	$("#channelType").val('${trainingChannel.channelType }');
});

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
				<li><a href="#"><img src="img/hr_training.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#">教育培训</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainingChannelAction!goList.action');">培训资源</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainingChannelAction!goList.action');">培训渠道</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainingChannelAction!goList.action');">新增培训渠道</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<form id="replanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/hr/trainingChannelAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增培训渠道" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${trainingChannel.id}" />
											<td align="right">渠道名称：</td>
											<td><input id="channelName" name="channelName" value="${trainingChannel.channelName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">渠道费用：</td>
											<td><input id="channelCost" name="channelCost" value="${trainingChannel.channelCost }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">所在省份：</td>
											<td><input id="province" name="province" value="${trainingChannel.province }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">所在城市：</td>
											<td><input id="city" name="city" value="${trainingChannel.city }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">联系方式：</td>
											<td><input name="contactInformation" id="contactInformation" type="text" size="30" value="${trainingChannel.contactInformation}" /></td>
											<td align="right">渠道类型：</td>
											<td><select id="channelType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">内部渠道</option>
													<option value="2">外部渠道</option>
											</select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">描述</td>
											<td colspan="2"><textarea id="channelDescription" name="channelDescription" class="channelDescription" rows="2" style="width: 250px">${trainingChannel.channelDescription }</textarea></td>
											</t>
									</table>
									<script type="text/javascript">
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
								</dd>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(1,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />培训讲师</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/hr/trainingChannelAction!getTrainingCourseJson.action?id=${trainingChannel.id}'">
								<thead>
									<tr>
										<th data-options="field:'lecturerCode',align:'center',width:120,editor:'text'">编码</th>
										<th data-options="field:'lecturerName',width:200,align:'center',editor:'text'">姓名</th>
										<th data-options="field:'lecturerPosition',width:120,align:'center',editor:'text'">职位</th>
										<th data-options="field:'branches',width:120,align:'center',editor:'text'">部门</th>
										<th data-options="field:'lecturerLevel',width:120,align:'center',editor:'text'">讲师级别</th>
										<th data-options="field:'lecturerType',width:120,align:'center',editor:'text'">讲师类别</th>
										<th data-options="field:'lecturerCost',width:120,align:'center',editor:'text'">费用</th>
										<th data-options="field:'lecturerIntroduction',width:120,align:'center',editor:'text'">简介</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="baddItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="beditItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="bremoveDlLineItem()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlLineItem').datagrid();  
								function baddItem(){
									$.ajax({
										  url:'${vix}/hr/trainingChannelAction!goAddtrainingChannel.action',
										  cache: false,
										  success: function(html){
											  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
											  $(".ab_c .content").css("margin-bottom","0");
											  $('.ab_c .content').parent().css('position','relative');
											  asyncbox.open({
												 	modal:true,
													width : 1000,
													height : 500,
													title:"选择培训讲师",
													html:html,
													callback : function(action,returnValue){
														if(action == 'ok'){
															if(returnValue != undefined){
																var rv = returnValue.split(",");
																$.ajax({
																	  url:'${vix}/hr/trainingChannelAction!saveOrUpdateTrainingCourse.action?tcId='+rv[0]+"&id=${trainingChannel.id}",
																	  cache: false,
																	  success: function(html){
																		  showMessage(html);
																		  setTimeout("",1000);
																		  $('#dlLineItem').datagrid("reload");
																	  }
																});
															}else{
																asyncbox.success("请选择培训讲师!","提示信息");
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
										  url:'${vix}/hr/trainingChannelAction!goAddtrainingChannel.action?lineItemId='+rows.id,
										  cache: false,
										  success: function(html){
											  asyncbox.open({
												 	modal:true,
													width : 700,
													height : 400,
													title:"添加讲师",
													html:html,
													callback : function(action){
														if(action == 'ok'){
															if($('#orderItemForm').validationEngine('validate')){
																$.post('${vix}/hr/trainingChannelAction!saveOrUpdateTrainingCourse.action', {
																		'id' : $("#id").val(),
																		'trainingLecturer.id' : $("#oiId").val(),
																		'trainingLecturer.lecturerCode' : $("#lecturerCode").val(),
																		'trainingLecturer.lecturerName' : $("#lecturerName").val(),
																		'trainingLecturer.lecturerPosition' : $("#lecturerPosition").val(),
																		'trainingLecturer.branches' : $("#branches").val(),
																		'trainingLecturer.lecturerType' : $("#lecturerType").val(),
																		'trainingLecturer.lecturerLevel' : $("#lecturerLevel").val(),
																		'trainingLecturer.lecturerCost' : $("#lecturerCost").val(),
																		'trainingLecturer.lecturerIntroduction' : $("#lecturerIntroduction").val()
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
	</form>
</div>