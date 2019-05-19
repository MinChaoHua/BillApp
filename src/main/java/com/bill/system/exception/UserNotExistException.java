package com.bill.system.exception;
/**
 * 使用的时候直接 throw new UserNotExistException();
 * */
public class UserNotExistException extends RuntimeException {
    public UserNotExistException(){
        super("用户不存在");
    }
}
