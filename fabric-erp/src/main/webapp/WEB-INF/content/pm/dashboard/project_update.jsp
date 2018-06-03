<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>

<script type="text/javascript">
	$(".addtable .addtable_t").click(function() {
	    $(this).toggleClass("addtable_td");
	    $(this).next(".addtable_c").toggle();
    });
    $(".newvoucher dt b").click(function() {
	    $(this).toggleClass("downup");
	    $(this).parent("dt").next("dd").toggle();
    });
    $(".order_table input[type='text']").focusin(function() {
	    $(this).css({
	    'border' : '1px solid #f00',
	    'background' : '#f5f5f5'
	    });
    });
    $(".order_table  input[type='text']").focusout(function() {
	    $(this).css({
	    'border' : '1px solid #ccc',
	    'background' : '#fff'
	    });
    });

    //提示
    $("table tr").mouseover(function() {
	    $(this).addClass("over");
    }).mouseout(function() {
	    $(this).removeClass("over");
    });
    $("table tr:even").addClass("alt");
    $("#projectForm").validationEngine();
    /** 保存项目 */
    function saveOrUpdateProject() {
	    var orderItemStr = "";
	    $.post('${vix}/pm/addPmAction!saveOrUpdate.action', {
	    'projectManagementsss.id' : $("#id").val(),
	    'projectManagementsss.projectCode' : $("#projectCode").val(),
	    'projectManagementsss.projectName' : $("#projectName").val(),
	    'projectManagementsss.department' : $("#department").val(),
	    'projectManagementsss.projectCreDate' : $("#projectCreDate").val(),
	    'projectManagementsss.projectStartDate' : $("#projectStartDate").val(),
	    'projectManagementsss.peojectEndDate' : $("#peojectEndDate").val(),
	    'projectManagementsss.relatedProducts' : $("#relatedProducts").val(),
	    'projectManagementsss.associatedClient' : $("#associatedClient").val(),
	    'projectManagementsss.projectTypes' : $("#projectTypes").val(),
	    'projectManagementsss.owner' : $("#owner").val(),
	    'projectManagementsss.projectOverview' : editor.html(),
	    'orderItemStr' : orderItemStr
	    }, function(result) {
		    showMessage(result);
		    setTimeout("", 1000);
		    loadContent('${vix}/pm/addPmAction!goList.action?menuId=menuOrder');
	    });
	    if ($('#projectForm').validationEngine('validate')) {
		    $.post('${vix}/pm/projectAction!saveOrUpdate.action', {
		    'project.id' : $("#id").val(),
		    'project.projectCode' : $("#projectCode").val(),
		    'project.projectName' : $("#projectName").val(),
		    'project.department' : $("#department").val(),
		    'project.status' : $("#status").val(),
		    'project.creator' : $("#creator").val(),
		    'project.createTime' : $("#createTime").val(),
		    'project.startTime' : $("#startTime").val(),
		    'project.endTime' : $("#endTime").val(),
		    'project.description' : $("#description").val(),
		    'orderItemStr' : orderItemStr
		    }, function(result) {
			    showMessage(result);
			    setTimeout("", 1000);
			    loadContent('${vix}/pm/projectAction!goList.action?menuId=menuOrder');
		    });
	    }
    }

    pager("start", "${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id=" + $("#id").val(), 'orderUpdate');
    function currentPager(tag) {
	    pager(tag, "${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id=" + $("#id").val(), 'orderUpdate');
    }
    if ($(".ms").length > 0) {
	    $(".ms").hover(function() {
		    $(">ul", this).stop().slideDown(100);
	    }, function() {
		    $(">ul", this).stop().slideUp(100);
	    });
	    $(".ms ul li").hover(function() {
		    $(">a", this).addClass("hover");
		    $(">ul", this).stop().slideDown(100);
	    }, function() {
		    $(">a", this).removeClass("hover");
		    $(">ul", this).stop().slideUp(100);
	    });
    }
    //弹出组织树
    function chooseParentCategory() {
	    $.ajax({
	    url : '${vix}/hr/rePlanAction!goChooseCategory.action',
	    cache : false,
	    success : function(html) {
		    asyncbox.open({
		    modal : true,
		    width : 560,
		    height : 340,
		    title : "选择部门",
		    html : html,
		    callback : function(action,returnValue) {
			    if (action == 'ok') {
				    if (returnValue != undefined) {
					    var result = returnValue.split(",");
					    $("#department").val(result[1]);
				    } else {
					    asyncbox.success("请选择分类信息!", "提示信息");
				    }
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/pm_project.png" alt="" /> <s:text name="supplyChain" /></a></li>
				<li><a href="#"><s:text name="项目管理" /></a></li>
				<li><a href="#"><s:text name="仪表板" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/projectAction!goList.action');">新增项目</a></li>
			</ul>

		</div>
	</h2>
</div>
<div class="content">
	<form id="projectForm">
		<input type="hidden" id="id" name="id" value="${project.id}" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateProject()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#" onclick="loadContent('${vix}/pm/projectAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增项目 </b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<%--检查ID，判断修改--%>
											<input type="hidden" id="id" name="id" value="${projectManagementsss.id}" />
											<td align="right">项目编码：</td>
											<td><input name="" id="projectCode" type="text" size="30" value="${projectManagementsss.projectCode}" /> <span style="color: red;">*</span></td>
											<td align="right">项目名称：</td>
											<td><input name="" id="projectName" type="text" size="30" value="${projectManagementsss.projectName}" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">关联产品：</td>
											<td><input name="" id="relatedProducts" type="text" size="30" value="${projectManagementsss.relatedProducts}" /></td>
											<td align="right"></td>
											<td></td>
										</tr>
										<tr>
											<td align="right">项目状态：</td>
											<td><input name="" id="projectTypes" type="text" size="30" value="${projectManagementsss.projectTypes}" /></td>
											<td align="right">所有者：</td>
											<td><input name="" id="owner" type="text" size="30" value="${projectManagementsss.owner}" /></td>
										</tr>
										<tr>
											<td align="right">所属部门：</td>
											<td><input id="department" name="department" value="${projectManagementsss.department}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id}" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(5);" /> <span
												style="color: red;">*</span>
											<td align="right">创建时间：</td>
											<td><input id="projectCreDate" name="projectCreDate" value="<fmt:formatDate value='${projectManagementsss.projectCreDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('projectCreDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">实际开始时间：</td>
											<td><input id="projectStartDate" name="projectStartDate" value="<fmt:formatDate value='${projectManagementsss.projectStartDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('projectStartDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">实际结束时间：</td>
											<td><input id="peojectEndDate" name="peojectEndDate" value="<fmt:formatDate value='${projectManagementsss.peojectEndDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('peojectEndDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">项目概述：</th>
											<td colspan="3"><textarea id="projectOverview" name="projectOverview">${projectManagementsss.projectOverview}</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
													var editor = KindEditor.create('textarea[name="projectOverview"]', {
                                                    basePath : '${vix}/plugin/KindEditor/',
                                                    width : 830,
                                                    height : 200,
                                                    cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
                                                    uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
                                                    fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
                                                    allowFileManager : true
                                                    });
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