package com.example.common;

import com.example.common.enums.AckCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author： 17602
 * @Date： 2022/8/15 14:23
 * @Desc：
 **/
@Getter
@Setter
public class WaimaiException extends RuntimeException {
    
    private AckCode ackCode;
    
    /**
     * 自定义异常构造函数
     *
     * @param ackCode
     */
    public WaimaiException(AckCode ackCode) {
        this(ackCode.getMsg());
        this.ackCode = ackCode;
    }
    
    
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public WaimaiException() {
    }
    
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public WaimaiException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     *
     * @since 1.4
     */
    public WaimaiException(String message, Throwable cause) {
        super(message, cause);
    }
}
