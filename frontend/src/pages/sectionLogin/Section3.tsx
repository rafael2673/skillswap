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

interface Props {
  onClick(value: number): void;
  handleLogin(email: string, password: string): void;
  setAlert: React.Dispatch<React.SetStateAction<string | null>>;
  setLoading: React.Dispatch<React.SetStateAction<boolean>>;
}

const Section3: React.FC<Props> = ({
  onClick,
  handleLogin,
  setAlert,
  setLoading,
}: Props) => {
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSignUp = () => {
    // Lógica de criação de conta aqui
    api
      .post("/register", {
        username,
        email,
        password,
      })
      .then(() => {
        handleLogin(email, password);
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
        height: { xs: "80vh", md: "75vh" },
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
          height: "69vh",
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
            variant="h4"
            component="h1"
            gutterBottom
            sx={{
              fontFamily: "Inter, sans-serif",
              fontWeight: 700,
              fontSize: "1.75em",
              textAlign: "center",
              marginBottom: "1.5rem",
            }}
          >
            Criar Conta
          </Typography>
          <FormControl sx={{ width: "100%", marginBottom: "1.5rem" }}>
            <TextField
              id="email-input"
              label="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              margin="normal"
              fullWidth
            />
            <FormHelperText id="email-helper-text">
              Insira um email válido.
            </FormHelperText>
          </FormControl>
          <FormControl sx={{ width: "100%", marginBottom: "1.5rem" }}>
            <TextField
              id="username-input"
              label="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              margin="normal"
              fullWidth
            />
          </FormControl>
          <FormControl sx={{ width: "100%", marginBottom: "1.5rem" }}>
            <TextField
              id="password-input"
              label="Password"
              type="password"
              placeholder="Mínimo 8 caracteres"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              margin="normal"
              fullWidth
            />
          </FormControl>
          <Button
            variant="contained"
            color="violet"
            onClick={handleSignUp}
            sx={{ marginTop: "1rem", width: "100%" }}
          >
            Criar Conta
          </Button>
        </Box>
      </Box>
    </Box>
  );
};

export default Section3;
