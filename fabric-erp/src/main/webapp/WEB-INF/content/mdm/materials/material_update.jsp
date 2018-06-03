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
	<%--
	$('#dgdA1').datagrid({
		title:'资质明细',
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
			{field:'itemName',title:'资质名称',width:200,
				formatter:function(value,rec){
					return '<span>资质证书</span>';
				}
			}
		]],
		columns:[[
			{field:'specification',title:'资质描述',width:300,
				formatter:function(value,rec){
					return '<span>我公司将进一步提高，对原材料的需求将加大</span>';
				}
			},
			{field:'itemType',title:'资质文件',width:200,
				formatter:function(value,rec){
					return '<span>营业执照、税务登记证</span>';
				}	
			},
			{field:'amount',title:'资质有效期',width:100,
				formatter:function(value,rec){
					return '<span>10000</span>';
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
	
	$('#dgdA2').datagrid({
		title:'通信地址明细',
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
			{field:'itemCode',title:'编码',width:110,
				formatter:function(value,rec){
					return '<span>srm20130423</span>';
				}
			}
		]],
		columns:[[
			{field:'specification',title:'国家',width:100,
				formatter:function(value,rec){
					return '<span>中国</span>';
				}
			},
			{field:'itemType',title:'城市',width:100,
				formatter:function(value,rec){
					return '<span>北京</span>';
				}	
			},
			{field:'amount',title:'省份/州',width:100,
				formatter:function(value,rec){
					return '<span>北京</span>';
				}
			},
			{field:'netTotal',title:'街道',width:100,
				formatter:function(value,rec){
					return '<span>和平路12号</span>';
				}
			},
			{field:'netPrice',title:'电话',width:100,
				formatter:function(value,rec){
					return '<span>010-24706839</span>';
				}
			},
			{field:'coin',title:'传真',width:100,
				formatter:function(value,rec){
					return '<span>010-24706839</span>';
				}
			},
			{field:'materialBatches',title:'邮政编码',width:100,
				formatter:function(value,rec){
					return '<span>199999</span>';
				}
			},
			{field:'batch',title:'邮政信箱',width:100,
				formatter:function(value,rec){
					return '<span>abc@123.com</span>';
				}
			},
			{field:'supplier',title:'网址',width:150,
				formatter:function(value,rec){
					return '<span>www.123.com</span>';
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
	
	$('#dgdA3').datagrid({
		title:'银行信息明细',
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
			{field:'itemCode',title:'编码',width:110,
				formatter:function(value,rec){
					return '<span>srm20130423</span>';
				}
			}
		]],
		columns:[[
			{field:'specification',title:'开户银行',width:200,
				formatter:function(value,rec){
					return '<span>北京西单支行</span>';
				}
			},
			{field:'itemType',title:'银行帐号',width:200,
				formatter:function(value,rec){
					return '<span>62228888888888888888</span>';
				}	
			},
			{field:'amount',title:'开户银行代码',width:200,
				formatter:function(value,rec){
					return '<span>bank_20130523</span>';
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
	
	$('#dgdA4').datagrid({
		title:'指标明细',
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
			{field:'supplierCode',title:'编码',width:110,
				formatter:function(value,rec){
					return '<span>srm20130423</span>';
				}
			}
		]],
		columns:[[
			{field:'specification',title:'指标名称',width:100,
				formatter:function(value,rec){
					return '<span>注册资金</span>';
				}
			},
			{field:'itemType',title:'权重',width:100,
				formatter:function(value,rec){
					return '<span>★★★☆☆</span>';
				}	
			},
			{field:'amount',title:'分数',width:100,
				formatter:function(value,rec){
					return '<span>70</span>';
				}
			},
			{field:'netTotal',title:'内容',width:300,
				formatter:function(value,rec){
					return '<span>注册资金5,000,000</span>';
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
	
	$('#dgdA5').datagrid({
		title:'附件明细',
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
			{field:'itemCode',title:'关联对象编码',width:110,
				formatter:function(value,rec){
					return '<span>srm20130423</span>';
				}
			}
		]],
		columns:[[
			{field:'specification',title:'业务对象类型',width:100,
				formatter:function(value,rec){
					return '<span>类型A</span>';
				}
			},
			{field:'itemType',title:'附件名称',width:100,
				formatter:function(value,rec){
					return '<span>营业执照</span>';
				}	
			},
			{field:'amount',title:'附件描述',width:300,
				formatter:function(value,rec){
					return '<span>这是我们公司的营业执照...</span>';
				}
			},
			{field:'netTotal',title:'路径',width:200,
				formatter:function(value,rec){
					return '<span>D:\appendix\营业执照.jpg</span>';
				}
			},
			{field:'netPrice',title:'创建时间',width:100,
				formatter:function(value,rec){
					return '<span>2013-04-23</span>';
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
--%>
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
				<li><a href="#"><font color="black;">${vv:varView("vix_mdm_item")}</font></a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/financeAction!goSaveOrUpdate.action');">财务</a></li>
				<a href="#" onclick="loadContent('${vix}/mdm/produceAction!goSaveOrUpdate.action');">生产</a>
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
					</span> <strong> <b>${vv:varView("vix_mdm_item")}基本信息</b> <i></i>
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
									</span> <strong>${vv:varView("vix_mdm_item")}</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th><font color="red">一般信息：</font></th>
											<th colspan="3"></th>
										</tr>
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
											<td><input id="code" type="text" size="30" /></td>
											<td align="right">旧${vv:varView("vix_mdm_item")}编码：</td>
											<td><input id="oldItemCode" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}名称：</td>
											<td><input id="name" type="text" size="30" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}助记码：</td>
											<td><input id="scode" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">通用名称：</td>
											<td><input id="gernalName" type="text" size="30" /></td>
											<td align="right">英文名：</td>
											<td><input id="engishName" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}类型：</td>
											<td><input id="type" type="text" size="30" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}分类：</td>
											<td><input id="catalog" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}状态码：</td>
											<td><input id="status" type="text" size="30" /></td>
											<td align="right">规格型号：</td>
											<td><input id="specification" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">规格说明：</td>
											<td><input id="specDescription" type="text" size="30" /></td>
											<td align="right">味道类型：</td>
											<td><input id="tasteType" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否${vv:varView("vix_mdm_item")}组：</td>
											<td><input id="isItemGrpoup" type="text" size="30" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}组：</td>
											<td><input id="itemGroup" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}组1：</td>
											<td><input id="itemGroup1" type="text" size="30" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}组2：</td>
											<td><input id="itemGroup2" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">状态码：</td>
											<td><input id="statusCode" type="text" size="30" /></td>
											<td align="right">行业领域：</td>
											<td><input id="industry" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">投放日期：</td>
											<td><input id="putOnDate" type="text" size="30" /></td>
											<td align="right">停售日期：</td>
											<td><input id="stopSellingDate" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">图号：</td>
											<td><input id="drawingCode" type="text" size="30" /></td>
											<td align="right">销售税率：</td>
											<td><input id="saleTax" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否为虚拟件：</td>
											<td><input id="isVirtual" type="text" size="30" /></td>
											<td align="right">是否主生产计划${vv:varView("vix_mdm_item")}(MPS)：</td>
											<td><input id="isMPS" type="text" size="30" /></td>
										</tr>
										<tr>
											<td colspan="4">====================================================================================================</td>
										</tr>
										<tr>
											<th><font color="red">价格信息：</font></th>
											<th colspan="3"></th>
										</tr>
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}低阶码：</td>
											<td><input id="lowerCode" type="text" size="30" /></td>
											<td align="right">价格：</td>
											<td><input id="price" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">成本价格：</td>
											<td><input id="costPrice" type="text" size="30" /></td>
											<td align="right">采购价格：</td>
											<td><input id="purchasePrice" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">批发价格：</td>
											<td><input id="wholesalePrice" type="text" size="30" /></td>
										</tr>
										<tr>
											<td colspan="4">====================================================================================================</td>
										</tr>
										<tr>
											<th><font color="red">量纲：</font></th>
											<th colspan="3"></th>
										</tr>
										<tr>
											<td align="right">主计量单位：</td>
											<td><input id="masterUnit" type="text" size="30" /></td>
											<td align="right">零售计量单位：</td>
											<td><input id="retailUnit" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">采购默认单位：</td>
											<td><input id="purchaseUnit" type="text" size="30" /></td>
											<td align="right">销售默认计量单位：</td>
											<td><input id="saleUnit" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">生产计量单位：</td>
											<td><input id="produceUnit" type="text" size="30" /></td>
											<td align="right">销售默认单位：</td>
											<td><input id="saleDefaultUnit" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">库存默认单位：</td>
											<td><input id="stockDefaultUnit" type="text" size="30" /></td>
											<td align="right">成本默认计量单位：</td>
											<td><input id="costDefaultUnit" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">净含量：</td>
											<td><input id="net" type="text" size="30" /></td>
											<td align="right">EAN/UPC：</td>
											<td><input id="eanupc" type="text" size="30" /></td>
										</tr>
										<tr>
											<td colspan="4">====================================================================================================</td>
										</tr>
										<tr>
											<th><font color="red">生产情况：</font></th>
											<th colspan="3"></th>
										</tr>
										<tr>
											<td align="right">部门：</td>
											<td><input id="departmentCode" type="text" size="30" /></td>
											<td align="right">产品组：</td>
											<td><input id="productGroup" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">批次号：</td>
											<td><input id="batchCode" type="text" size="30" /></td>
											<td align="right">序列号：</td>
											<td><input id="serialCode" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">生产国别：</td>
											<td><input id="productCountry" type="text" size="30" /></td>
											<td align="right">生产企业：</td>
											<td><input id="productEnterprise" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">产品线：</td>
											<td><input id="productLine" type="text" size="30" /></td>
											<td align="right">次产品线：</td>
											<td><input id="viceProductLine" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">生产地点：</td>
											<td><input id="productLocation" type="text" size="30" /></td>
											<td align="right">产地：</td>
											<td><input id="origin" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">品牌：</td>
											<td><input id="brand" type="text" size="30" /></td>
											<td align="right">成分：</td>
											<td><input id="composition" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">材料质地：</td>
											<td><input id="material" type="text" size="30" /></td>
											<td align="right">地区：</td>
											<td><input id="region" type="text" size="30" /></td>
										</tr>
										<tr>

											<td align="right">尺码：</td>
											<td><input id="size" type="text" size="30" /></td>
											<td align="right">颜色：</td>
											<td><input id="color" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">生产日期：</td>
											<td><input id="produceDate" type="text" size="30" /></td>
											<td align="right">出厂日期：</td>
											<td><input id="productionDate" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">保质期：</td>
											<td><input id="qualityPeriod" type="text" size="30" /></td>
											<td align="right">失效日期：</td>
											<td><input id="expiryDate" type="text" size="30" /></td>
										</tr>
										<tr>
											<td colspan="4">====================================================================================================</td>
										</tr>
										<tr>
											<th><font color="red">操作信息：</font></th>
											<th colspan="3"></th>
										</tr>
										<tr>
											<td align="right">是否需要配置：</td>
											<td><input id="isConfig" type="text" size="30" /></td>
											<td align="right">操作人：</td>
											<td><input id="Operator" type="text" size="30" /></td>
										</tr>

										<tr>
											<td align="right">操作时间：</td>
											<td><input id="operateTime" type="text" size="30" /></td>
											<td align="right">是否统计：</td>
											<td><input id="isStatics" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否有效：</td>
											<td colspan="3"><input id="isValid" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>采购</strong>
								</dt>

								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}代码：</td>
											<td><input id="itemCode" type="text" size="30" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}类型：</td>
											<td><input id="itemType" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">采购基本计量单位：</td>
											<td><input id="purBaseUnit" type="text" size="30" /></td>
											<td align="right">采购订单单位：</td>
											<td><input id="poUnit" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">采购组：</td>
											<td><input id="purchaseGroup" type="text" size="30" /></td>
											<td align="right">采购员：</td>
											<td><input id="purchasePerson" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">不足交货容差：</td>
											<td><input id="lessDeliveryAllowance" type="text" size="30" /></td>
											<td align="right">超量交货容差：</td>
											<td><input id="exceedDelieryAllowance" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最小交货数量百分比：</td>
											<td><input id="minDelieryPercent" type="text" size="30" /></td>
											<td align="right">最小交货数量：</td>
											<td><input id="minDelieryAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">催付天数1：</td>
											<td><input id="remindDay1" type="text" size="30" /></td>
											<td align="right">催付天数2：</td>
											<td><input id="remindDay2" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">催付天数3：</td>
											<td><input id="remindDay3" type="text" size="30" /></td>
											<td align="right">催付天数4：</td>
											<td><input id="remindDay4" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">催付天数5：</td>
											<td><input id="remindDay5" type="text" size="30" /></td>
											<td align="right">标准交货时间偏差(D)：</td>
											<td><input id="standardDelieryDeviation" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">收货处理时间(D)：</td>
											<td><input id="takeDelieryDays" type="text" size="30" /></td>
											<td align="right">配额安排：</td>
											<td><input id="quota" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否需要批次管理：</td>
											<td><input id="isNeedBatch" type="text" size="30" /></td>
											<td align="right">是否为关键组件：</td>
											<td><input id="isKeyComponent" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">JIT：</td>
											<td><input id="isJIT" type="text" size="30" /></td>
											<td align="right">制造商零配件编码：</td>
											<td><input id="producerSharepartCode" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">制造商零配件概况：</td>
											<td><input id="produceSharepartDesc" type="text" size="30" /></td>
											<td align="right">采购类型：</td>
											<td><input id="purchaseType" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">特殊采购类：</td>
											<td><input id="specialPurchaseType" type="text" size="30" /></td>
											<td align="right">允许提前天数：</td>
											<td><input id="permitAheadDays" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">允许滞后天数：</td>
											<td><input id="permitDelayDays" type="text" size="30" /></td>
											<td align="right">是否允许替换物品收货：</td>
											<td><input id="isPermitReplaceItem" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否允许没有订单收货：</td>
											<td><input id="isPermitNoOrderTake" type="text" size="30" /></td>
											<td align="right">接收仓库：</td>
											<td><input id="recieveWarehouse" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">接收地址：</td>
											<td colspan="3"><input id="recieveLocation" type="text" size="80" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>销售</strong>
								</dt>

								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
											<td><input id="itemCode" type="text" size="30" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}类型：</td>
											<td><input id="itemType" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">销售组织：</td>
											<td><input id="saleOrg" type="text" size="30" /></td>
											<td align="right">销售组：</td>
											<td><input id="saleGroup" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">销售员编码：</td>
											<td><input id="salePersonCode" type="text" size="30" /></td>
											<td align="right">销售员：</td>
											<td><input id="salePerson" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">交货工厂：</td>
											<td><input id="deliveryFactory" type="text" size="30" /></td>
											<td align="right">渠道：</td>
											<td><input id="channel" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">缺省利润中心：</td>
											<td><input id="defalutMarginCenter" type="text" size="30" /></td>
											<td align="right">缺省利润中心代码：</td>
											<td><input id="defalutMarginCenterCode" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">销售税率：</td>
											<td><input id="saleTax" type="text" size="30" /></td>
											<td align="right">最小订购量：</td>
											<td><input id="minRequireAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最小发货量：</td>
											<td><input id="minDeliveryAmount" type="text" size="30" /></td>
											<td align="right">销售退回是否需要检验：</td>
											<td><input id="isReturnCheck" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">销售基本计量单位：</td>
											<td><input id="saleBaseUnit" type="text" size="30" /></td>
											<td align="right">销售单位：</td>
											<td><input id="saleUnit" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">产品组：</td>
											<td><input id="productGroup" type="text" size="30" /></td>
											<td align="right">是否提供现金折扣：</td>
											<td><input id="isCashDiscount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}定价组：</td>
											<td><input id="itemPriceGroup" type="text" size="30" /></td>
											<td align="right">成交额回扣组：</td>
											<td><input id="dealAmountCommissionGroup" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">定价参考${vv:varView("vix_mdm_item")}：</td>
											<td><input id="setPriceRefItemCode" type="text" size="30" /></td>
											<td align="right">佣金组：</td>
											<td><input id="commissionGroup" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">科目设置组：</td>
											<td><input id="accountTitleGroup" type="text" size="30" /></td>
											<td align="right">销售科目：</td>
											<td><input id="saleAccountTitle" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>库存</strong>
								</dt>

								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
											<td><input id="itemCode" type="text" size="30" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}类型：</td>
											<td><input id="itemType" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最高库存：</td>
											<td><input id="maxStockAmount" type="text" size="30" /></td>
											<td align="right">最低库存：</td>
											<td><input id="minStockAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">安全库存：</td>
											<td><input id="safeStockAmount" type="text" size="30" /></td>
											<td align="right">默认仓库：</td>
											<td><input id="defalutWarehouse" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">默认货位：</td>
											<td><input id="defalutShelf" type="text" size="30" /></td>
											<td align="right">替换${vv:varView("vix_mdm_item")}：</td>
											<td><input id="repalceItem" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">请购时上限：</td>
											<td><input id="requireMaxAmount" type="text" size="30" /></td>
											<td align="right">入库超额上限：</td>
											<td><input id="inStockExceedAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">出库超额上限：</td>
											<td><input id="outStockExceedAmount" type="text" size="30" /></td>
											<td align="right">订货超额上限：</td>
											<td><input id="bookingExceedAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">主辅计量单位转换率：</td>
											<td><input id="unitExchangeRate" type="text" size="30" /></td>
											<td align="right">合理耗损率：</td>
											<td><input id="coefficientOfLosses" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">ABC分类(码)：</td>
											<td><input id="abcCatalog" type="text" size="30" /></td>
											<td align="right">库存分类：</td>
											<td><input id="catalogCode" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">自定义库存分类：</td>
											<td><input id="cusomterCatalog" type="text" size="30" /></td>
											<td align="right">领料批量：</td>
											<td><input id="receiveMateriaBatchAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最小分割组：</td>
											<td><input id="minSperateGroup" type="text" size="30" /></td>
											<td align="right">盘点周期：</td>
											<td><input id="countingCycle" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">盘点周期单位：</td>
											<td><input id="countingCycleUnit" type="text" size="30" /></td>
											<td align="right">上次盘点时间：</td>
											<td><input id="lastCountingTime" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否单独存放：</td>
											<td><input id="isStockAlone" type="text" size="30" /></td>
											<td align="right">出库跟踪入库：</td>
											<td><input id="isOutTraceIn" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">序列号管理：</td>
											<td><input id="isNeedSerialNumber" type="text" size="30" /></td>
											<td align="right">条码管理：</td>
											<td><input id="isNeedBarCode" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">呆滞积压：</td>
											<td><input id="overStock" type="text" size="30" /></td>
											<td align="right">质量检验：</td>
											<td><input id="isNeedQuality" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否进行库存管理：</td>
											<td><input id="isNeedStock" type="text" size="30" /></td>
											<td align="right">是否需要批次号：</td>
											<td><input id="isNeedBatchNumber" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">库存寿命(保质期)：</td>
											<td><input id="shelfLife" type="text" size="30" /></td>
											<td align="right">是否倒冲入账：</td>
											<td><input id="isBackflush" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>运输和包装</strong>
								</dt>

								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
											<td><input id="itemCode" type="text" size="30" /></td>
											<td align="right">包装${vv:varView("vix_mdm_item")}：</td>
											<td><input id="packageItem" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">包装物规则：</td>
											<td><input id="packageRule" type="text" size="30" /></td>
											<td align="right">是否可回收：</td>
											<td><input id="isRecycle" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">运输分类：</td>
											<td><input id="transferCatalog" type="text" size="30" /></td>
											<td align="right">容器：</td>
											<td><input id="container" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">容器类型：</td>
											<td><input id="containerType" type="text" size="30" /></td>
											<td align="right">运输工具：</td>
											<td><input id="transferTools" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">内部尺寸：</td>
											<td><input id="innerDimensions" type="text" size="30" /></td>
											<td align="right">最大运输重量：</td>
											<td><input id="maxTransferWeight" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最小填充百分比：</td>
											<td colspan="3"><input id="minFillOffPercent" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>价格条件</strong>
								</dt>

								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
											<td><input id="itemCode" type="text" size="30" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}名称：</td>
											<td><input id="itemName" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">主题：</td>
											<td><input id="subject" type="text" size="30" /></td>
											<td align="right">价格条件：</td>
											<td><input id="conditionName" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">价格条件类型：</td>
											<td colspan="3"><input id="priceType" type="text" size="30" /></td>
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
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />资质</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(6,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />通信地址</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(6,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />财务</a></li>
							<li><a onclick="javascript:$('#a4').attr('style','');tab(6,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />指标</a></li>
							<li><a onclick="javascript:$('#a5').attr('style','');tab(6,5,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>
					<div id="a1" style="width: 100%;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA1"></table>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA2"></table>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA3"></table>
					</div>
					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA4"></table>
					</div>
					<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA5"></table>
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