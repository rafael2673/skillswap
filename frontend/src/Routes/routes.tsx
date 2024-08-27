import * as React from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import PaginaInicial from "../pages/PaginaInicial";
import Login from "../pages/Login";
import Timeline from "../pages/Timeline";
import Match from "../pages/Match";
import PrivateRoute from "./PrivateRoutes";

const router = createBrowserRouter([
  {
    path: "/",
    element: <PaginaInicial />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/timeline",
    element: (
      <PrivateRoute>
        <Timeline />
      </PrivateRoute>
    ),
  },
  {
    path: "/match",
    element: (
      <PrivateRoute>
        <Match />
      </PrivateRoute>
    ),
  },
]);

const Router: React.FC = () => <RouterProvider router={router} />;

export default Router;
