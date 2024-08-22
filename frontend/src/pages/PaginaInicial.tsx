import React from "react";
import { Box } from "@mui/material";
import Section1 from "./sectionPaginaInicial/Section1";
import Section2 from "./sectionPaginaInicial/Section2";
import Section3 from "./sectionPaginaInicial/Section3";

const PaginaInicial: React.FC = () => (
  <Box sx={{ flexGrow: 1 }}>
    <Section1 />
    <Section2 />
    <Section3 />
  </Box>
);

export default PaginaInicial;
