package org.jena.wso2.projects;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jena.wso2.projects.constants.Constants;
import org.jena.wso2.projects.dto.POM;
import freemarker.template.TemplateException;

public class CAPPProject extends ArtifactProject {
    private String projectName;
    private String groupId;

    public CAPPProject(String projectName, String groupId) {
        super(projectName, groupId);
    }

    public void create() throws IOException, TemplateException {
        createDirectoryStructure();
        createPOM();
    }

    private boolean createDirectoryStructure() {
        File dir = new File(projectName);
        return dir.mkdir();
    }

    private void createPOM() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<String, Object>();
        POM pom = new POM();
        pom.setGroupId(groupId);
        pom.setArtifactId(projectName);
        pom.setVersion(Constants.PROJECT_VERSION);
        pom.setProjectName(projectName);
        input.put("pomObject", pom);
        TemplateGenerator.generate(input, Constants.CAPP_POM_FTL, projectName + "/" + Constants.POM_XML);
        TemplateGenerator.generate(input, Constants.CAPP_ECLIPSE_FILE_FTL, projectName + "/" + Constants.ECLIPSE_PROJECT_XML);
    }
}
