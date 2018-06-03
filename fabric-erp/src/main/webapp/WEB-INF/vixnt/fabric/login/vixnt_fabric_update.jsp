<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content" class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm"></div>
		<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
			<div class="well no-padding">
				<form id="attributeForm" class="smart-form client-form">
					<header> 登录信息 </header>
					<fieldset>
						<section>
							<label class="label">账户:</label> <label class="input"> <input type="text" name="username">
							</label>
						</section>
						<section>
							<label class="label">密码:</label> <label class="input"> <input type="password" name="password">
							</label>
						</section>
						<section>
							<label class="label"> 公司名称:</label> <select id="orgName" name="orgName" class="form-control validate[required]">
								<option value="org1">中盛</option>
							</select>
						</section>
					</fieldset>
					<footer>
						<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">登录</button>
					</footer>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#attributeForm").validationEngine();
	function saveOrUpdate() {
		if ($("#attributeForm").validationEngine('validate')) {
			$("#attributeForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntFabricAction!login.action",
			dataType : "text",
			success : function(data) {
				loadContent('', '${nvix}/nvixnt/vixntFabricBillAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>