<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- <link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet"/>
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet"/>
<link href="${vix}/common/css/newservice.css" rel="stylesheet"/>
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script> --%>
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
		</i> <strong><img alt="" src="img/drp_channel.png">新闻</strong>
	</h2>
</div>
<div class="content">
	<form id="newsForm">

		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateNews();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <%-- <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png"/></a> --%> <a onclick="loadContent('${vix}/common/model/newsAction!goList.action');" href="#"><img
							width="22" height="22" alt="取消" src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="打印" src="${vix}/common/img/dt_print.png"></a> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a>
					</span> <strong> <b>编辑新闻</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<input type="hidden" name="entityForm.id" value="${entity.id}" />
						<table class="table-padding020">
							<!-- <tr height="50">
								<td class="tr"><select name="">
										<option value="">行政</option>
										<option value="">行政</option>
								</select>
								</td>
								<td><input name="" type="text" class="ipt"
									style="width: 440px" />
								</td>
							</tr> -->
							<tr>
								<td class="tr">类型：</td>
								<td><s:radio list="#{0:\"文本\",1:\"图片\"}" name="entityForm.newsType" value="%{entity.newsType}" theme="simple"></s:radio></td>
							</tr>
							<tr>
								<td class="tr">标题：</td>
								<td><input name="entityForm.title" value="${entity.title}" type="text" class="ipt" maxlength="30" style="width: 350px;" /> （最多输入30个字）</td>
							</tr>
							<tr>
								<td class="tr">附标题：</td>
								<td><input name="entityForm.subtitle" value="${entity.subtitle}" type="text" class="ipt" maxlength="30" style="width: 350px;" /> （最多输入30个字）</td>
							</tr>
							<tr>
								<td class="tr">关键词：</td>
								<td><input name="entityForm.keywords" value="${entity.keywords}" type="text" class="ipt" maxlength="30" style="width: 350px;" /> （最多输入30个字）</td>
							</tr>
							<tr>
								<td class="tr">新闻来源：</td>
								<td><input name="entityForm.sourceFrom" value="${entity.sourceFrom}" type="text" class="ipt" maxlength="30" style="width: 350px;" /></td>
							</tr>
							<tr>
								<td class="tr">新闻地址：</td>
								<td><input name="entityForm.sourceFromUrl" value="${entity.sourceFromUrl}" type="text" class="ipt" maxlength="30" style="width: 350px;" /></td>
							</tr>
							<!-- <tr>
								<td class="tr">附件与权限：</td>
								<td><label> <input name="" type="checkbox" value=""
										class="checkbox" /> 允许下载office附件</label> <label> <input
										name="" type="checkbox" value="" class="checkbox" />
										允许打印office附件</label> <span class="gray">(都不选中，则只能阅读内容)</span></td>
							</tr> -->
							<tr>
								<th align="right">新闻内容：</th>
								<td colspan="3"><textarea id="newsContentView"></textarea> <input id="newsContentHidden" type="hidden" name="entityForm.content" value="${entity.content}"> <%--<textarea id="bulletinContent" name="entityForm.content"></textarea> --%> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script
										type="text/javascript">
											var newsEditor = KindEditor
													.create(
															'#newsContentView',
															{
																basePath : '${vix}/plugin/KindEditor/',
																width : 600,
																height : 200,
																cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
																uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
																fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
																allowFileManager : true
															});
											newsEditor.html("${entity.content}");
										</script></td>
							</tr>
						</table>

					</div>
				</dd>
			</dl>
		</div>
		<!--oder  textarea[name="content"]-->
		<div class="sub_menu sub_menu_bot">
			<div class="drop">
				<p>
					<a href="#" onclick="saveOrUpdateNews()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/common/model/newsAction!goList.action');"><span>返回</span></a>
					<!-- <a href="#"><span>编辑</span></a>
				<a href="#"><span>复制</span></a>
				<a href="#"><span>删除</span></a>
				<a href="#"><span>关闭并新建</span></a>
				<a href="#"><span>关闭</span></a>
				<a href="#" id="text"><span>弹出窗口测试</span></a> -->
				</p>
			</div>
		</div>
		<!--submenu-->
	</form>
</div>
<script src="${vix}/common/js/jtip.js" type="text/javascript"></script>
<script type="text/javascript">
<!--
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
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop }).animate({'opacity':'1', 'marginTop':'0'}, 500);
	}).focusout(function() {
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
}
//-->
</script>
