package net.github.rtc.app.controller.common;

import com.google.common.base.Throwables;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ivan Yatcuba on 7/24/14.
 */
@Controller
public class ErrorController {

    @RequestMapping("error404")
    public ModelAndView redirectToErrorPage() {
        return new ModelAndView("error/error404");
    }

    @RequestMapping("error")
    public ModelAndView customError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String exceptionMessage = getExceptionMessage(throwable, statusCode);


        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        String message = formatErrorMessage(statusCode, requestUri, exceptionMessage);
        ModelAndView mnv = new ModelAndView("error/error");
        mnv.addObject("errorMessage", message);
        return mnv;
    }

    private String getExceptionMessage(Throwable throwable, Integer statusCode) {
        if (throwable != null) {
            return Throwables.getRootCause(throwable).getMessage();
        }
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        return httpStatus.getReasonPhrase();
    }

    private String formatErrorMessage(Integer statusCode, String requestUri, String message){
        StringBuilder sb = new StringBuilder();
        sb.append("<!--");
        sb.append("\nERROR DETAILS\n");
        sb.append("Error code: ").append(statusCode);
        sb.append("\nReturned for: ").append(requestUri);
        sb.append("\nError message:\n");
        sb.append(message).append("\n");
        sb.append("-->");
        return sb.toString();
    }


}
