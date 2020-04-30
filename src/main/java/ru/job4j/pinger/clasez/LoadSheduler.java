package ru.job4j.pinger.clasez;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import ru.job4j.pinger.models.CurrentTask;
import ru.job4j.pinger.repositories.TasksRepository;
import ru.job4j.pinger.tasks.PingTask;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Component
public class LoadSheduler {

    @Autowired
    private TasksRepository ttt;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @PostConstruct
    public void init() {
        List<CurrentTask> res = (List) ttt.findAll();
        for (var aaa: res) {
            taskScheduler.scheduleAtFixedRate(new PingTask(), new Date(aaa.getBegin().getTime()), aaa.getPeriod());
        }
    }
}
