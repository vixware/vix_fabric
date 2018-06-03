<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript"> 
/** 保存合同模版 */
function saveOrUpdateOrder(){
	if($('#orderForm').validationEngine('validate')){
		$.post('${vix}/contract/contractAssociateTemplateAction!saveOrUpdate.action',
			{
			'contractAssociateTemplate.id' : $("#id").val(),
			'contractAssociateTemplate.contractCode' : $("#contractCode").val(),
			'contractAssociateTemplate.templeteId' : $("#templeteId").val(),
			'contractAssociateTemplate.contractApproveCode' : $("#contractApproveCode").val(),			
			'contractAssociateTemplate.theme' : $("#theme").val(),			
			'contractAssociateTemplate.description' : $("#description").val(),			
			'contractAssociateTemplate.templateStatus' : $("#templateStatus").val()			
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/contract/contractAssociateTemplateAction!goList.action?menuId=menuContract');
			}
		);
	}
}

/** 保存并新增合同模版 */
function saveOrAdd(){
	if($('#orderForm').validationEngine('validate')){
		$.post('${vix}/contract/contractAssociateTemplateAction!saveOrUpdate.action',
			{
			'contractAssociateTemplate.id' : $("#id").val(),
			'contractAssociateTemplate.contractCode' : $("#contractCode").val(),
			'contractAssociateTemplate.templeteId' : $("#templeteId").val(),
			'contractAssociateTemplate.contractApproveCode' : $("#contractApproveCode").val(),			
			'contractAssociateTemplate.theme' : $("#theme").val(),			
			'contractAssociateTemplate.description' : $("#description").val(),			
			'contractAssociateTemplate.templateStatus' : $("#templateStatus").val()			
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/contract/contractAssociateTemplateAction!goSaveOrUpdate.action');
			}
		);
	}
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

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/ctm_contract.png" alt="" /> <s:text name="supplyChain" /></a></li>
				<li><a href="#"><s:text name="ctm_agreement" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractAssociateTemplateAction!goList.action?pageNo=${pageNo}');"><s:text name="ctm_contract_template_manager" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractAssociateTemplateAction!goList.action?pageNo=${pageNo}');"><s:text name="新增合同模板" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${contractAssociateTemplate.id}" />
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/contract/contractAssociateTemplateAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增合同模板" /> </b><i></i> </strong>
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
										<tr>
											<th align="right">合同编号：</th>
											<td><input id="contractCode" name="contractCode" value="${contractAssociateTemplate.contractCode}" type="text" size="30" class="validate[required] text-input" /></td>
											<th align="right">模板编号：</th>
											<td><input id="templeteId" name="templeteId" value="${contractAssociateTemplate.templeteId}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th align="right">审批单编号：</th>
											<td><input id="contractApproveCode" name="contractApproveCode" value="${contractAssociateTemplate.contractApproveCode}" type="text" size="30" class="validate[required] text-input" /></td>
											<th align="right">主题：</th>
											<td><input id="theme" name="theme" value="${contractAssociateTemplate.theme}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th align="right">描述：</th>
											<td colspan="3"><textarea id="description" class="description" rows="1" style="width: 880px">${contractAssociateTemplate.description}</textarea></td>
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
							<li class="current"><a onclick="javascript:tab(1,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />合同模板</a></li>
						</ul>

					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="soAttach"></table>

							<script type="text/javascript">
								$('#soAttach').datagrid({
									url: '${vix}/contract/contractAssociateTemplateAction!getContractTemplate.action?id=${contractAssociateTemplate.id}',
									title: '合同模板',
									width: 'auto',
									height: '450',
									fitColumns: true,
									columns:[[
										{field:'id',title:'序号',width:80},
										{field:'saleOrderCode',title:'主题',width:80},
										{field:'detail',title:'路径',width:80},
										{field:'deliveryTime',title:'创建时间',width:80},
										{field:'itemCode',title:'下载',width:80},
									]],
									toolbar:[{
										id:'saBtnadd',
										text:'新增',
										iconCls:'icon-add',
										handler:function(){
											$('#btnsave').linkbutton('enable');
											$.ajax({
												  url:'${vix}/contract/contractAssociateTemplateAction!addAttachFile2.action',
												  cache: false,
												  success: function(html){
													  asyncbox.open({
														 	modal:true,
															width : 364,
															height : 160,
															title:"上传附件",
															html:html,
															callback : function(action,returnValue){
																if(action == 'ok'){
																	uploadFile('${vix}/contract/contractAssociateTemplateAction!uploadAttachment.action?id=${contractAssociateTemplate.id}','fileToUpload');
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
												$.ajax({url:'${vix}/contract/contractAssociateTemplateAction!deleteAttachFile.action?afId='+rows[i].id,cache: false});
											}
										}
									}]
								});
							</script>
						</div>
					</div>
				</div>
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
	$('#a1 .right_btn,#a5 .right_btn').fix({'advance':38,'top':38});
});

	function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
	
	$(window).scroll(function(){
		if($('#orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()){
			if(!$('#orderTitleFixd').hasClass('fixed')){
				$('#orderTitleFixd').addClass('fixed');
				$('#orderTitleFixd').parent('dl').css('padding-top',30);
			}
		}else{
			$('#orderTitleFixd').removeClass('fixed');
			$('#orderTitleFixd').parent('dl').css('padding-top',0);
		}
	});

</script>