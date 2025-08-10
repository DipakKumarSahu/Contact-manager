# 👨‍👩‍👧☎️ PeopleVault — Your Ultimate Contact Management Solution

PeopleVault is a modern, secure, and cloud-powered contact management application built with Spring Boot. Designed to help users effortlessly organize, store, and manage contacts, PeopleVault offers a feature-rich experience combining ease of use with powerful integrations such as OAuth2 login, email verification, cloud image hosting, birthday reminders, and data export. Whether you’re managing personal contacts or running a small CRM, PeopleVault provides the tools you need to stay connected and productive.

---

## 🚀 Features

### 🔐 Secure Authentication
- **Traditional Login:** Users can create accounts with strong password encryption using BCrypt hashing to protect sensitive data and prevent unauthorized access.
- **OAuth2 Single Sign-On:** Simplify login by authenticating via trusted providers like Google and GitHub, eliminating the need to remember extra passwords and enhancing user convenience and security.
- **Email Verification:** New users receive verification emails to confirm their identity before gaining full access, helping keep the platform free from spam and fraudulent accounts.

### 📂 Comprehensive Contact Management
- **Full CRUD Functionality:** Easily add, update, delete, or view contacts through an intuitive interface, ensuring you always have up-to-date information at your fingertips.
- **Smart Search & Filters:** Quickly locate any contact by searching or filtering by name, email, or phone number, saving time when managing large contact lists.
- **Cloud Image Storage:** Contact profile pictures are stored securely in Cloudinary, a reliable cloud service that ensures images load fast and are accessible from anywhere, reducing local storage needs and improving app responsiveness.

### 🎉 Birthday & Event Reminders
- **Automated Birthday Tracker:** Never miss an important date again! The app automatically compiles a list of upcoming birthdays, helping you stay connected and thoughtful with your contacts.
- **Dark/Light Mode:** Choose between visually comfortable themes tailored for different lighting conditions, reducing eye strain and enhancing usability during day or night.

### 📤 Contact Export
- **One-Click Excel Export:** Backup or share your contacts with ease by exporting them in Excel (.xlsx) format. This feature is especially useful for offline access or migrating data to other systems.

### 📧 Robust Email Integration
- **Welcome Emails:** Engage new users immediately with a warm, automated welcome message, improving user experience and retention.
- **Email-Based Account Activation:** Ensures every account is linked to a verified email address, enhancing security and user trust.
- **Mailtrap SMTP Integration:** Enables developers to safely test email sending functionality in development without spamming real users, streamlining testing and debugging.

### 🛠 Developer-Friendly Design
- **REST API Ready:** Built with extendibility in mind, PeopleVault exposes RESTful APIs, making it easy to integrate with mobile applications or other third-party services in the future.
- **Modular Code Architecture:** The project is organized into clear, distinct modules, making the codebase easy to understand, maintain, and scale as features grow.
- **Responsive UI:** Thymeleaf templates combined with TailwindCSS ensure the interface adapts seamlessly across devices, delivering a consistent user experience on desktops, tablets, and smartphones.

---

## 🏗 Tech Stack

### Backend
- **Spring Boot 3.4.5:** Provides a mature, reliable foundation for building scalable, production-ready Java applications quickly and efficiently.
- **Spring Data JPA (Hibernate):** Simplifies database interactions by abstracting boilerplate SQL with powerful ORM capabilities, allowing developers to focus on business logic.
- **Spring Security with OAuth2 Client:** Handles user authentication and authorization securely, including social login flows through OAuth2 providers.
- **MySQL Database:** A robust relational database system used to store all application data securely and efficiently.

### Frontend
- **Thymeleaf:** Server-side template engine enabling dynamic rendering of HTML views, tightly integrated with Spring Boot for clean MVC architecture.
- **TailwindCSS & Custom CSS:** Offers a modern, utility-first CSS framework combined with custom styles, providing flexible and visually appealing layouts with dark/light mode support.

### Integrations
- **Google & GitHub OAuth2:** Popular OAuth2 providers used to streamline the login process and enhance user convenience.
- **Cloudinary:** Trusted cloud service for image hosting, providing fast delivery and reliable storage of profile pictures.
- **Mailtrap SMTP:** Secure email testing platform that allows developers to send emails in staging environments without reaching actual inboxes.

