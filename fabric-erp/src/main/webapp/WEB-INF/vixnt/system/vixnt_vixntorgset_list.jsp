<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 系统管理 <span>> 基础设置 </span><span>> 门店接口管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>门店</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vixntDistributionSystemAction!findOrgAndUnitTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$.ajax({
							url : '${nvix}/nvixnt/vixntStoreSetAction!goSaveOrUpdate.action?channelDistributorId=' + treeNode.id + "&treeType=" + treeNode.treeType,
							cache : false,
							success : function(html) {
								$("#jobInfo").html(html);
							}
							});
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<h2>接口设置</h2>
					</header>
					<div>
						<div class="widget-body">
							<form id="channelDistributorSetForm" class="form-horizontal">
								<fieldset>
									<div id="jobInfo">
										<input type="hidden" id="id" name="channelDistributorSet.id" value="${channelDistributorSet.id}" /> <input type="hidden" id="channelDistributorId" name="channelDistributorSet.channelDistributor.id" value="${channelDistributorSet.channelDistributor.id}" />
										<legend>POS端配置信息:</legend>
										<div class="form-group">
											<label class="col-md-2 control-label"> 是否需要同步到门店: </label>
											<div class="col-md-4">
												<div data-toggle="buttons" class="btn-group">
													<label class="btn btn-default <s:if test="channelDistributorSet.isUpload == 0">active</s:if>"> <input type="radio" value="0" id="isUpload" name="channelDistributorSet.isUpload" <s:if test="channelDistributorSet.isUpload == 0">checked="checked"</s:if>>是
													</label> <label class="btn btn-default <s:if test="channelDistributorSet.isUpload == 1">active</s:if>"> <input type="radio" value="1" id="isUpload" name="channelDistributorSet.isUpload" <s:if test="channelDistributorSet.isUpload == 1">checked="checked"</s:if>>否
													</label>
												</div>
											</div>
											<label class="col-md-2 control-label"> 门店内部编码:</label>
											<div class="col-md-4">
												<input id="innerCode" name="channelDistributorSet.innerCode" value="${channelDistributorSet.innerCode}" class="form-control" type="text" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> 门店商品分类接口地址:</label>
											<div class="col-md-10">
												<input id="zf_pos_menutype_upload" name="channelDistributorSet.zf_pos_menutype_upload" value="${channelDistributorSet.zf_pos_menutype_upload}" class="form-control" type="text" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> 门店商品接口地址:</label>
											<div class="col-md-10">
												<input id="zf_pos_menu_upload" name="channelDistributorSet.zf_pos_menu_upload" value="${channelDistributorSet.zf_pos_menu_upload}" class="form-control" type="text" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> 接口调用账号:</label>
											<div class="col-md-4">
												<input id="zf_pos_useraccount" name="channelDistributorSet.zf_pos_useraccount" value="${channelDistributorSet.zf_pos_useraccount}" class="form-control" type="text" />
											</div>
											<label class="col-md-2 control-label"> 接口调用密码:</label>
											<div class="col-md-4">
												<input id="zf_pos_password" name="channelDistributorSet.zf_pos_password" value="${channelDistributorSet.zf_pos_password}" class="form-control" type="password" />
											</div>
										</div>
										<legend>电商端配置信息:</legend>
										<div class="form-group">
											<label class="col-md-2 control-label"> 是否需要同步到电商: </label>
											<div class="col-md-4">
												<div data-toggle="buttons" class="btn-group">
													<label class="btn btn-default <s:if test="channelDistributorSet.isUploadToEc == 0">active</s:if>"> <input type="radio" value="0" id="isUploadToEc" name="channelDistributorSet.isUploadToEc" <s:if test="channelDistributorSet.isUploadToEc == 0">checked="checked"</s:if>>是
													</label> <label class="btn btn-default <s:if test="channelDistributorSet.isUploadToEc == 1">active</s:if>"> <input type="radio" value="1" id="isUploadToEc" name="channelDistributorSet.isUploadToEc" <s:if test="channelDistributorSet.isUploadToEc == 1">checked="checked"</s:if>>否
													</label>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> 电商接口地址:</label>
											<div class="col-md-10">
												<input id="ec_useraccount_upload" name="channelDistributorSet.ec_useraccount_upload" value="${channelDistributorSet.ec_useraccount_upload}" class="form-control" type="text" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> 电商接口调用账号:</label>
											<div class="col-md-4">
												<input id="ec_useraccount" name="channelDistributorSet.ec_useraccount" value="${channelDistributorSet.ec_useraccount}" class="form-control" type="text" />
											</div>
											<label class="col-md-2 control-label"> 电商接口调用密码:</label>
											<div class="col-md-4">
												<input id="ec_password" name="channelDistributorSet.ec_password" value="${channelDistributorSet.ec_password}" class="form-control" type="password" />
											</div>
										</div>
									</div>
								</fieldset>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-12">
											<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
												<i class="fa fa-save"></i> 保存
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	$("#channelDistributorSetForm").validationEngine();
	function saveOrUpdate() {
		if ($("#channelDistributorSetForm").validationEngine('validate')) {
			$("#channelDistributorSetForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntStoreSetAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntStoreSetAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>