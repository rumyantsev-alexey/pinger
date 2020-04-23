package ru.job4j.pinger.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class TaskDto {

    private String name1;
    private Tools sellist1;
    private Integer cnt;
    private Integer packetsize;
    private Integer ttl;
    private Integer timeout;
    private String text2;
    private String date1;
    private String date2;
    private Integer text3;
    private String sellist2;
    private String sellist3;
    private ToolsFront sellist4;
    private String text4;

    public Task convertToTask(TaskDto tdto, UserDto udto) {
        Task result = new Task();

        result.setUser(udto.convert());
        result.setName1(tdto.getName1());
        result.setCnt(tdto.getCnt());
        result.setPacketsize(tdto.getPacketsize());
        result.setTtl(tdto.getTtl());
        result.setTimeout(tdto.getTimeout());
        result.setSellist1(tdto.getSellist1());
        result.setText2(tdto.getText2());
        result.setDate1(Timestamp.valueOf(tdto.getDate1().replace("T", " ") + ":00"));
        result.setDate2(Timestamp.valueOf(tdto.getDate2().replace("T", " ") + ":00"));
        result.setText3(tdto.getText3());
        result.setSellist2(tdto.getSellist2());
        result.setSellist3(tdto.getSellist3());
        result.setSellist4(tdto.getSellist4());
        result.setText4(tdto.getText4());
        result.setReview(false);

        return result;
    }

}
