import React, { useEffect, useState } from "react";
import UserContext from "./create";
import axios from 'axios';

export const UserContextProvider = ({ children }) => {
  const [user, setUser] = useState(() => {
    const storedUser = sessionStorage.getItem('user');
    return storedUser ? JSON.parse(storedUser) : {
      fullname: "",
      email: "",
      picture: "",
    };
  });

  const [isLoggedIn, setIsLoggedIn] = useState(() => {
    return JSON.parse(sessionStorage.getItem('isLoggedIn')) || false;
  });

  useEffect(() => {
    const checkAuthentication = async () => {
      try {
        await axios.get('http://localhost:8080/api/user', { withCredentials: true });
        setIsLoggedIn(true);
      } catch (error) {
        sessionStorage.removeItem('user');
        sessionStorage.removeItem('isLoggedIn');
        setUser({
          fullname: "",
          email: "",
          picture: "",
        });
        setIsLoggedIn(false);
      }
    };  

    checkAuthentication();
  }, []);

  useEffect(() => {
    if (isLoggedIn) {
      sessionStorage.setItem('user', JSON.stringify(user));
      sessionStorage.setItem('isLoggedIn', JSON.stringify(isLoggedIn));
    } else {
      sessionStorage.removeItem('user');
      sessionStorage.removeItem('isLoggedIn');
    }
  }, [user, isLoggedIn]);

  const userContextValue = {
    user,
    setUser,
    isLoggedIn,
    setIsLoggedIn,
  };

  return (
    <UserContext.Provider value={userContextValue}>
      {children}
    </UserContext.Provider>
  );
};
