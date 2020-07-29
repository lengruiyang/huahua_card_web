package cn.huiounet.pojo.exception;

public class MyException extends Exception{

    private String message;

    public MyException(String msg){
        super(msg);
    }
    public String getMessage() {
        return message;
    }
}
