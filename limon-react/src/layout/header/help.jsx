import React from 'react';
import {Dropdown, Icon} from "semantic-ui-react";

const Help = () => {

    return (
        <Dropdown pointing='top right'
                  trigger={
                      <Icon
                          name='help circle'
                          title='主题'
                          style={{margin: 0}}
                      />
                  }
        >
            <Dropdown.Menu>
                <Dropdown.Item as="a" target="_blank" href={`https://github.com/aircjm`} icon='github' text='Github'/>
            </Dropdown.Menu>
        </Dropdown>
    )
}
export default Help;
