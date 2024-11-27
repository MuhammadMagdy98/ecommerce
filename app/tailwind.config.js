/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: ["class"],
  content: ["./index.html", "./src/**/*.{ts,tsx,js,jsx}"],
  theme: {
    extend: {
      colors: {
        background: "#000000", // Black
        foreground: "#ffffff", // White text
        primary: "#EA4335", // Tailwind Zinc-800 for primary buttons
        "primary-foreground": "#ffffff", // White text on primary buttons
        secondary: "#f9fafb", // Tailwind Zinc-100 for secondary background
        "secondary-foreground": "#1f2937", // Dark text on secondary buttons
        border: "#e5e7eb", // Tailwind Zinc-300
        input: "#e5e7eb", // Tailwind Zinc-300
        ring: "#1f2937", // Tailwind Zinc-800
      },
      borderRadius: {
        lg: "0.5rem",
        md: "0.375rem",
        sm: "0.25rem",
      },
    },
  },
  plugins: [require("tailwindcss-animate")],
};
