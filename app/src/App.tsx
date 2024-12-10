import "./App.css";
import { Navbar } from "./components/layout/header";
import Register from "./components/forms/register-form";
import Login from "./components/forms/login-form";
import Footer from "./components/layout/footer";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";

function App() {
  return (
    <BrowserRouter>
      <div className="min-h-screen flex flex-col">
        <Navbar />
        <main className="flex-grow">
          <Routes>
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login />} />
            {/* Add more routes as needed */}
            {/* You can add a home page route */}
            <Route path="/" element={<Register />} />{" "}
            <Route path="/home" element={<Home />} />{" "}
            {/* or whatever component you want for the home page */}
          </Routes>
        </main>
        <Footer />
      </div>
    </BrowserRouter>
  );
}

export default App;
