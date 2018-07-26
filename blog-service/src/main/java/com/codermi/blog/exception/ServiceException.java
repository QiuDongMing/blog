package com.codermi.blog.exception;

/**
 * @author qiudm
 * @date 2018/7/27 3:02
 * @desc
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer resultCode;

    public ServiceException(Integer resultCode, String message) {
        super(message);

        this.resultCode = resultCode;
    }

    public ServiceException(String message) {
        super(message);
    }


    public Integer getResultCode() {
        return resultCode;
    }


}
