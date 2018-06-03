package com.vix.common.id.storer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import com.vix.common.id.SequenceStorer;
import com.vix.common.id.exception.StoreSequenceException;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-20
 */
public class FileSequenceStorer implements SequenceStorer {

	public static final String DEFAULT_FILE_PATH = "vix-id-sequence-store.properties";
	/**
	 * 文件路径，包含文件名，
	 */
	private String filePath = DEFAULT_FILE_PATH;

	protected String getRealFilePath() throws StoreSequenceException {
		java.io.File tmp = new java.io.File(filePath);
		if (tmp.exists()) {
			return this.filePath;
		}
		URL url = FileSequenceStorer.class.getClassLoader().getResource(this.filePath);
		if (url == null) {
			final String msg = "存储sequence失败!没有发现文件：" + filePath;
			throw new StoreSequenceException(msg);
		}
		return url.getFile();
	}

	@Override
	public long load(String sequenceID) throws StoreSequenceException {

		java.util.Properties props = new java.util.Properties();
		final String realFilePath = getRealFilePath();
		java.io.FileInputStream is = null;
		try {
			is = new java.io.FileInputStream(realFilePath);
			props.load(is);
			String result = props.getProperty(sequenceID);
			if (result == null) {
				return -1;
			} else {
				return Long.parseLong(result);
			}
		} catch (FileNotFoundException e) {
			final String msg = "存储sequence失败!没有发现文件：" + realFilePath;
			throw new StoreSequenceException(msg, e);
		} catch (IOException e) {
			final String msg = "存储sequence失败!" + e.getMessage();
			throw new StoreSequenceException(msg, e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateMaxValueByFieldName(long sequence, String sequenceID) throws StoreSequenceException {
		java.util.Properties props = new java.util.Properties();
		final String realFilePath = getRealFilePath();
		java.io.FileInputStream is = null;
		try {
			is = new java.io.FileInputStream(realFilePath);
			props.load(is);
			props.setProperty(sequenceID, sequence + "");
		} catch (FileNotFoundException e) {
			final String msg = "存储sequence失败!没有发现文件：" + realFilePath;
			throw new StoreSequenceException(msg, e);
		} catch (IOException e) {
			final String msg = "存储sequence失败!" + e.getMessage();
			throw new StoreSequenceException(msg, e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		java.io.FileOutputStream out = null;
		try {
			out = new java.io.FileOutputStream(realFilePath);
			props.store(out, "e3 id sequence storer, don't edit");
		} catch (FileNotFoundException e) {
			final String msg = "存储sequence失败!没有发现文件：" + realFilePath;
			throw new StoreSequenceException(msg, e);
		} catch (IOException e) {
			final String msg = "存储sequence失败!" + e.getMessage();
			throw new StoreSequenceException(msg, e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
