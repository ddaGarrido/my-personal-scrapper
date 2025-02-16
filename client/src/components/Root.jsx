// src/components/Root.jsx
import { Outlet, Link } from "react-router-dom";
import { Box, Typography, Button, AppBar, Toolbar, IconButton } from "@mui/material";
import { Brightness4, Brightness7 } from "@mui/icons-material";
import { useContext } from "react";
import { ThemeContext } from "../context/ThemeContext";

function Root() {
  const { isDarkMode, toggleTheme } = useContext(ThemeContext);

  return (
    <Box sx={{ minHeight: "100vh", bgcolor: "background.default", color: "text.primary" }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>
            Hub de Aplicações
          </Typography>
          <Button component={Link} to="/login" color="inherit">
            Login
          </Button>
          <Button component={Link} to="/register" color="inherit">
            Registrar
          </Button>
          <IconButton onClick={toggleTheme} color="inherit">
            {isDarkMode ? <Brightness7 /> : <Brightness4 />}
          </IconButton>
        </Toolbar>
      </AppBar>
      <Outlet />
    </Box>
  );
}

export default Root;
