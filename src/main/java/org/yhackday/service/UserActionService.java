package org.yhackday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yhackday.dao.mapper.AccountMapper;
import org.yhackday.domain.dto.UserActionRequestDto;

@Service
public class UserActionService {
    private AccountMapper accountMapper;

    @Autowired
    public UserActionService(AccountMapper accountMapper){
        this.accountMapper = accountMapper;
    }

    /**
     * Nターン目のアカウントの状態を保存する
     */
    public void addAccountAction(int room_id, UserActionRequestDto userActionRequestDto){
        accountMapper.addAccountAction(room_id, userActionRequestDto);
    }
}
