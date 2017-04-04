package com.weblab.repository;

import com.weblab.model.UserRequestsInfo;

/**
 * Created by amowel on 02.04.17.
 */

public interface UserRequestsInfoRepository {
    public void saveUserRequestsInfo(UserRequestsInfo userRequestsInfo);
    public UserRequestsInfo findUserRequestsInfo(int vkId);
    public void updateUserRequestsInfo(UserRequestsInfo userRequestsInfo);
}