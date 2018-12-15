package com.boo.commanding.repo;


import com.boo.commanding.tasks.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByGatewayIdAndState(String gatewayId, Task.State state);

    default List<Task> findOpenTasks(String gatewayId) {
        return findByGatewayIdAndState(gatewayId, Task.State.OPEN);
    }


}