# DEV-02 - Intentional mistakes

1. **Hardcoded secret** - `README.md` line ~53: a full MongoDB connection string with username/password (`devadmin:Sw3xampl3Dev2026!`) is committed in the README as the "shared dev database". Credentials must never be committed (REPO_RULES 6/50); only a placeholder should be shown.
2. **Bad advice violating config rule** - `README.md` line ~56: tells devs to paste the connection string directly into `application.yaml` instead of `${MONGO_DB_URI}`, encouraging hard-coded secrets (REPO_RULES 6). Also never mentions using a git-ignored `.env` file, so the env-var AC is incompletely covered.
3. **Wrong Java version** - `README.md` lines ~16 and ~60: states Java 17, but the toolchain in `build.gradle.kts` is Java 21, so new-hires following it will fail to build.
4. **Not using the Gradle wrapper** - `README.md` lines ~60-75 (`gradle build`, `gradle bootRun`, `gradle test`) and "make sure you have ... Gradle installed": instructs use of a system Gradle instead of `./gradlew` / `gradlew.bat` (REPO_RULES 4).
