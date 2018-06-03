package com.vix.common.base.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码
 * @author arron
 */
public class ValidateCode extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException{}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] captchaChallengeAsJpeg = null;
		Random random = new Random();
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			BufferedImage image = new BufferedImage(65,22,BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(this.getColor(200, 250));
			g.fillRect(0, 0, 65,22);
			g.setFont(new Font("Times New Roman", Font.BOLD, 14));
			g.setColor(this.getColor(180, 200));
			/** 绘制50条干扰线 */
			for (int i = 0; i < 50; i++) {
				int x1 = random.nextInt(65);
				int y1 = random.nextInt(22);
				int x2 = random.nextInt(65 - 3);
				int y2 = random.nextInt(22 - 3);
				BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
				Line2D line = new Line2D.Double(x1, y1, x2, y2);
				g2d.setStroke(bs);
				g2d.draw(line);
				g.setColor(getColor(180, 222));
			}
			StringBuffer codeStr = new StringBuffer();
			for (int i = 0; i < 4; i++) {
				char c = "ABCDEF2GH3JKL4MN5PQR6ST7UV8WX9YZ".charAt(random.nextInt(32));
				codeStr.append(c);
				Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110));
				g.setColor(color);
				g.drawString(String.valueOf(c), 15 * i + 4, 15);
			}
			String checkCode = codeStr.toString();
			request.getSession().setAttribute("ValidateCode",checkCode);
			g.dispose();
			try {
				ImageOutputStream imgOutput = ImageIO.createImageOutputStream(jpegOutputStream);
				ImageIO.write(image, "JPEG", imgOutput);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}
 
	/**
	 * 利用随机数，随机生成一个Color颜色的对象
	 * @param fc
	 * @param bc
	 * @return 颜色对象
	 */
	private Color getColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}