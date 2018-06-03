<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/competitors.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">基础资料管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/project/competitorProductAction!goList.action');">竞争产品管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content" style="margin-top: 5px;">
	<form id="competitorProductForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#" onclick="saveOrUpdateCompetitorProduct();"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png" /></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/project/competitorProductAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="competitor.id > 0">
								${competitor.companyName}
							</s:if> <s:else>
								新增竞争产品
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
									<s:hidden id="id" name="competitorProduct.id" value="%{competitorProduct.id}" theme="simple" />
									<s:hidden id="competitorId" name="competitorProduct.competitor.id" value="%{competitorProduct.competitor.id}" theme="simple" />
									<table class="addtable_c">
										<tr height="30">
											<td align="right" width="15%">产品名称:</td>
											<td width="35%"><input type="text" id="name" name="competitorProduct.name" value="${competitorProduct.name}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr height="30">
											<td align="right">产品规格:</td>
											<td><input type="text" id="specification" name="competitorProduct.specification" value="${competitorProduct.specification}" size="30" /></td>
											<td align="right">竞争对手:</td>
											<td colspan="3"><input type="text" id="competitorName" name="competitorProduct.competitor.name" value="${competitorProduct.competitor.companyName}" readonly="readonly" size="30" /> <span><a class="abtn" href="###" onclick="chooseCompetitor();"><span>选择</span></a></span></td>
										</tr>
										<tr height="30">
											<td align="right" width="10%">价格:</td>
											<td width="40%"><input type="text" id="price" name="competitorProduct.price" value="${competitorProduct.price}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">销售区域:</td>
											<td><input type="text" id="saleArea" name="competitorProduct.saleArea" value="${competitorProduct.saleArea}" size="30" /></td>
										</tr>
										<tr>
											<td colspan="4" align="center"><h4>
													<span>产品描述:&nbsp;</span>
												</h4></td>
										</tr>
										<tr>
											<td colspan="4" align="center"><textarea id="content" name="content">${competitorProduct.productContent}</textarea></td>
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
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
<script type="text/javascript">
	var editor = KindEditor.create('textarea[name="content"]',
					{basePath:'${vix}/plugin/KindEditor/',
						width:900,
						height:300,
						cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
						uploadJson : '${vix}/common/kindEditorAction!uploadFile.action',
						fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
						allowFileManager : true 
					}
				 );
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
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
	$("#competitorProductForm").validationEngine();
	function chooseCompetitor(){
		$.ajax({
			  url:'${vix}/crm/project/competitorAction!goChooseCompetitor.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
					 	width : 960,
						height : 500,
						title:"选择竞争对手",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var result = returnValue.split(":");
									$("#competitorId").val(result[0]);
									$("#competitorName").val(result[1]);
								}else{
									$("#competitorId").val("");
									$("#competitorName").val("");
									asyncbox.success("请选择竞争对手!","<s:text name='vix_message'/>");
								}
							}else{
								$("#competitorId").val("");
								$("#competitorName").val("");
								asyncbox.success("请选择竞争对手!","<s:text name='vix_message'/>");
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	function saveOrUpdateCompetitorProduct(){
		if($('#competitorProductForm').validationEngine('validate')){
			$.post('${vix}/crm/project/competitorProductAction!saveOrUpdate.action',
				{'competitorProduct.id':$("#id").val(),
				  'competitorProduct.name':$("#name").val(),
				  'competitorProduct.specification':$("#specification").val(),
				  'competitorProduct.price':$("#price").val(),
				  'competitorProduct.saleArea':$("#saleArea").val(),
				  'competitorProduct.competitor.id':$("#competitorId").val(),
				  'competitorProduct.competitor.companyName':$("#competitorName").val(),
				  'competitorProduct.productContent':editor.html()
				},
				function(result){
					asyncbox.success(result,"<s:text name='vix_message'/>",function(){
						loadContent('${vix}/crm/project/competitorProductAction!goList.action');
					});
				}
			);
		}
	}
</script>