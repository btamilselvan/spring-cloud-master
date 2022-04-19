- Use shade plugin to generate the package that needs to be uploaded in AWS lambda
- Specify the transformer configuration
- Include spring-boot-maven-plugin dependency under maven-shade-plugin (to support the transfor configuration)
- Use "spring_cloud_function_definition" environment variable to specify the function name - in lambda console
- Use "MAIN_CLASS" environment variable to specify the entry point class (com.success.SpringCloudFunctionApplication) - in lambda console
- The default RequestHandler implementation is org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest
- mvn clean package
- Use spring_cloud_function_routingExpression environment variable for dynamic routing. For e.g.
	spring_cloud_function_routingExpression=headers['func_name']
- spring_cloud_function_definition can also be used to route the requests to specific function.
 - Both these variables can be set in application.properties as well or directly in the lambda console.

##### references
- https://docs.spring.io/spring-cloud-function/docs/3.2.3/reference/html/spring-cloud-function.html#_serverless_platform_adapters
- https://github.com/spring-cloud/spring-cloud-function/tree/main/spring-cloud-function-samples/function-sample-aws-routing