package com.boo.commanding.tasks;

import lombok.Builder;

import javax.persistence.Entity;

import static com.boo.commanding.tasks.Task.TaskType.FACTORY_RESET;

@Builder
@Entity
public class FactoryResetTask extends Task {

    public FactoryResetTask() {
        super(FACTORY_RESET);
        super.setState(State.OPEN);
    }

}
