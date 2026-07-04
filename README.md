# Ding-QAProject

[![QA Automation Suite](https://github.com/imranhassanaiub/Ding-QAProject/actions/workflows/qa-tests.yml/badge.svg)](https://github.com/imranhassanaiub/Ding-QAProject/actions/workflows/qa-tests.yml)

Selenium + TestNG + Cucumber test automation project targeting the [Swag Labs](https://www.saucedemo.com/) demo e‑commerce site. The suite covers login, product selection, checkout, navigation/state preservation, and negative/validation scenarios.

The project ships **two independent test entry points** built on the same page-object locators:

1. A **plain TestNG suite** (`qaproject.tests.*`) — linear, dependency-chained UI tests.
2. A **Cucumber BDD suite** (`Runner.java` + `.feature` files) — the same user journeys expressed as Gherkin scenarios.

Read [Known Limitations](#known-limitations--technical-debt) before you rely on this for anything beyond a demo/interview exercise — it documents a few real gaps found during review so you don't lose time rediscovering them.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup — Step by Step](#setup--step-by-step)
- [Run Configuration](#run-configuration)
- [Running the Tests](#running-the-tests)
- [Test Reports](#test-reports)
- [CI/CD Pipeline](#cicd-pipeline)
- [Known Limitations / Technical Debt](#known-limitations--technical-debt)
- [Suggested .gitignore](#suggested-gitignore)

---

## Tech Stack

| Layer               | Tool / Library                                      | Version               |
|---------------------|------------------------------------------------------|------------------------|
| Language            | Java                                                  | 11+ (17 recommended)  |
| Build tool          | Maven                                                 | 3.8+                   |
| Browser automation  | Selenium WebDriver                                    | 4.45.0                 |
| Test runner         | TestNG                                                | 7.12.0                 |
| BDD framework       | Cucumber-JVM (testng, java, junit-platform-engine)    | 7.34.4                 |
| Assertions          | JUnit / TestNG asserts                                | 4.13.2 / bundled       |
| Logging             | SLF4J Simple                                          | 2.0.16                 |
| CI/CD               | GitHub Actions (Ubuntu runner + Xvfb)                 | n/a                    |
| Target app          | [saucedemo.com](https://www.saucedemo.com/)           | n/a                    |

## Prerequisites

Install and verify each of these **before** touching the project:

| # | Requirement | Install | Verify |
|---|-------------|---------|--------|
| 1 | JDK 11+ (17 recommended) | [Adoptium Temurin](https://adoptium.net/) | `java -version` |
| 2 | Maven 3.8+ | [maven.apache.org](https://maven.apache.org/install.html) | `mvn -version` |
| 3 | Git | [git-scm.com](https://git-scm.com/downloads) | `git --version` |
| 4 | Google Chrome | [google.com/chrome](https://www.google.com/chrome/) | `google-chrome --version` (Linux) or open Chrome → **Settings → About** |

Selenium 4.45's built-in **Selenium Manager** auto-downloads the matching ChromeDriver binary for whatever Chrome version you have installed — you do **not** need to manually download or manage `chromedriver`. You do need internet access on first run, both for Maven to pull dependencies and for Selenium Manager to fetch the driver binary.

> The tests drive a **real, visible (headed) Chrome window** on your machine — see [Known Limitations](#known-limitations--technical-debt). Don't lock the screen or click into the browser while a run is in progress.

## Project Structure

```
Ding-QAProject/
├── pom.xml
├── src/test/java/
│   ├── Runner/
│   │   └── Runner.java                  # Cucumber-TestNG entry point
│   ├── Stepdefinitions/
│   │   ├── E2ePurchaseStepDefinitions.java
│   │   ├── Navigation.java
│   │   └── NegativeValidation.java
│   ├── SuiteTest.xml                    # TestNG suite descriptor (see Known Limitations)
│   └── qaproject/
│       ├── pages/                       # Page Object locators (Login, Items, Cart)
│       ├── tests/                       # Plain TestNG tests (Login, Checkout flow)
│       └── utils/                       # DriverManager + URL/text constants
├── src/test/resources/features/
│   ├── e2epurchase.feature              # @e2e
│   ├── navigation.feature               # @navigation
│   └── negative_validation.feature      # @negative
└── .github/workflows/qa-tests.yml       # CI pipeline
```

## Setup — Step by Step

Follow these in order on a fresh machine. Each step includes how to confirm it worked before moving on.

**Step 1 — Install prerequisites**
Install JDK 17, Maven, Git, and Google Chrome from the links in the [Prerequisites](#prerequisites) table, then confirm all four version commands succeed.

**Step 2 — Clone the repository**
```bash
git clone https://github.com/imranhassanaiub/Ding-QAProject.git
cd Ding-QAProject
```

**Step 3 — Resolve dependencies and compile**
```bash
mvn -q clean compile test-compile
```
This downloads every dependency in `pom.xml` into your local `~/.m2` repository and compiles both main and test sources. A clean exit with no `BUILD FAILURE` means your toolchain is correctly set up.

**Step 4 — Sanity-check Selenium Manager can reach Chrome**
```bash
mvn -q dependency:tree | grep selenium-java
```
Confirms Selenium 4.45.0 resolved correctly. The first time you actually run a test (Step 6), Selenium Manager will silently download the matching ChromeDriver binary — no separate step needed.

**Step 5 — (Optional) Import into an IDE**
See [Run Configuration](#run-configuration) below for Eclipse and IntelliJ import steps if you want to run/debug individual tests from an IDE instead of the command line.

**Step 6 — Run the suites**
Jump to [Running the Tests](#running-the-tests) for the exact commands. Run the TestNG suite first (`mvn test`) since it's the smaller/faster of the two, then the Cucumber suite (`mvn test -Dtest=Runner`).

**Step 7 — Open the reports**
Open `test-output/index.html` and `target/cucumber-reports.html` in a browser to confirm results. See [Test Reports](#test-reports) for all report locations.

## Run Configuration

### Command line (recommended — no IDE required)

| Goal | Command |
|------|---------|
| Compile only | `mvn clean compile test-compile` |
| Run plain TestNG suite | `mvn test` |
| Run Cucumber suite | `mvn test -Dtest=Runner` |
| Run Cucumber, filtered to one tag | `mvn test -Dtest=Runner -Dcucumber.filter.tags="@negative"` |
| Run a single TestNG class | `mvn test -Dtest=LoginTest` |
| Skip tests (compile only) | `mvn clean install -DskipTests` |

`-Dcucumber.filter.tags` works because Cucumber resolves tag options in this order of precedence: JVM system property > `cucumber.properties`/`junit-platform.properties` file > the `tags` attribute hardcoded in `Runner.java`'s `@CucumberOptions`. Passing the flag on the command line overrides the annotation without editing code.

### Eclipse (project already includes `.project` / `.classpath`)

1. **File → Import → Maven → Existing Maven Projects**, select the cloned folder, finish.
2. Right-click `Runner.java` → **Run As → TestNG Test** to run the full Cucumber suite.
3. Right-click `SuiteTest.xml` (under `src/test/java/`) → **Run As → TestNG Suite** to run the plain TestNG classes via the suite descriptor.
4. To run a single test class, right-click e.g. `LoginTest.java` → **Run As → TestNG Test**.
5. To create a reusable configuration: **Run → Run Configurations → TestNG → New**, set the **Class** or **Suite** field, then **Apply**.

### IntelliJ IDEA

1. **File → Open**, select the cloned folder (IntelliJ auto-detects the Maven project from `pom.xml`).
2. Let the Maven import finish (bottom-right progress bar), then let it index.
3. To run via Maven directly: **Run → Edit Configurations → + → Maven**, set **Command line** to `test` (or `test -Dtest=Runner`), name it, **Apply**, then run it from the toolbar dropdown.
4. To run a single class: open `LoginTest.java` or `Runner.java`, click the green gutter arrow next to the class declaration → **Run**.
5. TestNG plugin (bundled with IntelliJ Ultimate, install "TestNG" from Marketplace on Community) lets you run `SuiteTest.xml` directly: right-click the file → **Run 'SuiteTest.xml'**.

## Running the Tests

Because `Runner.java` doesn't match Maven Surefire's default test-file naming pattern (`**/*Test.java`, `**/Test*.java`, etc.), **`mvn test` alone only runs the plain TestNG suite** — the Cucumber suite has to be invoked explicitly. Use the commands below.

### 1. Plain TestNG suite (Login → Item Selection → Checkout)

```bash
mvn test
```

This runs `LoginTest → SelectItemsForCheckoutTest → CartCheckoutTest` in that order (they're chained via `dependsOnMethods`, sharing one `DriverManager.driver` instance for the run).

### 2. Cucumber BDD suite (all `.feature` files)

```bash
mvn test -Dtest=Runner
```

This executes every scenario tagged `@e2e`, `@navigation`, or `@negative` across all three feature files, using the step definitions in `Stepdefinitions/`.

### Running a single feature or tag

Either edit the `tags` attribute in `Runner.java`'s `@CucumberOptions` and re-run, or override it from the command line without touching code:

```bash
mvn test -Dtest=Runner -Dcucumber.filter.tags="@negative"
```

## Test Reports

| Suite     | Report location                              |
|-----------|-----------------------------------------------|
| TestNG    | `test-output/index.html` (open in a browser) |
| TestNG    | `target/surefire-reports/` (raw XML/TXT)     |
| Cucumber  | `target/cucumber-reports.html`               |

## CI/CD Pipeline

**File:** `.github/workflows/qa-tests.yml`
**Runner:** `ubuntu-latest` (GitHub-hosted)

### Triggers

| Event | Scope |
|-------|-------|
| `push` | Every branch |
| `pull_request` | Targeting `master` or `main` |
| `workflow_dispatch` | Manual — trigger from the **Actions** tab with a "Run workflow" button |

### What the pipeline does, step by step

| Step | Purpose |
|------|---------|
| Checkout repository | Pulls the commit that triggered the run |
| Set up JDK 17 | Installs Temurin 17 via `actions/setup-java`, with Maven dependency caching enabled |
| Verify Chrome is available | Runs `google-chrome --version` to fail fast if the runner image ever drops Chrome |
| Install Xvfb | `apt-get install xvfb` — provides a virtual display so headed Chrome can launch on a display-less runner (see [Known Limitations](#known-limitations--technical-debt)) |
| Compile | `mvn clean compile test-compile` — fails the build early on a compile error before wasting time launching a browser |
| Run TestNG suite | `xvfb-run mvn test` — runs `LoginTest → SelectItemsForCheckoutTest → CartCheckoutTest` |
| Run Cucumber suite | `xvfb-run mvn test -Dtest=Runner` — runs regardless of whether the TestNG step passed (`if: always()`), so one suite failing doesn't hide the other's results |
| Upload TestNG HTML report | Publishes `test-output/` as a workflow artifact |
| Upload Surefire raw reports | Publishes `target/surefire-reports/` as a workflow artifact |
| Upload Cucumber HTML report | Publishes `target/cucumber-reports.html` as a workflow artifact |

All three upload steps run with `if: always()`, so reports are available whether the suites passed or failed — useful for triaging a red build.

### Viewing results

1. Go to the repo's **Actions** tab.
2. Click the **QA Automation Suite** workflow run you're interested in.
3. Scroll to **Artifacts** at the bottom of the run summary to download the TestNG, Surefire, or Cucumber reports.
4. The badge at the top of this README reflects the status of the most recent run on the default branch.

### Re-running or triggering manually

- **Re-run a failed run:** open the run in the Actions tab → **Re-run jobs → Re-run all jobs**.
- **Manual trigger:** Actions tab → **QA Automation Suite** → **Run workflow** (enabled by the `workflow_dispatch` trigger).

### Why Xvfb instead of headless Chrome

`DriverManager` hardcodes `new ChromeDriver()` with no `ChromeOptions`, so there's no `--headless` flag to pass. Rather than editing the driver setup, the pipeline gives headed Chrome a virtual display (`Xvfb`) so it runs unmodified in CI, identically to how it runs on a developer machine. If `DriverManager` is later updated to support a real headless mode, the `Install Xvfb` / `xvfb-run` wrapping can be removed from the workflow.

### No secrets required

The pipeline needs no repository secrets or credentials — saucedemo.com is a public demo site with fixed, published test credentials used directly in the step definitions.

## Known Limitations / Technical Debt

Flagging these now so they don't cost you a debugging session later:

- **No headless toggle.** `DriverManager` hardcodes `new ChromeDriver()` with no `ChromeOptions`. Locally this pops a real browser window; in CI it only works because the pipeline wraps the run in Xvfb. If you add local headless support, parameterize it via a system property or environment variable rather than hardcoding `--headless`.
- **No driver teardown.** Nothing in the codebase calls `driver.quit()` — expect orphaned Chrome/Chromedriver processes to accumulate on your machine across repeated local runs.
- **`SuiteTest.xml` isn't wired into the Maven build.** There's no `<build>`/Surefire configuration in `pom.xml` referencing it, so `mvn test` runs via Surefire's auto-generated TestNG suite rather than `SuiteTest.xml` directly. Functionally the same three classes run either way, but if you add a fourth test class expecting `SuiteTest.xml` to control inclusion, it won't until the plugin is configured with `<suiteXmlFiles>`.
- **Shared static `WebDriver`.** All step definitions and tests reach into `DriverManager.driver`, a shared static instance. This makes parallel execution unsafe as-is — don't enable TestNG's parallel modes without refactoring to a ThreadLocal driver.
- **Build artifacts are committed to git.** `target/` and `test-output/` are currently tracked in the repository. Recommend adding a `.gitignore` (excluding at minimum `target/` and `test-output/`) so CI runs and reports don't pollute diffs.
- **No pinned Java compiler version** in `pom.xml` (no `maven.compiler.source`/`target` or `release` property). The CI pipeline pins JDK 17 explicitly; consider adding the same to `pom.xml` so local and CI builds can't silently diverge.
- **Some assertions are no-ops.** A few steps (e.g. `driver.getCurrentUrl().equals(...)`) call `.equals()` without wrapping it in an `Assert`, so they evaluate a boolean and discard it rather than failing the test on mismatch. Worth an audit if you plan to trust these as real checks.

## Suggested `.gitignore`

Not currently present in the repo — add one to keep build output and IDE files out of version control:

```gitignore
target/
test-output/
.settings/
.classpath
.project
*.log
```

---

Maintained by [Imran Hassan](https://github.com/imranhassanaiub).
