import React, { useState, useContext, useEffect } from 'react';
import './Login.css';
import { Container, Button, Form, Alert } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { useHistory } from 'react-router-dom';
import { Context } from '../../context/AuthContext';

function Login() {
    const { authenticated, handleLogin } = useContext(Context);
    const history = useHistory();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [validated, setValidated] = useState(false);

    useEffect(() => {
        if (authenticated) {
            history.push('/');
        }
    }, [authenticated]);

    function login(event) {
        event.preventDefault();
        const form = event.currentTarget;
        if (form.checkValidity() === true) {
            handleLogin(email, password).then(res => {
                if (res.response && res.response.status == 400) {
                    setErrorMessage(res.response.data);
                    setShowErrorMessage(true);
                }
            });
        }

        setValidated(true);
    }

    function closeAlerts() {
        setShowErrorMessage(false);
    }

    return (
        <Container className="loginPanel">
            <Alert show={showErrorMessage} 
                variant="danger"
                dismissible="true"
                onClose={() => closeAlerts()}>
                {errorMessage}
            </Alert>
            <Form noValidate validated={validated} onSubmit={login}>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" 
                        name="email" 
                        placeholder="Enter email"
                        value={email}
                        onChange={e => setEmail(e.target.value)} required />
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" 
                        name="password" 
                        placeholder="Password"
                        value={password}
                        onChange={e => setPassword(e.target.value)} required />
                </Form.Group>
                <Form.Group controlId="formSignUpLink">
                    <Link to="/signUp">Sign up</Link>
                </Form.Group>
                
                <Button variant="primary" type="submit">
                    Login
                </Button>
            </Form>
        </Container>
    );
}

export default Login;