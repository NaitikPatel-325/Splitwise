import React, { useContext } from 'react';
import UserContext from '../../context/create';

export const Home = () => {
  const {
    username,
    isLoggedIn,
    jwt,
    setIsLoggedIn,
    setUsername,
    setJwt,
  } = useContext(UserContext);

  return (
    <div className="flex flex-col justify-center items-center h-full mt-64">
      <h1 className="text-center text-5xl text-gray-600">
        <span className='text-white'>Share</span> <span className='text-green-500'>Expenses</span><br />
        <span className='text-white'>with</span> <span className='text-green-500'>Friends</span><br />
        <span className='text-white'>and Family</span>
      </h1>
      <button className="bg-green-500 hover:bg-green-700 text-white  py-4 px-8 rounded mt-4">
        Create Group
      </button>
    </div>
  );
};
