<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/date.js" type="text/javascript"></script>
<script src="${vix}/common/js/page_01.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){

	//高级搜索效果
	bindSearch();
	//索引效果
	bindIndexSearch();
	//按键效果
	addButtonClass();
	$('#breadCrumb').jBreadCrumb();
});

var popHtml = '';
var idx = 999;
function openNewTask(value){
	var node = $('#tt').treegrid('getSelected');
	if(popHtml==''){
		popHtml = $('#new_task_win').html();
		$('#new_task_win').remove();
	}
	$('#parentId').val(value);
	asyncbox.open({
	 	modal:true,
		width : 790,
		height :460,
		title:"任务",
		html:popHtml,
		callback : function(action){
			if(action == 'ok'){
				$('#tt').treegrid('append', {
					parent: $('#parentId').val(),
					data: [{
						id: idx,
						code: $('#taskName').val(),
						name: $('#taskMan').val(),
						addr: $('#startTime').val(),
						progress:0
					}]
				});
				idx++;
				$('#parentId').val('');
			}
		},
		btnsbar : $.btn.OKCANCEL
	});
}

function formatProgress(value){
	if (value){
		var bcolor = "#cc0000";
		if(value>90)
			bcolor='#00A06B';
		else if(value>70)
				bcolor='#1B4F93';
		else if(value>50)
			bcolor='#EC870E';
		
    	var s = '<div style="width:100%;border:1px solid #ccc">' +
    			'<div style="width:' + value + '%;background:'+bcolor+';color:#fff">' + value + '%' + '</div>'
    			'</div>';
    	return s;
	} else {
    	return '';
	}
}
function adSubProgress(value){
	return '<a href="javascript:openNewTask('+value+')">添加</a>'
}
</script>
<style type="text/css">
.order_table input {
	width: 260px;
}
</style>
<input type="hidden" id="parentId" />
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />供应链</a></li>
				<li><a href="#">项目管理</a></li>
				<li><a href="#">任务管理</a></li>
				<li><a href="javascript:loadContent('/vix/pm/taskManagementAction!mgrTaskList.action');">任务列表</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="javascript:openNewTask()"><span>新建任务</span></a> <a href="#"><span>删除</span></a>
	</div>
