<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/search/css/css.css" rel="stylesheet" type="text/css" />
<script>
  function addUser(){
	  $("#aaaejee").load("adduser.htm",function(){
		  asyncbox.html({
			   title:"维度",
			   content:document.getElementById("aaaejee"),
			   width : 600,
			   height : 460,
			   buttons : [{
				  　　　　　value : '确定',
				  　　　　　result : 'ok'
				  　　　},{
				  　　　　　value : '取消',
				  　　　　　result : false
				  　　　}],
			   onload : function(){
				   $("#aaaejee").html("");
			   }
		  });
	  });
  };
</script>





<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/oa_poll_manager.png" alt="" /> <s:text name="oa_xtbg" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/fileRetrievalCenterAction!goList.action?pageNo=${pageNo}');"><s:text name="知识管理" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/fileRetrievalCenterAction!goList.action?pageNo=${pageNo}');"><s:text name="文档检索中心" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />索引</a></li>
			<!--<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>-->
			<li class="fly"><a href="#"><img src="img/icon_10.png" alt="" />数据分类</a>
				<ul>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
				</ul></li>
			<li class="fly_th"><a href="#"><img src="img/icon_10.png" alt="" />热点分类</a>
				<ul>
					<li>
						<dl>
							<dt>信息</dt>
							<dd>
								<a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a>
							</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>信息</dt>
							<dd>
								<a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a>
							</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>信息</dt>
							<dd>
								<a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a>
							</dd>
						</dl>
					</li>
				</ul></li>
		</ul>
		<div>
			<label>名称 <input name="" type="text" class="int" />
			</label> <label>我的项目 <input name="" type="checkbox" value="" />
			</label> <label> <input name="" type="button" class="btn" value="搜索" /> <input name="" type="button" class="btn" value="重置" />
			</label> <strong id="search_advanced">高级搜索</strong>
		</div>
		<div class="search_advanced">
			<label>名称 <input name="" type="text" class="int" />
			</label> <label>我的项目 <input name="" type="checkbox" value="" />
			</label> <label> <input name="" type="button" class="btn" value="搜索" /> <input name="" type="button" class="btn" value="重置" />
			</label> <label>姓名 <input name="" type="text" class="int" />
			</label> <label>我的项目 <input name="" type="checkbox" value="" />
			</label> <label> <input name="" type="button" class="btn" value="搜索" /> <input name="" type="button" class="btn" value="重置" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="text_list">
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
			<div class="ec_pp_top">
				<div class="ec_title">
					<h3>
						<a href="#" class="ec-link ">学<font color="#CC0000">安卓开发</font>多长时间?哪家学校好?
						</a>
					</h3>
				</div>
				<div class="ec_font_small">
					学<font color="#CC0000">安卓开发</font>很好就业,因为行业发展迅猛,国家这方面的人才缺口很大,<font color="#CC0000">北京</font>汇众是国家信产部唯一指定的<font color="#CC0000">安卓开发</font>培训机构,与企业合作育人,100%毕业=就业!
				</div>
				<div class="ec_font_small">
					<span class="ec_url">android.gamfe.com</span><span class="ec_date">2014-03</span><a href="javascript:;" class="arrow"></a>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<!-- content -->
<div id="footpanel">
	<ul id="mainpanel">
		<li><a href="http://www.designbombs.com" class="home">Inspiration <small>Design </small></a></li>
		<li><a href="http://www.designbombs.com" class="profile">View Profile <small>View Profile</small></a></li>
		<li><a href="http://www.designbombs.com" class="editprofile">Edit Profile <small>Edit Profile</small></a></li>
		<li><a href="http://www.designbombs.com" class="contacts">Contacts <small>Contacts</small></a></li>
		<li><a href="http://www.designbombs.com" class="messages">Messages (10) <small>Messages</small></a></li>
		<li><a href="http://www.designbombs.com" class="playlist">Play List <small>Play List</small></a></li>
		<li><a href="http://www.designbombs.com" class="videos">Videos <small>Videos</small></a></li>
		<li id="alertpanel"><a class="alerts" id="alerts_off">Alerts</a></li>
		<li id="chatpanel"><a href="#" class="chat">Friends (<strong>18</strong>)
		</a>
			<div class="subpanel">
				<h3>
					<span> &ndash; </span>Friends Online
				</h3>
				<ul>
					<li><span>Family Members</span></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><span>Other Friends</span></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
				</ul>
			</div></li>
	</ul>
