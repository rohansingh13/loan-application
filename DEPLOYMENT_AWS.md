
# Deploy a Spring Boot application on AWS using AWS Elastic Beanstalk

Steps to deploy a Spring Boot application on AWS using AWS Elastic Beanstalk.

## Prerequisites

1. AWS Account: You need an AWS account to deploy services on AWS.
2. Spring Boot Application: Your Spring Boot application should be packaged as a JAR or WAR file.
3. AWS Elastic Beanstalk CLI: Install the AWS Elastic Beanstalk Command Line Interface (EB CLI) to interact with Elastic Beanstalk from your terminal.

## Steps

1. Package Your Application:

Build your Spring Boot application and package it as a JAR or WAR file. The packaged file will be deployed to Elastic Beanstalk.

2. Initialize Elastic Beanstalk Environment:

Open a terminal and navigate to your application's directory. Initialize an Elastic Beanstalk environment using the EB CLI.
 Replace my-app-name with your desired application name.

```bash
  eb init -p java-8 my-app-name
```

3. Create Elastic Beanstalk Application Version:

Create an application version for your Spring Boot application using the packaged JAR/WAR file.

```bash
  eb create my-app-version --version 1.0.0 --artifact my-app.jar
```

4. Configure Environment Variables:

Set up environment variables for your application using the EB CLI or through the Elastic Beanstalk console.
Replace SPRING_PROFILES_ACTIVE=production with your required environment variables

```bash
  eb setenv SPRING_PROFILES_ACTIVE=production
```

5. Deploy Application:

Deploy your application version to the Elastic Beanstalk environment.
Replace my-environment-name with your environment's name.

```bash
  eb deploy my-environment-name
```

6. Access Your Application:

Once the deployment is successful, you can access your application using the environment URL provided by Elastic Beanstalk.

## Updating Your Application

If you need to update your application, make the necessary changes, rebuild your JAR/WAR file, and then redeploy the new version using the following commands:

1. Build Your Application:

Build your Spring Boot application and package it as a JAR or WAR file.

2. Create a New Application Version:

Create a new application version using the updated packaged JAR/WAR file.

```bash
  eb create my-new-app-version --version 1.1.0 --artifact target/my-updated-app.jar
```

3. Deploy Updated Application:

Deploy the updated version to your Elastic Beanstalk environment.

```bash
 eb deploy my-environment-name
```

- Note: Replace placeholders like my-app-name, my-app-version, my-environment-name, and file paths with actual values specific to your application and environment.

##Update and Scaling

- Update Application: 
When you make changes to your application, package and deploy a new version using the same steps.

- Scaling: 
Elastic Beanstalk can automatically scale your application based on CPU utilization, memory, or custom metrics.



## Monitoring and Scaling

Elastic Beanstalk provides built-in monitoring and auto-scaling capabilities. You can configure scaling policies and view metrics through the AWS Management Console.

## Considerations

- Configuration Files: 
Customize your environment's configuration by adding .ebextensions directory with configuration YAML files.

- Database Configuration: 
If your application uses a database, ensure you have configured its connection properties.

- Security: 
Elastic Beanstalk environments can be launched in a Virtual Private Cloud (VPC) for enhanced security.

- Domain Mapping: 

You can map a custom domain to your Elastic Beanstalk environment using Route 53.










## Deployment

To deploy this project run

```bash
  npm run deploy
```

