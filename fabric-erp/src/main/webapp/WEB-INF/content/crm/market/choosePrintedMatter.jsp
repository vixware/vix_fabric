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
	pager("start","${vix}/crm/market/printedMatterAction!goSubListContent.action?name="+name+"&pageNo=${pageNo}",'printedMatter');
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
pager("start","${vix}/crm/market/printedMatterAction!goSubListContent.action?name="+name+"&pageNo=${pageNo}",'printedMatter');
 
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#printedMatterOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/market/printedMatterrAction!goSubListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'printedMatter');
}

function printedMatterPager(tag){
	loadName();
	pager(tag,"${vix}/crm/market/printedMatterAction!goSubListContent.action?name="+name+'&parentId='+$('#selectId').val());
}

</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>名称<input id="nameS" name="nameS" type="text" class="int" /></label> <label><input name="" type="button" class="btn" value="搜索" onclick="searchAccount();" /> </label>
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
							</ul></li>
					</ul>
					<div>
						<span><a class="start" onclick="printedMatterPager('start');"></a></span> <span><a class="previous" onclick="printedMatterPager('previous');"></a></span> <em>(<b class="printedMatterInfo"></b> <s:text name='cmn_to' /> <b class="printedMatterTotalCount"></b>)
						</em> <span><a class="next" onclick="printedMatterPager('next');"></a></span> <span><a class="end" onclick="printedMatterPager('end');"></a></span>
					</div>
				</div>
				<div id="printedMatter" class="table"></div>
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
					<div>
						<span><a class="start" onclick="printedMatterPager('start');"></a></span> <span><a class="previous" onclick="printedMatterPager('previous');"></a></span> <em>(<b class="printedMatterInfo"></b> <s:text name='cmn_to' /> <b class="printedMatterTotalCount"></b>)
						</em> <span><a class="next" onclick="printedMatterPager('next');"></a></span> <span><a class="end" onclick="printedMatterPager('end');"></a></span>
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