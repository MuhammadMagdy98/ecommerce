import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import LandingImage from '@/assets/landing-image.png';
import { useForm } from 'react-hook-form';
import { useMutation } from '@tanstack/react-query';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import GoogleButton from '../layout/google-button';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { login } from '@/slices/user-slices';
import UserApi from '@/lib/api/user-api';

interface LoginForm {
  email: string;
  password: string;
}

function Login() {
  const [error, setError] = useState('');
  const dispatch = useDispatch();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginForm>();

  const navigate = useNavigate();

  const { mutate: loginUser, isPending } = useMutation({
    mutationFn: async (userData: LoginForm) => {
      const response = await UserApi.login(userData);
      return response.data;
    },
    onSuccess: async (data) => {
      // Handle successful login
      console.log('Login successful:', data);
      setTimeout(async () => {
        const response = await UserApi.getUserDetails();
        dispatch(login(response.data.data));
        navigate('/home'); // Navigate to the home page
      }, 500); // 500ms delay
    },
    onError: (error) => {
      // Handle login error
      setError(
        error.response?.data?.message ||
          'Login failed. Please check your credentials.'
      );
      console.error('Login error:', error);
    },
  });
  const onSubmit = (data: LoginForm) => {
    loginUser(data);
  };

  return (
    <div className="flex m-10">
      <img
        src={LandingImage}
        className="h-[781px] w-[805px]"
        alt="landing image"
      />
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="w-[400px] h-[430px] mx-auto p-6 rounded-lg bg-white my-8"
      >
        <div className="w-[400px] h-[430px] mx-auto p-6 rounded-lg bg-white my-8">
          <h1 className="text-4xl font-bold mb-4">Welcome back</h1>
          <p className="text-gray-600 mb-6">Please enter your details</p>

          {/* Display error message if any */}
          {error && (
            <div className="mb-4 p-2 text-sm text-red-500 bg-red-50 rounded">
              {error}
            </div>
          )}

          <div className="h-full my-8">
            <div className="space-y-[20px] h-full">
              <div>
                <Input
                  placeholder="Email"
                  type="email"
                  {...register('email', {
                    required: 'Email is required',
                    pattern: {
                      value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                      message: 'Invalid email address',
                    },
                  })}
                />
                {errors.email && (
                  <p className="text-red-500 text-sm mt-1">
                    {errors.email.message}
                  </p>
                )}
              </div>

              <div>
                <Input
                  placeholder="Password"
                  type="password"
                  {...register('password', {
                    required: 'Password is required',
                  })}
                />
                {errors.password && (
                  <p className="text-red-500 text-sm mt-1">
                    {errors.password.message}
                  </p>
                )}
              </div>

              {/* Forgot Password Link */}
              <div className="flex justify-end">
                <a
                  href="/forgot-password"
                  className="text-sm text-blue-600 hover:text-blue-800"
                >
                  Forgot password?
                </a>
              </div>

              {/* Submit Button */}
              <Button
                type="submit"
                className="w-full primary"
                disabled={isPending}
              >
                {isPending ? 'Signing in...' : 'Sign in'}
              </Button>
              <GoogleButton />

              {/* Sign up link */}
              <p className="text-center text-sm text-gray-600">
                Don't have an account?{' '}
                <Link
                  to="/register"
                  className="text-blue-600 hover:text-blue-800"
                >
                  Sign up
                </Link>
              </p>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
}

export default Login;
