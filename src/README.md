Project Overview

This project is a modular movie recommendation system that integrates a local SQLite database with data retrieved from the TMDB (The Movie Database) API. It is structured to separate concerns such as data acquisition, persistence, domain logic, and user interaction, allowing each component to be developed and tested independently.

The system supports user accounts, movie storage, and tracking of watched films. Based on this data, the application is intended to generate personalized movie recommendations.

Implemented Features
Modular Architecture

The project is built in a modular way. Data access, business logic, API communication, and the user interface are implemented in separate layers, allowing for maintainability and future extension.

Custom Math Library

A dedicated math module is used to support rating calculations and recommendation logic. This allows flexible experimentation with weighting, similarity scoring, and ranking algorithms without affecting the rest of the system.

Database Integration

The application uses SQLite as a local database backend. It stores:

Movie metadata

User accounts

Watched movie lists

Access levels

All access to the database is handled through a DAO layer using JDBC and prepared statements.

TMDB API Integration

Movie data is fetched from the TMDB API and written into the local database. This allows the application to operate on a persistent local dataset while still using real, up-to-date movie information.

Current Limitations
Admin Functionality

Administrative features such as managing users, editing movies, or moderating data are not yet implemented.

User Interface Robustness

The CLI user interface is functional but not user-proof. Invalid input, unexpected commands, or malformed data can still cause errors or crashes.

Recommendation Logic

The recommendation system is implemented but not yet optimal. Similarity calculations and ranking heuristics still require tuning, and recommendations may not always be accurate or meaningful.

Summary

This project provides a working foundation for a movie recommendation system with real data, persistence, and modular logic. While the core infrastructure is in place, further work is required to complete the admin tools, harden the UI, and improve the quality of recommendations.