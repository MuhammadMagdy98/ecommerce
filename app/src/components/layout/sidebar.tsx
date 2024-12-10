function Sidebar() {
  const categories = [
    "Electronics",
    "Clothing",
    "Home Appliances",
    "Books",
    "Beauty Products",
  ];

  return (
    <div className="flex justify-start justify-items-start mx-[153px]">
      <aside className="h-screen w-64 bg-white border-r border-gray-200 flex flex-col container">
        {/* Categories Section */}
        <div className="px-6 py-4">
          <h3 className="text-lg font-semibold text-gray-800 mb-3">
            Categories
          </h3>
          <ul className="space-y-2">
            {categories.map((category) => (
              <li key={category}>
                <a
                  href={`/category/${category.toLowerCase()}`}
                  className="block text-gray-700 hover:text-gray-900 hover:bg-gray-100 p-2 rounded-lg"
                >
                  {category}
                </a>
              </li>
            ))}
          </ul>
        </div>
      </aside>
    </div>
  );
}

export default Sidebar;
