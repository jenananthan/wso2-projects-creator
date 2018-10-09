package org.jena.wso2.projects;

import java.io.IOException;

import org.jena.wso2.projects.constants.Constants;

import freemarker.template.TemplateException;

public class DSSProject extends ArtifactProject {

    public DSSProject(String projectName, String groupId) {
        super(projectName, groupId);
    }

    public void create() throws IOException, TemplateException {
        createDirectoryStructure();
        createPOMAndArtifactXML(Constants.DSS_POM_FTL, Constants.DSS_ECLIPSE_FILE_FTL);
        createDirectoryStructure();
    }

    private void createDirectoryStructure() {
        String[] dirs = { Constants.DSS__DIR };
        createDirectoryStructure(dirs);
    }
}
