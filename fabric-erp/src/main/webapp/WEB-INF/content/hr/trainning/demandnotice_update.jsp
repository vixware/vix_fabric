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
/** 保存需求通知 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	$.post('${vix}/hr/demandNoticeAction!saveOrUpdate.action',
		{
			'demandNotice.id':$("#id").val(),	
			'demandNotice.noticeTheName':$("#noticeTheName").val(),	
			'demandNotice.companyOrDepartment':$("#companyOrDepartment").val(),	
			'demandNotice.noticeContent':editor.html(),
			'demandNotice.pubIds' : $("#pubIds").val()+',',
			'demandNotice.pubType':$('input:radio[name=pubType]:checked').val(),
			'demandNotice.pubIdss' : $("#pubIdss").val()+',',
			'demandNotice.pubTypes':$('input:radio[name=pubTypes]:checked').val(),
			'demandNotice.departmentOrPerson':$("#departmentOrPerson").val(),	
			'demandNotice.noticeStartTime':$("#noticeStartTime").val(),	
			'demandNotice.noticeEndTime':$("#noticeEndTime").val(),	
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/demandNoticeAction!goList.action?menuId=menuOrder');
		}
	 );
}
/** 保存并新增需求通知 */
function saveOrAdd(){
	var orderItemStr = "";
	$.post('${vix}/hr/demandNoticeAction!saveOrUpdate.action',
		{
			'demandNotice.id':$("#id").val(),	
			'demandNotice.noticeTheName':$("#noticeTheName").val(),	
			'demandNotice.companyOrDepartment':$("#companyOrDepartment").val(),	
			'demandNotice.noticeContent':editor.html(),
			'demandNotice.pubIds' : $("#pubIds").val()+',',
			'demandNotice.pubType':$('input:radio[name=pubType]:checked').val(),
			'demandNotice.pubIdss' : $("#pubIdss").val()+',',
			'demandNotice.pubTypes':$('input:radio[name=pubTypes]:checked').val(),
			'demandNotice.departmentOrPerson':$("#departmentOrPerson").val(),	
			'demandNotice.noticeStartTime':$("#noticeStartTime").val(),	
			'demandNotice.noticeEndTime':$("#noticeEndTime").val(),	
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/demandNoticeAction!goList.action');
		}
	 );
}

//页面首次加载
$(function(){
	//默认选择部门
    if($('input:radio[name=pubType]:checked').length==0){
    	$('input:radio[name=pubType]:first').trigger('click');
    }
	//默认选择部门
    if($('input:radio[name=pubTypes]:checked').length==0){
    	$('input:radio[name=pubTypes]:first').trigger('click');
    }
});

/**
 * 变更选择发布类型
 */
function changePubType(pubTypeValue){
	clearPubType();
}

/**
 * 清空选择对象
 */
function clearPubType(){
	$("#pubIds").val("");
	$("#departmentOrPerson").val("");
}

/**
 * 添加发布对象
 */
function addBulletinPubobject(){
	
	var pubTypeVal = $("input[name='pubType']:checked").val();
	//debugger;
	if(pubTypeVal=="O"){
		chooseBulletinOrgUnit($("#pubIds").val());
	}else if(pubTypeVal=="E"){
		chooseBulletinEmp($("#pubIds").val());
	}
}

/**
 * 选择发布部门
 */
 function chooseBulletinOrgUnit(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
			  data:{chkStyle:"checkbox",canCheckComp:0},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择发布部门",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var resObj = $.parseJSON(returnValue);
									
									for(var i=0;i<resObj.length;i++){
										selectIds += "," + resObj[i].treeId;
										selectNames += "," + resObj[i].name;
									}

									if(selectIds!=''){
										selectIds = selectIds.substring(1);
										selectNames = selectNames.substring(1);
										//alert(selectIds)
										$("#pubIds").val(selectIds);
										$("#departmentOrPerson").val(selectNames);
									}
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
 

/**
 * 选择发布人员
 */
function chooseBulletinEmp(checkObjIds){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 340,
					title:"选择发布人员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
							
								var pubIdTmp = $("#pubIds").val();
								
								pubIdTmp = pubIdTmp + ",";
								
								/* if(resObj.length == 0 ){
									return;
								} */
								//debugger;
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										if(!pubIdTmp.contains(v[0]+",")){
											selectIds += "," + v[0];
											selectNames += "," + v[1];
										}
									}
								}
								
								selectIds = $("#pubIds").val()+selectIds;
								selectNames = $("#departmentOrPerson").val()+selectNames;
								
								$("#pubIds").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#departmentOrPerson").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}


