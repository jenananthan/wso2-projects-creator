package org.jena.wso2.projects;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jena.wso2.projects.constants.Constants;
import org.jena.wso2.projects.dto.POM;

import freemarker.template.TemplateException;

public class RootProject {
    private String projectName;
    private String groupId;
    private List<String> modules;

    public RootProject(String projectName, String groupId, List<String> modules) {
        this.projectName = projectName;
        this.groupId = groupId;
        this.modules = modules;
    }

    public void create() throws IOException, TemplateException {
        createDirectoryStructure();
        createPOM();
        moveModules();
    }

    private void createDirectoryStructure() {
        new File(projectName + Constants.SUFFIX_TEMP).mkdir();
    }

    private void createPOM() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<String, Object>();
        POM pom = new POM();
        pom.setGroupId(groupId);
        pom.setArtifactId(projectName + "-parent");
        pom.setVersion(Constants.PROJECT_VERSION);
        pom.setProjectName(projectName);
        input.put("pomObject", pom);
        input.put("modules", modules);
        TemplateGenerator.generate(input, Constants.ROOT_PROJECT_POM_FTL, projectName + Constants.SUFFIX_TEMP + "/" + Constants.POM_XML);
        TemplateGenerator.generate(input, Constants.ROOT_ECLIPSE_FILE_FTL, projectName + Constants.SUFFIX_TEMP + "/" + Constants.ECLIPSE_PROJECT_XML);

    }

    private void moveModules() throws IOException {
        File targetDir = new File(projectName + Constants.SUFFIX_TEMP);
        for (String module : modules) {
            FileUtils.moveDirectoryToDirectory(new File(module), targetDir, false);
        }
        FileUtils.moveDirectory(targetDir, new File(projectName));
    }

}
