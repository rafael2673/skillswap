import React from "react";
import { Box } from "@mui/material";
import Section1 from "./sections/Section1";
import Section2 from "./sections/Section2";
import Section3 from "./sections/Section3";

const PaginaInicial: React.FC = () => (
  <Box sx={{ flexGrow: 1 }}>
    <Section1 />
    <Section2 />
    <Section3 />
  </Box>
);

export default PaginaInicial;
