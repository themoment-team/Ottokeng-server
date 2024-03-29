package com.example.ottokeng.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UNKNOWN_ERROR(500, "알 수 없는 에러입니다."),
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    USER_NOT_FOUND(404, "존재하지 않는 유저입니다."),
    UNABLE_TO_ISSUANCE_REFRESHTOKEN(400, "유효하지 않은 리프레쉬 토큰입니다."),
    ALREADY_BLACKLIST(409, "이미 블랙리스트에 존재합니다."),
    POST_NOT_FOUND(404, "존재하지 않는 게시글입니다."),
    COMMENT_NOT_FOUND(404, "존재하지 않는 댓글입니다."),
    IMAGE_UPLOAD_ERROR(400, "이미지 업로드 에러"),
    WRONG_INPUT_IMAGE(400, "잘못된 이미지 파일입니다.");

    private final int status;
    private final String detail;

}
