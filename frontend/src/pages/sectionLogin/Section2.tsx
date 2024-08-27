import React, { useState } from "react";
import {
  Box,
  Button,
  FormControl,
  FormHelperText,
  IconButton,
  TextField,
  Typography,
} from "@mui/material";
import KeyboardBackspaceIcon from "@mui/icons-material/KeyboardBackspace";
import api from "../../services/api";
import { AxiosError } from "axios";
import { NavigateFunction } from "react-router-dom";

interface Props {
  onClick(value: number): void;
  setAlert: React.Dispatch<React.SetStateAction<string | null>>;
  setLoading: React.Dispatch<React.SetStateAction<boolean>>;
  navigate: NavigateFunction;
}

const Section2: React.FC<Props> = ({
  onClick,
  setAlert,
  setLoading,
  navigate,
}: Props) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {
    // Lógica de login aqui
    setLoading(true);
    api
      .post("/login", {
        email: email,
        password: password,
      })
      .then((response) => {
        sessionStorage.setItem("accessToken", response.data.accessToken);
        const date = Date.now();
        const expirationTime = date + response.data.expiresIn * 1000;
        const refreshExpiresIn = date + response.data.refreshExpiresIn * 1000;
        sessionStorage.setItem("expirationTime", expirationTime.toString());
        sessionStorage.setItem("refreshToken", response.data.refreshToken);
        sessionStorage.setItem("refreshExpiresIn", refreshExpiresIn.toString());

        navigate("/timeline")
      })
      .catch((error: AxiosError<{ message: string }>) => {
        if (error.response) {
          // Resposta com status fora do alcance 2xx
          const errorMessage =
            error.response.data?.message ||
            `Erro de status ${error.response.status}, tente novamente mais tarde!`;

          setAlert(errorMessage);
        } else if (error.request) {
          // Requisição foi feita, mas não houve resposta
          setAlert("Erro de requisição, tente novamente mais tarde!");
          console.error("Erro na requisição:", error.request);
        } else {
          // Algo aconteceu ao configurar a requisição
          setAlert(`Erro: ${error.message}`);
          console.error("Erro na configuração:", error.message);
        }
      })
      .finally(() => setLoading(false));
  };

  return (
    <Box
      sx={{
        height: { xs: "69vh", md: "75vh" },
        backgroundColor: "#f5f5f5",
        padding: "3rem",
      }}
    >
      <IconButton aria-label="retornar" onClick={() => onClick(0)}>
        <KeyboardBackspaceIcon />
      </IconButton>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
          height: "70vh",
        }}
      >
        <Box
          sx={{
            width: "100%",
            maxWidth: "400px",
            backgroundColor: "white",
            padding: "2rem",
            borderRadius: "10px",
            boxShadow: "0px 10px 30px rgba(0, 0, 0, 0.1)",
          }}
        >
          <Typography
            variant="h2"
            component="h2"
            gutterBottom
            sx={{
              fontFamily: "Inter, sans-serif",
              fontWeight: 700,
              fontSize: { xs: "2em", md: "2.5em" },
              letterSpacing: "-0.03em",
              lineHeight: 1.25,
              maxWidth: "90%",
              width: "100%",
              textAlign: "center",
              marginBottom: "2rem",
            }}
          >
            Login
          </Typography>
          <Box sx={{ display: "flex", justifyContent: "center" }}>
            <FormControl
              sx={{ width: "100%", maxWidth: "400px", textAlign: "center" }}
            >
              <TextField
                id="email-input"
                label="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                margin="normal"
                fullWidth
              />
              <FormHelperText id="email-helper-text">
                We'll never share your email.
              </FormHelperText>
              <TextField
                id="password-input"
                label="Password"
                type="password"
                placeholder="Enter your password" // Placeholder adicionado
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                margin="normal"
                fullWidth
              />
              <Button
                variant="contained"
                color="violet"
                onClick={handleLogin}
                sx={{ marginTop: "1rem" }}
              >
                Login
              </Button>
            </FormControl>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default Section2;
