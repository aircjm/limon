import React from 'react';
import {Grid, Header, Search, Segment} from 'semantic-ui-react'


function HeaderSearch() {

    const [results, setResults] = React.useState([]);
    const [loading, setLoading] = React.useState(false);
    const [value, setValue] = React.useState('');


    function handleSearchChange(event, data) {
        console.log(event);
        console.log(data);
        const resultList = [
            {
                "title": "Rodriguez, Jones and Streich",
                "description": "Versatile mission-critical concept",
                "image": "https://s3.amazonaws.com/uifaces/faces/twitter/tobysaxon/128.jpg",
                "price": "$17.00"
            },
            {
                "title": "Weimann, Keeling and Turner",
                "description": "Organic actuating pricing structure",
                "image": "https://s3.amazonaws.com/uifaces/faces/twitter/jay_wilburn/128.jpg",
                "price": "$87.87"
            },
            {
                "title": "Paucek, Moen and Carter",
                "description": "Business-focused global Graphical User Interface",
                "image": "https://s3.amazonaws.com/uifaces/faces/twitter/stayuber/128.jpg",
                "price": "$87.30"
            },
            {
                "title": "Bartell, Padberg and Walsh",
                "description": "Business-focused context-sensitive toolset",
                "image": "https://s3.amazonaws.com/uifaces/faces/twitter/prheemo/128.jpg",
                "price": "$53.37"
            },
            {
                "title": "Kulas, Reilly and Nitzsche",
                "description": "Cloned bi-directional infrastructure",
                "image": "https://s3.amazonaws.com/uifaces/faces/twitter/dactrtr/128.jpg",
                "price": "$92.53"
            }
        ];
        setResults(resultList)
    }

    return (
        <Grid>
            <Grid.Column width={6}>
                <Search
                    loading={loading}
                    onResultSelect={(e, data) =>
                        dispatch({type: 'UPDATE_SELECTION', selection: data.result.title})
                    }
                    onSearchChange={handleSearchChange}
                    results={results}
                    value={value}
                />
            </Grid.Column>

            <Grid.Column width={10}>
                <Segment>
                    <Header>State</Header>
                    <pre style={{overflowX: 'auto'}}>
            {JSON.stringify({loading, results, value}, null, 2)}
          </pre>
                    <Header>Options</Header>
                    <pre style={{overflowX: 'auto'}}>
            {JSON.stringify(source, null, 2)}
          </pre>
                </Segment>
            </Grid.Column>
        </Grid>
    )
}

export default HeaderSearch