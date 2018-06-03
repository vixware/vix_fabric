<%@ page language="java" pageEncoding="UTF-8"%>
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
<script src="js/jquery.jcarousel.min.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="js/bar.js" type="text/javascript"></script>
<script src="js/all.js" type="text/javascript"></script>
<script src="js/jquery.ui.core.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.widget.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.mouse.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.resizable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.draggable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.position.min.js" type="text/javascript"></script>
<script src="js/jquery.lightbox-0.5.pack.js" type="text/javascript"></script>

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
<table class="list">
	<tbody>
		<ul class="imglist clearfix">
			<li>
				<div class="imgitembox">
					<img src="img/img01-s.jpg" /><span class="imgitemfn"><a href="img/img01.jpg" class="l lightimg" title="图片说明文字">查看</a><a href="img/img01.jpg" class="r">下载</a></span>
				</div>
			</li>
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
	</tbody>
</table>


