package ru.job4j.pinger.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
//@Sql(scripts = {"/addTestUser.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UtilityControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void testPing() throws Exception {
                mockMvc.perform(get("/ping")
                        .param("host", "ya.ru")
                        ).andExpect(status().isOk());
        }

        @Test
        public void testTaskAdd() throws Exception {
                this.mockMvc.perform(post("/taskadd"))
                        .andExpect(status().isOk());
        }
}