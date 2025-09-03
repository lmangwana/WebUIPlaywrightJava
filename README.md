# WebUIPlaywrightJava

Playwright + Java + Cucumber + TestNG framework for **Web UI test automation**.  
This framework shows best practices for Java UI automation with Playwright: clean structure, externalized config, CI integration, and test evidence (videos, screenshots, reports).

---

## Tests Performed
Automated against [SauceDemo](https://www.saucedemo.com/):

- Login
- Add item to cart
- View cart and verify items
- Add your information (checkout)
- Placed order

---

## Project Structure

<pre>
src
└── test
├── java
│   ├── Hooks/       # Setup & teardown
│   ├── Pages/       # Page Objects
│   ├── Runners/     # TestNG + Cucumber runners
│   ├── Steps/       # Step Definitions
│   └── Utils/       # ConfigManager, PlaywrightManager
│
└── resources
├── features/    # Gherkin feature files
├── config.properties
└── cucumber.properties
</pre>
---

## Pre-requisites
- Java 17 (Temurin recommended)
- Maven 3.8+
- Git
- FFmpeg (for Playwright video recording in CI)

---

## Execution Evidence
-	Videos: each scenario recorded (target/videos/**/video.webm)
-	Screenshots: saved on failures (target/screenshots/)
-	Reports: target/cucumber-report.html (local use, downloadable in CI)

---
 
## Config Handling
-	config.properties: committed with demo values for quick start.
-	In real projects:
-	Commit config.example.properties with placeholders.
-	Add config.properties to .gitignore.
-	Inject secrets via -D flags or CI secrets.

---
## How to Run Tests

### Local run
```bash
git clone git@github.com:lmangwana/WebUIPlaywrightJava.git
cd WebUIPlaywrightJava
mvn clean test -DsuiteXmlFile=testng-firsttimeuser.xml

```

### CI run (GitHub Actions)
- Workflow in .github/workflows/tests.yml runs on push/PR.
- Uses Java 17 + FFmpeg.
- Injects secrets (e.g. BASE_URL, PASSWORD, USER_STANDARD, USER_LOCKED).
- Uploads videos (target/videos) and screenshots (target/screenshots) as artifacts.

---


