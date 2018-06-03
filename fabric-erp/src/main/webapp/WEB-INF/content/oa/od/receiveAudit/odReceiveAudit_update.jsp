<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>
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
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	/** 初始化easyui的grid */
	var products = [
	    {productid:'FI-SW-01',name:'Koi'},
	    {productid:'K9-DL-01',name:'Dalmation'},
	    {productid:'RP-SN-01',name:'Rattlesnake'},
	    {productid:'RP-LI-02',name:'Iguana'},
	    {productid:'FL-DSH-01',name:'Manx'},
	    {productid:'FL-DLH-02',name:'Persian'},
	    {productid:'AV-CB-01',name:'Amazon Parrot'}
	];
	function productFormatter(value){
		for(var i=0; i<products.length; i++){
			if (products[i].productid == value) return products[i].name;
		}
		return value;
	}
	
	/** 添加订单子项 */
	function addOrderItem(){
		/**js 操作dom添加html代码段实现行添加*/
		var orderItem = $("#orderItemTemplate").html();
		$("#orderItemTable").after(orderItem);
		$("#orderItem tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
		$("#orderItem tr:even").addClass("alt");
	}

	/** 弹出窗口示例 */
	function addOrderMemo(){
		loadContent('${vix}/oa/od/odReceiveAuditAction!goList.action');
	}

	/** 选择多选对象示例 */
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
	/** 弹出窗口示例 */
	function windowTest(){
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
	
	function addWindowOrderItem(){
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
								/** 拼接html并用jquery获取弹出窗口中的数据，添加到子项列表中 */
								 var html = "<tr><td><input type='checkbox'/></td>" +
											"<td><input type='hidden' id='orderItemId'/><img src='${vix}/common/img/icon_edit.png' title='提示：123' class='hand' /></td>" +
											"<td><b>&nbsp;<input id='orderItemName' type='text' value='"+$("#name").val()+"'/></b></td>" +
											"<td><input id='orderItemPrice' type='text' value='"+$("#memo").val() +"'/></td>" +
											"<td><input id='orderItemCount' type='text' value='1'/></td>" +
								  			"</tr>";
								$("#orderItemTable").after(html);
								$("#orderItem tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
								$("#orderItem tr:even").addClass("alt");
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	/** 选择单选对象示例 */
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
/** 保存订单 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	/** 获取订单子项内的新增数据，拼接未字符串提交到后台 */
	 $("#orderItem tr").each(function(trindex,tritem){
		 if(trindex > 0){
			 $(tritem).find("input").each(function(inputIndex,inputItem){
	             if(inputIndex == 1){
	            	 orderItemStr += "id:"+$(inputItem).val();
	             }
	             if(inputIndex == 2){
	            	 orderItemStr += ",name:"+$(inputItem).val();
	             }
	             if(inputIndex == 3){
	            	 orderItemStr += ",price:"+$(inputItem).val();
	             }
	             if(inputIndex == 4){
	            	 orderItemStr += ",count:"+$(inputItem).val()+";";
	             }
	         }); 
		 }
     });
	$.post('${vix}/template/orderAction!saveOrUpdate.action',
		{'order.id':$("#id").val(),
		  'order.code':$("#code").val(),
		  'order.memo':$("#memo").val(),
		  'orderItemStr':orderItemStr
		},
		function(result){
			asyncbox.success(result,"提示信息",function(action){
				loadContent('${vix}/template/orderAction!goList.action?menuId=menuOrder');
			});
		}
	 );
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
				<li><a href="#"><img src="${vix}/common/img/oa_approval.png" alt="" /> <s:text name="协同办公" /></a></li>
				<li><a href="#"><s:text name="公文管理" /></a></li>
				<li><a href="#"><s:text name="收发文件" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/odReceiveAuditAction!goList.action?pageNo=${pageNo}');"><s:text name="领导批阅" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/odReceiveAuditAction!goList.action?pageNo=${pageNo}');"><s:text name="收文批阅" /></a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${order.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="addOrderMemo();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/oa/odReceiveAuditAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="收文批阅" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr height="40">
								<td align="right">发文字号:&nbsp;</td>
								<td><input type="text" id="posName" name="entityForm.posName" value="（2013）319办公公文" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
								<td align="right">时间:&nbsp;</td>
								<td><input type="text" id="posName" name="entityForm.posName" value="${entity.posName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
							</tr>
							<tr height="40">
								<td align="right">发文标题:&nbsp;</td>
								<td><input type="text" id="posName" name="entityForm.posName" value="公司文件" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
								<td align="right">部门:&nbsp;</td>
								<td><input type="text" id="posName" name="entityForm.posName" value="${entity.posName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
							</tr>

							<tr height="40">
								<td align="right">密级:&nbsp;</td>
								<td><input type="text" id="posName" name="entityForm.posName" value="一级" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
								<td align="right">主题词:&nbsp;</td>
								<td><input type="text" id="code" name="entityForm.code" value="" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
							</tr>
							<tr height="40">
								<td align="right">主办单位:&nbsp;</td>
								<td><input type="text" id="posName" name="entityForm.posName" value="${entity.posName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
								<td align="right">缓急程度:&nbsp;</td>
								<td><input type="text" id="code" name="entityForm.code" value="紧急" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
							</tr>
							<tr height="40">
								<td align="right">主送:&nbsp;</td>
								<td><input type="text" id="posName" name="entityForm.posName" value="${entity.posName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
								<td align="right">抄送:&nbsp;</td>
								<td><input type="text" id="code" name="entityForm.code" value="${entity.code}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
							</tr>
							<tr height="40">
								<td align="right">备注说明:&nbsp;</td>
								<td colspan="3"><textarea id="memo" name="entityForm.memo" class="example" rows="2" style="width: 823px; height: 74px;">${entity.memo }</textarea></td>
							</tr>
							<tr>
								<th align="right">内容：</th>
								<td colspan="3"><textarea id="content" name="content">${trends.content}</textarea> <script type="text/javascript">
									 var contents = KindEditor.create('textarea[name="content"]',
									{basePath:'${vix}/plugin/KindEditor/',
										width:825,
										height:281,
										cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
										uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
										fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
										allowFileManager : true 
										}
									 );
							 </script></td>
						</table>
					</div>

				</dd>
		</div>
		<!--ode
	<div class="sub_menu sub_menu_bot">
		<div class="drop">
			<p>
				<a href="#" onclick="saveOrUpdateOrder()"><span>保存</span></a>
				<a href="#" onclick="loadContent('${vix}/template/orderAction!goList.action');"><span>返回</span></a>
				<a href="#"><span>编辑</span></a>
				<a href="#"><span>复制</span></a>
				<a href="#"><span>删除</span></a>
				<a href="#"><span>关闭并新建</span></a>
				<a href="#"><span>关闭</span></a>
				<a href="#" id="text"><span>弹出窗口测试</span></a>
			</p>
			<ul>
				<li>
					<a href="#"><span>帮助</span></a>
					<ul>
					<li><a href="#">帮助</a></li>
					<li><a href="#">帮助</a></li>
					<li><a href="#">帮助</a></li>
					<li><a href="#">帮助</a></li>
					</ul>
				</li>
			</ul>			
		</div>
	</div>-->
		<!--submenu-->
	</form>
</div>
<!-- content -->
<script type="text/javascript">
$(function(){
	$.fn.fix = function(options){
		var defaults = {
			'advance' : 0,
			'top' : 0
		}
		options = $.extend(defaults, options);
		return this.each(function(){
			var $_this = $(this);
			$_this.wrap('<div></div>');
			var wp = $_this.parent('div');
			wp.height(wp.height());
			var ofst = wp.offset();
			
			if(!$_this.is(':visible') && $(window).scrollTop() + options.advance > $_this.offset().top){
				$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
			}
			$(window).scroll(function(){
				if(!$_this.is(':visible')){
					return ;
				}
				
				if($(window).scrollTop() + options.advance > wp.offset().top){
					$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
				}else{
					$_this.css({'position':'static','z-index':'auto','top': 'auto','width':'auto'});
				}
			});
		});
	}
	$('#a1 .right_btn,#a3 .right_btn').fix({'advance':38,'top':38});
	$('#a5 .right_btn').fix({'advance':43,'top':43});
	
	$('.adddom').click(function(){
		$('#addtodo').css({'left':$(this).offset().left-320,'top':$(this).offset().top-56});
		$('#addtodoid').show();
	});
	$('#sybtn').click(function(){
		$('#addtodo2').css({'left':$(this).offset().left-320,'top':$(this).offset().top-50});
		$('#addtodoid2').show();
	});
	$(document).click(function(e){
		var $_this = $(e.target);
		if(!($_this.hasClass('adddom') || $_this.is('#addtodo') || $_this.parents('#addtodo').length > 0)){
			$('#addtodoid').hide();
		}
		if(!($_this.attr('id') == 'sybtn' || $_this.is('#addtodo2') || $_this.parents('#addtodo2').length > 0)){
			$('#addtodoid2').hide();
		}
	});
	
	$('#numBtn a').click(function(){
		if($(this).attr('data-rel') != '-'){
			if($('#xhnum').text().indexOf('.') != -1 && ($(this).attr('data-rel') == '.' || $('#xhnum').text().slice($('#xhnum').text().indexOf('.')).length > 2)){
				return false;
			}
			$('#xhnum').text($('#xhnum').text()+$(this).attr('data-rel'));
		}else{
			$('#xhnum').text($('#xhnum').text().slice(0,$('#xhnum').text().length-1));
		}
		$('#xhnum').scrollLeft(999999);
	});
	
	$('#enBtnList a').click(function(){
		console.log($('#sylist ul li').index($('#sylist ul li[data-rel='+$(this).attr('data-rel')+']')));
		$('#sylist').scrollTop($('#sylist ul li').index($('#sylist ul li[data-rel='+$(this).attr('data-rel')+']'))*20+28);
	});
	
	tabs('#a4 .commont_tab li','#a4 .commont_ct','current');
});
function tabs(title,content,style){
	$(title).click(function(){
		$(title).removeClass(style);
		$(this).addClass(style);
		$(content).hide();
		$(content+':eq('+$(title).index($(this))+')').show();
	});
}
</script>
<div id="addtodo">
	<form onsubmit="$('#addtodoid').hide();return false;" method="POST" class="formParam">
		<div id="addtodoid" class="txtSmall qcdiv w320">
			<div class="zt">
				<img border="0" class="zprarrow" src="img/spacer.gif">
			</div>
			<table width="100%" class="qulbl">
				<tbody>
					<tr>
						<td valign="top" class="pL10 pb1"><div class="fl pt5 requiredField">型号</div></td>
					</tr>
					<tr>
						<td class="pL10 pb5"><div class="labelbox clearfix">
								<label><input name="" type="checkbox" value="" /> 型号1</label><label><input name="" type="checkbox" value="" /> 型号1</label><label><input name="" type="checkbox" value="" /> 型号1</label><label><input name="" type="checkbox" value="" /> 型号1</label><label><input name="" type="checkbox" value="" /> 型号1</label><label><input name=""
									type="checkbox" value="" /> 型号1</label><label><input name="" type="checkbox" value="" /> 型号1</label><label><input name="" type="checkbox" value="" /> 型号1</label>
							</div></td>
					</tr>
					<tr>
						<td valign="top" class="pL10 pt10 pb1">输入数量</td>
					</tr>
					<tr>
						<td valign="top" class="pL10 pt10 pb1"><div>
								<div id="xhnum"></div>
							</div></td>
					</tr>
					<tr>
						<td valign="top" class="pL10 pt10 pb1"><ul id="numBtn" class="btnlist clearfix">
								<li><a href="javascript:;" data-rel="1">1</a></li>
								<li><a href="javascript:;" data-rel="2">2</a></li>
								<li><a href="javascript:;" data-rel="3">3</a></li>
								<li><a href="javascript:;" data-rel="4">4</a></li>
								<li><a href="javascript:;" data-rel="5">5</a></li>
								<li><a href="javascript:;" data-rel="6">6</a></li>
								<li><a href="javascript:;" data-rel="7">7</a></li>
								<li><a href="javascript:;" data-rel="8">8</a></li>
								<li><a href="javascript:;" data-rel="9">9</a></li>
								<li><a href="javascript:;" data-rel="0">0</a></li>
								<li><a href="javascript:;" data-rel=".">.</a></li>
								<li><a href="javascript:;" data-rel="-">←</a></li>
							</ul></td>
					</tr>
					<tr>
						<td valign="top" class="pL10 pt10 pb1 tc"><input type="submit" class="btn" value="添加" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div id="addtodo2">
	<div id="addtodoid2" class="txtSmall qcdiv w320">
		<div class="zt">
			<img border="0" class="zprarrow" src="img/spacer.gif">
		</div>
		<table width="100%" class="qulbl">
			<tbody>
				<tr>
					<td valign="top" class="pL10 pb1"><div class="fl pt5 requiredField">索引</div></td>
				</tr>
				<tr>
					<td valign="top" class="pL10 pt10 pb1"><ul id="enBtnList" class="btnlist list6 clearfix">
							<li><a href="javascript:;" data-rel="A">A</a></li>
							<li><a href="javascript:;" data-rel="B">B</a></li>
							<li><a href="javascript:;" data-rel="C">C</a></li>
							<li><a href="javascript:;" data-rel="D">D</a></li>
							<li><a href="javascript:;" data-rel="E">E</a></li>
							<li><a href="javascript:;" data-rel="F">F</a></li>
							<li><a href="javascript:;" data-rel="G">G</a></li>
							<li><a href="javascript:;" data-rel="H">H</a></li>
							<li><a href="javascript:;" data-rel="I">I</a></li>
							<li><a href="javascript:;" data-rel="J">J</a></li>
							<li><a href="javascript:;" data-rel="K">K</a></li>
							<li><a href="javascript:;" data-rel="L">L</a></li>
							<li><a href="javascript:;" data-rel="M">M</a></li>
							<li><a href="javascript:;" data-rel="N">N</a></li>
							<li><a href="javascript:;" data-rel="O">O</a></li>
							<li><a href="javascript:;" data-rel="P">P</a></li>
							<li><a href="javascript:;" data-rel="Q">Q</a></li>
							<li><a href="javascript:;" data-rel="R">R</a></li>
							<li><a href="javascript:;" data-rel="S">S</a></li>
							<li><a href="javascript:;" data-rel="T">T</a></li>
							<li><a href="javascript:;" data-rel="U">U</a></li>
							<li><a href="javascript:;" data-rel="V">V</a></li>
							<li><a href="javascript:;" data-rel="W">W</a></li>
							<li><a href="javascript:;" data-rel="X">X</a></li>
							<li><a href="javascript:;" data-rel="Y">Y</a></li>
							<li><a href="javascript:;" data-rel="Z">Z</a></li>
						</ul></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>