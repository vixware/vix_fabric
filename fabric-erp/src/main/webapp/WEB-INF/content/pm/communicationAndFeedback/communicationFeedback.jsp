<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/page_01.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$('#breadCrumb').jBreadCrumb();
});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/newico.png" class="png" alt="" width="26" height="26" />&nbsp;供应链</a></li>
				<li><a href="#">项目管理</a></li>
				<li><a href="#">沟通管理</a></li>
				<li><a href="#">沟通与反馈</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并创建</span></a> <a href="#"><span>关闭</span></a> <a href="#" id="text"><span>弹出窗口测试</span></a>
		</p>
		<ul>
			<li><a href="#"><span>帮助</span></a>
				<ul>
					<li><a href="#">帮助信息</a></li>
					<li><a href="#">帮助信息</a></li>
					<li><a href="#">帮助信息</a></li>
					<li><a href="#">帮助信息</a></li>
				</ul></li>
		</ul>
	</div>
</div>
<!-- sub menu -->
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="${vix}/common/img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="${vix}/common/img/icon_10.png" alt="" />索引</a></li>
			<!--<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />information</a></li>-->
			<li class="fly"><a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />数据分类</a>
				<ul>
					<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a></li>
				</ul></li>
			<li class="fly_th"><a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />热点分类</a>
				<ul>
					<li>
						<dl>
							<dt>信息</dt>
							<dd>
								<a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png"
									alt="" />信息</a>
							</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>信息</dt>
							<dd>
								<a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png"
									alt="" />信息</a>
							</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>信息</dt>
							<dd>
								<a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png" alt="" />信息</a> <a href="#"><img src="${vix}/common/img/icon_10.png"
									alt="" />信息</a>
							</dd>
						</dl>
					</li>
				</ul></li>
		</ul>
		<div>
			<label>名称<input name="" type="text" class="int" /></label> <label>我的项目<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label> <strong id="search_advanced">高级搜索</strong>
		</div>
		<div class="search_advanced">
			<label>名称<input name="" type="text" class="int" /></label> <label>我的项目<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label> <label>姓名<input name="" type="text" class="int" /></label> <label>我的项目<input name=""
				type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label>
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
		<div class="span12">
			<!--PAGE CONTENT BEGINS-->

			<div class="align-right">
				<span class="green middle bolder">Choose timeline: &nbsp;</span>

				<div class="btn-toolbar inline middle no-margin">
					<div data-toggle="buttons-radio" class="btn-group no-margin">
						<button class="btn btn-small btn-yellow active">
							<span class="bigger-110">1</span>
						</button>

						<button class="btn btn-small btn-yellow">
							<span class="bigger-110">2</span>
						</button>
					</div>
				</div>
			</div>

			<div id="timeline-1">
				<div class="row-fluid">
					<div class="offset1 span10">
						<div class="timeline-container">
							<div class="timeline-label">
								<span class="label label-primary arrowed-in-right label-large"> <b>Today</b>
								</span>
							</div>

							<div class="timeline-items">
								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<img alt="Susan't Avatar" src="${vix}/pad/tep/avatars/avatar1.png" /> <span class="label label-info">16:22</span>
									</div>

									<div class="widget-box transparent">
										<div class="widget-header widget-header-small">
											<h5 class="smaller">
												<a href="#" class="blue">Susan</a> <span class="grey">reviewed a product</span>
											</h5>

											<span class="widget-toolbar no-border"> <i class="icon-time bigger-110"></i> 16:22
											</span> <span class="widget-toolbar"> <a href="#" data-action="reload"> <i class="icon-refresh"></i>
											</a> <a href="#" data-action="collapse"> <i class="icon-chevron-up"></i>
											</a>
											</span>
										</div>

										<div class="widget-body">
											<div class="widget-main">
												Anim pariatur cliche reprehenderit, enim eiusmod <span class="red">high life</span> accusamus terry richardson ad squid &hellip;
												<div class="space-6"></div>

												<div class="widget-toolbox clearfix">
													<div class="pull-left">
														<i class="icon-hand-right grey bigger-125"></i> <a href="#" class="bigger-110">Click to read &hellip;</a>
													</div>

													<div class="pull-right action-buttons">
														<a href="#"> <i class="icon-ok green bigger-130"></i>
														</a> <a href="#"> <i class="icon-pencil blue bigger-125"></i>
														</a> <a href="#"> <i class="icon-remove red bigger-125"></i>
														</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<i class="timeline-indicator icon-food btn btn-success no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-header widget-header-small hidden"></div>

										<div class="widget-body">
											<div class="widget-main">
												Going to cafe for lunch
												<div class="pull-right">
													<i class="icon-time bigger-110"></i> 12:30
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<i class="timeline-indicator icon-star btn btn-warning no-hover green"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-header widget-header-small">
											<h5 class="smaller">New logo</h5>

											<span class="widget-toolbar no-border"> <i class="icon-time bigger-110"></i> 9:15
											</span> <span class="widget-toolbar"> <a href="#" data-action="reload"> <i class="icon-refresh"></i>
											</a> <a href="#" data-action="collapse"> <i class="icon-chevron-up"></i>
											</a>
											</span>
										</div>

										<div class="widget-body">
											<div class="widget-main">
												Designed a new logo for our website. Would appreciate feedback.
												<div class="space-6"></div>

												<div class="widget-toolbox clearfix">
													<div class="pull-right action-buttons">
														<div class="space-4"></div>

														<div>
															<a href="#"> <i class="icon-heart red bigger-125"></i>
															</a> <a href="#"> <i class="icon-facebook blue bigger-125"></i>
															</a> <a href="#"> <i class="icon-reply light-green bigger-130"></i>
															</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<i class="timeline-indicator icon-beaker btn btn-default no-hover"></i> <span class="label label-grey">9:00 am</span>
									</div>

									<div class="widget-box transparent">
										<div class="widget-header hidden"></div>

										<div class="widget-body">
											<div class="widget-main">Took the final exam. Phew!</div>
										</div>
									</div>
								</div>
							</div>
							<!--/.timeline-items-->
						</div>
						<!--/.timeline-container-->

						<div class="timeline-container">
							<div class="timeline-label">
								<span class="label label-success arrowed-in-right label-large"> <b>Yesterday</b>
								</span>
							</div>

							<div class="timeline-items">
								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<i class="timeline-indicator icon-beer btn btn-inverse no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-header widget-header-small">
											<h5 class="smaller">Haloween party</h5>

											<span class="widget-toolbar"> <i class="icon-time bigger-110"></i> 1 hour ago
											</span>
										</div>

										<div class="widget-body">
											<div class="widget-main">
												<div class="clearfix">
													<div class="pull-left">
														Lots of fun at Haloween party. <br /> Take a look at some pics:
													</div>

													<div class="pull-right">
														<i class="icon-chevron-left blue bigger-110"></i> &nbsp; <img alt="Image 4" width="36" src="${vix}/common/img/face_2.jpg" /> <img alt="Image 3" width="36" src="${vix}/common/img/face_2.jpg" /> <img alt="Image 2" width="36" src="${vix}/common/img/face_2.jpg" /> <img alt="Image 1" width="36" src="${vix}/common/img/face_2.jpg" />
														&nbsp; <i class="icon-chevron-right blue bigger-110"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<i class="timeline-indicator icon-trophy btn btn-pink no-hover green"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-header widget-header-small">
											<h5 class="smaller">Lorum Ipsum</h5>
										</div>

										<div class="widget-body">
											<div class="widget-main">
												Anim pariatur cliche reprehenderit, enim eiusmod <span class="green bolder">high life</span> accusamus terry richardson ad squid &hellip;
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<i class="timeline-indicator icon-food btn btn-success no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-header widget-header-small hidden"></div>

										<div class="widget-body">
											<div class="widget-main">Going to cafe for lunch</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<i class="timeline-indicator icon-bug btn btn-danger no-hover"></i>
									</div>

									<div class="widget-box">
										<div class="widget-header header-color-red2 widget-header-small">
											<h5 class="smaller">Critical security patch released</h5>

											<span class="widget-toolbar no-border"> <i class="icon-time bigger-110"></i> 9:15
											</span> <span class="widget-toolbar"> <a href="#" data-action="reload"> <i class="icon-refresh"></i>
											</a> <a href="#" data-action="collapse"> <i class="icon-chevron-up"></i>
											</a>
											</span>
										</div>

										<div class="widget-body">
											<div class="widget-main">Please download the new patch for maximum security.</div>
										</div>
									</div>
								</div>
							</div>
							<!--/.timeline-items-->
						</div>
						<!--/.timeline-container-->

						<div class="timeline-container">
							<div class="timeline-label">
								<span class="label label-grey arrowed-in-right label-large"> <b>May 17</b>
								</span>
							</div>

							<div class="timeline-items">
								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<i class="timeline-indicator icon-leaf btn btn-primary no-hover green"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-header widget-header-small">
											<h5 class="smaller">Lorum Ipsum</h5>

											<span class="widget-toolbar no-border"> <i class="icon-time bigger-110"></i> 10:22
											</span> <span class="widget-toolbar"> <a href="#" data-action="reload"> <i class="icon-refresh"></i>
											</a> <a href="#" data-action="collapse"> <i class="icon-chevron-up"></i>
											</a>
											</span>
										</div>

										<div class="widget-body">
											<div class="widget-main">
												Anim pariatur cliche reprehenderit, enim eiusmod <span class="blue bolder">high life</span> accusamus terry richardson ad squid &hellip;
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--/.timeline-items-->
						</div>
						<!--/.timeline-container-->
					</div>
				</div>
			</div>

			<div id="timeline-2" class="hide">
				<div class="row-fluid">
					<div class="offset1 span10">
						<div class="timeline-container timeline-style2">
							<span class="timeline-label"> <b>Today</b>
							</span>

							<div class="timeline-items">
								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">11:15 pm</span> <i class="timeline-indicator btn btn-info no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												<span class="bigger-110"> <a href="#" class="purple bolder">Susan</a> reviewed a product
												</span> <br /> <i class="icon-hand-right grey bigger-125"></i> <a href="#">Click to read &hellip;</a>
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">12:30 pm</span> <i class="timeline-indicator btn btn-info no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												Going to <span class="green bolder">veg cafe</span> for lunch
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">11:15 pm</span> <i class="timeline-indicator btn btn-info no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												Designed a new logo for our website. Would appreciate feedback. <a href="#"> Click to see <i class="icon-zoom-in blue bigger-110"></i>
												</a>

												<div class="space-2"></div>

												<div class="action-buttons">
													<a href="#"> <i class="icon-heart red bigger-125"></i>
													</a> <a href="#"> <i class="icon-facebook blue bigger-125"></i>
													</a> <a href="#"> <i class="icon-reply light-green bigger-130"></i>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 am</span> <i class="timeline-indicator btn btn-info no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">Took the final exam. Phew!</div>
										</div>
									</div>
								</div>
							</div>
							<!--/.timeline-items-->
						</div>
						<!--/.timeline-container-->

						<div class="timeline-container timeline-style2">
							<span class="timeline-label"> <b>Yesterday</b>
							</span>

							<div class="timeline-items">
								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 am</span> <i class="timeline-indicator btn btn-success no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												<div class="clearfix">
													<div class="pull-left">
														<span class="orange2 bolder">Haloween party</span> Lots of fun at Haloween party. <br /> Take a look at some pics:
													</div>

													<div class="pull-right">
														<i class="icon-chevron-left blue bigger-110"></i> &nbsp; <img alt="Image 4" width="36" src="${vix}/common/img/face_2.jpg" /> <img alt="Image 3" width="36" src="${vix}/common/img/face_2.jpg" /> <img alt="Image 2" width="36" src="${vix}/common/img/face_2.jpg" /> <img alt="Image 1" width="36" src="${vix}/common/img/face_2.jpg" />
														&nbsp; <i class="icon-chevron-right blue bigger-110"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 am</span> <i class="timeline-indicator btn btn-success no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												Anim pariatur cliche reprehenderit, enim eiusmod <span class="pink2 bolder">high life</span> accusamus terry richardson ad squid &hellip;
											</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 am</span> <i class="timeline-indicator btn btn-success no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">Going to cafe for lunch</div>
										</div>
									</div>
								</div>

								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 am</span> <i class="timeline-indicator btn btn-success no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												<span class="red bolder">Critical security patch released</span> <br /> Please download the new patch for maximum security.
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--/.timeline-items-->
						</div>
						<!--/.timeline-container-->

						<div class="timeline-container timeline-style2">
							<span class="timeline-label"> <b>May 17</b>
							</span>

							<div class="timeline-items">
								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<span class="timeline-date">9:00 am</span> <i class="timeline-indicator btn btn-grey no-hover"></i>
									</div>

									<div class="widget-box transparent">
										<div class="widget-body">
											<div class="widget-main no-padding">
												<span class="bolder blue">Lorum Ipsum</span> Anim pariatur cliche reprehenderit, enim eiusmod <span class="purple bolder">high life</span> accusamus terry richardson ad squid &hellip;
											</div>
										</div>
									</div>
								</div>
							</div>
							<!--/.timeline-items-->
						</div>
						<!--/.timeline-container-->
					</div>
				</div>
			</div>

			<!--PAGE CONTENT ENDS-->
		</div>



















































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
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><span>Other Friends</span></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="${vix}/common/img/chat-thumb.gif" alt="" /> Your Friend</a></li>
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
			<li><a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="${vix}/common/img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="${vix}/common/img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="${vix}/common/img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="${vix}/common/img/balloon.png" alt="" />demo</a></li>
		</ul>
	</div>
