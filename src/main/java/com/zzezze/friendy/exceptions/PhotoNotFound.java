package com.zzezze.friendy.exceptions;

public class PhotoNotFound extends RuntimeException{
    public PhotoNotFound() {
        super("사진을 찾지 못했습니다");
    }
}
