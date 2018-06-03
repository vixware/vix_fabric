<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	if(name != "undefined"){
		//name=Url.encode(name);
		//name=Url.encode(name);
	}
}
 
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/common/taskAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 880,
					height : 420,
					title:"BOM结构",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#bomNodeForm').validationEngine('validate')){
								$.post('${vix}/mdm/bom/bomNodeAction!saveOrUpdate.action',
									 {'bomNode.id':$("#id").val(),
									  'bomNode.codeRule':$("#codeRule").val(),
									  'bomNode.parentItemCatalog.id':$("#parentItemCatalogId").val(),
									  'bomNode.name':$("#name").val(),
									  'bomNode.preExamineStandard':$("#preExamineStandard").val(),
									  'bomNode.prePurchaseDays':$("#prePurchaseDays").val(),
									  'bomNode.preProduceDays':$("#preProduceDays").val(),
									  'bomNode.preExamineDays':$("#preExamineDays").val(),
									  'bomNode.preBackupDays':$("#preBackupDays").val(),
									  'bomNode.preInventoryUnit':$("#preInventoryUnit").val(),
									  'bomNode.preBatchCode':$("#preBatchCode").val(),
									  'bomNode.preVirtualItem':$("#preVirtualItem").val(),
									  'bomNode.isSingalItemDelivery':$("#isSingalItemDelivery").val(),
									  'bomNode.preWholePackaged':$("#preWholePackaged").val(),
									  'bomNode.preSingal':$("#preSingal").val(),
									  'bomNode.preCycleCheck':$("#preCycleCheck").val(),
									  'bomNode.preAttritionRate':$("#preAttritionRate").val(),
									  'bomNode.preAttritionDelivery':$("#preAttritionDelivery").val(),
									  'bomNode.preCustomhouseProductCode':$("#preCustomhouseProductCode").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
											if(null != treeNode){
												treeNode.isParent = true;
											} 
											zTree.reAsyncChildNodes(treeNode, "refresh");
											pager("current","${vix}/mdm/bom/bomNodeAction!goListContent.action?name="+name+"&id="+$("#selectId").val(),'bomNode');
										});
									}
								 );
							}else{
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

function saveOrUpdateBomNode(url,id){
	loadContent(url+"?id="+id);
}
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
 
function deleteById(id){
	$.ajax({
		  url:'${vix}/common/taskAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/common/taskAction!goListContent.action?name="+name,'task');
			});
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/common/taskAction!goListContent.action?name="+name,'task');
}
 
loadName();
//载入分页列表数据
//pager("start","${vix}/common/taskAction!goListContent.action?name="+name,'task');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#taskOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/taskAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'task');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/common/taskAction!goListContent.action?name="+name,'task');
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">工作台</a></li>
				<li><a href="#" onclick="loadContent('${vix}/common/taskAction!goList.action');">代办任务</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并创建</span></a> <a href="#"><span>关闭</span></a>
		</p>
		<ul>
			<li><a href="#"><span>帮助</span></a>
				<ul>
					<li><a href="#">帮助信息</a></li>
					<li><a href="#">帮助信息</a></li>
					<li><a href="#">帮助信息</a></li>
					<li><a href="#">帮助信息</a></li>
				</ul></li>
		</ul>
	</div>
</div>
<!-- sub menu -->
<div class="content">
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
		<ul class="c_head_tabbtn">
			<li class="current"><a href="javascript:void(0);" onclick="javascript:tab(8,1,'newtab',event)"><img alt="" src="img/icon_10.png">工作总结</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:tab(8,2,'newtab',event)"><img alt="" src="img/icon_10.png">数据参考</a></li>
		</ul>
		<div>
			<label>Name<input name="" type="text" class="int" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label> <strong id="search_advanced" class="zh">高级搜索</strong>
		</div>
		<div class="search_advanced">
			<label>名称<input name="" type="text" class="int" /></label> <label>我的项目<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label> <label>名称<input name="" type="text" class="int" /></label> <label>我的项目<input name=""
				type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div id="newtab1">
				<div class="addleft">
					<div class="addbtn">
						<p>
							<img src="img/address_book.png" /> 查看日报
						</p>
						<span>-某用户周月汇总</span>
					</div>
					<p>2007-12-25(星期二)</p>
					<div id="date1" class="date_box"></div>
					<script language="javascript" type="text/javascript">
						WdatePicker({eCont:'date1',dateFmt:'yyyy-MM-dd',skin:'blue'});
					</script>

					<div class="addbox">
						<div class="addtitle">人员选择：日报</div>
						<p>
							<a href="#">杜洪博</a> <a href="#">李度</a> <a href="#">张彬</a> <a href="#">赵敏</a> <a href="#">李莉</a> <a href="#">刘北海</a> <a href="#">刘东东</a> <a href="#">马和平</a> <a href="#">吴天明</a> <a href="#">赵露</a>
						</p>
					</div>
				</div>
				<div class="addright">
					<p class="drt">
						<em><a href="#">本日全部日报快速浏览</a> <a href="#">月度日报状态</a></em>过去5天新日报/回复：25号<a href="#">0</a> 26号<a href="#">0</a> 27号<a href="#">0</a> 28号<a href="#">0</a> 29号<a href="#">0</a>
					</p>
					<div class="daily_box">
						<h2 class="blue">2007-12-25(星期二) 日报 杜洪博</h2>
						<h3>数据参考</h3>
						<h4 class="blue">办理任务</h4>
						<table class="list tc">
							<tr>
								<th class="tl">任务主题</th>
								<th>今日创建</th>
								<th>需今日结束</th>
								<th>完成活推进</th>
								<th>结束</th>
								<th>传递</th>
							</tr>
							<tr>
								<td class="tl"><a href="#">准备自动储物柜的报价</a></td>
								<td>■</td>
								<td>■</td>
								<td>■</td>
								<td>■</td>
								<td>◆</td>
							</tr>
							<tr>
								<td class="tl"><a href="#">准备自动储物柜的报价</a></td>
								<td>■</td>
								<td>■</td>
								<td>■</td>
								<td>■</td>
								<td>◆</td>
							</tr>
							<tr>
								<td class="tl"><a href="#">准备自动储物柜的报价</a></td>
								<td>■</td>
								<td>■</td>
								<td>■</td>
								<td>■</td>
								<td>◆</td>
							</tr>
							<tr>
								<td class="tl"><a href="#">准备自动储物柜的报价</a></td>
								<td>■</td>
								<td>■</td>
								<td>■</td>
								<td>■</td>
								<td>◆</td>
							</tr>
							<tr>
								<td class="tl"><a href="#">准备自动储物柜的报价</a></td>
								<td>■</td>
								<td>■</td>
								<td>■</td>
								<td>■</td>
								<td>◆</td>
							</tr>
						</table>
						<h4 class="blue">日程</h4>
						<table class="list">
							<tr>
								<td><a href="#">拜访，了解服务情况</a> 2007-12-25 14:00 【上海菲奥德】</td>
							</tr>
							<tr>
								<td><a href="#">拜访，了解服务情况</a> 2007-12-25 14:00</td>
							</tr>
							<tr>
								<td><a href="#">拜访，了解服务情况</a> 2007-12-25 14:00</td>
							</tr>
							<tr>
								<td><a href="#">拜访，了解服务情况</a> 2007-12-25 14:00</td>
							</tr>
							<tr>
								<td><a href="#">拜访，了解服务情况</a> 2007-12-25 14:00</td>
							</tr>
						</table>
						<h4 class="blue">行动历史</h4>
						<table class="list">
							<tr>
								<td><a href="#">拜访库管，配合技术测量画图</a> 2007-12-25 14:00 【上海菲奥德】</td>
							</tr>
							<tr>
								<td><a href="#">拜访库管，配合技术测量画图</a> 2007-12-25 14:00 【上海菲奥德】</td>
							</tr>
							<tr>
								<td><a href="#">拜访库管，配合技术测量画图</a> 2007-12-25 14:00 【上海菲奥德】</td>
							</tr>
							<tr>
								<td><a href="#">拜访库管，配合技术测量画图</a> 2007-12-25 14:00 【上海菲奥德】</td>
							</tr>
							<tr>
								<td><a href="#">拜访库管，配合技术测量画图</a> 2007-12-25 14:00 【上海菲奥德】</td>
							</tr>
							<tr>
								<td><a href="#">拜访库管，配合技术测量画图</a> 2007-12-25 14:00 【上海菲奥德】</td>
							</tr>
						</table>
						<h4>写回复</h4>
						<form action="" method="get">
							<textarea class="area" name="" cols="" rows=""></textarea>
							<br />
							<input name="" type="button" value="确认回复" class="btn" />
						</form>
					</div>
				</div>
			</div>
			<div id="newtab2" style="display: none;">
				<div class="addleft">
					<div class="addbtn">
						<p>
							<img src="img/address_book.png" /> 日期选择
						</p>
						<span>2009-11-02 - 2009-11-02</span>
					</div>
					<div id="date2" class="date_box"></div>
					<script language="javascript" type="text/javascript">
						WdatePicker({eCont:'date2',dateFmt:'yyyy-MM-dd',skin:'blue'});
					</script>
					<div class="addbox">
						<ul class="ul_list">
							<li><a href="#">看 当月 数据</a></li>
							<li><a href="#">看 第44周 数据</a></li>
							<li><a href="#">看 第45周 数据</a></li>
							<li><a href="#">看 第46周 数据</a></li>
							<li><a href="#">看 第47周 数据</a></li>
							<li><a href="#">看 第48周 数据</a></li>
							<li><a href="#">看 第49周 数据</a></li>
						</ul>
					</div>
					<div class="addbox">
						<p>
							<a href="#">杜洪博</a> <a href="#">李度</a> <a href="#">张彬</a> <a href="#">赵敏</a> <a href="#">李莉</a> <a href="#">刘北海</a> <a href="#">刘东东</a> <a href="#">马和平</a> <a href="#">吴天明</a> <a href="#">赵露</a>
						</p>
					</div>
				</div>
				<div class="addright">
					<p class="drt clearfix">
						<em>调至：<a href="#">5</a>号 <a href="#">10</a>号 <a href="#">20</a>号 <a href="#">25</a>号 <a href="#">30</a>号
						</em>
					</p>
					<h2>2009-11-02(周一) - 2009-11-02(周一) 王天虹</h2>
					<p class="drt clearfix">
						<span class="gray">注意：未写日报的日期不再下面显示</span>
					</p>
					<div class="addbox">
						<div class="addtitle">2009-10-28[星期三]</div>
						<div class="addbox_content">
							<p class="gray">上午注册了一个QQ和MSN并加入了同事的联系方式，另外看了咱们公司的资料，感觉CRM软件内容很多，想搞清除需要花费一番功夫。</p>
							<p class="gray">下午接收培训，通过培训对公司的基本概况有一个简要了解，例如公司名称及网址、公司Logo、公司产品、公司服务方式及服务对象、公司成长历史及在业界的地位等。有些都有助于我们在日后为客户服务过程中介绍公司。</p>
							<p>&nbsp;</p>
							<p class="gray">
								<b>批示和回复</b>
							</p>
							<p class="gray">批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复</p>
						</div>
					</div>
					<div class="addbox">
						<div class="addtitle">2009-10-28[星期三]</div>
						<div class="addbox_content">
							<p class="gray">上午注册了一个QQ和MSN并加入了同事的联系方式，另外看了咱们公司的资料，感觉CRM软件内容很多，想搞清除需要花费一番功夫。</p>
							<p class="gray">下午接收培训，通过培训对公司的基本概况有一个简要了解，例如公司名称及网址、公司Logo、公司产品、公司服务方式及服务对象、公司成长历史及在业界的地位等。有些都有助于我们在日后为客户服务过程中介绍公司。</p>
							<p>&nbsp;</p>
							<p class="gray">
								<b>批示和回复</b>
							</p>
							<p class="gray">批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复</p>
						</div>
					</div>
					<div class="addbox">
						<div class="addtitle">2009-10-28[星期三]</div>
						<div class="addbox_content">
							<p class="gray">上午注册了一个QQ和MSN并加入了同事的联系方式，另外看了咱们公司的资料，感觉CRM软件内容很多，想搞清除需要花费一番功夫。</p>
							<p class="gray">下午接收培训，通过培训对公司的基本概况有一个简要了解，例如公司名称及网址、公司Logo、公司产品、公司服务方式及服务对象、公司成长历史及在业界的地位等。有些都有助于我们在日后为客户服务过程中介绍公司。</p>
							<p>&nbsp;</p>
							<p class="gray">
								<b>批示和回复</b>
							</p>
							<p class="gray">批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复</p>
						</div>
					</div>
					<div class="addbox">
						<div class="addtitle">2009-10-28[星期三]</div>
						<div class="addbox_content">
							<p class="gray">上午注册了一个QQ和MSN并加入了同事的联系方式，另外看了咱们公司的资料，感觉CRM软件内容很多，想搞清除需要花费一番功夫。</p>
							<p class="gray">下午接收培训，通过培训对公司的基本概况有一个简要了解，例如公司名称及网址、公司Logo、公司产品、公司服务方式及服务对象、公司成长历史及在业界的地位等。有些都有助于我们在日后为客户服务过程中介绍公司。</p>
							<p>&nbsp;</p>
							<p class="gray">
								<b>批示和回复</b>
							</p>
							<p class="gray">批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复批示和回复</p>
						</div>
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