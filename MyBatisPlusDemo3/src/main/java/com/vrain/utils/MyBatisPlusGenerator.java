package com.vrain.utils;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author vrain
 * @create 2019-04-02 9:52
 */
public class MyBatisPlusGenerator {
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //配置全局策略
        mpg.setGlobalConfig(new GlobalConfig()
                .setAuthor("vrain")//设置作者名
                .setActiveRecord(true)//开启AR模式
                .setFileOverride(false)//文件覆盖
                .setOutputDir("D:\\test\\07-idea\\MyBatisPlusDemo3\\src\\main\\java")//生成路径
                .setBaseColumnList(true)
                .setEnableCache(false)
                .setBaseResultMap(true)
                .setOpen(false)
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
        );
        //配置策略
        mpg.setStrategy(new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("tb_")
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setInclude(new String[]{"tb_roles"})
        );
        //设置数据源
        mpg.setDataSource(new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/ssm?serverTimezone=GMT%2B8")
                .setUsername("root")
                .setPassword("zhu123456")
        );
        //设置包名策略
        mpg.setPackageInfo(new PackageConfig()
                .setParent("com.vrain")
                .setMapper("mapper")
                .setEntity("entity")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller")
        );
        //执行
        mpg.execute();
    }
}
