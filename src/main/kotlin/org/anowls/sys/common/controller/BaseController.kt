package org.anowls.sys.common.controller

import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.NoHandlerFoundException
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(value = "org.anowls.sys.web")
open class BaseController {

    private fun getRequest() : HttpServletRequest{
        return (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
    }

    protected fun getUserIp() : String {
        val value : String? = this.getRequest().getHeader("X-Real-IP")
        return if (StringUtils.isBlank(value) || "unknown".equals(value, true))  this.getRequest().remoteAddr else value.toString()
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFoundException(e: NoHandlerFoundException) = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(e: MissingServletRequestParameterException) = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
}
