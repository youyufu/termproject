# MedBay
MedBay is a medication tracking program designed to replicate physical weekly medication organizers with added functionality. 
Users add medications, including the name, dose size, dose unit, inventory, weekly schedule, and additional comments. 

This allows them to see their information presented in a table, as well as a built-in daily checklist that updates 
their information in real time when interacted with. 

Additional functionalities include a drug-drug interaction checker, low inventory list, and restocking reminders. 
To delete a medication, all the user needs to do is enter its name and press delete on the delete medication tab.
The project was built with Java version 17.0.9 and uses the external Maven library org.json:json:20231013.

This is the link to the HealthData Drug Interaction API documentation: https://lhncbc.nlm.nih.gov/RxNav/APIs/InteractionAPIs.html
NOTE: the API has been shut down as of March 2024. An alternative API is needed to maintain the drug interaction checking functionality.

This is the link to the RxNorm API documentation: https://lhncbc.nlm.nih.gov/RxNav/APIs/api-RxNorm.findRxcuiByString.html
