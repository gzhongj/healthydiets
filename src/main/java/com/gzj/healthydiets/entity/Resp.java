package com.gzj.healthydiets.entity;

public class Resp<E> {
    private String errcode;
    private String message;
    private E body;

    public Resp() {
    }

    public Resp(String errcode, String message, E body) {
        this.errcode = errcode;
        this.message = message;
        this.body = body;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getBody() {
        return body;
    }

    public void setBody(E body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Resp{" +
                "errcode='" + errcode + '\'' +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
