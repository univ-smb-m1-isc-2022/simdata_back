package com.durbo.simData.core.metadata;

import com.durbo.simData.User.User;
import jakarta.persistence.*;
import lombok.Data;

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
