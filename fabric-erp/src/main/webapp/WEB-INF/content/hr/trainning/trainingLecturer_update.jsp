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

/** 保存培训教师 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	$.post('${vix}/hr/trainingLecturerAction!saveOrUpdate.action',
		{
		'trainingLecturer.id':$("#id").val(),	
		'trainingLecturer.lecturerNature':$("#lecturerNature").val(),	
		'trainingLecturer.lecturerCode':$("#lecturerCode").val(),	
		'trainingLecturer.lecturerName':$("#lecturerName").val(),	
		'trainingLecturer.lecturerPosition':$("#lecturerPosition").val(),	
		'trainingLecturer.branches':$("#branches").val(),	
		'trainingLecturer.lecturerLevel':$("#lecturerLevel").val(),	
		'trainingLecturer.lecturerType':$("#lecturerType").val(),	
		'trainingLecturer.lecturerCost':$("#lecturerCost").val(),	
		'trainingLecturer.lecturerIntroduction':$("#lecturerIntroduction").val(),	
		'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/trainingLecturerAction!goList.action?menuId=menuOrder');
		}
	 );
}


/** 保存并新增培训教师 */
function saveOrAdd(){
	var orderItemStr = "";
		$.post('${vix}/hr/trainingLecturerAction!saveOrUpdate.action',
		   {
			'trainingLecturer.id':$("#id").val(),	
			'trainingLecturer.lecturerNature':$("#lecturerNature").val(),	
			'trainingLecturer.lecturerCode':$("#lecturerCode").val(),	
			'trainingLecturer.lecturerName':$("#lecturerName").val(),	
			'trainingLecturer.lecturerPosition':$("#lecturerPosition").val(),	
			'trainingLecturer.branches':$("#branches").val(),	
			'trainingLecturer.lecturerLevel':$("#lecturerLevel").val(),	
			'trainingLecturer.lecturerType':$("#lecturerType").val(),	
			'trainingLecturer.lecturerCost':$("#lecturerCost").val(),	
			'trainingLecturer.lecturerIntroduction':$("#lecturerIntroduction").val(),	
			'orderItemStr':orderItemStr
		   },
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainingLecturerAction!goSaveOrUpdate.action');
			} 
		);
}

//页面首次加载
$(function(){
	$("#lecturerNature").val('${trainingLecturer.lecturerNature }');
	$("#lecturerLevel").val('${trainingLecturer.lecturerLevel }');
	$("#lecturerType").val('${trainingLecturer.lecturerType }');
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
								$("#lecturerName").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
//弹出组织树
function chooseBulletinOrgUnit(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择填报部门",
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
										$("#parentId").val(selectIds);
										$("#branches").val(selectNames);
									}
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
				<li><a href="#" onclick="loadContent('${vix}/hr/trainingLecturerAction!goList.action');">培训讲师</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainingLecturerAction!goList.action');">新增培训讲师</a></li>
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
						onclick="loadContent('${vix}/hr/trainingLecturerAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增培训讲师" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${trainingLecturer.id}" />
											<td align="right">讲师性质：</td>
											<td><select id="lecturerNature" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">内部讲师</option>
													<option value="2">外部讲师</option>
											</select> <span style="color: red;">*</span></td>
											<td align="right">编码：</td>
											<td><input name="" id="lecturerCode" type="text" size="30" value="${trainingLecturer.lecturerCode}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">姓名：</td>
											<td><input id="lecturerName" name="parentId" value="${trainingLecturer.lecturerName}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> <span
												style="color: red;">*</span></td>
											<td align="right">职位：</td>
											<td><input name="lecturerPosition" id="lecturerPosition" type="text" size="30" value="${trainingLecturer.lecturerPosition}" /></td>
										</tr>
										<tr>
											<td align="right">所属部门：</td>
											<td><input id="branches" name="branches" value="${trainingLecturer.branches}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id}" /> <input class="btn" type="button" value="选择" onclick="chooseBulletinOrgUnit();" /> <span
												style="color: red;">*</span></td>
											<td align="right">讲师级别：</td>
											<td><select id="lecturerLevel" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">助理讲师</option>
													<option value="2">讲师</option>
													<option value="3">高级讲师</option>
											</select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">讲师类别：</td>
											<td><select id="lecturerType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">内聘讲师</option>
													<option value="2">外聘讲师</option>
											</select> <span style="color: red;">*</span></td>
											<td align="right">费用：</td>
											<td><input name="lecturerCost" id="lecturerCost" type="text" size="30" value="${trainingLecturer.lecturerCost}" /></td>
										</tr>
										<tr>
											<th align="right">简介：</th>
											<td colspan="2"><textarea id="lecturerIntroduction" name="lecturerIntroduction" class="example" rows="2" style="width: 250px">${trainingLecturer.lecturerIntroduction }</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
	</form>
</div>