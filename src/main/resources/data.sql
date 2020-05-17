INSERT INTO api_user(id, name, login, password, profile, is_admin)
    VALUES (1, 'admin', 'admin', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq',
        'API', true);

INSERT INTO api_user(id, name, login, password, profile, is_admin)
    VALUES (2, 'client', 'client', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq',
        'API', true);

INSERT INTO profile(id, name) VALUES(1, 'ROLE_ADMIN');
INSERT INTO profile(id, name) VALUES(2, 'ROLE_USER');

INSERT INTO api_user_profiles(api_user_id, profiles_id)
	VALUES (1, 1);

INSERT INTO api_user_profiles(api_user_id, profiles_id)
	VALUES (1, 2);

INSERT INTO api_user_profiles(api_user_id, profiles_id)
	VALUES (2, 2);