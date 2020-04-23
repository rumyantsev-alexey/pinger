package ru.job4j.pinger.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

       @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Getter
        private int id;

        @Getter
        @Setter
        @NonNull
        @Column(name = "name")
        private String name;

        @Getter
        @Setter
        @NonNull
        @Column(name = "password")
        private String password;

        @Getter
        @Setter
        @Email
        @Column(name = "email")
        private String email;
}
