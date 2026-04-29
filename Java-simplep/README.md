# simple-maven-project
This is a basic Java project built using Maven. It demonstrates a simple project structure and integrates with Jenkins for continuous integration.

## Project Structure
```
simple-maven-project/
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── company/
    │               └── myapp/
    │                   └── App.java
    └── test/
        └── java/
            └── com/
                └── company/
                    └── myapp/
                        └── AppTest.java
```
## Project Setup
Create Project Structure
```
mkdir -p /simple-maven-project/src/main/java/com/company/myapp
mkdir -p /simple-maven-project/src/test/java/com/company/myapp
cd /simple-maven-project
touch pom.xml
touch src/main/java/com/company/myapp/App.java
touch src/test/java/com/company/myapp/AppTest.java
```
## Jenkins Integration
Jenkins Configuration (on Jenkins server)
1. Navigate to http://jenkins-server-ip:8080/
2. Go to Manage Jenkins
   - Under Global Tool Configuration
      . Configure JDK: Add a name, enable "Install automatically"
      . Configure Maven: Add a name, select version (auto-selected if available)
   - Save changes

Creating a Jenkins Job
1. Go to Jenkins Home Page
2. Click New Item
3. Enter a project name and select Freestyle Project
4. Configure the job:
    - SCM: Enter the Git repository link and branch
    - Build Triggers: Check "GitHub hook trigger for GITScm polling"
    - Build Steps:
      . Add "Invoke top-level Maven targets"
      . Choose the configured Maven version
      . Set Goals: clean package
5. Save the job
6. Run the job manually to test
After a successful build, we can find the generated .jar file in the job's Workspace.
