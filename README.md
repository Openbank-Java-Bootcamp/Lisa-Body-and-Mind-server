# Body & Mind App - Server

## Set Up

1. Clone or Download the project from the repository.

2. Open the directory as a project on a IDE as IntelliJ.

3. Create the databases `user_service_db` and `training_service_db` in your SQL Workbench.

4. Run the *ConfigServiceApplication.java*, *DiscoveryServiceApplication.java*, *ApiGateway.java*,  *TrainingServiceApplication.java* and the *UserServiceApplication.java*

5. After running, check the tables are correctly created, and import the `api-ninja-data.csv` in the exercise_type table.

<br/>

### Technologies Used

<img src="https://cdn.jsdelivr.net/npm/programming-languages-logos/src/java/java_256x256.png" width=50> Java   <img src="https://raw.githubusercontent.com/docker-library/docs/c408469abbac35ad1e4a50a6618836420eb9502e/mysql/logo.png" width=60> MySQL <img src="https://user-images.githubusercontent.com/33158051/103466606-760a4000-4d14-11eb-9941-2f3d00371471.png" width=80> SpringBoot <img src="https://miro.medium.com/max/300/1*uOLtvuo9wxHXyETP_c085A.png" width=60> Microservices

<br/>

## Routes

| Route  | Method | Access |
| ------------- | ------------- | ------------- |
| /api/accounts/admin/status/{id}  | PATCH  | Admin  |
| /api/accounts/admin/balance/{id}  | PATCH  | Admin  |
| /api/accounts/admin/delete/{id}  | DELETE  | Admin  |
| /api/accounts/balance/{id}  | GET  | Admin & Account Holder  |
| /api/account-holders/admin/new  | POST  | Admin  |
| /api/account-holders/admin/delete/{id}  | DELETE  | Admin  |
| /api/admins/admin/new  | POST  | Admin  |
| /api/checking-accounts/admin/new  | POST  | Admin  |
| /api/credit-card-accounts/admin/new  | POST  | Admin  |
| /api/credit-card-accounts/{id}  | GET  | Admin & Account Holder  |
| /api/savings-accounts/admin/new  | POST  | Admin  |
| /api/savings-accounts/{id}  | GET  | Admin & Account Holder  |
| /api/student-checking-accounts/admin/new  | POST  | Admin  |
| /api/third-parties/admin/new  | POST  | Admin  |
| /api/transactions/account-holder/new  | POST  | Admin & Account Holder  |
| /api/transactions/third-party/new  | POST  | third party (key header HTTP request)  |


<br/>

## Future Work

- Dividing the training service in 7 small services (program/workout/exercise/set/rep)

- Adding fitbit api to track user personal health stats to  further improve their wellbeing


<br/>

## Resources

[Trello](https://trello.com/b/xrk45zcW/bodymind)

[Auth0 Api Authorization guide](https://auth0.com/docs/quickstart/backend/java-spring-security5/interactive)

[Auth0 Microservices ApiGateway Implementation eg](https://auth0.com/blog/apigateway-microservices-superglue/)
