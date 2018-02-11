import React, { Component } from 'react';
import '../../css/Article.css';
import ArticleMiniInfo from './ArticleMiniInfo';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

export default class ArticlesList extends Component{

    render(){
        let list = this.props.articles.map((el, index) => {
            return <ArticleMiniInfo key={index} article={el} />
        });
        return(
            <div className="articlesList">
                {list}
            </div>
        )
    }
}

//          <div dangerouslySetInnerHTML={{__html: this.state.text}} />
