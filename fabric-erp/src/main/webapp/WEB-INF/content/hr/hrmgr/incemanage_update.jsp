<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");

//页面首次加载
$(function(){
	//覆盖地区 
	$("#states").val('${inceManage.states }');
});
function saveOrUpdateOrder(){
	var orderItemStr = "";
	$.post('${vix}/hr/inceManageAction!saveOrUpdate.action',
		{
			'inceManage.id':$("#id").val(),	
			'inceManage.inceEmployeeCode':$("#inceEmployeeCode").val(),	
			'inceManage.inceEmployeeName':$("#inceEmployeeName").val(),	
			'inceManage.inceTheme':$("#inceTheme").val(),	
			'inceManage.states':$("#states").val(),	
			'inceManage.inceTime':$("#inceTime").val(),	
			'inceManage.approval':$("#approval").val(),	
			'inceManage.remarks':$("#remarks").val(),	
			'inceManage.reasons':editor.html(),
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/inceManageAction!goList.action?menuId=menuOrder');
		}
	 );
}

function saveOrAdd(){
	var orderItemStr = "";
	$.post('${vix}/hr/inceManageAction!saveOrUpdate.action',
		{
			'inceManage.id':$("#id").val(),	
			'inceManage.inceEmployeeCode':$("#inceEmployeeCode").val(),	
			'inceManage.inceEmployeeName':$("#inceEmployeeName").val(),	
			'inceManage.inceTheme':$("#inceTheme").val(),	
			'inceManage.states':$("#states").val(),	
			'inceManage.inceTime':$("#inceTime").val(),	
			'inceManage.approval':$("#approval").val(),	
			'inceManage.remarks':$("#remarks").val(),	
			'inceManage.reasons':editor.html(),
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/inceManageAction!goSaveOrUpdate.action');
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
				<li><a href="#" onclick="loadContent('${vix}/hr/inceManageAction!goList.action');">奖惩管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/inceManageAction!goList.action');">新增奖惩信息</a></li>
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
						onclick="loadContent('${vix}/hr/inceManageAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增奖惩信息" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${inceManage.id}" />
											<td align="right">员工编号：</td>
											<td><input name="inceEmployeeCode" id="inceEmployeeCode" type="text" size="30" value="${inceManage.inceEmployeeCode}" /></td>
											<td align="right">员工名称：</td>
											<td><input name="inceEmployeeName" id="inceEmployeeName" type="text" size="30" value="${inceManage.inceEmployeeName}" /></td>
										</tr>
										<tr>
											<td align="right">主题：</td>
											<td><input name="inceTheme" id="inceTheme" type="text" size="30" value="${inceManage.inceTheme}" /></td>
											<td align="right">状态：</td>
											<td><select id="states" name="states" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">已处理</option>
													<option value="2">未处理</option>
											</select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">奖惩时间：</td>
											<td><input id="inceTime" name="inceTime" value="<fmt:formatDate value='${inceManage.inceTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('inceTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">备注：</td>
											<td><input name="remarks" id="remarks" type="text" size="30" value="${inceManage.remarks}" /></td>
										</tr>
										<tr>
											<th align="right">奖惩理由：</th>
											<td colspan="3"><textarea id="reasons" name="reasons">${inceManage.reasons}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
													 var editor = KindEditor.create('textarea[name="reasons"]',
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
				<!--oder-->
				<!--submenu-->
	</form>
</div>
<!-- content -->