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
		  url:'${vix}/hr/applyregistrationAction!goChooseCategory.action',
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
//页面首次加载
$(function(){
	$("#coverageArea").val('${hrRecruitplan.coverageArea }');
	$("#planStatus").val('${hrRecruitplan.planStatus }');
});
<%-- 处理保存按钮功能--%>
function saveOrUpdateOrder(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/hr/applyregistrationAction!saveOrUpdate.action',
			{
				'hrRecruitplan.id':$("#id").val(),
				'hrRecruitplan.programName':$("#programName").val(),
				'hrRecruitplan.org':$("#categoryName").val(),
				'hrRecruitplan.expenseBudget':$("#expenseBudget").val(),
				'hrRecruitplan.responsibleForOrgUnit':$("#categoryName1").val(),
				'hrRecruitplan.leadingOfficial':$("#leadingOfficial").val(),
				'hrRecruitplan.positionName':$("#positionName").val(),
				'hrRecruitplan.jobDescription':$("#jobDescription").val(),
				'hrRecruitplan.effectTime':$("#effectTime").val(),
				'hrRecruitplan.coverageArea':$("#coverageArea").val(),
				'hrRecruitplan.planStatus':$("#planStatus").val(),
				'hrRecruitplan.schemer':$("#schemer").val(),
				'hrRecruitplan.proposedTime':$("#proposedTime").val(),
				'hrRecruitplan.projectContent':$("#projectContent").val(),
				'hrRecruitplan.planningNature':$("#planningNature").val(),
				'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/applyregistrationAction!goList.action?menuId=menuOrder');
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
				<li><a href="#"><img src="img/hr_re.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#">员工自助</a></li>
				<li><a href="#">员工自助平台</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/applyregistrationAction!goList.action');">个人应聘报名</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<form id="replanForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" title="保存" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/hr/hrEmpstaffselfAction!goSaveOrUpdate.action');"><img width="22" height="22" title="返回" alt="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>个人应聘报名 </b> <i></i>
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
											<td align="right">曾用名：</td>
											<td><input name="entityForm.oldName" type="text" size="30" value="${entity.oldName}" /></td>
											<td align="right">姓名</td>
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
											<td align="right">求职编号：</td>
											<td><input name="entityForm.staffJobNumber" type="text" size="30" value="${entity.staffJobNumber}" /></td>
											<td align="right">户籍地址：</td>
											<td><input name="entityForm.residenceAddress" type="text" size="30" value="${entity.residenceAddress}" /></td>
										</tr>
										<tr>
											<td align="right">出生地：</td>
											<td><input name="entityForm.birthplace" type="text" size="30" value="${entity.birthplace}" /></td>
											<td align="right">是否离职:&nbsp;</td>
											<td><s:radio list="#{\"1\":\"是\",\"0\":\"否\"}" name="entityForm.isDemission" value="0" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<td align="right">求职日期：</td>
											<td><input id="entityTime" name="entityForm.entityTime" value="<s:date name="%{entity.entityTime}" format="yyyy-MM-dd"/>" type="text" /> <img onclick="showTime('entityTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">联系电话：</td>
											<td><input name="entityForm.telephone" type="text" size="30" value="${entity.telephone}" /></td>
										</tr>
										<tr>
											<td align="right">个人介绍：</td>
											<td><input name="entityForm.telephone" type="text" size="30" value="${entity.telephone}" /></td>
											<td align="right">获得奖项说明：</td>
											<td><input name="entityForm.telephone" type="text" size="30" value="${entity.telephone}" /></td>
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
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->