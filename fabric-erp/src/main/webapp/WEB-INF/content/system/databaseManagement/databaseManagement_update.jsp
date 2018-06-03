<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	/** input 获取焦点input效果绑定  */
    $(".order_table input[type='text']").focusin(function() {
	    $(this).css({
	    'border' : '1px solid #f00',
	    'background' : '#f5f5f5'
	    });
    });
    $(".order_table  input[type='text']").focusout(function() {
	    $(this).css({
	    'border' : '1px solid #ccc',
	    'background' : '#fff'
	    });
    });
    function saveOrUpdateDatabaseRecord() {
	    if ($('#order').validationEngine('validate')) {
		    $.post('${vix}/system/databaseManagementAction!saveOrUpdate.action', {
		    'databaseRecord.id' : $("#id").val(),
		    'databaseRecord.dataBaseName' : $("#dataBaseName").val(),
		    'databaseRecord.dataBaseType' : $("#dataBaseType").val(),
		    'databaseRecord.startTime' : $("#startTime").val(),
		    'databaseRecord.endTime' : $("#endTime").val(),
		    'databaseRecord.dataBaseServerIp' : $("#dataBaseServerIp").val(),
		    'databaseRecord.status' : $("#status").val(),
		    }, function(result) {
			    showMessage(result);
			    setTimeout("", 1000);
			    loadContent('${vix}/system/databaseManagementAction!goList.action');
		    });
	    }
    }
    $("#order").validationEngine();
    if ($(".ms").length > 0) {
	    $(".ms").hover(function() {
		    $(">ul", this).stop().slideDown(100);
	    }, function() {
		    $(">ul", this).stop().slideUp(100);
	    });
	    $(".ms ul li").hover(function() {
		    $(">a", this).addClass("hover");
		    $(">ul", this).stop().slideDown(100);
	    }, function() {
		    $(">a", this).removeClass("hover");
		    $(">ul", this).stop().slideUp(100);
	    });
    }
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" /> 系统管理 </a></li>
				<li><a href="#">运行管理 </a></li>
				<li><a href="#">数据库管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${databaseRecord.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateDatabaseRecord()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_savenew.png" /> </a> <a href="#"><img width="22" height="22" title="取消"
							src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" />
					</a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#"><img width="22" height="22" title="上一条" src="${vix}/common/img/dt_before.png"> </a> <a href="#"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#"><img width="22"
							height="22" title="打印" src="${vix}/common/img/dt_print.png"> </a> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/system/databaseManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回"
							src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>数据库管理 </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>数据库名称：</th>
											<td><input id=dataBaseName name="dataBaseName" value="${databaseRecord.dataBaseName }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>数据库类型：</th>
											<td><input id="dataBaseType" name="dataBaseType" value="${databaseRecord.dataBaseType }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>启动时间：</th>
											<td><input id="startTime" name="startTime" value="${databaseRecord.startTime}" type="text" /><img onclick="showTime('startTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th>结束时间：</th>
											<td><input id="endTime" name="endTime" value="${databaseRecord.endTime}" type="text" /><img onclick="showTime('endTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>数据库所在服务器：</th>
											<td><input id="dataBaseServerIp" name="dataBaseServerIp" value="${databaseRecord.dataBaseServerIp}" type="text" size="30" /></td>
											<th>状态：</th>
											<td><input id="status" name="status" value="${databaseRecord.status}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->
<script type="text/javascript">
	$(function() {
	    $.fn.fix = function(options) {
		    var defaults = {
		    'advance' : 0,
		    'top' : 0
		    };
		    options = $.extend(defaults, options);
		    return this.each(function() {
			    var $_this = $(this);
			    $_this.wrap('<div></div>');
			    var wp = $_this.parent('div');
			    wp.height(wp.height());
			    var ofst = wp.offset();
			    if (!$_this.is(':visible') && $(window).scrollTop() + options.advance > $_this.offset().top) {
				    $_this.css({
				    'position' : 'fixed',
				    'z-index' : 9999,
				    'top' : options.top,
				    'width' : $_this.width()
				    });
			    }
			    $(window).scroll(function() {
				    if (!$_this.is(':visible')) {
					    return;
				    }
				    if ($(window).scrollTop() + options.advance > wp.offset().top) {
					    $_this.css({
					    'position' : 'fixed',
					    'z-index' : 9999,
					    'top' : options.top,
					    'width' : $_this.width()
					    });
				    } else {
					    $_this.css({
					    'position' : 'static',
					    'z-index' : 'auto',
					    'top' : 'auto',
					    'width' : 'auto'
					    });
				    }
			    });
		    });
	    }
	    $('#a1 .right_btn,#a3 .right_btn').fix({
	    'advance' : 38,
	    'top' : 38
	    });
    });
    function tabs(title,content,style) {
	    $(title).click(function() {
		    $(title).removeClass(style);
		    $(this).addClass(style);
		    $(content).hide();
		    $(content + ':eq(' + $(title).index($(this)) + ')').show();
	    });
    }
    $(window).scroll(function() {
	    if ($('#orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()) {
		    if (!$('#orderTitleFixd').hasClass('fixed')) {
			    $('#orderTitleFixd').addClass('fixed');
			    $('#orderTitleFixd').parent('dl').css('padding-top', 30);
		    }
	    } else {
		    $('#orderTitleFixd').removeClass('fixed');
		    $('#orderTitleFixd').parent('dl').css('padding-top', 0);
	    }
    });
</script>