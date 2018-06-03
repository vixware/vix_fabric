package com.vix.nvix.wx.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;

/**
 * @author jian shen
 *
 */
public class PadImageUtil {
	public static enum ImageFormat {
		JPEG, JPG, BMP, PNG, JIF
	}

	/**
	 * 默认最大宽度为600
	 */
	private static final int DEFAULT_NORMAL_MAX_WIDTH = 600;
	/**
	 * 默认最大高度为800
	 */
	private static final int DEFAULT_NORMAL_MAX_HEIGHT = 800;
	/**
	 * 默认的头像基准比例宽度为450
	 */
	private static final int DEFAULT_SCALE_WIDTH = 240;
	/**
	 * 默认的头像目标宽度为200
	 */
	private static final int DEFAULT_TARGET_WIDTH = 240;
	/**
	 * 默认的头像目标高度为200
	 */
	private static final int DEFAULT_TARGET_HEIGHT = 240;

	private static class ImageAttr {
		private int width;
		private int height;

		public ImageAttr(int width, int height) {
			this.width = width;
			this.height = height;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}
	}

	/**
	 * 通过后缀名判断一个文件是否是图片文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return true-是图片，false-不是图片
	 */
	public static boolean isImage(String fileName) {
		boolean isImage = false;

		int pos = fileName.lastIndexOf(".");
		if (pos > 0) {
			String ext = fileName.substring(pos + 1);
			if (ImageFormat.JPEG.toString().equalsIgnoreCase(ext) || ImageFormat.JPG.toString().equalsIgnoreCase(ext) || ImageFormat.BMP.toString().equalsIgnoreCase(ext) || ImageFormat.PNG.toString().equalsIgnoreCase(ext) || ImageFormat.JIF.toString().equalsIgnoreCase(ext)) {
				isImage = true;
			}
		}

		return isImage;
	}

	/**
	 * 缩放图片，缩放后保持原图片的比例，且输出格式转化为JPEG
	 * 
	 * @param srcImage
	 *            源图片地址
	 * @param targetImage
	 *            目标图片地址
	 * @param maxWidth
	 *            指定的目标图片最大宽度，如果该值不指定使用Integer.MAX_VALUE
	 * @param maxHeight
	 *            指定的目标图片最大高度，如果该值不指定使用Integer.MAX_VALUE
	 */
	public static void scale(File srcImage, File targetImage, int maxWidth, int maxHeight, Float quality) {
		scale(srcImage, targetImage, maxWidth, maxHeight, quality, true, ImageFormat.JPEG);
	}

