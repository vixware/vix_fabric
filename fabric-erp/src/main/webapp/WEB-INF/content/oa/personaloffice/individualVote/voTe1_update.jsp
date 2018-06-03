<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript">
/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	var updateField = "";
	function salesOrderFieldChanged(input){
		updateField+= $(input).attr("id")+",";
	}
	

$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
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

$("#orderForm").validationEngine();
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


/**选举人员*/
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
					title:"投票",
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
								$("#electionWorkers").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

/**被推荐人*/
function chooseEmployees1(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 600,
					title:"被推荐人",
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
								$("#recommended").val(selectNames);
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/oa_personal_office.png" alt="" /> <s:text name="oa_xtbg" /> </a></li>
				<li><a href="#"><s:text name="oa_grbg" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/voTeAction!goList.action?pageNo=${pageNo}');"><s:text name="oa_vote" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/voTeAction!goList.action?pageNo=${pageNo}');"><s:text name="投票" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${votingManagement.id}" />
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdate1(0);"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="查看结果" src="${vix}/common/img/dt_submit.png" /></a> <%--   --%> <a href="#"
						onclick="loadContent('${vix}/oa/voTeAction!goList.action?pageNo=${pageNo}');"> <img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b><s:text name="投票" /></b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /> </a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<div class="source">
											<h1 align="center" style="color: #0000C6;">${votingManagement.title}</h1>
											<div class="source">
												<span>发布人：${votingManagement.uploadPersonName}</span><span>发布时间: ${votingManagement.startTime}</span><span>终止时间: ${votingManagement.endTime}</span>
											</div>
										</div>
										<div class="left">
											<span style="color: #0000C6;">发布类型： ${votingManagement.pubNames}</span>
										</div>
										<br />
										<div class="left">
											<span style="color: #0000C6;">备注： ${votingManagement.remarks}</span>
										</div>
										<br />
										<div class="left">
											<span style="color: #0000C6;">投票描述： ${votingManagement.voteDescribe}</span>
										</div>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>投票</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td><div>
													&nbsp;&nbsp;<span style="color: #0000C6;">发布类型： ${votingManagement.pubNames}</span>&nbsp;&nbsp;<span style="color: red">（只能对该发布类型进行投票!）</span>
												</div></td>
										</tr>
										<tr>
											<td><input type="hidden" id="pubIds" name="pubIds" value="${pubIds}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" /> <textarea rows="6" cols="6" id="electionWorkers" name="electionWorkers" style="width: 841px; height: 143px">${votingManagement.electionWorkers}</textarea> <input class="btn" type="button"
												value="选择" onclick="chooseEmployees();" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>其它人员</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td>&nbsp;&nbsp;<span style="color: red"> 除以上推荐人员，可推荐自认为合格的人员！</span></td>
										</tr>
										<tr>
											<td><input type="hidden" id="pubIds" name="pubIds" value="${pubIds}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" /> <textarea rows="6" cols="6" id="recommended" name="recommended" style="width: 841px; height: 143px">${votingManagement.recommended}</textarea> <input class="btn" type="button" value="选择"
												onclick="chooseEmployees1();" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
<script type="text/javascript">
$(function(){
	$.fn.fix = function(options){
		var defaults = {
			'advance' : 0,
			'top' : 0
		}
		options = $.extend(defaults, options);
		return this.each(function(){
			var $_this = $(this);
			$_this.wrap('<div></div>');
			var wp = $_this.parent('div');
			wp.height(wp.height());
			var ofst = wp.offset();
			
			if(!$_this.is(':visible') && $(window).scrollTop() + options.advance > $_this.offset().top){
				$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
			}
			$(window).scroll(function(){
				if(!$_this.is(':visible')){
					return ;
				}
				
				if($(window).scrollTop() + options.advance > wp.offset().top){
					$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
				}else{
					$_this.css({'position':'static','z-index':'auto','top': 'auto','width':'auto'});
				}
			});
		});
	}
	$('#a1 .right_btn,#a6 .right_btn').fix({'advance':38,'top':38});
});

	function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
	

</script>