### References
- https://docs.awspring.io/spring-cloud-aws/docs/2.4.0/reference/html/index.html#configuration-properties
- https://github.com/awspring/spring-cloud-aws
- https://awspring.io/learn/introduction/


#### Notes
- Using minimal configuration SpringBoot, Spring Cloud AWS can be integrated in an application.
- Refer pom.xml
- To activate/integrate with AWS parameter store, simply add a dependency on the spring-cloud-starter-aws-parameter-store-config starter module to activate the support.
- Make sure to add spring.config.import=aws-parameterstore: in the application.properties to activate the parameter store integration.
- Read replica support can be enabled using property "readReplicaSupport".
- The transactions that are marked as readOnly will be automatically directed to replica.
- This project can be run in two profiles.
	- rds profile - this would make use of the spring-cloud-aws-jdbc.
	- custom profile - this is legacy way of directing the JDBC calls to primary and replica databases based on the transaction type.
	- rds instance needs to run
- Spring Cloud AWS fetches all the necessary metadata and creates a Tomcat JDBC pool with the default properties.