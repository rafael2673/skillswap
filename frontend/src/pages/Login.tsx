import React, { useState } from "react";
import {
  Alert,
  Box,
  Container,
  Grid,
  useMediaQuery,
  useTheme,
} from "@mui/material";
import Section1PaginaInicial from "./sectionPaginaInicial/Section1";
import Section1Login from "./sectionLogin/Section1";
import Section2Login from "./sectionLogin/Section2";
import Section3Login from "./sectionLogin/Section3";
import LinearIndeterminate from "../Components/LinearIndeterminate";
import { useNavigate } from "react-router-dom";
import { AxiosError } from "axios";
import api from "../services/authservice";

const Login: React.FC = () => {
  const [value, setValue] = useState(0);
  const [alert, setAlert] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down("sm"));

  const onClick = (value: number) => {
    setValue(value);
  };

  const handleLogin = (email: string, password: string) => {
    setLoading(true);
    api
      .post("/login", {
        email,
        password,
      })
      .then((response) => {
        sessionStorage.setItem("accessToken", response.data.accessToken);
        const date = Date.now();
        const expirationTime = date + response.data.expiresIn * 1000;
        const refreshExpiresIn = date + response.data.refreshExpiresIn * 1000;
        sessionStorage.setItem("expirationTime", expirationTime.toString());
        sessionStorage.setItem("refreshToken", response.data.refreshToken);
        sessionStorage.setItem("refreshExpiresIn", refreshExpiresIn.toString());

        navigate("/timeline");
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
        setTimeout(() => {
          setAlert(null); 
        }, 5000);
      })
      .finally(() => setLoading(false));
  };

  return (
    <section
      style={{
        height: "100vh",
      }}
    >
      <Section1PaginaInicial button={false} />
      {loading && <LinearIndeterminate />}
      {alert && (
        <Alert
          severity="error"
          sx={{
            position: "fixed",
            margin: { xs: "13vh 0 0 14vw", md: "13vh 0 0 40vw" },
          }}
        >
          {alert}
        </Alert>
      )}
      <Container maxWidth="md">
        <Grid container spacing={isSmallScreen ? 0 : 2}>
          <Grid
            item
            xs={12}
            md={8}
            style={{
              padding: isSmallScreen ? "5em 1.5em 0 1.5em" : "5.75em 0 0 0",
            }}
          >
            {value === 0 ? (
              <Section1Login onClick={onClick} />
            ) : value === 1 ? (
              <Section2Login onClick={onClick} handleLogin={handleLogin} />
            ) : (
              <Section3Login
                onClick={onClick}
                handleLogin={handleLogin}
                setAlert={setAlert}
                setLoading={setLoading}
              />
            )}
          </Grid>
          {!isSmallScreen && (
            <Grid item md={4}>
              <Box
                sx={{
                  width: "100%",
                  height: "100vh",
                  backgroundImage:
                    "url(https://imgproxy.gamma.app/resize/quality:80/resizing_type:fit/width:400/blur:50/https://cdn.gamma.app/uoafjh6m5y7vvhw/52d5b9045651491e8955eb0aeaa337c5/original/team-of-diverse-professionals-sitting-at-a-table-shaking-hands-and-collaborating.jpg)",
                  backgroundPosition: "center",
                  backgroundSize: "cover",
                  backgroundRepeat: "no-repeat",
                  borderRadius: "0.35em",
                  padding: "2rem",
                  boxSizing: "border-box",
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                <img
                  src="https://imgproxy.gamma.app/resize/quality:80/resizing_type:fit/width:1200/https://cdn.gamma.app/uoafjh6m5y7vvhw/52d5b9045651491e8955eb0aeaa337c5/original/team-of-diverse-professionals-sitting-at-a-table-shaking-hands-and-collaborating.jpg"
                  alt="Pessoas Colaborando"
                  style={{
                    maxWidth: "100%",
                    borderRadius: "0.35em",
                  }}
                />
              </Box>
            </Grid>
          )}
        </Grid>
      </Container>
    </section>
  );
};

export default Login;
