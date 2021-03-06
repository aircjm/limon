import React from 'react';
import {Card, Image} from "semantic-ui-react";
import styled from 'styled-components'
import {Link} from "react-router-dom";
import {StyledDescription} from "../styled/common";

const StyledA = styled.a`
     margin-bottom: 20px;
   img {
    height: 140px;
   }
`
const StyledLink = styled(Link)`
   margin-bottom: 20px;
   img {
    height: 140px;
   }
`
const CardItem = (props) => {
    console.log(props)
    const {image, title, description} = props;


    return <Wrapper {...props}>
        <Card>
            <Image src={image} wrapped ui={false}/>
            <Card.Content>
                <Card.Header>{title}</Card.Header>
                <Card.Description>
                    <StyledDescription>{description} </StyledDescription>
                </Card.Description>
            </Card.Content>
        </Card>
    </Wrapper>;
}


const Wrapper = (props) => {
    const {href, extra, children, disabled} = props;
    const target = href.startsWith("http") ? "_blank" : "_self";
    if (extra) {
        return <StyledA target={target} rel='noopener noreferrer' href={href} disabled={disabled}>
            {children}
        </StyledA>
    }
    return <StyledLink to={href} disabled={disabled}>
        {children}
    </StyledLink>
}

export default CardItem;
