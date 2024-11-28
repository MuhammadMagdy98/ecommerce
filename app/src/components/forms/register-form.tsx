import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import LandingImage from "../../assets/landing-image.png";

function Register() {
  return (
    <div className="flex m-10">
      <img
        src={LandingImage}
        className="h-[781px] w-[805px]"
        alt="landing image"
      />
      <div className="w-[400px] h-[530px] mx-auto p-6 rounded-lg bg-white my-8">
        {/* Header */}
        <h1 className="text-4xl font-bold mb-4">Create an account</h1>
        <p className="text-gray-600 mb-6">Enter your details below</p>

        {/* Form Fields */}
        <div className="h-full my-8">
          <div className="space-y-[20px] h-full">
            <Input placeholder="Name" />
            <Input placeholder="Email" type="email" />
            <Input placeholder="Password" type="password" />

            {/* Submit Button */}
            <Button type="submit" className="w-full primary">
              Create Account
            </Button>
            {/* Divider */}
            <div className="flex items-center my-6">
              <hr className="flex-grow border-gray-300" />
              <span className="px-2 text-gray-500">OR</span>
              <hr className="flex-grow border-gray-300" />
            </div>
            {/* Sign up with Google */}
            <Button className="w-full bg-white text-gray-700 border border-gray-300 hover:bg-gray-100">
              <svg
                className="w-5 h-5 inline-block mr-2"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 48 48"
              >
                <path
                  fill="#EA4335"
                  d="M24 9.5c3.2 0 6.1 1.2 8.4 3.2l6.3-6.3C34.3 3.4 29.4 1.5 24 1.5 14.7 1.5 7 8.4 4.2 17.3l7.5 5.8C13.2 14.5 18.2 9.5 24 9.5z"
                />
                <path
                  fill="#34A853"
                  d="M46.1 24.3c0-1.6-.2-3.2-.6-4.7H24v9h12.5c-.5 2.7-2 5-4.1 6.6v5.4h6.7c3.9-3.6 6.2-8.9 6.2-15.3z"
                />
                <path
                  fill="#4A90E2"
                  d="M11.7 28.1c-1-2.7-1.6-5.6-1.6-8.6s.6-6 1.6-8.6v-5.7H4.2C2 10.5 1 15.2 1 24s1 13.5 3.2 18.6l7.5-5.8c-.5-1.9-.8-3.8-.8-5.7z"
                />
                <path
                  fill="#FBBC05"
                  d="M24 46.5c5.4 0 10.3-1.9 14.2-5.4l-7.5-5.8c-2.3 1.5-5.3 2.4-8.7 2.4-5.8 0-10.8-3.8-12.5-9.1l-7.5 5.8C7 39.6 14.7 46.5 24 46.5z"
                />
              </svg>
              Sign Up with Google
            </Button>

            {/* Already have an account */}
            <p className="mt-4 text-center text-gray-600">
              Already have an account?{" "}
              <a href="/login" className="text-blue-500 hover:underline">
                Log in
              </a>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Register;
