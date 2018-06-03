<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
<!--
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
function addOrderItem(){
	$.ajax({
		  url:'${vix}/template/simpleGridAction!saveOrUpdateCustomer.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 440,
					title:"客户信息",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							 
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function addOrderMemo(){
	$.ajax({
		  url:'${vix}/template/simpleGridAction!addOrderMemo.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 1024,
					height : 540,
					title:"客户信息",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							 
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function resize(){
	$('#test').datagrid('resize', {
		width:700,
		height:400
	});
}
function getSelected(){
	var selected = $('#test').datagrid('getSelected');
	if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	}
}
function getSelections(){
	var ids = [];
	var rows = $('#test').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].code);
	}
	alert(ids.join(':'));
}
function clearSelections(){
	$('#test').datagrid('clearSelections');
}
function selectRow(){
	$('#test').datagrid('selectRow',2);
}
function selectRecord(){
	$('#test').datagrid('selectRecord','002');
}
function unselectRow(){
	$('#test').datagrid('unselectRow',2);
}
function mergeCells(){
	$('#test').datagrid('mergeCells',{
		index:2,
		field:'addr',
		rowspan:2,
		colspan:2
	});
}
function chooseChkCustomer(){
	$.ajax({
		  url:'${vix}/template/simpleGridAction!goChooseChkSimpleGrid.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 440,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								$("#customerChk").html(returnValue);
							}else{
								$("#customerChk").html("");
								asyncbox.success("请选择分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function saveOrUpdateCustomer(){
	$.ajax({
		  url:'${vix}/template/simpleGridAction!saveOrUpdateCustomer.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 440,
					title:"客户信息",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							 
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseRadioCustomer(){
	$.ajax({
		  url:'${vix}/template/simpleGridAction!goChooseRadioSimpleGrid.action',
		  cache: false,
		  success: function(html){
			  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
			  $(".ab_c .content").css("margin-bottom","0");
			  $('.ab_c .content').parent().css('position','relative');
			  asyncbox.open({
				 	modal:true,
					width : 1160,
					height : 600,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								$("#customerRadio").html(returnValue);
							}else{
								$("#customerRadio").html("");
								asyncbox.success("请选择分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
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
function chooseProduct(){
	$.ajax({
		  url:'${vix}/template/productAction!goChooseProduct.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 1000,
					height : 520,
					title:"选择商品",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							$.ajax({
								url:'${vix}/template/orderAction!saveOrUpdateOrderItem.action?id='+$("#id").val() + "&productIds="+returnValue,
								cache: false,
								success: function(result){
									asyncbox.success(result,"提示信息",function(action){
										pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?id="+$("#id").val(),'orderUpdate');
									});
								}
							});
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
function currentPager(tag){
	pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
}
/** 保存采购订单 */
function saveOrUpdateEvaluation(){
	var orderItemStr = "";
	/** 明细 */
	var dlData = $("#dlEvaluationItem").datagrid("getRows");
	var eiJson = JSON.stringify(dlData);
	if($('#supplierEvaluationForm').validationEngine('validate')){
		$.post('${vix}/srm/supplierEvaluationAction!saveOrUpdate.action',
				{
					'supplierEvaluation.id':$("#id").val(),
					'supplierEvaluation.code':$("#code").val(),
					'supplierEvaluation.name':$("#supplierEvaluation_name").val(),
					'supplierEvaluation.qualityLevel':$("#qualityLevel").val(),
					'supplierEvaluation.qualityEvaluation':$("#qualityEvaluation").val(),
					'supplierEvaluation.priceLevel':$("#priceLevel").val(),
					'supplierEvaluation.historyPrice':$("#historyPrice").val(),
					'supplierEvaluation.capacity':$("#capacity").val(),
					'supplierEvaluation.technologicalLevel':$("#technologicalLevel").val(),
					'supplierEvaluation.returnGoods':$("#returnGoods").val(),
					'supplierEvaluation.rejectInfo':$("#rejectInfo").val(),
					'supplierEvaluation.onTimeDelivery':$("#onTimeDelivery").val(),
					'supplierEvaluation.reliability':$("#reliability").val(),
					'supplierEvaluation.serviceEvaluation':$("#serviceEvaluation").val(),
					'orderItemStr':orderItemStr,
					'eiJson':eiJson
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/srm/supplierEvaluationAction!goList.action?menuId=menuOrder');
				}
			 );
	}
}

$("#supplierEvaluationForm").validationEngine();
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
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/srm_supplier.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="srm_supp_rela_manage" /></a></li>
				<li><a href="#"><s:text name='srm_supp_manage' /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/supplierEvaluationAction!goList.action');">供应商预选与评估</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${supplierEvaluation.id }" />
<div class="content">
	<form id="supplierEvaluationForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateEvaluation();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消"
							src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a
						href="#"><img width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" alt="打印"
							src="${vix}/common/img/dt_print.png"></a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a>
					</span> <strong> <b>新增供应商评选与预估</b> <i></i>
					</strong>
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
											<td align="right">编码：</td>
											<td><input id="code" name="code" type="text" value="${supplierEvaluation.code }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">名称：</td>
											<td><input id="supplierEvaluation_name" name="name" type="text" value="${supplierEvaluation.name }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>

										<tr>
											<td align="right">质量水平：</td>
											<td><input id="qualityLevel" name="qualityLevel" type="text" value="${supplierEvaluation.qualityLevel }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">质量评价：</td>
											<td><input id="qualityEvaluation" name="qualityEvaluation" type="text" value="${supplierEvaluation.qualityEvaluation }" size="30" /></td>
										</tr>
										<tr>
											<td align="right">价格水平：</td>
											<td><input id="priceLevel" name="priceLevel" type="text" value="${supplierEvaluation.priceLevel }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">历史价格：</td>
											<td><input id="historyPrice" name="historyPrice" type="text" value="${supplierEvaluation.historyPrice }" size="30" /></td>
										</tr>
										<tr>
											<td align="right">生产能力：</td>
											<td><input id="capacity" name="capacity" type="text" value="${supplierEvaluation.capacity }" size="30" /></td>
											<td align="right">工艺水平：</td>
											<td><input id="technologicalLevel" name="technologicalLevel" type="text" value="${supplierEvaluation.technologicalLevel }" size="30" /></td>
										</tr>
										<tr>
											<td align="right">退货情况：</td>
											<td><input id="returnGoods" name="returnGoods" type="text" value="${supplierEvaluation.returnGoods }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">拒收情况：</td>
											<td><input id="rejectInfo" name="rejectInfo" type="text" value="${supplierEvaluation.rejectInfo }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">按时交货率：</td>
											<td><input id="onTimeDelivery" name="onTimeDelivery" type="text" value="${supplierEvaluation.onTimeDelivery }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">数据可靠性：</td>
											<td><input id="reliability" name="reliability" type="text" value="${supplierEvaluation.reliability }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">服务评价：</td>
											<td colspan="3"><textarea id="serviceEvaluation" name="serviceEvaluation" class="example" rows="1" style="width: 600px">${supplierEvaluation.serviceEvaluation }</textarea></td>
										</tr>
									</table>
									<!-- 
								<p>
									<i><s:text name="summary"/>123：</i><input name="" type="text" size="80"/><a href="#" onclick="addOrderMemo();">[维护常用摘要]</a>
								</p>
								<p>
									<span><i><s:text name="time"/>：</i><input id="time9" name="" type="text" /> <img onclick="showTime('time9','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></span>
								</p>
								<p>
									<span><i><s:text name="email"/>：</i><input name="" type="text" class="validate[required,custom[email]] text-input"/></span>
									<span><i><s:text name="hobby"/>：</i>
										<input type="checkbox" value="1" id="maxcheck4" name="group2" class="validate[maxCheckbox[2]] checkbox">&nbsp;读书&nbsp;&nbsp;
										<input type="checkbox" value="2" id="maxcheck5" name="group2" class="validate[maxCheckbox[2]] checkbox">&nbsp;登山&nbsp;&nbsp;
										<input type="checkbox" value="3" id="maxcheck6" name="group2" class="validate[maxCheckbox[2]] checkbox">&nbsp;游泳&nbsp;&nbsp;
									</span>
								</p>
								<p>
									<span>
										<i>性别：</i>
										<input id="radio2" name="group0" type="radio" value="1" class="validate[required] radio"/>男 
										<input id="radio3" name="group0" type="radio" value="0" class="validate[required] radio"/>女 
									</span>
								</p>
								<p>
									<span><i>商品数量：</i><input name="" type="text" class="validate[required,custom[integer]] text-input sweet-tooltip" data-text-tooltip="我是提示文字"/></span>
									<span><i>贷方金额：</i>
									<input id="money" name="" type="text"/>
									<script type="text/javascript">
									$(function(){
										$('#money').priceFormat({
										    prefix: '',
										    centsLimit: 3
										});
									});
										
									</script>
									</span>
									<span><input name="" type="button" value="下一行" class="btn" /></span>
								</p>
								<p><i>商品数量：</i><span><textarea id="textExt" class="example" rows="1" style="width:155px"></textarea></span></p>
							 -->
									<script type="text/javascript">
							$(document).ready(function(){
								$('#textExt').textext({
									plugins : 'autocomplete'
									}).bind('getSuggestions', function(e, data){
										var list = [
												'Acdsee',
												'Basic',
												'Closure',
												'Cobol',
												'Delphi',
												'Erlang',
												'Fortran',
												'Go',
												'Groovy',
												'Haskel',
												'Im',
												'Java',
												'JavaScript',
												'King',
												'Leo',
												'Menu',
												'No',
												'OCAML',
												'PHP',
												'Perl',
												'Python',
												'Query',
												'Ruby',
												'Scala',
												'Time',
												'UTC',
												'Vv',
												'What',
												'Xx',
												'Yang',
												'Zero'
											],
											textext = $(e.target).textext()[0],
											query = (data ? data.query : '') || '';
										$(this).trigger(
											'setSuggestions',{ result : textext.itemManager().filter(list, query) }
										);
									});
								});
							</script>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher" style="display: none;">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>其他信息</strong>
								</dt>

								<dd style="display: none;">
									<!-- 
								<p>
									<i>摘要：</i><input name="" type="text" size="80" /><a href="javascript:void(0);" onclick="saveOrUpdate();">[维护常用摘要]</a>
								</p>
								<p>
									<span><i>科目：</i><input name="" type="text" /></span>
								</p>
								<p>
									<span><i>币别：</i><input name="" type="text" /></span>
									<span><i>汇率：</i><input name="" type="text" /></span>
								</p>
								<p>
									<span><i>原币：</i></span>
								</p>
								<p>
									<span><i>借方金额：</i><input name="" type="text" /></span>
									<span><i>贷方金额：</i><input name="" type="text" /></span>
									<span><input name="" type="button" value="下一行" class="btn" /></span>
								</p>
								 -->

									<!-- 
								 <table>
									<tr>
										<th width="10%">上级财务组织</th>
										<td width="40%"><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" />
											<span class="formInfo">
												<a href="#" class="jTip" id="tip1" name="我是标题标题，rel是宽度" rel="375">?
													<div class="jTipCenter">
														<ul>
															<li>1) Password must be <strong>between 6 - 20 characters</strong> long</li>
															<li>2) Password must contain at least <strong>one letter</strong></li>
															<li>3) Password must contain at least <strong>one number</strong></li>
															<li>4) Password <strong>cannot</strong> contain <strong>special characters</strong> (@, %, etc)</li>
															<li>5) Password cannot be the same as the User ID</li>
															<li>6) Password validation is <strong>case sensitive</strong></li>
														</ul>
													</div>
												</a>
											</span></td>
										
								  	</tr>
								  	<tr>
										<th>上级财务组织</th>
										<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
										<th>科目表</th>
										<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
								  	</tr>
									<tr>
										<th>下拉</th>
										<td><div class="no-border"><div id="combo1" class="combo"></div></div>
											<script type="text/javascript">
													var dd = [];
													for(var i=1;i<=5; i++)
														dd.push({ code: i + '', name: 'Employee ' + i });
													var cfg = {
														keyField: 'code',
														displayField: 'name',
														multiSelect: false,
														width: 200,
														boxWidth: 200,
														cols : [{
															field: 'code', width: '28%'
														},{
															field: 'name', width: '70%'
														}],
														data: dd
													};
													var cfg1 = $.extend({}, cfg);
													var cb1 = $('#combo1').mac('combo', cfg1);
													$('#get1').click(function(){
														alert(cb1.selected);
													});
													$('#set1').click(function(){
														cb1.select(2);
													});
													var cfg2 = $.extend({}, cfg);
													cfg2.multiSelect = true;
													var cb2 = $('#combo2').mac('combo', cfg2);
												$('#get2').click(function(){
													alert(cb2.selected.join(','));
												});
												$('#set2').click(function(){
													cb2.select([2,4]);
												});
										</script>
										</td>
										<th>下拉多选</th>
										<td><div class="no-border"><div id="combo2" class="combo"></div></div></td>
									</tr>
									<tr>
										<th>tip button</th>
									  	<td><div id="StatusBar"></div>
											<input name="" id="addStatusBarMessage" type="button" value="点击我" />
											<script type="text/javascript">
											   $('#StatusBar').jnotifyInizialize({
													oneAtTime: true
												})
												$('#Notification')
													.jnotifyInizialize({
														oneAtTime: false,
														appendType: 'append'
													});
					
												$(document).ready(function() {
													$('#addStatusBarMessage').click(function() {
														$('#StatusBar').jnotifyAddMessage({
															text: 'This is a non permanent message.',
															permanent: false,
															showIcon: false
														});
													});
												});
											</script>
										</td>
										<th>客户：</th>
									  	<td>
											<span id="customerChk"> </span>
											<input class="btn" type="button" value="选择" onclick="chooseChkCustomer();">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="customerRadio"> </span>
											<input class="btn" type="button" value="选择" onclick="chooseRadioCustomer();"/> 
										</td>
									</tr>
								  <tr>
									<th>上级财务组织</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
									<th>科目表</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
								  </tr>
								  <tr>
									<th>上级财务组织</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
									<th>科目表</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
								  </tr>
								  <tr>
									<th>上级财务组织</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
									<th>科目表</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
								  </tr>
								  <tr>
									<th>时间</th>
									<td><input class="sweet-tooltip time_aoto_input" data-text-tooltip="时间 class = time_aoto_input 控制，取消则删除此class。" name="" type="text" rel="yyyy-MM-dd HH:00" /></td>
									<th>时间 yyyy-MM-dd HH:mm:ss</th>
									<td><input id="time1" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="" type="text" /> <img onclick="showTime('time1','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								  </tr>
								  <tr>
									<th>时间 yyyy-MM-dd HH:mm</th>
									<td><input id="time2" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm" name="" type="text" /> <img onclick="showTime('time2','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
									<th>时间 yyyy-MM-dd</th>
									<td><input id="time3" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="" type="text" /> <img onclick="showTime('time3','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								  </tr>
								  <tr>
									<th>索引</th>
									<td><input class="sweet-tooltip" data-text-tooltip="Tip tip tip tip" id="demo-input-facebook-theme" name="" type="text" />
										<script type="text/javascript">
											$(document).ready(function() {
												$("#demo-input-facebook-theme").tokenInput("http://shell.loopj.com/tokeninput/tvshows.php", {
													theme: "facebook"
												});
											});
											$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
												$(this).addClass("btnhover");
											},function(){
												$(this).removeClass("btnhover");
											});
										</script>
									</td>
									<th>massage</th>
									<td>
										<input name="" id="addStatusBarMessage" class="btn" type="button" onclick="showMessage('我是提示文字，showMessage')" value="消息提示" />&nbsp;&nbsp;<input name="" id="addStatusBarMessage" class="btn" type="button" onclick="showErrorMessage('错误提示文字，showErrorMessage')" value="错误提示" />
									</td>
								  </tr>
								</table>
								 -->
								</dd>
							</dl>
						</div>


						<div style="margin: 15px 70px; display: none;">
							<textarea id="content" name="content"></textarea>
							<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
							<script type="text/javascript">
					 var editor = KindEditor.create('textarea[name="content"]',
								{basePath:'${vix}/plugin/KindEditor/',
									width:1100,
									height:300,
									cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
									uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
									fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
									allowFileManager : true 
								}
					 );
					 </script>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />指标项</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlEvaluationItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',idField:'code',singleSelect: true,toolbar: '#dlEvaluationItemTb',url: '${vix}/srm/supplierEvaluationAction!getSupplierEvaluationItemJson.action?id=${supplierEvaluation.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'name',width:200,align:'center',editor:'text'">指标名称</th>
										<th data-options="field:'weight',width:200,align:'center',editor:'numberbox'">权重</th>
										<th data-options="field:'value',width:200,align:'center',editor:'numberbox'">分数</th>
										<th data-options="field:'content',width:200,align:'center',editor:'text'">内容</th>
									</tr>
								</thead>
							</table>
							<div id="dlEvaluationItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlEvaluationItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
									class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeDlEvaluationItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-save',plain:true" onclick="saveDlEvaluationItem()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlEvaluationItem').datagrid();
							var editIndexDlEvaluationItem = undefined;
							function endDlEditing(){
								if (editIndexDlEvaluationItem == undefined){return true;}
								if ($('#dlEvaluationItem').datagrid('validateRow', editIndexDlEvaluationItem)){
									$('#dlEvaluationItem').datagrid('endEdit', editIndexDlEvaluationItem);
									editIndexDlEvaluationItem = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow(index){
								if (editIndexDlEvaluationItem != index){
									if (endDlEditing()){
										$('#dlEvaluationItem').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlEvaluationItem = index;
									} else {
										$('#dlEvaluationItem').datagrid('selectRow', editIndexDlEvaluationItem);
									}
								}
							}
							function appendDlEvaluationItem(){
								if (endDlEditing()){
									$('#dlEvaluationItem').datagrid('appendRow',{status:'P'});
									editIndexDlEvaluationItem = $('#dlEvaluationItem').datagrid('getRows').length-1;
									$('#dlEvaluationItem').datagrid('selectRow', editIndexDlEvaluationItem).datagrid('beginEdit', editIndexDlEvaluationItem);
								}
							}
							function removeDlEvaluationItem(){
								if (editIndexDlEvaluationItem == undefined){return;}
								$('#dlEvaluationItem').datagrid('cancelEdit', editIndexDlEvaluationItem)
										.datagrid('deleteRow', editIndexDlEvaluationItem);
								editIndexDlEvaluationItem = undefined;
							}
							function saveDlEvaluationItem(){
								if (endDlEditing()){
									$('#dlEvaluationItem').datagrid('acceptChanges');
								}
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
					<a href="#" onclick="saveOrUpdateOrder()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/template/orderAction!goList.action');"><span>返回</span></a> <a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并新建</span></a> <a href="#"><span>关闭</span></a> <a href="#"
						id="text"><span>弹出窗口测试</span></a>
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