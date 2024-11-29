import "./App.css";
import { Navbar } from "./components/layout/header";
import Register from "./components/forms/register-form";
import Footer from "./components/layout/footer";

function App() {
  return (
    <>
      <Navbar/>
      <Register />
      <Footer/>
    </>
  );
}

export default App;
