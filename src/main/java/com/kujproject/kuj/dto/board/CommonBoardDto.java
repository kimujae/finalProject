package com.kujproject.kuj.dto.board;

public class CommonBoardDto <T>{
    private T t;

    public CommonBoardDto(T t){
        this.t = t;
    }

    public T getT() {
        return t;
    }
}
