/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: ["class"],
  content: ["./index.html", "./src/**/*.{ts,tsx,js,jsx}"],
  theme: {
  	extend: {
  		colors: {
  			background: '#000000',
  			foreground: '#ffffff',
  			primary: '#EA4335',
  			'primary-foreground': '#ffffff',
  			secondary: '#f9fafb',
  			'secondary-foreground': '#1f2937',
  			border: '#e5e7eb',
  			input: '#e5e7eb',
  			ring: '#1f2937',
  			sidebar: {
  				DEFAULT: 'hsl(var(--sidebar-background))',
  				foreground: 'hsl(var(--sidebar-foreground))',
  				primary: 'hsl(var(--sidebar-primary))',
  				'primary-foreground': 'hsl(var(--sidebar-primary-foreground))',
  				accent: 'hsl(var(--sidebar-accent))',
  				'accent-foreground': 'hsl(var(--sidebar-accent-foreground))',
  				border: 'hsl(var(--sidebar-border))',
  				ring: 'hsl(var(--sidebar-ring))'
  			}
  		},
  		borderRadius: {
  			lg: '0.5rem',
  			md: '0.375rem',
  			sm: '0.25rem'
  		}
  	}
  },
  plugins: [require("tailwindcss-animate")],
};
