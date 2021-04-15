import React, { createContext, useState, useEffect } from 'react';
import api from '../services/api';

const Context = createContext();

function AuthProvider({ children }) {
    const [authenticated, setAuthenticated] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('token');

        if (token) {
            api.defaults.headers.Authorization = `Bearer ${token}`;
            setAuthenticated(true);
        }

        setLoading(false);
    }, []);

    function handleLogin(email, password) {
        return api.post("signin", { email, password })
            .then(res => {
                localStorage.setItem('token', res.data);
                api.defaults.headers.Authorization = `Bearer ${res.data}`;
                setAuthenticated(true);
                return res;
            })
            .catch(error => {
                return error;
            });
    }

    function handleLogout() {
        localStorage.removeItem('token');
        api.defaults.headers.Authorization = undefined;
        setAuthenticated(false);
    }

    if (loading) {
        return <h1>Loading...</h1>;
    }

    return (
        <Context.Provider value={{ loading, authenticated, handleLogin, handleLogout }}>
            { children }
        </Context.Provider>
    )
}

export { Context, AuthProvider }; 