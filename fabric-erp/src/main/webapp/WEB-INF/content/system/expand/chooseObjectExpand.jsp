<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
pager("start","${vix}/system/objectExpandAction!goSubSingleList.action?name="+name,'objectExpandSub');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#objectExpandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("current","${vix}/system/objectExpandAction!goSubSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'objectExpandSub');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/system/objectExpandAction!goSubSingleList.action?name="+name,'objectExpandSub');
}

function currentPagerClick(input,event){
	loadName();
	pagerClick(input,event,"${vix}/system/objectExpandAction!goSubSingleList.action?name="+name,'objectExpandSub');
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
				<div id="objectExpandSub" class="table"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="objectExpandSubInfo"></b> <s:text name='cmn_to' /> <b class="objectExpandSubTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
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