import './NewsSearch.css';
import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import api from '../../services/api';
import NewsList from '../newsList/NewsList';

function NewsSearch() {
    const [countries, setCountries] = useState([]);
    const [categories, setCategories] = useState([]);
    const [resetFilters, setResetFilters] = useState(false);

    // filters
    const [selectedCountry, setSelectedCountry] = useState('');
    const [selectedCategory, setSelectedCategory] = useState('');
    const [newsContent, setNewsContent] = useState('');
    
    // list of searched news
    const [news, setNews] = useState([]);

    const [page, setPage] = useState(1);
    const [totalItems, setTotalItems] = useState(1);

    useEffect(() => {
        if (countries.length === 0) {
            api.get('/countries').then(res => {
                setCountries(res.data);
            }).catch(error => {
                console.log(error);
            });
        }
        
        if (categories.length === 0) {
            api.get('/categories').then(res => {
                setCategories(res.data);
            }).catch(error => {
                console.log(error);
            });
        }

        if (resetFilters) {
            search(1);
            setResetFilters(false);
        }
    }, [resetFilters]);

    function search(page) {
        setPage(page);
        let queryParams = '';

        selectedCountry ? queryParams += '?country=' + selectedCountry : queryParams += '?country=us';

        if (selectedCategory) {
            queryParams += '&category=' + selectedCategory;
        }

        if (newsContent) {
            queryParams += '&query=' + newsContent;
        }

        queryParams += '&page=' + page;

        api.get('/news' + queryParams).then(res => {
            setNews(res.data.articles);
            setTotalItems(res.data.totalResults);
        }).catch(error => {
            console.log(error);
        });
    }

    function resetFilter() {
        setSelectedCountry('');
        setSelectedCategory('');
        setNewsContent('');
        setResetFilters(true);
    }

    function handleChange(selectedOption) {
        if (selectedOption.target.name === 'countryFilter') {
            setSelectedCountry(selectedOption.target.value);
        } else {
            setSelectedCategory(selectedOption.target.value);
        }
    };

    return (
        <Container>
            <h1>News</h1>
            <Row className="justify-content-md-center">
                <Col xs lg="3">
                    <Form.Group>
                        <Form.Label>Country</Form.Label>
                        <Form.Control as="select" onChange={handleChange} name="countryFilter" value={selectedCountry}>
                            <option value="">Select</option>
                            {countries.map((item) => {     
                                return (<option key={item.id} value={item.initials}>{item.name}</option>) 
                            })}
                        </Form.Control>
                    </Form.Group>
                </Col>
                <Col xs lg="3">
                    <Form.Group>
                        <Form.Label>Category</Form.Label>
                        <Form.Control as="select" onChange={handleChange} name="categoryFilter" value={selectedCategory}>
                            <option value="">Select</option>
                            {categories.map((item) => {     
                                return (<option key={item.id} value={item.name}>{item.name}</option>) 
                            })}
                        </Form.Control>
                    </Form.Group>
                </Col>
                <Col xs lg="3">
                    <Form.Group controlId="formQueryString">
                        <Form.Label>News Content</Form.Label>
                        <Form.Control type="newsContent" 
                            name="newsContent"
                            placeholder="Type part of the news"
                            value={newsContent}
                            onChange={e => setNewsContent(e.target.value)} required />
                    </Form.Group>
                </Col>
            </Row>
            <Row className="justify-content-md-center">
                <Col xs lg="1">
                    <Button onClick={() => search(1)}>Search</Button>
                </Col>
                <Col xs lg="2">
                    <Button onClick={() => resetFilter()}>Reset filters</Button>
                </Col>
            </Row>
            <NewsList news={news} refreshList={search} totalItems={totalItems} page={page} />
        </Container>
    );
}

export default NewsSearch;