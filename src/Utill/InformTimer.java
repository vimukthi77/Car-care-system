/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utill;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformTimer {
    public static void startTimer(JLabel label, int delay) {
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the text after the specified delay
                label.setText("");
                // Stop the timer
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }
}

