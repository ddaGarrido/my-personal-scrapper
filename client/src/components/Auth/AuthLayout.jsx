import React from 'react';
import './AuthLayout.css';

const AuthLayout = ({ children }) => {
  return (
    <div className="auth-layout">
      <header className="auth-header">
        <h1>Bem-vindo ao Meu App</h1>
        <p>Faça login para acessar o sistema</p>
      </header>
      <main className="auth-main">{children}</main>
      <footer className="auth-footer">
        <p>© 2023 Meu App. Todos os direitos reservados.</p>
      </footer>
    </div>
  );
};

export default AuthLayout;