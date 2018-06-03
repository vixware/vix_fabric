package com.vix.core.constant.vo;

public class IndexPage {

	private String pcPage;
	
	private String padPage;
	
	private String mobilePage;

	public IndexPage() {
		super();
	}

	public IndexPage(String pcPage, String padPage, String mobilePage) {
		super();
		this.pcPage = pcPage;
		this.padPage = padPage;
		this.mobilePage = mobilePage;
	}

	public String getPcPage() {
		return pcPage;
	}

	public void setPcPage(String pcPage) {
		this.pcPage = pcPage;
	}

	public String getPadPage() {
		return padPage;
	}

	public void setPadPage(String padPage) {
		this.padPage = padPage;
	}

	public String getMobilePage() {
		return mobilePage;
	}

	public void setMobilePage(String mobilePage) {
		this.mobilePage = mobilePage;
	}
	
}
