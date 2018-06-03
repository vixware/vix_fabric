var grid_demo_id = "myGrid1" ;
var dsOption= {

	fields :[
		{name : 'no'  },
		{name : 'country'  },
		{name : 'customer'  },
		{name : 'employee'  },
		{name : 'bill2005' ,type: 'float' },
		{name : 'bill2006' ,type: 'float' },
		{name : 'bill2007' ,type: 'float' },
		{name : 'bill2008' ,type: 'float' },
		{name : 'orderDate' ,type:'date'  }
		
	],

	recordType : 'array',
	data : __TEST_DATA__
}

// Below function Add by me 
function column_render(value ,record,columnObj,grid,colNo,rowNo){
		 var options = {'1': 'US','2':'FR', '3':'BR'};
		 var ret = options[value];
		 if(ret==null){
       ret = value;
     }
		 return ret;
}
// My function over

// Below function you send us in mail
/*column_render:function(){
 var options = {'1': 'US','2':'FR', '3':'BR'};
 return options[cell-value];
}
*/

// Your function over
var colsOption = [
     {id: 'no' , header: "Text Box" , width :145, editor: {  type :"text"  } },
     {id: 'employee', header: "Text Box" , width :185, editor: {  type :"text"  }  },
	   {id: 'country', header: "Drop Down" , width :170,
     editor : { type :"select" ,options : {'1': 'US' ,'2':'FR', '3':'BR'} 
     ,defaultText : 'US' },
	 // Below function call by us
	 renderer:column_render },
	   {id: 'customer' , header: "Long Text" , width :300,editor: {type :"textarea", width:'300px',height:'100px'  }  },
	   {id: 'orderDate' , header: "Date Picker" , width :230, editor: {type :"date"}},
	   {id: 'bill2005' , header: "Readonly" , width :150},
	   {id: 'bill2006' , header: "Readonly" , width :150}
       
];

var gridOption={
	id : grid_demo_id,
	width: "100%",  //"100%", // 700,
	height: "380",  //"100%", // 330,
	container : 'gridbox', 
	replaceContainer : true, 
	
	dataset : dsOption ,
	columns : colsOption,
	pageSize : 15 ,
	toolbarContent : 'nav | goto'
};


var mygrid=new Sigma.Grid( gridOption );
Sigma.Util.onLoad( Sigma.Grid.render(mygrid) );
