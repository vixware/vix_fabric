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
 
$("#crmGiftForm").validationEngine();
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
function saveOrUpdateCrmGift(){
	if($('#crmGiftForm').validationEngine('validate')){
		$.post('${vix}/crm/market/crmGiftAction!saveOrUpdate.action',
			{'crmGift.id':$("#id").val(),
			  'crmGift.name':$("#name").val(),
			  'crmGift.buyCount':$("#buyCount").val(),
			  'crmGift.unitPrice':$("#unitPrice").val(),
			  'crmGift.giftCompany':$("#giftCompany").val(),
			  'crmGift.contactPerson':$("#contactPerson").val(),
			  'crmGift.contactStyle':$("#contactStyle").val(),
			  'crmGift.requirement':$("#requirement").val(),
			  'crmGift.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/crm/market/crmGiftAction!goList.action');
			}
		);
	}
}

function chooseCustomerAccount(){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goChooseCustomerAccount.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#customerAccountId").val(result[0]);
								$("#customerName").val(result[1]);
								$("#customerCode").val(result[2]);
							}else{
								$("#customerAccountId").val("");
								$("#customerName").val("");
								$("#customerCode").val("");
								asyncbox.success("<s:text name='pleaseChooseCustomerAccount'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
	
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
				<li><a href="#"><img src="${vix}/common/img/crm/market.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">市场管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/market/crmGiftAction!goList.action');">礼品管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${crmGift.id}" />
<div class="content">
	<form id="crmGiftForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCrmGift();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/market/crmGiftAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="crmGift.id > 0">
							${crmGift.name}
						</s:if> <s:else>
							新增礼品
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">名称:</td>
								<td width="35%"><input id="name" name="crmGift.name" value="${crmGift.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td width="10%" align="right">单价:</td>
								<td width="40%"><input id="unitPrice" name="crmGift.unitPrice" value="${crmGift.unitPrice}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">数量:</td>
								<td><input id="buyCount" name="crmGift.buyCount" value="${crmGift.buyCount}" type="text" size="30" class="validate[required,customer[number]] text-input" /><span style="color: red;">*</span></td>
								<td align="right">礼品厂商:</td>
								<td><input id="giftCompany" name="crmGift.giftCompany" value="${crmGift.giftCompany}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">接洽人:</td>
								<td><input id="contactPerson" name="crmGift.contactPerson" value="${crmGift.contactPerson}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">联系方式:</td>
								<td><input id="contactStyle" name="crmGift.contactStyle" value="${crmGift.contactStyle}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">备注:</td>
								<td colspan="3"><input id="memo" name="crmGift.memo" value="${crmGift.memo}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
