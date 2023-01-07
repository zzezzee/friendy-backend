package com.zzezze.friendy.exceptions;

public class PhotoDeleteFailed extends RuntimeException {
    public PhotoDeleteFailed() {
        super("사진을 삭제할 수 없습니다");
    }
}
