import React from "react";

const Footer = () => {
  return (
    <footer className="bg-black text-white py-10">
      <div className="container mx-auto px-4 grid grid-cols-1 md:grid-cols-4 gap-8">
        {/* Column 1: About Us */}
        <div>
          <h3 className="text-lg font-bold mb-4">About Us</h3>
          <p className="text-sm">
            We are your trusted e-commerce platform offering the best products
            at unbeatable prices. Shop with confidence and enjoy quick delivery
            and excellent customer service.
          </p>
        </div>

        {/* Column 2: Quick Links */}
        <div>
          <h3 className="text-lg font-bold mb-4">Quick Links</h3>
          <ul className="space-y-2">
            <li>
              <a href="/about" className="hover:underline">
                About Us
              </a>
            </li>
            <li>
              <a href="/shop" className="hover:underline">
                Shop
              </a>
            </li>
            <li>
              <a href="/contact" className="hover:underline">
                Contact Us
              </a>
            </li>
            <li>
              <a href="/faq" className="hover:underline">
                FAQs
              </a>
            </li>
          </ul>
        </div>

        {/* Column 3: Social Media */}
        <div>
          <h3 className="text-lg font-bold mb-4">Follow Us</h3>
          <div className="flex space-x-4">
            <a
              href="https://facebook.com"
              target="_blank"
              rel="noopener noreferrer"
              className="w-8 h-8 flex items-center justify-center bg-white text-black rounded-full hover:bg-yellow-400"
            >
              F
            </a>
            <a
              href="https://twitter.com"
              target="_blank"
              rel="noopener noreferrer"
              className="w-8 h-8 flex items-center justify-center bg-white text-black rounded-full hover:bg-yellow-400"
            >
              T
            </a>
            <a
              href="https://instagram.com"
              target="_blank"
              rel="noopener noreferrer"
              className="w-8 h-8 flex items-center justify-center bg-white text-black rounded-full hover:bg-yellow-400"
            >
              I
            </a>
            <a
              href="https://linkedin.com"
              target="_blank"
              rel="noopener noreferrer"
              className="w-8 h-8 flex items-center justify-center bg-white text-black rounded-full hover:bg-yellow-400"
            >
              L
            </a>
          </div>
        </div>

        {/* Column 4: Newsletter */}
        <div>
          <h3 className="text-lg font-bold mb-4">Newsletter</h3>
          <p className="text-sm mb-4">
            Subscribe to our newsletter to receive the latest updates and
            offers.
          </p>
          <form className="flex">
            <input
              type="email"
              placeholder="Enter your email"
              className="px-4 py-2 flex-1 rounded-l-md focus:outline-none text-black"
              required
            />
            <button
              type="submit"
              className="bg-yellow-500 text-black px-4 py-2 rounded-r-md hover:bg-yellow-600 font-bold"
            >
              Subscribe
            </button>
          </form>
        </div>
      </div>

      {/* Footer Bottom */}
      <div className="mt-10 border-t border-gray-700 pt-4 text-center text-sm">
        &copy; 2024 Your E-commerce Website. All Rights Reserved.
      </div>
    </footer>
  );
};

export default Footer;
