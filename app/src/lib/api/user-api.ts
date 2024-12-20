import Api from './api';
import { ENDPOINTS } from '@/constants/Url';

interface LoginRequest {
  email: string;
  password: string;
}

interface RegisterRequest {
  name: string;
  email: string;
  password: string;
}
class UserApi extends Api {
  static async getUserDetails() {
    return Api.get(ENDPOINTS.AUTH.ME);
  }
  static async login(userData: LoginRequest) {
    return Api.post(ENDPOINTS.AUTH.LOGIN, userData);
  }
  static async register(userData: RegisterRequest) {
    return Api.post(ENDPOINTS.AUTH.REGISTER, userData);
  }
}

export default UserApi;
