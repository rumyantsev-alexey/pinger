package ru.job4j.pinger.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "quartz_tasks")
public class QuartzTask {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private int id;

    @Getter
    @Setter
    @NonNull
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private User user;

    @Getter
    @Setter
    @NonNull
    @Column(name = "trig")
    private Trigger trig;

    @Getter
    @Setter
    @NonNull
    @Column(name = "job")
    private JobDetail job;

    @Getter
    @Setter
    @NonNull
    @OneToOne(cascade = {CascadeType.REFRESH})
    private Task task;
}
