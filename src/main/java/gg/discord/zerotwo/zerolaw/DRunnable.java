package gg.discord.zerotwo.zerolaw;

public abstract class DRunnable implements Runnable {
    String msg;
    public DRunnable(String message) {
        this.msg = message;
    }

    @Override
    public void run() {

    }
}