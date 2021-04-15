import './NewsList.css';
import { useState, useEffect } from 'react';
import NoImage from '../../no_image.png';
import { HiHeart, HiOutlineHeart } from "react-icons/hi";
import { Container, Row, Col, Button, Image, Pagination } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';
import api from '../../services/api';
import { 
    WhatsappShareButton,
    WhatsappIcon,
    TwitterShareButton,
    TwitterIcon
 } from 'react-share';

function NewsList(props) {
    const history = useHistory();
    const [paginationItems, setPaginationItems] = useState(0);

    useEffect(() => {
        renderPagination(props.totalItems, props.page);
    }, [props.totalItems, props.page]);

    function viewNews(news) {
        history.push({
            pathname: '/news-view',
            state: { news }
        })
    }

    function saveNews(news) {
        api.post('/news', news).then(res => {
            props.refreshList(props.page);
        }).catch(error => {
            console.log(error);
        })
    }

    function removeSavedNews(news) {
        api.delete(`/news/${news.id}`).then(res => {
            props.refreshList(props.page);
        }).catch(error => {
            console.log(error);
        })
    }

    function renderPagination(totalItems, active = 1) {
        let items = [];
        for (let number = 1; number <= Math.ceil(totalItems / 10); number++) {
            items.push(
                <Pagination.Item key={number} active={number === active} onClick={() => { props.refreshList(number) }}>
                {number}
                </Pagination.Item>,
            );
        }
        setPaginationItems(items);
    }

    return (
        <Container>
            {props.news.length > 0 &&
                <Container className="newsTable">
                    <Pagination>
                        <Pagination>{paginationItems}</Pagination>
                    </Pagination>
                    <Row>
                        <Col xs={4}></Col>
                        <Col xs={2} className="newsHeadColumn">Title</Col>
                        <Col xs={4} className="newsHeadColumn">Description</Col>
                        <Col xs={1}></Col>
                        <Col xs={1}></Col>
                    </Row>
                    {props.news.map((item, i) => {     
                        return (
                            <Row key={i} className="newsRow">
                                <Col xs={4}>
                                    {item.urlToImage &&
                                        <Image src={item.urlToImage} thumbnail />
                                    }
                                    {!item.urlToImage &&
                                        <Image src={NoImage} thumbnail thumbnail />
                                    }
                                </Col>
                                <Col className="newsColumn" xs={2}>
                                    {item.title}
                                </Col>
                                <Col className="newsColumn" xs={4}>
                                    {item.description}
                                    <Button variant="link" onClick={() => viewNews(item)}>Read more...</Button>
                                </Col>
                                <Col className="heartColumn" xs={1}>
                                    {(item.saved || props.isMyNews ) &&
                                        <HiHeart className="heartIcon" onClick={() => removeSavedNews(item)} />
                                    }
                                    {(!item.saved && !props.isMyNews) &&
                                        <HiOutlineHeart className="outlineHeartIcon" onClick={() => saveNews(item)} />
                                    }
                                </Col>
                                <Col className="heartColumn" xs={1}>
                                    <WhatsappShareButton url={item.url}>
                                        <WhatsappIcon size={25} />
                                    </WhatsappShareButton>
                                    <TwitterShareButton url={item.url}>
                                        <TwitterIcon size={25} />
                                    </TwitterShareButton>
                                </Col>
                            </Row>
                        ) 
                    })}
                    <Pagination>
                        <Pagination>{paginationItems}</Pagination>
                    </Pagination>
                </Container>
            }
        </Container>
    );
}

export default NewsList;