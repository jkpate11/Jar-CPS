# Automatic Refill System for Jars

This project is a web application developed using Java as the backend and HTML/CSS for the frontend. It simulates an automatic refill system for jars, implementing principles of Cyber-Physical Systems (CPS). The system allows users to track the quantity of items in a jar and receive notifications when the quantity falls below a certain threshold. It also enables users to adjust the quantity of items in the jar.

## Cyber-Physical System (CPS) Aspects

This project exhibits characteristics of Cyber-Physical Systems (CPS) through the following features:

1. **Interacting with the Environment**: The system interacts with the physical world by tracking the quantity of items stored in the jar and making decisions based on this information.

2. **Reactive Computation**: The system reacts to changes in the environment, such as the quantity of items in the jar falling below a certain threshold, by triggering notifications and refilling actions.

3. **Concurrency**: The system handles multiple processes simultaneously, such as tracking multiple jars and interacting with the same backend concurrently.

4. **Real-Time Computation**: Real-time updates are provided to users, ensuring timely notifications and accurate representation of the jar's quantity.

5. **Safety-Critical Considerations**: The system deals with critical user data, such as home addresses and consumption patterns, requiring robust security measures to safeguard user privacy.


## Technologies Used

- Java
- Spring Boot
- HTML
- CSS

## Getting Started

To run the project locally, follow these steps:

1. Clone this repository to your local machine.
2. Navigate to the project directory.
3. Run the backend server using Maven: `mvn spring-boot:run`.
4. Open the `index.html` file in your web browser to access the frontend.
