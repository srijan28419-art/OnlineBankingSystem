# OnlineBankingSystem — Spring Boot + Thymeleaf

A runnable **educational demo** banking application with a responsive interface.

## Requirements
- Java 17+ (Java 21 works)
- Apache Maven 3.9+

## Run
```bash
mvn spring-boot:run
```
Visit **http://localhost:8080**.

## Demo logins
| Username | Password | Account | Starting balance |
|---|---|---|---|
| alice | Demo@123 | 1000000001 | NPR 25,000 |
| bob | Demo@123 | 1000000002 | NPR 18,000 |
| charlie | Demo@123 | 1000000003 | NPR 12,000 |

## Features
- Spring Security form login, BCrypt password hashes, CSRF protection and logout
- Personal dashboard with balance and recent transactions
- Money transfer between demo accounts with balance validation
- Transaction history (incoming and outgoing)
- Persistent local H2 file database (`./data/bankdb`)
- Row locking and transactional debit/credit operations
- Responsive Thymeleaf/CSS UI, no Node.js required

## Import in IntelliJ
Open the extracted project folder and select **Open as Maven Project**. Wait for Maven dependencies to download. Run `OnlineBankingApplication.java` or `mvn spring-boot:run`.

## Reset demo data
Stop the app, delete the `data` folder, and restart.

## IMPORTANT: NOT FOR REAL BANKING
This is a classroom prototype, not production banking software. It has no actual bank/payment integration, regulatory compliance, MFA, fraud monitoring, immutable audit ledger, rate limiting, production secret management, or comprehensive automated testing. The seed accounts use publicly documented demo passwords. Do not deploy to the public internet or use real money or personal financial data.