</div>
<!-- sub menu -->
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />索引</a></li>
			<!--<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>-->
			<li class="fly"><a href="#"><img src="img/icon_10.png" alt="" />数据分类</a>
				<ul>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
				</ul></li>
			<li class="fly_th"><a href="#"><img src="img/icon_10.png" alt="" />热点分类</a>
				<ul>
					<li>
						<dl>
							<dt>信息</dt>
							<dd>
								<a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a>
							</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>信息</dt>
							<dd>
								<a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a>
							</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>信息</dt>
							<dd>
								<a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a> <a href="#"><img src="img/icon_10.png" alt="" />信息</a>
							</dd>
						</dl>
					</li>
				</ul></li>
		</ul>
		<div>
			<label>项目名称<input name="" type="text" class="int" /></label> <label>我的项目<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label> <strong id="lb_search_advanced">高级搜索</strong>
		</div>
		<div class="search_advanced">
			<label>子项目名称<input name="" type="text" class="int" /></label> <label>我的项目<input name="" type="checkbox" value="" /></label> <label>负责人<input name="" type="text" class="int" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<li><a href="#">2020 Eyeware</a></li>
			<li><a href="#">5150 Something Orother</a></li>
			<li><a href="#">8020 Solutions</a></li>
			<li><a href="#">9999 Placeholders</a></li>
			<li><a href="#">Accountants</a></li>
			<li><a href="#">Accounting &amp; Bookkeeping - General Service</a></li>
			<li><a href="#">Acupuncture</a></li>
			<li><a href="#">Advertising - Agencies &amp; Counselors</a></li>
			<li><a href="#">Advertising - Computer</a></li>
			<li><a href="#">Advertising - Promotional</a></li>
			<li><a href="#">Advertising - Television</a></li>
			<li><a href="#">Air Conditioning - Contractors &amp; Systems</a></li>
			<li><a href="#">Air Conditioning - Equipment - Repair</a></li>
			<li><a href="#">Alcoholism Information &amp; Treatment Centers</a></li>
			<li><a href="#">Amusement &amp; Theme Parks</a></li>
			<li><a href="#">Animals - Breeders</a></li>
			<li><a href="#">Animals - Hospitals</a></li>
			<li><a href="#">Animals - Shelters</a></li>
			<li><a href="#">Animals - Specialties</a></li>
			<li><a href="#">Antiques - Dealers</a></li>
			<li><a href="#">Apartments</a></li>
			<li><a href="#">Appliances - Household - Major - Dealers</a></li>
			<li><a href="#">Architects</a></li>
			<li><a href="#">Artists - Commercial</a></li>
			<li><a href="#">Artists - Fine Arts</a></li>
			<li><a href="#">Associations</a></li>
			<li><a href="#">Attorneys</a></li>
			<li><a href="#">Attorneys - Service Bureaus</a></li>
			<li><a href="#">Audio Visual Production Service</a></li>
			<li><a href="#">Automation Consultants</a></li>
			<li><a href="#">Automobile - Air Conditioning Equipment</a></li>
			<li><a href="#">Automobile - Body - Repairing &amp; Painting</a></li>
			<li><a href="#">Automobile - Customizing</a></li>
			<li><a href="#">Automobile - Electric Service</a></li>
			<li><a href="#">Automobile - Machine Shop Service</a></li>
			<li><a href="#">Automobile - Parts &amp; Supplies (Wholesale)</a></li>
			<li><a href="#">Automobile - Parts &amp; Supplies - Retail - New</a></li>
			<li><a href="#">Automobile - Racing &amp; Sports Car Equipment</a></li>
			<li><a href="#">Automobile - Radio &amp; Stereo Systems - Sales &amp; Service</a></li>
			<li><a href="#">Automobile - Renting</a></li>
			<li><a href="#">Automobile - Repair &amp; Service</a></li>
			<li><a href="#">Banks</a></li>
			<li><a href="#">Banquet Rooms</a></li>
			<li><a href="#">Barber Shops</a></li>
			<li><a href="#">Baseball Clubs</a></li>
			<li><a href="#">Batteries - Storage - Retail</a></li>
			<li><a href="#">Beauty Salons</a></li>
			<li><a href="#">Beauty Salons - Equipment &amp; Supplies (Manufacturers)</a></li>
			<li><a href="#">Beauty Salons - Equipment &amp; Supplies (Wholesale)</a></li>
			<li><a href="#">Bicycles - Dealers</a></li>
			<li><a href="#">Book Dealers - Retail</a></li>
			<li><a href="#">Book Dealers - Used &amp; Rare</a></li>
			<li><a href="#">Brassieres &amp; Corsets &amp; Girdles</a></li>
			<li><a href="#">Bridal - Shops</a></li>
			<li><a href="#">Brokers - Merchandise</a></li>
			<li><a href="#">Building Designers</a></li>
			<li><a href="#">Buildings - Metal</a></li>
			<li><a href="#">Business - Management Consultants</a></li>
			<li><a href="#">Business - Services</a></li>
			<li><a href="#">Cabinet Makers</a></li>
			<li><a href="#">Cabinets</a></li>
			<li><a href="#">Calligraphers</a></li>
			<li><a href="#">Campgrounds</a></li>
			<li><a href="#">Camps</a></li>
			<li><a href="#">Car Washing &amp; Polishing</a></li>
			<li><a href="#">Carpets &amp; Rugs - Dealers - New</a></li>
			<li><a href="#">Caterers</a></li>
			<li><a href="#">Caulking Contractors</a></li>
			<li><a href="#">Cellular Telephones (Services)</a></li>
			<li><a href="#">Cellular Telephones - Equipment &amp; Supplies</a></li>
			<li><a href="#">Chambers of Commerce</a></li>
			<li><a href="#">Child Care Service</a></li>
			<li><a href="#">Childrens Garment (Manufacturers)</a></li>
			<li><a href="#">Chimney &amp; Fireplace - Cleaning Building &amp; Repair</a></li>
			<li><a href="#">Chiropractors</a></li>
			<li><a href="#">Church - Organizations</a></li>
			<li><a href="#">Churches</a></li>
			<li><a href="#">City Government - Executive Offices</a></li>
			<li><a href="#">Cleaners</a></li>
			<li><a href="#">Clinics</a></li>
			<li><a href="#">Coffee Break Service &amp; Supplies</a></li>
			<li><a href="#">Collection Agencies</a></li>
			<li><a href="#">Commercial Printing</a></li>
			<li><a href="#">Communications</a></li>
			<li><a href="#">Computer &amp; Equipment Dealers</a></li>
			<li><a href="#">Computers - Consultants</a></li>
			<li><a href="#">Computers - Multimedia</a></li>
			<li><a href="#">Computers - Networking</a></li>
			<li><a href="#">Consignment Shops</a></li>
			<li><a href="#">Construction - Building Contractors</a></li>
			<li><a href="#">Construction - Companies</a></li>
			<li><a href="#">Construction - Heavy Projects</a></li>
			<li><a href="#">Construction - Management</a></li>
			<li><a href="#">Dancing Instruction</a></li>
			<li><a href="#">Data Communications Equipment &amp; Systems - Networks</a></li>
			<li><a href="#">Deck Builders</a></li>
			<li><a href="#">Dentists</a></li>
			<li><a href="#">Department Stores</a></li>
			<li><a href="#">Designers - Industrial</a></li>
			<li><a href="#">Direct Selling Establishments</a></li>
			<li><a href="#">Display Designers &amp; Producers</a></li>
			<li><a href="#">Distribution Services</a></li>
			<li><a href="#">Dolls (Wholesale)</a></li>
			<li><a href="#">Drafting Services</a></li>
			<li><a href="#">Earthquake Products &amp; Services</a></li>
			<li><a href="#">E-Commerce</a></li>
			<li><a href="#">Education Centers</a></li>
			<li><a href="#">Educational Consultants</a></li>
			<li><a href="#">Electric Contractors</a></li>
			<li><a href="#">Electric Transmission Equipment (Manufacturers)</a></li>
			<li><a href="#">Electrolysis</a></li>
			<li><a href="#">Electronic - Consultants</a></li>
			<li><a href="#">Electronic - Equipment &amp; Supplies (Manufacturers)</a></li>
			<li><a href="#">Electronic - Equipment &amp; Supplies (Wholesale)</a></li>
			<li><a href="#">Electronic - Equipment &amp; Supplies - Retail</a></li>
			<li><a href="#">Electronic - Parts Assemblers (Wholesale)</a></li>
			<li><a href="#">Embroidery</a></li>
			<li><a href="#">Employment - Agencies &amp; Opportunities</a></li>
			<li><a href="#">Energy Conservation &amp; Management Consultants</a></li>
			<li><a href="#">Energy Management Systems &amp; Products</a></li>
			<li><a href="#">Engineering</a></li>
			<li><a href="#">Engineers</a></li>
			<li><a href="#">Engineers - Civil</a></li>
			<li><a href="#">Engineers - Communications</a></li>
			<li><a href="#">Engineers - Consulting</a></li>
			<li><a href="#">Engineers - Electrical</a></li>
			<li><a href="#">Engineers - Electronic</a></li>
			<li><a href="#">Engineers - Industrial</a></li>
			<li><a href="#">Engineers - Mechanical</a></li>
			<li><a href="#">Engineers - Structural</a></li>
			<li><a href="#">Excavating Contractors</a></li>
			<li><a href="#">Executive Search Consultants</a></li>
			<li><a href="#">Exercise &amp; Physical Fitness Programs</a></li>
			<li><a href="#">Exterminating &amp; Pest Control Services</a></li>
			<li><a href="#">Fabric Shops</a></li>
			<li><a href="#">Fabrics (Wholesale)</a></li>
			<li><a href="#">Facilities Management</a></li>
			<li><a href="#">Fence Contractors</a></li>
			<li><a href="#">Fiber Optics</a></li>
			<li><a href="#">Financial Advisory Services</a></li>
			<li><a href="#">Financial Planning Consultants</a></li>
			<li><a href="#">Fire Departments</a></li>
			<li><a href="#">Floor Refinishing &amp; Resurfacing</a></li>
			<li><a href="#">Florists - Retail</a></li>
			<li><a href="#">Food - Markets</a></li>
			<li><a href="#">Food - Service - Management</a></li>
			<li><a href="#">Formal Wear - Rental</a></li>
			<li><a href="#">Fraternities &amp; Sororities</a></li>
			<li><a href="#">Freight - Forwarding</a></li>
			<li><a href="#">Freight - Traffic Consultants</a></li>
			<li><a href="#">Fuel - Management</a></li>
			<li><a href="#">Furniture - Dealers - Retail</a></li>
			<li><a href="#">Furniture - Designers &amp; Custom Builders</a></li>
			<li><a href="#">Furniture - Repairing &amp; Refinishing</a></li>
			<li><a href="#">Games &amp; Game Supplies</a></li>
			<li><a href="#">Garbage Collection</a></li>
			<li><a href="#">Gas - Liquefied Petroleum - Bottled &amp; Bulk (Wholesale)</a></li>
			<li><a href="#">Gas - Natural</a></li>
			<li><a href="#">General Contractors</a></li>
			<li><a href="#">General Merchandise - Retail</a></li>
			<li><a href="#">Gift Shops</a></li>
			<li><a href="#">Glass - Auto Plate &amp; Window</a></li>
			<li><a href="#">Glass - Hand Blown Art</a></li>
			<li><a href="#">Glass - Stained &amp; Leaded</a></li>
			<li><a href="#">Golf Courses - Public</a></li>
			<li><a href="#">Golf - Equipment &amp; Supplies - Retail</a></li>
			<li><a href="#">Government Offices - City, Village &amp; Township (Municipal)</a></li>
			<li><a href="#">Graphic Designers - Computer</a></li>
			<li><a href="#">Greenhouses</a></li>
			<li><a href="#">Grocers - Retail</a></li>
			<li><a href="#">Handyman Services</a></li>
			<li><a href="#">Hardware - Retail</a></li>
			<li><a href="#">Hardwood</a></li>
			<li><a href="#">Health &amp; Diet Foods - Retail</a></li>
			<li><a href="#">Health - Clubs Studios &amp; Gymnasiums</a></li>
			<li><a href="#">Health - Food Products (Wholesale)</a></li>
			<li><a href="#">Health - Services</a></li>
			<li><a href="#">Health - Spas</a></li>
			<li><a href="#">Herbs</a></li>
			<li><a href="#">Holistic Practitioners</a></li>
			<li><a href="#">Home Health - Service</a></li>
			<li><a href="#">Hospital Equipment &amp; Supplies (Wholesale)</a></li>
			<li><a href="#">Hospitals</a></li>
			<li><a href="#">Hotels &amp; Motels</a></li>
			<li><a href="#">Human Factors - Research &amp; Development</a></li>
			<li><a href="#">Industrial - Process - Furnaces &amp; Ovens (Manufacturers)</a></li>
			<li><a href="#">Information Technology Services</a></li>
			<li><a href="#">Insurance</a></li>
			<li><a href="#">Insurance - Title</a></li>
			<li><a href="#">Interior Decorators Design &amp; Consultants</a></li>
			<li><a href="#">Internet - Home Page Development Consulting</a></li>
			<li><a href="#">Internet - Services</a></li>
			<li><a href="#">Investigators</a></li>
			<li><a href="#">Investment - Management</a></li>
			<li><a href="#">Janitor Service</a></li>
			<li><a href="#">Jewelers - Retail</a></li>
			<li><a href="#">Laboratories</a></li>
			<li><a href="#">Laboratories - Research &amp; Development</a></li>
			<li><a href="#">Land Companies</a></li>
			<li><a href="#">Landscaping - Contractors</a></li>
			<li><a href="#">Landscaping - Designers</a></li>
			<li><a href="#">Landscaping - Equipment &amp; Supplies</a></li>
			<li><a href="#">Laundry - Equipment (Wholesale)</a></li>
			<li><a href="#">Laundry - Self Service</a></li>
			<li><a href="#">Libraries - Public</a></li>
			<li><a href="#">Limousine Service</a></li>
			<li><a href="#">Liquor - Retail</a></li>
			<li><a href="#">Loans</a></li>
			<li><a href="#">Locks &amp; Locksmiths</a></li>
			<li><a href="#">Machine Shops</a></li>
			<li><a href="#">Machinery - Specially Designed &amp; Built</a></li>
			<li><a href="#">Maid &amp; Butler Service</a></li>
			<li><a href="#">Mailing &amp; Shipping Services</a></li>
			<li><a href="#">Manicuring</a></li>
			<li><a href="#">Manufacturers</a></li>
			<li><a href="#">Manufacturers - Agents &amp; Representatives</a></li>
			<li><a href="#">Marble - Natural (Wholesale)</a></li>
			<li><a href="#">Marketing Consultants</a></li>
			<li><a href="#">Marketing Programs &amp; Services</a></li>
			<li><a href="#">Marriage &amp; Family Counselors</a></li>
			<li><a href="#">Martial Arts Instruction</a></li>
			<li><a href="#">Masonry Contractors</a></li>
			<li><a href="#">Massage Therapists</a></li>
			<li><a href="#">Medical - Management Service</a></li>
			<li><a href="#">Midwives</a></li>
			<li><a href="#">Mobile Homes - Parks &amp; Communities</a></li>
			<li><a href="#">Mobile Homes - Transporting</a></li>
			<li><a href="#">Motorcycles &amp; Motor Scooters - Repair &amp; Service</a></li>
			<li><a href="#">Multimedia (Manufacturers)</a></li>
			<li><a href="#">Music - Instruction - Instrumental</a></li>
			<li><a href="#">Musical Instruments (Manufacturers)</a></li>
			<li><a href="#">Musical Instruments - Dealers</a></li>
			<li><a href="#">Musicians</a></li>
			<li><a href="#">Newspaper - Publishers</a></li>
			<li><a href="#">Nonclassifiable Establishments</a></li>
			<li><a href="#">Non-Profit Organizations</a></li>
			<li><a href="#">Nurseries - Plants &amp; Trees (Wholesale)</a></li>
			<li><a href="#">Nurserymen</a></li>
			<li><a href="#">Nutritionists</a></li>
			<li><a href="#">Office - Supplies (Wholesale)</a></li>
			<li><a href="#">Optical Equipment Machinery &amp; Supplies (Wholesale)</a></li>
			<li><a href="#">Optometrists</a></li>
			<li><a href="#">Organizations</a></li>
			<li><a href="#">Organizing Services - Household &amp; Business</a></li>
			<li><a href="#">Paint - Retail</a></li>
			<li><a href="#">Painters</a></li>
			<li><a href="#">Parking Area &amp; Lots Maintenance &amp; Marking</a></li>
			<li><a href="#">Parks</a></li>
			<li><a href="#">Party Supplies</a></li>
			<li><a href="#">Paving Contractors</a></li>
			<li><a href="#">Pay Telephones &amp; Booths Equipment &amp; Service</a></li>
			<li><a href="#">Payroll - Preparation Service</a></li>
			<li><a href="#">Periodical Publishers</a></li>
			<li><a href="#">Petroleum - Products (Wholesale)</a></li>
			<li><a href="#">Pets - Boarding &amp; Sitting</a></li>
			<li><a href="#">Pets - Supplies &amp; Foods - Retail</a></li>
			<li><a href="#">Pets - Washing &amp; Grooming</a></li>
			<li><a href="#">Pharmacies &amp; Drug Stores</a></li>
			<li><a href="#">Photographers - Commercial</a></li>
			<li><a href="#">Photographers - Portrait</a></li>
			<li><a href="#">Photographs - Finishing - Retail</a></li>
			<li><a href="#">Physical Therapists</a></li>
			<li><a href="#">Physicians &amp; Surgeons</a></li>
			<li><a href="#">Physicians &amp; Surgeons - Equipment &amp; Supplies (Manufacturers)</a></li>
			<li><a href="#">Picture Frames - Dealers</a></li>
			<li><a href="#">Pipe (Wholesale)</a></li>
			<li><a href="#">Plants - Interior Design &amp; Maintenance</a></li>
			<li><a href="#">Plastering Contractors</a></li>
			<li><a href="#">Plastics - Molders (Manufacturers)</a></li>
			<li><a href="#">Plumbing - Contractors</a></li>
			<li><a href="#">Podiatrists</a></li>
			<li><a href="#">Police - Departments</a></li>
			<li><a href="#">Polygraph Service</a></li>
			<li><a href="#">Post Offices</a></li>
			<li><a href="#">Pressed &amp; Blown Glass &amp; Glassware (Manufacturers)</a></li>
			<li><a href="#">Printers</a></li>
			<li><a href="#">Printing - Brokers</a></li>
			<li><a href="#">Product Development &amp; Marketing</a></li>
			<li><a href="#">Psychic Mediums</a></li>
			<li><a href="#">Psychologists</a></li>
			<li><a href="#">Psychotherapists</a></li>
			<li><a href="#">Public Relations Counselors</a></li>
			<li><a href="#">Publishers - Book</a></li>
			<li><a href="#">Publishers - Computer Software</a></li>
			<li><a href="#">Publishers - Periodical</a></li>
			<li><a href="#">Quilting - Materials &amp; Supplies</a></li>
			<li><a href="#">Radio Stations &amp; Broadcasting Companies</a></li>
			<li><a href="#">Reading Rooms</a></li>
			<li><a href="#">Real Estate</a></li>
			<li><a href="#">Real Estate - Consultants</a></li>
			<li><a href="#">Real Estate - Developers</a></li>
			<li><a href="#">Real Estate - Inspection</a></li>
			<li><a href="#">Real Estate - Loans</a></li>
			<li><a href="#">Real Estate - Management</a></li>
			<li><a href="#">Recording Studios</a></li>
			<li><a href="#">Records - Phonograph - Used &amp; Rare</a></li>
			<li><a href="#">Recreation Centers</a></li>
			<li><a href="#">Recreational Vehicles</a></li>
			<li><a href="#">Rehabilitation Services</a></li>
			<li><a href="#">Religious Organizations</a></li>
			<li><a href="#">Remodeling &amp; Repairing Buildings Contractors</a></li>
			<li><a href="#">Rental Agencies</a></li>
			<li><a href="#">Research Service</a></li>
			<li><a href="#">Residential - Care Homes</a></li>
			<li><a href="#">Resorts</a></li>
			<li><a href="#">Restaurants</a></li>
			<li><a href="#">Restaurants - American</a></li>
			<li><a href="#">Restaurants - Bakeries</a></li>
			<li><a href="#">Restaurants - Barbecue</a></li>
			<li><a href="#">Restaurants - Cafe</a></li>
			<li><a href="#">Restaurants - Chinese</a></li>
			<li><a href="#">Restaurants - Coffee House</a></li>
			<li><a href="#">Restaurants - Deli</a></li>
			<li><a href="#">Restaurants - Donuts</a></li>
			<li><a href="#">Restaurants - Fast Food</a></li>
			<li><a href="#">Restaurants - Ice Cream</a></li>
			<li><a href="#">Restaurants - Japanese</a></li>
			<li><a href="#">Restaurants - Mexican</a></li>
			<li><a href="#">Restaurants - Pizza</a></li>
			<li><a href="#">Roofing - Contractors</a></li>
			<li><a href="#">Roofing - Materials</a></li>
			<li><a href="#">Roofing - Service Consultants</a></li>
			<li><a href="#">Sand &amp; Gravel (Wholesale)</a></li>
			<li><a href="#">School Supplies (Wholesale)</a></li>
			<li><a href="#">Schools &amp; Educational Services</a></li>
			<li><a href="#">Schools - Nursery &amp; Kindergarten Academic</a></li>
			<li><a href="#">Schools - Universities &amp; Colleges Academic</a></li>
			<li><a href="#">Seafood (Wholesale)</a></li>
			<li><a href="#">Secretarial &amp; Court Reporting Services</a></li>
			<li><a href="#">Security Control Equipment &amp; Systems (Wholesale)</a></li>
			<li><a href="#">Security Guard &amp; Patrol Service</a></li>
			<li><a href="#">Seeds &amp; Bulbs - Retail</a></li>
			<li><a href="#">Semiconductor Devices (Manufacturers)</a></li>
			<li><a href="#">Senior Citizens - Service Organizations</a></li>
			<li><a href="#">Service Stations - Gasoline &amp; Oil</a></li>
			<li><a href="#">Services</a></li>
			<li><a href="#">Sewing Contractors</a></li>
			<li><a href="#">Shoe &amp; Boot Repairing</a></li>
			<li><a href="#">Shoes - Retail</a></li>
			<li><a href="#">Shopping Centers &amp; Malls</a></li>
			<li><a href="#">Signs (Manufacturers)</a></li>
			<li><a href="#">Skating Rinks</a></li>
			<li><a href="#">Soccer Clubs</a></li>
			<li><a href="#">Social Services &amp; Welfare Organizations</a></li>
			<li><a href="#">Solar Energy - Equipment (Wholesale)</a></li>
			<li><a href="#">Spas - Beauty &amp; Day</a></li>
			<li><a href="#">Sporting Goods - Retail</a></li>
			<li><a href="#">Sprinklers - Automatic - Fire (Manufacturers)</a></li>
			<li><a href="#">Sprinklers - Automatic - Fire (Wholesale)</a></li>
			<li><a href="#">Stables</a></li>
			<li><a href="#">Stock &amp; Bond Brokers</a></li>
			<li><a href="#">Stone - Natural</a></li>
			<li><a href="#">Storage - Batteries (Manufacturers)</a></li>
			<li><a href="#">Storage - Household &amp; Commercial</a></li>
			<li><a href="#">Swimming Pools - Coping, Plastering &amp; Tiling</a></li>
			<li><a href="#">Swimming Pools - Equipment &amp; Supplies (Wholesale)</a></li>
			<li><a href="#">Swimming Pools - Public</a></li>
			<li><a href="#">Tanning Salons</a></li>
			<li><a href="#">Tax Consultants</a></li>
			<li><a href="#">Tax Return Preparation &amp; Filing</a></li>
			<li><a href="#">Technical Manual Preparation</a></li>
			<li><a href="#">Technical Writing</a></li>
			<li><a href="#">Telecommunications Consultants</a></li>
			<li><a href="#">Telecommunications Wiring &amp; Cabling</a></li>
			<li><a href="#">Television &amp; Radio - Dealers</a></li>
			<li><a href="#">Television - Program Producers</a></li>
			<li><a href="#">Theatres - Live</a></li>
			<li><a href="#">Theatres - Movie</a></li>
			<li><a href="#">Tile - Ceramic - Contractors &amp; Dealers</a></li>
			<li><a href="#">Tires - Dealers - Retail</a></li>
			<li><a href="#">Title Companies</a></li>
			<li><a href="#">Tools (Wholesale)</a></li>
			<li><a href="#">Tractors - Repairing &amp; Service</a></li>
			<li><a href="#">Training Consultants</a></li>
			<li><a href="#">Transcribing Service - Tape &amp; Disc</a></li>
			<li><a href="#">Transmissions - Automobile</a></li>
			<li><a href="#">Travel - Agencies &amp; Bureaus</a></li>
			<li><a href="#">Tree Service</a></li>
			<li><a href="#">Trucking</a></li>
			<li><a href="#">Trust Service</a></li>
			<li><a href="#">Tutoring</a></li>
			<li><a href="#">Ultrasonic Equipment &amp; Supplies (Wholesale)</a></li>
			<li><a href="#">Upholsterers</a></li>
			<li><a href="#">Utilities - Underground - Cable Locating Service</a></li>
			<li><a href="#">Vacuum Cleaners - Household - Dealers</a></li>
			<li><a href="#">Vending Machines</a></li>
			<li><a href="#">Ventilating Systems - Cleaning</a></li>
			<li><a href="#">Venture Capital Companies</a></li>
			<li><a href="#">Veterinarians</a></li>
			<li><a href="#">Veterinarians - Equipment &amp; Supplies (Wholesale)</a></li>
			<li><a href="#">Video Production &amp; Taping Service</a></li>
			<li><a href="#">Video Tapes &amp; Discs &amp; Cassettes</a></li>
			<li><a href="#">Video Tapes &amp; Discs - Renting &amp; Leasing</a></li>
			<li><a href="#">Vitamin Products (Manufacturers)</a></li>
			<li><a href="#">Water &amp; Sewage Companies - Utility</a></li>
			<li><a href="#">Water Companies - Bottled &amp; Bulk</a></li>
			<li><a href="#">Water Softening Equipment Service &amp; Supplies</a></li>
			<li><a href="#">Website Design Service</a></li>
			<li><a href="#">Welding</a></li>
			<li><a href="#">Windows - Repairing</a></li>
			<li><a href="#">Wineries</a></li>
			<li><a href="#">Womens Apparel - Retail</a></li>
			<li><a href="#">X-Ray - Apparatus &amp; Supplies (Manufacturers)</a></li>
			<li><a href="#">Youth Organizations &amp; Centers</a></li>
		</ul>
		<!--<div class="num_box"><a href="#">0-9</a><a href="#">A</a><a href="#">B</a><a href="#">C</a><a href="#">D</a><a href="#">E</a><a href="#">F</a><a href="#">G</a><a href="#">H</a><a href="#">I</a><a href="#">J</a><a href="#">K</a><a href="#">L</a><a href="#">M</a><a href="#">N</a><a href="#">O</a><a href="#">P</a><a href="#">Q</a><a href="#">R</a><a href="#">S</a><a href="#">T</a><a href="#">U</a><a href="#">V</a><a href="#">W</a><a href="#">X</a><a href="#">Y</a><a href="#" class="num-last">Z</a>
        
        </div>-->
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="switch_btn" id="switch_box"></div>
			<div class="left_content" id="tree_00" style="height: 540px;"></div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="javascript:tab(2,1,'a',event)"><img src="img/mail.png" alt="" />任务列表</a></li>
					<li><a onclick="javascript:tab(2,2,'a',event)"><img src="img/mail.png" alt="" />历史任务</a></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="tg_w_auto">
					<div class="right_btn clearfix" style="width: auto; border: 0; margin: 0;">
						<span><a href="javascript:void(0);" onclick="editNode('tt')" title="编辑"><img alt="" src="img/user.png"></a></span> <span><a href="javascript:void(0);" onclick="saveNode('tt')" title="保存"><img alt="" src="img/wrench_screwdriver.png"></a></span> <span><a href="javascript:void(0);" onclick="cancelNode('tt')" title="取消"><img
								alt="" src="img/address_book.png"></a></span>
					</div>
					<table id="tt" title="任务列表" class="easyui-treegrid" style="height: 470px" url="${vix}/common/json_tests/datagrid_data_pm_task.json" idField="id" treeField="code" pagination="true" fitColumns="true">
						<thead>
							<tr>
								<th field="code" rowspan="2" width="190" editor="text">任务名称</th>
								<th colspan="4">任务细节</th>
							</tr>
							<tr>
								<th field="name" width="250" editor="text">负责人</th>
								<th field="addr" width="250" editor="text">任务开始时间</th>
								<th field="progress" width="150" formatter="formatProgress">任务进度</th>
								<th field="id" width="70" formatter="adSubProgress">添加子任务</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="right_content" id="a2" style="display: none;">
				<div class="tg_w_auto">
					<table id="tt2" title="历史任务" class="easyui-treegrid" style="height: 470px" url="${vix}/common/json_tests/datagrid_data_pm_task2.json" idField="id" treeField="code" pagination="true" fitColumns="true">
						<thead>
							<tr>
								<th field="code" rowspan="2" width="190" editor="text">任务名称</th>
								<th colspan="3">任务细节</th>
							</tr>
							<tr>
								<th field="name" width="250" editor="text">负责人</th>
								<th field="addr" width="250" editor="text">任务开始时间</th>
								<th field="progress" width="150" formatter="formatProgress">任务进度</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<!-- content -->
