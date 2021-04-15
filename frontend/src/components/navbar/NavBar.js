import './NavBar.css'
import { Navbar, Nav, NavDropdown } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { useContext } from 'react';
import { Context } from '../../context/AuthContext';
import { useHistory } from 'react-router-dom';

function NavBar() {
    const { authenticated, handleLogout } = useContext(Context);
    const history = useHistory();

    function logout() {
        handleLogout();
        history.push('/login');
    }

    if (!authenticated) {
        return '';
    }

    return (
        <Navbar bg="light" expand="lg">
            <Navbar.Brand as={Link} to="/">Demo-thiagoporto</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Link as={Link} to="/">Home</Nav.Link>
                    <NavDropdown title="News" id="basic-nav-dropdown">
                        <NavDropdown.Item as={Link} to="/news">News around the world!</NavDropdown.Item>
                        <NavDropdown.Item as={Link} to="/my-news">My Saved News</NavDropdown.Item>
                    </NavDropdown>
                    <Nav.Link className="logoutLink" onClick={logout}>Logout</Nav.Link>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    )
}

export default NavBar;