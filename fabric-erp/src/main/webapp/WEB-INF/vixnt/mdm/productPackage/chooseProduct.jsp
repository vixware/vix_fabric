<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}"/>
<input type="hidden" id="chooseEcitemStatus" value="${status}"/>
<input type="hidden" id="itemCategoryIdForChoose" value=""/>
<div class="jarviswidget" style="padding: 6px;">
	<header style="height: 1px;border-color:#ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin:5px;">
				<div class="col-md-4">
					<div id="treeMenuForChoose" class="input-group">
						<input id="itemCategoryNameForChoose" type="text" onfocus="showMenuForChoose(); return false;" value="" placeholder="分类" type="text" class="form-control"/>
						<div class="input-group-btn">
							<button type="button" class="btn btn-default" onclick="$('#itemCategoryIdForChoose').val('');$('#itemCategoryNameForChoose').val('');">
								清空
							</button>
						</div>
						<div id="menuContentForChoose" class="menuContent" style="display:none; position: absolute;border:1px solid gray;background-color: #dfdfdf;z-index: 9999;">
							<ul id="itemCategoryTreeForChoose" class="ztree"></ul>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<input type="text" value="" class="form-control" placeholder="名称" id="searchNameForChoose">
				</div>
				<button onclick="chooseEcitemTable.ajax.reload();chooseMap.clear();" type="button" class="btn btn-info">
					<i class="glyphicon glyphicon-search"></i> 检索
				</button>
				<button onclick="$('#itemCategoryIdForChoose').val('');$('#itemCategoryNameForChoose').val('');$('#searchNameForChoose').val('');chooseEcitemTable.ajax.reload();chooseMap.clear();" type="button" class="btn btn-default">
					<i class="glyphicon glyphicon-repeat"></i> 清空
				</button>
			</div>
		    <table id="chooseEcitem" class="table table-striped table-bordered table-hover" width="100%">
		   		<thead>			                
					<tr>
						<th width="10%">
							<s:if test="chooseType == 'multi'">
								<div class="btn-group">
									<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default" aria-expanded="false">
										选择 <i class="fa fa-caret-down"></i>
									</button>
									<ul class="dropdown-menu js-status-update pull-left">
										<li><a id="all" onclick="changeDataTableSelect('chooseEcitem','all');" href="javascript:void(0);">全选</a></li>
										<li><a id="reverse" onclick="changeDataTableSelect('chooseEcitem','reverse');" href="javascript:void(0);">反选</a></li>
										<li><a id="cancle" onclick="changeDataTableSelect('chooseEcitem','cancle');" href="javascript:void(0);">取消</a></li>
									</ul>
								</div>
							</s:if>
							<s:else>选择</s:else>
						</th>
						<th width="20%">编码</th>
						<th width="50%">名称</th>
						<th width="10%">价格</th>
					</tr>
				</thead>
		    </table>
		 </div>
	</div>
</div>
<script type="text/javascript">
	/** 数据选择类型 */
	var chooseType = $("#chooseType").val();
	
	var chooseEcitemColumns = [
		{"orderable":false,"data" : function(data) {
			if(chooseType == 'single'){
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "','" + data.price + "','" + data.sku + "');\"/>";
			}
			return "";
		}},
		{"name":"","data" : function(data) {return data.code;}},
		{"name":"","data" : function(data) {
			var epImage = data.mainImagePath;
			if(epImage == null || epImage == ""){
				return data.name;
			}else{
				var result = data.name + "&nbsp;&nbsp;<span style=\"cursor: pointer;font-weight: bold;\" onmouseover=\"$('#epImageShow_"+data.id+"').attr('style','position: absolute;padding:-50px;');\" onmouseout=\"$('#epImageShow_"+data.id+"').attr('style','display:none;');\">";
				result += "<img src=\"${snow}/"+epImage+"\" onerror=\"$(this).attr('src','${snow}/resources/backstage/common/image/default.png')\" width='20' height='20'/>";
				result += "</span><img id=\"epImageShow_"+data.id+"\" src=\"${snow}/"+epImage+"\" onerror=\"$('#epImageShow_"+data.id+"').attr('src','${snow}/resources/backstage/common/image/default.png')\" width='100' height='100' style='display:none;position: absolute;'/>"
				return result;
			}
		}},
		{"name":"price","data" : function(data) {return data.price;}}
	];
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var chooseEcitemTable = initDataTable("chooseEcitem","${nvix}/nvixnt/mdm/nvixntItemAction!goSingleList.action",chooseEcitemColumns,function(page,pageSize,orderField,orderBy){
  		var status = $("#chooseEcitemStatus").val();
  		var itemCategoryId = $("#itemCategoryIdForChoose").val();
  		var name = $("#searchNameForChoose").val();
		name=Url.encode(name);
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"treeId":itemCategoryId,"itemName":name,"status":status};
	},10,selectType,isGenerateIndex);

	var showMenuForChoose = initDropListTree("treeMenuForChoose","${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action","itemCategoryIdForChoose","itemCategoryNameForChoose","itemCategoryTreeForChoose","menuContentForChoose");
</script>