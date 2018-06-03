<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var account = "";
function loadAccount(){
	account = $('#accountS').val();
	account=Url.encode(account);
	account=Url.encode(account);
}

function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/security/userAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 540,
					height : 350,
					title:"账号信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$.post('${vix}/security/userAction!saveOrUpdate.action',
								 {'user.id':$("#id").val(),
								  'user.userName':$("#userName").val(),
								  'deleteRoleIds':$("#deleteRoleIds").val(),
								  "addRoleIds":$("#addRoleIds").val()
								},
								function(result){
									showMessage(result);
									setTimeout("", 1000);
									pager("start","${vix}/security/userAction!goSingleList.action?account="+account,'userL');
								}
							 );
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

function deleteById(id){
	$.ajax({
		  url:'${vix}/security/userAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(result){
			  showMessage(result);
			  setTimeout("", 1000);
			  pager("start","${vix}/security/userAction!goSingleList.action?account="+account,'userL');
		  }
	});
}

function searchForContent(){
	loadAccount();
	pager("start","${vix}/security/userAction!goSingleList.action?account="+account,'userL');
}

loadAccount();
//载入分页列表数据
pager("start","${vix}/security/userAction!goSingleList.action?account="+account,'userL');
//排序 
function orderBy(orderField){
	loadAccount();
	var orderBy = $("#userLOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/security/userAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&account="+account,'userL');
}

$("#search_advanced").toggle(
		function () {
			$("#c_head").addClass("advanced");
			$("#search_advanced").text("Basic Search")
		  },
		  function () {
			$("#c_head").removeClass("advanced");
			$("#search_advanced").text("Advanced Search")
		  }
);
function currentPager(tag){
	loadAccount();
	pager(tag,"${vix}/security/roleAction!goSingleList.action?name="+name,'userL');
}


// 用户账户的列表
function goToUserAccountList(id){
	$.ajax({
		  url:'${vix}/security/userAccountAction!goList.action',
		  data: {'userId':id},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 400,
					title:"账号信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">打印</a> <a href="#"><img alt="" src="img/icon_15.gif">帮助</a>
		</i> <strong><img alt="" src="img/drp_channel.png">用户</strong>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span>添加</span></a>
		</p>
	</div>
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
			<li><a href="#"><img alt="" src="img/icon_10.png">索引</a></li>
			<li><a href="#"><img alt="" src="img/icon_10.png">数据分类</a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">热点分类</a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png">信息</a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">信息</a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">信息</a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">信息</a></li>
				</ul></li>
		</ul>
		<div>
			<label>Name<input type="text" class="int" id="accountS"></label> <label>My items<input type="checkbox" value="" name=""></label> <label><input type="button" value="搜索" class="btn" onclick="searchForContent();"><input type="button" value="重置" class="btn" name=""></label> <strong id="search_advanced">高级搜索</strong>
		</div>
		<div class="search_advanced">
			<label>名称<input type="text" class="int" name=""></label> <label>我的项目<input type="checkbox" value="" name=""></label> <label><input type="button" value="搜索" class="btn" name=""><input type="button" value="重置" class="btn" name=""></label> <label>名称<input type="text" class="int" name=""></label> <label>我的项目<input
				type="checkbox" value="" name=""></label> <label><input type="button" value="搜索" class="btn" name=""><input type="button" value="重置" class="btn" name=""></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#">选项</a>
							<ul>
								<li><a href="#">删除</a></li>
								<li><a href="#">邮件</a></li>
								<li><a href="#">批量更新</a></li>
								<li><a href="#">合并</a></li>
								<li><a href="#">添加到目标列表</a></li>
								<li><a href="#">导出</a></li>
							</ul></li>
					</ul>
					<strong>选中:<span id="selectCount">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="userLInfo"></b>到 <b class="userLTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="userL" class="table" style="height: 468px;"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#">选项</a>
							<ul>
								<li><a href="#">删除</a></li>
								<li><a href="#">邮件</a></li>
								<li><a href="#">批量更新</a></li>
								<li><a href="#">合并</a></li>
								<li><a href="#">添加到目标列表</a></li>
								<li><a href="#">导出</a></li>
							</ul></li>
					</ul>
					<strong>选中:<span id="selectCount">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="userLInfo"></b>到 <b class="userLTotalCount"></b>)
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