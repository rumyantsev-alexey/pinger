package ru.job4j.pinger.tasks;

import lombok.NoArgsConstructor;
import ru.job4j.pinger.clasez.Ping;
import ru.job4j.pinger.clasez.PingImplIcmpPing;
import ru.job4j.pinger.clasez.Result;
import ru.job4j.pinger.clasez.SampleResult;

import java.net.UnknownHostException;

@NoArgsConstructor
public class PingTask implements Runnable {
    private Ping ping = new PingImplIcmpPing();
    private  Integer count = 1;

    {
        try {
            ping.setIp("ya.ru");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ping.setTimeOut(53);
        ping.setPacketsize(32);
        ping.setTTL(53);
        count = 2;
    }

    @Override
    public void run() {
        String result= ping.reportPing(ping.ping(count));
        Result rrr = new SampleResult();
        rrr.proceed("aaa111", result);
    }
}
