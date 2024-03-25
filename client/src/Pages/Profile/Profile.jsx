import axios from 'axios'
import React,{useContext} from 'react'
import UserContext from '../../context/create';

export const Profile = () => {
  const {
    jwt,
    logout
  } = useContext(UserContext);

  const handleLogout = async () => {
      await axios.delete('http://localhost:8080/user/signout', {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      })
      .then(response => {
        console.log(response.data);
        logout();
        window.location.href = "/login";
      })
      .catch(error => {
        console.error("Error logging out:", error);
      });
  };

  return (
    <button onClick={handleLogout} className='text-white'>Logout</button>
  );
}
