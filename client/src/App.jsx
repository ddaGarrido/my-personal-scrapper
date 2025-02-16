// src/App.jsx
import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ThemeProvider } from "./context/ThemeContext";
import AuthContext from "./context/AuthContext";
import Cookies from "js-cookie";
import Root from "./components/Root";
import Login from "./components/Auth/Login";
import Register from "./components/Auth/Register";
import Dashboard from "./components/Dashboard/dashboard";
import ProtectedRoute from "./components/Auth/ProtectedRoute";
import NotFound from "./pages/NotFound";

function App() {
  const [auth, setAuth] = useState(false);
  const [accountCreated, setAccountCreated] = useState(false);

  useEffect(() => {
    const user = Cookies.get("token");
    if (user) setAuth(true);
  }, []);

  // const router = createBrowserRouter(
  //   createRoutesFromElements(
  //     <Route path='/' element={<Root />}>
  //       <Route path='/login' element={<Login />} />
  //       <Route path='/register' element={<Register />} />
  //       <Route path="*" element={<NotFound />} />

  //       <Route path="/dashboard" element={<Dashboard />} />
  //     </Route>
  //   )
  // );

  return (
    <ThemeProvider>
      <AuthContext.Provider value={{ auth, setAuth, accountCreated, setAccountCreated }}>
        <Router>
          <Routes>
            <Route path="/" element={<Root />}>
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route
                path="/dashboard"
                element={
                  <ProtectedRoute>
                    <Dashboard />
                  </ProtectedRoute>
                }
              />
              <Route path="*" element={<NotFound />} />
            </Route>
          </Routes>
        </Router>
      </AuthContext.Provider>
    </ThemeProvider>
  );
}

export default App;