import { useState } from 'react';
import { Input } from '@/components/ui/input';
import { Search, ShoppingCart, Heart, UserRound } from 'lucide-react';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { RootState } from '@/store';
import AccountDropdown from './account-dropdown';

export function Navbar() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const { isAuthenticated, userDetails } = useSelector(
    (state: RootState) => state.userDetails
  );

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

        {/* Right: Search Input and Icons */}
        <div className="hidden md:flex items-center space-x-6">
          {/* Search Input */}
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
          {/* Cart Icon */}
          <Link to="/cart" className="text-gray-600 hover:text-gray-800">
            <ShoppingCart className="h-6 w-6" />
          </Link>
          {/* Wishlist Icon */}
          <Link to="/wishlist" className="text-gray-600 hover:text-gray-800">
            <Heart className="h-6 w-6" />
          </Link>
          {isAuthenticated && (
            <AccountDropdown trigger={<UserRound className="h-6 w-6" />} />
          )}
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
            {/* Search Input */}
            <Input
              type="text"
              placeholder="What are you looking for?"
              className="w-full"
            />
            {/* Cart and Wishlist Icons */}
            <div className="flex items-center space-x-6 mt-2">
              <Link to="/cart" className="text-gray-600 hover:text-gray-800">
                <ShoppingCart className="h-6 w-6" />
              </Link>
              <Link
                to="/wishlist"
                className="text-gray-600 hover:text-gray-800"
              >
                <Heart className="h-6 w-6" />
              </Link>
              {isAuthenticated && (
                <AccountDropdown trigger={<UserRound className="h-6 w-6" />} />
              )}
            </div>
          </div>
        </div>
      )}
    </nav>
  );
}
