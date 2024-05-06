package com.example.demo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "RoleModel")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 240)
    private String name;
    
    @NotBlank
    @Size(max = 240)
    private String code;

    @Column(nullable = false)
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY,targetEntity = PermissionModel.class,cascade = CascadeType.MERGE )
    private List<PermissionModel> permissions;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<PermissionModel> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionModel> permissions) {
		this.permissions = permissions;
	}
    public boolean hasOverHundredPoints() {
        return this.id > 10;
    }

    public Object get(String string) {
        return null;
    }
}

    