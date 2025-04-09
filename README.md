# Alphabet Learning

A simple ecommerce web application for selling PDF educational worksheets.

## Project Overview

Alphabet Learning is a platform focused on selling PDF worksheets for early learners. Teachers, home-schoolers, carers, tutors, and parents can purchase high-quality educational resources at affordable prices.

## Tech Stack

- **Backend**: Clojure with Ring, Compojure, and Component
- **Frontend**: Server-side rendering with Hiccup, ClojureScript for interactivity
- **Styling**: Tailwind CSS
- **Database**: SQLite in development, PostgreSQL in production

## Development Setup

### Prerequisites

- Clojure (1.11 or newer)
- Node.js and npm (for Tailwind CSS)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/alphabet-learning.git
cd alphabet-learning
```

2. Install npm dependencies:
```bash
npm install
```

3. Build CSS:
```bash
npm run build:css
```

### Running the Application

Start the application server:
```bash
clj -M:run
```

For development with CSS live-reloading:
```bash
npm run dev
```

The application will be available at http://localhost:3000

### Running Tests

Run all tests:
```bash
clj -M:test
```

Run a specific test:
```bash
clj -M:test --var namespace/test-name
```

## Features

- Browse educational resources by category
- User registration and authentication
- Shopping cart functionality
- Secure payments via Stripe
- Resource upload for administrators