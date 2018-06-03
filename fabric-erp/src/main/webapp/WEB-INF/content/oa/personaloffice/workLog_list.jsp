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


function editEntity(id){
	var eqId = $('#eqId').val();
	var popWinId = 'sbwd_eq_'+eqId;
	var params = "eqId="+eqId
	if(id && id>0)
		params = params + '&id='+id;

	$.ajax({
		url:'${vix}/oa/taskDefineAction!eqSbwdEdit.action',
		data:params,
		cache: false,
		success: function(html){
			asyncbox.open({
				id:popWinId,
				modal:true,
				width : 780,
				height :250,
				title:"编辑文档信息",
				html:html,
				callback : function(action){
					if(action == 'ok'){
						if($('#sbwdForm').length>0){
							if(saveEqsbwd()){
								
							}else{
								return false;
							}
						}
					}else if(action=='cancel'){
					}
				},
				btnsbar : $.btn.OKCANCEL
			});
		}
	});
}

function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/oa/workLogAction!goSaveOrWorkLong.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 380,
					title:"工作日志",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#brandForm').validationEngine('validate')){
								$.post('${vix}/oa/workLogAction!saveOrUpdate.action',
									 {'workLog.id':$("#update_id").val(),
									  'workLog.title':$("#update_title").val(),
									  'workLog.updateTime':$("#update_updateTime").val(),
									  'workLog.logType':$('input:radio[name=update_logType]:checked').val(),		
									  'workLog.logContent' : updateLogContent.html(),
									  'workLog.logDate':$("#update_logDate").val()
									},
									function(result){
										asyncbox.open({
											modal:true,
											width : 320,
											height : 50,
											title:"<s:text name='工作日志'/>",
											html:result,
											callback : function(action){
												pager("current","${vix}/oa/workLogAction!goList.action?name="+name,'newtab1');
											},
											btnsbar : $.btn.OKCANCEL
										});
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

/* 查看工作日志 */
function popNews(id){
	$.ajax({
		  url:"${vix}/oa/workLogAction!goSeenoticenotice.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"查看工作日志",
					html:html,
					btnsbar : [{
						text :'关闭',
						action :'cancel'
					}]
				});
		  }
	});
};

/* 后期如果优化再更改 */
/* function popNews(id){
	$.ajax({
		  url:"${vix}/oa/workLogAction!goSeenoticenotice.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height :460,
					title:"查看工作日志",
					html:html,
					callback : function(action){
						if(action == 'cancel'){
							$.ajax({
								//从新Action里面定义一个方法
								  url:"${vix}/oa/workLogAction!goCommentsnumber.action?id="+id,
								  cache: false,
								  success: function(html){
									  $('#logcount_'+id).text(html);
								  }
							});
						}
					},
					btnsbar : [{
						text :'关闭',
						action :'cancel'
					}]
				});
		  }
	});
}; */
//新建日志
function saveOrUpdateOrder(){
	$.post('${vix}/oa/workLogAction!saveOrUpdate.action',
		{
		'workLog.title':$("#title").val(),						  
		'workLog.logDate':$("#logDate").val(),						  
		'workLog.logContent' : logContents.html(),
		'workLog.logType':$('input:radio[name=logType]:checked').val(),		
		},function(result){
			showMessage(result);
			setTimeout("",1000);
		loadContent('${vix}/oa/workLogAction!goList.action?title=${title}');
		}
	);
}


function saveOrUpdate1(workLogId,id){
	$.ajax({
		  url:"${vix}/oa/workLogAction!goSaveOrUpdate.action?id="+id,
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
								/* var str="";   
								$("input[name='commentsnumber']:checkbox:checked").each(function(){   
									str+=$(this).val()+",";   
								});    */
								//alert(str);  
								$.post('${vix}/oa/workLogAction!saveOrUpdate1.action',
									 {
									  'logComment.id':$("#logCommentId").val(),
									  'logComment.workLog.id':workLogId,
									  'logComment.updateTime':$("#updateTime").val(),
									  'logComment.commentscontent':commentscontents.html()
									},
									function(result){
										showMessage(result);
										/* setTimeout("",1000);
										pager("start","${vix}/oa/workLogAction!goList.action?name="+name,'newtab1'); */
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


 function chosedatabydate(onchangedate){
	 //alert(onchangedate);
	$.ajax({
		  url:'${vix}/oa/workLogAction!goSingleList.action?onchangedate='+onchangedate,
		  cache: false,
		  success: function(html){
		  	$("#newtab1").html(html);
		  }
	});
}



//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');



pager("start","${vix}/oa/workLogAction!goSingleList.action?name="+name,'newtab1');
pager("start","${vix}/oa/workLogAction!goSingleList1.action?name="+name,'newtab2');

/* 外出记录搜索功能 */
switchSearch(1);
function switchSearch(idx){
	var url = "";
	
	if(idx==1){
		url = "${vix}/oa/workLogAction!goSingleList.action";
	}else if(idx==2){
		url = "${vix}/oa/workLogAction!goSingleList1.action";
	}
}


function djTab(count,idx,name,eventObj){
	switchSearch(idx);
	tab(count,idx,name,eventObj);
} 

/* 搜索功能 */
var name = "";
var senderPeople = "";
var newscontent ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadTitle(){
	title = $('#workLog_title').val();
	title = Url.encode(title);
	title = Url.encode(title);
}
function loadLogContent(){
	logContent = $('#workLog_logContent').val();
	logContent = Url.encode(logContent);
	logContent = Url.encode(logContent);
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
		$("#workLog_title").val("");
		$("#workLog_logContent").val("");
	}
}

var searchUrl = "${vix}/oa/workLogAction!goSearchList.action"; 
var listId = "newtab1";
function searchForContent(i){
	loadName();
	loadTitle();
	loadLogContent();
	if(i == 0){
		pager("start",searchUrl+"?i="+i+"&title="+name,listId);
	}
	else{
		pager("start",searchUrl+"?i="+i+"&title="+title+"&logContent="+logContent,listId);
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/workLogAction!goSearch.action',
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
				pager("start", "${vix}/oa/workLogAction!goSearchList.action?title=" + $('#title').val() + "&logContent=" + $('#logContent').val(), listId);
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

loadName();
//载入分页列表数据
pager("start","${vix}/oa/workLogAction!goSingleList.action?name="+name,'newtab1');
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"> <img src="${vix}/common/img/icon_14.gif" alt="" /> <s:text name="print" />
		</a> <a href="#" id="help"> <img src="${vix}/common/img/icon_15.gif" alt="" /> <s:text name="help" />
		</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/oa_work_diary.png" alt="" /> <s:text name="oa_xtbg" /></a></li>
				<li><a href="#"><s:text name="oa_grbg" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/workLogAction!goList.action?pageNo=${pageNo}');"><s:text name="oa_work_log" /></a></li>
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
			<li class="current"><a href="javascript:void(0);" onclick="javascript:djTab(2,1,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="日志列表" />
			</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:djTab(2,2,'newtab',event)"> <img alt="" src="${vix}/common/img/icon_10.png"> <s:text name="新建日志" />
			</a></li>
		</ul>
		<div>
			<label>主题: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
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
		</div>
	</div>
</div>