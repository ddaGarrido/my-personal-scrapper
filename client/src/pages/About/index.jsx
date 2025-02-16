// src/pages/About/index.jsx
import React, { useContext } from 'react';
import { AuthContext } from '../../context/AuthContext.js';
import './About.css';

const About = () => {
  const { isAuthenticated, logout } = useContext(AuthContext);

  if (!isAuthenticated) {
    logout();
    return <p>Você não tem permissão para acessar esta página.</p>;
  }

  return (
    <div className="about">
      <h1>Sobre Nós</h1>
      <p>Esta é a página sobre a nossa aplicação.</p>
    </div>
  );
};

export default About;