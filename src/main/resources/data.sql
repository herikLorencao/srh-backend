INSERT INTO api_user(id, name, login, password, profile)
    VALUES (1, 'api', 'api', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', 'API');

INSERT INTO profile(id, name) VALUES(1, 'ROLE_ADMIN');

INSERT INTO api_user_profiles(api_user_id, profiles_id)
	VALUES (1, 1);