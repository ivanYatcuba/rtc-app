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
    private static final String ERROR_CAS = "errorCause";

    @RequestMapping("error500")
    public ModelAndView redirectToErrorPage500(final HttpServletRequest request) {
        final ModelAndView mnv = new ModelAndView(ERROR_PAGE);
        mnv.addObject(ERROR_TTL, "Critical Error");
        mnv.addObject(ERROR_MSG, "Error content");
        mnv.addObject(ERROR_CAS, getFullMessage(request));
        return mnv;
    }

    @RequestMapping("error404")
    public ModelAndView redirectToErrorPage404(final HttpServletRequest request) {
        final ModelAndView mnv = new ModelAndView(ERROR_PAGE);
        mnv.addObject(ERROR_TTL, "404 Error: Page not found");
        mnv.addObject(ERROR_MSG, "Please use menu from the left side bar or contact to administrator.");
        mnv.addObject(ERROR_CAS, getFullMessage(request));
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
        final StringBuilder sb = new StringBuilder();
        sb.append("<!--");
        sb.append("\nERROR DETAILS");
        sb.append("\nError code:    ").append(statusCode);
        sb.append("\nReturned for:  ").append(requestUri);
        sb.append("\nError message: ").append(message);
        sb.append("\n-->");
        return sb.toString();
    }
}
