// src/routes.js
import Home from './pages/Home';
import About from './pages/About';
import NotFound from './pages/NotFound';

const routes = [
  {
    path: '/',
    element: <Home />,
  },
  {
    path: '/about',
    element: <About />,
  },
  {
    path: '*',
    element: <NotFound />,
  }
];

export default routes;