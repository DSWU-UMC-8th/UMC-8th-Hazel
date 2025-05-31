package umc.study.apiPayload.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.exception.GeneralException;

public class PageHandler extends GeneralException {
    public PageHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
