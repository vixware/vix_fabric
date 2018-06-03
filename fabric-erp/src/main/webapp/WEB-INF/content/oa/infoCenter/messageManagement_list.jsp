<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link rel="stylesheet" href="${vix}/common/css/aristo/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery-ui.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/underscore-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/backbone-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-debug.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-tinyPubSub.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');

bindSearch();
function getTotalHeight(){
	if($.browser.msie){return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;}
	else {return self.innerHeight;}
}

function getTotalHeight(){
	if($.browser.msie){return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;}
	else {return self.innerHeight;}
}

if($('.listlib').length){
	$('.listlib').each(function(){
		$('strong',$('li',$(this)).first()).first().next('ul').css('display','block');
		$('strong',$('li',$(this)).first()).first().addClass('current');
	});
	$('.listlib li').each(function(){
		var $_this = $(this);
		if($('ul',this).length){
			if(!$_this.next('li').length&&!$_this.prev('li').length){
		 		$('strong',$_this).first().css('cursor','default').toggleClass('current');
		 		$('strong',$_this).first().next('ul').css('display','block');
			}
			$('strong',$_this).first().css('cursor','pointer').click(function(){
				$(this).toggleClass('current');
		 		$('ul',$_this).first().slideToggle();
		 	});
		}
	});
	$('.listlib strong,.listlib span').hover(function(){$(this).addClass('al_hover');},function(){$(this).removeClass('al_hover');})
}


if($('.w_bfb').length>0){
	$('.w_bfb').each(function(){
		$(this).append('<div class="bfb" style="width:'+$('span',this).text()+'"></div>');
	})
}

//login
$('.login .btn').hover(
  function () {$(this).css('margin','2px 0 0 51px');},
  function () {$(this).css('margin','0 0 0 50px');}
);

$('#list_switch_search').toggle(
  function () {
	  $(this).addClass("current");
	  $('#list_search_bar').slideUp('fast');
	},
  function () {
	  $(this).removeClass("current");
	  $('#list_search_bar').slideDown('fast');
	}
);
//left menu
$('#list_left h2').toggle(
  function () {
	  $(this).next().slideDown('fast');
	  $(this).addClass("current");
	},
  function () {
	  $(this).next().slideUp('fast');
	  $(this).removeClass("current");
	}
);
//version
$('.version').hover(
  function () {$(this).children('ul').slideDown('fast');},
  function () {$(this).children('ul').slideUp('fast');}
);
$('.version li').hover(
  function () {$(this).addClass("hover");},
  function () {$(this).removeClass("hover");}
);
$('.version li').click(
  function () {
	$('.version b').text($(this).text()); 
});
//table
$("table tr").mouseover(function(){
	$(this).addClass("over");}).mouseout(function(){
		$(this).removeClass("over");})
$("table tr:even").addClass("alt");
//switch
$("#list_switch").toggle(
  function () {
	$("#list_content").removeClass("list_bg");
	$("#list_switch").addClass("off")
	$("#list_left").addClass("switch")
	$("#list_right").addClass("switch")
  },
  function () {
	$("#list_content").addClass("list_bg")
	$("#list_switch").removeClass("off")
	$("#list_left").removeClass("switch")
	$("#list_right").removeClass("switch")
  });

$(".untitled b img").click(function () {
	var $_this = $(this).parent().parent(".untitled");
	$_this.css({ "position": "relative"});
	$_this.children('.popup').css({ "display": "block"});
	var bh = $("body").height(); 
	var pt = $_this.offset();
	if(( bh - pt.top) < 165){$_this.children('.popup').css({ "bottom": "0"});}else{$_this.children('.popup').css({ "top": "-7px"});};
  });
  $(".untitled span img").bind('mouseover',function () {
	var $_this = $(this).parent().parent(".untitled");
	$_this.css({ "position": "relative"});
	$_this.children('.popup').css({ "display": "block"});
	var bh = $("body").height(); 
	var pt = $_this.offset();
	if(( bh - pt.top) < 165){$_this.children('.popup').css({ "bottom": "0"});}else{$_this.children('.popup').css({ "top": "-7px"});};
  });
  $(".untitled").bind('mouseleave',function(){
	$(this).css({ "position": "static"});
	$(this).children('.popup').css({ "display": "none"});
  });
  
  
$(".close").click(function () {
	$(this).parent().parent().css({ "display": "none"});
	return false;
});

/* 删除消息 */
function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/messageManagementAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				pager("start","${vix}/oa/messageManagementAction!goList.action?name="+name,'mainContent');
			});
		  }
	});
}

/* 删除消息跟上面一样只是处理方式不一样 */
function deleteById1(id,a){
	$.ajax({
		  url:'${vix}/oa/messageManagementAction!deleteById1.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				$(a).parent().parent().parent().empty();
			});
		  }
	});
}
/* 删除附件*/
function deleteUploader(id,a){
	$.ajax({
		  url:'${vix}/oa/messageManagementAction!deleteUploader.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				$(a).parent().parent().parent().empty();
			});
		  }
	});
}
//删除消息
function deleteEntity(id){
	asyncbox.confirm('确定要删除该消息么?','提示信息',function(action){
		if(action == 'ok'){
			deleteById(id);
		}
	});
}

