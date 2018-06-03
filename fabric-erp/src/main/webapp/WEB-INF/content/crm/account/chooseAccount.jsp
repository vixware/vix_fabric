<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function searchAccount(){
	loadName();
	pager("start","${vix}/crm/accountAction!goSubSingleList.action?name="+name+"&pageNo=${pageNo}",'account');
}
bindSearch();
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
loadName();
//载入分页列表数据
pager("start","${vix}/crm/accountAction!goSubSingleList.action?name="+name+"&pageNo=${pageNo}",'account');
 
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#accountOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/accountrAction!goSubSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'account');
}

function accountPager(tag,entity){
	loadName();
	if(entity == 'account'){
		pager(tag,"${vix}/crm/accountAction!goSubSingleList.action?name="+name+'&parentId='+$('#selectId').val(),entity);
	}
}

</script>
<div class="content" style="background: #DCE7F1">
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
			<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
			<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
			<li class="fly"><a href="#"><img src="img/icon_10.png" alt="" />information</a>
				<ul>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
				</ul></li>
		</ul>
		<div>
			<label>Name<input id="nameS" name="nameS" type="text" class="int" /></label> <label>My items<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="Search" onclick="searchAccount();" /> </label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label>Name<input name="" type="text" class="int" /></label> <label>My items<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="Search" /> </label> <label>Name<input name="" type="text" class="int" /></label> <label>My items<input name="" type="checkbox" value="" /></label> <label><input
				name="" type="button" class="btn" value="Search" /> </label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_operate' /></a>
							<ul>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="accountPager('start','account');"></a></span> <span><a class="previous" onclick="accountPager('previous','account');"></a></span> <em>(<b class="accountInfo"></b> <s:text name='cmn_to' /> <b class="accountTotalCount"></b>)
						</em> <span><a class="next" onclick="accountPager('next','account');"></a></span> <span><a class="end" onclick="accountPager('end','account');"></a></span>
					</div>
				</div>
				<div id="account" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="accountPager('start','account');"></a></span> <span><a class="previous" onclick="accountPager('previous','account');"></a></span> <em>(<b class="accountInfo"></b> <s:text name='cmn_to' /> <b class="accountTotalCount"></b>)
						</em> <span><a class="next" onclick="accountPager('next','account');"></a></span> <span><a class="end" onclick="accountPager('end','account');"></a></span>
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