package com.example.demo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @ManyToMany(fetch = FetchType.LAZY,targetEntity = PermissionModel.class,cascade = CascadeType.PERSIST )
    // public List<RoleModel> roles;
    // public Set<PermissionModel> permissions = new HashSet<>();
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
}

    