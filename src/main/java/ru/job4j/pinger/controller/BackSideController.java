package ru.job4j.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.pinger.clasez.PingImplIcmpPing;
import ru.job4j.pinger.clasez.Result;
import ru.job4j.pinger.clasez.SampleResult;
import ru.job4j.pinger.models.CurrentTask;
import ru.job4j.pinger.models.Task;
import ru.job4j.pinger.dto.TaskDto;
import ru.job4j.pinger.dto.UserDto;
import ru.job4j.pinger.repositories.TasksRepository;
import ru.job4j.pinger.repositories.ArchiveRepository;
import ru.job4j.pinger.repositories.UsersRepository;
import ru.job4j.pinger.tasks.PingTask;

import java.net.UnknownHostException;
import java.security.Principal;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Controller
public class BackSideController {

    @Autowired
    private PingImplIcmpPing ppp;

    @Autowired
    private ArchiveRepository ttt;

    @Autowired
    private TasksRepository qqq;

    @Autowired
    private UsersRepository uuu;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

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
        int period;
        u.setName(princ.getName());
        CurrentTask ct = new CurrentTask();
        Result result = new SampleResult();
        Task task = dto.convertToTask(u);
        ttt.save(task);
        ct.setUser(u.convert());
        ct.setTask(task);
        ct.setBegin(task.getDate1());
        switch (task.getSellist2()) {
            case ("mins"):
                period = task.getText3() * 60 * 1000;
                break;
            case ("hrs"):
                period = task.getText3() * 60 * 60 * 1000;
                break;
            case ("days"):
                period = task.getText3() * 24 * 60 * 60 * 1000;
                break;
            case ("wks"):
                period = task.getText3() * 7 * 24 * 60 * 60 * 1000;
                break;
            case ("mns"):
                period = task.getText3() * 30* 7 * 24 * 60 * 60 * 1000;
                break;
            case ("yrs"):
                period = task.getText3() * 365* 24 * 60 * 60 * 1000;
                break;
            default:
                period = 0;
        }
        ct.setPeriod((long) period);
        qqq.save(ct);
        taskScheduler.scheduleAtFixedRate(new PingTask(), new Date(ct.getBegin().getTime()), ct.getPeriod());
        return "ok";
    }

    @GetMapping (value = "/list")
    public String getList(Model model, Principal princ) {
        if (princ != null) {
            model.addAttribute("lst", ttt.findAllByUser(uuu.findByName(princ.getName())));
        }
        return "table";
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
