# DEV-02 - Update README.md with running instructions and repo info

## PR Title
DEV-02: Add running instructions, env vars and repo info to README

## Summary
New-hires were having trouble getting the repo running, so I've expanded the README with everything needed to get started.

## Changes
- Added a **Tech Stack** section listing Kotlin, Spring Boot, Java, Gradle and MongoDB versions.
- Added a **Repository Structure** section explaining the layer folders (controllers/services/repositories/entities) and the Controller -> Service -> Repository -> Entity flow.
- Added an **Environment Variables** section documenting `MONGO_DB_URI`, where it is read in `application.yaml`, and a connection string for the dev database so people can get going quickly.
- Added step-by-step **Running the Application** instructions, including how to set the env var on Windows and Mac/Linux.
- Added a **Running Tests** section.
- Added an **Implementing Features** section covering the `//TODO: DEV-XX` convention.

## Testing
- Followed the steps in the README to confirm they read correctly.
- Docs only change, no code changes.
