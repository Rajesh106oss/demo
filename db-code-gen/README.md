# jOOQ Code Generator
jOOQ Code Generator generates the jOOQ based database classes that contains classes for tables, sequences, etc.
The following command can be used to generate the code.

## Commands
### codegen
`gradle codeGen -PdatabaseName={database-name} -PmoduleName={module-name}`
### migration
`pg_dump -U postgres -T flyway_schema_history -T oauth_* -s -f schema.sql <database-name>`
