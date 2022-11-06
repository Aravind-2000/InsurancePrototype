package com.example.insuranceprototype.Auth.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.example.insuranceprototype.Auth.models.User;
import com.example.insuranceprototype.Entity.AgentAppointmentDetails;
import com.example.insuranceprototype.Entity.Permissions;
import com.example.insuranceprototype.Entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private AgentAppointmentDetails agent;

	private Role role;

	private List<Permissions> specialAccess;

	private String profilePicture;

	public UserDetailsImpl(Long id, String username, String email, String password, AgentAppointmentDetails agent, Role role, List<Permissions> specialAccess, String profilePicture) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.agent = agent;
		this.role = role;
		this.specialAccess = specialAccess;
		this.profilePicture = profilePicture;
	}

	public static UserDetailsImpl build(User user) {

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(),
				user.getAgent(),
				user.getRole(),
				user.getSpecialAccess(),
				user.getProfilePicture()
				);
	}


	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public AgentAppointmentDetails getAgent() {
		return agent;
	}

	public void setAgent(AgentAppointmentDetails agent) {
		this.agent = agent;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public List<Permissions> getSpecialAccess() {
		return specialAccess;
	}

	public void setSpecialAccess(List<Permissions> specialAccess) {
		this.specialAccess = specialAccess;
	}


	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
