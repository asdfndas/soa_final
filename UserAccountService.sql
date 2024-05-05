select * from public.detail_library_studyset

select * from public.detail_notification_user

select * from public.notification

select * from public.user_role

select * from public.user

select noti.* from notification noti join detail_notification_user d on d.notification_id = noti.notification_id join public.user u on d.user_id = u.user_id where u.user_id = 1

select r.* from public.user_role r join public.user u on r.user_role_id = u.user_role_id where u.email = 'example1@gmail.com'

delete from public.detail_library_studyset d where d.study_id = 3 and d.user_id = 3

-- Insert data to public.user_role
INSERT INTO public.user_role (created_at, role_name) 
VALUES ('2024-05-05', 'Student');

INSERT INTO public.user_role (created_at, role_name) 
VALUES ('2024-05-05', 'Student');

INSERT INTO public.user_role (created_at, role_name) 
VALUES ('2024-05-05', 'Student');

INSERT INTO public.user_role (created_at, role_name) 
VALUES ('2024-05-05', 'Student');

INSERT INTO public.user_role (created_at, role_name) 
VALUES ('2024-05-05', 'Teacher');

INSERT INTO public.user_role (created_at, role_name) 
VALUES ('2024-05-05', 'Teacher');

INSERT INTO public.user_role (created_at, role_name) 
VALUES ('2024-05-05', 'Teacher');

INSERT INTO public.user_role (created_at, role_name) 
VALUES ('2024-05-05', 'Teacher');

INSERT INTO public.user_role (created_at, role_name) 
VALUES ('2024-05-05', 'Teacher');

INSERT INTO public.user_role (created_at, role_name) 
VALUES ('2024-05-05', 'Teacher');

-- Insert data to public.user
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-01', 'example1@gmail.com', 'John', 'Male', FALSE, 'leduy1', 'Doe', 1);

INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-02', 'example2@gmail.com', 'Jane', 'Female', TRUE, 'leduy2', 'Smith', 2);

INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-03', 'example3@gmail.com', 'Michael', 'Male', TRUE, 'leduy3', 'Johnson', 3);

INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-04', 'example4@gmail.com', 'Emily', 'Female', FALSE, 'leduy4', 'Brown', 4);

INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-05', 'example5@gmail.com', 'David', 'Male', TRUE, 'leduy5', 'Wilson', 5);

INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-06', 'example6@gmail.com', 'Emma', 'Female', FALSE, 'leduy6', 'Martinez', 6);

INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-07', 'example7@gmail.com', 'Christopher', 'Male', TRUE, 'leduy7', 'Anderson', 7);

INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-08', 'example8@gmail.com', 'Olivia', 'Female', FALSE, 'leduy8', 'Taylor', 8);

INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-09', 'example9@gmail.com', 'William', 'Male', TRUE, 'leduy9', 'Thomas', 9);

INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 10);

INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 11);
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 12);
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 13);
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 14);
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 15);
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 16);
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 17);
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 18);
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 19);
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 20);
INSERT INTO public.user (date_of_birth, email, first_name, gender, is_teacher, last_name, user_name, user_role_id) 
VALUES ('1990-01-10', 'example10@gmail.com', 'Sophia', 'Female', FALSE, 'leduy10', 'Hernandez', 21);

-- Insert data to public.notification
INSERT INTO public.notification (content, description, study_id, user_id) 
VALUES ('Nội dung thông báo 1', 'Mô tả thông báo 1', 1, 1);

INSERT INTO public.notification (content, description, study_id, user_id) 
VALUES ('Nội dung thông báo 2', 'Mô tả thông báo 2', 2, 1);

INSERT INTO public.notification (content, description, study_id, user_id) 
VALUES ('Nội dung thông báo 3', 'Mô tả thông báo 3', 3, 1);

INSERT INTO public.notification (content, description, study_id, user_id) 
VALUES ('Nội dung thông báo 4', 'Mô tả thông báo 4', 4, 2);

INSERT INTO public.notification (content, description, study_id, user_id) 
VALUES ('Nội dung thông báo 5', 'Mô tả thông báo 5', 5, 2);

INSERT INTO public.notification (content, description, study_id, user_id) 
VALUES ('Nội dung thông báo 6', 'Mô tả thông báo 6', 6, 3);

INSERT INTO public.notification (content, description, study_id, user_id) 
VALUES ('Nội dung thông báo 7', 'Mô tả thông báo 7', 7, 3);

INSERT INTO public.notification (content, description, study_id, user_id) 
VALUES ('Nội dung thông báo 8', 'Mô tả thông báo 8', 8, 4);

INSERT INTO public.notification (content, description, study_id, user_id) 
VALUES ('Nội dung thông báo 9', 'Mô tả thông báo 9', 9, 4);

INSERT INTO public.notification (content, description, study_id, user_id) 
VALUES ('Nội dung thông báo 10', 'Mô tả thông báo 10', 10, 5);

-- Insert data to public.detail_notification_user

INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (1, 1);
INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (1, 2);
INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (1, 3);
INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (1, 4);
INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (2, 1);
INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (2, 2);
INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (2, 3);
INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (2, 4);
INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (3, 1);
INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (3, 2);
INSERT INTO public.detail_notification_user (notification_id, user_id) 
VALUES (3, 3);

-- Insert data to public.detail_library_studyset
INSERT INTO public.detail_library_studyset (study_id, user_id) 
VALUES (3, 1);
INSERT INTO public.detail_library_studyset (study_id, user_id) 
VALUES (3, 1);
INSERT INTO public.detail_library_studyset (study_id, user_id) 
VALUES (3, 2);
INSERT INTO public.detail_library_studyset (study_id, user_id) 
VALUES (3, 4);
INSERT INTO public.detail_library_studyset (study_id, user_id) 
VALUES (2, 3);
INSERT INTO public.detail_library_studyset (study_id, user_id) 
VALUES (3, 4);
INSERT INTO public.detail_library_studyset (study_id, user_id) 
VALUES (3, 3);
INSERT INTO public.detail_library_studyset (study_id, user_id) 
VALUES (10, 4);