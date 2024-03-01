package com.reading;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @SpringBootTest
 * 통합 테스트를 제공하는 기본적인 스프링 부트 테스트 어노테이션이다.
 * 애플리케이션이 실행될 때의 설정을 임의로 바꾸어 테스트를 진행할 수 있으며
 * 여러 단위 테스트를 하나의 통합된 테스트로 수행할 때 적합하다.
 * Spring Boot 프로젝트를 만들면 메인 클레스와 함께 기본으로 제공된다.
 *
 * @SpringBootTest는 만능이라 할 수 있는데,
 * 실제 구동되는 애플리케이션과 똑같이 ApplicationContext를 로드하여
 * 테스트하기 때문에 하고 싶은 테스트를 모두 수행할 수 있다.
 *
 * 단, 애플리케이션에 설정된 빈을 모두 로드하기 때문에 애플리케이션의 규모가 클수록 느려진다.
 */
@SpringBootTest
@Log4j2
public class DataSourceTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {

        /**
         * @Cleanup
         * 자동으로 자원을 관리한다.
         * close() 메소드를 호출하여 자원을 종료시킨다.
         */
        @Cleanup
        Connection con = dataSource.getConnection();

        log.info(con);

        Assertions.assertNotNull(con);
    }
}
