package com.vix.common.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;

public class ExportReport {
	/**
	 * 导出html
	 * @param jasperPrint
	 * @return
	 */
	public static String exportToHTML(JasperPrint jasperPrint){
		StringBuffer sb = new StringBuffer();
		try {
			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sb);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "../servlets/image?image=");
			//报表边框图片设置IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE，不使用图片
		    exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
		    exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
	/*		exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");*/
			exporter.exportReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 导出html
	 * @param jasperPrint
	 * @return
	 */
	public static void exportToPDF(JasperPrint jasperPrint){
		try {
			JRPdfExporter pdfexporter = new JRPdfExporter();
			pdfexporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        //如果保存到硬盘用下面语句
	        pdfexporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "D:/mypdf.pdf");
	        pdfexporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.FALSE);
	        pdfexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			pdfexporter.exportReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 导出RTF
	 * @param jasperPrint
	 * @return
	 */
	public static void exportToRTF(JasperPrint jasperPrint){
		try {
			JRRtfExporter rtfExporter=new JRRtfExporter();
			rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//设置导出文件名
			rtfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "D:/myrtf.rtf");
			rtfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			rtfExporter.exportReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 导出xml
	 * @param jasperPrint
	 * @return
	 */
	public static void exportToXML(JasperPrint jasperPrint){
		try {
			JRXmlExporter xmlExporter=new JRXmlExporter();
			xmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			xmlExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"D:/myxml.xml");
			xmlExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			xmlExporter.exportReport();

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 导出Excel
	 * @param jasperPrint
	 * @return
	 */
	public static void exportToExcel(JasperPrint jasperPrint){
		try {
			JRXlsExporter jrXlsExporter=new JRXlsExporter();
			jrXlsExporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
			//设置输出流
			jrXlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"D:/myexcel.xls");
			jrXlsExporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			jrXlsExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			jrXlsExporter.exportReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
