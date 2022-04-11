package com.example.insuranceprototype.Auth.payload.response;

import com.example.insuranceprototype.Entity.AgentAppointmentDetails;
import com.example.insuranceprototype.Entity.Permissions;
import com.example.insuranceprototype.Entity.Role;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private Long id;
	private String username;
	private String email;
	private AgentAppointmentDetails agent;
	private Role role;
	private List<Permissions> specialAccess;

	public AgentAppointmentDetails getAgent() {
		return agent;
	}

	public void setAgent(AgentAppointmentDetails agent) {
		this.agent = agent;
	}

	public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, AgentAppointmentDetails agent, Role role, List<Permissions> specialAccess) {
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.agent = agent;
		this.role = role;
		this.specialAccess = specialAccess;

	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
