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
									$("#appraiser").val(selectNames);
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
		  url:'${vix}/hr/seniorReAction!goChooseCategory.action',
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
									$("#recommendedDepartment").val(result[1]);
									}
									else if (tag==1){
										$("#applicantsDepartment").val(result[1]);
									}
									else if (tag==2){
										$("#recommendedPositionName").val(result[1]);
									}
									else if (tag==4){
										$("#appraiserPosition").val(result[1]);
									}
									else{
									$("#employmentObjective").val(result[1]);
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
$("#order").validationEngine();
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
//提示
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");

//页面首次加载
$(function(){
	
	$("#recruitmentStage").val('${hrSenior.recruitmentStage }');
	$("#conclusion").val('${hrSenior.conclusion }');
	$("#highestDegree").val('${hrSenior.highestDegree }');
});

function saveOrUpdateOrder(){
	var orderItemStr = "";
	$.post('${vix}/hr/seniorReAction!saveOrUpdate.action',
		{
			'hrSenior.id':$("#id").val(),	
			'hrSenior.applicantsName':$("#applicantsName").val(),	
			'hrSenior.employmentObjective':$("#employmentObjective").val(),	
			'hrSenior.applicantsDepartment':$("#applicantsDepartment").val(),	
			'hrSenior.highestDegree':$("#highestDegree").val(),	
			'hrSenior.recruitmentStage':$("#recruitmentStage").val(),	
			'hrSenior.recommendedPositionName':$("#recommendedPositionName").val(),	
			'hrSenior.recommendedDepartment':$("#recommendedDepartment").val(),	
			'hrSenior.appraiser':$("#appraiser").val(),	
			'hrSenior.appraiserPosition':$("#appraiserPosition").val(),	
			'hrSenior.evaluationDate':$("#evaluationDate").val(),	
			'hrSenior.comment':editor.html(),
			'hrSenior.conclusion':$("#conclusion").val(),	
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/seniorReAction!goList.action?menuId=menuOrder');
		}
	 );
}

/* 保存并新增高级人才招聘 */
function saveOrAdd(){
	var orderItemStr = "";
	$.post('${vix}/hr/seniorReAction!saveOrUpdate.action',
		{
			'hrSenior.id':$("#id").val(),	
			'hrSenior.applicantsName':$("#applicantsName").val(),	
			'hrSenior.employmentObjective':$("#employmentObjective").val(),	
			'hrSenior.applicantsDepartment':$("#applicantsDepartment").val(),	
			'hrSenior.highestDegree':$("#highestDegree").val(),	
			'hrSenior.recruitmentStage':$("#recruitmentStage").val(),	
			'hrSenior.recommendedPositionName':$("#recommendedPositionName").val(),	
			'hrSenior.recommendedDepartment':$("#recommendedDepartment").val(),	
			'hrSenior.appraiser':$("#appraiser").val(),	
			'hrSenior.appraiserPosition':$("#appraiserPosition").val(),	
			'hrSenior.evaluationDate':$("#evaluationDate").val(),	
			'hrSenior.comment':editor.html(),
			'hrSenior.conclusion':$("#conclusion").val(),	
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/seniorReAction!goSaveOrUpdate.action');
		}
	 );
}


function chooseProduct(){
	$.ajax({
		  url:'${vix}/template/productAction!goChooseProduct.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 1000,
					height : 520,
					title:"选择商品",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							$.ajax({
								url:'${vix}/template/orderAction!saveOrUpdateOrderItem.action?id='+$("#id").val() + "&productIds="+returnValue,
								cache: false,
								success: function(result){
									asyncbox.success(result,"提示信息",function(action){
										pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?id="+$("#id").val(),'orderUpdate');
									});
								}
							});
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
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
				<li><a href="#" onclick="loadContent('${vix}/hr/seniorReAction!goList.action');"><s:text name="hr_senior_recruit" /></a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/hr/seniorReAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增高级人才管理" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${hrSenior.id}" />
											<td align="right">姓名：</td>
											<td><input name="applicantsName" id="applicantsName" type="text" size="30" value="${hrSenior.applicantsName}" /></td>
											<td align="right">应聘职位：</td>
											<td><input id="employmentObjective" name="parentId" value="${hrSenior.employmentObjective}" type="text" size="30" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(3);" /></td>
										</tr>
										<tr>
											<td align="right">最高学历：</td>
											<td><select id="highestDegree" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">本科</option>
													<option value="2">硕士</option>
													<option value="3">博士</option>
											</select> <span style="color: red;">*</span></td>
											<td align="right">所在公司部门：</td>
											<td><input id="applicantsDepartment" name="parentId" value="${hrSenior.applicantsDepartment}" type="text" size="30" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(1);" /></td>
										</tr>
										<tr>
											<td align="right">推荐部门：</td>
											<td><input id="recommendedDepartment" name="parentId" value="${hrSenior.recommendedDepartment}" type="text" size="30" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(0);" /></td>
											<td align="right">推荐职位：</td>
											<td><input id="recommendedPositionName" name="parentId" value="${hrSenior.recommendedPositionName}" type="text" size="30" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(2);" /></td>
										</tr>
										<tr>
											<td align="right">评价人职位：</td>
											<td><input id="appraiserPosition" name="parentId" value="${hrSenior.appraiserPosition}" type="text" size="30" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(4);" /></td>
											<td align="right">评价人姓名：</td>
											<td><input id="appraiser" name="parentId" value="${hrSenior.appraiser}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> <span style="color: red;">*</span>
											</td>
										</tr>
										<tr>
											<td align="right">招聘阶段：</td>
											<td><select id="recruitmentStage" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">日常寻访联系阶段</option>
													<option value="2">准备入职联系阶段</option>
											</select></td>
											<td align="right">评价日期：</td>
											<td><input id="evaluationDate" name="evaluationDate" value="<fmt:formatDate value='${hrSenior.evaluationDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('evaluationDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">结论：</td>
											<td><select id="conclusion" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">通过</option>
													<option value="2">未通过</option>
													<option value="3">待议</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">评语：</td>
											<td colspan="3"><textarea id="comment" name="comment">${hrSenior.comment}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
													 var editor = KindEditor.create('textarea[name="comment"]',
														{basePath:'${vix}/plugin/KindEditor/',
															width:830,
															height:200,
															cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
															uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
															fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
															allowFileManager : true 
														}
													 );
						 						</script></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
	</form>
</div>