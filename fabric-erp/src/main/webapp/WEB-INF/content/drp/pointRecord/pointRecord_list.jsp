<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function searchForContent(){
	loadName();
	pager("start","${vix}/drp/pointRecordAction!goSingleList.action?name="+name,'pointRecord');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/drp/pointRecordAction!goSingleList.action?name="+name,'pointRecord');
//排序 
function orderBy(orderField){
	var orderBy = $("#pointRecordOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/drp/pointRecordAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'pointRecord');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/drp/pointRecordAction!goSingleList.action?name="+name,'pointRecord');
}
//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	$.ajax({
		url : '${vix}/drp/pointRecordAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/drp/pointRecordAction!goSingleList.action?1=1", 'pointRecord');
			});
		}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" />供应链</a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">会员管理</a></li>
				<li><a href="#">忠诚度管理</a></li>
				<li><a href="#">会员积分管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"></b>
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
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_unapproved_plan" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_by_plan" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_in" /></a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="姓名"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="employeeList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.name}</a></li>
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
						<li class="tp"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="pointRecordInfo"></b> <s:text name='cmn_to' /> <b class="pointRecordTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="pointRecord" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="pointRecordInfo"></b> <s:text name='cmn_to' /> <b class="pointRecordTotalCount"></b>)
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