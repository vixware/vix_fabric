<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
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
    /** 保存图书借用 */
    function saveOrUpdateBookLibrarian() {
	    if ($('#order').validationEngine('validate')) {
		    $.post('${vix}/oa/bookLibrarianAction!saveOrUpdate.action', {
		    'bookRegister.id' : $("#id").val(),
		    'bookRegister.bookNumber' : $("#bookNumber").val(),
		    'bookRegister.bookNames' : $("#bookNames").val(),
		    'bookRegister.bookCodes' : $("#bookCodes").val(), 
		    'bookRegister.recipientsWho' : $("#recipientsWho").val(),
		    'bookRegister.isTemp':$('input:radio[name=isTemp]').val(),
		    'bookRegister.createTime' : $("#createTime").val()
		    }, function(result) {
			    showMessage(result);
			    setTimeout("", 1000);
			    loadContent('${vix}/oa/bookLibrarianAction!goList.action?');
		    });
	    }
    }
    
    /** 保存并新增书借用 */
    function saveOrAdd(){
    	if($('#order').validationEngine('validate')){
    		$.post('${vix}/oa/bookLibrarianAction!saveOrUpdate.action',{									  
    			'bookRegister.id' : $("#id").val(),
    		    'bookRegister.bookNumber' : $("#bookNumber").val(),
    		    'bookRegister.bookNames' : $("#bookNames").val(),
    		    'bookRegister.bookCodes' : $("#bookCodes").val(), 
    		    'bookRegister.recipientsWho' : $("#recipientsWho").val(),
    		    'bookRegister.isTemp':$('input:radio[name=isTemp]').val(),
    		    'bookRegister.createTime' : $("#createTime").val()
    			},
    			function(result){
    				showMessage(result);
    				setTimeout("",1000);
    				loadContent('${vix}/oa/bookLibrarianAction!goSaveOrUpdate.action');
    			} 
    		);
    	}
    }
    
    $("#order").validationEngine();
    
    $(function() {
        //加载借用时间(新增)
        if (document.getElementById("createTime").value == "") {
    	    var myDate = new Date();
    	    $("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
        }
    });
    
    /**借书人*/
    function chooseEmployees(){
    	$.ajax({
    		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
    		  data:{chkStyle:"checkbox"},
    		  cache: false,
    		  success: function(html){
    			  asyncbox.open({
    				 	modal:true,
    					width : 800,
    					height : 600,
    					title:"选择借书人",
    					html:html,
    					callback : function(action,returnValue){
    						if(action == 'ok'){
    							if(returnValue != undefined){
    								var selectIds = "";
    								var selectNames = "";
    								var result = returnValue.split(",");
    								for (var i=0; i<result.length; i++){
    									if(result[i].length>1){
    										var v = result[i].split(":");
    										
    										selectIds += "," + v[0];
    										selectNames = v[1];
    									}
    								}
    								$("#recipientsWho").val(selectNames);
    							}
    							
    						}
    					},
    					btnsbar : $.btn.OKCANCEL
    				});
    		  }
    	});
    }
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a></i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');"> <img src="img/oa_office_Supplies.png" alt="" /> 协同办公
				</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');">图书管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');">借还书管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');">图书借用</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${bookRegister.id}" />
