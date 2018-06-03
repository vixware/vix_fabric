<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<link href="${vix}/common/js/vreport/vreport/css/vreport.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/js/vreport/jquery/css/jquery-ui-1.9.1.custom.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/vreport/vprint.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/jquery/jquery-1.8.2.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/jquery/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
<script src="${vix}/common/js/raphael-min.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/flowtask.js" type="text/javascript"></script>

<script type="text/javascript">
function saveOrUpdate() {
		$.post('${vix}/crm/business/marketingAutomationProcessAction!saveOrUpdate.action', {
		'marketingActivities.id' : $("#id").val(),
		'marketingActivities.code' : $("#code").val(),
		'marketingActivities.name' : $("#name").val(),
		'marketingActivities.startTime' : $("#startTime").val(),
		'marketingActivities.endTime' : $("#endTime").val(),
		'marketingActivities.memo' : $("#memo").val(),
		'marketingActivities.activityFlow' : $("#activityFlow").val()
		}, function(result) {
			/* showMessage(result);
			setTimeout("", 1000); */
			loadContent('${vix}/crm/business/marketingAutomationProcessAction!goList.action');
		});
}
//载入分页列表数据
pager("start","${vix}/crm/business/marketingAutomationProcessAction!goProcessLogList.action?1=1",'processLog');
function categoryPager(tag,entity){
	if(entity == 'nodesLog'){
		pager(tag,"${vix}/crm/business/marketingAutomationProcessAction!goNodesLogList.action?1=1",entity);
	}
	if(entity == 'processLog'){
		pager(tag,"${vix}/crm/business/marketingAutomationProcessAction!goProcessLogList.action?1=1",entity);
	}
}
function categoryTab(num,befor,id,e,entity){
	var el=e.target?e.target.parentNode:e.srcElement.parentNode;
	var pa=el.parentNode.getElementsByTagName("li");
	for(var i=0;i<pa.length;i++){
		pa[i].className="";
	}
	el.className="current";
	for(i=1;i<=num;i++){
		try{
			if(i==befor){
				document.getElementById(id+i).style.display="block";
			}else{
				document.getElementById(id+i).style.display="none";
			}
		}catch(e){ }
	}
	if(entity != undefined){
		categoryPager('start',entity);
	}
}
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/money_26x26.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">会员交互管理 </a></li>
				<li><a href="#">会员管理 </a></li>
				<li><a href="#">智能营销 </a></li>
				<li><a href="#">营销流程管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${marketingActivities.id}" />
<div class="content">
	<!-- sub menu -->

	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a onclick="saveOrUpdate()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
						src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/crm/business/marketingAutomationProcessAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</span> <strong><b>营销流程 </b> </strong>
			</dt>
			<dd style="display: block;" class="clearfix">
				<div class="order_table">
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b class="downup"></b> <strong>流程信息</strong>
							</dt>
							<dd style="display: block;">
								<div id="contentDiv"></div>
								<script>
									   var report = new Rreport({
										contentDiv : 'contentDiv',
										isEdit : true,
										saveFun : function(data) {
											 $("#activityFlow").val(data); 
										},
										});
										$('#vreporttoolbar').attr({ style : 'display:none'});
										report.loadData(${activityFlow});
										report.monitorNode(${executingProcessData});
							      </script>
							</dd>
						</dl>
					</div>
					<div class="clearfix" style="background: #FFF; position: relative;">
						<div id="right">
							<div class="right_menu">
								<ul>
									<li class="current"><a onclick="categoryTab(5,1,'a',event,'processLog')"><img src="img/holidaysam.png" alt="" />流程日志</a></li>
									<!-- <li><a onclick="categoryTab(5,2,'a',event,'nodesLog')"><img src="img/holidaysam.png" alt="" />节点日志</a></li> -->
								</ul>
							</div>
							<div class="right_content" id="a1">
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
									<strong><s:text name="cmn_selected" />:<span id="selectBrandCount1">0</span></strong>
									<div>
										<span><a class="start" onclick="categoryPager('start','processLog');"></a></span> <span><a class="previous" onclick="categoryPager('previous','processLog');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name='cmn_to' /> <b class="processLogTotalCount">${pager.totalCount}</b>)
										</em> <span><a class="next" onclick="categoryPager('next','processLog');"></a></span> <span><a class="end" onclick="categoryPager('end','processLog');"></a></span>
									</div>
								</div>
								<div id="processLog" class="table"></div>
								<div class="pagelist drop">
									<ul>
										<li class="ed"><a href="#"><s:text name="cmn_choose" /></a>
											<ul>
												<li><a href="#"><s:text name="cmn_delete" /></a></li>
												<li><a href="#"><s:text name="cmn_email" /></a></li>
												<li><a href="#"><s:text name="merger" /></a></li>
												<li><a href="#"><s:text name="export" /></a></li>
											</ul></li>
									</ul>
									<strong><s:text name="cmn_selected" />:<span id="selectBrandCount2">0</span></strong>
									<div>
										<span><a class="start" onclick="categoryPager('start','processLog');"></a></span> <span><a class="previous" onclick="categoryPager('previous','processLog');"></a></span> <em>(${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize} <s:text name="cmn_to" /> <b class="processLogTotalCount">${pager.totalCount}</b>)
										</em> <span><a class="next" onclick="categoryPager('next','processLog');"></a></span> <span><a class="end" onclick="categoryPager('end','processLog');"></a></span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</dd>
		</dl>
	</div>
	<!-- dialog -->
</div>