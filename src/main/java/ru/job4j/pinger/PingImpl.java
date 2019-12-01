package ru.job4j.pinger;

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Component
public class PingImpl implements Ping {

    private InetAddress ip = InetAddress.getLoopbackAddress();

    public void setIp(String host) throws UnknownHostException {
        this.ip = InetAddress.getByName(host);
    }

    @Override
    public List<IcmpPingResponse> ping(int count) {
        IcmpPingRequest req = IcmpPingUtil.createIcmpPingRequest();
        req.setHost(this.ip.getHostAddress());
        return IcmpPingUtil.executePingRequests(req, count <= 1 ? 2 : count + 1);
    }

    @Override
    public List<IcmpPingResponse> ping() {
        return this.ping(4);
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
