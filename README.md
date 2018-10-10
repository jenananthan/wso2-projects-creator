# wso2-projects-creator

This tool will help to create wso2 developer studio ESB, REG , DSS and CAPP project as multi maven
project in a minute.

This tool allows to create environment specific composite apps where environment specific configs
e.g endpoints and test artifacts can be configured.

Once project structure is created , it can be simply opened in wso2 developer studio (user option import as 
existing wso2 projects in eclipse) and artifacts can be created as per the requirement.

**Please note that without any artifacts created maven build will fail for projects.**


# Development Guide
###### Prerequisites
* Java 1.8
* Maven  

Build the project
```sh
mvn clean install
```

# How to use

1. Build the project
2. Navigate to the target/ directory and get the jar **wso2-projects-creator-tool.jar**
3. Place the jar where you want to create the project and execute the command


```sh
java -jar wso2-projects-creator-tool.jar 
```

4. Give the answers to the questions prompted by the tool

    **Enter project name. e.g invoice-sync**
    
        invoice-sync
    
    **Enter environments with comma separated values. e.g dev,aat,sit**
    
        dev,sit,prod
   
    **Enter group id. e.g org.test.sales**
    
        org.test.sales
   
    **Do you want to create ESB project. Y/N**
    
        y
    
    **Do you want to create Registry project. Y/N**
    
        y
    
    **Do you want to create custom directories in Registry projects. Y/N**
    
        y

    **Enter custom directory paths. e.g xslt, xsd, endpoints/dev, endpoints/sit**

        endpoints/dev,endpoints/sit,endpoints/prod

    **Do you want to create DSS project. Y/N**
    
        y
    

5. Project structure will be created with given information

```sh
invoice-sync/
├── pom.xml
├── .project
├── invoice-sync
│   ├── pom.xml
│   └── .project
├── invoice-sync-config-dev
│   ├── pom.xml
│   └── .project
├── invoice-sync-config-prod
│   ├── pom.xml
│   └── .project
├── invoice-sync-config-sit
│   ├── pom.xml
│   └── .project
├── invoice-sync-dss
│   ├── artifact.xml
│   ├── dataservice
│   ├── pom.xml
│   └── .project
├── invoice-sync-esb
│   ├── artifact.xml
│   ├── pom.xml
│   ├── .project
│   └── src
│       └── main
│           └── synapse-config
│               ├── api
│               ├── endpoints
│               ├── inbound-endpoints
│               ├── local-entries
│               ├── message-processors
│               ├── message-stores
│               ├── proxy-services
│               ├── sequences
│               ├── tasks
│               └── templates
└── invoice-sync-reg
    ├── artifact.xml
    ├── endpoints
    │   ├── prod
    │   └── sit
    │   └── dev
    ├── pom.xml
    └── .project


```
