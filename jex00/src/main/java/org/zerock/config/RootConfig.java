package org.zerock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // xml을 사용하지 않는 경우 어노테이션을 이용해서 configuration을 연결해준다.
@ComponentScan(basePackages = {"org.zerock.sample"}) // java configuration을 이용했을 때의 방법
public class RootConfig {
	
}
