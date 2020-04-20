package ru.job4j.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.pinger.PingImplIcmpPing;
import ru.job4j.pinger.models.TaskPingerDto;

import java.net.UnknownHostException;
import java.security.Principal;
import java.util.concurrent.ExecutionException;

@Controller
public class BackSideController {

    AsyncResult<String> rrr;

    @Autowired
    private PingImplIcmpPing ppp;

    @PostMapping (value = "/")
    public String getMain2() {
        return "login";
    }

    @GetMapping (value = "/")
    public String getMain() {
        return "login";
    }

    @GetMapping(value = "/p")
    public String getP() {
        return  "ping";
    }

    @GetMapping(value = "/ping")
    public @ResponseBody String getPing(@RequestParam(value = "host") String host, @RequestParam(value = "count", required = false, defaultValue = "4") int count, Model model) throws ExecutionException {
        String res;
        try {
            ppp.setIp(host);
        } catch (UnknownHostException e) {
            return String.format("Ping request could not find host %s. Please check the name and try again.", host);
        }
        res = "wait ping processing....";
        pr(host, count, model);
        return  res;
    }

    @PostMapping(value = "/taskadd")
    public  @ResponseBody Principal getTaskAdd(@ModelAttribute TaskPingerDto dto, Model model, Principal princ) {
        model.addAttribute("prince", princ);
        //            system.out.println(dto);
        return princ;
    }

    @Async
    public AsyncResult<String> pr(String host, int count, Model model) {
        String res;
        res = ppp.reportPing(ppp.ping(count));
        res = res.replaceAll("\n", "<br>");
        model.addAttribute("rrr", res);
        return new AsyncResult<>(res);
    }


}
