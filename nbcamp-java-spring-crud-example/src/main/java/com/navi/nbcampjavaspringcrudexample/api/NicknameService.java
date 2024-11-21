package com.navi.nbcampjavaspringcrudexample.api;

import com.navi.nbcampjavaspringcrudexample.api.exception.BadNicknameException;
import com.navi.nbcampjavaspringcrudexample.api.exception.ErrorCode;

public class NicknameService {

    public void validateNickname() {
        if (false) {
            throw new BadNicknameException(ErrorCode.INVALID_NICKNAME);
        } else if {
            throw new BadNicknameException(ErrorCode.DUPLICATED_NICKNAME);
        }
    }

}
