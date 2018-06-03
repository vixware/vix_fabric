<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script>
	$ (document).ready (function (){
		$.ajax ({
		url : '${vix}/system/accraditationAction!goAccraditation.action' ,
		cache : false ,
		success : function (html){
			$ ("#right").html (html);
		}
		});
	});
	/* 弹出审批窗口 */
	function accraditationAction (){
		$.ajax ({
		url : '${vix}/system/accraditationAction!showAccraditation.action' ,
		cache : false ,
		success : function (html){
			asyncbox.open ({
			modal : true ,
			width : 750 ,
			height : 380 ,
			title : "审批意见" ,
			html : html ,
			callback : function (action , returnValue){
				if (action == 'ok'){
				}
			} ,
			btnsbar : $.btn.OKCANCEL
			});
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
				<li><a href="#"><img src="${vix}/common/img/newico.png" alt="" /> 工作台 </a></li>
				<li><a href="${vix}/common/vixAction!goIndex.action?isHomePage=1">审批管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="inventoryForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="accraditationAction();" href="#"><img width="22" height="22" title="同意" src="${vix}/common/img/dt_aprroval.png"> </a> <a href="#"><img width="22" height="22" title="拒绝" src="${vix}/common/img/dt_savenew.png" /> </a> <a href="#"><img width="22" height="22" title="驳回"
							src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#"><img width="22" height="22" title="终止" src="${vix}/common/img/dt_disable.png" /> </a> <a href="${vix}/common/vixAction!goIndex.action?isHomePage=1"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a></span> <strong><b>审批管理 </b><i></i> </strong>
				</dt>
				<div id="right"></div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->
<script type="text/javascript">
	/* $ (function (){
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
				wp.offset ();
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
		};
		$ ('#a1 .right_btn').fix ({
		'advance' : 38 ,
		'top' : 38
		});
	}); */
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