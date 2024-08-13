package com.coder.hotel.util;

import javax.swing.*;

/**
 * @author teacher_shi
 */
public class UiUtil {
    public static void indent(JFrame src,JFrame dest){
        if (src!=null)
            src.setVisible(false);
        if (dest!=null)
            dest.setVisible(true);
    }
}