/**
 * 变更选择发布类型
 */
function changePubTypes(pubTypeValues){
	clearPubTypes();
}

/**
 * 清空选择对象
 */
function clearPubTypes(){
	$("#pubIdss").val("");
	$("#companyOrDepartment").val("");
}

/**
 * 添加通知对象
 */
function addBulletinPubobjects(){
	
	var pubTypeVals = $("input[name='pubTypes']:checked").val();
	//debugger;
	if(pubTypeVals=="O"){
		chooseBulletinOrgUnits($("#pubIdss").val());
	}else if(pubTypeVals=="E"){
		chooseBulletinEmps($("#pubIdss").val());
	}
}

/**
 * 选择通知部门
 */
 function chooseBulletinOrgUnits(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
			  data:{chkStyle:"checkbox",canCheckComp:0},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择通知部门",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var resObj = $.parseJSON(returnValue);
									
									for(var i=0;i<resObj.length;i++){
										selectIds += "," + resObj[i].treeId;
										selectNames += "," + resObj[i].name;
									}

									if(selectIds!=''){
										selectIds = selectIds.substring(1);
										selectNames = selectNames.substring(1);
										//alert(selectIds)
										$("#pubIdss").val(selectIds);
										$("#companyOrDepartment").val(selectNames);
									}
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
 

/**
 * 选择通知人员
 */
function chooseBulletinEmps(checkObjIds){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 340,
					title:"选择通知人员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
							
								var pubIdTmp = $("#pubIdss").val();
								
								pubIdTmp = pubIdTmp + ",";
								
								/* if(resObj.length == 0 ){
									return;
								} */
								//debugger;
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										if(!pubIdTmp.contains(v[0]+",")){
											selectIds += "," + v[0];
											selectNames += "," + v[1];
										}
									}
								}
								
								selectIds = $("#pubIdss").val()+selectIds;
								selectNames = $("#companyOrDepartment").val()+selectNames;
								
								$("#pubIdss").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#companyOrDepartment").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_training.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#">教育培训</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/demandNoticeAction!goList.action');">培训需求</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/demandNoticeAction!goList.action');">需求通知</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/demandNoticeAction!goList.action');">新增需求通知</a></li>
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
						onclick="loadContent('${vix}/hr/demandNoticeAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增培训需求通知" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${demandNotice.id}" />
											<td align="right">主题：</td>
											<td><input name="" id="noticeTheName" type="text" size="30" value="${demandNotice.noticeTheName}" /></td>
										</tr>
										<tr>
											<td align="right">发布部门或人员：</td>
											<td><input type="hidden" id="id" name="id" value="${demandNotice.id}" /> <s:radio id="pubType" list="#{'O':'部门','E':'人员'}" name="pubType" value="%{demandNotice.pubType}" onchange="changePubType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobject()"><img src="img/icon_25.gif" />新增</a>&nbsp;&nbsp;
												<a href="#" onclick="clearPubType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="departmentOrPerson" name="departmentOrPerson" style="width: 250px; height: 103px;">${demandNotice.departmentOrPerson}</textarea> <input type="hidden" id="pubIds" name="demandNotice.pubIds" value="${demandNotice.pubIds}" /></td>
											<td align="right">通知部门或人员：</td>
											<td><input type="hidden" id="id" name="id" value="${demandNotice.id}" /> <s:radio id="pubTypes" list="#{'O':'部门','E':'人员'}" name="pubTypes" value="%{demandNotice.pubTypes}" onchange="changePubTypes(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobjects()"><img src="img/icon_25.gif" />新增</a>&nbsp;&nbsp;
												<a href="#" onclick="clearPubTypes()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="companyOrDepartment" name="companyOrDepartment" style="width: 250px; height: 103px;">${demandNotice.companyOrDepartment}</textarea> <input type="hidden" id="pubIdss" name="demandNotice.pubIdss" value="${demandNotice.pubIdss}" />
											</td>
										</tr>
										<tr>
											<td align="right">实际开始时间：</td>
											<td><input id="noticeStartTime" name="noticeStartTime" value="<fmt:formatDate value='${demandNotice.noticeStartTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('noticeStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">实际结束时间：</td>
											<td><input id="noticeEndTime" name="noticeEndTime" value="<fmt:formatDate value='${demandNotice.noticeEndTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('noticeEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">通知内容：</th>
											<td colspan="3"><textarea id="noticeContent" name="noticeContent">${demandNotice.noticeContent}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
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