package com.boo.commanding.tasks;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

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
        @JsonSubTypes.Type(value = PasswordResetTask.class, name = "PasswordResetTask"),
        @JsonSubTypes.Type(value = FactoryResetTask.class, name = "FactoryResetTask")
})
public abstract class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    private String gatewayId;
    private State state;


    public Task() {
        this.state = State.OPEN;
    }

    public enum State {
        OPEN, DONE
    }

}