</div>
<!-- dialog -->
<script src="js/menu.js" type="text/javascript"></script>
<script src="js/loadtree.js" type="text/javascript"></script>
<div id="helpContent" title="Viewing and using brush files">
	<p>&nbsp;&nbsp;You can view brush files in the Browser just like fonts or other files. This means that you can assign ratings or organize them just like any other file in ACDSee. Because many .abr files are actually groups of images in one file, you need to open them in the Viewer to see the individual images.</p>
	<p>
		<img src="img/address_book.png">This icon indicates an .abr brush file in ACDSee.
	</p>
	<p>
		<strong>To view brush files:</strong>
	</p>
	<p>&nbsp;&nbsp;1. In the Browser, navigate to the folder containing your brush files.</p>
	<p>&nbsp;&nbsp;2. To see just the top image in any .abr file, hover over the thumbnail to activate the pop-up, or click on it to see that image in the Preview pane.</p>
	<p>&nbsp;&nbsp;3. To view the other images in the .abr file, double-click on it to open it in the Viewer.</p>
	<p>&nbsp;&nbsp;The file opens in the Viewer showing the individual images in a pane on the left-hand side.</p>
	<p>&nbsp;&nbsp;4. To see the number of images, and select them by number, click the down-arrow at the top of the sidebar, and then select the number of the image.</p>
	<p>&nbsp;&nbsp;5. To scroll through the images, click the right and left arrows at the top of the sidebar, or on each image.</p>
	<p>
		<strong>To use brush files in Adobe Photoshop:</strong>
	</p>
	<p>&nbsp;&nbsp;With both Adobe Photoshop and ACDSee open, drag the file from the File List (in the Browser) onto the Photoshop window.</p>
	<p>&nbsp;&nbsp;Even though nothing appears to happen, the brush is loaded into the Photoshop brush library. To view the new brushes, open the library and scroll to the bottom of the pane.</p>
	<p class="helpTd">To make it even easier to use brushes in Photo Shop, you can configure it to be your default editor. Then you can use Ctrl +E to open Photoshop and use the brush right away.</p>
</div>
<div class="c-tip-con">
	<div>
		<div class="c-tip-menu">
			<ul>
				<li><a href="#">收藏</a></li>
				<li><a href="#">分享</a></li>
				<li><a href="#">评价</a></li>
				<li><a href="#">举报</a></li>
			</ul>
		</div>
	</div>
	<div class="c-tip-arrow" style="left: 30px;">
		<em></em>
		<ins></ins>
	</div>
</div>
<script>
$(function(){
	$('.tc_texticon').live('click',function(){
		if($.trim($(this).text()) == '+'){
			$(this).closest('td').append('<div class="tc_copay">'+$(this).closest('.tc_copay').html()+'</div>');
			$('.tc_texticon',$(this).closest('td')).text('-');
			$('.tc_texticon:last',$(this).closest('td')).text('+');
		}else if($.trim($(this).text()) == '-'){
			$(this).closest('.tc_copay').remove();
		}
	});
	
	$(document).on('click',function(e){
		var $_this = $(e.target),
			l = $_this.offset().left-31,
			t = $_this.offset().top+20;
		if($_this.closest('.arrow').length > 0){
			$('.c-tip-con').css({'left':l,'top':t}).show();
		}else{
			$('.c-tip-con').hide();
		}
	});
});
</script>