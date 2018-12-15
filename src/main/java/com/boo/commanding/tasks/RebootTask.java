package com.boo.commanding.tasks;

import lombok.Builder;

import javax.persistence.Entity;

import static com.boo.commanding.tasks.Task.TaskType.REBOOT;


@Entity
@Builder
public class RebootTask extends Task {

    public RebootTask() {
        super(REBOOT);
    }

}