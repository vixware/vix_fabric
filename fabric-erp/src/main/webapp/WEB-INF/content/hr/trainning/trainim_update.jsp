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
	
	/** 选择培训计划 */
	function choosePurchasePlan (){
		$.ajax ({
				url : '${vix}/hr/trainImpAction!goChoosePlanning.action' ,
				cache : false ,
				success : function (html){
					asyncbox
						.open ({
						modal : true ,
						width : 900 ,
						height : 550 ,
						title : "选择培训计划" ,
						html : html ,
						callback : function (action , returnValue){
							if (action == 'ok'){
								if (returnValue != ''){
									$.ajax ({
											url : '${vix}/hr/trainImpAction!converterPlanToOrder.action?trainingPlanid='+returnValue+"&id=${trainningImplement.id}",
											cache : false ,
											success : function (result){
												showMessage (result);
												setTimeout ("" , 1000);
												$ ('#dlAddress2').datagrid ("reload");
											}
											});
								}else{
									asyncbox.success ("请选择计划!" , "<s:text name='培训计划'/>");
									return false;
								}
							}
						} ,
						btnsbar : $.btn.OKCANCEL
						});
				}
		   });
	}
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
									$("#leadings").val(selectNames);
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
		  url:'${vix}/hr/trainImpAction!goChooseCategory.action',
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
									$("#orgUnit").val(result[1]);
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

