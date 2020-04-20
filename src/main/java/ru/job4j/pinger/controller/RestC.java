package ru.job4j.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.pinger.Ping;
import ru.job4j.pinger.Traceroute;

import java.net.UnknownHostException;

@RestController
public class RestC{

    @Autowired
    Ping ppp;

    @Autowired
    Traceroute ttt;

    @GetMapping(value = "/rest/ping")
    public String getPing(@RequestParam(value = "host") String host, @RequestParam(value = "count", required = false, defaultValue = "4") int count, @RequestParam(value = "packetsize", required = false, defaultValue = "32") int packetsize, @RequestParam(value = "ttl", required = false, defaultValue = "53") int ttl, @RequestParam(value = "timeout", required = false, defaultValue = "53") long timeout) {
        String res;
        count = count < 1? 1: count;
        packetsize = packetsize < 1? 32: packetsize;
        ttl = ttl < 1? 53: ttl;
        timeout = timeout < 1? -1: timeout;
        ppp.setPacketsize(packetsize);
        ppp.setTTL(ttl);
        ppp.setTimeOut(timeout);
        try {
            ppp.setIp(host);
            res = ppp.reportPing(ppp.ping(count));
        } catch (UnknownHostException e) {
            res = String.format("Ping request could not find host %s. Please check the name and try again.", host);
        }
        return  res;
    }

    @GetMapping (value = "/rest/traceroute")
    public String getTraceroute(@RequestParam(value = "host") String host, @RequestParam(value = "packetsize", required = false, defaultValue = "32") int packetsize, @RequestParam(value = "ttl", required = false, defaultValue = "30") int ttl, @RequestParam(value = "timeout", required = false, defaultValue = "30") long timeout) {
        String res;
        packetsize = packetsize < 1? 32: packetsize;
        ttl = ttl < 1? 30: ttl;
        timeout = timeout < 1? -1: timeout;
        ttt.setPacketsize(packetsize);
        ttt.setTTL(ttl);
        ttt.setTimeOut(timeout);
        try {
            ttt.setIp(host);
            res = ttt.reportTraceroute(ttt.traceroute());
        } catch (UnknownHostException e) {
            res = String.format("Ping request could not find host %s. Please check the name and try again.", host);
        }
        return  res;
    }

}
