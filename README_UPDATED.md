### Updated Readme!!

- Before Moving forward: Local Port has been updated from 8080 to 8081.
- Swagger UI : http://localhost:8081/swagger-ui.html
- H2 UI : http://localhost:8081/h2-console


## Work Update
  
  - Bugs & Improvements: 
    - 
    - @RequestBody was missing at EmployeeController.java:42
    - Fixed the logic of updateEmployee() at EmployeeController.java:55.
    - Added fallbacks for deleteEmployee() at EmployeeServiceImpl.java:40 & 
      getEmployee() at EmployeeServiceImpl.java:30.
    - Added return statements for various methods.
    - Added a welcome message on base route.

  - Docs:
    - 
    - Configured Swagger & placed it in a new package "config" -> Swaggerconfig
    - In SwaggerConfig, added some developer info & license. Along with this, updated endpoints description by adding 
      ApiOperation().

  - Tests:
    - 
    - Added tests using JUnit & Mockito for EmployeeServiceImpl service.
  
  - Caching Logic:
    - 
    - Added caching logic using spring caching package.
    - Cacheable , CacheEvict & CachePut are used.
    
  - Comments:
    - 
    - Added appropriate comments for all the function present in service.

  - Securing end points:
    - 
    - Tried to secure the end points using spring security package.
    - Faced few issues with some exceptions. 
    - Dropped it for now.
    
  ## Final Remarks:
    - There are no performance issues after the fix.
    - Tests & Basic app is running fine.
    - I am a beginner with Java & spring framwork, but would love to learn it more.
    - Hoping for the best.

