package net.itanken.ddos.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * 自定义滚动条
 * @author iTanken
 * @version 2016-11-02 19:27:55
 */
public class ScrollBarUI extends BasicScrollBarUI {
	public ScrollBarUI() {
		super();
	}
/*
	@Override
	protected void configureScrollBarColors() {
		// thumbColor = new Color(163,184,204); // 滑块
		trackColor = new Color(240, 240, 240); // 滑道
	}*/

	/**
	 * 滑道
	 */
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		super.paintTrack(g, c, trackBounds);
	}

	/**
	 * 重绘滑块
	 */
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		// 把绘制区的x，y点坐标定义为坐标系的原点，不然拖动就失效了
		g.translate(thumbBounds.x, thumbBounds.y); 
		g.setColor(new Color(150,200,200)); // 设置滑块颜色
		// 画一个圆角矩形，前四个参数就不多讲了，坐标和宽高，后两个参数需要注意一下，是用来控制角落的圆角弧度
		g.drawRoundRect(3, 0, 11, thumbBounds.height-1, 11, 11); 
		// 消除锯齿
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.addRenderingHints(rh);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // 半透明
		// 设置填充颜色，这里设置了渐变，由下往上
		// g2.setPaint(new GradientPaint(c.getWidth() / 2, 1, Color.BLACK, c.getWidth() / 2, c.getHeight(), Color.GRAY));
		// 填充圆角矩形
		g2.fillRoundRect(3, 0, 11, thumbBounds.height-1, 9, 9);
	}
	
	/**
	 * 底部按钮
	 */
	protected JButton createIncreaseButton(int orientation) {
		JButton button = new JButton();
		button.setBorder(null);
		return button;
	}
	
	/**
	 * 顶部按钮
	 */
	protected JButton createDecreaseButton(int orientation) {
		JButton button = new JButton();
		button.setBorder(null);
		return button;
	}

}
