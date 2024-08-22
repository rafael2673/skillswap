import * as React from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import PaginaInicial from "./pages/PaginaInicial";
import Login from "./pages/Login";

const router = createBrowserRouter([
    {
      path: "/",
      element: <PaginaInicial />,
    },
    {
        path: "/login",
        element: <Login />,
    },
  ]);

const Router: React.FC = () => <RouterProvider router={router}/>;

export default Router;
