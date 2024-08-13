package com.coder.test;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author teacher_shi
 */
public class TimeThread implements Runnable{
    private JLabel label;

    public TimeThread(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {
        while (true) {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String text = dateTime.format(formatter);
            label.setText(text);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
