// src/pages/Home/index.jsx
import React, { useContext } from 'react';
import { AuthContext } from '../../context/AuthContext.js';
import './Home.css';

const Home = () => {
  const { isAuthenticated, logout } = useContext(AuthContext);

  if (!isAuthenticated) {
    return <p>Você não tem permissão para acessar esta página.</p>;
  }

  return (
    <div className="home">
      <h1>Bem-vindo à Página Inicial</h1>
      <p>Esta é a página inicial da aplicação.</p>
      <button onClick={logout}>Logout</button>
    </div>
  );
};

export default Home;