# Android Architectures Summary

#### For Implementations check -

* **[MVC](https://github.com/bhaskar966/Architectural-Design-Patterns-Android/tree/mvc)**
* **[MVP](https://github.com/bhaskar966/Architectural-Design-Patterns-Android/tree/mvp)**
* **[MVVM](https://github.com/bhaskar966/Architectural-Design-Patterns-Android/tree/mvvm)**
* **[MVI](https://github.com/bhaskar966/Architectural-Design-Patterns-Android/tree/mvi)**
* **[Clean Architecture](https://github.com/bhaskar966/Architectural-Design-Patterns-Android/tree/clean-arch)**

---

### **1. MVC (Model-View-Controller)**
**Summary:** The oldest pattern in Android (circa 2011). It separates the app into the **Model** (data/logic), **View** (XML/UI), and **Controller** (Bridge/Brain).

*   **How to Use:**
    *   **Model:** Holds data and business logic (e.g., `CounterModel`).
    *   **View:** The Layout (XML) and Activity/Fragment. It implements an interface (e.g., `CounterView`) to update the UI.
    *   **Controller:** Accepts user input from the View, manipulates the Model, and calls methods on the View to update it manually.
*   **Advantages:**
    *   Simple to understand for beginners.
    *   Basic separation of concerns (Logic is separated from UI).
*   **Disadvantages:**
    *   **State Loss:** The Controller does not survive screen rotation (configuration changes), leading to data loss unless manually saved.
    *   **Tight Coupling:** The Controller and View have circular dependencies; the Controller is often tightly coupled to the Android Framework (Activity/Context), making Unit Testing difficult.
    *   **Bloat:** As features grow, the Controller becomes massive and hard to maintain.
*   **When to Use:** **Rarely/Never for new apps.** It is mostly found in very old legacy projects. It is strongly **not recommended** for Jetpack Compose.

---

### **2. MVP (Model-View-Presenter)**
**Summary:** An evolution of MVC designed to improve testability. It replaces the Controller with a **Presenter**.

*   **How to Use:**
    *   **Contract:** Define an interface (Contract) listing methods for both View and Presenter.
    *   **Presenter:** Handles logic. It does *not* know about Android APIs (Context/Activity). It communicates with the View only through the Interface.
    *   **View:** Is "Passive" or "Dumb." It only forwards clicks to the Presenter and displays exactly what the Presenter tells it to.
*   **Advantages:**
    *   **High Testability:** Since the Presenter is pure Java/Kotlin and communicates via interfaces, you can write Unit Tests without Android Emulators.
    *   **Separation:** The View contains no logic.
*   **Disadvantages:**
    *   **Boilerplate:** You must create Interfaces/Contracts for every single screen and interaction.
    *   **Manual Lifecycle:** The Presenter must be manually attached/detached (e.g., `attachView`, `detachView`) to prevent memory leaks.
    *   **Imperative:** It relies on telling the UI *what* to do (`view.showLoading()`), which conflicts with modern declarative UI tools like Compose.
*   **When to Use:** Good for learning the concept of abstraction or working on legacy XML-based apps. **Avoid with Jetpack Compose**.

---

### **3. MVVM (Model-View-ViewModel)**
**Summary:** The current industry standard recommended by Google. It introduces the **ViewModel**, a component designed specifically to handle Android lifecycle issues.

*   **How to Use:**
    *   **ViewModel:** Holds the UI State (using `StateFlow` or `LiveData`). It survives configuration changes (rotation).
    *   **View:** Observes the State exposed by the ViewModel. When state changes, the UI updates automatically (Reactive).
    *   **Model:** Often uses a **Repository** to abstract data sources (API/Database).
*   **Advantages:**
    *   **Lifecycle Aware:** Solves the rotation/data loss issue automatically.
    *   **No Redundant Calls:** Data persists across rotation, so API calls aren't wasted.
    *   **Standardization:** Most companies use it; it supports "Process Death" handling.
*   **Disadvantages:**
    *   Slightly higher learning curve than MVC for absolute beginners.
*   **When to Use:** **The Default Choice.** Use this for almost all standard Android applications, whether using XML or Jetpack Compose.

---

### **4. MVI (Model-View-Intent)**
**Summary:** A pattern focused on **Strict Unidirectional Data Flow**, highly compatible with Jetpack Compose.

*   **How to Use:**
    *   **Intent:** The User triggers an Intent (e.g., `ClickButton`).
    *   **Action & Result:** The Intent is processed into an Action, which produces a Result.
    *   **State:** The Result creates a *new*, immutable State object.
    *   **View:** The View renders this single State.
*   **Advantages:**
    *   **Predictability:** State flows in only one direction. Easy to debug "state" issues.
    *   **Thread Safety:** Uses immutable data models.
    *   **Best for Compose:** Aligns perfectly with Compose's state-driven rendering.
*   **Disadvantages:**
    *   **Boilerplate:** Requires creating Sealed Classes for Intents, Actions, Results, and States for every screen.
    *   **Complexity:** Can be overkill for simple screens (e.g., a static "About Us" page).
*   **When to Use:** **Complex Screens & Jetpack Compose.** Ideal when a screen has many complex states (Loading, Error, Empty, Content) that depend on each other.

---

### **5. Clean Architecture**
**Summary:** A structural architecture that organizes the *entire* project (not just the UI layer like the patterns above). It can be combined with MVVM or MVI.

*   **How to Use:**
    *   **Data Layer:** API calls (Retrofit), Databases (Room), and Repository Implementations.
    *   **Domain Layer:** The "Leader" (Independent). Contains **Use Cases** (Business Logic) and Repository Interfaces. Depends on no one.
    *   **Presentation Layer:** UI and ViewModel (MVVM/MVI). Depends on Domain.
*   **Advantages:**
    *   **Independence:** Business logic is isolated from UI and Framework.
    *   **Scalability:** Best for large teams and massive projects.
    *   **Reusability:** **Use Cases** (e.g., `GetProfileUseCase`) can be reused across different ViewModels.
*   **Disadvantages:**
    *   **Overkill:** Too many files/layers for small, simple apps.
    *   **Complexity:** Requires understanding Dependency Injection and strict layer rules.
*   **When to Use:** Large-scale enterprise applications, long-term projects, or when high testability (100% logic coverage) is required.

---

### **Summary Comparison Table**

| Feature              | MVC                | MVP                    | MVVM                | MVI                 | Clean Arch              |
|:---------------------|:-------------------|:-----------------------|:--------------------|:--------------------|:------------------------|
| **Best For**         | Legacy/Simple XML  | Legacy/Testing         | **Standard Apps**   | **Complex/Compose** | **Large Scale**         |
| **Communication**    | Controller -> View | Presenter -> View      | View Observes VM    | Unidirectional Flow | Layers (Data/Domain/UI) |
| **Rotation Support** | No (Manual)        | Manual (Attach/Detach) | **Yes (Automatic)** | **Yes (Automatic)** | N/A (Structural)        |
| **Testing**          | Hard               | High                   | High                | High                | **Highest**             |
| **Jetpack Compose**  | **No**             | **No**                 | **Yes**             | **Best Fit**        | **Yes**                 |

### **Best usage**

1.  **For a standard modern Android App:** Use **MVVM**. It balances complexity and features perfectly. Use it with the **Repository Pattern** to manage data.
2.  **For complex UI with Jetpack Compose:** Use **MVI**. The strict state management prevents bugs in reactive UIs.
3.  **For Professional/Large Scale Apps:** Wrap your MVVM/MVI patterns inside **Clean Architecture**. This ensures your business logic (Domain) remains clean and reusable as the app grows.