<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/bootstrap-suggest/bootstrap-suggest.min.js"></script>
<div class="input-group" >
    <input type="text" class="form-control" id="invShelfJson">
    <div class="input-group-btn">
        <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu dropdown-menu-right" role="menu">
        </ul>
    </div>
</div>
<script type="text/javascript">
 var testdataBsSuggest = $("#invShelfJson").bsSuggest({
     indexId: 1,
     indexKey: 1,
     idField: "id",                 
     keyField: "",              
     effectiveFields: ["word"], 
     listStyle: {
         "padding-top":0, "max-height": "123px", "max-width": "300px",
         "overflow": "auto", "width": "auto",
         "transition": "0.3s", "-webkit-transition": "0.3s", "-moz-transition": "0.3s", "-o-transition": "0.3s"       
     },                            
     data:${invShelfJson}
 });
 $("input#invShelfJson").on("onSetSelectValue", function (event, result) {
	    var sID = result.id;
	    var name = result.key;
	    if(sID=='all'){
	    	$("#invShelfId").val('');
	    	$("#invShelfName").val('');
	    }else{
	    	$("#invShelfId").val(sID);
		    $("#invShelfName").val(name);
	    }
	});
</script>