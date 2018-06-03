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
				<li><a href="#" onclick="loadContent('${vix}/mdm/materialsAction!goSaveOrUpdate.action');">${vv:varView("vix_mdm_item")}</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/financeAction!goSaveOrUpdate.action');">财务</a></li>
				<a href="#"><font color="black;">生产</font></a>
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
					</span> <strong> <b>生产信息</b> <i></i>
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
									</span> <strong>MRP</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
											<td><input id="itemCode" type="text" size="30" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}类型：</td>
											<td><input id="itemType" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">MRP类型：</td>
											<td><input id="mrpType" type="text" size="30" /></td>
											<td align="right">重订货点：</td>
											<td><input id="reOrderPoint" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">生产部门：</td>
											<td><input id="produceDepartment" type="text" size="30" /></td>
											<td align="right">计划员：</td>
											<td><input id="planner" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划方法：</td>
											<td><input id="planMethod" type="text" size="30" /></td>
											<td align="right">计划时界：</td>
											<td><input id="planTimeFence" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划周期：</td>
											<td><input id="planPeroid" type="text" size="30" /></td>
											<td align="right">装配报废率：</td>
											<td><input id="assembleScrapRate" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">产出率：</td>
											<td><input id="outPutRate" type="text" size="30" /></td>
											<td align="right">需求时栅：</td>
											<td><input id="requirementTimefence" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划时栅天数：</td>
											<td><input id="planTimefenceDays" type="text" size="30" /></td>
											<td align="right">重叠天数：</td>
											<td><input id="overlapDays" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最高供应量：</td>
											<td><input id="maxSupplyAmount" type="text" size="30" /></td>
											<td align="right">最低供应量：</td>
											<td><input id="minSupplyAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">供需政策：</td>
											<td><input id="supplyPolicy" type="text" size="30" /></td>
											<td align="right">供应期间：</td>
											<td><input id="supplyPeriod" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">供应倍数：</td>
											<td><input id="supplyMultiple" type="text" size="30" /></td>
											<td align="right">变动基数：</td>
											<td><input id="changeBase" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">变动提前期：</td>
											<td><input id="changeLeadTime" type="text" size="30" /></td>
											<td align="right">可用性检查方式：</td>
											<td><input id="ATPCheckType" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">供应类型：</td>
											<td><input id="supplyType" type="text" size="30" /></td>
											<td align="right">工程图号：</td>
											<td><input id="projectDrawNumber" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">低阶码：</td>
											<td><input id="lowLevelCode" type="text" size="30" /></td>
											<td align="right">计划品：</td>
											<td><input id="planItem" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">转换因子：</td>
											<td><input id="conversionFactor" type="text" size="30" /></td>
											<td align="right">是否检查ATP：</td>
											<td><input id="isCheckATP" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">ATP规则：</td>
											<td><input id="ATPRule" type="text" size="30" /></td>
											<td align="right">替换日期：</td>
											<td><input id="replaceDate" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">安全库存方法：</td>
											<td><input id="safeStockMethod" type="text" size="30" /></td>
											<td align="right">期间类型：</td>
											<td><input id="periodType" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">期间数：</td>
											<td><input id="periodAmount" type="text" size="30" /></td>
											<td align="right">动态安全库存方法：</td>
											<td><input id="dynamicSafeStockMethod" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">覆盖天数：</td>
											<td><input id="overlayDays" type="text" size="30" /></td>
											<td align="right">百分比：</td>
											<td><input id="percent" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否属于主生产计划：</td>
											<td><input id="isBelongMPS" type="text" size="30" /></td>
											<td align="right">订购策略：</td>
											<td><input id="bookingPolice" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">工艺路线代码：</td>
											<td><input id="routeCode" type="text" size="30" /></td>
											<td align="right">是否成本相关：</td>
											<td><input id="isCostRelated" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否切除尾数：</td>
											<td><input id="isRemovalMantissa" type="text" size="30" /></td>
											<td align="right">是否令单合并：</td>
											<td><input id="isProductOrderMerge" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否为重复计划：</td>
											<td><input id="isRepeatPlan" type="text" size="30" /></td>
											<td align="right">是否MPS件：</td>
											<td><input id="isMPSComponent" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否允许BOM母件：</td>
											<td><input id="isPermitBOMParent" type="text" size="30" /></td>
											<td align="right">是否允许BOM子件：</td>
											<td><input id="isPermitBOMChild" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否允许生产订单：</td>
											<td><input id="isPermitProductOrder" type="text" size="30" /></td>
											<td align="right">是否为销售跟单：</td>
											<td><input id="isSaleTrace" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">批量数据-最小批量大小：</td>
											<td><input id="batchMinAmount" type="text" size="30" /></td>
											<td align="right">批量数据-最大批量大小：</td>
											<td><input id="batchMaxAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">批量数据-固定批量大小：</td>
											<td><input id="batchFixedAmount" type="text" size="30" /></td>
											<td align="right">批量数据-最大库存水平：</td>
											<td><input id="batchMaxInventory2" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">批量数据-订购成本：</td>
											<td><input id="batchOrderCost" type="text" size="30" /></td>
											<td align="right">批量数据-装配报废率%：</td>
											<td><input id="batchAssembleScrap" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">批量数据-间隔时间：</td>
											<td><input id="batchPeriodTime" type="text" size="30" /></td>
											<td align="right">批量数据-舍入值：</td>
											<td><input id="batchRoundedValue" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">批量数据-计量单位组：</td>
											<td><input id="batchUnitGroup" type="text" size="30" /></td>
											<td align="right">采购-采购类型：</td>
											<td><input id="purType" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">采购-反冲：</td>
											<td><input id="purRecoil" type="text" size="30" /></td>
											<td align="right">采购-JIT交货：</td>
											<td><input id="purJITDelivery" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划-收货处理时间：</td>
											<td><input id="planTakeDeliveryTime" type="text" size="30" /></td>
											<td align="right">计划-交货时间：</td>
											<td><input id="planDeliveryTime" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划-计划日历：</td>
											<td colspan="3"><input id="planCalendar" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>计划</strong>
								</dt>

								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
											<td><input id="itemCode" type="text" size="30" /></td>
											<td align="right">ROP件：</td>
											<td><input id="isROPComponent" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">再订货点方法：</td>
											<td><input id="reOrderMethod" type="text" size="30" /></td>
											<td align="right">ROP批量规则：</td>
											<td><input id="ropBatchRule" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">保证供应天数：</td>
											<td><input id="promiseSupplyDay" type="text" size="30" /></td>
											<td align="right">规定供应量：</td>
											<td><input id="prescriptSupplyAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">固定提前期：</td>
											<td><input id="fixedLeadTime" type="text" size="30" /></td>
											<td align="right">累计提前期：</td>
											<td><input id="totalLeadTime" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">变动提前期(加工提前期)：</td>
											<td><input id="varLeadTime" type="text" size="30" /></td>
											<td align="right">检查提前期：</td>
											<td><input id="checkLeadTime" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">安全时间：</td>
											<td><input id="safetyTime" type="text" size="30" /></td>
											<td align="right">安全存量：</td>
											<td><input id="safetyStock" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最大订购量：</td>
											<td><input id="maxBookingAmount" type="text" size="30" /></td>
											<td align="right">倍数：</td>
											<td><input id="multiple" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">固定订购量：</td>
											<td><input id="fixedBookingAmount" type="text" size="30" /></td>
											<td align="right">再订购点：</td>
											<td><input id="reOrderPoint" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">经济订购量：</td>
											<td><input id="economicOrderQuantity" type="text" size="30" /></td>
											<td align="right">结构代码：</td>
											<td><input id="structureCode" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>质量</strong>
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
											<td align="right">质量要求：</td>
											<td><input id="qualityRequirement" type="text" size="30" /></td>
											<td align="right">质量检验方法：</td>
											<td><input id="qualityMethod" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">检验说明：</td>
											<td><input id="qualityDesciption" type="text" size="30" /></td>
											<td align="right">质检周期：</td>
											<td><input id="qualityCycle" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">质检周期天数：</td>
											<td><input id="qualityCycleDays" type="text" size="30" /></td>
											<td align="right">到货入库质检：</td>
											<td><input id="isInStockQuality" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">出库质检：</td>
											<td><input id="isOutStockQuality" type="text" size="30" /></td>
											<td align="right">退货质检：</td>
											<td><input id="isReturnQuality" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">抽检方案：</td>
											<td><input id="spotCheck" type="text" size="30" /></td>
											<td align="right">抽检率：</td>
											<td><input id="spotCheckRate" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">抽检量：</td>
											<td><input id="spotCheckAmount" type="text" size="30" /></td>
											<td align="right">良品率%：</td>
											<td><input id="yieldRate" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">检验等级：</td>
											<td><input id="checkClass" type="text" size="30" /></td>
											<td align="right">是否批次取样：</td>
											<td><input id="isBackCheck" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">取样标准：</td>
											<td><input id="checkStandart" type="text" size="30" /></td>
											<td align="right">允许水平AQL：</td>
											<td><input id="averageAQL" type="text" size="30" /></td>
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