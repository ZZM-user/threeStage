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
    
    private String className = "People";
    // 绝对路径
    final private static String ABS_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\" + packageName.replace(".", "\\");
    
    // 置换的规则
    private HashMap<String, String> BASE_FORMAT;
    
    private void replaceRules() {
        BASE_FORMAT = new HashMap<String, String>() {
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
    private String readConfig(String configName) throws IOException {
        String filePath = CONFIG_PATH + '\\' + configName + ".txt";
        File configFile = new File(filePath);
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
    private String formatOfClass(String config) {
        Set<String> set = BASE_FORMAT.keySet();
        for (String key : set) {
            config = config.replace(key, BASE_FORMAT.get(key));
        }
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
        String space = ("ServiceImpl".equals(type)) ? "service\\impl" : type.substring(0, 1).toLowerCase() + type.substring(1);
        String newFileName = className + type + ".java";
        String newFilePath = ABS_PATH + "\\" + space + "\\" + newFileName;
        System.out.println("newFilePath = " + newFilePath);
        File file = new File(newFilePath);
        try {
            boolean created = file.createNewFile();
            System.out.println(created);
            if (created || file.exists()) {
                FileWriter writer = new FileWriter(file);
                writer.write(context);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public void generate() throws IOException {
        File configPath = new File(CONFIG_PATH);
        String entityPath = (ABS_PATH + "\\entity").replace(".", "\\");
        System.out.println("entityPath = " + entityPath);
        File entityFiles = new File(entityPath);
        String[] configList = configPath.list();
        String[] entityList = entityFiles.list();
        assert configList != null;
        for (String item : configList) {
            assert entityList != null;
            for (String entity : entityList) {
                System.out.println(item + "-----" + entity);
                this.className = entity.substring(0, entity.lastIndexOf('.'));
                replaceRules();
                String configType = item.substring(0, item.lastIndexOf('.'));
                String config = readConfig(configType);
                String configResult = formatOfClass(config);
                buildFile(configResult, configType);
            }
        }
    }
}
