import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import {
  createTheme,
  ThemeProvider,
  alpha,
  getContrastRatio,
} from "@mui/material/styles";

// Augment the palette to include a violet color
declare module "@mui/material/styles" {
  interface Palette {
    violet: Palette["primary"];
    white: Palette["primary"];
  }

  interface PaletteOptions {
    violet?: PaletteOptions["primary"];
    white?: Palette["primary"];
  }
}

// Update the Button's color options to include a violet option
declare module "@mui/material/Button" {
  interface ButtonPropsColorOverrides {
    violet: true;
    white: true;
  }
}

declare module "@mui/material/AppBar" {
  interface AppBarPropsColorOverrides {
    violet: true;
    white: true;
  }
}

const violetMain = "#6F42C1";

const theme = createTheme({
  palette: {
    violet: {
      main: violetMain,
      light: alpha(violetMain, 0.5),
      dark: alpha(violetMain, 0.9),
      contrastText:
        getContrastRatio(violetMain, "#fff") > 4.5 ? "#fff" : "#111",
    },
    white: {
      main: '#FFF',
      light: '#FFF', 
      dark: '#000',
      contrastText: '#000'
    }
  },
  typography: {
    fontFamily: 'Inter, sans-serif',
  },
  components: {
    // Name of the component
    MuiButton: {
      styleOverrides: {
        // Name of the slot
        root: {
          // Some CSS
          fontSize: '1.27rem',
          textTransform: 'inherit',
          borderRadius: '0.375em',
          fontWeight: 600
        },
      },
    },
  },
});

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <App />
    </ThemeProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
