# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands
- Start server: `clj -M:run`
- Run all tests: `clj -M:test`
- Run single test: `clj -M:test --var namespace/test-name`
- REPL: `clj -M:dev:repl`
- Build frontend: `npx tailwindcss -i ./resources/css/input.css -o ./resources/public/css/output.css`

## Code Style Guidelines
- Use kebab-case for namespaces, functions, and variables
- Prefer pure functions and immutable data structures
- Follow Clojure style guide (https://guide.clojure.style/)
- Structure: components using Stuart Sierra's Component library
- Frontend: hiccup for HTML templates, ClojureScript for interactivity
- Database: next.jdbc with HoneySQL for query building
- Error handling: use ex-info/ex-data for structured errors
- Testing: use clojure.test with fixtures as needed

## Architecture 
- Server-side rendering with minimal JS (similar to Django approach)
- Routing with Compojure
- Tailwind CSS for styling
- Figwheel for repl and hot reloading when doing Clojurescript

## Dependencies
- compojure
- stuartsierra/component
- hiccup
- ring
- next.jdbc
- HoneySQL
- sqlite3
- tailwindcss
