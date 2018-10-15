package org.jena.wso2.projects.constants;

public class Constants {
    //Project Types
    public final static String PROJECT_TYPE_ROOT = "ROOT_PROJECT";
    public final static String PROJECT_TYPE_CAPP = "CAPP_PROJECT";
    public final static String PROJECT_TYPE_ESB = "ESB_PROJECT";
    public final static String PROJECT_TYPE_REG = "REG_PROJECT";
    public final static String PROJECT_TYPE_DSS = "DSS_PROJECT";

    //Project Suffixes
    public final static String SUFFIX_REG = "-reg";
    public final static String SUFFIX_ESB = "-esb";
    public final static String SUFFIX_DSS = "-dss";
    public final static String SUFFIX_CONFIG = "-config-";
    public final static String SUFFIX_ROOT = "-parent";

    //POM Template Files
    public final static String ROOT_POM_FTL = "project-pom.ftl";
    public final static String CAPP_POM_FTL = "capp-pom.ftl";
    public final static String ESB_POM_FTL = "esb-pom.ftl";
    public final static String REG_POM_FTL = "reg-pom.ftl";
    public final static String DSS_POM_FTL = "dss-pom.ftl";

    //Eclipse Project File Templates
    public final static String ROOT_ECLIPSE_FILE_FTL = "root-eclipse-project.ftl";
    public final static String CAPP_ECLIPSE_FILE_FTL = "capp-eclipse-project.ftl";
    public final static String ESB_ECLIPSE_FILE_FTL = "esb-eclipse-project.ftl";
    public final static String DSS_ECLIPSE_FILE_FTL = "dss-eclipse-project.ftl";
    public final static String REG_ECLIPSE_FILE_FTL = "reg-eclipse-project.ftl";

    //Other common constants
    public final static String POM_XML = "pom.xml";
    public final static String ARTIFACT_XML = "artifact.xml";
    public final static String ARTIFACT_FTL = "artifact.ftl";
    public final static String ECLIPSE_PROJECT_XML = ".project";
    public final static String PROJECT_VERSION = "1.0.0";
    public final static String TEMPLATES_DIR = "/templates";

    //Logger
    public final static String LOGGER_NAME = "org.jena.wso2.projects";


    //ESB sub Directories
    private final static String ESB_API_DIR = "/src/main/synapse-config/api";
    private final static String ESB_EP_DIR = "/src/main/synapse-config/endpoints";
    private final static String ESB_INBOUND_EP_DIR = "/src/main/synapse-config/inbound-endpoints";
    private final static String ESB_LOCAL_ENTRIES_DIR = "/src/main/synapse-config/local-entries";
    private final static String ESB_MP_DIR = "/src/main/synapse-config/message-processors";
    private final static String ESB_MS_DIR = "/src/main/synapse-config/message-stores";
    private final static String ESB_PROXY_DIR = "/src/main/synapse-config/proxy-services";
    private final static String ESB_SEQUENCES_DIR = "/src/main/synapse-config/sequences";
    private final static String ESB_TASKS_DIR = "/src/main/synapse-config/tasks";
    private final static String ESB_TEMPLATES_DIR = "/src/main/synapse-config/templates";
    public static final String[] ESB_DIRS = {
            Constants.ESB_API_DIR,
            Constants.ESB_EP_DIR,
            Constants.ESB_INBOUND_EP_DIR,
            Constants.ESB_LOCAL_ENTRIES_DIR,
            Constants.ESB_MP_DIR,
            Constants.ESB_MS_DIR,
            Constants.ESB_PROXY_DIR,
            Constants.ESB_SEQUENCES_DIR,
            Constants.ESB_TASKS_DIR,
            Constants.ESB_TEMPLATES_DIR
    };

    //DSS sub directories
    public final static String DSS_DIR = "/dataservice";
    public final static String[] DSS_DIRS = {
            Constants.DSS_DIR
    };

}
