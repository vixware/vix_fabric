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
/** 查询展开与收起效果 */
$(function(){
	$("#lb_search_advanced").toggle(
		function () {
			$("#c_head").addClass("advanced");
			$("#lb_search_advanced").text($("#cmn_simple_search").val());
		 },
		 function () {
			$("#c_head").removeClass("advanced");
			$("#lb_search_advanced").text($("#cmn_advance_search").val());
		 }
	);
	$("#switch_box").toggle(
		  function () {
			$("#switch_box").addClass("off")
			$("#left").addClass("switch")
			$("#right").addClass("switch")
			$(".left_head").addClass("wid7px")
			$(".left_content").addClass("current")
			$("#left").css({ "width": "7px"})
		  },
		  function () {
			$("#switch_box").removeClass("off")
			$("#left").removeClass("switch")
			$("#right").removeClass("switch")
			$(".left_head").removeClass("wid7px")
			$(".left_content").removeClass("current")
			$("#left").css({ "width": "252px"})
		  }
	);
});
bindSwitch();
function categoryTab(num,befor,id,e,entity){
	var el=e.target?e.target.parentNode:e.srcElement.parentNode;
	var pa=el.parentNode.getElementsByTagName("li");
	for(var i=0;i<pa.length;i++){
		pa[i].className="";
	}
	el.className="current";
	for(i=1;i<=num;i++){
		try{
			if(i==befor){
				document.getElementById(id+i).style.display="block";
			}else{
				document.getElementById(id+i).style.display="none";
			}
		}catch(e){ }
	}
	if(entity != undefined){
		categoryPager('start',entity);
	}
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
}
$(function(){
	$("#right").resizable({
		maxHeight: 300,
		minHeight: 118,
		handles: 's'
	}); 
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
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" />人力资源</a></li>
				<li><a href="#">员工管理</a></li>
				<li><a href="#">员工信息管理</a></li>
				<li><a href="#">新建</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCustomer();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消"
							src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a href="#"><img
							width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" alt="打印"
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
					</span> <strong> <b>人员信息</b> <i></i>
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
											<td align="right">人员编码：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">姓名：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">曾用名：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">年龄：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">性别：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">身份证号码：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">出生日期：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">血型：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">户籍：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">民族：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">婚姻状况：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">健康状况：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">邮政编码：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">通讯地址：</td>
											<td><input name="" type="text" size="30" /></td>
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

					<div class="clearfix" style="background: #FFF; position: relative;">
						<div class="right_menu">
							<ul>
								<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />通讯信息</a></li>
								<li><a onclick="javascript:$('#a2').attr('style','');tab(6,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />其他项</a></li>
								<li><a onclick="javascript:tab(6,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />地址信息</a></li>
								<li><a onclick="javascript:tab(6,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />学历信息</a></li>
								<li><a onclick="javascript:tab(6,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />工作经历</a></li>
								<li><a onclick="javascript:tab(6,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />家庭成员</a></li>
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
										<th>序号</th>
										<th>内容</th>
										<th>类别</th>
										<th>创建时间</th>
										<th><s:text name="cmn_operate" /></th>
									</tr>
									<tr>
										<td><input name="" type="checkbox" value="" /></td>
										<td></td>
										<td><span style="color: gray;">qq20130423</span></td>
										<td><span style="color: gray;">1211</span></td>
										<td><span style="color: gray;">A121</span></td>
										<td><span style="color: gray;">2013-7-8</span></td>
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

						<!-- 地址信息 -->
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
										<th>地址类型<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
										<th>邮政编码</th>
										<th>国家</th>
										<th>省份</th>
										<th>地市</th>
										<th>县区</th>
										<th>街道门牌号</th>
										<th>开始日期</th>
										<th>结束日期</th>
										<th width="10%"><s:text name="cmn_operate" /></th>
									</tr>
									<tr>
										<td><input name="" type="checkbox" value="" /></td>
										<td></td>
										<td>居住地址</td>
										<td>123</td>
										<td>中国</td>
										<td>北京</td>
										<td>北京</td>
										<td>朝阳</td>
										<td>23</td>
										<td>2012-10-1</td>
										<td>2013-10-1</td>
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
						<!-- 学历信息 -->
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
										<th>学历<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
										<th>学历证书</th>
										<th>入学时间</th>
										<th>学历证书编号</th>
										<th>专业门类代码</th>
										<th>学习形式</th>
										<th>学制</th>
										<th>学校</th>
										<th>开始日期</th>
										<th>结束日期</th>
										<th width="10%"><s:text name="cmn_operate" /></th>
									</tr>
									<tr>
										<td><input name="" type="checkbox" value="" /></td>
										<td></td>
										<td>本科</td>
										<td>毕业证书</td>
										<td>2012-1-1</td>
										<td>007</td>
										<td>软件专业</td>
										<td>全日制</td>
										<td>3.5+0.5</td>
										<td>xx学校</td>
										<td>2013-1-1</td>
										<td>2014-1-1</td>
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
						<!-- 工作经历 -->
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
										<th>所在单位<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
										<th>所在部门</th>
										<th>所在岗位</th>
										<th>职级</th>
										<th>专业经历</th>
										<th>证明人</th>
										<th>任职文号</th>
										<th>开始日期</th>
										<th>结束日期</th>
										<th><s:text name="cmn_operate" /></th>
									</tr>
									<tr>
										<td><input name="" type="checkbox" value="" /></td>
										<td></td>
										<td>xx集团</td>
										<td>技术研发部</td>
										<td>技术总监</td>
										<td>总监</td>
										<td>计算机软件开发</td>
										<td>啸天犬</td>
										<td>1111</td>
										<td>2011-1-1</td>
										<td>2013-1-1</td>
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
						<!-- 家庭成员 -->
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
										<th>成员类别<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
										<th>序号</th>
										<th>姓名</th>
										<th>性别</th>
										<th>出生年月日</th>
										<th>民族</th>
										<th>身份证号</th>
										<th>工作单位</th>
										<th>开始日期</th>
										<th>结束日期</th>
										<th><s:text name="cmn_operate" /></th>
									</tr>
									<tr>
										<td><input name="" type="checkbox" value="" /></td>
										<td></td>
										<td>子女</td>
										<td>01</td>
										<td>二郎神</td>
										<td>男</td>
										<td>2013-04-23</td>
										<td>汉</td>
										<td>20130101</td>
										<td>学生</td>
										<td>2013-04-23</td>
										<td>2013-04-23</td>
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