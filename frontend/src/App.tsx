import React from "react";
import "./App.css";
import {
  AppBar,
  Box,
  Button,
  Container,
  Grid,
  IconButton,
  Toolbar,
  Typography,
} from "@mui/material";
import { motion } from "framer-motion";
import logo from "./images/SkillswapLogo.png";
import Session2Component from "./Components/Session2Component";

const App: React.FC = () => (
  <Box sx={{ flexGrow: 1 }}>
    <section>
      <AppBar position="fixed" color="white" sx={{ height: "75px" }}>
        <Container maxWidth="lg">
          <motion.div
            initial={{ opacity: 0, y: 5 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 1 }}
          >
            <Toolbar sx={{ justifyContent: "space-between" }}>
              <IconButton
                edge="start"
                color="inherit"
                aria-label="menu"
                sx={{ mr: 2 }}
                disableRipple
              >
                <img src={logo} alt="Logo" width={64} height={64} />
              </IconButton>
              <Button variant="contained" color="violet" size="large">
                Junte-se ao Skillswap
              </Button>
            </Toolbar>
          </motion.div>
        </Container>
      </AppBar>
    </section>

    <section
      style={{
        height: "100vh",
        overflow: "hidden",
        color: "white",
        scrollBehavior: "smooth",
        paddingTop: "75px",
      }}
    >
      <Box
        sx={{
          position: "relative",
          width: "100%",
          height: "100%",
          background: "#000",
        }}
      >
        <motion.div
          initial={{ opacity: 0, scale: 0.9 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ duration: 0.5 }}
        >
          <img
            src="https://imgproxy.gamma.app/resize/quality:80/resizing_type:fit/width:2400/https://cdn.gamma.app/uoafjh6m5y7vvhw/3a70006ea8c940b2b016192328b8a2c5/original/amb-1.jpg"
            alt="background"
            style={{ width: "100%", height: "100vh", objectFit: "cover" }}
          />
        </motion.div>
        <Box
          sx={{
            position: "absolute",
            top: 0,
            left: 0,
            width: "100%",
            height: "100%",
            backgroundColor: "#000000cc",
            zIndex: 1,
            display: "flex",
            flexDirection: "column",
            alignItems: { xs: "center", md: "flex-start" },
            justifyContent: "center",
            padding: { xs: "23rem 2rem 23rem 2rem", md: "4rem" },
            boxSizing: "border-box",
          }}
        >
          <Container maxWidth="md">
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 1 }}
            >
              <Typography
                variant="h1"
                component="h1"
                gutterBottom
                sx={{
                  fontFamily: "Inter, sans-serif",
                  fontWeight: 600,
                  fontSize: { xs: "2.25em", md: "4.05em" },
                  letterSpacing: "-0.03em",
                  lineHeight: 1.25,
                  maxWidth: "90%",
                  width: "100%",
                }}
              >
                Skillswap:
              </Typography>
              <Typography
                variant="h1"
                component="h1"
                gutterBottom
                sx={{
                  fontFamily: "Inter, sans-serif",
                  fontWeight: 600,
                  fontSize: { xs: "2.25em", md: "4.05em" },
                  letterSpacing: "-0.03em",
                  lineHeight: 1.25,
                  maxWidth: "90%",
                  width: "100%",
                }}
              >
                Compartilhe suas habilidades, aprenda algo novo.
              </Typography>
              <Typography
                variant="body1"
                sx={{
                  fontFamily: "Inter, sans-serif",
                  fontWeight: 400,
                  fontSize: "1.2rem",
                  letterSpacing: "-0.02em",
                  lineHeight: 1.6,
                  maxWidth: "90%",
                  width: "100%",
                  paddingTop: "1rem",
                }}
              >
                Skillswap é uma plataforma inovadora que conecta pessoas dentro
                da sua comunidade para que elas compartilhem seus conhecimentos
                e aprendam juntas, sem a necessidade de dinheiro. Imagine um
                mundo onde cada um pode ensinar o que sabe e aprender algo novo
                em troca, apenas por amor ao aprendizado!
              </Typography>
            </motion.div>
            <motion.div
              initial={{ opacity: 0 }}
              whileInView={{ opacity: 1 }}
              viewport={{ once: true }}
            >
              <Button
                variant="contained"
                color="violet"
                size="large"
                sx={{ marginTop: "2rem" }}
              >
                Junte-se ao Skillswap
              </Button>
            </motion.div>
          </Container>
        </Box>
      </Box>
    </section>
    <section
      style={{
        height: "100vh",
      }}
    >
      <Box
        sx={{
          width: "100%",
          zIndex: 1,
          display: "flex",
          flexDirection: "column",
          alignItems: { xs: "center", md: "flex-start" },
          justifyContent: "center",
          padding: "1rem",
          paddingLeft: { xs: "1rem", md: "0.6625em" },
          paddingBottom: { xs: "1rem", md: "4.75em" },
          boxSizing: "border-box",
        }}
      >
        <Container maxWidth="md">
          <Grid container spacing={2} alignItems="center">
            <Grid xs={12} md={6} item>
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
                }}
              >
                Despertando o potencial da comunidade
              </Typography>
              <Session2Component
                number="1"
                titulo="Construindo pontes"
                texto="SkillSwap une pessoas de diferentes idades, origens e áreas
                    de interesse, criando um ambiente vibrante e enriquecedor
                    para o aprendizado colaborativo. Através da troca de
                    conhecimentos, a comunidade se fortalece e se torna mais
                    conectada."
              />
              <Session2Component
                number="2"
                titulo="Compartilhando conhecimento"
                texto="Incentivamos o aprendizado mútuo, valorizando 
                o conhecimento individual e coletivo. Através do 
                compartilhamento de habilidades, todos se beneficiam 
                e contribuem para o crescimento da comunidade."
              />
              <Session2Component
                number="3"
                titulo="Cultivando a paixão pelo aprendizado"
                texto="SkillSwap é um espaço para explorar novas paixões, 
                desenvolver hobbies e fortalecer habilidades já existentes. 
                O aprendizado se torna uma jornada divertida e enriquecedora, 
                impulsionada pelo desejo de aprender e compartilhar."
              />
            </Grid>
            <Grid xs={12} md={6} item>
              <Box
                sx={{
                  width: "100%",
                  height: "100vh",
                  backgroundImage:
                    "url(https://imgproxy.gamma.app/resize/quality:80/resizing_type:fit/width:400/blur:50/https://cdn.gamma.app/uoafjh6m5y7vvhw/0d255a93bc3743398bfafef6d2ef57f7/original/conjunto-de-retratos-de-pessoas-felizes-de-diferentes-idades-e-nacionalidades_116547-21899.jpg)",
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
                  src="https://imgproxy.gamma.app/resize/quality:80/resizing_type:fit/width:1200/https://cdn.gamma.app/uoafjh6m5y7vvhw/0d255a93bc3743398bfafef6d2ef57f7/original/conjunto-de-retratos-de-pessoas-felizes-de-diferentes-idades-e-nacionalidades_116547-21899.jpg"
                  alt="Pessoas Felizes de diferentes idades e nacionalidades"
                  style={{
                    maxWidth: "100%",
                    borderRadius: "0.35em",
                  }}
                />
              </Box>
            </Grid>
          </Grid>
        </Container>
      </Box>
    </section>
  </Box>
);

export default App;
