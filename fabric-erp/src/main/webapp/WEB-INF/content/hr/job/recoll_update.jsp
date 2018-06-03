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
									$("#issuer").val(selectNames);
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
		  url:'${vix}/hr/reCollAction!goChooseCategory.action',
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
									$("#sendObj").val(result[1]);
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
/* 保存招聘征集 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	$.post('${vix}/hr/reCollAction!saveOrUpdate.action',
		{
			'hrRecruitnoficaition.id':$("#id").val(),	
			'hrRecruitnoficaition.noticeTheName':$("#noticeTheName").val(),	
			'hrRecruitnoficaition.companyOrDepartment':$("#companyOrDepartment").val(),	
			'hrRecruitnoficaition.noticeContent':editor.html(),
			'hrRecruitnoficaition.sendObj':$("#sendObj").val(),	
			'hrRecruitnoficaition.issuer':$("#issuer").val(),	
			'hrRecruitnoficaition.noticeStartTime':$("#noticeStartTime").val(),	
			'hrRecruitnoficaition.noticeEndTime':$("#noticeEndTime").val(),	
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/reCollAction!goList.action?menuId=menuOrder');
		}
	 );
}

/* 保存并新增招聘征集 */
function saveOrAdd(){
	var orderItemStr = "";
	$.post('${vix}/hr/reCollAction!saveOrUpdate.action',
		{
			'hrRecruitnoficaition.id':$("#id").val(),	
			'hrRecruitnoficaition.noticeTheName':$("#noticeTheName").val(),	
			'hrRecruitnoficaition.companyOrDepartment':$("#companyOrDepartment").val(),	
			'hrRecruitnoficaition.noticeContent':editor.html(),
			'hrRecruitnoficaition.sendObj':$("#sendObj").val(),	
			'hrRecruitnoficaition.issuer':$("#issuer").val(),	
			'hrRecruitnoficaition.noticeStartTime':$("#noticeStartTime").val(),	
			'hrRecruitnoficaition.noticeEndTime':$("#noticeEndTime").val(),	
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/reCollAction!goSaveOrUpdate.action');
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
				<li><a href="#"><img src="img/hr_re.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/reCollAction!goList.action');"><s:text name="hr_recruitment_mgs" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/reCollAction!goList.action');"><s:text name="hr_recruitment_coll" /></a></li>
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
						onclick="loadContent('${vix}/hr/reCollAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增招聘征集" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${hrRecruitnoficaition.id}" />
											<td align="right">主题：</td>
											<td><input name="noticeTheName" id="noticeTheName" type="text" size="30" value="${hrRecruitnoficaition.noticeTheName}" /></td>
											<td align="right">通知部门：</td>
											<td><input id="companyOrDepartment" name="companyOrDepartment" value="${hrRecruitnoficaition.companyOrDepartment}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id}" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentCategory(5);" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">发送部门：</td>
											<td><input id="sendObj" name="sendObj" value="${hrRecruitnoficaition.sendObj}" type="text" size="30" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id}" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(0);" /></td>
											<%-- 不需要手动添加发布人--%>
											<%-- <td align="right">发布人：</td>
										<td>
											<input id="issuer" name="parentId" value="${hrRecruitnoficaition.issuer}" type="text" size="30" class="validate[required] text-input" /> 
											<input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> 
											<input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> 
											<span style="color: red;">*</span>
										</td> --%>
										</tr>
										<tr>
											<td align="right">实际开始时间：</td>
											<td><input id="noticeStartTime" name="noticeStartTime" value="<fmt:formatDate value='${hrRecruitnoficaition.noticeStartTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('noticeStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">实际结束时间：</td>
											<td><input id="noticeEndTime" name="noticeEndTime" value="<fmt:formatDate value='${hrRecruitnoficaition.noticeEndTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('noticeEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">通知内容：</th>
											<td colspan="3"><textarea id="noticeContent" name="noticeContent">${hrRecruitnoficaition.noticeContent}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
													var editor = KindEditor.create('textarea[name="noticeContent"]',
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