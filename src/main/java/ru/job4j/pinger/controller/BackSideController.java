package ru.job4j.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.pinger.PingImplIcmpPing;
import ru.job4j.pinger.models.Task;
import ru.job4j.pinger.models.TaskDto;
import ru.job4j.pinger.models.UserDto;
import ru.job4j.pinger.repositories.TaskRepository;

import java.net.UnknownHostException;
import java.security.Principal;
import java.util.concurrent.ExecutionException;

@Controller
public class BackSideController {

    @Autowired
    private PingImplIcmpPing ppp;

    @Autowired
    private TaskRepository ttt;

    @Autowired
    private UserDto u;

    @GetMapping (value = "/")
    public String getMain() {
        return "login";
    }

    @GetMapping (value = "/p")
    public String getMain2() {
        return "ping";
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
//        pr(host, count, model);
        return  res;
    }

    @PostMapping(value = "/taskadd")
    public @ResponseBody String getTaskAdd(@ModelAttribute TaskDto dto, Principal princ) {
        u.setName(princ.getName());
        Task task = dto.convertToTask(dto, u);
        ttt.save(task);
        return "ok";

    }

/*    @Async
    public AsyncResult<String> pr(String host, int count, Model model) {
        String res;
        res = ppp.reportPing(ppp.ping(count));
        res = res.replaceAll("\n", "<br>");
        model.addAttribute("rrr", res);
        return new AsyncResult<>(res);
    }
*/

}
