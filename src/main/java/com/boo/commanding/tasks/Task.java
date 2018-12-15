package com.boo.commanding.tasks;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RebootTask.class, name = "RebootTask"),
        @JsonSubTypes.Type(value = PasswordResetTask.class, name = "PasswordResetTask")
})
public abstract class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    private String gatewayId;
    private State state;
    @Getter
    private TaskType taskType;

    public Task(TaskType taskType) {
        this.taskType = taskType;
    }


    public enum State {
        OPEN, DONE
    }

    public enum TaskType {
        PWD_RESET, FACTORY_RESET, REBOOT
    }
}
