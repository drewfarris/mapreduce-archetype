MapReduce Archetype
============================

Quickly setup a MapReduce Maven artifact that includes: 

 - A single Job class with inner Mapper and Reducer classes
 - ...the corresponding unit test classes based on MRUnit 
 - job configuration
 - dependency setup using Apache's Hadoop dist
 - command-line parsing with JCommander
 - a sample run script
 

Install
----------------------------

You install the archetype on your own box by cloning the repo and running 
the Maven installer. Like this: 

    git clone git://github.com/drewfarris/mapreduce-archetype.git 
    cd mapreduce-archetype
    mvn install
    
    
Usage 
-----------------------------

Once installed on your own box you quickly create your own MapReduce artifact
like this:

    mvn archetype:generate -DarchetypeCatalog=local

this starts an interactive session where you enter: 

 - the number of the mapreduce-archetype
 - group id, artifact id and version
 - a java package
 - the base name of your mapreduce job
 
Run `mvn package` to build your job, and take a look at run.sh.sample for how to execute your job. 

Any maven dependencies *with runtime scope* you add to your newly created pom.xml will be included
in the resulting job jar. 

Example run
-------------------------------------------------------
 The example below creates a project directory in `~/src/sample-mapreduce` and 
 create source files named: 
 
  - src/main/java/drew/SampleJob.java
  - src/main/java/drew/SampleMapperTest.java
  - src/main/java/drew/SampleReducerTest.java

Sample run: 

    mybox:~$ cd ~/src
    mybox:~$ mvn archetype:generate -DarchetypeCatalog=local
    [INFO] Scanning for projects...
    [INFO]
    [INFO] ------------------------------------------------------------------------
    [INFO] Building Maven Stub Project (No POM) 1
    [INFO] ------------------------------------------------------------------------
    [INFO]
    [INFO] >>> maven-archetype-plugin:2.2:generate (default-cli) @ standalone-pom >>>
    [INFO]
    [INFO] <<< maven-archetype-plugin:2.2:generate (default-cli) @ standalone-pom <<<
    [INFO]
    [INFO] --- maven-archetype-plugin:2.2:generate (default-cli) @ standalone-pom ---
    [INFO] Generating project in Interactive mode
    [INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
    Choose archetype:
    1: local -> drew:mapreduce-archetype (mapreduce-archetype)
    Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : 1
    Define value for property 'groupId': : drew
    Define value for property 'artifactId': : sample
    Define value for property 'version':  1.0-SNAPSHOT: :
    Define value for property 'package':  drew: :
    Define value for property 'job-base-name': : Sample
    Confirm properties configuration:
    groupId: drew
    artifactId: sample
    version: 1.0-SNAPSHOT
    package: drew
    job-base-name: Sample
     Y: : y
    [INFO] ----------------------------------------------------------------------------
    [INFO] Using following parameters for creating project from Archetype: mapreduce-archetype:3
    [INFO] ----------------------------------------------------------------------------
    [INFO] Parameter: groupId, Value: drew
    [INFO] Parameter: artifactId, Value: sample
    [INFO] Parameter: version, Value: 1.0-SNAPSHOT
    [INFO] Parameter: package, Value: drew
    [INFO] Parameter: packageInPathFormat, Value: drew
    [INFO] Parameter: package, Value: drew
    [INFO] Parameter: version, Value: 1.0-SNAPSHOT
    [INFO] Parameter: job-base-name, Value: Sample
    [INFO] Parameter: groupId, Value: drew
    [INFO] Parameter: artifactId, Value: sample
    [INFO] project created from Archetype in dir: /Users/andrewfarris/src/sample
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 34.919s
    [INFO] Finished at: Fri Oct 04 21:17:56 EDT 2013
    [INFO] Final Memory: 7M/81M
    [INFO] ------------------------------------------------------------------------
    mybox:~$ 
