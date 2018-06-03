<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<s:iterator value="pager.resultList" var="entity" status="s">
	<li>
		<div class="file-list-list clearfix">
			<div class="pull-right file-buttons">
				<a href="#" onclick="downloadUploader('${entity.id }');"><i class="fa fa-download"></i></a><a href="#" onclick="deleteUploaderById('${entity.id }');"><i class="fa fa-trash-o"></i></a>
			</div>
			<div class="file-body">
				<div class="clearfix">
					<span class="smart-form pull-left"><label class="checkbox"><input type="checkbox" name="checkbox" value="${entity.id }"> <i></i>&nbsp;</label></span>
					<div class="file-icon">
						<c:if test='${entity.fileType == "xlsx"}'>
							<img src="${nvix}/vixntcommon/base/images/icon-01.png">
						</c:if>
						<c:if test='${entity.fileType == "xls"}'>
							<img src="${nvix}/vixntcommon/base/images/icon-01.png">
						</c:if>
						<c:if test='${entity.fileType == "docx"}'>
							<img src="${nvix}/vixntcommon/base/images/icon-08.png">
						</c:if>
						<c:if test='${entity.fileType == "doc"}'>
							<img src="${nvix}/vixntcommon/base/images/icon-08.png">
						</c:if>
						<c:if test='${entity.fileType == "txt"}'>
							<img src="${nvix}/vixntcommon/base/images/icon-04.png">
						</c:if>
						<c:if test='${entity.fileType == "pdf"}'>
							<img src="${nvix}/vixntcommon/base/images/icon-10.png">
						</c:if>
						<div class="file-layer">
							${entity.creator } 创建于 ${fn:substring(entity.createTime, 0,19)}
							<div class="clearfix">
								<div class="pull-right layer-buttons">
									<a href="javascript:;" class="comment-btn" onclick="downloadUploader('${entity.id }');"><i class="fa fa-download"></i></a> <a href="#" onclick="deleteUploaderById('${entity.id }');"><i class="fa fa-trash-o"></i></a>
								</div>
							</div>
						</div>
					</div>
					<div class="file-line">
						<div class="file-title">${entity.fileName }</div>
						<div class="file-desc">${entity.creator }创建于${fn:substring(entity.createTime, 0,19)}</div>
					</div>
				</div>
			</div>
		</div>
	</li>
</s:iterator>
