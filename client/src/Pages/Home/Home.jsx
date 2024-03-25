import React,{useContext} from 'react'
import UserContext from '../../context/create';

export const Home = () => {
  const {
    username,
    jwt,
    setIsLoggedIn,
    setUsername,
    setJwt,
    isLoggedIn,
  } = useContext(UserContext);
  
  return (
    <div>
      <p>Welcome {username} and {isLoggedIn} {jwt}</p>
      <div>Home</div>
    </div>
  )
}