<div id="footpanel">
	<ul id="mainpanel">
		<li><a href="http://www.designbombs.com" class="home">Inspiration <small>Design </small></a></li>
		<li><a href="http://www.designbombs.com" class="profile">View Profile <small>View Profile</small></a></li>
		<li><a href="http://www.designbombs.com" class="editprofile">Edit Profile <small>Edit Profile</small></a></li>
		<li><a href="http://www.designbombs.com" class="contacts">Contacts <small>Contacts</small></a></li>
		<li><a href="http://www.designbombs.com" class="messages">Messages (10) <small>Messages</small></a></li>
		<li><a href="http://www.designbombs.com" class="playlist">Play List <small>Play List</small></a></li>
		<li><a href="http://www.designbombs.com" class="videos">Videos <small>Videos</small></a></li>
		<li id="alertpanel"><a class="alerts" id="alerts_off">Alerts</a></li>
		<li id="chatpanel"><a href="#" class="chat">Friends (<strong>18</strong>)
		</a>
			<div class="subpanel">
				<h3>
					<span> &ndash; </span>Friends Online
				</h3>
				<ul>
					<li><span>Family Members</span></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><span>Other Friends</span></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
				</ul>
			</div></li>
	</ul>
</div>
<div id="footpanel_switch">
	<ul id="mainpanel">
		<li id="alertpanel"><a class="alerts" id="alerts_on">Alerts</a></li>
	</ul>
