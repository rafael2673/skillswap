import * as React from "react";
import Box from "@mui/material/Box";
import LinearProgress from "@mui/material/LinearProgress";

export default function LinearIndeterminate() {
  return (
    <Box sx={{ width: "100%" }}>
      <LinearProgress
        sx={{
          position: "fixed",
          margin: { xs: "13vh 0 0 14vw", md: "13vh 0 0 40vw" },
        }}
      />
    </Box>
  );
}
