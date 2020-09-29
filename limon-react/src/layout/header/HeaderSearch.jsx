import React from 'react';
import {Grid, Search} from 'semantic-ui-react'

/**
 * 页面头的搜索框 目前不支持自定义渲染
 * @returns {*}
 * @constructor
 */
function HeaderSearch() {

    const [results, setResults] = React.useState([]);
    const [loading, setLoading] = React.useState(false);
    const [value, setValue] = React.useState('');


    React.useEffect(() => {
        const resultList = [
            {
                "title": "Rodriguez, Jones and Streich",
                "description": "Versatile mission-critical concept",
                "image": "https://s3.amazonaws.com/uifaces/faces/twitter/tobysaxon/128.jpg",
                "price": "$17.00"
            }
        ];

        setResults(resultList);
    }, [value]);


    function handleSearchChange(event, data) {
        setValue(event.target.value)
    }

    function selectResult(event, data) {
        console.log(data.result);
    }

    return (
        <Grid>
            <Grid.Column width={6}>
                <Search
                    loading={loading}
                    onResultSelect={(e, data) => selectResult(e, data)
                    }
                    onSearchChange={handleSearchChange}
                    results={results}
                    value={value}
                />
            </Grid.Column>
        </Grid>
    )
}

export default HeaderSearch