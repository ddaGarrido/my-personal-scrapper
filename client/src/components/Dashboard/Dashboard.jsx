// src/components/Dashboard/Dashboard.jsx
import React, { useContext } from "react";
import { Box, Typography } from "@mui/material";
import AuthContext from "../../context/AuthContext";

const Dashboard = () => {
  const { auth } = useContext(AuthContext);

  return (
    <Box sx={{ p: 3, textAlign: "center" }}>
      <Typography variant="h4">Bem-vindo ao Dashboard</Typography>
      <Typography variant="body1">{auth ? "Você está autenticado!" : "Acesso negado."}</Typography>
    </Box>
  );
};

export default Dashboard;
