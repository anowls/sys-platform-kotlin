package org.anowls.sys.common.message

import org.anowls.sys.common.enumeration.MessageLevel
import org.springframework.http.HttpStatus

data class SimpleMessage(val code : Int,  val level : String, val content : Any?, val message : String) {

    constructor(code : HttpStatus, level : MessageLevel, content : Any?) : this(code.value(), level.getKey(), content, "")

    constructor(code : HttpStatus, level : MessageLevel, message : String) : this(code.value(), level.getKey(),null, message)

    constructor(code : HttpStatus, level : MessageLevel, content : Any?, message : String) : this(code.value(), level.getKey(), content, message)

    companion object {

        fun info(message : String) : SimpleMessage {
            return SimpleMessage(HttpStatus.OK, MessageLevel.INFO, message)
        }

        fun info(content : Any?) : SimpleMessage {
            return SimpleMessage(HttpStatus.OK, MessageLevel.INFO, content)
        }

        fun info(message : String , content : Any?) : SimpleMessage {
            return SimpleMessage(HttpStatus.OK, MessageLevel.INFO, content, message)
        }

        fun warning(message : String) : SimpleMessage {
            return SimpleMessage(HttpStatus.FORBIDDEN, MessageLevel.WARN, message)
        }

        fun warning(content : Any?) : SimpleMessage {
            return SimpleMessage(HttpStatus.FORBIDDEN, MessageLevel.WARN, content)
        }

        fun warning(message : String , content : Any?) : SimpleMessage {
            return SimpleMessage(HttpStatus.FORBIDDEN, MessageLevel.WARN, content, message)
        }

        fun failure(message : String) : SimpleMessage {
            return SimpleMessage(HttpStatus.INTERNAL_SERVER_ERROR, MessageLevel.FAIL, message)
        }

        fun failure(content : Any?) : SimpleMessage {
            return SimpleMessage(HttpStatus.INTERNAL_SERVER_ERROR, MessageLevel.FAIL, content)
        }

        fun failure(message : String , content : Any?) : SimpleMessage {
            return SimpleMessage(HttpStatus.INTERNAL_SERVER_ERROR, MessageLevel.FAIL, content, message)
        }

    }

}
