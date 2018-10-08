# wso2-projects-creator

This tool will help to create wso2 developer studio ESB, REG , DSS and CAPP project in a minute. 
1. Create the project structure
2. Open in developer studio and use

# Development Guide
###### Prerequisites
* Java 1.8
* Maven  

Build the project
```sh
mvn clean install
```

#How to use

1. Build the project
2. Navigate to the target/ directory and get the jar **wso2-projects-creator-tool.jar**
3. Place the jar where you want to create the project and execute the command


```sh
java -jar wso2-projects-creator-tool.jar 
```

4. Give the answers to the questions prompted by the tool

            **Enter project name. e.g Sample-Project**
            InvoiceManagement
            
            **Enter environments with comma separated values. e.g local,aat,sit**
            dev,test,ppd,prod   
            
            **Enter group id. e.g org.test.sample-project**
            org.test.invoicemanagement
            
            **Do you want to create ESB project. Y/N**
            y
            
            **Do you want to create Registry project. Y/N**
            y
            
            **Do you want to create custom directories. Y/N**
            y
            
            **Enter custom directory paths if needed. e.g xslt, xsd, endpoints/local, endpoints/sit**
            xslt,xsd,endpoints/dev,endpoints/test,endpoints/ppd,endpoints/prod
            
            **Do you want to create DSS project. Y/N**
            y

5. Project structure will be created with given information

```sh
InvoiceManagement/
|-- .project
|-- InvoiceManagementCAPP
|   |-- .project
|   `-- pom.xml
|-- InvoiceManagementCAPP-DEV
|   |-- .project
|   `-- pom.xml
|-- InvoiceManagementCAPP-PPD
|   |-- .project
|   `-- pom.xml
|-- InvoiceManagementCAPP-PROD
|   |-- .project
|   `-- pom.xml
|-- InvoiceManagementCAPP-TEST
|   |-- .project
|   `-- pom.xml
|-- InvoiceManagementDSS
|   |-- .project
|   |-- artifact.xml
|   |-- dataservice
|   `-- pom.xml
|-- InvoiceManagementESB
|   |-- .project
|   |-- artifact.xml
|   |-- pom.xml
|   `-- src
|       `-- main
|           `-- synapse-config
|               |-- api
|               |-- endpoints
|               |-- inbound-endpoints
|               |-- local-entries
|               |-- message-processors
|               |-- message-stores
|               |-- proxy-services
|               |-- sequences
|               |-- tasks
|               `-- templates
|-- InvoiceManagementREG
|   |-- .project
|   |-- artifact.xml
|   |-- endpoints
|   |   |-- dev
|   |   |-- ppd
|   |   |-- prod
|   |   `-- test
|   |-- pom.xml
|   |-- xsd
|   `-- xslt
`-- pom.xml

```