	/**
	 * 缩放图片
	 * 
	 * @param srcImage
	 *            源图片地址
	 * @param targetImage
	 *            目标图片地址
	 * @param maxWidth
	 *            指定的目标图片最大宽度，如果该值不指定使用Integer.MAX_VALUE
	 * @param maxHeight
	 *            指定的目标图片最大高度，如果该值不指定使用Integer.MAX_VALUE
	 * @param keepProportion
	 *            是否保持原图片比例
	 * @param format
	 *            目标图片输出格式， 如JPEG
	 */
	public static void scale(File srcImage, File targetImage, int maxWidth, int maxHeight, Float quality, boolean keepProportion, ImageFormat format) {
		try {
			BufferedImage src = ImageIO.read(srcImage);
			ImageAttr imageAttr = zoomIn(src, maxWidth, maxHeight, keepProportion);
			Image image = src.getScaledInstance(imageAttr.getWidth(), imageAttr.getHeight(), Image.SCALE_SMOOTH);
			BufferedImage target = new BufferedImage(imageAttr.getWidth(), imageAttr.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics graphic = target.getGraphics();
			graphic.drawImage(image, 0, 0, null);
			graphic.dispose();
			// ImageIO.write(target, format.toString(), targetImage);
			if (quality == null || quality.floatValue() == 0)
				quality = 0.8f;
			bufferedImageTobytes(target, quality, format.toString(), targetImage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 自己设置压缩质量来把图片压缩成byte[]
	 * 
	 * @param image
	 *            压缩源图片
	 * @param quality
	 *            压缩质量，在0-1之间，
	 * @return 返回的字节数组
	 */
	private static byte[] bufferedImageTobytes(BufferedImage image, float quality, String formatName, Object output) {
		// 如果图片空，返回空
		if (image == null) {
			return null;
		}
		// 得到指定Format图片的writer
		Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(formatName);// 得到迭代器
		ImageWriter writer = iter.next(); // 得到writer

		// 得到指定writer的输出参数设置(ImageWriteParam )
		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
		iwp.setCompressionQuality(quality); // 设置压缩质量参数

		iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		iwp.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

		// 开始打包图片，写入byte[]
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
		IIOImage iIamge = new IIOImage(image, null, null);
		try {
			// 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
			// 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
			// writer.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
			writer.setOutput(ImageIO.createImageOutputStream(output));
			writer.write(null, iIamge, iwp);

		} catch (IOException e) {
			System.out.println("write errro");
			e.printStackTrace();
		}
		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 收缩图片尺寸
	 * 
	 * @param src
	 *            原图片
	 * @param maxWidth
	 *            指定的目标图片最大宽度，如果该值不指定使用Integer.MAX_VALUE
	 * @param maxHeight
	 *            指定的目标图片最大高度，如果该值不指定使用Integer.MAX_VALUE
	 * @param keepProportion
	 *            是否保持原图片比例
	 * @return 计算后的目标图片宽度和高度
	 */
	private static ImageAttr zoomIn(BufferedImage src, double maxWidth, double maxHeight, boolean keepProportion) {
		ImageAttr imageAttr = null;

		if (maxWidth <= 0) {
			maxWidth = DEFAULT_NORMAL_MAX_WIDTH;
		}
		if (maxWidth <= 0) {
			maxHeight = DEFAULT_NORMAL_MAX_HEIGHT;
		}

		double srcWidth = src.getWidth();
		double srcHeight = src.getHeight();

		if (srcWidth < maxWidth && srcHeight < maxHeight) { // 如果图片的宽度和高度都比指定的宽度高度小，则图片不发生收缩
			imageAttr = new ImageAttr(Double.valueOf(srcWidth).intValue(), Double.valueOf(srcHeight).intValue());
		} else { // 否则计算宽度和高度
			double targetWidth = maxWidth;
			double targetHeight = maxHeight;

			if (keepProportion) {
				double wProportion = targetWidth / srcWidth; // 计算宽度缩放比例
				double hProportion = targetHeight / srcHeight; // 计算高度缩放比例

				if (targetWidth < srcWidth && targetHeight >= srcHeight) { // 如果指定宽度比原图宽度小，而指定高度比原高度大
					targetHeight = srcHeight * wProportion; // 原图的高度按宽度的缩放比例缩放
				} else if (targetWidth >= srcWidth && targetHeight < srcHeight) { // 如果指定高度比原图高度小，而指定宽度比原宽度大
					targetWidth = srcWidth * hProportion; // 原图的宽度按高度的缩放比例缩放
				} else { // 如果指定的宽度和高度都比原图小
					if (wProportion > hProportion) { // 如果宽度缩放的比高度小
						targetWidth = srcWidth * hProportion; // 宽度按高度的比例缩放（即缩小的更多）
					} else {
						targetHeight = srcHeight * wProportion; // 高度按宽度的比例缩放（即缩小的更多）
					}
				}
			}

			imageAttr = new ImageAttr(Double.valueOf(targetWidth).intValue(), Double.valueOf(targetHeight).intValue());
		}

		return imageAttr;
	}

	/**
	 * 图片类型转换
	 * 
	 * @param srcImage
	 *            源图片地址
	 * @param targetImage
	 *            目标图片地址
	 * @param format
	 *            目标图片存储格式， 如JPEG
	 */
	public static void convert(File srcImage, File targetImage, ImageFormat format) {
		try {
			BufferedImage src = ImageIO.read(srcImage);
			ImageIO.write(src, format.toString(), targetImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 黑白化图片
	 * 
	 * @param srcImage
	 *            源图片地址
	 * @param targetImage
	 *            目标图片地址
	 * @param format
	 *            目标图片存储格式， 如JPEG
	 */
	public static void gray(File srcImage, File targetImage, ImageFormat format) {
		try {
			BufferedImage src = ImageIO.read(srcImage);
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, format.toString(), targetImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 截取头像照片
	 * 
	 * @param srcImage
	 *            源图片地址
	 * @param targetImage
	 *            目标图片地址
	 * @param x1
	 *            左上角X坐标
	 * @param y1
	 *            左上角Y坐标
	 * @param width
	 *            截取框宽度
	 * @param height
	 *            截取框高度
	 * @param format
	 *            目标图片存储格式， 如JPEG
	 */
	public static void headImage(File srcImage, File targetImage, int x1, int y1, int width, int height, ImageFormat format) {
		headImage(srcImage, targetImage, x1, y1, width, height, DEFAULT_SCALE_WIDTH, DEFAULT_TARGET_WIDTH, DEFAULT_TARGET_HEIGHT, format);
	}

	/**
	 * 截取头像照片
	 * 
	 * @param srcImage
	 *            源图片地址
	 * @param targetImage
	 *            目标图片地址
	 * @param x1
	 *            左上角X坐标
	 * @param y1
	 *            左上角Y坐标
	 * @param width
	 *            截取框宽度
	 * @param height
	 *            截取框高度
	 * @param scaleWidth
	 *            基准比例宽度
	 * @param targetWidth
	 *            目标图片宽度
	 * @param targetHeight
	 *            目标图片高度
	 * @param format
	 *            目标图片存储格式， 如JPEG
	 */
	public static void headImage(File srcImage, File targetImage, int x1, int y1, int width, int height, int scaleWidth, int targetWidth, int targetHeight, ImageFormat format) {
		try {
			BufferedImage src = ImageIO.read(srcImage);
			double srcWidth = src.getWidth();

			double scale = srcWidth / scaleWidth;

			Double xx1 = x1 * scale;
			Double yy1 = y1 * scale;
			Double w = width * scale;
			Double h = height * scale;

			cut(srcImage, targetImage, xx1.intValue(), yy1.intValue(), w.intValue(), h.intValue(), ImageFormat.BMP);

			BufferedImage cut = ImageIO.read(targetImage);
			ImageAttr imageAttr = new ImageAttr(targetWidth, targetHeight);
			Image image = cut.getScaledInstance(imageAttr.getWidth(), imageAttr.getHeight(), Image.SCALE_SMOOTH);
			BufferedImage target = new BufferedImage(imageAttr.getWidth(), imageAttr.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics graphic = target.getGraphics();
			graphic.drawImage(image, 0, 0, null);
			graphic.dispose();
			ImageIO.write(target, format.toString(), targetImage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 剪切图片
	 * 
	 * @param srcImage
	 *            源图片地址
	 * @param targetImage
	 *            目标图片地址
	 * @param x
	 *            起点X坐标
	 * @param y
	 *            起点Y坐标
	 * @param width
	 *            裁剪区域宽度
	 * @param height
	 *            裁剪区域高度
	 * @param format
	 *            目标图片存储格式， 如JPEG
	 */
	public static void cut(File srcImage, File targetImage, int x, int y, int width, int height, ImageFormat format) {
		FileInputStream fis = null;
		ImageInputStream iis = null;
		try {
			fis = new FileInputStream(srcImage);
			iis = ImageIO.createImageInputStream(fis);
			Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
			ImageReader reader = it.next();
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);
			BufferedImage src = reader.read(0, param);
			ImageIO.write(src, format.toString(), targetImage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (iis != null) {
					iis.close();
				}

				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {

			}
		}
	}

	public static void main(String... args) {
		File source = new File("d:\\album\\1321167240838.jpg");
		File scale = new File("d:\\album\\thumb_1321167240838.jpg");
		File gray = new File("d:\\album\\gray_1321167240838.jpg");
		File cut = new File("d:\\album\\cut_1321167240838.jpg");

		PadImageUtil.scale(source, scale, 400, 300, null, true, ImageFormat.JPEG);
		PadImageUtil.gray(source, gray, ImageFormat.JPEG);
		PadImageUtil.scale(source, scale, 450, Integer.MAX_VALUE, null, true, ImageFormat.JPEG);
		PadImageUtil.cut(scale, cut, 0, 0, 220, 220, ImageFormat.JPEG);

		PadImageUtil.headImage(source, cut, 10, 10, 300, 300, ImageFormat.JPEG);

		System.out.println(source.getName());
		System.out.println(source.getAbsolutePath());
		System.out.println(source.getPath());
	}

}
