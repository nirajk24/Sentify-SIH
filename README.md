# Sentiment Analysis Android App

Welcome to the README file for the Sentiment Analysis Android app built with Kotlin. This app leverages sentiment analysis on Twitter profiles and posts using Nitter to scrape tweets and ML models to provide a comprehensive dashboard about emotions and sentiments. Additionally, it includes a feature to analyze the sentiment and emotion of any sentence. The app uses Anygraph library to draw graphs for better visualization.

![Sentiment Analysis App Screenshots](Images/screenshot_main.jpg)

## Table of Contents

- [About the Project](#about-the-project)
  - [Features](#features)
  - [Screenshots](#screenshots)
- [Tech Stack](#tech-stack)
  - [Backend](#backend)
  - [Frontend](#frontend)
- [Architecture](#architecture)
- [Usage](#usage)
- [License](#license)
- [Contact](#contact)

## About the Project

The Sentiment Analysis Android app is designed to provide users with insights into the sentiments and emotions expressed in Twitter profiles, posts, and custom sentences. By utilizing Nitter to scrape tweets and ML models for sentiment analysis, the app offers a comprehensive dashboard to visualize emotions and sentiments in a user-friendly manner.

### Features

- Analyze sentiments and emotions in Twitter profiles and posts.
- Utilize Nitter to scrape tweets from user profiles or trending hashtags.
- Generate comprehensive dashboards with graphs illustrating sentiment trends.
- Perform sentiment analysis on custom sentences for immediate insights.
- Visualize sentiment and emotion analysis results using Anygraph library.
- Switch between light and dark themes for a personalized user experience.

### Screenshots

Here are some screenshots showcasing the app's user interface and features:

![Screenshot 1](/Images/screenshot_light.jpg)
<p align="center"><i>Light Mode - Dashboard</i></p>

![Screenshot 2](/Images/screenshot_dark.jpg)
<p align="center"><i>Dark Mode - Sentiment Analysis</i></p>

## Tech Stack

### Backend

- **Data Scraping:** Nitter is used for scraping tweets from user profiles or trending hashtags.
- **Machine Learning Model:** ML models are employed for sentiment and emotion analysis of tweets.
- **Graph Visualization:** Anygraph library is utilized to draw graphs for a more intuitive representation of sentiment trends.
- **API:** The backend API handles data processing and communication between the app and ML models.

### Frontend

- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel) pattern for clean separation of concerns.
- **Network Calls:** Retrofit library for efficient API calls and data loading.
- **UI Components:** 
  - RecyclerView: Displays scraped tweets and sentiment analysis results.
  - Anygraph: Draws interactive graphs for visualizing sentiment trends.
  - Material Components: Consistent UI elements and themes for an appealing look and feel.
- **Asynchronous Operations:** Coroutines for handling asynchronous tasks efficiently.

## Architecture

The Sentiment Analysis Android app follows the MVVM (Model-View-ViewModel) architectural pattern, providing a modular structure for efficient development and maintenance. This pattern divides the app into three distinct components:

- **Model:** Manages data and business logic, including ML models for sentiment analysis, data structures, and repositories crucial for app functionality.

- **View:** Presents user interface elements and handles user interactions. Screens, themes, and animations are managed within the View component.

- **ViewModel:** Acts as a bridge between the Model and the View, holding UI-related data and logic. This facilitates communication between components while maintaining data integrity.
  
![MVVM](https://github.com/yourusername/yourrepository/blob/main/Images/viewmodel.png)

By adopting the MVVM architecture, the Sentiment Analysis app gains several benefits:

- **Separation of Concerns:** Each component has a well-defined responsibility, leading to a structured and easily maintainable codebase.

- **Testability:** Business logic within the ViewModel can be independently tested, enhancing overall reliability.

- **Scalability:** Adding features or making changes becomes more straightforward, as components remain isolated.

- **Data Binding:** Coupled with ViewModel, data binding streamlines UI-data interaction, resulting in a smoother user experience.

In essence, the MVVM architecture empowers the Sentiment Analysis app with a foundation that promotes code quality, maintenance efficiency, and a delightful user experience. It seamlessly integrates various technologies and libraries while laying the groundwork for future enhancements.

## Usage

- Analyze sentiments and emotions in Twitter profiles and posts.
- Explore sentiment trends with comprehensive dashboards.
- Perform sentiment analysis on custom sentences for immediate insights.
- Switch between light and dark themes for a personalized user experience.

## License

This project is licensed under the [Apache-2.0 license](https://github.com/yourusername/yourrepository/blob/main/LICENSE). See the [Apache-2.0 license](https://github.com/yourusername/yourrepository/blob/main/LICENSE) file for details.

## Contact

Have questions or feedback? Feel free to reach out:

- LinkedIn: [Your LinkedIn Profile](https://www.linkedin.com/in/nirajk24)
