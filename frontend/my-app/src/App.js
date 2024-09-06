import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import BusinessDashboard from './components/BusinessDashboard';
import CustomerDashboard from './components/CustomerDashboard';
import Header from './components/Header';
import { AuthProvider, useAuth } from './authContext';

function App() {
    return (
        <AuthProvider>
            <Router>
                <Header />
                <div className="container">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Register />} />
                        <Route path="/dashboard/*" element={<AuthRoutes />} />
                    </Routes>
                </div>
            </Router>
        </AuthProvider>
    );
}

function AuthRoutes() {
    const { user } = useAuth();
    return (
        <Routes>
            {user ? (
                <>
                    {user.role === 'business' ? (
                        <Route path="/" element={<BusinessDashboard />} />
                    ) : (
                        <Route path="/" element={<CustomerDashboard />} />
                    )}
                    <Route path="*" element={<Navigate to="/" />} />
                </>
            ) : (
                <Route path="*" element={<Navigate to="/login" />} />
            )}
        </Routes>
    );
}

export default App;
