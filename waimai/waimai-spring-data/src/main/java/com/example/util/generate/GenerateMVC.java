package com.example.util.generate;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

/**
 * @Author： 17602
 * @Date： 2022/8/1 19:31
 * @Desc： 生成mvc三层架构
 **/
public class GenerateMVC {
    // 配置文件路径
    final static String CONFIG_PATH = "src/main/java/com/example/util/generate/config";
    
    // 包名
    private static final String packageName = "com.example";
    // 类名
    private String className = "Entity";
    // 当前包的绝对路径
    final private static String ABS_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\" + packageName.replace(".", "\\");
    
    // 置换配置文件的规则
    private HashMap<String, String> baseFormatRules;
    
    /**
     * 根据当前类名/包名，重置配置文件规则
     */
    private void replaceRules() {
        baseFormatRules = new HashMap<String, String>() {
            {
                put("{packageName}", packageName);
                put("{a}", className);
            }
        };
    }
    
    
    /**
     * 初始化包
     */
    private void initPackage() {
        // File entity = new File(absPath + "\\entity");
        File service = new File(ABS_PATH + "\\service");
        File serviceImpl = new File(ABS_PATH + "\\service\\impl");
        File mapper = new File(ABS_PATH + "\\mapper");
        // if (!entity.exists()) {
        //     entity.mkdirs();
        // }
        if (!mapper.exists()) {
            mapper.mkdirs();
        }
        if (!service.exists()) {
            service.mkdirs();
        }
        if (!serviceImpl.exists()) {
            serviceImpl.mkdirs();
        }
    }
    
    /**
     * 读取配置文件
     *
     * @param configName
     *
     * @return
     *
     * @throws IOException
     */
    private String readConfig(File configFile) throws IOException {
        FileReader fileReader;
        
        fileReader = new FileReader(configFile);
        BufferedReader reader = new BufferedReader(fileReader);
        
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        
        return builder.toString();
    }
    
    /**
     * 格式化生成
     *
     * @param config
     *
     * @return
     */
    private String formatConfigOfClass(String config) {
        Set<String> set = baseFormatRules.keySet();
        for (String key : set) {
            config = config.replace(key, baseFormatRules.get(key));
        }
        // 这里的主要是处理排版
        config = config.replace(";", ";\n")
                         .replace("{}", "{\n}")
                         .replace("import", "\nimport")
                         .replace("public", "\npublic");
        return config;
    }
    
    /**
     * 构建文件
     *
     * @param context
     * @param type
     */
    private void buildFile(String context, String type) {
        // 是否为serviceImpl，是则替换为二级包名,否则直接转换取就可
        String space = ("ServiceImpl".equals(type)) ? "service\\impl" :
                               type.substring(0, 1).toLowerCase() + type.substring(1);
        
        String newFileName = className + type + ".java";
        String newFilePath = ABS_PATH + "\\" + space + "\\" + newFileName;
        
        File file = new File(newFilePath);
        try {
            // 文件不存在就新建
            if (!file.exists()) {
                file.createNewFile();
            }
            // 如果文件存在，就写入内容
            if (file.exists()) {
                FileWriter writer = new FileWriter(file);
                writer.write(context);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    /**
     * 生成三层
     *
     * @throws IOException
     */
    public void generate() throws IOException {
        initPackage();
        // 构建配置文件路径
        File configPath = new File(CONFIG_PATH);
        // 构建实体类绝对路径
        String entityPath = (ABS_PATH + "\\entity").replace(".", "\\");
        
        File entityFiles = new File(entityPath);
        File[] configList = configPath.listFiles();
        String[] entityList = entityFiles.list();
        assert configList != null;
        for (File configFile : configList) {
            assert entityList != null;
            for (String entity : entityList) {
                System.out.println(configFile.getName() + "-----" + entity);
                // 根据配置文件名抽取当前配置层(mapper?service？serviceImpl?)
                String configType = configFile.getName().substring(0, configFile.getName().lastIndexOf("."));
                // 重置当前类名
                this.className = entity.substring(0, entity.lastIndexOf('.'));
                // 重新定义替换规则中的类名
                replaceRules();
                // 读取配置文件内容
                String config = readConfig(configFile);
                // 执行替换规则
                String configResult = formatConfigOfClass(config);
                // 构建并写出文件
                buildFile(configResult, configType);
            }
        }
    }
}
