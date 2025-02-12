import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import notFoundImage from '../../assets/not-found.svg';
import './NotFound.css';

const NotFound = () => {
  useEffect(() => {
    document.title = '404 - Página Não Encontrada'; // Altera o título da página
  }, []);

  return (
    <div className="not-found">
      <div className="not-found-content">
        <img src={notFoundImage} alt="Página não encontrada" className="not-found-image" />
        <h1>404</h1>
        <h2>Página Não Encontrada</h2>
        <p>Oops! Parece que você se perdeu. A página que você está procurando não existe ou foi movida.</p>
        <Link to="/" className="home-link">
          <span className="home-icon">🏠</span> Voltar para a Página Inicial
        </Link>
      </div>
    </div>
  );
};

export default NotFound;