</div>
<!-- foot bar -->
<div class="pos_menu">
	<em id="show_menu"></em>
	<div id="pos_menu">
		<strong><a id="hidd_menu" href="###"></a>SHORTCHUTS</strong>
		<ul>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
		</ul>
	</div>
</div>
<!-- pos_menu -->

<div id="dialog" title="Basic dialog">
	<p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>
</div>
<!-- dialog -->
<script src="${vix}/common/js/loadtree.js" type="text/javascript"></script>
<div id="helpContent" title="Viewing and using brush files">
	<p>&nbsp;&nbsp;You can view brush files in the Browser just like fonts or other files. This means that you can assign ratings or organize them just like any other file in ACDSee. Because many .abr files are actually groups of images in one file, you need to open them in the Viewer to see the individual images.</p>
	<p>
		<img src="img/address_book.png">This icon indicates an .abr brush file in ACDSee.
	</p>
	<p>
		<strong>To view brush files:</strong>
	</p>
	<p>&nbsp;&nbsp;1. In the Browser, navigate to the folder containing your brush files.</p>
	<p>&nbsp;&nbsp;2. To see just the top image in any .abr file, hover over the thumbnail to activate the pop-up, or click on it to see that image in the Preview pane.</p>
	<p>&nbsp;&nbsp;3. To view the other images in the .abr file, double-click on it to open it in the Viewer.</p>
	<p>&nbsp;&nbsp;The file opens in the Viewer showing the individual images in a pane on the left-hand side.</p>
	<p>&nbsp;&nbsp;4. To see the number of images, and select them by number, click the down-arrow at the top of the sidebar, and then select the number of the image.</p>
	<p>&nbsp;&nbsp;5. To scroll through the images, click the right and left arrows at the top of the sidebar, or on each image.</p>
	<p>
		<strong>To use brush files in Adobe Photoshop:</strong>
	</p>
	<p>&nbsp;&nbsp;With both Adobe Photoshop and ACDSee open, drag the file from the File List (in the Browser) onto the Photoshop window.</p>
	<p>&nbsp;&nbsp;Even though nothing appears to happen, the brush is loaded into the Photoshop brush library. To view the new brushes, open the library and scroll to the bottom of the pane.</p>
	<p class="helpTd">To make it even easier to use brushes in Photo Shop, you can configure it to be your default editor. Then you can use Ctrl +E to open Photoshop and use the brush right away.</p>
