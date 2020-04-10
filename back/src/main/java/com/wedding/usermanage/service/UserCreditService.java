package com.wedding.usermanage.service;

import com.wedding.model.ReturnMessage;
import com.wedding.usermanage.vo.CreditCommentVO;

public interface UserCreditService {
    public ReturnMessage addCreditComment(CreditCommentVO creditCommentVO);
}
