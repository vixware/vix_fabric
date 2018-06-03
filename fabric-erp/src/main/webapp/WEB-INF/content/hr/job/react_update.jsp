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
		  url:'${vix}/hr/reActAction!goChooseCategory.action',
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
//页面首次加载
$(function(){
	//覆盖地区 
	$("#coverageArea").val('${hrRecruitplan.coverageArea }');
	$("#planStatus").val('${hrRecruitplan.planStatus }');
});
/* 保存招聘活动 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	/** 明细 */
	if($('#reactForm').validationEngine('validate')){
		$.post('${vix}/hr/reActAction!saveOrUpdate.action',
			{
			'hrRecruitactivitity.id':$("#id").val(),
			'hrRecruitactivitity.activityName':$("#activityName").val(),
			'hrRecruitactivitity.activityAddress':$("#activityAddress").val(),
			'hrRecruitactivitity.recruitingObject':$("#recruitingObject").val(),
			'hrRecruitactivitity.activitystartDate':$("#activitystartDate").val(),
			'hrRecruitactivitity.activityendDate':$("#activityendDate").val(),
			'hrRecruitactivitity.mattersNeedAttention':$("#mattersNeedAttention").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/reActAction!goList.action?menuId=menuOrder');
			}
		 );
	}
}

/* 保存并新增招聘活动 */
function saveOrAdd(){
	var orderItemStr = "";
	/** 明细 */
	if($('#reactForm').validationEngine('validate')){
		$.post('${vix}/hr/reActAction!saveOrUpdate.action',
			{
			'hrRecruitactivitity.id':$("#id").val(),
			'hrRecruitactivitity.activityName':$("#activityName").val(),
			'hrRecruitactivitity.activityAddress':$("#activityAddress").val(),
			'hrRecruitactivitity.recruitingObject':$("#recruitingObject").val(),
			'hrRecruitactivitity.activitystartDate':$("#activitystartDate").val(),
			'hrRecruitactivitity.activityendDate':$("#activityendDate").val(),
			'hrRecruitactivitity.mattersNeedAttention':$("#mattersNeedAttention").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/reActAction!goSaveOrUpdate.action');
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
				<li><a href="#"><s:text name="hr_recruitment_mgs" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/reActAction!goList.action');"><s:text name="hr_recruit_activities" /></a></li>
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
						onclick="loadContent('${vix}/hr/reActAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增招聘活动 " /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${hrRecruitactivitity.id}" />
											<td align="right">活动名称：</td>
											<td><input name="activityName" id="activityName" type="text" size="30" value="${hrRecruitactivitity.activityName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">活动地点：</td>
											<td><input name="activityAddress" id="activityAddress" type="text" size="30" value="${hrRecruitactivitity.activityAddress }" /></td>
										</tr>
										<tr>
											<td align="right">活动开始时间：</td>
											<td><input id="activitystartDate" name="activitystartDate" value="<fmt:formatDate value='${hrRecruitactivitity.activitystartDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('activitystartDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">活动结束时间：</td>
											<td><input id="activityendDate" name="activityendDate" value="<fmt:formatDate value='${hrRecruitactivitity.activityendDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('activityendDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">招聘对象：</td>
											<td><input name="recruitingObject" id="recruitingObject" type="text" size="30" value="${hrRecruitactivitity.recruitingObject }" /></td>
										</tr>
										<tr>
											<td align="right">注意事项：</td>
											<td colspan="2"><textarea id="mattersNeedAttention" name="mattersNeedAttention" class="example" rows="2" style="width: 250px">${hrRecruitactivitity.mattersNeedAttention }</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(1,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />招聘活动</a></li>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
						function saveDeliveryAddress(url){
							$.ajax({
								  url:url,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										    title:'招聘活动明细 ',
										 	modal:true,
											width : 900,
											height :500,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#daForm').validationEngine('validate')){
														$.post('${vix}/hr/reActAction!saveOrUpdateHrRecruitactivitityDetails.action',
																{'id':$("#id").val(),
																  'details.id':$("#daId").val(),
																  'details.orgDepartment':$("#orgDepartment").val(),
																  'details.job':$("#job").val(),
																  'details.publicationType':$("#publicationType").val(),
																  'details.publicationStruts':$("#publicationStruts").val(),
																  'details.lifeSpan':$("#lifeSpan").val(),
																  'details.workingPlace':$("#workingPlace").val(),
																  'details.recruitmentInTheSeriesNumber':$("#recruitmentInTheSeriesNumber").val(),
																  'details.recruitmentOfTheUnofficialNumber':$("#recruitmentOfTheUnofficialNumber").val(),
																  'details.recruitmentRequirements':$("#recruitmentRequirements").val(),
																  'details.operatingDuty':$("#operatingDuty").val()
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
							url: '${vix}/hr/reActAction!getHrRecruitactivitityDetailsJson.action?id=${hrRecruitactivitity.id}',
							title: '招聘活动明细',
							width: 'auto',
							height: '450',
							fitColumns: true,
							columns:[[
								{field:'id',title:'编号',width:100,align:'center'},
								{field:'orgDepartment',title:'招聘组织部门',width:200,align:'center'},
								{field:'job',title:'招聘职位',width:200,align:'center'},
								{field:'publicationType',title:'发布类型',width:200,align:'center'},
								{field:'publicationStruts',title:'发布状态',width:200,align:'center'},
								{field:'lifeSpan',title:'有效期限',width:200,align:'center' ,formatter:formatDatebox},
								{field:'workingPlace',title:'工作地点',width:200,align:'center'},
								{field:'recruitmentInTheSeriesNumber',title:'招聘在编人数',width:200,align:'center'},
								{field:'recruitmentOfTheUnofficialNumber',title:'招聘非在编人数',width:200,align:'center'},
								{field:'recruitmentRequirements',title:'招聘要求',width:200,align:'center'},
								{field:'operatingDuty',title:'工作职责',width:200,align:'center'},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									saveDeliveryAddress('${vix}/hr/reActAction!goAddHrRecruitactivitityDetails.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#dlAddress2').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										saveDeliveryAddress('${vix}/hr/reActAction!goAddHrRecruitactivitityDetails.action?id='+row.id)
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
										$.ajax({url:'${vix}/hr/reActAction!deleteHrRecruitactivitityDetailsById.action?id='+rows[i].id,cache: false});
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