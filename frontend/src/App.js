import { useContext } from 'react';
import './App.css';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';
import Login from './components/login/Login';
import SignUp from './components/signUp/SignUp';
import NewsSearch from './components/newsSearch/NewsSearch';
import NewsView from './components/newsView/NewsView';
import Home from './components/home/Home';
import MyNews from './components/myNews/MyNews';
import { AuthProvider, Context } from './context/AuthContext';
import NavBar from './components/navbar/NavBar';

function CustomRoute({ isPrivate, ...rest }) {
  const { authenticated } = useContext(Context);

  if (isPrivate && !authenticated) {
    return <Redirect to="/login" />;
  }

  return <Route {...rest} />;
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
      </header>
      <AuthProvider>
        <BrowserRouter>
          <NavBar />
          <Switch>
              <CustomRoute path="/login" exact component={Login} />
              <CustomRoute path="/signUp" component={SignUp} />
              <CustomRoute isPrivate path="/" exact component={Home} />
              <CustomRoute isPrivate path="/news" component={NewsSearch} />
              <CustomRoute isPrivate path="/news-view" component={NewsView} />
              <CustomRoute isPrivate path="/my-news" component={MyNews} />
          </Switch>
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
}

export default App;
