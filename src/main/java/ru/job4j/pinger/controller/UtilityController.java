package ru.job4j.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.pinger.PingImpl;
import ru.job4j.pinger.models.TaskPingerDto;

import java.net.UnknownHostException;

@Controller
public class UtilityController {

    @Autowired
    private PingImpl ppp;

    @GetMapping(value = "/")
    public String getMain() {
        return "ping";
    }

    @GetMapping(value = "/ping")
    public @ResponseBody String getPing(@RequestParam(value = "host") String host, @RequestParam(value = "count", required = false, defaultValue = "4") int count, ModelAndView model) {
        String res;
        try {
            ppp.setIp(host);
        } catch (UnknownHostException e) {
            return String.format("Ping request could not find host %s. Please check the name and try again.", host);
        }
        res = ppp.reportPing(ppp.ping(count));
        res = res.replaceAll("\n", "<br>");
        return  res;
    }

    @PostMapping(value="/taskadd")
    public void getTaskAdd(@ModelAttribute TaskPingerDto dto) { 
            system.out.println();
    }

}
