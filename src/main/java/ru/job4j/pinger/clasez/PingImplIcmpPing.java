package ru.job4j.pinger.clasez;

import lombok.NoArgsConstructor;
import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Component
@NoArgsConstructor
public class PingImplIcmpPing implements Ping {

    private InetAddress ip = InetAddress.getLoopbackAddress();
    private IcmpPingRequest ipr = new IcmpPingRequest();
    private Integer cnt;

    {
        ipr.setHost(InetAddress.getLoopbackAddress().toString());
    }

    public void PingImpIcmpPing (String host, Integer count, Integer packetsize, Integer ttl, Integer timeout) throws UnknownHostException {
        this.setIp(host);
        this.setTimeOut(timeout);
        this.setPacketsize(packetsize);
        this.setTTL(ttl);
        cnt = count;

    }


    @Override
    public void setIp(String host) throws UnknownHostException {
        this.ip = InetAddress.getByName(host);
        this.ipr.setHost(host);
    }

    @Override
    public void setPacketsize(int packetsize) {
        this.ipr.setPacketSize(packetsize);
    }

    @Override
    public void setTTL(int ttl) {
        this.ipr.setTtl(ttl);
    }

    @Override
    public void setTimeOut(long ttl) {
        this.ipr.setTimeout(ttl);
    }

    @Override
    public List<IcmpPingResponse> ping(int count) {
        return IcmpPingUtil.executePingRequests(ipr, count < 0 ? 2 : count + 1);
    }

    @Override
    public String reportPing(List<IcmpPingResponse> list) {
        IcmpPingResponse one = list.get(0);
        String header = "Pinging %s [%s] with %s bytes of data:\n\n";
        String body = "Reply from %s: bytes=%s time=%sms TTL=%s\n";
        String footer = "\nPing statistics for %s:\n"
                + "    Packets: Sent = %s, Received = %s, Lost = %s (%s prc loss),\n"
                + "Approximate round trip times in milli-seconds:\n"
                + "    Minimum = %sms, Maximum = %sms, Average = %sms\n";

        StringBuilder result = new StringBuilder();
        int lost = (int) list.stream().filter((x) -> !x.getSuccessFlag()).count();
        int size = list.size();
        int min = (int) one.getDuration();
        int max = (int) one.getDuration();
        int sum = 0;
        for (IcmpPingResponse r: list) {
            sum += r.getDuration();
            if (r.getDuration() < min) {
                min = (int) r.getDuration();
            }
            if (r.getDuration() > max) {
                max = (int) r.getDuration();
            }

        }

        result.append(String.format(header, this.ip.getHostName(), this.ip.getHostAddress(), one.getSize()));
        list.forEach((x) -> result.append(String.format(body, this.ip.getHostAddress(), x.getSize(), x.getDuration(), x.getTtl())));
        result.append(String.format(footer, this.ip.getHostAddress(), size, size - lost, lost, (lost / size) * 100, min, max, sum / size));
        return result.toString();
    }

}
