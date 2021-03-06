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
		  url:'${vix}/hr/hirManageAction!goChooseCategory.action',
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
									$("#employmentObjective").val(result[1]);
									}
									else if (tag==1){
										$("#applicantsDepartment").val(result[1]);
									}
									else{
									$("#applicationDepartment").val(result[1]);
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
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//页面首次加载
$(function(){
	$("#intoTheCategory").val('${hrHirManage.intoTheCategory}');
	$("#sex").val('${hrHirManage.sex}');
});

/* 保存录用管理 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	$.post('${vix}/hr/hirManageAction!saveOrUpdate.action',
		{
			'hrHirManage.id':$("#id").val(),	
			'hrHirManage.candidateName':$("#candidateName").val(),	
			'hrHirManage.sex':$("#sex").val(),	
			'hrHirManage.age':$("#age").val(),	
			'hrHirManage.employmentViews':$("#employmentViews").val(),	
			'hrHirManage.resultsOfInterview':$("#resultsOfInterview").val(),	
			'hrHirManage.writtenTestResults':$("#writtenTestResults").val(),	
			'hrHirManage.employmentObjective':$("#employmentObjective").val(),	
			'hrHirManage.applicantsDepartment':$("#applicantsDepartment").val(),	
			'hrHirManage.expectedArrivalDate':$("#expectedArrivalDate").val(),	
			'hrHirManage.intoTheCategory':$("#intoTheCategory").val(),	
			'hrHirManage.remarks':$("#remarks").val(),	
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/hirManageAction!goList.action?menuId=menuOrder');
		}
	 );
}

/* 保存并新增录用管理 */
function saveOrAdd(){
	var orderItemStr = "";
	$.post('${vix}/hr/hirManageAction!saveOrUpdate.action',
		{
			'hrHirManage.id':$("#id").val(),	
			'hrHirManage.candidateName':$("#candidateName").val(),	
			'hrHirManage.sex':$("#sex").val(),	
			'hrHirManage.age':$("#age").val(),	
			'hrHirManage.employmentViews':$("#employmentViews").val(),	
			'hrHirManage.resultsOfInterview':$("#resultsOfInterview").val(),	
			'hrHirManage.writtenTestResults':$("#writtenTestResults").val(),	
			'hrHirManage.employmentObjective':$("#employmentObjective").val(),	
			'hrHirManage.applicantsDepartment':$("#applicantsDepartment").val(),	
			'hrHirManage.expectedArrivalDate':$("#expectedArrivalDate").val(),	
			'hrHirManage.intoTheCategory':$("#intoTheCategory").val(),	
			'hrHirManage.remarks':$("#remarks").val(),	
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/hirManageAction!goSaveOrUpdate.action');
		}
	 );
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#"><s:text name="hr_recruitment_mgs" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/hirManageAction!goList.action');"><s:text name="hr_selection_hired" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/hirManageAction!goList.action');"><s:text name='hr_hiring_manage' /></a></li>
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
						onclick="loadContent('${vix}/hr/hirManageAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增录用管理" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${hrHirManage.id}" />
											<td align="right">应聘人姓名：</td>
											<td><input name="" id="candidateName" type="text" size="30" value="${hrHirManage.candidateName}" /></td>
											<td align="right">性别：</td>
											<td><select id="sex" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">男</option>
													<option value="2">女</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">年龄：</td>
											<td><input name="" id="age" type="text" size="30" value="${hrHirManage.age}" /></td>
											<th align="right">预计到岗日期：</th>
											<td><input id="expectedArrivalDate" name="expectedArrivalDate" value="<fmt:formatDate value='${hrHirManage.expectedArrivalDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('expectedArrivalDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">笔试结果：</td>
											<td><input name="" id="writtenTestResults" type="text" size="30" value="${hrHirManage.writtenTestResults}" /></td>
											<td align="right">面试结果：</td>
											<td><input name="" id="resultsOfInterview" type="text" size="30" value="${hrHirManage.resultsOfInterview}" /></td>
										</tr>
										<tr>
											<td align="right">应聘职位：</td>
											<td><input id="employmentObjective" name="parentId" value="${hrHirManage.employmentObjective}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(0);" />
												<span style="color: red;">*</span></td>
											<td align="right">应聘部门：</td>
											<td><input id="applicantsDepartment" name="parentId" value="${hrHirManage.applicantsDepartment}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentCategory(1);" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">转入类别：</td>
											<td><select id="intoTheCategory" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">普通人员</option>
													<option value="2">后备人才</option>
													<option value="3">高级人才</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td><textarea id="remarks" name="remarks" class="example" rows="2" style="width: 250px">${hrHirManage.remarks }</textarea></td>
											<td align="right">录用意见：</td>
											<td><textarea id="employmentViews" name="employmentViews" class="example" rows="2" style="width: 250px">${hrHirManage.employmentViews }</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
	</form>
</div>