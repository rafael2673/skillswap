import React, { useState } from "react";
import { Box, Container, Grid, useMediaQuery, useTheme } from "@mui/material";
import Section1PaginaInicial from "./sectionPaginaInicial/Section1";
import Section1Login from "./sectionLogin/Section1";
import Section2Login from "./sectionLogin/Section2";
import Section3Login from "./sectionLogin/Section3";

const Login: React.FC = () => {
  const [value, setValue] = useState(0);

  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down("sm"));

  const onClick = (value: number) => {
    setValue(value);
  };

  return (
    <section
      style={{
        height: "100vh",
      }}
    >
      <Section1PaginaInicial button={false} />
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
              <Section2Login onClick={onClick} />
            ) : (
              <Section3Login onClick={onClick} />
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
