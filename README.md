# Hybrid Recommendation System

## Install (Linux)

```bash
> chmod +x startConfig.sh
> ./startConfig.sh
```

- Create a database in PostgreSQL with name **srh**
- Insert the content of data.sql on database:

```sql
INSERT INTO user_api(id, name, login, password, profile) VALUES (1, 'api', 'api', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', 'API');
```

Use the user **api** with password **123456** to generate the token for api