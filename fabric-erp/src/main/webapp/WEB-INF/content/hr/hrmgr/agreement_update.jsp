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
		  url:'${vix}/hr/agreementAction!goChooseCategory.action',
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
									$("#companyOrDepartment").val(result[1]);
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
								$("#lesseenName").val(selectNames);
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
	
	$("#contractType").val('${personnelContract.contractType }');
	$("#contractState").val('${personnelContract.contractState }');
});
function saveOrUpdateOrder(){
	var orderItemStr = "";
	$.post('${vix}/hr/agreementAction!saveOrUpdate.action',
		{
			'personnelContract.id':$("#id").val(),	
			'personnelContract.contractCode':$("#contractCode").val(),	
			'personnelContract.contractType':$("#contractType").val(),	
			'personnelContract.contractState':$("#contractState").val(),	
			'personnelContract.party':$("#party").val(),	
			'personnelContract.attn':$("#attn").val(),	
			'personnelContract.approver':$("#approver").val(),	
			'personnelContract.signingDate':$("#signingDate").val(),	
			'personnelContract.jiaName':$("#categoryName").val(),	
			'personnelContract.jiaAddress':$("#jiaAddress").val(),	
			'personnelContract.representative':$("#representative").val(),	
			'personnelContract.jiaIndustry':$("#jiaIndustry").val(),	
			'personnelContract.lesseenName':$("#lesseenName").val(),	
			'personnelContract.lesseenAddress':$("#lesseenAddress").val(),	
			'personnelContract.IDNumber':$("#IDNumber").val(),	
			'personnelContract.contractPeriod':$("#contractPeriod").val(),	
			'personnelContract.contractPeriodType':$("#contractPeriodType").val(),	
			'personnelContract.availabilityDate':$("#availabilityDate").val(),	
			'personnelContract.dueDate':$("#dueDate").val(),	
			'personnelContract.probation':$("#probation").val(),	
			'personnelContract.record':$("#record").val(),	
			'personnelContract.renewalDate':$("#renewalDate").val(),	
			'personnelContract.enddingDate':$("#enddingDate").val(),	
			'personnelContract.contractAmount':$("#contractAmount").val(),	
			'personnelContract.orgContractAmount':$("#orgContractAmount").val(),	
			'personnelContract.remarks':editor.html(),
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/agreementAction!goList.action?menuId=menuOrder');
		}
	 );
}

function saveOrAdd(){
	var orderItemStr = "";
	$.post('${vix}/hr/agreementAction!saveOrUpdate.action',
		{
			'personnelContract.id':$("#id").val(),	
			'personnelContract.contractCode':$("#contractCode").val(),	
			'personnelContract.contractType':$("#contractType").val(),	
			'personnelContract.contractState':$("#contractState").val(),	
			'personnelContract.party':$("#party").val(),	
			'personnelContract.attn':$("#attn").val(),	
			'personnelContract.approver':$("#approver").val(),	
			'personnelContract.signingDate':$("#signingDate").val(),	
			'personnelContract.jiaName':$("#categoryName").val(),	
			'personnelContract.jiaAddress':$("#jiaAddress").val(),	
			'personnelContract.representative':$("#representative").val(),	
			'personnelContract.jiaIndustry':$("#jiaIndustry").val(),	
			'personnelContract.lesseenName':$("#lesseenName").val(),	
			'personnelContract.lesseenAddress':$("#lesseenAddress").val(),	
			'personnelContract.IDNumber':$("#IDNumber").val(),	
			'personnelContract.contractPeriod':$("#contractPeriod").val(),	
			'personnelContract.contractPeriodType':$("#contractPeriodType").val(),	
			'personnelContract.availabilityDate':$("#availabilityDate").val(),	
			'personnelContract.dueDate':$("#dueDate").val(),	
			'personnelContract.probation':$("#probation").val(),	
			'personnelContract.record':$("#record").val(),	
			'personnelContract.renewalDate':$("#renewalDate").val(),	
			'personnelContract.enddingDate':$("#enddingDate").val(),	
			'personnelContract.contractAmount':$("#contractAmount").val(),	
			'personnelContract.orgContractAmount':$("#orgContractAmount").val(),	
			'personnelContract.remarks':editor.html(),
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/agreementAction!goSaveOrUpdate.action');
		}
	 );
}

pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
function currentPager(tag){
	pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
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
				<li><a href="#"><img src="img/hr_re.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#">员工管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/agreementAction!goList.action');">合同管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/agreementAction!goSaveOrUpdate.action');">新增合同管理</a></li>
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
						onclick="loadContent('${vix}/hr/agreementAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增合同管理" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${personnelContract.id}" />
											<td align="right">合同编号：</td>
											<td><input name="" id="contractCode" type="text" size="30" value="${personnelContract.contractCode}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">合同类型：</td>
											<td><select id="contractType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">初签</option>
													<option value="2">续签</option>
											</select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">合同状态：</td>
											<td><select id="contractState" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">执行中</option>
													<option value="2">意外中止</option>
													<option value="3">结束</option>
											</select> <span style="color: red;">*</span></td>
											<%-- <td align="right">当事人：</td>
											<td><input name="" id="party" type="text" size="30" value="${personnelContract.party}" /></td> --%>
										</tr>
										<tr>
											<td align="right">合同期限：</td>
											<td><input name="" id="contractPeriod" type="text" size="30" value="${personnelContract.contractPeriod}" /></td>
											<td align="right">合同期限类别：</td>
											<td><input name="" id="contractPeriodType" type="text" size="30" value="${personnelContract.contractPeriodType}" /></td>
										</tr>
										<tr>
											<td align="right">经办人：</td>
											<td><input name="" id="attn" type="text" size="30" value="${personnelContract.attn}" /></td>
											<td align="right">批准人：</td>
											<td><input name="" id="approver" type="text" size="30" value="${personnelContract.approver}" /></td>
										</tr>
										<tr>
											<td align="right">签订日期：</td>
											<td><input id="signingDate" name="signingDate" value="<fmt:formatDate value='${personnelContract.signingDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('signingDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">解除/终止日期：</td>
											<td><input id="enddingDate" name="enddingDate" value="<fmt:formatDate value='${personnelContract.enddingDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('enddingDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a>
									</span> <strong>合同内容</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">甲方名称：</td>
											<td><input id="categoryName" name="parentId" value="${personnelContract.jiaName}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(0);" /> <span
												style="color: red;">*</span></td>
											<td align="right">续签日期：</td>
											<td><input id="renewalDate" name="renewalDate" value="<fmt:formatDate value='${personnelContract.renewalDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('renewalDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">甲方地址：</td>
											<td><input name="" id="jiaAddress" type="text" size="30" value="${personnelContract.jiaAddress}" /></td>
											<td align="right">甲方代表人：</td>
											<td><input name="" id="representative" type="text" size="30" value="${personnelContract.representative}" /></td>
										</tr>
										<tr>
											<td align="right">甲方所属行业：</td>
											<td><input name="" id="jiaIndustry" type="text" size="30" value="${personnelContract.jiaIndustry}" /></td>
											<td align="right">乙方名称：</td>
											<td><input id="lesseenName" name="parentId" value="${personnelContract.lesseenName}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> <span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">乙方住址：</td>
											<td><input name="" id="lesseenAddress" type="text" size="30" value="${personnelContract.lesseenAddress}" /></td>
											<td align="right">乙方身份证号：</td>
											<td><input name="" id="IDNumber" type="text" size="30" value="${personnelContract.IDNumber}" /></td>
										</tr>
										<tr>
											<td align="right">生效日期：</td>
											<td><input id="availabilityDate" name="availabilityDate" value="<fmt:formatDate value='${personnelContract.availabilityDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('availabilityDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">到期日期：</td>
											<td><input id="dueDate" name="dueDate" value="<fmt:formatDate value='${personnelContract.dueDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('dueDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">试用期：</td>
											<td><input name="" id="probation" type="text" size="30" value="${personnelContract.probation}" /></td>
											<td align="right">是否备案：</td>
											<td><input name="" id="record" type="text" size="30" value="${personnelContract.record}" /></td>
										</tr>
										<tr>
											<td align="right">劳动合同违约金额：</td>
											<td><input name="" id="contractAmount" type="text" size="30" value="${personnelContract.contractAmount}" /></td>
											<td align="right">单位支付补偿金额：</td>
											<td><input name="" id="orgContractAmount" type="text" size="30" value="${personnelContract.orgContractAmount}" /></td>
										</tr>
										<tr>
											<th align="right">备注：</th>
											<td colspan="3"><textarea id="remarks" name="remarks">${personnelContract.remarks}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var editor = KindEditor.create('textarea[name="remarks"]',
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
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
					$('#soAttach').datagrid({
						url: '${vix}/hr/agreementAction!getHrAttachmentsJson.action?id=${personnelContract.id}',
						title: '附件',
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
									  url:'${vix}/hr/agreementAction!addHrAttachments.action',
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
														uploadFile('${vix}/hr/agreementAction!uploadHrAttachments.action?id=${personnelContract.id}','fileToUpload');
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
									$.ajax({url:'${vix}/hr/agreementAction!deleteHrAttachments.action?afId='+rows[i].id,cache: false});
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