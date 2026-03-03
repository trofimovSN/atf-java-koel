# Koel Automation Framework UI

A professional, scalable test automation framework for the **Koel** music streaming application. Built with a focus on speed, thread-safety, and a hybrid testing approach.

## 🚀 Tech Stack
* **Language:** Java 11/17
* **Core:** Selenium WebDriver 4
* **Test Runners:** TestNG & Cucumber (BDD)
* **API Testing:** RestAssured
* **Build Tool:** Gradle
* **Cloud Integration:** LambdaTest & Selenium Grid

## 🏗️ Architecture Highlights

### 1. Robust Driver Management
* **DriverFactory**: Centralized logic for instantiating drivers based on the environment (`Local`, `Grid`, or `LambdaTest Cloud`).
* **DriverManager**: Uses `ThreadLocal<WebDriver>` to ensure complete thread-safety during parallel execution. No more session conflicts.

### 2. Hybrid UI + API Approach
To optimize execution time, the framework leverages a hybrid strategy:
* **API Integration**: Test data (like playlists) is created/deleted via REST API in milliseconds.
* **UI Testing**: Selenium is used only for critical visual and functional verifications, significantly reducing the overall build time.

### 3. Smart Page Objects
* **POM Implementation**: Clean Page Object Model using `PageFactory`.
* **Stability**: Custom synchronization layers (e.g., `visibilityWait`) to handle SPA (Single Page Application) transitions smoothly.

## 🔧 Running Tests

You can run specific test suites using the following Gradle commands:

* **Full Regression (The Whole Deal):**
  ```bash
  ./gradlew clean test -Dsuite=FullTest

* **Smoke Suite (Critical Path):**

  ```Bash
  ./gradlew clean test -Dsuite=smoke
  ```
* **Cucumber Scenarios (BDD):**

  ```Bash
  ./gradlew clean test -Dsuite=CucumberTest
  ```
* **General Regression:**

  ```Bash
  ./gradlew clean test -Dsuite=Regression
  ```
  
