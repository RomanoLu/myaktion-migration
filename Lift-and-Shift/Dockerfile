# Use the official WildFly image as the base image
FROM jboss/wildfly:9.0.0.Final

# Set the working directory to the WildFly deployment directory
WORKDIR /opt/jboss/wildfly/standalone/deployments/

# Copy the compiled application WAR file to the deployment directory
COPY target/my-aktion.war ./

COPY my-standalone.xml /opt/jboss/wildfly/standalone/configuration/

COPY my-aktion.keystore /opt/jboss/wildfly/standalone/configuration/

# Start WildFly and deploy the application
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-c","my-standalone.xml"]
