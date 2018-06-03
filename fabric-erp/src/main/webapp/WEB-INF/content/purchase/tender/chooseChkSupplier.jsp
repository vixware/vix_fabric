<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$.returnValue = "";
function chkChange(chk,id,name){
	 if(chk.checked){
		 $.returnValue = $.returnValue +","+id+":"+name;
	 }else{
		 $.returnValue = $.returnValue.replace(","+id+":"+name,"");
	 }
} 
pager("start","${vix}/purchase/invitingTenderAction!goSubChkSupplierList.action?tag=${tag}","chk");
//排序 
/* function subOrderBy(orderField){
	var orderBy = $("#subBrandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/purchase/invitingTenderAction!goSubChkSupplierList.action?tag=${tag}&orderField="+orderField+"&orderBy="+orderBy,"chk");
} */

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/purchase/invitingTenderAction!goSubChkSupplierList.action?tag=${tag}&name="+name,'chk');
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
var code = "";
function loadCode(){
	code = $('#srm_code').val();
	code=Url.encode(code);
	code=Url.encode(code);
} 
/*搜索*/
function searchForContent(i){
	loadCode();
	pager("start","${vix}/purchase/invitingTenderAction!goSubChkSupplierList.action?searchContent="+code,'radio');
}
/*重置搜索*/
function resetForContent(){
	$("#srm_code").val("");
}
//状态
function srmStatus(status){
	pager("start","${vix}/purchase/invitingTenderAction!goSubChkSupplierList.action?status="+status,'radio');
}
//最近使用
function srmRecent(rencentDate){
	pager("start","${vix}/purchase/invitingTenderAction!goSubChkSupplierList.action?updateTime="+rencentDate,'radio');
}
</script>
<div id="c_head" class="drop">
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
		<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
		<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /></a>
			<ul>
				<li><a href="#" onclick="srmStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
				<li><a href="#" onclick="srmStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
				<li><a href="#" onclick="srmStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
				<li><a href="#" onclick="srmStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
			</ul></li>
		<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /></a>
			<ul>
				<li><a href="#" onclick="srmRecent('T1')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /></a></li>
				<li><a href="#" onclick="srmRecent('T2')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /></a></li>
				<li><a href="#" onclick="srmRecent('T3')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /></a></li>
				<li><a href="#" onclick="srmRecent('T4')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /></a></li>
			</ul></li>
	</ul>
	<div>
		<label><s:text name='srm_supplier_code' />:<input id="srm_code" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>"
			onclick="resetForContent();" />
		</label>
	</div>
</div>
<div class="table" style="margin: 5px;">
	<div id="chk"></div>
	<div class="pagelist">
		<div>
			<span> <span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="chkInfo"></b>到 <b class="chkTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
			</span>
		</div>
	</div>
</div>