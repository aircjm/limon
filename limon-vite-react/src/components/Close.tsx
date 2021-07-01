import React from "react";
import { IconButton } from "@material-ui/core";
import styled from "styled-components";

const Container = styled.div`
  position: absolute;
  top: 1rem;
  right: 1rem;
`;

interface Props {
    onClose: () => void;
    onPopper?: boolean;
}

const Close = ({ onClose, onPopper = false }: Props) => {
    return (
        <Container>
            <IconButton
                size="small"
                onClick={onClose}
                aria-label="close"
                data-testid={onPopper ? "close-popper" : "close-dialog"}
            >
                {/*<TimesIcon />*/}
            </IconButton>
        </Container>
    );
};

export default Close;