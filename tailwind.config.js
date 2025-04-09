/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./resources/templates/**/*.html",
    "./src/alclojure/views/**/*.clj"
  ],
  theme: {
    extend: {
      colors: {
        blue: {
          600: '#2563eb',
          700: '#1d4ed8',
        }
      }
    },
  },
  plugins: [],
}