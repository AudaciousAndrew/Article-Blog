import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import '../../css/Article.css';


const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class ArticleMiniInfo extends Component{

    constructor(props){
        super(props);
    }

    render(){
        return(
            <div className="articleInfoMini">
                    <div className="articleNameMini">
                        <Link to={'/article/'+this.props.article.articleName}
                            articlename={this.props.article.articleName}>
                            {this.props.article.articleName}
                        </Link>
                            </div>
                <div className="articleNameMini">Тип: {this.props.article.articleType}</div>
                    <div className="smallDescMini">{this.props.article.small_desc}</div>
                    <div className="authorMini">
                        <label>Автор: </label>
                        <Link to={'/user/' + this.props.article.author}>
                            {this.props.article.author}
                        </Link>
                    </div>
                    <div className="ratingMini">Рейтинг: {this.props.article.rating}</div>
            </div>
        )
    }
}

export default ArticleMiniInfo;
