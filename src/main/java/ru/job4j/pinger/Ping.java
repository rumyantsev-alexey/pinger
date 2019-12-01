package ru.job4j.pinger;

import org.icmp4j.IcmpPingResponse;

import java.net.UnknownHostException;
import java.util.List;

public interface Ping {
    List<IcmpPingResponse> ping(int count) throws UnknownHostException;
    List<IcmpPingResponse> ping() throws UnknownHostException;
    String reportPing(List<IcmpPingResponse> list) throws UnknownHostException;
}
