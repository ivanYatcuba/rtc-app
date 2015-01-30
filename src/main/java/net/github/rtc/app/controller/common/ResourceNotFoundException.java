package net.github.rtc.app.controller.common;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Alexey Samoylov on 27.01.2015.
 */
@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public final class ResourceNotFoundException extends RuntimeException {
    //  class definition
}