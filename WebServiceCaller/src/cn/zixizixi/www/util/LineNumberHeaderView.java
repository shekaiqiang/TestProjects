package cn.zixizixi.www.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

/**
 * 显示行号
 * 
 * @author Tanken·L
 * @version 20170905
 */
public class LineNumberHeaderView extends JComponent {

	private static final long serialVersionUID = 1L;
	private final Font DEFAULT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 11);
	public final Color DEFAULT_BACKGROUD = new Color(228, 228, 228);
	public final Color DEFAULT_FOREGROUD = Color.BLACK;
	public final int nHEIGHT = Integer.MAX_VALUE - 1000000;
	public final int WIDTH = 40;
	public final int MARGIN = 5;
	private int lineHeight;
	private int fontLineHeight;
	private int currentRowWidth;
	private FontMetrics fontMetrics;

	public LineNumberHeaderView() {
		this.setFont(DEFAULT_FONT);
		super.setForeground(DEFAULT_FOREGROUD);
		super.setBackground(DEFAULT_BACKGROUD);
		super.setPreferredSize(new Dimension(WIDTH, 9999 * this.getLineHeight())); // 行号做多显示 9999 行
	}
	
	@Override
	public void setFont(Font font) {
		super.setFont(font);
		fontMetrics = this.getFontMetrics(getFont());
		fontLineHeight = fontMetrics.getHeight();
	}
	
	public int getLineHeight() {
		if (lineHeight == 0) {
			return fontLineHeight;
		}
		return lineHeight;
	}
	
	public void setLineHeight(int lineHeight) {
		if (lineHeight > 0) {
			this.lineHeight = lineHeight;
		}
	}
	
	public int getStartOffset() {
		return 4;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int nlineHeight = this.getLineHeight();
		int startOffset = this.getStartOffset();
		Rectangle drawHere = g.getClipBounds();
		g.setColor(getBackground());
		g.fillRect(drawHere.x, drawHere.y, drawHere.width, drawHere.height);
		g.setColor(getForeground());
		int startLineNum = (drawHere.y / nlineHeight) + 1;
		int endLineNum = startLineNum + (drawHere.height / nlineHeight);
		int start = (drawHere.y / nlineHeight) * nlineHeight + nlineHeight - startOffset;
		for (int i = startLineNum; i <= endLineNum; ++i) {
			String lineNum = String.valueOf(i);
			// int width = fontMetrics.stringWidth(lineNum);
			// System.out.printf("%s - %d - %d\n", lineNum, start, nlineHeight);
			g.drawString(lineNum + " ", MARGIN + currentRowWidth, start);
			start += nlineHeight;
		}
		// super.setPreferredSize(new Dimension(WIDTH, endLineNum));
	}
}
