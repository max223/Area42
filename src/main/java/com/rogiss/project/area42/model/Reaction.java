package com.rogiss.project.area42.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @NotNull
    private String description;

    @ManyToMany(mappedBy = "compatibleReaction", cascade = CascadeType.ALL)
    private List<Action> compatibleActions = new ArrayList<>();


    public Reaction() {
    }

    public Reaction(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Action> getCompatibleActions() {
        return compatibleActions;
    }

    public void setCompatibleActions(List<Action> compatibleActions) {
        this.compatibleActions = compatibleActions;
    }
}
