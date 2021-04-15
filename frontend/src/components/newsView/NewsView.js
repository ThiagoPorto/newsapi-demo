import './NewsView.css';
import { Container, Image, Button } from 'react-bootstrap';
import NoImage from '../../no_image.png';
import moment from 'moment'
import { Link } from 'react-router-dom';
import { useHistory } from 'react-router-dom';

function NewsView(props) {
    const history = useHistory();

    function getFormattedDate(publishedAt) {
        return moment(publishedAt).format('MM/DD/YYYY HH:mm');
    }

    function goBack() {
        history.goBack();
    }

    return (
        <Container className="newsContainer">
            <Button variant="link" onClick={() => goBack()}>Back</Button>
            <h1 className="newsTitle">{props.location.state.news.title}</h1>
            <h2 className="newsDescription">{props.location.state.news.description}</h2>
            <strong>{props.location.state.news.author}</strong>
            <p>{getFormattedDate(props.location.state.news.publishedAt)}</p>
            <Link to={{ pathname: props.location.state.news.url }} target="_blank">Source</Link>
            <p className="newsContent">{props.location.state.news.content}</p>
            <div>
                {props.location.state.news.urlToImage &&
                    <Image src={props.location.state.news.urlToImage} thumbnail />
                }
                {!props.location.state.news.urlToImage &&
                    <Image src={NoImage} thumbnail thumbnail />
                }
            </div>
        </Container>
    );
}

export default NewsView;