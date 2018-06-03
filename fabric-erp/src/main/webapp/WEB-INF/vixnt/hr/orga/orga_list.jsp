<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" href="${nvix}/vixntcommon/base/css/hr/font-awesome.min.css">
<link rel="stylesheet" href="${nvix}/vixntcommon/base/css/hr/jquery.orgchart.css">
<link rel="stylesheet" href="${nvix}/vixntcommon/base/css/hr/style.css">
<script type="text/javascript" src="${nvix}/vixntcommon/base/js/hr/jquery.orgchart.js"></script>


<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>人事管理<span>> 组织架构图 </span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>组织架构图</h2>
		</header>
		<div id="chart-container"></div>
	</div>
</div>

<script type='text/javascript'>
	$(function() {
		//数据返回
		$.ajax({
		url : "${nvix}/nvixnt/hr/nvixOrganizeAction!findOrgJson.action",
		type : 'GET',
		success : function(result) {
			$('#chart-container').orgchart({
			'data' : result,
			'nodeContent' : 'title',
			'draggable' : true,
			'dropCriteria' : function($draggedNode, $dragZone, $dropZone) {
				if ($draggedNode.find('.content').text().indexOf('manager') > -1 && $dropZone.find('.content').text().indexOf('engineer') > -1) {
					return false;
				}
				return true;
			}
			}).children('.orgchart').on('nodedropped.orgchart', function(event) {
				console.log('draggedNode:' + event.draggedNode.children('.title').text() + ', dragZone:' + event.dragZone.children('.title').text() + ', dropZone:' + event.dropZone.children('.title').text());
			});
		}
		});
	});
</script>