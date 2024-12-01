import { env } from "@/config/env";

const BASE_URL = env.API_URL;

export const ENDPOINTS = {
  AUTH: {
    REGISTER: "/user/register",
    LOGIN: "/user/login",
    LOGOUT: "/user/logout",
    REFRESH_TOKEN: "/user/refresh-token",
  },
  USER: {
    PROFILE: "/user/profile",
    UPDATE_PROFILE: "/user/profile/update",
  },
  // Add other endpoints without the base URL
} as const;
