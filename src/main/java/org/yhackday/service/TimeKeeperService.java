package org.yhackday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yhackday.dao.mapper.AccountMapper;
import org.yhackday.domain.dto.NextActionDto;

@Service
public class TimeKeeperService {
    private AccountMapper accountMapper;

    @Autowired
    public TimeKeeperService(AccountMapper accountMapper){
        this.accountMapper = accountMapper;
    }

    public NextActionDto getNextActionInfo(int user_id){
        return accountMapper.getNextAction(user_id);
    }
}
