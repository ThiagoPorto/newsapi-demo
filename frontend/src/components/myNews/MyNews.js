import './MyNews.css';
import { useState, useEffect } from 'react';
import api from '../../services/api';
import { Container } from 'react-bootstrap';
import NewsList from '../newsList/NewsList';

function MyNews() {
    const [news, setNews] = useState([]);
    const [page, setPage] = useState(1);
    const [totalItems, setTotalItems] = useState(1);

    useEffect(() => {
        findMyNews(1);
    }, []);

    function findMyNews(page) {
        setPage(page);
        api.get(`/user-news/${page}`).then(res => {
            setNews(res.data);
            setTotalItems(Number(res.headers['x-total-count']));
        }).catch(error => {
            console.log(error);
        });
    }

    return (
        <Container>
            <h1>My News!</h1>
            <NewsList news={news} refreshList={findMyNews} isMyNews={true} totalItems={totalItems} page={page} />
        </Container>
    );
}

export default MyNews;