var grid_demo_id = "myGrid1" ;


var dsOption= {

	fields :[
		{name : 'accountName'  },
		{name : 'city'  },
		{name : 'billingCountry'  },
		{name : 'phone'  },
		{name : 'user' }
		
	],
  uniqueField : 0 ,
	recordType : 'array',
	data : __TEST_DATA__
}



var colsOption = [
     {id: 'chk' ,isCheckColumn : true, 	editable:false },
     {id: 'accountName' , header: "Account Name" , width :260 , editor: {  type :"text"  } },
     {id: 'city' , header: "City" , width :260 , editor: {  type :"text"  } },
	   {id: 'billingCountry' , header: "Billing Country" , width :260 , editor: {  type :"text"  } },
	   {id: 'phone' , header: "Phone" , width :260 , editor: {  type :"text"  } },
	   {id: 'user' , header: "User" , width :263, editor: {  type :"text"  }}
       
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
