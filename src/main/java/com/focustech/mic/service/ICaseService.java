package com.focustech.mic.service;

import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.pojo.MicCase; /**
 * User: songboxue
 * Date: 2018/1/18
 * Description:
 */
public interface ICaseService {
    ServerResponse addCase(MicCase micCase);
}
