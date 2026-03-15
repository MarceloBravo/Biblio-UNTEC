$env:MAVEN_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
mvn tomcat7:run