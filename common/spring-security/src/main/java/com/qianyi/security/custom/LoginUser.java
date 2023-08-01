package com.qianyi.security.custom;

import com.qianyi.model.system.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
public class LoginUser extends User{
	private SysUser sysUser;
	public LoginUser(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
		super(sysUser.getUsername(), sysUser.getPassword(), authorities);
		this.sysUser = sysUser;
	}
	public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

}
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class LoginUser implements UserDetails {
//	    /**
//     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象
//     */
//    private SysUser sysUser;
//
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return null;
//	}
//
//	@Override
//	public String getPassword() {
//		return sysUser.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return sysUser.getUsername();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//}
