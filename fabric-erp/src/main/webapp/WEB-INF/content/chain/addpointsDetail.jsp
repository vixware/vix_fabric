<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>

<script type="text/javascript">
	$ (".addtable .addtable_t").click (function () {
		$ (this).toggleClass ("addtable_td");
		$ (this).next (".addtable_c").toggle ();
	});
	$ (".newvoucher dt b").click (function () {
		$ (this).toggleClass ("downup");
		$ (this).parent ("dt").next ("dd").toggle ();
	});
	$ (".order_table input[type='text']").focusin (function () {
		$ (this).css ({
		'border' : '1px solid #f00' ,
		'background' : '#f5f5f5'
		});
	});
	$ (".order_table  input[type='text']").focusout (function () {
		$ (this).css ({
		'border' : '1px solid #ccc' ,
		'background' : '#fff'
		});
	});
	$ ('#test')
			.datagrid (
					{
					title : '积分规则列表' ,
					width : "auto" ,
					height : 550 ,
					nowrap : true ,
					autoRowHeight : false ,
					striped : true ,
					collapsible : true ,
					url : '${vix}/common/json_tests/datagrid_data.json' ,
					sortName : 'distributorCode' ,
					sortOrder : 'asc' ,
					remoteSort : false ,
					pagination : true ,
					rownumbers : true ,
					idField : 'distributorCode' ,
					frozenColumns : [
						[
						{
						field : 'ck' ,
						checkbox : true
						} , {
						title : '经销商编码' ,
						field : 'distributorCode' ,
						align : 'center' ,
						width : 100 ,
						formatter : function (value, rec) {
							return '12345';
						}
						}
						]
					] ,
					columns : [
						[
						{
						title : '经销商名称' ,
						field : 'distributorName' ,
						align : 'center' ,
						width : 100 ,
						fitColumns : true ,
						formatter : function (value, rec) {
							return '沃尔玛';
						}
						} , {
						title : '客户类型' ,
						field : 'customerType' ,
						align : 'center' ,
						width : 100 ,
						formatter : function (value, rec) {
							return 'VIP会员';
						}
						} , {
						title : '商品名称' ,
						field : 'itemName' ,
						align : 'center' ,
						width : 100 ,
						formatter : function (value, rec) {
							return '无线路由器';
						}
						} , {
						title : '价格' ,
						field : 'price' ,
						align : 'center' ,
						width : 100 ,
						formatter : function (value, rec) {
							return '89';
						}
						} , {
						title : '折扣' ,
						field : 'discount' ,
						align : 'center' ,
						width : 100 ,
						formatter : function (value, rec) {
							return '0.9';
						}
						} , {
						title : '积分' ,
						field : 'points' ,
						align : 'center' ,
						width : 100 ,
						formatter : function (value, rec) {
							return '9';
						}
						} , {
						title : '地域' ,
						field : 'field' ,
						align : 'center' ,
						width : 100 ,
						formatter : function (value, rec) {
							return '华北地区';
						}
						} , {
						title : '分类' ,
						field : 'catalog' ,
						align : 'center' ,
						width : 100 ,
						formatter : function (value, rec) {
							return '满立减';
						}
						} , {
						field : 'opt' ,
						title : '操作' ,
						width : 150 ,
						align : 'center' ,
						formatter : function (value, rec) {
							return '<span style="color:red">Edit Delete</span>';
						}
						}
						]
					] ,
					toolbar : [
					{
					id : 'btnadd' ,
					text : '新建' ,
					iconCls : 'icon-add' ,
					handler : function () {
						$ ('#btnsave').linkbutton ('enable');
						$
								.ajax ({
								url : '${vix}/template/toolAction!addOaListItem.action?tag=addOaListItem3' ,
								cache : false ,
								success : function (html) {
									asyncbox.open ({
									modal : true ,
									width : 900 ,
									height : 400 ,
									title : "新建明细" ,
									content : html ,
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
					}
					} , {
					id : 'btncut' ,
					text : '删除' ,
					iconCls : 'icon-cut' ,
					handler : function () {
						$ ('#btnsave').linkbutton ('enable');
						alert ('cut');
					}
					} , '-' , {
					id : 'btnsave' ,
					text : '保存' ,
					disabled : true ,
					iconCls : 'icon-save' ,
					handler : function () {
						$ ('#btnsave').linkbutton ('disable');
						alert ('save');
					}
					}
					]
					});
	function opentt () {
		$ ('#tt')
				.datagrid (
						{
						title : '凭证列表' ,
						width : "auto" ,
						height : 550 ,
						nowrap : true ,
						autoRowHeight : false ,
						striped : true ,
						collapsible : true ,
						url : '${vix}/common/json_tests/datagrid_data.json' ,
						sortName : 'code' ,
						sortOrder : 'asc' ,
						remoteSort : false ,
						pagination : true ,
						rownumbers : true ,
						idField : 'code' ,
						frozenColumns : [
							[
							{
							field : 'ck' ,
							checkbox : true
							} , {
							title : '${vv:varView("vix_mdm_item")}编码' ,
							field : 'code' ,
							align : 'center' ,
							width : 150
							}
							]
						] ,
						columns : [
							[
							{
							title : '${vv:varView("vix_mdm_item")}名称' ,
							field : 'name' ,
							align : 'center' ,
							width : 150 ,
							fitColumns : true
							} , {
							title : '价格' ,
							field : 'col4' ,
							align : 'center' ,
							width : 150
							} , {
							title : '折扣' ,
							field : 'col4' ,
							align : 'center' ,
							width : 150
							} , {
							title : '积分' ,
							field : 'col4' ,
							align : 'center' ,
							width : 150
							} , {
							title : '地域' ,
							field : 'col4' ,
							align : 'center' ,
							width : 150
							} , {
							title : '分类' ,
							field : 'col4' ,
							align : 'center' ,
							width : 150
							} , {
							field : 'opt' ,
							title : '操作' ,
							width : 150 ,
							align : 'center' ,
							formatter : function (value, rec) {
								return '<span style="color:red">Edit Delete</span>';
							}
							}
							]
						] ,
						toolbar : [
						{
						id : 'btnadd' ,
						text : '新建' ,
						iconCls : 'icon-add' ,
						handler : function () {
							$ ('#btnsave').linkbutton ('enable');
							$
									.ajax ({
									url : '${vix}/template/toolAction!addOaListItem.action?tag=addOaListItem3' ,
									cache : false ,
									success : function (html) {
										asyncbox.open ({
										modal : true ,
										width : 900 ,
										height : 400 ,
										title : "新建明细" ,
										content : html ,
										btnsbar : $.btn.OKCANCEL
										});
									}
									});
						}
						} , {
						id : 'btncut' ,
						text : '删除' ,
						iconCls : 'icon-cut' ,
						handler : function () {
							$ ('#btnsave').linkbutton ('enable');
							alert ('cut');
						}
						} , '-' , {
						id : 'btnsave' ,
						text : '保存' ,
						disabled : true ,
						iconCls : 'icon-save' ,
						handler : function () {
							$ ('#btnsave').linkbutton ('disable');
							alert ('save');
						}
						}
						]
						});
	}
	var p = $ ('#test').datagrid ('getPager');
	$ (p).pagination ({
		onBeforeRefresh : function () {
			alert ('before refresh');
		}
	});
	function addOrderItem () {
		$
				.ajax ({
				url : '${vix}/inventory/inboundWarehouseAction!goSaveOrUpdateDetail.action' ,
				cache : false ,
				success : function (html) {
					asyncbox.open ({
					modal : true ,
					width : 760 ,
					height : 440 ,
					title : "入库单明细" ,
					html : html ,
					callback : function (action, returnValue) {
						if (action == 'ok') {
						}
					} ,
					btnsbar : $.btn.OKCANCEL
					});
				}
				});
	}
	function addOrderMemo () {
		$.ajax ({
		url : '${vix}/template/simpleGridAction!addOrderMemo.action' ,
		cache : false ,
		success : function (html) {
			asyncbox.open ({
			modal : true ,
			width : 1024 ,
			height : 540 ,
			title : "客户信息" ,
			html : html ,
			callback : function (action, returnValue) {
				if (action == 'ok') {
				}
			} ,
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function resize () {
		$ ('#test').datagrid ('resize', {
		width : 700 ,
		height : 400
		});
		$ ('#tt').datagrid ('resize', {
		width : 700 ,
		height : 400
		});
	}
	function getSelected () {
		var selected = $ ('#test').datagrid ('getSelected');
		if (selected) {
			alert (selected.code + ":" + selected.name + ":" + selected.addr + ":" + selected.col4);
		}
	}
	function getSelections () {
		var ids = [];
		var rows = $ ('#test').datagrid ('getSelections');
		for ( var i = 0; i < rows.length; i ++ ) {
			ids.push (rows [ i ].code);
		}
		alert (ids.join (':'));
	}
	function clearSelections () {
		$ ('#test').datagrid ('clearSelections');
	}
	function selectRow () {
		$ ('#test').datagrid ('selectRow', 2);
	}
	function selectRecord () {
		$ ('#test').datagrid ('selectRecord', '002');
	}
	function unselectRow () {
		$ ('#test').datagrid ('unselectRow', 2);
	}
	function mergeCells () {
		$ ('#test').datagrid ('mergeCells', {
		index : 2 ,
		field : 'addr' ,
		rowspan : 2 ,
		colspan : 2
		});
	}
	function chooseChkCustomer () {
		$.ajax ({
		url : '${vix}/template/simpleGridAction!goChooseChkSimpleGrid.action' ,
		cache : false ,
		success : function (html) {
			asyncbox.open ({
			modal : true ,
			width : 760 ,
			height : 440 ,
			title : "选择客户" ,
			html : html ,
			callback : function (action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						$ ("#customerChk").html (returnValue);
					}
					else {
						$ ("#customerChk").html ("");
						asyncbox.success ("请选择分类信息!", "提示信息");
					}
				}
			} ,
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function saveOrUpdateCustomer () {
		$.ajax ({
		url : '${vix}/template/simpleGridAction!saveOrUpdateCustomer.action' ,
		cache : false ,
		success : function (html) {
			asyncbox.open ({
			modal : true ,
			width : 760 ,
			height : 440 ,
			title : "客户信息" ,
			html : html ,
			callback : function (action, returnValue) {
				if (action == 'ok') {
				}
			} ,
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function chooseRadioCustomer () {
		$
				.ajax ({
				url : '${vix}/template/simpleGridAction!goChooseRadioSimpleGrid.action' ,
				cache : false ,
				success : function (html) {
					$ (".ab_outer .list td input[type='checkbox']").css (
							"margin-left", 10);
					$ (".ab_c .content").css ("margin-bottom", "0");
					$ ('.ab_c .content').parent ().css ('position', 'relative');
					asyncbox.open ({
					modal : true ,
					width : 1160 ,
					height : 600 ,
					title : "选择客户" ,
					html : html ,
					callback : function (action, returnValue) {
						if (action == 'ok') {
							if (returnValue != undefined) {
								$ ("#customerRadio").html (returnValue);
							}
							else {
								$ ("#customerRadio").html ("");
								asyncbox.success ("请选择分类信息!", "提示信息");
							}
						}
					} ,
					btnsbar : $.btn.OKCANCEL
					});
				}
				});
	}
	//提示
	if ($ ('input.sweet-tooltip').length) {
		$ ('input.sweet-tooltip')
				.focus (
						function () {
							tooltip = $ (this);
							tooltipText = tooltip.attr ('data-text-tooltip');
							tooltipClassName = 'tooltip';
							tooltipClass = '.' + tooltipClassName;
							if (tooltip.hasClass ('showed-tooltip'))
								return false;
							tooltip
									.addClass ('showed-tooltip')
									.after (
											'<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">' + tooltipText + '</div><div class="tooltip_b"></div></div>');
							tooltipPosTop = tooltip.position ().top - $ (
									tooltipClass).outerHeight () - 10;
							tooltipPosLeft = tooltip.position ().left;
							tooltipPosLeft = tooltipPosLeft - ( ($ (
									tooltipClass).outerWidth () / 2) - tooltip
									.outerWidth () / 2);
							$ (tooltipClass).css ({
							'left' : tooltipPosLeft ,
							'top' : tooltipPosTop
							}).animate ({
							'opacity' : '1' ,
							'marginTop' : '0'
							}, 500);
						}).focusout (function () {
					$ (tooltipClass).animate ({
					'opacity' : '0' ,
					'marginTop' : '-10px'
					}, 500, function () {
						$ (this).remove ();
						tooltip.removeClass ('showed-tooltip');
					});
				});
		JT_init ();
	}
	$ ("table tr").mouseover (function () {
		$ (this).addClass ("over");
	}).mouseout (function () {
		$ (this).removeClass ("over");
	});
	$ ("table tr:even").addClass ("alt");
	function saveOrUpdateOrder () {
		$.post ('${vix}/template/orderAction!saveOrUpdate.action', {
		'order.id' : $ ("#id").val () ,
		'order.code' : $ ("#code").val () ,
		'order.memo' : $ ("#memo").val ()
		}, function (result) {
			asyncbox.success (result, "提示信息", function (action) {
				loadContent ('${vix}/template/orderAction!goList.action');
			});
		});
	}
	function chooseProduct () {
		$
				.ajax ({
				url : '${vix}/template/productAction!goChooseProduct.action?tag=choose' ,
				cache : false ,
				success : function (html) {
					asyncbox
							.open ({
							modal : true ,
							width : 1000 ,
							height : 520 ,
							title : "选择商品" ,
							html : html ,
							callback : function (action, returnValue) {
								if (action == 'ok') {
									$
											.ajax ({
											url : '${vix}/template/orderAction!saveOrUpdateOrderItem.action?id=' + $ (
													"#id").val () + "&productIds=" + returnValue ,
											cache : false ,
											success : function (result) {
												asyncbox
														.success (
																result,
																"提示信息",
																function (action) {
																	pager (
																			"start",
																			"${vix}/template/orderAction!goOrderItemSingleList.action?id=" + $ (
																					"#id")
																					.val (),
																			'orderUpdate');
																});
											}
											});
								}
							} ,
							btnsbar : $.btn.OKCANCEL
							});
				}
				});
	}
	pager (
			"start",
			"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id=" + $ (
					"#id").val (), 'orderUpdate');
	function currentPager (tag) {
		pager (
				tag,
				"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id=" + $ (
						"#id").val (), 'orderUpdate');
	}
	$ ("#order").validationEngine ();
	if ($ (".ms").length > 0) {
		$ (".ms").hover (function () {
			$ (">ul", this).stop ().slideDown (100);
		}, function () {
			$ (">ul", this).stop ().slideUp (100);
		});
		$ (".ms ul li").hover (function () {
			$ (">a", this).addClass ("hover");
			$ (">ul", this).stop ().slideDown (100);
		}, function () {
			$ (">a", this).removeClass ("hover");
			$ (">ul", this).stop ().slideUp (100);
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
	</h2>
	<div id="breadCrumb" class="breadCrumb module">
		<ul>
			<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
			<li><a href="#"><s:text name="chain" /> </a></li>
			<li><a href="#"> <s:text name="cbm_points_management" />
			</a></li>
			<a href="#"> <s:text name="cbm_points_detail" />
			</a>
		</ul>
	</div>
</div>
<input type="hidden" id="id" name="id" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCustomer();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /> </a> <a href="#"><img width="22" height="22" alt="取消"
							src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a
						href="#"><img width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"> </a> <a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#"><img width="22" height="22"
							alt="打印" src="${vix}/common/img/dt_print.png"> </a> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"> </a>
					</span> <strong> <b><s:text name="cbm_points_detail" /> </b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong><s:text name="inventory_basicinfo" /> </strong>
								</dt>
								<dd style="display: block;">
									<p>
										<span><i>积分主题：</i><input name="subject" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> </span><span><i>积分类型：</i> <select name="priceType">
												<option value="0" selected="selected"></option>
												<option value="1">下拉菜单1</option>
												<option value="2">下拉菜单2</option>
												<option value="3">下拉菜单3</option>
										</select> </span><span><i>积分优先级：</i><input name="priority" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> </span>
									</p>
									<p>
										<span><i>编码：</i><input name="itemCode" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> </span><span><i>名称：</i><input name="itemName" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> </span><span><i>价格：</i><input name="price" type="text" size="20"
											class="validate[required] text-input sweet-tooltip" /> </span>
									</p>
									<p>
										<span><i>客户类型：</i><select name="customerType">
												<option value="0" selected="selected"></option>
												<option value="1">下拉菜单1</option>
												<option value="2">下拉菜单2</option>
												<option value="3">下拉菜单3</option>
										</select> </span><span><i>折扣：</i><input name="discount" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> </span><span><i>积分：</i><input name="points" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> </span>
									</p>
									<p>
										<span><i>价格区间类型：</i><select name="pricePeroidType">
												<option value="0" selected="selected"></option>
												<option value="1">下拉菜单1</option>
												<option value="2">下拉菜单2</option>
												<option value="3">下拉菜单3</option>
										</select> </span><i>价格区间-开始：</i><input name="startPrice" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> 截止：<input name="endPrice" type="text" size="20" class="validate[required] text-input sweet-tooltip" />
									</p>
									<p>
										<span><i>地域：</i><input name="field" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> </span><i>有效时间：</i><input id="time3" name="ufts" type="text" /><img onclick="showTime('time3','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="10" height="22" align="middle"> 至 <input id="time3" name="ufts"
											type="text" /><img onclick="showTime('time3','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="10" height="22" align="middle">
									</p>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<%-- <div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a
								onclick="javascript:tab(3,1,'a',event)"><img
									src="${vix}/common/img/mail.png" alt="" />采购订单</a>
							</li>
							<li class="current"><a
								onclick="javascript:$('#a2').attr('style','');tab(3,2,'a',event);"><img
									src="${vix}/common/img/mail.png" alt="" />积分规则</a></li>
							<li><a
								onclick="javascript:$('#a3').attr('style','');tab(3,3,'a',event); opentt();"><img
									src="${vix}/common/img/mail.png" alt="" />凭证</a></li>
						</ul>
					</div>
					<div id="a2" style="position: relative; z-index: 1; background: #FFF;">
						<div class="right_btn">
							<span><a href="#" onclick="addOrderItem();"><img
									src="${vix}/common/img/dt_line_add.png" alt="" /> </a> </span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_edit.png" alt="" /> </a> </span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_view.png" alt="" /> </a> </span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /> </a> </span>
						</div>
						<table id="test"></table>
					</div>
					<div id="a3" style="display: none;">
						<div class="right_btn">
							<span><a href="javascript:void(0);" onclick="saveOrUpdate();"><img
									src="img/address_book.png" alt="" /> </a> </span> <span><a href="javascript:void(0);"
								onclick="saveOrUpdate();"><img src="img/address_book.png" alt="" /> </a> </span> <span><a
								href="#"><img src="img/address_book.png" alt="" /> </a> </span> <span><a href="#"><img
									src="img/address_book.png" alt="" /> </a> </span> <span><a href="#"><img
									src="img/address_book.png" alt="" /> </a> </span> <span><a href="#"><img
									src="img/address_book.png" alt="" /> </a> </span>
						</div>
						<table id="tt"></table>
						<!-- <div class="pagelist drop">
							<ul>
								<li class="tp"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul>
								</li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a> </span> <span><a
									class="previous"></a> </span> <em>(1-20 到 29)</em> <span><a
									class="next"></a> </span> <span><a class="end"></a> </span>
							</div>
						</div> -->
						<!-- <div class="table_10">
							<table class="list">
								<tr>
									<th width="50">
										<div class="list_check">
											<div class="drop">
												<label><input name="" type="checkbox" value="" /> </label>
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
									<th>采购订单号<a href="#"><img src="img/arrow.gif" alt="" />
									</a></th>
									<th>采购类型编码<a href="#"><img src="img/arrow.gif" alt="" />
									</a></th>
									<th>业务员编码<a href="#"><img src="img/arrow.gif" alt="" />
									</a></th>
									<th>供应商编码<a href="#"><img src="img/arrow.gif" alt="" />
									</a></th>
									<th>采购到货单号<a href="#"><img src="img/arrow.gif" alt="" />
									</a></th>
									<th>&nbsp;</th>
								</tr>
								<tr>
									<td><input name="" type="checkbox" value="" /></td>
									<td><img src="img/icon_edit.png" alt="" title="提示：123"
										class="hand" /></td>
									<td><b>Tortoise Corp</b></td>
									<td>Sunnyvale</td>
									<td>USA</td>
									<td>(989)247-068392</td>
									<td><em>Sally Bronsen</em></td>
									<td>
										<div class="untitled">
											<span><img src="img/icon_untitled.png" alt="" /> </span>
											<div class="popup">
												<strong> <i class="close"><a href="#"></a> </i> <i><a
														href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
												</strong>
												<p>Launched in January 1998, People's Daily Online is a
													website built by People's Daily, the official newspaper of
													the Communist Party of China. Containing</p>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div> -->
						<!-- <div class="pagelist drop">
							<ul>
								<li class="ed"><a href="#">选项</a>
									<ul>
										<li><a href="#">删除</a></li>
										<li><a href="#">邮件</a></li>
										<li><a href="#">批量更新</a></li>
										<li><a href="#">合并</a></li>
										<li><a href="#">添加到目标列表</a></li>
										<li><a href="#">导出</a></li>
									</ul>
								</li>
							</ul>
							<strong>选中:0</strong>
							<div>
								<span><a class="start"></a> </span> <span><a
									class="previous"></a> </span> <em>(1-20 到 29)</em> <span><a
									class="next"></a> </span> <span><a class="end"></a> </span>
							</div>
						</div> -->
						<!-- <div class="addtable lineh30">
							<div class="addtable_t">
								<strong>表格输入</strong><b></b>
							</div>
							<table class="addtable_c">
							</table>
						</div> -->
					</div>
				</div> --%>
			</dl>
		</div>
		<!--oder-->
		<div class="sub_menu sub_menu_bot">
			<div class="drop">
				<p>
					<a href="#" onclick="saveOrUpdateOrder()"><span>保存</span> </a> <a href="#" onclick="loadContent('${vix}/template/orderAction!goList.action');"><span>返回</span> </a> <a href="#"><span>编辑</span> </a> <a href="#"><span>复制</span> </a> <a href="#"><span>删除</span> </a> <a href="#"><span>关闭并新建</span> </a> <a href="#"><span>关闭</span> </a> <a href="#"
						id="text"><span>弹出窗口测试</span> </a>
				</p>
				<ul>
					<li><a href="#"><span>帮助</span> </a>
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