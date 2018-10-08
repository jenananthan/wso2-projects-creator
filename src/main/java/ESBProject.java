import dto.POM;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ESBProject {
    private String projectName;

    private String groupId;

    public ESBProject(String projectName, String groupId) {
        this.projectName = projectName;
        this.groupId = groupId;

    }

    public void create() throws IOException, TemplateException {
        createDirectoryStructure();
        createPOM();
        createArtifactXML();
    }

    private void createDirectoryStructure() {


        new File(projectName).mkdir();
        new File(projectName + Constants.ESB_API_DIR).mkdirs();
        new File(projectName + Constants.ESB_EP_DIR).mkdirs();
        new File(projectName + Constants.ESB_INBOUND_EP_DIR).mkdirs();
        new File(projectName + Constants.ESB_LOCAL_ENTRIES_DIR).mkdirs();
        new File(projectName + Constants.ESB_MP_DIR).mkdirs();
        new File(projectName + Constants.ESB_MS_DIR).mkdirs();
        new File(projectName + Constants.ESB_PROXY_DIR).mkdirs();
        new File(projectName + Constants.ESB_SEQUENCES_DIR).mkdirs();
        new File(projectName + Constants.ESB_TASKS_DIR).mkdirs();
        new File(projectName + Constants.ESB_TEMPALATES__DIR).mkdirs();


    }

    private void createPOM() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<String, Object>();
        POM pom = new POM();
        pom.setGroupId(groupId);
        pom.setArtifactId(projectName);
        pom.setVersion(Constants.PROJECT_VERSION);
        pom.setProjectName(projectName);
        input.put("pomObject", pom);
        TemplateGenerator.generate(input, Constants.ESB_POM_FTL, projectName + "/" + Constants.POM_XML);
        TemplateGenerator.generate(input, Constants.ESB_ECLIPSE_FILE_FTL, projectName + "/" + Constants.ECLIPSE_PROJECT_XML);
    }

    private void createArtifactXML() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<String, Object>();
        TemplateGenerator.generate(input, Constants.ARTIFACT_FTL, projectName + "/" + Constants.ARTIFACT_XML);
    }
}
