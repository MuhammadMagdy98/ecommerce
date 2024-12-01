import Cookies from "js-cookie";

export const COOKIE_NAME = "auth_token";

export const cookieUtils = {
  setToken: (token: string) => {
    Cookies.set(COOKIE_NAME, token, {
      expires: 7, // 7 days
      secure: true,
      sameSite: "strict",
      path: "/",
    });
  },

  getToken: () => {
    return Cookies.get(COOKIE_NAME);
  },

  removeToken: () => {
    Cookies.remove(COOKIE_NAME);
  },
};
