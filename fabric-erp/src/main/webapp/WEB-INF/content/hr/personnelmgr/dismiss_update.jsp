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
		  url:'${vix}/hr/probationAction!goChooseCategory.action',
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
									$("#probationDepartment").val(result[1]);
									}
									else if (tag==1){
										$("#probationPost").val(result[1]);
										$("#probationDepartment").val(result[3]);
									}
									else if (tag==2){
										
									}
									else if (tag==3){
										$("#afterThePromotionPost").val(result[1]);
										$("#afterThePromotionDepartment").val(result[3]);
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
								$("#employeeName").val(selectNames);
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
	$("#transactionState").val('${hrBecomeRegular.transactionState }');
	$("#becomeFullMemberState").val('${hrBecomeRegular.becomeFullMemberState }');
	
});

function saveOrUpdateOrder(){
	var types = "5";
	$.post('${vix}/hr/probationAction!saveOrUpdateHrBecomeRegular.action',
		{
			'hrBecomeRegular.id':$("#id").val(),	
			'hrBecomeRegular.employeeName':$("#employeeName").val(),	
			'hrBecomeRegular.jobNumber':$("#jobNumber").val(),	
			'hrBecomeRegular.transactionState':$("#transactionState").val(),	
			'hrBecomeRegular.transactionTime':$("#transactionTime").val(),	
			'hrBecomeRegular.staff':$("#staff").val(),	
			'hrBecomeRegular.subStaff':$("#subStaff").val(),	
			'hrBecomeRegular.theme':$("#theme").val(),	
			'hrBecomeRegular.probationDepartment':$("#probationDepartment").val(),	
			'hrBecomeRegular.probationPost':$("#probationPost").val(),	
			'hrBecomeRegular.afterThePromotionDepartment':$("#afterThePromotionDepartment").val(),	
			'hrBecomeRegular.afterThePromotionPost':$("#afterThePromotionPost").val(),	
			'hrBecomeRegular.becomeFullMembercause':$("#becomeFullMembercause").val(),	
			'hrBecomeRegular.becomeFullMemberState':$("#becomeFullMemberState").val(),	
			'hrBecomeRegular.trialPeriod':$("#trialPeriod").val(),	
			'hrBecomeRegular.approval':$("#approval").val(),	
			'hrBecomeRegular.approvalEvaluate':$("#approvalEvaluate").val(),	
			'hrBecomeRegular.licenseNumber':$("#licenseNumber").val(),	
			'hrBecomeRegular.licenseDate':$("#licenseDate").val(),	
			'hrBecomeRegular.litigant':$("#litigant").val(),	
			'hrBecomeRegular.gestores':$("#gestores").val(),	
			'hrBecomeRegular.infoOrgDate':$("#infoOrgDate").val(),	
			'hrBecomeRegular.employmentDate':$("#employmentDate").val(),	
			'types':types
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/probationAction!goList.action');
		}
	 );
}

function saveOrAdd(){
	var types = "5";
	$.post('${vix}/hr/probationAction!saveOrUpdateHrBecomeRegular.action',
		{
			'hrBecomeRegular.id':$("#id").val(),	
			'hrBecomeRegular.employeeName':$("#employeeName").val(),	
			'hrBecomeRegular.jobNumber':$("#jobNumber").val(),	
			'hrBecomeRegular.transactionState':$("#transactionState").val(),	
			'hrBecomeRegular.transactionTime':$("#transactionTime").val(),	
			'hrBecomeRegular.staff':$("#staff").val(),	
			'hrBecomeRegular.subStaff':$("#subStaff").val(),	
			'hrBecomeRegular.theme':$("#theme").val(),	
			'hrBecomeRegular.probationDepartment':$("#probationDepartment").val(),	
			'hrBecomeRegular.probationPost':$("#probationPost").val(),	
			'hrBecomeRegular.afterThePromotionDepartment':$("#afterThePromotionDepartment").val(),	
			'hrBecomeRegular.afterThePromotionPost':$("#afterThePromotionPost").val(),	
			'hrBecomeRegular.becomeFullMembercause':$("#becomeFullMembercause").val(),	
			'hrBecomeRegular.becomeFullMemberState':$("#becomeFullMemberState").val(),	
			'hrBecomeRegular.trialPeriod':$("#trialPeriod").val(),	
			'hrBecomeRegular.approval':$("#approval").val(),	
			'hrBecomeRegular.approvalEvaluate':$("#approvalEvaluate").val(),	
			'hrBecomeRegular.licenseNumber':$("#licenseNumber").val(),	
			'hrBecomeRegular.licenseDate':$("#licenseDate").val(),	
			'hrBecomeRegular.litigant':$("#litigant").val(),	
			'hrBecomeRegular.gestores':$("#gestores").val(),	
			'hrBecomeRegular.infoOrgDate':$("#infoOrgDate").val(),	
			'hrBecomeRegular.employmentDate':$("#employmentDate").val(),	
			'types':types
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/probationAction!goSaveOrUpdateDismiss.action');
		}
	 );
}

/* pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
function currentPager(tag){
	pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
} */
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
<%--检查ID，判断修改--%>
<input type="hidden" id="id" name="id" value="${hrBecomeRegular.id}" />
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_empma.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#"><s:text name="hr_staff_manage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goList.action');">人事事务管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/probationAction!goSaveOrUpdateDismiss.action');">辞退</a></li>
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
						onclick="loadContent('${vix}/hr/probationAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增人事事务-辞退" /> </b><i></i> </strong>
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
											<td align="right">事务主题：</td>
											<td><input name="" id="theme" type="text" size="30" value="${hrBecomeRegular.theme}" /></td>
											<td align="right">事务状态：</td>
											<td><select id="transactionState" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">通过</option>
													<option value="2">未通过</option>
													<option value="3">待议</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">姓名 ：</td>
											<td><input id="employeeName" name="parentId" value="${hrBecomeRegular.employeeName}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> <span
												style="color: red;">*</span></td>
											<td align="right">工号 ：</td>
											<td><input name="" id="jobNumber" type="text" size="30" value="${hrBecomeRegular.jobNumber}" /></td>
										</tr>
										<tr>
											<td align="right">员工组：</td>
											<td><input name="" id="staff" type="text" size="30" value="${hrBecomeRegular.staff}" /></td>
											<td align="right">员工子组：</td>
											<td><input name="" id="subStaff" type="text" size="30" value="${hrBecomeRegular.subStaff}" /></td>
										</tr>

										<tr>
											<td align="right">所属部门 ：</td>
											<td><input id="probationDepartment" name="parentId" value="${hrBecomeRegular.probationDepartment}" type="text" size="30" class="validate[required] text-input" /></td>
											<td align="right">所属职位 ：</td>
											<td><input id="probationPost" name="parentId" value="${hrBecomeRegular.probationPost}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(1);" /> <span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">审批人：</td>
											<td><input name="" id="approval" type="text" size="30" value="${hrBecomeRegular.approval}" /></td>
											<td align="right">当前状态 ：</td>
											<td><select id="becomeFullMemberState" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">已辞退</option>
													<option value="2">未辞退</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">批准文号：</td>
											<td><input name="" id="licenseNumber" type="text" size="30" value="${hrBecomeRegular.licenseNumber}" /></td>
											<td align="right">批准日期 ：</td>
											<td><input id="licenseDate" name="licenseDate" value="<fmt:formatDate value='${hrBecomeRegular.licenseDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('licenseDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">入司日期：</td>
											<td><input id="infoOrgDate" name="infoOrgDate" value="<fmt:formatDate value='${hrBecomeRegular.infoOrgDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('infoOrgDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">任职日期 ：</td>
											<td><input id="employmentDate" name="employmentDate" value="<fmt:formatDate value='${hrBecomeRegular.employmentDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('employmentDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">任职表现 ：</td>
											<td colspan="2"><textarea id="trialPeriod" name="trialPeriod" class="example" rows="2" style="width: 250px">${hrBecomeRegular.trialPeriod }</textarea></td>
										</tr>
										<tr>
											<td align="right">部门主管评价 ：</td>
											<td colspan="2"><textarea id="approvalEvaluate" name="approvalEvaluate" class="example" rows="2" style="width: 250px">${hrBecomeRegular.approvalEvaluate }</textarea></td>
										</tr>
										<tr>
											<td align="right">辞退原因 ：</td>
											<td colspan="2"><textarea id="becomeFullMembercause" name="becomeFullMembercause" class="example" rows="2" style="width: 250px">${hrBecomeRegular.becomeFullMembercause }</textarea></td>
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
							<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
					$('#soAttach').datagrid({
						url: '${vix}/hr/probationAction!getHrAttachmentsJson.action?id=${hrBecomeRegular.id}',
						title: '订单附件',
						width: 'auto',
						height: '450',
						fitColumns: true,
						columns:[[
							{field:'id',title:'编号',width:80},
							{field:'name',title:'名称',width:180},
						]],
						toolbar:[{
							id:'saBtnadd',
							text:'添加',
							iconCls:'icon-add',
							handler:function(){
								$('#btnsave').linkbutton('enable');
								$.ajax({
									  url:'${vix}/hr/probationAction!addHrAttachments.action',
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
											 	width : 364,
												height : 160,
												title:"添加明细",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														uploadFile('${vix}/hr/probationAction!uploadHrAttachments.action?id=${hrBecomeRegular.id}','fileToUpload');
														$('#soAttach').datagrid("reload");
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
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
									$.ajax({url:'${vix}/hr/probationAction!deleteHrAttachments.action?afId='+rows[i].id,cache: false});
								}
							}
						}]
					});
				</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
				</div>
				<!--oder-->
				<!--submenu-->
	</form>
</div>
<!-- content -->