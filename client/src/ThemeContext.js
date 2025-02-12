// src/context/ThemeContext.js
import React, { createContext, useState, useEffect } from 'react';

export const ThemeContext = createContext();

export const ThemeProvider = ({ children }) => {
  const [isDarkMode, setIsDarkMode] = useState(true); // Tema dark por padrão

  // Função para alternar o tema
  const toggleTheme = () => {
    setIsDarkMode(!isDarkMode);
  };

  // Aplicar o tema ao body
  useEffect(() => {
    const root = document.documentElement;
    if (isDarkMode) {
      root.style.setProperty('--background', 'var(--background-dark)');
      root.style.setProperty('--surface', 'var(--surface-dark)');
      root.style.setProperty('--text', 'var(--text-dark)');
      root.style.setProperty('--primary', 'var(--primary-dark)');
      root.style.setProperty('--secondary', 'var(--secondary-dark)');
      root.style.setProperty('--hover', 'var(--hover-dark)');
    } else {
      root.style.setProperty('--background', 'var(--background-light)');
      root.style.setProperty('--surface', 'var(--surface-light)');
      root.style.setProperty('--text', 'var(--text-light)');
      root.style.setProperty('--primary', 'var(--primary-light)');
      root.style.setProperty('--secondary', 'var(--secondary-light)');
      root.style.setProperty('--hover', 'var(--hover-light)');
    }
  }, [isDarkMode]);

  return (
    <ThemeContext.Provider value={{ isDarkMode, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
};