
import React, {useState} from 'react';
import {Icon} from "semantic-ui-react";
import LoginStatus from "../../../components/hooks/LoginStatus";

const Search = () => {

  const [openSearch, setOpenSearch] = useState(true);
  const loginStatus = LoginStatus();

  if(!loginStatus) return null;
  return (
    <React.Fragment>
        <Icon
            title='新建'
            name='search'
            style={{margin: 0}}
        />
    </React.Fragment>
  )
}
export default Search;
