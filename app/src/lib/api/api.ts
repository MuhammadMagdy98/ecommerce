// src/lib/axios.ts
import axios, { AxiosRequestConfig } from 'axios';
import { env } from '@/config/env';
import { convertObjectToQueryParams } from '@/utils/url';

const axiosInstance = axios.create({
  baseURL: env.API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
});

class Api {
  static async get(
    url: string,
    queryParams?: any,
    config: AxiosRequestConfig = {}
  ) {
    return axiosInstance.get(url + convertObjectToQueryParams(queryParams), {
      ...config,
    });
  }

  static async post(
    url: string,
    body?: any,
    queryParams?: any,
    config: AxiosRequestConfig = {}
  ) {
    return axiosInstance.post(
      url + convertObjectToQueryParams(queryParams),
      body,
      {
        ...config,
      }
    );
  }

  static async put(
    url: string,
    body?: any,
    queryParams?: any,
    config: AxiosRequestConfig = {}
  ) {
    return axiosInstance.put(
      url + convertObjectToQueryParams(queryParams),
      body,
      {
        ...config,
      }
    );
  }

  static async patch(
    url: string,
    body?: any,
    queryParams?: any,
    config: AxiosRequestConfig = {}
  ) {
    return axiosInstance.patch(
      url + convertObjectToQueryParams(queryParams),
      body,
      {
        ...config,
      }
    );
  }

  static async delete(
    url: string,
    queryParams?: any,
    config: AxiosRequestConfig = {}
  ) {
    return axiosInstance.delete(url + convertObjectToQueryParams(queryParams), {
      ...config,
    });
  }
}

export type HttpMethod = 'GET' | 'POST' | 'PUT' | 'DELETE' | 'PATCH';

export default Api;
