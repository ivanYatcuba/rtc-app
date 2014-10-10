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

    private static final String ERROR_PAGE = "error/error";
    private static final String ERROR_MSG = "errorMessage";
    private static final String ERROR_ADV_MSG = "errorAdvancedMessage";

    @RequestMapping("error500")
    public ModelAndView redirectToErrorPage500(final HttpServletRequest request) {
        final ModelAndView mnv = new ModelAndView(ERROR_PAGE);
        mnv.addObject(ERROR_MSG, "500 Internal Server Error");
        mnv.addObject(ERROR_ADV_MSG, getFullMessage(request));
        return mnv;
    }

    @RequestMapping("error404")
    public ModelAndView redirectToErrorPage404(final HttpServletRequest request) {
        final ModelAndView mnv = new ModelAndView(ERROR_PAGE);
        mnv.addObject(ERROR_MSG, "404 Not Found Error");
        mnv.addObject(ERROR_ADV_MSG, getFullMessage(request));
        return mnv;
    }

    @RequestMapping("error400")
    public ModelAndView redirectToErrorPage400(final HttpServletRequest request) {
        final ModelAndView mnv = new ModelAndView(ERROR_PAGE);
        mnv.addObject(ERROR_MSG, "400 Bad Request Error");
        mnv.addObject(ERROR_ADV_MSG, getFullMessage(request));
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
