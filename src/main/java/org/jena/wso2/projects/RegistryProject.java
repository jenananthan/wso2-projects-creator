package org.jena.wso2.projects;

import java.io.IOException;

import org.jena.wso2.projects.constants.Constants;

import freemarker.template.TemplateException;

public class RegistryProject extends ArtifactProject {

    public RegistryProject(String projectName, String groupId) {
        super(projectName, groupId);
    }

    public void create(String[] customDirectories) throws IOException, TemplateException {
        createParentDir();
        createPOMAndArtifactXML(Constants.REG_POM_FTL, Constants.REG_ECLIPSE_FILE_FTL);
        createDirectoryStructure(customDirectories);
    }

}
