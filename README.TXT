How to run the application.
1. Open serverproject in IntelliJ IDEA
2. Run Spring application using Java class DemotaskApplication
3. Open http://localhost:8080 and try using different requests. You can open "Network" tab in the browser (by pressing F12) to see the details of the requests
You can use the following credentials on the page: username = user, password = password

If you want to generate UI code and copy it to the server, you can do:
1. Install Node.js.
2. Run "npm install -g @angular/cli" command to install Angular CLI
3. Go to clientproject folder
4. Run "npm install" command
5. Run "ng build" command
6. Copy files from clientproject/dist to serverproject/src/main/resources/static folder