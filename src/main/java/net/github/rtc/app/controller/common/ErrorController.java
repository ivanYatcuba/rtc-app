package net.github.rtc.app.controller.common;

import com.google.common.base.Throwables;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ivan Yatcuba on 7/24/14.
 */
@Controller
public class ErrorController {

    private static final String STRING_JAVAX = "javax";
    private static final String STRING_DOT_SERVLET = ".servlet";

    private static final String ERROR_PAGE = "portal/admin/page/error";
    private static final String ERROR_TTL = "errorTitle";
    private static final String ERROR_MSG = "errorMessage";

    @RequestMapping("error500")
    public ModelAndView redirectToErrorPage500(final HttpServletRequest request) {
        final ModelAndView mnv = new ModelAndView(ERROR_PAGE);
        mnv.addObject(ERROR_TTL, "Critical Error");
        mnv.addObject(ERROR_MSG, "Error content");
        return mnv;
    }

    @RequestMapping("error404")
    public ModelAndView redirectToErrorPage404(final HttpServletRequest request) {
        final ModelAndView mnv = new ModelAndView(ERROR_PAGE);
        mnv.addObject(ERROR_TTL, "404 Error: Page not found");
        mnv.addObject(ERROR_MSG, "Please use menu from the left side bar or contact to administrator.");
        return mnv;
    }

    private String getFullMessage(final HttpServletRequest request) {
        final Integer statusCode = (Integer) request.getAttribute(
                STRING_JAVAX + STRING_DOT_SERVLET + ".error.status_code");
        final Throwable throwable = (Throwable) request.getAttribute(
                STRING_JAVAX + STRING_DOT_SERVLET + ".error.exception");
        final String exceptionMessage = getExceptionMessage(throwable,
                statusCode);

        String requestUri = (String) request.getAttribute(
                STRING_JAVAX + STRING_DOT_SERVLET + ".error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        return formatErrorMessage(statusCode, requestUri, exceptionMessage);
    }

    private String getExceptionMessage(
            final Throwable throwable, final Integer statusCode) {
        if (throwable != null) {
            return Throwables.getRootCause(throwable).getMessage();
        }
        final HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        return httpStatus.getReasonPhrase();
    }

    private String formatErrorMessage(
            final Integer statusCode, final String requestUri, final String message) {
        String sb = "<!--";
        sb += "\nERROR DETAILS";
        sb += "\nError code:    " + statusCode;
        sb += "\nReturned for:  " + requestUri;
        sb += "\nError message: " + message;
        sb += "\n-->";
        return sb;
    }

}
