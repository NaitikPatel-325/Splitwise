import React, { useEffect } from "react";
import axios from "axios";
import UserContext from "./create";


export const UserContextProvider = ({ children }) => {
  const [username, setUsername] = React.useState("");
  const [isLoggedIn, setIsLoggedIn] = React.useState(false);
  const [jwt, setJwt] = React.useState("");

  console.log("Context Value:", { username, isLoggedIn, jwt });

  const fetchUserData = async () => {
    try {
      const token = sessionStorage.getItem("jwt"); 
      if (token) {
        setJwt(token);

        const response = await axios.get("http://localhost:8080/user/data", {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });

        setUsername(response.data.username);
        setIsLoggedIn(true);
        console.log("User data fetched:", response.data);
      }
    } catch (error) {
      console.error("Error fetching user data:", error);
    }
  };

  useEffect(() => {
    fetchUserData();
  }, []); 

  const logout = () => {
    sessionStorage.removeItem("jwt");
    setUsername("");
    setIsLoggedIn(false);
    setJwt("");
  };

  const login = (jwtToken, username) => {
    sessionStorage.setItem("jwt", jwtToken);
    setJwt(jwtToken);
    setUsername(username);
    setIsLoggedIn(true);
  };

  const userContextValue = {
    username,
    isLoggedIn,
    jwt,
    login,
    logout,
    setJwt,
    setIsLoggedIn,
    setUsername
  };

  return (
    <UserContext.Provider value={userContextValue}>
      {children}
    </UserContext.Provider>
  );
};
