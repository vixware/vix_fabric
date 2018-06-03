<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 办公 <span onclick="loadContent('trends','${nvix}/nvixnt/newsAdministrationAction!goList.action');">&gt; 新闻管理</span> <span onclick="loadContent('trends','${nvix}/nvixnt/newsAdministrationAction!goList.action');">&gt; 查看新闻</span>
			</h1>
		</div>

		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						个人新闻查看 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="javascript:void(0);" onclick="loadContent('trends','${nvix}/nvixnt/newsAdministrationAction!goList.action');">新闻管理</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- row -->
	<div class="row">
		<div class="col-sm-12">
			<ul id="myTab1" class="nav nav-tabs bordered">
				<li class="active"><a href="#s1" data-toggle="tab">查看新闻 <i class="fa fa-caret-down"></i></a></li>
			</ul>

			<div id="myTabContent1" class="tab-content bg-color-white padding-10">
				<div class="tab-pane fade in active" id="s1">
					<div class="input-group input-group-lg hidden-mobile">
						<div class="input-group-btn">
							<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">新闻标题</button>
						</div>
						<input class="form-control input-lg" " type="text" name="trends.title" placeholder="填写新闻标题..." id="searchnews">
						<div class="input-group-btn">
							<button onclick="SearchNews()" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchnews').val('');" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</div>
					</div>
					<div class="search-results clearfix" id="newss">
						<s:if test="trendsList.size > 0">
							<s:iterator value="trendsList" var="entity" status="s">
								<h4 class="margin-bottom-10">
									<a href="#" onclick="newsDetail('${entity.id}');"> ${entity.title} </a>
								</h4>
								<div class="margin-bottom-10">
									<p class="note">
										<a href="javascript:void(0);"><i class="fa fa-calendar"></i> <fmt:formatDate value="${entity.createTime}" type="both" pattern="yyyy-MM-dd" />&nbsp;&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-child"></i> ${entity.uploadPersonName}&nbsp;&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-comments"></i>
											${entity.readTimes}条阅读记录&nbsp;&nbsp;</a> <a href="javascript:void(0);"><i class="fa fa-comments"></i> 新闻来源：${entity.sourceFrom}&nbsp;&nbsp;</a>
									</p>
									<div class="url text-success margin-bottom-10">
										<a href="${entity.sourceFromUrl}">${entity.sourceFromUrl}</a>
									</div>
									<p class="description margin-bottom-10">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${entity.content}</p>
								</div>
							</s:iterator>
						</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
//根据ID查看新闻
function newsDetail(id){
	$.ajax({
		  url:'${nvix}/nvixnt/newsAdministrationAction!goNewsDetail.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}

//查看页面搜索新闻
function SearchNews(){
	var searchnews=$("#searchnews").val();
	searchnews=Url.encode(searchnews);
	$.ajax({
		url : '${nvix}/nvixnt/newsAdministrationAction!goNewss.action?title=' + searchnews,
		cache : false,
		success : function(obj){
		$("#newss").html(obj);
		}
	});
};
</script>