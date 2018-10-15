package org.jena.wso2.projects.dto;

public class ProjectMetadata {
    private String projectName;
    private String groupId;
    private String version;

    private boolean includeEsbProject;
    private boolean includeRegistryProject;
    private boolean includeDssProject;

    private String[] environments;
    private String[] registryCustomDirs;

    public ProjectMetadata() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isIncludeEsbProject() {
        return includeEsbProject;
    }

    public void setIncludeEsbProject(boolean includeEsbProject) {
        this.includeEsbProject = includeEsbProject;
    }

    public boolean isIncludeRegistryProject() {
        return includeRegistryProject;
    }

    public void setIncludeRegistryProject(boolean includeRegistryProject) {
        this.includeRegistryProject = includeRegistryProject;
    }

    public boolean isIncludeDssProject() {
        return includeDssProject;
    }

    public void setIncludeDssProject(boolean includeDssProject) {
        this.includeDssProject = includeDssProject;
    }

    public String[] getEnvironments() {
        return environments;
    }

    public void setEnvironments(String[] environments) {
        this.environments = environments;
    }

    public String[] getRegistryCustomDirs() {
        return registryCustomDirs;
    }

    public void setRegistryCustomDirs(String[] registryCustomDirs) {
        this.registryCustomDirs = registryCustomDirs;
    }
}
