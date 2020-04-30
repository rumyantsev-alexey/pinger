package ru.job4j.pinger.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import ru.job4j.pinger.clasez.Result;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Table(name = "tasks")
public class CurrentTask {


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
    @Column(name = "begin")
    private Timestamp begin;

    @Getter
    @Setter
    @Column(name = "period")
    private Long period;

    @Getter
    @Setter
    @NonNull
    @OneToOne(cascade = {CascadeType.REFRESH})
    private Task task;

    @Getter
    @Setter
    @NonNull
    private Boolean review = true;
}
