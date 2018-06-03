<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#tableChoose tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#tableChoose tr:even").addClass("alt");
pager("start","${vix}/system/enumerationAction!goSubSingleList.action?temp=1","subEnumeration");
//排序 
function subOrderBy(orderField){
	var orderBy = $("#subEnumerationOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/system/enumerationAction!goSubSingleList.action?orderField="+orderField+"&orderBy="+orderBy,"subEnumeration");
}
function subEnumerationPager(tag){
	pager(tag,"${vix}/system/enumerationAction!goSubSingleList.action?name="+name,'subEnumeration');
}

function currentPagerClick(input,event){
	pagerClick(input,event,"${vix}/system/enumerationAction!goSubSingleList.action?name="+name,'subEnumeration');
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
						<span><a class="start" onclick="subEnumerationPager('start');"></a></span> <span><a class="previous" onclick="subEnumerationPager('previous');"></a></span> <em>(<b class="subEnumerationInfo"></b> <s:text name='cmn_to' /> <b class="subEnumerationTotalCount"></b>)
						</em> <span><a class="next" onclick="subEnumerationPager('next');"></a></span> <span><a class="end" onclick="subEnumerationPager('end');"></a></span>
					</div>
				</div>
				<div id="subEnumeration" class="table"></div>
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
						<span><a class="start" onclick="subEnumerationPager('start');"></a></span> <span><a class="previous" onclick="subEnumerationPager('previous');"></a></span> <em>(<b class="subEnumerationInfo"></b> <s:text name='cmn_to' /> <b class="subEnumerationTotalCount"></b>)
						</em> <span><a class="next" onclick="subEnumerationPager('next');"></a></span> <span><a class="end" onclick="subEnumerationPager('end');"></a></span>
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