---

## 📦 Key Dependencies

| Dependency                      | Purpose                                   |
| ------------------------------- | ----------------------------------------- |
| `spring-boot-starter-data-jpa`  | Simplifies ORM and database operations    |
| `spring-boot-starter-thymeleaf` | Dynamic server-side HTML rendering         |
| `spring-boot-starter-security`  | Provides authentication and authorization |
| `spring-boot-starter-oauth2-client` | Adds OAuth2-based social login support  |
| `spring-boot-starter-mail`      | Email sending functionality                |
| `cloudinary-http5`              | Cloud image uploading and management       |
| `mysql-connector-j`             | MySQL database connectivity                 |
| `lombok`                       | Reduces boilerplate code through annotations|

---

## Why Choose PeopleVault?

- **Security First:** Employs best practices for authentication, authorization, and data protection.  
- **User-Centric Design:** Smooth user experience with intuitive navigation, instant search, and personalization options like theme switching.  
- **Scalable Architecture:** Modular design and REST APIs enable future expansion, including mobile app integration.  
- **Rich Feature Set:** From automated reminders to seamless social logins, PeopleVault combines convenience with powerful functionality.  
- **Developer Friendly:** Clean, maintainable codebase with industry-standard technologies and detailed documentation.

---

## Getting Started

1. Clone the repository.  
2. Configure your MySQL database connection in `application.properties`.  
3. Setup your OAuth2 credentials for Google and GitHub.  
4. Provide Cloudinary API credentials for image uploads.  
5. Configure Mailtrap for email testing or replace with your SMTP provider.  
6. Build and run the Spring Boot application using Maven or your IDE.  
7. Access the application at `http://localhost:8080`.

---

Feel free to reach out if you want me to help with generating badges, screenshots, or adding a demo link section for your README too!


---

## 📸 Screenshots
> _(Add screenshots here for login, dashboard, contact management, and birthday reminders)_
![home light](https://github.com/user-attachments/assets/6143d570-6a88-4478-81f1-2a4d35ea61f0)
> ![home dark](https://github.com/user-attachments/assets/3d660c8d-38db-4431-9594-6b554640d9e5)

About Page
> ![About light 1](https://github.com/user-attachments/assets/9c076e00-2774-4f1b-8de2-0c2e139c8382)
> ![About light 2](https://github.com/user-attachments/assets/686af5dd-e920-4459-a9f1-c5f7f3188c6d)

Service Page
>![service light 1](https://github.com/user-attachments/assets/b8230c7c-bae1-4dcb-82c6-475ac1c6c3f1)
>![service light 2](https://github.com/user-attachments/assets/fb4dd5c4-4e99-442b-9ef8-1f18129d1e7f)

Sign Up Page
> ![signup light](https://github.com/user-attachments/assets/1ae628d6-aa24-4f72-9dc7-b6c7a1d050ef)
> ![signup dark](https://github.com/user-attachments/assets/53c7c552-d74f-4383-a307-7ebb163fbbc1)
> ![signup with oauth](https://github.com/user-attachments/assets/77d5a5d1-f089-4d42-b6ec-6251d95d2950)

Email Verification
>![verficartion link send to email](https://github.com/user-attachments/assets/29e27826-0d9c-4ada-afbb-fbf353df2b6f)

Login Page
>![Login page](https://github.com/user-attachments/assets/80ac8816-46e1-4b23-9794-cb3606745d70)

User Profile Page
>![profile](https://github.com/user-attachments/assets/7c950b3f-0690-4eaa-bcb4-ced9a908ce51)

User Add new Contact
>![add new contact](https://github.com/user-attachments/assets/be148d17-643c-4b89-9e34-180dd617c61f)
>![add new contact 2](https://github.com/user-attachments/assets/e488e9c6-13e6-45c0-b72a-3642ba0e5bf3)

All Contacts
>![all your contacts](https://github.com/user-attachments/assets/3496a771-bfae-45fc-9490-c98e0afc8921)

Upcoming Birthdays
>![upcomiing birthdays](https://github.com/user-attachments/assets/707dce57-33ee-4d81-b8b5-0223519c11cc)


---

