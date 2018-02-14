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
                    <Link to={'article/'+this.props.article.articleName}
                          articlename={this.props.article.articleName}>
                        {this.props.article.articleName}
                    </Link>
                </div>
            </div>
        )
    }
}

export default ArticleMiniInfo;
