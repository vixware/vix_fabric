<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	//设置单据类型选中(修改)
	$("#type").val('${resourceRequestAction.type}');
	$("#status").val('${resourceRequestAction.status}');
});
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

function currentPager(tag){
	pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
}
/** 保存采购计划 */
function saveOrUpdatePlan(){
	var orderItemStr = "";
	if($('#resourceRequestForm').validationEngine('validate')){
		$.post('${vix}/oa/resourceRequestAction!saveOrUpdate.action',
				{
					'resourceRequest.id':$("#id").val(),
					'resourceRequest.department':$("#department").val(),
					'resourceRequest.code':$("#code").val(),
					'resourceRequest.name':$("#name").val(),
					'resourceRequest.type':$("#type").val(),
					'resourceRequest.address':$("#address").val(),
					'resourceRequest.amount':$("#amount").val(),
					'resourceRequest.status':$("#status").val(),
					'resourceRequest.interestedPartyPerson':$("#interestedPartyPerson").val(),
					'resourceRequest.requestDate':$("#requestDate").val(),
					'resourceRequest.repayDate':$("#repayDate").val(),
					'resourceRequest.creator':$("#creator").val(),
					'resourceRequest.introduction':$("#introduction").val(),
					'orderItemStr':orderItemStr
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/resourceRequestAction!goList.action?menuId=menuOrder');
				}
			 );
	}
}

$("#resourceRequestForm").validationEngine();
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
				<li><a href="#"><img src="img/mdm_resourceRequest.png" alt="" /> <s:text name="oa_xtbg" /></a></li>
				<li><a href="#"><s:text name="oa_administrative_civil" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/resourceRequestAction!goList.action');">资源申请管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="resourceRequestForm">
		<input type="hidden" id="id" name="id" value="${resourceRequest.id }" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdatePlan();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/oa/resourceRequestAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增资源" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div id="newtab4">
							<div class="addleft">
								<div class="addbtn">
									<p>
										<img src="${vix}/common/img/address_book.png" />资源管理
									</p>
								</div>
								<ul class="listlib">
									<li class="liston"><strong>资源分类</strong>
										<ul>
											<li><strong class="home">百科类</strong></li>
											<li><strong class="home">人文类</strong></li>
											<li><strong class="home">哲学类</strong></li>
											<li><strong class="home">艺术类</strong></li>
											<li><strong class="home">其它</strong></li>
										</ul></li>
									<li class="liston"><strong>申请者消息</strong>
										<ul>
											<li><strong class="home">姓名</strong></li>
											<li><strong class="home">证件号</strong></li>
											<li><strong class="home">借阅书名</strong></li>
										</ul></li>
									<li class="liston"><strong>管理权限</strong>
										<ul>
											<li><strong class="home">超级管理员</strong></li>
											<li><strong class="home">管理员1</strong></li>
											<li><strong class="home">管理员2</strong></li>
										</ul></li>
								</ul>
							</div>
							<div class="addright">
								<div class="addbox">
									<div class="addtitle">资源明细</div>
									<div class="addbox_content">
										<table style="border: none; height: 450px;">
											<tr>
												<td align="right">所属部门：</td>
												<td><input id="department" name="department" value="${resourceRequest.department }" type="text" size="30" /></td>
												<td align="right">资源编码：</td>
												<td><input id="code" name="code" value="${resourceRequest.code }" type="text" size="30" /></td>
											</tr>
											<tr>
												<td align="right">资源名称：</td>
												<td><input id="name" name="name" value="${resourceRequest.name }" type="text" size="30" /></td>
												<td align="right">资源类别：</td>
												<td><select id="type" name="type" style="width: 225px">
														<option value="">请选择</option>
														<option value="1">类型1</option>
														<option value="2">类型2</option>
												</select></td>
											</tr>
											<tr>
												<td align="right">存放地点：</td>
												<td><input id="address" name="address" value="${resourceRequest.address }" type="text" size="30" /></td>
												<td align="right">数量：</td>
												<td><input id="amount" name="amount" value="${resourceRequest.amount }" type="text" size="30" /></td>
											</tr>
											<tr>
												<td align="right">申请状态：</td>
												<td><select id="status" name="status" style="width: 225px">
														<option value="">请选择</option>
														<option value="S1">待批</option>
														<option value="S2">已批</option>
														<option value="S3">未批</option>
												</select></td>
												<td align="right">申请人：</td>
												<td><input id="interestedPartyPerson" name="interestedPartyPerson" value="${resourceRequest.interestedPartyPerson }" type="text" size="30" /></td>
											</tr>
											<tr>
												<td align="right">申请日期：</td>
												<td><input id="requestDate" name="requestDate" value="<fmt:formatDate value='${resourceRequest.requestDate }' type='both' pattern='yyyy-MM-dd' />" type="text" /><span style="color: red;">*</span> <img onclick="showTime('requestDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
												<td align="right">归还日期：</td>
												<td><input id="repayDate" name="repayDate" value="<fmt:formatDate value='${resourceRequest.repayDate }' type='both' pattern='yyyy-MM-dd' />" type="text" /><span style="color: red;">*</span> <img onclick="showTime('repayDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											</tr>
											<tr>
												<td align="right">业务员：</td>
												<td colspan="3"><input id="creator" name="creator" value="${resourceRequest.creator }" type="text" size="30" /></td>
											</tr>
											<tr>
												<td align="right">内容简介：</td>
												<td colspan="3"><textarea id="introduction" name="introduction" class="example" rows="2" style="width: 600px">${resourceRequest.introduction }</textarea></td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>

<!-- content -->