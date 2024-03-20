-- admin username: potter@gmail.com
-- admin password: testHotel1234!
INSERT INTO app_user (
    user_id,
    creation_date,
    email,
    first_name,
    last_modified_date,
    last_name,
    password,
    extra_info
)
VALUES
    (1, '2024-01-05 20:11:38.192771', 'potter@gmail.com', 'Harry', '2024-01-05 20:11:38.192771', 'Potter', '$2a$10$zr3JyxnPloSvyPm5URT9X.bTw8Nk7DSPLTVO0JvUGKcki95l6scBK', null),
    (2, '2024-01-05 20:12:00.169250', 'hermione@gmail.com', 'Hermione', '2024-01-05 22:21:53.368433', 'Granger', '$2a$10$d/z8IWldvvYsxaPsYWWqq.NmkLxBaL/GuLzf84Jb/iykm1rP9ef7C', 'extra'),
    (3, '2024-01-05 23:12:34.226064', 'ron@gmail.com', 'Ronald', '2024-01-05 23:12:34.226064', 'Weasley', '$2a$10$1BqL6LYrR2DCQ8ZCRbOzKOhNmoB/GaVVuQUucDG/DD992hvUhxy1a', null);