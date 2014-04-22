package net.github.rtc.app.resource;

import net.github.rtc.app.model.UserRequest;

import java.util.List;

/**
 * Created by ivan on 18.04.14.
 */
public interface UserRequestResource {
    void insert(UserRequest request);
    UserRequest findByID(long id);
    List<UserRequest> getAll();
    void delete(UserRequest request);
    void update(UserRequest request);
}
