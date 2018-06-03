<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>




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
	$('#test').datagrid({
		title:'My DataGrid',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/json_tests/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{title:'Code',field:'code',width:155,sortable:true}
		]],
		columns:[[
			{title:'Base Information',colspan:3},
			{field:'opt',title:'Operation',width:100,align:'center', rowspan:2,
				formatter:function(value,rec){
					return '<span style="color:red">Edit Delete</span>';
				}
			}
		],[
			{field:'name',title:'Name',width:120},
			{field:'addr',title:'Address',width:220,rowspan:2,sortable:true,
				sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'col4',title:'Col41',width:150,rowspan:2}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'Add',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$.ajax({
					  url:'${vix}/template/toolAction!addOaListItem.action?tag=addOaListItem3',
					  cache: false,
					  success: function(html){
						  asyncbox.open({
								 modal:true,
								 width : 900,
								 height : 400,
								 title:"新建文章",
								 content:html,
								 btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			}
		},{
			id:'btncut',
			text:'Cut',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},'-',{
			id:'btnsave',
			text:'Save',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	var p = $('#test').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			alert('before refresh');
		}
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
function saveOrUpdateOrder(){
	$.post('${vix}/template/orderAction!saveOrUpdate.action',
		{'order.id':$("#id").val(),
		  'order.code':$("#code").val(),
		  'order.memo':$("#memo").val()
		},
		function(result){
			asyncbox.success(result,"提示信息",function(action){
				loadContent('${vix}/template/orderAction!goList.action');
			});
		}
	 );
}
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
$("#order").validationEngine();
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
		</i> <strong><img alt="" src="img/drp_channel.png"> <s:text name="supplyChain" /></strong>
	</h2>
</div>
<input type="hidden" id="id" name="id" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCustomer();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <!-- 
					<a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png"/></a>
					<a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png"/></a>
					<a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png"/></a>
					<a href="#"><img width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a>
					<a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a>
					<a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a>
					<a href="#"><img width="22" height="22" alt="打印" src="${vix}/common/img/dt_print.png"></a>
					<div class="ms" style="float:none;display:inline;"><a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
                        <ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
                            <li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
                            <li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
                            <li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
                            <li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
                        </ul>
                    </div>
					<a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a>
					-->
					</span> <strong> <b>新增招标项目</b> <i></i>
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
											<td align="right">项目编号：</td>
											<td><input name="" type="text" /></td>
											<td align="right">项目名称：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">需求部门：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">采购组织：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">发标时间：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">招标公司：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">联系人：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">电话：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">邮件地址：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">简介：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">项目描述：</td>
											<td colspan="3"><textarea id="txtDescription" class="example" rows="1" style="width: 600px"></textarea></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea class="example" rows="1" style="width: 600px"></textarea></td>
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
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>其他信息</strong>
								</dt>

								<dd style="display: block;">
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
									<table style="border: none;">
										<tr>
											<td align="right">退货情况：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">拒收情况：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">按时交货率：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">数量可靠性：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">服务评价：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">信用等级：</td>
											<td><div class="no-border">
													<div id="comboCreditLevel" class="combo"></div>
												</div></td>

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
											var cb1 = $('#comboCreditLevel').mac('combo', cfg1);
											$('#get1').click(function(){
												alert(cb1.selected);
											});
											$('#set1').click(function(){
												cb1.select(2);
											});
											var cfg2 = $.extend({}, cfg);
											cfg2.multiSelect = true;
										</script>

										</tr>
									</table>

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
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />项目公告项</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(6,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />其他项</a></li>
							<li><a onclick="javascript:tab(6,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />项目明细项</a></li>
							<li><a onclick="javascript:tab(6,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />供应商资料项</a></li>
							<li><a onclick="javascript:tab(6,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />投标项</a></li>
							<li><a onclick="javascript:tab(6,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />投标过程管理项</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div class="right_btn">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<div class="pagelist drop">
							<ul>
								<li class="tp"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul></li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
							</div>
						</div>
						<div class="table_10">
							<table class="list">
								<tr>
									<th width="50">
										<div class="list_check">
											<div class="drop">
												<label><input name="" type="checkbox" value="" /></label>
												<ul>
													<li><a href="#">所有</a></li>
													<li><a href="#">其他</a></li>
													<li><a href="#">式样</a></li>
													<li><a href="#">关闭</a></li>
												</ul>
											</div>
										</div>
									</th>
									<th>&nbsp;</th>
									<th width="10%">编号</th>
									<th width="15%">公告名称</th>
									<th width="30%">公告内容</th>
									<th width="10%">发布时间</th>
									<th width="10%">经办人</th>
									<th width="10%">审批人</th>
									<th width="10%"><s:text name="cmn_operate" /></th>
								</tr>
								<tr>
									<td><input name="" type="checkbox" value="" /></td>
									<td></td>
									<td><span style="color: gray;">srm20130425</span></td>
									<td><span style="color: gray;">商业建筑方案设计招标公告</span></td>
									<td><span style="color: gray;">须具有建设部颁发的建筑设计院甲级或乙级资质</span></td>
									<td><span style="color: gray;">2013-04-25</span></td>
									<td><span style="color: gray;">张三</span></td>
									<td><span style="color: gray;">李四</span></td>
									<td>
										<div class="untitled">
											<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
											<div class="popup">
												<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
												</strong>
												<p>帮助</p>
											</div>
										</div>
									</td>
								</tr>
								<c:forEach begin="1" end="9">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="pagelist drop">
							<ul>
								<li class="ed"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul></li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
							</div>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: 0;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="test"></table>
					</div>
					<div id="a3" style="display: none;">
						<div class="right_btn">
							<span><a href="javascript:void(0);" onclick="saveOrUpdate();"><img src="img/address_book.png" alt="" /></a></span> <span><a href="javascript:void(0);" onclick="saveOrUpdate();"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img
									src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span>
						</div>
						<div class="pagelist drop">
							<ul>
								<li class="tp"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul></li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
							</div>
						</div>
						<div class="table_10">
							<table class="list">
								<tr>
									<th width="50">
										<div class="list_check">
											<div class="drop">
												<label><input name="" type="checkbox" value="" /></label>
												<ul>
													<li><a href="#">所有</a></li>
													<li><a href="#">其他</a></li>
													<li><a href="#">式样</a></li>
													<li><a href="#">关闭</a></li>
												</ul>
											</div>
										</div>
									</th>
									<th>&nbsp;</th>
									<th width="15%">项目编号<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
									<th width="20%">项目名称</th>
									<th width="40%">项目内容</th>
									<th width="15%">备注</th>
									<th width="10%"><s:text name="cmn_operate" /></th>
								</tr>
								<tr>
									<td><input name="" type="checkbox" value="" /></td>
									<td></td>
									<td>srm20130423</td>
									<td>三台工业园两江工业集中区南区合作办园项目</td>
									<td>为进一步扩大对外开放，推进工业强县战略，创新园区运行模式，吸引社会资本和…</td>
									<td>无</td>
									<td>
										<div class="untitled">
											<span><img src="img/icon_untitled.png" alt="" /></span>
											<div class="popup">
												<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
												</strong>
												<p>帮助</p>
											</div>
										</div>
									</td>
								</tr>
								<c:forEach begin="1" end="9">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="pagelist drop">
							<ul>
								<li class="ed"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul></li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
							</div>
						</div>
					</div>
					<div id="a4" style="display: none;">
						<div class="right_btn">
							<span><a href="javascript:void(0);" onclick="saveOrUpdate();"><img src="img/address_book.png" alt="" /></a></span> <span><a href="javascript:void(0);" onclick="saveOrUpdate();"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img
									src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span>
						</div>
						<div class="pagelist drop">
							<ul>
								<li class="tp"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul></li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
							</div>
						</div>
						<div class="table_10">
							<table class="list">
								<tr>
									<th width="50">
										<div class="list_check">
											<div class="drop">
												<label><input name="" type="checkbox" value="" /></label>
												<ul>
													<li><a href="#">所有</a></li>
													<li><a href="#">其他</a></li>
													<li><a href="#">式样</a></li>
													<li><a href="#">关闭</a></li>
												</ul>
											</div>
										</div>
									</th>
									<th>&nbsp;</th>
									<th width="10%">编号<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
									<th width="15%">供应商名称</th>
									<th width="10%">法人</th>
									<th width="10%">类型</th>
									<th width="10%">所属行业</th>
									<th width="10%">分类</th>
									<th width="10%">所属地区</th>
									<th width="10%">员工人数</th>
									<th width="10%">等级</th>
									<th width="10%"><s:text name="cmn_operate" /></th>
								</tr>
								<tr>
									<td><input name="" type="checkbox" value="" /></td>
									<td></td>
									<td>srm20130423</td>
									<td>小Apple</td>
									<td>小乔</td>
									<td>厂家供应商</td>
									<td>电子产品</td>
									<td>待定</td>
									<td>北京</td>
									<td>1,000</td>
									<td>C</td>
									<td>
										<div class="untitled">
											<span><img src="img/icon_untitled.png" alt="" /></span>
											<div class="popup">
												<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
												</strong>
												<p>帮助</p>
											</div>
										</div>
									</td>
								</tr>
								<c:forEach begin="1" end="9">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="pagelist drop">
							<ul>
								<li class="ed"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul></li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
							</div>
						</div>
					</div>
					<div id="a5" style="display: none;">
						<div class="right_btn">
							<span><a href="javascript:void(0);" onclick="saveOrUpdate();"><img src="img/address_book.png" alt="" /></a></span> <span><a href="javascript:void(0);" onclick="saveOrUpdate();"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img
									src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span>
						</div>
						<div class="pagelist drop">
							<ul>
								<li class="tp"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul></li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
							</div>
						</div>
						<div class="table_10">
							<table class="list">
								<tr>
									<th width="50">
										<div class="list_check">
											<div class="drop">
												<label><input name="" type="checkbox" value="" /></label>
												<ul>
													<li><a href="#">所有</a></li>
													<li><a href="#">其他</a></li>
													<li><a href="#">式样</a></li>
													<li><a href="#">关闭</a></li>
												</ul>
											</div>
										</div>
									</th>
									<th>&nbsp;</th>
									<th width="15%">投标编码<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
									<th width="15%">项目编码</th>
									<th width="15%">公司编号</th>
									<th width="15%">公司名称</th>
									<th width="30%">内容</th>
									<th width="10%"><s:text name="cmn_operate" /></th>
								</tr>
								<tr>
									<td><input name="" type="checkbox" value="" /></td>
									<td></td>
									<td>srm20130423</td>
									<td>pro20130423</td>
									<td>com20130423</td>
									<td>小Apple</td>
									<td>该公司以10,000,000元RMB竞标成功…</td>
									<td>
										<div class="untitled">
											<span><img src="img/icon_untitled.png" alt="" /></span>
											<div class="popup">
												<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
												</strong>
												<p>帮助</p>
											</div>
										</div>
									</td>
								</tr>
								<c:forEach begin="1" end="9">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="pagelist drop">
							<ul>
								<li class="ed"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul></li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
							</div>
						</div>
					</div>
					<div id="a6" style="display: none;">
						<div class="right_btn">
							<span><a href="javascript:void(0);" onclick="saveOrUpdate();"><img src="img/address_book.png" alt="" /></a></span> <span><a href="javascript:void(0);" onclick="saveOrUpdate();"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img
									src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span> <span><a href="#"><img src="img/address_book.png" alt="" /></a></span>
						</div>
						<div class="pagelist drop">
							<ul>
								<li class="tp"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul></li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
							</div>
						</div>
						<div class="table_10">
							<table class="list">
								<tr>
									<th width="50">
										<div class="list_check">
											<div class="drop">
												<label><input name="" type="checkbox" value="" /></label>
												<ul>
													<li><a href="#">所有</a></li>
													<li><a href="#">其他</a></li>
													<li><a href="#">式样</a></li>
													<li><a href="#">关闭</a></li>
												</ul>
											</div>
										</div>
									</th>
									<th>&nbsp;</th>
									<th width="10%">项目编码<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
									<th width="20%">项目名称</th>
									<th width="20%">过程主题</th>
									<th width="40%">内容</th>
									<th width="10%"><s:text name="cmn_operate" /></th>
								</tr>
								<tr>
									<td><input name="" type="checkbox" value="" /></td>
									<td></td>
									<td>pro20130423</td>
									<td>三台工业园两江工业集中区南区合作办园项目</td>
									<td>关于三台工业园办园项目过程记录</td>
									<td>为进一步扩大对外开放，推进工业强县战略，创新园区运行模式，吸引社会资本和…</td>
									<td>
										<div class="untitled">
											<span><img src="img/icon_untitled.png" alt="" /></span>
											<div class="popup">
												<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
												</strong>
												<p>帮助</p>
											</div>
										</div>
									</td>
								</tr>
								<c:forEach begin="1" end="9">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="pagelist drop">
							<ul>
								<li class="ed"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul></li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
							</div>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--oder-->
		<div class="sub_menu sub_menu_bot">
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