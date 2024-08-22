import React from "react";
import { Box, Container, Grid, Typography } from "@mui/material";
import Session2Component from "../../Components/Session2Component";

const Section3: React.FC = () => (
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
);

export default Section3;
