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

    private static final String STRING_JAVAX = "javax";
    private static final String STRING_DOT_SERVLET = ".servlet";

    @RequestMapping("error404")
    public ModelAndView redirectToErrorPage() {
        return new ModelAndView("error/error404");
    }

    @RequestMapping("error")
    public ModelAndView customError(final HttpServletRequest request) {
        final Integer statusCode = (Integer) request.getAttribute(STRING_JAVAX
          + STRING_DOT_SERVLET
          + ".error.status_code");
        final Throwable throwable = (Throwable) request.getAttribute(
                STRING_JAVAX + STRING_DOT_SERVLET
          + ".error.exception");
        final String exceptionMessage = getExceptionMessage(throwable,
          statusCode);

        String requestUri = (String) request.getAttribute("javax.servlet"
          + ""
          +
          ".error.request_uri");
        if (requestUri
          == null) {
            requestUri = "Unknown";
        }

        final String message = formatErrorMessage(statusCode, requestUri,
          exceptionMessage);
        final ModelAndView mnv = new ModelAndView("error/error");
        mnv.addObject("errorMessage", message);
        return mnv;
    }

    private String getExceptionMessage(
      final Throwable throwable, final Integer statusCode) {
        if (throwable
          != null) {
            return Throwables.getRootCause(throwable).getMessage();
        }
        final HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        return httpStatus.getReasonPhrase();
    }

    private String formatErrorMessage(
      final Integer statusCode, final String requestUri, final String message) {
        final StringBuilder sb = new StringBuilder();
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
