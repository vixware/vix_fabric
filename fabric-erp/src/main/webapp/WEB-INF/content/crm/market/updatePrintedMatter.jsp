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
 
$("#printedMatterForm").validationEngine();
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
function saveOrUpdatePrintedMatter(){
	if($('#printedMatterForm').validationEngine('validate')){
		$.post('${vix}/crm/market/printedMatterAction!saveOrUpdate.action',
			{'printedMatter.id':$("#id").val(),
			  'printedMatter.name':$("#name").val(),
			  'printedMatter.printedCount':$("#printedCount").val(),
			  'printedMatter.totalPrice':$("#totalPrice").val(),
			  'printedMatter.stockNumber':$("#stockNumber").val(),
			  'printedMatter.printedMatterUnit.id':$("#printedMatterUnitId").val(),
			  'printedMatter.printer':$("#printer").val(),
			  'printedMatter.contactPerson':$("#contactPerson").val(),
			  'printedMatter.contactStyle':$("#contactStyle").val(),
			  'printedMatter.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/crm/market/printedMatterAction!goList.action');
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
				<li><a href="#"><img src="${vix}/common/img/crm/market.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">市场管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/market/printedMatterAction!goList.action');">印刷品管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${printedMatter.id}" />
<div class="content">
	<form id="printedMatterForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdatePrintedMatter();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/market/printedMatterAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="printedMatter.id > 0">
							${printedMatter.name}
						</s:if> <s:else>
							新增印刷品
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td align="right">名称:</td>
								<td colspan="3"><input id="name" name="printedMatter.name" value="${printedMatter.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td width="15%" align="right">印刷量:</td>
								<td width="35%"><input id="printedCount" name="printedMatter.printedCount" value="${printedMatter.printedCount}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td width="10%" align="right">总价格:</td>
								<td width="40%"><input id="totalPrice" name="printedMatter.totalPrice" value="${printedMatter.totalPrice}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">单位:</td>
								<td><s:select id="printedMatterUnitId" headerKey="" headerValue="--请选择--" list="%{printedMatterUnitList}" listValue="name" listKey="id" value="%{printedMatter.printedMatterUnit.id}" multiple="false" theme="simple"></s:select></td>
								<td align="right">库存数量:</td>
								<td>${printedMatter.stockNumber}<input id="stockNumber" name="printedMatter.stockNumber" value="${printedMatter.stockNumber}" type="hidden" /></td>
							</tr>
							<tr>
								<td align="right">备注:</td>
								<td colspan="3"><textarea id="memo" style="width: 320px; height: 80px;" rows="5">${printedMatter.memo}</textarea></td>
							</tr>
							<tr>
								<td align="right">印刷商:</td>
								<td><input id="printer" name="printedMatter.printer" value="${printedMatter.printer}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">接洽人:</td>
								<td><input id="contactPerson" name="printedMatter.contactPerson" value="${printedMatter.contactPerson}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">联系方式:</td>
								<td colspan="3"><textarea id="contactStyle" style="width: 320px; height: 80px;" rows="5">${printedMatter.contactStyle}</textarea></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
