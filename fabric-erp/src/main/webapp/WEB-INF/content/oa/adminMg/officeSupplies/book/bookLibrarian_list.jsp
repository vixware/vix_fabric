<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name = "";
var bookCodes = "";
var bookNumber = "";
var bookNames = "";
var recipientsWho = "";
var uploadPersonName ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadBookCodes(){
	bookCodes = $('#book_bookCodes').val();
	bookCodes = Url.encode(bookCodes);
	bookCodes = Url.encode(bookCodes);
}
function loadBookNumber(){
	bookNumber = $('#book_bookNumber').val();
	bookNumber = Url.encode(bookNumber);
	bookNumber = Url.encode(bookNumber);
}
function loadBookNames(){
	bookNames = $('#book_bookNames').val();
	bookNames = Url.encode(bookNames);
	bookNames = Url.encode(bookNames);
}
function loadRecipientsWho(){
	recipientsWho = $('#book_recipientsWho').val();
	recipientsWho = Url.encode(recipientsWho);
	recipientsWho = Url.encode(recipientsWho);
}
function loadUploadPersonName(){
	uploadPersonName = $('#book_uploadPersonName').val();
	uploadPersonName = Url.encode(uploadPersonName);
	uploadPersonName = Url.encode(uploadPersonName);
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
		$("#book_bookCodes").val("");
		$("#book_bookNumber").val("");
		$("#book_bookNames").val("");
		$("#book_recipientsWho").val("");
		$("#book_uploadPersonName").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/bookLibrarianAction!goSearchList.action?i="+i+"&bookNames="+name,'outBound');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/bookLibrarianAction!goSearch.action',
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
				pager("start", "${vix}/oa/bookLibrarianAction!goSearchList.action?bookCodes=" + $('#bookCodes').val()
						+ "&bookNumber=" + $('#bookNumber').val()
						+ "&bookNames=" + $('#bookNames').val()
						+ "&recipientsWho=" + $('#recipientsWho').val()
						+ "&uploadPersonName=" + $('#uploadPersonName').val(), 'outBound');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

//状态
 function saleOrderStatus(isTemp){
 	pager("start","${vix}/oa/bookLibrarianAction!goBookRegister.action?isTemp="+isTemp,'outBound');
 }
 //最近使用
 function leastRecentlyUsed(createTime){
 	pager("start","${vix}/oa/bookLibrarianAction!goBookRegister.action?createTime="+createTime,'outBound');
 }

 
 
$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	});
	return false;
});
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});
function saveOrUpdate(id,pageNo,biztype){
	if(biztype==1){
		saveOrUpdateConsuming(id,pageNo);
    }else if(biztype==2){
	    saveOrUpdateOtherOutBound(id,pageNo);
    }
   };
/**
 * 借用
 */
function saveOrUpdateConsuming(id,pageNo){
	$.ajax({
		  url:'${vix}/oa/bookLibrarianAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
}

/**
 * 归还
 */
function saveOrUpdateOtherOutBound(id,pageNo){
	$.ajax({
		  url:'${vix}/oa/bookLibrarianAction!goSaveOrUpdateBook.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
}
/**
 * 查看
 */
function popNews(id){
	$.ajax({
		  url:"${vix}/oa/bookLibrarianAction!goSeenoticenotice.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 1050,
					height :460,
					title:"查看借用归还书",
					html:html,
					callback : function(action){
						if(action == 'ok'){
								}
							} ,
							btnsbar : [{
								text :'关闭',
								action :'cancel'
							}]
				});
		  }
	});
};

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	asyncbox.success(ids,"选中的id");
}

function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/bookLibrarianAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/oa/bookLibrarianAction!goBookRegister.action?name="+name,'outBound');
			});
		  }
	});
}

loadName();
//载入分页列表数据
pager("start","${vix}/oa/bookLibrarianAction!goBookRegister.action?name="+name,'outBound');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#outBoundOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/bookLibrarianAction!goBookRegister.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'outBound');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/bookLibrarianAction!goBookRegister.action?name="+name,'outBound');
}

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/oa/oa_book.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公</a></li>
				<li><a href="#">图书管理管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');">借还书管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>借还书管理</span></a>
				<ul>
					<li><a href="#" onclick="saveOrUpdateConsuming(0);"><s:text name='借用' /> </a></li>
					<li><a href="#" onclick="saveOrUpdateOtherOutBound(0);"><s:text name='归还' /></a></li>
				</ul></li>
		</ul>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> <s:text name="借用" /> </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> <s:text name="归还" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>名称: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="bookRegisterList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());"><span style="display: none;">${c.chineseFirstLetter}</span>${c.bookNames}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<%-- <ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong> --%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="outBoundInfo"></b> <s:text name='cmn_to' /> <b class="outBoundTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="outBound" class="table"></div>
				<div class="pagelist drop">
					<%-- <ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul> 
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>--%>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="outBoundInfo"></b> <s:text name='cmn_to' /> <b class="outBoundTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>