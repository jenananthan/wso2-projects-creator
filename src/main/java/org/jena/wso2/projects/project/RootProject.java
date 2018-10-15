package org.jena.wso2.projects.project;

import com.diogonunes.jcdp.color.api.Ansi;
import freemarker.template.TemplateException;
import org.jena.wso2.projects.constants.Constants;
import org.jena.wso2.projects.dto.POM;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RootProject extends Project {

    public RootProject(ProjectBuilder builder) {
        super(builder);
    }

    @Override
    public void generate() throws IOException {
        commandLineUtil.println(Ansi.FColor.GREEN, "Generating project " + this.projectName);

        generateDirectoryStructure();
        try {
            generateProjectFiles();
        } catch (TemplateException e) {
            throw new IOException("Failed to generate project file from template");
        }
    }

    protected void generateDirectoryStructure() throws IOException {
        //generate parent project dir
        String projectFolder = this.baseProjectName;
        new File(projectFolder).mkdirs();
    }

    private void generateProjectFiles() throws IOException, TemplateException {
        POM pom = new POM();
        pom.setGroupId(this.groupId);
        pom.setArtifactId(this.projectName);
        pom.setVersion(this.version);

        pom.setProjectName(this.baseProjectName);

        Map<String, Object> input = new HashMap<>();
        input.put("pomObject", pom);
        input.put("modules", Project.Auditor.getInstance().getAudits());

        String projectFolder = this.baseProjectName;

        String pomFile = projectFolder + File.separator + Constants.POM_XML;
        String eclipseFile = projectFolder + File.separator + Constants.ECLIPSE_PROJECT_XML;

        generateTemplate(input, this.pomFileTemplate, pomFile);
        generateTemplate(input, this.eclipseProjectFileTemplate, eclipseFile);
    }
}
