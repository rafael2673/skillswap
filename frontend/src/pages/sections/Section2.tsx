import React from "react";
import { Box, Button, Container, Typography } from "@mui/material";
import { motion } from "framer-motion";

const Section2: React.FC = () => (
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
              Skillswap é uma plataforma inovadora que conecta pessoas dentro da
              sua comunidade para que elas compartilhem seus conhecimentos e
              aprendam juntas, sem a necessidade de dinheiro. Imagine um mundo
              onde cada um pode ensinar o que sabe e aprender algo novo em
              troca, apenas por amor ao aprendizado!
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
);

export default Section2;
