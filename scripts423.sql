SELECT s.name, s.age, f.name AS faculty_name
FROM students s
JOIN faculties f ON s.faculty_id = f.id;

SELECT s.name, s.age, a.image_url
FROM students s
JOIN avatars a ON s.id = a.student_id;