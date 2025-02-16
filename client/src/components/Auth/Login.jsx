import React from 'react';
import { useNavigate } from 'react-router-dom';
import AuthLayout from './AuthLayout.jsx';
import LoginForm from './LoginForm.jsx';

const Login = () => {
  const navigate = useNavigate();

  const handleLogin = () => {
    navigate('/home'); // Redireciona para a página inicial após o login
  };

  return (
    <AuthLayout>
      <LoginForm onLogin={handleLogin} />
    </AuthLayout>
  );
};

export default Login;