<div class="content">
	<form id="order">
		<input type="hidden" id="" name="bookRegister.isTemp" value="1" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateBookLibrarian();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action?pageNo=${pageNo}');"> <img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="图书借用" /> </b><i></i> </strong>
				</dt>
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
											<th>编码：</th>
											<td><input id="bookCodes" name="code1" value="${bookRegister.bookCodes}" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>借书证号：</th>
											<td><input id="bookNumber" name="bookRegister.bookNumber" value="${bookRegister.bookNumber}" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>书名：</th>
											<td><input id="bookNames" name="bookNames" value="${bookRegister.bookNames }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>借书人：</th>
											<td><input id="recipientsWho" name="recipientsWho" value="${bookRegister.recipientsWho }" type="text" size="30" class="validate[required] text-input"> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>借用时间：</th>
											<td><input id="createTime" name="bookRegister.createTime" value="${bookRegister.createTime}" type="text" class="validate[required] text-input" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr style="display: none;">
											<td><s:radio list="#{'0':'借用','1':'归还'}" id="isTemp" name="isTemp" value="%{bookRegister.isTemp==0}" theme="simple"></s:radio></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />借用明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
						function updateBookBorrow(){
							var row = $('#dlAddress2').datagrid('getSelected');
							if(row){
								showBookBorrow(row.id);
							}else{
								showMessage("请选择一条数据!");
							}
						}
							function showBookBorrow(id) {
								$.ajax({
									  url:'${vix}/oa/bookLibrarianAction!goAddbookBorrowing.action?id='+id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
											 	width : 1000,
												height : 540,
												title:"图书明细",
												html:html,
												callback : function(action){
													if(action == 'cancel' || action == 'close'){
														$('#dlAddress2').datagrid("reload");
													}
												},
												btnsbar : [{
													text    : '关闭',
													action  : 'cancel'
												}]
											});
									  }
								});
                            }
                            $('#dlAddress2').datagrid({
                            url : '${vix}/oa/bookLibrarianAction!getBookBorrowJson.action?id=${bookRegister.id}',
                            width : 'auto',
                            height : 450,
                            pagination : true,
                            rownumbers : true,
                            sortOrder : 'desc',
                            striped : true,
                            frozenColumns : [ [ {
                            field : 'id',
                            title : '编号',
                            width : 60,
                            align : 'center'
                            }, {
                            field : 'code',
                            title : '图书编码',
                            width : 100,
                            align : 'center'
                            } ] ],
                            columns : [ [ {
                            field : 'bookName',
                            title : '图书名称',
                            width : 100,
                            align : 'center'
                            }, {
                            field : 'ISBN',
                            title : 'ISBN',
                            width : 100,
                            align : 'center'
                            },{
                            field : 'bookType',
                            title : '类型',
                            width : 100,
                            align : 'center'
                            },{
                            field : 'author',
                            title : '作者',
                            width : 100,
                            align : 'center'
                            },{
                            field : 'press',
                            title : '出版社',
                            width : 100,
                            align : 'center'
                            },{
                            field : 'address',
                            title : '存放地点',
                            width : 100,
                            align : 'center'
                            },{
                            field : 'amount',
                            title : '存放数量',
                            width : 100,
                            align : 'center'
                            }, {
                            field : 'borrowNumber',
                            title : '借用数量',
                            width : 100,
                            align : 'right',
                            editor : 'numberbox',
                            required : 'true'
                            } ] ],
                            toolbar : [ {
                            id : 'da2Btnadd',
                            text : '添加',
                            iconCls : 'icon-add',
                            handler : function() {
	                            showBookBorrow(0);
                            }
                            }, '-', {
                            id : 'btnedit',
                            text : '修改',
                            iconCls : 'icon-edit',
                            handler : function() {
	                            var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
	                            if (row) {
	                            	updateBookBorrow();
	                            }
                            }
                            }, '-', {
                            text : '删除',
                            iconCls : 'icon-remove',
                            handler : function() {
	                            var rows = $('#dlAddress2').datagrid("getSelections"); //获取你选择的所有行	
	                            //循环所选的行
	                            for ( var i = 0; i < rows.length; i++) {
		                            var index = $('#dlAddress2').datagrid('getRowIndex', rows[i]);//获取某行的行号
		                            $('#dlAddress2').datagrid('deleteRow', index); //通过行号移除该行
		                            $.ajax({
		                            url : '${vix}/oa/bookLibrarianAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
		                            cache : false
		                            });
	                            }
                            }
                            } ]
                            });
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
			</dl>
		</div>
	</form>
</div>
