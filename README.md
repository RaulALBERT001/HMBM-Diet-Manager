# HMBM-Diet-Manager

The porpouse of this project is to create an Diet manager MVP(mainly for educational purposes, this is important to say), this particullar repository concearns only to the backend of the project, since backend and frontend will be separated between two different repositories.

# How do i run this project?

See it on the EXPLANATION.md

#Product Requirement Document(PRD)

To get a better "overview" about the project, read the PRD below:



## 1. Project Overview

**Project Title:** Diet Manager MVP

**Status:** Early-stage / Concept Validation

**Target Release:** [TBD – Recommend starting with a closed beta within 3–6 months]

**Participants & Roles:**

- **Product Owner:** Raul Sergio Alberti
- **Development Lead:** Raul Sergio Alberti
- **UI/UX Designer:** Raul Sergio Alberti
- **QA/Test Engineer:** Raul Sergio Alberti
- **Stakeholders:** Raul Sergio Albert, potential early users (health enthusiasts, nutritionists)

---

## 2. Business Goals & Objectives

**Business Goals:**

- Validate the core concept of a diet management tool with a focus on ease of use and personalization.
- Establish product-market fit by tracking early user behaviors and feedback.
- Lay the groundwork for a scalable and modular architecture for later features and integrations.(such as using ai to create diets, based on especific parameters, for example: Vegan diet with x macros of protein, and x macros of carb , with growing muscle mass goal)w


**Objectives:**

- Provide users with an intuitive interface to set dietary goals, plan meals, and track their nutritional intake.
- Use user data to offer personalized insights (calories, macronutrients, food recommendations).
- Ensure secure user authentication and data storage with modern technologies.

---

## 3. Background and Strategic Fit

**Why Build It?**

The rise in health awareness and diet-related issues has created a demand for simple, yet effective, tools that help users manage their eating habits. Diet Manager addresses:

- **Personalized Diet Tracking:** Users often struggle to keep track of their nutritional intake. This tool provides a centralized way to log daily meals.
- **Goal Management:** It helps users set goals (weight loss, muscle gain, maintenance) and monitor progress.
- **User-Friendly Interface:** By offering a modern, responsive UI, the product aims to reduce the friction many users face with complex diet apps.
- **Ai Diet Generation**: users can generate a diest based on specific parameters given by the user

**Strategic Fit:**

This MVP will serve as the foundation to expand into a comprehensive diet and wellness platform that could eventually include additional integrations (e.g., wearable devices, recipe suggestions, community features).

---

## 4. Assumptions

- **Target Users:** Individuals interested in improving their diet, fitness enthusiasts, and users who want easy tracking of nutritional intake.
- **Market Readiness:** Users are accustomed to using any kinds of application for health and diet management.
- **Data Privacy:** Users expect secure handling of personal and nutritional data.
- **Tech Readiness:** The chosen technology stack (Spring Boot, React, SQL, JWT, Redux) is deemed sufficient to support rapid development and scalability.
- **Development Process:** The project will follow agile methodologies, with iterative development cycles and regular stakeholder reviews.

---

## 5. Core Features & User Stories

### 5.1. User Management & Authentication

- **Feature:** User Registration & Login
    - **Description:** Allow users to register with email or social login, leveraging JWT for secure authentication.
    - **User Story:** *“As a new user, I want to register and securely log in so that I can access my personalized diet data.”*
- **Feature:** Profile Management
    - **Description:** Users can update their personal details, dietary preferences, and goals.
    - **User Story:** *“As a logged-in user, I want to update my profile information so that my diet recommendations are accurately tailored.”*

### 5.2. Dashboard & Data Visualization

- **Feature:** Overview Dashboard
    - **Description:** A user-friendly landing page summarizing daily/weekly progress including calories, macros, and goal tracking.
    - **User Story:** *“As a user, I want to view a daily dashboard that visualizes my nutritional intake and progress towards my diet goals.”*

### 5.3. Meal Planning & Tracking

- **Feature:** Meal Logging
    - **Description:** Users can log meals (breakfast, lunch, dinner, snacks) with options to search or manually input foods.
    - **User Story:** *“As a user, I want to log my meals quickly so that I can track my daily nutritional intake.”*
