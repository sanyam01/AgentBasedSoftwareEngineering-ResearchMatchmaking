# AgentBasedSoftwareEngineering-ResearchMatchmaking

**Research Matchmaking**

_A SENG 696 Sample Project_

**Requirements:**

1. Ability to sign up as Provider and Client
2. Ability to be a Guest and visit the app
3. For Providers: ability to submit name, website, logo, resume, special keywords, hourly compensation
4. Providers can get a verified icon if they send their proof of business to the system. The system should make sure that every piece of information is correct and then
accept the request
5. For Guests: ability to search keywords and get a list of available Providers
6. A contract should be sent to a Provider the moment they sign up
7. Provider should be able to accept or reject the contract
8. Upon rejection, the Provider will be automatically converted to a Client, losing their resume, website, special keywords and hourly compensation information
9. When a Guest visits the app, they can only see the name, website, logo, resume, and special keywords of Providers. They cannot see their hourly compensation. Also, they cannot place a bid for the Provider
10. Signed-up Agents (Providers and Clients) can see every piece of information available on the system
11. Providers can choose between Basic and Premium plans. Premium subscribers will appear first on the search list, regardless of their approval ratings or hourly compensation
12. The sorting algorithm always puts Premium Providers on top, then verified Providers, and then the rest. Between each group, Providers should be sorted based on their approval ratings by default (can be changed)
13. A Client is able to change the sorting of results upon searching a keyword (e.g. Clients’ approvals, number of projects done, the amount of hourly compensation)
14. A Client can request a Provider and place a bid. The bid can be a different value that the hourly compensation of the Provider
15. Provider can accept or reject a bid
16. A rejection from the Provider will be directly sent to the Client
17. Accepting a request from the Provider will go through the system first, and not directly to the Client
18. The system, upon receiving an accept confirmation, will pull up a contract and send it to both Provider and Client
19. Provider and Client can accept or reject the contract
20. Any money transfer will be handled by the system. The system will receive 30% of any transaction. This info should be in the contract
21. Ability to watch the progress of the project for the both sides (Provider and Client)
22. The tracking page will show the tentative deadline, progress so far, and estimated time of completion based on the current pace
23. A chat room page will be created for Client and Provider once a project gets accepted
24. Any change request from the Client must first get accepted by the Provider after a project begins. Deadlines could change based on Provider’s request
25. When a project is done, the Client can leave a comment and rating for the Provider
26. A Provider can also leave a comment and rating for the Client. Providers can see the past ratings of a Client when there is a new bid
27. The app must have a GUI
