#### FIRST TIME JAVA USER SETUP

## Installing Homebrew
If you have not done so already, you will need to install Homebrew:
    -https://brew.sh/

## Installing Java
If you have not done so already, you will need to install Java:
-In Terminal, type the following commands:
    -brew update
    -brew tap caskroom/cask
    -brew cask install caskroom/versions/java11
        -Note: Java 13 was released in September 2019 and is not compatible with other programs required to run this project.
    -brew cask info java

#### RUNNING THIS PROJECT:

-Clone this repository.
-Create a database called pet_hotel
-Copy+paste the contents of database.sql into your database and run.
-In application.properties, update spring.datasource.username to reflect your Postico user ID

-To run, enter the following commands into the terminal:
    -./gradlew bootrun
        -Without Gradle, the Node commands would be as follows (Controller is the sample name and is the equivalent of server.js in Javascript):
            -javac Controller.java
                -This compiles it.
            -java Controller
                -This runs it once it is compiled.

-Clone the client-side repository.
-npm install
-npm start

#### NEW JAVA PROJECT:

## Installing Gradle
(Gradle is to Java what NPM is to Javascript)
Go to https://start.spring.io/
-Select the following:
    -Project: Gradle Project
    -Language: Java
    -Spring Boot: 2.1.8
    -Project Metadata: com.pethotel
    -Artifact: demo
    -Options>Java: 11
    -Dependencies>List>
        -Spring Web
        -Spring Data JDBC
        -PostgreSQL Driver
    -Click Generate
    -Unzip the file, then copy+paste into the project folder

## Creating the database
Create the database as normal in Postico

## Connecting to the database
In the main project folder, create a file called application.properties. (This is the Java equivalent to pool.js module in Javascript to run JDBC, which is equivalent to PG)
-Copy+paste the contents from this project
    -Update the spring.datasource.url to reflect the database name in Postico
    -Update the spring.datasource.username to reflect your Postico user ID
        Note: It is recommended that if working on a group project, add application.properties to the .gitignore, and each user should create a copy with their personal username.

## Hello World
Create a file called Controller.java (Controller is the sample name and is the equivalent of server.js in Javascript.) Demo in the first line is the file path in which the files are located:

    package demo;
    class Controller
    {
        public static void main (String args[])
        {
            System.out.println("Hello World!");
        }
    }

## Connecting to the client side
React setup and startup is the same.
(One important note regarding Axios requests is that the RowMapper will convert server-side snake_case to client-side camelCase.)

In the package.json file, ensure the following is reflected:
    "proxy": "http://localhost:8080",