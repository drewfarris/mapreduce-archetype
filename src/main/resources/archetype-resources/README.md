${job-base-name} Mapreduce Job
=========================================

A project that builds a self-contained Hadoop job jar.

This project was created using the maven mapreduce archetype available on [Github][1]

`mvn packge` will build a Hadoop job jar in `target/${artifactId}-job.jar` containing the class `${package}.${job-base-name}Job`



Usage
-----------------------------------------

To run this job, execute the following command in the project root:

    hadoop jar ./target/${artifactId}-job.jar --input /tmp/${artifactId}/input --output /tmp/${artifactId}/output

Alternately, execute the script `run.sh.sample`

[1]: https://github.com/drewfarris/mapreduce-archetype

