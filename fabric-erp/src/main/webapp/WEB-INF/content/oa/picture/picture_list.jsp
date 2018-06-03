<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/toolbar.css" rel="stylesheet" type="text/css" />
<link href="css/tree.css" rel="stylesheet" type="text/css" />
<link href="css/base/jquery.ui.all.css" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='css/fullcalendar.print.css' media='print' />
<link href="css/skin_01.css" type="text/css" id="skin" rel="stylesheet">
<link href="css/font_02.css" type="text/css" id="font" rel="stylesheet">
<link href="css/jquery.lightbox-0.5.css" type="text/css" rel="stylesheet">
<!-- 此js对页面有影响 -->
<!-- <script src="js/jquery.js" type="text/javascript"></script> -->
<script src="js/jquery.jcarousel.min.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="js/jquery.jBreadCrumb.1.1.js" type="text/javascript"></script>
<script src="js/bar.js" type="text/javascript"></script>
<script src="js/all.js" type="text/javascript"></script>
<script src="js/jquery.tree.js" type="text/javascript"></script>
<script src="js/date.js" type="text/javascript"></script>
<script src="js/date.js" type="text/javascript"></script>
<script src="js/jquery.ui.core.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.widget.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.mouse.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.resizable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.draggable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.position.min.js" type="text/javascript"></script>
<script src="js/jquery.lightbox-0.5.pack.js" type="text/javascript"></script>
<body>
	<div class="sub_menu">
		<h2>
			<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" /> <s:text name="print" /></a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" /> <s:text name="help" /></a>
			</i>
			<div id="breadCrumb" class="breadCrumb module">
				<ul>
					<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /></a></li>
					<li><a href="#"><s:text name="oa_xtbg" /></a></li>
					<li><a href="#">知识管理</a></li>
					<li><a href="#">图片浏览</a></li>
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
				<div class="switch_btn" id="switch_box"></div>
				<div class="left_content" id="tree_00"></div>
			</div>
			<div id="right">
				<div class="right_content">
					<div class="addtitle taskmenutitle clearfix">
						<span class="l">图片列表</span>
					</div>
					<ul class="imglist clearfix">
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img02-s.jpg" /><span class="imgitemfn"><a href="img/img02.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img03-s.jpg" /><span class="imgitemfn"><a href="img/img03.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
						<li><div class="imgitembox">
								<img src="img/img04-s.jpg" /><span class="imgitemfn"><a href="img/img04.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
							</div></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="c_foot">
			<span class="left_bg"></span> <span class="right_bg"></span>
		</div>
		<!-- c_foot -->
	</div>
	<script src="js/loadtree.js" type="text/javascript"></script>
	<script type="text/javascript">
	// 左右拖动
	$ (document).ready (function (){
		$ (function (){
			if ($ ("#left").length){
				$ ("#left").resizable ({
				maxHeight : 680 ,
				minHeight : 680 ,
				maxWidth : 400 ,
				minWidth : 200 ,
				handles : 'e'
				});
			}
		});
	});
	// 弹出层
	$ (document).ready (function (){
		$.fx.speeds._default = 1000;
		$ (function (){
			$ ("#dialog").dialog ({
			autoOpen : false ,
			modal : true
			});
			$ ("#text").click (function (){
				$ ("#dialog").dialog ("open");
				return false;
			});
		});
		$ ('.imgitembox').hover (function (){
			$ ('.imgitemfn' , this).show ();
		} , function (){
			$ ('.imgitemfn' , this).hide ();
		});
		$ ('a.lightimg').lightBox ({
			fixedNavigation : true
		});
	});
	
	//面包屑
	if($('.sub_menu').length){
		$("#breadCrumb").jBreadCrumb();
	}
	loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');
</script>
</body>