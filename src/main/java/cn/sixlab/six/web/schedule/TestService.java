package cn.sixlab.six.web.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class TestService {

    public void link() {
        log.info("link-------------");
    }

    public void page() {
        log.info("page-------------");
    }

}
