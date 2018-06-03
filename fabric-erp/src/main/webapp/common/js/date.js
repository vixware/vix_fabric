function createNode(){
  var root = {
    "id" : "0",
    "text" : "中外名人文化产业集团",
    "value" : "中外名人文化产业集团",
    "showcheck" : 0,
    complete : true,
    "isexpand" : true,
    "checkstate" : 0,
    "hasChildren" : true
  };
  var arr = [];
  var level1 = '第一事业中心,第二事业中心,第三事业中心,第四事业中心,中外名人文化传媒股份有限公司,中外名人影视制作公司,中外名人国际公关策划有限公司,中外名人国际广告传媒有限公司,捷爱特科技有限公司,名人会所,基金公司,中外名人管理咨询有限公司,集团项目管理中心,集团战略与发展中心,集团影视节目购销中心,集团研发中心,集团媒介研究中心,集团财务中心,集团人力资源中心';
  var level1Names = level1.split(',');
  for(var i= 1;i<level1Names.length; i++){
	    var subarr = [];
	    var num = (Math.random() * 3)+1;
	    for(var j=1;j<num;j++){
	      var value = "任务名称" + j; 
	      subarr.push( {
	         "id" : 'node-'+i+'-'+j,
	         "text" : value,
	         "value" : value,
	         "showcheck" : 0,
	         complete : true,
	         "isexpand" : false,
	         "checkstate" : 0,
	         "hasChildren" : false
	      });
	    }
		arr.push( {
		  "id" : "node-" + i,
		  "text" : level1Names[i],
		  "value" : level1Names[i],
		  "showcheck" : 0,
		  complete : true,
		  "isexpand" : false,
		  "checkstate" : 0,
		  "hasChildren" : true
		});
	      //,"ChildNodes" : subarr
  }
  
  root["ChildNodes"] = arr;
  return root; 
}

treedata = [createNode()];
