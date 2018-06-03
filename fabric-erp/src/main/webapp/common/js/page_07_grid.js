function example_renderTaxFree(value ,record,columnObj,grid,colNo,rowNo){
				if(value==0) return "<img src='img/unchecked.gif'>";
				return "<img src='img/checked.gif'>";

}

function example_renderDiscount(value ,record,columnObj,grid,colNo,rowNo){
        var i = Math.round(value / 0.4 * 5);
        if(i>5)i=5;if(i<1)i=1;
				return "<IMG SRC=\"img/five_star" + i + ".gif\" />"


}

///////////////////////////////////////

var grid_demo_id = "myGrid1" ;
var grid_details_id = "myGrid2";


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
  uniqueField : 0 ,
	recordType : 'object'
}

var dsOptionDetails= {

	fields :[
		{name : 'no'  },
		{name : 'product'  },
		{name : 'unitPrice' ,type:'float'  },
		{name : 'quantity' ,type: 'int' },
		{name : 'discount' ,type: 'float'  },
		{name : 'taxFree'  ,type: 'int'  },
		{name : 'taxRate'  ,type: 'float'  },
		
		{name : 'shipTo', type: 'int'},
		{name : 'shipment'},
		{name : 'note'},
		
		{name : 'tax',type: 'float' , initValue : function(record){
        var avg = record[5]*record[6];
				return avg.toFixed(2);
    }},
		{name : 'totalPrice' ,type: 'float' , initValue : function(record){
        var avg = record[2]*record[3]*record[4]*record[5]*record[6];
				return avg.toFixed(2);
    }}
	],

	recordType : 'array'
}

var colsOption = [
     {id: 'chk' ,isCheckColumn : true, filterable: false, exportable:false},
     {id: 'no' , header: "Order No" , width :126  },
     {id: 'employee' , header: "Employee" , width :170  },
	   {id: 'country' , header: "Country" , width :146  },
	   {id: 'customer' , header: "Customer" , width :166  },
	   {id: 'bill2005' , header: "2005" , width :126, inChart :true, chartColor : 'eecc99'},
	   {id: 'bill2006' , header: "2006" , width :126, inChart :true, chartColor : '66eeaa'  },
	   {id: 'bill2007' , header: "2007" , width :126, inChart :true, chartColor : 'd65555'  },
	   {id: 'bill2008' , header: "2008" , width :126, inChart :true, chartColor : 'eeaa33'  },
	   {id: 'orderDate' , header: "Delivery Date" , width :206}
       
];
 
var colsOptionDetails = [
     {id: 'no' , header: "Item No" , width :88  , editor:{type:"text"}, frozen : true},
	   {id: 'product' , header: "Product" , width :128, grouped : true , frozen : true , sortOrder : 'asc',
     editor : { type :"select" ,
     options : {"Abalone":"Abalone","Amber":"Amber","Amethyst":"Amethyst","Aquamarine":"Aquamarine",
                "Cameos":"Cameos","Citrine":"Citrine","Coral":"Coral","Crystal":"Crystal","Cubic Zirconia":"Cubic Zirconia",
                "Emerald":"Emerald","Enamel":"Enamel","Garnet":"Garnet","Glass":"Glass","Moissanite":"Moissanite",
                "Onyx":"Onyx","Opal":"Opal","Pearl":"Pearl"} ,defaultText : '' }},
	   {id: 'unitPrice' , header: "Unit Price" , width :88 ,
     editor: { type :"text" ,validRule : ['R','F'] } },
	   {id: 'quantity' , header: "Quantity" , width :108, 
     editor: { type :"text" ,validRule : ['R'] } },
	   {id: 'discount' , header: "Discount" , width :128 ,renderer : example_renderDiscount,
     editor: { type :"text" ,validRule : ['R','F'] } },
	   
		{id: 'taxFree' , header: "Tax Free" , width :88 ,renderer : example_renderTaxFree },
		{id: 'taxRate' , header: "Tax Rate" , width :88 ,
    editor: { type :"text" ,validRule : ['R','F'] } },
		{id: 'tax' , header: "Tax" , width :88},
		{id: 'totalPrice' , header: "Total Price" , width :88, align:"right"
		},
		{id: 'shipTo' , header: "Ship To" , width :128  },
		{id: 'shipment' , header: "Shipment" , width :128  },
		{id: 'note' , header: "Note" , width :178  }
		
		
 ];




function masterCompleted(grid){
    var colObj=grid.columnMap['country'];
	  colObj.group();
}

var gridOption={
	id : grid_demo_id,
	width: "100%",  
	height: "512",  	
	container : 'gridbox', 
	replaceContainer : true, 
	dataset : dsOption ,
	columns : colsOption ,
	pageSizeList : [5,10,15,20],
	customHead : 'myHead',
	onCellClick  : function(value, record , cell, row,  colNO, rowNO,columnObj,grid){
		var no = record["no"]>"070-19"?"070-19":record["no"];
    var grid=Sigma.$grid(grid_details_id);
    grid.loadURL = "data/" + no + ".js";
    grid.reload();
	},
	SigmaGridPath : 'grid/',
	loadURL : 'export_php/testMasterList.php',
	exportURL : 'export_php/testMasterList.php?export=true',
	exportFileName : 'sales-master-report',
	remotePaging : false,
	defaultRecord : ["","","","",0,0,0,0,"2008-01-01"],
	pageSize:20,
	toolbarContent : 'nav goto | pagesize | reload | print csv xls xml pdf filter chart | state'
};


var gridOptionDetails={
	id : grid_details_id,
	loadUrl : 'data/010-0.js',
	width: "100%",
	height: "260", 	
	container : 'gridboxDetails', 
	replaceContainer : true, 
	editable : true,
  groupable : true,
	pageSizeList : [5,10,15,20],
	dataset : dsOptionDetails ,
	columns : colsOptionDetails ,
	clickStartEdit : true ,
	defaultRecord : ["","",0,0,0.0,0,0.0,"Customer","UPS",""],
	pageSize:30,
	toolbarContent : 'reload | add del | print | state',
	showGridMenu : true,
	allowCustomSkin	: true ,
	allowFreeze	: true ,
	allowHide	: true ,
	allowGroup	: true,
	customRowAttribute : function(record,rn,grid){
		if (record[11]>80){
			return 'style="height:50px; background-color:#ffddcc"';
		}
	}
};

var mygrid=new Sigma.Grid( gridOption );
var mygridDetails = new Sigma.Grid(gridOptionDetails);
		

Sigma.Util.onLoad( Sigma.Grid.render(mygrid) );
Sigma.Util.onLoad( Sigma.Grid.render(mygridDetails) );

//////////////////////////////////////////////////////////

function unlockAllColumn(){
	Sigma.Column.unlockAllColumn(grid_demo_id);
}

function lockColumnNAllBefore(){
	Sigma.Column.lockColumnNAllBefore(grid_demo_id,Sigma.$("idx2").value);
}



function showGrid(){

if (Sigma.$('bigbox').style.display!="none") {
	Sigma.$('bigbox').style.display="none";
}else{
Sigma.$('bigbox').style.display='';

// must call onShow() !!!!
mygrid.onShow();
}
}