- **Feature:** Nutrient Breakdown and Analysis
    - **Description:** Automatic calculation of calories, macronutrients, and micronutrients for each logged meal.
    - **User Story:** *“As a user, I want to see a detailed breakdown of the nutritional content of my meals so I can adjust my diet if needed.”*

### 5.4. Goal Setting & Recommendations

- **Feature:** Personalized Diet Goals
    - **Description:** Users can set their dietary goals (e.g., weight loss, muscle gain, maintenance) and track progress.
    - **User Story:** *“As a user, I want to set and adjust my diet goals so that the system can suggest tailored meal plans.”*
- **Feature:** Food Recommendations (Future Iteration)
    - **Description:** Using stored data and possibly third-party APIs, the app could recommend recipes and foods that meet user dietary restrictions.
    - **User Story:** *“As a user, I want to receive meal recommendations based on my dietary preferences so I can discover new healthy recipes.”*

### 5.5. Reporting and Analytics

- **Feature:** Weekly/Monthly Reports
    - **Description:** Generate summary reports on dietary intake, nutrient consumption, and progress trends.
    - **User Story:** *“As a user, I want to view reports of my dietary progress over time to understand how I’m progressing toward my goals.”*

---

## 6. Technical & Integration Considerations

**Backend (Spring Boot REST API):**

- Expose secure endpoints for registration, meal logging, and data retrieval.
- Use JWT with Spring Security for authentication.
- Containerize the application for scalable deployment (e.g., Docker).

**Frontend (React.js SPA):**

- Develop a responsive UI that communicates with the backend API.
- Use Redux for state management, especially for user sessions and data flow.
- Separate build and deployment streams for frontend (static hosting) and backend (containerized deployment).

**Database (SQL):**

- Schema design to support user profiles, meals, nutritional data, and progress logs.
- Implement strong data integrity checks and backup routines.

**Deployment & Scalability:**

- Separate deployments enable easier scaling and maintenance.
- Use CI/CD pipelines (integrated with GitHub) for automated builds, tests, and deployments.

---

## 7. User Interaction and Design Considerations

- **Wireframes & Mockups:** Start with low-fidelity sketches focusing on the dashboard, meal logging interface, and profile screens. Early user testing sessions (even with a few friends or target users) can provide insights.
- **UX Flow:** Ensure that logging in, meal tracking, and dashboard navigation are streamlined with minimal friction.
- **Visual Style:** Clean and modern UI inspired by health-tracking apps. Consistent visual hierarchy (colors, typography) that emphasizes important actions and data visualizations.

---

## 8. Open Questions & Research Items

| **Open Question** | **Notes/Action Items** |
| --- | --- |
| How granular should the meal logging be? | Decide whether to support portion sizes, ingredient-level detail, etc. |
| Third-party integrations | Should we integrate public food databases or nutritional APIs from the start? |
| Offline functionality | Consider if the MVP will support offline mode or deferred syncing. |
| Data Privacy Compliance | Confirm requirements (e.g., GDPR, HIPAA if targeting specific regions) for handling nutritional data. |

---

## 9. Out of Scope (Initial MVP)

- **Social & Community Features:** No friend networks, messaging, or public sharing in the first version.
- **Advanced Analytics:** Deep insights, machine learning-based recommendations, or predictive analytics can be deferred.
- **Wearable Device Integration:** Focus on core meal and diet tracking; integrations can be planned for future releases.
- **In-app Purchases/Monetization:** This phase focuses on functionality and user validation. Monetization strategies can be explored later.

---

## 10. Success Metrics

- **User Engagement:** Track daily active users, frequency of meal logs, and feature usage.
- **User Retention:** Monitor retention rates over 30, 60, and 90 days post-signup.
- **Goal Achievement:** Measure how many users set and update goals, and correlate with reported progress.
- **Performance Metrics:** API response times, UI responsiveness, and system uptime.
- **User Feedback:** Through surveys, interviews, and analytics to iterate on features.

---

## 11. Next Steps

1. **Finalize Wireframes:** Develop initial prototypes for the core screens.
2. **Set Up Development Environment:** Configure code repositories, CI/CD pipelines, and containerization.
3. **Sprint Planning:** Break down features into user stories for agile iterations.
4. **User Testing:** Engage with a small group of target users during beta to validate core functionalities.
5. **Iterate & Expand:** Refine features based on feedback and plan for additional functionalities in subsequent releases.

