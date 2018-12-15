package com.boo.commanding.tasks;

import lombok.Builder;

import javax.persistence.Entity;

import static com.boo.commanding.tasks.Task.TaskType.PWD_RESET;

@Builder
@Entity
public class PasswordResetTask extends Task {

    public PasswordResetTask() {
        super(PWD_RESET);
    }

}
