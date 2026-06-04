package com.example;

import com.birdbrain.Finch;

public class FinchNavigatorRunner {
    public static void main(String[] args) {
        Finch myFinch = new FinchNavigator();

        myFinch.playNote(60,0.5);

        for (int i = 0; i < 10; i++) {
            myFinch.setBeak(100, 100, 100);
            myFinch.pause(1);
            myFinch.setBeak(0, 0, 0);
            myFinch.pause(1);
        }

        myFinch.stopAll();
        myFinch.disconnect();
    }
}