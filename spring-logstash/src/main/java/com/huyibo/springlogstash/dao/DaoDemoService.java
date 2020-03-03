package com.huyibo.springlogstash.dao;

import com.huyibo.springcommon.log.CostTime;
import com.huyibo.springlogstash.vo.TestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by huyibo on 2020/3/3.
 */
@Slf4j
@Service
public class DaoDemoService {

    @CostTime
    public String debuInfoTest(TestVO testVO){
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("================== A SQL debuInfoTest ==================");
        return "aaaa";
    }

}
