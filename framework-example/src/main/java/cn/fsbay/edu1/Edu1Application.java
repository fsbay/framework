package cn.fsbay.edu1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fsbay.framework.cache.annotation.EnableCacheModule;
import com.fsbay.framework.distributed.annotation.EnableDsModule;
import com.fsbay.framework.id.annotation.EnableIdModule;
import com.fsbay.framework.session.annotation.EnableSessionModule;

@SpringBootApplication(scanBasePackages="cn.fsbay.*")
@EnableCacheModule
@EnableSessionModule
@EnableDsModule
@EnableIdModule
public class Edu1Application {

  public static void main(String[] args) {
    SpringApplication.run(Edu1Application.class, args);
  }
}
