# Fantasy_Coin_League_Server
Built by Andrew Aslakson, Michael Chau, Cole Paris, and Heather Zrinsky.

Spring based project created in revature batch 211101 java-react-enterprise curriculum.

This project is a crypto-trading simulator that puts users in direct competition with each other to see who can gain the most given an initial starting value.

This server should run with the FCL-React client application linked below.

Github Link: https://github.com/ThiccWalletCo/FCL-React

--------------------------------------------

### Necessary Setup

Before running this application it is necessary to first download the CryptoORM_p1 project and install it using maven.

You must also add a file called "db.properties" to "/src/main/resources/" with the following information about the database to be used:
<pre>
url={URL to database here}
username={username}
password={password}
</pre>

Included in the "/src/main/resources/" is a file called "crypto_wallet_table_instantiation.sql" which is a script that can be run on a database schema to set up the database tables and create some testing data for the application.

This application was built to run on a tomcat server so you will need a tomcat installation and you will need to install the war after starting the tomcat server by running the command "mvn tomcat7:deploy" in gitbash from the project directory.

--------------------------------------------

## Exposed Endpoints

### localhost:8080/crypto/register

POST: Registers a new user, expects Json in the following format:
<pre>
{
    "username":"entered_username",
    "password":"entered_password",
    "firstName":"entered_name",
    "lastName":"entered_name"
}
</pre>

DELETE: Deletes a currently logged in users account.
