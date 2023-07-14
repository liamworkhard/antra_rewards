package com.antra.rewards.shiro;

import com.antra.rewards.entity.Customer;
import com.antra.rewards.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // get login name
        String customerName = (String) principalCollection.getPrimaryPrincipal();
        // add roles and permissions
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        List<Map<String, Object>> permissions = loginService.getCustomerPermission(customerName);

        for (Map<String, Object> permissionMap : permissions) {
            simpleAuthorizationInfo.addRole(String.valueOf(permissionMap.get("roleName")));
            simpleAuthorizationInfo.addStringPermission(String.valueOf(permissionMap.get("permissionsName")));
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String customerName = authenticationToken.getPrincipal().toString();
        Customer customer = loginService.findCustomer(customerName);
        if (customer == null) {
            return null;
        } else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(customerName, customer.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
