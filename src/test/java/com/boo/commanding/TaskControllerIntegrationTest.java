package com.boo.commanding;

import com.boo.commanding.repo.TaskRepository;
import com.boo.commanding.tasks.FactoryResetTask;
import com.boo.commanding.tasks.PasswordResetTask;
import com.boo.commanding.tasks.RebootTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void createTasks_getAllTasks_success() throws Exception {

        //arrange
        var gatewayId = UUID.randomUUID().toString();
        var pwdResetTask = PasswordResetTask.builder().build();
        var factoryResetTask = FactoryResetTask.builder().build();
        var rebootTask = RebootTask.builder().build();
        pwdResetTask.setGatewayId(gatewayId);
        rebootTask.setGatewayId(gatewayId);
        factoryResetTask.setGatewayId(gatewayId);
        repository.save(pwdResetTask);
        repository.save(rebootTask);
        repository.save(factoryResetTask);


        //act & assert
        var url = String.format("/%s/tasks", gatewayId);
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].type").value("PasswordResetTask"))
                .andExpect(jsonPath("$[1].type").value("RebootTask"));


    }

}
