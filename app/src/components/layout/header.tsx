import React, { useState } from "react";
import { Input } from "../ui/input";

export function Navbar() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <nav className="bg-gray-100 border-b border-gray-300">
      <div className="container mx-auto px-4 py-3 flex items-center justify-between">
        {/* Left: Logo */}
        <div className="flex items-center">
          <a href="/" className="text-2xl font-bold text-gray-800">
            LOGO
          </a>
        </div>

        {/* Middle: Hamburger Icon (Mobile) */}
        <div className="md:hidden">
          <button
            className="text-gray-600 hover:text-gray-800"
            onClick={() => setIsMenuOpen(!isMenuOpen)}
          >
            <svg
              className="w-6 h-6"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M4 6h16M4 12h16m-7 6h7"
              />
            </svg>
          </button>
        </div>

        {/* Middle: Links (Desktop) */}
        <div className="hidden md:flex space-x-8">
          <a href="/" className="text-gray-600 hover:text-gray-800">
            Home
          </a>
          <a href="/contact" className="text-gray-600 hover:text-gray-800">
            Contact
          </a>
          <a href="/about" className="text-gray-600 hover:text-gray-800">
            About
          </a>
        </div>

        {/* Right: Search Input */}
        <div className="hidden md:flex items-center space-x-4">
          <Input
            type="text"
            placeholder="What are you looking for?"
            className="w-64"
          />
        </div>
      </div>

      {/* Mobile Menu */}
      {isMenuOpen && (
        <div className="md:hidden bg-gray-100 border-t border-gray-300">
          <div className="flex flex-col items-start p-4 space-y-4">
            <a href="/" className="text-gray-600 hover:text-gray-800">
              Home
            </a>
            <a href="/contact" className="text-gray-600 hover:text-gray-800">
              Contact
            </a>
            <a href="/about" className="text-gray-600 hover:text-gray-800">
              About
            </a>
            <Input
              type="text"
              placeholder="What are you looking for?"
              className="w-full"
            />
          </div>
        </div>
      )}
    </nav>
  );
}
