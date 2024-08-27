import { Box, Typography } from "@mui/material";
import React from "react";

interface Props {
  number: string;
}

const BoxNumber: React.FC<Props> = ({ number }: Props) => (
  <Box
    sx={{
      width: { xs: 35.5, md: 40.5 },
      height: { xs: 35.5, md: 40.5 },
      borderRadius: 1,
      border: "0.0625em solid #c8bfd8ff",
      bgcolor: "#e2d9f2",
      display: "flex",
      flexDirection: "column",
      alignItems: "center",
      justifyContent: "center",
    }}
  >
    <Typography
      variant="body1"
      sx={{
        fontFamily: "Inter, sans-serif",
        fontWeight: 700,
        fontSize: { xs: "1.2em", md: "1.5em" },
        letterSpacing: "-0.03em",
        lineHeight: 1.6,
        maxWidth: "90%",
        width: "100%",
        textAlign: "center",
      }}
    >
      {number}
    </Typography>
  </Box>
);

export default BoxNumber;
