import React, { useState } from 'react';
import './SignUp.css';
import api from '../../services/api';
import { Container, Button, Form, Alert } from 'react-bootstrap';
import { Link } from 'react-router-dom';

function SignUp() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [showSuccessMessage, setShowSuccessMessage] = useState(false);
    const [validated, setValidated] = useState(false);

    function createUser(event) {
        event.preventDefault();
        const form = event.currentTarget;
        if (form.checkValidity() === true) {
            api.post("signup", { email, password })
                .then(res => {
                    setEmail('');
                    setPassword('');
                    setShowSuccessMessage(true);
                    setShowErrorMessage(false);
                    setValidated(false);
                })
                .catch(error => {
                    setErrorMessage(error.response.data);
                    setShowErrorMessage(true);
                    setShowSuccessMessage(false);
                });
        }

        setValidated(true);
    }

    function closeAlerts() {
        setShowErrorMessage(false);
        setShowSuccessMessage(false);
    }
    
    return (
        <Container className="signUpPanel">
            <Alert show={showSuccessMessage} 
                variant="success"
                dismissible="true"
                onClose={() => closeAlerts()}>
                User registered!
                <Link to="/">Login</Link>
            </Alert>
            <Alert show={showErrorMessage} 
                variant="danger"
                dismissible="true"
                onClose={() => closeAlerts()}>
                {errorMessage}
            </Alert>
            <Form noValidate validated={validated} onSubmit={createUser}>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" 
                        name="email" 
                        placeholder="Enter email"
                        value={email}
                        onChange={e => setEmail(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Please provide a valid e-mail.
                    </Form.Control.Feedback>
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" 
                        name="password" 
                        placeholder="Password"
                        value={password}
                        onChange={e => setPassword(e.target.value)} required />
                    <Form.Control.Feedback type="invalid">
                        Please provide a valid password.
                    </Form.Control.Feedback>
                </Form.Group>
                
                <Button variant="primary" type="submit">
                    Create
                </Button>
            </Form>
        </Container>
    );
}

export default SignUp;