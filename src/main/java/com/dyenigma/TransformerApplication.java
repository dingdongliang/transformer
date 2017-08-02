package com.dyenigma;

import com.dyenigma.druid.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DynamicDataSourceRegister.class)
@MapperScan("com.dyenigma.dao")
@ServletComponentScan
public class TransformerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransformerApplication.class, args);
    }
}
