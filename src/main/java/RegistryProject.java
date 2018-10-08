import dto.POM;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistryProject {
    private String projectName;

    private String groupId;

    public RegistryProject(String projectName, String groupId) {
        this.projectName = projectName;
        this.groupId = groupId;
    }

    public void create(String[] customDirectories) throws IOException, TemplateException {
        createDirectoryStructure(customDirectories);
        createPOM();
        createArtifactXML();
    }

    private void createDirectoryStructure(String[] customDirectories) {
        new File(projectName).mkdir();
        if (customDirectories != null) {

            for (String dir : customDirectories
                    ) {
                System.out.println("Reg custom dir : " + dir);
                new File(projectName + "/" + dir).mkdirs();
            }
        }

    }

    private void createPOM() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<String, Object>();
        POM pom = new POM();
        pom.setGroupId(groupId);
        pom.setArtifactId(projectName);
        pom.setVersion(Constants.PROJECT_VERSION);
        pom.setProjectName(projectName);
        input.put("pomObject", pom);
        TemplateGenerator.generate(input, Constants.REG_POM_FTL, projectName + "/" + Constants.POM_XML);
        TemplateGenerator.generate(input, Constants.REG_ECLIPSE_FILE_FTL, projectName + "/" + Constants.ECLIPSE_PROJECT_XML);
    }

    private void createArtifactXML() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<String, Object>();
        TemplateGenerator.generate(input, Constants.ARTIFACT_FTL, projectName + "/" + Constants.ARTIFACT_XML);
    }


}
