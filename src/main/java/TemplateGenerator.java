import freemarker.template.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;


public class TemplateGenerator {


    private static Configuration cfg;

    private static void initialize() {
        if (cfg == null) {
            cfg = new Configuration();

            // Where do we load the templates from:
            cfg.setClassForTemplateLoading(TemplateGenerator.class, "templates");

            // Some other recommended settings:
            cfg.setIncompatibleImprovements(new Version(2, 3, 20));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setLocale(java.util.Locale.US);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        }

    }


    public static void generate(Map<String, Object> input, String templateName, String outputFile) throws IOException, TemplateException {
        initialize();
        // 2.2. Get the template

        Template template = cfg.getTemplate(templateName);

        // 2.3. Generate the output

        // Write output to the console
//        Writer consoleWriter = new OutputStreamWriter(System.out);
//        template.process(input, consoleWriter);

        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File(outputFile));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }
    }
}
