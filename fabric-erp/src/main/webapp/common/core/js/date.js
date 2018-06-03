function createNode(){
  var root = {
    "id" : "0",
    "text" : "123",
    "value" : "86",
    "showcheck" : 0,
    complete : true,
    "isexpand" : true,
    "checkstate" : 0,
    "hasChildren" : true
  };
  var arr = [];
  for(var i= 1;i<10; i++){
    var subarr = [];
    for(var j=1;j<10;j++){
      var value = "node-" + i + "-" + j; 
      subarr.push( {
         "id" : value,
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
      "text" : "node-" + i,
      "value" : "node-" + i,
      "showcheck" : 0,
      complete : true,
      "isexpand" : false,
      "checkstate" : 0,
      "hasChildren" : true,
      "ChildNodes" : subarr
    });
  }
  root["ChildNodes"] = arr;
  return root; 
}

treedata = [createNode()];
