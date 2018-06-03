<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/core/css/css.css" rel="stylesheet">
<script src="${vix}/common/js/newservice.js" type="text/javascript"></script>


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
	$ ("#order").validationEngine ();
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
	$ ('#tt').treegrid ({
	rownumbers : true ,
	animate : true ,
	collapsible : true ,
	fitColumns : true ,
	showFooter : true ,
	singleSelect : false ,
	url : '${vix}/common/json_tests/treegrid_data3.json' ,
	idField : 'id' ,
	treeField : 'code' ,
	frozenColumns : [
		[
		{
		field : 'ck' ,
		height : 60 ,
		checkbox : true
		} , {
		title : '订单编码' ,
		field : 'code' ,
		width : 150 ,
		editor : true
		} , {
		field : 'name' ,
		title : '${vv:varView("vix_mdm_item")}编码' ,
		width : 150 ,
		align : 'right' ,
		editor : true
		}
		]
	] ,
	columns : [
		[
		{
		field : 'name1' ,
		title : '${vv:varView("vix_mdm_item")}名称' ,
		width : 150 ,
		align : 'right' ,
		editor : true
		} , {
		field : 'addr' ,
		title : '数量' ,
		width : 150 ,
		align : 'right' ,
		editor : true
		} , {
		field : 'addr1' ,
		title : '交付日期' ,
		width : 150 ,
		align : 'right' ,
		editor : true
		}
		]
	]
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />生产管理</a></li>
				<li><a href="#">主生产计划</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mm/planSourceAction!goList.action');">MPS计划来源</a></li>
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
						</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a><a href="#" onclick="loadContent('${vix}/mm/planSourceAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>订单</b>
					</strong>
				</dt>
				<dd class="clearfix">
					<%-- <div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text
												name="calculator" /></a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text
												name="calculator" /></a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text
												name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;"></dd>
							</dl>
						</div>
					</div> --%>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">

					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div class="gridbox">
							<div class="right_btn nomargin">
								<span><a href="javascript:void(0);" onclick="editNode('tt')" title="编辑"><img alt="" src="img/user.png"></a></span> <span><a href="javascript:void(0);" onclick="saveNode('tt')" title="保存"><img alt="" src="img/wrench_screwdriver.png"></a></span> <span><a href="javascript:void(0);" onclick="cancelNode('tt')" title="取消"><img
										alt="" src="img/address_book.png"></a></span>
							</div>
							<div class="tg_w_auto">
								<table id="tt" class="easyui-treegrid" style="width: 1200px; height: 460px" url="json_tests/treegrid_data3.json" idField="id" treeField="code" pagination="true" fitColumns="true">
								</table>
							</div>
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