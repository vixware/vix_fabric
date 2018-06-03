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
 
$("#customerAccountGroupForm").validationEngine();
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
function saveOrUpdateCustomerAccountGroup(){
	if($('#customerAccountGroupForm').validationEngine('validate')){
		$.post('${vix}/mdm/crm/customerAccountGroupAction!saveOrUpdate.action',
			{'customerAccountGroup.id':$("#id").val(),
			  'customerAccountGroup.name':$("#name").val(),
			  'customerAccountGroup.code':$("#code").val(),
			  'customerAccountGroup.enable':$(":radio[name=enable][checked]").val(),
			  'customerAccountGroup.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/mdm/crm/customerAccountGroupAction!goList.action');
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
				<li><a href="#"><img src="${vix}/common/img/crm/site.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">主数据管理</a></li>
				<li><a href="#">主数据维护</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountGroupAction!goList.action');">客户组</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${customerAccountGroup.id}" />
<div class="content">
	<form id="customerAccountGroupForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCustomerAccountGroup();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/mdm/crm/customerAccountGroupAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="customerAccountGroup.id > 0">
							${customerAccountGroup.name}
						</s:if> <s:else>
							新增客户组
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">编码:</td>
								<td width="35%"><input id="code" name="customerAccountGroup.code" value="${customerAccountGroup.code}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td width="10%" align="right">名称:</td>
								<td width="40%"><input id="name" name="customerAccountGroup.name" value="${customerAccountGroup.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">备注:</td>
								<td colspan="3"><input id="memo" name="customerAccountGroup.memo" value="${customerAccountGroup.memo}" type="text" size="30" /></td>
							</tr>
						</table>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(1,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />客户</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="customerAccount" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#customerAccountTb',url: '${vix}/mdm/crm/customerAccountGroupAction!getCustomerAccountJson.action?id=${customerAccountGroup.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'code',width:80,align:'center'">编码</th>
										<th data-options="field:'shortName',width:100,align:'center'">简称</th>
										<th data-options="field:'name',width:120,align:'center'">名称</th>
									</tr>
								</thead>
							</table>
							<div id="customerAccountTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addCustomerAccount(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeCustomerAccount()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
						$('#customerAccount').datagrid();
						function removeCustomerAccount(){
							var row = $('#customerAccount').datagrid('getSelected');
							if(row){
								asyncbox.confirm('是否删除该客户?','提示信息',function(action){
									if(action == 'ok'){
										var index = $('#customerAccount').datagrid('getRowIndex',row);
										$('#customerAccount').datagrid('deleteRow', index);
										$.ajax({
											  url:'${vix}/mdm/crm/customerAccountGroupAction!deleteCustomerAccountById.action?id=' + $("#id").val() + "&customerAccountId=" + row.id,
											  cache: false,
											  success: function(html){
												  showMessage(html);
											  }
										});
									}
								});
							}else{
								showMessage("请选择一条数据!");
							}
						}
						function addCustomerAccount(id){
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
													if(returnValue != ''){
														var i = returnValue.split(":");
														$.ajax({
															  url:'${vix}/mdm/crm/customerAccountGroupAction!addCustomerAccountById.action?id=' + $("#id").val() + "&customerAccountId=" + i[0],
															  cache: false,
															  success: function(html){
																  showMessage(html);
																  $('#customerAccount').datagrid("reload");
															  }
														});
													}
												}
											},
											btnsbar : $.btn.OKCANCEL
										});
								  }
							});
						}
					</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
