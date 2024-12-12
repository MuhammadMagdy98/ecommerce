import { env } from "@/config/env";
import axios from "axios";

const BASE_URL = env.API_URL;

// Create axios instance with default config
export const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

export const ENDPOINTS = {
  AUTH: {
    REGISTER: "/user/register",
    LOGIN: "/user/login",
    LOGOUT: "/user/logout",
    ME: "/user/me"
  },
  USER: {
    PROFILE: "/user/profile",
    UPDATE_PROFILE: "/user/profile/update",
  },
} as const;
