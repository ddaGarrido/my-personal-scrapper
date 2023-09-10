// const express = require('express');
// const cors = require('cors');
// const path = require('path');

// const app = express();

// // Middleware para permitir CORS
// app.use(cors());
// app.use(express.json());
// app.use(express.urlencoded({ extended: true }));


// // Middleware para servir arquivos estáticos do React
// app.use(express.static(path.join(__dirname, 'build')));

// // Exemplo de rota
// app.get('/api/test', (req, res) => {
//   res.json({ message: 'API funcionando!' });
// });

// app.get('/a', (req, res) => {
//   fetch('http://127.0.0.1:8080/web/connectors')
//     .then(response => {
//       if (!response.ok) {
//         throw new Error('Erro ao buscar dados');
//       }
//       return response.json();
//     })
//     .then(data => {
//       console.log(data);
//     })
//     .catch(error => {
//       console.log(error.message);
//     })
// res.json({ message: 'API funcionando!' });
// });

// // Rota para servir o frontend React
// app.get('*', (req, res) => {
//   console.log(1234523);
//   res.sendFile(path.join(__dirname, 'build', 'index.html'));
// });

// const PORT = 3001;
// app.listen(PORT, () => {
//   console.log(`Servidor rodando na porta ${PORT}`);
// });