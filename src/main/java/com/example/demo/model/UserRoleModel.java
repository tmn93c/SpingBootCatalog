package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name = "UserRoleModel")
public class UserRoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    // @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(nullable = false)
    private boolean active;
    
    @ManyToOne
    @JoinColumn(name ="FK_UserId")
    private UserModel userModel;
    
    @ManyToOne
    @JoinColumn(name ="FK_RoleId")
    private RoleModel roleModel;

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

    