<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<style>
.hidden {
	display: none;
}
</style>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cog"></i>系统管理 <span>>基础设置 </span><span>>编码管理 </span>
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
					<h2>单据类型</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vixntCodeAction!findOrgAndUnitTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#billTypeCode").val(treeNode.code);
							$.ajax({
							url : '${nvix}/nvixnt/vixntCodeAction!goSaveOrUpdate.action?billTypeCode=' + treeNode.code,
							cache : false,
							success : function(html) {
								$("#right").html(html);
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
						<h2>编码设置</h2>
					</header>
					<div id="right">
						<div class="widget-body">
							<form id="encodingRulesTableInTheMiddleForm" class="form-horizontal">
								<input type="hidden" id="id" name="encodingRulesTableInTheMiddle.id" value="${encodingRulesTableInTheMiddle.id}" /> <input type="hidden" id="billType" name="encodingRulesTableInTheMiddle.billType" value="${encodingRulesTableInTheMiddle.billType}" /> <input type="hidden" id="billTypeCode"
									name="billTypeCode" value="${billTypeCode}" />
								<fieldset>
									<legend>编码类型:</legend>
									<div class="form-group">
										<label class="col-md-2 control-label"></label>
										<div class="col-md-8" id="radioid">
											<div class="radio">
												<label> <input type="radio" class="radiobox style-0" checked="checked" name="encodingRulesTableInTheMiddle.codeType" value="A" onclick="offchecked(this);"> <span>手工录入</span>
												</label>
												<p class="help-block">&nbsp;&nbsp;&nbsp;&nbsp;用户手动输入单据编码.</p>
											</div>
											<div class="radio">
												<label> <input type="radio" class="radiobox style-0" name="encodingRulesTableInTheMiddle.codeType" value="B" onclick="offchecked(this);"> <span>系统生成</span>
												</label>
												<p class="help-block">&nbsp;&nbsp;&nbsp;&nbsp;系统随机生成单据编码.</p>
											</div>
											<div class="radio">
												<label> <input type="radio" class="radiobox style-0" id="codetypeC" name="encodingRulesTableInTheMiddle.codeType" value="C" onclick="onchecked(this);"> <span>编码规则 </span>
												</label>
												<p class="help-block">&nbsp;&nbsp;&nbsp;&nbsp;根据用户设置的编码规则生成相应的单据编码.</p>
											</div>
										</div>
									</div>
									<div id="jobInfo" class="hidden">
										<legend>编码规则:</legend>
										<div class="form-group">
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel1" id="islevel1" value="1" checked disabled="disabled"> <span>一&nbsp;&nbsp;&nbsp;级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level1value" name="encodingRulesTableInTheMiddle.level1value">
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level1value == billTypeCode'>selected</s:if>>单据类型</option>
													</select>
												</div>
											</div>
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel2" id="islevel2" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel2 == 1'>checked</s:if>> <span>二&nbsp;&nbsp;&nbsp;级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level2value" name="encodingRulesTableInTheMiddle.level2value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level2value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level2value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level2value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level2value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel3" id="islevel3" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel3 == 1'>checked</s:if>> <span>三&nbsp;&nbsp;&nbsp;级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level3value" name="encodingRulesTableInTheMiddle.level3value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level3value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level3value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level3value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level3value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel4" id="islevel4" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel4 == 1'>checked</s:if>> <span>四&nbsp;&nbsp;&nbsp;级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level4value" name="encodingRulesTableInTheMiddle.level4value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level4value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level4value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level4value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level4value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel5" id="islevel5" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel5 == 1'>checked</s:if>> <span>五&nbsp;&nbsp;&nbsp;级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level5value" name="encodingRulesTableInTheMiddle.level5value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level5value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level5value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level5value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level5value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel6" id="islevel6" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel6 == 1'>checked</s:if>> <span>六&nbsp;&nbsp;&nbsp;级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level6value" name="encodingRulesTableInTheMiddle.level6value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level6value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level6value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level6value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level6value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel7" id="islevel7" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel7 == 1'>checked</s:if>> <span>七&nbsp;&nbsp;&nbsp;级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level7value" name="encodingRulesTableInTheMiddle.level7value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level7value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level7value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level7value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level7value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel8" id="islevel8" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel8 == 1'>checked</s:if>> <span>八&nbsp;&nbsp;&nbsp;级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level8value" name="encodingRulesTableInTheMiddle.level8value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level8value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level8value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level8value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level8value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel9" id="islevel9" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel9 == 1'>checked</s:if>> <span>九&nbsp;&nbsp;&nbsp;级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level9value" name="encodingRulesTableInTheMiddle.level9value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level9value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level9value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level9value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level9value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel10" id="islevel10" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel10 == 1'>checked</s:if>> <span>十&nbsp;&nbsp;&nbsp;级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level10value" name="encodingRulesTableInTheMiddle.level10value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level10value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level10value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level10value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level10value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel11" id="islevel11" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel11 == 1'>checked</s:if>> <span>十一级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level11value" name="encodingRulesTableInTheMiddle.level11value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level11value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level11value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level11value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level11value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
											<label class="col-md-2 control-label"> <input type="checkbox" class="checkbox style-0" name="encodingRulesTableInTheMiddle.islevel12" id="islevel12" value="1" <s:if test='encodingRulesTableInTheMiddle.islevel12 == 1'>checked</s:if>> <span>十二级:</span>
											</label>
											<div class="col-md-3">
												<div class="icon-addon addon-md">
													<select class="form-control" id="level12value" name="encodingRulesTableInTheMiddle.level12value">
														<option value="">请选择</option>
														<option value="${billTypeCode}" <s:if test='encodingRulesTableInTheMiddle.level12value == billTypeCode'>selected</s:if>>单据类型</option>
														<option value="YYYYMMDD" <s:if test='encodingRulesTableInTheMiddle.level12value == "YYYYMMDD"'>selected</s:if>>年月日</option>
														<option value="YYYYMM" <s:if test='encodingRulesTableInTheMiddle.level12value == "YYYYMM"'>selected</s:if>>年月</option>
														<option value="YYYY" <s:if test='encodingRulesTableInTheMiddle.level12value == "YYYY"'>selected</s:if>>年</option>
													</select>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> <span class="text-danger">*</span> 编&nbsp;码&nbsp;长&nbsp;度:
											</label>
											<div class="col-md-3">
												<input id="codeLength" name="encodingRulesTableInTheMiddle.codeLength" value="${encodingRulesTableInTheMiddle.codeLength}" class="form-control validate[required]" type="text" />
											</div>
											<label class="col-md-2 control-label"><span class="text-danger">*</span> 起&nbsp;&nbsp;&nbsp;始:</label>
											<div class="col-md-3">
												<input id="serialNumberBegin" name="encodingRulesTableInTheMiddle.serialNumberBegin" value="${encodingRulesTableInTheMiddle.serialNumberBegin }" class="form-control validate[required]" type="text" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> <span class="text-danger">*</span> 序号最大值:
											</label>
											<div class="col-md-3">
												<input id="serialNumberEnd" name="encodingRulesTableInTheMiddle.serialNumberEnd" value="${encodingRulesTableInTheMiddle.serialNumberEnd}" class="form-control validate[required]" type="text" />
											</div>
											<label class="col-md-2 control-label"><span class="text-danger">*</span> 步&nbsp;&nbsp;&nbsp;长:</label>
											<div class="col-md-3">
												<input id="serialNumberStep" name="encodingRulesTableInTheMiddle.serialNumberStep" value="${encodingRulesTableInTheMiddle.serialNumberStep }" class="form-control validate[required]" type="text" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label"> 编&nbsp;码&nbsp;预&nbsp;览:</label>
											<div class="col-md-8">
												<input id="codingPreview" name="encodingRulesTableInTheMiddle.codingPreview" value="${encodingRulesTableInTheMiddle.codingPreview}" class="form-control" type="text" />
											</div>
										</div>
									</div>
								</fieldset>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-12">
											<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
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
	$("#encodingRulesTableInTheMiddleForm").validationEngine();
	function saveOrUpdate() {
		if ($("#encodingRulesTableInTheMiddleForm").validationEngine('validate')) {
			$("#encodingRulesTableInTheMiddleForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntCodeAction!saveOrUpdate.action",
			dataType : "text",
			success : function(html) {
				showMessage(html, 'success');
			},
			error : function(html) {
				showMessage(html, 'error');
			}
			});
		} else {
			return false;
		}
	};
	function onchecked(id) {
		if ($(id).is(':checked')) {
			$('#jobInfo').removeClass('hidden');
		}
	};
	function offchecked(id) {
		if ($(id).is(':checked')) {
			$('#jobInfo').addClass('hidden');
		}
	};
</script>