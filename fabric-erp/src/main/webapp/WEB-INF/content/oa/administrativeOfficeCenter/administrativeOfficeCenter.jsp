<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function goBusinessFormTemplate(id) {
		$.ajax({
		url : '${vix}/oa/administrativeAction!goBusinessFormTemplate.action?businessFormTemplateId=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> 协同办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/administrativeAction!goList.action?pageNo=${pageNo}');">行政办公中心</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="mail_left">
				<div class="addbox" style="margin: 0;" id="scrNavBox">
					<div class="addtitle">
						<span class="l"> <img width="16" height="16" src="img/oa/nivagator.png" style="vertical-align: middle">&nbsp;导航
						</span>
					</div>
					<ul class="navmenu" id="scrNav">
						<s:iterator value="businessFormTypeList" var="entity" status="s">
							<li><a href="#${entity.id }">${entity.typeName }</a></li>
						</s:iterator>
					</ul>
				</div>
			</div>
		</div>
		<div id="right">
			<div class="right_content" style="border: 0;">
				<div class="clearfix listfn_btn_box">
					<ul id="listfn_btn">
						<li data-rel="listfn_tw" class="current"><a href="javascript:;"> <img src="img/win_btn_01.png" width="16" height="16" /></a></li>
						<li data-rel="listfn_tb"><a href="javascript:;"> <img src="img/win_btn_02.png" width="16" height="16" /></a></li>
						<li data-rel="listfn_lb"><a href="javascript:;"> <img src="img/win_btn_03.png" width="16" height="16" /></a></li>
					</ul>
				</div>
				<div id="scroBox" style="overflow: hidden; overflow-y: auto;" class="listfn_tw">
					<s:iterator value="businessFormTemplateMap" id="column">
						<div class="scr" id="<s:property value="#column.key" />">
							<div class="scrTitle">
								<img src="img/oa/nivagator.png" width="16" height="16" style="vertical-align: middle" />&nbsp;
								<s:iterator value="businessFormTypeList" var="entity" status="s">
									<s:if test="#entity.id==#column.key">${entity.typeName }</s:if>
								</s:iterator>
							</div>
							<s:set var="total" name="total" value="#column.value.size" />
							<ul class="listfn clearfix">
								<s:iterator value="#column.value" id="col" status="st">
									<li class="listfn_item">
										<div class="listfn_box clearfix">
											<div class="listfn_img">
												<a href="javascript:;"><img src="img/oa/oa_finance.jpg" /></a>
											</div>
											<div class="listfn_title">
												<s:property value="templateName" />
											</div>
											<div class="listfn_desc">
												<a href="#" onclick="goBusinessFormTemplate('<s:property value="id" />');">表单填写</a>
											</div>
										</div>
									</li>
								</s:iterator>
							</ul>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<script type="text/javascript">
	loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');

	// 左右拖动
	$(document).ready(function() {
		$(function() {
			if ($("#left").length) {
				$("#left").resizable({
				maxHeight : 680,
				minHeight : 680,
				maxWidth : 400,
				minWidth : 200,
				handles : 'e'
				});
			}
		});
	});
	$(function() {
		$('.addbox li a').click(function() {
			$("a.current").removeClass();
			$(this).addClass("current");
		})
	});
	// 弹出层
	$(document).ready(function() {
		$.fx.speeds._default = 1000;
		$(function() {
			$("#dialog").dialog({
			autoOpen : false,
			modal : true
			});

			$("#text").click(function() {
				$("#dialog").dialog("open");
				return false;
			});
		});
	});

	var timeout;
	$(function() {
		$('#scroBox').height($(window).height() - 110);
		scrollNav();
		if ($('#scrNavBox').height() > $(window).height() - 112) {
			$('#scrNav').height($(window).height() - 144).css({
			'overflow' : 'hidden',
			'overflow-y' : 'auto'
			});
		}
		$('.navmenu a').click(function() {
			$(this).attr('href');
			var n = $($(this).attr("href")).index('#scroBox .scr');
			var st = 0;
			for (var i = 0; i < n; i++) {
				st += $('#scroBox .scr:eq(' + i + ')').innerHeight();
			}

			$("#scroBox").stop().animate({
				scrollTop : st
			}, 500);
			return false;
		});

		$('#scroBox').scroll(function() {
			clearTimeout(timeout);
			timeout = setTimeout(scrollNav, 100);
		});

		$('#listfn_btn li').click(function() {
			$('#listfn_btn li').removeClass('current');
			$(this).addClass('current');
			$('#scroBox').removeClass('listfn_tw').removeClass('listfn_tb').removeClass('listfn_lb').addClass($(this).attr('data-rel'));
			;
		});

	});

	function scrollNav() {
		var len = $('#scroBox .scr').length;
		var t = $('#scroBox').scrollTop();
		var m = -50;
		var i = 0;
		for (i; i < len; i++) {
			m += $('#scroBox .scr:eq(' + i + ')').outerHeight();
			if (m > t) {
				break;
			}
		}
		$('.navmenu a').removeClass('current');
		$('.navmenu a[href=#' + $('#scroBox .scr:eq(' + i + ')').attr('id') + ']').addClass('current');
	}
</script>