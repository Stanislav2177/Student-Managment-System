Student Management System.

This is a mini project that contains three main pages: login, student, and admin. To implement this project, you will need JDBC, MySQL connector, and JavaFX SDK.

For easier implementation, here are some helpful resources:

A video tutorial on how to connect Java with MySQL: https://www.youtube.com/watch?v=V2bGKzvMQyc&t=1s
A video tutorial on JavaFX: https://www.youtube.com/watch?v=ZfaPMLdgJxQ + shown how to use 
SceneBuilder, an application that allows you to modify FXML files.

In the project files, there is a MySQL file that needs to be opened in MySQL Workbench. After opening the tables, you will need to change the root and password to be relevant to your MySQL in the open() methods in the DBMessages and DBUtils classes, which are located in the StudentInfo package. These two classes are used for working with the databases, such as adding, updating, and sorting.

In the other package, controllers are placed, which process the actions made in the UI. Each scene that is loaded on the screen is separated into a different class, although there are still 1-2 scenes that are available in the controller.

Once you have made the necessary changes, the code can be started from the Main Class. If everything is working properly, you should see the login page, which allows two types of users. To connect as an admin, you will need to write "admin" in both the faculty number and egn fields. For students, all information is based in the MySQL database.

That's a brief overview of the project. If you encounter any bugs or need more information, please feel free to contact me at stanislav2177@gmail.com.

![login](https://user-images.githubusercontent.com/91600823/233652542-c4671aa7-ea86-474b-a1e1-ee5b4f617a1d.png)
