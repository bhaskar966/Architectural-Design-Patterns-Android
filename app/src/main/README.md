## MVP

### Advantages of MVP
*   **High Testability:** This is a major improvement over MVC. Because the Presenter communicates with the View through an interface (a Contract) rather than a direct reference to an Activity or Fragment, you can easily mock the View. This allows you to write Unit Tests that run on the JVM without needing resource-heavy Android emulators.
*   **"Dumb" or Passive View:** In MVP, the View (Activity/Fragment) contains absolutely no logic. It simply reports user actions (like button clicks) to the Presenter and displays what the Presenter tells it to. It does not decide anything on its own.
*   **Lifecycle Management:** The Presenter can be attached and detached from the View based on the Activity's lifecycle (e.g., calling `attach` in `onCreate` and `detach` in `onDestroy`). This helps manage the connection between the logic and the UI logically.

### Disadvantages of MVP
*   **Boilerplate and Interface Bloat:** MVP requires creating "Contracts" (interfaces) for the View and Presenter. To add a single feature, you often have to update the View interface, the Presenter interface, and their implementations. If a screen has many events, these interfaces become very bulky.
*   **Presenter Bloat:** Similar to the Controller in MVC, the Presenter handles all logic. As the app grows, the Presenter can become massive and difficult to manage.
*   **Manual Lifecycle Handling:** While better than MVC, the developer still has to manually write functions to attach and detach the View to prevent memory leaks or crashes when the View is destroyed.

### Why is MVP better than MVC?
*   **Decoupling from Android Framework:** In MVC, the Controller often has deep dependencies on Android APIs (like `Context` or `Activity`), making it hard to test without an emulator. In MVP, the Presenter depends on a pure Java/Kotlin interface, making unit testing much easier.
*   **Passive vs. Active View:** In MVC, the View is often "Active" (it might access the Model directly or handle some logic). In MVP, the View is strictly "Passive," ensuring a cleaner separation of concerns where the View never touches the Model directly.

### Why is MVP not recommended for Jetpack Compose?
*   **Imperative vs. Declarative Conflict:** MVP works by the Presenter manually telling the View what to update (e.g., calling a method like `view.showText()`). This is an **imperative** approach. Jetpack Compose is **declarative** and relies on monitoring State (like `StateFlow` or `MutableState`) to automatically update the UI.
*   **Lack of State Management:** MVP does not natively use reactive state holders (like flows). Implementing it in Compose is described as "very typical" (problematic) because you would have to force an imperative architecture into a framework designed for reactive streams and Unidirectional Data Flow.
*   **Historical Relevance:** While developers should know MVP, it is effectively obsolete for modern UI toolkits like Compose. It was useful when XML was the standard, but modern patterns like MVVM or MVI are designed to handle state and configuration changes much more effectively.