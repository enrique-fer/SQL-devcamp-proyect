package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Used XAMPP and phpMyAdmin with MySql
public class BBDD {	
	private final String drive = "jdbc:mysql://localhost:3306/";
	private final String NOMBRE = "university";
	private final String TABLE_PROF = "CREATE TABLE professors ( "
			+ "id INT AUTO_INCREMENT, name VARCHAR(50), "
			+ "last_name VARCHAR(100), salary DOUBLE, "
			+ "department VARCHAR(50), "
			+ "CONSTRAINT professors_PK PRIMARY KEY(id) "
			+ ");";

	private final String TABLE_STUD = "CREATE TABLE students ( "
			+ "id INT AUTO_INCREMENT, name VARCHAR(50), "
			+ "last_name VARCHAR(100), "
			+ "CONSTRAINT students_PK PRIMARY KEY(id) "
			+ ");";
	
	private final String TABLE_COURSES = "CREATE TABLE courses ( "
			+ "id INT, subject VARCHAR(50), "
			+ "id_professor INT NOT NULL, "
			+ "CONSTRAINT courses_PK PRIMARY KEY(id, id_professor), "
			+ "CONSTRAINT courses_prof_FK FOREIGN KEY(id_professor) REFERENCES professors(id) "
			+ ");";
	
	private final String TABLE_GRADES = "CREATE TABLE grades ( "
			+ "id_student INT, id_course INT, "
			+ "grade DOUBLE, "
			+ "CONSTRAINT grades_PK PRIMARY KEY(id_student, id_course), "
			+ "CONSTRAINT grades_student_FK FOREIGN KEY(id_student) REFERENCES students(id), "
			+ "CONSTRAINT grades_course_FK FOREIGN KEY(id_course) REFERENCES courses(id),"
			+ "CONSTRAINT grades_grade_value CHECK (grade BETWEEN 0 AND 10)"
			+ ");";
	
	private Connection db;

	public BBDD() {
		if (!conect()) {
			createDB();
			conect();
			createTables();
		}
	}

	private boolean conect() {
		try {
			db = DriverManager.getConnection(drive + this.NOMBRE, "root", "root");

			return true;
		} catch (SQLException sqle) {

			return false;
		}
	}

	private void createDB() {
		try {
			db = DriverManager.getConnection(drive, "root", "root");
			PreparedStatement ps = db.prepareStatement("CREATE DATABASE " + this.NOMBRE);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createTables() {
		try {
			createTableProf();
			createTableStud();
			createTableCourses();
			createTableGrades();
			
			populate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createTableProf() throws SQLException {
		PreparedStatement ps = db.prepareStatement(TABLE_PROF);
		ps.executeUpdate();
		ps.close();
	}
	private void createTableStud() throws SQLException {
		PreparedStatement ps = db.prepareStatement(TABLE_STUD);
		ps.executeUpdate();
		ps.close();
	}
	private void createTableCourses() throws SQLException {
		PreparedStatement ps = db.prepareStatement(TABLE_COURSES);
		ps.executeUpdate();
		ps.close();
	}
	private void createTableGrades() throws SQLException {
		PreparedStatement ps = db.prepareStatement(TABLE_GRADES);
		ps.executeUpdate();
		ps.close();
	}
	
	private void populate() throws SQLException {
		String sql = "";
		PreparedStatement ps;
		//Table professors
		sql = "INSERT INTO professors (name, last_name, salary, department) "
				+ "VALUES ('Enrique', 'Fernandez', 2568, 'Science')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO professors (name, last_name, salary, department) "
				+ "VALUES ('Ana I', 'Santiago', 2820, 'Languages')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO professors (name, last_name, salary, department) "
				+ "VALUES ('Antionio', 'Sousa', 1841, 'Techonolgy')";
		ps = db.prepareStatement(sql);
		ps.execute();

		sql = "INSERT INTO professors (name, last_name, salary, department) "
				+ "VALUES ('Pepe', 'Ruiz', 1987, 'Art')";
		ps = db.prepareStatement(sql);
		ps.execute();
	
		sql = "INSERT INTO professors (name, last_name, salary, department) "
				+ "VALUES ('Begona', 'Fernandez', 2354, 'History')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		
		//Table students
		sql = "INSERT INTO students (name, last_name) VALUES ('Lara', 'Ramos')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO students (name, last_name) VALUES ('David', 'Isla')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO students (name, last_name) VALUES ('Maria', 'Gonzalez')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO students (name, last_name) VALUES ('Ierai', 'Galindo')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO students (name, last_name) VALUES ('Raquel', 'Garcia')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO students (name, last_name) VALUES ('Cesar', 'Diaz')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO students (name, last_name) VALUES ('Martin', 'Sanchez')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO students (name, last_name) VALUES ('Dario', 'Morales')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO students (name, last_name) VALUES ('Ana', 'Martinez')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO students (name, last_name) VALUES ('Carmen', 'Lopez')";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		
		//Table courses
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('12','MATH I', 1)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('13','MATH II', 1)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('4','SPANISH', 2)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('5','ENGLISH', 2)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('21','GRAPHIC DESIGN', 4)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('18','ROBOTICS', 3)";
		ps = db.prepareStatement(sql);
		ps.execute();	
		
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('7','TECH TROUGH THE AGES', 5)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('11','FISICS', 1)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('20','3D DESIGN', 4)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('22','ENVIRONMENTAL MODELING ', 4)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO courses (id, subject, id_professor) "
				+ "VALUES ('8','WORLD HISTORY', 5)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		
		
		//Table grades
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (4, 1, 9)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (8, 1, 7.51)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (12, 1, 10)";
		ps = db.prepareStatement(sql);
		ps.execute();	
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (13, 1, 9.36)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (22, 1, 10)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (4, 2, 8.2)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (5, 2, 7)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (7, 2, 8)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (11, 2, 10)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (12, 2, 8.24)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (18, 2, 9.75)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (20, 2, 8)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (21, 3, 8.3)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (22, 3, 6.58)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (4, 3, 7.94)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (8, 3, 8)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (11, 3, 9)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (18, 3, 7)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (5, 4, 7.85)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (7, 4, 7)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (8, 4, 7)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (12, 4, 9.2)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (11, 4, 8.98)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (18, 4, 10)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (4, 5, 7.63)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (11, 5, 6.87)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (12, 5, 5.32)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (13, 5, 7.54)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (21, 5, 8.21)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (4, 6, 7.84)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (5, 6, 6)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (8, 6, 5.21)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (20, 6, 4.8)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (22, 6, 5.1)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (5, 7, 8.26)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (13, 7, 5.47)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (18, 7, 6.95)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (21, 7, 8.5)";
		ps = db.prepareStatement(sql);
		ps.execute();
	
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (12, 8, 6.58)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (20, 8, 5.26)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (21, 8, 4.21)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (22, 8, 8.6)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (5, 9, 3.52)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (8, 9, 7.69)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (18, 9, 9.63)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (22, 9, 6.26)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (4, 10, 8.25)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (7, 10, 7.89)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (11, 10, 9.21)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (12, 10, 5.26)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (18, 10, 8.57)";
		ps = db.prepareStatement(sql);
		ps.execute();
		
		sql = "INSERT INTO grades (id_course, id_student, grade) "
				+ "VALUES (20, 10, 9)";
		ps = db.prepareStatement(sql);
		ps.execute();
	}
}