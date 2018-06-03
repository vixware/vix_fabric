var grid_demo_id = "myGrid1" ;


var dsOption= {

	fields :[
	
	
		{name : 'operating'  },
		{name : 'summary'  },
		{name : 'subject'  },
		{name : 'quantity' ,type: 'float'},
		{name : 'unitPrice' ,type: 'float' },
		{name : 'units' ,type: 'float' },
		{name : 'amount' ,type: 'float' },
		{name : 'exchangeRate' ,type: 'float' },
		{name : 'debit' ,type: 'float' },
		{name : 'credit' ,type: 'float' }
		
	],
  uniqueField : 0 ,
	recordType : 'array',
	data : __TEST_DATA__
}



var colsOption = [
     {id: 'chk' ,isCheckColumn : true, 	editable:false },
		{id : 'operating'  , header: "操作" , width :133 , editor: {  type :"text"  }  },
		{id : 'summary' , header: "摘要" , width :133 , editor: {  type :"text"  } },
		{id : 'subject' , header: "科目" , width :133 , editor: {  type :"text"  } },
		{id : 'quantity' , header: "数量" , width :133 , editor: {  type :"text"  }},
		{id : 'unitPrice' , header: "单价" , width :133 , editor: {  type :"text"  }},
		{id : 'units' , header: "计量单位" , width :120 , editor: {  type :"text"  }},
		{id : 'amount' , header: "原币金额" , width :133 , editor: {  type :"text"  }},
		{id : 'exchangeRate' , header: "币别/汇率" , width :133 , editor: {  type :"text"  }},
		{id : 'debit' , header: "借方金额" , width :133 , editor: {  type :"text"  }},
		{id : 'credit' , header: "贷方金额" , width :133 , editor: {  type :"text"  }}
];
 

var gridOption={
	id : grid_demo_id,
	width: "100%",  //"100%", // 700,
	height: "270",  //"100%", // 330,
	
	container : 'gridbox', 
	replaceContainer : true, 
	
	dataset : dsOption ,
	columns : colsOption ,
	pageSize : 10 ,
	toolbarContent : 'nav | goto'
};




var mygrid=new Sigma.Grid( gridOption );
Sigma.Util.onLoad( Sigma.Grid.render(mygrid) );
