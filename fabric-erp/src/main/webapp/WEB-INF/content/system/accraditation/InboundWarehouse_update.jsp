<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	/** input 获取焦点input效果绑定  */
    $(".order_table input[type='text']").focusin(function() {
	    $(this).css({
	    'border' : '1px solid #f00',
	    'background' : '#f5f5f5'
	    });
    });
    $(".order_table  input[type='text']").focusout(function() {
	    $(this).css({
	    'border' : '1px solid #ccc',
	    'background' : '#fff'
	    });
    });
    /* 	if ($ (".ms").length > 0){
     $ (".ms").hover (function (){
     $ (">ul" , this).stop ().slideDown (100);
     } , function (){
     $ (">ul" , this).stop ().slideUp (100);
     });
     $ (".ms ul li").hover (function (){
     $ (">a" , this).addClass ("hover");
     $ (">ul" , this).stop ().slideDown (100);
     } , function (){
     $ (">a" , this).removeClass ("hover");
     $ (">ul" , this).stop ().slideUp (100);
     });
     } */
    $(".newvoucher dt b").click(function() {
	    $(this).toggleClass("downup");
	    $(this).parent("dt").next("dd").toggle();
    });
    $(function() {
	    //设置原单类型选中
	    $("#purchasetypecode").val('${wimStockrecords.purchasetypecode}');
	    //设置入库类别选中
	    $("#bizclasscode").val('${wimStockrecords.bizclasscode}');
	    //加载创建时间(新增)
	    if (document.getElementById("createTime").value == "") {
		    var myDate = new Date();
		    $("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
	    }
    });
</script>
<input type="hidden" id="id" name="wimStockrecords.id" value="${wimStockrecords.id}" />
<dd class="clearfix">
	<div class="order_table">
		<div class="voucher newvoucher">
			<dl>
				<dt>
					<b class="downup"></b> <strong>基本信息</strong>
				</dt>
				<dd style="display: block;">
					<table style="border: none;">
						<tr>
							<td align="right">单据编号：</td>
							<td><input id="code" name="code" value="${wimStockrecords.code}" type="text" size="30" readonly="readonly"></td>
							<td align="right">日期：</td>
							<td><input id="createTime" name="createTime" value="${wimStockrecords.createTime}" type="text" readonly="readonly" /></td>
						</tr>
						<tr>
							<td align="right">入库类别：</td>
							<td><select id="bizclasscode" name="bizclasscode" disabled="disabled">
									<option value="">请选择</option>
									<option value="1" selected="selected">采购入库</option>
									<option value="2">生产入库</option>
									<option value="3">其他入库</option>
							</select></td>
							<td align="right">取单类型：</td>
							<td><select id="purchasetypecode" name="purchasetypecode" disabled="disabled">
									<option value="">请选择</option>
									<option value="1" selected="selected">采购订单</option>
									<option value="2">生产订单</option>
									<option value="3">其他开单</option>
							</select></td>
						</tr>
						<tr>
							<td align="right">仓库：</td>
							<td><input type="hidden" id="warehousecode" name="warehousecode" value="${wimStockrecords.warehousecode}" readonly="readonly" /> <input id="warehousename" name="warehousename" value="" type="text" size="20" /></td>
							<td align="right">业务员：</td>
							<td><input id="personcode" name="personcode" value="${wimStockrecords.personcode }" type="text" size="30" readonly="readonly" /></td>
						</tr>
					</table>
				</dd>
			</dl>
		</div>
	</div>
</dd>
<div class="clearfix" style="background: #FFF; position: relative;">
	<div class="right_menu">
		<ul>
			<li class="current"><a onclick="javascript:$('#a2').attr('style','');tab(4,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
		</ul>
	</div>
	<div id="a2" style="position: relative; z-index: 1; background: #FFF;">
		<script type="text/javascript">
			$('#dlAddress2').datagrid({
            url : '${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=${wimStockrecords.id}',
            width : "100%",
            height : 450,
            nowrap : true,
            autoRowHeight : false,
            striped : true,
            fitColumns : true,
            collapsible : true,
            sortName : 'id',
            sortOrder : 'desc',
            remoteSort : true,
            pagination : true,
            rownumbers : true,
            showFooter : true,
            idField : 'id',
            frozenColumns : [ [ {
            field : 'id',
            title : '编号',
            width : 60,
            hidden : true,
            align : 'center'
            }, {
            field : 'itemname',
            title : '${vv:varView("vix_mdm_item")}名称',
            width : 100,
            align : 'center'
            }, {
            field : 'itemcode',
            title : '${vv:varView("vix_mdm_item")}编码',
            width : 100,
            align : 'center'
            } ] ],
            columns : [ [ {
            field : 'specification',
            title : '规格型号',
            width : 100,
            align : 'center'
            }, {
            field : 'unit',
            title : '单位',
            width : 100,
            align : 'center'
            }, {
            field : 'unitcost',
            title : '单价',
            width : 100,
            align : 'right',
            editor : 'numberbox',
            required : 'true'
            }, {
            field : 'quantity',
            title : '数量',
            width : 100,
            align : 'right',
            editor : 'numberbox',
            required : 'true'
            }, {
            field : 'price',
            title : '金额',
            width : 100,
            align : 'right',
            editor : 'numberbox',
            required : 'true'
            }, {
            field : 'suppliercode',
            title : '供应商',
            width : 100,
            align : 'center'
            }, {
            field : 'producedate',
            title : '生产日期',
            width : 100,
            editor : 'datebox',
            align : 'center',
            formatter : function(val,rec) {
	            if (val != null && val != "") {
		            var d = new Date(val);
		            return format(d);
	            } else
		            return "";
            }
            }, {
            field : 'massdate',
            title : '保质期天数',
            width : 100,
            align : 'center'
            }, {
            field : 'poCode',
            title : '引用单号',
            width : 100,
            align : 'center'
            } ] ]
            });
            function format(date) {
	            var y = date.getFullYear();
	            var m = date.getMonth() + 1;
	            var d = date.getDate();
	            return y + '-' + m + '-' + d;
            }
		</script>
		<div style="padding: 8px;">
			<table id="dlAddress2"></table>
		</div>
	</div>
</div>
<dd class="clearfix">
	<div class="order_table">
		<table>
			<tr>
				<th width="12%">部门：</th>
				<td width="14%"><input id="department" name="department" value="${wimStockrecords.department }" type="text" readonly="readonly" /></td>
				<th width="12%">制单人：</th>
				<td width="14%"><input id="creator" name="creator" value="${wimStockrecords.creator }" type="text" readonly="readonly" /></td>
				<th width="12%">审批人：</th>
				<td width="14%"><input id="approver" name="approver" value="${wimStockrecords.approver }" type="text" readonly="readonly" /></td>
			</tr>
		</table>
	</div>
</dd>