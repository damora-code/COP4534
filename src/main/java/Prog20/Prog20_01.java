package Prog20;

import javax.swing.*;

public class Prog20_01 {
    public Prog20_01() {
        // circle
        int centerX = 100;
        int centerY = 100;
        int radius = 20;

        // rectangle
        int leftX = 50;
        int topY = 300;
        int width = 200;
        int height = 50;

        FrameDisplay frame = new FrameDisplay(centerX, centerY, radius,
                leftX, topY, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        delay(1000);
        while (true) {
            while (distance(centerX, centerY, centerX, topY) > radius) {
                delay(10);

                frame.setCircle(centerX, centerY, radius);
                frame.repaint();

                centerY++;
            }

            //added for program 20_02 to bounce ball once it hits the box
            while (centerY > 100) {
                delay(10);
                frame.setCircle(centerX, centerY, radius);
                frame.repaint();

                centerY--;
            }

        }
    }

    public static void main(String[] args) {
        new Prog20_01();
    }

    /**
     * Determines distance between given points
     */
    public double distance(int x0, int y0, int x1, int y1) {
        return Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
    }

    /**
     * Method that makes the thread that it's called from to pause.
     *
     * @param ms milliseconds the thread will pause
     */
    public void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}