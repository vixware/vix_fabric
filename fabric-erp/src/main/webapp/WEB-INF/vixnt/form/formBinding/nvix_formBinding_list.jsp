<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-file-text-o "></i>表单管理 <span> > 表单角色绑定 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goChooseBusinessFormTemplate();">
						<i class="glyphicon glyphicon-plus"></i>&nbsp; 绑定
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>角色</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vreport/nvixFormBindingAction!findOrgAndUnitTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>业务表列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<iframe src="${flow_ip}/form/businessform/vix_businessform_list" style="width: 100%; height: 550px; border-style: none; margin: 0px; padding: 0px;"> </iframe>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	function goChooseBusinessFormTemplate() {
		if ($("#selectId").val() != '' && $("#selectId").val() != null) {
			chooseListData('${nvix}/nvixnt/vreport/nvixFormBindingAction!goChooseBusinessFormTemplate.action?parentId=' + $("#selectId").val(), 'multi', '选择表单', null, function() {
				var emp = chooseMap.valueSetWithClear().split(":");
				if (emp != '' && emp.length == 2) {
					var businessFormId = emp[0].split(",");
					$.ajax({
					url : '${nvix}/nvixnt/vreport/nvixFormBindingAction!binding.action?businessFormId=' + businessFormId[0] + "&roleId=" + $("#selectId").val(),
					cache : false,
					success : function(result) {
						showMessage(result);
					}
					});
				} else {
					layer.alert("请选择表单!");
				}
			}, 750, 550);
		} else {
			layer.alert("请选择角色!");
		}
	};
</script>