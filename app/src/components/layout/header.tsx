import React, { useState } from "react";
import { Input } from "@/components/ui/input";
import { Search } from "lucide-react";
import { Link } from "react-router-dom";

export function Navbar() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <nav className="bg-white border-b border-gray-300">
      <div className="container mx-auto py-3 flex items-center justify-between">
        {/* Left: Logo */}
        <div className="flex items-start">
          <Link to="/" className="text-2xl font-bold text-gray-800">
            MoCommerce
          </Link>
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
          <Link to="/home" className="text-gray-600 hover:text-gray-800">
            Home
          </Link>
          <Link to="/contact" className="text-gray-600 hover:text-gray-800">
            Contact
          </Link>
          <Link to="/about" className="text-gray-600 hover:text-gray-800">
            About
          </Link>
        </div>

        {/* Right: Search Input */}
        <div className="hidden md:flex items-center space-x-4">
          <div className="relative w-64">
            <Input
              type="text"
              placeholder="What are you looking for?"
              className="w-full pr-10"
            />
            <div className="absolute inset-y-0 right-3 flex items-center">
              <Search className="h-5 w-5 text-gray-500" />
            </div>
          </div>
        </div>
      </div>

      {/* Mobile Menu */}
      {isMenuOpen && (
        <div className="md:hidden bg-white border-t border-gray-300">
          <div className="flex flex-col items-start p-4 space-y-4">
            <Link to="/home" className="text-gray-600 hover:text-gray-800">
              Home
            </Link>
            <Link to="/contact" className="text-gray-600 hover:text-gray-800">
              Contact
            </Link>
            <Link to="/about" className="text-gray-600 hover:text-gray-800">
              About
            </Link>
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
