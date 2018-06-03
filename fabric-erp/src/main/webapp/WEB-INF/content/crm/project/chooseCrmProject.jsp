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
	pager("start","${vix}/crm/project/crmProjectAction!goSubListContent.action?name="+name+"&pageNo=${pageNo}",'crmProject');
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
pager("start","${vix}/crm/project/crmProjectAction!goSubListContent.action?name="+name+"&pageNo=${pageNo}",'crmProject');
 
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#crmProjectOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/project/crmProjectrAction!goSubListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'crmProject');
}

function crmProjectPager(tag){
	loadName();
	pager(tag,"${vix}/crm/project/crmProjectAction!goSubListContent.action?name="+name+'&parentId='+$('#selectId').val());
}

</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>名称<input id="nameS" name="nameS" type="text" class="int" /></label> <label><input type="button" class="btn" value="搜索" onclick="searchAccount();" /> </label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="crmProjectPager('start');"></a></span> <span><a class="previous" onclick="crmProjectPager('previous');"></a></span> <em>(<b class="crmProjectInfo"></b> <s:text name='cmn_to' /> <b class="crmProjectTotalCount"></b>)
						</em> <span><a class="next" onclick="crmProjectPager('next');"></a></span> <span><a class="end" onclick="crmProjectPager('end');"></a></span>
					</div>
				</div>
				<div id="crmProject" class="table"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="crmProjectPager('start');"></a></span> <span><a class="previous" onclick="crmProjectPager('previous');"></a></span> <em>(<b class="crmProjectInfo"></b> <s:text name='cmn_to' /> <b class="crmProjectTotalCount"></b>)
						</em> <span><a class="next" onclick="crmProjectPager('next');"></a></span> <span><a class="end" onclick="crmProjectPager('end');"></a></span>
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