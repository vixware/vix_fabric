<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>




<script type="text/javascript">
<!--
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	$('#dgdA1').datagrid({
		title:'语言能力明细  ',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/json_tests/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{field:'ship',title:'员工编码  ',width:110,
				formatter:function(value,rec){
					return '<span>谢朝阳  </span>';
				}
			},   
			{field:'invoicedocument',title:'开始日  ',width:130,
				formatter:function(value,rec){
					return '<span>00003258 </span>';
				}
			}
		]],
		columns:[[
			{field:'specification',title:'至',width:150,
				formatter:function(value,rec){
					return '<span>18620128796 </span>';
				}
			},
			{field:'itemType',title:'语种 ',width:150,
				formatter:function(value,rec){
					return '<span>vixware@163.com</span>';
				}	
			},
			{field:'amount',title:'熟练程度',width:150,
				formatter:function(value,rec){
					return '<span>北京市朝阳区学院路15号  </span>';
				}
			},
			{field:'masterlanguagelevel',title:'掌握语种水平级别',width:150,
				formatter:function(value,rec){
					return '<span>北京市朝阳区学院路15号  </span>';
				}
			},
			{field:'typeofcertificate',title:'证书类型',width:150,
				formatter:function(value,rec){
					return '<span>北京市朝阳区学院路15号  </span>';
				}
			},
			{field:'othertypeofcertificate',title:'其他证书类型',width:150,
				formatter:function(value,rec){
					return '<span>北京市朝阳区学院路15号  </span>';
				}
			},
			{field:'certificatenumber',title:'证书编号',width:150,
				formatter:function(value,rec){
					return '<span>北京市朝阳区学院路15号  </span>';
				}
			},
			{field:'score',title:'成绩',width:150,
				formatter:function(value,rec){
					return '<span>北京市朝阳区学院路15号  </span>';
				}
			},
			{field:'certificateintime',title:'证书取得时间',width:150,
				formatter:function(value,rec){
					return '<span>北京市朝阳区学院路15号  </span>';
				}
			},
			{field:'certificationbody',title:'认证机构',width:150,
				formatter:function(value,rec){
					return '<span>北京市朝阳区学院路15号  </span>';
				}
			},
			{field:'remarks',title:'备注',width:150,
				formatter:function(value,rec){
					return '<span>北京市朝阳区学院路15号  </span>';
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$.ajax({
					  url:'${vix}/template/toolAction!addOaListItem.action?tag=addOaListItem3',
					  cache: false,
					  success: function(html){
						  asyncbox.open({
								 modal:true,
								 width : 900,
								 height : 400,
								 title:"新建文章",
								 content:html,
								 btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			}
		},{
			id:'btncut',
			text:'修改',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#tt').datagrid('getSelected');
				if (row){
					var index = $('#tt').datagrid('getRowIndex', row);
					$('#tt').datagrid('deleteRow', index);
				}
			}
		},'-',{
			id:'btnsave',
			text:'保存',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	$('#dgdA2').datagrid({
		title:'计算机水平明细  ',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/json_tests/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{field:'orderCode',title:'员工编码',width:110,
				formatter:function(value,rec){
					return '<span>人资专员 </span>';
				}
			},   
			{field:'contry',title:'开始日 ',width:100,
				formatter:function(value,rec){
					return '<span>人力资源部 </span>';
				}
			},
			{field:'province',title:'至',width:100,
				formatter:function(value,rec){
					return '<span>发布撤销招聘信息</span>';
				}
			},
			{field:'city',title:'计算机水平级别 ',width:100,
				formatter:function(value,rec){
					return '<span>四级 </span>';
				}	
			},
			{field:'obtaincertificatename',title:'取得证书名称 ',width:100,
				formatter:function(value,rec){
					return '<span>四级 </span>';
				}	
			},
			{field:'othercertificatename',title:'其他证书名称 ',width:100,
				formatter:function(value,rec){
					return '<span>四级 </span>';
				}	
			},
			{field:'certificatetoobtainime',title:'证书取得时间 ',width:100,
				formatter:function(value,rec){
					return '<span>四级 </span>';
				}	
			},
			{field:'remark',title:'备注  ',width:100,
				formatter:function(value,rec){
					return '<span>四级 </span>';
				}	
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$.ajax({
					  url:'${vix}/template/toolAction!addOaListItem.action?tag=addOaListItem3',
					  cache: false,
					  success: function(html){
						  asyncbox.open({
								 modal:true,
								 width : 900,
								 height : 400,
								 title:"新建文章",
								 content:html,
								 btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			}
		},{
			id:'btncut',
			text:'修改',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#tt').datagrid('getSelected');
				if (row){
					var index = $('#tt').datagrid('getRowIndex', row);
					$('#tt').datagrid('deleteRow', index);
				}
			}
		},'-',{
			id:'btnsave',
			text:'保存',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	$('#dgdA3').datagrid({
		title:'技术工人职业资格明细 ',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/json_tests/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{field:'itemCode',title:'员工编码',width:110,
				formatter:function(value,rec){
					return '<span>02159642</span>';
				}
			},   
			{field:'itemName',title:'专业技术资格系列 ',width:200,
				formatter:function(value,rec){
					return '<span>否 </span>';
				}
			}
		]],
		columns:[[
			{field:'amount',title:'专业技术资格名称',width:100,
				formatter:function(value,rec){
					return '<span>手机验证</span>';
				}
			},
			{field:'recieveWareHouse',title:'级别 ',width:200,
				formatter:function(value,rec){
					return '<span>9999-12-31</span>';
				}
			},
			{field:'materialBatches',title:'评定机构',width:100,
				formatter:function(value,rec){
					return '<span>18546213695</span>';
				}
			},
			{field:'accreditationinstitution',title:'认定机构',width:100,
				formatter:function(value,rec){
					return '<span>18546213695</span>';
				}
			},
			{field:'whetherthesystemcall',title:'是否系统外调入',width:100,
				formatter:function(value,rec){
					return '<span>18546213695</span>';
				}
			},
			{field:'whetherTheNationalProfessionalQualificationExamination',title:'是否国家专业技术资格考试',width:100,
				formatter:function(value,rec){
					return '<span>18546213695</span>';
				}
			},
			{field:'whetherThroughTheIdentificationOf',title:'是否通过认定',width:100,
				formatter:function(value,rec){
					return '<span>18546213695</span>';
				}
			},
			{field:'ifTheHighestQualification',title:'是否最高资格',width:100,
				formatter:function(value,rec){
					return '<span>18546213695</span>';
				}
			},
			{field:'certificateNo',title:'资格证号',width:100,
				formatter:function(value,rec){
					return '<span>18546213695</span>';
				}
			},
			{field:'fileNumber',title:'文件号',width:100,
				formatter:function(value,rec){
					return '<span>18546213695</span>';
				}
			},
			{field:'remark',title:'备注 ',width:100,
				formatter:function(value,rec){
					return '<span>18546213695</span>';
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$.ajax({
					  url:'${vix}/template/toolAction!addOaListItem.action?tag=addOaListItem3',
					  cache: false,
					  success: function(html){
						  asyncbox.open({
								 modal:true,
								 width : 900,
								 height : 400,
								 title:"新建文章",
								 content:html,
								 btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			}
		},{
			id:'btncut',
			text:'修改',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#tt').datagrid('getSelected');
				if (row){
					var index = $('#tt').datagrid('getRowIndex', row);
					$('#tt').datagrid('deleteRow', index);
				}
			}
		},'-',{
			id:'btnsave',
			text:'保存',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	
	$('#dgdA4').datagrid({
		title:'学历学位情况明细',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/json_tests/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
			{field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'itemType',title:'员工编码',width:100,
				formatter:function(value,rec){
					return '<span>电子产品固件</span>';
				}	
			},
			{field:'netTotal',title:'开始日',width:300,
				formatter:function(value,rec){
					return '<span>2,000,000</span>';
				}
			},
			{field:'coin',title:'到',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'educationalBackground',title:'学历',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'academicCertificates',title:'学历证书',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'admissionTime',title:'入学时间',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'educationCertificateNumber',title:'学历证书编号',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'professionalCategoryCode',title:'专业门类代码',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'professionalOneDisciplinaryCode',title:'专业一级学科代码',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'professionalTwoDisciplinaryCode',title:'专业二级学科代码',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'formsOfLearning',title:'学习形式',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'schoolSystem',title:'学制',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'schoolName',title:'学校名称',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'whether211Or985',title:'是否211或985',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'whetherTheHighestDegree',title:'是否最高学历',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'whetherTheOriginalQualifications',title:'是否原始学历',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'degree',title:'学位',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'diplomaCoding',title:'学位证书编码',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'degreeGrantingCountries',title:'学位授予国家',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'degreeAwarding',title:'学位授予单位',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'degreeGrantedWhetherTheHighestDegree',title:'是否最高学位',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'whetherMinorInTheSecondDegree',title:'是否辅修二学位',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'minorSecondDegreeName',title:'辅修二学位名称',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'remark',title:'备注',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$.ajax({
					  url:'${vix}/template/toolAction!addOaListItem.action?tag=addOaListItem3',
					  cache: false,
					  success: function(html){
						  asyncbox.open({
								 modal:true,
								 width : 900,
								 height : 400,
								 title:"新建文章",
								 content:html,
								 btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			}
		},{
			id:'btncut',
			text:'修改',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#tt').datagrid('getSelected');
				if (row){
					var index = $('#tt').datagrid('getRowIndex', row);
					$('#tt').datagrid('deleteRow', index);
				}
			}
		},'-',{
			id:'btnsave',
			text:'保存',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	
	$('#dgdA5').datagrid({
		title:'培训情况明细',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/json_tests/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
			{field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'itemType',title:'员工编码',width:100,
				formatter:function(value,rec){
					return '<span>营业执照</span>';
				}	
			},
			{field:'type',title:'培训类别',width:100,
				formatter:function(value,rec){
					return '<span>word</span>';
				}	
			},
			{field:'amount',title:'主办层次',width:300,
				formatter:function(value,rec){
					return '<span>这是我们公司的营业执照...</span>';
				}
			},
			{field:'netTotal',title:'培训项目名称',width:200,
				formatter:function(value,rec){
					return '<span>D:\appendix\营业执照.jpg</span>';
				}
			},
			{field:'netPrice',title:'培训对象',width:100,
				formatter:function(value,rec){
					return '<span>2013-04-23</span>';
				}
			},
			{field:'operate',title:'培训内容',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'trainingForm',title:'培训形式',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'trainingMaterials',title:'培训教材',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'trainingInstructor',title:'培训讲师',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'planTime',title:'计划时间',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'cassHour',title:'学时',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'peopleNumber',title:'人数',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'periods',title:'期数',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'trainingAgency',title:'培训机构',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'contacts',title:'联系人',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'contactNumber',title:'联系电话',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			},
			{field:'remark',title:'备注',width:100,
				formatter:function(value,rec){
					return '<span><a href="#">下载</a></span>';
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$.ajax({
					  url:'${vix}/template/toolAction!addOaListItem.action?tag=addOaListItem3',
					  cache: false,
					  success: function(html){
						  asyncbox.open({
								 modal:true,
								 width : 900,
								 height : 400,
								 title:"新建文章",
								 content:html,
								 btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			}
		},{
			id:'btncut',
			text:'修改',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#tt').datagrid('getSelected');
				if (row){
					var index = $('#tt').datagrid('getRowIndex', row);
					$('#tt').datagrid('deleteRow', index);
				}
			}
		},'-',{
			id:'btnsave',
			text:'保存',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	$('#dgdA6').datagrid({
		title:'专业技术职务明细',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/json_tests/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{field:'code',title:'员工编码',width:110,
				formatter:function(value,rec){
					return '<span>iCode20130514</span>';
				}
			},   
			{field:'name',title:'专业技术资格系列',width:200,
				formatter:function(value,rec){
					return '<span>营业执照</span>';
				}
			}
		]],
		columns:[[
			{field:'itemType',title:'专业技术资格名称',width:100,
				formatter:function(value,rec){
					return '<span>电子产品固件</span>';
				}	
			},
			{field:'amount',title:'级别',width:100,
				formatter:function(value,rec){
					return '<span>10000</span>';
				}
			},
			{field:'netTotal',title:'评定机构',width:100,
				formatter:function(value,rec){
					return '<span>2,000,000</span>';
				}
			},
			{field:'accreditationInstitution',title:'认定机构',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'whetherTheSystemCall',title:'是否系统外调入',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'whetherTheNationalProfessionalQualificationExamination',title:'是否国家专业技术资格考试',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'whetherThroughTheIdentificationOf',title:'是否通过认定',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'ifTheHighestQualification',title:'是否最高资格',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'certificateNo',title:'资格证号',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'fileNumber',title:'文件号',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'remark',title:'备注',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$.ajax({
					  url:'${vix}/template/toolAction!addOaListItem.action?tag=addOaListItem3',
					  cache: false,
					  success: function(html){
						  asyncbox.open({
								 modal:true,
								 width : 900,
								 height : 400,
								 title:"新建文章",
								 content:html,
								 btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			}
		},{
			id:'btncut',
			text:'修改',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#tt').datagrid('getSelected');
				if (row){
					var index = $('#tt').datagrid('getRowIndex', row);
					$('#tt').datagrid('deleteRow', index);
				}
			}
		},'-',{
			id:'btnsave',
			text:'保存',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	
	$('#dgdA7').datagrid({
		title:'专业技术成果明细',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/json_tests/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{field:'itemCode',title:'员工编码',width:110,
				formatter:function(value,rec){
					return '<span>iCode20130514</span>';
				}
			},   
			{field:'itemName',title:'专家名称',width:200,
				formatter:function(value,rec){
					return '<span>4.0寸手机外壳</span>';
				}
			}
		]],
		columns:[[
			{field:'specification',title:'专家层级',width:100,
				formatter:function(value,rec){
					return '<span>sfn4.0</span>';
				}
			},
			{field:'itemType',title:'专业类别',width:100,
				formatter:function(value,rec){
					return '<span>电子产品固件</span>';
				}	
			},
			{field:'amount',title:'专业名称',width:100,
				formatter:function(value,rec){
					return '<span>10000</span>';
				}
			},
			{field:'netTotal',title:'专家等级',width:100,
				formatter:function(value,rec){
					return '<span>2,000,000</span>';
				}
			},
			{field:'netPrice',title:'专业技术成果',width:100,
				formatter:function(value,rec){
					return '<span>200</span>';
				}
			},
			{field:'coin',title:'年度考评结果',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			},
			{field:'materialBatches',title:'状态',width:100,
				formatter:function(value,rec){
					return '<span>3</span>';
				}
			},
			{field:'batch',title:'备注',width:100,
				formatter:function(value,rec){
					return '<span>7</span>';
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				$.ajax({
					  url:'${vix}/template/toolAction!addOaListItem.action?tag=addOaListItem3',
					  cache: false,
					  success: function(html){
						  asyncbox.open({
								 modal:true,
								 width : 900,
								 height : 400,
								 title:"新建文章",
								 content:html,
								 btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			}
		},{
			id:'btncut',
			text:'修改',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut')
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#tt').datagrid('getSelected');
				if (row){
					var index = $('#tt').datagrid('getRowIndex', row);
					$('#tt').datagrid('deleteRow', index);
				}
			}
		},'-',{
			id:'btnsave',
			text:'保存',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save')
			}
		}]
	});
	
	$('#dgdA8').datagrid({
		title:'相关附件',
		iconCls:'icon-save',
		width:"auto",
		height:550,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/json_tests/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
			{field:'ck',checkbox:true},
			{field:'code',title:'企业编码',width:110,
				formatter:function(value,rec){
					return '<span>iCode20130514</span>';
				}
			},   
			{field:'name',title:'部门编码',width:200,
				formatter:function(value,rec){
					return '<span>营业执照</span>';
				}
			},
			{field:'amount',title:'审批人编码',width:100,
				formatter:function(value,rec){
					return '<span>10000</span>';
				}
			}
		]],
		columns:[[
			{field:'itemType',title:'审批人',width:100,
				formatter:function(value,rec){
					return '<span>电子产品固件</span>';
				}	
			},
			{field:'netTotal',title:'审批意见',width:100,
				formatter:function(value,rec){
					return '<span>2,000,000</span>';
				}
			},
			{field:'coin',title:'审批时间',width:100,
				formatter:function(value,rec){
					return '<span>RMB</span>';
				}
			}
		]]
	});
	
	var p = $('#test').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			alert('before refresh');
		}
	});
	
function addOrderItem(){
	$.ajax({
		  url:'${vix}/template/simpleGridAction!saveOrUpdateCustomer.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 440,
					title:"客户信息",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							 
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function addOrderMemo(){
	$.ajax({
		  url:'${vix}/template/simpleGridAction!addOrderMemo.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 1024,
					height : 540,
					title:"客户信息",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							 
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function resize(){
	$('#test').datagrid('resize', {
		width:700,
		height:400
	});
}
function getSelected(){
	var selected = $('#test').datagrid('getSelected');
	if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	}
}
function getSelections(){
	var ids = [];
	var rows = $('#test').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].code);
	}
	alert(ids.join(':'));
}
function clearSelections(){
	$('#test').datagrid('clearSelections');
}
function selectRow(){
	$('#test').datagrid('selectRow',2);
}
function selectRecord(){
	$('#test').datagrid('selectRecord','002');
}
function unselectRow(){
	$('#test').datagrid('unselectRow',2);
}
function mergeCells(){
	$('#test').datagrid('mergeCells',{
		index:2,
		field:'addr',
		rowspan:2,
		colspan:2
	});
}
function chooseChkCustomer(){
	$.ajax({
		  url:'${vix}/template/simpleGridAction!goChooseChkSimpleGrid.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 440,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								$("#customerChk").html(returnValue);
							}else{
								$("#customerChk").html("");
								asyncbox.success("请选择分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function saveOrUpdateCustomer(){
	$.ajax({
		  url:'${vix}/template/simpleGridAction!saveOrUpdateCustomer.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 440,
					title:"客户信息",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							 
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseRadioCustomer(){
	$.ajax({
		  url:'${vix}/template/simpleGridAction!goChooseRadioSimpleGrid.action',
		  cache: false,
		  success: function(html){
			  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
			  $(".ab_c .content").css("margin-bottom","0");
			  $('.ab_c .content').parent().css('position','relative');
			  asyncbox.open({
				 	modal:true,
					width : 1160,
					height : 600,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								$("#customerRadio").html(returnValue);
							}else{
								$("#customerRadio").html("");
								asyncbox.success("请选择分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
//提示
if($('input.sweet-tooltip').length){
	$('input.sweet-tooltip').focus(function() {
		tooltip				= $(this);
		tooltipText 		= tooltip.attr('data-text-tooltip');
		tooltipClassName	= 'tooltip';
		tooltipClass		= '.' + tooltipClassName;
		
		if(tooltip.hasClass('showed-tooltip')) return false;
		
		tooltip.addClass('showed-tooltip')
				 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');

		
		tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
		tooltipPosLeft = tooltip.position().left;
		tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
		
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop })
							.animate({'opacity':'1', 'marginTop':'0'}, 500);
		
	}).focusout(function() {
		
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
				
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
	JT_init();
}
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
function saveOrUpdateOrder(){
	$.post('${vix}/template/orderAction!saveOrUpdate.action',
		{'order.id':$("#id").val(),
		  'order.code':$("#code").val(),
		  'order.memo':$("#memo").val()
		},
		function(result){
			asyncbox.success(result,"提示信息",function(action){
				loadContent('${vix}/template/orderAction!goList.action');
			});
		}
	 );
}
function chooseProduct(){
	$.ajax({
		  url:'${vix}/template/productAction!goChooseProduct.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 1000,
					height : 520,
					title:"选择商品",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							$.ajax({
								url:'${vix}/template/orderAction!saveOrUpdateOrderItem.action?id='+$("#id").val() + "&productIds="+returnValue,
								cache: false,
								success: function(result){
									asyncbox.success(result,"提示信息",function(action){
										pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?id="+$("#id").val(),'orderUpdate');
									});
								}
							});
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
function currentPager(tag){
	pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
}
$("#order").validationEngine();
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#"><s:text name="hr_staff_manage" /></a></li>
				<a href="#"><s:text name="hr_emp_informa_manage" /></a>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCustomer();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消"
							src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a href="#"><img
							width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" alt="打印"
							src="${vix}/common/img/dt_print.png"></a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a>
					</span> <strong> <b>员工信息管理个人技能</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">

										<tr>
											<td align="right">员工编码：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">姓名：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">曾用名：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">姓名缩写</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">员工职号：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">身份证号：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">血型：</td>
											<td><select style="width: 50"><option>请选择</option></select></td>
											<td align="right">学历代号：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">科系代号：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">户籍地址：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">性别：</td>
											<td><select style="width: 50"><option>请选择</option></select></td>
											<td align="right">出生日期：</td>
											<td><input id="birthDate" name="" type="text" /> <img onclick="showTime('birthDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">年龄：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">出生地：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
									</table>
									<!-- 
								<p>
									<i><s:text name="summary"/>123：</i><input name="" type="text" size="80"/><a href="#" onclick="addOrderMemo();">[维护常用摘要]</a>
								</p>
								<p>
									<span><i><s:text name="time"/>：</i><input id="time9" name="" type="text" /> <img onclick="showTime('time9','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></span>
								</p>
								<p>
									<span><i><s:text name="email"/>：</i><input name="" type="text" class="validate[required,custom[email]] text-input"/></span>
									<span><i><s:text name="hobby"/>：</i>
										<input type="checkbox" value="1" id="maxcheck4" name="group2" class="validate[maxCheckbox[2]] checkbox">&nbsp;读书&nbsp;&nbsp;
										<input type="checkbox" value="2" id="maxcheck5" name="group2" class="validate[maxCheckbox[2]] checkbox">&nbsp;登山&nbsp;&nbsp;
										<input type="checkbox" value="3" id="maxcheck6" name="group2" class="validate[maxCheckbox[2]] checkbox">&nbsp;游泳&nbsp;&nbsp;
									</span>
								</p>
								<p>
									<span>
										<i>性别：</i>
										<input id="radio2" name="group0" type="radio" value="1" class="validate[required] radio"/>男 
										<input id="radio3" name="group0" type="radio" value="0" class="validate[required] radio"/>女 
									</span>
								</p>
								<p>
									<span><i>商品数量：</i><input name="" type="text" class="validate[required,custom[integer]] text-input sweet-tooltip" data-text-tooltip="我是提示文字"/></span>
									<span><i>贷方金额：</i>
									<input id="money" name="" type="text"/>
									<script type="text/javascript">
									$(function(){
										$('#money').priceFormat({
										    prefix: '',
										    centsLimit: 3
										});
									});
										
									</script>
									</span>
									<span><input name="" type="button" value="下一行" class="btn" /></span>
								</p>
								<p><i>商品数量：</i><span><textarea id="textExt" class="example" rows="1" style="width:155px"></textarea></span></p>
							 -->
									<script type="text/javascript">
							$(document).ready(function(){
								$('#textExt').textext({
									plugins : 'autocomplete'
									}).bind('getSuggestions', function(e, data){
										var list = [
												'Acdsee',
												'Basic',
												'Closure',
												'Cobol',
												'Delphi',
												'Erlang',
												'Fortran',
												'Go',
												'Groovy',
												'Haskel',
												'Im',
												'Java',
												'JavaScript',
												'King',
												'Leo',
												'Menu',
												'No',
												'OCAML',
												'PHP',
												'Perl',
												'Python',
												'Query',
												'Ruby',
												'Scala',
												'Time',
												'UTC',
												'Vv',
												'What',
												'Xx',
												'Yang',
												'Zero'
											],
											textext = $(e.target).textext()[0],
											query = (data ? data.query : '') || '';
										$(this).trigger(
											'setSuggestions',{ result : textext.itemManager().filter(list, query) }
										);
									});
								});
							</script>
								</dd>
							</dl>
						</div>
						<%-- 	<div class="voucher newvoucher">
						<dl>
							<dt>
								<b></b>
								<span style="display: none;">
									<a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									<a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									<a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
								</span>
								<strong>销售信息</strong>
							</dt>
							
							<dd style="display: none;">
								<!-- 
								<p>
									<i>摘要：</i><input name="" type="text" size="80" /><a href="javascript:void(0);" onclick="saveOrUpdate();">[维护常用摘要]</a>
								</p>
								<p>
									<span><i>科目：</i><input name="" type="text" /></span>
								</p>
								<p>
									<span><i>币别：</i><input name="" type="text" /></span>
									<span><i>汇率：</i><input name="" type="text" /></span>
								</p>
								<p>
									<span><i>原币：</i></span>
								</p>
								<p>
									<span><i>借方金额：</i><input name="" type="text" /></span>
									<span><i>贷方金额：</i><input name="" type="text" /></span>
									<span><input name="" type="button" value="下一行" class="btn" /></span>
								</p>
								 -->
								 <table style="border: none;">
								 	<tr>
										<td align="right">请求交货日期：</td>
										<td><input name="" type="text" size="30"/> <!--  <input type="button" value="选择" class="btn" /></td>-->
										<td align="right">交货工厂：</td>
										<td><input name="" type="text" size="30"/></td>
									</tr>
									<tr>
										<td align="right">总重量：</td>
										<td><input name="" type="text" size="30"/></td>
										<td align="right">交货冻结：</td>
										<td><select style="width: 50"><option>请选择</option></select></td>
									</tr>
									<tr>
										<td align="right">出具发票冻结：</td>
										<td><select style="width: 50"><option>请选择</option></select></td>
										<td align="right">业务量 ：</td>
										<td><input name="" type="text" size="30"/></td>
									</tr>
									<tr>
										<td align="right">付款条件：</td>
										<td><select style="width: 50"><option>请选择</option></select></td>
										<td align="right">收款条件：</td>
										<td><select style="width: 50"><option>请选择</option></select></td>
									</tr>
									<tr>
										<td align="right">国际贸易条件：</td>
										<td><select style="width: 50"><option>请选择</option></select></td>
										<td align="right">销售区域：</td>
										<td><input name="" type="text" size="30"/></td>
									</tr>
								 </table>
								 
								 <!-- 
								 <table>
									<tr>
										<th width="10%">上级财务组织</th>
										<td width="40%"><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" />
											<span class="formInfo">
												<a href="#" class="jTip" id="tip1" name="我是标题标题，rel是宽度" rel="375">?
													<div class="jTipCenter">
														<ul>
															<li>1) Password must be <strong>between 6 - 20 characters</strong> long</li>
															<li>2) Password must contain at least <strong>one letter</strong></li>
															<li>3) Password must contain at least <strong>one number</strong></li>
															<li>4) Password <strong>cannot</strong> contain <strong>special characters</strong> (@, %, etc)</li>
															<li>5) Password cannot be the same as the User ID</li>
															<li>6) Password validation is <strong>case sensitive</strong></li>
														</ul>
													</div>
												</a>
											</span></td>
										
								  	</tr>
								  	<tr>
										<th>上级财务组织</th>
										<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
										<th>科目表</th>
										<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
								  	</tr>
									<tr>
										<th>下拉</th>
										<td><div class="no-border"><div id="combo1" class="combo"></div></div>
											<script type="text/javascript">
													var dd = [];
													for(var i=1;i<=5; i++)
														dd.push({ code: i + '', name: 'Employee ' + i });
													var cfg = {
														keyField: 'code',
														displayField: 'name',
														multiSelect: false,
														width: 200,
														boxWidth: 200,
														cols : [{
															field: 'code', width: '28%'
														},{
															field: 'name', width: '70%'
														}],
														data: dd
													};
													var cfg1 = $.extend({}, cfg);
													var cb1 = $('#combo1').mac('combo', cfg1);
													$('#get1').click(function(){
														alert(cb1.selected);
													});
													$('#set1').click(function(){
														cb1.select(2);
													});
													var cfg2 = $.extend({}, cfg);
													cfg2.multiSelect = true;
													var cb2 = $('#combo2').mac('combo', cfg2);
												$('#get2').click(function(){
													alert(cb2.selected.join(','));
												});
												$('#set2').click(function(){
													cb2.select([2,4]);
												});
										</script>
										</td>
										<th>下拉多选</th>
										<td><div class="no-border"><div id="combo2" class="combo"></div></div></td>
									</tr>
									<tr>
										<th>tip button</th>
									  	<td><div id="StatusBar"></div>
											<input name="" id="addStatusBarMessage" type="button" value="点击我" />
											<script type="text/javascript">
											   $('#StatusBar').jnotifyInizialize({
													oneAtTime: true
												})
												$('#Notification')
													.jnotifyInizialize({
														oneAtTime: false,
														appendType: 'append'
													});
					
												$(document).ready(function() {
													$('#addStatusBarMessage').click(function() {
														$('#StatusBar').jnotifyAddMessage({
															text: 'This is a non permanent message.',
															permanent: false,
															showIcon: false
														});
													});
												});
											</script>
										</td>
										<th>客户：</th>
									  	<td>
											<span id="customerChk"> </span>
											<input class="btn" type="button" value="选择" onclick="chooseChkCustomer();">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="customerRadio"> </span>
											<input class="btn" type="button" value="选择" onclick="chooseRadioCustomer();"/> 
										</td>
									</tr>
								  <tr>
									<th>上级财务组织</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
									<th>科目表</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
								  </tr>
								  <tr>
									<th>上级财务组织</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
									<th>科目表</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
								  </tr>
								  <tr>
									<th>上级财务组织</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
									<th>科目表</th>
									<td><input class="sweet-tooltip" data-text-tooltip="我是提示文字" name="" type="text" /></td>
								  </tr>
								  <tr>
									<th>时间</th>
									<td><input class="sweet-tooltip time_aoto_input" data-text-tooltip="时间 class = time_aoto_input 控制，取消则删除此class。" name="" type="text" rel="yyyy-MM-dd HH:00" /></td>
									<th>时间 yyyy-MM-dd HH:mm:ss</th>
									<td><input id="time1" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="" type="text" /> <img onclick="showTime('time1','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								  </tr>
								  <tr>
									<th>时间 yyyy-MM-dd HH:mm</th>
									<td><input id="time2" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm" name="" type="text" /> <img onclick="showTime('time2','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
									<th>时间 yyyy-MM-dd</th>
									<td><input id="time3" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="" type="text" /> <img onclick="showTime('time3','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								  </tr>
								  <tr>
									<th>索引</th>
									<td><input class="sweet-tooltip" data-text-tooltip="Tip tip tip tip" id="demo-input-facebook-theme" name="" type="text" />
										<script type="text/javascript">
											$(document).ready(function() {
												$("#demo-input-facebook-theme").tokenInput("http://shell.loopj.com/tokeninput/tvshows.php", {
													theme: "facebook"
												});
											});
											$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
												$(this).addClass("btnhover");
											},function(){
												$(this).removeClass("btnhover");
											});
										</script>
									</td>
									<th>massage</th>
									<td>
										<input name="" id="addStatusBarMessage" class="btn" type="button" onclick="showMessage('我是提示文字，showMessage')" value="消息提示" />&nbsp;&nbsp;<input name="" id="addStatusBarMessage" class="btn" type="button" onclick="showErrorMessage('错误提示文字，showErrorMessage')" value="错误提示" />
									</td>
								  </tr>
								</table>
								 -->
							</dd>
						</dl>
					</div>--%>


						<div style="margin: 15px 70px; display: none;">
							<textarea id="content" name="content"></textarea>
							<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
							<script type="text/javascript">
					 var editor = KindEditor.create('textarea[name="content"]',
								{basePath:'${vix}/plugin/KindEditor/',
									width:1100,
									height:300,
									cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
									uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
									fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
									allowFileManager : true 
								}
					 );
					 </script>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />语言能力</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />计算机水平</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />技术工人职业资格</a></li>
							<li><a onclick="javascript:$('#a4').attr('style','');tab(8,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />学历学位情况</a></li>
							<li><a onclick="javascript:$('#a5').attr('style','');tab(8,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />培训情况</a></li>
							<li><a onclick="javascript:$('#a7').attr('style','');tab(8,7,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />专业技术职务</a></li>
							<li><a onclick="javascript:$('#a6').attr('style','');tab(8,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />专业技术成果</a></li>
							<!--<li><a onclick="javascript:$('#a8').attr('style','');tab(8,8,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />审批项</a></li>-->
						</ul>
					</div>
					<div id="a1" style="width: 100%;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA1"></table>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA2"></table>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA3"></table>
					</div>
					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA4"></table>
					</div>
					<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA5"></table>
					</div>
					<div id="a6" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA6"></table>
					</div>
					<div id="a7" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA7"></table>
					</div>
					<div id="a8" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div class="right_btn nomargin">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
						</div>
						<table id="dgdA8"></table>
					</div>
				</div>
			</dl>
		</div>
		<!--oder-->
		<div class="sub_menu sub_menu_bot">
			<div class="drop">
				<p>
					<a href="#" onclick="saveOrUpdateOrder()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/template/orderAction!goList.action');"><span>返回</span></a> <a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并新建</span></a> <a href="#"><span>关闭</span></a> <a href="#"
						id="text"><span>弹出窗口测试</span></a>
				</p>
				<ul>
					<li><a href="#"><span>帮助</span></a>
						<ul>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->