1. Create a table in the database.

CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    phone INT
);


2. Add data to the database.

INSERT INTO contacts (name, phone) VALUES ('Абдула', 806347530);
INSERT INTO contacts (name, phone) VALUES ('Ашот', 806323475);
INSERT INTO contacts (name, phone) VALUES ('Арсен', 8063173345);
INSERT INTO contacts (name, phone) VALUES ('Любомир', 809347530);

REST

1.GET
URL http://localhost:8080/
endpoint: api/v.1/contacts
response: All contacts;

technology stack: JAX-RS, Jersey, SQL.

2.POST
URL http://localhost:8080/
endpoint: api/v.1/contacts/{body}
Body:
{
    "name": "Вася",
    "phone": 94949494
}
response: ?

3.PUT
URL: http://localhost:8080/
endpoint: api/v.1/contacts/{id}
response: ?

4.@DELETE
URL: http://localhost:8080/
endpoint: api/v.1/contacts/{id}
response: ?



