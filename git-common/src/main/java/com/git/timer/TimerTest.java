package com.git.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    private static int i = 0;

    public static void main(String[] args) throws Exception {

        Timer timer = new Timer();

        /**
         * void java.util.Timer.schedule(TimerTask task, long delay)
         */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("@schedule(TimerTask task, long delay),表示延迟delay时间后,执行TimerTask任务");
            }
        }, 5 * 1000);

        /**
         * 在Date指定的特定时刻之后执行TimerTask的任务
         * void java.util.Timer.schedule(TimerTask task, Date time)
         */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("@schedule(TimerTask task, Date time),表示在Date时间执行TimerTask任务");
                sayHello("Panda");
            }
        }, new Date(System.currentTimeMillis() + 1000));

        /**
         * void java.util.Timer.schedule(TimerTask task, long delay, long period)
         * 在延迟delay时间之后,执行timerTask任务,每隔period时间执行一次
         */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("@schedule(TimerTask task, long delay, long period),表示在延迟delay时间之后,执行timerTask任务,每隔period时间执行一次...");
                sayHello("Panda");
            }
        }, 5 * 1000, 2 * 1000);
        
        /**
         * void java.util.Timer.scheduleAtFixedRate(TimerTask task, long delay, long period)
         */
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("@scheduleAtFixedRate(TimerTask task, long delay, long period),表示在delay时间之后,每隔period时间,执行一次timerTask任务");
                sayHello("Panda");

            }
        }, 3 * 1000, 2 * 1000);

        /**
         * void java.util.Timer.scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
         */
        SimpleDateFormat fTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date firstTime = fTime.parse("2014/6/18 11:37:00");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("@scheduleAtFixedRate(TimerTask task, Date firstTime, long period),表示在指定时间之后,每隔period时间执行一次timerTask任务;如果指定时间在当前时间之前,则立即打印出指定时间和当前时间之间的时间段应该执行的任务");
                sayHello("Panda");
            }
        }, firstTime, 5 * 1000);
    }

    public static void sayHello(String name) {
        System.out.println("Hello " + name + i++ + " - " + new Date());
    }

}
