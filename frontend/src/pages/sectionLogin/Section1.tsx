import React from "react";
import { Button, Typography } from "@mui/material";

interface Props {
  onClick(value: number): void;
}

const Section1: React.FC<Props> = ({ onClick }: Props) => {
  return (
    <section>
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
          textAlign: "justify",
        }}
      >
        SkillSwap:
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
        Conecte-se com o conhecimento!
      </Typography>
      <Typography
        variant="body1"
        sx={{
          fontFamily: "Inter, sans-serif",
          fontWeight: 400,
          fontSize: "1.3rem",
          letterSpacing: "-0.02em",
          lineHeight: 1.6,
          maxWidth: "90%",
          width: "100%",
          paddingTop: "1rem",
        }}
      >
        Compartilhe seu conhecimento e aprenda com especialistas. Descubra
        habilidades novas e aprimore suas habilidades existentes.
      </Typography>
      <Button
        variant="contained"
        color="violet"
        size="large"
        sx={{ marginTop: "2rem" }}
        onClick={() => onClick(1)}
      >
        Entrar
      </Button>
      <Button
        variant="outlined"
        color="violet"
        size="large"
        sx={{ marginTop: "2rem", marginLeft: { xs: "0", md: "1rem" } }}
        onClick={() => onClick(2)}
      >
        Criar Conta
      </Button>
    </section>
  );
};

export default Section1;
