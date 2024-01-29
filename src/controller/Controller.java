package controller;

import model.Student;
import model.Teacher;
import view.LoginMenu;
import view.MainMenu;
import view.StudentMenu;
import view.TeacherMenu;

import javax.print.attribute.standard.NumberUp;

public class Controller {
    private Student loggedInStudent;
    private Teacher loggedInTeacher;
    private final LoginMenu loginMenu;
    private final MainMenu mainMenu;
    private final TeacherMenu teacherMenu;
    private final StudentMenu studentMenu;

    public Controller() {
        loginMenu = new LoginMenu(this);
        mainMenu = new MainMenu(this);
        teacherMenu = new TeacherMenu(this);
        studentMenu = new StudentMenu(this);
    }

    public boolean isLoggedInUserStudent() {
        if (loggedInStudent == null) {
            return false;
        } else {
            return true;
        }
    }

    public void run() {
        if (loginMenu.run().equals("exit")) {
            return;
        } else {
            while (true) {
                switch (mainMenu.run()) {
                    case "teacher menu":
                        teacherMenu.run();
                        break;
                    case "student menu":
                        studentMenu.run();
                        break;
                    case "logout":
                        if (loginMenu.run().equals("exit")) {
                            return;
                        }
                        break;
                }
            }
        }

    }

    public String register(String username, String password, String role) {
        if (Teacher.getTeacherByUsername(username) != null || Student.getStudentByUsername(username) != null) {
            return "register failed: username already exists";
        }
        if (password.length() < 5 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*[0-9].*")) {
            return "register failed: password is weak";
        }
        if (role.equals("teacher")) {
            Teacher.addTeacher(username, password);
        } else if (role.equals("student")) {
            Student.addStudent(username, password);
        } else {
            return "register failed: invalid role!";
        }
        return "register successful";
    }
    public String login(String username,String password){

    }


}

