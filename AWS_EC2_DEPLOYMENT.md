
# Deploy a Spring Boot application on AWS using Amazon EC2

Steps to deploy a Spring Boot application on AWS using AWS EC2.

## Prerequisites

1. AWS Account: You need an AWS account to deploy services on AWS.
2. Spring Boot Application: Your Spring Boot application should be packaged as a JAR or WAR file.


## Steps

1. Create an EC2 Instance:
 - Log in to the AWS Management Console.
 - Navigate to the EC2 service.
 - Click on "Launch Instance" to create a new EC2 instance.
 - Name the Instance for eg. Loan Application
 - Choose an Amazon Machine Image (AMI) that suits your application's requirements. I have selected Amazon Linux 2023 AMI for the application.
 - Configure instance details, such as instance type, network settings, and storage.
 - Create a key pair for eg. rohansingh.ppk. I have used RSA as key pair type.
 - Create or select a security group that allows incoming traffic on the necessary ports (e.g., HTTP, HTTPS).
 - Click on Launch instance.

2. Connect to the EC2 Instance:

- Once the instance is running, you can connect to it using SSH or the Connect button on the instance.

3. Install Java and Dependencies:

- Install Java: 
  ```bash
    sudo yum install java-17-amazon-corretto
  ```

- Install any other necessary dependencies.

4. Package Your Application:

- Build your Spring Boot application and package it as a JAR or WAR file. The packaged file will be deployed to EC2 instance.


5. Upload Your Spring Boot Application:

- We can use WinSCP to upload the Spring Boot JAR/WAR file to the EC2 instance.

- We need to use the public IPv4 DNS of the created instance as hostname and username as below:

  ```bash
   Hostname: ec2-16-171-206-111.eu-north-1.compute.amazonaws.com
   Username: ec2-user
  ```

6. Run Your Application:

- I have copied a demo application for printing "Hello World" and the jar is named as demo-0.0.1-SNAPSHOT.jar.

- We need to use tools like Putty to run the application.
- Connect to EC2 instance using Putty
- Use the SSH key pair (rohansingh.ppk) that you've configured during the instance creation. For eg. rohansingh.ppk in my case. Provide the path of the ppk in Auth section of the configuration.
- Hostname is same as above

 ```bash
  Hostname: ec2-16-171-206-111.eu-north-1.compute.amazonaws.com
 ```

- After connecting to the SSH, use the login as : ec2-user

- Start your Spring Boot application using a command like:

  ```bash
   java -jar demo-0.0.1-SNAPSHOT.jar
  ```

7. Access Your Application:

- Once the deployment is successful, you can access your application using the environment URL provided by EC2.

http://ec2-16-171-206-111.eu-north-1.compute.amazonaws.com:9098/


8. Use a Reverse Proxy (Optional):

- If you want to expose your application over HTTP/HTTPS, consider using a reverse proxy like Nginx or Apache HTTP Server.
- Configure the proxy to forward requests to your Spring Boot application's port (e.g., 8080).

9. Set Up a Domain (Optional):

- If you have a domain name, you can associate it with your EC2 instance's public IP address.
- This can be done through Route 53 or your domain registrar's DNS settings.

10. Secure Your Application:

- Consider using HTTPS by setting up an SSL certificate with a service like AWS Certificate Manager (ACM) and configuring your reverse proxy accordingly.
- Apply security best practices to your EC2 instance, like using SSH key-based authentication and securing access to sensitive files.

11. Automate Deployment (Optional):

- For continuous deployment, consider using tools like AWS CodeDeploy or Jenkins to automate the deployment process.

