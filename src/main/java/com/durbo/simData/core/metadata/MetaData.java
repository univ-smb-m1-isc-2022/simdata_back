package com.durbo.simData.core.metadata;

import com.durbo.simData.User.User;
import com.durbo.simData.core.simdata.SimData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@Entity
public class MetaData {

    @Column
    private Date creationDate;

    @ManyToOne
    private User creator;

    @Column
    private Boolean valid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public MetaData() {
        this.creationDate = new Date();
        this.valid = true;
    }

    public MetaData(User creator, Boolean valid) {
        this.creationDate = new Date();
        this.creator = creator;
        this.valid = valid;
    }

    public void setCreator(User creator)
    {
        this.creator = creator;
    }
}
