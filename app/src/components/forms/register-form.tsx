import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import LandingImage from '../../assets/landing-image.png';
import { useForm } from 'react-hook-form';
import { useMutation } from '@tanstack/react-query';
import { useState } from 'react';
import { Link } from 'react-router-dom'; // Import Link and useNavigate
import UserApi from '@/lib/api/user-api';
import GoogleButton from '../layout/google-button';

interface RegisterForm {
  name: string;
  email: string;
  password: string;
}
function Register() {
  const [error, setError] = useState('');

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<RegisterForm>();

  const { mutate: registerUser, isPending } = useMutation({
    mutationFn: async (userData: RegisterForm) => {
      const response = await UserApi.register(userData);
      return response.data;
    },
    onSuccess: (data) => {
      // Handle successful registration
      console.log('Registration successful:', data);
      // Redirect to login page
    },
    onError: (error) => {
      // Handle registration error
      setError(
        error.response?.data?.message ||
          'Registration failed. Please try again.'
      );
      console.error('Registration error:', error);
    },
  });

  const onSubmit = (data: RegisterForm) => {
    registerUser(data);
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
        className="w-[400px] h-[530px] mx-auto p-6 rounded-lg bg-white my-8"
      >
        <div className="w-[400px] h-[530px] mx-auto p-6 rounded-lg bg-white my-8">
          <h1 className="text-4xl font-bold mb-4">Create an account</h1>
          <p className="text-gray-600 mb-6">Enter your details below</p>

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
                  placeholder="Name"
                  {...register('name', {
                    required: 'Name is required',
                    minLength: {
                      value: 2,
                      message: 'Name must be at least 2 characters',
                    },
                  })}
                />
                {errors.name && (
                  <p className="text-red-500 text-sm mt-1">
                    {errors.name.message}
                  </p>
                )}
              </div>

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
                    minLength: {
                      value: 8,
                      message: 'Password must be at least 8 characters',
                    },
                    pattern: {
                      value: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/,
                      message:
                        'Password must contain at least one letter and one number',
                    },
                  })}
                />
                {errors.password && (
                  <p className="text-red-500 text-sm mt-1">
                    {errors.password.message}
                  </p>
                )}
              </div>

              {/* Submit Button */}
              <Button
                type="submit"
                className="w-full primary"
                disabled={isPending}
              >
                {isPending ? 'Creating Account...' : 'Create Account'}
              </Button>

              <GoogleButton />
              {/* Already have an account */}
              <p className="text-center text-sm text-gray-600">
                Already have an account?{' '}
                <Link to="/login" className="text-blue-600 hover:text-blue-800">
                  Sign in
                </Link>
              </p>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
}

export default Register;
