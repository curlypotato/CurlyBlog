//package com.curly.common.util;
//
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * @author broWsJle
// * @date 2022/11/13 22:09
// */
//public class MybatisPlusUtil {
//
//    /** 作者 */
//    public static final String AUTHOR = "broWsJle";
//
//    /** 类命名 */
//    /**
//     * Entity命名
//     */
//    public static final String FILE_NAME_ENTITY = "%sEntity";
//
//    /**
//     * MAPPER命名
//     */
//    public static final String FILE_NAME_MAPPER = "%sMapper";
//
//    /**
//     * xml命名
//     */
//    public static final String FILE_NAME_XML = "%sMapper";
//
//    /**
//     * Service命名
//     */
//    public static final String FILE_NAME_SERVICE = "%sService";
//
//    /**
//     * ServiceImpl命名
//     */
//    public static final String FILE_NAME_SERVICE_IMPL = "%sServiceImpl";
//
//    /**
//     * Controller命名
//     */
//    public static final String FILE_NAME_CONTROLLER = "%sController";
//
//    /**
//     包命名，可以根据自己的项目情况自定义生成后的存放路径
//     entity默认路径为父目录.entity
//     mapper默认路径为父目录.mapper
//     service默认路径为父目录.service
//     serviceImpl默认路径为父目录.service.impl
//     controller默认路径为父目录.controller
//     */
//    /**
//     * PARENT命名
//     */
//    public static final String PACKAGE_NAME_PARENT = "com.curly";
//
//    /**
//     * Entity命名
//     */
//    public static final String PACKAGE_NAME_ENTITY = "com.curly.common.model";
//
//    /**
//     * MAPPER命名
//     */
//    public static final String PACKAGE_NAME_MAPPER = "com.curly.admin.mapper";
//
//    /**
//     * xml命名
//     */
//    public static final String PACKAGE_NAME_XML = "mapper";
//
//    /**
//     * Service命名
//     */
//    public static final String PACKAGE_NAME_SERVICE = "com.curly.admin.service";
//
//    /**
//     * ServiceImpl命名
//     */
//    public static final String PACKAGE_NAME_SERVICE_IMPL = "com.curly.admin.service.impl";
//
//    /**
//     * Controller命名
//     */
//    public static final String PACKAGE_NAME_CONTROLLER = "com.curly.admin.controller";
//
//    /**
//     * 读取控制台内容
//     */
//    private static String scanner(String tip) {
//
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotBlank(ipt)) {
//                return ipt;
//            }
//        }
//
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//
//    }
//
//    /**
//     * 运行这个main方法进行代码生成
//     */
//    public static void main(String[] args) {
//
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("CurlyBlog.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setFileOverride(true);
//        gc.setAuthor(AUTHOR);
//        gc.setOpen(false);
//        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
//        gc.setEnableCache(false);// XML 二级缓存
//        gc.setSwagger2(true); // 实体属性 Swagger2 注解
//        gc.setBaseResultMap(true);
//        gc.setBaseColumnList(true);
//
//        gc.setEntityName(FILE_NAME_ENTITY);
//        gc.setMapperName(FILE_NAME_MAPPER);
//        gc.setXmlName(FILE_NAME_XML);
//        gc.setServiceName(FILE_NAME_SERVICE);
//        gc.setServiceImplName(FILE_NAME_SERVICE_IMPL);
//        gc.setControllerName(FILE_NAME_CONTROLLER);
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/blog_admin?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("1007");
//        mpg.setDataSource(dsc);
//
//        //包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(null);
//        pc.setParent(PACKAGE_NAME_PARENT);
//        pc.setController(PACKAGE_NAME_CONTROLLER);
//        pc.setService(PACKAGE_NAME_SERVICE);
//        pc.setServiceImpl(PACKAGE_NAME_SERVICE_IMPL);
//        pc.setMapper(PACKAGE_NAME_MAPPER);
//        pc.setEntity(PACKAGE_NAME_ENTITY);
//        pc.setXml(PACKAGE_NAME_XML);
//        mpg.setPackageInfo(pc);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        //strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//        strategy.setControllerMappingHyphenStyle(true);
//        // 设置表前缀
//        strategy.setTablePrefix("IEMR_");
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/"
//                        + "/" + tableInfo.getMapperName() + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//
//        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);
//
//        mpg.execute();
//    }
//}
