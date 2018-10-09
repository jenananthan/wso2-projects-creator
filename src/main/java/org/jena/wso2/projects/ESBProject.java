package org.jena.wso2.projects;

import java.io.IOException;

import org.jena.wso2.projects.constants.Constants;

import freemarker.template.TemplateException;

public class ESBProject extends ArtifactProject {

    public ESBProject(String projectName, String groupId) {
        super(projectName, groupId);
    }

    public void create() throws IOException, TemplateException {
        createParentDir();
        createPOMAndArtifactXML(Constants.ESB_POM_FTL, Constants.ESB_ECLIPSE_FILE_FTL);
        createDirectoryStructure();
    }

    private void createDirectoryStructure() {
        String[] dirs = { Constants.ESB_API_DIR, Constants.ESB_EP_DIR, Constants.ESB_INBOUND_EP_DIR, Constants.ESB_LOCAL_ENTRIES_DIR, Constants.ESB_MP_DIR,
                Constants.ESB_MS_DIR, Constants.ESB_PROXY_DIR, Constants.ESB_SEQUENCES_DIR, Constants.ESB_TASKS_DIR, Constants.ESB_TEMPALATES__DIR };
        createDirectoryStructure(dirs);
    }

}
