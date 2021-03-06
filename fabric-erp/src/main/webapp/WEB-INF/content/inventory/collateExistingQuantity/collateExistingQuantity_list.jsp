<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
/* 内容 */
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
/*入库编码  */
var code1="";
function loadCode(){
	code1 = $('#code1').val();
	code1=Url.encode(code1);
	code1=Url.encode(code1);
}
/* 调出仓库 */
var outwarehousecodes="";
function loadOutwarehousecodes(){
	outwarehousecodes = $('#outwarehousecodes').val();
	outwarehousecodes=Url.encode(outwarehousecodes);
	outwarehousecodes=Url.encode(outwarehousecodes);
}
/*调入仓库  */
var inwarehousecodes="";
function loadInwarehousecodes(){
	inwarehousecodes = $('#inwarehousecodes').val();
	inwarehousecodes=Url.encode(inwarehousecodes);
	inwarehousecodes=Url.encode(inwarehousecodes);
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
function saveOrUpdate(id,pageNo,type){
	if(type==0){
		saveOrUpdateTransfer();
	}else if(type==1){
		saveOrUpdateAllocateandTransfer();
	}
};

function saveOrUpdateTransfer(id,pageNo){
	$.ajax({
		  url:'${vix}/inventory/collateExistingQuantityAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
}
function saveOrUpdateAllocateandTransfer(id,pageNo){
	$.ajax({
		  url:'${vix}/inventory/collateExistingQuantityAction!goSaveOrUpdateAllocateandTransfer.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
}
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
		  url:'${vix}/inventory/collateExistingQuantityAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/inventory/collateExistingQuantityAction!goSingleList.action?name="+name,'brand');
			});
		  }
	});
}
loadName();
//载入分页列表数据
pager("start","${vix}/inventory/collateExistingQuantityAction!goSingleList.action?name="+name,'brand');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/inventory/collateExistingQuantityAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'brand');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/inventory/collateExistingQuantityAction!goSingleList.action?name="+name,'brand');
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
/*搜索转换 */
function changeDisplay(){
	var divText = $("#lb_search_advanced").text();
	if(divText == "高级搜索"){
		$("#nameS").attr({disabled:'true'});
	}
	else{
		$("#nameS").removeAttr("disabled");
	}
}
/* 搜索 */
function searchForContent(tag){
	loadName();
	loadCode();
	loadOutwarehousecodes();
	loadInwarehousecodes();
	if(tag==0){
     	pager("start","${vix}/inventory/collateExistingQuantityAction!goSearchTransferList.action?content="+name+"&tag="+tag,'brand');
	}else {
	    pager("start","${vix}/inventory/collateExistingQuantityAction!goSearchTransferList.action?outwarehousecodes="+outwarehousecodes+"&inwarehousecodes="+inwarehousecodes+"&code1="+code1+"&tag="+tag,'brand');
	}
}
/* 重置 */
function resetForContent(tag){
	if(tag == 0){
		$("#nameS").val("");
	}
	else{
		$("#code1").val("");
		$("#outwarehousecodes").val("");
		$("#inwarehousecodes").val("");
	}
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_transfer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">库存管理 </a></li>
				<li><a href="#">其他业务处理</a></li>
				<li><a href="#">整理现存量</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdateTransfer(0);"><span><s:text name='cmn_add' /> </span> </a>
		</p>
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
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_unapproved_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_by_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_in" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="nameS"> </label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn"
				onclick="resetForContent(0)"> </label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name="cmn_advance_search" /> </strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="wim_requisition_number1" /><input type="text" class="int" name="" id="code1"> </label> <label><s:text name="wim_v_warehouse" />：<input type="text" class="int" name="" id="outwarehousecodes"></label> <label><s:text name="wim_call_warehouse" />：<input type="text" class="int" name=""
				id="inwarehousecodes"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(1);"> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(1)">
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="wimTransvouchList" var="c">
				<li><a href="#" onclick="saveOrUpdateTransfer(${c.id});">${c.tvcode}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="brandInfo"></b> <s:text name='cmn_to' /> <b class="brandTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="brand" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="brandInfo"></b> <s:text name='cmn_to' /> <b class="brandTotalCount"></b>)
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