</div>

<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
<div id="new_task_win" style="display: none;">
	<div class="" style="margin-top: 5px;">
		<form id="dailyForm">
			<div class="box order_table" style="line-height: normal;">
				<table>
					<s:hidden id="dailyId" name="daily.id" value="%{daily.id}" theme="simple" />
					<tr height="30">
						<td align="right">名称:&nbsp;</td>
						<td><input id="taskName" name="daily.name" value="${daily.name}" /></td>
						<td align="right">任务类型:&nbsp;</td>
						<td><s:select id="modelnum" name="equipment.modelnum" list="#{'1':'常规任务','2':'外事活动','3':'人员接待'}" theme="simple"></s:select></td>
					</tr>
					<tr height="30">
						<td align="right">负责人:&nbsp;</td>
						<td><input id="taskMan" name="" value="${daily.name}" /></td>
						<td align="right">优先级:&nbsp;</td>
						<td><s:select id="modelnum" name="equipment.modelnum" list="#{'1':'普通','2':'重要'}" theme="simple"></s:select></td>
					</tr>
					<tr height="30">
						<td align="right">任务开始日期:&nbsp;</td>
						<td><input id="startTime" name="daily.startTime" value="${daily.startTime}" /> <img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
						<td align="right">&nbsp;</td>
						<td><input id="endTime" name="daily.endTime" value="${daily.endTime}" /> <img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					</tr>
					<tr height="30">
						<td colspan="4">任务描述</td>
					</tr>
					<tr height="30">
						<td colspan="4"><textarea id="todaySummary" name="todaySummary"></textarea></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<script>
	var editorSummary = KindEditor.create('textarea[name="todaySummary"]',{basePath:'${vix}/plugin/KindEditor/',width:750,height:150});
</script>