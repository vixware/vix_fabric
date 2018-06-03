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

/** 保存培训课程 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	$.post('${vix}/hr/trainingCourseAction!saveOrUpdate.action',
	   {
		'trainingCourse.id':$("#id").val(),	
		'trainingCourse.courseCode':$("#courseCode").val(),	
		'trainingCourse.courseName':$("#courseName").val(),	
		'trainingCourse.courseType':$("#courseType").val(),	
		'trainingCourse.needs':$("#needs").val(),	
		'trainingCourse.courseFees':$("#courseFees").val(),	
		'trainingCourse.natureCourse':$("#natureCourse").val(),	
		'trainingCourse.effectiveStartDate':$("#effectiveStartDate").val(),	
		'trainingCourse.effectiveEndDate':$("#effectiveEndDate").val(),	
		'trainingCourse.remarkss':$("#remarkss").val(),	
		'trainingCourse.courseeducation':$("#courseeducation").val(),	
		'orderItemStr':orderItemStr
	   },
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/trainingCourseAction!goList.action?menuId=menuOrder');
		}
	 );
}


/** 保存并新增培训课程 */
function saveOrAdd(){
	var orderItemStr = "";
		$.post('${vix}/hr/trainingCourseAction!saveOrUpdate.action',
		   {
			'trainingCourse.id':$("#id").val(),	
			'trainingCourse.courseCode':$("#courseCode").val(),	
			'trainingCourse.courseName':$("#courseName").val(),	
			'trainingCourse.courseType':$("#courseType").val(),	
			'trainingCourse.needs':$("#needs").val(),	
			'trainingCourse.courseFees':$("#courseFees").val(),	
			'trainingCourse.natureCourse':$("#natureCourse").val(),	
			'trainingCourse.effectiveStartDate':$("#effectiveStartDate").val(),	
			'trainingCourse.effectiveEndDate':$("#effectiveEndDate").val(),	
			'trainingCourse.remarkss':$("#remarkss").val(),	
			'trainingCourse.courseeducation':$("#courseeducation").val(),	
			'orderItemStr':orderItemStr
		   },
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainingCourseAction!goSaveOrUpdate.action');
			} 
		);
}

//页面首次加载
$(function(){
	$("#courseType").val('${trainingCourse.courseType }');
});

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
				<li><a href="#" onclick="loadContent('${vix}/hr/trainingCourseAction!goList.action');">培训课程</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainingCourseAction!goList.action');">新增培训课程</a></li>
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
						onclick="loadContent('${vix}/hr/trainingCourseAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增培训课程" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${trainingCourse.id}" />
											<td align="right">课程编码：</td>
											<td><input name="" id="courseCode" type="text" size="30" value="${trainingCourse.courseCode}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">课程名称：</td>
											<td><input name="" id="courseName" type="text" size="30" value="${trainingCourse.courseName}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">课程类别：</td>
											<td><select id="courseType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">入职培训</option>
													<option value="2">技术课程</option>
													<option value="3">业务课程</option>
													<option value="4">管理课程</option>
											</select> <span style="color: red;">*</span></td>
											<td align="right">需要课时：</td>
											<td><input name="" id="needs" type="text" size="30" value="${trainingCourse.needs}" /></td>
										</tr>
										<tr>
											<td align="right">课程费用：</td>
											<td><input name="" id="courseFees" type="text" size="30" value="${trainingCourse.courseFees}" /></td>
											<td align="right">课程性质：</td>
											<td><input name="" id="natureCourse" type="text" size="30" value="${trainingCourse.natureCourse}" /></td>
										</tr>
										<tr>
											<td align="right">有效开始时间：</td>
											<td><input id="effectiveStartDate" name="effectiveStartDate" value="<fmt:formatDate value='${trainingCourse.effectiveStartDate }' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" readonly="readonly" type="text" /> <img onclick="showTime('effectiveStartDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
											<td align="right">有效结束时间：</td>
											<td><input id="effectiveEndDate" name="effectiveEndDate" value="<fmt:formatDate value='${trainingCourse.effectiveEndDate }' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" readonly="readonly" type="text" /> <img onclick="showTime('effectiveEndDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td><textarea id="remarkss" name="remarkss" rows="6" style="width: 250px; height: 103px;">${trainingCourse.remarkss }</textarea></td>
											<th align="right">课程内容：</th>
											<td><textarea id="courseeducation" name="courseeducation" rows="6" style="width: 250px; height: 103px;">${trainingCourse.courseeducation }</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
	</form>
</div>