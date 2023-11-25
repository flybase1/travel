package com.backend.travel.common.security;

import com.backend.travel.POJO.entity.Account;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.service.impl.AccountServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义userDetails
 */
@Service
public class MyUserDetailService implements UserDetailsService {
    @Resource
    private AccountServiceImpl accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getByUserName(username);
        if (account == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"用户名不存在");
        } else if (account.getAccountStatus() == 1) {
            throw new BusinessException(ErrorCode.ACCOUNT_BLOCK);
        }
        return new User(account.getUserAccount(), account.getUserPassword(), getUserAuthority(account.getAccountId()));
    }

    public List<GrantedAuthority> getUserAuthority(Long accountId) {
        // 格式 ROLE_admin,ROLE_user,system:user:delete,system:user:add
        String authority = accountService.getUserAuthorityInfo(accountId);
        return AuthorityUtils.createAuthorityList(authority);
    }
}
