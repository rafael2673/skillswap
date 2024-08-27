import React from "react";
import { Navigate } from "react-router-dom";
import api from "../services/api";

interface PrivateRouteProps {
  children: JSX.Element;
}

const PrivateRoute: React.FC<PrivateRouteProps> = ({ children }) => {
  function isTokenValid() {
    const refreshToken = sessionStorage.getItem("refreshToken");
    if (!refreshToken) return false;

    const expirationTime = sessionStorage.getItem("expirationTime");
    const refreshExpiresIn = sessionStorage.getItem("refreshExpiresIn");
    if (
      Date.now() > Number(expirationTime) &&
      Date.now() < Number(refreshExpiresIn)
    ) {
      sessionStorage.removeItem("accessToken");
      sessionStorage.removeItem("expirationTime");

      api
        .post("/refresh-token", {
          refreshToken: refreshToken,
        })
        .then((response) => {
          sessionStorage.setItem("accessToken", response.data.accessToken);
          const expirationTime = Date.now() + response.data.expiresIn * 1000;
          sessionStorage.setItem("expirationTime", expirationTime.toString());
        });
    } else if (
      Date.now() > Number(expirationTime) &&
      Date.now() > Number(refreshExpiresIn)
    ) {
      sessionStorage.removeItem("refreshToken");
      sessionStorage.removeItem("expirationTime");
      sessionStorage.removeItem("refreshExpiresIn");
      sessionStorage.removeItem("accessToken");
      return false;
    } else {
      return true;
    }

    // Verifica se o tempo atual é menor que o tempo de expiração
  } // Verifica se o token está armazenado

  return isTokenValid() ? children : <Navigate to="/login" />;
};

export default PrivateRoute;
