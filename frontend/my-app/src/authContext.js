import React, { createContext, useState, useContext, useEffect } from 'react';
import axios from 'axios';

const AuthContext = createContext();

export function AuthProvider({ children }) {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const storedUser = JSON.parse(localStorage.getItem('user'));
        if (storedUser) {
            setUser(storedUser);
        }
    }, []);

    const login = (name, password) => {
        return axios.post('http://localhost:8080/persons/login', { name, password })
            .then(response => {
                const user = response.data;
                localStorage.setItem('user', JSON.stringify(user));
                setUser(user);
            });
    };

    const register = (name, surname, password, role) => {
        return axios.post('http://localhost:8080/persons/register', { name, surname, password, role })
            .then(response => {
                const user = response.data;
                localStorage.setItem('user', JSON.stringify(user));
                setUser(user);
            });
    };

    const logout = () => {
        localStorage.removeItem('user');
        setUser(null);
    };

    const value = { user, login, register, logout };

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}