//查看工作日志
function looknewsComment(id){
	$.ajax({
		  url:"${vix}/oa/messageManagementAction!goLooknewsComment.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"查看消息与评论",
					html:html,
					btnsbar : [{
						text :'关闭',
						action :'cancel'
					}]
				});
		  }
	});
};

//消息评论 
function saveCommentMessage(messageManagementId,id){
	$.ajax({
		  url:"${vix}/oa/messageManagementAction!goCommentMessage.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 370,
					title:"发表评论",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#brandForm').validationEngine('validate')){
								$.post('${vix}/oa/messageManagementAction!saveCommentMessage.action',
									 {
									  'newsComment.id':$("#id").val(),
									  'newsComment.messageManagement.id':messageManagementId,
									  'newsComment.updateTime':$("#updateTime").val(),
									  'newsComment.commentscontent':commentscontents.html()
									},
									function(result){
										showMessage(result);
									}
								 ); 
			  				}else{
			  					return false;
			  				}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};



pager("start","${vix}/oa/messageManagementAction!goSingleList.action?name="+name,'newtab1');
pager("start","${vix}/oa/messageManagementAction!goSingleList1.action?name="+name,'newtab2');
pager("start","${vix}/oa/messageManagementAction!goSingleList2.action?name="+name,'newtab3');

/* 外出记录搜索功能 */
switchSearch(1);
function switchSearch(idx){
	var url = "";
	
	if(idx==1){
		url = "${vix}/oa/messageManagementAction!goSingleList.action";
	}else if(idx==2){
		url = "${vix}/oa/messageManagementAction!goSingleList1.action";
	}else if(idx==3){
		url = "${vix}/oa/messageManagementAction!goSingleList2.action";
	}
	
}


function djTab(count,idx,name,eventObj){
	switchSearch(idx);
	tab(count,idx,name,eventObj);
} 

/* 搜索功能 */
var name = "";
var senderPeople = "";
var ccPeople = "";
var ccPeople1 = "";
var newscontent ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadSenderPeople(){
	senderPeople = $('#message_senderPeople').val();
	senderPeople = Url.encode(senderPeople);
	senderPeople = Url.encode(senderPeople);
}
function loadccPeople(){
	ccPeople = $('#message_ccPeople').val();
	ccPeople = Url.encode(ccPeople);
	ccPeople = Url.encode(ccPeople);
}
function loadccPeople1(){
	ccPeople1 = $('#message_ccPeople1').val();
	ccPeople1 = Url.encode(ccPeople1);
	ccPeople1 = Url.encode(ccPeople1);
}
function loadNewscontent(){
	newscontent = $('#message_newscontent').val();
	newscontent = Url.encode(newscontent);
	newscontent = Url.encode(newscontent);
}



/*判断搜索内容是否为空*/
function validateSearch(temp){
	if(null == temp || "" == temp){
		return false;
	}
	return true;
}

/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#message_senderPeople").val("");
		$("#message_ccPeople").val("");
		$("#message_ccPeople1").val("");
		$("#message_newscontent").val("");
	}
}

var searchUrl = "${vix}/oa/messageManagementAction!goSearchList.action"; 
var listId = "newtab1";
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start",searchUrl+"?i="+i+"&senderPeople="+name,listId);
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/messageManagementAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				pager("start", "${vix}/oa/messageManagementAction!goSearchList.action?senderPeople=" + $('#senderPeople').val() 
						+ "&ccPeople=" + $('#ccPeople').val()
						+ "&ccPeople1=" + $('#ccPeople1').val()
						+ "&newscontent=" + $('#newscontent').val(), listId);
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

loadName();
//载入分页列表数据
pager("start","${vix}/oa/messageManagementAction!goSingleList.action?name="+name,'newtab1');
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" /> <s:text name="print" /> </a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" /> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/oa/oa_news_manager.png" alt="" /> <s:text name="oa_xtbg" /></a></li>
				<li><a href="#"><s:text name="oa_grbg" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/messageManagementAction!goList.action?pageNo=${pageNo}');"><s:text name="oa_message_management" /></a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="${vix}/common/img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
			</div>
		</div>
		<ul class="c_head_tabbtn">
			<li class="current"><a href="javascript:void(0);" onclick="javascript:djTab(3,1,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="oa_message_list" />
			</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:djTab(3,2,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="oa_send_message" />
			</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:djTab(3,3,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="oa_things_remind" />
			</a></li>
		</ul>
		<div>
			<label>收信人: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>

	<!-- 1 -->
	<div class="box">
		<div id="right">
			<div id="newtab1"></div>
			<!-- 2 -->
			<div id="newtab2" style="display: none;"></div>
			<!-- 3 -->
			<div id="newtab3" style="display: none;"></div>
		</div>
	</div>
</div>