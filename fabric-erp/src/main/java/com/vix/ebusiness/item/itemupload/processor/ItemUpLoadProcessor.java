package com.vix.ebusiness.item.itemupload.processor;

import org.springframework.stereotype.Controller;

/**
 * 商品上传 com.vix.E_business.item.itemupload.processor.ItemUpLoadProcessor
 * 
 * @author zhanghaibing
 *
 * @date 2014年7月22日
 */
@Controller("itemUpLoadProcessor")
public class ItemUpLoadProcessor {/*

	/*
	public PToGProcessor() throws MalformedUriException {
		super(new ProcessorUri("cloudsoft/processor/" + CommandRequestTypeConstants.PTOG_REQUEST));
	}

	@Override
	public IResponse process(Processible processible) throws ProcessException {

		if (!(processible instanceof PToGRequest))
			throw new ProcessException(new InvalidRequestTypeException("Invalid request type!"));
		Long channelId = 3974223300661219L;
		PToGResponse PToGResponse = new PToGResponse();
		if (channelId != null && !"".equals(channelId)) {
			try {
				ptoG(channelId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return PToGResponse;
	}

	private void ptoG(Long channelId) throws CacheException, TypeConversionException, MalformedUriException, CacheLoadingException {
		// 获取产品信息
		RowSet inventoryProductRowSet = getInventoryProduct();
		if (inventoryProductRowSet != null && inventoryProductRowSet.size() > 0) {
			CategoryConverter converter = CategoryConverter.getInstance();
			List<String> prdSkuPropsList = new ArrayList<String>();
			for (Row inventoryProduct : inventoryProductRowSet) {
				Integer typeId = inventoryProduct.getInt(new AttributeUri(InventoryProduct.TYPEID));
				String productProps = inventoryProduct.getString(new AttributeUri(InventoryProduct.PROPS));
				// 调用类目转换
				// converter.toGoods(typeId, productProps,
				// prdSkuPropsList.toArray(new String[0]), 3, 1405);
			}
			Map<String, String> map = getPropsBuyFile();
			for (Row inventoryProduct : inventoryProductRowSet) {
				int i = 1;
				RowSet warehouseProductRowSet = getWarehouseProductRowSet(inventoryProduct.getPkValue());
				if (inventoryProduct != null) {
					try {
						// 上传到网店
						addGoodsToShop(channelId, inventoryProduct, warehouseProductRowSet, map.get(StringUtils.getString(i)));
					} catch (TypeConversionException e1) {
						e1.printStackTrace();
					} catch (CacheException e) {
						e.printStackTrace();
					} catch (MalformedUriException e) {
						e.printStackTrace();
					}
				}
				i++;
			}
		}
	}

	private Item addGoodsToShop(Long targetChannelId, Row inventoryProduct, RowSet warehouseProductRowSet, String newProps) throws TypeConversionException, MalformedUriException, CacheException {
		Item item = new Item();
		int storeNum = 0;
		for (Row row : warehouseProductRowSet) {
			int store = row.getInt(new AttributeUri(WarehouseProduct.AVAILABLEFORSALE));
			storeNum += store;
		}
		item.setItemName(inventoryProduct.getString(new AttributeUri(InventoryProduct.PRODUCTNAME)));
		item.setLength(inventoryProduct.getString(new AttributeUri(InventoryProduct.LENGTH)));
		item.setWide(inventoryProduct.getString(new AttributeUri(InventoryProduct.WIDE)));
		item.setHeight(inventoryProduct.getString(new AttributeUri(InventoryProduct.HEIGHT)));
		item.setWeight(inventoryProduct.getString(new AttributeUri(InventoryProduct.WEIGHT)));
		item.setMktprice(inventoryProduct.getString(new AttributeUri(InventoryProduct.MKTPRICE)));
		item.setPrice(inventoryProduct.getString(new AttributeUri(InventoryProduct.PRICE)));

		String oldIntro = inventoryProduct.getString(new AttributeUri(InventoryProduct.INTRO));
		String newIntro = null;
		if (oldIntro != null && !"".equals(oldIntro.trim()) && !"null".equals(oldIntro)) {
			newIntro = replaceImageUrl(oldIntro);
		} else {
			newIntro = "暂无商品详情描述。";
		}
		item.setIntro(newIntro);
		item.setOuterId(inventoryProduct.getString(new AttributeUri(InventoryProduct.BN)));
		item.setCatalogId(1405L);

		CloudItemAddRequest cloudItemAddRequest = new CloudItemAddRequest();
		cloudItemAddRequest.setItemName(inventoryProduct.getString(new AttributeUri(InventoryProduct.PRODUCTNAME)));
		if (inventoryProduct.getString(new AttributeUri(InventoryProduct.LENGTH)) != null && !"".equals(inventoryProduct.getString(new AttributeUri(InventoryProduct.LENGTH)))) {
			cloudItemAddRequest.setLength(inventoryProduct.getString(new AttributeUri(InventoryProduct.LENGTH)));
		}
		if (inventoryProduct.getString(new AttributeUri(InventoryProduct.WIDE)) != null && !"".equals(inventoryProduct.getString(new AttributeUri(InventoryProduct.WIDE)))) {
			cloudItemAddRequest.setWide(inventoryProduct.getString(new AttributeUri(InventoryProduct.WIDE)));
		}
		if (inventoryProduct.getString(new AttributeUri(InventoryProduct.HEIGHT)) != null && !"".equals(inventoryProduct.getString(new AttributeUri(InventoryProduct.HEIGHT)))) {
			cloudItemAddRequest.setHeight(inventoryProduct.getString(new AttributeUri(InventoryProduct.HEIGHT)));
		}
		if (inventoryProduct.getString(new AttributeUri(InventoryProduct.WEIGHT)) != null && !"".equals(inventoryProduct.getString(new AttributeUri(InventoryProduct.WEIGHT)))) {
			cloudItemAddRequest.setWeight(inventoryProduct.getString(new AttributeUri(InventoryProduct.WEIGHT)));
		}
		cloudItemAddRequest.setMktprice(inventoryProduct.getString(new AttributeUri(InventoryProduct.MKTPRICE)));
		cloudItemAddRequest.setPrice(inventoryProduct.getString(new AttributeUri(InventoryProduct.PRICE)));
		cloudItemAddRequest.setIntro(newIntro);
		cloudItemAddRequest.setOuterId(inventoryProduct.getString(new AttributeUri(InventoryProduct.BN)));

		cloudItemAddRequest.setProps(newProps);
		cloudItemAddRequest.setCatalogId(1405L);
		cloudItemAddRequest.setItemImage(inventoryProduct.getString(new AttributeUri(InventoryProduct.DEFAULTIMAGEURL)));
		cloudItemAddRequest.setStoreNum(storeNum);
		cloudItemAddRequest.setTenantId(StaticContext.getTenantId());
		cloudItemAddRequest.setChannelId(StringUtils.getString(targetChannelId));
		CloudShopsApiRequest<CloudItemAddResponse> cmd = new CloudShopsApiRequest<CloudItemAddResponse>(StaticContext.getTenantId(), StaticContext.getUserId(), false);
		cmd.setCloudShopsRequest(cloudItemAddRequest);
		CloudItemAddResponse cloudItemAddResponse = null;
		try {
			CloudShopsApiResponse<CloudItemAddResponse> cloudShopsApiResponse = (CloudShopsApiResponse<CloudItemAddResponse>) RequestExecutor.executeCommand(cmd);
			if (cloudShopsApiResponse != null && cloudShopsApiResponse.isSuccess()) {
				cloudItemAddResponse = cloudShopsApiResponse.getResponse();
				if (cloudItemAddResponse.isSuccess()) {
					System.out.println(cloudItemAddResponse.getBody());
				}
			}
		} catch (ProcessException e) {
			e.printStackTrace();
		}
		return item;
	}

	private Map<String, String> getPropsBuyFile() {
		Map<String, String> map = new HashMap<String, String>();
		
		 * Set<Map.Entry<String, String>> set = map.entrySet(); for
		 * (Iterator<Map.Entry<String, String>> it = set.iterator();
		 * it.hasNext();) { Map.Entry<String, String> entry = (Map.Entry<String,
		 * String>) it.next(); System.out.println(entry.getKey() + "--->" +
		 * entry.getValue()); }
		 
		// 打开源数据的JSON文件
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("D:/prdData.json"));
			// 读入产品属性JSON字符串
			while (true) {
				int i = 1;
				String prdProps;
				try {
					prdProps = reader.readLine();
					String goodsProps = getProps(prdProps);
					map.put(StringUtils.getString(i), goodsProps);
					if (prdProps.equals("EOF"))
						break;
					// 处理当前字符串，调京东API
				} catch (IOException e) {
					e.printStackTrace();
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return map;
	}

	public String replaceImageUrl(String html) {
		String intro = null;
		Document doc;
		try {
			doc = Jsoup.parseBodyFragment(html);
			Elements images = doc.getElementsByTag("img");
			for (Element element : images) {
				try {
					String imageName = buildGoodsImage(element.attr("src"));
					element.attr("src", "http://static.109mall.cn/" + StaticContext.getTenantId() + "/" + imageName);
				} catch (MalformedUriException e) {
					e.printStackTrace();
				}
			}
			intro = doc.toString();
			System.out.println(intro);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return intro;
	}

	private String buildGoodsImage(String imageUri) throws MalformedUriException, IOException {
		String imageName = null;
		ContinueFTP ftp = new ContinueFTP();
		String name = Config.getProperty("ftp.user");
		String password = Config.getProperty("ftp.password");
		String httpservice = Config.getProperty("ftp.host");
		SimpleDateFormat dateFormator = new SimpleDateFormat("yyyyMMdd");
		int port = Integer.parseInt(Config.getProperty("ftp.port"));
		StringBuilder finalName = null;
		try {
			finalName = new StringBuilder();
			if (imageUri != null && !"".equals(imageUri)) {
				URL url = new URL(imageUri);
				url.openConnection();
				InputStream ins = url.openStream();
				finalName.append("goods");
				finalName.append("/");
				String str = dateFormator.format(new Date());
				finalName.append(str);
				finalName.append("/");
				finalName.append(UUID.randomUUID().toString().replaceAll("-", ""));
				finalName.append(".");
				finalName.append(imageUri.substring(imageUri.lastIndexOf(".") + 1, imageUri.length()));
				ftp.connect(httpservice, port, name, password);
				imageName = finalName.toString();
				ftp.uploadFile(StaticContext.getTenantId() + "/" + finalName.toString(), ins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageName;
	}

	private RowSet getInventoryProduct() {

		String sql = "SELECT * from inventory_product";
		RowSet rowSet = null;
		try {
			rowSet = queryBySQL(null, sql);
		} catch (ProcessException e1) {
			e1.printStackTrace();
		}
		return rowSet;
	}

	public String getProps(String props) {
		String[] propArray = props.replace("{", "").replace("}", "").split(";");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (String prop : propArray) {
			Map<String, String> map = new HashMap<String, String>();
			String[] p = prop.split(":");
			if (p != null && p.length > 0 && !"NULL".equals(p[1]) && !"null".equals(p[1])) {
				map.put(p[0], p[1]);
				list.add(map);
			}
		}
		String propsstr = null;
		StringBuffer str = new StringBuffer();
		for (Map<String, String> map1 : list) {
			Set<String> keySet = map1.keySet();
			for (String key : keySet) {
				str.append(key).append(":").append(map1.get(key)).append(";");
			}
		}
		if (str != null && !"".equals(str) && str.toString().endsWith(";")) {
			propsstr = str.substring(0, str.toString().length() - 1);
		}
		return propsstr;
	}

	private RowSet getWarehouseProductRowSet(Long inventoryProductId) {

		String sql = "SELECT * from warehouse_product where INVENTORY_PRODUCT_ID = " + inventoryProductId;
		RowSet rowSet = null;
		try {
			rowSet = queryBySQL(null, sql);
		} catch (ProcessException e1) {
			e1.printStackTrace();
		}
		return rowSet;
	}

	private RowSet queryBySQL(SqlUri uri, String sql) throws ProcessException {
		DBConnection conn = null;
		RowSet rowSet = null;
		try {
			conn = StaticContext.getDBConnection();
			rowSet = conn.executeQuery(uri, sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProcessException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowSet;
	}
*/}