/** 保存培训活动 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	if($('#reactForm').validationEngine('validate')){
		$.post('${vix}/hr/trainImpAction!saveOrUpdate.action',
			{
			'trainningImplement.id':$("#id").val(),
			'trainningImplement.trainingProjectNumber':$("#trainingProjectNumber").val(),
			'trainningImplement.trainingName':$("#trainingName").val(),
			'trainningImplement.trainingLevel':$("#trainingLevel").val(),
			'trainningImplement.orgUnit':$("#orgUnit").val(),
			'trainningImplement.planType':$("#planType").val(),
			'trainningImplement.trainingWay':$("#trainingWay").val(),
			'trainningImplement.needing':$("#needing").val(),
			'trainningImplement.trainingObject':$("#trainingObject").val(),
			'trainningImplement.trainingPeopleNumber':$("#trainingPeopleNumber").val(),
			'trainningImplement.expensesBudget':$("#expensesBudget").val(),
			'trainningImplement.leadings':$("#leadings").val(),
			'trainningImplement.contactInformation':$("#contactInformation").val(),
			'trainningImplement.org':$("#org").val(),
			'trainningImplement.trainingInstitutions':$("#trainingInstitutions").val(),
			'trainningImplement.trainingStartDate':$("#trainingStartDate").val(),
			'trainningImplement.trainingEndDate':$("#trainingEndDate").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainImpAction!goList.action?menuId=menuOrder');
			}
		 );
	}
}
/** 保存并新增培训活动 */
function saveOrAdd(){
	var orderItemStr = "";
	if($('#reactForm').validationEngine('validate')){
		$.post('${vix}/hr/trainImpAction!saveOrUpdate.action',
			{
			'trainningImplement.id':$("#id").val(),
			'trainningImplement.trainingProjectNumber':$("#trainingProjectNumber").val(),
			'trainningImplement.trainingName':$("#trainingName").val(),
			'trainningImplement.trainingLevel':$("#trainingLevel").val(),
			'trainningImplement.orgUnit':$("#orgUnit").val(),
			'trainningImplement.planType':$("#planType").val(),
			'trainningImplement.trainingWay':$("#trainingWay").val(),
			'trainningImplement.needing':$("#needing").val(),
			'trainningImplement.trainingObject':$("#trainingObject").val(),
			'trainningImplement.trainingPeopleNumber':$("#trainingPeopleNumber").val(),
			'trainningImplement.expensesBudget':$("#expensesBudget").val(),
			'trainningImplement.leadings':$("#leadings").val(),
			'trainningImplement.contactInformation':$("#contactInformation").val(),
			'trainningImplement.org':$("#org").val(),
			'trainningImplement.trainingInstitutions':$("#trainingInstitutions").val(),
			'trainningImplement.trainingStartDate':$("#trainingStartDate").val(),
			'trainningImplement.trainingEndDate':$("#trainingEndDate").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainImpAction!goList.action?menuId=menuOrder');
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
				<li><a href="#" onclick="loadContent('${vix}/hr/trainImpAction!goList.action');">教育培训</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainImpAction!goList.action');">培训活动</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainImpAction!goList.action');">新增培训活动</a></li>
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
						onclick="loadContent('${vix}/hr/trainImpAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增培训活动" /> </b><i></i> </strong>
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
											<td align="right">选择计划：</td>
											<td colspan="3"><font color="red">选择计划</font> <input class="btn" type="button" value="选择" onclick="choosePurchasePlan();" /></td>
										</tr>
										<tr>
											<%--检查ID，判断修改--%>
											<input type="hidden" id="id" name="id" value="${trainningImplement.id}" />
											<td align="right">培训项目编号：</td>
											<td><input name="trainingProjectNumber" id="trainingProjectNumber" type="text" size="30" value="${trainningImplement.trainingProjectNumber }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">培训名称：</td>
											<td><input name="trainingName" id="trainingName" type="text" size="30" value="${trainningImplement.trainingName }" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">培训级别：</td>
											<td><input name="trainingLevel" id="trainingLevel" type="text" size="30" value="${trainningImplement.trainingLevel }" /></td>
											<td align="right">培训部门：</td>
											<td><input id="orgUnit" name="orgUnit" value="${trainningImplement.orgUnit}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id}" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(0);" /> <span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">培训类别：</td>
											<td><input name="planType" id="planType" type="text" size="30" value="${trainningImplement.planType }" /></td>
											<td align="right">培训方式：</td>
											<td><input name="trainingWay" id="trainingWay" type="text" size="30" value="${trainningImplement.trainingWay }" /></td>
										</tr>
										<tr>
											<td align="right">培训机构：</td>
											<td><input name="trainingInstitutions" id="trainingInstitutions" type="text" size="30" value="${trainningImplement.trainingInstitutions }" /></td>
											<td align="right">培训对象：</td>
											<td><input name="trainingObject" id="trainingObject" type="text" size="30" value="${trainningImplement.trainingObject }" /></td>
										</tr>
										<tr>
											<td align="right">培训人数：</td>
											<td><input name="trainingPeopleNumber" id="trainingPeopleNumber" type="text" size="30" value="${trainningImplement.trainingPeopleNumber }" /></td>
											<td align="right">培训费用预算：</td>
											<td><input name="expensesBudget" id="expensesBudget" type="text" size="30" value="${trainningImplement.expensesBudget }" /></td>
										</tr>
										<tr>
											<td align="right">负责人：</td>
											<td><input id="leadings" name="parentId" value="${trainningImplement.leadings}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> <span
												style="color: red;">*</span></td>
											<td align="right">联系方式：</td>
											<td><input name="contactInformation" id="contactInformation" type="text" size="30" value="${trainningImplement.contactInformation }" /></td>
										</tr>
										<tr>
											<td align="right">主办单位：</td>
											<td><input name="org" id="org" type="text" size="30" value="${trainningImplement.org }" /></td>
											<td align="right">注意事项：</td>
											<td colspan="2"><textarea id="needing" name="needing" class="example" rows="2" style="width: 250px">${trainningImplement.needing }</textarea></td>
										</tr>
										<tr>
											<td align="right">培训开始时间：</td>
											<td><input id="trainingStartDate" name="trainingStartDate" value="<fmt:formatDate value='${trainningImplement.trainingStartDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('trainingStartDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">培训结束时间：</td>
											<td><input id="trainingEndDate" name="trainingEndDate" value="<fmt:formatDate value='${trainningImplement.trainingEndDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('trainingEndDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />培训活动</a></li>
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
											  	title:'培训活动明细 ',
											 	modal:true,
												width : 700,
												height :450,
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														if($('#daForm').validationEngine('validate')){
															$.post('${vix}/hr/trainImpAction!saveOrUpdateTrainningImplementDetail.action',
																	{'id':$("#id").val(),
																	  'detail.id':$("#daId").val(),
																	  'detail.trainingCourse':$("#trainingCourse").val(),
																	  'detail.goalOfTraining':$("#goalOfTraining").val(),
																	  'detail.trainingSite':$("#trainingSite").val(),
																	  'detail.trainingContent':$("#trainingContent").val(),
																	  'detail.curriculumClassHours':$("#curriculumClassHours").val(),
																	  'detail.planStartDate':$("#planStartDate").val(),
																	  'detail.planEndDate':$("#planEndDate").val(),
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
								url: '${vix}/hr/trainImpAction!getTrainningImplementDetailJson.action?id=${trainningImplement.id}',
								title: '培训活动明细',
								width: 'auto',
								height: '450',
								fitColumns: true,
								columns:[[
									{field:'id',title:'编号',width:100,align:'center'},
									{field:'trainingCourse',title:'培训课程名称',width:200,align:'center'},
									{field:'goalOfTraining',title:'授课教师',width:200,align:'center'},
									{field:'trainingSite',title:'授课地点',width:200,align:'center'},
									{field:'trainingContent',title:'课程内容',width:200,align:'center'},
									{field:'curriculumClassHours',title:'课程学时',width:200,align:'center'},
									{field:'planStartDate',title:'授课开始日期',width:200,align:'center' ,formatter:formatDatebox},
									{field:'planEndDate',title:'授课结束日期',width:200,align:'center' ,formatter:formatDatebox},
								]],
								toolbar:[{
									id:'da2Btnadd',
									text:'添加',
									iconCls:'icon-add',
									handler:function(){
										$('#btnsave').linkbutton('enable');
										saveDeliveryAddress('${vix}/hr/trainImpAction!goAddTrainningImplementDetail.action?id=0');
									}
								},'-',{
									id:'btnedit',
									text:'修改',
									iconCls: 'icon-edit',
									handler: function(){
										var row = $('#dlAddress2').datagrid("getSelected");	//获取你选择的所有行
										if(row){
											saveDeliveryAddress('${vix}/hr/trainImpAction!goAddTrainningImplementDetail.action?id='+row.id)
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
											$.ajax({url:'${vix}/hr/trainImpAction!deleteTrainningImplementDetailById.action?id='+rows[i].id,cache: false});
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
	</form>
</div>