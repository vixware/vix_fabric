<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript"> 
<!-- 
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
/** 保存采购询价单 */
function saveOrUpdateCommissionRatio(){
	if($('#commissionRatioForm').validationEngine('validate')){
		$.post('${vix}/sales/commission/commissionRatioAction!saveOrUpdate.action',
				{
					'commissionRatio.id':$("#id").val(),
					'commissionRatio.crCode':$("#crCode").val(),
					'commissionRatio.crName':$("#crName").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/sales/commission/commissionRatioAction!goList.action');
				}
			 );
	}
}
$("#commissionRatioForm").validationEngine();
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
				<li><a href="#"><img src="img/pur_inquire.png" alt="" /> <s:text name="supplyChain" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">销售佣金管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/commission/commissionRatioAction!goList.action');">佣金比率</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${commissionRatio.id }" />
<div class="content">
	<form id="commissionRatioForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCommissionRatio();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/commission/commissionRatioAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b>新增佣金方案</b> <i></i>
					</strong>
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
											<td align="right">比率编码：</td>
											<td><input id="crCode" name="commissionRatio.crCode" value="${commissionRatio.crCode}" type="text" size="30" /></td>
											<td align="right">比率名称：</td>
											<td><input id="crName" name="commissionRatio.crName" value="${commissionRatio.crName}" type="text" size="30" /></td>
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
							<li class="current"><a><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="crItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#crItemTb',url: '${vix}/sales/commission/commissionRatioAction!getCommissionRatioItemJson.action?id=${commissionRatio.id}'">
								<thead>
									<tr>
										<th data-options="field:'crCode',align:'center',width:120,editor:'text'">佣金比率编码</th>
										<th data-options="field:'crName',width:120,align:'center',editor:'text'">佣金比率名称</th>
										<th data-options="field:'type',width:100,align:'center',editor:'text'">类型</th>
										<th data-options="field:'amountUnit',width:100,align:'center',editor:'numberbox'">数量单位</th>
										<th data-options="field:'sumUnit',width:100,align:'center',editor:'text'">金额单位</th>
										<th data-options="field:'from',width:100,align:'center',editor:'numberbox'">从</th>
										<th data-options="field:'to',width:100,align:'center',editor:'text'">到</th>
										<th data-options="field:'returnRatio',width:120,align:'center',editor:'numberbox'">返款比率</th>
										<th data-options="field:'returnAmount',width:120,align:'center',editor:'text'">返款金额</th>
									</tr>
								</thead>
							</table>
							<div id="crItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-add',plain:true" onclick="updateItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#crItem').datagrid();
								function removeItem(){
									var row = $('#crItem').datagrid('getSelected');
									if(row){
										asyncbox.confirm('是否删除该比率明细?','提示信息',function(action){
											if(action == 'ok'){
												var index = $('#crItem').datagrid('getRowIndex',row);
												$('#crItem').datagrid('deleteRow', index);
												$.ajax({
													  url:'${vix}/sales/commission/commissionRatioItemAction!deleteById.action?id='+row.id,
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
								 
								function updateItem(){
									var row = $('#crItem').datagrid('getSelected');
									if(row){
										addItem(row.id);
									}else{
										showMessage("请选择一条数据!");
									}
								}
								function addItem(id){
									$.ajax({
										  url:'${vix}/sales/commission/commissionRatioItemAction!goSaveOrUpdate.action?id='+id,
										  cache: false,
										  success: function(html){
											  asyncbox.open({
												 	modal:true,
													width : 760,
													height : 360,
													title:"比率明细",
													html:html,
													callback : function(action){
														if(action == 'ok'){
															if($('#commissionRatioItemForm').validationEngine('validate')){
																$.post('${vix}/sales/commission/commissionRatioItemAction!saveOrUpdate.action',
																		{'commissionRatioItem.id':$("#criId").val(),
																		  'commissionRatioItem.crCode':$("#crCode").val(),
																		  'commissionRatioItem.crName':$("#crName").val(),
																		  'commissionRatioItem.type':$("#type").val(),
																		  'commissionRatioItem.amountUnit':$("#amountUnit").val(),
																		  'commissionRatioItem.sumUnit':$("#sumUnit").val(),
																		  'commissionRatioItem.from':$("#from").val(),
																		  'commissionRatioItem.to':$("#to").val(),
																		  'commissionRatioItem.returnRatio':$("#returnRatio").val(),
																		  'commissionRatioItem.returnAmount':$("#returnAmount").val(),
																		  'commissionRatioItem.commissionRatio.id':$("#id").val()
																		},
																		function(result){
																			showMessage(result);
																			setTimeout("",1000);
																			$('#crItem').datagrid("reload");
																		}
																	);
															}else{
											  					return false;
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
		<!--oder-->
		<div class="sub_menu sub_menu_bot" style="display: none;">
			<div class="drop">
				<p>
					<a href="#"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/template/orderAction!goList.action');"><span>返回</span></a> <a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并新建</span></a> <a href="#"><span>关闭</span></a> <a href="#" id="text"><span>弹出窗口测试</span></a>
				</p>
				<ul>
					<li><a href="#"><span>帮助</span></a>
						<ul>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->