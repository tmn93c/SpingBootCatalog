package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "RolePermissionModel")
public class RolePermissionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false)
    private boolean active;
    
    @ManyToOne
    @JoinColumn(name ="FK_PermissionId")
    private PermissionModel permissionModel;
    
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

    