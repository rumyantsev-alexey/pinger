package ru.job4j.pinger.clasez;

import org.icmp4j.IcmpPingResponse;

import java.net.UnknownHostException;
import java.util.List;

public interface Ping {
    void setIp(String host) throws UnknownHostException;
    void setPacketsize(int packetsize);
    void setTTL(int ttl);
    void setTimeOut(long ttl);

    List<IcmpPingResponse> ping(int count);
    String reportPing(List<IcmpPingResponse> list);
}
