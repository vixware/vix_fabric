<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
$("#qualityAssuranceForm").validationEngine();
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
function saveOrUpdateQualityAssurance(){
	if($('#qualityAssuranceForm').validationEngine('validate')){
		$.post('${vix}/crm/service/qualityAssuranceAction!saveOrUpdate.action',
			{'qualityAssurance.id':$("#id").val(),
			  'qualityAssurance.title':$("#title").val(),
			  'qualityAssurance.answer':$("#answer").val(),
			  'qualityAssurance.innerTip':$("#innerTip").val(),
			  'qualityAssurance.status':$(":radio[name=status][checked]").val()
			},
			function(result){
				showMessage(result);
				loadContent('${vix}/crm/service/qualityAssuranceAction!goList.action');
			}
		);
	}
}
 
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/faq.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">客服管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/service/qualityAssuranceAction!goList.action');">QA库</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${qualityAssurance.id}" />
<div class="content">
	<form id="qualityAssuranceForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateQualityAssurance();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/service/qualityAssuranceAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="qualityAssurance.id > 0">
							${qualityAssurance.title}
						</s:if> <s:else>
							新增质量信息
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c">
										<tr>
											<td align="right" width="15%">标题:</td>
											<td width="35%"><input id="title" name="qualityAssurance.title" value="${qualityAssurance.title}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right" width="10%">状态:</td>
											<td width="40%"><s:if test="qualityAssurance.status == 1">
													<input type="radio" id="status1" name="status" value="1" checked="checked" />正常
												<input type="radio" id="status1" name="status" value="0" />关闭
											</s:if> <s:elseif test="qualityAssurance.status == 0">
													<input type="radio" id="status1" name="status" value="1" />正常
												<input type="radio" id="status1" name="status" value="0" checked="checked" />关闭
											</s:elseif> <s:else>
													<input type="radio" id="status1" name="status" value="1" />正常
												<input type="radio" id="status1" name="status" value="0" />关闭
											</s:else></td>
										</tr>
										<tr height="30">
											<td align="right">内部提示:</td>
											<td colspan="3"><textarea id="innerTip" rows="3" cols="60" style="font-size: 12px;">${qualityAssurance.innerTip}</textarea></td>
										</tr>
										<tr height="30">
											<td align="right">答案:</td>
											<td colspan="3"><textarea id="answer" rows="3" cols="60" style="font-size: 12px;">${qualityAssurance.answer}</textarea></td>
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
