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
									$("#employeeName").val(selectNames);
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
		  url:'${vix}/hr/welfareArchivesAction!goChooseCategory.action',
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
									else if (tag==2){
										$("#categoryName2").val(result[1]);
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
	//覆盖地区 
	$("#employeeTypes").val('${welfareArchives.employeeTypes }');
	$("#accountTypes").val('${welfareArchives.accountTypes }');
	$("#whetherIntoAccount").val('${welfareArchives.whetherIntoAccount }');
});
<%-- 处理保存按钮功能--%>
function saveOrUpdateOrder(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/hr/welfareArchivesAction!saveOrUpdate.action',
			{
				'welfareArchives.id':$("#id").val(),
				'welfareArchives.employeeCode':$("#employeeCode").val(),
				'welfareArchives.employeeName':$("#employeeName").val(),
				'welfareArchives.accountingYear':$("#accountingYear").val(),
				'welfareArchives.accountingMonth':$("#accountingMonth").val(),
				'welfareArchives.welfareDepartment':$("#categoryName").val(),
				'welfareArchives.individualAccount':$("#individualAccount").val(),
				'welfareArchives.computerNumber':$("#computerNumber").val(),
				'welfareArchives.socialSecurityNumber':$("#socialSecurityNumber").val(),
				'welfareArchives.payBases':$("#payBases").val(),
				'welfareArchives.proportionUnitsPay':$("#proportionUnitsPay").val(),
				'welfareArchives.unitTransferRatios':$("#unitTransferRatios").val(),
				'welfareArchives.unitBeganPayingMonth':$("#unitBeganPayingMonth").val(),
				'welfareArchives.employeeTypes':$("#employeeTypes").val(),
				'welfareArchives.unitBeganPayingMonth':$("#unitBeganPayingMonth").val(),
				'welfareArchives.accountOpeningDate':$("#accountOpeningDate").val(),
				'welfareArchives.endPaymentDate':$("#endPaymentDate").val(),
				'welfareArchives.proposedTime':$("#proposedTime").val(),
				'welfareArchives.paymentMonth':$("#paymentMonth").val(),
				'welfareArchives.actualPaymentMonth':$("#actualPaymentMonth").val(),
				'welfareArchives.accountTypes':$("#accountTypes").val(),
				'welfareArchives.increase':$("#increase").val(),
				'welfareArchives.reduceCauses':$("#reduceCauses").val(),
				'welfareArchives.whetherIntoAccount':$("#whetherIntoAccount").val(),
				'welfareArchives.transferredUnit':$("#categoryName1").val(),
				'welfareArchives.transferredDate':$("#transferredDate").val(),
				'welfareArchives.sealDate':$("#sealDate").val(),
				'welfareArchives.opentingDate':$("#opentingDate").val(),
				'welfareArchives.closingDate':$("#closingDate").val(),
				'welfareArchives.outDate':$("#outDate").val(),
				'welfareArchives.outUnit':$("#categoryName2").val(),
				'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/welfareArchivesAction!goList.action?menuId=menuOrder');
			}
		 );
	}
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
				<li><a href="#">保险福利管理</a></li>
				<li><a href="#">福利业务</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/welfareArchivesAction!goList.action');">福利档案</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/welfareArchivesAction!goSaveOrUpdate.action');">新增福利档案</a></li>
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
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/hr/welfareArchivesAction!goList.action');"><img width="22" height="22" title="返回" alt="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增福利档案 </b> <i></i>
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
											<%--检查ID，判断修改--%>
											<input type="hidden" id="id" name="id" value="${welfareArchives.id}" />
											<td align="right">人员编码：</td>
											<td><input id="employeeCode" name="employeeCode" value="${welfareArchives.employeeCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">人员姓名：</td>
											<td><input id="employeeName" name="parentId" value="${welfareArchives.employeeName}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> <span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">会计年度：</td>
											<td><input name="accountingYear" id="accountingYear" type="text" size="30" value="${welfareArchives.accountingYear}" /></td>
											<td align="right">会计月份：</td>
											<td><input name="accountingMonth" id="accountingMonth" type="text" size="30" value="${welfareArchives.accountingMonth}" /></td>
										</tr>
										<tr>
											<td align="right">福利部门：</td>
											<td><input id="categoryName" name="parentId" value="${welfareArchives.welfareDepartment}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(0);" /> <span
												style="color: red;">*</span></td>
											<td align="right">职工个人账户：</td>
											<td><input name="individualAccount" id="individualAccount" type="text" size="30" value="${welfareArchives.individualAccount}" /></td>
										</tr>
										<tr>
											<td align="right">电脑序号：</td>
											<td><input name="computerNumber" id="computerNumber" type="text" size="30" value="${welfareArchives.computerNumber}" /></td>
											<td align="right">社会保障号：</td>
											<td><input name="socialSecurityNumber" id="socialSecurityNumber" type="text" size="30" value="${welfareArchives.socialSecurityNumber}" /></td>
										</tr>
										<tr>
											<td align="right">缴交基数：</td>
											<td><input name="payBases" id="payBases" type="text" size="30" value="${welfareArchives.payBases}" /></td>
											<td align="right">个人缴交比例%：</td>
											<td><input name="proportionUnitsPay" id="proportionUnitsPay" type="text" size="30" value="${welfareArchives.proportionUnitsPay}" /></td>
										</tr>
										<tr>
											<td align="right">单位缴交比例%：</td>
											<td><input name="unitTransferRatios" id="unitTransferRatios" type="text" size="30" value="${welfareArchives.unitTransferRatios}" /></td>
											<td align="right">单位划转比例%：</td>
											<td><input name="unitBeganPayingMonth" id="unitBeganPayingMonth" type="text" size="30" value="${welfareArchives.unitBeganPayingMonth}" /></td>
										</tr>
										<tr>
											<td align="right">人员类别：</td>
											<td><select id="employeeTypes" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">在职员工</option>
													<option value="2">退休职工</option>
											</select> <span style="color: red;">*</span></td>
											<td align="right">本单位开始缴费月份：</td>
											<td><input name="unitBeganPayingMonth" id="unitBeganPayingMonth" type="text" size="30" value="${welfareArchives.unitBeganPayingMonth}" /></td>
										</tr>
										<tr>
											<td align="right">开户日期：</td>
											<td><input id="accountOpeningDate" name="accountOpeningDate" value="<fmt:formatDate value='${welfareArchives.accountOpeningDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('accountOpeningDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">首次缴费日期：</td>
											<td><input id="firstPaymentDate" name="firstPaymentDate" value="<fmt:formatDate value='${welfareArchives.firstPaymentDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('firstPaymentDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">缴费截止日期：</td>
											<td><input id="endPaymentDate" name="endPaymentDate" value="<fmt:formatDate value='${welfareArchives.endPaymentDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('endPaymentDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">视同缴费月：</td>
											<td><input name="paymentMonth" id="paymentMonth" type="text" size="30" value="${welfareArchives.paymentMonth}" /></td>
										</tr>
										<tr>
											<td align="right">实际缴费月：</td>
											<td><input name="actualPaymentMonth" id="actualPaymentMonth" type="text" size="30" value="${welfareArchives.actualPaymentMonth}" /></td>
											<td align="right">账户状态：</td>
											<td><select id="accountTypes" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">已开通</option>
													<option value="2">未开通</option>
											</select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">增加原因：</td>
											<td><input name="increase" id="increase" type="text" size="30" value="${welfareArchives.increase}" /></td>
											<td align="right">减少原因：</td>
											<td><input name="reduceCauses" id="reduceCauses" type="text" size="30" value="${welfareArchives.reduceCauses}" /></td>
										</tr>
										<tr>
											<td align="right">是否转入账户：</td>
											<td><select id="whetherIntoAccount" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">是</option>
													<option value="2">否</option>
											</select> <span style="color: red;">*</span></td>
											<td align="right">转入单位：</td>
											<td><input id="categoryName1" name="parentId" value="${welfareArchives.transferredUnit}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(1);" /> <span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">转入日期：</td>
											<td><input id="transferredDate" name="transferredDate" value="<fmt:formatDate value='${welfareArchives.transferredDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('transferredDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">封存日期：</td>
											<td><input id="sealDate" name="sealDate" value="<fmt:formatDate value='${welfareArchives.sealDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('sealDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">启封日期：</td>
											<td><input id="opentingDate" name="opentingDate" value="<fmt:formatDate value='${welfareArchives.opentingDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('opentingDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">销户日期：</td>
											<td><input id="closingDate" name="closingDate" value="<fmt:formatDate value='${welfareArchives.closingDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('closingDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">转出日期：</td>
											<td><input id="outDate" name="outDate" value="<fmt:formatDate value='${welfareArchives.outDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('outDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">转出单位：</td>
											<td><input id="categoryName2" name="parentId" value="${welfareArchives.outUnit}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(2);" /> <span
												style="color: red;">*</span></td>
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