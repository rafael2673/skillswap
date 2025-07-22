import { Grid, Typography } from "@mui/material";
import React from "react";
import BoxNumber from "./BoxNumber";

interface Props {
  number: string;
  titulo: string;
  texto: string;
}

const Session2Component: React.FC<Props> = ({
  number,
  titulo,
  texto,
}: Props) => (
  <Grid container spacing={2} sx={{ padding: "20.25px 0 0 0" }}>
    <Grid
      xs={2}
      item
      sx={{
        paddingRight: {
          xs: "2rem",
          md: "1rem",
        },
      }}
    >
      <BoxNumber number={number} />
    </Grid>
    <Grid xs={10} item>
      <Typography
        variant="h5"
        sx={{
          fontSize: "1.25em",
          fontWeight: 700,
        }}
      >
        {titulo}
      </Typography>
      <Typography textAlign="justify" padding="8px 0 0 0">{texto}</Typography>
    </Grid>
  </Grid>
);

export default Session2Component;
