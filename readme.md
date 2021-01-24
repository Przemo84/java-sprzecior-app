# Simple App for managing tools in surveyor company    
 - Admin panel  
 - App panel for employees  

### Running an app on local environment

 1. Set your DB password in `application.properties` and `docker-compose-dev.yml` files.  
 2. Run command: `docker-compose -f docker-compose-dev.yml up -d`.  
 3. In a browser enter: `http://localhost:8080/login`.  
    <p>&nbsp;</p>
    You can login as one of seeded users with following usernames:
 - `admin` - with Admin role to Admin panel  
 - `employee` - with Employee role to App panel  
 - `editor` - with Editor role to App panel.  
All above with password: `random`.


