# Build Status

[![CircleCI](https://circleci.com/gh/jenananthan/wso2-projects-creator/tree/master.svg?style=shield)](https://circleci.com/gh/jenananthan/wso2-projects-creator/tree/master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0c6c2820140242529fc8ca457049ae8f)](https://app.codacy.com/app/jenananthan/wso2-projects-creator?utm_source=github.com&utm_medium=referral&utm_content=jenananthan/wso2-projects-creator&utm_campaign=Badge_Grade_Dashboard)


# wso2-projects-creator

This tool will help to create wso2 developer studio ESB, REG , DSS and CAPP project as multi maven
project in a minute.

This tool allows to create environment specific composite apps where environment specific configs
e.g endpoints and test artifacts can be configured.

Once project structure is created , it can be simply opened in wso2 developer studio (Use the option **import** as 
**Existing wso2 projects into work space** in eclipse) and artifacts can be created as per the requirement.

**Please note that without any artifacts created maven build will fail for projects.**


# How to use


1. Download the jar **wso2-projects-creator-tool.jar** (https://github.com/jenananthan/wso2-projects-creator/blob/master/distribution/wso2-projects-creator-tool.jar)
2. Place the jar where you want to create the project and execute the command


```sh
java -jar wso2-projects-creator-tool.jar 
```

3. Give the answers to the questions prompted by the tool

    **Enter project name.  e.g invoice-sync**
    
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
    

4. Project structure will be created with given information

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
5. Open the project using the option **import** as **Existing wso2 projects into work space** in eclipse/developer studio

# Development Guide
###### Prerequisites
* Java 1.8
* Maven  

Build the project
```sh
mvn clean install
```
