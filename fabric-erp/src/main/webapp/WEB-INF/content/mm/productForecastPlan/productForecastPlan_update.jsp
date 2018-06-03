<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript">
	$ (".newvoucher dt b").click (function (){
		$ (this).toggleClass ("downup");
		$ (this).parent ("dt").next ("dd").toggle ();
	});
	$ (".order_table input[type='text']").focusin (function (){
		$ (this).css ({
		'border' : '1px solid #f00' ,
		'background' : '#f5f5f5'
		});
	});
	$ (".order_table  input[type='text']").focusout (function (){
		$ (this).css ({
		'border' : '1px solid #ccc' ,
		'background' : '#fff'
		});
	});
	$ ("table tr").mouseover (function (){
		$ (this).addClass ("over");
	}).mouseout (function (){
		$ (this).removeClass ("over");
	});
	$ ("table tr:even").addClass ("alt");
	function saveOrUpdateOrder (){
		$.post ('${vix}/template/orderAction!saveOrUpdate.action' , {
		'order.id' : $ ("#id").val () ,
		'order.code' : $ ("#code").val () ,
		'order.memo' : $ ("#memo").val ()
		} , function (result){
			asyncbox.success (result , "提示信息" , function (action){
				loadContent ('${vix}/template/orderAction!goList.action');
			});
		});
	}
	if ($ (".ms").length > 0){
		$ (".ms").hover (function (){
			$ (">ul" , this).stop ().slideDown (100);
		} , function (){
			$ (">ul" , this).stop ().slideUp (100);
		});
		$ (".ms ul li").hover (function (){
			$ (">a" , this).addClass ("hover");
			$ (">ul" , this).stop ().slideDown (100);
		} , function (){
			$ (">a" , this).removeClass ("hover");
			$ (">ul" , this).stop ().slideUp (100);
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />生产管理</a></li>
				<li><a href="#">生产规划</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mm/productForecastPlanAction!goList.action');">生产预测计划</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCustomer();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消"
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
						</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a><a href="#" onclick="loadContent('${vix}/mm/productForecastPlanAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增生产预测计划</b>
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
											<td align="right">名称：</td>
											<td><input id="name" name="BO_SPR_CMNM_name" type="text" size="30" /></td>
											<td align="right">简称：</td>
											<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">法人：</td>
											<td><input id="artificialPerson" name="BO_SPR_CMNM_artificialPerson" type="text" size="30" /></td>
											<td align="right">类型：</td>
											<td><select name="BO_SPR_CMNM_type" style="width: 50"><option>请选择</option></select></td>
										</tr>
										<tr>
											<td align="right">拼音：</td>
											<td><input id="pinYin" name="BO_SPR_CMNM_pinYin" type="text" size="30" /></td>
											<td align="right">所属分类：</td>
											<td><select name="BO_SPR_CMNM_catalog" style="width: 50"><option>请选择</option></select></td>
										</tr>
										<tr>
											<td align="right">分类：</td>
											<td><select name="BO_SPR_CMNM_catalogs" style="width: 50"><option>请选择</option></select></td>
											<td align="right">所属行业：</td>
											<td><input id="industry" name="BO_SPR_CMNM_industry" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">员工人数：</td>
											<td><input id="employeeCounts" name="BO_SPR_CMNM_employeeCounts" type="text" size="30" /></td>
											<td align="right">母公司代码：</td>
											<td><input id="parentCompanyCode" name="BO_SPR_CMNM_parentCompanyCode" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">母公司：</td>
											<td><input id="parentCompanyName" name="BO_SPR_CMNM_parentCompanyName" type="text" size="30" /></td>
											<td align="right">所属地区：</td>
											<td><input id="region" name="BO_SPR_CMNM_region" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><select name="BO_SPR_CMNM_currency" style="width: 50"><option>请选择</option></select></td>
											<td align="right">注册资金：</td>
											<td><input id="registeredCapital" name="BO_SPR_CMNM_registeredCapital" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">等级：</td>
											<td colspan="3"><select name="BO_SPR_CMNM_classIfy" style="width: 50"><option>请选择</option></select></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />产能明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/inventory/allocateTransferAction!getWimTransvouchItemJson.action?id=${wimTransvouch.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'cinvcode',width:100,align:'center',editor:'text'">名称</th>
										<th data-options="field:'tvquantity',width:100,align:'center',editor:'text'">类型</th>
										<th data-options="field:'tvacost',width:100,align:'center',editor:'numberbox'">数量</th>
										<th data-options="field:'tvaprice',width:100,align:'center',editor:'numberbox'">正班产能</th>
										<th data-options="field:'tvpcost',width:100,align:'center',editor:'numberbox'">加班产能</th>
										<th data-options="field:'salecost',width:100,align:'center',editor:'numberbox'">总产能</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
							</div>
							<script type="text/javascript">
								$ ('#dlAddress').datagrid ();
								var editIndexDlAddress = undefined;
								function endDlEditing (){
									if (editIndexDlAddress == undefined){
										return true;
									}
									if ($ ('#dlAddress').datagrid ('validateRow' , editIndexDlAddress)){
										$ ('#dlAddress').datagrid ('endEdit' , editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									}else{
										return false;
									}
								}
								function onDlClickRow (index){
									if (editIndexDlAddress != index){
										if (endDlEditing ()){
											$ ('#dlAddress').datagrid ('selectRow' , index).datagrid ('beginEdit' , index);
											editIndexDlAddress = index;
										}else{
											$ ('#dlAddress').datagrid ('selectRow' , editIndexDlAddress);
										}
									}
								}
								function appendDlAddress (){
									if (endDlEditing ()){
										$ ('#dlAddress').datagrid ('appendRow' , {
											status : 'P'
										});
										editIndexDlAddress = $ ('#dlAddress').datagrid ('getRows').length - 1;
										$ ('#dlAddress').datagrid ('selectRow' , editIndexDlAddress).datagrid ('beginEdit' , editIndexDlAddress);
									}
								}
								function removeDlAddress (){
									if (editIndexDlAddress == undefined){
										return;
									}
									$ ('#dlAddress').datagrid ('cancelEdit' , editIndexDlAddress).datagrid ('deleteRow' , editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress (){
									if (endDlEditing ()){
										$ ('#dlAddress').datagrid ('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ (function (){
		$.fn.fix = function (options){
			var defaults = {
			'advance' : 0 ,
			'top' : 0
			};
			options = $.extend (defaults , options);
			return this.each (function (){
				var $_this = $ (this);
				$_this.wrap ('<div></div>');
				var wp = $_this.parent ('div');
				wp.height (wp.height ());
				var ofst = wp.offset ();
				if ( ! $_this.is (':visible') && $ (window).scrollTop () + options.advance > $_this.offset ().top){
					$_this.css ({
					'position' : 'fixed' ,
					'z-index' : 9999 ,
					'top' : options.top ,
					'width' : $_this.width ()
					});
				}
				$ (window).scroll (function (){
					if ( ! $_this.is (':visible')){
						return;
					}
					if ($ (window).scrollTop () + options.advance > wp.offset ().top){
						$_this.css ({
						'position' : 'fixed' ,
						'z-index' : 9999 ,
						'top' : options.top ,
						'width' : $_this.width ()
						});
					}else{
						$_this.css ({
						'position' : 'static' ,
						'z-index' : 'auto' ,
						'top' : 'auto' ,
						'width' : 'auto'
						});
					}
				});
			});
		}
		$ ('#a1 .right_btn,#a3 .right_btn').fix ({
		'advance' : 38 ,
		'top' : 38
		});
	});
	function tabs (title , content , style){
		$ (title).click (function (){
			$ (title).removeClass (style);
			$ (this).addClass (style);
			$ (content).hide ();
			$ (content + ':eq(' + $ (title).index ($ (this)) + ')').show ();
		});
	}
	$ (window).scroll (function (){
		if ($ ('#orderTitleFixd').parent ('dl').offset ().top - 43 < $ (window).scrollTop ()){
			if ( ! $ ('#orderTitleFixd').hasClass ('fixed')){
				$ ('#orderTitleFixd').addClass ('fixed');
				$ ('#orderTitleFixd').parent ('dl').css ('padding-top' , 30);
			}
		}else{
			$ ('#orderTitleFixd').removeClass ('fixed');
			$ ('#orderTitleFixd').parent ('dl').css ('padding-top' , 0);
		}
	});
</script>