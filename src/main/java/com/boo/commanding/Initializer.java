package com.boo.commanding;

import com.boo.commanding.repo.TaskRepository;
import com.boo.commanding.tasks.FactoryResetTask;
import com.boo.commanding.tasks.PasswordResetTask;
import com.boo.commanding.tasks.RebootTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Initializer {

    private final TaskRepository repo;

    @Autowired
    public Initializer(TaskRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void initSomeData() {
        String gatewayID = "4711";
        var rebootTask = RebootTask.builder().build();
        var pwResetTask = PasswordResetTask.builder().build();
        var factoryResetTask = FactoryResetTask.builder().build();
        rebootTask.setGatewayId(gatewayID);
        pwResetTask.setGatewayId(gatewayID);
        factoryResetTask.setGatewayId(gatewayID);
        repo.save(rebootTask);
        repo.save(pwResetTask);
        repo.save(factoryResetTask);
    }
}
