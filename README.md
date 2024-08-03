# KMP MovieDb

This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

## Project Overview

This project was created as a playground for Kotlin Multiplatform (KMP). Apologies for the mess in the Gradle files. The architecture is designed with a multimodule structure, with all shared code for all platforms located in the `:core` folder.

### UI Frameworks
- **Desktop and Android:** Kotlin Compose
- **iOS:** SwiftUI

### Shared Resources
For shared resources, Moko Resources was chosen because it works better with iOS than Kotlin Compose Resources.

### Database
Room was chosen as the ORM for database operations. Unfortunately, Room is still in alpha and has many issues out of the box, including the inability to generate a `PagingSource`, which had to be implemented manually.

### Gradle Configuration
To improve convenience, the Gradle convention plugin was used to externalize common dependencies and settings.

## Setup Instructions

To build the project, please follow these steps:

1. Visit [The Movie Database API Getting Started](https://developer.themoviedb.org/reference/intro/getting-started) to create your own API key.
2. Create a file named `local.properties` in the root directory of the project.
3. Add the following fields to the `local.properties` file:

   ```properties
   API_TOKEN=your_api_token
   BASE_URL="https://api.themoviedb.org"



Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…