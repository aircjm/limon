import styled from 'styled-components';


export const StyledDescription = styled.span`
  color: rgba(1,1,1, 0.33);
  font-size: .85rem;
  height: 20px;
  line-height: 20px;
  //opacity: .9;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

  display: inline;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
    margin: 0;
`

export const Bootstrap = styled.header`
  background: url("https://cdn.jansora.com/application/Jansora/2020/12/26/10:04:28/image.png");
  height: 150px;
  padding: 30px;
  flex-direction: column;
  align-items: center;
  display: flex;
`;


