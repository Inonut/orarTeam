package team.orar.support.mvc.exception;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import team.orar.exception.BusinessException;
import team.orar.exception.SystemException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(GlobalDefaultExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBusinessException(BusinessException ex) {
        LOGGER.debug(ex);

        if (ex.getBusinessMessage() != null) {
            return messageSource.getMessage(ex.getBusinessMessage().getMessage(), ex.getBusinessMessage().getParams(), null);
        }

        return messageSource.getMessage("exception.business.generic.message", null, null) + " " + ex.getMessage();
    }

    @ExceptionHandler(SystemException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleSystemException(SystemException ex) {
        LOGGER.debug(ex);

        if (ex.getBusinessMessage() != null) {
            return messageSource.getMessage(ex.getBusinessMessage().getMessage(), ex.getBusinessMessage().getParams(), null);
        }

        return messageSource.getMessage("exception.system.generic.message", null, null) + " " + ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        LOGGER.debug(e);
        return messageSource.getMessage("exception.system.message", null, null) + " " + e.getMessage();
    }
}