<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- <script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script> --%>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>

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
	$('#dgdA1').datagrid({
		title:'生产订单明细',
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
			{field:'itemName',title:'子件行号',width:200,
				formatter:function(value,rec){
					return '<span>资质证书</span>';
				}
			}
		]],
		columns:[[
			{field:'specification',title:'工序行号',width:100,
				formatter:function(value,rec){
					return '<span>类型1</span>';
				}
			},
			{field:'itemType',title:'子件编码',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType2',title:'子件名称',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType3',title:'计量单位',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType4',title:'基本用量',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType5',title:'基础数量',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType6',title:'固定用量',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType7',title:'使用数量',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType8',title:'产出品',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType9',title:'供应类型',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType10',title:'应领数量',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType11',title:'需求日期',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			},
			{field:'itemType12',title:'已领数量',width:100,
				formatter:function(value,rec){
					return '<span>10010</span>';
				}	
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'新增',
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
			text:'修改',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#tt').datagrid('getSelected');
				if (row){
					var index = $('#tt').datagrid('getRowIndex', row);
					$('#tt').datagrid('deleteRow', index);
				}
			}
		},'-',{
			id:'btnsave',
			text:'保存',
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/mm_scorder.png" alt="" />生产管理</a></li>
				<li><a href="#">生产订单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mm/pmOrderAction!goList.action');">生产订单列表</a></li>
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
						</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/mm/pmOrderAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增生产订单</b> <i></i>
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
											<td align="right">生产订单号：</td>
											<td><input id="name" name="BO_SPR_CMNM_name" type="text" size="30" /></td>
											<td align="right">订单主题：</td>
											<td><input id="parentCompanyNames" name="BO_SPR_CMNM_parentCompanyNames" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
											<td><input id="pinYin" name="BO_SPR_CMNM_pinYin" type="text" size="30" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}名称：</td>
											<td><input id="pinYin" name="BO_SPR_CMNM_pinYin" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计量单位：</td>
											<td><input id="parentCompanyName" name="BO_SPR_CMNM_parentCompanyName" type="text" size="30" /></td>
											<td align="right">生产数量：</td>
											<td><input id="region" name="BO_SPR_CMNM_region" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">规格：</td>
											<td><input id="name" name="BO_SPR_CMNM_name" type="text" size="30" /></td>
											<td align="right">供应仓库名称：</td>
											<td><input id="parentCompanyNames" name="BO_SPR_CMNM_parentCompanyNames" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}规格：</td>
											<td><input id="artificialPerson" name="BO_SPR_CMNM_artificialPerson" type="text" size="30" /></td>
											<td align="right">状态：</td>
											<td><select name="BO_SPR_CMNM_type" style="width: 50"><option>请选择</option></select></td>
										</tr>
										<tr>
											<td align="right">供应类型：</td>
											<td><select name="BO_SPR_CMNM_currency" style="width: 50"><option>请选择</option></select></td>
											<td align="right">供应仓库：</td>
											<td><input id="registeredCapital" name="BO_SPR_CMNM_registeredCapital" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">开工日期：</td>
											<td><input id="creDate" name="creDate" value="<fmt:formatDate value='${processManagement.creDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('creDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">完工日期：</td>
											<td><input id="creDaste" name="cresDate" value="<fmt:formatDate value='${processManagement.cresDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('creDsate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							<li class="current"><a onclick="javascript:tab(7,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />生产订单明细</a></li>
						</ul>
					</div>
					<div id="a1" style="width: 100%;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA1"></table>
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