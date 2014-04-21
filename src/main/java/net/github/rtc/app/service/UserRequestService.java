package net.github.rtc.app.service;

import net.github.rtc.app.model.UserRequest;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
public interface UserRequestService {
    UserRequest getUserRequest(long id);
    List<UserRequest> getAll();
    void update(UserRequest request);
    void delete(UserRequest request);
}
