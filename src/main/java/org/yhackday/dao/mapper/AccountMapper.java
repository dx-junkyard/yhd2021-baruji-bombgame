package org.yhackday.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.yhackday.domain.dto.NextActionDto;
import org.yhackday.domain.dto.UserActionRequestDto;

@Mapper
public interface AccountMapper {
    void addAccountAction(int user_id, UserActionRequestDto userActionRequestDto);
    NextActionDto getNextAction(int user_id);
}
