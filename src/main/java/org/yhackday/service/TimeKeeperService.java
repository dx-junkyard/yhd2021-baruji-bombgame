package org.yhackday.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yhackday.dao.mapper.AccountMapper;
import org.yhackday.domain.dto.NextActionDto;
import org.yhackday.domain.dto.UserActionDto;

@Service
public class TimeKeeperService {
    private AccountMapper accountMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TimeKeeperService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public NextActionDto getNextActionInfo(int user_id) {
        UserActionDto userActionDto = accountMapper.getUserStatusInfo(user_id);
        return modelMapper.map(userActionDto, NextActionDto.class);
    }
}
