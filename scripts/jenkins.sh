#!/bin/bash

# Show commands being run and stop on failures
set -euxo pipefail

# Remove existing tests
git rm -rf src/diffblue-test/java || true

# Regenerate tests
/cover-ui/dcover create io.diffblue.corebanking --test-sources-root src/diffblue-test/java -cp target/classes

# Add tests back to git and push
BRANCH="diffblue/${BRANCH_NAME:-tests}"
git checkout -B "${BRANCH}"
git add .
git commit -m "Added Diffblue tests"
git push -f origin "${BRANCH}"
