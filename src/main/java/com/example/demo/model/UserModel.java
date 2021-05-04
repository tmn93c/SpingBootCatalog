package com.example.demo.model;

import java.util.Collection;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "UserModel")
public class UserModel extends BaseIdEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 240)
    private String name;
    
    @NotBlank
    @Size(max = 240)
    private String username;

    @NotBlank
    @Size(max = 240)
    private String email;

    @NotBlank
    @Size(max = 240)
    private String password;

    private boolean enabled;
	
	@Column(name = "account_locked")
	private boolean accountNonLocked;
	
	@Column(name = "account_expired")
	private boolean accountNonExpired;

	@Column(name = "credentials_expired")
	private boolean credentialsNonExpired;


    // @ManyToMany(fetch = FetchType.LAZY)
    // @JoinTable(name = "user_role_model",
    //         joinColumns = @JoinColumn(name = "fk_user_id"),
    //         inverseJoinColumns = @JoinColumn(name = "fk_role_id"))
    @ManyToMany(fetch = FetchType.LAZY,targetEntity = RoleModel.class,cascade = CascadeType.ALL )
    // public List<RoleModel> roles;
    private Set<RoleModel> roles = new HashSet<>();

    public UserModel() {

    }
    public UserModel(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		// roles.forEach(r -> {
		// 	authorities.add(new SimpleGrantedAuthority(r.getName()));
		// 	r.getPermissions().forEach(p -> {
		// 		authorities.add(new SimpleGrantedAuthority(p.getName()));
		// 	});
		// });

		return authorities;
    }

    @Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountNonExpired;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !accountNonLocked;
	}
    
    public Set<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

}

    