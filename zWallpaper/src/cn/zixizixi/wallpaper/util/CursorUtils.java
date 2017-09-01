package cn.zixizixi.wallpaper.util;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 鼠标样式工具
 * @author Tanken·L
 * @version 20170901
 */
public class CursorUtils {
    
    /**
     * 鼠标效果
     * 
     * @param comObj 控件对象
     * @param type default/text/pointer/help...
     */
    public void addMouLis(final Component comObj, final int type) {
        comObj.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent mouseEvent) {
                comObj.setCursor(new Cursor(type)); // 鼠标进入Text区后变为文本输入指针
            }

            public void mouseExited(MouseEvent mouseEvent) {
                comObj.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 鼠标离开Text区后恢复默认形态
            }
        });
    }

}
