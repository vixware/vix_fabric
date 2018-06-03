<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="memberTagForm" class="form-horizontal" style="padding: 35px 15px;" action="">
	<input type="hidden" id="customerId" name="customerAccount.id" value="${customerAccount.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label">标签库:</label>
						<div class="col-md-9" id="taglib">
							<c:forEach items="${memberTagList}" var="entity">
								<div class="btn-group">
									<button class="btn btn-sm btn-info" type="button" onclick="check('${entity.id}')">
										${entity.name}
									</button>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">新增标签:
						</label>
						<div class="col-md-9">
							<div class="jarviswidget jarviswidget-color-gray">
								<header>
									<h2>
										<strong></strong> <i>自定义标签</i>
									</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<textarea class="form-control no-border validate[required]" rows="4" id="msgContent" name="memberTag.name"> ${memberTag.name}</textarea>
										<div class="widget-footer">
											<button class="btn btn-sm btn-success" type="button" onclick="saveOrUpdate();">
												<i class="fa fa-save"></i>&nbsp;保存
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">已打标签:</label>
						<div class="col-md-9" id="tag">
							<c:forEach items="${customerAccounMmemberTagList}" var="customerEntity">
								<div class="btn-group">
									<button class="btn btn-sm btn-warning" type="button" onclick="checked('${customerEntity.id}')">
										${customerEntity.name}&nbsp;&nbsp;&nbsp;<i class="fa fa-times" style="position: absolute;right: 2px;top: 2px;"></i>
									</button>
								</div>
							</c:forEach>
						</div>
					</div>
				</fieldset>
			</form>
<script type="text/javascript">
	var id = $("#customerId").val();
	function check(tagId){
		$.ajax({
			url:'${nvix}/nvixnt/nvixCustomerAccountAction!makeTagForCustomer.action',
			type:'post',
			async:true,
			data:{'id':id,'tagId':tagId},
			success:function(result){
				showMessage(result);
				$("#tag").load("${nvix}/nvixnt/nvixCustomerAccountAction!loadMemberTag.action?id="+id);
				$("#taglib").load("${nvix}/nvixnt/nvixCustomerAccountAction!loadMemberTagLib.action?id="+id);
			}
		});
	}
	function saveOrUpdate(){
		var memberTagName = $('#msgContent').val();
		if(!memberTagName|| memberTagName == " "){
			showMessage("请输入标签!");
			return;
		}
		$.ajax({
			url:'${nvix}/nvixnt/nvixCustomerAccountAction!saveTagAndMake.action',
			type:'post',
			data:{'id':id,'memberTagName':memberTagName},
			success:function(result){
				showMessage(result);
				$('#msgContent').val('');
				$("#tag").load("${nvix}/nvixnt/nvixCustomerAccountAction!loadMemberTag.action?id="+id);
				$("#taglib").load("${nvix}/nvixnt/nvixCustomerAccountAction!loadMemberTagLib.action?id="+id);
			}
		});
	}
	function checked(tagId){
		/* $.ajax({
			url:'${nvix}/nvixnt/nvixCustomerAccountAction!deleteTag.action',
			type:'post',
			data:{'id':id,'tagId':tagId},
			success:function(result){
				layer.alert(result);
				$("#tag").load("${nvix}/nvixnt/nvixCustomerAccountAction!loadMemberTag.action?id="+id);
				$("#taglib").load("${nvix}/nvixnt/nvixCustomerAccountAction!loadMemberTagLib.action?id="+id);
			}
		}); */
		layer.confirm("是否删除用户标签?", {title:"提示信息"}, function(index){
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			$.ajax({
				url:'${nvix}/nvixnt/nvixCustomerAccountAction!deleteTag.action?id='+id+"&tagId="+tagId,
				cache: false,
				success: function(result){
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					showMessage(result);
					$("#tag").load("${nvix}/nvixnt/nvixCustomerAccountAction!loadMemberTag.action?id="+id);
					$("#taglib").load("${nvix}/nvixnt/nvixCustomerAccountAction!loadMemberTagLib.action?id="+id);
					if(typeof func == 'function'){
			    		func();
			    	}
				},error: function(XMLHttpRequest, textStatus, errorThrown) {
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					layer.alert("系统错误，请联系管理员");
				}
			});
		    layer.close(index);
		});
	}
</script>