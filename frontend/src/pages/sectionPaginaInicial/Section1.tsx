import React from "react";
import { AppBar, Button, Container, IconButton, Toolbar } from "@mui/material";
import { motion } from "framer-motion";
import logo from "../../images/SkillswapLogo.png";
import { useNavigate } from "react-router-dom";

interface Props {
  button?: boolean;
}

const Section1: React.FC<Props> = ({ button = true }: Props) => {
  const navigate = useNavigate();

  return (
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
                onClick={() => navigate("/")}
              >
                <img src={logo} alt="Logo" width={64} height={64} />
              </IconButton>
              {button && (
                <Button
                  variant="contained"
                  color="violet"
                  size="large"
                  onClick={() => navigate("/login")}
                >
                  Junte-se ao Skillswap
                </Button>
              )}
            </Toolbar>
          </motion.div>
        </Container>
      </AppBar>
    </section>
  );
};

export default Section1;
