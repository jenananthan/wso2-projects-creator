package org.jena.wso2.projects;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.diogonunes.jcdp.color.api.Ansi;
import org.jena.wso2.projects.constants.Constants;
import org.jena.wso2.projects.dto.POM;

import freemarker.template.TemplateException;
import org.jena.wso2.projects.util.CommandLineUtil;

public class ArtifactProject {
    private String projectName;
    private String groupId;

    private final CommandLineUtil commandLineUtil = CommandLineUtil.getInstance();

    ArtifactProject(String projectName, String groupId) {
        this.projectName = projectName;
        this.groupId = groupId;
    }

    protected void createParentDir() {
        new File(projectName).mkdir();
    }

    protected void createPOMAndArtifactXML(String pomTemplate, String artifactTemplate) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<String, Object>();
        POM pom = new POM();
        pom.setGroupId(groupId);
        pom.setArtifactId(projectName);
        pom.setVersion(Constants.PROJECT_VERSION);
        pom.setProjectName(projectName);
        input.put("pomObject", pom);
        TemplateGenerator.generate(input, pomTemplate, projectName + "/" + Constants.POM_XML);
        TemplateGenerator.generate(input, artifactTemplate, projectName + "/" + Constants.ECLIPSE_PROJECT_XML);
        TemplateGenerator.generate(input, Constants.ARTIFACT_FTL, projectName + "/" + Constants.ARTIFACT_XML);
    }

    protected void createDirectoryStructure(String[] dirs) {
        if (dirs != null) {
            for (String dir : dirs) {
                commandLineUtil.println(Ansi.FColor.GREEN, "\tGenerating directory : " + dir);
                new File(projectName + "/" + dir).mkdirs();
            }
        }
    }

}
