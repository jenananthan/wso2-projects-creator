package org.jena.wso2.projects.project;

import org.jena.wso2.projects.constants.Constants;
import org.jena.wso2.projects.dto.ProjectMetadata;

import java.io.IOException;

public class ProjectGenerator {

    private final ProjectMetadata projectMetadata;

    public ProjectGenerator(ProjectMetadata projectMetadata) {
        this.projectMetadata = projectMetadata;
    }

    public void generate() throws IOException {
        //Generate CAPP Project
        new Project.ProjectBuilder()
                .withBaseProjectName(projectMetadata.getProjectName())
                .withGroupId(projectMetadata.getGroupId())
                .withVersion(Constants.PROJECT_VERSION)
                .build(Constants.PROJECT_TYPE_CAPP)
                .generate();

        //Generate CAPP Projects for environments
        for (String env : projectMetadata.getEnvironments()) {
            new Project.ProjectBuilder()
                    .withBaseProjectName(projectMetadata.getProjectName())
                    .withGroupId(projectMetadata.getGroupId())
                    .withVersion(Constants.PROJECT_VERSION)
                    .withSuffix(env)
                    .build(Constants.PROJECT_TYPE_CAPP)
                    .generate();
        }

        //Create REG project
        if (projectMetadata.isIncludeRegistryProject()) {
            new Project.ProjectBuilder()
                    .withBaseProjectName(projectMetadata.getProjectName())
                    .withGroupId(projectMetadata.getGroupId())
                    .withVersion(Constants.PROJECT_VERSION)
                    .withSubdirectories(projectMetadata.getRegistryCustomDirs())
                    .build(Constants.PROJECT_TYPE_REG)
                    .generate();
        }

        //Create ESB Project
        if (projectMetadata.isIncludeEsbProject()) {
            new Project.ProjectBuilder()
                    .withBaseProjectName(projectMetadata.getProjectName())
                    .withGroupId(projectMetadata.getGroupId())
                    .withVersion(Constants.PROJECT_VERSION)
                    .build(Constants.PROJECT_TYPE_ESB)
                    .generate();
        }

        //Create DSS project
        if (projectMetadata.isIncludeDssProject()) {
            new Project.ProjectBuilder()
                    .withBaseProjectName(projectMetadata.getProjectName())
                    .withGroupId(projectMetadata.getGroupId())
                    .withVersion(Constants.PROJECT_VERSION)
                    .build(Constants.PROJECT_TYPE_DSS)
                    .generate();
        }

        //Create ROOT project
        new Project.ProjectBuilder()
                .withBaseProjectName(projectMetadata.getProjectName())
                .withGroupId(projectMetadata.getGroupId())
                .withVersion(Constants.PROJECT_VERSION)
                .build(Constants.PROJECT_TYPE_ROOT)
                .generate();
    }
}
