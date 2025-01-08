CREATE TABLE IF NOT EXISTS users (
	username TEXT NOT NULL,
	password TEXT NOT NULL,

	PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS thresholds (
	threshold_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	points_3 NUMERIC NOT NULL,
	points_3_5 NUMERIC NOT NULL,
	points_4 NUMERIC NOT NULL,
	points_4_5 NUMERIC NOT NULL,
	points_5 NUMERIC NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
	course_name TEXT NOT NULL,
	owned_by_user TEXT NOT NULL,
	threshold_id INTEGER NOT NULL,
	min_points NUMERIC NOT NULL,
	current_points NUMERIC NOT NULL,
	min_labs_points NUMERIC,
	min_tests_points NUMERIC,
	min_projects_points NUMERIC,
	min_exams_points NUMERIC,

	PRIMARY KEY (course_name, owned_by_user),
	
	FOREIGN KEY (owned_by_user) REFERENCES users(username) ON DELETE CASCADE,
	FOREIGN KEY (threshold_id) REFERENCES thresholds(threshold_id) ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS tests (
	course_name TEXT NOT NULL,
	owned_by_user TEXT NOT NULL,
	test_number INTEGER NOT NULL,
	description TEXT NOT NULL,
	min_points NUMERIC NOT NULL,
	user_points NUMERIC NOT NULL,
	max_points NUMERIC NOT NULL,
	date DATE NOT NULL,
	is_exam BOOLEAN NOT NULL DEFAULT FALSE,

	PRIMARY KEY (course_name, owned_by_user, test_number),
	FOREIGN KEY (course_name, owned_by_user) REFERENCES courses(course_name, owned_by_user) ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS projects (
	course_name TEXT NOT NULL,
	owned_by_user TEXT NOT NULL,
	project_number INTEGER NOT NULL,
	description TEXT NOT NULL,
	min_points NUMERIC NOT NULL,
	user_points NUMERIC NOT NULL,
	max_points NUMERIC NOT NULL,
	deadline DATE,

	PRIMARY KEY (course_name, owned_by_user, project_number),
	FOREIGN KEY (course_name, owned_by_user) REFERENCES courses(course_name, owned_by_user) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS labs (
	course_name TEXT NOT NULL,
	owned_by_user TEXT NOT NULL,
	lab_number INTEGER NOT NULL,
	description TEXT NOT NULL,
	min_points NUMERIC NOT NULL,
	user_points NUMERIC NOT NULL,
	max_points NUMERIC NOT NULL,
	date DATE,
	deadline DATE,

	PRIMARY KEY (course_name, owned_by_user, lab_number),
	FOREIGN KEY (course_name, owned_by_user) REFERENCES courses(course_name, owned_by_user) ON DELETE CASCADE
);
