package org.jena.wso2.projects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.jena.wso2.projects.constants.Constants;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class TemplateGenerator {

    private static Configuration cfg;

    private static void initialize() {
        if (cfg == null) {
            cfg = new Configuration();
            cfg.setClassForTemplateLoading(TemplateGenerator.class, Constants.TEMPLATES_DIR);
            cfg.setIncompatibleImprovements(new Version(2, 3, 20));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setLocale(java.util.Locale.US);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        }

    }

    public static void generate(Map<String, Object> input, String templateName, String outputFile) throws IOException, TemplateException {
        initialize();
        Template template = cfg.getTemplate(templateName);
        Writer fileWriter = new FileWriter(new File(outputFile));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }
    }
}