</div>
<!-- pos_menu -->

<div id="dialog" title="Basic dialog">
	<p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>
</div>
<!-- dialog -->
<script src="${vix}/common/js/menu.js" type="text/javascript"></script>
<script src="${vix}/common/js/loadtree.js" type="text/javascript"></script>
<div id="helpContent" title="Viewing and using brush files">
	<p>&nbsp;&nbsp;You can view brush files in the Browser just like fonts or other files. This means that you can assign ratings or organize them just like any other file in ACDSee. Because many .abr files are actually groups of images in one file, you need to open them in the Viewer to see the individual images.</p>
	<p>
		<img src="${vix}/common/img/address_book.png">This icon indicates an .abr brush file in ACDSee.
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
<script>
$(function(){
	$('[data-action=collapse]').click(function(){
		$('i',this).toggleClass('icon-chevron-down');
		$(this).closest('.widget-box').find('.widget-body').slideToggle('fast');
		return false;
	});
	
	$('[data-action=reload]').click(function(){
		$(this).closest('.widget-box').append('<div class="widget-box-layer"><i class="icon-spinner icon-spin icon-2x white"></i></div>');
		(function(el){
			setTimeout(function(){
				el.find('.widget-box-layer').remove();
			},3000);
		})($(this).closest('.widget-box'));
	});
	$('[data-toggle=buttons-radio]').on('click', function(e){
		var target = $(e.target);
		var which = parseInt($.trim(target.text()));
		$('button',this).removeClass('active');
		target.addClass('active');
		
		
		$('[id*="timeline-"]').hide();
		$('#timeline-'+which).show();
	});

});
</script>